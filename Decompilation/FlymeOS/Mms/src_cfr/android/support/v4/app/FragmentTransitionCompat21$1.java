/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.graphics.Rect
 *  android.transition.Transition
 *  android.transition.Transition$EpicenterCallback
 */
package android.support.v4.app;

import android.graphics.Rect;
import android.transition.Transition;

final class FragmentTransitionCompat21$1
extends Transition.EpicenterCallback {
    final /* synthetic */ Rect val$epicenter;

    FragmentTransitionCompat21$1(Rect rect) {
        this.val$epicenter = rect;
    }

    public Rect onGetEpicenter(Transition transition) {
        return this.val$epicenter;
    }
}

