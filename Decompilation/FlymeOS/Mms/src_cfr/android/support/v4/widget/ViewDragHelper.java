/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.util.DisplayMetrics
 *  android.view.MotionEvent
 *  android.view.VelocityTracker
 *  android.view.View
 *  android.view.ViewConfiguration
 *  android.view.ViewGroup
 *  android.view.ViewParent
 *  android.view.animation.Interpolator
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.util.Arrays
 */
package android.support.v4.widget;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.VelocityTrackerCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ScrollerCompat;
import android.support.v4.widget.ViewDragHelper$1;
import android.support.v4.widget.ViewDragHelper$2;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.Interpolator;
import java.util.Arrays;

public class ViewDragHelper {
    private static final int BASE_SETTLE_DURATION = 256;
    public static final int DIRECTION_ALL = 3;
    public static final int DIRECTION_HORIZONTAL = 1;
    public static final int DIRECTION_VERTICAL = 2;
    public static final int EDGE_ALL = 15;
    public static final int EDGE_BOTTOM = 8;
    public static final int EDGE_LEFT = 1;
    public static final int EDGE_RIGHT = 2;
    private static final int EDGE_SIZE = 20;
    public static final int EDGE_TOP = 4;
    public static final int INVALID_POINTER = -1;
    private static final int MAX_SETTLE_DURATION = 600;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_IDLE = 0;
    public static final int STATE_SETTLING = 2;
    private static final String TAG = "ViewDragHelper";
    private static final Interpolator sInterpolator = new ViewDragHelper$1();
    private int mActivePointerId = -1;
    private final Callback mCallback;
    private View mCapturedView;
    private int mDragState;
    private int[] mEdgeDragsInProgress;
    private int[] mEdgeDragsLocked;
    private int mEdgeSize;
    private int[] mInitialEdgesTouched;
    private float[] mInitialMotionX;
    private float[] mInitialMotionY;
    private float[] mLastMotionX;
    private float[] mLastMotionY;
    private float mMaxVelocity;
    private float mMinVelocity;
    private final ViewGroup mParentView;
    private int mPointersDown;
    private boolean mReleaseInProgress;
    private ScrollerCompat mScroller;
    private final Runnable mSetIdleRunnable;
    private int mTouchSlop;
    private int mTrackingEdges;
    private VelocityTracker mVelocityTracker;

