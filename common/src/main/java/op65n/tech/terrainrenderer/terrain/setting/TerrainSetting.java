package op65n.tech.terrainrenderer.terrain.setting;

import op65n.tech.terrainrenderer.terrain.setting.object.OctaveOffset;

public final class TerrainSetting {

    private int width;
    private int height;
    private double scale;
    private int octaves;
    private double lacunarity;
    private double persistence;
    private long seed;
    private OctaveOffset offset;

    public TerrainSetting(final int width, final int height, final double scale,
                          final int octaves, final double lacunarity, final double persistence,
                          final long seed, final OctaveOffset octaveOffset) {
        this.width = width;
        this.height = height;
        this.scale = scale;
        this.octaves = octaves;
        this.lacunarity = lacunarity;
        this.persistence = persistence;
        this.seed = seed;
        this.offset = octaveOffset;
    }

    public TerrainSetting(final double scale,
                          final int octaves, final double lacunarity, final double persistence,
                          final long seed, final OctaveOffset octaveOffset) {
        this.width = 0;
        this.height = 0;
        this.scale = scale;
        this.octaves = octaves;
        this.lacunarity = lacunarity;
        this.persistence = persistence;
        this.seed = seed;
        this.offset = octaveOffset;
    }

    public OctaveOffset getOffset() {
        return offset;
    }

    public void setOffset(OctaveOffset offset) {
        this.offset = offset;
    }

    public long getSeed() {
        return seed;
    }

    public void setSeed(long seed) {
        this.seed = seed;
    }

    public double getPersistence() {
        return persistence;
    }

    public void setPersistence(double persistence) {
        this.persistence = persistence;
    }

    public double getLacunarity() {
        return lacunarity;
    }

    public void setLacunarity(double lacunarity) {
        this.lacunarity = lacunarity;
    }

    public int getOctaves() {
        return octaves;
    }

    public void setOctaves(int octaves) {
        this.octaves = octaves;
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
