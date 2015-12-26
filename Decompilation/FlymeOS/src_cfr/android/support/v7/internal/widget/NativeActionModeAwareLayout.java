/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.annotation.TargetApi
 *  android.content.Context
 *  android.util.AttributeSet
 *  android.view.ActionMode
 *  android.view.ActionMode$Callback
 *  android.view.View
 *  java.lang.Object
 */
package android.support.v7.internal.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.v7.internal.widget.ContentFrameLayout;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.View;

@TargetApi(value=11)
public class NativeActionModeAwareLayout
extends ContentFrameLayout {
    private OnActionModeForChildListener mActionModeForChildListener;

    public NativeActionModeAwareLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void makeOptionalFitsSystemWindows() {
    }

    public void setActionModeForChildListener(OnActionModeForChildListener onActionModeForChildListener) {
        this.mActionModeForChildListener = onActionModeForChildListener;
    }

    public ActionMode startActionModeForChild(View view, ActionMode.Callback callback) {
        if (this.mActionModeForChildListener != null) {
            return this.mActionModeForChildListener.startActionModeForChild(view, callback);
        }
        return super.startActionModeForChild(view, callback);
    }

    public static interface OnActionModeForChildListener {
        public ActionMode startActionModeForChild(View var1, ActionMode.Callback var2);
    }

}

