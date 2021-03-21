package de.aelpecyem.besmirchment.common.transformation;

import de.aelpecyem.besmirchment.common.block.entity.PhylacteryBlockEntity;
import de.aelpecyem.besmirchment.common.packet.LichRevivePacket;
import de.aelpecyem.besmirchment.common.world.BSMWorldState;
import moriyashiine.bewitchment.api.interfaces.entity.TransformationAccessor;
import moriyashiine.bewitchment.common.misc.BWUtil;
import moriyashiine.bewitchment.common.registry.BWStatusEffects;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Pair;

public class LichLogic {
    public static final int STAGE_TWO_SOULS = 6;

    public static boolean revive(LivingEntity lich, DamageSource source, int lastRevive) {
        Pair<ServerWorld, PhylacteryBlockEntity> phylactery = getPhylactery(lich);
        if (phylactery != null && phylactery.getRight().drainSoul(1)) {
            lich.setHealth(lich.getMaxHealth());
            lich.clearStatusEffects();
            lich.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 100, 1));
            lich.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 200, 0));
            lich.addStatusEffect(new StatusEffectInstance(BWStatusEffects.ETHEREAL, 200, 0));
            LichRevivePacket.send(lich);
            if (lich instanceof TransformationAccessor && ((TransformationAccessor) lich).getAlternateForm()){
                ((TransformationAccessor) lich).setAlternateForm(false);
            }
            if (lich instanceof ServerPlayerEntity && (source.isOutOfWorld() || (lastRevive < 600 && lich.isSneaking()))) {
                if (!phylactery.getLeft().equals(lich.world)) {
                    ((ServerPlayerEntity) lich).teleport(phylactery.getLeft(), lich.getX(), lich.getY(), lich.getZ(), lich.yaw, lich.pitch);
                }
                BWUtil.attemptTeleport(lich, phylactery.getRight().getPos(), 2, false);
                LichRevivePacket.send(lich);
            }
            return true;
        }
        return false;
    }

    public static Pair<ServerWorld, PhylacteryBlockEntity> getPhylactery(LivingEntity player) {
        if (player.world instanceof ServerWorld) {
            for (ServerWorld serverWorld : player.world.getServer().getWorlds()) {
                BSMWorldState worldState = BSMWorldState.get(serverWorld);
                if (worldState.phylacteries.containsKey(player.getUuid())) {
                    BlockEntity entity = serverWorld.getBlockEntity(worldState.phylacteries.get(player.getUuid()));
                    if (entity instanceof PhylacteryBlockEntity) {
                        return new Pair<>(serverWorld, (PhylacteryBlockEntity) entity);
                    }
                    worldState.phylacteries.remove(player.getUuid());
                }
            }
        }
        return null;
    }
}
