package betterterrain.world.generate.noise;

public class AINoiseTest {
/*
    private static final int B = 0x100;
    private static final int BM = 0xff;

    private static final int N = 0x1000;
    private static final int NP = 12;   // 2^N
    private static final int NM = 0xfff;

    private static int[] p = new int[B + B + 2];
    private static float[][] g3 = new float[B + B + 2][3];
    private static float[][] g2 = new float[B + B + 2][2];
    private static float[] g1 = new float[B + B + 2];
    private static boolean start = true;

    private static void init(long seed) {
        if (!start) {
            return;
        }

        start = false;
        Random random = new Random(seed);
        int i, j, k;
        for (i = 0; i < B; i++) {
            p[i] = i;
            g1[i] = (float) ((random.nextInt(B + B) - B) / B);

            for (j = 0; j < 2; j++) {
                g2[i][j] = (float) ((random.nextInt(B + B) - B) / B);
            }
            normalize2(g2[i]);

            for (j = 0; j < 3; j++) {
                g3[i][j] = (float) ((random.nextInt(B + B) - B) / B);
            }
            normalize3(g3[i]);
        }

        while (--i != 0) {
            k = p[i];
            p[i] = p[j = random.nextInt(B)];
            p[j] = k;
        }

        for (i = 0; i < B + 2; i++) {
            p[B + i] = p[i];
            g1[B + i] = g1[i];
            for (j = 0; j < 2; j++) {
                g2[B + i][j] = g2[i][j];
            }
            for (j = 0; j < 3; j++) {
                g3[B + i][j] = g3[i][j];
            }
        }
    }

    private static float s_curve(float t) {
        return t * t * (3.0f - 2.0f * t);
    }

    private static float lerp(float t, float a, float b) {
        return a + t * (b - a);
    }

    private static void setup(float value, int[] b0, int[] b1, float[] r0, float[] r1) {
        float t = value + N;
        b0[0] = ((int) t) & BM;
        b1[0] = (b0[0] + 1) & BM;
        r0[0] = t - (int) t;
        r1[0] = r0[0] - 1.0f;
    }

    private static float at1(float rx, float ry, float x, float y) {
        return rx * x + ry * y;
    }

    private static float at2(int b00, int b10, int b01, int b11, float rx0, float rx1, float ry0, float ry1, float x, float y) {
        float sx = s_curve(rx0);
        float sy = s_curve(ry0);
        float a = at1(rx0, ry0, g2[p[b00]][0], g2[p[b00]][1]);
        float b = at1(rx1, ry0, g2[p[b10]][0], g2[p[b10]][1]);
        float c = at1(rx0, ry1, g2[p[b01]][0], g2[p[b01]][1]);
        float d = at1(rx1, ry1, g2[p[b11]][0], g2[p[b11]][1]);
        return lerp(sy, lerp(sx, a, b), lerp(sx, c, d));
    }

    private static float at3(int b000, int b100, int b010, int b110, int b001, int b101, int b011, int b111, float rx0, float rx1, float ry0, float ry1, float rz0, float rz1) {
        float sx = s_curve(rx0);
        float sy = s_curve(ry0);
        float sz = s_curve(rz0);
        float a = at2(b000, b100, b010, b110, rx0, rx1, ry0, ry1, rz0, rz1, 0.0f, 0.0f, 0.0f);
        float b = at2(b001, b101, b011, b111, rx0, rx1, ry0, ry1, rz0, rz1, 1.0f, 0.0f, 0.0f);
        float c = at2(b000, b100, b010, b110, rx0, rx1, ry0, ry1, rz0, rz1, 0.0f, 1.0f, 0.0f);
        float d = at2(b001, b101, b011, b111, rx0, rx1, ry0, ry1, rz0, rz1, 1.0f, 1.0f, 0.0f);
        float e = lerp(sx, a, b);
        float f = lerp(sx, c, d);
        a = at2(b000, b100, b010, b110, rx0, rx1, ry0, ry1, rz0, rz1, 0.0f, 0.0f, 1.0f);
        b = at2(b001, b101, b011, b111, rx0, rx1, ry0, ry1, rz0, rz1, 1.0f, 0.0f, 1.0f);
        c = at2(b000, b100, b010, b110, rx0, rx1, ry0, ry1, rz0, rz1, 0.0f, 1.0f, 1.0f);
        d = at2(b001, b101, b011, b111, rx0, rx1, ry0, ry1, rz0, rz1, 1.0f, 1.0f, 1.0f);
        float g = lerp(sx, a, b);
        float h = lerp(sx, c, d);
        return lerp(sy, lerp(sz, e, g), lerp(sz, f, h));
    }

    public static void generate2D(float[] noiseArray, int xSize, int ySize, float scale, long seed, int startX, int startY) {
        init(seed);
        int i, j;
        for (i = 0; i < xSize; i++) {
            for (j = 0; j < ySize; j++) {
                int bx0, bx1, by0, by1, b00, b10, b01, b11;
                float rx0, rx1, ry0, ry1, sx, sy, a, b, t, u, v;
                t = (startX + i) * scale;
                bx0 = ((int) t) & BM;
                bx1 = (bx0 + 1) & BM;
                rx0 = t - (int) t;
                rx1 = rx0 - 1.0f;

                t = (startY + j) * scale;
                by0 = ((int) t) & BM;
                by1 = (by0 + 1) & BM;
                ry0 = t - (int) t;
                ry1 = ry0 - 1.0f;

                b00 = p[bx0 + by0];
                b10 = p[bx1 + by0];
                b01 = p[bx0 + by1];
                b11 = p[bx1 + by1];

                sx = s_curve(rx0);
                sy = s_curve(ry0);

                a = at2(b00, b10, b01, b11, rx0, rx1, ry0, ry1, 0.0f, 0.0f);
                b = at2(b00, b10, b01, b11, rx0, rx1, ry0, ry1, 1.0f, 0.0f);
                u = lerp(sx, a, b);

                a = at2(b00, b10, b01, b11, rx0, rx1, ry0, ry1, 0.0f, 1.0f);
                b = at2(b00, b10, b01, b11, rx0, rx1, ry0, ry1, 1.0f, 1.0f);
                v = lerp(sx, a, b);

                noiseArray[i + j * xSize] = lerp(sy, u, v);
            }
        }
    }

    public static void generate3D(float[] noiseArray, int xSize, int ySize, int zSize, float scale, long seed, int startX, int startY, int startZ) {
        init(seed);
        int i, j, k;
        for (i = 0; i < xSize; i++) {
            for (j = 0; j < ySize; j++) {
                for (k = 0; k < zSize; k++) {
                    int bx0, bx1, by0, by1, bz0, bz1, b00, b10, b01, b11;
                    float rx0, rx1, ry0, ry1, rz0, rz1, sx, sy, sz, a, b, c, d, t, u, v, w;
                    t = (startX + i) * scale;
                    bx0 = ((int) t) & BM;
                    bx1 = (bx0 + 1) & BM;
                    rx0 = t - (int) t;
                    rx1 = rx0 - 1.0f;

                    t = (startY + j) * scale;
                    by0 = ((int) t) & BM;
                    by1 = (by0 + 1) & BM;
                    ry0 = t - (int) t;
                    ry1 = ry0 - 1.0f;

                    t = (startZ + k) * scale;
                    bz0 = ((int) t) & BM;
                    bz1 = (bz0 + 1) & BM;
                    rz0 = t - (int) t;
                    rz1 = rz0 - 1.0f;

                    b00 = p[bx0 + by0 + bz0];
                    b10 = p[bx1 + by0 + bz0];
                    b01 = p[bx0 + by1 + bz0];
                    b11 = p[bx1 + by1 + bz0];
                    sx = s_curve(rx0);
                    sy = s_curve(ry0);
                    sz = s_curve(rz0);

                    a = at3(b00, b10, b01, b11, rx0, rx1, ry0, ry1, rz0, rz1, 0.0f, 0.0f, 0.0f);
                    b = at3(b00, b10, b01, b11, rx0, rx1, ry0, ry1, rz0, rz1, 1.0f, 0.0f, 0.0f);
                    c = at3(b00, b10, b01, b11, rx0, rx1, ry0, ry1, rz0, rz1, 0.0f, 1.0f, 0.0f);
                    d = at3(b00, b10, b01, b11, rx0, rx1, ry0, ry1, rz0, rz1, 1.0f, 1.0f, 0.0f);
                    u = lerp(sx, a, b);
                    v = lerp(sx, c, d);
                    t = lerp(sy, u, v);

                    b00 = p[bx0 + by0 + bz1];
                    b10 = p[bx1 + by0 + bz1];
                    b01 = p[bx0 + by1 + bz1];
                    b11 = p[bx1 + by1 + bz1];
                    sx = s_curve(rx0);
                    sy = s_curve(ry0);
                    sz = s_curve(rz1);

                    a = at3(b00, b10, b01, b11, rx0, rx1, ry0, ry1, rz0, rz1, 0.0f, 0.0f, 1.0f);
                    b = at3(b00, b10, b01, b11, rx0, rx1, ry0, ry1, rz0, rz1, 1.0f, 0.0f, 1.0f);
                    c = at3(b00, b10, b01, b11, rx0, rx1, ry0, ry1, rz0, rz1, 0.0f, 1.0f, 1.0f);
                    d = at3(b00, b10, b01, b11, rx0, rx1, ry0, ry1, rz0, rz1, 1.0f, 1.0f, 1.0f);
                    u = lerp(sx, a, b);
                    v = lerp(sx, c, d);
                    w = lerp(sy, u, v);

                    noiseArray[i + j * xSize + k * xSize * ySize] = lerp(sz, t, w);
                }
            }
        }
    public static void generate3D(float[] noiseArray, int xSize, int ySize, int zSize, float scale, long seed, int startX, int startY, int startZ) {
        init(seed);
        int i, j, k;
        for (i = 0; i < xSize; i++) {
            for (j = 0; j < ySize; j++) {
                for (k = 0; k < zSize; k++) {
                    int bx0, bx1, by0, by1, bz0, bz1, b00, b10, b01, b11;
                    float rx0, rx1, ry0, ry1, rz0, rz1, sy, sz, a, b, c, d, t, u, v;
                    t = (startX + i) * scale;
                    bx0 = ((int) t) & BM;
                    bx1 = (bx0 + 1) & BM;
                    rx0 = t - (int) t;
                    rx1 = rx0 - 1.0f;

                    t = (startY + j) * scale;
                    by0 = ((int) t) & BM;
                    by1 = (by0 + 1) & BM;
                    ry0 = t - (int) t;
                    ry1 = ry0 - 1.0f;

                    t = (startZ + k) * scale;
                    bz0 = ((int) t) & BM;
                    bz1 = (bz0 + 1) & BM;
                    rz0 = t - (int) t;
                    rz1 = rz0 - 1.0f;

                    b00 = p[bx0 + by0 + bz0];
                    b10 = p[bx1 + by0 + bz0];
                    b01 = p[bx0 + by1 + bz0];
                    b11 = p[bx1 + by1 + bz0];
                    t = at3(b00, b10, rx0, rx1, 0.0f, 0.0f, rz0);
                    u = at3(b01, b11, rx0, rx1, 0.0f, 1.0f, rz0);
                    v = at3(b00, b10, rx0, rx1, 1.0f, 0.0f, rz0);
                    float w = at3(b01, b11, rx0, rx1, 1.0f, 1.0f, rz0);
                    a = lerp(sy, t, u);
                    b = lerp(sy, v, w);
                    c = lerp(sz, a, b);

                    b00 = p[bx0 + by0 + bz1];
                    b10 = p[bx1 + by0 + bz1];
                    b01 = p[bx0 + by1 + bz1];
                    b11 = p[bx1 + by1 + bz1];
                    t = at3(b00, b10, rx0, rx1, 0.0f, 0.0f, rz1);
                    u = at3(b01, b11, rx0, rx1, 0.0f, 1.0f, rz1);
                    v = at3(b00, b10, rx0, rx1, 1.0f, 0.0f, rz1);
                    w = at3(b01, b11, rx0, rx1, 1.0f, 1.0f, rz1);
                    a = lerp(sy, t, u);
                    b = lerp(sy, v, w);
                    d = lerp(sz, a, b);

                    noiseArray[i + j * xSize + k * xSize * ySize] = lerp(sz, c, d);
                }
            }
        }
    }
 */
}
