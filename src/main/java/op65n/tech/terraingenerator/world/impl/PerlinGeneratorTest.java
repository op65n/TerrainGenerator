package op65n.tech.terraingenerator.world.impl;

import op65n.tech.terraingenerator.terrain.generation.TerrainGeneration;
import op65n.tech.terraingenerator.terrain.populator.impl.GrassPopulator;
import op65n.tech.terraingenerator.terrain.setting.TerrainSetting;
import op65n.tech.terraingenerator.world.TerrainGenerator;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public final class PerlinGeneratorTest extends TerrainGenerator {

    private static final int HEIGHT_NORMALIZATION = 60;
    private static final TerrainGeneration GENERATION = new TerrainGeneration(new TerrainSetting(
            100, 50000, 50000, 0.5D, 0.005, 8, 0.25D, .75D)
    );

    @NotNull
    @Override
    public ChunkData generateChunkData(@NotNull World world, @NotNull Random random, int chunkX, int chunkZ, @NotNull ChunkGenerator.BiomeGrid biome) {
        final ChunkData data = createChunkData(world);

        for (int z = 0; z < 16; z++) {
            for (int x = 0; x < 16; x++) {
                final int coordinateX = (chunkX << 4) + x;
                final int coordinateZ = (chunkZ << 4) + z;

                final int value;
                try {
                    value = GENERATION.getTerrain()[coordinateX][coordinateZ];
                } catch (final ArrayIndexOutOfBoundsException ignored) {
                    continue;
                }

                final int y = value + HEIGHT_NORMALIZATION;

                for (int height = y - 1; height >= y - 8; height--)
                    data.setBlock(x, height, z, Material.DIRT);

                for (int height = y - 9; height > 0; height--)
                    data.setBlock(x, height, z, Material.STONE);

                data.setBlock(x, 0, z, Material.BEDROCK);

                generateFloor(data, x, y, z);
                generateBeach(data, x, y, z);
                generateWater(data, x, y, z);
            }
        }

        return data;
    }

    private void generateFloor(@NotNull final ChunkData data, final int x, final int y, final int z) {
        if (y > 63)
            data.setBlock(x, y, z, Material.GRASS_BLOCK);
        else if (y > 56 && y < 63)
            data.setBlock(x, y, z, Material.SAND);
        else
            data.setBlock(x, y, z, Material.GRAVEL);
    }

    private void generateBeach(@NotNull final ChunkData data, final int x, final int y, final int z) {
        if (y > 65 || y < 60) return;

        for (int height = 65; height > 55; height--) {
            if (data.getType(x, height, z) == Material.AIR) continue;

            data.setBlock(x, height, z, Material.SAND);
        }
    }

    private void generateWater(@NotNull final ChunkData data, final int x, final int y, final int z) {
        if (y > 60) return;

        for (int height = 60; height > y; height--) {
            if (data.getType(x, height, z) != Material.AIR) continue;

            data.setBlock(x, height, z, Material.WATER);
        }
    }

    @NotNull
    @Override
    public List<BlockPopulator> getDefaultPopulators(@NotNull final World world) {
        return Arrays.asList(
                new GrassPopulator()
        );
    }
}
