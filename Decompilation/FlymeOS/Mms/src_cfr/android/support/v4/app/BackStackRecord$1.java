/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  java.lang.Object
 */
package android.support.v4.app;

import android.support.v4.app.BackStackRecord;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransitionCompat21;
import android.view.View;

class BackStackRecord$1
implements FragmentTransitionCompat21.ViewRetriever {
    final /* synthetic */ BackStackRecord this$0;
    final /* synthetic */ Fragment val$inFragment;

    BackStackRecord$1(BackStackRecord backStackRecord, Fragment fragment) {
        this.this$0 = backStackRecord;
        this.val$inFragment = fragment;
    }

    @Override
    public View getView() {
        return this.val$inFragment.getView();
    }
}

