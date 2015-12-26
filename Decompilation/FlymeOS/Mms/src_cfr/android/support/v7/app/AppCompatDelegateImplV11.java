/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.annotation.TargetApi
 *  android.content.Context
 *  android.util.AttributeSet
 *  android.view.ActionMode
 *  android.view.ActionMode$Callback
 *  android.view.LayoutInflater
 *  android.view.LayoutInflater$Factory2
 *  android.view.View
 *  android.view.ViewGroup
 *  android.view.Window
 *  android.view.Window$Callback
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v7.app;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.app.AppCompatDelegateImplV7;
import android.support.v7.internal.view.SupportActionModeWrapper;
import android.support.v7.internal.widget.NativeActionModeAwareLayout;
import android.support.v7.view.ActionMode;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

@TargetApi(value=11)
class AppCompatDelegateImplV11
extends AppCompatDelegateImplV7
implements NativeActionModeAwareLayout.OnActionModeForChildListener {
    private NativeActionModeAwareLayout mNativeActionModeAwareLayout;

    AppCompatDelegateImplV11(Context context, Window window, AppCompatCallback appCompatCallback) {
        super(context, window, appCompatCallback);
    }

    private boolean checkMultiChoiceMode(ActionMode.Callback object) {
        boolean bl2 = false;
        if ((object = object.getClass().getName()).equals((Object)"android.widget.AbsListView$MultiChoiceModeWrapper") || object.equals((Object)"flyme.support.v7.widget.MzRecyclerView$MultiChoiceModeWrapper")) {
            bl2 = true;
        }
        return bl2;
    }

    @Override
    View callActivityOnCreateView(View view, String string2, Context context, AttributeSet attributeSet) {
        View view2 = super.callActivityOnCreateView(view, string2, context, attributeSet);
        if (view2 != null) {
            return view2;
        }
        if (this.mOriginalWindowCallback instanceof LayoutInflater.Factory2) {
            return ((LayoutInflater.Factory2)this.mOriginalWindowCallback).onCreateView(view, string2, context, attributeSet);
        }
        return null;
    }

    @Override
    void onSubDecorInstalled(ViewGroup viewGroup) {
        this.mNativeActionModeAwareLayout = (NativeActionModeAwareLayout)viewGroup.findViewById(16908290);
        if (this.mNativeActionModeAwareLayout != null) {
            this.mNativeActionModeAwareLayout.setActionModeForChildListener(this);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public ActionMode startActionModeForChild(View object, ActionMode.Callback object2) {
        object = object.getContext();
        if (this.checkMultiChoiceMode((ActionMode.Callback)object2)) {
            SupportActionModeWrapper.CallbackWrapper callbackWrapper = new SupportActionModeWrapper.CallbackWrapper((Context)object, (ActionMode.Callback)object2);
            object = object2 = this.startMultiChoiceActionMode(callbackWrapper);
            if (object2 == null) {
                object = this.startSupportActionMode(callbackWrapper);
            }
        } else {
            object = this.startSupportActionMode(new SupportActionModeWrapper.CallbackWrapper((Context)object, (ActionMode.Callback)object2));
        }
        if (object != null) {
            return new SupportActionModeWrapper(this.mContext, (android.support.v7.view.ActionMode)object);
        }
        return null;
    }
}

