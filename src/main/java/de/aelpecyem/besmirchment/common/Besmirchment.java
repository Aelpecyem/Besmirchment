package de.aelpecyem.besmirchment.common;

import de.aelpecyem.besmirchment.client.packet.FamiliarAbilityPacket;
import de.aelpecyem.besmirchment.client.packet.WerepyreJumpPacket;
import de.aelpecyem.besmirchment.common.entity.WerepyreEntity;
import de.aelpecyem.besmirchment.common.entity.interfaces.DyeableEntity;
import de.aelpecyem.besmirchment.common.packet.SparklePacket;
import de.aelpecyem.besmirchment.common.registry.*;
import de.aelpecyem.besmirchment.common.transformation.LichAccessor;
import de.aelpecyem.besmirchment.common.transformation.WerepyreAccessor;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import moriyashiine.bewitchment.api.BewitchmentAPI;
import moriyashiine.bewitchment.api.event.AllowVampireBurn;
import moriyashiine.bewitchment.api.event.BloodSetEvents;
import moriyashiine.bewitchment.api.event.BloodSuckEvents;
import moriyashiine.bewitchment.api.event.ReviveEvents;
import moriyashiine.bewitchment.api.interfaces.entity.BloodAccessor;
import moriyashiine.bewitchment.api.interfaces.entity.CurseAccessor;
import moriyashiine.bewitchment.api.interfaces.entity.TransformationAccessor;
import moriyashiine.bewitchment.client.network.packet.SpawnSmokeParticlesPacket;
import moriyashiine.bewitchment.common.entity.living.VampireEntity;
import moriyashiine.bewitchment.common.entity.living.util.BWHostileEntity;
import moriyashiine.bewitchment.common.registry.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;

public class Besmirchment implements ModInitializer {
    public static final String MODID = "besmirchment";
    public static final ItemGroup BESMIRCHMENT = FabricItemGroupBuilder.create(Besmirchment.id("group")).icon(() -> new ItemStack(BSMObjects.FINAL_BROOM)).build();
    public static BSMConfig config;

