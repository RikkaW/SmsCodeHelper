/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Configuration
 *  android.content.res.Resources
 *  android.graphics.drawable.Drawable
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.text.TextUtils
 *  android.util.AttributeSet
 *  android.util.DisplayMetrics
 *  android.view.LayoutInflater
 *  android.view.Menu
 *  android.view.View
 *  android.view.View$MeasureSpec
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewGroup$MarginLayoutParams
 *  android.view.ViewParent
 *  android.view.ViewTreeObserver
 *  android.view.ViewTreeObserver$OnPreDrawListener
 *  android.view.accessibility.AccessibilityEvent
 *  android.view.animation.DecelerateInterpolator
 *  android.view.animation.Interpolator
 *  android.widget.LinearLayout
 *  android.widget.TextView
 *  java.lang.Class
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v7.internal.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.animation.PathInterpolatorCompat;
import android.support.v7.appcompat.R;
import android.support.v7.internal.view.ActionBarPolicy;
import android.support.v7.internal.view.ViewPropertyAnimatorCompatSet;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.internal.view.menu.MenuPresenter;
import android.support.v7.internal.view.menu.MenuView;
import android.support.v7.internal.widget.AbsActionBarView;
import android.support.v7.internal.widget.ActionBarContextView$1;
import android.support.v7.internal.widget.ActionBarContextView$2;
import android.support.v7.internal.widget.TintTypedArray;
import android.support.v7.internal.widget.ViewUtils;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.ActionMenuPresenter;
import android.support.v7.widget.ActionMenuView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ActionBarContextView
extends AbsActionBarView
implements ViewPropertyAnimatorListener {
    private static final int ANIMATE_IDLE = 0;
    private static final int ANIMATE_IN = 1;
    private static final int ANIMATE_OUT = 2;
    private static final int MULTI_CHOICE_ANIMATION_DURATION = 260;
    private static final Interpolator MULTI_CHOICE_ANIMATION_INTERPOLATOR_IN = PathInterpolatorCompat.create(0.0f, 0.33f, 0.1f, 1.0f);
    private static final Interpolator MULTI_CHOICE_ANIMATION_INTERPOLATOR_OUT = PathInterpolatorCompat.create(0.0f, 0.66f, 0.66f, 1.0f);
    private static final String TAG = "ActionBarContextView";
    private boolean mAnimateInOnLayout;
    private int mAnimationMode;
    private View mClose;
    private int mCloseItemLayout;
    private ViewPropertyAnimatorCompatSet mCurrentAnimation;
    private View mCustomView;
    private boolean mInMultiChoiceActionMode;
    private Drawable mSplitBackground;
    private CharSequence mSubtitle;
    private int mSubtitleStyleRes;
    private TextView mSubtitleView;
    private CharSequence mTitle;
    private LinearLayout mTitleLayout;
    private boolean mTitleOptional;
    private int mTitleStyleRes;
    private TextView mTitleView;
    protected final ContextViewVisibilityAnimListener mVisAnimListener;

    public ActionBarContextView(Context context) {
        this(context, null);
    }

    public ActionBarContextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.actionModeStyle);
    }

    public ActionBarContextView(Context object, AttributeSet attributeSet, int n2) {
        super((Context)object, attributeSet, n2);
        this.mVisAnimListener = new ContextViewVisibilityAnimListener();
        object = TintTypedArray.obtainStyledAttributes((Context)object, attributeSet, R.styleable.ActionMode, n2, 0);
        this.setBackgroundDrawable(object.getDrawable(R.styleable.ActionMode_background));
        this.mTitleStyleRes = object.getResourceId(R.styleable.ActionMode_titleTextStyle, 0);
        this.mSubtitleStyleRes = object.getResourceId(R.styleable.ActionMode_subtitleTextStyle, 0);
        this.mContentHeight = object.getLayoutDimension(R.styleable.ActionMode_height, 0);
        this.mSplitBackground = object.getDrawable(R.styleable.ActionMode_backgroundSplit);
        this.mCloseItemLayout = object.getResourceId(R.styleable.ActionMode_closeItemLayout, R.layout.abc_action_mode_close_item_material);
        object.recycle();
    }

    static /* synthetic */ Interpolator access$000() {
        return MULTI_CHOICE_ANIMATION_INTERPOLATOR_IN;
    }

    private void finishAnimation() {
        ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet = this.mCurrentAnimation;
        if (viewPropertyAnimatorCompatSet != null) {
            this.mCurrentAnimation = null;
            viewPropertyAnimatorCompatSet.cancel();
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void initTitle() {
        TextView textView;
        int n2;
        block7 : {
            int n3 = 8;
            boolean bl2 = true;
            if (this.mTitleLayout == null) {
                LayoutInflater.from((Context)this.getContext()).inflate(R.layout.mz_action_bar_title_item, (ViewGroup)this);
                this.mTitleLayout = (LinearLayout)this.getChildAt(this.getChildCount() - 1);
                this.mTitleView = (TextView)this.mTitleLayout.findViewById(R.id.action_bar_title);
                this.mSubtitleView = (TextView)this.mTitleLayout.findViewById(R.id.action_bar_subtitle);
                if (this.mTitleStyleRes != 0) {
                    this.mTitleView.setTextAppearance(this.getContext(), this.mTitleStyleRes);
                }
                if (this.mSubtitleStyleRes != 0) {
                    this.mSubtitleView.setTextAppearance(this.getContext(), this.mSubtitleStyleRes);
                }
            }
            this.mTitleView.setText(this.mTitle);
            this.mSubtitleView.setText(this.mSubtitle);
            n2 = !TextUtils.isEmpty((CharSequence)this.mTitle) ? 1 : 0;
            if (TextUtils.isEmpty((CharSequence)this.mSubtitle)) {
                bl2 = false;
            }
            textView = this.mSubtitleView;
            int n4 = bl2 ? 0 : 8;
            textView.setVisibility(n4);
            textView = this.mTitleLayout;
            if (n2 == 0) {
                n2 = n3;
                if (!bl2) break block7;
            }
            n2 = 0;
        }
        textView.setVisibility(n2);
        if (this.mTitleLayout.getParent() == null) {
            this.addView((View)this.mTitleLayout);
        }
    }

    private ViewPropertyAnimatorCompatSet makeInAnimation() {
        int n2;
        ViewCompat.setTranslationX(this.mClose, - this.mClose.getWidth() - ((ViewGroup.MarginLayoutParams)this.mClose.getLayoutParams()).leftMargin);
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = ViewCompat.animate(this.mClose).translationX(0.0f);
        viewPropertyAnimatorCompat.setDuration(200);
        viewPropertyAnimatorCompat.setListener(this);
        viewPropertyAnimatorCompat.setInterpolator((Interpolator)new DecelerateInterpolator());
        ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet = new ViewPropertyAnimatorCompatSet();
        viewPropertyAnimatorCompatSet.play(viewPropertyAnimatorCompat);
        if (this.mMenuView != null && (n2 = this.mMenuView.getChildCount()) > 0) {
            int n3 = n2 - 1;
            n2 = 0;
            while (n3 >= 0) {
                viewPropertyAnimatorCompat = this.mMenuView.getChildAt(n3);
                ViewCompat.setScaleY((View)viewPropertyAnimatorCompat, 0.0f);
                viewPropertyAnimatorCompat = ViewCompat.animate((View)viewPropertyAnimatorCompat).scaleY(1.0f);
                viewPropertyAnimatorCompat.setDuration(300);
                viewPropertyAnimatorCompatSet.play(viewPropertyAnimatorCompat);
                --n3;
                ++n2;
            }
        }
        return viewPropertyAnimatorCompatSet;
    }

    private ViewPropertyAnimatorCompatSet makeOutAnimation() {
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = ViewCompat.animate(this.mClose).translationX(- this.mClose.getWidth() - ((ViewGroup.MarginLayoutParams)this.mClose.getLayoutParams()).leftMargin);
        viewPropertyAnimatorCompat.setDuration(200);
        viewPropertyAnimatorCompat.setListener(this);
        viewPropertyAnimatorCompat.setInterpolator((Interpolator)new DecelerateInterpolator());
        ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet = new ViewPropertyAnimatorCompatSet();
        viewPropertyAnimatorCompatSet.play(viewPropertyAnimatorCompat);
        if (this.mMenuView == null || this.mMenuView.getChildCount() > 0) {
            // empty if block
        }
        return viewPropertyAnimatorCompatSet;
    }

    @Override
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

    public void closeMode() {
    }

    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.MarginLayoutParams(-1, -2);
    }

    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ViewGroup.MarginLayoutParams(this.getContext(), attributeSet);
    }

    public CharSequence getSubtitle() {
        return this.mSubtitle;
    }

    public CharSequence getTitle() {
        return this.mTitle;
    }

    @Override
    public boolean hideOverflowMenu() {
        if (this.mActionMenuPresenter != null) {
            return this.mActionMenuPresenter.hideOverflowMenu();
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void initForMode(ActionMode object) {
        if (this.mClose == null) {
            this.mClose = LayoutInflater.from((Context)this.getContext()).inflate(this.mCloseItemLayout, (ViewGroup)this, false);
            this.addView(this.mClose);
        } else if (this.mClose.getParent() == null) {
            this.addView(this.mClose);
        }
        this.mClose.findViewById(R.id.action_mode_close_button).setOnClickListener((View.OnClickListener)new ActionBarContextView$1(this, (ActionMode)object));
        object = (MenuBuilder)object.getMenu();
        if (this.mActionMenuPresenter != null) {
            this.mActionMenuPresenter.dismissPopupMenus();
        }
        this.mActionMenuPresenter = new ActionMenuPresenter(this.getContext());
        this.mActionMenuPresenter.setReserveOverflow(true);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-2, -1);
        if (!this.mSplitActionBar) {
            object.addMenuPresenter(this.mActionMenuPresenter, this.mPopupContext);
            this.mMenuView = (ActionMenuView)this.mActionMenuPresenter.getMenuView(this);
            this.mMenuView.setBackgroundDrawable(null);
            this.addView((View)this.mMenuView, layoutParams);
        } else {
            this.mActionMenuPresenter.setWidthLimit(this.getContext().getResources().getDisplayMetrics().widthPixels, true);
            this.mActionMenuPresenter.setItemLimit(Integer.MAX_VALUE);
            layoutParams.width = -1;
            layoutParams.height = this.mContentHeight;
            object.addMenuPresenter(this.mActionMenuPresenter, this.mPopupContext);
            this.mMenuView = (ActionMenuView)this.mActionMenuPresenter.getMenuView(this);
            this.mMenuView.setBackgroundDrawable(this.mSplitBackground);
            this.mSplitView.addView((View)this.mMenuView, layoutParams);
        }
        this.mAnimateInOnLayout = true;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void initForMultiChoiceMode(ActionMode object) {
        this.mInMultiChoiceActionMode = true;
        object = (MenuBuilder)object.getMenu();
        if (this.mActionMenuPresenter != null) {
            this.mActionMenuPresenter.dismissPopupMenus();
        }
        this.mActionMenuPresenter = new ActionMenuPresenter(this.getContext());
        this.mActionMenuPresenter.setReserveOverflow(true);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-2, -1);
        if (this.mSplitView == null) {
            object.addMenuPresenter(this.mActionMenuPresenter, this.mPopupContext);
            this.mMenuView = (ActionMenuView)this.mActionMenuPresenter.getMenuView(this);
            this.mMenuView.setBackgroundDrawable(null);
            this.addView((View)this.mMenuView, layoutParams);
        } else {
            int n2 = ActionBarPolicy.get(this.getContext()).getSplitActionBarPadding();
            this.mActionMenuPresenter.setWidthLimit(this.getContext().getResources().getDisplayMetrics().widthPixels - n2 * 2, true);
            this.mActionMenuPresenter.setItemLimit(Integer.MAX_VALUE);
            this.mActionMenuPresenter.setIsSplit(true);
            layoutParams.width = -1;
            layoutParams.height = -2;
            object.addMenuPresenter(this.mActionMenuPresenter, this.mPopupContext);
            this.mMenuView = (ActionMenuView)this.mActionMenuPresenter.getMenuView(this);
            this.mMenuView.setId(R.id.mz_action_mode_menu_view);
            this.mMenuView.setBackgroundDrawable(this.mSplitBackground);
            this.mSplitView.addView((View)this.mMenuView, layoutParams);
        }
        this.mAnimateInOnLayout = true;
    }

    public boolean isInMultiChoiceActionMode() {
        return this.mInMultiChoiceActionMode;
    }

    @Override
    public boolean isOverflowMenuShowing() {
        if (this.mActionMenuPresenter != null) {
            return this.mActionMenuPresenter.isOverflowMenuShowing();
        }
        return false;
    }

    public boolean isTitleOptional() {
        return this.mTitleOptional;
    }

    public void killMode() {
        this.finishAnimation();
        this.removeAllViews();
        if (this.mSplitView != null) {
            this.mSplitView.removeView((View)this.mMenuView);
        }
        this.mCustomView = null;
        this.mMenuView = null;
        this.mAnimateInOnLayout = false;
        this.mInMultiChoiceActionMode = false;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void multiChoiceMenuViewAnimateToVisibility(int n2) {
        int n3 = n2 == 0 ? 1 : 2;
        this.mAnimationMode = n3;
        Object object = this.mSplitView != null ? (ViewGroup)this.mSplitView.getChildAt(0) : null;
        object = this.mSplitView == null ? this.mMenuView : this.mSplitView;
        if (object == null) {
            this.mAnimationMode = 0;
            return;
        } else {
            if (n2 != 0) {
                float f2 = object.getHeight();
                object = ViewCompat.animate((View)object).translationY(f2);
                object.setDuration(260);
                object.setListener(this);
                object.setInterpolator(MULTI_CHOICE_ANIMATION_INTERPOLATOR_OUT);
                object.start();
                return;
            }
            ViewTreeObserver viewTreeObserver = object.getViewTreeObserver();
            if (viewTreeObserver == null) return;
            {
                viewTreeObserver.addOnPreDrawListener((ViewTreeObserver.OnPreDrawListener)new ActionBarContextView$2(this, (View)object));
                return;
            }
        }
    }

    @Override
    public void onAnimationCancel(View view) {
    }

    @Override
    public void onAnimationEnd(View view) {
        if (this.mAnimationMode == 2) {
            this.killMode();
        }
        this.mAnimationMode = 0;
    }

    @Override
    public void onAnimationStart(View view) {
    }

    @Override
    protected void onConfigurationChanged(Configuration configuration) {
        if (this.mSplitWhenNarrow) {
            this.setSplitToolbar(this.getContext().getResources().getBoolean(R.bool.mz_split_action_bar_is_narrow));
        }
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mActionMenuPresenter != null) {
            this.mActionMenuPresenter.hideOverflowMenu();
            this.mActionMenuPresenter.hideSubMenus();
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (Build.VERSION.SDK_INT < 14) return;
        if (accessibilityEvent.getEventType() == 32) {
            accessibilityEvent.setSource((View)this);
            accessibilityEvent.setClassName((CharSequence)this.getClass().getName());
            accessibilityEvent.setPackageName((CharSequence)this.getContext().getPackageName());
            accessibilityEvent.setContentDescription(this.mTitle);
            return;
        }
        super.onInitializeAccessibilityEvent(accessibilityEvent);
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void onLayout(boolean bl2, int n2, int n3, int n4, int n5) {
        Object object;
        bl2 = ViewUtils.isLayoutRtl((View)this);
        int n6 = bl2 ? n4 - n2 - this.getPaddingRight() : this.getPaddingLeft();
        int n7 = this.getPaddingTop();
        int n8 = n5 - n3 - this.getPaddingTop() - this.getPaddingBottom();
        if (this.mClose != null && this.mClose.getVisibility() != 8) {
            object = (ViewGroup.MarginLayoutParams)this.mClose.getLayoutParams();
            n3 = bl2 ? object.rightMargin : object.leftMargin;
            n5 = bl2 ? object.leftMargin : object.rightMargin;
            n3 = ActionBarContextView.next(n6, n3, bl2);
            n3 = ActionBarContextView.next(this.positionChild(this.mClose, n3, n7, n8, bl2) + n3, n5, bl2);
        } else {
            n3 = n6;
        }
        n5 = n3;
        if (this.mTitleLayout != null) {
            n5 = n3;
            if (this.mCustomView == null) {
                n5 = n3;
                if (this.mTitleLayout.getVisibility() != 8) {
                    n5 = n3 + this.positionChild((View)this.mTitleLayout, n3, n7, n8, bl2);
                }
            }
        }
        if (this.mCustomView != null) {
            this.positionChild(this.mCustomView, n5, n7, n8, bl2);
        }
        n2 = bl2 ? this.getPaddingLeft() : n4 - n2 - this.getPaddingRight();
        if (this.mMenuView != null && this.mMenuView.getParent() == this) {
            object = this.mMenuView;
            bl2 = !bl2;
            this.positionChild((View)object, n2, n7, n8, bl2);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void onMeasure(int n2, int n3) {
        ViewGroup.MarginLayoutParams marginLayoutParams;
        int n4 = 1073741824;
        int n5 = 0;
        if (View.MeasureSpec.getMode((int)n2) != 1073741824) {
            throw new IllegalStateException(this.getClass().getSimpleName() + " can only be used " + "with android:layout_width=\"match_parent\" (or fill_parent)");
        }
        if (View.MeasureSpec.getMode((int)n3) == 0) {
            throw new IllegalStateException(this.getClass().getSimpleName() + " can only be used " + "with android:layout_height=\"wrap_content\"");
        }
        int n6 = View.MeasureSpec.getSize((int)n2);
        int n7 = this.mContentHeight > 0 ? this.mContentHeight : View.MeasureSpec.getSize((int)n3);
        int n8 = this.getPaddingTop() + this.getPaddingBottom();
        n2 = n6 - this.getPaddingLeft() - this.getPaddingRight();
        int n9 = n7 - n8;
        int n10 = View.MeasureSpec.makeMeasureSpec((int)n9, (int)Integer.MIN_VALUE);
        n3 = n2;
        if (this.mClose != null) {
            n2 = this.measureChildView(this.mClose, n2, n10, 0);
            marginLayoutParams = (ViewGroup.MarginLayoutParams)this.mClose.getLayoutParams();
            n3 = marginLayoutParams.leftMargin;
            n3 = n2 - (marginLayoutParams.rightMargin + n3);
        }
        n2 = n3;
        if (this.mMenuView != null) {
            n2 = n3;
            if (this.mMenuView.getParent() == this) {
                n2 = this.measureChildView((View)this.mMenuView, n3, n10, 0);
            }
        }
        n3 = n2;
        if (this.mTitleLayout != null) {
            n3 = n2;
            if (this.mCustomView == null) {
                if (this.mTitleOptional) {
                    n3 = View.MeasureSpec.makeMeasureSpec((int)0, (int)0);
                    this.mTitleLayout.measure(n3, n10);
                    int n11 = this.mTitleLayout.getMeasuredWidth();
                    n10 = n11 <= n2 ? 1 : 0;
                    n3 = n2;
                    if (n10 != 0) {
                        n3 = n2 - n11;
                    }
                    marginLayoutParams = this.mTitleLayout;
                    n2 = n10 != 0 ? 0 : 8;
                    marginLayoutParams.setVisibility(n2);
                } else {
                    n3 = this.measureChildView((View)this.mTitleLayout, n2, n10, 0);
                }
            }
        }
        if (this.mCustomView != null) {
            marginLayoutParams = this.mCustomView.getLayoutParams();
            n2 = marginLayoutParams.width != -2 ? 1073741824 : Integer.MIN_VALUE;
            n10 = n3;
            if (marginLayoutParams.width >= 0) {
                n10 = Math.min((int)marginLayoutParams.width, (int)n3);
            }
            n3 = marginLayoutParams.height != -2 ? n4 : Integer.MIN_VALUE;
            n4 = marginLayoutParams.height >= 0 ? Math.min((int)marginLayoutParams.height, (int)n9) : n9;
            this.mCustomView.measure(View.MeasureSpec.makeMeasureSpec((int)n10, (int)n2), View.MeasureSpec.makeMeasureSpec((int)n4, (int)n3));
        }
        if (this.mContentHeight > 0) {
            this.setMeasuredDimension(n6, n7);
            return;
        }
        n10 = this.getChildCount();
        n2 = 0;
        n3 = n5;
        do {
            if (n3 >= n10) {
                this.setMeasuredDimension(n6, n2);
                return;
            }
            n7 = this.getChildAt(n3).getMeasuredHeight() + n8;
            if (n7 > n2) {
                n2 = n7;
            }
            ++n3;
        } while (true);
    }

    @Override
    public void setContentHeight(int n2) {
        this.mContentHeight = n2;
    }

    public void setCustomView(View view) {
        if (this.mCustomView != null) {
            this.removeView(this.mCustomView);
        }
        this.mCustomView = view;
        if (this.mTitleLayout != null) {
            this.removeView((View)this.mTitleLayout);
            this.mTitleLayout = null;
        }
        if (view != null) {
            this.addView(view);
        }
        this.requestLayout();
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void setSplitToolbar(boolean bl2) {
        if (this.mSplitActionBar != bl2) {
            if (this.mActionMenuPresenter != null) {
                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-2, -1);
                if (!bl2) {
                    this.mMenuView = (ActionMenuView)this.mActionMenuPresenter.getMenuView(this);
                    this.mMenuView.setBackgroundDrawable(null);
                    ViewGroup viewGroup = (ViewGroup)this.mMenuView.getParent();
                    if (viewGroup != null) {
                        viewGroup.removeView((View)this.mMenuView);
                    }
                    this.addView((View)this.mMenuView, layoutParams);
                } else {
                    this.mActionMenuPresenter.setWidthLimit(this.getContext().getResources().getDisplayMetrics().widthPixels, true);
                    this.mActionMenuPresenter.setItemLimit(Integer.MAX_VALUE);
                    layoutParams.width = -1;
                    layoutParams.height = this.mContentHeight;
                    this.mMenuView = (ActionMenuView)this.mActionMenuPresenter.getMenuView(this);
                    this.mMenuView.setBackgroundDrawable(this.mSplitBackground);
                    ViewGroup viewGroup = (ViewGroup)this.mMenuView.getParent();
                    if (viewGroup != null) {
                        viewGroup.removeView((View)this.mMenuView);
                    }
                    this.mSplitView.addView((View)this.mMenuView, layoutParams);
                }
            }
            super.setSplitToolbar(bl2);
        }
    }

    public void setSubtitle(CharSequence charSequence) {
        this.mSubtitle = charSequence;
        this.initTitle();
    }

    public void setTitle(CharSequence charSequence) {
        this.mTitle = charSequence;
        this.initTitle();
    }

    public void setTitleOptional(boolean bl2) {
        if (bl2 != this.mTitleOptional) {
            this.requestLayout();
        }
        this.mTitleOptional = bl2;
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }

    @Override
    public boolean showOverflowMenu() {
        if (this.mActionMenuPresenter != null) {
            return this.mActionMenuPresenter.showOverflowMenu();
        }
        return false;
    }

    public class ContextViewVisibilityAnimListener
    extends AbsActionBarView.VisibilityAnimListener {
        protected ContextViewVisibilityAnimListener() {
        }

        @Override
        public void onAnimationEnd(View view) {
            super.onAnimationEnd(view);
            if (this.mFinalVisibility == 8) {
                ActionBarContextView.this.killMode();
            }
        }
    }

}

