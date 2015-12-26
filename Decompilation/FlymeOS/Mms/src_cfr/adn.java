/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.animation.Animator
 *  android.animation.AnimatorListenerAdapter
 *  android.view.ViewGroup
 */
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.ViewGroup;
import com.android.mms.view.MessageListItemBase;

public class adn
extends AnimatorListenerAdapter {
    final /* synthetic */ MessageListItemBase a;

    public adn(MessageListItemBase messageListItemBase) {
        this.a = messageListItemBase;
    }

    public void onAnimationCancel(Animator animator2) {
        this.a.w.setHasTransientState(false);
        this.a.ah = false;
    }

    public void onAnimationEnd(Animator animator2) {
        this.a.w.setHasTransientState(false);
        this.a.ah = false;
    }

    public void onAnimationStart(Animator animator2) {
        this.a.w.setHasTransientState(true);
        this.a.ah = true;
    }
}

