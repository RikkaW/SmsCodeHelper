/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.ActivityOptions
 *  android.os.Bundle
 *  android.util.Pair
 *  android.view.View
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.app;

import android.app.Activity;
import android.app.ActivityOptions;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;

class ActivityOptionsCompat21 {
    private final ActivityOptions mActivityOptions;

    private ActivityOptionsCompat21(ActivityOptions activityOptions) {
        this.mActivityOptions = activityOptions;
    }

    public static ActivityOptionsCompat21 makeSceneTransitionAnimation(Activity activity, View view, String string2) {
        return new ActivityOptionsCompat21(ActivityOptions.makeSceneTransitionAnimation((Activity)activity, (View)view, (String)string2));
    }

    public static ActivityOptionsCompat21 makeSceneTransitionAnimation(Activity activity, View[] arrview, String[] arrstring) {
        Pair[] arrpair = null;
        if (arrview != null) {
            arrpair = new Pair[arrview.length];
            for (int i2 = 0; i2 < arrpair.length; ++i2) {
                arrpair[i2] = Pair.create((Object)arrview[i2], (Object)arrstring[i2]);
            }
        }
        return new ActivityOptionsCompat21(ActivityOptions.makeSceneTransitionAnimation((Activity)activity, (Pair[])arrpair));
    }

    public Bundle toBundle() {
        return this.mActivityOptions.toBundle();
    }

    public void update(ActivityOptionsCompat21 activityOptionsCompat21) {
        this.mActivityOptions.update(activityOptionsCompat21.mActivityOptions);
    }
}

