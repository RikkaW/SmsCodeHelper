/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.animation.ValueAnimator
 *  android.animation.ValueAnimator$AnimatorUpdateListener
 *  android.widget.ProgressBar
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 */
import android.animation.ValueAnimator;
import android.widget.ProgressBar;
import com.android.mms.view.MessageListItemTalk;

public class aea
implements ValueAnimator.AnimatorUpdateListener {
    final /* synthetic */ int a;
    final /* synthetic */ MessageListItemTalk b;

    public aea(MessageListItemTalk messageListItemTalk, int n2) {
        this.b = messageListItemTalk;
        this.a = n2;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        if (this.b.J != null) {
            this.b.J.setMax(this.a);
            int n2 = Integer.parseInt((String)valueAnimator.getAnimatedValue().toString());
            this.b.J.setProgress(n2);
        }
    }
}

