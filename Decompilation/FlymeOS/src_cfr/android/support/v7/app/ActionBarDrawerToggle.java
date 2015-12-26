/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.ActionBar
 *  android.app.Activity
 *  android.content.Context
 *  android.content.res.Configuration
 *  android.content.res.Resources
 *  android.content.res.TypedArray
 *  android.graphics.drawable.Drawable
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.util.AttributeSet
 *  android.util.Log
 *  android.view.MenuItem
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.Window
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v7.app;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle$1;
import android.support.v7.app.ActionBarDrawerToggleHoneycomb;
import android.support.v7.app.DrawerArrowDrawable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

public class ActionBarDrawerToggle
implements DrawerLayout.DrawerListener {
    private final Delegate mActivityImpl;
    private final int mCloseDrawerContentDescRes;
    private boolean mDrawerIndicatorEnabled = true;
    private final DrawerLayout mDrawerLayout;
    private boolean mHasCustomUpIndicator;
    private Drawable mHomeAsUpIndicator;
    private final int mOpenDrawerContentDescRes;
    private DrawerToggle mSlider;
    private View.OnClickListener mToolbarNavigationClickListener;
    private boolean mWarnedForDisplayHomeAsUp = false;

    public ActionBarDrawerToggle(Activity activity, DrawerLayout drawerLayout, int n2, int n3) {
        this(activity, null, drawerLayout, null, n2, n3);
    }

    public ActionBarDrawerToggle(Activity activity, DrawerLayout drawerLayout, Toolbar toolbar, int n2, int n3) {
        this(activity, toolbar, drawerLayout, null, n2, n3);
    }

    /*
     * Enabled aggressive block sorting
     */
    <T extends Drawable> ActionBarDrawerToggle(Activity activity, Toolbar toolbar, DrawerLayout drawerLayout, T t2, int n2, int n3) {
        if (toolbar != null) {
            this.mActivityImpl = new ToolbarCompatDelegate(toolbar);
            toolbar.setNavigationOnClickListener(new ActionBarDrawerToggle$1(this));
        } else {
            this.mActivityImpl = activity instanceof DelegateProvider ? ((DelegateProvider)activity).getDrawerToggleDelegate() : (Build.VERSION.SDK_INT >= 18 ? new JellybeanMr2Delegate(activity, null) : (Build.VERSION.SDK_INT >= 11 ? new HoneycombDelegate(activity, null) : new DummyDelegate(activity)));
        }
        this.mDrawerLayout = drawerLayout;
        this.mOpenDrawerContentDescRes = n2;
        this.mCloseDrawerContentDescRes = n3;
        this.mSlider = t2 == null ? new DrawerArrowDrawableToggle(activity, this.mActivityImpl.getActionBarThemedContext()) : (DrawerToggle)t2;
        this.mHomeAsUpIndicator = this.getThemeUpIndicator();
    }

    static /* synthetic */ boolean access$000(ActionBarDrawerToggle actionBarDrawerToggle) {
        return actionBarDrawerToggle.mDrawerIndicatorEnabled;
    }

    static /* synthetic */ void access$100(ActionBarDrawerToggle actionBarDrawerToggle) {
        actionBarDrawerToggle.toggle();
    }

    static /* synthetic */ View.OnClickListener access$200(ActionBarDrawerToggle actionBarDrawerToggle) {
        return actionBarDrawerToggle.mToolbarNavigationClickListener;
    }

    private void toggle() {
        if (this.mDrawerLayout.isDrawerVisible(8388611)) {
            this.mDrawerLayout.closeDrawer(8388611);
            return;
        }
        this.mDrawerLayout.openDrawer(8388611);
    }

    Drawable getThemeUpIndicator() {
        return this.mActivityImpl.getThemeUpIndicator();
    }

    public View.OnClickListener getToolbarNavigationClickListener() {
        return this.mToolbarNavigationClickListener;
    }

    public boolean isDrawerIndicatorEnabled() {
        return this.mDrawerIndicatorEnabled;
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (!this.mHasCustomUpIndicator) {
            this.mHomeAsUpIndicator = this.getThemeUpIndicator();
        }
        this.syncState();
    }

    @Override
    public void onDrawerClosed(View view) {
        this.mSlider.setPosition(0.0f);
        if (this.mDrawerIndicatorEnabled) {
            this.setActionBarDescription(this.mOpenDrawerContentDescRes);
        }
    }

    @Override
    public void onDrawerOpened(View view) {
        this.mSlider.setPosition(1.0f);
        if (this.mDrawerIndicatorEnabled) {
            this.setActionBarDescription(this.mCloseDrawerContentDescRes);
        }
    }

    @Override
    public void onDrawerSlide(View view, float f2) {
        this.mSlider.setPosition(Math.min((float)1.0f, (float)Math.max((float)0.0f, (float)f2)));
    }

    @Override
    public void onDrawerStateChanged(int n2) {
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem != null && menuItem.getItemId() == 16908332 && this.mDrawerIndicatorEnabled) {
            this.toggle();
            return true;
        }
        return false;
    }

    void setActionBarDescription(int n2) {
        this.mActivityImpl.setActionBarDescription(n2);
    }

    void setActionBarUpIndicator(Drawable drawable2, int n2) {
        if (!this.mWarnedForDisplayHomeAsUp && !this.mActivityImpl.isNavigationVisible()) {
            Log.w((String)"ActionBarDrawerToggle", (String)"DrawerToggle may not show up because NavigationIcon is not visible. You may need to call actionbar.setDisplayHomeAsUpEnabled(true);");
            this.mWarnedForDisplayHomeAsUp = true;
        }
        this.mActivityImpl.setActionBarUpIndicator(drawable2, n2);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setDrawerIndicatorEnabled(boolean bl2) {
        if (bl2 != this.mDrawerIndicatorEnabled) {
            if (bl2) {
                Drawable drawable2 = (Drawable)this.mSlider;
                int n2 = this.mDrawerLayout.isDrawerOpen(8388611) ? this.mCloseDrawerContentDescRes : this.mOpenDrawerContentDescRes;
                this.setActionBarUpIndicator(drawable2, n2);
            } else {
                this.setActionBarUpIndicator(this.mHomeAsUpIndicator, 0);
            }
            this.mDrawerIndicatorEnabled = bl2;
        }
    }

    public void setHomeAsUpIndicator(int n2) {
        Drawable drawable2 = null;
        if (n2 != 0) {
            drawable2 = this.mDrawerLayout.getResources().getDrawable(n2);
        }
        this.setHomeAsUpIndicator(drawable2);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setHomeAsUpIndicator(Drawable drawable2) {
        if (drawable2 == null) {
            this.mHomeAsUpIndicator = this.getThemeUpIndicator();
            this.mHasCustomUpIndicator = false;
        } else {
            this.mHomeAsUpIndicator = drawable2;
            this.mHasCustomUpIndicator = true;
        }
        if (!this.mDrawerIndicatorEnabled) {
            this.setActionBarUpIndicator(this.mHomeAsUpIndicator, 0);
        }
    }

    public void setToolbarNavigationClickListener(View.OnClickListener onClickListener) {
        this.mToolbarNavigationClickListener = onClickListener;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void syncState() {
        if (this.mDrawerLayout.isDrawerOpen(8388611)) {
            this.mSlider.setPosition(1.0f);
        } else {
            this.mSlider.setPosition(0.0f);
        }
        if (this.mDrawerIndicatorEnabled) {
            Drawable drawable2 = (Drawable)this.mSlider;
            int n2 = this.mDrawerLayout.isDrawerOpen(8388611) ? this.mCloseDrawerContentDescRes : this.mOpenDrawerContentDescRes;
            this.setActionBarUpIndicator(drawable2, n2);
        }
    }

    public static interface Delegate {
        public Context getActionBarThemedContext();

        public Drawable getThemeUpIndicator();

        public boolean isNavigationVisible();

        public void setActionBarDescription(int var1);

        public void setActionBarUpIndicator(Drawable var1, int var2);
    }

    public static interface DelegateProvider {
        @Nullable
        public Delegate getDrawerToggleDelegate();
    }

    static class DrawerArrowDrawableToggle
    extends DrawerArrowDrawable
    implements DrawerToggle {
        private final Activity mActivity;

        public DrawerArrowDrawableToggle(Activity activity, Context context) {
            super(context);
            this.mActivity = activity;
        }

        @Override
        public float getPosition() {
            return super.getProgress();
        }

        @Override
        boolean isLayoutRtl() {
            if (ViewCompat.getLayoutDirection(this.mActivity.getWindow().getDecorView()) == 1) {
                return true;
            }
            return false;
        }

        /*
         * Enabled aggressive block sorting
         */
        @Override
        public void setPosition(float f2) {
            if (f2 == 1.0f) {
                this.setVerticalMirror(true);
            } else if (f2 == 0.0f) {
                this.setVerticalMirror(false);
            }
            super.setProgress(f2);
        }
    }

    static interface DrawerToggle {
        public float getPosition();

        public void setPosition(float var1);
    }

    static class DummyDelegate
    implements Delegate {
        final Activity mActivity;

        DummyDelegate(Activity activity) {
            this.mActivity = activity;
        }

        @Override
        public Context getActionBarThemedContext() {
            return this.mActivity;
        }

        @Override
        public Drawable getThemeUpIndicator() {
            return null;
        }

        @Override
        public boolean isNavigationVisible() {
            return true;
        }

        @Override
        public void setActionBarDescription(int n2) {
        }

        @Override
        public void setActionBarUpIndicator(Drawable drawable2, int n2) {
        }
    }

    static class HoneycombDelegate
    implements Delegate {
        final Activity mActivity;
        ActionBarDrawerToggleHoneycomb.SetIndicatorInfo mSetIndicatorInfo;

        private HoneycombDelegate(Activity activity) {
            this.mActivity = activity;
        }

        /* synthetic */ HoneycombDelegate(Activity activity, ActionBarDrawerToggle$1 actionBarDrawerToggle$1) {
            this(activity);
        }

        @Override
        public Context getActionBarThemedContext() {
            ActionBar actionBar = this.mActivity.getActionBar();
            if (actionBar != null) {
                return actionBar.getThemedContext();
            }
            return this.mActivity;
        }

        @Override
        public Drawable getThemeUpIndicator() {
            return ActionBarDrawerToggleHoneycomb.getThemeUpIndicator(this.mActivity);
        }

        @Override
        public boolean isNavigationVisible() {
            ActionBar actionBar = this.mActivity.getActionBar();
            if (actionBar != null && (actionBar.getDisplayOptions() & 4) != 0) {
                return true;
            }
            return false;
        }

        @Override
        public void setActionBarDescription(int n2) {
            this.mSetIndicatorInfo = ActionBarDrawerToggleHoneycomb.setActionBarDescription(this.mSetIndicatorInfo, this.mActivity, n2);
        }

        @Override
        public void setActionBarUpIndicator(Drawable drawable2, int n2) {
            this.mActivity.getActionBar().setDisplayShowHomeEnabled(true);
            this.mSetIndicatorInfo = ActionBarDrawerToggleHoneycomb.setActionBarUpIndicator(this.mSetIndicatorInfo, this.mActivity, drawable2, n2);
            this.mActivity.getActionBar().setDisplayShowHomeEnabled(false);
        }
    }

    static class JellybeanMr2Delegate
    implements Delegate {
        final Activity mActivity;

        private JellybeanMr2Delegate(Activity activity) {
            this.mActivity = activity;
        }

        /* synthetic */ JellybeanMr2Delegate(Activity activity, ActionBarDrawerToggle$1 actionBarDrawerToggle$1) {
            this(activity);
        }

        @Override
        public Context getActionBarThemedContext() {
            ActionBar actionBar = this.mActivity.getActionBar();
            if (actionBar != null) {
                return actionBar.getThemedContext();
            }
            return this.mActivity;
        }

        @Override
        public Drawable getThemeUpIndicator() {
            TypedArray typedArray = this.getActionBarThemedContext().obtainStyledAttributes(null, new int[]{16843531}, 16843470, 0);
            Drawable drawable2 = typedArray.getDrawable(0);
            typedArray.recycle();
            return drawable2;
        }

        @Override
        public boolean isNavigationVisible() {
            ActionBar actionBar = this.mActivity.getActionBar();
            if (actionBar != null && (actionBar.getDisplayOptions() & 4) != 0) {
                return true;
            }
            return false;
        }

        @Override
        public void setActionBarDescription(int n2) {
            ActionBar actionBar = this.mActivity.getActionBar();
            if (actionBar != null) {
                actionBar.setHomeActionContentDescription(n2);
            }
        }

        @Override
        public void setActionBarUpIndicator(Drawable drawable2, int n2) {
            ActionBar actionBar = this.mActivity.getActionBar();
            if (actionBar != null) {
                actionBar.setHomeAsUpIndicator(drawable2);
                actionBar.setHomeActionContentDescription(n2);
            }
        }
    }

    static class ToolbarCompatDelegate
    implements Delegate {
        final CharSequence mDefaultContentDescription;
        final Drawable mDefaultUpIndicator;
        final Toolbar mToolbar;

        ToolbarCompatDelegate(Toolbar toolbar) {
            this.mToolbar = toolbar;
            this.mDefaultUpIndicator = toolbar.getNavigationIcon();
            this.mDefaultContentDescription = toolbar.getNavigationContentDescription();
        }

        @Override
        public Context getActionBarThemedContext() {
            return this.mToolbar.getContext();
        }

        @Override
        public Drawable getThemeUpIndicator() {
            return this.mDefaultUpIndicator;
        }

        @Override
        public boolean isNavigationVisible() {
            return true;
        }

        @Override
        public void setActionBarDescription(int n2) {
            if (n2 == 0) {
                this.mToolbar.setNavigationContentDescription(this.mDefaultContentDescription);
                return;
            }
            this.mToolbar.setNavigationContentDescription(n2);
        }

        @Override
        public void setActionBarUpIndicator(Drawable drawable2, int n2) {
            this.mToolbar.setNavigationIcon(drawable2);
            this.setActionBarDescription(n2);
        }
    }

}

