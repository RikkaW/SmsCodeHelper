/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.pm.ApplicationInfo
 *  android.content.res.Configuration
 *  android.content.res.Resources
 *  android.content.res.Resources$Theme
 *  android.content.res.TypedArray
 *  android.graphics.Canvas
 *  android.graphics.Rect
 *  android.graphics.drawable.Drawable
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.IBinder
 *  android.os.Parcelable
 *  android.util.AttributeSet
 *  android.util.Log
 *  android.util.SparseArray
 *  android.view.Menu
 *  android.view.View
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewGroup$MarginLayoutParams
 *  android.view.Window
 *  android.view.Window$Callback
 *  java.lang.Class
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v7.internal.widget;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.IBinder;
import android.os.Parcelable;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.widget.ScrollerCompat;
import android.support.v7.appcompat.R;
import android.support.v7.internal.view.ActionBarPolicy;
import android.support.v7.internal.view.menu.MenuPresenter;
import android.support.v7.internal.widget.ActionBarContainer;
import android.support.v7.internal.widget.ActionBarContextView;
import android.support.v7.internal.widget.ActionBarOverlayLayout$1;
import android.support.v7.internal.widget.ActionBarOverlayLayout$2;
import android.support.v7.internal.widget.ActionBarOverlayLayout$3;
import android.support.v7.internal.widget.ActionBarOverlayLayout$4;
import android.support.v7.internal.widget.ContentFrameLayout;
import android.support.v7.internal.widget.DecorContentParent;
import android.support.v7.internal.widget.DecorToolbar;
import android.support.v7.internal.widget.ViewUtils;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

