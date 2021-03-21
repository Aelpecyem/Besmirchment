package de.aelpecyem.besmirchment.common.transformation;

import de.aelpecyem.besmirchment.common.block.entity.PhylacteryBlockEntity;
import de.aelpecyem.besmirchment.common.packet.LichRevivePacket;
import de.aelpecyem.besmirchment.common.world.BSMWorldState;
import moriyashiine.bewitchment.api.interfaces.entity.TransformationAccessor;
import moriyashiine.bewitchment.common.misc.BWUtil;
import moriyashiine.bewitchment.common.registry.BWStatusEffects;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Pair;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.UUID;

public class LichLogic {
    public static final int STAGE_TWO_SOULS = 6;
    private static final EntityAttributeModifier LICH_STRENGTH_MODIFIER_0 = new EntityAttributeModifier(UUID.randomUUID(), "Transformation modifier", -2, EntityAttributeModifier.Operation.ADDITION);
    private static final EntityAttributeModifier LICH_STRENGTH_MODIFIER_1 = new EntityAttributeModifier(UUID.randomUUID(), "Transformation modifier", 1, EntityAttributeModifier.Operation.ADDITION);
    private static final EntityAttributeModifier LICH_STRENGTH_MODIFIER_2 = new EntityAttributeModifier(UUID.randomUUID(), "Transformation modifier", 2, EntityAttributeModifier.Operation.ADDITION);
    private static final EntityAttributeModifier LICH_MOVEMENT_SPEED_MODIFIER_0 = new EntityAttributeModifier(UUID.randomUUID(), "Transformation modifier", -0.08, EntityAttributeModifier.Operation.ADDITION);
    private static final EntityAttributeModifier LICH_MOVEMENT_SPEED_MODIFIER_1 = new EntityAttributeModifier(UUID.randomUUID(), "Transformation modifier", -0.02, EntityAttributeModifier.Operation.ADDITION);
    private static final EntityAttributeModifier LICH_MOVEMENT_SPEED_MODIFIER_2 = new EntityAttributeModifier(UUID.randomUUID(), "Transformation modifier", 0.04, EntityAttributeModifier.Operation.ADDITION);
    private static final EntityAttributeModifier LICH_HEALTH_MODIFIER_0 = new EntityAttributeModifier(UUID.randomUUID(), "Transformation modifier", -10, EntityAttributeModifier.Operation.ADDITION);
    private static final EntityAttributeModifier LICH_HEALTH_MODIFIER_1 = new EntityAttributeModifier(UUID.randomUUID(), "Transformation modifier", -6, EntityAttributeModifier.Operation.ADDITION);
    private static final EntityAttributeModifier LICH_HEALTH_MODIFIER_2 = new EntityAttributeModifier(UUID.randomUUID(), "Transformation modifier", 4, EntityAttributeModifier.Operation.ADDITION);
    private static final EntityAttributeModifier LICH_HEALTH_MODIFIER_3 = new EntityAttributeModifier(UUID.randomUUID(), "Transformation modifier", 10, EntityAttributeModifier.Operation.ADDITION);
    private static final EntityAttributeModifier LICH_HEALTH_MODIFIER_4 = new EntityAttributeModifier(UUID.randomUUID(), "Transformation modifier", 20, EntityAttributeModifier.Operation.ADDITION);
    private static final EntityAttributeModifier LICH_ARMOR_MODIFIER= new EntityAttributeModifier(UUID.randomUUID(), "Transformation modifier", 4, EntityAttributeModifier.Operation.ADDITION);

