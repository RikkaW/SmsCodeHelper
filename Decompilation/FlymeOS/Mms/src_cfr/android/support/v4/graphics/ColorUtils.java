/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.graphics.Color
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.graphics;

import android.graphics.Color;

public class ColorUtils {
    private static final int MIN_ALPHA_SEARCH_MAX_ITERATIONS = 10;
    private static final int MIN_ALPHA_SEARCH_PRECISION = 10;

    private ColorUtils() {
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static int HSLToColor(float[] arrf) {
        int n2;
        int n3;
        int n4;
        float f2 = arrf[0];
        float f3 = arrf[1];
        float f4 = arrf[2];
        f3 = (1.0f - Math.abs((float)(2.0f * f4 - 1.0f))) * f3;
        f4 -= 0.5f * f3;
        float f5 = f3 * (1.0f - Math.abs((float)(f2 / 60.0f % 2.0f - 1.0f)));
        switch ((int)f2 / 60) {
            default: {
                n3 = 0;
                n2 = 0;
                n4 = 0;
                do {
                    return Color.rgb((int)ColorUtils.constrain(n4, 0, 255), (int)ColorUtils.constrain(n2, 0, 255), (int)ColorUtils.constrain(n3, 0, 255));
                    break;
                } while (true);
            }
            case 0: {
                n4 = Math.round((float)((f3 + f4) * 255.0f));
                n2 = Math.round((float)((f5 + f4) * 255.0f));
                n3 = Math.round((float)(255.0f * f4));
                return Color.rgb((int)ColorUtils.constrain(n4, 0, 255), (int)ColorUtils.constrain(n2, 0, 255), (int)ColorUtils.constrain(n3, 0, 255));
            }
            case 1: {
                n4 = Math.round((float)((f5 + f4) * 255.0f));
                n2 = Math.round((float)((f3 + f4) * 255.0f));
                n3 = Math.round((float)(255.0f * f4));
                return Color.rgb((int)ColorUtils.constrain(n4, 0, 255), (int)ColorUtils.constrain(n2, 0, 255), (int)ColorUtils.constrain(n3, 0, 255));
            }
            case 2: {
                n4 = Math.round((float)(255.0f * f4));
                n2 = Math.round((float)((f3 + f4) * 255.0f));
                n3 = Math.round((float)((f5 + f4) * 255.0f));
                return Color.rgb((int)ColorUtils.constrain(n4, 0, 255), (int)ColorUtils.constrain(n2, 0, 255), (int)ColorUtils.constrain(n3, 0, 255));
            }
            case 3: {
                n4 = Math.round((float)(255.0f * f4));
                n2 = Math.round((float)((f5 + f4) * 255.0f));
                n3 = Math.round((float)((f3 + f4) * 255.0f));
                return Color.rgb((int)ColorUtils.constrain(n4, 0, 255), (int)ColorUtils.constrain(n2, 0, 255), (int)ColorUtils.constrain(n3, 0, 255));
            }
            case 4: {
                n4 = Math.round((float)((f5 + f4) * 255.0f));
                n2 = Math.round((float)(255.0f * f4));
                n3 = Math.round((float)((f3 + f4) * 255.0f));
                return Color.rgb((int)ColorUtils.constrain(n4, 0, 255), (int)ColorUtils.constrain(n2, 0, 255), (int)ColorUtils.constrain(n3, 0, 255));
            }
            case 5: 
            case 6: 
        }
        n4 = Math.round((float)((f3 + f4) * 255.0f));
        n2 = Math.round((float)(255.0f * f4));
        n3 = Math.round((float)((f5 + f4) * 255.0f));
        return Color.rgb((int)ColorUtils.constrain(n4, 0, 255), (int)ColorUtils.constrain(n2, 0, 255), (int)ColorUtils.constrain(n3, 0, 255));
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void RGBToHSL(int n2, int n3, int n4, float[] arrf) {
        float f2 = (float)n2 / 255.0f;
        float f3 = (float)n3 / 255.0f;
        float f4 = (float)n4 / 255.0f;
        float f5 = Math.max((float)f2, (float)Math.max((float)f3, (float)f4));
        float f6 = Math.min((float)f2, (float)Math.min((float)f3, (float)f4));
        float f7 = f5 - f6;
        float f8 = (f5 + f6) / 2.0f;
        if (f5 == f6) {
            f2 = 0.0f;
            f7 = 0.0f;
        } else {
            f2 = f5 == f2 ? (f3 - f4) / f7 % 6.0f : (f5 == f3 ? (f4 - f2) / f7 + 2.0f : (f2 - f3) / f7 + 4.0f);
            f3 = f7 / (1.0f - Math.abs((float)(2.0f * f8 - 1.0f)));
            f7 = f2;
            f2 = f3;
        }
        f7 = f3 = f7 * 60.0f % 360.0f;
        if (f3 < 0.0f) {
            f7 = f3 + 360.0f;
        }
        arrf[0] = ColorUtils.constrain(f7, 0.0f, 360.0f);
        arrf[1] = ColorUtils.constrain(f2, 0.0f, 1.0f);
        arrf[2] = ColorUtils.constrain(f8, 0.0f, 1.0f);
    }

    public static double calculateContrast(int n2, int n3) {
        if (Color.alpha((int)n3) != 255) {
            throw new IllegalArgumentException("background can not be translucent");
        }
        int n4 = n2;
        if (Color.alpha((int)n2) < 255) {
            n4 = ColorUtils.compositeColors(n2, n3);
        }
        double d2 = ColorUtils.calculateLuminance(n4) + 0.05;
        double d3 = ColorUtils.calculateLuminance(n3) + 0.05;
        return Math.max((double)d2, (double)d3) / Math.min((double)d2, (double)d3);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static double calculateLuminance(int n2) {
        double d2 = (double)Color.red((int)n2) / 255.0;
        d2 = d2 < 0.03928 ? (d2 /= 12.92) : Math.pow((double)((d2 + 0.055) / 1.055), (double)2.4);
        double d3 = (double)Color.green((int)n2) / 255.0;
        d3 = d3 < 0.03928 ? (d3 /= 12.92) : Math.pow((double)((d3 + 0.055) / 1.055), (double)2.4);
        double d4 = (double)Color.blue((int)n2) / 255.0;
        if (d4 < 0.03928) {
            d4 /= 12.92;
            return d2 * 0.2126 + d3 * 0.7152 + 0.0722 * d4;
        }
        d4 = Math.pow((double)((d4 + 0.055) / 1.055), (double)2.4);
        return d2 * 0.2126 + d3 * 0.7152 + 0.0722 * d4;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static int calculateMinimumAlpha(int n2, int n3, float f2) {
        int n4 = 0;
        int n5 = 255;
        if (Color.alpha((int)n3) != 255) {
            throw new IllegalArgumentException("background can not be translucent");
        }
        if (ColorUtils.calculateContrast(ColorUtils.setAlphaComponent(n2, 255), n3) < (double)f2) {
            return -1;
        }
        int n6 = 0;
        do {
            int n7 = n5;
            if (n6 > 10) return n7;
            n7 = n5;
            if (n5 - n4 <= 10) return n7;
            n7 = (n4 + n5) / 2;
            if (ColorUtils.calculateContrast(ColorUtils.setAlphaComponent(n2, n7), n3) < (double)f2) {
                n4 = n7;
            } else {
                n5 = n7;
            }
            ++n6;
        } while (true);
    }

    public static void colorToHSL(int n2, float[] arrf) {
        ColorUtils.RGBToHSL(Color.red((int)n2), Color.green((int)n2), Color.blue((int)n2), arrf);
    }

    private static int compositeAlpha(int n2, int n3) {
        return 255 - (255 - n3) * (255 - n2) / 255;
    }

    public static int compositeColors(int n2, int n3) {
        int n4 = Color.alpha((int)n3);
        int n5 = Color.alpha((int)n2);
        int n6 = ColorUtils.compositeAlpha(n5, n4);
        return Color.argb((int)n6, (int)ColorUtils.compositeComponent(Color.red((int)n2), n5, Color.red((int)n3), n4, n6), (int)ColorUtils.compositeComponent(Color.green((int)n2), n5, Color.green((int)n3), n4, n6), (int)ColorUtils.compositeComponent(Color.blue((int)n2), n5, Color.blue((int)n3), n4, n6));
    }

    private static int compositeComponent(int n2, int n3, int n4, int n5, int n6) {
        if (n6 == 0) {
            return 0;
        }
        return (n2 * 255 * n3 + n4 * n5 * (255 - n3)) / (n6 * 255);
    }

    private static float constrain(float f2, float f3, float f4) {
        if (f2 < f3) {
            return f3;
        }
        if (f2 > f4) {
            return f4;
        }
        return f2;
    }

    private static int constrain(int n2, int n3, int n4) {
        if (n2 < n3) {
            return n3;
        }
        if (n2 > n4) {
            return n4;
        }
        return n2;
    }

    public static int setAlphaComponent(int n2, int n3) {
        if (n3 < 0 || n3 > 255) {
            throw new IllegalArgumentException("alpha must be between 0 and 255.");
        }
        return 16777215 & n2 | n3 << 24;
    }
}