    @Override
    public void onInitialize() {
        AutoConfig.register(BSMConfig.class, GsonConfigSerializer::new);
        config = AutoConfig.getConfigHolder(BSMConfig.class).getConfig();
        BSMConditions.init();
        BSMContracts.init();
        BSMEntityTypes.init();
        BSMObjects.init();
        BSMBlockEntityTypes.init();
        BSMStatusEffects.init();
        BSMTransformations.init();
        BSMSounds.init();
        BSMRitualFunctions.init();
        ServerPlayNetworking.registerGlobalReceiver(FamiliarAbilityPacket.ID, FamiliarAbilityPacket::handle);
        ServerPlayNetworking.registerGlobalReceiver(WerepyreJumpPacket.ID, WerepyreJumpPacket::handle);
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            if (handler.player instanceof LichAccessor) {
                ((LichAccessor) handler.player).updateCachedSouls();
            }
        });
        ServerPlayerEvents.AFTER_RESPAWN.register((oldPlayer, newPlayer, b) -> {
            ((LichAccessor) newPlayer).updateCachedSouls();
            ((WerepyreAccessor) newPlayer).setWerepyreVariant(((WerepyreAccessor) oldPlayer).getWerepyreVariant());
            ((DyeableEntity) newPlayer).setColor(((DyeableEntity) oldPlayer).getColor());
        });
        ReviveEvents.ON_REVIVE.register((playerEntity, source, itemStack) -> {
            if (((CurseAccessor) this).hasCurse(BWCurses.SUSCEPTIBILITY)) {
                TransformationAccessor transformationAccessor = (TransformationAccessor) playerEntity;
                if (transformationAccessor.getTransformation() == BWTransformations.WEREWOLF || transformationAccessor.getTransformation() == BWTransformations.HUMAN) { //no vampire because they can't use totems
                    boolean sourceVampire = source.getSource() instanceof VampireEntity || (BewitchmentAPI.isVampire(source.getSource(), true) && source.getSource() instanceof PlayerEntity && BewitchmentAPI.isPledged((PlayerEntity) source.getSource(), BWPledges.LILITH));
                    boolean sourceWerepyre = source.getSource() instanceof WerepyreEntity || (BSMTransformations.isWerepyre(source.getSource(), true) && BSMTransformations.hasWerepyrePledge((PlayerEntity) source.getSource()));
                    if (sourceVampire || sourceWerepyre) {
                        transformationAccessor.getTransformation().onRemoved(playerEntity);
                        transformationAccessor.setTransformation(BSMTransformations.WEREPYRE);
                        transformationAccessor.getTransformation().onAdded(playerEntity);
                        PlayerLookup.tracking(playerEntity).forEach(foundPlayer -> SpawnSmokeParticlesPacket.send(foundPlayer, playerEntity));
                        SpawnSmokeParticlesPacket.send(playerEntity, playerEntity);
                        playerEntity.world.playSound(null, playerEntity.getBlockPos(), BWSoundEvents.ENTITY_GENERIC_CURSE, playerEntity.getSoundCategory(), 1, 1);
                        int variant = -1;
                        if (source.getSource() instanceof WerepyreEntity) {
                            variant = source.getSource().getDataTracker().get(BWHostileEntity.VARIANT);
                        } else if (source.getSource() instanceof WerepyreAccessor && BSMTransformations.hasWerepyrePledge((PlayerEntity) source.getSource())) {
                            variant = ((WerepyreAccessor) source.getSource()).getWerepyreVariant();
                        }
                        if (variant > -1) {
                            ((WerepyreAccessor) playerEntity).setWerepyreVariant(variant);
                        }
                    }
                }
            }
        });
        UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            if (entity instanceof LivingEntity && hand == Hand.MAIN_HAND && player.isSneaking() && entity.isAlive() && BSMTransformations.isWerepyre(player, true) && player.getStackInHand(hand).isEmpty()) {
                int toGive = BWTags.HAS_BLOOD.contains(entity.getType()) ? 5 : entity instanceof AnimalEntity ? 1 : 0;
                toGive = BloodSuckEvents.BLOOD_AMOUNT.invoker().onBloodSuck(player, (LivingEntity) entity, toGive);
                if (toGive > 0) {
                    BloodAccessor playerBlood = (BloodAccessor) player;
                    BloodAccessor entityBlood = (BloodAccessor) entity;
                    if (playerBlood.fillBlood(toGive, true) && entityBlood.drainBlood(10, true)) {
                        if (!world.isClient && ((LivingEntity) entity).hurtTime == 0) {
                            BloodSuckEvents.ON_BLOOD_SUCK.invoker().onBloodSuck(player, (LivingEntity) entity, toGive);
                            world.playSound(null, player.getBlockPos(), SoundEvents.ITEM_HONEY_BOTTLE_DRINK, player.getSoundCategory(), 1, 0.5f);
                            if (!((LivingEntity) entity).isSleeping() || entityBlood.getBlood() < entityBlood.MAX_BLOOD / 2) {
                                entity.damage(BWDamageSources.VAMPIRE, 2);
                            }
                            playerBlood.fillBlood(toGive, false);
                            entityBlood.drainBlood(10, false);
                        }
                        return ActionResult.success(world.isClient);
                    }
                }
            }
            return ActionResult.PASS;
        });
        AllowVampireBurn.EVENT.register(playerEntity -> {
            if(playerEntity.hasStatusEffect(BSMStatusEffects.SUNSCREEN)){
                if (playerEntity.age % 20 == 0) {
                    playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 100, 1, true, true));
                    SparklePacket.send(playerEntity);
                }
                return false;
            }
            return true;
        });
    }

    public static Identifier id(String path) {
        return new Identifier(MODID, path);
    }
}
