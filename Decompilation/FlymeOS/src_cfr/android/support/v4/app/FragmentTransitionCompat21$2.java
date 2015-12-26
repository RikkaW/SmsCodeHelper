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
import java.util.Collection;
import java.util.Map;
import java.util.Set;

final class FragmentTransitionCompat21$2
implements ViewTreeObserver.OnPreDrawListener {
    final /* synthetic */ View val$container;
    final /* synthetic */ Transition val$enterTransition;
    final /* synthetic */ ArrayList val$enteringViews;
    final /* synthetic */ FragmentTransitionCompat21.ViewRetriever val$inFragment;
    final /* synthetic */ Map val$nameOverrides;
    final /* synthetic */ View val$nonExistentView;
    final /* synthetic */ Map val$renamedViews;

    FragmentTransitionCompat21$2(View view, FragmentTransitionCompat21.ViewRetriever viewRetriever, Map map, Map map2, Transition transition, ArrayList arrayList, View view2) {
        this.val$container = view;
        this.val$inFragment = viewRetriever;
        this.val$nameOverrides = map;
        this.val$renamedViews = map2;
        this.val$enterTransition = transition;
        this.val$enteringViews = arrayList;
        this.val$nonExistentView = view2;
    }

    public boolean onPreDraw() {
        this.val$container.getViewTreeObserver().removeOnPreDrawListener((ViewTreeObserver.OnPreDrawListener)this);
        View view = this.val$inFragment.getView();
        if (view != null) {
            if (!this.val$nameOverrides.isEmpty()) {
                FragmentTransitionCompat21.findNamedViews(this.val$renamedViews, view);
                this.val$renamedViews.keySet().retainAll(this.val$nameOverrides.values());
                for (Map.Entry entry : this.val$nameOverrides.entrySet()) {
                    String string2 = (String)entry.getValue();
                    if ((string2 = (View)this.val$renamedViews.get(string2)) == null) continue;
                    string2.setTransitionName((String)entry.getKey());
                }
            }
            if (this.val$enterTransition != null) {
                FragmentTransitionCompat21.access$000(this.val$enteringViews, view);
                this.val$enteringViews.removeAll(this.val$renamedViews.values());
                this.val$enteringViews.add((Object)this.val$nonExistentView);
                this.val$enterTransition.removeTarget(this.val$nonExistentView);
                FragmentTransitionCompat21.addTargets((Object)this.val$enterTransition, this.val$enteringViews);
            }
        }
        return true;
    }
}

