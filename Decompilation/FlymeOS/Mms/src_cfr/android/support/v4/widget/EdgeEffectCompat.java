/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.Canvas
 *  android.os.Build
 *  android.os.Build$VERSION
 *  java.lang.Object
 */
package android.support.v4.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.support.v4.widget.EdgeEffectCompatIcs;
import android.support.v4.widget.EdgeEffectCompatLollipop;

public class EdgeEffectCompat {
    private static final EdgeEffectImpl IMPL = Build.VERSION.SDK_INT >= 21 ? new EdgeEffectLollipopImpl() : (Build.VERSION.SDK_INT >= 14 ? new EdgeEffectIcsImpl() : new BaseEdgeEffectImpl());
    private Object mEdgeEffect;

    public EdgeEffectCompat(Context context) {
        this.mEdgeEffect = IMPL.newEdgeEffect(context);
    }

    public boolean draw(Canvas canvas) {
        return IMPL.draw(this.mEdgeEffect, canvas);
    }

    public void finish() {
        IMPL.finish(this.mEdgeEffect);
    }

    public boolean isFinished() {
        return IMPL.isFinished(this.mEdgeEffect);
    }

    public boolean onAbsorb(int n2) {
        return IMPL.onAbsorb(this.mEdgeEffect, n2);
    }

    public boolean onPull(float f2) {
        return IMPL.onPull(this.mEdgeEffect, f2);
    }

    public boolean onPull(float f2, float f3) {
        return IMPL.onPull(this.mEdgeEffect, f2, f3);
    }

    public boolean onRelease() {
        return IMPL.onRelease(this.mEdgeEffect);
    }

    public void setSize(int n2, int n3) {
        IMPL.setSize(this.mEdgeEffect, n2, n3);
    }

    static class BaseEdgeEffectImpl
    implements EdgeEffectImpl {
        BaseEdgeEffectImpl() {
        }

        @Override
        public boolean draw(Object object, Canvas canvas) {
            return false;
        }

        @Override
        public void finish(Object object) {
        }

        @Override
        public boolean isFinished(Object object) {
            return true;
        }

        @Override
        public Object newEdgeEffect(Context context) {
            return null;
        }

        @Override
        public boolean onAbsorb(Object object, int n2) {
            return false;
        }

        @Override
        public boolean onPull(Object object, float f2) {
            return false;
        }

        @Override
        public boolean onPull(Object object, float f2, float f3) {
            return false;
        }

        @Override
        public boolean onRelease(Object object) {
            return false;
        }

        @Override
        public void setSize(Object object, int n2, int n3) {
        }
    }

    static class EdgeEffectIcsImpl
    implements EdgeEffectImpl {
        EdgeEffectIcsImpl() {
        }

        @Override
        public boolean draw(Object object, Canvas canvas) {
            return EdgeEffectCompatIcs.draw(object, canvas);
        }

        @Override
        public void finish(Object object) {
            EdgeEffectCompatIcs.finish(object);
        }

        @Override
        public boolean isFinished(Object object) {
            return EdgeEffectCompatIcs.isFinished(object);
        }

        @Override
        public Object newEdgeEffect(Context context) {
            return EdgeEffectCompatIcs.newEdgeEffect(context);
        }

        @Override
        public boolean onAbsorb(Object object, int n2) {
            return EdgeEffectCompatIcs.onAbsorb(object, n2);
        }

        @Override
        public boolean onPull(Object object, float f2) {
            return EdgeEffectCompatIcs.onPull(object, f2);
        }

        @Override
        public boolean onPull(Object object, float f2, float f3) {
            return EdgeEffectCompatIcs.onPull(object, f2);
        }

        @Override
        public boolean onRelease(Object object) {
            return EdgeEffectCompatIcs.onRelease(object);
        }

        @Override
        public void setSize(Object object, int n2, int n3) {
            EdgeEffectCompatIcs.setSize(object, n2, n3);
        }
    }

    static interface EdgeEffectImpl {
        public boolean draw(Object var1, Canvas var2);

        public void finish(Object var1);

        public boolean isFinished(Object var1);

        public Object newEdgeEffect(Context var1);

        public boolean onAbsorb(Object var1, int var2);

        public boolean onPull(Object var1, float var2);

        public boolean onPull(Object var1, float var2, float var3);

        public boolean onRelease(Object var1);

        public void setSize(Object var1, int var2, int var3);
    }

    static class EdgeEffectLollipopImpl
    extends EdgeEffectIcsImpl {
        EdgeEffectLollipopImpl() {
        }

        @Override
        public boolean onPull(Object object, float f2, float f3) {
            return EdgeEffectCompatLollipop.onPull(object, f2, f3);
        }
    }

}

