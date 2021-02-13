package op65n.tech.terraingeneration.world.impl;

import op65n.tech.terraingeneration.terrain.populator.impl.GrassPopulator;
import op65n.tech.terraingeneration.terrain.util.TerrainUtil;
import op65n.tech.terraingeneration.world.TerrainGenerator;
import op65n.tech.terrainrenderer.terrain.ProceduralTerrainGeneration;
import op65n.tech.terrainrenderer.terrain.height.HeightMap;
import op65n.tech.terrainrenderer.terrain.setting.TerrainSetting;
import op65n.tech.terrainrenderer.terrain.setting.object.OctaveOffset;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public final class InfinitePerlinGenerator extends TerrainGenerator {

    private static final ProceduralTerrainGeneration GENERATION = new ProceduralTerrainGeneration(new TerrainSetting(
            1.2, 4, 2.0, 0.6, 130102, new OctaveOffset(0, 0))
    );

    @NotNull
    @Override
    public ChunkData generateChunkData(@NotNull World world, @NotNull Random random, int chunkX, int chunkZ, @NotNull ChunkGenerator.BiomeGrid biome) {
        final ChunkData data = createChunkData(world);
        final float[][] noiseMap = GENERATION.getNoiseMapForChunk(chunkX, chunkZ);

        for (int z = 0; z < 16; z++) {
            for (int x = 0; x < 16; x++) {
                final float value;
                try { value = (int) noiseMap[x][z];
                } catch (final ArrayIndexOutOfBoundsException ignored) { continue; }

                final int y = (int) (value * HeightMap.getMultiplierForHeight(value) * 100);
                for (int height = y - 1; height >= y - 8; height--)
                    data.setBlock(x, height, z, Material.DIRT);

                for (int height = y - 9; height > 0; height--)
                    data.setBlock(x, height, z, Material.STONE);

                data.setBlock(x, 0, z, Material.BEDROCK);

                TerrainUtil.generateFloor(data, x, y, z);
                TerrainUtil.generateBeach(data, x, y, z);
                TerrainUtil.generateWater(data, x, y, z);
            }
        }

        return data;
    }

    @NotNull
    @Override
    public List<BlockPopulator> getDefaultPopulators(@NotNull final World world) {
        return Arrays.asList(
                new GrassPopulator()
        );
    }

}
