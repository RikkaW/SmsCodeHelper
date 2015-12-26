/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.res.TypedArray
 *  android.graphics.drawable.Drawable
 *  android.os.Bundle
 *  android.view.KeyEvent
 *  android.view.Menu
 *  android.view.MenuInflater
 *  android.view.View
 *  android.view.Window
 *  android.view.Window$Callback
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v7.app;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.appcompat.R;
import android.support.v7.internal.app.WindowDecorActionBar;
import android.support.v7.internal.view.SupportMenuInflater;
import android.support.v7.internal.view.WindowCallbackWrapper;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.internal.widget.TintTypedArray;
import android.support.v7.internal.widget.ViewUtils;
import android.support.v7.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Window;

abstract class AppCompatDelegateImplBase
extends AppCompatDelegate {
    private ActionBar mActionBar;
    final AppCompatCallback mAppCompatCallback;
    final Context mContext;
    boolean mHasActionBar;
    private boolean mIsDestroyed;
    boolean mIsFloating;
    private MenuInflater mMenuInflater;
    int mMzUiOptions;
    final Window.Callback mOriginalWindowCallback;
    boolean mOverlayActionBar;
    boolean mOverlayActionMode;
    private CharSequence mTitle;
    final Window mWindow;
    boolean mWindowNoTitle;

    AppCompatDelegateImplBase(Context context, Window window, AppCompatCallback appCompatCallback) {
        this.mContext = context;
        this.mWindow = window;
        this.mAppCompatCallback = appCompatCallback;
        this.mOriginalWindowCallback = this.mWindow.getCallback();
        if (this.mOriginalWindowCallback instanceof AppCompatWindowCallbackBase) {
            throw new IllegalStateException("AppCompat has already installed itself into the Window");
        }
        this.mWindow.setCallback(this.wrapWindowCallback(this.mOriginalWindowCallback));
    }

    abstract ActionBar createSupportActionBar();

    abstract boolean dispatchKeyEvent(KeyEvent var1);

    final Context getActionBarThemedContext() {
        Context context = null;
        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            context = actionBar.getThemedContext();
        }
        actionBar = context;
        if (context == null) {
            actionBar = this.mContext;
        }
        return actionBar;
    }

    @Override
    public final ActionBarDrawerToggle.Delegate getDrawerToggleDelegate() {
        return new ActionBarDrawableToggleImpl();
    }

    @Override
    public MenuInflater getMenuInflater() {
        if (this.mMenuInflater == null) {
            this.mMenuInflater = new SupportMenuInflater(this.getActionBarThemedContext());
        }
        return this.mMenuInflater;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public ActionBar getSupportActionBar() {
        if (this.mHasActionBar) {
            if (this.mActionBar != null) return this.mActionBar;
            this.mActionBar = this.createSupportActionBar();
            return this.mActionBar;
        }
        if (!(this.mActionBar instanceof WindowDecorActionBar)) return this.mActionBar;
        this.mActionBar = null;
        return this.mActionBar;
    }

    final CharSequence getTitle() {
        if (this.mOriginalWindowCallback instanceof Activity) {
            return ((Activity)this.mOriginalWindowCallback).getTitle();
        }
        return this.mTitle;
    }

    final Window.Callback getWindowCallback() {
        return this.mWindow.getCallback();
    }

    final boolean isDestroyed() {
        return this.mIsDestroyed;
    }

    @Override
    public boolean isHandleNativeActionModesEnabled() {
        return false;
    }

    @Override
    public void onCreate(Bundle bundle) {
        ViewUtils.init();
        bundle = this.mContext.obtainStyledAttributes(R.styleable.Theme);
        if (!bundle.hasValue(R.styleable.Theme_windowActionBar)) {
            bundle.recycle();
            throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
        }
        if (bundle.getBoolean(R.styleable.Theme_windowActionBar, false)) {
            this.mHasActionBar = true;
        }
        if (bundle.getBoolean(R.styleable.Theme_windowActionBarOverlay, false)) {
            this.mOverlayActionBar = true;
        }
        if (bundle.getBoolean(R.styleable.Theme_windowActionModeOverlay, false)) {
            this.mOverlayActionMode = true;
        }
        this.mIsFloating = bundle.getBoolean(R.styleable.Theme_android_windowIsFloating, false);
        this.mWindowNoTitle = bundle.getBoolean(R.styleable.Theme_windowNoTitle, false);
        bundle.recycle();
    }

    @Override
    public final void onDestroy() {
        this.mIsDestroyed = true;
    }

    abstract boolean onKeyShortcut(int var1, KeyEvent var2);

    abstract boolean onMenuOpened(int var1, Menu var2);

    abstract boolean onPanelClosed(int var1, Menu var2);

    abstract void onTitleChanged(CharSequence var1);

    final ActionBar peekSupportActionBar() {
        return this.mActionBar;
    }

    @Override
    public void setHandleNativeActionModesEnabled(boolean bl2) {
    }

    final void setSupportActionBar(ActionBar actionBar) {
        this.mActionBar = actionBar;
    }

    @Override
    public final void setTitle(CharSequence charSequence) {
        this.mTitle = charSequence;
        this.onTitleChanged(charSequence);
    }

    @Override
    public void setUiOptions(int n2) {
        this.mMzUiOptions = n2;
    }

    abstract ActionMode startSupportActionModeFromWindow(ActionMode.Callback var1);

    Window.Callback wrapWindowCallback(Window.Callback callback) {
        return new AppCompatWindowCallbackBase(callback);
    }

    class ActionBarDrawableToggleImpl
    implements ActionBarDrawerToggle.Delegate {
        private ActionBarDrawableToggleImpl() {
        }

        @Override
        public Context getActionBarThemedContext() {
            return AppCompatDelegateImplBase.this.getActionBarThemedContext();
        }

        @Override
        public Drawable getThemeUpIndicator() {
            TintTypedArray tintTypedArray = TintTypedArray.obtainStyledAttributes(this.getActionBarThemedContext(), null, new int[]{R.attr.homeAsUpIndicator});
            Drawable drawable2 = tintTypedArray.getDrawable(0);
            tintTypedArray.recycle();
            return drawable2;
        }

        @Override
        public boolean isNavigationVisible() {
            ActionBar actionBar = AppCompatDelegateImplBase.this.getSupportActionBar();
            if (actionBar != null && (actionBar.getDisplayOptions() & 4) != 0) {
                return true;
            }
            return false;
        }

        @Override
        public void setActionBarDescription(int n2) {
            ActionBar actionBar = AppCompatDelegateImplBase.this.getSupportActionBar();
            if (actionBar != null) {
                actionBar.setHomeActionContentDescription(n2);
            }
        }

        @Override
        public void setActionBarUpIndicator(Drawable drawable2, int n2) {
            ActionBar actionBar = AppCompatDelegateImplBase.this.getSupportActionBar();
            if (actionBar != null) {
                actionBar.setHomeAsUpIndicator(drawable2);
                actionBar.setHomeActionContentDescription(n2);
            }
        }
    }

    class AppCompatWindowCallbackBase
    extends WindowCallbackWrapper {
        AppCompatWindowCallbackBase(Window.Callback callback) {
            super(callback);
        }

        @Override
        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            if (super.dispatchKeyEvent(keyEvent)) {
                return true;
            }
            return AppCompatDelegateImplBase.this.dispatchKeyEvent(keyEvent);
        }

        @Override
        public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
            if (AppCompatDelegateImplBase.this.onKeyShortcut(keyEvent.getKeyCode(), keyEvent)) {
                return true;
            }
            return super.dispatchKeyShortcutEvent(keyEvent);
        }

        @Override
        public void onContentChanged() {
        }

        @Override
        public boolean onCreatePanelMenu(int n2, Menu menu) {
            if (n2 == 0 && !(menu instanceof MenuBuilder)) {
                return false;
            }
            return super.onCreatePanelMenu(n2, menu);
        }

        @Override
        public boolean onMenuOpened(int n2, Menu menu) {
            if (AppCompatDelegateImplBase.this.onMenuOpened(n2, menu)) {
                return true;
            }
            return super.onMenuOpened(n2, menu);
        }

        @Override
        public void onPanelClosed(int n2, Menu menu) {
            if (AppCompatDelegateImplBase.this.onPanelClosed(n2, menu)) {
                return;
            }
            super.onPanelClosed(n2, menu);
        }

        /*
         * Enabled aggressive block sorting
         */
        @Override
        public boolean onPreparePanel(int n2, View view, Menu menu) {
            boolean bl2;
            MenuBuilder menuBuilder = menu instanceof MenuBuilder ? (MenuBuilder)menu : null;
            if (n2 == 0) {
                if (menuBuilder == null) {
                    return false;
                }
                if (menuBuilder != null) {
                    menuBuilder.setOverrideVisibleItems(true);
                }
            }
            boolean bl3 = bl2 = super.onPreparePanel(n2, view, menu);
            if (menuBuilder == null) return bl3;
            menuBuilder.setOverrideVisibleItems(false);
            return bl2;
        }
    }

}

