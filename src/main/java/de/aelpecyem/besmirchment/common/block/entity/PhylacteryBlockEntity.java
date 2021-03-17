package de.aelpecyem.besmirchment.common.block.entity;

import de.aelpecyem.besmirchment.common.registry.BSMBlockEntityTypes;
import de.aelpecyem.besmirchment.common.world.BSMWorldState;
import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Pair;
import net.minecraft.util.math.BlockPos;

public class PhylacteryBlockEntity extends BlockEntity implements BlockEntityClientSerializable {
    private int souls;
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

    public static Pair<ServerWorld, PhylacteryBlockEntity> getPhylactery(LivingEntity player){
        if (player.world instanceof ServerWorld){
            for (ServerWorld serverWorld : player.world.getServer().getWorlds()) {
               BSMWorldState worldState = BSMWorldState.get(serverWorld);
               if (worldState.phylacteries.containsKey(player.getUuid())){
                   BlockEntity entity = serverWorld.getBlockEntity(worldState.phylacteries.get(player.getUuid()));
                   if (entity instanceof PhylacteryBlockEntity){
                       return new Pair<>(serverWorld, (PhylacteryBlockEntity) entity);
                   }
                   worldState.phylacteries.remove(player.getUuid());
               }
            }
        }
        return null;
    }
}
