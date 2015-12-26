/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  android.view.ViewParent
 *  java.lang.Object
 */
package android.support.v4.animation;

import android.support.v4.animation.DonutAnimatorCompatProvider;
import android.view.View;
import android.view.ViewParent;

class DonutAnimatorCompatProvider$DonutFloatValueAnimator$1
implements Runnable {
    final /* synthetic */ DonutAnimatorCompatProvider.DonutFloatValueAnimator this$0;

    DonutAnimatorCompatProvider$DonutFloatValueAnimator$1(DonutAnimatorCompatProvider.DonutFloatValueAnimator donutFloatValueAnimator) {
        this.this$0 = donutFloatValueAnimator;
    }

    @Override
    public void run() {
        float f2 = (float)(DonutAnimatorCompatProvider.DonutFloatValueAnimator.access$000(this.this$0) - DonutAnimatorCompatProvider.DonutFloatValueAnimator.access$100(this.this$0)) * 1.0f / (float)DonutAnimatorCompatProvider.DonutFloatValueAnimator.access$200(this.this$0);
        if (f2 > 1.0f || this.this$0.mTarget.getParent() == null) {
            f2 = 1.0f;
        }
        DonutAnimatorCompatProvider.DonutFloatValueAnimator.access$302(this.this$0, f2);
        DonutAnimatorCompatProvider.DonutFloatValueAnimator.access$400(this.this$0);
        if (DonutAnimatorCompatProvider.DonutFloatValueAnimator.access$300(this.this$0) >= 1.0f) {
            DonutAnimatorCompatProvider.DonutFloatValueAnimator.access$500(this.this$0);
            return;
        }
        this.this$0.mTarget.postDelayed(DonutAnimatorCompatProvider.DonutFloatValueAnimator.access$600(this.this$0), 16);
    }
}