    private ViewDragHelper(Context context, ViewGroup viewGroup, Callback callback) {
        this.mSetIdleRunnable = new ViewDragHelper$2(this);
        if (viewGroup == null) {
            throw new IllegalArgumentException("Parent view may not be null");
        }
        if (callback == null) {
            throw new IllegalArgumentException("Callback may not be null");
        }
        this.mParentView = viewGroup;
        this.mCallback = callback;
        viewGroup = ViewConfiguration.get((Context)context);
        this.mEdgeSize = (int)(context.getResources().getDisplayMetrics().density * 20.0f + 0.5f);
        this.mTouchSlop = viewGroup.getScaledTouchSlop();
        this.mMaxVelocity = viewGroup.getScaledMaximumFlingVelocity();
        this.mMinVelocity = viewGroup.getScaledMinimumFlingVelocity();
        this.mScroller = ScrollerCompat.create(context, sInterpolator);
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private boolean checkNewEdgeDrag(float f2, float f3, int n2, int n3) {
        f2 = Math.abs((float)f2);
        f3 = Math.abs((float)f3);
        if ((this.mInitialEdgesTouched[n2] & n3) != n3) return false;
        if ((this.mTrackingEdges & n3) == 0) return false;
        if ((this.mEdgeDragsLocked[n2] & n3) == n3) return false;
        if ((this.mEdgeDragsInProgress[n2] & n3) == n3) return false;
        if (f2 <= (float)this.mTouchSlop && f3 <= (float)this.mTouchSlop) {
            return false;
        }
        if (f2 < f3 * 0.5f && this.mCallback.onEdgeLock(n3)) {
            int[] arrn = this.mEdgeDragsLocked;
            arrn[n2] = arrn[n2] | n3;
            return false;
        }
        if ((this.mEdgeDragsInProgress[n2] & n3) != 0) return false;
        if (f2 <= (float)this.mTouchSlop) return false;
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    private boolean checkTouchSlop(View view, float f2, float f3) {
        boolean bl2 = true;
        if (view == null) {
            return false;
        }
        boolean bl3 = this.mCallback.getViewHorizontalDragRange(view) > 0;
        if (this.mCallback.getViewVerticalDragRange(view) <= 0) return false;
        boolean bl4 = true;
        if (bl3 && bl4) {
            if (f2 * f2 + f3 * f3 > (float)(this.mTouchSlop * this.mTouchSlop)) return bl2;
            return false;
        }
        if (bl3) {
            if (Math.abs((float)f2) > (float)this.mTouchSlop) return bl2;
            return false;
        }
        if (!bl4) {
            return false;
        }
        if (Math.abs((float)f3) > (float)this.mTouchSlop) return bl2;
        return false;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private float clampMag(float f2, float f3, float f4) {
        float f5 = Math.abs((float)f2);
        if (f5 < f3) {
            return 0.0f;
        }
        if (f5 <= f4) return f2;
        f3 = f4;
        if (f2 > 0.0f) return f3;
        return - f4;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private int clampMag(int n2, int n3, int n4) {
        int n5 = Math.abs((int)n2);
        if (n5 < n3) {
            return 0;
        }
        if (n5 <= n4) return n2;
        n3 = n4;
        if (n2 > 0) return n3;
        return - n4;
    }

    private void clearMotionHistory() {
        if (this.mInitialMotionX == null) {
            return;
        }
        Arrays.fill((float[])this.mInitialMotionX, (float)0.0f);
        Arrays.fill((float[])this.mInitialMotionY, (float)0.0f);
        Arrays.fill((float[])this.mLastMotionX, (float)0.0f);
        Arrays.fill((float[])this.mLastMotionY, (float)0.0f);
        Arrays.fill((int[])this.mInitialEdgesTouched, (int)0);
        Arrays.fill((int[])this.mEdgeDragsInProgress, (int)0);
        Arrays.fill((int[])this.mEdgeDragsLocked, (int)0);
        this.mPointersDown = 0;
    }

    private void clearMotionHistory(int n2) {
        if (this.mInitialMotionX == null) {
            return;
        }
        this.mInitialMotionX[n2] = 0.0f;
        this.mInitialMotionY[n2] = 0.0f;
        this.mLastMotionX[n2] = 0.0f;
        this.mLastMotionY[n2] = 0.0f;
        this.mInitialEdgesTouched[n2] = 0;
        this.mEdgeDragsInProgress[n2] = 0;
        this.mEdgeDragsLocked[n2] = 0;
        this.mPointersDown &= ~ (1 << n2);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private int computeAxisDuration(int n2, int n3, int n4) {
        if (n2 == 0) {
            return 0;
        }
        int n5 = this.mParentView.getWidth();
        int n6 = n5 / 2;
        float f2 = Math.min((float)1.0f, (float)((float)Math.abs((int)n2) / (float)n5));
        float f3 = n6;
        float f4 = n6;
        f2 = this.distanceInfluenceForSnapDuration(f2);
        if ((n3 = Math.abs((int)n3)) > 0) {
            n2 = Math.round((float)(Math.abs((float)((f2 * f4 + f3) / (float)n3)) * 1000.0f)) * 4;
            do {
                return Math.min((int)n2, (int)600);
                break;
            } while (true);
        }
        n2 = (int)(((float)Math.abs((int)n2) / (float)n4 + 1.0f) * 256.0f);
        return Math.min((int)n2, (int)600);
    }

    /*
     * Enabled aggressive block sorting
     */
    private int computeSettleDuration(View view, int n2, int n3, int n4, int n5) {
        n4 = this.clampMag(n4, (int)this.mMinVelocity, (int)this.mMaxVelocity);
        n5 = this.clampMag(n5, (int)this.mMinVelocity, (int)this.mMaxVelocity);
        int n6 = Math.abs((int)n2);
        int n7 = Math.abs((int)n3);
        int n8 = Math.abs((int)n4);
        int n9 = Math.abs((int)n5);
        int n10 = n8 + n9;
        int n11 = n6 + n7;
        float f2 = n4 != 0 ? (float)n8 / (float)n10 : (float)n6 / (float)n11;
        float f3 = n5 != 0 ? (float)n9 / (float)n10 : (float)n7 / (float)n11;
        n2 = this.computeAxisDuration(n2, n4, this.mCallback.getViewHorizontalDragRange(view));
        n3 = this.computeAxisDuration(n3, n5, this.mCallback.getViewVerticalDragRange(view));
        float f4 = n2;
        return (int)(f3 * (float)n3 + f2 * f4);
    }

    public static ViewDragHelper create(ViewGroup object, float f2, Callback callback) {
        object = ViewDragHelper.create((ViewGroup)object, callback);
        object.mTouchSlop = (int)((float)object.mTouchSlop * (1.0f / f2));
        return object;
    }

    public static ViewDragHelper create(ViewGroup viewGroup, Callback callback) {
        return new ViewDragHelper(viewGroup.getContext(), viewGroup, callback);
    }

    private void dispatchViewReleased(float f2, float f3) {
        this.mReleaseInProgress = true;
        this.mCallback.onViewReleased(this.mCapturedView, f2, f3);
        this.mReleaseInProgress = false;
        if (this.mDragState == 1) {
            this.setDragState(0);
        }
    }

    private float distanceInfluenceForSnapDuration(float f2) {
        return (float)Math.sin((double)((float)((double)(f2 - 0.5f) * 0.4712389167638204)));
    }

    private void dragTo(int n2, int n3, int n4, int n5) {
        int n6 = this.mCapturedView.getLeft();
        int n7 = this.mCapturedView.getTop();
        if (n4 != 0) {
            n2 = this.mCallback.clampViewPositionHorizontal(this.mCapturedView, n2, n4);
            this.mCapturedView.offsetLeftAndRight(n2 - n6);
        }
        if (n5 != 0) {
            n3 = this.mCallback.clampViewPositionVertical(this.mCapturedView, n3, n5);
            this.mCapturedView.offsetTopAndBottom(n3 - n7);
        }
        if (n4 != 0 || n5 != 0) {
            this.mCallback.onViewPositionChanged(this.mCapturedView, n2, n3, n2 - n6, n3 - n7);
        }
    }

    private void ensureMotionHistorySizeForId(int n2) {
        if (this.mInitialMotionX == null || this.mInitialMotionX.length <= n2) {
            float[] arrf = new float[n2 + 1];
            float[] arrf2 = new float[n2 + 1];
            float[] arrf3 = new float[n2 + 1];
            float[] arrf4 = new float[n2 + 1];
            int[] arrn = new int[n2 + 1];
            int[] arrn2 = new int[n2 + 1];
            int[] arrn3 = new int[n2 + 1];
            if (this.mInitialMotionX != null) {
                System.arraycopy((Object)this.mInitialMotionX, (int)0, (Object)arrf, (int)0, (int)this.mInitialMotionX.length);
                System.arraycopy((Object)this.mInitialMotionY, (int)0, (Object)arrf2, (int)0, (int)this.mInitialMotionY.length);
                System.arraycopy((Object)this.mLastMotionX, (int)0, (Object)arrf3, (int)0, (int)this.mLastMotionX.length);
                System.arraycopy((Object)this.mLastMotionY, (int)0, (Object)arrf4, (int)0, (int)this.mLastMotionY.length);
                System.arraycopy((Object)this.mInitialEdgesTouched, (int)0, (Object)arrn, (int)0, (int)this.mInitialEdgesTouched.length);
                System.arraycopy((Object)this.mEdgeDragsInProgress, (int)0, (Object)arrn2, (int)0, (int)this.mEdgeDragsInProgress.length);
                System.arraycopy((Object)this.mEdgeDragsLocked, (int)0, (Object)arrn3, (int)0, (int)this.mEdgeDragsLocked.length);
            }
            this.mInitialMotionX = arrf;
            this.mInitialMotionY = arrf2;
            this.mLastMotionX = arrf3;
            this.mLastMotionY = arrf4;
            this.mInitialEdgesTouched = arrn;
            this.mEdgeDragsInProgress = arrn2;
            this.mEdgeDragsLocked = arrn3;
        }
    }

    private boolean forceSettleCapturedViewAt(int n2, int n3, int n4, int n5) {
        int n6 = this.mCapturedView.getLeft();
        int n7 = this.mCapturedView.getTop();
        if ((n2 -= n6) == 0 && (n3 -= n7) == 0) {
            this.mScroller.abortAnimation();
            this.setDragState(0);
            return false;
        }
        n4 = this.computeSettleDuration(this.mCapturedView, n2, n3, n4, n5);
        this.mScroller.startScroll(n6, n7, n2, n3, n4);
        this.setDragState(2);
        return true;
    }

    private int getEdgesTouched(int n2, int n3) {
        int n4 = 0;
        if (n2 < this.mParentView.getLeft() + this.mEdgeSize) {
            n4 = 1;
        }
        int n5 = n4;
        if (n3 < this.mParentView.getTop() + this.mEdgeSize) {
            n5 = n4 | 4;
        }
        n4 = n5;
        if (n2 > this.mParentView.getRight() - this.mEdgeSize) {
            n4 = n5 | 2;
        }
        n2 = n4;
        if (n3 > this.mParentView.getBottom() - this.mEdgeSize) {
            n2 = n4 | 8;
        }
        return n2;
    }

    private void releaseViewForPointerUp() {
        this.mVelocityTracker.computeCurrentVelocity(1000, this.mMaxVelocity);
        this.dispatchViewReleased(this.clampMag(VelocityTrackerCompat.getXVelocity(this.mVelocityTracker, this.mActivePointerId), this.mMinVelocity, this.mMaxVelocity), this.clampMag(VelocityTrackerCompat.getYVelocity(this.mVelocityTracker, this.mActivePointerId), this.mMinVelocity, this.mMaxVelocity));
    }

    /*
     * Enabled aggressive block sorting
     */
    private void reportNewEdgeDrags(float f2, float f3, int n2) {
        int n3 = 1;
        if (!this.checkNewEdgeDrag(f2, f3, n2, 1)) {
            n3 = 0;
        }
        int n4 = n3;
        if (this.checkNewEdgeDrag(f3, f2, n2, 4)) {
            n4 = n3 | 4;
        }
        n3 = n4;
        if (this.checkNewEdgeDrag(f2, f3, n2, 2)) {
            n3 = n4 | 2;
        }
        n4 = n3;
        if (this.checkNewEdgeDrag(f3, f2, n2, 8)) {
            n4 = n3 | 8;
        }
        if (n4 != 0) {
            int[] arrn = this.mEdgeDragsInProgress;
            arrn[n2] = arrn[n2] | n4;
            this.mCallback.onEdgeDragStarted(n4, n2);
        }
    }

    private void saveInitialMotion(float f2, float f3, int n2) {
        this.ensureMotionHistorySizeForId(n2);
        float[] arrf = this.mInitialMotionX;
        this.mLastMotionX[n2] = f2;
        arrf[n2] = f2;
        arrf = this.mInitialMotionY;
        this.mLastMotionY[n2] = f3;
        arrf[n2] = f3;
        this.mInitialEdgesTouched[n2] = this.getEdgesTouched((int)f2, (int)f3);
        this.mPointersDown |= 1 << n2;
    }

    private void saveLastMotion(MotionEvent motionEvent) {
        int n2 = MotionEventCompat.getPointerCount(motionEvent);
        for (int i2 = 0; i2 < n2; ++i2) {
            int n3 = MotionEventCompat.getPointerId(motionEvent, i2);
            float f2 = MotionEventCompat.getX(motionEvent, i2);
            float f3 = MotionEventCompat.getY(motionEvent, i2);
            this.mLastMotionX[n3] = f2;
            this.mLastMotionY[n3] = f3;
        }
    }

    public void abort() {
        this.cancel();
        if (this.mDragState == 2) {
            int n2 = this.mScroller.getCurrX();
            int n3 = this.mScroller.getCurrY();
            this.mScroller.abortAnimation();
            int n4 = this.mScroller.getCurrX();
            int n5 = this.mScroller.getCurrY();
            this.mCallback.onViewPositionChanged(this.mCapturedView, n4, n5, n4 - n2, n5 - n3);
        }
        this.setDragState(0);
    }

    protected boolean canScroll(View view, boolean bl2, int n2, int n3, int n4, int n5) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup)view;
            int n6 = view.getScrollX();
            int n7 = view.getScrollY();
            for (int i2 = viewGroup.getChildCount() - 1; i2 >= 0; --i2) {
                View view2 = viewGroup.getChildAt(i2);
                if (n4 + n6 < view2.getLeft() || n4 + n6 >= view2.getRight() || n5 + n7 < view2.getTop() || n5 + n7 >= view2.getBottom() || !this.canScroll(view2, true, n2, n3, n4 + n6 - view2.getLeft(), n5 + n7 - view2.getTop())) continue;
                return true;
            }
        }
        if (bl2 && (ViewCompat.canScrollHorizontally(view, - n2) || ViewCompat.canScrollVertically(view, - n3))) {
            return true;
        }
        return false;
    }

    public void cancel() {
        this.mActivePointerId = -1;
        this.clearMotionHistory();
        if (this.mVelocityTracker != null) {
            this.mVelocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    public void captureChildView(View view, int n2) {
        if (view.getParent() != this.mParentView) {
            throw new IllegalArgumentException("captureChildView: parameter must be a descendant of the ViewDragHelper's tracked parent view (" + (Object)this.mParentView + ")");
        }
        this.mCapturedView = view;
        this.mActivePointerId = n2;
        this.mCallback.onViewCaptured(view, n2);
        this.setDragState(1);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean checkTouchSlop(int n2) {
        boolean bl2 = false;
        int n3 = this.mInitialMotionX.length;
        int n4 = 0;
        do {
            boolean bl3 = bl2;
            if (n4 >= n3) return bl3;
            if (this.checkTouchSlop(n2, n4)) {
                return true;
            }
            ++n4;
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean checkTouchSlop(int n2, int n3) {
        boolean bl2 = true;
        if (!this.isPointerDown(n3)) {
            return false;
        }
        boolean bl3 = (n2 & 1) == 1;
        n2 = (n2 & 2) == 2 ? 1 : 0;
        float f2 = this.mLastMotionX[n3] - this.mInitialMotionX[n3];
        float f3 = this.mLastMotionY[n3] - this.mInitialMotionY[n3];
        if (bl3 && n2 != 0) {
            if (f2 * f2 + f3 * f3 > (float)(this.mTouchSlop * this.mTouchSlop)) return bl2;
            return false;
        }
        if (bl3) {
            if (Math.abs((float)f2) > (float)this.mTouchSlop) return bl2;
            return false;
        }
        if (n2 == 0) {
            return false;
        }
        if (Math.abs((float)f3) > (float)this.mTouchSlop) return bl2;
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean continueSettling(boolean bl2) {
        if (this.mDragState == 2) {
            boolean bl3 = this.mScroller.computeScrollOffset();
            int n2 = this.mScroller.getCurrX();
            int n3 = this.mScroller.getCurrY();
            int n4 = n2 - this.mCapturedView.getLeft();
            int n5 = n3 - this.mCapturedView.getTop();
            if (n4 != 0) {
                this.mCapturedView.offsetLeftAndRight(n4);
            }
            if (n5 != 0) {
                this.mCapturedView.offsetTopAndBottom(n5);
            }
            if (n4 != 0 || n5 != 0) {
                this.mCallback.onViewPositionChanged(this.mCapturedView, n2, n3, n4, n5);
            }
            if (bl3 && n2 == this.mScroller.getFinalX() && n3 == this.mScroller.getFinalY()) {
                this.mScroller.abortAnimation();
                bl3 = false;
            }
            if (!bl3) {
                if (bl2) {
                    this.mParentView.post(this.mSetIdleRunnable);
                } else {
                    this.setDragState(0);
                }
            }
        }
        if (this.mDragState == 2) {
            return true;
        }
        return false;
    }

    public View findTopChildUnder(int n2, int n3) {
        for (int i2 = this.mParentView.getChildCount() - 1; i2 >= 0; --i2) {
            View view = this.mParentView.getChildAt(this.mCallback.getOrderedChildIndex(i2));
            if (n2 < view.getLeft() || n2 >= view.getRight() || n3 < view.getTop() || n3 >= view.getBottom()) continue;
            return view;
        }
        return null;
    }

    public void flingCapturedView(int n2, int n3, int n4, int n5) {
        if (!this.mReleaseInProgress) {
            throw new IllegalStateException("Cannot flingCapturedView outside of a call to Callback#onViewReleased");
        }
        this.mScroller.fling(this.mCapturedView.getLeft(), this.mCapturedView.getTop(), (int)VelocityTrackerCompat.getXVelocity(this.mVelocityTracker, this.mActivePointerId), (int)VelocityTrackerCompat.getYVelocity(this.mVelocityTracker, this.mActivePointerId), n2, n4, n3, n5);
        this.setDragState(2);
    }

    public int getActivePointerId() {
        return this.mActivePointerId;
    }

    public View getCapturedView() {
        return this.mCapturedView;
    }

    public int getEdgeSize() {
        return this.mEdgeSize;
    }

    public float getMinVelocity() {
        return this.mMinVelocity;
    }

    public int getTouchSlop() {
        return this.mTouchSlop;
    }

    public int getViewDragState() {
        return this.mDragState;
    }

    public boolean isCapturedViewUnder(int n2, int n3) {
        return this.isViewUnder(this.mCapturedView, n2, n3);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean isEdgeTouched(int n2) {
        boolean bl2 = false;
        int n3 = this.mInitialEdgesTouched.length;
        int n4 = 0;
        do {
            boolean bl3 = bl2;
            if (n4 >= n3) return bl3;
            if (this.isEdgeTouched(n2, n4)) {
                return true;
            }
            ++n4;
        } while (true);
    }

    public boolean isEdgeTouched(int n2, int n3) {
        if (this.isPointerDown(n3) && (this.mInitialEdgesTouched[n3] & n2) != 0) {
            return true;
        }
        return false;
    }

    public boolean isPointerDown(int n2) {
        if ((this.mPointersDown & 1 << n2) != 0) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean isViewUnder(View view, int n2, int n3) {
        if (view == null || n2 < view.getLeft() || n2 >= view.getRight() || n3 < view.getTop() || n3 >= view.getBottom()) {
            return false;
        }
        return true;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public void processTouchEvent(MotionEvent var1_1) {
        block20 : {
            var6_2 = 0;
            var7_3 = 0;
            var9_4 = MotionEventCompat.getActionMasked(var1_1);
            var8_5 = MotionEventCompat.getActionIndex(var1_1);
            if (var9_4 == 0) {
                this.cancel();
            }
            if (this.mVelocityTracker == null) {
                this.mVelocityTracker = VelocityTracker.obtain();
            }
            this.mVelocityTracker.addMovement(var1_1);
            switch (var9_4) {
                default: {
                    return;
                }
                case 0: {
                    var2_6 = var1_1.getX();
                    var3_11 = var1_1.getY();
                    var6_2 = MotionEventCompat.getPointerId(var1_1, 0);
                    var1_1 = this.findTopChildUnder((int)var2_6, (int)var3_11);
                    this.saveInitialMotion(var2_6, var3_11, var6_2);
                    this.tryCaptureViewForDrag((View)var1_1, var6_2);
                    var7_3 = this.mInitialEdgesTouched[var6_2];
                    if ((this.mTrackingEdges & var7_3) == 0) return;
                    this.mCallback.onEdgeTouched(var7_3 & this.mTrackingEdges, var6_2);
                    return;
                }
                case 5: {
                    var6_2 = MotionEventCompat.getPointerId(var1_1, var8_5);
                    var2_7 = MotionEventCompat.getX(var1_1, var8_5);
                    var3_12 = MotionEventCompat.getY(var1_1, var8_5);
                    this.saveInitialMotion(var2_7, var3_12, var6_2);
                    if (this.mDragState == 0) {
                        this.tryCaptureViewForDrag(this.findTopChildUnder((int)var2_7, (int)var3_12), var6_2);
                        var7_3 = this.mInitialEdgesTouched[var6_2];
                        if ((this.mTrackingEdges & var7_3) == 0) return;
                        this.mCallback.onEdgeTouched(var7_3 & this.mTrackingEdges, var6_2);
                        return;
                    }
                    if (this.isCapturedViewUnder((int)var2_7, (int)var3_12) == false) return;
                    this.tryCaptureViewForDrag(this.mCapturedView, var6_2);
                    return;
                }
                case 2: {
                    if (this.mDragState == 1) {
                        var6_2 = MotionEventCompat.findPointerIndex(var1_1, this.mActivePointerId);
                        var2_8 = MotionEventCompat.getX(var1_1, var6_2);
                        var3_13 = MotionEventCompat.getY(var1_1, var6_2);
                        var6_2 = (int)(var2_8 - this.mLastMotionX[this.mActivePointerId]);
                        var7_3 = (int)(var3_13 - this.mLastMotionY[this.mActivePointerId]);
                        this.dragTo(this.mCapturedView.getLeft() + var6_2, this.mCapturedView.getTop() + var7_3, var6_2, var7_3);
                        this.saveLastMotion(var1_1);
                        return;
                    }
                    var8_5 = MotionEventCompat.getPointerCount(var1_1);
                    var6_2 = var7_3;
                    do {
                        if (var6_2 >= var8_5) ** GOTO lbl-1000
                        var7_3 = MotionEventCompat.getPointerId(var1_1, var6_2);
                        var2_9 = MotionEventCompat.getX(var1_1, var6_2);
                        var3_14 = MotionEventCompat.getY(var1_1, var6_2);
                        var4_16 = var2_9 - this.mInitialMotionX[var7_3];
                        var5_17 = var3_14 - this.mInitialMotionY[var7_3];
                        this.reportNewEdgeDrags(var4_16, var5_17, var7_3);
                        if (this.mDragState == 1 || this.checkTouchSlop(var10_18 = this.findTopChildUnder((int)var2_9, (int)var3_14), var4_16, var5_17) && this.tryCaptureViewForDrag(var10_18, var7_3)) lbl-1000: // 2 sources:
                        {
                            this.saveLastMotion(var1_1);
                            return;
                        }
                        ++var6_2;
                    } while (true);
                }
                case 6: {
                    var7_3 = MotionEventCompat.getPointerId(var1_1, var8_5);
                    if (this.mDragState == 1 && var7_3 == this.mActivePointerId) {
                        var8_5 = MotionEventCompat.getPointerCount(var1_1);
                        while (var6_2 < var8_5) {
                            var9_4 = MotionEventCompat.getPointerId(var1_1, var6_2);
                            if (var9_4 != this.mActivePointerId && this.findTopChildUnder((int)(var2_10 = MotionEventCompat.getX(var1_1, var6_2)), (int)(var3_15 = MotionEventCompat.getY(var1_1, var6_2))) == this.mCapturedView && this.tryCaptureViewForDrag(this.mCapturedView, var9_4)) {
                                var6_2 = this.mActivePointerId;
                                break block20;
                            }
                            ++var6_2;
                        }
                        break;
                    }
                    ** GOTO lbl90
                }
                case 1: {
                    if (this.mDragState == 1) {
                        this.releaseViewForPointerUp();
                    }
                    this.cancel();
                    return;
                }
                case 3: {
                    if (this.mDragState == 1) {
                        this.dispatchViewReleased(0.0f, 0.0f);
                    }
                    this.cancel();
                    return;
                }
            }
            var6_2 = -1;
        }
        if (var6_2 == -1) {
            this.releaseViewForPointerUp();
        }
lbl90: // 4 sources:
        this.clearMotionHistory(var7_3);
    }

    void setDragState(int n2) {
        this.mParentView.removeCallbacks(this.mSetIdleRunnable);
        if (this.mDragState != n2) {
            this.mDragState = n2;
            this.mCallback.onViewDragStateChanged(n2);
            if (this.mDragState == 0) {
                this.mCapturedView = null;
            }
        }
    }

    public void setEdgeTrackingEnabled(int n2) {
        this.mTrackingEdges = n2;
    }

    public void setMinVelocity(float f2) {
        this.mMinVelocity = f2;
    }

    public boolean settleCapturedViewAt(int n2, int n3) {
        if (!this.mReleaseInProgress) {
            throw new IllegalStateException("Cannot settleCapturedViewAt outside of a call to Callback#onViewReleased");
        }
        return this.forceSettleCapturedViewAt(n2, n3, (int)VelocityTrackerCompat.getXVelocity(this.mVelocityTracker, this.mActivePointerId), (int)VelocityTrackerCompat.getYVelocity(this.mVelocityTracker, this.mActivePointerId));
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean shouldInterceptTouchEvent(MotionEvent motionEvent) {
        int n2 = MotionEventCompat.getActionMasked(motionEvent);
        int n3 = MotionEventCompat.getActionIndex(motionEvent);
        if (n2 == 0) {
            this.cancel();
        }
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        switch (n2) {
            case 0: {
                float f2 = motionEvent.getX();
                float f3 = motionEvent.getY();
                n3 = MotionEventCompat.getPointerId(motionEvent, 0);
                this.saveInitialMotion(f2, f3, n3);
                motionEvent = this.findTopChildUnder((int)f2, (int)f3);
                if (motionEvent == this.mCapturedView && this.mDragState == 2) {
                    this.tryCaptureViewForDrag((View)motionEvent, n3);
                }
                if ((this.mTrackingEdges & (n2 = this.mInitialEdgesTouched[n3])) == 0) break;
                this.mCallback.onEdgeTouched(n2 & this.mTrackingEdges, n3);
                break;
            }
            case 5: {
                n2 = MotionEventCompat.getPointerId(motionEvent, n3);
                float f4 = MotionEventCompat.getX(motionEvent, n3);
                float f5 = MotionEventCompat.getY(motionEvent, n3);
                this.saveInitialMotion(f4, f5, n2);
                if (this.mDragState == 0) {
                    n3 = this.mInitialEdgesTouched[n2];
                    if ((this.mTrackingEdges & n3) == 0) break;
                    this.mCallback.onEdgeTouched(n3 & this.mTrackingEdges, n2);
                    break;
                }
                if (this.mDragState != 2 || (motionEvent = this.findTopChildUnder((int)f4, (int)f5)) != this.mCapturedView) break;
                this.tryCaptureViewForDrag((View)motionEvent, n2);
                break;
            }
            case 2: {
                if (this.mInitialMotionX == null || this.mInitialMotionY == null) break;
                int n4 = MotionEventCompat.getPointerCount(motionEvent);
                for (n3 = 0; n3 < n4; ++n3) {
                    int n5 = MotionEventCompat.getPointerId(motionEvent, n3);
                    float f6 = MotionEventCompat.getX(motionEvent, n3);
                    float f7 = MotionEventCompat.getY(motionEvent, n3);
                    float f8 = f6 - this.mInitialMotionX[n5];
                    float f9 = f7 - this.mInitialMotionY[n5];
                    View view = this.findTopChildUnder((int)f6, (int)f7);
                    n2 = view != null && this.checkTouchSlop(view, f8, f9) ? 1 : 0;
                    if (n2 != 0) {
                        int n6 = view.getLeft();
                        int n7 = (int)f8;
                        n7 = this.mCallback.clampViewPositionHorizontal(view, n7 + n6, (int)f8);
                        int n8 = view.getTop();
                        int n9 = (int)f9;
                        n9 = this.mCallback.clampViewPositionVertical(view, n9 + n8, (int)f9);
                        int n10 = this.mCallback.getViewHorizontalDragRange(view);
                        int n11 = this.mCallback.getViewVerticalDragRange(view);
                        if ((n10 == 0 || n10 > 0 && n7 == n6) && (n11 == 0 || n11 > 0 && n9 == n8)) break;
                    }
                    this.reportNewEdgeDrags(f8, f9, n5);
                    if (this.mDragState == 1 || n2 != 0 && this.tryCaptureViewForDrag(view, n5)) break;
                }
                this.saveLastMotion(motionEvent);
                break;
            }
            case 6: {
                this.clearMotionHistory(MotionEventCompat.getPointerId(motionEvent, n3));
                break;
            }
            case 1: 
            case 3: {
                this.cancel();
            }
        }
        if (this.mDragState == 1) {
            return true;
        }
        return false;
    }

    public boolean smoothSlideViewTo(View view, int n2, int n3) {
        this.mCapturedView = view;
        this.mActivePointerId = -1;
        boolean bl2 = this.forceSettleCapturedViewAt(n2, n3, 0, 0);
        if (!bl2 && this.mDragState == 0 && this.mCapturedView != null) {
            this.mCapturedView = null;
        }
        return bl2;
    }

    boolean tryCaptureViewForDrag(View view, int n2) {
        if (view == this.mCapturedView && this.mActivePointerId == n2) {
            return true;
        }
        if (view != null && this.mCallback.tryCaptureView(view, n2)) {
            this.mActivePointerId = n2;
            this.captureChildView(view, n2);
            return true;
        }
        return false;
    }

    public static abstract class Callback {
        public int clampViewPositionHorizontal(View view, int n2, int n3) {
            return 0;
        }

        public int clampViewPositionVertical(View view, int n2, int n3) {
            return 0;
        }

        public int getOrderedChildIndex(int n2) {
            return n2;
        }

        public int getViewHorizontalDragRange(View view) {
            return 0;
        }

        public int getViewVerticalDragRange(View view) {
            return 0;
        }

        public void onEdgeDragStarted(int n2, int n3) {
        }

        public boolean onEdgeLock(int n2) {
            return false;
        }

        public void onEdgeTouched(int n2, int n3) {
        }

        public void onViewCaptured(View view, int n2) {
        }

        public void onViewDragStateChanged(int n2) {
        }

        public void onViewPositionChanged(View view, int n2, int n3, int n4, int n5) {
        }

        public void onViewReleased(View view, float f2, float f3) {
        }

        public abstract boolean tryCaptureView(View var1, int var2);
    }

}

