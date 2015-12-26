/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Configuration
 *  android.content.res.Resources
 *  android.content.res.Resources$Theme
 *  android.content.res.TypedArray
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.util.AttributeSet
 *  android.util.TypedValue
 *  android.view.ContextThemeWrapper
 *  android.view.View
 *  android.view.View$MeasureSpec
 *  android.view.ViewGroup
 *  android.view.animation.Interpolator
 *  java.lang.Math
 *  java.lang.Object
 */
package android.support.v7.internal.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.animation.PathInterpolatorCompat;
import android.support.v7.appcompat.R;
import android.support.v7.internal.view.ViewPropertyAnimatorCompatSet;
import android.support.v7.internal.widget.AbsActionBarView$1;
import android.support.v7.widget.ActionMenuPresenter;
import android.support.v7.widget.ActionMenuView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;

abstract class AbsActionBarView
extends ViewGroup {
    protected static final int FADE_DURATION = 160;
    static final Interpolator sAlphaInterpolator = PathInterpolatorCompat.create(0.33f, 0.0f, 0.66f, 1.0f);
    protected ActionMenuPresenter mActionMenuPresenter;
    protected int mContentHeight;
    protected ActionMenuView mMenuView;
    protected final Context mPopupContext;
    protected boolean mSplitActionBar;
    protected ViewGroup mSplitView;
    protected boolean mSplitWhenNarrow;
    protected final VisibilityAnimListener mVisAnimListener;
    protected ViewPropertyAnimatorCompat mVisibilityAnim;

    AbsActionBarView(Context context) {
        this(context, null);
    }

    AbsActionBarView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    AbsActionBarView(Context context, AttributeSet attributeSet, int n2) {
        super(context, attributeSet, n2);
        this.mVisAnimListener = new VisibilityAnimListener();
        attributeSet = new TypedValue();
        if (context.getTheme().resolveAttribute(R.attr.actionBarPopupTheme, (TypedValue)attributeSet, true) && attributeSet.resourceId != 0) {
            this.mPopupContext = new ContextThemeWrapper(context, attributeSet.resourceId);
            return;
        }
        this.mPopupContext = context;
    }

    protected static int next(int n2, int n3, boolean bl2) {
        if (bl2) {
            return n2 - n3;
        }
        return n2 + n3;
    }

    public void animateToVisibility(int n2) {
        if (this.mVisibilityAnim != null) {
            this.mVisibilityAnim.cancel();
        }
        if (n2 == 0) {
            if (this.getVisibility() != 0) {
                ViewCompat.setAlpha((View)this, 0.0f);
                if (this.mSplitView != null && this.mMenuView != null) {
                    ViewCompat.setAlpha((View)this.mMenuView, 0.0f);
                }
            }
            ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = ViewCompat.animate((View)this).alpha(1.0f);
            viewPropertyAnimatorCompat.setDuration(160);
            viewPropertyAnimatorCompat.setInterpolator(sAlphaInterpolator);
            if (this.mSplitView != null && this.mMenuView != null) {
                ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet = new ViewPropertyAnimatorCompatSet();
                ViewPropertyAnimatorCompat viewPropertyAnimatorCompat2 = ViewCompat.animate((View)this.mMenuView).alpha(1.0f);
                viewPropertyAnimatorCompat2.setDuration(160);
                viewPropertyAnimatorCompatSet.setListener(this.mVisAnimListener.withFinalVisibility(viewPropertyAnimatorCompat, n2));
                viewPropertyAnimatorCompatSet.play(viewPropertyAnimatorCompat).play(viewPropertyAnimatorCompat2);
                viewPropertyAnimatorCompatSet.start();
                return;
            }
            viewPropertyAnimatorCompat.setListener(this.mVisAnimListener.withFinalVisibility(viewPropertyAnimatorCompat, n2));
            viewPropertyAnimatorCompat.start();
            return;
        }
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = ViewCompat.animate((View)this).alpha(0.0f);
        viewPropertyAnimatorCompat.setDuration(160);
        viewPropertyAnimatorCompat.setInterpolator(sAlphaInterpolator);
        if (this.mSplitView != null && this.mMenuView != null) {
            ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet = new ViewPropertyAnimatorCompatSet();
            ViewPropertyAnimatorCompat viewPropertyAnimatorCompat3 = ViewCompat.animate((View)this.mMenuView).alpha(0.0f);
            viewPropertyAnimatorCompat3.setDuration(160);
            viewPropertyAnimatorCompatSet.setListener(this.mVisAnimListener.withFinalVisibility(viewPropertyAnimatorCompat, n2));
            viewPropertyAnimatorCompatSet.play(viewPropertyAnimatorCompat).play(viewPropertyAnimatorCompat3);
            viewPropertyAnimatorCompatSet.start();
            return;
        }
        viewPropertyAnimatorCompat.setListener(this.mVisAnimListener.withFinalVisibility(viewPropertyAnimatorCompat, n2));
        viewPropertyAnimatorCompat.start();
    }

    public boolean canShowOverflowMenu() {
        if (this.isOverflowReserved() && this.getVisibility() == 0) {
            return true;
        }
        return false;
    }

    public void dismissPopupMenus() {
        if (this.mActionMenuPresenter != null) {
            this.mActionMenuPresenter.dismissPopupMenus();
        }
    }

    public int getAnimatedVisibility() {
        if (this.mVisibilityAnim != null) {
            return this.mVisAnimListener.mFinalVisibility;
        }
        return this.getVisibility();
    }

    public int getContentHeight() {
        return this.mContentHeight;
    }

    public boolean hideOverflowMenu() {
        if (this.mActionMenuPresenter != null) {
            return this.mActionMenuPresenter.hideOverflowMenu();
        }
        return false;
    }

    public boolean isOverflowMenuShowPending() {
        if (this.mActionMenuPresenter != null) {
            return this.mActionMenuPresenter.isOverflowMenuShowPending();
        }
        return false;
    }

    public boolean isOverflowMenuShowing() {
        if (this.mActionMenuPresenter != null) {
            return this.mActionMenuPresenter.isOverflowMenuShowing();
        }
        return false;
    }

    public boolean isOverflowReserved() {
        if (this.mActionMenuPresenter != null && this.mActionMenuPresenter.isOverflowReserved()) {
            return true;
        }
        return false;
    }

    protected int measureChildView(View view, int n2, int n3, int n4) {
        view.measure(View.MeasureSpec.makeMeasureSpec((int)n2, (int)Integer.MIN_VALUE), n3);
        return Math.max((int)0, (int)(n2 - view.getMeasuredWidth() - n4));
    }

    protected void onConfigurationChanged(Configuration configuration) {
        if (Build.VERSION.SDK_INT >= 8) {
            super.onConfigurationChanged(configuration);
        }
        TypedArray typedArray = this.getContext().obtainStyledAttributes(null, R.styleable.ActionBar, R.attr.actionBarStyle, 0);
        this.setContentHeight(typedArray.getLayoutDimension(R.styleable.ActionBar_height, 0));
        typedArray.recycle();
        if (this.mActionMenuPresenter != null) {
            this.mActionMenuPresenter.onConfigurationChanged(configuration);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    protected int positionChild(View view, int n2, int n3, int n4, boolean bl2) {
        int n5 = view.getMeasuredWidth();
        int n6 = view.getMeasuredHeight();
        n3 = (n4 - n6) / 2 + n3;
        if (bl2) {
            view.layout(n2 - n5, n3, n2, n6 + n3);
        } else {
            view.layout(n2, n3, n2 + n5, n6 + n3);
        }
        n2 = n5;
        if (!bl2) return n2;
        return - n5;
    }

    public void postShowOverflowMenu() {
        this.post((Runnable)new AbsActionBarView$1(this));
    }

    public void setContentHeight(int n2) {
        this.mContentHeight = n2;
        this.requestLayout();
    }

    public void setSplitToolbar(boolean bl2) {
        this.mSplitActionBar = bl2;
    }

    public void setSplitView(ViewGroup viewGroup) {
        this.mSplitView = viewGroup;
    }

    public void setSplitWhenNarrow(boolean bl2) {
        this.mSplitWhenNarrow = bl2;
    }

    public boolean showOverflowMenu() {
        if (this.mActionMenuPresenter != null) {
            return this.mActionMenuPresenter.showOverflowMenu();
        }
        return false;
    }

    public class VisibilityAnimListener
    implements ViewPropertyAnimatorListener {
        private boolean mCanceled;
        int mFinalVisibility;

        protected VisibilityAnimListener() {
            this.mCanceled = false;
        }

        @Override
        public void onAnimationCancel(View view) {
            this.mCanceled = true;
        }

        /*
         * Enabled aggressive block sorting
         * Lifted jumps to return sites
         */
        @Override
        public void onAnimationEnd(View view) {
            if (this.mCanceled) {
                return;
            }
            AbsActionBarView.this.mVisibilityAnim = null;
            AbsActionBarView.this.setVisibility(this.mFinalVisibility);
            if (AbsActionBarView.this.mSplitView == null) return;
            if (AbsActionBarView.this.mMenuView == null) return;
            AbsActionBarView.this.mMenuView.setVisibility(this.mFinalVisibility);
        }

        @Override
        public void onAnimationStart(View view) {
            AbsActionBarView.this.setVisibility(0);
            this.mCanceled = false;
        }

        public VisibilityAnimListener withFinalVisibility(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, int n2) {
            AbsActionBarView.this.mVisibilityAnim = viewPropertyAnimatorCompat;
            this.mFinalVisibility = n2;
            return this;
        }
    }

}

