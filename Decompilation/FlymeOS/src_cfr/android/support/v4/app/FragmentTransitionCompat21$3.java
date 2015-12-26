/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.graphics.Rect
 *  android.transition.Transition
 *  android.transition.Transition$EpicenterCallback
 *  android.view.View
 */
package android.support.v4.app;

import android.graphics.Rect;
import android.support.v4.app.FragmentTransitionCompat21;
import android.transition.Transition;
import android.view.View;

final class FragmentTransitionCompat21$3
extends Transition.EpicenterCallback {
    private Rect mEpicenter;
    final /* synthetic */ FragmentTransitionCompat21.EpicenterView val$epicenterView;

    FragmentTransitionCompat21$3(FragmentTransitionCompat21.EpicenterView epicenterView) {
        this.val$epicenterView = epicenterView;
    }

    public Rect onGetEpicenter(Transition transition) {
        if (this.mEpicenter == null && this.val$epicenterView.epicenter != null) {
            this.mEpicenter = FragmentTransitionCompat21.access$100(this.val$epicenterView.epicenter);
        }
        return this.mEpicenter;
    }
}

