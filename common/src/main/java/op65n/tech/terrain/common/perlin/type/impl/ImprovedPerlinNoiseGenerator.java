package op65n.tech.terrain.common.perlin.type.impl;

import op65n.tech.terrain.common.perlin.type.NoiseGenerator;
import op65n.tech.terrain.common.util.Math;

import java.util.Random;

@SuppressWarnings("unused")
public final class ImprovedPerlinNoiseGenerator extends NoiseGenerator {

    private static final ImprovedPerlinNoiseGenerator instance = new ImprovedPerlinNoiseGenerator();
    private int repeat;

    protected ImprovedPerlinNoiseGenerator() {
        final int[] p = {151, 160, 137, 91, 90, 15, 131, 13, 201,
                95, 96, 53, 194, 233, 7, 225, 140, 36, 103, 30, 69, 142, 8, 99, 37,
                240, 21, 10, 23, 190, 6, 148, 247, 120, 234, 75, 0, 26, 197, 62,
                94, 252, 219, 203, 117, 35, 11, 32, 57, 177, 33, 88, 237, 149, 56,
                87, 174, 20, 125, 136, 171, 168, 68, 175, 74, 165, 71, 134, 139,
                48, 27, 166, 77, 146, 158, 231, 83, 111, 229, 122, 60, 211, 133,
                230, 220, 105, 92, 41, 55, 46, 245, 40, 244, 102, 143, 54, 65, 25,
                63, 161, 1, 216, 80, 73, 209, 76, 132, 187, 208, 89, 18, 169, 200,
                196, 135, 130, 116, 188, 159, 86, 164, 100, 109, 198, 173, 186, 3,
                64, 52, 217, 226, 250, 124, 123, 5, 202, 38, 147, 118, 126, 255,
                82, 85, 212, 207, 206, 59, 227, 47, 16, 58, 17, 182, 189, 28, 42,
                223, 183, 170, 213, 119, 248, 152, 2, 44, 154, 163, 70, 221, 153,
                101, 155, 167, 43, 172, 9, 129, 22, 39, 253, 19, 98, 108, 110, 79,
                113, 224, 232, 178, 185, 112, 104, 218, 246, 97, 228, 251, 34, 242,
                193, 238, 210, 144, 12, 191, 179, 162, 241, 81, 51, 145, 235, 249,
                14, 239, 107, 49, 192, 214, 31, 181, 199, 106, 157, 184, 84, 204,
                176, 115, 121, 50, 45, 127, 4, 150, 254, 138, 236, 205, 93, 222,
                114, 67, 29, 24, 72, 243, 141, 128, 195, 78, 66, 215, 61, 156, 180};

        for (int index = 0; index < 512; index++) {
            perm[index] = p[index & 255];
        }
    }

    public ImprovedPerlinNoiseGenerator(final int repeat) {
        this.repeat = repeat;
    }

    /**
     * Creates a seeded perlin noise generator for the given seed
     *
     * @param seed Seed to construct this generator for
     */
    public ImprovedPerlinNoiseGenerator(final long seed) {
        this(new Random(seed));
    }

    /**
     * Creates a seeded perlin noise generator with the given Random
     *
     * @param rand Random to construct with
     */
    public ImprovedPerlinNoiseGenerator(final Random rand) {
        offsetX = rand.nextDouble() * 256;
        offsetY = rand.nextDouble() * 256;
        offsetZ = rand.nextDouble() * 256;

        for (int i = 0; i < 256; i++) {
            perm[i] = rand.nextInt(256);
        }

        for (int i = 0; i < 256; i++) {
            final int pos = rand.nextInt(256 - i) + i;
            final int old = perm[i];

            perm[i] = perm[pos];
            perm[pos] = old;
            perm[i + 256] = perm[i];
        }
    }

    /**
     * Computes and returns the 1D unseeded perlin noise for the given
     * coordinates in 1D space
     *
     * @param x X coordinate
     * @return Noise at given location, from range -1 to 1
     */
    public static double getNoise(final double x) {
        return instance.noise(x);
    }

    /**
     * Computes and returns the 2D unseeded perlin noise for the given
     * coordinates in 2D space
     *
     * @param x X coordinate
     * @param y Y coordinate
     * @return Noise at given location, from range -1 to 1
     */
    public static double getNoise(final double x, final double y) {
        return instance.noise(x, y);
    }

