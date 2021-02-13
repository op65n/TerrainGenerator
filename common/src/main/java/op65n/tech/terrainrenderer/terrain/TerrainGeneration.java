package op65n.tech.terrainrenderer.terrain;

import op65n.tech.terrainrenderer.perlin.impl.PerlinNoiseGenerator;
import op65n.tech.terrainrenderer.terrain.height.HeightMap;
import op65n.tech.terrainrenderer.terrain.setting.TerrainSetting;
import op65n.tech.terrainrenderer.terrain.setting.object.OctaveOffset;
import op65n.tech.terrainrenderer.util.Math;

import java.util.SplittableRandom;

public final class TerrainGeneration {

    private final TerrainSetting setting;
    private float[][] noiseMap;

    public TerrainGeneration(final TerrainSetting setting) {
        this.setting = setting;

        noiseMap = new float[setting.getWidth()][setting.getHeight()];
        final PerlinNoiseGenerator generator = PerlinNoiseGenerator.getInstance();
        map(generator);
    }

    public void map(final PerlinNoiseGenerator generator) {
        noiseMap = new float[setting.getWidth()][setting.getHeight()];

        final SplittableRandom random = new SplittableRandom(this.setting.getSeed());
        final OctaveOffset octaveOffset = this.setting.getOffset();

        final OctaveOffset[] octaveOffsets = new OctaveOffset[this.setting.getOctaves()];
        for (int index = 0; index < this.setting.getOctaves(); index++) {
            final float offsetX = random.nextInt(-100000, 100000) + octaveOffset.getX();
            final float offsetY = random.nextInt(-100000, 100000) + octaveOffset.getY();
            octaveOffsets[index] = new OctaveOffset(offsetX, offsetY);
        }

        float maxNoiseHeight = Float.MIN_VALUE;
        float minNoiseHeight = Float.MAX_VALUE;

        final float halfWidth = this.setting.getWidth() / 2f;
        final float halfHeight = this.setting.getHeight() / 2f;

        for (int y = 0; y < this.setting.getHeight(); y++) {
            for (int x = 0; x < this.setting.getWidth(); x++) {
                float amplitude = 1;
                float frequency = 1;
                float noiseHeight = 0;

                for (int index = 0; index < this.setting.getOctaves(); index++) {
                    float sampleX = (float) ((x - halfWidth) / this.setting.getScale() * frequency + octaveOffsets[index].getX());
                    float sampleY = (float) ((y - halfHeight) / this.setting.getScale() * frequency + octaveOffsets[index].getY());

                    float perlinValue = (float) generator.noise(sampleX, sampleY) * 2 - 1;
                    perlinValue = (float) HeightMap.getMultiplierForHeight(perlinValue) * perlinValue;
                    noiseHeight += perlinValue * amplitude;

                    amplitude *= this.setting.getPersistence();
                    frequency *= this.setting.getLacunarity();
                }

                if (noiseHeight > maxNoiseHeight)
                    maxNoiseHeight = noiseHeight;
                else if (noiseHeight < minNoiseHeight)
                    minNoiseHeight = noiseHeight;

                noiseMap[x][y] = noiseHeight;
            }
        }

        for (int y = 0; y < this.setting.getHeight(); y++) {
            for (int x = 0; x < this.setting.getWidth(); x++) {
                noiseMap[x][y] = Math.inverseLerp(minNoiseHeight, maxNoiseHeight, noiseMap[x][y]);
            }
        }
    }

    public float[][] getNoiseMap() {
        return this.noiseMap;
    }

}
