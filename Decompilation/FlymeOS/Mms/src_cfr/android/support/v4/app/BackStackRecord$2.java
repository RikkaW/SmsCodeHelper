/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  android.view.ViewTreeObserver
 *  android.view.ViewTreeObserver$OnPreDrawListener
 *  java.lang.Object
 *  java.util.ArrayList
 */
package android.support.v4.app;

import android.support.v4.app.BackStackRecord;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransitionCompat21;
import android.support.v4.util.ArrayMap;
import android.view.View;
import android.view.ViewTreeObserver;
import java.util.ArrayList;
import java.util.Collection;

class BackStackRecord$2
implements ViewTreeObserver.OnPreDrawListener {
    final /* synthetic */ BackStackRecord this$0;
    final /* synthetic */ Fragment val$inFragment;
    final /* synthetic */ boolean val$isBack;
    final /* synthetic */ Fragment val$outFragment;
    final /* synthetic */ View val$sceneRoot;
    final /* synthetic */ ArrayList val$sharedElementTargets;
    final /* synthetic */ Object val$sharedElementTransition;
    final /* synthetic */ BackStackRecord.TransitionState val$state;

    BackStackRecord$2(BackStackRecord backStackRecord, View view, Object object, ArrayList arrayList, BackStackRecord.TransitionState transitionState, boolean bl2, Fragment fragment, Fragment fragment2) {
        this.this$0 = backStackRecord;
        this.val$sceneRoot = view;
        this.val$sharedElementTransition = object;
        this.val$sharedElementTargets = arrayList;
        this.val$state = transitionState;
        this.val$isBack = bl2;
        this.val$inFragment = fragment;
        this.val$outFragment = fragment2;
    }

    public boolean onPreDraw() {
        this.val$sceneRoot.getViewTreeObserver().removeOnPreDrawListener((ViewTreeObserver.OnPreDrawListener)this);
        if (this.val$sharedElementTransition != null) {
            FragmentTransitionCompat21.removeTargets(this.val$sharedElementTransition, this.val$sharedElementTargets);
            this.val$sharedElementTargets.clear();
            ArrayMap arrayMap = BackStackRecord.access$000(this.this$0, this.val$state, this.val$isBack, this.val$inFragment);
            this.val$sharedElementTargets.add((Object)this.val$state.nonExistentView);
            this.val$sharedElementTargets.addAll(arrayMap.values());
            FragmentTransitionCompat21.addTargets(this.val$sharedElementTransition, this.val$sharedElementTargets);
            BackStackRecord.access$100(this.this$0, arrayMap, this.val$state);
            BackStackRecord.access$200(this.this$0, this.val$state, this.val$inFragment, this.val$outFragment, this.val$isBack, arrayMap);
        }
        return true;
    }
}