    public static void addAttributes(LivingEntity lich, int cachedSouls) {
        EntityAttributeInstance attackDamageAttribute = lich.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE);
        EntityAttributeInstance armorAttribute = lich.getAttributeInstance(EntityAttributes.GENERIC_ARMOR);
        EntityAttributeInstance movementSpeedAttribute = lich.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
        EntityAttributeInstance healthAttribute = lich.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);
        //clear all before adding new
        attackDamageAttribute.removeModifier(LICH_STRENGTH_MODIFIER_0);
        attackDamageAttribute.removeModifier(LICH_STRENGTH_MODIFIER_1);
        attackDamageAttribute.removeModifier(LICH_STRENGTH_MODIFIER_2);
        movementSpeedAttribute.removeModifier(LICH_MOVEMENT_SPEED_MODIFIER_0);
        movementSpeedAttribute.removeModifier(LICH_MOVEMENT_SPEED_MODIFIER_1);
        movementSpeedAttribute.removeModifier(LICH_MOVEMENT_SPEED_MODIFIER_2);
        healthAttribute.removeModifier(LICH_HEALTH_MODIFIER_0);
        healthAttribute.removeModifier(LICH_HEALTH_MODIFIER_1);
        healthAttribute.removeModifier(LICH_HEALTH_MODIFIER_2);
        healthAttribute.removeModifier(LICH_HEALTH_MODIFIER_3);
        healthAttribute.removeModifier(LICH_HEALTH_MODIFIER_4);
        armorAttribute.removeModifier(LICH_ARMOR_MODIFIER);
        if (cachedSouls < 0){
            return;
        }
        switch (cachedSouls){
            case 0:
                attackDamageAttribute.addPersistentModifier(LICH_STRENGTH_MODIFIER_0);
                healthAttribute.addPersistentModifier(LICH_HEALTH_MODIFIER_0);
                movementSpeedAttribute.addPersistentModifier(LICH_MOVEMENT_SPEED_MODIFIER_0);
                break;
            case 1:
            case 2:
                healthAttribute.addPersistentModifier(LICH_HEALTH_MODIFIER_1);
                movementSpeedAttribute.addPersistentModifier(LICH_MOVEMENT_SPEED_MODIFIER_1);
                break;
            case 3:
            case 4:
                healthAttribute.addPersistentModifier(LICH_HEALTH_MODIFIER_2);
                attackDamageAttribute.addPersistentModifier(LICH_STRENGTH_MODIFIER_1);
                movementSpeedAttribute.addPersistentModifier(LICH_MOVEMENT_SPEED_MODIFIER_2);
                break;
            case 5:
                healthAttribute.addPersistentModifier(LICH_HEALTH_MODIFIER_3);
                attackDamageAttribute.addPersistentModifier(LICH_STRENGTH_MODIFIER_1);
                armorAttribute.addPersistentModifier(LICH_ARMOR_MODIFIER);
                movementSpeedAttribute.addPersistentModifier(LICH_MOVEMENT_SPEED_MODIFIER_2);
                break;
            case 6:
                healthAttribute.addPersistentModifier(LICH_HEALTH_MODIFIER_3);
                attackDamageAttribute.addPersistentModifier(LICH_STRENGTH_MODIFIER_2);
                armorAttribute.addPersistentModifier(LICH_ARMOR_MODIFIER);
                movementSpeedAttribute.addPersistentModifier(LICH_MOVEMENT_SPEED_MODIFIER_2);
                break;
            case 7:
            case 8:
                healthAttribute.addPersistentModifier(LICH_HEALTH_MODIFIER_4);
                attackDamageAttribute.addPersistentModifier(LICH_STRENGTH_MODIFIER_2);
                armorAttribute.addPersistentModifier(LICH_ARMOR_MODIFIER);
                movementSpeedAttribute.addPersistentModifier(LICH_MOVEMENT_SPEED_MODIFIER_2);
                break;
        }
    }
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
                    worldState.removePhylactery(player.getUuid());
                }
            }
        }
        return null;
    }

    public static UUID getPlayerForPhylactery(BSMWorldState worldState, BlockPos pos){
        for (UUID uuid : worldState.phylacteries.keySet()) {
            if (worldState.phylacteries.get(uuid).equals(pos)) {
                return uuid;
            }
        }
        return null;
    }
}
