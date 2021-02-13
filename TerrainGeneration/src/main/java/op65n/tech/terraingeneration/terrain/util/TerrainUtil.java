package op65n.tech.terraingeneration.terrain.util;

import org.bukkit.Material;
import org.bukkit.generator.ChunkGenerator;
import org.jetbrains.annotations.NotNull;

public final class TerrainUtil {

    public static void generateFloor(@NotNull final ChunkGenerator.ChunkData data, final int x, final int y, final int z) {
        if (y > 63)
            data.setBlock(x, y, z, Material.GRASS_BLOCK);
        else if (y > 56 && y < 63)
            data.setBlock(x, y, z, Material.SAND);
        else
            data.setBlock(x, y, z, Material.GRAVEL);
    }

    public static void generateBeach(@NotNull final ChunkGenerator.ChunkData data, final int x, final int y, final int z) {
        if (y > 65 || y < 60) return;

        for (int height = 65; height > 55; height--) {
            if (data.getType(x, height, z) == Material.AIR) continue;

            data.setBlock(x, height, z, Material.SAND);
        }
    }

    public static void generateWater(@NotNull final ChunkGenerator.ChunkData data, final int x, final int y, final int z) {
        if (y > 60) return;

        for (int height = 60; height > y; height--) {
            if (data.getType(x, height, z) != Material.AIR) continue;

            data.setBlock(x, height, z, Material.WATER);
        }
    }

}
