/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.graphics.Bitmap
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.Bundle
 *  android.view.View
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.app;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat21;
import android.support.v4.app.ActivityOptionsCompatJB;
import android.support.v4.util.Pair;
import android.view.View;

public class ActivityOptionsCompat {
    protected ActivityOptionsCompat() {
    }

    public static ActivityOptionsCompat makeCustomAnimation(Context context, int n2, int n3) {
        if (Build.VERSION.SDK_INT >= 16) {
            return new ActivityOptionsImplJB(ActivityOptionsCompatJB.makeCustomAnimation(context, n2, n3));
        }
        return new ActivityOptionsCompat();
    }

    public static ActivityOptionsCompat makeScaleUpAnimation(View view, int n2, int n3, int n4, int n5) {
        if (Build.VERSION.SDK_INT >= 16) {
            return new ActivityOptionsImplJB(ActivityOptionsCompatJB.makeScaleUpAnimation(view, n2, n3, n4, n5));
        }
        return new ActivityOptionsCompat();
    }

    public static ActivityOptionsCompat makeSceneTransitionAnimation(Activity activity, View view, String string2) {
        if (Build.VERSION.SDK_INT >= 21) {
            return new ActivityOptionsImpl21(ActivityOptionsCompat21.makeSceneTransitionAnimation(activity, view, string2));
        }
        return new ActivityOptionsCompat();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static /* varargs */ ActivityOptionsCompat makeSceneTransitionAnimation(Activity activity, Pair<View, String> ... arrpair) {
        View[] arrview = null;
        if (Build.VERSION.SDK_INT < 21) return new ActivityOptionsCompat();
        if (arrpair != null) {
            arrview = new View[arrpair.length];
            String[] arrstring = new String[arrpair.length];
            for (int i2 = 0; i2 < arrpair.length; ++i2) {
                arrview[i2] = (View)arrpair[i2].first;
                arrstring[i2] = (String)arrpair[i2].second;
            }
            arrpair = arrstring;
            do {
                return new ActivityOptionsImpl21(ActivityOptionsCompat21.makeSceneTransitionAnimation(activity, arrview, arrpair));
                break;
            } while (true);
        }
        Object var4_4 = null;
        arrpair = arrview;
        arrview = var4_4;
        return new ActivityOptionsImpl21(ActivityOptionsCompat21.makeSceneTransitionAnimation(activity, arrview, arrpair));
    }

    public static ActivityOptionsCompat makeThumbnailScaleUpAnimation(View view, Bitmap bitmap, int n2, int n3) {
        if (Build.VERSION.SDK_INT >= 16) {
            return new ActivityOptionsImplJB(ActivityOptionsCompatJB.makeThumbnailScaleUpAnimation(view, bitmap, n2, n3));
        }
        return new ActivityOptionsCompat();
    }

    public Bundle toBundle() {
        return null;
    }

    public void update(ActivityOptionsCompat activityOptionsCompat) {
    }

    static class ActivityOptionsImpl21
    extends ActivityOptionsCompat {
        private final ActivityOptionsCompat21 mImpl;

        ActivityOptionsImpl21(ActivityOptionsCompat21 activityOptionsCompat21) {
            this.mImpl = activityOptionsCompat21;
        }

        @Override
        public Bundle toBundle() {
            return this.mImpl.toBundle();
        }

        @Override
        public void update(ActivityOptionsCompat activityOptionsCompat) {
            if (activityOptionsCompat instanceof ActivityOptionsImpl21) {
                activityOptionsCompat = (ActivityOptionsImpl21)activityOptionsCompat;
                this.mImpl.update(activityOptionsCompat.mImpl);
            }
        }
    }

    static class ActivityOptionsImplJB
    extends ActivityOptionsCompat {
        private final ActivityOptionsCompatJB mImpl;

        ActivityOptionsImplJB(ActivityOptionsCompatJB activityOptionsCompatJB) {
            this.mImpl = activityOptionsCompatJB;
        }

        @Override
        public Bundle toBundle() {
            return this.mImpl.toBundle();
        }

        @Override
        public void update(ActivityOptionsCompat activityOptionsCompat) {
            if (activityOptionsCompat instanceof ActivityOptionsImplJB) {
                activityOptionsCompat = (ActivityOptionsImplJB)activityOptionsCompat;
                this.mImpl.update(activityOptionsCompat.mImpl);
            }
        }
    }

}

