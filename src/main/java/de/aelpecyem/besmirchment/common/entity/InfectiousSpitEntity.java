package de.aelpecyem.besmirchment.common.entity;

import de.aelpecyem.besmirchment.common.entity.interfaces.DyeableEntity;
import de.aelpecyem.besmirchment.common.registry.BSMEntityTypes;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.projectile.LlamaSpitEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.potion.PotionUtil;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.HashSet;
import java.util.Set;

public class InfectiousSpitEntity extends LlamaSpitEntity implements DyeableEntity {
    private final Set<StatusEffectInstance> effects = new HashSet<>();
    private static final TrackedData<Integer> COLOR = DataTracker.registerData(InfectiousSpitEntity.class, TrackedDataHandlerRegistry.INTEGER);
    public InfectiousSpitEntity(EntityType<? extends LlamaSpitEntity> entityType, World world) {
        super(entityType, world);
    }

    public InfectiousSpitEntity(World world, Set<StatusEffectInstance> effects) {
        this(BSMEntityTypes.INFECTIOUS_SPIT, world);
        this.effects.addAll(effects);
        initColor();
    }

    @Environment(EnvType.CLIENT)
    public InfectiousSpitEntity(World world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
        this(BSMEntityTypes.INFECTIOUS_SPIT, world);
        this.updatePosition(x, y, z);
        this.setVelocity(velocityX, velocityY, velocityZ);
    }

    public void init(LivingEntity owner, LivingEntity target, Set<StatusEffectInstance> effects){
        setOwner(owner);
        this.updatePosition(owner.getX() - (double) owner.getWidth() * (double) MathHelper.sin(owner.bodyYaw * 0.017453292F), owner.getEyeY() - 0.10000000149011612D, owner.getZ() + (double) owner.getWidth() * (double) MathHelper.cos(owner.bodyYaw * 0.017453292F));
        if (target != null) {
            double targetX = target.getX() - this.getX();
            double targetY = target.getBodyY(0.3333333333333333D) - getY();
            double targetZ = target.getZ() - this.getZ();
            float g = MathHelper.sqrt(targetX * targetX + targetZ * targetZ) * 0.2F;
            setVelocity(targetX, targetY + (double) g, targetZ, 1.5F, 10.0F);
        }
        this.effects.addAll(effects);
        initColor();
    }

    private void initColor() {
        if (this.effects.isEmpty()) {
            this.dataTracker.set(COLOR, 0xFFFFFF);
        } else {
            this.dataTracker.set(COLOR, PotionUtil.getColor(effects));
        }
    }

    @Override
    public void tick() {
        if (world.isClient){
            this.spawnParticles(2);
        }
        super.tick();
    }

    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = this.getOwner();
        if (entity instanceof LivingEntity) {
            entityHitResult.getEntity().damage(DamageSource.mobProjectile(this, (LivingEntity)entity).setProjectile(), 1.0F);
            if (entityHitResult.getEntity() instanceof LivingEntity){
                LivingEntity hit = (LivingEntity) entityHitResult.getEntity();
                for (StatusEffectInstance effect : effects) {
                    if (effect.getEffectType().isInstant()){
                        effect.getEffectType().applyInstantEffect(this, entity, hit, effect.getAmplifier(), 0);
                    }else {
                        hit.addStatusEffect(effect);
                    }
                }
            }
        }
    }

    private void spawnParticles(int count) {
        int j = this.getColor();
        if (j != -1 && count > 0) {
            double r = (double)(j >> 16 & 255) / 255.0D;
            double g = (double)(j >> 8 & 255) / 255.0D;
            double b = (double)(j >> 0 & 255) / 255.0D;

            for(int k = 0; k < count; ++k) {
                this.world.addParticle(new DustParticleEffect((float) r, (float)g, (float) b, 3), this.getParticleX(0.5D), this.getRandomBodyY(), this.getParticleZ(0.5D), getVelocity().x, getVelocity().y, getVelocity().z);
            }

        }
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        dataTracker.startTracking(COLOR, 0xFFFFFF);
    }

    @Override
    public void setColor(int color) {
        dataTracker.set(COLOR, color);
    }

    @Override
    public int getColor() {
        return dataTracker.get(COLOR);
    }

    @Override
    public void writeCustomDataToTag(CompoundTag tag) {
        super.writeCustomDataToTag(tag);
        tag.putInt("Color", this.getColor());
        if (!this.effects.isEmpty()) {
            ListTag listTag = new ListTag();
            for (StatusEffectInstance effect : effects) {
                listTag.add(effect.toTag(new CompoundTag()));
            }
            tag.put("CustomPotionEffects", listTag);
        }
    }

    public void readCustomDataFromTag(CompoundTag tag) {
        super.readCustomDataFromTag(tag);
        effects.addAll(PotionUtil.getCustomPotionEffects(tag));
        this.setColor(tag.getInt("Color"));
    }
}