    /**
     * Computes and returns the 3D unseeded perlin noise for the given
     * coordinates in 3D space
     *
     * @param x X coordinate
     * @param y Y coordinate
     * @param z Z coordinate
     * @return Noise at given location, from range -1 to 1
     */
    public static double getNoise(final double x, final double y, final double z) {
        return instance.noise(x, y, z);
    }

    /**
     * Gets the singleton unseeded instance of this generator
     *
     * @return Singleton
     */
    public static ImprovedPerlinNoiseGenerator getInstance() {
        return instance;
    }

    @Override
    public double noise(double x, double y, double z) {
        if (repeat > 0) {
            x = x % repeat;
            y = y % repeat;
            z = z % repeat;
        }

        final int xi = (int) x & 255;
        final int yi = (int) y & 255;
        final int zi = (int) z & 255;

        final double xf = x - (int) x;
        final double yf = y - (int) y;
        final double zf = z - (int) z;

        final double u = Math.fade(xf);
        final double v = Math.fade(yf);
        final double w = Math.fade(zf);

        final int aaa, aba, aab, abb, baa, bba, bab, bbb;
        aaa = perm[perm[perm[xi] + yi] + zi];
        aba = perm[perm[perm[xi] + inc(yi)] + zi];
        aab = perm[perm[perm[xi] + yi] + inc(zi)];
        abb = perm[perm[perm[xi] + inc(yi)] + inc(zi)];
        baa = perm[perm[perm[inc(xi)] + yi] + zi];
        bba = perm[perm[perm[inc(xi)] + inc(yi)] + zi];
        bab = perm[perm[perm[inc(xi)] + yi] + inc(zi)];
        bbb = perm[perm[perm[inc(xi)] + inc(yi)] + inc(zi)];

        double x1, x2, y1, y2;
        x1 = Math.lerp(Math.grad(aaa, xf, yf, zf), Math.grad(baa, xf - 1, yf, zf), u);
        x2 = Math.lerp(Math.grad(aba, xf, yf - 1, zf), Math.grad(bba, xf - 1, yf - 1, zf), u);

        y1 = Math.lerp(x1, x2, v);
        x1 = Math.lerp(Math.grad(aab, xf, yf, zf - 1), Math.grad(bab, xf - 1, yf, zf - 1), u);
        x2 = Math.lerp(Math.grad(abb, xf, yf - 1, zf - 1), Math.grad(bbb, xf - 1, yf - 1, zf - 1), u);
        y2 = Math.lerp(x1, x2, v);

        return (Math.lerp(y1, y2, w) + 1);
    }

    /**
     * Generates noise for the 1D coordinates using the specified number of
     * octaves and parameters
     *
     * @param x         X-coordinate
     * @param octaves   Number of octaves to use
     * @param frequency How much to alter the frequency by each octave
     * @param amplitude How much to alter the amplitude by each octave
     * @return Resulting noise
     */
    public static double getNoise(final double x, final int octaves, final double frequency, final double amplitude) {
        return instance.noise(x, octaves, frequency, amplitude);
    }

    /**
     * Generates noise for the 2D coordinates using the specified number of
     * octaves and parameters
     *
     * @param x         X-coordinate
     * @param y         Y-coordinate
     * @param octaves   Number of octaves to use
     * @param frequency How much to alter the frequency by each octave
     * @param amplitude How much to alter the amplitude by each octave
     * @return Resulting noise
     */
    public static double getNoise(final double x, final double y, final int octaves, final double frequency, final double amplitude) {
        return instance.noise(x, y, octaves, frequency, amplitude);
    }

    /**
     * Generates noise for the 3D coordinates using the specified number of
     * octaves and parameters
     *
     * @param x         X-coordinate
     * @param y         Y-coordinate
     * @param z         Z-coordinate
     * @param octaves   Number of octaves to use
     * @param frequency How much to alter the frequency by each octave
     * @param amplitude How much to alter the amplitude by each octave
     * @return Resulting noise
     */
    public static double getNoise(final double x, final double y, final double z, final int octaves, final double frequency, final double amplitude) {
        return instance.noise(x, y, z, octaves, frequency, amplitude);
    }

    private int inc(int num) {
        num++;
        if (repeat > 0) num %= repeat;

        return num;
    }

}
