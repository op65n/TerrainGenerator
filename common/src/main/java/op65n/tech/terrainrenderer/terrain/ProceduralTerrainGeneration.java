package op65n.tech.terrainrenderer.terrain;

import op65n.tech.terrainrenderer.perlin.impl.PerlinNoiseGenerator;
import op65n.tech.terrainrenderer.terrain.height.HeightMap;
import op65n.tech.terrainrenderer.terrain.setting.TerrainSetting;
import op65n.tech.terrainrenderer.terrain.setting.object.OctaveOffset;
import op65n.tech.terrainrenderer.util.Math;

import java.util.SplittableRandom;

public final class ProceduralTerrainGeneration {

    private static final PerlinNoiseGenerator GENERATOR = PerlinNoiseGenerator.getInstance();

    private final TerrainSetting setting;
    private final SplittableRandom random;
    private final OctaveOffset octaveOffset;

    public ProceduralTerrainGeneration(final TerrainSetting setting) {
        this.setting = setting;

        this.random = new SplittableRandom(this.setting.getSeed());
        this.octaveOffset = this.setting.getOffset();
    }

    public float[][] getNoiseMapForChunk(final int chunkX, final int chunkZ) {
        final float[][] noiseMap = new float[256][256];

        final OctaveOffset[] octaveOffsets = new OctaveOffset[this.setting.getOctaves()];
        for (int index = 0; index < this.setting.getOctaves(); index++) {
            final float offsetX = random.nextInt(-100000, 100000) + octaveOffset.getX();
            final float offsetY = random.nextInt(-100000, 100000) + octaveOffset.getY();
            octaveOffsets[index] = new OctaveOffset(offsetX, offsetY);
        }

        float maxNoiseHeight = Float.MIN_VALUE;
        float minNoiseHeight = Float.MAX_VALUE;

        for (int y = 0; y < 16; y++) {
            for (int x = 0; x < 16; x++) {
                float amplitude = 1;
                float frequency = 1;
                float noiseHeight = 0;

                for (int index = 0; index < this.setting.getOctaves(); index++) {
                    final float sampleX = (float) ((x + chunkX << 4) / this.setting.getScale() * frequency + octaveOffsets[index].getX());
                    final float sampleY = (float) ((y + chunkZ << 4) / this.setting.getScale() * frequency + octaveOffsets[index].getY());

                    float perlinValue = (float) GENERATOR.noise(sampleX, sampleY) * 2 - 1;
                    //perlinValue = (float) HeightMap.getMultiplierForHeight(perlinValue) * perlinValue;
                    noiseHeight += perlinValue * amplitude;

                    amplitude *= this.setting.getPersistence();
                    frequency *= this.setting.getLacunarity();
                }

                if (noiseHeight > maxNoiseHeight)
                    maxNoiseHeight = noiseHeight;
                else if (noiseHeight < minNoiseHeight)
                    minNoiseHeight = noiseHeight;

                noiseMap[x][y] = noiseHeight;
                System.out.println(noiseHeight);
            }
        }

        for (int y = 0; y < 16; y++) {
            for (int x = 0; x < 16; x++) {
                noiseMap[x][y] = Math.inverseLerp(minNoiseHeight, maxNoiseHeight, noiseMap[x][y]);
            }
        }

        return noiseMap;
    }

}
