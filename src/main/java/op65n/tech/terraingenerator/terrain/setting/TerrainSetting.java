package op65n.tech.terraingenerator.terrain.setting;

import op65n.tech.terraingenerator.terrain.generation.object.OctaveOffset;

public final class TerrainSetting {

    private final int defaultHeight;
    private final int width;
    private final int height;
    private final double scale;
    private final double offset;
    private final int octaves;
    private final double frequency;
    private final double amplitude;

    private final double lacunarity;
    private final double persistance;
    private final long seed;
    private final OctaveOffset octaveOffset;

    public TerrainSetting(final int defaultHeight, final int width, final int height, final double scale, final double offset,
                          final int octaves, final double frequency, final double amplitude) {
        this.defaultHeight = defaultHeight;
        this.width = width;
        this.height = height;
        this.scale = scale;
        this.offset = offset;
        this.octaves = octaves;
        this.frequency = frequency;
        this.amplitude = amplitude;

        this.lacunarity = 2;
        this.persistance = 0.5;
        this.seed = 0;
        this.octaveOffset = new OctaveOffset(0, 0);
    }

    public TerrainSetting(final int width, final int height, final double scale,
                          final int octaves, final double lacunarity, final double persistance,
                          final long seed, final OctaveOffset octaveOffset) {
        this.defaultHeight = 0;
        this.width = width;
        this.height = height;
        this.scale = scale;
        this.offset = 0;
        this.octaves = octaves;
        this.frequency = 0;
        this.amplitude = 0;

        this.lacunarity = lacunarity;
        this.persistance = persistance;
        this.seed = seed;
        this.octaveOffset = octaveOffset;
    }

    public int getDefaultHeight() {
        return this.defaultHeight;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public double getScale() {
        return this.scale;
    }

    public double getOffset() {
        return this.offset;
    }

    public int getOctaves() {
        return this.octaves;
    }

    public double getFrequency() {
        return this.frequency;
    }

    public double getAmplitude() {
        return this.amplitude;
    }

    public double getLacunarity() {
        return this.lacunarity;
    }

    public double getPersistance() {
        return this.persistance;
    }

    public long getSeed() {
        return this.seed;
    }

    public OctaveOffset getOctaveOffset() {
        return this.octaveOffset;
    }
}
