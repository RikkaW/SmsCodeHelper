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

class aqx
implements ValueAnimator.AnimatorUpdateListener {
    final /* synthetic */ float a;
    final /* synthetic */ aqt b;

    aqx(aqt aqt2, float f2) {
        this.b = aqt2;
        this.a = f2;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        int n2 = (Integer)valueAnimator.getAnimatedValue();
        if (aqt.access$600(this.b)) {
            aqt.access$102(this.b, n2);
        }
        if (aqt.access$700(this.b) >= n2) {
            aqt.access$302(this.b, false);
        }
        if (aqt.access$800(this.b)) {
            n2 = (int)(((float)n2 / (float)aqt.access$200(this.b) - this.a) / (1.0f - this.a) * (float)aqt.access$900(this.b));
            aqt.access$1000(this.b).setAlpha(n2);
            aqt.access$1100(this.b).setAlpha(n2 / 2);
        }
        this.b.invalidateSelf();
    }
}

