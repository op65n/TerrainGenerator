package op65n.tech.terraingenerator.terrain.generation;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import op65n.tech.terraingenerator.terrain.setting.TerrainSetting;
import org.bukkit.util.noise.PerlinNoiseGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class TerrainGeneration {

    private static final ExecutorService SERVICE = Executors.newCachedThreadPool(new ThreadFactoryBuilder()
            .setNameFormat("TerrainGeneration-[%d]")
            .setUncaughtExceptionHandler((thread, throwable) ->
                System.out.printf("Caught an exception on Thread: %s, Throwable: %s", thread.getId(), throwable.getMessage())
            )
            .build());

    private final TerrainSetting setting;
    private final int[][] terrain;
    private final int columns;
    private final int rows;

    public TerrainGeneration(@NotNull final TerrainSetting setting) {
        this.setting = setting;

        columns = setting.getWidth();
        rows = setting.getHeight();
        terrain = new int[columns][rows];

        final PerlinNoiseGenerator generator = new PerlinNoiseGenerator(new Random());
        map(generator);
    }

    private void map(@NotNull final PerlinNoiseGenerator generator) {
        SERVICE.submit(() -> {
            float yOffset = 0;
            for (int z = 0; z < rows; z++) {
                float xOffset = 0;
                for (int x = 0; x < columns; x++) {
                    int noise = (int) ((generator.noise(xOffset, yOffset, this.setting.getOctaves(), this.setting.getFrequency(), this.setting.getAmplitude())
                            * this.setting.getDefaultHeight())
                            * this.setting.getScale());

                    terrain[x][z] = noise;
                    xOffset += this.setting.getOffset();
                }

                yOffset += this.setting.getOffset();
            }
        });
    }

    public int[][] getTerrain() {
        return this.terrain;
    }

}
