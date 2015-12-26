/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.FragmentTransaction
 *  android.content.Context
 *  android.content.res.Configuration
 *  android.content.res.TypedArray
 *  android.graphics.drawable.Drawable
 *  android.util.AttributeSet
 *  android.view.KeyEvent
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewGroup$MarginLayoutParams
 *  android.widget.SpinnerAdapter
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 */
package android.support.v7.app;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.appcompat.R;
import android.support.v7.view.ActionMode;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

public abstract class ActionBar {
    public static final int DISPLAY_HOME_AS_UP = 4;
    public static final int DISPLAY_SHOW_CUSTOM = 16;
    public static final int DISPLAY_SHOW_HOME = 2;
    public static final int DISPLAY_SHOW_TAB = 64;
    public static final int DISPLAY_SHOW_TITLE = 8;
    public static final int DISPLAY_USE_LOGO = 1;
    public static final int NAVIGATION_MODE_LIST = 1;
    public static final int NAVIGATION_MODE_STANDARD = 0;
    public static final int NAVIGATION_MODE_TABS = 2;

    public abstract void addOnMenuVisibilityListener(OnMenuVisibilityListener var1);

    public abstract void addTab(Tab var1);

    public abstract void addTab(Tab var1, int var2);

    public abstract void addTab(Tab var1, int var2, boolean var3);

    public abstract void addTab(Tab var1, boolean var2);

    public boolean collapseActionView() {
        return false;
    }

    public void dispatchMenuVisibilityChanged(boolean bl2) {
    }

    public aqy getActionBarTabContainer() {
        return null;
    }

    public ArrayList getAllTabs() {
        return null;
    }

    public abstract View getCustomView();

    public abstract int getDisplayOptions();

    public float getElevation() {
        return 0.0f;
    }

    public abstract int getHeight();

    public int getHideOffset() {
        return 0;
    }

    public abstract int getNavigationItemCount();

    public abstract int getNavigationMode();

    public abstract int getSelectedNavigationIndex();

    @Nullable
    public abstract Tab getSelectedTab();

    @Nullable
    public abstract CharSequence getSubtitle();

    public abstract Tab getTabAt(int var1);

    public abstract int getTabCount();

    public Context getThemedContext() {
        return null;
    }

    @Nullable
    public abstract CharSequence getTitle();

    public abstract void hide();

    public void hide(int n2) {
        this.hide();
    }

    public boolean invalidateOptionsMenu() {
        return false;
    }

    public boolean isHideOnContentScrollEnabled() {
        return false;
    }

    public abstract boolean isShowing();

    public boolean isTitleTruncated() {
        return false;
    }

    public abstract Tab newTab();

    public void onConfigurationChanged(Configuration configuration) {
    }

    public boolean onKeyShortcut(int n2, KeyEvent keyEvent) {
        return false;
    }

    public boolean onMenuKeyEvent(KeyEvent keyEvent) {
        return false;
    }

    public boolean openOptionsMenu() {
        return false;
    }

    public abstract void removeAllTabs();

    public abstract void removeOnMenuVisibilityListener(OnMenuVisibilityListener var1);

    public abstract void removeTab(Tab var1);

    public abstract void removeTabAt(int var1);

    public abstract void selectTab(Tab var1);

    public abstract void setBackgroundDrawable(@Nullable Drawable var1);

    public abstract void setCustomView(int var1);

    public abstract void setCustomView(View var1);

    public abstract void setCustomView(View var1, LayoutParams var2);

    public void setDefaultDisplayHomeAsUpEnabled(boolean bl2) {
    }

    public abstract void setDisplayHomeAsUpEnabled(boolean var1);

    public abstract void setDisplayOptions(int var1);

    public abstract void setDisplayOptions(int var1, int var2);

    public abstract void setDisplayShowCustomEnabled(boolean var1);

    public abstract void setDisplayShowHomeEnabled(boolean var1);

    public abstract void setDisplayShowTabEnabled(boolean var1);

    public abstract void setDisplayShowTitleEnabled(boolean var1);

    public abstract void setDisplayUseLogoEnabled(boolean var1);

    public void setElevation(float f2) {
        if (f2 != 0.0f) {
            throw new UnsupportedOperationException("Setting a non-zero elevation is not supported in this action bar configuration.");
        }
    }

    public void setHideOffset(int n2) {
        if (n2 != 0) {
            throw new UnsupportedOperationException("Setting an explicit action bar hide offset is not supported in this action bar configuration.");
        }
    }

    public void setHideOnContentScrollEnabled(boolean bl2) {
        if (bl2) {
            throw new UnsupportedOperationException("Hide on content scroll is not supported in this action bar configuration.");
        }
    }

    public void setHomeActionContentDescription(int n2) {
    }

