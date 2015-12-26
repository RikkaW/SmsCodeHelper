/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.view.VelocityTracker
 *  java.lang.Object
 */
package android.support.v4.view;

import android.os.Build;
import android.support.v4.view.VelocityTrackerCompatHoneycomb;
import android.view.VelocityTracker;

public class VelocityTrackerCompat {
    static final VelocityTrackerVersionImpl IMPL = Build.VERSION.SDK_INT >= 11 ? new HoneycombVelocityTrackerVersionImpl() : new BaseVelocityTrackerVersionImpl();

    public static float getXVelocity(VelocityTracker velocityTracker, int n2) {
        return IMPL.getXVelocity(velocityTracker, n2);
    }

    public static float getYVelocity(VelocityTracker velocityTracker, int n2) {
        return IMPL.getYVelocity(velocityTracker, n2);
    }

    static class BaseVelocityTrackerVersionImpl
    implements VelocityTrackerVersionImpl {
        BaseVelocityTrackerVersionImpl() {
        }

        @Override
        public float getXVelocity(VelocityTracker velocityTracker, int n2) {
            return velocityTracker.getXVelocity();
        }

        @Override
        public float getYVelocity(VelocityTracker velocityTracker, int n2) {
            return velocityTracker.getYVelocity();
        }
    }

    static class HoneycombVelocityTrackerVersionImpl
    implements VelocityTrackerVersionImpl {
        HoneycombVelocityTrackerVersionImpl() {
        }

        @Override
        public float getXVelocity(VelocityTracker velocityTracker, int n2) {
            return VelocityTrackerCompatHoneycomb.getXVelocity(velocityTracker, n2);
        }

        @Override
        public float getYVelocity(VelocityTracker velocityTracker, int n2) {
            return VelocityTrackerCompatHoneycomb.getYVelocity(velocityTracker, n2);
        }
    }

    static interface VelocityTrackerVersionImpl {
        public float getXVelocity(VelocityTracker var1, int var2);

        public float getYVelocity(VelocityTracker var1, int var2);
    }

}

