/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.animation.Animation
 *  android.view.animation.Animation$AnimationListener
 *  java.lang.Object
 */
package android.support.v4.widget;

import android.support.v4.widget.MaterialProgressDrawable;
import android.view.animation.Animation;

class MaterialProgressDrawable$2
implements Animation.AnimationListener {
    final /* synthetic */ MaterialProgressDrawable this$0;
    final /* synthetic */ MaterialProgressDrawable.Ring val$ring;

    MaterialProgressDrawable$2(MaterialProgressDrawable materialProgressDrawable, MaterialProgressDrawable.Ring ring) {
        this.this$0 = materialProgressDrawable;
        this.val$ring = ring;
    }

    public void onAnimationEnd(Animation animation) {
    }

    public void onAnimationRepeat(Animation animation) {
        this.val$ring.storeOriginals();
        this.val$ring.goToNextColor();
        this.val$ring.setStartTrim(this.val$ring.getEndTrim());
        if (this.this$0.mFinishing) {
            this.this$0.mFinishing = false;
            animation.setDuration(1332);
            this.val$ring.setShowArrow(false);
            return;
        }
        MaterialProgressDrawable.access$402(this.this$0, (MaterialProgressDrawable.access$400(this.this$0) + 1.0f) % 5.0f);
    }

    public void onAnimationStart(Animation animation) {
        MaterialProgressDrawable.access$402(this.this$0, 0.0f);
    }
}