    public void setHomeActionContentDescription(@Nullable CharSequence charSequence) {
    }

    public void setHomeAsUpIndicator(int n2) {
    }

    public void setHomeAsUpIndicator(@Nullable Drawable drawable2) {
    }

    public void setHomeButtonEnabled(boolean bl2) {
    }

    public abstract void setIcon(int var1);

    public abstract void setIcon(Drawable var1);

    public abstract void setListNavigationCallbacks(SpinnerAdapter var1, OnNavigationListener var2);

    public abstract void setLogo(int var1);

    public abstract void setLogo(Drawable var1);

    public abstract void setNavigationMode(int var1);

    public void setScrollTabAllowCollapse(boolean bl2) {
    }

    public void setScrollTabCollapseButtonClickListener(View.OnClickListener onClickListener) {
    }

    public abstract void setSelectedNavigationItem(int var1);

    public void setShowHideAnimationEnabled(boolean bl2) {
    }

    public void setSplitBackgroundDrawable(Drawable drawable2) {
    }

    public void setStackedBackgroundDrawable(Drawable drawable2) {
    }

    public abstract void setSubtitle(int var1);

    public abstract void setSubtitle(CharSequence var1);

    public void setTabIndicatorDrawable(Drawable drawable2) {
    }

    public abstract void setTabScrolled(int var1, float var2, int var3);

    public abstract void setTitle(int var1);

    public abstract void setTitle(CharSequence var1);

    public void setUiOptions(int n2) {
    }

    public void setWindowTitle(CharSequence charSequence) {
    }

    public abstract void show();

    public void show(int n2) {
        this.show();
    }

    public ActionMode startActionMode(ActionMode.Callback callback) {
        return null;
    }

    public ActionMode startMultiChoiceActionMode(ActionMode.Callback callback) {
        return null;
    }

    @Retention(value=RetentionPolicy.SOURCE)
    @IntDef(flag=1, value={1, 2, 4, 8, 16, 64})
    public static @interface DisplayOptions {
    }

    public static class LayoutParams
    extends ViewGroup.MarginLayoutParams {
        public int gravity = 0;

        public LayoutParams(int n2) {
            this(-2, -1, n2);
        }

        public LayoutParams(int n2, int n3) {
            super(n2, n3);
            this.gravity = 8388627;
        }

        public LayoutParams(int n2, int n3, int n4) {
            super(n2, n3);
            this.gravity = n4;
        }

        public LayoutParams(@NonNull Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            context = context.obtainStyledAttributes(attributeSet, R.styleable.ActionBarLayout);
            this.gravity = context.getInt(R.styleable.ActionBarLayout_android_layout_gravity, 0);
            context.recycle();
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((ViewGroup.MarginLayoutParams)layoutParams);
            this.gravity = layoutParams.gravity;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    @Retention(value=RetentionPolicy.SOURCE)
    @IntDef(value={0, 1, 2})
    public static @interface NavigationMode {
    }

    public static interface OnMenuVisibilityListener {
        public void onMenuVisibilityChanged(boolean var1);
    }

    public static interface OnNavigationListener {
        public boolean onNavigationItemSelected(int var1, long var2);
    }

    public static abstract class Tab {
        public static final int INVALID_POSITION = -1;

        public abstract TabListener getCallback();

        public abstract TabListenerSDK getCallbackSDK();

        public abstract CharSequence getContentDescription();

        public abstract View getCustomView();

        public abstract Drawable getIcon();

        public abstract int getPosition();

        public abstract Object getTag();

        public abstract CharSequence getText();

        public abstract boolean isEnabled();

        public abstract void select();

        public abstract Tab setContentDescription(int var1);

        public abstract Tab setContentDescription(CharSequence var1);

        public abstract Tab setCustomView(int var1);

        public abstract Tab setCustomView(View var1);

        public abstract Tab setEnabled(boolean var1);

        public abstract Tab setIcon(int var1);

        public abstract Tab setIcon(Drawable var1);

        public abstract Tab setTabListener(TabListener var1);

        public abstract Tab setTabListenerSDK(TabListenerSDK var1);

        public abstract Tab setTag(Object var1);

        public abstract Tab setText(int var1);

        public abstract Tab setText(CharSequence var1);
    }

    public static interface TabListener {
        public void onTabReselected(Tab var1, FragmentTransaction var2);

        public void onTabSelected(Tab var1, FragmentTransaction var2);

        public void onTabUnselected(Tab var1, FragmentTransaction var2);
    }

    public static interface TabListenerSDK {
        public void onTabReselected(Tab var1, android.app.FragmentTransaction var2);

        public void onTabSelected(Tab var1, android.app.FragmentTransaction var2);

        public void onTabUnselected(Tab var1, android.app.FragmentTransaction var2);
    }

}

