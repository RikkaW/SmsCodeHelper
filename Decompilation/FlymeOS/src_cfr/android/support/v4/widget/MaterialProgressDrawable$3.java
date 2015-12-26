/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.graphics.drawable.Drawable
 *  android.graphics.drawable.Drawable$Callback
 *  java.lang.Object
 */
package android.support.v4.widget;

import android.graphics.drawable.Drawable;
import android.support.v4.widget.MaterialProgressDrawable;

class MaterialProgressDrawable$3
implements Drawable.Callback {
    final /* synthetic */ MaterialProgressDrawable this$0;

    MaterialProgressDrawable$3(MaterialProgressDrawable materialProgressDrawable) {
        this.this$0 = materialProgressDrawable;
    }

    public void invalidateDrawable(Drawable drawable2) {
        this.this$0.invalidateSelf();
    }

    public void scheduleDrawable(Drawable drawable2, Runnable runnable, long l2) {
        this.this$0.scheduleSelf(runnable, l2);
    }

    public void unscheduleDrawable(Drawable drawable2, Runnable runnable) {
        this.this$0.unscheduleSelf(runnable);
    }
}

