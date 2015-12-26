/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.View
 */
package android.support.v7.internal.view;

import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v7.internal.view.ViewPropertyAnimatorCompatSet;
import android.view.View;

class ViewPropertyAnimatorCompatSet$1
extends ViewPropertyAnimatorListenerAdapter {
    private int mProxyEndCount;
    private boolean mProxyStarted;
    final /* synthetic */ ViewPropertyAnimatorCompatSet this$0;

    ViewPropertyAnimatorCompatSet$1(ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet) {
        this.this$0 = viewPropertyAnimatorCompatSet;
        this.mProxyStarted = false;
        this.mProxyEndCount = 0;
    }

    @Override
    public void onAnimationEnd(View view) {
        int n2;
        this.mProxyEndCount = n2 = this.mProxyEndCount + 1;
        if (n2 == ViewPropertyAnimatorCompatSet.access$200(this.this$0).size()) {
            if (ViewPropertyAnimatorCompatSet.access$000(this.this$0) != null) {
                ViewPropertyAnimatorCompatSet.access$000(this.this$0).onAnimationEnd(null);
            }
            this.onEnd();
        }
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    @Override
    public void onAnimationStart(View view) {
        if (this.mProxyStarted) {
            return;
        }
        this.mProxyStarted = true;
        if (ViewPropertyAnimatorCompatSet.access$000(this.this$0) == null) return;
        ViewPropertyAnimatorCompatSet.access$000(this.this$0).onAnimationStart(null);
    }

    void onEnd() {
        this.mProxyEndCount = 0;
        this.mProxyStarted = false;
        ViewPropertyAnimatorCompatSet.access$100(this.this$0);
    }
}