public class ActionBarOverlayLayout
extends ViewGroup
implements NestedScrollingParent,
DecorContentParent {
    static final int[] ATTRS = new int[]{R.attr.actionBarSize, 16842841, R.attr.mzWindowSplitActionBar, R.attr.mzSplitActionBarFloat};
    private static final String TAG = "ActionBarOverlayLayout";
    private final int ACTION_BAR_ANIMATE_DELAY = 600;
    private ActionBarContainer mActionBarBottom;
    private int mActionBarHeight;
    private ActionBarContainer mActionBarTop;
    private ActionBarVisibilityCallback mActionBarVisibilityCallback;
    private final Runnable mAddActionBarHideOffset;
    private boolean mAnimatingForFling;
    private final Rect mBaseContentInsets = new Rect();
    private final Rect mBaseInnerInsets = new Rect();
    private final ViewPropertyAnimatorListener mBottomAnimatorListener;
    private ContentFrameLayout mContent;
    private final Rect mContentInsets = new Rect();
    private ViewPropertyAnimatorCompat mCurrentActionBarBottomAnimator;
    private ViewPropertyAnimatorCompat mCurrentActionBarTopAnimator;
    private DecorToolbar mDecorToolbar;
    private ScrollerCompat mFlingEstimator;
    private boolean mHasNonEmbeddedTabs;
    private boolean mHideOnContentScroll;
    private int mHideOnContentScrollReference;
    private boolean mIgnoreWindowContentOverlay;
    private final Rect mInnerInsets = new Rect();
    private final Rect mLastBaseContentInsets = new Rect();
    private final Rect mLastInnerInsets = new Rect();
    private int mLastSystemUiVisibility;
    private boolean mMzSplitBarFloat;
    private boolean mMzWindowSplitToolBar;
    private boolean mOverlayMode;
    private final NestedScrollingParentHelper mParentHelper;
    private final Runnable mRemoveActionBarHideOffset;
    private final ViewPropertyAnimatorListener mTopAnimatorListener;
    private boolean mTransStatusBarInFlyme3;
    private int mUiOptions;
    private Drawable mWindowContentOverlay;
    private int mWindowVisibility = 0;

    public ActionBarOverlayLayout(Context context) {
        this(context, null);
    }

    public ActionBarOverlayLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mTopAnimatorListener = new ActionBarOverlayLayout$1(this);
        this.mBottomAnimatorListener = new ActionBarOverlayLayout$2(this);
        this.mRemoveActionBarHideOffset = new ActionBarOverlayLayout$3(this);
        this.mAddActionBarHideOffset = new ActionBarOverlayLayout$4(this);
        this.mTransStatusBarInFlyme3 = false;
        this.init(context);
        this.mParentHelper = new NestedScrollingParentHelper(this);
    }

    static /* synthetic */ ViewPropertyAnimatorCompat access$002(ActionBarOverlayLayout actionBarOverlayLayout, ViewPropertyAnimatorCompat viewPropertyAnimatorCompat) {
        actionBarOverlayLayout.mCurrentActionBarTopAnimator = viewPropertyAnimatorCompat;
        return viewPropertyAnimatorCompat;
    }

    static /* synthetic */ boolean access$102(ActionBarOverlayLayout actionBarOverlayLayout, boolean bl2) {
        actionBarOverlayLayout.mAnimatingForFling = bl2;
        return bl2;
    }

    static /* synthetic */ ViewPropertyAnimatorCompat access$202(ActionBarOverlayLayout actionBarOverlayLayout, ViewPropertyAnimatorCompat viewPropertyAnimatorCompat) {
        actionBarOverlayLayout.mCurrentActionBarBottomAnimator = viewPropertyAnimatorCompat;
        return viewPropertyAnimatorCompat;
    }

    static /* synthetic */ void access$300(ActionBarOverlayLayout actionBarOverlayLayout) {
        actionBarOverlayLayout.haltActionBarHideOffsetAnimations();
    }

    static /* synthetic */ ViewPropertyAnimatorListener access$400(ActionBarOverlayLayout actionBarOverlayLayout) {
        return actionBarOverlayLayout.mTopAnimatorListener;
    }

    static /* synthetic */ ActionBarContainer access$500(ActionBarOverlayLayout actionBarOverlayLayout) {
        return actionBarOverlayLayout.mActionBarTop;
    }

    static /* synthetic */ ActionBarContainer access$600(ActionBarOverlayLayout actionBarOverlayLayout) {
        return actionBarOverlayLayout.mActionBarBottom;
    }

    static /* synthetic */ ViewPropertyAnimatorListener access$700(ActionBarOverlayLayout actionBarOverlayLayout) {
        return actionBarOverlayLayout.mBottomAnimatorListener;
    }

    private void addActionBarHideOffset() {
        this.haltActionBarHideOffsetAnimations();
        this.mAddActionBarHideOffset.run();
    }

    private boolean applyInsets(View object, Rect rect, boolean bl2, boolean bl3, boolean bl4, boolean bl5) {
        boolean bl6 = false;
        object = (LayoutParams)object.getLayoutParams();
        boolean bl7 = bl6;
        if (bl2) {
            bl7 = bl6;
            if (object.leftMargin != rect.left) {
                object.leftMargin = rect.left;
                bl7 = true;
            }
        }
        bl2 = bl7;
        if (bl3) {
            bl2 = bl7;
            if (object.topMargin != rect.top) {
                object.topMargin = rect.top;
                bl2 = true;
            }
        }
        bl3 = bl2;
        if (bl5) {
            bl3 = bl2;
            if (object.rightMargin != rect.right) {
                object.rightMargin = rect.right;
                bl3 = true;
            }
        }
        if (bl4 && object.bottomMargin != rect.bottom) {
            object.bottomMargin = rect.bottom;
            return true;
        }
        return bl3;
    }

    /*
     * Enabled aggressive block sorting
     */
    private boolean applyInsetsForFlyme(Rect rect) {
        int n2 = (this.getWindowSystemUiVisibility() & 256) != 0 || this.mTransStatusBarInFlyme3 ? 1 : 0;
        if (n2 != 0) {
            n2 = this.getResources().getDimensionPixelSize(R.dimen.status_bar_height);
            this.mActionBarTop.setPadding(this.mActionBarTop.getPaddingLeft(), n2, this.mActionBarTop.getPaddingRight(), this.mActionBarTop.getPaddingBottom());
            this.applyInsets((View)this.mActionBarTop, new Rect(0, 0, 0, 0), true, true, true, true);
            return true;
        }
        this.mActionBarTop.setPadding(this.mActionBarTop.getPaddingLeft(), 0, this.mActionBarTop.getPaddingRight(), this.mActionBarTop.getPaddingBottom());
        return this.applyInsets((View)this.mActionBarTop, rect, true, true, false, true);
    }

    private DecorToolbar getDecorToolbar(View view) {
        if (view instanceof DecorToolbar) {
            return (DecorToolbar)view;
        }
        if (view instanceof Toolbar) {
            return ((Toolbar)view).getWrapper();
        }
        throw new IllegalStateException("Can't make a decor toolbar out of " + view.getClass().getSimpleName());
    }

    private void haltActionBarHideOffsetAnimations() {
        this.removeCallbacks(this.mRemoveActionBarHideOffset);
        this.removeCallbacks(this.mAddActionBarHideOffset);
        if (this.mCurrentActionBarTopAnimator != null) {
            this.mCurrentActionBarTopAnimator.cancel();
        }
        if (this.mCurrentActionBarBottomAnimator != null) {
            this.mCurrentActionBarBottomAnimator.cancel();
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void init(Context context) {
        boolean bl2 = true;
        TypedArray typedArray = this.getContext().getTheme().obtainStyledAttributes(ATTRS);
        this.mActionBarHeight = typedArray.getDimensionPixelSize(0, 0);
        this.mWindowContentOverlay = typedArray.getDrawable(1);
        boolean bl3 = this.mWindowContentOverlay == null;
        this.setWillNotDraw(bl3);
        this.mMzWindowSplitToolBar = typedArray.getBoolean(2, false);
        this.mMzSplitBarFloat = typedArray.getBoolean(3, false);
        typedArray.recycle();
        bl3 = context.getApplicationInfo().targetSdkVersion < 19 ? bl2 : false;
        this.mIgnoreWindowContentOverlay = bl3;
        this.mFlingEstimator = ScrollerCompat.create(context);
    }

    private void postAddActionBarHideOffset() {
        this.haltActionBarHideOffsetAnimations();
        this.postDelayed(this.mAddActionBarHideOffset, 600);
    }

    private void postRemoveActionBarHideOffset() {
        this.haltActionBarHideOffsetAnimations();
        this.postDelayed(this.mRemoveActionBarHideOffset, 600);
    }

    private void removeActionBarHideOffset() {
        this.haltActionBarHideOffsetAnimations();
        this.mRemoveActionBarHideOffset.run();
    }

    private boolean shouldHideActionBarOnFling(float f2, float f3) {
        boolean bl2 = false;
        this.mFlingEstimator.fling(0, 0, 0, (int)f3, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        if (this.mFlingEstimator.getFinalY() > this.mActionBarTop.getHeight()) {
            bl2 = true;
        }
        return bl2;
    }

    @Override
    public boolean canShowOverflowMenu() {
        this.pullChildren();
        return this.mDecorToolbar.canShowOverflowMenu();
    }

    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    protected boolean computeFitSystemWindowsForFullScreen(Rect rect, Rect rect2) {
        boolean bl2 = false;
        if (ViewUtils.computeFitSystemWindows((View)this, this.mBaseInnerInsets, this.mBaseContentInsets)) {
            return true;
        }
        if (super.getFitsSystemWindows()) return bl2;
        rect2.bottom = rect.bottom;
        rect.bottom = 0;
        return false;
    }

    @Override
    public void dismissPopups() {
        this.pullChildren();
        this.mDecorToolbar.dismissPopupMenus();
    }

    /*
     * Enabled aggressive block sorting
     */
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.mWindowContentOverlay != null && !this.mIgnoreWindowContentOverlay) {
            int n2 = this.mActionBarTop.getVisibility() == 0 ? (int)((float)this.mActionBarTop.getBottom() + ViewCompat.getTranslationY((View)this.mActionBarTop) + 0.5f) : 0;
            this.mWindowContentOverlay.setBounds(0, n2, this.getWidth(), this.mWindowContentOverlay.getIntrinsicHeight() + n2);
            this.mWindowContentOverlay.draw(canvas);
        }
    }

    protected boolean fitSystemWindows(Rect rect) {
        this.pullChildren();
        if ((ViewCompat.getWindowSystemUiVisibility((View)this) & 256) != 0) {
            // empty if block
        }
        boolean bl2 = this.applyInsetsForFlyme(rect);
        if (this.mActionBarBottom != null) {
            bl2 = this.applyInsets((View)this.mActionBarBottom, rect, true, false, this.mMzSplitBarFloat, true) | bl2;
        }
        this.mBaseInnerInsets.set(rect);
        this.computeFitSystemWindowsForFullScreen(this.mBaseInnerInsets, this.mBaseContentInsets);
        if (!this.mLastBaseContentInsets.equals((Object)this.mBaseContentInsets)) {
            this.mLastBaseContentInsets.set(this.mBaseContentInsets);
            bl2 = true;
        }
        if (bl2) {
            this.requestLayout();
        }
        return true;
    }

    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -1);
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(this.getContext(), attributeSet);
    }

    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    public int getActionBarHideOffset() {
        if (this.mActionBarTop != null) {
            return - (int)ViewCompat.getTranslationY((View)this.mActionBarTop);
        }
        return 0;
    }

    @Override
    public int getNestedScrollAxes() {
        return this.mParentHelper.getNestedScrollAxes();
    }

    @Override
    public CharSequence getTitle() {
        this.pullChildren();
        return this.mDecorToolbar.getTitle();
    }

    @Override
    public boolean hasIcon() {
        this.pullChildren();
        return this.mDecorToolbar.hasIcon();
    }

    @Override
    public boolean hasLogo() {
        this.pullChildren();
        return this.mDecorToolbar.hasLogo();
    }

    @Override
    public boolean hideOverflowMenu() {
        this.pullChildren();
        return this.mDecorToolbar.hideOverflowMenu();
    }

    @Override
    public void initFeature(int n2) {
        this.pullChildren();
        switch (n2) {
            default: {
                return;
            }
            case 2: {
                this.mDecorToolbar.initProgress();
                return;
            }
            case 5: {
                this.mDecorToolbar.initIndeterminateProgress();
                return;
            }
            case 9: 
        }
        this.setOverlayMode(true);
    }

    public boolean isHideOnContentScrollEnabled() {
        return this.mHideOnContentScroll;
    }

    public boolean isInOverlayMode() {
        return this.mOverlayMode;
    }

    @Override
    public boolean isOverflowMenuShowPending() {
        this.pullChildren();
        return this.mDecorToolbar.isOverflowMenuShowPending();
    }

    @Override
    public boolean isOverflowMenuShowing() {
        this.pullChildren();
        return this.mDecorToolbar.isOverflowMenuShowing();
    }

    protected void onConfigurationChanged(Configuration configuration) {
        if (Build.VERSION.SDK_INT >= 8) {
            super.onConfigurationChanged(configuration);
        }
        this.init(this.getContext());
        ViewCompat.requestApplyInsets((View)this);
        this.setUiOptions(this.mUiOptions);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.haltActionBarHideOffsetAnimations();
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void onLayout(boolean bl2, int n2, int n3, int n4, int n5) {
        int n6 = this.getChildCount();
        int n7 = this.getPaddingLeft();
        this.getPaddingRight();
        int n8 = this.getPaddingTop();
        int n9 = this.getPaddingBottom();
        n2 = 0;
        while (n2 < n6) {
            View view = this.getChildAt(n2);
            if (view.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
                int n10 = view.getMeasuredWidth();
                int n11 = view.getMeasuredHeight();
                int n12 = layoutParams.leftMargin + n7;
                n4 = view == this.mActionBarBottom ? n5 - n3 - n9 - n11 - layoutParams.bottomMargin : layoutParams.topMargin + n8;
                view.layout(n12, n4, n10 + n12, n11 + n4);
            }
            ++n2;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void onMeasure(int n2, int n3) {
        this.pullChildren();
        this.measureChildWithMargins((View)this.mActionBarTop, n2, 0, n3, 0);
        LayoutParams layoutParams = (LayoutParams)this.mActionBarTop.getLayoutParams();
        int n4 = Math.max((int)0, (int)(this.mActionBarTop.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin));
        int n5 = this.mActionBarTop.getMeasuredHeight();
        int n6 = layoutParams.topMargin;
        int n7 = Math.max((int)0, (int)(layoutParams.bottomMargin + (n5 + n6)));
        int n8 = ViewUtils.combineMeasuredStates(0, ViewCompat.getMeasuredState((View)this.mActionBarTop));
        if (this.mActionBarBottom != null) {
            this.measureChildWithMargins((View)this.mActionBarBottom, n2, 0, n3, 0);
            layoutParams = (LayoutParams)this.mActionBarBottom.getLayoutParams();
            n4 = Math.max((int)n4, (int)(this.mActionBarBottom.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin));
            n5 = this.mActionBarBottom.getMeasuredHeight();
            n6 = layoutParams.topMargin;
            n7 = Math.max((int)n7, (int)(layoutParams.bottomMargin + (n5 + n6)));
            n8 = ViewUtils.combineMeasuredStates(n8, ViewCompat.getMeasuredState((View)this.mActionBarBottom));
        }
        boolean bl2 = (ViewCompat.getWindowSystemUiVisibility((View)this) & 256) != 0 || this.mTransStatusBarInFlyme3;
        if (bl2) {
            n5 = n6 = this.mActionBarHeight;
            if (this.mHasNonEmbeddedTabs) {
                n5 = n6;
                if (this.mActionBarTop.getTabContainer() != null) {
                    n5 = n6 + ActionBarPolicy.get(this.getContext()).getTabContainerHeight();
                }
            }
        } else {
            n5 = this.mActionBarTop.getVisibility() != 8 ? this.mActionBarTop.getMeasuredHeight() : 0;
        }
        n6 = this.mDecorToolbar.isSplit() && this.mActionBarBottom != null ? (bl2 ? this.mActionBarHeight : this.mActionBarBottom.getMeasuredHeight()) : 0;
        this.mContentInsets.set(this.mBaseContentInsets);
        this.mInnerInsets.set(this.mBaseInnerInsets);
        if (!this.mOverlayMode && !bl2) {
            layoutParams = this.mContentInsets;
            layoutParams.top = n5 + layoutParams.top;
            layoutParams = this.mContentInsets;
            layoutParams.bottom = n6 + layoutParams.bottom;
        } else {
            layoutParams = this.mInnerInsets;
            layoutParams.top = n5 + layoutParams.top;
            layoutParams = this.mInnerInsets;
            layoutParams.bottom = n6 + layoutParams.bottom;
        }
        this.applyInsets((View)this.mContent, this.mContentInsets, true, true, true, true);
        if (!this.mLastInnerInsets.equals((Object)this.mInnerInsets)) {
            this.mLastInnerInsets.set(this.mInnerInsets);
            this.mContent.dispatchFitSystemWindows(this.mInnerInsets);
        }
        this.measureChildWithMargins((View)this.mContent, n2, 0, n3, 0);
        layoutParams = (LayoutParams)this.mContent.getLayoutParams();
        n5 = Math.max((int)n4, (int)(this.mContent.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin));
        n6 = this.mContent.getMeasuredHeight();
        n4 = layoutParams.topMargin;
        n6 = Math.max((int)n7, (int)(layoutParams.bottomMargin + (n6 + n4)));
        n8 = ViewUtils.combineMeasuredStates(n8, ViewCompat.getMeasuredState((View)this.mContent));
        n7 = this.getPaddingLeft();
        n4 = this.getPaddingRight();
        n6 = Math.max((int)(n6 + (this.getPaddingTop() + this.getPaddingBottom())), (int)this.getSuggestedMinimumHeight());
        this.setMeasuredDimension(ViewCompat.resolveSizeAndState(Math.max((int)(n5 + (n7 + n4)), (int)this.getSuggestedMinimumWidth()), n2, n8), ViewCompat.resolveSizeAndState(n6, n3, n8 << 16));
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public boolean onNestedFling(View view, float f2, float f3, boolean bl2) {
        if (!this.mHideOnContentScroll || !bl2) {
            return false;
        }
        if (this.shouldHideActionBarOnFling(f2, f3)) {
            this.addActionBarHideOffset();
        } else {
            this.removeActionBarHideOffset();
        }
        this.mAnimatingForFling = true;
        return true;
    }

    @Override
    public boolean onNestedPreFling(View view, float f2, float f3) {
        return false;
    }

    @Override
    public void onNestedPreScroll(View view, int n2, int n3, int[] arrn) {
    }

    @Override
    public void onNestedScroll(View view, int n2, int n3, int n4, int n5) {
        this.mHideOnContentScrollReference += n3;
        this.setActionBarHideOffset(this.mHideOnContentScrollReference);
    }

    @Override
    public void onNestedScrollAccepted(View view, View view2, int n2) {
        this.mParentHelper.onNestedScrollAccepted(view, view2, n2);
        this.mHideOnContentScrollReference = this.getActionBarHideOffset();
        this.haltActionBarHideOffsetAnimations();
        if (this.mActionBarVisibilityCallback != null) {
            this.mActionBarVisibilityCallback.onContentScrollStarted();
        }
    }

    @Override
    public boolean onStartNestedScroll(View view, View view2, int n2) {
        if ((n2 & 2) == 0 || this.mActionBarTop.getVisibility() != 0) {
            return false;
        }
        return this.mHideOnContentScroll;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void onStopNestedScroll(View view) {
        if (this.mHideOnContentScroll && !this.mAnimatingForFling) {
            if (this.mHideOnContentScrollReference <= this.mActionBarTop.getHeight()) {
                this.postRemoveActionBarHideOffset();
            } else {
                this.postAddActionBarHideOffset();
            }
        }
        if (this.mActionBarVisibilityCallback != null) {
            this.mActionBarVisibilityCallback.onContentScrollStopped();
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void onWindowSystemUiVisibilityChanged(int n2) {
        boolean bl2 = true;
        if (Build.VERSION.SDK_INT >= 16) {
            super.onWindowSystemUiVisibilityChanged(n2);
        }
        this.pullChildren();
        int n3 = this.mLastSystemUiVisibility;
        this.mLastSystemUiVisibility = n2;
        boolean bl3 = (n2 & 4) == 0;
        boolean bl4 = (n2 & 256) != 0;
        if (this.mActionBarVisibilityCallback != null) {
            ActionBarVisibilityCallback actionBarVisibilityCallback = this.mActionBarVisibilityCallback;
            if (bl4) {
                bl2 = false;
            }
            actionBarVisibilityCallback.enableContentAnimations(bl2);
            if (bl3 || !bl4) {
                this.mActionBarVisibilityCallback.showForSystem();
            } else {
                this.mActionBarVisibilityCallback.hideForSystem();
            }
        }
        if (((n3 ^ n2) & 256) != 0 && this.mActionBarVisibilityCallback != null) {
            ViewCompat.requestApplyInsets((View)this);
        }
    }

    protected void onWindowVisibilityChanged(int n2) {
        super.onWindowVisibilityChanged(n2);
        this.mWindowVisibility = n2;
        if (this.mActionBarVisibilityCallback != null) {
            this.mActionBarVisibilityCallback.onWindowVisibilityChanged(n2);
        }
    }

    void pullChildren() {
        if (this.mContent == null) {
            this.mContent = (ContentFrameLayout)this.findViewById(R.id.action_bar_activity_content);
            this.mActionBarTop = (ActionBarContainer)this.findViewById(R.id.action_bar_container);
            this.mDecorToolbar = this.getDecorToolbar(this.findViewById(R.id.action_bar));
            this.mActionBarBottom = (ActionBarContainer)this.findViewById(R.id.split_action_bar);
        }
    }

    @Override
    public void restoreToolbarHierarchyState(SparseArray<Parcelable> sparseArray) {
        this.pullChildren();
        this.mDecorToolbar.restoreHierarchyState(sparseArray);
    }

    @Override
    public void saveToolbarHierarchyState(SparseArray<Parcelable> sparseArray) {
        this.pullChildren();
        this.mDecorToolbar.saveHierarchyState(sparseArray);
    }

    public void setActionBarHideOffset(int n2) {
        this.haltActionBarHideOffsetAnimations();
        int n3 = this.mActionBarTop.getHeight();
        n2 = Math.max((int)0, (int)Math.min((int)n2, (int)n3));
        ViewCompat.setTranslationY((View)this.mActionBarTop, - n2);
        if (this.mActionBarBottom != null && this.mActionBarBottom.getVisibility() != 8) {
            n2 = (int)((float)n2 / (float)n3 * (float)this.mActionBarBottom.getHeight());
            ViewCompat.setTranslationY((View)this.mActionBarBottom, n2);
        }
    }

    public void setActionBarVisibilityCallback(ActionBarVisibilityCallback actionBarVisibilityCallback) {
        this.mActionBarVisibilityCallback = actionBarVisibilityCallback;
        if (this.getWindowToken() != null) {
            this.mActionBarVisibilityCallback.onWindowVisibilityChanged(this.mWindowVisibility);
            if (this.mLastSystemUiVisibility != 0) {
                this.onWindowSystemUiVisibilityChanged(this.mLastSystemUiVisibility);
                ViewCompat.requestApplyInsets((View)this);
            }
        }
    }

    public void setHasNonEmbeddedTabs(boolean bl2) {
        this.mHasNonEmbeddedTabs = bl2;
    }

    public void setHideOnContentScrollEnabled(boolean bl2) {
        if (bl2 != this.mHideOnContentScroll) {
            this.mHideOnContentScroll = bl2;
            if (!bl2) {
                this.haltActionBarHideOffsetAnimations();
                this.setActionBarHideOffset(0);
            }
        }
    }

    @Override
    public void setIcon(int n2) {
        this.pullChildren();
        this.mDecorToolbar.setIcon(n2);
    }

    @Override
    public void setIcon(Drawable drawable2) {
        this.pullChildren();
        this.mDecorToolbar.setIcon(drawable2);
    }

    @Override
    public void setLogo(int n2) {
        this.pullChildren();
        this.mDecorToolbar.setLogo(n2);
    }

    @Override
    public void setMenu(Menu menu, MenuPresenter.Callback callback) {
        this.pullChildren();
        this.mDecorToolbar.setMenu(menu, callback);
    }

    @Override
    public void setMenuPrepared() {
        this.pullChildren();
        this.mDecorToolbar.setMenuPrepared();
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setOverlayMode(boolean bl2) {
        this.mOverlayMode = bl2;
        bl2 = bl2 && this.getContext().getApplicationInfo().targetSdkVersion < 19;
        this.mIgnoreWindowContentOverlay = bl2;
    }

    public void setShowingForActionMode(boolean bl2) {
    }

    @Override
    public void setTransStatusBarInFlyme3(boolean bl2) {
        this.mTransStatusBarInFlyme3 = bl2;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    @Override
    public void setUiOptions(int var1_1) {
        this.mUiOptions = var1_1;
        var2_2 = (var1_1 & 1) != 0;
        var3_3 = var2_2 != false ? this.getContext().getResources().getBoolean(R.bool.mz_split_action_bar_is_narrow) : this.mMzWindowSplitToolBar;
        this.pullChildren();
        var4_4 = (ActionBarContextView)this.findViewById(R.id.action_context_bar);
        if (!var3_3) ** GOTO lbl13
        if (this.mActionBarBottom != null && this.mDecorToolbar.canSplit()) {
            this.mDecorToolbar.setSplitView((ViewGroup)this.mActionBarBottom);
            var4_4.setSplitView((ViewGroup)this.mActionBarBottom);
        } else {
            Log.e((String)"ActionBarOverlayLayout", (String)"Requested split action bar with incompatible window decor! Ignoring request.");
            return;
lbl13: // 1 sources:
            this.mDecorToolbar.setSplitView(null);
            if (!var4_4.isInMultiChoiceActionMode()) {
                var4_4.setSplitView(null);
            }
        }
        this.mDecorToolbar.setSplitToolbar(var3_3);
        this.mDecorToolbar.setSplitWhenNarrow(var2_2);
        var4_4.setSplitToolbar(var3_3);
        var4_4.setSplitWhenNarrow(var2_2);
    }

    @Override
    public void setWindowCallback(Window.Callback callback) {
        this.pullChildren();
        this.mDecorToolbar.setWindowCallback(callback);
    }

    @Override
    public void setWindowTitle(CharSequence charSequence) {
        this.pullChildren();
        this.mDecorToolbar.setWindowTitle(charSequence);
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }

    @Override
    public boolean showOverflowMenu() {
        this.pullChildren();
        return this.mDecorToolbar.showOverflowMenu();
    }

    public static interface ActionBarVisibilityCallback {
        public void enableContentAnimations(boolean var1);

        public void hideForSystem();

        public void onContentScrollStarted();

        public void onContentScrollStopped();

        public void onWindowVisibilityChanged(int var1);

        public void showForSystem();
    }

    public static class LayoutParams
    extends ViewGroup.MarginLayoutParams {
        public LayoutParams(int n2, int n3) {
            super(n2, n3);
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }
    }

}

