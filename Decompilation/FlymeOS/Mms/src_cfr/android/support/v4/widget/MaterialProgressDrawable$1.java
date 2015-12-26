/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.animation.Animation
 *  android.view.animation.Transformation
 */
package android.support.v4.widget;

import android.support.v4.widget.MaterialProgressDrawable;
import android.view.animation.Animation;
import android.view.animation.Transformation;

class MaterialProgressDrawable$1
extends Animation {
    final /* synthetic */ MaterialProgressDrawable this$0;
    final /* synthetic */ MaterialProgressDrawable.Ring val$ring;

    MaterialProgressDrawable$1(MaterialProgressDrawable materialProgressDrawable, MaterialProgressDrawable.Ring ring) {
        this.this$0 = materialProgressDrawable;
        this.val$ring = ring;
    }

    public void applyTransformation(float f2, Transformation transformation) {
        if (this.this$0.mFinishing) {
            MaterialProgressDrawable.access$000(this.this$0, f2, this.val$ring);
            return;
        }
        float f3 = MaterialProgressDrawable.access$100(this.this$0, this.val$ring);
        float f4 = this.val$ring.getStartingEndTrim();
        float f5 = this.val$ring.getStartingStartTrim();
        float f6 = this.val$ring.getStartingRotation();
        MaterialProgressDrawable.access$200(this.this$0, f2, this.val$ring);
        if (f2 <= 0.5f) {
            float f7 = f2 / 0.5f;
            f7 = MaterialProgressDrawable.access$300().getInterpolation(f7);
            this.val$ring.setStartTrim(f5 + f7 * (0.8f - f3));
        }
        if (f2 > 0.5f) {
            f5 = (f2 - 0.5f) / 0.5f;
            f5 = MaterialProgressDrawable.access$300().getInterpolation(f5);
            this.val$ring.setEndTrim((0.8f - f3) * f5 + f4);
        }
        this.val$ring.setRotation(0.25f * f2 + f6);
        f3 = MaterialProgressDrawable.access$400(this.this$0) / 5.0f;
        this.this$0.setRotation(216.0f * f2 + 1080.0f * f3);
    }
}

