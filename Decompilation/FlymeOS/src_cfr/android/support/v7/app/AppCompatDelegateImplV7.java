/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.Dialog
 *  android.content.Context
 *  android.content.pm.ApplicationInfo
 *  android.content.res.Configuration
 *  android.content.res.Resources
 *  android.content.res.Resources$Theme
 *  android.content.res.TypedArray
 *  android.graphics.Rect
 *  android.graphics.drawable.Drawable
 *  android.media.AudioManager
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.Bundle
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  android.text.TextUtils
 *  android.util.AndroidRuntimeException
 *  android.util.AttributeSet
 *  android.util.DisplayMetrics
 *  android.util.Log
 *  android.util.TypedValue
 *  android.view.KeyCharacterMap
 *  android.view.KeyEvent
 *  android.view.LayoutInflater
 *  android.view.LayoutInflater$Factory
 *  android.view.Menu
 *  android.view.MenuItem
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.ViewConfiguration
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewGroup$MarginLayoutParams
 *  android.view.ViewParent
 *  android.view.Window
 *  android.view.Window$Callback
 *  android.view.WindowManager
 *  android.view.WindowManager$LayoutParams
 *  android.widget.FrameLayout
 *  android.widget.ListAdapter
 *  android.widget.PopupWindow
 *  android.widget.TextView
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.reflect.Field
 */
package android.support.v7.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.NavUtils;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewConfigurationCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.app.AppCompatDelegateImplBase;
import android.support.v7.app.AppCompatDelegateImplV7$1;
import android.support.v7.app.AppCompatDelegateImplV7$2;
import android.support.v7.app.AppCompatDelegateImplV7$3;
import android.support.v7.app.AppCompatDelegateImplV7$4;
import android.support.v7.app.AppCompatDelegateImplV7$PanelFeatureState$SavedState$1;
import android.support.v7.appcompat.R;
import android.support.v7.internal.app.AppCompatViewInflater;
import android.support.v7.internal.app.ToolbarActionBar;
import android.support.v7.internal.app.WindowDecorActionBar;
import android.support.v7.internal.view.ContextThemeWrapper;
import android.support.v7.internal.view.StandaloneActionMode;
import android.support.v7.internal.view.menu.ListMenuPresenter;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.internal.view.menu.MenuPresenter;
import android.support.v7.internal.view.menu.MenuView;
import android.support.v7.internal.widget.ActionBarContextView;
import android.support.v7.internal.widget.ContentFrameLayout;
import android.support.v7.internal.widget.DecorContentParent;
import android.support.v7.internal.widget.FitWindowsViewGroup;
import android.support.v7.internal.widget.TintManager;
import android.support.v7.internal.widget.ViewStubCompat;
import android.support.v7.internal.widget.ViewUtils;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import android.widget.TextView;
import java.lang.reflect.Field;

