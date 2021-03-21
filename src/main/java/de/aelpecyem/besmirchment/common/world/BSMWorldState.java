package de.aelpecyem.besmirchment.common.world;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.nbt.Tag;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.PersistentState;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BSMWorldState extends PersistentState {
    public final Map<UUID, BlockPos> phylacteries = new HashMap<>();

    public BSMWorldState(String key) {
        super(key);
    }

    public CompoundTag toTag(CompoundTag tag) {
        ListTag phylacteryList = new ListTag();
        phylacteries.forEach(((uuid, blockPos) -> {
            CompoundTag phylacteryTag = new CompoundTag();
            phylacteryTag.putUuid("Player", uuid);
            phylacteryTag.put("Pos", NbtHelper.fromBlockPos(blockPos));
            phylacteryList.add(phylacteryTag);
        }));
        tag.put("Phylacteries", phylacteryList);
        return tag;
    }

    public void addPhylactery(UUID uuid, BlockPos pos){
        phylacteries.put(uuid, pos);
        markDirty();
    }

    public void removePhylactery(UUID uuid){
        phylacteries.remove(uuid);
        markDirty();
    }

    public void fromTag(CompoundTag tag) {
        ListTag phylacteryList = tag.getList("Phylacteries", 10);
        for (Tag nbt : phylacteryList) {
            CompoundTag phylacteryTag = (CompoundTag) nbt;
            phylacteries.put(phylacteryTag.getUuid("Player"), NbtHelper.toBlockPos(phylacteryTag.getCompound("Pos")));
        }
    }

    public static BSMWorldState get(World world) {
        return ((ServerWorld) world).getPersistentStateManager().getOrCreate(() -> new BSMWorldState("bsm_data"), "bsm_data");
    }
}