/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.transition.Transition
 *  android.view.View
 *  android.view.ViewTreeObserver
 *  android.view.ViewTreeObserver$OnPreDrawListener
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.Map$Entry
 */
package android.support.v4.app;

import android.support.v4.app.FragmentTransitionCompat21;
import android.transition.Transition;
import android.view.View;
import android.view.ViewTreeObserver;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

final class FragmentTransitionCompat21$4
implements ViewTreeObserver.OnPreDrawListener {
    final /* synthetic */ Transition val$enterTransition;
    final /* synthetic */ ArrayList val$enteringViews;
    final /* synthetic */ Transition val$exitTransition;
    final /* synthetic */ ArrayList val$exitingViews;
    final /* synthetic */ ArrayList val$hiddenViews;
    final /* synthetic */ View val$nonExistentView;
    final /* synthetic */ Transition val$overallTransition;
    final /* synthetic */ Map val$renamedViews;
    final /* synthetic */ View val$sceneRoot;
    final /* synthetic */ ArrayList val$sharedElementTargets;
    final /* synthetic */ Transition val$sharedElementTransition;

    FragmentTransitionCompat21$4(View view, Transition transition, View view2, ArrayList arrayList, Transition transition2, ArrayList arrayList2, Transition transition3, ArrayList arrayList3, Map map, ArrayList arrayList4, Transition transition4) {
        this.val$sceneRoot = view;
        this.val$enterTransition = transition;
        this.val$nonExistentView = view2;
        this.val$enteringViews = arrayList;
        this.val$exitTransition = transition2;
        this.val$exitingViews = arrayList2;
        this.val$sharedElementTransition = transition3;
        this.val$sharedElementTargets = arrayList3;
        this.val$renamedViews = map;
        this.val$hiddenViews = arrayList4;
        this.val$overallTransition = transition4;
    }

    public boolean onPreDraw() {
        this.val$sceneRoot.getViewTreeObserver().removeOnPreDrawListener((ViewTreeObserver.OnPreDrawListener)this);
        if (this.val$enterTransition != null) {
            this.val$enterTransition.removeTarget(this.val$nonExistentView);
            FragmentTransitionCompat21.removeTargets((Object)this.val$enterTransition, this.val$enteringViews);
        }
        if (this.val$exitTransition != null) {
            FragmentTransitionCompat21.removeTargets((Object)this.val$exitTransition, this.val$exitingViews);
        }
        if (this.val$sharedElementTransition != null) {
            FragmentTransitionCompat21.removeTargets((Object)this.val$sharedElementTransition, this.val$sharedElementTargets);
        }
        for (Map.Entry entry : this.val$renamedViews.entrySet()) {
            ((View)entry.getValue()).setTransitionName((String)entry.getKey());
        }
        int n2 = this.val$hiddenViews.size();
        for (int i2 = 0; i2 < n2; ++i2) {
            this.val$overallTransition.excludeTarget((View)this.val$hiddenViews.get(i2), false);
        }
        this.val$overallTransition.excludeTarget(this.val$nonExistentView, false);
        return true;
    }
}

