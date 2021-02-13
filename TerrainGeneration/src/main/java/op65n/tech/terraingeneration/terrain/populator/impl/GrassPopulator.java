package op65n.tech.terraingeneration.terrain.populator.impl;

import op65n.tech.terraingeneration.terrain.populator.TerrainPopulator;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public final class GrassPopulator extends TerrainPopulator {

    @Override
    public void populate(@NotNull final World world, @NotNull final Random random, @NotNull final Chunk source) {
        final int chunkX = source.getX();
        final int chunkZ = source.getZ();

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                final int coordinateX = (chunkX << 4) + x;
                final int coordinateZ = (chunkZ << 4) + z;
                final int y = getHighestBlockAt(world, x, z);

                if (y < 65) continue;
                generateGrass(world, x, y, z);
            }
        }
    }

    private void generateGrass(final World world, final int x, final int y, final int z) {

    }

}
