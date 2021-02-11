package op65n.tech.terraingeneration.terrain.height;

public enum HeightMap {

    WATER(0.4F, 1),
    LAND_LOW(0.6F, 1.05),
    LAND_MEDIUM(0.75F, 1.1),
    LAND_HIGH(0.9F, 1.15),
    LAND_MEGA(1.0F, 1.25),
    ;

    private final float value;
    private final double multiplier;

    HeightMap(final float value, final double multiplier) {
        this.value = value;
        this.multiplier = multiplier;
    }

    public static double getMultiplierForHeight(final float height) {
        double result = 1;
        float previous = 0F;

        for (final HeightMap map : values()) {
            final float value = map.value;
            if (height <= value && height > previous) {
                result = map.multiplier;
                break;
            }
        }

        return result;
    }

}
