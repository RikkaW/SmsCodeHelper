/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.graphics.Path
 *  android.graphics.PathMeasure
 *  android.view.animation.Interpolator
 *  java.lang.Object
 */
package android.support.v4.view.animation;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.view.animation.Interpolator;

class PathInterpolatorDonut
implements Interpolator {
    private static final float PRECISION = 0.002f;
    private final float[] mX;
    private final float[] mY;

    public PathInterpolatorDonut(float f2, float f3) {
        this(PathInterpolatorDonut.createQuad(f2, f3));
    }

    public PathInterpolatorDonut(float f2, float f3, float f4, float f5) {
        this(PathInterpolatorDonut.createCubic(f2, f3, f4, f5));
    }

    public PathInterpolatorDonut(Path path) {
        path = new PathMeasure(path, false);
        float f2 = path.getLength();
        int n2 = (int)(f2 / 0.002f) + 1;
        this.mX = new float[n2];
        this.mY = new float[n2];
        float[] arrf = new float[2];
        for (int i2 = 0; i2 < n2; ++i2) {
            path.getPosTan((float)i2 * f2 / (float)(n2 - 1), arrf, null);
            this.mX[i2] = arrf[0];
            this.mY[i2] = arrf[1];
        }
    }

    private static Path createCubic(float f2, float f3, float f4, float f5) {
        Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        path.cubicTo(f2, f3, f4, f5, 1.0f, 1.0f);
        return path;
    }

    private static Path createQuad(float f2, float f3) {
        Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        path.quadTo(f2, f3, 1.0f, 1.0f);
        return path;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public float getInterpolation(float f2) {
        float f3 = 1.0f;
        if (f2 <= 0.0f) {
            return 0.0f;
        }
        if (f2 >= 1.0f) return f3;
        int n2 = 0;
        int n3 = this.mX.length - 1;
        while (n3 - n2 > 1) {
            int n4 = (n2 + n3) / 2;
            if (f2 < this.mX[n4]) {
                n3 = n4;
                continue;
            }
            n2 = n4;
        }
        f3 = this.mX[n3] - this.mX[n2];
        if (f3 == 0.0f) {
            return this.mY[n2];
        }
        f2 = (f2 - this.mX[n2]) / f3;
        f3 = this.mY[n2];
        return f2 * (this.mY[n3] - f3) + f3;
    }
}

