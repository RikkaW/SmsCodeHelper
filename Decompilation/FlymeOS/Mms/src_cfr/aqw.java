/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.animation.ValueAnimator
 *  android.animation.ValueAnimator$AnimatorUpdateListener
 *  java.lang.Integer
 *  java.lang.Object
 */
import android.animation.ValueAnimator;

class aqw
implements ValueAnimator.AnimatorUpdateListener {
    final /* synthetic */ aqt a;

    aqw(aqt aqt2) {
        this.a = aqt2;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        int n2 = (Integer)valueAnimator.getAnimatedValue();
        aqt.access$102(this.a, n2);
        if (aqt.access$200(this.a) <= n2) {
            aqt.access$302(this.a, false);
            if (!aqt.access$400(this.a) && aqt.access$500(this.a)) {
                this.a.rippleFade();
            }
        }
        this.a.invalidateSelf();
    }
}

