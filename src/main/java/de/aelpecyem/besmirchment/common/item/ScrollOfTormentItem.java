package de.aelpecyem.besmirchment.common.item;

import de.aelpecyem.besmirchment.common.Besmirchment;
import de.aelpecyem.besmirchment.common.entity.BeelzebubEntity;
import de.aelpecyem.besmirchment.common.registry.BSMEntityTypes;
import de.aelpecyem.besmirchment.common.registry.BSMUtil;
import moriyashiine.bewitchment.api.BewitchmentAPI;
import moriyashiine.bewitchment.common.item.ContractItem;
import moriyashiine.bewitchment.common.registry.BWDamageSources;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class ScrollOfTormentItem extends Item {
    public ScrollOfTormentItem() {
        super(new FabricItemSettings().group(Besmirchment.BESMIRCHMENT).maxCount(1).rarity(Rarity.UNCOMMON).fireproof());
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (!world.isClient && Besmirchment.config.mobs.enableBeelzebub && user instanceof PlayerEntity && BewitchmentAPI.drainMagic((PlayerEntity) user, 1, false)) {
            EntityHitResult hit = BSMUtil.hitscanEntity(world, user, 8, (target) -> target instanceof LivingEntity && !target.isSpectator() && user.canSee(target));
            if (hit != null && hit.getEntity() instanceof PigEntity) {
                world.createExplosion(hit.getEntity(), DamageSource.MAGIC, null, hit.getPos().x, hit.getPos().y, hit.getPos().z, 1, true, Explosion.DestructionType.NONE);
                LightningEntity lightning = EntityType.LIGHTNING_BOLT.create(world);
                lightning.updatePosition(hit.getPos().x, hit.getPos().y, hit.getPos().z);
                world.spawnEntity(lightning);
                BeelzebubEntity beelzebub = BSMEntityTypes.BEELZEBUB.create(world);
                beelzebub.updatePositionAndAngles(hit.getPos().x, hit.getPos().y, hit.getPos().z, user.getRandom().nextFloat() * 360, 0);
                hit.getEntity().damage(new BWDamageSources.MagicPlayer(user), 42069);
                world.spawnEntity(beelzebub);
                stack.decrement(1);
            }
        }
        return super.finishUsing(stack, world, user);
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (!Besmirchment.config.mobs.enableBeelzebub){
            return;
        }
        EntityHitResult hit = BSMUtil.hitscanEntity(world, user, 8, (target) -> target instanceof LivingEntity && !target.isSpectator() && user.canSee(target));
        if (world.isClient) {
            if (hit != null && hit.getEntity() instanceof PigEntity) {
                world.addParticle(ParticleTypes.ENTITY_EFFECT, hit.getEntity().getParticleX(0.5D), hit.getEntity().getRandomBodyY(), hit.getEntity().getParticleZ(0.5D), 0.45, 0, 0);
            }
        }else{
            if (hit != null && hit.getEntity() instanceof PigEntity){
                ((PigEntity) hit.getEntity()).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 40, 10, true, false));
            }
            BewitchmentAPI.drainMagic((PlayerEntity) user, 1, false);
        }
        super.usageTick(world, user, stack, remainingUseTicks);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return ItemUsage.consumeHeldItem(world, user, hand);
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 90;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }
}
