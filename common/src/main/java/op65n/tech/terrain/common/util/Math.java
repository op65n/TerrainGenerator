package op65n.tech.terrain.common.util;

public final class Math {

    private static double inverseLerp(final double a, final double b, final double value) {
        if (a != b) return clamp((value - a) / (b - a));
        else return 0.0F;
    }

    public static double clamp(final double value) {
        if (value < 0F) return 0F;
        else if (value > 1F) return 1F;
        return value;
    }

    private static double map(final double value, final double inMinimum, final double inMaximum, final double outMinimum, final double outMaximum) {
        return (value - inMinimum) * (outMaximum - outMinimum + 1) / (inMaximum - inMinimum + 1) + outMinimum;
    }

    public static double inverseLerpMap(final double a, final double b, final double value, final double inMinimum, final double inMaximum, final double outMinimum, final double outMaximum) {
        return map(inverseLerp(a, b, value), inMinimum, inMaximum, outMinimum, outMaximum);
    }

    public static double lerp(final double a, final double b, final double x) {
        return a + x * (b - a);
    }

    public static double fade(final double value) {
        return value * value * value * (value * (value * 6 - 15) + 10);
    }

    public static int floor(final double value) {
        return value >= 0 ? (int) value : (int) value - 1;
    }

    public static double grad(int hash, final double x, final double y, final double z) {
        final int h = hash & 15;
        final double u = h < 8 ? x : y;
        final double v = h < 4 ? y : h == 12 || h == 14 ? x : z;

        return ((h & 1) == 0 ? u : -u) + ((h & 2) == 0 ? v : -v);
    }

}
