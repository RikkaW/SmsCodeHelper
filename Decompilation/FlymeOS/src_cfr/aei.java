/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.animation.ValueAnimator
 *  android.animation.ValueAnimator$AnimatorUpdateListener
 *  java.lang.Object
 */
import android.animation.ValueAnimator;
import com.android.mms.view.MessagePopupGroupView;

class aei
implements ValueAnimator.AnimatorUpdateListener {
    final /* synthetic */ boolean a;
    final /* synthetic */ MessagePopupGroupView.a b;
    final /* synthetic */ aee c;

    aei(aee aee2, boolean bl2, MessagePopupGroupView.a a2) {
        this.c = aee2;
        this.a = bl2;
        this.b = a2;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        if (this.a) {
            this.b.c(aee.a(this.c, this.b));
        }
    }
}

