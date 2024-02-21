package site.dodoneko.peoplemobsmod2.base;

public class PMM2_Math {
    public static final float Deg2Rad = 0.0174532F;

    public static float toRad(float v) {
        return v * Deg2Rad;
    }

    public static float cos(float v) {
        return (float)Math.cos(v);
    }
    public static float sin(float v) {
        return (float)Math.sin(v);
    }
    public static float cosDeg(float v) {
        return (float)Math.cos(v * Deg2Rad);
    }
    public static float sinDeg(float v) {
        return (float)Math.sin(v * Deg2Rad);
    }

    public static float abs(float v) {
        return (float)Math.abs(v);
    }
}
