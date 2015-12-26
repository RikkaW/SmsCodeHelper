/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.animation.Animator
 *  android.animation.Animator$AnimatorListener
 *  android.animation.TimeInterpolator
 *  android.animation.ValueAnimator
 *  android.animation.ValueAnimator$AnimatorUpdateListener
 *  android.content.Context
 *  android.content.res.Configuration
 *  android.content.res.Resources
 *  android.graphics.Canvas
 *  android.graphics.Paint
 *  android.graphics.drawable.Drawable
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.text.TextUtils
 *  android.text.TextUtils$TruncateAt
 *  android.util.AttributeSet
 *  android.util.DisplayMetrics
 *  android.view.View
 *  android.view.View$MeasureSpec
 *  android.view.View$OnClickListener
 *  android.view.View$OnLongClickListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewParent
 *  android.view.accessibility.AccessibilityEvent
 *  android.view.accessibility.AccessibilityNodeInfo
 *  android.view.animation.Animation
 *  android.view.animation.DecelerateInterpolator
 *  android.view.animation.Interpolator
 *  android.widget.AbsListView
 *  android.widget.AbsListView$LayoutParams
 *  android.widget.BaseAdapter
 *  android.widget.HorizontalScrollView
 *  android.widget.ImageView
 *  android.widget.SpinnerAdapter
 *  android.widget.TextView
 *  android.widget.Toast
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 */
package android.support.v7.internal.widget;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.app.ActionBar;
import android.support.v7.appcompat.R;
import android.support.v7.internal.view.ActionBarPolicy;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.support.v7.internal.widget.ScrollingTabContainerView$1;
import android.support.v7.internal.widget.ScrollingTabContainerView$SlidingTabStrip$1;
import android.support.v7.internal.widget.ScrollingTabContainerView$SlidingTabStrip$2;
import android.support.v7.internal.widget.SpinnerCompat;
import android.support.v7.internal.widget.TintTypedArray;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Iterator;

