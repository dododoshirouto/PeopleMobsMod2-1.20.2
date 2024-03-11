package site.dodoneko.peoplemobsmod2.base;

import net.minecraft.util.Mth;

public class PMath {
    public static final float Deg2Rad = 0.0174532F;
    public static final float PI = 3.141592653F;
    public static final float PI2 = PI * 2;

    public static float toRad(float deg) {
        return deg * Deg2Rad;
    }

    public static float sin(float v) {
        return (float) Mth.sin(v);
    }

    public static float sin1(float v) {
        return sin(v * PI2);
    }

    public static float sinD(float v) {
        return sin(toRad(v));
    }

    public static float cos(float v) {
        return (float) Mth.cos(v);
    }

    public static float cos1(float v) {
        return cos(v * PI2);
    }

    public static float cosD(float v) {
        return cos(toRad(v));
    }

    public static float asin(float v) {
        return (float) Math.asin(v);
    }

    public static float abs(float v) {
        return (float) Mth.abs(v);
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

    public static float sqrt(float v) {
        return (float) Mth.sqrt(v);
    }

    public static float pow(float v, float p) {
        return (float) Math.pow(v, p);
    }

    public static float pow2(float v) {
        return pow(v, 2);
    }

    public static float easeOut(float time) {
        return sqrt(abs(sin(time * PI / 2))) * sign(time);
    }

    private static float sign(float time) {
        return time < 0 ? -1 : 1;
    }

    public static float easeOut(float time, float start, float end) {
        return start + (end - start) * easeOut(time) * sign(time);
    }

    public static float easeIn(float time) {
        return 1 - sqrt(abs(sin((1 - time) * PI / 2)));
    }

    public static float easeIn(float time, float start, float end) {
        return start + (end - start) * easeIn(time);
    }

    public static float easeInOut(float time) {
        return pow2(sin(time * PI / 2));
    }

    public static float easeInOut(float time, float start, float end) {
        return start + (end - start) * easeInOut(time);
    }

    public static float lerp(float a, float b, float t) {
        return a + (b - a) * t;
    }

    public static float floor(float v) {
        return (float) Mth.floor(v);
    }

    public static int floori(float v) {
        return (int) floor(v);
    }

    public static float log(float v) {
        if (v == 0f) {
            return 0;
        } else if(v < 0) {
            return -log(-v);
        }
        return (float) Math.log(v);
    }
}
