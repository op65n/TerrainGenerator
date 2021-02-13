package op65n.tech.terrainrendering.renderer.map;

import java.awt.*;

public enum ImageMapper {

    DEEP_3(0.15F, new Color(0, 38, 99)),
    DEEP_2(0.2F, new Color(0, 53, 138)),
    DEEP_1(0.3F, new Color(0, 64, 166)),
    SHALLOW_2(0.35F, new Color(0, 71, 184)),
    SHALLOW_1(0.4F, new Color(0, 92, 237)),
    SAND_LOW(0.42F, new Color(252, 234, 131)),
    SAND_HIGH(0.45F, new Color(184, 170, 95)),
    GRASS_LOW(0.47F, new Color(56, 145, 41)),
    GRASS_MEDIUM(0.5F, new Color(40, 120, 26)),
    GRASS_HIGH(0.53F, new Color(28, 94, 16)),
    ROCK_LOW_2(0.55F, new Color(107, 91, 73)),
    ROCK_LOW_1(0.58F, new Color(84, 71, 57)),
    ROCK_MEDIUM_2(0.63F, new Color(66, 57, 47)),
    ROCK_MEDIUM_1(0.68F, new Color(54, 46, 39)),
    ROCK_HIGH_2(0.73F, new Color(46, 41, 37)),
    ROCK_HIGH_1(0.78F, new Color(38, 35, 33)),
    ROCK_MEGA_2(0.83F, new Color(33, 31, 30)),
    ROCK_MEGA_1(0.86F, new Color(26, 24, 24)),
    SNOW_2(0.92F, new Color(138, 138, 138)),
    SNOW_1(1.0F, new Color(199, 199, 199));

    private final float value;
    private final Color color;

    ImageMapper(final float value, final Color color) {
        this.value = value;
        this.color = color;
    }

    public static Color getColorForHeight(final float height) {
        Color result = null;
        float previous = 0F;

        for (final ImageMapper mapper : values()) {
            final float value = mapper.value;
            if (height <= value && height > previous) {
                result = mapper.color;
                break;
            }

            previous = value;
        }

        return result == null ? new Color(255, 0, 0) : result;
    }

}
