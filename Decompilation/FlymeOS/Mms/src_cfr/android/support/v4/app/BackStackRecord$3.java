/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  android.view.ViewTreeObserver
 *  android.view.ViewTreeObserver$OnPreDrawListener
 *  java.lang.Object
 */
package android.support.v4.app;

import android.support.v4.app.BackStackRecord;
import android.view.View;
import android.view.ViewTreeObserver;

class BackStackRecord$3
implements ViewTreeObserver.OnPreDrawListener {
    final /* synthetic */ BackStackRecord this$0;
    final /* synthetic */ int val$containerId;
    final /* synthetic */ View val$sceneRoot;
    final /* synthetic */ BackStackRecord.TransitionState val$state;
    final /* synthetic */ Object val$transition;

    BackStackRecord$3(BackStackRecord backStackRecord, View view, BackStackRecord.TransitionState transitionState, int n2, Object object) {
        this.this$0 = backStackRecord;
        this.val$sceneRoot = view;
        this.val$state = transitionState;
        this.val$containerId = n2;
        this.val$transition = object;
    }

    public boolean onPreDraw() {
        this.val$sceneRoot.getViewTreeObserver().removeOnPreDrawListener((ViewTreeObserver.OnPreDrawListener)this);
        BackStackRecord.access$300(this.this$0, this.val$state, this.val$containerId, this.val$transition);
        return true;
    }
}

