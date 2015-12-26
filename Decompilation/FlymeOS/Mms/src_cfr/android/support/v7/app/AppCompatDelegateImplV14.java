/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.ActionMode
 *  android.view.ActionMode$Callback
 *  android.view.KeyEvent
 *  android.view.Window
 *  android.view.Window$Callback
 *  java.lang.Object
 */
package android.support.v7.app;

import android.content.Context;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.app.AppCompatDelegateImplBase;
import android.support.v7.app.AppCompatDelegateImplV11;
import android.support.v7.internal.view.SupportActionModeWrapper;
import android.support.v7.view.ActionMode;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Window;

class AppCompatDelegateImplV14
extends AppCompatDelegateImplV11 {
    private boolean mHandleNativeActionModes = true;

    AppCompatDelegateImplV14(Context context, Window window, AppCompatCallback appCompatCallback) {
        super(context, window, appCompatCallback);
    }

    @Override
    public boolean isHandleNativeActionModesEnabled() {
        return this.mHandleNativeActionModes;
    }

    @Override
    public void setHandleNativeActionModesEnabled(boolean bl2) {
        this.mHandleNativeActionModes = bl2;
    }

    @Override
    Window.Callback wrapWindowCallback(Window.Callback callback) {
        return new AppCompatWindowCallbackV14(callback);
    }

    class AppCompatWindowCallbackV14
    extends AppCompatDelegateImplBase.AppCompatWindowCallbackBase {
        AppCompatWindowCallbackV14(Window.Callback callback) {
            super(callback);
        }

        @Override
        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            if (AppCompatDelegateImplV14.this.dispatchKeyEvent(keyEvent)) {
                return true;
            }
            return super.dispatchKeyEvent(keyEvent);
        }

        @Override
        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
            if (AppCompatDelegateImplV14.this.mHandleNativeActionModes) {
                return this.startAsSupportActionMode(callback);
            }
            return super.onWindowStartingActionMode(callback);
        }

        final ActionMode startAsSupportActionMode(ActionMode.Callback object) {
            Object object2 = AppCompatDelegateImplV14.this.startSupportActionMode((ActionMode.Callback)(object = new SupportActionModeWrapper.CallbackWrapper(AppCompatDelegateImplV14.this.mContext, (ActionMode.Callback)object)));
            if (object2 != null) {
                object2 = new SupportActionModeWrapper(AppCompatDelegateImplV14.this.mContext, (android.support.v7.view.ActionMode)object2);
                object.addActionModeWrapper((SupportActionModeWrapper)((Object)object2));
                return object2;
            }
            return null;
        }
    }

}

