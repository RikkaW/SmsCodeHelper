/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.content.res.TypedArray
 *  android.graphics.drawable.Drawable
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.util.AttributeSet
 *  android.util.DisplayMetrics
 *  android.util.Log
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.View$MeasureSpec
 *  android.view.ViewConfiguration
 *  android.view.ViewGroup
 *  android.view.animation.Animation
 *  android.view.animation.Animation$AnimationListener
 *  android.view.animation.DecelerateInterpolator
 *  android.view.animation.Interpolator
 *  android.widget.AbsListView
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.CircleImageView;
import android.support.v4.widget.MaterialProgressDrawable;
import android.support.v4.widget.SwipeRefreshLayout$1;
import android.support.v4.widget.SwipeRefreshLayout$2;
import android.support.v4.widget.SwipeRefreshLayout$3;
import android.support.v4.widget.SwipeRefreshLayout$4;
import android.support.v4.widget.SwipeRefreshLayout$5;
import android.support.v4.widget.SwipeRefreshLayout$6;
import android.support.v4.widget.SwipeRefreshLayout$7;
import android.support.v4.widget.SwipeRefreshLayout$8;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.AbsListView;

public class SwipeRefreshLayout
extends ViewGroup {
    private static final int ALPHA_ANIMATION_DURATION = 300;
    private static final int ANIMATE_TO_START_DURATION = 200;
    private static final int ANIMATE_TO_TRIGGER_DURATION = 200;
    private static final int CIRCLE_BG_LIGHT = -328966;
    private static final int CIRCLE_DIAMETER = 40;
    private static final int CIRCLE_DIAMETER_LARGE = 56;
    private static final float DECELERATE_INTERPOLATION_FACTOR = 2.0f;
    public static final int DEFAULT = 1;
    private static final int DEFAULT_CIRCLE_TARGET = 64;
    private static final float DRAG_RATE = 0.5f;
    private static final int INVALID_POINTER = -1;
    public static final int LARGE = 0;
    private static final int[] LAYOUT_ATTRS;
    private static final String LOG_TAG;
    private static final int MAX_ALPHA = 255;
    private static final float MAX_PROGRESS_ANGLE = 0.8f;
    private static final int SCALE_DOWN_DURATION = 150;
    private static final int STARTING_PROGRESS_ALPHA = 76;
    private int mActivePointerId = -1;
    private Animation mAlphaMaxAnimation;
    private Animation mAlphaStartAnimation;
    private final Animation mAnimateToCorrectPosition;
    private final Animation mAnimateToStartPosition;
    private int mCircleHeight;
    private CircleImageView mCircleView;
    private int mCircleViewIndex = -1;
    private int mCircleWidth;
    private int mCurrentTargetOffsetTop;
    private final DecelerateInterpolator mDecelerateInterpolator;
    protected int mFrom;
    private float mInitialDownY;
    private float mInitialMotionY;
    private boolean mIsBeingDragged;
    private OnRefreshListener mListener;
    private int mMediumAnimationDuration;
    private boolean mNotify;
    private boolean mOriginalOffsetCalculated = false;
    protected int mOriginalOffsetTop;
    private MaterialProgressDrawable mProgress;
    private Animation.AnimationListener mRefreshListener;
    private boolean mRefreshing = false;
    private boolean mReturningToStart;
    private boolean mScale;
    private Animation mScaleAnimation;
    private Animation mScaleDownAnimation;
    private Animation mScaleDownToStartAnimation;
    private float mSpinnerFinalOffset;
    private float mStartingScale;
    private View mTarget;
    private float mTotalDragDistance = -1.0f;
    private int mTouchSlop;
    private boolean mUsingCustomStart;

    static {
        LOG_TAG = SwipeRefreshLayout.class.getSimpleName();
        LAYOUT_ATTRS = new int[]{16842766};
    }

    public SwipeRefreshLayout(Context context) {
        this(context, null);
    }

    public SwipeRefreshLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mRefreshListener = new SwipeRefreshLayout$1(this);
        this.mAnimateToCorrectPosition = new SwipeRefreshLayout$6(this);
        this.mAnimateToStartPosition = new SwipeRefreshLayout$7(this);
        this.mTouchSlop = ViewConfiguration.get((Context)context).getScaledTouchSlop();
        this.mMediumAnimationDuration = this.getResources().getInteger(17694721);
        this.setWillNotDraw(false);
        this.mDecelerateInterpolator = new DecelerateInterpolator(2.0f);
        context = context.obtainStyledAttributes(attributeSet, LAYOUT_ATTRS);
        this.setEnabled(context.getBoolean(0, true));
        context.recycle();
        context = this.getResources().getDisplayMetrics();
        this.mCircleWidth = (int)(context.density * 40.0f);
        this.mCircleHeight = (int)(context.density * 40.0f);
        this.createProgressView();
        ViewCompat.setChildrenDrawingOrderEnabled(this, true);
        this.mTotalDragDistance = this.mSpinnerFinalOffset = context.density * 64.0f;
    }

    static /* synthetic */ boolean access$000(SwipeRefreshLayout swipeRefreshLayout) {
        return swipeRefreshLayout.mRefreshing;
    }

    static /* synthetic */ MaterialProgressDrawable access$100(SwipeRefreshLayout swipeRefreshLayout) {
        return swipeRefreshLayout.mProgress;
    }

    static /* synthetic */ void access$1000(SwipeRefreshLayout swipeRefreshLayout, Animation.AnimationListener animationListener) {
        swipeRefreshLayout.startScaleDownAnimation(animationListener);
    }

    static /* synthetic */ boolean access$1100(SwipeRefreshLayout swipeRefreshLayout) {
        return swipeRefreshLayout.mUsingCustomStart;
    }

    static /* synthetic */ float access$1200(SwipeRefreshLayout swipeRefreshLayout) {
        return swipeRefreshLayout.mSpinnerFinalOffset;
    }

    static /* synthetic */ void access$1300(SwipeRefreshLayout swipeRefreshLayout, float f2) {
        swipeRefreshLayout.moveToStart(f2);
    }

    static /* synthetic */ float access$1400(SwipeRefreshLayout swipeRefreshLayout) {
        return swipeRefreshLayout.mStartingScale;
    }

    static /* synthetic */ boolean access$200(SwipeRefreshLayout swipeRefreshLayout) {
        return swipeRefreshLayout.mNotify;
    }

    static /* synthetic */ OnRefreshListener access$300(SwipeRefreshLayout swipeRefreshLayout) {
        return swipeRefreshLayout.mListener;
    }

    static /* synthetic */ CircleImageView access$400(SwipeRefreshLayout swipeRefreshLayout) {
        return swipeRefreshLayout.mCircleView;
    }

    static /* synthetic */ void access$500(SwipeRefreshLayout swipeRefreshLayout, int n2) {
        swipeRefreshLayout.setColorViewAlpha(n2);
    }

    static /* synthetic */ boolean access$600(SwipeRefreshLayout swipeRefreshLayout) {
        return swipeRefreshLayout.mScale;
    }

    static /* synthetic */ void access$700(SwipeRefreshLayout swipeRefreshLayout, float f2) {
        swipeRefreshLayout.setAnimationProgress(f2);
    }

    static /* synthetic */ int access$800(SwipeRefreshLayout swipeRefreshLayout) {
        return swipeRefreshLayout.mCurrentTargetOffsetTop;
    }

    static /* synthetic */ int access$802(SwipeRefreshLayout swipeRefreshLayout, int n2) {
        swipeRefreshLayout.mCurrentTargetOffsetTop = n2;
        return n2;
    }

    static /* synthetic */ void access$900(SwipeRefreshLayout swipeRefreshLayout, int n2, boolean bl2) {
        swipeRefreshLayout.setTargetOffsetTopAndBottom(n2, bl2);
    }

    private void animateOffsetToCorrectPosition(int n2, Animation.AnimationListener animationListener) {
        this.mFrom = n2;
        this.mAnimateToCorrectPosition.reset();
        this.mAnimateToCorrectPosition.setDuration(200);
        this.mAnimateToCorrectPosition.setInterpolator((Interpolator)this.mDecelerateInterpolator);
        if (animationListener != null) {
            this.mCircleView.setAnimationListener(animationListener);
        }
        this.mCircleView.clearAnimation();
        this.mCircleView.startAnimation(this.mAnimateToCorrectPosition);
    }

    private void animateOffsetToStartPosition(int n2, Animation.AnimationListener animationListener) {
        if (this.mScale) {
            this.startScaleDownReturnToStartAnimation(n2, animationListener);
            return;
        }
        this.mFrom = n2;
        this.mAnimateToStartPosition.reset();
        this.mAnimateToStartPosition.setDuration(200);
        this.mAnimateToStartPosition.setInterpolator((Interpolator)this.mDecelerateInterpolator);
        if (animationListener != null) {
            this.mCircleView.setAnimationListener(animationListener);
        }
        this.mCircleView.clearAnimation();
        this.mCircleView.startAnimation(this.mAnimateToStartPosition);
    }

    private void createProgressView() {
        this.mCircleView = new CircleImageView(this.getContext(), -328966, 20.0f);
        this.mProgress = new MaterialProgressDrawable(this.getContext(), (View)this);
        this.mProgress.setBackgroundColor(-328966);
        this.mCircleView.setImageDrawable((Drawable)this.mProgress);
        this.mCircleView.setVisibility(8);
        this.addView((View)this.mCircleView);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private void ensureTarget() {
        if (this.mTarget != null) return;
        int n2 = 0;
        while (n2 < this.getChildCount()) {
            View view = this.getChildAt(n2);
            if (!view.equals((Object)this.mCircleView)) {
                this.mTarget = view;
                return;
            }
            ++n2;
        }
    }

    private float getMotionEventY(MotionEvent motionEvent, int n2) {
        if ((n2 = MotionEventCompat.findPointerIndex(motionEvent, n2)) < 0) {
            return -1.0f;
        }
        return MotionEventCompat.getY(motionEvent, n2);
    }

    private boolean isAlphaUsedForScale() {
        if (Build.VERSION.SDK_INT < 11) {
            return true;
        }
        return false;
    }

    private boolean isAnimationRunning(Animation animation) {
        if (animation != null && animation.hasStarted() && !animation.hasEnded()) {
            return true;
        }
        return false;
    }

    private void moveToStart(float f2) {
        this.setTargetOffsetTopAndBottom(this.mFrom + (int)((float)(this.mOriginalOffsetTop - this.mFrom) * f2) - this.mCircleView.getTop(), false);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void onSecondaryPointerUp(MotionEvent motionEvent) {
        int n2 = MotionEventCompat.getActionIndex(motionEvent);
        if (MotionEventCompat.getPointerId(motionEvent, n2) == this.mActivePointerId) {
            n2 = n2 == 0 ? 1 : 0;
            this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, n2);
        }
    }

    private void setAnimationProgress(float f2) {
        if (this.isAlphaUsedForScale()) {
            this.setColorViewAlpha((int)(255.0f * f2));
            return;
        }
        ViewCompat.setScaleX((View)this.mCircleView, f2);
        ViewCompat.setScaleY((View)this.mCircleView, f2);
    }

    private void setColorViewAlpha(int n2) {
        this.mCircleView.getBackground().setAlpha(n2);
        this.mProgress.setAlpha(n2);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private void setRefreshing(boolean bl2, boolean bl3) {
        if (this.mRefreshing == bl2) return;
        this.mNotify = bl3;
        this.ensureTarget();
        this.mRefreshing = bl2;
        if (this.mRefreshing) {
            this.animateOffsetToCorrectPosition(this.mCurrentTargetOffsetTop, this.mRefreshListener);
            return;
        }
        this.startScaleDownAnimation(this.mRefreshListener);
    }

    private void setTargetOffsetTopAndBottom(int n2, boolean bl2) {
        this.mCircleView.bringToFront();
        this.mCircleView.offsetTopAndBottom(n2);
        this.mCurrentTargetOffsetTop = this.mCircleView.getTop();
        if (bl2 && Build.VERSION.SDK_INT < 11) {
            this.invalidate();
        }
    }

    private Animation startAlphaAnimation(int n2, int n3) {
        if (this.mScale && this.isAlphaUsedForScale()) {
            return null;
        }
        SwipeRefreshLayout$4 swipeRefreshLayout$4 = new SwipeRefreshLayout$4(this, n2, n3);
        swipeRefreshLayout$4.setDuration(300);
        this.mCircleView.setAnimationListener(null);
        this.mCircleView.clearAnimation();
        this.mCircleView.startAnimation((Animation)swipeRefreshLayout$4);
        return swipeRefreshLayout$4;
    }

    private void startProgressAlphaMaxAnimation() {
        this.mAlphaMaxAnimation = this.startAlphaAnimation(this.mProgress.getAlpha(), 255);
    }

    private void startProgressAlphaStartAnimation() {
        this.mAlphaStartAnimation = this.startAlphaAnimation(this.mProgress.getAlpha(), 76);
    }

    private void startScaleDownAnimation(Animation.AnimationListener animationListener) {
        this.mScaleDownAnimation = new SwipeRefreshLayout$3(this);
        this.mScaleDownAnimation.setDuration(150);
        this.mCircleView.setAnimationListener(animationListener);
        this.mCircleView.clearAnimation();
        this.mCircleView.startAnimation(this.mScaleDownAnimation);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void startScaleDownReturnToStartAnimation(int n2, Animation.AnimationListener animationListener) {
        this.mFrom = n2;
        this.mStartingScale = this.isAlphaUsedForScale() ? (float)this.mProgress.getAlpha() : ViewCompat.getScaleX((View)this.mCircleView);
        this.mScaleDownToStartAnimation = new SwipeRefreshLayout$8(this);
        this.mScaleDownToStartAnimation.setDuration(150);
        if (animationListener != null) {
            this.mCircleView.setAnimationListener(animationListener);
        }
        this.mCircleView.clearAnimation();
        this.mCircleView.startAnimation(this.mScaleDownToStartAnimation);
    }

    private void startScaleUpAnimation(Animation.AnimationListener animationListener) {
        this.mCircleView.setVisibility(0);
        if (Build.VERSION.SDK_INT >= 11) {
            this.mProgress.setAlpha(255);
        }
        this.mScaleAnimation = new SwipeRefreshLayout$2(this);
        this.mScaleAnimation.setDuration((long)this.mMediumAnimationDuration);
        if (animationListener != null) {
            this.mCircleView.setAnimationListener(animationListener);
        }
        this.mCircleView.clearAnimation();
        this.mCircleView.startAnimation(this.mScaleAnimation);
    }

    public boolean canChildScrollUp() {
        boolean bl2 = false;
        if (Build.VERSION.SDK_INT < 14) {
            if (this.mTarget instanceof AbsListView) {
                AbsListView absListView = (AbsListView)this.mTarget;
                if (absListView.getChildCount() > 0 && (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0).getTop() < absListView.getPaddingTop())) {
                    return true;
                }
                return false;
            }
            if (ViewCompat.canScrollVertically(this.mTarget, -1) || this.mTarget.getScrollY() > 0) {
                bl2 = true;
            }
            return bl2;
        }
        return ViewCompat.canScrollVertically(this.mTarget, -1);
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    protected int getChildDrawingOrder(int n2, int n3) {
        if (this.mCircleViewIndex < 0) {
            return n3;
        }
        if (n3 == n2 - 1) {
            return this.mCircleViewIndex;
        }
        if (n3 < this.mCircleViewIndex) return n3;
        return n3 + 1;
    }

    public int getProgressCircleDiameter() {
        if (this.mCircleView != null) {
            return this.mCircleView.getMeasuredHeight();
        }
        return 0;
    }

    public boolean isRefreshing() {
        return this.mRefreshing;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public boolean onInterceptTouchEvent(MotionEvent var1_1) {
        this.ensureTarget();
        var3_2 = MotionEventCompat.getActionMasked(var1_1);
        if (this.mReturningToStart && var3_2 == 0) {
            this.mReturningToStart = false;
        }
        if (this.isEnabled() == false) return false;
        if (this.mReturningToStart != false) return false;
        if (this.canChildScrollUp() != false) return false;
        if (this.mRefreshing) {
            return false;
        }
        switch (var3_2) {
            case 0: {
                this.setTargetOffsetTopAndBottom(this.mOriginalOffsetTop - this.mCircleView.getTop(), true);
                this.mActivePointerId = MotionEventCompat.getPointerId(var1_1, 0);
                this.mIsBeingDragged = false;
                var2_3 = this.getMotionEventY(var1_1, this.mActivePointerId);
                if (var2_3 == -1.0f) return false;
                this.mInitialDownY = var2_3;
                ** break;
            }
            case 2: {
                if (this.mActivePointerId == -1) {
                    Log.e((String)SwipeRefreshLayout.LOG_TAG, (String)"Got ACTION_MOVE event but don't have an active pointer id.");
                    return false;
                }
                var2_4 = this.getMotionEventY(var1_1, this.mActivePointerId);
                if (var2_4 == -1.0f) return false;
                if (var2_4 - this.mInitialDownY <= (float)this.mTouchSlop) return this.mIsBeingDragged;
                if (this.mIsBeingDragged != false) return this.mIsBeingDragged;
                this.mInitialMotionY = this.mInitialDownY + (float)this.mTouchSlop;
                this.mIsBeingDragged = true;
                this.mProgress.setAlpha(76);
                ** break;
            }
            case 6: {
                this.onSecondaryPointerUp(var1_1);
            }
lbl33: // 4 sources:
            default: {
                return this.mIsBeingDragged;
            }
            case 1: 
            case 3: 
        }
        this.mIsBeingDragged = false;
        this.mActivePointerId = -1;
        return this.mIsBeingDragged;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    protected void onLayout(boolean bl2, int n2, int n3, int n4, int n5) {
        n2 = this.getMeasuredWidth();
        n3 = this.getMeasuredHeight();
        if (this.getChildCount() == 0) {
            return;
        }
        if (this.mTarget == null) {
            this.ensureTarget();
        }
        if (this.mTarget == null) return;
        View view = this.mTarget;
        n4 = this.getPaddingLeft();
        n5 = this.getPaddingTop();
        view.layout(n4, n5, n2 - this.getPaddingLeft() - this.getPaddingRight() + n4, n3 - this.getPaddingTop() - this.getPaddingBottom() + n5);
        n3 = this.mCircleView.getMeasuredWidth();
        n4 = this.mCircleView.getMeasuredHeight();
        this.mCircleView.layout(n2 / 2 - n3 / 2, this.mCurrentTargetOffsetTop, n2 / 2 + n3 / 2, this.mCurrentTargetOffsetTop + n4);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void onMeasure(int n2, int n3) {
        super.onMeasure(n2, n3);
        if (this.mTarget == null) {
            this.ensureTarget();
        }
        if (this.mTarget == null) {
            return;
        }
        this.mTarget.measure(View.MeasureSpec.makeMeasureSpec((int)(this.getMeasuredWidth() - this.getPaddingLeft() - this.getPaddingRight()), (int)1073741824), View.MeasureSpec.makeMeasureSpec((int)(this.getMeasuredHeight() - this.getPaddingTop() - this.getPaddingBottom()), (int)1073741824));
        this.mCircleView.measure(View.MeasureSpec.makeMeasureSpec((int)this.mCircleWidth, (int)1073741824), View.MeasureSpec.makeMeasureSpec((int)this.mCircleHeight, (int)1073741824));
        if (!this.mUsingCustomStart && !this.mOriginalOffsetCalculated) {
            this.mOriginalOffsetCalculated = true;
            this.mOriginalOffsetTop = n2 = - this.mCircleView.getMeasuredHeight();
            this.mCurrentTargetOffsetTop = n2;
        }
        this.mCircleViewIndex = -1;
        n2 = 0;
        while (n2 < this.getChildCount()) {
            if (this.getChildAt(n2) == this.mCircleView) {
                this.mCircleViewIndex = n2;
                return;
            }
            ++n2;
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public boolean onTouchEvent(MotionEvent var1_1) {
        var8_2 = MotionEventCompat.getActionMasked((MotionEvent)var1_1);
        if (this.mReturningToStart && var8_2 == 0) {
            this.mReturningToStart = false;
        }
        if (this.isEnabled() == false) return false;
        if (this.mReturningToStart != false) return false;
        if (this.canChildScrollUp()) {
            return false;
        }
        switch (var8_2) {
            case 0: {
                this.mActivePointerId = MotionEventCompat.getPointerId((MotionEvent)var1_1, 0);
                this.mIsBeingDragged = false;
                ** break;
            }
            case 2: {
                var8_2 = MotionEventCompat.findPointerIndex((MotionEvent)var1_1, this.mActivePointerId);
                if (var8_2 < 0) {
                    Log.e((String)SwipeRefreshLayout.LOG_TAG, (String)"Got ACTION_MOVE event but have an invalid active pointer id.");
                    return false;
                }
                var3_3 = 0.5f * (MotionEventCompat.getY((MotionEvent)var1_1, var8_2) - this.mInitialMotionY);
                if (this.mIsBeingDragged == false) return true;
                this.mProgress.showArrow(true);
                var2_5 = var3_3 / this.mTotalDragDistance;
                if (var2_5 < 0.0f) {
                    return false;
                }
                var4_7 = Math.min((float)1.0f, (float)Math.abs((float)var2_5));
                var5_8 = (float)Math.max((double)((double)var4_7 - 0.4), (double)0.0) * 5.0f / 3.0f;
                var6_9 = Math.abs((float)var3_3);
                var7_10 = this.mTotalDragDistance;
                var2_5 = this.mUsingCustomStart != false ? this.mSpinnerFinalOffset - (float)this.mOriginalOffsetTop : this.mSpinnerFinalOffset;
                var6_9 = Math.max((float)0.0f, (float)(Math.min((float)(var6_9 - var7_10), (float)(2.0f * var2_5)) / var2_5));
                var6_9 = (float)((double)(var6_9 / 4.0f) - Math.pow((double)(var6_9 / 4.0f), (double)2.0)) * 2.0f;
                var8_2 = this.mOriginalOffsetTop;
                var9_11 = (int)(var2_5 * var4_7 + var2_5 * var6_9 * 2.0f);
                if (this.mCircleView.getVisibility() != 0) {
                    this.mCircleView.setVisibility(0);
                }
                if (!this.mScale) {
                    ViewCompat.setScaleX((View)this.mCircleView, 1.0f);
                    ViewCompat.setScaleY((View)this.mCircleView, 1.0f);
                }
                if (var3_3 < this.mTotalDragDistance) {
                    if (this.mScale) {
                        this.setAnimationProgress(var3_3 / this.mTotalDragDistance);
                    }
                    if (this.mProgress.getAlpha() > 76 && !this.isAnimationRunning(this.mAlphaStartAnimation)) {
                        this.startProgressAlphaStartAnimation();
                    }
                    this.mProgress.setStartEndTrim(0.0f, Math.min((float)0.8f, (float)(0.8f * var5_8)));
                    this.mProgress.setArrowScale(Math.min((float)1.0f, (float)var5_8));
                } else if (this.mProgress.getAlpha() < 255 && !this.isAnimationRunning(this.mAlphaMaxAnimation)) {
                    this.startProgressAlphaMaxAnimation();
                }
                this.mProgress.setProgressRotation((-0.25f + 0.4f * var5_8 + 2.0f * var6_9) * 0.5f);
                this.setTargetOffsetTopAndBottom(var9_11 + var8_2 - this.mCurrentTargetOffsetTop, true);
                ** break;
            }
            case 5: {
                this.mActivePointerId = MotionEventCompat.getPointerId((MotionEvent)var1_1, MotionEventCompat.getActionIndex((MotionEvent)var1_1));
                ** break;
            }
            case 6: {
                this.onSecondaryPointerUp((MotionEvent)var1_1);
            }
lbl56: // 5 sources:
            default: {
                return true;
            }
            case 1: 
            case 3: 
        }
        if (this.mActivePointerId == -1) {
            if (var8_2 != 1) return false;
            Log.e((String)SwipeRefreshLayout.LOG_TAG, (String)"Got ACTION_UP event but don't have an active pointer id.");
            return false;
        }
        var2_6 = MotionEventCompat.getY((MotionEvent)var1_1, MotionEventCompat.findPointerIndex((MotionEvent)var1_1, this.mActivePointerId));
        var3_4 = this.mInitialMotionY;
        this.mIsBeingDragged = false;
        if ((var2_6 - var3_4) * 0.5f > this.mTotalDragDistance) {
            this.setRefreshing(true, true);
        } else {
            this.mRefreshing = false;
            this.mProgress.setStartEndTrim(0.0f, 0.0f);
            var1_1 = null;
            if (!this.mScale) {
                var1_1 = new SwipeRefreshLayout$5(this);
            }
            this.animateOffsetToStartPosition(this.mCurrentTargetOffsetTop, (Animation.AnimationListener)var1_1);
            this.mProgress.showArrow(false);
        }
        this.mActivePointerId = -1;
        return false;
    }

    public void requestDisallowInterceptTouchEvent(boolean bl2) {
    }

    @Deprecated
    public /* varargs */ void setColorScheme(int ... arrn) {
        this.setColorSchemeResources(arrn);
    }

    public /* varargs */ void setColorSchemeColors(int ... arrn) {
        this.ensureTarget();
        this.mProgress.setColorSchemeColors(arrn);
    }

    public /* varargs */ void setColorSchemeResources(int ... arrn) {
        Resources resources = this.getResources();
        int[] arrn2 = new int[arrn.length];
        for (int i2 = 0; i2 < arrn.length; ++i2) {
            arrn2[i2] = resources.getColor(arrn[i2]);
        }
        this.setColorSchemeColors(arrn2);
    }

    public void setDistanceToTriggerSync(int n2) {
        this.mTotalDragDistance = n2;
    }

    public void setOnRefreshListener(OnRefreshListener onRefreshListener) {
        this.mListener = onRefreshListener;
    }

    @Deprecated
    public void setProgressBackgroundColor(int n2) {
        this.setProgressBackgroundColorSchemeResource(n2);
    }

    public void setProgressBackgroundColorSchemeColor(int n2) {
        this.mCircleView.setBackgroundColor(n2);
        this.mProgress.setBackgroundColor(n2);
    }

    public void setProgressBackgroundColorSchemeResource(int n2) {
        this.setProgressBackgroundColorSchemeColor(this.getResources().getColor(n2));
    }

    public void setProgressViewEndTarget(boolean bl2, int n2) {
        this.mSpinnerFinalOffset = n2;
        this.mScale = bl2;
        this.mCircleView.invalidate();
    }

    public void setProgressViewOffset(boolean bl2, int n2, int n3) {
        this.mScale = bl2;
        this.mCircleView.setVisibility(8);
        this.mCurrentTargetOffsetTop = n2;
        this.mOriginalOffsetTop = n2;
        this.mSpinnerFinalOffset = n3;
        this.mUsingCustomStart = true;
        this.mCircleView.invalidate();
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setRefreshing(boolean bl2) {
        if (bl2 && this.mRefreshing != bl2) {
            this.mRefreshing = bl2;
            int n2 = !this.mUsingCustomStart ? (int)(this.mSpinnerFinalOffset + (float)this.mOriginalOffsetTop) : (int)this.mSpinnerFinalOffset;
            this.setTargetOffsetTopAndBottom(n2 - this.mCurrentTargetOffsetTop, true);
            this.mNotify = false;
            this.startScaleUpAnimation(this.mRefreshListener);
            return;
        }
        this.setRefreshing(bl2, false);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setSize(int n2) {
        if (n2 != 0 && n2 != 1) {
            return;
        }
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        if (n2 == 0) {
            int n3;
            this.mCircleWidth = n3 = (int)(displayMetrics.density * 56.0f);
            this.mCircleHeight = n3;
        } else {
            int n4;
            this.mCircleWidth = n4 = (int)(displayMetrics.density * 40.0f);
            this.mCircleHeight = n4;
        }
        this.mCircleView.setImageDrawable(null);
        this.mProgress.updateSizes(n2);
        this.mCircleView.setImageDrawable((Drawable)this.mProgress);
    }

    public static interface OnRefreshListener {
        public void onRefresh();
    }

}