public class ScrollingTabContainerView
extends HorizontalScrollView
implements AdapterViewCompat.OnItemClickListener {
    private static final int ANIMATION_DURATION = 300;
    private static final int FADE_DURATION = 200;
    private static final int FIXED_WRAP_GUTTER_MIN = 16;
    private static final int MODE_FIXED = 1;
    public static final int MODE_SCROLLABLE = 0;
    private static final int MOTION_NON_ADJACENT_OFFSET = 24;
    private static final String TAG = "ScrollingTabContainerView";
    private static final Interpolator sAlphaInterpolator = new DecelerateInterpolator();
    private boolean isAtToolbar;
    private boolean mAllowCollapse;
    private int mContentHeight;
    int mMaxTabWidth;
    private int mMode;
    private int mSelectedTabIndex;
    int mStackedTabMaxWidth;
    private TabClickListener mTabClickListener;
    private SlidingTabStrip mTabLayout;
    Runnable mTabSelector;
    private SpinnerCompat mTabSpinner;
    protected final VisibilityAnimListener mVisAnimListener;
    protected ViewPropertyAnimatorCompat mVisibilityAnim;

    public ScrollingTabContainerView(Context context) {
        this(context, null);
    }

    public ScrollingTabContainerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.mzActionBarTabScrollViewStyle);
    }

    public ScrollingTabContainerView(Context object, AttributeSet attributeSet, int n2) {
        super((Context)object, attributeSet, n2);
        this.mVisAnimListener = new VisibilityAnimListener();
        this.mMode = 0;
        this.setOverScrollMode(2);
        this.setHorizontalFadingEdgeEnabled(true);
        this.setFadingEdgeLength(this.getResources().getDimensionPixelSize(R.dimen.mz_action_bar_tab_scroll_fading_edge_length));
        object = ActionBarPolicy.get((Context)object);
        this.setContentHeight(object.getTabContainerHeight());
        this.mStackedTabMaxWidth = object.getStackedTabMaxWidth();
        this.mTabLayout = this.createTabLayout();
        this.addView((View)this.mTabLayout, new ViewGroup.LayoutParams(-2, -1));
    }

    /*
     * Enabled aggressive block sorting
     */
    private int calculateScrollXForTab(int n2, float f2) {
        int n3 = 0;
        int n4 = 0;
        if (this.mMode != 0) return n3;
        View view = this.mTabLayout.getChildAt(n2);
        View view2 = n2 + 1 < this.mTabLayout.getChildCount() ? this.mTabLayout.getChildAt(n2 + 1) : null;
        n2 = view != null ? view.getWidth() : 0;
        n3 = n4;
        if (view2 != null) {
            n3 = view2.getWidth();
        }
        float f3 = view.getLeft();
        return (int)((float)(n3 + n2) * f2 * 0.5f + f3 + (float)view.getWidth() * 0.5f - (float)this.getWidth() * 0.5f);
    }

    private SpinnerCompat createSpinner() {
        SpinnerCompat spinnerCompat = new SpinnerCompat(this.getContext(), null, R.attr.actionDropDownStyle);
        spinnerCompat.setLayoutParams((ViewGroup.LayoutParams)new LinearLayoutCompat.LayoutParams(-2, -1));
        spinnerCompat.setOnItemClickListenerInt(this);
        return spinnerCompat;
    }

    private SlidingTabStrip createTabLayout() {
        SlidingTabStrip slidingTabStrip = new SlidingTabStrip(this.getContext(), null, R.attr.actionBarTabBarStyle);
        slidingTabStrip.setGravity(17);
        slidingTabStrip.setLayoutParams((ViewGroup.LayoutParams)new LinearLayoutCompat.LayoutParams(-2, -1));
        return slidingTabStrip;
    }

    private TabView createTabView(ActionBar.Tab object, boolean bl2) {
        object = new TabView(this.getContext(), (ActionBar.Tab)object, bl2);
        if (bl2) {
            object.setBackgroundDrawable(null);
            object.setLayoutParams((ViewGroup.LayoutParams)new AbsListView.LayoutParams(-1, this.mContentHeight));
            return object;
        }
        object.setFocusable(true);
        if (this.mTabClickListener == null) {
            this.mTabClickListener = new TabClickListener(null);
        }
        object.setOnClickListener((View.OnClickListener)this.mTabClickListener);
        return object;
    }

    private int dpToPx(int n2) {
        return Math.round((float)(this.getResources().getDisplayMetrics().density * (float)n2));
    }

    private static boolean isAnimationRunning(Animation animation) {
        if (animation != null && animation.hasStarted() && !animation.hasEnded()) {
            return true;
        }
        return false;
    }

    private boolean isCollapsed() {
        if (this.mTabSpinner != null && this.mTabSpinner.getParent() == this) {
            return true;
        }
        return false;
    }

    private void performCollapse() {
        if (this.isCollapsed()) {
            return;
        }
        if (this.mTabSpinner == null) {
            this.mTabSpinner = this.createSpinner();
        }
        this.removeView((View)this.mTabLayout);
        this.addView((View)this.mTabSpinner, new ViewGroup.LayoutParams(-2, -1));
        if (this.mTabSpinner.getAdapter() == null) {
            this.mTabSpinner.setAdapter((SpinnerAdapter)new TabAdapter(null));
        }
        if (this.mTabSelector != null) {
            this.removeCallbacks(this.mTabSelector);
            this.mTabSelector = null;
        }
        this.mTabSpinner.setSelection(this.mSelectedTabIndex);
    }

    private boolean performExpand() {
        if (!this.isCollapsed()) {
            return false;
        }
        this.removeView((View)this.mTabSpinner);
        this.addView((View)this.mTabLayout, new ViewGroup.LayoutParams(-2, -1));
        this.setTabSelected(this.mTabSpinner.getSelectedItemPosition());
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void setSelectedTabView(int n2) {
        int n3 = this.mTabLayout.getChildCount();
        int n4 = 0;
        while (n4 < n3) {
            View view = this.mTabLayout.getChildAt(n4);
            boolean bl2 = n4 == n2;
            view.setSelected(bl2);
            ++n4;
        }
    }

    public void addTab(ActionBar.Tab object, int n2, boolean bl2) {
        object = this.createTabView((ActionBar.Tab)object, false);
        this.mTabLayout.addView((View)object, n2, (ViewGroup.LayoutParams)new LinearLayoutCompat.LayoutParams(-2, -1));
        if (this.mTabSpinner != null) {
            ((TabAdapter)this.mTabSpinner.getAdapter()).notifyDataSetChanged();
        }
        if (bl2) {
            object.setSelected(true);
            this.mTabLayout.setIndicatorPositionFromTabPosition(this.mTabLayout.getChildCount() - 1, 0.0f);
        }
        if (this.mAllowCollapse) {
            this.requestLayout();
        }
    }

    public void addTab(ActionBar.Tab object, boolean bl2) {
        object = this.createTabView((ActionBar.Tab)object, false);
        this.mTabLayout.addView((View)object, (ViewGroup.LayoutParams)new LinearLayoutCompat.LayoutParams(-2, -1));
        if (this.mTabSpinner != null) {
            ((TabAdapter)this.mTabSpinner.getAdapter()).notifyDataSetChanged();
        }
        if (bl2) {
            object.setSelected(true);
            this.mTabLayout.setIndicatorPositionFromTabPosition(this.mTabLayout.getChildCount() - 1, 0.0f);
        }
        if (this.mAllowCollapse) {
            this.requestLayout();
        }
    }

    public void animateToTab(int n2) {
        View view = this.mTabLayout.getChildAt(n2);
        if (this.mTabSelector != null) {
            this.removeCallbacks(this.mTabSelector);
        }
        this.mTabSelector = new ScrollingTabContainerView$1(this, view, n2);
        this.post(this.mTabSelector);
    }

    public void animateToVisibility(int n2) {
        if (this.mVisibilityAnim != null) {
            this.mVisibilityAnim.cancel();
        }
        if (n2 == 0) {
            if (this.getVisibility() != 0) {
                ViewCompat.setAlpha((View)this, 0.0f);
            }
            ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = ViewCompat.animate((View)this).alpha(1.0f);
            viewPropertyAnimatorCompat.setDuration(200);
            viewPropertyAnimatorCompat.setInterpolator(sAlphaInterpolator);
            viewPropertyAnimatorCompat.setListener(this.mVisAnimListener.withFinalVisibility(viewPropertyAnimatorCompat, n2));
            viewPropertyAnimatorCompat.start();
            return;
        }
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = ViewCompat.animate((View)this).alpha(0.0f);
        viewPropertyAnimatorCompat.setDuration(200);
        viewPropertyAnimatorCompat.setInterpolator(sAlphaInterpolator);
        viewPropertyAnimatorCompat.setListener(this.mVisAnimListener.withFinalVisibility(viewPropertyAnimatorCompat, n2));
        viewPropertyAnimatorCompat.start();
    }

    public int getTabStripWidth() {
        return this.mTabLayout.getMeasuredWidth();
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mTabSelector != null) {
            this.post(this.mTabSelector);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void onConfigurationChanged(Configuration object) {
        if (Build.VERSION.SDK_INT >= 8) {
            super.onConfigurationChanged((Configuration)object);
        }
        object = ActionBarPolicy.get(this.getContext());
        int n2 = this.isAtToolbar ? object.getActionBarHeight() : object.getTabContainerHeight();
        this.setContentHeight(n2);
        this.mStackedTabMaxWidth = object.getStackedTabMaxWidth();
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mTabSelector != null) {
            this.removeCallbacks(this.mTabSelector);
        }
    }

    @Override
    public void onItemClick(AdapterViewCompat<?> adapterViewCompat, View view, int n2, long l2) {
        ((TabView)view).getTab().select();
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void onLayout(boolean bl2, int n2, int n3, int n4, int n5) {
        View view;
        super.onLayout(bl2, n2, n3, n4, n5);
        if (this.mTabLayout.getParent() != this || (view = this.mTabLayout.getChildAt(this.mSelectedTabIndex)) == null || (n2 = view.getLeft() - (this.getWidth() - view.getWidth()) / 2) <= 0) {
            return;
        }
        this.setScrollX(n2);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void onMeasure(int n2, int n3) {
        n3 = 1;
        int n4 = View.MeasureSpec.getMode((int)n2);
        boolean bl2 = n4 == 1073741824;
        this.setFillViewport(bl2);
        int n5 = this.mTabLayout.getChildCount();
        if (n5 > 1 && (n4 == 1073741824 || n4 == Integer.MIN_VALUE)) {
            this.mMaxTabWidth = n5 > 2 ? (int)((float)View.MeasureSpec.getSize((int)n2) * 0.4f) : View.MeasureSpec.getSize((int)n2) / 2;
            this.mMaxTabWidth = Math.min((int)this.mMaxTabWidth, (int)this.mStackedTabMaxWidth);
        } else {
            this.mMaxTabWidth = -1;
        }
        n4 = View.MeasureSpec.makeMeasureSpec((int)this.mContentHeight, (int)1073741824);
        if (bl2 || !this.mAllowCollapse) {
            n3 = 0;
        }
        if (n3 != 0) {
            this.mTabLayout.measure(0, n4);
            if (this.mTabLayout.getMeasuredWidth() > View.MeasureSpec.getSize((int)n2)) {
                this.performCollapse();
            } else {
                this.performExpand();
            }
        } else {
            this.performExpand();
        }
        n3 = this.getMeasuredWidth();
        super.onMeasure(n2, n4);
        n2 = this.getMeasuredWidth();
        if (bl2 && n3 != n2) {
            this.setTabSelected(this.mSelectedTabIndex);
        }
    }

    public void removeAllTabs() {
        this.mTabLayout.removeAllViews();
        if (this.mTabSpinner != null) {
            ((TabAdapter)this.mTabSpinner.getAdapter()).notifyDataSetChanged();
        }
        if (this.mAllowCollapse) {
            this.requestLayout();
        }
    }

    public void removeTabAt(int n2) {
        this.mTabLayout.removeViewAt(n2);
        this.mTabLayout.resetPosition(n2);
        if (this.mTabSpinner != null) {
            ((TabAdapter)this.mTabSpinner.getAdapter()).notifyDataSetChanged();
        }
        if (this.mAllowCollapse) {
            this.requestLayout();
        }
    }

    public void setAllowCollapse(boolean bl2) {
        this.mAllowCollapse = bl2;
    }

    public void setContentHeight(int n2) {
        this.mContentHeight = n2;
        this.requestLayout();
    }

    public void setIndicatorDrawable(Drawable drawable2) {
        this.mTabLayout.setIndicatorDrawable(drawable2);
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public void setScrollPosition(int n2, float f2, boolean bl2) {
        if (ScrollingTabContainerView.isAnimationRunning(this.getAnimation())) {
            return;
        }
        if (n2 < 0) return;
        if (n2 >= this.mTabLayout.getChildCount()) return;
        this.mTabLayout.setIndicatorPositionFromTabPosition(n2, f2);
        this.smoothScrollTo(this.calculateScrollXForTab(n2, f2), 0);
        if (!bl2) return;
        this.setSelectedTabView(Math.round((float)((float)n2 + f2)));
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setTabSelected(int n2) {
        this.mSelectedTabIndex = n2;
        int n3 = this.mTabLayout.getChildCount();
        for (int i2 = 0; i2 < n3; ++i2) {
            View view = this.mTabLayout.getChildAt(i2);
            boolean bl2 = i2 == n2;
            view.setSelected(bl2);
            if (!bl2) continue;
            this.animateToTab(n2);
        }
        if (this.mTabSpinner != null && n2 >= 0) {
            this.mTabSpinner.setSelection(n2);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void showAtToolbar(boolean bl2) {
        if (this.isAtToolbar == bl2) {
            return;
        }
        int n2 = 1;
        if (n2 != 0) {
            this.isAtToolbar = bl2;
            Object object = ActionBarPolicy.get(this.getContext());
            n2 = this.isAtToolbar ? object.getActionBarHeight() : object.getTabContainerHeight();
            this.setContentHeight(n2);
            if (this.mTabLayout != null && this.mTabLayout.getChildCount() > 0) {
                Object object2;
                int n3 = this.mTabLayout.getChildCount();
                object = new ArrayList();
                int n4 = this.mSelectedTabIndex;
                for (n2 = 0; n2 < n3; ++n2) {
                    object2 = (TabView)this.mTabLayout.getChildAt(n2);
                    if (object2.isSelected()) {
                        n4 = n2;
                    }
                    object.add((Object)object2.getTab());
                }
                this.mTabLayout.removeAllViews();
                object = object.iterator();
                while (object.hasNext()) {
                    object2 = (ActionBar.Tab)object.next();
                    bl2 = object2.getPosition() == n4;
                    this.addTab((ActionBar.Tab)object2, bl2);
                }
                this.setTabSelected(n4);
            }
        }
    }

    public void updateTab(int n2) {
        ((TabView)this.mTabLayout.getChildAt(n2)).update();
        if (this.mTabSpinner != null) {
            ((TabAdapter)this.mTabSpinner.getAdapter()).notifyDataSetChanged();
        }
        if (this.mAllowCollapse) {
            this.requestLayout();
        }
    }

    class SlidingTabStrip
    extends LinearLayoutCompat {
        private ValueAnimator mIndicatorAnimator;
        private Drawable mIndicatorDrawable;
        private int mIndicatorLeft;
        private int mIndicatorRight;
        private int mSelectedIndicatorColor;
        private int mSelectedIndicatorHeight;
        private final Paint mSelectedIndicatorPaint;
        private int mSelectedPosition;
        private float mSelectionOffset;

        public SlidingTabStrip(Context context, AttributeSet attributeSet) {
            this(context, attributeSet, 0);
        }

        public SlidingTabStrip(Context context, AttributeSet attributeSet, int n2) {
            super(context, attributeSet, n2);
            this.mSelectedPosition = -1;
            this.mIndicatorLeft = -1;
            this.mIndicatorRight = -1;
            this.setWillNotDraw(false);
            this.mSelectedIndicatorPaint = new Paint();
            ScrollingTabContainerView.this = TintTypedArray.obtainStyledAttributes(context, attributeSet, R.styleable.MzActionBarTabBar, n2, 0);
            this.mSelectedIndicatorColor = ScrollingTabContainerView.this.getColor(R.styleable.MzActionBarTabBar_mzTabBarIndicatorColor, this.getResources().getColor(R.color.mz_action_bar_tab_indicator_default_color));
            this.mSelectedIndicatorPaint.setColor(this.mSelectedIndicatorColor);
            this.mSelectedIndicatorHeight = ScrollingTabContainerView.this.getDimensionPixelSize(R.styleable.MzActionBarTabBar_mzTabBarIndicatorHeight, this.getResources().getDimensionPixelSize(R.dimen.mz_action_bar_tab_indicator_height));
            this.mIndicatorDrawable = ScrollingTabContainerView.this.getDrawable(R.styleable.MzActionBarTabBar_mzTabBarIndicatorDrawable);
            ScrollingTabContainerView.this.recycle();
            this.setMotionEventSplittingEnabled(false);
        }

        static /* synthetic */ float access$1002(SlidingTabStrip slidingTabStrip, float f2) {
            slidingTabStrip.mSelectionOffset = f2;
            return f2;
        }

        static /* synthetic */ void access$800(SlidingTabStrip slidingTabStrip, int n2, int n3) {
            slidingTabStrip.setIndicatorPosition(n2, n3);
        }

        static /* synthetic */ int access$902(SlidingTabStrip slidingTabStrip, int n2) {
            slidingTabStrip.mSelectedPosition = n2;
            return n2;
        }

        private void setIndicatorPosition(int n2, int n3) {
            if (n2 != this.mIndicatorLeft || n3 != this.mIndicatorRight) {
                this.mIndicatorLeft = n2;
                this.mIndicatorRight = n3;
                ViewCompat.postInvalidateOnAnimation((View)this);
            }
        }

        /*
         * Enabled aggressive block sorting
         */
        private void updateIndicatorPosition() {
            int n2;
            int n3;
            View view = this.getChildAt(this.mSelectedPosition);
            if (view != null && view.getWidth() > 0) {
                int n4;
                int n5 = view.getLeft();
                n3 = n4 = view.getRight();
                n2 = n5;
                if (this.mSelectionOffset > 0.0f) {
                    n3 = n4;
                    n2 = n5;
                    if (this.mSelectedPosition < this.getChildCount() - 1) {
                        view = this.getChildAt(this.mSelectedPosition + 1);
                        float f2 = this.mSelectionOffset;
                        float f3 = view.getLeft();
                        float f4 = this.mSelectionOffset;
                        n2 = (int)((float)n5 * (1.0f - f4) + f2 * f3);
                        f2 = this.mSelectionOffset;
                        f3 = view.getRight();
                        f4 = this.mSelectionOffset;
                        n3 = (int)((float)n4 * (1.0f - f4) + f3 * f2);
                    }
                }
            } else {
                n3 = -1;
                n2 = -1;
            }
            this.setIndicatorPosition(n2, n3);
        }

        void animateIndicatorToPosition(int n2, int n3) {
            View view;
            if (ViewCompat.getLayoutDirection((View)this) == 1) {
                // empty if block
            }
            if ((view = this.getChildAt(n2)) == null) {
                return;
            }
            int n4 = view.getLeft();
            int n5 = view.getRight();
            int n6 = this.mIndicatorLeft;
            int n7 = this.mIndicatorRight;
            if ((n6 != n4 || n7 != n5) && n6 >= 0 && n7 >= 0) {
                if (this.mIndicatorAnimator != null && this.mIndicatorAnimator.isRunning()) {
                    this.mIndicatorAnimator.cancel();
                }
                this.mIndicatorAnimator = ValueAnimator.ofFloat((float[])new float[]{0.0f, 1.0f});
                this.mIndicatorAnimator.setDuration((long)n3);
                this.mIndicatorAnimator.setInterpolator((TimeInterpolator)new DecelerateInterpolator());
                this.mIndicatorAnimator.addUpdateListener((ValueAnimator.AnimatorUpdateListener)new ScrollingTabContainerView$SlidingTabStrip$1(this, n6, n4, n7, n5));
                this.mIndicatorAnimator.addListener((Animator.AnimatorListener)new ScrollingTabContainerView$SlidingTabStrip$2(this, n2));
                this.mIndicatorAnimator.start();
                return;
            }
            this.mSelectedPosition = n2;
            this.mSelectionOffset = 0.0f;
        }

        public void cancelIndicatorAnim() {
            if (this.mIndicatorAnimator != null && this.mIndicatorAnimator.isRunning()) {
                this.mIndicatorAnimator.cancel();
            }
        }

        public boolean isIndicatorAnimRunning() {
            if (this.mIndicatorAnimator != null && this.mIndicatorAnimator.isRunning()) {
                return true;
            }
            return false;
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        @Override
        protected void onDraw(Canvas canvas) {
            if (this.mIndicatorLeft < 0 || this.mIndicatorRight <= this.mIndicatorLeft) return;
            if (this.mIndicatorDrawable != null) {
                int n2 = this.mIndicatorDrawable.getIntrinsicHeight();
                this.mIndicatorDrawable.setBounds(this.mIndicatorLeft, this.getHeight() - n2, this.mIndicatorRight, this.getHeight());
                this.mIndicatorDrawable.draw(canvas);
                return;
            }
            canvas.drawRect((float)this.mIndicatorLeft, (float)(this.getHeight() - this.mSelectedIndicatorHeight), (float)this.mIndicatorRight, (float)this.getHeight(), this.mSelectedIndicatorPaint);
        }

        /*
         * Enabled aggressive block sorting
         * Lifted jumps to return sites
         */
        @Override
        protected void onLayout(boolean bl2, int n2, int n3, int n4, int n5) {
            super.onLayout(bl2, n2, n3, n4, n5);
            int n6 = this.getChildCount();
            if (n6 == 0) {
                return;
            }
            n3 = 0;
            for (n2 = 0; n2 < n6; n3 += this.getChildAt((int)n2).getMeasuredWidth(), ++n2) {
            }
            if (n3 < this.getMeasuredWidth()) {
                n2 = 0;
                if (n6 == 2) {
                    n2 = this.getResources().getDimensionPixelSize(R.dimen.mz_action_bar_tab_bar_inset_2_tab);
                } else if (n6 == 3) {
                    n2 = this.getResources().getDimensionPixelSize(R.dimen.mz_action_bar_tab_bar_inset_3_tab);
                }
                int n7 = (this.getMeasuredWidth() - n2 * 2) / n6;
                n4 = 0;
                n3 = n2;
                while (n4 < n6) {
                    View view = this.getChildAt(n4);
                    int n8 = view.getMeasuredWidth();
                    if (n8 > n7) {
                        n3 = n5 = n3 - (n8 - n7) / 2;
                        if (n5 < 0) {
                            n3 = 0;
                        }
                    } else {
                        n3 += (n7 - n8) / 2;
                    }
                    view.layout(n3, view.getTop(), n8 + n3, view.getBottom());
                    n3 = n7 * ++n4 + n2;
                }
            }
            if (ScrollingTabContainerView.isAnimationRunning(this.getAnimation())) return;
            this.updateIndicatorPosition();
        }

        /*
         * Enabled aggressive block sorting
         * Lifted jumps to return sites
         */
        @Override
        protected void onMeasure(int n2, int n3) {
            Object object;
            int n4;
            super.onMeasure(n2, n3);
            if (View.MeasureSpec.getMode((int)n2) != 1073741824) {
                return;
            }
            if (ScrollingTabContainerView.this.mMode != 1) return;
            int n5 = this.getChildCount();
            int n6 = View.MeasureSpec.makeMeasureSpec((int)0, (int)0);
            int n7 = 0;
            for (n4 = 0; n4 < n5; ++n4) {
                object = this.getChildAt(n4);
                object.measure(n6, n3);
                n7 = Math.max((int)n7, (int)object.getMeasuredWidth());
            }
            if (n7 <= 0) return;
            n4 = ScrollingTabContainerView.this.dpToPx(16);
            if (n7 * n5 <= this.getMeasuredWidth() - n4 * 2) {
                for (n4 = 0; n4 < n5; ++n4) {
                    object = (LinearLayoutCompat.LayoutParams)this.getChildAt(n4).getLayoutParams();
                    object.width = n7;
                    object.weight = 0.0f;
                }
            }
            super.onMeasure(n2, n3);
        }

        public void resetPosition(int n2) {
            if (this.mSelectedPosition == n2) {
                this.mIndicatorLeft = -1;
                this.mIndicatorRight = -1;
                return;
            }
            this.setIndicatorPositionFromTabPosition(Math.max((int)0, (int)(n2 - 1)), 0.0f);
        }

        void setIndicatorDrawable(Drawable drawable2) {
            if (this.mIndicatorDrawable != drawable2) {
                this.mIndicatorDrawable = drawable2;
                this.invalidate();
            }
        }

        void setIndicatorPositionFromTabPosition(int n2, float f2) {
            if (ScrollingTabContainerView.isAnimationRunning(this.getAnimation())) {
                return;
            }
            this.cancelIndicatorAnim();
            this.mSelectedPosition = n2;
            this.mSelectionOffset = f2;
            this.updateIndicatorPosition();
        }

        void setSelectedIndicatorColor(int n2) {
            this.mSelectedIndicatorPaint.setColor(n2);
            ViewCompat.postInvalidateOnAnimation((View)this);
        }

        void setSelectedIndicatorHeight(int n2) {
            this.mSelectedIndicatorHeight = n2;
            ViewCompat.postInvalidateOnAnimation((View)this);
        }
    }

    class TabAdapter
    extends BaseAdapter {
        private TabAdapter() {
        }

        /* synthetic */ TabAdapter(ScrollingTabContainerView$1 scrollingTabContainerView$1) {
            this();
        }

        public int getCount() {
            return ScrollingTabContainerView.this.mTabLayout.getChildCount();
        }

        public Object getItem(int n2) {
            return ((TabView)ScrollingTabContainerView.this.mTabLayout.getChildAt(n2)).getTab();
        }

        public long getItemId(int n2) {
            return n2;
        }

        public View getView(int n2, View view, ViewGroup viewGroup) {
            if (view == null) {
                return ScrollingTabContainerView.this.createTabView((ActionBar.Tab)this.getItem(n2), true);
            }
            ((TabView)view).bindTab((ActionBar.Tab)this.getItem(n2));
            return view;
        }
    }

    class TabClickListener
    implements View.OnClickListener {
        private TabClickListener() {
        }

        /* synthetic */ TabClickListener(ScrollingTabContainerView$1 scrollingTabContainerView$1) {
            this();
        }

        /*
         * Enabled aggressive block sorting
         */
        public void onClick(View view) {
            ((TabView)view).getTab().select();
            int n2 = ScrollingTabContainerView.this.mTabLayout.getChildCount();
            int n3 = 0;
            while (n3 < n2) {
                View view2 = ScrollingTabContainerView.this.mTabLayout.getChildAt(n3);
                boolean bl2 = view2 == view;
                view2.setSelected(bl2);
                ++n3;
            }
        }
    }

    class TabView
    extends LinearLayoutCompat
    implements View.OnLongClickListener {
        private final int[] BG_ATTRS;
        private View mCustomView;
        private ImageView mIconView;
        private ActionBar.Tab mTab;
        private TextView mTextView;

        /*
         * Enabled aggressive block sorting
         */
        public TabView(Context context, ActionBar.Tab tab, boolean bl2) {
            void var4_5;
            int n2 = ((ScrollingTabContainerView)Object.this).isAtToolbar ? R.attr.mzToolBarTabStyle : R.attr.actionBarTabStyle;
            super(context, null, n2);
            this.BG_ATTRS = new int[]{16842964};
            this.mTab = tab;
            int[] arrn = this.BG_ATTRS;
            n2 = ((ScrollingTabContainerView)Object.this).isAtToolbar ? R.attr.mzToolBarTabStyle : R.attr.actionBarTabStyle;
            Object.this = TintTypedArray.obtainStyledAttributes(context, null, arrn, n2, 0);
            if (Object.this.hasValue(0)) {
                this.setBackgroundDrawable(Object.this.getDrawable(0));
            }
            Object.this.recycle();
            if (var4_5 != false) {
                this.setGravity(8388627);
            }
            this.update();
        }

        public void bindTab(ActionBar.Tab tab) {
            this.mTab = tab;
            this.update();
        }

        public ActionBar.Tab getTab() {
            return this.mTab;
        }

        @Override
        public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(accessibilityEvent);
            accessibilityEvent.setClassName((CharSequence)ActionBar.Tab.class.getName());
        }

        @Override
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            if (Build.VERSION.SDK_INT >= 14) {
                accessibilityNodeInfo.setClassName((CharSequence)ActionBar.Tab.class.getName());
            }
        }

        public boolean onLongClick(View object) {
            object = new int[2];
            this.getLocationOnScreen((int[])object);
            Context context = this.getContext();
            int n2 = this.getWidth();
            int n3 = this.getHeight();
            int n4 = context.getResources().getDisplayMetrics().widthPixels;
            context = Toast.makeText((Context)context, (CharSequence)this.mTab.getContentDescription(), (int)0);
            context.setGravity(49, (int)(object[0] + n2 / 2 - n4 / 2), n3);
            context.show();
            return true;
        }

        @Override
        public void onMeasure(int n2, int n3) {
            super.onMeasure(n2, n3);
        }

        /*
         * Enabled aggressive block sorting
         */
        public void setSelected(boolean bl2) {
            boolean bl3 = this.isSelected() != bl2;
            super.setSelected(bl2);
            if (bl3 && bl2) {
                this.sendAccessibilityEvent(4);
            }
        }

        /*
         * Enabled aggressive block sorting
         */
        public void update() {
            ActionBar.Tab tab = this.mTab;
            Object object = tab.getCustomView();
            if (object != null) {
                ViewParent viewParent = object.getParent();
                if (viewParent != this) {
                    if (viewParent != null) {
                        ((ViewGroup)viewParent).removeView((View)object);
                    }
                    this.addView((View)object);
                }
                this.mCustomView = object;
                if (this.mTextView != null) {
                    this.mTextView.setVisibility(8);
                }
                if (this.mIconView != null) {
                    this.mIconView.setVisibility(8);
                    this.mIconView.setImageDrawable(null);
                }
            } else {
                Object object2;
                if (this.mCustomView != null) {
                    this.removeView(this.mCustomView);
                    this.mCustomView = null;
                }
                Object object3 = tab.getIcon();
                object = tab.getText();
                if (object3 != null) {
                    if (this.mIconView == null) {
                        object2 = new ImageView(this.getContext());
                        LinearLayoutCompat.LayoutParams layoutParams = new LinearLayoutCompat.LayoutParams(-2, -2);
                        layoutParams.gravity = 16;
                        object2.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
                        this.addView((View)object2, 0);
                        this.mIconView = object2;
                    }
                    this.mIconView.setImageDrawable((Drawable)object3);
                    this.mIconView.setVisibility(0);
                } else if (this.mIconView != null) {
                    this.mIconView.setVisibility(8);
                    this.mIconView.setImageDrawable(null);
                }
                boolean bl2 = !TextUtils.isEmpty((CharSequence)object);
                if (bl2) {
                    if (this.mTextView == null) {
                        object3 = this.getContext();
                        int n2 = Object.this.isAtToolbar ? R.attr.mzToolBarTabTextStyle : R.attr.actionBarTabTextStyle;
                        object3 = new AppCompatTextView((Context)object3, null, n2);
                        object3.setEllipsize(TextUtils.TruncateAt.END);
                        object2 = new LinearLayoutCompat.LayoutParams(-2, -2);
                        object2.gravity = 16;
                        object3.setLayoutParams((ViewGroup.LayoutParams)object2);
                        this.addView((View)object3);
                        this.mTextView = object3;
                    }
                    this.mTextView.setText((CharSequence)object);
                    this.mTextView.setVisibility(0);
                    this.mTextView.setEnabled(tab.isEnabled());
                } else if (this.mTextView != null) {
                    this.mTextView.setVisibility(8);
                    this.mTextView.setText(null);
                }
                if (this.mIconView != null) {
                    this.mIconView.setContentDescription(tab.getContentDescription());
                }
                if (!bl2 && !TextUtils.isEmpty((CharSequence)tab.getContentDescription())) {
                    this.setOnLongClickListener((View.OnLongClickListener)this);
                } else {
                    this.setOnLongClickListener(null);
                    this.setLongClickable(false);
                }
            }
            this.setEnabled(tab.isEnabled());
        }
    }

    public class VisibilityAnimListener
    implements ViewPropertyAnimatorListener {
        private boolean mCanceled;
        private int mFinalVisibility;

        protected VisibilityAnimListener() {
            this.mCanceled = false;
        }

        @Override
        public void onAnimationCancel(View view) {
            this.mCanceled = true;
        }

        @Override
        public void onAnimationEnd(View view) {
            if (this.mCanceled) {
                return;
            }
            ScrollingTabContainerView.this.mVisibilityAnim = null;
            ScrollingTabContainerView.this.setVisibility(this.mFinalVisibility);
        }

        @Override
        public void onAnimationStart(View view) {
            ScrollingTabContainerView.this.setVisibility(0);
            this.mCanceled = false;
        }

        public VisibilityAnimListener withFinalVisibility(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, int n2) {
            this.mFinalVisibility = n2;
            ScrollingTabContainerView.this.mVisibilityAnim = viewPropertyAnimatorCompat;
            return this;
        }
    }

}

