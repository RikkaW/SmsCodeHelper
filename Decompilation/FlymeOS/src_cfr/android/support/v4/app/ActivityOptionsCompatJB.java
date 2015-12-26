/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.ActivityOptions
 *  android.content.Context
 *  android.graphics.Bitmap
 *  android.os.Bundle
 *  android.view.View
 *  java.lang.Object
 */
package android.support.v4.app;

import android.app.ActivityOptions;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;

class ActivityOptionsCompatJB {
    private final ActivityOptions mActivityOptions;

    private ActivityOptionsCompatJB(ActivityOptions activityOptions) {
        this.mActivityOptions = activityOptions;
    }

    public static ActivityOptionsCompatJB makeCustomAnimation(Context context, int n2, int n3) {
        return new ActivityOptionsCompatJB(ActivityOptions.makeCustomAnimation((Context)context, (int)n2, (int)n3));
    }

    public static ActivityOptionsCompatJB makeScaleUpAnimation(View view, int n2, int n3, int n4, int n5) {
        return new ActivityOptionsCompatJB(ActivityOptions.makeScaleUpAnimation((View)view, (int)n2, (int)n3, (int)n4, (int)n5));
    }

    public static ActivityOptionsCompatJB makeThumbnailScaleUpAnimation(View view, Bitmap bitmap, int n2, int n3) {
        return new ActivityOptionsCompatJB(ActivityOptions.makeThumbnailScaleUpAnimation((View)view, (Bitmap)bitmap, (int)n2, (int)n3));
    }

    public Bundle toBundle() {
        return this.mActivityOptions.toBundle();
    }

    public void update(ActivityOptionsCompatJB activityOptionsCompatJB) {
        this.mActivityOptions.update(activityOptionsCompatJB.mActivityOptions);
    }
}

