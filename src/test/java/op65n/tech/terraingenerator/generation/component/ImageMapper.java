package op65n.tech.terraingenerator.generation.component;

import java.awt.*;

public enum ImageMapper {

    DEFAULT(1.5F, 1.5F, new Color(118, 124, 140)),
    WATER_DEEP(0, 0.3F, new Color(0, 0, 247)),
    WATER_SHALLOW(0.3F, 0.4F, new Color(38, 99, 252)),
    SAND(0.4F, 0.45F, new Color(240, 224, 105)),
    GRASS_1(0.45F, 0.55F, new Color(56, 138, 37)),
    GRASS_2(0.55F, 0.6F, new Color(26, 61, 18)),
    ROCK_1(0.6F, 0.7F, new Color(48, 41, 28)),
    ROCK_2(0.7F, 0.8F, new Color(33, 29, 20)),
    SNOW(0.8F, 1, Color.WHITE);

    /*
    DEEP_6(-25, -100, new Color(0, 0, 247)),
    DEEP_5(-20, -25, new Color(20, 67, 252)),
    DEEP_4(-15, -20, new Color(38, 81, 252)),
    DEEP_3(-10, -15, new Color(38, 99, 252)),
    DEEP_2(-5, -10, new Color(74, 125, 255)),
    DEEP_1(0, -5, new Color(102, 145, 255)),
    DEFAULT(0, 0, new Color(118, 124, 140)),
    HIGH_1(0, 5, new Color(122, 145, 116)),
    HIGH_2(5, 10, new Color(112, 153, 103)),
    HIGH_3(10, 15, new Color(88, 148, 74)),
    HIGH_4(15, 20, new Color(56, 138, 37)),
    HIGH_5(20, 25, new Color(41, 99, 28)),
    HIGH_6(25, 100, new Color(26, 61, 18)),
    ;
     */

    private final float from;
    private final float to;
    private final Color color;

    ImageMapper(final float from, final float to, final Color color) {
        this.from = from;
        this.to = to;
        this.color = color;
    }

    public int getR() {
        return this.color.getRed();
    }

    public int getB() {
        return this.color.getBlue();
    }

    public int getG() {
        return this.color.getGreen();
    }

    public static ImageMapper getValue(final float value) {
        ImageMapper mapper = null;

        for (final ImageMapper imageMapper : values()) {
            final float from = imageMapper.from;
            final float to = imageMapper.to;

            if (value >= 0 && from < value && value <= to) {
                mapper = imageMapper;
                break;
            }

            if (to < value && value <= from) {
                mapper = imageMapper;
                break;
            }
        }

        return mapper == null ? DEFAULT : mapper;
    }

}
