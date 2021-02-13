package op65n.tech.terrainrenderer.util;

public final class Math {

    public static float inverseLerp(final float a, final float b, final float value) {
        if (a != b)
            return clamp((value - a) / (b - a));
        else
            return 0.0F;
    }

    public static float lerp(final float a, final float b, final float value) {
        return  a + (b - a) * clamp(value);
    }

    public static float clamp(final float value) {
        if (value < 0F)
            return 0F;
        else if (value > 1F)
            return 1F;
        return value;
    }

}
