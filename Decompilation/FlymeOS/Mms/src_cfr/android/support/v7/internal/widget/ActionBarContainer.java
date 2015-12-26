/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.TypedArray
 *  android.graphics.Rect
 *  android.graphics.drawable.Drawable
 *  android.graphics.drawable.Drawable$Callback
 *  android.graphics.drawable.LayerDrawable
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.util.AttributeSet
 *  android.view.ActionMode
 *  android.view.ActionMode$Callback
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.View$MeasureSpec
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewTreeObserver
 *  android.view.ViewTreeObserver$OnDrawListener
 *  android.view.ViewTreeObserver$OnGlobalLayoutListener
 *  android.widget.FrameLayout
 *  android.widget.FrameLayout$LayoutParams
 *  java.lang.Class
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Thread
 *  java.lang.reflect.Field
 *  java.lang.reflect.Method
 */
package android.support.v7.internal.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.support.v7.appcompat.R;
import android.support.v7.internal.VersionUtils;
import android.support.v7.internal.widget.ActionBarBackgroundDrawable;
import android.support.v7.internal.widget.ActionBarBackgroundDrawableV21;
import android.support.v7.internal.widget.ActionBarContainer$1;
import android.support.v7.internal.widget.ActionBarContainer$2;
import android.support.v7.internal.widget.ScrollingTabContainerView;
import android.support.v7.view.ActionMode;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ActionBarContainer
extends FrameLayout {
    private static Field sDirtyField;
    private static Method sGetViewRootImplMethod;
    private View mActionBarView;
    Drawable mBackground;
    private ViewTreeObserver.OnDrawListener mBlurOnDrawListener;
    private ViewTreeObserver.OnGlobalLayoutListener mBlurOnGlobalLayoutListener;
    private View mContextView;
    private int mHeight;
    boolean mIsSplit;
    boolean mIsStacked;
    private boolean mIsTransitioning;
    Drawable mSplitBackground;
    Drawable mStackedBackground;
    private View mTabContainer;
    private ViewTreeObserver mViewTreeObserver;
    private Rect mVisibleRect = new Rect();

    public ActionBarContainer(Context context) {
        this(context, null);
    }

    /*
     * Enabled aggressive block sorting
     */
    public ActionBarContainer(Context context, AttributeSet attributeSet) {
        void var4_4;
        super(context, attributeSet);
        this.mBlurOnDrawListener = this.getBlurOnDrawListener();
        this.mBlurOnGlobalLayoutListener = new ActionBarContainer$2(this);
        if (VersionUtils.isAtLeastL()) {
            ActionBarBackgroundDrawableV21 actionBarBackgroundDrawableV21 = new ActionBarBackgroundDrawableV21(this);
        } else {
            ActionBarBackgroundDrawable actionBarBackgroundDrawable = new ActionBarBackgroundDrawable(this);
        }
        this.setBackgroundDrawable((Drawable)var4_4);
        context = context.obtainStyledAttributes(attributeSet, R.styleable.ActionBar);
        this.mBackground = context.getDrawable(R.styleable.ActionBar_background);
        this.mStackedBackground = context.getDrawable(R.styleable.ActionBar_backgroundStacked);
        this.mHeight = context.getDimensionPixelSize(R.styleable.ActionBar_height, -1);
        if (this.getId() == R.id.split_action_bar) {
            this.mIsSplit = true;
            this.mSplitBackground = context.getDrawable(R.styleable.ActionBar_backgroundSplit);
        }
        context.recycle();
        boolean bl2 = this.mIsSplit ? this.mSplitBackground == null : this.mBackground == null && this.mStackedBackground == null;
        this.setWillNotDraw(bl2);
    }

    static /* synthetic */ Field access$000() {
        return sDirtyField;
    }

    static /* synthetic */ Method access$100() {
        return sGetViewRootImplMethod;
    }

    static /* synthetic */ Rect access$200(ActionBarContainer actionBarContainer) {
        return actionBarContainer.mVisibleRect;
    }

    private ViewTreeObserver.OnDrawListener getBlurOnDrawListener() {
        if (Build.VERSION.SDK_INT >= 16) {
            return new ActionBarContainer$1(this);
        }
        return null;
    }

    private static Field getField() {
        try {
            Field field = Class.forName((String)"android.view.ViewRootImpl").getDeclaredField("mDirty");
            field.setAccessible(true);
            return field;
        }
        catch (Exception var0_1) {
            return null;
        }
    }

    private int getMeasuredHeightWithMargins(View view) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams)view.getLayoutParams();
        int n2 = view.getMeasuredHeight();
        int n3 = layoutParams.topMargin;
        return layoutParams.bottomMargin + (n2 + n3);
    }

    private static Method getMethod() {
        try {
            Method method = View.class.getDeclaredMethod("getViewRootImpl", new Class[0]);
            return method;
        }
        catch (NoSuchMethodException var0_1) {
            return null;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private boolean isBlurDrawable(Drawable drawable2) {
        boolean bl2;
        boolean bl3 = bl2 = false;
        if (drawable2 == null) return bl3;
        if (drawable2 instanceof LayerDrawable) {
            int n2 = 0;
            do {
                bl3 = bl2;
                if (n2 >= ((LayerDrawable)drawable2).getNumberOfLayers()) return bl3;
                if (this.isBlurDrawable(((LayerDrawable)drawable2).getDrawable(n2))) {
                    return true;
                }
                ++n2;
            } while (true);
        }
        bl3 = bl2;
        if (!drawable2.getClass().getName().equals((Object)"com.meizu.common.drawble.BlurDrawable")) return bl3;
        return true;
    }

    private boolean isCollapsed(View view) {
        if (view == null || view.getVisibility() == 8 || view.getMeasuredHeight() == 0) {
            return true;
        }
        return false;
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.mBackground != null && this.mBackground.isStateful()) {
            this.mBackground.setState(this.getDrawableState());
        }
        if (this.mStackedBackground != null && this.mStackedBackground.isStateful()) {
            this.mStackedBackground.setState(this.getDrawableState());
        }
        if (this.mSplitBackground != null && this.mSplitBackground.isStateful()) {
            this.mSplitBackground.setState(this.getDrawableState());
        }
    }

    public View getTabContainer() {
        return this.mTabContainer;
    }

    public void jumpDrawablesToCurrentState() {
        if (Build.VERSION.SDK_INT >= 11) {
            super.jumpDrawablesToCurrentState();
            if (this.mBackground != null) {
                this.mBackground.jumpToCurrentState();
            }
            if (this.mStackedBackground != null) {
                this.mStackedBackground.jumpToCurrentState();
            }
            if (this.mSplitBackground != null) {
                this.mSplitBackground.jumpToCurrentState();
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        boolean bl2 = this.mIsSplit ? this.isBlurDrawable(this.mSplitBackground) : this.isBlurDrawable(this.mBackground) || this.isBlurDrawable(this.mStackedBackground);
        this.registerViewTreeObserver(bl2);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.registerViewTreeObserver(false);
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.mActionBarView = this.findViewById(R.id.action_bar);
        this.mContextView = this.findViewById(R.id.action_context_bar);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.mIsTransitioning || super.onInterceptTouchEvent(motionEvent)) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void onLayout(boolean bl2, int n2, int n3, int n4, int n5) {
        int n6 = 1;
        super.onLayout(bl2, n2, n3, n4, n5);
        View view = this.mTabContainer;
        bl2 = view != null && view.getVisibility() != 8;
        if (view != null && view.getVisibility() != 8) {
            int n7 = this.getMeasuredHeight();
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams)view.getLayoutParams();
            int n8 = view.getMeasuredHeight();
            view.layout(layoutParams.leftMargin + n2, n7 - n8 - layoutParams.bottomMargin, n4 - layoutParams.rightMargin, n7 - layoutParams.bottomMargin);
        }
        if (this.mIsSplit) {
            if (this.mSplitBackground == null) {
                return;
            }
            this.mSplitBackground.setBounds(0, 0, this.getMeasuredWidth(), this.getMeasuredHeight());
            n2 = n6;
        } else {
            if (this.mBackground != null) {
                if (this.mActionBarView.getVisibility() == 0 || this.mContextView != null && this.mContextView.getVisibility() == 0) {
                    this.mBackground.setBounds(n2, n3, n4, n5);
                } else {
                    this.mBackground.setBounds(0, 0, 0, 0);
                }
                n2 = 1;
            } else {
                n2 = 0;
            }
            this.mIsStacked = bl2;
            if (bl2 && this.mStackedBackground != null) {
                this.mStackedBackground.setBounds(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
                n2 = n6;
            }
        }
        if (n2 != 0) {
            this.invalidate();
        }
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public void onMeasure(int n2, int n3) {
        int n4 = n3;
        if (this.mActionBarView == null) {
            n4 = n3;
            if (View.MeasureSpec.getMode((int)n3) == Integer.MIN_VALUE) {
                n4 = n3;
                if (this.mHeight >= 0) {
                    n4 = View.MeasureSpec.makeMeasureSpec((int)Math.min((int)this.mHeight, (int)View.MeasureSpec.getSize((int)n3)), (int)Integer.MIN_VALUE);
                }
            }
        }
        super.onMeasure(n2, n4);
        if (this.mActionBarView == null) {
            return;
        }
        n3 = View.MeasureSpec.getMode((int)n4);
        if (this.mTabContainer == null) return;
        if (this.mTabContainer.getVisibility() == 8) return;
        if (n3 == 1073741824) return;
        n2 = !this.isCollapsed(this.mActionBarView) ? this.getMeasuredHeightWithMargins(this.mActionBarView) : (!this.isCollapsed(this.mContextView) ? this.getMeasuredHeightWithMargins(this.mContextView) : 0);
        n3 = n3 == Integer.MIN_VALUE ? View.MeasureSpec.getSize((int)n4) : Integer.MAX_VALUE;
        this.setMeasuredDimension(this.getMeasuredWidth(), Math.min((int)(n2 + this.getMeasuredHeightWithMargins(this.mTabContainer) + this.getPaddingTop() + this.getPaddingBottom()), (int)n3));
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        return true;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public void registerViewTreeObserver(boolean bl2) {
        if (Build.VERSION.SDK_INT < 16) {
            return;
        }
        if (bl2) {
            if (sDirtyField == null || sGetViewRootImplMethod == null) {
                new Thread((Runnable)new GetReflectRunnable(null)).start();
            }
            if (this.mViewTreeObserver != null && this.mViewTreeObserver.isAlive()) {
                this.mViewTreeObserver.removeOnDrawListener(this.mBlurOnDrawListener);
                this.mViewTreeObserver.removeOnGlobalLayoutListener(this.mBlurOnGlobalLayoutListener);
            }
            this.mViewTreeObserver = this.getViewTreeObserver();
            this.mViewTreeObserver.addOnDrawListener(this.mBlurOnDrawListener);
            this.mViewTreeObserver.addOnGlobalLayoutListener(this.mBlurOnGlobalLayoutListener);
            return;
        }
        if (this.mViewTreeObserver == null) return;
        if (this.mViewTreeObserver.isAlive()) {
            this.mViewTreeObserver.removeOnDrawListener(this.mBlurOnDrawListener);
            this.mViewTreeObserver.removeOnGlobalLayoutListener(this.mBlurOnGlobalLayoutListener);
        }
        this.mViewTreeObserver = null;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setPrimaryBackground(Drawable drawable2) {
        boolean bl2 = true;
        if (this.mBackground != null) {
            this.mBackground.setCallback(null);
            this.unscheduleDrawable(this.mBackground);
        }
        this.mBackground = drawable2;
        if (drawable2 != null) {
            drawable2.setCallback((Drawable.Callback)this);
            if (this.mActionBarView != null) {
                this.mBackground.setBounds(0, 0, this.getMeasuredWidth(), this.getMeasuredHeight());
            }
        }
        if (this.mIsSplit) {
            if (this.mSplitBackground != null) {
                bl2 = false;
            }
        } else if (this.mBackground != null || this.mStackedBackground != null) {
            bl2 = false;
        }
        this.setWillNotDraw(bl2);
        this.registerViewTreeObserver(this.isBlurDrawable(this.mBackground));
        this.invalidate();
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setSplitBackground(Drawable drawable2) {
        boolean bl2 = true;
        if (this.mSplitBackground != null) {
            this.mSplitBackground.setCallback(null);
            this.unscheduleDrawable(this.mSplitBackground);
        }
        this.mSplitBackground = drawable2;
        if (drawable2 != null) {
            drawable2.setCallback((Drawable.Callback)this);
            if (this.mIsSplit && this.mSplitBackground != null) {
                this.mSplitBackground.setBounds(0, 0, this.getMeasuredWidth(), this.getMeasuredHeight());
            }
        }
        if (this.mIsSplit) {
            if (this.mSplitBackground != null) {
                bl2 = false;
            }
        } else if (this.mBackground != null || this.mStackedBackground != null) {
            bl2 = false;
        }
        this.setWillNotDraw(bl2);
        this.registerViewTreeObserver(this.isBlurDrawable(this.mSplitBackground));
        this.invalidate();
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setStackedBackground(Drawable drawable2) {
        boolean bl2 = true;
        if (this.mStackedBackground != null) {
            this.mStackedBackground.setCallback(null);
            this.unscheduleDrawable(this.mStackedBackground);
        }
        this.mStackedBackground = drawable2;
        if (drawable2 != null) {
            drawable2.setCallback((Drawable.Callback)this);
            if (this.mIsStacked && this.mStackedBackground != null) {
                this.mStackedBackground.setBounds(this.mTabContainer.getLeft(), this.mTabContainer.getTop(), this.mTabContainer.getRight(), this.mTabContainer.getBottom());
            }
        }
        if (this.mIsSplit) {
            if (this.mSplitBackground != null) {
                bl2 = false;
            }
        } else if (this.mBackground != null || this.mStackedBackground != null) {
            bl2 = false;
        }
        this.setWillNotDraw(bl2);
        this.registerViewTreeObserver(this.isBlurDrawable(this.mStackedBackground));
        this.invalidate();
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setTabContainer(ScrollingTabContainerView scrollingTabContainerView) {
        if (this.mTabContainer != null) {
            this.removeView(this.mTabContainer);
        }
        if (this.mTabContainer == null && scrollingTabContainerView != null) {
            this.mTabContainer = new aqy(this.getContext());
        }
        if (scrollingTabContainerView != null) {
            ((aqy)this.mTabContainer).setTabView(scrollingTabContainerView);
            this.addView(this.mTabContainer);
            ViewGroup.LayoutParams layoutParams = this.mTabContainer.getLayoutParams();
            layoutParams.width = -1;
            layoutParams.height = -2;
            scrollingTabContainerView.setAllowCollapse(false);
            return;
        } else {
            if (this.mTabContainer == null) return;
            {
                ((aqy)this.mTabContainer).setTabView(null);
                this.mTabContainer = null;
                return;
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setTransitioning(boolean bl2) {
        this.mIsTransitioning = bl2;
        int n2 = bl2 ? 393216 : 262144;
        this.setDescendantFocusability(n2);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setVisibility(int n2) {
        super.setVisibility(n2);
        boolean bl2 = n2 == 0;
        if (this.mBackground != null) {
            this.mBackground.setVisible(bl2, false);
        }
        if (this.mStackedBackground != null) {
            this.mStackedBackground.setVisible(bl2, false);
        }
        if (this.mSplitBackground != null) {
            this.mSplitBackground.setVisible(bl2, false);
        }
    }

    public ActionMode startActionModeForChild(View view, ActionMode.Callback callback) {
        return null;
    }

    public android.view.ActionMode startActionModeForChild(View view, ActionMode.Callback callback) {
        return null;
    }

    protected boolean verifyDrawable(Drawable drawable2) {
        if (drawable2 == this.mBackground && !this.mIsSplit || drawable2 == this.mStackedBackground && this.mIsStacked || drawable2 == this.mSplitBackground && this.mIsSplit || super.verifyDrawable(drawable2)) {
            return true;
        }
        return false;
    }

    static class GetReflectRunnable
    implements Runnable {
        private GetReflectRunnable() {
        }

        /* synthetic */ GetReflectRunnable(ActionBarContainer$1 actionBarContainer$1) {
            this();
        }

        @Override
        public void run() {
            sDirtyField = ActionBarContainer.getField();
            sGetViewRootImplMethod = ActionBarContainer.getMethod();
        }
    }

}

