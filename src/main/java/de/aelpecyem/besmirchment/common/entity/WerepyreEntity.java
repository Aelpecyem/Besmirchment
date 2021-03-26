package de.aelpecyem.besmirchment.common.entity;

import moriyashiine.bewitchment.api.BewitchmentAPI;
import moriyashiine.bewitchment.client.network.packet.SpawnSmokeParticlesPacket;
import moriyashiine.bewitchment.common.entity.interfaces.DespawnAccessor;
import moriyashiine.bewitchment.common.entity.living.util.BWHostileEntity;
import moriyashiine.bewitchment.common.registry.BWSoundEvents;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.*;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.Random;

public class WerepyreEntity extends BWHostileEntity{
    public static final TrackedData<Integer> JUMP_TICKS = DataTracker.registerData(WerepyreEntity.class, TrackedDataHandlerRegistry.INTEGER);

    private boolean despawns = false;
    @Environment(EnvType.CLIENT)
    public float jumpBeginProgress = 0;

    public WerepyreEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    public void tick() {
        super.tick();
        if (world.isDay() && !world.isRaining() && world.isSkyVisible(getBlockPos())){
            setOnFireFor(8);
        }
        if (!this.world.isClient && this.despawns && this.age % 20 == 0 && this.world.isDay() && isValidMoonPhase(world)) {
            VillagerEntity entity = EntityType.VILLAGER.create(this.world);
            if (entity instanceof DespawnAccessor) {
                PlayerLookup.tracking(this).forEach((player) -> {
                    SpawnSmokeParticlesPacket.send(player, this);
                });
                entity.updatePositionAndAngles(this.getX(), this.getY(), this.getZ(), this.random.nextFloat() * 360.0F, 0.0F);
                entity.setHealth(entity.getMaxHealth() * (this.getHealth() / this.getMaxHealth()));
                entity.setFireTicks(this.getFireTicks());
                for (StatusEffectInstance statusEffect : getStatusEffects()) {
                    entity.addStatusEffect(statusEffect);
                }
                ((DespawnAccessor) entity).setDespawnTimer(1200);
                this.world.spawnEntity(entity);
                this.remove();
            }
        }
    }

    @Override
    public void tickMovement() {
        super.tickMovement();
        if (getLastJumpTime() < 200) {
            setLastJumpTime(getLastJumpTime() + 1);
        }
        if (world.isClient){
            if (getLastJumpTime() > 20) {
                if (jumpBeginProgress > 0) {
                    jumpBeginProgress -= 0.1;
                }
            }else if (jumpBeginProgress < 1){
                jumpBeginProgress += 0.1;
            }
        }
    }

    @Override
    public boolean handleFallDamage(float fallDistance, float damageMultiplier) {
        return super.handleFallDamage(Math.max(fallDistance - 12, 0), damageMultiplier);
    }

    protected boolean hasShiny() {
        return true;
    }

    public int getVariants() {
        return getVariantsStatic();
    }

    public EntityGroup getGroup() {
        return BewitchmentAPI.DEMON;
    }

    @Nullable
    protected SoundEvent getAmbientSound() {
        return BWSoundEvents.ENTITY_WEREWOLF_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        return BWSoundEvents.ENTITY_WEREWOLF_HURT;
    }

    protected SoundEvent getDeathSound() {
        return BWSoundEvents.ENTITY_WEREWOLF_DEATH;
    }

    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable CompoundTag entityTag) {
        EntityData data = super.initialize(world, difficulty, spawnReason, entityData, entityTag);
        if (this.dataTracker.get(VARIANT) != 0) {
            switch (world.getBiome(this.getBlockPos()).getCategory()) {
                case FOREST:
                    this.dataTracker.set(VARIANT, random.nextBoolean() ? 2 : 3);
                    break;
                case TAIGA:
                    this.dataTracker.set(VARIANT, random.nextBoolean() ? 1 : 4);
                    break;
                case ICY:
                    this.dataTracker.set(VARIANT, random.nextBoolean() ? 3 : 4);
                    break;
                default:
                    this.dataTracker.set(VARIANT, this.random.nextInt(this.getVariants() - 1) + 1);
            }
        }

        this.despawns = spawnReason == SpawnReason.NATURAL;
        return data;
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        dataTracker.startTracking(JUMP_TICKS, 40);
    }

    public int getLastJumpTime(){
        return dataTracker.get(JUMP_TICKS);
    }

    public void setLastJumpTime(int ticks){
        dataTracker.set(JUMP_TICKS, ticks);
    }

    public void readCustomDataFromTag(CompoundTag tag) {
        super.readCustomDataFromTag(tag);
        this.despawns = tag.getBoolean("Despawns");
        setLastJumpTime(tag.getInt("LastJumpTicks"));
    }


    public void writeCustomDataToTag(CompoundTag tag) {
        super.writeCustomDataToTag(tag);
        tag.putBoolean("Despawns", this.despawns);
        tag.putInt("LastJumpTicks", getLastJumpTime());
    }

    @Override
    protected void initGoals() {
        goalSelector.add(0, new SwimGoal(this));
        goalSelector.add(1, new PounceAtTargetGoal());
        goalSelector.add(2, new MeleeAttackGoal(this, 1, true));
        goalSelector.add(3, new WanderAroundFarGoal(this, 1));
        goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 8));
        goalSelector.add(5, new LookAroundGoal(this));
        targetSelector.add(0, new RevengeGoal(this));
        targetSelector.add(1, new FollowTargetGoal<>(this, LivingEntity.class, 10, true, false, entity -> entity instanceof PlayerEntity || entity instanceof SheepEntity || entity instanceof MerchantEntity || entity.getGroup() == EntityGroup.ILLAGER));
    }

    public static int getVariantsStatic() {
        return 5;
    }

    public boolean canJump(){
        return getLastJumpTime() > 5;
    }

    public static boolean isValidMoonPhase(WorldAccess world) {
        int phase = BewitchmentAPI.getMoonPhase(world);
        return phase == 0;
    }
    public static boolean canSpawn(EntityType<WerepyreEntity> type, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
        return world.getDifficulty() != Difficulty.PEACEFUL && world.getLevelProperties().getTime() > 24000L && isValidMoonPhase(world);
    }

    public class PounceAtTargetGoal extends Goal {
        private LivingEntity target;

        public PounceAtTargetGoal() {
            this.setControls(EnumSet.of(Goal.Control.JUMP, Goal.Control.MOVE));
        }

        public boolean canStart() {
            this.target = getTarget();
            if (this.target == null) {
                return false;
            } else {
                double d = distanceTo(this.target);
                if (d >= 3 && d <= 14.0D) {
                    if (!canJump()) {
                        return false;
                    } else {
                        return getRandom().nextInt(5) == 0;
                    }
                } else {
                    return false;
                }
            }
        }

        public boolean shouldContinue() {
            return !canJump();
        }

        public void start() {
            setLastJumpTime(0);
            lookAtEntity(this.target, 60, 60);
            Vec3d vec3d = getVelocity();
            Vec3d vec3d2 = new Vec3d(this.target.getX() - getX(), this.target.getY() + 1 - getY(), this.target.getZ() - getZ());
            if (vec3d2.lengthSquared() > 1.0E-7D) {
                vec3d2 = vec3d2.normalize().multiply(0.8D).add(vec3d.multiply(0.2D));
            }
            setVelocity(vec3d2.x, vec3d2.y, vec3d2.z);
        }
    }
}
