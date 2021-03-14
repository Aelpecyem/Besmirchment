package de.aelpecyem.besmirchment.common.world;

import moriyashiine.bewitchment.common.world.BWUniversalWorldState;
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

public class BSMUniversalWorldState extends PersistentState {
    public final Map<UUID, BlockPos> phylacteries = new HashMap<>();

    public BSMUniversalWorldState(String key) {
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

    public void fromTag(CompoundTag tag) {
        ListTag phylacteryList = tag.getList("Phylacteries", 10);
        for (Tag nbt : phylacteryList) {
            CompoundTag phylacteryTag = (CompoundTag) nbt;
            phylacteries.put(phylacteryTag.getUuid("Player"), NbtHelper.toBlockPos(phylacteryTag.getCompound("Pos")));
        }
    }

    public static BSMUniversalWorldState get(World world) {
        return ((ServerWorld)world).getServer().getOverworld().getPersistentStateManager().getOrCreate(() -> new BSMUniversalWorldState("bsm_universal"), "bsm_universal");
    }
}