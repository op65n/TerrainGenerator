package op65n.tech.terraingeneration.renderer;

import op65n.tech.terraingeneration.terrain.TerrainGeneration;

public final class ImageRenderer {

    private final TerrainGeneration generation;

    public ImageRenderer(final TerrainGeneration generation) {
        this.generation = generation;
    }

    public void render() {
        final float[][] noiseMap = generation.getNoiseMap();

        for (int z = 0; z < noiseMap.length; z++) {
            for (int x = 0; x < noiseMap[1].length; x++) {
                final float value;
                try {
                    value = noiseMap[x][z];
                } catch (final ArrayIndexOutOfBoundsException ignored) {
                    continue;
                }

            }
        }
    }

}
