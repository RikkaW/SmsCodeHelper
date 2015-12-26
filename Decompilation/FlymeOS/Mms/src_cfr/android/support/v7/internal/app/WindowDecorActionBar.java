/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.Dialog
 *  android.app.FragmentManager
 *  android.app.FragmentTransaction
 *  android.content.Context
 *  android.content.res.Configuration
 *  android.content.res.Resources
 *  android.content.res.Resources$Theme
 *  android.content.res.TypedArray
 *  android.graphics.drawable.Drawable
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.util.AttributeSet
 *  android.util.TypedValue
 *  android.view.ContextThemeWrapper
 *  android.view.LayoutInflater
 *  android.view.Menu
 *  android.view.MenuInflater
 *  android.view.MenuItem
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.Window
 *  android.view.animation.AnimationUtils
 *  android.view.animation.Interpolator
 *  android.widget.SpinnerAdapter
 *  java.lang.Class
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 */
package android.support.v7.internal.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.ViewPropertyAnimatorUpdateListener;
import android.support.v7.app.ActionBar;
import android.support.v7.appcompat.R;
import android.support.v7.internal.app.NavItemSelectedListener;
import android.support.v7.internal.app.WindowDecorActionBar$1;
import android.support.v7.internal.app.WindowDecorActionBar$2;
import android.support.v7.internal.app.WindowDecorActionBar$3;
import android.support.v7.internal.app.WindowDecorActionBar$ActionModeImpl$1;
import android.support.v7.internal.view.ActionBarPolicy;
import android.support.v7.internal.view.SupportMenuInflater;
import android.support.v7.internal.view.ViewPropertyAnimatorCompatSet;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.internal.view.menu.MenuPopupHelper;
import android.support.v7.internal.view.menu.SubMenuBuilder;
import android.support.v7.internal.widget.ActionBarContainer;
import android.support.v7.internal.widget.ActionBarContextView;
import android.support.v7.internal.widget.ActionBarOverlayLayout;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.support.v7.internal.widget.DecorToolbar;
import android.support.v7.internal.widget.ScrollingTabContainerView;
import android.support.v7.internal.widget.TintManager;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.SpinnerAdapter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class WindowDecorActionBar
extends ActionBar
implements ActionBarOverlayLayout.ActionBarVisibilityCallback {
    static final /* synthetic */ boolean $assertionsDisabled;
    private static final boolean ALLOW_SHOW_HIDE_ANIMATIONS;
    private static final int CONTEXT_DISPLAY_NORMAL = 0;
    private static final int CONTEXT_DISPLAY_SPLIT = 1;
    private static final int INVALID_POSITION = -1;
    private static final String TAG = "WindowDecorActionBar";
    ActionModeImpl mActionMode;
    private Activity mActivity;
    private ActionBarContainer mContainerView;
    private boolean mContentAnimations = true;
    private View mContentView;
    private Context mContext;
    private int mContextDisplayMode;
    private ActionBarContextView mContextView;
    private int mCurWindowVisibility = 0;
    private ViewPropertyAnimatorCompatSet mCurrentShowAnim;
    private DecorToolbar mDecorToolbar;
    ActionMode mDeferredDestroyActionMode;
    ActionMode.Callback mDeferredModeDestroyCallback;
    private Dialog mDialog;
    private boolean mDisplayHomeAsUpSet;
    private int mDuration;
    private boolean mHasEmbeddedTabs;
    private boolean mHiddenByApp;
    private boolean mHiddenBySystem;
    final ViewPropertyAnimatorListener mHideListener;
    boolean mHideOnContentScroll;
    private boolean mLastMenuVisibility;
    private ArrayList<ActionBar.OnMenuVisibilityListener> mMenuVisibilityListeners = new ArrayList();
    private boolean mNowShowing = true;
    private ActionBarOverlayLayout mOverlayLayout;
    private int mSavedTabPosition = -1;
    private TabImpl mSelectedTab;
    private boolean mShowHideAnimationEnabled;
    final ViewPropertyAnimatorListener mShowListener;
    private boolean mShowingForMode;
    private ActionBarContainer mSplitView;
    private ScrollingTabContainerView mTabScrollView;
    private ArrayList<TabImpl> mTabs = new ArrayList();
    private Context mThemedContext;
    private TintManager mTintManager;
    final ViewPropertyAnimatorUpdateListener mUpdateListener;
    private boolean mforceShowTab;

    /*
     * Enabled aggressive block sorting
     */
    static {
        boolean bl2 = true;
        boolean bl3 = !WindowDecorActionBar.class.desiredAssertionStatus();
        $assertionsDisabled = bl3;
        bl3 = Build.VERSION.SDK_INT >= 14 ? bl2 : false;
        ALLOW_SHOW_HIDE_ANIMATIONS = bl3;
    }

    public WindowDecorActionBar(Activity activity, boolean bl2) {
        this.mHideListener = new WindowDecorActionBar$1(this);
        this.mShowListener = new WindowDecorActionBar$2(this);
        this.mUpdateListener = new WindowDecorActionBar$3(this);
        this.mDuration = 250;
        this.mActivity = activity;
        activity = activity.getWindow().getDecorView();
        this.init((View)activity);
        if (!bl2) {
            this.mContentView = activity.findViewById(16908290);
        }
    }

    public WindowDecorActionBar(Dialog dialog) {
        this.mHideListener = new WindowDecorActionBar$1(this);
        this.mShowListener = new WindowDecorActionBar$2(this);
        this.mUpdateListener = new WindowDecorActionBar$3(this);
        this.mDuration = 250;
        this.mDialog = dialog;
        this.init(dialog.getWindow().getDecorView());
    }

    public WindowDecorActionBar(View view) {
        this.mHideListener = new WindowDecorActionBar$1(this);
        this.mShowListener = new WindowDecorActionBar$2(this);
        this.mUpdateListener = new WindowDecorActionBar$3(this);
        this.mDuration = 250;
        if (!$assertionsDisabled && !view.isInEditMode()) {
            throw new AssertionError();
        }
        this.init(view);
    }

    static /* synthetic */ boolean access$000(WindowDecorActionBar windowDecorActionBar) {
        return windowDecorActionBar.mContentAnimations;
    }

    static /* synthetic */ View access$100(WindowDecorActionBar windowDecorActionBar) {
        return windowDecorActionBar.mContentView;
    }

    static /* synthetic */ ActionBarContainer access$200(WindowDecorActionBar windowDecorActionBar) {
        return windowDecorActionBar.mContainerView;
    }

    static /* synthetic */ ActionBarContainer access$300(WindowDecorActionBar windowDecorActionBar) {
        return windowDecorActionBar.mSplitView;
    }

    static /* synthetic */ ViewPropertyAnimatorCompatSet access$402(WindowDecorActionBar windowDecorActionBar, ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet) {
        windowDecorActionBar.mCurrentShowAnim = viewPropertyAnimatorCompatSet;
        return viewPropertyAnimatorCompatSet;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void animateToMultiChoiceMode(boolean bl2) {
        ActionBarContextView actionBarContextView = this.mContextView;
        int n2 = bl2 ? 0 : 8;
        actionBarContextView.multiChoiceMenuViewAnimateToVisibility(n2);
    }

    /*
     * Enabled aggressive block sorting
     */
    private static boolean checkShowingFlags(boolean bl2, boolean bl3, boolean bl4) {
        if (bl4 || !bl2 && !bl3) {
            return true;
        }
        return false;
    }

    private void cleanupTabs() {
        if (this.mSelectedTab != null) {
            this.selectTab(null);
        }
        this.mTabs.clear();
        if (this.mTabScrollView != null) {
            this.mTabScrollView.removeAllTabs();
        }
        this.mSavedTabPosition = -1;
    }

    private void configureTab(ActionBar.Tab tab, int n2) {
        tab = (TabImpl)tab;
        ActionBar.TabListener tabListener = tab.getCallback();
        ActionBar.TabListenerSDK tabListenerSDK = tab.getCallbackSDK();
        if (tabListener == null && tabListenerSDK == null) {
            throw new IllegalStateException("Action Bar Tab must have a Callback");
        }
        tab.setPosition(n2);
        this.mTabs.add(n2, (Object)tab);
        int n3 = this.mTabs.size();
        ++n2;
        while (n2 < n3) {
            ((TabImpl)this.mTabs.get(n2)).setPosition(n2);
            ++n2;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void ensureTabsExist() {
        if (this.mTabScrollView != null) {
            return;
        }
        ScrollingTabContainerView scrollingTabContainerView = new ScrollingTabContainerView(this.mContext);
        scrollingTabContainerView.setId(R.id.mz_action_bar_tab_scroll_view);
        if (this.mHasEmbeddedTabs) {
            scrollingTabContainerView.setVisibility(0);
            this.mDecorToolbar.setEmbeddedTabView(scrollingTabContainerView);
            scrollingTabContainerView.showAtToolbar(true);
        } else {
            if (this.getNavigationMode() == 2) {
                scrollingTabContainerView.setVisibility(0);
                if (this.mOverlayLayout != null) {
                    ViewCompat.requestApplyInsets((View)this.mOverlayLayout);
                }
            } else {
                scrollingTabContainerView.setVisibility(8);
            }
            this.mContainerView.setTabContainer(scrollingTabContainerView);
            scrollingTabContainerView.showAtToolbar(false);
        }
        this.mTabScrollView = scrollingTabContainerView;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private DecorToolbar getDecorToolbar(View object) {
        if (object instanceof DecorToolbar) {
            return (DecorToolbar)object;
        }
        if (object instanceof Toolbar) {
            return ((Toolbar)((Object)object)).getWrapper();
        }
        if ("Can't make a decor toolbar out of " + object != null) {
            object = object.getClass().getSimpleName();
            do {
                throw new IllegalStateException((String)object);
                break;
            } while (true);
        }
        object = "null";
        throw new IllegalStateException((String)object);
    }

    private void hideForActionMode() {
        if (this.mShowingForMode) {
            this.mShowingForMode = false;
            if (this.mOverlayLayout != null) {
                this.mOverlayLayout.setShowingForActionMode(false);
            }
            this.updateVisibility(false);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void init(View object) {
        this.mOverlayLayout = (ActionBarOverlayLayout)object.findViewById(R.id.decor_content_parent);
        if (this.mOverlayLayout != null) {
            this.mOverlayLayout.setActionBarVisibilityCallback(this);
        }
        this.mDecorToolbar = this.getDecorToolbar(object.findViewById(R.id.action_bar));
        this.mContextView = (ActionBarContextView)object.findViewById(R.id.action_context_bar);
        this.mContainerView = (ActionBarContainer)object.findViewById(R.id.action_bar_container);
        this.mSplitView = (ActionBarContainer)object.findViewById(R.id.split_action_bar);
        if (this.mDecorToolbar == null || this.mContextView == null || this.mContainerView == null) {
            throw new IllegalStateException(this.getClass().getSimpleName() + " can only be used " + "with a compatible window decor layout");
        }
        this.mContext = this.mDecorToolbar.getContext();
        int n2 = this.mDecorToolbar.isSplit() ? 1 : 0;
        this.mContextDisplayMode = n2;
        int n3 = this.mDecorToolbar.getDisplayOptions();
        n2 = (n3 & 4) != 0 ? 1 : 0;
        if (n2 != 0) {
            this.mDisplayHomeAsUpSet = true;
        }
        boolean bl2 = (object = ActionBarPolicy.get(this.mContext)).enableHomeButtonByDefault() || n2 != 0;
        this.setHomeButtonEnabled(bl2);
        bl2 = (n3 & 64) != 0;
        this.mforceShowTab = bl2;
        bl2 = object.hasEmbeddedTabs() || this.mforceShowTab;
        this.setHasEmbeddedTabs(bl2);
        object = this.mContext.obtainStyledAttributes(null, R.styleable.ActionBar, R.attr.actionBarStyle, 0);
        if (object.getBoolean(R.styleable.ActionBar_hideOnContentScroll, false)) {
            this.setHideOnContentScrollEnabled(true);
        }
        if ((n2 = object.getDimensionPixelSize(R.styleable.ActionBar_elevation, 0)) != 0) {
            this.setElevation(n2);
        }
        object.recycle();
    }

    /*
     * Enabled aggressive block sorting
     */
    private void setHasEmbeddedTabs(boolean bl2) {
        boolean bl3 = true;
        this.mHasEmbeddedTabs = bl2;
        if (!this.mHasEmbeddedTabs) {
            this.mDecorToolbar.setEmbeddedTabView(null);
            this.mContainerView.setTabContainer(this.mTabScrollView);
            if (this.mTabScrollView != null) {
                this.mTabScrollView.showAtToolbar(false);
            }
        } else {
            this.mContainerView.setTabContainer(null);
            this.mDecorToolbar.setEmbeddedTabView(this.mTabScrollView);
            if (this.mTabScrollView != null) {
                this.mTabScrollView.showAtToolbar(true);
            }
        }
        boolean bl4 = this.getNavigationMode() == 2;
        if (this.mTabScrollView != null) {
            if (bl4) {
                this.mTabScrollView.setVisibility(0);
                if (this.mOverlayLayout != null) {
                    ViewCompat.requestApplyInsets((View)this.mOverlayLayout);
                }
            } else {
                this.mTabScrollView.setVisibility(8);
            }
        }
        Object object = this.mDecorToolbar;
        bl2 = !this.mHasEmbeddedTabs && bl4;
        object.setCollapsible(bl2);
        object = this.mOverlayLayout;
        bl2 = !this.mHasEmbeddedTabs && bl4 ? bl3 : false;
        object.setHasNonEmbeddedTabs(bl2);
    }

    private void showForActionMode() {
        if (!this.mShowingForMode) {
            this.mShowingForMode = true;
            if (this.mOverlayLayout != null) {
                this.mOverlayLayout.setShowingForActionMode(true);
            }
            this.updateVisibility(false);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void updateVisibility(boolean bl2) {
        if (WindowDecorActionBar.checkShowingFlags(this.mHiddenByApp, this.mHiddenBySystem, this.mShowingForMode)) {
            if (this.mNowShowing) return;
            {
                this.mNowShowing = true;
                this.doShow(bl2);
                return;
            }
        } else {
            if (!this.mNowShowing) return;
            {
                this.mNowShowing = false;
                this.doHide(bl2);
                return;
            }
        }
    }

    @Override
    public void addOnMenuVisibilityListener(ActionBar.OnMenuVisibilityListener onMenuVisibilityListener) {
        this.mMenuVisibilityListeners.add((Object)onMenuVisibilityListener);
    }

    @Override
    public void addTab(ActionBar.Tab tab) {
        this.addTab(tab, this.mTabs.isEmpty());
    }

    @Override
    public void addTab(ActionBar.Tab tab, int n2) {
        this.addTab(tab, n2, this.mTabs.isEmpty());
    }

    @Override
    public void addTab(ActionBar.Tab tab, int n2, boolean bl2) {
        this.ensureTabsExist();
        this.mTabScrollView.addTab(tab, n2, bl2);
        this.configureTab(tab, n2);
        if (bl2) {
            this.selectTab(tab);
        }
    }

    @Override
    public void addTab(ActionBar.Tab tab, boolean bl2) {
        this.ensureTabsExist();
        this.mTabScrollView.addTab(tab, bl2);
        this.configureTab(tab, this.mTabs.size());
        if (bl2) {
            this.selectTab(tab);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void animateToMode(boolean bl2) {
        int n2 = 0;
        if (bl2) {
            this.showForActionMode();
        } else {
            this.hideForActionMode();
        }
        Object object = this.mDecorToolbar;
        int n3 = bl2 ? 8 : 0;
        object.animateToVisibility(n3);
        object = this.mContextView;
        n3 = bl2 ? n2 : 8;
        object.animateToVisibility(n3);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void animateToMode(boolean bl2, boolean bl3) {
        int n2 = 0;
        if (bl3) {
            this.showForActionMode();
        } else {
            this.hideForActionMode();
        }
        Object object = this.mDecorToolbar;
        int n3 = bl2 ? 8 : 0;
        object.animateToVisibility(n3);
        object = this.mContextView;
        n3 = bl2 ? n2 : 8;
        object.animateToVisibility(n3);
    }

    @Override
    public boolean collapseActionView() {
        if (this.mDecorToolbar != null && this.mDecorToolbar.hasExpandedActionView()) {
            this.mDecorToolbar.collapseActionView();
            return true;
        }
        return false;
    }

    void completeDeferredDestroyActionMode() {
        if (this.mDeferredModeDestroyCallback != null) {
            this.mDeferredModeDestroyCallback.onDestroyActionMode(this.mDeferredDestroyActionMode);
            this.mDeferredDestroyActionMode = null;
            this.mDeferredModeDestroyCallback = null;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public void dispatchMenuVisibilityChanged(boolean bl2) {
        if (bl2 == this.mLastMenuVisibility) {
            return;
        }
        this.mLastMenuVisibility = bl2;
        int n2 = this.mMenuVisibilityListeners.size();
        int n3 = 0;
        while (n3 < n2) {
            ((ActionBar.OnMenuVisibilityListener)this.mMenuVisibilityListeners.get(n3)).onMenuVisibilityChanged(bl2);
            ++n3;
        }
    }

    public void doHide(boolean bl2) {
        if (this.mCurrentShowAnim != null) {
            this.mCurrentShowAnim.cancel();
        }
        if (this.mCurWindowVisibility == 0 && ALLOW_SHOW_HIDE_ANIMATIONS && (this.mShowHideAnimationEnabled || bl2)) {
            Object object;
            float f2;
            ViewCompat.setAlpha((View)this.mContainerView, 1.0f);
            this.mContainerView.setTransitioning(true);
            ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet = new ViewPropertyAnimatorCompatSet();
            float f3 = f2 = (float)(- this.mContainerView.getHeight());
            if (bl2) {
                object = new int[2];
                Object object2 = object;
                object2[0] = 0;
                object2[1] = 0;
                this.mContainerView.getLocationInWindow((int[])object);
                f3 = f2 - (Object)object[1];
            }
            object = ViewCompat.animate((View)this.mContainerView).translationY(f3);
            object.setUpdateListener(this.mUpdateListener);
            viewPropertyAnimatorCompatSet.play((ViewPropertyAnimatorCompat)object);
            if (this.mContentAnimations && this.mContentView != null) {
                viewPropertyAnimatorCompatSet.play(ViewCompat.animate(this.mContentView).translationY(f3));
            }
            if (this.mSplitView != null && this.mSplitView.getVisibility() == 0) {
                ViewCompat.setAlpha((View)this.mSplitView, 1.0f);
                viewPropertyAnimatorCompatSet.play(ViewCompat.animate((View)this.mSplitView).translationY(this.mSplitView.getHeight()));
            }
            viewPropertyAnimatorCompatSet.setInterpolator(AnimationUtils.loadInterpolator((Context)this.mContext, (int)17432581));
            viewPropertyAnimatorCompatSet.setDuration(this.mDuration);
            viewPropertyAnimatorCompatSet.setListener(this.mHideListener);
            this.mCurrentShowAnim = viewPropertyAnimatorCompatSet;
            viewPropertyAnimatorCompatSet.start();
            return;
        }
        this.mHideListener.onAnimationEnd(null);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void doShow(boolean bl2) {
        if (this.mCurrentShowAnim != null) {
            this.mCurrentShowAnim.cancel();
        }
        this.mContainerView.setVisibility(0);
        if (this.mCurWindowVisibility == 0 && ALLOW_SHOW_HIDE_ANIMATIONS && (this.mShowHideAnimationEnabled || bl2)) {
            Object object;
            float f2;
            ViewCompat.setTranslationY((View)this.mContainerView, 0.0f);
            float f3 = f2 = (float)(- this.mContainerView.getHeight());
            if (bl2) {
                object = new int[2];
                Object object2 = object;
                object2[0] = 0;
                object2[1] = 0;
                this.mContainerView.getLocationInWindow((int[])object);
                f3 = f2 - (Object)object[1];
            }
            ViewCompat.setTranslationY((View)this.mContainerView, f3);
            object = new ViewPropertyAnimatorCompatSet();
            ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = ViewCompat.animate((View)this.mContainerView).translationY(0.0f);
            viewPropertyAnimatorCompat.setUpdateListener(this.mUpdateListener);
            object.play(viewPropertyAnimatorCompat);
            if (this.mContentAnimations && this.mContentView != null) {
                ViewCompat.setTranslationY(this.mContentView, f3);
                object.play(ViewCompat.animate(this.mContentView).translationY(0.0f));
            }
            if (this.mSplitView != null) {
                ViewCompat.setTranslationY((View)this.mSplitView, this.mSplitView.getHeight());
                this.mSplitView.setVisibility(0);
                object.play(ViewCompat.animate((View)this.mSplitView).translationY(0.0f));
            }
            object.setInterpolator(AnimationUtils.loadInterpolator((Context)this.mContext, (int)17432582));
            object.setDuration(this.mDuration);
            object.setListener(this.mShowListener);
            this.mCurrentShowAnim = object;
            object.start();
        } else {
            ViewCompat.setAlpha((View)this.mContainerView, 1.0f);
            ViewCompat.setTranslationY((View)this.mContainerView, 0.0f);
            if (this.mContentAnimations && this.mContentView != null) {
                ViewCompat.setTranslationY(this.mContentView, 0.0f);
            }
            if (this.mSplitView != null && this.mContextDisplayMode == 1) {
                ViewCompat.setAlpha((View)this.mSplitView, 1.0f);
                ViewCompat.setTranslationY((View)this.mSplitView, 0.0f);
                this.mSplitView.setVisibility(0);
            }
            this.mShowListener.onAnimationEnd(null);
        }
        if (this.mOverlayLayout != null) {
            ViewCompat.requestApplyInsets((View)this.mOverlayLayout);
        }
    }

    @Override
    public void enableContentAnimations(boolean bl2) {
        this.mContentAnimations = bl2;
    }

    @Override
    public aqy getActionBarTabContainer() {
        return (aqy)this.mContainerView.getTabContainer();
    }

    @Override
    public ArrayList getAllTabs() {
        return this.mTabs;
    }

    @Override
    public View getCustomView() {
        return this.mDecorToolbar.getCustomView();
    }

    @Override
    public int getDisplayOptions() {
        return this.mDecorToolbar.getDisplayOptions();
    }

    @Override
    public float getElevation() {
        return ViewCompat.getElevation((View)this.mContainerView);
    }

    @Override
    public int getHeight() {
        return this.mContainerView.getHeight();
    }

    @Override
    public int getHideOffset() {
        return this.mOverlayLayout.getActionBarHideOffset();
    }

    @Override
    public int getNavigationItemCount() {
        switch (this.mDecorToolbar.getNavigationMode()) {
            default: {
                return 0;
            }
            case 2: {
                return this.mTabs.size();
            }
            case 1: 
        }
        return this.mDecorToolbar.getDropdownItemCount();
    }

    @Override
    public int getNavigationMode() {
        return this.mDecorToolbar.getNavigationMode();
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public int getSelectedNavigationIndex() {
        switch (this.mDecorToolbar.getNavigationMode()) {
            case 2: {
                if (this.mSelectedTab != null) {
                    return this.mSelectedTab.getPosition();
                }
            }
            default: {
                return -1;
            }
            case 1: 
        }
        return this.mDecorToolbar.getDropdownSelectedPosition();
    }

    @Override
    public ActionBar.Tab getSelectedTab() {
        return this.mSelectedTab;
    }

    @Override
    public CharSequence getSubtitle() {
        return this.mDecorToolbar.getSubtitle();
    }

    @Override
    public ActionBar.Tab getTabAt(int n2) {
        return (ActionBar.Tab)this.mTabs.get(n2);
    }

    @Override
    public int getTabCount() {
        return this.mTabs.size();
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public Context getThemedContext() {
        if (this.mThemedContext != null) return this.mThemedContext;
        TypedValue typedValue = new TypedValue();
        this.mContext.getTheme().resolveAttribute(R.attr.actionBarWidgetTheme, typedValue, true);
        int n2 = typedValue.resourceId;
        if (n2 != 0) {
            this.mThemedContext = new ContextThemeWrapper(this.mContext, n2);
            return this.mThemedContext;
        }
        this.mThemedContext = this.mContext;
        return this.mThemedContext;
    }

    TintManager getTintManager() {
        if (this.mTintManager == null) {
            this.mTintManager = TintManager.get(this.mContext);
        }
        return this.mTintManager;
    }

    @Override
    public CharSequence getTitle() {
        return this.mDecorToolbar.getTitle();
    }

    public boolean hasIcon() {
        return this.mDecorToolbar.hasIcon();
    }

    public boolean hasLogo() {
        return this.mDecorToolbar.hasLogo();
    }

    @Override
    public void hide() {
        if (!this.mHiddenByApp) {
            this.mHiddenByApp = true;
            this.updateVisibility(false);
        }
    }

    @Override
    public void hide(int n2) {
        this.mDuration = n2;
        this.hide();
    }

    @Override
    public void hideForSystem() {
        if (!this.mHiddenBySystem) {
            this.mHiddenBySystem = true;
            this.updateVisibility(true);
        }
    }

    @Override
    public boolean isHideOnContentScrollEnabled() {
        return this.mOverlayLayout.isHideOnContentScrollEnabled();
    }

    @Override
    public boolean isShowing() {
        int n2 = this.getHeight();
        if (this.mNowShowing && (n2 == 0 || this.getHideOffset() < n2)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isTitleTruncated() {
        if (this.mDecorToolbar != null && this.mDecorToolbar.isTitleTruncated()) {
            return true;
        }
        return false;
    }

    @Override
    public ActionBar.Tab newTab() {
        return new TabImpl();
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void onConfigurationChanged(Configuration configuration) {
        boolean bl2 = ActionBarPolicy.get(this.mContext).hasEmbeddedTabs() || this.mforceShowTab;
        this.setHasEmbeddedTabs(bl2);
    }

    @Override
    public void onContentScrollStarted() {
        if (this.mCurrentShowAnim != null) {
            this.mCurrentShowAnim.cancel();
            this.mCurrentShowAnim = null;
        }
    }

    @Override
    public void onContentScrollStopped() {
    }

    @Override
    public void onWindowVisibilityChanged(int n2) {
        this.mCurWindowVisibility = n2;
    }

    @Override
    public void removeAllTabs() {
        this.cleanupTabs();
    }

    @Override
    public void removeOnMenuVisibilityListener(ActionBar.OnMenuVisibilityListener onMenuVisibilityListener) {
        this.mMenuVisibilityListeners.remove((Object)onMenuVisibilityListener);
    }

    @Override
    public void removeTab(ActionBar.Tab tab) {
        this.removeTabAt(tab.getPosition());
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    @Override
    public void removeTabAt(int n2) {
        if (this.mTabScrollView == null) {
            return;
        }
        int n3 = this.mSelectedTab != null ? this.mSelectedTab.getPosition() : this.mSavedTabPosition;
        this.mTabScrollView.removeTabAt(n2);
        TabImpl tabImpl = (TabImpl)this.mTabs.remove(n2);
        if (tabImpl != null) {
            tabImpl.setPosition(-1);
        }
        int n4 = this.mTabs.size();
        for (int i2 = n2; i2 < n4; ++i2) {
            ((TabImpl)this.mTabs.get(i2)).setPosition(i2);
        }
        if (n3 != n2) return;
        tabImpl = this.mTabs.isEmpty() ? null : (TabImpl)this.mTabs.get(Math.max((int)0, (int)(n2 - 1)));
        this.selectTab(tabImpl);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void selectTab(ActionBar.Tab tab) {
        android.app.FragmentTransaction fragmentTransaction = null;
        int n2 = -1;
        if (this.getNavigationMode() != 2) {
            n2 = tab != null ? tab.getPosition() : -1;
            this.mSavedTabPosition = n2;
            return;
        } else {
            FragmentTransaction fragmentTransaction2 = this.mActivity instanceof FragmentActivity && !this.mDecorToolbar.getViewGroup().isInEditMode() ? ((FragmentActivity)this.mActivity).getSupportFragmentManager().beginTransaction().disallowAddToBackStack() : null;
            if (!this.mDecorToolbar.getViewGroup().isInEditMode()) {
                fragmentTransaction = this.mActivity.getFragmentManager().beginTransaction().disallowAddToBackStack();
            }
            if (this.mSelectedTab == tab) {
                if (this.mSelectedTab != null) {
                    if (this.mSelectedTab.getCallback() != null) {
                        this.mSelectedTab.getCallback().onTabReselected(this.mSelectedTab, fragmentTransaction2);
                    } else {
                        this.mSelectedTab.getCallbackSDK().onTabReselected(this.mSelectedTab, fragmentTransaction);
                    }
                    this.mTabScrollView.animateToTab(tab.getPosition());
                }
            } else {
                ScrollingTabContainerView scrollingTabContainerView = this.mTabScrollView;
                if (tab != null) {
                    n2 = tab.getPosition();
                }
                scrollingTabContainerView.setTabSelected(n2);
                if (this.mSelectedTab != null) {
                    if (this.mSelectedTab.getCallback() != null) {
                        this.mSelectedTab.getCallback().onTabUnselected(this.mSelectedTab, fragmentTransaction2);
                    } else {
                        this.mSelectedTab.getCallbackSDK().onTabUnselected(this.mSelectedTab, fragmentTransaction);
                    }
                }
                this.mSelectedTab = (TabImpl)tab;
                if (this.mSelectedTab != null) {
                    if (this.mSelectedTab.getCallback() != null) {
                        this.mSelectedTab.getCallback().onTabSelected(this.mSelectedTab, fragmentTransaction2);
                    } else {
                        this.mSelectedTab.getCallbackSDK().onTabSelected(this.mSelectedTab, fragmentTransaction);
                    }
                }
            }
            if (fragmentTransaction2 == null || fragmentTransaction2.isEmpty()) return;
            {
                fragmentTransaction2.commit();
                return;
            }
        }
    }

    @Override
    public void setBackgroundDrawable(Drawable drawable2) {
        this.mContainerView.setPrimaryBackground(drawable2);
    }

    @Override
    public void setCustomView(int n2) {
        this.setCustomView(LayoutInflater.from((Context)this.getThemedContext()).inflate(n2, this.mDecorToolbar.getViewGroup(), false));
    }

    @Override
    public void setCustomView(View view) {
        this.mDecorToolbar.setCustomView(view);
    }

    @Override
    public void setCustomView(View view, ActionBar.LayoutParams layoutParams) {
        view.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
        this.mDecorToolbar.setCustomView(view);
    }

    @Override
    public void setDefaultDisplayHomeAsUpEnabled(boolean bl2) {
        if (!this.mDisplayHomeAsUpSet) {
            this.setDisplayHomeAsUpEnabled(bl2);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void setDisplayHomeAsUpEnabled(boolean bl2) {
        int n2 = bl2 ? 4 : 0;
        this.setDisplayOptions(n2, 4);
    }

    @Override
    public void setDisplayOptions(int n2) {
        if ((n2 & 4) != 0) {
            this.mDisplayHomeAsUpSet = true;
        }
        this.mDecorToolbar.setDisplayOptions(n2);
    }

    @Override
    public void setDisplayOptions(int n2, int n3) {
        int n4 = this.mDecorToolbar.getDisplayOptions();
        if ((n3 & 4) != 0) {
            this.mDisplayHomeAsUpSet = true;
        }
        this.mDecorToolbar.setDisplayOptions(n4 & ~ n3 | n2 & n3);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void setDisplayShowCustomEnabled(boolean bl2) {
        int n2 = bl2 ? 16 : 0;
        this.setDisplayOptions(n2, 16);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void setDisplayShowHomeEnabled(boolean bl2) {
        int n2 = bl2 ? 2 : 0;
        this.setDisplayOptions(n2, 2);
    }

    @Override
    public void setDisplayShowTabEnabled(boolean bl2) {
        this.mforceShowTab = bl2;
        this.setHasEmbeddedTabs(bl2);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void setDisplayShowTitleEnabled(boolean bl2) {
        int n2 = bl2 ? 8 : 0;
        this.setDisplayOptions(n2, 8);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void setDisplayUseLogoEnabled(boolean bl2) {
        int n2 = bl2 ? 1 : 0;
        this.setDisplayOptions(n2, 1);
    }

    @Override
    public void setElevation(float f2) {
        ViewCompat.setElevation((View)this.mContainerView, f2);
        if (this.mSplitView != null) {
            ViewCompat.setElevation((View)this.mSplitView, f2);
        }
    }

    @Override
    public void setHideOffset(int n2) {
        if (n2 != 0 && !this.mOverlayLayout.isInOverlayMode()) {
            throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to set a non-zero hide offset");
        }
        this.mOverlayLayout.setActionBarHideOffset(n2);
    }

    @Override
    public void setHideOnContentScrollEnabled(boolean bl2) {
        if (bl2 && !this.mOverlayLayout.isInOverlayMode()) {
            throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
        }
        this.mHideOnContentScroll = bl2;
        this.mOverlayLayout.setHideOnContentScrollEnabled(bl2);
    }

    @Override
    public void setHomeActionContentDescription(int n2) {
        this.mDecorToolbar.setNavigationContentDescription(n2);
    }

    @Override
    public void setHomeActionContentDescription(CharSequence charSequence) {
        this.mDecorToolbar.setNavigationContentDescription(charSequence);
    }

    @Override
    public void setHomeAsUpIndicator(int n2) {
        this.mDecorToolbar.setNavigationIcon(n2);
    }

    @Override
    public void setHomeAsUpIndicator(Drawable drawable2) {
        this.mDecorToolbar.setNavigationIcon(drawable2);
    }

    @Override
    public void setHomeButtonEnabled(boolean bl2) {
        this.mDecorToolbar.setHomeButtonEnabled(bl2);
    }

    @Override
    public void setIcon(int n2) {
        this.mDecorToolbar.setIcon(n2);
    }

    @Override
    public void setIcon(Drawable drawable2) {
        this.mDecorToolbar.setIcon(drawable2);
    }

    @Override
    public void setListNavigationCallbacks(SpinnerAdapter spinnerAdapter, ActionBar.OnNavigationListener onNavigationListener) {
        this.mDecorToolbar.setDropdownParams(spinnerAdapter, new NavItemSelectedListener(onNavigationListener));
    }

    @Override
    public void setLogo(int n2) {
        this.mDecorToolbar.setLogo(n2);
    }

    @Override
    public void setLogo(Drawable drawable2) {
        this.mDecorToolbar.setLogo(drawable2);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void setNavigationMode(int n2) {
        boolean bl2 = true;
        int n3 = this.mDecorToolbar.getNavigationMode();
        switch (n3) {
            case 2: {
                this.mSavedTabPosition = this.getSelectedNavigationIndex();
                this.selectTab(null);
                this.mTabScrollView.setVisibility(8);
                break;
            }
        }
        if (n3 != n2 && !this.mHasEmbeddedTabs && this.mOverlayLayout != null) {
            ViewCompat.requestApplyInsets((View)this.mOverlayLayout);
        }
        this.mDecorToolbar.setNavigationMode(n2);
        switch (n2) {
            case 2: {
                this.ensureTabsExist();
                this.mTabScrollView.setVisibility(0);
                if (this.mSavedTabPosition == -1) break;
                this.setSelectedNavigationItem(this.mSavedTabPosition);
                this.mSavedTabPosition = -1;
            }
        }
        Object object = this.mDecorToolbar;
        boolean bl3 = n2 == 2 && !this.mHasEmbeddedTabs;
        object.setCollapsible(bl3);
        object = this.mOverlayLayout;
        bl3 = n2 == 2 && !this.mHasEmbeddedTabs ? bl2 : false;
        object.setHasNonEmbeddedTabs(bl3);
    }

    @Override
    public void setScrollTabAllowCollapse(boolean bl2) {
        aqy aqy2 = (aqy)this.mContainerView.getTabContainer();
        if (aqy2 != null) {
            aqy2.setAllowCollapse(bl2);
        }
    }

    @Override
    public void setScrollTabCollapseButtonClickListener(View.OnClickListener onClickListener) {
        aqy aqy2 = (aqy)this.mContainerView.getTabContainer();
        if (aqy2 != null) {
            aqy2.setCollapseButtonClickListener(onClickListener);
        }
    }

    @Override
    public void setSelectedNavigationItem(int n2) {
        switch (this.mDecorToolbar.getNavigationMode()) {
            default: {
                throw new IllegalStateException("setSelectedNavigationIndex not valid for current navigation mode");
            }
            case 2: {
                this.selectTab((ActionBar.Tab)this.mTabs.get(n2));
                return;
            }
            case 1: 
        }
        this.mDecorToolbar.setDropdownSelectedPosition(n2);
    }

    @Override
    public void setShowHideAnimationEnabled(boolean bl2) {
        this.mShowHideAnimationEnabled = bl2;
        if (!bl2 && this.mCurrentShowAnim != null) {
            this.mCurrentShowAnim.cancel();
        }
    }

    @Override
    public void setSplitBackgroundDrawable(Drawable drawable2) {
        if (this.mSplitView != null) {
            this.mSplitView.setSplitBackground(drawable2);
        }
    }

    @Override
    public void setStackedBackgroundDrawable(Drawable drawable2) {
        this.mContainerView.setStackedBackground(drawable2);
    }

    @Override
    public void setSubtitle(int n2) {
        this.setSubtitle(this.mContext.getString(n2));
    }

    @Override
    public void setSubtitle(CharSequence charSequence) {
        this.mDecorToolbar.setSubtitle(charSequence);
    }

    @Override
    public void setTabIndicatorDrawable(Drawable drawable2) {
        if (this.mTabScrollView != null) {
            this.mTabScrollView.setIndicatorDrawable(drawable2);
        }
    }

    @Override
    public void setTabScrolled(int n2, float f2, int n3) {
        if (this.mTabScrollView != null) {
            this.mTabScrollView.setScrollPosition(n2, f2, true);
        }
    }

    @Override
    public void setTitle(int n2) {
        this.setTitle(this.mContext.getString(n2));
    }

    @Override
    public void setTitle(CharSequence charSequence) {
        this.mDecorToolbar.setTitle(charSequence);
    }

    @Override
    public void setUiOptions(int n2) {
        this.mOverlayLayout.setUiOptions(n2);
    }

    @Override
    public void setWindowTitle(CharSequence charSequence) {
        this.mDecorToolbar.setWindowTitle(charSequence);
    }

    @Override
    public void show() {
        if (this.mHiddenByApp) {
            this.mHiddenByApp = false;
            this.updateVisibility(false);
        }
    }

    @Override
    public void show(int n2) {
        this.mDuration = n2;
        this.show();
    }

    @Override
    public void showForSystem() {
        if (this.mHiddenBySystem) {
            this.mHiddenBySystem = false;
            this.updateVisibility(true);
        }
    }

    @Override
    public ActionMode startActionMode(ActionMode.Callback object) {
        if (this.mActionMode != null) {
            this.mActionMode.finish();
        }
        this.mOverlayLayout.setHideOnContentScrollEnabled(false);
        this.mContextView.killMode();
        object = new ActionModeImpl(this.mContextView.getContext(), (ActionMode.Callback)object);
        if (object.dispatchOnCreate()) {
            object.invalidate();
            this.mContextView.initForMode((ActionMode)object);
            this.animateToMode(true);
            if (this.mSplitView != null && this.mContextDisplayMode == 1 && this.mSplitView.getVisibility() != 0) {
                this.mSplitView.setVisibility(0);
                if (this.mOverlayLayout != null) {
                    ViewCompat.requestApplyInsets((View)this.mOverlayLayout);
                }
            }
            this.mContextView.sendAccessibilityEvent(32);
            this.mActionMode = object;
            return object;
        }
        return null;
    }

    @Override
    public ActionMode startMultiChoiceActionMode(ActionMode.Callback object) {
        if (this.mActionMode != null) {
            this.mActionMode.finish();
        }
        this.mOverlayLayout.setHideOnContentScrollEnabled(false);
        this.mContextView.killMode();
        object = new ActionModeImpl(this.mContextView.getContext(), (ActionMode.Callback)object);
        if (object.dispatchOnCreate()) {
            object.invalidate();
            this.mContextView.setSplitView((ViewGroup)this.mSplitView);
            this.mContextView.initForMultiChoiceMode((ActionMode)object);
            this.animateToMode(true, object.isShowActionBar());
            if (object.isAnimateToShowMenu()) {
                this.animateToMultiChoiceMode(true);
            }
            if (this.mSplitView != null && this.mSplitView.getVisibility() != 0) {
                this.mSplitView.setVisibility(0);
                if (this.mOverlayLayout != null) {
                    ViewCompat.requestApplyInsets((View)this.mOverlayLayout);
                }
            }
            this.mContextView.sendAccessibilityEvent(32);
            object.setIsMultiChoiceMode(true);
            this.mActionMode = object;
            return object;
        }
        return null;
    }

    public class ActionModeImpl
    extends ActionMode
    implements MenuBuilder.Callback {
        private final Context mActionModeContext;
        private ActionMode.BackPressedListener mBackPressedListener;
        private ActionMode.Callback mCallback;
        private WeakReference<View> mCustomView;
        private boolean mIsMultiChoiceMode;
        private boolean mIsShowActionBar;
        private final MenuBuilder mMenu;

        public ActionModeImpl(Context context, ActionMode.Callback callback) {
            this.mBackPressedListener = new WindowDecorActionBar$ActionModeImpl$1(this);
            this.mIsShowActionBar = true;
            this.mActionModeContext = context;
            this.mCallback = callback;
            this.mMenu = new MenuBuilder(context).setDefaultShowAsAction(1);
            this.mMenu.setCallback(this);
            this.setBackPressListener(this.mBackPressedListener);
        }

        public boolean dispatchOnCreate() {
            this.mMenu.stopDispatchingItemsChanged();
            try {
                boolean bl2 = this.mCallback.onCreateActionMode(this, this.mMenu);
                return bl2;
            }
            finally {
                this.mMenu.startDispatchingItemsChanged();
            }
        }

        /*
         * Enabled aggressive block sorting
         */
        @Override
        public void finish() {
            if (WindowDecorActionBar.this.mActionMode != this) {
                return;
            }
            if (!WindowDecorActionBar.checkShowingFlags(WindowDecorActionBar.this.mHiddenByApp, WindowDecorActionBar.this.mHiddenBySystem, false) && this.isShowActionBar()) {
                WindowDecorActionBar.this.mDeferredDestroyActionMode = this;
                WindowDecorActionBar.this.mDeferredModeDestroyCallback = this.mCallback;
            } else {
                this.mCallback.onDestroyActionMode(this);
            }
            this.mCallback = null;
            WindowDecorActionBar.this.animateToMode(false);
            if (this.mIsMultiChoiceMode && this.isAnimateToShowMenu()) {
                WindowDecorActionBar.this.animateToMultiChoiceMode(false);
            }
            WindowDecorActionBar.this.mContextView.closeMode();
            WindowDecorActionBar.this.mDecorToolbar.getViewGroup().sendAccessibilityEvent(32);
            WindowDecorActionBar.this.mOverlayLayout.setHideOnContentScrollEnabled(WindowDecorActionBar.this.mHideOnContentScroll);
            WindowDecorActionBar.this.mActionMode = null;
        }

        @Override
        public View getCustomView() {
            if (this.mCustomView != null) {
                return this.mCustomView.get();
            }
            return null;
        }

        @Override
        public Menu getMenu() {
            return this.mMenu;
        }

        @Override
        public MenuInflater getMenuInflater() {
            return new SupportMenuInflater(this.mActionModeContext);
        }

        @Override
        public CharSequence getSubtitle() {
            return WindowDecorActionBar.this.mContextView.getSubtitle();
        }

        @Override
        public CharSequence getTitle() {
            return WindowDecorActionBar.this.mContextView.getTitle();
        }

        @Override
        public void invalidate() {
            if (WindowDecorActionBar.this.mActionMode != this) {
                return;
            }
            this.mMenu.stopDispatchingItemsChanged();
            try {
                this.mCallback.onPrepareActionMode(this, this.mMenu);
                return;
            }
            finally {
                this.mMenu.startDispatchingItemsChanged();
            }
        }

        @Override
        public boolean isShowActionBar() {
            return this.mIsShowActionBar;
        }

        @Override
        public boolean isTitleOptional() {
            return WindowDecorActionBar.this.mContextView.isTitleOptional();
        }

        public void onCloseMenu(MenuBuilder menuBuilder, boolean bl2) {
        }

        public void onCloseSubMenu(SubMenuBuilder subMenuBuilder) {
        }

        @Override
        public boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
            if (this.mCallback != null) {
                return this.mCallback.onActionItemClicked(this, menuItem);
            }
            return false;
        }

        @Override
        public void onMenuModeChange(MenuBuilder menuBuilder) {
            if (this.mCallback == null) {
                return;
            }
            this.invalidate();
            WindowDecorActionBar.this.mContextView.showOverflowMenu();
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
            boolean bl2 = true;
            if (this.mCallback == null) {
                return false;
            }
            if (!subMenuBuilder.hasVisibleItems()) return bl2;
            new MenuPopupHelper(WindowDecorActionBar.this.getThemedContext(), subMenuBuilder).show();
            return true;
        }

        @Override
        public void setCustomView(View view) {
            WindowDecorActionBar.this.mContextView.setCustomView(view);
            this.mCustomView = new WeakReference<View>(view);
        }

        public void setIsMultiChoiceMode(boolean bl2) {
            this.mIsMultiChoiceMode = bl2;
        }

        @Override
        public void setShowActionBar(boolean bl2) {
            this.mIsShowActionBar = bl2;
        }

        @Override
        public void setSubtitle(int n2) {
            this.setSubtitle(WindowDecorActionBar.this.mContext.getResources().getString(n2));
        }

        @Override
        public void setSubtitle(CharSequence charSequence) {
            WindowDecorActionBar.this.mContextView.setSubtitle(charSequence);
        }

        @Override
        public void setTitle(int n2) {
            this.setTitle(WindowDecorActionBar.this.mContext.getResources().getString(n2));
        }

        @Override
        public void setTitle(CharSequence charSequence) {
            WindowDecorActionBar.this.mContextView.setTitle(charSequence);
        }

        @Override
        public void setTitleOptionalHint(boolean bl2) {
            super.setTitleOptionalHint(bl2);
            WindowDecorActionBar.this.mContextView.setTitleOptional(bl2);
        }
    }

    public class TabImpl
    extends ActionBar.Tab {
        private ActionBar.TabListener mCallback;
        private ActionBar.TabListenerSDK mCallbackSDK;
        private CharSequence mContentDesc;
        private View mCustomView;
        private Drawable mIcon;
        private boolean mIsEnabled;
        private int mPosition;
        private Object mTag;
        private CharSequence mText;

        public TabImpl() {
            this.mPosition = -1;
            this.mIsEnabled = true;
        }

        @Override
        public ActionBar.TabListener getCallback() {
            return this.mCallback;
        }

        @Override
        public ActionBar.TabListenerSDK getCallbackSDK() {
            return this.mCallbackSDK;
        }

        @Override
        public CharSequence getContentDescription() {
            return this.mContentDesc;
        }

        @Override
        public View getCustomView() {
            return this.mCustomView;
        }

        @Override
        public Drawable getIcon() {
            return this.mIcon;
        }

        @Override
        public int getPosition() {
            return this.mPosition;
        }

        @Override
        public Object getTag() {
            return this.mTag;
        }

        @Override
        public CharSequence getText() {
            return this.mText;
        }

        @Override
        public boolean isEnabled() {
            return this.mIsEnabled;
        }

        @Override
        public void select() {
            WindowDecorActionBar.this.selectTab(this);
        }

        @Override
        public ActionBar.Tab setContentDescription(int n2) {
            return this.setContentDescription(WindowDecorActionBar.this.mContext.getResources().getText(n2));
        }

        @Override
        public ActionBar.Tab setContentDescription(CharSequence charSequence) {
            this.mContentDesc = charSequence;
            if (this.mPosition >= 0) {
                WindowDecorActionBar.this.mTabScrollView.updateTab(this.mPosition);
            }
            return this;
        }

        @Override
        public ActionBar.Tab setCustomView(int n2) {
            return this.setCustomView(LayoutInflater.from((Context)WindowDecorActionBar.this.getThemedContext()).inflate(n2, null));
        }

        @Override
        public ActionBar.Tab setCustomView(View view) {
            this.mCustomView = view;
            if (this.mPosition >= 0) {
                WindowDecorActionBar.this.mTabScrollView.updateTab(this.mPosition);
            }
            return this;
        }

        @Override
        public ActionBar.Tab setEnabled(boolean bl2) {
            this.mIsEnabled = bl2;
            if (this.mPosition >= 0) {
                WindowDecorActionBar.this.mTabScrollView.updateTab(this.mPosition);
            }
            return this;
        }

        @Override
        public ActionBar.Tab setIcon(int n2) {
            return this.setIcon(WindowDecorActionBar.this.getTintManager().getDrawable(n2));
        }

        @Override
        public ActionBar.Tab setIcon(Drawable drawable2) {
            this.mIcon = drawable2;
            if (this.mPosition >= 0) {
                WindowDecorActionBar.this.mTabScrollView.updateTab(this.mPosition);
            }
            return this;
        }

        public void setPosition(int n2) {
            this.mPosition = n2;
        }

        @Override
        public ActionBar.Tab setTabListener(ActionBar.TabListener tabListener) {
            this.mCallback = tabListener;
            return this;
        }

        @Override
        public ActionBar.Tab setTabListenerSDK(ActionBar.TabListenerSDK tabListenerSDK) {
            this.mCallbackSDK = tabListenerSDK;
            return this;
        }

        @Override
        public ActionBar.Tab setTag(Object object) {
            this.mTag = object;
            return this;
        }

        @Override
        public ActionBar.Tab setText(int n2) {
            return this.setText(WindowDecorActionBar.this.mContext.getResources().getText(n2));
        }

        @Override
        public ActionBar.Tab setText(CharSequence charSequence) {
            this.mText = charSequence;
            if (this.mPosition >= 0) {
                WindowDecorActionBar.this.mTabScrollView.updateTab(this.mPosition);
            }
            return this;
        }
    }

}

