package site.dodoneko.peoplemobsmod2.base;

public class PMath {
    public static final float Deg2Rad = 0.0174532F;
    public static final float PI = 3.141592653F;
    public static final float PI2 = PI*2;

    public static float toRad(float deg) {
        return deg * Deg2Rad;
    }

    public static float sin(float v) {
        return (float)Math.sin(v);
    }
    public static float sinD(float v) {
        return (float)Math.sin(v * PI2);
    }
    public static float sin1(float v) {
        return (float)Math.sin(v * Deg2Rad);
    }
    public static float cos(float v) {
        return (float)Math.cos(v);
    }
    public static float cosD(float v) {
        return (float)Math.cos(v * Deg2Rad);
    }
    public static float cos1(float v) {
        return (float)Math.cos(v * PI2);
    }
    public static float asin(float v) {
        return (float)Math.asin(v);
    }

    public static float abs(float v) {
        return (float)Math.abs(v);
    }
    public static float max(float a, float b) {
        return Math.max(a, b);
    }
    public static float min(float a, float b) {
        return Math.min(a, b);
    }
    public static float clamp(float v, float a, float b) {
        float min = min(a, b);
        float max = max(a, b);
        return max(min, min(max, v));
    }
}