class AppCompatDelegateImplV7
extends AppCompatDelegateImplBase
implements LayoutInflaterFactory,
MenuBuilder.Callback {
    private ActionMenuPresenterCallback mActionMenuPresenterCallback;
    ActionMode mActionMode;
    PopupWindow mActionModePopup;
    ActionBarContextView mActionModeView;
    private AppCompatViewInflater mAppCompatViewInflater;
    private boolean mClosingActionMenu;
    private DecorContentParent mDecorContentParent;
    private boolean mEnableDefaultActionBarUp;
    private boolean mFeatureIndeterminateProgress;
    private boolean mFeatureProgress;
    private int mInvalidatePanelMenuFeatures;
    private boolean mInvalidatePanelMenuPosted;
    private final Runnable mInvalidatePanelMenuRunnable;
    private PanelMenuPresenterCallback mPanelMenuPresenterCallback;
    private PanelFeatureState[] mPanels;
    private PanelFeatureState mPreparedPanel;
    Runnable mShowActionModePopup;
    private View mStatusGuard;
    private ViewGroup mSubDecor;
    private boolean mSubDecorInstalled;
    private Rect mTempRect1;
    private Rect mTempRect2;
    private TextView mTitleView;
    private boolean mTransStatusBarInFlyme3;
    private ViewGroup mWindowDecor;

    AppCompatDelegateImplV7(Context context, Window window, AppCompatCallback appCompatCallback) {
        super(context, window, appCompatCallback);
        this.mInvalidatePanelMenuRunnable = new AppCompatDelegateImplV7$1(this);
    }

    static /* synthetic */ int access$000(AppCompatDelegateImplV7 appCompatDelegateImplV7) {
        return appCompatDelegateImplV7.mInvalidatePanelMenuFeatures;
    }

    static /* synthetic */ int access$002(AppCompatDelegateImplV7 appCompatDelegateImplV7, int n2) {
        appCompatDelegateImplV7.mInvalidatePanelMenuFeatures = n2;
        return n2;
    }

    static /* synthetic */ void access$100(AppCompatDelegateImplV7 appCompatDelegateImplV7, int n2) {
        appCompatDelegateImplV7.doInvalidatePanelMenu(n2);
    }

    static /* synthetic */ boolean access$202(AppCompatDelegateImplV7 appCompatDelegateImplV7, boolean bl2) {
        appCompatDelegateImplV7.mInvalidatePanelMenuPosted = bl2;
        return bl2;
    }

    static /* synthetic */ int access$300(AppCompatDelegateImplV7 appCompatDelegateImplV7, int n2) {
        return appCompatDelegateImplV7.updateStatusGuard(n2);
    }

    private void applyFixedSizeWindow(ContentFrameLayout contentFrameLayout) {
        contentFrameLayout.setDecorPadding(this.mWindowDecor.getPaddingLeft(), this.mWindowDecor.getPaddingTop(), this.mWindowDecor.getPaddingRight(), this.mWindowDecor.getPaddingBottom());
        TypedArray typedArray = this.mContext.obtainStyledAttributes(R.styleable.Theme);
        typedArray.getValue(R.styleable.Theme_windowMinWidthMajor, contentFrameLayout.getMinWidthMajor());
        typedArray.getValue(R.styleable.Theme_windowMinWidthMinor, contentFrameLayout.getMinWidthMinor());
        if (typedArray.hasValue(R.styleable.Theme_windowFixedWidthMajor)) {
            typedArray.getValue(R.styleable.Theme_windowFixedWidthMajor, contentFrameLayout.getFixedWidthMajor());
        }
        if (typedArray.hasValue(R.styleable.Theme_windowFixedWidthMinor)) {
            typedArray.getValue(R.styleable.Theme_windowFixedWidthMinor, contentFrameLayout.getFixedWidthMinor());
        }
        if (typedArray.hasValue(R.styleable.Theme_windowFixedHeightMajor)) {
            typedArray.getValue(R.styleable.Theme_windowFixedHeightMajor, contentFrameLayout.getFixedHeightMajor());
        }
        if (typedArray.hasValue(R.styleable.Theme_windowFixedHeightMinor)) {
            typedArray.getValue(R.styleable.Theme_windowFixedHeightMinor, contentFrameLayout.getFixedHeightMinor());
        }
        typedArray.recycle();
        contentFrameLayout.requestLayout();
    }

    /*
     * Enabled aggressive block sorting
     */
    private void callOnPanelClosed(int n2, PanelFeatureState panelFeatureState, Menu menu) {
        PanelFeatureState panelFeatureState2 = panelFeatureState;
        Menu menu2 = menu;
        if (menu == null) {
            PanelFeatureState panelFeatureState3 = panelFeatureState;
            if (panelFeatureState == null) {
                panelFeatureState3 = panelFeatureState;
                if (n2 >= 0) {
                    panelFeatureState3 = panelFeatureState;
                    if (n2 < this.mPanels.length) {
                        panelFeatureState3 = this.mPanels[n2];
                    }
                }
            }
            panelFeatureState2 = panelFeatureState3;
            menu2 = menu;
            if (panelFeatureState3 != null) {
                menu2 = panelFeatureState3.menu;
                panelFeatureState2 = panelFeatureState3;
            }
        }
        if (panelFeatureState2 != null && !panelFeatureState2.isOpen || (panelFeatureState = this.getWindowCallback()) == null) {
            return;
        }
        panelFeatureState.onPanelClosed(n2, menu2);
    }

    private void checkCloseActionMenu(MenuBuilder menuBuilder) {
        if (this.mClosingActionMenu) {
            return;
        }
        this.mClosingActionMenu = true;
        this.mDecorContentParent.dismissPopups();
        Window.Callback callback = this.getWindowCallback();
        if (callback != null && !this.isDestroyed()) {
            callback.onPanelClosed(8, (Menu)menuBuilder);
        }
        this.mClosingActionMenu = false;
    }

    private void closePanel(int n2) {
        this.closePanel(this.getPanelState(n2, true), true);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void closePanel(PanelFeatureState panelFeatureState, boolean bl2) {
        if (bl2 && panelFeatureState.featureId == 0 && this.mDecorContentParent != null && this.mDecorContentParent.isOverflowMenuShowing()) {
            this.checkCloseActionMenu(panelFeatureState.menu);
            return;
        } else {
            boolean bl3 = panelFeatureState.isOpen;
            WindowManager windowManager = (WindowManager)this.mContext.getSystemService("window");
            if (windowManager != null && bl3 && panelFeatureState.decorView != null) {
                windowManager.removeView((View)panelFeatureState.decorView);
            }
            panelFeatureState.isPrepared = false;
            panelFeatureState.isHandled = false;
            panelFeatureState.isOpen = false;
            if (bl3 && bl2) {
                this.callOnPanelClosed(panelFeatureState.featureId, panelFeatureState, null);
            }
            panelFeatureState.shownPanelView = null;
            panelFeatureState.refreshDecorView = true;
            if (this.mPreparedPanel != panelFeatureState) return;
            {
                this.mPreparedPanel = null;
                return;
            }
        }
    }

    private void doInvalidatePanelMenu(int n2) {
        PanelFeatureState panelFeatureState = this.getPanelState(n2, true);
        if (panelFeatureState.menu != null) {
            Bundle bundle = new Bundle();
            panelFeatureState.menu.saveActionViewStates(bundle);
            if (bundle.size() > 0) {
                panelFeatureState.frozenActionViewState = bundle;
            }
            panelFeatureState.menu.stopDispatchingItemsChanged();
            panelFeatureState.menu.clear();
        }
        panelFeatureState.refreshMenuContent = true;
        panelFeatureState.refreshDecorView = true;
        if ((n2 == 8 || n2 == 0) && this.mDecorContentParent != null && (panelFeatureState = this.getPanelState(0, false)) != null) {
            panelFeatureState.isPrepared = false;
            this.preparePanel(panelFeatureState, null);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void ensureSubDecor() {
        if (!this.mSubDecorInstalled) {
            Object object = LayoutInflater.from((Context)this.mContext);
            if (!this.mWindowNoTitle) {
                if (this.mIsFloating) {
                    this.mSubDecor = (ViewGroup)object.inflate(R.layout.abc_dialog_title_material, null);
                    this.mOverlayActionBar = false;
                    this.mHasActionBar = false;
                } else if (this.mHasActionBar) {
                    object = new TypedValue();
                    this.mContext.getTheme().resolveAttribute(R.attr.actionBarTheme, (TypedValue)object, true);
                    object = object.resourceId != 0 ? new ContextThemeWrapper(this.mContext, object.resourceId) : this.mContext;
                    this.mSubDecor = (ViewGroup)LayoutInflater.from((Context)object).inflate(R.layout.abc_screen_toolbar, null);
                    this.mDecorContentParent = (DecorContentParent)this.mSubDecor.findViewById(R.id.decor_content_parent);
                    this.mDecorContentParent.setWindowCallback(this.getWindowCallback());
                    if (this.mOverlayActionBar) {
                        this.mDecorContentParent.initFeature(9);
                    }
                    if (this.mFeatureProgress) {
                        this.mDecorContentParent.initFeature(2);
                    }
                    if (this.mFeatureIndeterminateProgress) {
                        this.mDecorContentParent.initFeature(5);
                    }
                    this.mDecorContentParent.setUiOptions(this.mMzUiOptions);
                    this.mDecorContentParent.setTransStatusBarInFlyme3(this.mTransStatusBarInFlyme3);
                }
            } else {
                this.mSubDecor = this.mOverlayActionMode ? (ViewGroup)object.inflate(R.layout.abc_screen_simple_overlay_action_mode, null) : (ViewGroup)object.inflate(R.layout.abc_screen_simple, null);
                if (Build.VERSION.SDK_INT >= 21) {
                    ViewCompat.setOnApplyWindowInsetsListener((View)this.mSubDecor, new AppCompatDelegateImplV7$2(this));
                } else {
                    ((FitWindowsViewGroup)this.mSubDecor).setOnFitSystemWindowsListener(new AppCompatDelegateImplV7$3(this));
                }
            }
            if (this.mSubDecor == null) {
                throw new IllegalArgumentException("AppCompat does not support the current theme features");
            }
            if (this.mDecorContentParent == null) {
                this.mTitleView = (TextView)this.mSubDecor.findViewById(R.id.title);
            }
            ViewUtils.makeOptionalFitsSystemWindows((View)this.mSubDecor);
            Object object2 = (ViewGroup)this.mWindow.findViewById(16908290);
            object = (ContentFrameLayout)this.mSubDecor.findViewById(R.id.action_bar_activity_content);
            while (object2.getChildCount() > 0) {
                View view = object2.getChildAt(0);
                object2.removeViewAt(0);
                object.addView(view);
            }
            this.mWindow.setContentView((View)this.mSubDecor);
            object2.setId(-1);
            object.setId(16908290);
            if (object2 instanceof FrameLayout) {
                ((FrameLayout)object2).setForeground(null);
            }
            if (!TextUtils.isEmpty((CharSequence)(object2 = this.getTitle())) && this.mDecorContentParent != null) {
                this.onTitleChanged((CharSequence)object2);
            }
            this.applyFixedSizeWindow((ContentFrameLayout)((Object)object));
            this.onSubDecorInstalled(this.mSubDecor);
            this.mSubDecorInstalled = true;
            object = this.getPanelState(0, false);
            if (!(this.isDestroyed() || object != null && object.menu != null)) {
                this.invalidatePanelMenu(8);
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private PanelFeatureState findMenuPanel(Menu menu) {
        PanelFeatureState[] arrpanelFeatureState = this.mPanels;
        int n2 = arrpanelFeatureState != null ? arrpanelFeatureState.length : 0;
        int n3 = 0;
        while (n3 < n2) {
            PanelFeatureState panelFeatureState = arrpanelFeatureState[n3];
            if (panelFeatureState != null && panelFeatureState.menu == menu) {
                return panelFeatureState;
            }
            ++n3;
        }
        return null;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private PanelFeatureState getPanelState(int var1_1, boolean var2_2) {
        var4_3 = this.mPanels;
        if (var4_3 == null) ** GOTO lbl-1000
        var3_4 = var4_3;
        if (var4_3.length <= var1_1) lbl-1000: // 2 sources:
        {
            var3_4 = new PanelFeatureState[var1_1 + 1];
            if (var4_3 != null) {
                System.arraycopy((Object)var4_3, (int)0, (Object)var3_4, (int)0, (int)var4_3.length);
            }
            this.mPanels = var3_4;
        }
        if ((var4_3 = var3_4[var1_1]) != null) return var4_3;
        var3_4[var1_1] = var4_3 = new PanelFeatureState(var1_1);
        return var4_3;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private boolean initializePanelContent(PanelFeatureState panelFeatureState) {
        if (panelFeatureState.createdPanelView != null) {
            panelFeatureState.shownPanelView = panelFeatureState.createdPanelView;
            return true;
        }
        if (panelFeatureState.menu == null) {
            return false;
        }
        if (this.mPanelMenuPresenterCallback == null) {
            this.mPanelMenuPresenterCallback = new PanelMenuPresenterCallback();
        }
        panelFeatureState.shownPanelView = (View)panelFeatureState.getListMenuView(this.mPanelMenuPresenterCallback);
        if (panelFeatureState.shownPanelView == null) return false;
        return true;
    }

    private boolean initializePanelDecor(PanelFeatureState panelFeatureState) {
        panelFeatureState.setStyle(this.getActionBarThemedContext());
        panelFeatureState.decorView = new ListMenuDecorView(panelFeatureState.listPresenterContext);
        panelFeatureState.gravity = 81;
        return true;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private boolean initializePanelMenu(PanelFeatureState var1_1) {
        var4_2 = this.mContext;
        if (var1_1.featureId != 0 && var1_1.featureId != 8 || this.mDecorContentParent == null) ** GOTO lbl-1000
        var5_3 = new TypedValue();
        var6_4 = var4_2.getTheme();
        var6_4.resolveAttribute(R.attr.actionBarTheme, var5_3, true);
        var2_5 = null;
        if (var5_3.resourceId != 0) {
            var2_5 = var4_2.getResources().newTheme();
            var2_5.setTo(var6_4);
            var2_5.applyStyle(var5_3.resourceId, true);
            var2_5.resolveAttribute(R.attr.actionBarWidgetTheme, var5_3, true);
        } else {
            var6_4.resolveAttribute(R.attr.actionBarWidgetTheme, var5_3, true);
        }
        var3_6 = var2_5;
        if (var5_3.resourceId != 0) {
            var3_6 = var2_5;
            if (var2_5 == null) {
                var3_6 = var4_2.getResources().newTheme();
                var3_6.setTo(var6_4);
            }
            var3_6.applyStyle(var5_3.resourceId, true);
        }
        if (var3_6 != null) {
            var2_5 = new ContextThemeWrapper(var4_2, 0);
            var2_5.getTheme().setTo((Resources.Theme)var3_6);
        } else lbl-1000: // 2 sources:
        {
            var2_5 = var4_2;
        }
        var2_5 = new MenuBuilder((Context)var2_5);
        var2_5.setCallback(this);
        var1_1.setMenu((MenuBuilder)var2_5);
        return true;
    }

    private void invalidatePanelMenu(int n2) {
        this.mInvalidatePanelMenuFeatures |= 1 << n2;
        if (!this.mInvalidatePanelMenuPosted && this.mWindowDecor != null) {
            ViewCompat.postOnAnimation((View)this.mWindowDecor, this.mInvalidatePanelMenuRunnable);
            this.mInvalidatePanelMenuPosted = true;
        }
    }

    private boolean onKeyDownPanel(int n2, KeyEvent keyEvent) {
        if (keyEvent.getRepeatCount() == 0) {
            PanelFeatureState panelFeatureState = this.getPanelState(n2, true);
            if (!panelFeatureState.isOpen) {
                return this.preparePanel(panelFeatureState, keyEvent);
            }
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private void onKeyUpPanel(int n2, KeyEvent keyEvent) {
        boolean bl2;
        boolean bl3 = true;
        if (this.mActionMode != null) {
            return;
        }
        PanelFeatureState panelFeatureState = this.getPanelState(n2, true);
        if (n2 == 0 && this.mDecorContentParent != null && this.mDecorContentParent.canShowOverflowMenu() && !ViewConfigurationCompat.hasPermanentMenuKey(ViewConfiguration.get((Context)this.mContext))) {
            if (!this.mDecorContentParent.isOverflowMenuShowing()) {
                if (this.isDestroyed()) return;
                if (!this.preparePanel(panelFeatureState, keyEvent)) return;
                bl2 = this.mDecorContentParent.showOverflowMenu();
            } else {
                bl2 = this.mDecorContentParent.hideOverflowMenu();
            }
        } else if (panelFeatureState.isOpen || panelFeatureState.isHandled) {
            bl2 = panelFeatureState.isOpen;
            this.closePanel(panelFeatureState, true);
        } else {
            if (!panelFeatureState.isPrepared) return;
            if (panelFeatureState.refreshMenuContent) {
                panelFeatureState.isPrepared = false;
                bl2 = this.preparePanel(panelFeatureState, keyEvent);
            } else {
                bl2 = true;
            }
            if (!bl2) return;
            this.openPanel(panelFeatureState, keyEvent);
            bl2 = bl3;
        }
        if (!bl2) return;
        keyEvent = (AudioManager)this.mContext.getSystemService("audio");
        if (keyEvent != null) {
            keyEvent.playSoundEffect(0);
            return;
        }
        Log.w((String)"AppCompatDelegate", (String)"Couldn't get audio manager");
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private void openPanel(PanelFeatureState var1_1, KeyEvent var2_2) {
        var5_3 = -1;
        if (var1_1.isOpen != false) return;
        if (this.isDestroyed()) {
            return;
        }
        if (var1_1.featureId == 0) {
            var6_4 = this.mContext;
            var3_5 = (var6_4.getResources().getConfiguration().screenLayout & 15) == 4 ? 1 : 0;
            var4_6 = var6_4.getApplicationInfo().targetSdkVersion >= 11;
            if (var3_5 != 0) {
                if (var4_6 != false) return;
            }
        }
        if ((var6_4 = this.getWindowCallback()) != null && !var6_4.onMenuOpened(var1_1.featureId, (Menu)var1_1.menu)) {
            this.closePanel(var1_1, true);
            return;
        }
        var6_4 = (WindowManager)this.mContext.getSystemService("window");
        if (var6_4 == null) return;
        if (this.preparePanel(var1_1, var2_2) == false) return;
        if (var1_1.decorView != null && !var1_1.refreshDecorView) ** GOTO lbl39
        if (var1_1.decorView == null) {
            if (this.initializePanelDecor(var1_1) == false) return;
            if (var1_1.decorView == null) return;
        } else if (var1_1.refreshDecorView && var1_1.decorView.getChildCount() > 0) {
            var1_1.decorView.removeAllViews();
        }
        if (this.initializePanelContent(var1_1) == false) return;
        if (var1_1.hasPanelItems() == false) return;
        var2_2 = var1_1.shownPanelView.getLayoutParams();
        if (var2_2 == null) {
            var2_2 = new ViewGroup.LayoutParams(-2, -2);
        }
        var3_5 = var1_1.background;
        var1_1.decorView.setBackgroundResource(var3_5);
        var7_7 = var1_1.shownPanelView.getParent();
        if (var7_7 != null && var7_7 instanceof ViewGroup) {
            ((ViewGroup)var7_7).removeView(var1_1.shownPanelView);
        }
        var1_1.decorView.addView(var1_1.shownPanelView, (ViewGroup.LayoutParams)var2_2);
        if (!var1_1.shownPanelView.hasFocus()) {
            var1_1.shownPanelView.requestFocus();
        }
        var3_5 = -2;
        ** GOTO lbl43
lbl39: // 1 sources:
        if (var1_1.createdPanelView == null || (var2_2 = var1_1.createdPanelView.getLayoutParams()) == null) ** GOTO lbl-1000
        var3_5 = var5_3;
        if (var2_2.width != -1) lbl-1000: // 2 sources:
        {
            var3_5 = -2;
        }
lbl43: // 4 sources:
        var1_1.isHandled = false;
        var2_2 = new WindowManager.LayoutParams(var3_5, -2, var1_1.x, var1_1.y, 1002, 8519680, -3);
        var2_2.gravity = var1_1.gravity;
        var2_2.windowAnimations = var1_1.windowAnimations;
        var6_4.addView((View)var1_1.decorView, (ViewGroup.LayoutParams)var2_2);
        var1_1.isOpen = true;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private boolean performPanelShortcut(PanelFeatureState var1_1, int var2_2, KeyEvent var3_3, int var4_4) {
        var6_5 = false;
        var5_6 = false;
        if (var3_3.isSystem()) {
            return var5_6;
        }
        if (var1_1.isPrepared) ** GOTO lbl-1000
        var5_6 = var6_5;
        if (this.preparePanel(var1_1, var3_3)) lbl-1000: // 2 sources:
        {
            var5_6 = var6_5;
            if (var1_1.menu != null) {
                var5_6 = var1_1.menu.performShortcut(var2_2, var3_3, var4_4);
            }
        }
        var6_5 = var5_6;
        if (var5_6 == false) return var6_5;
        var6_5 = var5_6;
        if ((var4_4 & 1) != 0) return var6_5;
        var6_5 = var5_6;
        if (this.mDecorContentParent != null) return var6_5;
        this.closePanel(var1_1, true);
        return var5_6;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private boolean preparePanel(PanelFeatureState panelFeatureState, KeyEvent keyEvent) {
        Window.Callback callback;
        if (this.isDestroyed()) {
            return false;
        }
        if (panelFeatureState.isPrepared) {
            return true;
        }
        if (this.mPreparedPanel != null && this.mPreparedPanel != panelFeatureState) {
            this.closePanel(this.mPreparedPanel, false);
        }
        if ((callback = this.getWindowCallback()) != null) {
            panelFeatureState.createdPanelView = callback.onCreatePanelView(panelFeatureState.featureId);
        }
        int n2 = panelFeatureState.featureId == 0 || panelFeatureState.featureId == 8 ? 1 : 0;
        if (n2 != 0 && this.mDecorContentParent != null) {
            this.mDecorContentParent.setMenuPrepared();
        }
        if (panelFeatureState.createdPanelView == null) {
            if (panelFeatureState.menu == null || panelFeatureState.refreshMenuContent) {
                if (panelFeatureState.menu == null) {
                    if (!this.initializePanelMenu(panelFeatureState)) return false;
                    if (panelFeatureState.menu == null) return false;
                }
                if (n2 != 0 && this.mDecorContentParent != null) {
                    if (this.mActionMenuPresenterCallback == null) {
                        this.mActionMenuPresenterCallback = new ActionMenuPresenterCallback();
                    }
                    this.mDecorContentParent.setMenu(panelFeatureState.menu, this.mActionMenuPresenterCallback);
                }
                panelFeatureState.menu.stopDispatchingItemsChanged();
                if (!callback.onCreatePanelMenu(panelFeatureState.featureId, (Menu)panelFeatureState.menu)) {
                    panelFeatureState.setMenu(null);
                    if (n2 == 0) return false;
                    if (this.mDecorContentParent == null) return false;
                    this.mDecorContentParent.setMenu(null, this.mActionMenuPresenterCallback);
                    return false;
                }
                panelFeatureState.refreshMenuContent = false;
            }
            panelFeatureState.menu.stopDispatchingItemsChanged();
            if (panelFeatureState.frozenActionViewState != null) {
                panelFeatureState.menu.restoreActionViewStates(panelFeatureState.frozenActionViewState);
                panelFeatureState.frozenActionViewState = null;
            }
            if (!callback.onPreparePanel(0, panelFeatureState.createdPanelView, (Menu)panelFeatureState.menu)) {
                if (n2 != 0 && this.mDecorContentParent != null) {
                    this.mDecorContentParent.setMenu(null, this.mActionMenuPresenterCallback);
                }
                panelFeatureState.menu.startDispatchingItemsChanged();
                return false;
            }
            n2 = keyEvent != null ? keyEvent.getDeviceId() : -1;
            boolean bl2 = KeyCharacterMap.load((int)n2).getKeyboardType() != 1;
            panelFeatureState.qwertyMode = bl2;
            panelFeatureState.menu.setQwertyMode(panelFeatureState.qwertyMode);
            panelFeatureState.menu.startDispatchingItemsChanged();
        }
        panelFeatureState.isPrepared = true;
        panelFeatureState.isHandled = false;
        this.mPreparedPanel = panelFeatureState;
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void reopenMenu(MenuBuilder object, boolean bl2) {
        if (this.mDecorContentParent != null && this.mDecorContentParent.canShowOverflowMenu() && (!ViewConfigurationCompat.hasPermanentMenuKey(ViewConfiguration.get((Context)this.mContext)) || this.mDecorContentParent.isOverflowMenuShowPending())) {
            object = this.getWindowCallback();
            if (!this.mDecorContentParent.isOverflowMenuShowing() || !bl2) {
                if (object == null || this.isDestroyed()) return;
                {
                    if (this.mInvalidatePanelMenuPosted && (this.mInvalidatePanelMenuFeatures & 1) != 0) {
                        this.mWindowDecor.removeCallbacks(this.mInvalidatePanelMenuRunnable);
                        this.mInvalidatePanelMenuRunnable.run();
                    }
                    PanelFeatureState panelFeatureState = this.getPanelState(0, true);
                    if (panelFeatureState.menu == null || panelFeatureState.refreshMenuContent || !object.onPreparePanel(0, panelFeatureState.createdPanelView, (Menu)panelFeatureState.menu)) return;
                    {
                        object.onMenuOpened(8, (Menu)panelFeatureState.menu);
                        this.mDecorContentParent.showOverflowMenu();
                        return;
                    }
                }
            } else {
                this.mDecorContentParent.hideOverflowMenu();
                if (this.isDestroyed()) return;
                {
                    object.onPanelClosed(8, (Menu)this.getPanelState((int)0, (boolean)true).menu);
                    return;
                }
            }
        }
        object = this.getPanelState(0, true);
        object.refreshDecorView = true;
        this.closePanel((PanelFeatureState)object, false);
        this.openPanel((PanelFeatureState)object, null);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private boolean setTransStatusBar(WindowManager.LayoutParams var1_1, boolean var2_3) {
        block3 : {
            try {
                var4_4 = var1_1.getClass().getDeclaredField("meizuFlags");
                var4_4.setAccessible(true);
                var3_5 = var4_4.getInt((Object)var1_1);
                if (!var2_3) break block3;
            }
            catch (Exception var1_2) {
                Log.e((String)"AppCompatDelegate", (String)("Can't set the status bar to be transparent, Caused by:" + var1_2.getMessage()));
                return false;
            }
            var3_5 = 64 | var3_5;
            ** GOTO lbl14
        }
        var3_5 &= -65;
lbl14: // 3 sources:
        var4_4.setInt((Object)var1_1, var3_5);
        return true;
    }

    private void throwFeatureRequestIfSubDecorInstalled() {
        if (this.mSubDecorInstalled) {
            throw new AndroidRuntimeException("Window feature must be requested before adding content");
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private int updateStatusGuard(int var1_1) {
        var3_2 = 1;
        var4_3 = 1;
        var5_4 = 0;
        if (this.mActionModeView == null || !(this.mActionModeView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) ** GOTO lbl50
        var6_5 = (ViewGroup.MarginLayoutParams)this.mActionModeView.getLayoutParams();
        if (!this.mActionModeView.isShown()) ** GOTO lbl29
        if (this.mTempRect1 == null) {
            this.mTempRect1 = new Rect();
            this.mTempRect2 = new Rect();
        }
        var7_6 = this.mTempRect1;
        var8_7 = this.mTempRect2;
        var7_6.set(0, var1_1, 0, 0);
        ViewUtils.computeFitSystemWindows((View)this.mSubDecor, var7_6, var8_7);
        var2_8 = var8_7.top == 0 ? var1_1 : 0;
        if (var6_5.topMargin == var2_8) ** GOTO lbl36
        var6_5.topMargin = var1_1;
        if (this.mStatusGuard == null) {
            this.mStatusGuard = new View(this.mContext);
            this.mStatusGuard.setBackgroundColor(this.mContext.getResources().getColor(R.color.abc_input_method_navigation_guard));
            this.mSubDecor.addView(this.mStatusGuard, -1, new ViewGroup.LayoutParams(-1, var1_1));
            var2_8 = 1;
        } else {
            var7_6 = this.mStatusGuard.getLayoutParams();
            if (var7_6.height != var1_1) {
                var7_6.height = var1_1;
                this.mStatusGuard.setLayoutParams((ViewGroup.LayoutParams)var7_6);
            }
            var2_8 = 1;
        }
        ** GOTO lbl37
lbl29: // 1 sources:
        if (var6_5.topMargin != 0) {
            var6_5.topMargin = 0;
            var2_8 = 0;
        } else {
            var3_2 = 0;
            var2_8 = 0;
        }
        ** GOTO lbl47
lbl36: // 1 sources:
        var2_8 = 0;
lbl37: // 3 sources:
        if (this.mStatusGuard == null) {
            var4_3 = 0;
        }
        var3_2 = var1_1;
        if (!this.mOverlayActionMode) {
            var3_2 = var1_1;
            if (var4_3 != 0) {
                var3_2 = 0;
            }
        }
        var1_1 = var3_2;
        var3_2 = var2_8;
        var2_8 = var4_3;
lbl47: // 3 sources:
        if (var3_2 != 0) {
            this.mActionModeView.setLayoutParams((ViewGroup.LayoutParams)var6_5);
        }
        ** GOTO lbl51
lbl50: // 1 sources:
        var2_8 = 0;
lbl51: // 2 sources:
        if (this.mStatusGuard == null) return var1_1;
        var6_5 = this.mStatusGuard;
        var2_8 = var2_8 != 0 ? var5_4 : 8;
        var6_5.setVisibility(var2_8);
        return var1_1;
    }

    @Override
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        this.ensureSubDecor();
        ((ViewGroup)this.mSubDecor.findViewById(16908290)).addView(view, layoutParams);
        this.mOriginalWindowCallback.onContentChanged();
    }

    View callActivityOnCreateView(View view, String string2, Context context, AttributeSet attributeSet) {
        if (this.mOriginalWindowCallback instanceof LayoutInflater.Factory && (view = ((LayoutInflater.Factory)this.mOriginalWindowCallback).onCreateView(string2, context, attributeSet)) != null) {
            return view;
        }
        return null;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public ActionBar createSupportActionBar() {
        this.ensureSubDecor();
        WindowDecorActionBar windowDecorActionBar = null;
        if (this.mOriginalWindowCallback instanceof Activity) {
            windowDecorActionBar = new WindowDecorActionBar((Activity)this.mOriginalWindowCallback, this.mOverlayActionBar);
        } else if (this.mOriginalWindowCallback instanceof Dialog) {
            windowDecorActionBar = new WindowDecorActionBar((Dialog)this.mOriginalWindowCallback);
        }
        if (windowDecorActionBar != null) {
            windowDecorActionBar.setDefaultDisplayHomeAsUpEnabled(this.mEnableDefaultActionBarUp);
        }
        return windowDecorActionBar;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    @Override
    public View createView(View var1_1, String var2_2, @NonNull Context var3_3, @NonNull AttributeSet var4_4) {
        var5_5 = Build.VERSION.SDK_INT < 21;
        if (this.mAppCompatViewInflater != null) ** GOTO lbl-1000
        this.mAppCompatViewInflater = new AppCompatViewInflater();
        if (var5_5) lbl-1000: // 2 sources:
        {
            if (this.mSubDecorInstalled && var1_1 != null && var1_1.getId() != 16908290 && !ViewCompat.isAttachedToWindow(var1_1)) {
                var6_6 = true;
                return this.mAppCompatViewInflater.createView(var1_1, var2_2, var3_3, var4_4, var6_6, var5_5, true);
            }
        }
        var6_6 = false;
        return this.mAppCompatViewInflater.createView(var1_1, var2_2, var3_3, var4_4, var6_6, var5_5, true);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    boolean dispatchKeyEvent(KeyEvent keyEvent) {
        int n2 = keyEvent.getKeyCode();
        if (keyEvent.getAction() != 0) return this.onKeyUp(n2, keyEvent);
        return this.onKeyDown(n2, keyEvent);
    }

    ViewGroup getSubDecor() {
        return this.mSubDecor;
    }

    @Override
    public void installViewFactory() {
        LayoutInflater layoutInflater = LayoutInflater.from((Context)this.mContext);
        if (layoutInflater.getFactory() == null) {
            LayoutInflaterCompat.setFactory(layoutInflater, this);
            return;
        }
        Log.i((String)"AppCompatDelegate", (String)"The Activity's LayoutInflater already has a Factory installed so we can not install AppCompat's");
    }

    @Override
    public void invalidateOptionsMenu() {
        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null && actionBar.invalidateOptionsMenu()) {
            return;
        }
        this.invalidatePanelMenu(0);
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    boolean onBackPressed() {
        if (this.mActionMode != null) {
            ActionMode.BackPressedListener backPressedListener = this.mActionMode.getBackPressListener();
            if (backPressedListener == null) return false;
            if (!backPressedListener.onBackPressed()) return false;
            this.mActionMode.finish();
            return true;
        }
        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar == null) return false;
        if (actionBar.collapseActionView()) return true;
        return false;
    }

    @Override
    public void onConfigurationChanged(Configuration configuration) {
        ActionBar actionBar;
        if (this.mHasActionBar && this.mSubDecorInstalled && (actionBar = this.getSupportActionBar()) != null) {
            actionBar.onConfigurationChanged(configuration);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void onCreate(Bundle object) {
        int n2;
        super.onCreate((Bundle)object);
        this.mWindowDecor = (ViewGroup)this.mWindow.getDecorView();
        if (this.mOriginalWindowCallback instanceof Activity && NavUtils.getParentActivityName((Activity)this.mOriginalWindowCallback) != null) {
            object = this.peekSupportActionBar();
            if (object == null) {
                this.mEnableDefaultActionBarUp = true;
            } else {
                object.setDefaultDisplayHomeAsUpEnabled(true);
            }
        }
        if ((n2 = Build.VERSION.SDK_INT) >= 16 && n2 < 19 && this.setTransStatusBar(this.mWindow.getAttributes(), true)) {
            object = this.mWindowDecor.getChildAt(0);
            if (object != null) {
                object.setFitsSystemWindows(false);
            }
            this.mTransStatusBarInFlyme3 = true;
        }
    }

    @Override
    public final View onCreateView(View view, String string2, Context context, AttributeSet attributeSet) {
        View view2 = this.callActivityOnCreateView(view, string2, context, attributeSet);
        if (view2 != null) {
            return view2;
        }
        return this.createView(view, string2, context, attributeSet);
    }

    boolean onKeyDown(int n2, KeyEvent keyEvent) {
        boolean bl2 = false;
        switch (n2) {
            default: {
                if (Build.VERSION.SDK_INT < 11) {
                    bl2 = this.onKeyShortcut(n2, keyEvent);
                }
                return bl2;
            }
            case 82: 
        }
        this.onKeyDownPanel(0, keyEvent);
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    boolean onKeyShortcut(int n2, KeyEvent keyEvent) {
        Object object = this.getSupportActionBar();
        if (object != null && object.onKeyShortcut(n2, keyEvent)) return true;
        if (this.mPreparedPanel != null && this.performPanelShortcut(this.mPreparedPanel, keyEvent.getKeyCode(), keyEvent, 1)) {
            if (this.mPreparedPanel == null) return true;
            {
                this.mPreparedPanel.isHandled = true;
                return true;
            }
        }
        if (this.mPreparedPanel != null) return false;
        {
            object = this.getPanelState(0, true);
            this.preparePanel((PanelFeatureState)object, keyEvent);
            boolean bl2 = this.performPanelShortcut((PanelFeatureState)object, keyEvent.getKeyCode(), keyEvent, 1);
            object.isPrepared = false;
            if (!bl2) return false;
        }
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    boolean onKeyUp(int n2, KeyEvent object) {
        switch (n2) {
            default: {
                return false;
            }
            case 82: {
                this.onKeyUpPanel(0, (KeyEvent)object);
                return true;
            }
            case 4: {
                object = this.getPanelState(0, false);
                if (object != null && object.isOpen) {
                    this.closePanel((PanelFeatureState)object, true);
                    return true;
                }
                if (!this.onBackPressed()) return false;
                return true;
            }
        }
    }

    @Override
    public boolean onMenuItemSelected(MenuBuilder object, MenuItem menuItem) {
        Window.Callback callback = this.getWindowCallback();
        if (callback != null && !this.isDestroyed() && (object = this.findMenuPanel(object.getRootMenu())) != null) {
            return callback.onMenuItemSelected(object.featureId, menuItem);
        }
        return false;
    }

    @Override
    public void onMenuModeChange(MenuBuilder menuBuilder) {
        this.reopenMenu(menuBuilder, true);
    }

    @Override
    boolean onMenuOpened(int n2, Menu object) {
        if (n2 == 8) {
            object = this.getSupportActionBar();
            if (object != null) {
                object.dispatchMenuVisibilityChanged(true);
            }
            return true;
        }
        return false;
    }

    @Override
    boolean onPanelClosed(int n2, Menu object) {
        if (n2 == 8) {
            object = this.getSupportActionBar();
            if (object != null) {
                object.dispatchMenuVisibilityChanged(false);
            }
            return true;
        }
        if (n2 == 0) {
            object = this.getPanelState(n2, true);
            if (object.isOpen) {
                this.closePanel((PanelFeatureState)object, false);
            }
        }
        return false;
    }

    @Override
    public void onPostCreate(Bundle bundle) {
        this.ensureSubDecor();
    }

    @Override
    public void onPostResume() {
        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setShowHideAnimationEnabled(true);
        }
    }

    @Override
    public void onStop() {
        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setShowHideAnimationEnabled(false);
        }
    }

    void onSubDecorInstalled(ViewGroup viewGroup) {
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    void onTitleChanged(CharSequence charSequence) {
        if (this.mDecorContentParent != null) {
            this.mDecorContentParent.setWindowTitle(charSequence);
            return;
        } else {
            if (this.peekSupportActionBar() != null) {
                this.peekSupportActionBar().setWindowTitle(charSequence);
                return;
            }
            if (this.mTitleView == null) return;
            {
                this.mTitleView.setText(charSequence);
                return;
            }
        }
    }

    @Override
    public boolean requestWindowFeature(int n2) {
        switch (n2) {
            default: {
                return this.mWindow.requestFeature(n2);
            }
            case 8: {
                this.throwFeatureRequestIfSubDecorInstalled();
                this.mHasActionBar = true;
                return true;
            }
            case 9: {
                this.throwFeatureRequestIfSubDecorInstalled();
                this.mOverlayActionBar = true;
                return true;
            }
            case 10: {
                this.throwFeatureRequestIfSubDecorInstalled();
                this.mOverlayActionMode = true;
                return true;
            }
            case 2: {
                this.throwFeatureRequestIfSubDecorInstalled();
                this.mFeatureProgress = true;
                return true;
            }
            case 5: {
                this.throwFeatureRequestIfSubDecorInstalled();
                this.mFeatureIndeterminateProgress = true;
                return true;
            }
            case 1: 
        }
        this.throwFeatureRequestIfSubDecorInstalled();
        this.mWindowNoTitle = true;
        return true;
    }

    @Override
    public void setContentView(int n2) {
        this.ensureSubDecor();
        ViewGroup viewGroup = (ViewGroup)this.mSubDecor.findViewById(16908290);
        viewGroup.removeAllViews();
        LayoutInflater.from((Context)this.mContext).inflate(n2, viewGroup);
        this.mOriginalWindowCallback.onContentChanged();
    }

    @Override
    public void setContentView(View view) {
        this.ensureSubDecor();
        ViewGroup viewGroup = (ViewGroup)this.mSubDecor.findViewById(16908290);
        viewGroup.removeAllViews();
        viewGroup.addView(view);
        this.mOriginalWindowCallback.onContentChanged();
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        this.ensureSubDecor();
        ViewGroup viewGroup = (ViewGroup)this.mSubDecor.findViewById(16908290);
        viewGroup.removeAllViews();
        viewGroup.addView(view, layoutParams);
        this.mOriginalWindowCallback.onContentChanged();
    }

    @Override
    public void setSupportActionBar(Toolbar object) {
        if (!(this.mOriginalWindowCallback instanceof Activity)) {
            return;
        }
        if (this.getSupportActionBar() instanceof WindowDecorActionBar) {
            throw new IllegalStateException("This Activity already has an action bar supplied by the window decor. Do not request Window.FEATURE_ACTION_BAR and set windowActionBar to false in your theme to use a Toolbar instead.");
        }
        object = new ToolbarActionBar((Toolbar)((Object)object), ((Activity)this.mContext).getTitle(), this.mWindow);
        this.setSupportActionBar((ActionBar)object);
        this.mWindow.setCallback(object.getWrappedWindowCallback());
        object.invalidateOptionsMenu();
    }

    @Override
    public ActionMode startMultiChoiceActionMode(ActionMode.Callback callback) {
        if (callback == null) {
            throw new IllegalArgumentException("ActionMode callback can not be null.");
        }
        if (this.mActionMode != null) {
            this.mActionMode.finish();
        }
        callback = new ActionModeCallbackWrapperV7(callback);
        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            this.mActionMode = actionBar.startMultiChoiceActionMode(callback);
        }
        return this.mActionMode;
    }

    @Override
    public ActionMode startSupportActionMode(ActionMode.Callback callback) {
        if (callback == null) {
            throw new IllegalArgumentException("ActionMode callback can not be null.");
        }
        if (this.mActionMode != null) {
            this.mActionMode.finish();
        }
        callback = new ActionModeCallbackWrapperV7(callback);
        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            this.mActionMode = actionBar.startActionMode(callback);
            if (this.mActionMode != null && this.mAppCompatCallback != null) {
                this.mAppCompatCallback.onSupportActionModeStarted(this.mActionMode);
            }
        }
        if (this.mActionMode == null) {
            this.mActionMode = this.startSupportActionModeFromWindow(callback);
        }
        return this.mActionMode;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    ActionMode startSupportActionModeFromWindow(ActionMode.Callback callback) {
        Object object;
        if (this.mActionMode != null) {
            this.mActionMode.finish();
        }
        ActionModeCallbackWrapperV7 actionModeCallbackWrapperV7 = new ActionModeCallbackWrapperV7(callback);
        if (this.mAppCompatCallback != null && !this.isDestroyed()) {
            try {
                object = this.mAppCompatCallback.onWindowStartingSupportActionMode(actionModeCallbackWrapperV7);
            }
            catch (AbstractMethodError var4_4) {
                object = null;
            }
        } else {
            object = null;
        }
        if (object != null) {
            this.mActionMode = object;
        } else {
            Object object2;
            if (this.mActionModeView == null) {
                if (this.mIsFloating) {
                    object2 = new TypedValue();
                    object = this.mContext.getTheme();
                    object.resolveAttribute(R.attr.actionBarTheme, (TypedValue)object2, true);
                    if (object2.resourceId != 0) {
                        Resources.Theme theme = this.mContext.getResources().newTheme();
                        theme.setTo((Resources.Theme)object);
                        theme.applyStyle(object2.resourceId, true);
                        object = new ContextThemeWrapper(this.mContext, 0);
                        object.getTheme().setTo(theme);
                    } else {
                        object = this.mContext;
                    }
                    this.mActionModeView = new ActionBarContextView((Context)object);
                    this.mActionModePopup = new PopupWindow((Context)object, null, R.attr.actionModePopupWindowStyle);
                    this.mActionModePopup.setContentView((View)this.mActionModeView);
                    this.mActionModePopup.setWidth(-1);
                    object.getTheme().resolveAttribute(R.attr.actionBarSize, (TypedValue)object2, true);
                    int n2 = TypedValue.complexToDimensionPixelSize((int)object2.data, (DisplayMetrics)object.getResources().getDisplayMetrics());
                    this.mActionModeView.setContentHeight(n2);
                    this.mActionModePopup.setHeight(-2);
                    this.mShowActionModePopup = new AppCompatDelegateImplV7$4(this);
                } else {
                    object = (ViewStubCompat)this.mSubDecor.findViewById(R.id.action_mode_bar_stub);
                    if (object != null) {
                        object.setLayoutInflater(LayoutInflater.from((Context)this.getActionBarThemedContext()));
                        this.mActionModeView = (ActionBarContextView)object.inflate();
                    }
                }
            }
            if (this.mActionModeView != null) {
                this.mActionModeView.killMode();
                object = this.mActionModeView.getContext();
                object2 = this.mActionModeView;
                boolean bl2 = this.mActionModePopup == null;
                if (callback.onCreateActionMode((ActionMode)(object = new StandaloneActionMode((Context)object, (ActionBarContextView)object2, actionModeCallbackWrapperV7, bl2)), object.getMenu())) {
                    object.invalidate();
                    this.mActionModeView.initForMode((ActionMode)object);
                    this.mActionModeView.setVisibility(0);
                    this.mActionMode = object;
                    if (this.mActionModePopup != null) {
                        this.mWindow.getDecorView().post(this.mShowActionModePopup);
                    }
                    this.mActionModeView.sendAccessibilityEvent(32);
                    if (this.mActionModeView.getParent() != null) {
                        ViewCompat.requestApplyInsets((View)this.mActionModeView.getParent());
                    }
                } else {
                    this.mActionMode = null;
                }
            }
        }
        if (this.mActionMode != null && this.mAppCompatCallback != null) {
            this.mAppCompatCallback.onSupportActionModeStarted(this.mActionMode);
        }
        return this.mActionMode;
    }

    final class ActionMenuPresenterCallback
    implements MenuPresenter.Callback {
        private ActionMenuPresenterCallback() {
        }

        @Override
        public void onCloseMenu(MenuBuilder menuBuilder, boolean bl2) {
            AppCompatDelegateImplV7.this.checkCloseActionMenu(menuBuilder);
        }

        @Override
        public boolean onOpenSubMenu(MenuBuilder menuBuilder) {
            Window.Callback callback = AppCompatDelegateImplV7.this.getWindowCallback();
            if (callback != null) {
                callback.onMenuOpened(8, (Menu)menuBuilder);
            }
            return true;
        }
    }

    class ActionModeCallbackWrapperV7
    implements ActionMode.Callback {
        private ActionMode.Callback mWrapped;

        public ActionModeCallbackWrapperV7(ActionMode.Callback callback) {
            this.mWrapped = callback;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            return this.mWrapped.onActionItemClicked(actionMode, menuItem);
        }

        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            return this.mWrapped.onCreateActionMode(actionMode, menu);
        }

        /*
         * Enabled aggressive block sorting
         */
        @Override
        public void onDestroyActionMode(ActionMode actionMode) {
            this.mWrapped.onDestroyActionMode(actionMode);
            if (AppCompatDelegateImplV7.this.mActionModePopup != null) {
                AppCompatDelegateImplV7.this.mWindow.getDecorView().removeCallbacks(AppCompatDelegateImplV7.this.mShowActionModePopup);
                AppCompatDelegateImplV7.this.mActionModePopup.dismiss();
            } else if (AppCompatDelegateImplV7.this.mActionModeView != null) {
                AppCompatDelegateImplV7.this.mActionModeView.setVisibility(8);
                if (AppCompatDelegateImplV7.this.mActionModeView.getParent() != null) {
                    ViewCompat.requestApplyInsets((View)AppCompatDelegateImplV7.this.mActionModeView.getParent());
                }
            }
            if (AppCompatDelegateImplV7.this.mActionModeView != null) {
                AppCompatDelegateImplV7.this.mActionModeView.removeAllViews();
            }
            if (AppCompatDelegateImplV7.this.mAppCompatCallback != null) {
                AppCompatDelegateImplV7.this.mAppCompatCallback.onSupportActionModeFinished(AppCompatDelegateImplV7.this.mActionMode);
            }
            AppCompatDelegateImplV7.this.mActionMode = null;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return this.mWrapped.onPrepareActionMode(actionMode, menu);
        }
    }

    class ListMenuDecorView
    extends FrameLayout {
        public ListMenuDecorView(Context context) {
            super(context);
        }

        private boolean isOutOfBounds(int n2, int n3) {
            if (n2 < -5 || n3 < -5 || n2 > this.getWidth() + 5 || n3 > this.getHeight() + 5) {
                return true;
            }
            return false;
        }

        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return AppCompatDelegateImplV7.this.dispatchKeyEvent(keyEvent);
        }

        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            if (motionEvent.getAction() == 0 && this.isOutOfBounds((int)motionEvent.getX(), (int)motionEvent.getY())) {
                AppCompatDelegateImplV7.this.closePanel(0);
                return true;
            }
            return super.onInterceptTouchEvent(motionEvent);
        }

        public void setBackgroundResource(int n2) {
            this.setBackgroundDrawable(TintManager.getDrawable(this.getContext(), n2));
        }
    }

    static final class PanelFeatureState {
        int background;
        View createdPanelView;
        ViewGroup decorView;
        int featureId;
        Bundle frozenActionViewState;
        Bundle frozenMenuState;
        int gravity;
        boolean isHandled;
        boolean isOpen;
        boolean isPrepared;
        ListMenuPresenter listMenuPresenter;
        Context listPresenterContext;
        MenuBuilder menu;
        public boolean qwertyMode;
        boolean refreshDecorView;
        boolean refreshMenuContent;
        View shownPanelView;
        boolean wasLastOpen;
        int windowAnimations;
        int x;
        int y;

        PanelFeatureState(int n2) {
            this.featureId = n2;
            this.refreshDecorView = false;
        }

        void applyFrozenState() {
            if (this.menu != null && this.frozenMenuState != null) {
                this.menu.restorePresenterStates(this.frozenMenuState);
                this.frozenMenuState = null;
            }
        }

        public void clearMenuPresenters() {
            if (this.menu != null) {
                this.menu.removeMenuPresenter(this.listMenuPresenter);
            }
            this.listMenuPresenter = null;
        }

        MenuView getListMenuView(MenuPresenter.Callback callback) {
            if (this.menu == null) {
                return null;
            }
            if (this.listMenuPresenter == null) {
                this.listMenuPresenter = new ListMenuPresenter(this.listPresenterContext, R.layout.abc_list_menu_item_layout);
                this.listMenuPresenter.setCallback(callback);
                this.menu.addMenuPresenter(this.listMenuPresenter);
            }
            return this.listMenuPresenter.getMenuView(this.decorView);
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public boolean hasPanelItems() {
            boolean bl2 = true;
            if (this.shownPanelView == null) {
                return false;
            }
            boolean bl3 = bl2;
            if (this.createdPanelView != null) return bl3;
            bl3 = bl2;
            if (this.listMenuPresenter.getAdapter().getCount() > 0) return bl3;
            return false;
        }

        void onRestoreInstanceState(Parcelable parcelable) {
            parcelable = (SavedState)parcelable;
            this.featureId = parcelable.featureId;
            this.wasLastOpen = parcelable.isOpen;
            this.frozenMenuState = parcelable.menuState;
            this.shownPanelView = null;
            this.decorView = null;
        }

        Parcelable onSaveInstanceState() {
            SavedState savedState = new SavedState();
            savedState.featureId = this.featureId;
            savedState.isOpen = this.isOpen;
            if (this.menu != null) {
                savedState.menuState = new Bundle();
                this.menu.savePresenterStates(savedState.menuState);
            }
            return savedState;
        }

        /*
         * Enabled aggressive block sorting
         * Lifted jumps to return sites
         */
        void setMenu(MenuBuilder menuBuilder) {
            if (menuBuilder == this.menu) {
                return;
            }
            if (this.menu != null) {
                this.menu.removeMenuPresenter(this.listMenuPresenter);
            }
            this.menu = menuBuilder;
            if (menuBuilder == null) return;
            if (this.listMenuPresenter == null) return;
            menuBuilder.addMenuPresenter(this.listMenuPresenter);
        }

        /*
         * Enabled aggressive block sorting
         */
        void setStyle(Context object) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme theme = object.getResources().newTheme();
            theme.setTo(object.getTheme());
            theme.resolveAttribute(R.attr.actionBarPopupTheme, typedValue, true);
            if (typedValue.resourceId != 0) {
                theme.applyStyle(typedValue.resourceId, true);
            }
            theme.resolveAttribute(R.attr.panelMenuListTheme, typedValue, true);
            if (typedValue.resourceId != 0) {
                theme.applyStyle(typedValue.resourceId, true);
            } else {
                theme.applyStyle(R.style.Theme_AppCompat_CompactMenu, true);
            }
            object = new ContextThemeWrapper((Context)object, 0);
            object.getTheme().setTo(theme);
            this.listPresenterContext = object;
            object = object.obtainStyledAttributes(R.styleable.Theme);
            this.background = object.getResourceId(R.styleable.Theme_panelBackground, 0);
            this.windowAnimations = object.getResourceId(R.styleable.Theme_android_windowAnimationStyle, 0);
            object.recycle();
        }

        static class SavedState
        implements Parcelable {
            public static final Parcelable.Creator<SavedState> CREATOR = new AppCompatDelegateImplV7$PanelFeatureState$SavedState$1();
            int featureId;
            boolean isOpen;
            Bundle menuState;

            private SavedState() {
            }

            static /* synthetic */ SavedState access$1100(Parcel parcel) {
                return SavedState.readFromParcel(parcel);
            }

            /*
             * Enabled aggressive block sorting
             */
            private static SavedState readFromParcel(Parcel parcel) {
                boolean bl2 = true;
                SavedState savedState = new SavedState();
                savedState.featureId = parcel.readInt();
                if (parcel.readInt() != 1) {
                    bl2 = false;
                }
                savedState.isOpen = bl2;
                if (savedState.isOpen) {
                    savedState.menuState = parcel.readBundle();
                }
                return savedState;
            }

            public int describeContents() {
                return 0;
            }

            /*
             * Enabled aggressive block sorting
             */
            public void writeToParcel(Parcel parcel, int n2) {
                parcel.writeInt(this.featureId);
                n2 = this.isOpen ? 1 : 0;
                parcel.writeInt(n2);
                if (this.isOpen) {
                    parcel.writeBundle(this.menuState);
                }
            }
        }

    }

    final class PanelMenuPresenterCallback
    implements MenuPresenter.Callback {
        private PanelMenuPresenterCallback() {
        }

        /*
         * Enabled aggressive block sorting
         */
        @Override
        public void onCloseMenu(MenuBuilder object, boolean bl2) {
            MenuBuilder menuBuilder = object.getRootMenu();
            boolean bl3 = menuBuilder != object;
            AppCompatDelegateImplV7 appCompatDelegateImplV7 = AppCompatDelegateImplV7.this;
            if (bl3) {
                object = menuBuilder;
            }
            if ((object = appCompatDelegateImplV7.findMenuPanel((Menu)object)) != null) {
                if (!bl3) {
                    AppCompatDelegateImplV7.this.closePanel((PanelFeatureState)object, bl2);
                    return;
                }
                AppCompatDelegateImplV7.this.callOnPanelClosed(object.featureId, (PanelFeatureState)object, menuBuilder);
                AppCompatDelegateImplV7.this.closePanel((PanelFeatureState)object, true);
            }
        }

        @Override
        public boolean onOpenSubMenu(MenuBuilder menuBuilder) {
            Window.Callback callback;
            if (menuBuilder == null && AppCompatDelegateImplV7.this.mHasActionBar && (callback = AppCompatDelegateImplV7.this.getWindowCallback()) != null && !AppCompatDelegateImplV7.this.isDestroyed()) {
                callback.onMenuOpened(8, (Menu)menuBuilder);
            }
            return true;
        }
    }

}

