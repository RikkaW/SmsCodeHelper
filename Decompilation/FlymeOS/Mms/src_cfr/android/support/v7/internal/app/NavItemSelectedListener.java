/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  java.lang.Object
 */
package android.support.v7.internal.app;

import android.support.v7.app.ActionBar;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.view.View;

class NavItemSelectedListener
implements AdapterViewCompat.OnItemSelectedListener {
    private final ActionBar.OnNavigationListener mListener;

    public NavItemSelectedListener(ActionBar.OnNavigationListener onNavigationListener) {
        this.mListener = onNavigationListener;
    }

    @Override
    public void onItemSelected(AdapterViewCompat<?> adapterViewCompat, View view, int n2, long l2) {
        if (this.mListener != null) {
            this.mListener.onNavigationItemSelected(n2, l2);
        }
    }

    @Override
    public void onNothingSelected(AdapterViewCompat<?> adapterViewCompat) {
    }
}

