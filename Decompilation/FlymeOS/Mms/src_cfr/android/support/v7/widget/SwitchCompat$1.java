/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.animation.Animation
 *  android.view.animation.Transformation
 */
package android.support.v7.widget;

import android.support.v7.widget.SwitchCompat;
import android.view.animation.Animation;
import android.view.animation.Transformation;

class SwitchCompat$1
extends Animation {
    final /* synthetic */ SwitchCompat this$0;
    final /* synthetic */ float val$diff;
    final /* synthetic */ float val$startPosition;

    SwitchCompat$1(SwitchCompat switchCompat, float f2, float f3) {
        this.this$0 = switchCompat;
        this.val$startPosition = f2;
        this.val$diff = f3;
    }

    protected void applyTransformation(float f2, Transformation transformation) {
        SwitchCompat.access$000(this.this$0, this.val$startPosition + this.val$diff * f2);
    }
}

