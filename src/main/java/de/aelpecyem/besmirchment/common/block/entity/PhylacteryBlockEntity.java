package de.aelpecyem.besmirchment.common.block.entity;

import de.aelpecyem.besmirchment.common.registry.BSMBlockEntityTypes;
import de.aelpecyem.besmirchment.common.world.BSMUniversalWorldState;
import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.command.TeleportCommand;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Pair;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;

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

    public static Pair<ServerWorld, BlockPos> getPhylactery(PlayerEntity player){
        if (player.world instanceof ServerWorld){
            for (ServerWorld world : player.world.getServer().getWorlds()) {
               BSMUniversalWorldState worldState = BSMUniversalWorldState.get(world);
               if (worldState.phylacteries.containsKey(player.getUuid())){
                   return new Pair<>(world, worldState.phylacteries.get(player.getUuid()));
               }
            }
        }
        return null;
    }
}
