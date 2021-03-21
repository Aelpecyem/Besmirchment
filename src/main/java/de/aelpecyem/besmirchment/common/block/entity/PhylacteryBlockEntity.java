package de.aelpecyem.besmirchment.common.block.entity;

import de.aelpecyem.besmirchment.common.registry.BSMBlockEntityTypes;
import de.aelpecyem.besmirchment.common.transformation.LichAccessor;
import de.aelpecyem.besmirchment.common.transformation.LichLogic;
import de.aelpecyem.besmirchment.common.world.BSMWorldState;
import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Pair;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

import java.util.UUID;

public class PhylacteryBlockEntity extends BlockEntity implements BlockEntityClientSerializable {
    public static final int MAX_SOULS = 8;
    public int souls;
    public PhylacteryBlockEntity() {
        super(BSMBlockEntityTypes.PHYLACTERY);
    }

    @Override
    public void fromClientTag(CompoundTag compoundTag) {
        souls = compoundTag.getInt("Souls");
    }

    @Override
    public CompoundTag toClientTag(CompoundTag compoundTag) {
        compoundTag.putInt("Souls", souls);
        return compoundTag;
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        toClientTag(tag);
        return super.toTag(tag);
    }

    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);
        fromClientTag(tag);
    }

    public int addSouls(int amount){
        int cachedAmount = souls;
        this.souls = MathHelper.clamp(souls + amount, 0, MAX_SOULS);
        updatePlayerCache();
        markDirty();
        return souls - cachedAmount;
    }

    public boolean drainSoul(int amount){
        if ((souls - amount) >= 0){
            souls -= amount;
            updatePlayerCache();
            markDirty();
            return true;
        }
        return false;
    }

    public void updatePlayerCache(){
        if (world instanceof ServerWorld) {
            BSMWorldState worldState = BSMWorldState.get(world);
            UUID foundUUID = LichLogic.getPlayerForPhylactery(worldState, pos);
            if (foundUUID != null) {
                for (ServerPlayerEntity player : PlayerLookup.all(world.getServer())) {
                    if (player.getUuid().equals(foundUUID)) {
                        ((LichAccessor) player).updateCachedSouls();
                    }
                }
            }
        }
    }
}
