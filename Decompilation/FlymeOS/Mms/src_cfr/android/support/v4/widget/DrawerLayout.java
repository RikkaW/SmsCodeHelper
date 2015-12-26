/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.content.res.TypedArray
 *  android.graphics.Canvas
 *  android.graphics.Paint
 *  android.graphics.Rect
 *  android.graphics.drawable.ColorDrawable
 *  android.graphics.drawable.Drawable
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  android.os.SystemClock
 *  android.util.AttributeSet
 *  android.util.DisplayMetrics
 *  android.view.KeyEvent
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.View$BaseSavedState
 *  android.view.View$MeasureSpec
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewGroup$MarginLayoutParams
 *  android.view.ViewParent
 *  android.view.accessibility.AccessibilityEvent
 *  java.lang.Integer
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.KeyEventCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewGroupCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.widget.DrawerLayout$SavedState$1;
import android.support.v4.widget.DrawerLayout$ViewDragCallback$1;
import android.support.v4.widget.DrawerLayoutCompatApi21;
import android.support.v4.widget.DrawerLayoutImpl;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

public class DrawerLayout
extends ViewGroup
implements DrawerLayoutImpl {
    private static final boolean ALLOW_EDGE_LOCK = false;
    private static final boolean CAN_HIDE_DESCENDANTS;
    private static final boolean CHILDREN_DISALLOW_INTERCEPT = true;
    private static final int DEFAULT_SCRIM_COLOR = -1728053248;
    static final DrawerLayoutCompatImpl IMPL;
    private static final int[] LAYOUT_ATTRS;
    public static final int LOCK_MODE_LOCKED_CLOSED = 1;
    public static final int LOCK_MODE_LOCKED_OPEN = 2;
    public static final int LOCK_MODE_UNLOCKED = 0;
    private static final int MIN_DRAWER_MARGIN = 64;
    private static final int MIN_FLING_VELOCITY = 400;
    private static final int PEEK_DELAY = 160;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_IDLE = 0;
    public static final int STATE_SETTLING = 2;
    private static final String TAG = "DrawerLayout";
    private static final float TOUCH_SLOP_SENSITIVITY = 1.0f;
    private final ChildAccessibilityDelegate mChildAccessibilityDelegate;
    private boolean mChildrenCanceledTouch;
    private boolean mDisallowInterceptRequested;
    private boolean mDrawStatusBarBackground;
    private int mDrawerState;
    private boolean mFirstLayout;
    private boolean mInLayout;
    private float mInitialMotionX;
    private float mInitialMotionY;
    private Object mLastInsets;
    private final ViewDragCallback mLeftCallback;
    private final ViewDragHelper mLeftDragger;
    private DrawerListener mListener;
    private int mLockModeLeft;
    private int mLockModeRight;
    private int mMinDrawerMargin;
    private final ViewDragCallback mRightCallback;
    private final ViewDragHelper mRightDragger;
    private int mScrimColor;
    private float mScrimOpacity;
    private Paint mScrimPaint;
    private Drawable mShadowLeft;
    private Drawable mShadowRight;
    private Drawable mStatusBarBackground;
    private CharSequence mTitleLeft;
    private CharSequence mTitleRight;

    /*
     * Enabled aggressive block sorting
     */
    static {
        boolean bl2 = true;
        LAYOUT_ATTRS = new int[]{16842931};
        if (Build.VERSION.SDK_INT < 19) {
            bl2 = false;
        }
        CAN_HIDE_DESCENDANTS = bl2;
        if (Build.VERSION.SDK_INT >= 21) {
            IMPL = new DrawerLayoutCompatImplApi21();
            return;
        }
        IMPL = new DrawerLayoutCompatImplBase();
    }

    public DrawerLayout(Context context) {
        this(context, null);
    }

    public DrawerLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DrawerLayout(Context context, AttributeSet attributeSet, int n2) {
        super(context, attributeSet, n2);
        this.mChildAccessibilityDelegate = new ChildAccessibilityDelegate();
        this.mScrimColor = -1728053248;
        this.mScrimPaint = new Paint();
        this.mFirstLayout = true;
        this.setDescendantFocusability(262144);
        float f2 = this.getResources().getDisplayMetrics().density;
        this.mMinDrawerMargin = (int)(64.0f * f2 + 0.5f);
        this.mLeftCallback = new ViewDragCallback(3);
        this.mRightCallback = new ViewDragCallback(5);
        this.mLeftDragger = ViewDragHelper.create(this, 1.0f, this.mLeftCallback);
        this.mLeftDragger.setEdgeTrackingEnabled(1);
        this.mLeftDragger.setMinVelocity(f2 *= 400.0f);
        this.mLeftCallback.setDragger(this.mLeftDragger);
        this.mRightDragger = ViewDragHelper.create(this, 1.0f, this.mRightCallback);
        this.mRightDragger.setEdgeTrackingEnabled(2);
        this.mRightDragger.setMinVelocity(f2);
        this.mRightCallback.setDragger(this.mRightDragger);
        this.setFocusableInTouchMode(true);
        ViewCompat.setImportantForAccessibility((View)this, 1);
        ViewCompat.setAccessibilityDelegate((View)this, new AccessibilityDelegate());
        ViewGroupCompat.setMotionEventSplittingEnabled(this, false);
        if (ViewCompat.getFitsSystemWindows((View)this)) {
            IMPL.configureApplyInsets((View)this);
            this.mStatusBarBackground = IMPL.getDefaultStatusBarBackground(context);
        }
    }

    private View findVisibleDrawer() {
        int n2 = this.getChildCount();
        for (int i2 = 0; i2 < n2; ++i2) {
            View view = this.getChildAt(i2);
            if (!this.isDrawerView(view) || !this.isDrawerVisible(view)) continue;
            return view;
        }
        return null;
    }

    static String gravityToString(int n2) {
        if ((n2 & 3) == 3) {
            return "LEFT";
        }
        if ((n2 & 5) == 5) {
            return "RIGHT";
        }
        return Integer.toHexString((int)n2);
    }

    private static boolean hasOpaqueBackground(View view) {
        boolean bl2 = false;
        view = view.getBackground();
        boolean bl3 = bl2;
        if (view != null) {
            bl3 = bl2;
            if (view.getOpacity() == -1) {
                bl3 = true;
            }
        }
        return bl3;
    }

    private boolean hasPeekingDrawer() {
        int n2 = this.getChildCount();
        for (int i2 = 0; i2 < n2; ++i2) {
            if (!((LayoutParams)this.getChildAt((int)i2).getLayoutParams()).isPeeking) continue;
            return true;
        }
        return false;
    }

    private boolean hasVisibleDrawer() {
        if (this.findVisibleDrawer() != null) {
            return true;
        }
        return false;
    }

    private static boolean includeChildForAccessibility(View view) {
        if (ViewCompat.getImportantForAccessibility(view) != 4 && ViewCompat.getImportantForAccessibility(view) != 2) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void updateChildrenImportantForAccessibility(View view, boolean bl2) {
        int n2 = this.getChildCount();
        int n3 = 0;
        while (n3 < n2) {
            View view2 = this.getChildAt(n3);
            if (!bl2 && !this.isDrawerView(view2) || bl2 && view2 == view) {
                ViewCompat.setImportantForAccessibility(view2, 1);
            } else {
                ViewCompat.setImportantForAccessibility(view2, 4);
            }
            ++n3;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void addView(View view, int n2, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, n2, layoutParams);
        if (this.findOpenDrawer() != null || this.isDrawerView(view)) {
            ViewCompat.setImportantForAccessibility(view, 4);
        } else {
            ViewCompat.setImportantForAccessibility(view, 1);
        }
        if (!CAN_HIDE_DESCENDANTS) {
            ViewCompat.setAccessibilityDelegate(view, this.mChildAccessibilityDelegate);
        }
    }

    void cancelChildViewTouch() {
        if (!this.mChildrenCanceledTouch) {
            long l2 = SystemClock.uptimeMillis();
            MotionEvent motionEvent = MotionEvent.obtain((long)l2, (long)l2, (int)3, (float)0.0f, (float)0.0f, (int)0);
            int n2 = this.getChildCount();
            for (int i2 = 0; i2 < n2; ++i2) {
                this.getChildAt(i2).dispatchTouchEvent(motionEvent);
            }
            motionEvent.recycle();
            this.mChildrenCanceledTouch = true;
        }
    }

    boolean checkDrawerViewAbsoluteGravity(View view, int n2) {
        if ((this.getDrawerViewAbsoluteGravity(view) & n2) == n2) {
            return true;
        }
        return false;
    }

    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof LayoutParams && super.checkLayoutParams(layoutParams)) {
            return true;
        }
        return false;
    }

    public void closeDrawer(int n2) {
        View view = this.findDrawerWithGravity(n2);
        if (view == null) {
            throw new IllegalArgumentException("No drawer view found with gravity " + DrawerLayout.gravityToString(n2));
        }
        this.closeDrawer(view);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void closeDrawer(View object) {
        if (!this.isDrawerView((View)object)) {
            throw new IllegalArgumentException("View " + object + " is not a sliding drawer");
        }
        if (this.mFirstLayout) {
            object = (LayoutParams)object.getLayoutParams();
            object.onScreen = 0.0f;
            object.knownOpen = false;
        } else if (this.checkDrawerViewAbsoluteGravity((View)object, 3)) {
            this.mLeftDragger.smoothSlideViewTo((View)object, - object.getWidth(), object.getTop());
        } else {
            this.mRightDragger.smoothSlideViewTo((View)object, this.getWidth(), object.getTop());
        }
        this.invalidate();
    }

    public void closeDrawers() {
        this.closeDrawers(false);
    }

    /*
     * Enabled aggressive block sorting
     */
    void closeDrawers(boolean bl2) {
        int n2 = this.getChildCount();
        int n3 = 0;
        for (int i2 = 0; i2 < n2; ++i2) {
            View view = this.getChildAt(i2);
            LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
            int n4 = n3;
            if (this.isDrawerView(view)) {
                if (bl2 && !layoutParams.isPeeking) {
                    n4 = n3;
                } else {
                    n4 = view.getWidth();
                    n3 = this.checkDrawerViewAbsoluteGravity(view, 3) ? (n3 |= this.mLeftDragger.smoothSlideViewTo(view, - n4, view.getTop())) : (n3 |= this.mRightDragger.smoothSlideViewTo(view, this.getWidth(), view.getTop()));
                    layoutParams.isPeeking = false;
                    n4 = n3;
                }
            }
            n3 = n4;
        }
        this.mLeftCallback.removeCallbacks();
        this.mRightCallback.removeCallbacks();
        if (n3 != 0) {
            this.invalidate();
        }
    }

    public void computeScroll() {
        int n2 = this.getChildCount();
        float f2 = 0.0f;
        for (int i2 = 0; i2 < n2; ++i2) {
            f2 = Math.max((float)f2, (float)((LayoutParams)this.getChildAt((int)i2).getLayoutParams()).onScreen);
        }
        this.mScrimOpacity = f2;
        if (this.mLeftDragger.continueSettling(true) | this.mRightDragger.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation((View)this);
        }
    }

    void dispatchOnDrawerClosed(View view) {
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        if (layoutParams.knownOpen) {
            layoutParams.knownOpen = false;
            if (this.mListener != null) {
                this.mListener.onDrawerClosed(view);
            }
            this.updateChildrenImportantForAccessibility(view, false);
            if (this.hasWindowFocus() && (view = this.getRootView()) != null) {
                view.sendAccessibilityEvent(32);
            }
        }
    }

    void dispatchOnDrawerOpened(View view) {
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        if (!layoutParams.knownOpen) {
            layoutParams.knownOpen = true;
            if (this.mListener != null) {
                this.mListener.onDrawerOpened(view);
            }
            this.updateChildrenImportantForAccessibility(view, true);
            if (this.hasWindowFocus()) {
                this.sendAccessibilityEvent(32);
            }
            view.requestFocus();
        }
    }

    void dispatchOnDrawerSlide(View view, float f2) {
        if (this.mListener != null) {
            this.mListener.onDrawerSlide(view, f2);
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    protected boolean drawChild(Canvas var1_1, View var2_2, long var3_3) {
        var12_4 = this.getHeight();
        var15_5 = this.isContentView(var2_2);
        var8_6 = 0;
        var10_7 = 0;
        var6_8 = this.getWidth();
        var13_9 = var1_1.save();
        var7_10 = var6_8;
        if (!var15_5) ** GOTO lbl32
        var14_11 = this.getChildCount();
        var9_12 = 0;
        var8_6 = var10_7;
        do {
            if (var9_12 >= var14_11) ** GOTO lbl30
            var17_14 = this.getChildAt(var9_12);
            if (var17_14 == var2_2 || var17_14.getVisibility() != 0 || !DrawerLayout.hasOpaqueBackground(var17_14) || !this.isDrawerView(var17_14)) ** GOTO lbl-1000
            if (var17_14.getHeight() >= var12_4) ** GOTO lbl20
            var10_7 = var8_6;
            var7_10 = var6_8;
            ** GOTO lbl63
lbl20: // 1 sources:
            if (!this.checkDrawerViewAbsoluteGravity(var17_14, 3)) ** GOTO lbl24
            var7_10 = var17_14.getRight();
            if (var7_10 <= var8_6) ** GOTO lbl60
            ** GOTO lbl61
lbl24: // 1 sources:
            var7_10 = var11_13 = var17_14.getLeft();
            var10_7 = var8_6;
            if (var11_13 >= var6_8) lbl-1000: // 2 sources:
            {
                var7_10 = var6_8;
                var10_7 = var8_6;
            }
            ** GOTO lbl63
lbl30: // 1 sources:
            var1_1.clipRect(var8_6, 0, var6_8, this.getHeight());
            var7_10 = var6_8;
lbl32: // 2 sources:
            var16_15 = super.drawChild(var1_1, var2_2, var3_3);
            var1_1.restoreToCount(var13_9);
            if (this.mScrimOpacity > 0.0f && var15_5) {
                var6_8 = (int)((float)((this.mScrimColor & -16777216) >>> 24) * this.mScrimOpacity);
                var9_12 = this.mScrimColor;
                this.mScrimPaint.setColor(var6_8 << 24 | var9_12 & 16777215);
                var1_1.drawRect((float)var8_6, 0.0f, (float)var7_10, (float)this.getHeight(), this.mScrimPaint);
                return var16_15;
            }
            if (this.mShadowLeft != null && this.checkDrawerViewAbsoluteGravity(var2_2, 3)) {
                var6_8 = this.mShadowLeft.getIntrinsicWidth();
                var7_10 = var2_2.getRight();
                var8_6 = this.mLeftDragger.getEdgeSize();
                var5_16 = Math.max((float)0.0f, (float)Math.min((float)((float)var7_10 / (float)var8_6), (float)1.0f));
                this.mShadowLeft.setBounds(var7_10, var2_2.getTop(), var6_8 + var7_10, var2_2.getBottom());
                this.mShadowLeft.setAlpha((int)(255.0f * var5_16));
                this.mShadowLeft.draw(var1_1);
                return var16_15;
            }
            if (this.mShadowRight == null) return var16_15;
            if (this.checkDrawerViewAbsoluteGravity(var2_2, 5) == false) return var16_15;
            var6_8 = this.mShadowRight.getIntrinsicWidth();
            var7_10 = var2_2.getLeft();
            var8_6 = this.getWidth();
            var9_12 = this.mRightDragger.getEdgeSize();
            var5_17 = Math.max((float)0.0f, (float)Math.min((float)((float)(var8_6 - var7_10) / (float)var9_12), (float)1.0f));
            this.mShadowRight.setBounds(var7_10 - var6_8, var2_2.getTop(), var7_10, var2_2.getBottom());
            this.mShadowRight.setAlpha((int)(255.0f * var5_17));
            this.mShadowRight.draw(var1_1);
            return var16_15;
lbl60: // 1 sources:
            var7_10 = var8_6;
lbl61: // 2 sources:
            var10_7 = var7_10;
            var7_10 = var6_8;
lbl63: // 3 sources:
            ++var9_12;
            var6_8 = var7_10;
            var8_6 = var10_7;
        } while (true);
    }

    View findDrawerWithGravity(int n2) {
        int n3 = GravityCompat.getAbsoluteGravity(n2, ViewCompat.getLayoutDirection((View)this));
        int n4 = this.getChildCount();
        for (n2 = 0; n2 < n4; ++n2) {
            View view = this.getChildAt(n2);
            if ((this.getDrawerViewAbsoluteGravity(view) & 7) != (n3 & 7)) continue;
            return view;
        }
        return null;
    }

    View findOpenDrawer() {
        int n2 = this.getChildCount();
        for (int i2 = 0; i2 < n2; ++i2) {
            View view = this.getChildAt(i2);
            if (!((LayoutParams)view.getLayoutParams()).knownOpen) continue;
            return view;
        }
        return null;
    }

    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -1);
    }

    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(this.getContext(), attributeSet);
    }

    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof LayoutParams) {
            return new LayoutParams((LayoutParams)layoutParams);
        }
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new LayoutParams((ViewGroup.MarginLayoutParams)layoutParams);
        }
        return new LayoutParams(layoutParams);
    }

    public int getDrawerLockMode(int n2) {
        if ((n2 = GravityCompat.getAbsoluteGravity(n2, ViewCompat.getLayoutDirection((View)this))) == 3) {
            return this.mLockModeLeft;
        }
        if (n2 == 5) {
            return this.mLockModeRight;
        }
        return 0;
    }

    public int getDrawerLockMode(View view) {
        int n2 = this.getDrawerViewAbsoluteGravity(view);
        if (n2 == 3) {
            return this.mLockModeLeft;
        }
        if (n2 == 5) {
            return this.mLockModeRight;
        }
        return 0;
    }

    @Nullable
    public CharSequence getDrawerTitle(int n2) {
        if ((n2 = GravityCompat.getAbsoluteGravity(n2, ViewCompat.getLayoutDirection((View)this))) == 3) {
            return this.mTitleLeft;
        }
        if (n2 == 5) {
            return this.mTitleRight;
        }
        return null;
    }

    int getDrawerViewAbsoluteGravity(View view) {
        return GravityCompat.getAbsoluteGravity(((LayoutParams)view.getLayoutParams()).gravity, ViewCompat.getLayoutDirection((View)this));
    }

    float getDrawerViewOffset(View view) {
        return ((LayoutParams)view.getLayoutParams()).onScreen;
    }

    public Drawable getStatusBarBackgroundDrawable() {
        return this.mStatusBarBackground;
    }

    boolean isContentView(View view) {
        if (((LayoutParams)view.getLayoutParams()).gravity == 0) {
            return true;
        }
        return false;
    }

    public boolean isDrawerOpen(int n2) {
        View view = this.findDrawerWithGravity(n2);
        if (view != null) {
            return this.isDrawerOpen(view);
        }
        return false;
    }

    public boolean isDrawerOpen(View view) {
        if (!this.isDrawerView(view)) {
            throw new IllegalArgumentException("View " + (Object)view + " is not a drawer");
        }
        return ((LayoutParams)view.getLayoutParams()).knownOpen;
    }

    boolean isDrawerView(View view) {
        if ((GravityCompat.getAbsoluteGravity(((LayoutParams)view.getLayoutParams()).gravity, ViewCompat.getLayoutDirection(view)) & 7) != 0) {
            return true;
        }
        return false;
    }

    public boolean isDrawerVisible(int n2) {
        View view = this.findDrawerWithGravity(n2);
        if (view != null) {
            return this.isDrawerVisible(view);
        }
        return false;
    }

    public boolean isDrawerVisible(View view) {
        if (!this.isDrawerView(view)) {
            throw new IllegalArgumentException("View " + (Object)view + " is not a drawer");
        }
        if (((LayoutParams)view.getLayoutParams()).onScreen > 0.0f) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    void moveDrawerToOffset(View view, float f2) {
        float f3 = this.getDrawerViewOffset(view);
        int n2 = view.getWidth();
        int n3 = (int)(f3 * (float)n2);
        n2 = (int)((float)n2 * f2) - n3;
        if (!this.checkDrawerViewAbsoluteGravity(view, 3)) {
            n2 = - n2;
        }
        view.offsetLeftAndRight(n2);
        this.setDrawerViewOffset(view, f2);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mFirstLayout = true;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mFirstLayout = true;
    }

    public void onDraw(Canvas canvas) {
        int n2;
        super.onDraw(canvas);
        if (this.mDrawStatusBarBackground && this.mStatusBarBackground != null && (n2 = IMPL.getTopInset(this.mLastInsets)) > 0) {
            this.mStatusBarBackground.setBounds(0, 0, this.getWidth(), n2);
            this.mStatusBarBackground.draw(canvas);
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public boolean onInterceptTouchEvent(MotionEvent var1_1) {
        var5_2 = false;
        var4_3 = MotionEventCompat.getActionMasked(var1_1);
        var6_4 = this.mLeftDragger.shouldInterceptTouchEvent(var1_1);
        var7_5 = this.mRightDragger.shouldInterceptTouchEvent(var1_1);
        switch (var4_3) {
            case 0: {
                var2_6 = var1_1.getX();
                var3_7 = var1_1.getY();
                this.mInitialMotionX = var2_6;
                this.mInitialMotionY = var3_7;
                var4_3 = this.mScrimOpacity > 0.0f && (var1_1 = this.mLeftDragger.findTopChildUnder((int)var2_6, (int)var3_7)) != null && this.isContentView((View)var1_1) != false ? 1 : 0;
            }
            case 2: {
                if (!this.mLeftDragger.checkTouchSlop(3)) break;
                this.mLeftCallback.removeCallbacks();
                this.mRightCallback.removeCallbacks();
                var4_3 = 0;
                ** GOTO lbl26
            }
            case 1: 
            case 3: {
                this.closeDrawers(true);
                this.mDisallowInterceptRequested = false;
                this.mChildrenCanceledTouch = false;
            }
        }
        var4_3 = 0;
        ** GOTO lbl26
        this.mDisallowInterceptRequested = false;
        this.mChildrenCanceledTouch = false;
lbl26: // 3 sources:
        if ((var6_4 | var7_5) != false) return true;
        if (var4_3 != 0) return true;
        if (this.hasPeekingDrawer() != false) return true;
        if (this.mChildrenCanceledTouch == false) return var5_2;
        return true;
    }

    public boolean onKeyDown(int n2, KeyEvent keyEvent) {
        if (n2 == 4 && this.hasVisibleDrawer()) {
            KeyEventCompat.startTracking(keyEvent);
            return true;
        }
        return super.onKeyDown(n2, keyEvent);
    }

    public boolean onKeyUp(int n2, KeyEvent keyEvent) {
        if (n2 == 4) {
            keyEvent = this.findVisibleDrawer();
            if (keyEvent != null && this.getDrawerLockMode((View)keyEvent) == 0) {
                this.closeDrawers();
            }
            if (keyEvent != null) {
                return true;
            }
            return false;
        }
        return super.onKeyUp(n2, keyEvent);
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void onLayout(boolean bl2, int n2, int n3, int n4, int n5) {
        this.mInLayout = true;
        int n6 = n4 - n2;
        int n7 = this.getChildCount();
        n4 = 0;
        do {
            if (n4 >= n7) {
                this.mInLayout = false;
                this.mFirstLayout = false;
                return;
            }
            View view = this.getChildAt(n4);
            if (view.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
                if (this.isContentView(view)) {
                    view.layout(layoutParams.leftMargin, layoutParams.topMargin, layoutParams.leftMargin + view.getMeasuredWidth(), layoutParams.topMargin + view.getMeasuredHeight());
                } else {
                    int n8;
                    float f2;
                    int n9 = view.getMeasuredWidth();
                    int n10 = view.getMeasuredHeight();
                    if (this.checkDrawerViewAbsoluteGravity(view, 3)) {
                        n2 = - n9;
                        n8 = (int)((float)n9 * layoutParams.onScreen) + n2;
                        f2 = (float)(n9 + n8) / (float)n9;
                    } else {
                        n8 = n6 - (int)((float)n9 * layoutParams.onScreen);
                        f2 = (float)(n6 - n8) / (float)n9;
                    }
                    boolean bl3 = f2 != layoutParams.onScreen;
                    switch (layoutParams.gravity & 112) {
                        default: {
                            view.layout(n8, layoutParams.topMargin, n9 + n8, n10 + layoutParams.topMargin);
                            break;
                        }
                        case 80: {
                            n2 = n5 - n3;
                            view.layout(n8, n2 - layoutParams.bottomMargin - view.getMeasuredHeight(), n9 + n8, n2 - layoutParams.bottomMargin);
                            break;
                        }
                        case 16: {
                            int n11 = n5 - n3;
                            int n12 = (n11 - n10) / 2;
                            if (n12 < layoutParams.topMargin) {
                                n2 = layoutParams.topMargin;
                            } else {
                                n2 = n12;
                                if (n12 + n10 > n11 - layoutParams.bottomMargin) {
                                    n2 = n11 - layoutParams.bottomMargin - n10;
                                }
                            }
                            view.layout(n8, n2, n9 + n8, n10 + n2);
                        }
                    }
                    if (bl3) {
                        this.setDrawerViewOffset(view, f2);
                    }
                    n2 = layoutParams.onScreen > 0.0f ? 0 : 4;
                    if (view.getVisibility() != n2) {
                        view.setVisibility(n2);
                    }
                }
            }
            ++n4;
        } while (true);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    protected void onMeasure(int var1_1, int var2_2) {
        var5_3 = 300;
        var8_4 = View.MeasureSpec.getMode((int)var1_1);
        var7_5 = View.MeasureSpec.getMode((int)var2_2);
        var4_6 = View.MeasureSpec.getSize((int)var1_1);
        var6_7 = View.MeasureSpec.getSize((int)var2_2);
        if (var8_4 != 1073741824) ** GOTO lbl9
        var3_8 = var4_6;
        if (var7_5 == 1073741824) ** GOTO lbl-1000
lbl9: // 2 sources:
        if (this.isInEditMode() == false) throw new IllegalArgumentException("DrawerLayout must be measured with MeasureSpec.EXACTLY.");
        if (var8_4 == Integer.MIN_VALUE) {
            var3_8 = var4_6;
        } else {
            var3_8 = var4_6;
            if (var8_4 == 0) {
                var3_8 = 300;
            }
        }
        if (var7_5 == Integer.MIN_VALUE) {
            var4_6 = var6_7;
            var5_3 = var3_8;
        } else {
            var4_6 = var5_3;
            var5_3 = var3_8;
            if (var7_5 != 0) lbl-1000: // 2 sources:
            {
                var4_6 = var6_7;
                var5_3 = var3_8;
            }
        }
        this.setMeasuredDimension(var5_3, var4_6);
        var3_8 = this.mLastInsets != null && ViewCompat.getFitsSystemWindows((View)this) != false ? 1 : 0;
        var7_5 = ViewCompat.getLayoutDirection((View)this);
        var8_4 = this.getChildCount();
        var6_7 = 0;
        while (var6_7 < var8_4) {
            var10_10 = this.getChildAt(var6_7);
            if (var10_10.getVisibility() != 8) {
                var11_11 = (LayoutParams)var10_10.getLayoutParams();
                if (var3_8 != 0) {
                    var9_9 = GravityCompat.getAbsoluteGravity(var11_11.gravity, var7_5);
                    if (ViewCompat.getFitsSystemWindows(var10_10)) {
                        DrawerLayout.IMPL.dispatchChildInsets(var10_10, this.mLastInsets, var9_9);
                    } else {
                        DrawerLayout.IMPL.applyMarginInsets(var11_11, this.mLastInsets, var9_9);
                    }
                }
                if (this.isContentView(var10_10)) {
                    var10_10.measure(View.MeasureSpec.makeMeasureSpec((int)(var5_3 - var11_11.leftMargin - var11_11.rightMargin), (int)1073741824), View.MeasureSpec.makeMeasureSpec((int)(var4_6 - var11_11.topMargin - var11_11.bottomMargin), (int)1073741824));
                } else {
                    if (this.isDrawerView(var10_10) == false) throw new IllegalStateException("Child " + (Object)var10_10 + " at index " + var6_7 + " does not have a valid layout_gravity - must be Gravity.LEFT, " + "Gravity.RIGHT or Gravity.NO_GRAVITY");
                    var9_9 = this.getDrawerViewAbsoluteGravity(var10_10) & 7;
                    if ((0 & var9_9) != 0) {
                        throw new IllegalStateException("Child drawer has absolute gravity " + DrawerLayout.gravityToString(var9_9) + " but this " + "DrawerLayout" + " already has a " + "drawer view along that edge");
                    }
                    var10_10.measure(DrawerLayout.getChildMeasureSpec((int)var1_1, (int)(this.mMinDrawerMargin + var11_11.leftMargin + var11_11.rightMargin), (int)var11_11.width), DrawerLayout.getChildMeasureSpec((int)var2_2, (int)(var11_11.topMargin + var11_11.bottomMargin), (int)var11_11.height));
                }
            }
            ++var6_7;
        }
    }

    protected void onRestoreInstanceState(Parcelable object) {
        View view;
        object = (SavedState)((Object)object);
        super.onRestoreInstanceState(object.getSuperState());
        if (object.openDrawerGravity != 0 && (view = this.findDrawerWithGravity(object.openDrawerGravity)) != null) {
            this.openDrawer(view);
        }
        this.setDrawerLockMode(object.lockModeLeft, 3);
        this.setDrawerLockMode(object.lockModeRight, 5);
    }

    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        View view = this.findOpenDrawer();
        if (view != null) {
            savedState.openDrawerGravity = ((LayoutParams)view.getLayoutParams()).gravity;
        }
        savedState.lockModeLeft = this.mLockModeLeft;
        savedState.lockModeRight = this.mLockModeRight;
        return savedState;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public boolean onTouchEvent(MotionEvent var1_1) {
        this.mLeftDragger.processTouchEvent(var1_1);
        this.mRightDragger.processTouchEvent(var1_1);
        switch (var1_1.getAction() & 255) {
            default: {
                return true;
            }
            case 0: {
                var2_2 = var1_1.getX();
                var3_4 = var1_1.getY();
                this.mInitialMotionX = var2_2;
                this.mInitialMotionY = var3_4;
                this.mDisallowInterceptRequested = false;
                this.mChildrenCanceledTouch = false;
                return true;
            }
            case 1: {
                var3_5 = var1_1.getX();
                var2_3 = var1_1.getY();
                var1_1 = this.mLeftDragger.findTopChildUnder((int)var3_5, (int)var2_3);
                if (var1_1 == null || !this.isContentView((View)var1_1) || (var3_5 -= this.mInitialMotionX) * var3_5 + (var2_3 -= this.mInitialMotionY) * var2_3 >= (float)((var4_6 = this.mLeftDragger.getTouchSlop()) * var4_6) || (var1_1 = this.findOpenDrawer()) == null) break;
                var5_7 = this.getDrawerLockMode((View)var1_1) == 2;
                ** GOTO lbl27
            }
            case 3: {
                this.closeDrawers(true);
                this.mDisallowInterceptRequested = false;
                this.mChildrenCanceledTouch = false;
                return true;
            }
        }
        var5_7 = true;
lbl27: // 2 sources:
        this.closeDrawers(var5_7);
        this.mDisallowInterceptRequested = false;
        return true;
    }

    public void openDrawer(int n2) {
        View view = this.findDrawerWithGravity(n2);
        if (view == null) {
            throw new IllegalArgumentException("No drawer view found with gravity " + DrawerLayout.gravityToString(n2));
        }
        this.openDrawer(view);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void openDrawer(View view) {
        if (!this.isDrawerView(view)) {
            throw new IllegalArgumentException("View " + (Object)view + " is not a sliding drawer");
        }
        if (this.mFirstLayout) {
            LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
            layoutParams.onScreen = 1.0f;
            layoutParams.knownOpen = true;
            this.updateChildrenImportantForAccessibility(view, true);
        } else if (this.checkDrawerViewAbsoluteGravity(view, 3)) {
            this.mLeftDragger.smoothSlideViewTo(view, 0, view.getTop());
        } else {
            this.mRightDragger.smoothSlideViewTo(view, this.getWidth() - view.getWidth(), view.getTop());
        }
        this.invalidate();
    }

    public void requestDisallowInterceptTouchEvent(boolean bl2) {
        super.requestDisallowInterceptTouchEvent(bl2);
        this.mDisallowInterceptRequested = bl2;
        if (bl2) {
            this.closeDrawers(true);
        }
    }

    public void requestLayout() {
        if (!this.mInLayout) {
            super.requestLayout();
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void setChildInsets(Object object, boolean bl2) {
        this.mLastInsets = object;
        this.mDrawStatusBarBackground = bl2;
        bl2 = !bl2 && this.getBackground() == null;
        this.setWillNotDraw(bl2);
        this.requestLayout();
    }

    public void setDrawerListener(DrawerListener drawerListener) {
        this.mListener = drawerListener;
    }

    public void setDrawerLockMode(int n2) {
        this.setDrawerLockMode(n2, 3);
        this.setDrawerLockMode(n2, 5);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setDrawerLockMode(int n2, int n3) {
        ViewDragHelper viewDragHelper;
        if ((n3 = GravityCompat.getAbsoluteGravity(n3, ViewCompat.getLayoutDirection((View)this))) == 3) {
            this.mLockModeLeft = n2;
        } else if (n3 == 5) {
            this.mLockModeRight = n2;
        }
        if (n2 != 0) {
            viewDragHelper = n3 == 3 ? this.mLeftDragger : this.mRightDragger;
            viewDragHelper.cancel();
        }
        switch (n2) {
            case 2: {
                viewDragHelper = this.findDrawerWithGravity(n3);
                if (viewDragHelper == null) return;
                {
                    this.openDrawer((View)viewDragHelper);
                    return;
                }
            }
            default: {
                return;
            }
            case 1: 
        }
        viewDragHelper = this.findDrawerWithGravity(n3);
        if (viewDragHelper == null) return;
        {
            this.closeDrawer((View)viewDragHelper);
            return;
        }
    }

    public void setDrawerLockMode(int n2, View view) {
        if (!this.isDrawerView(view)) {
            throw new IllegalArgumentException("View " + (Object)view + " is not a " + "drawer with appropriate layout_gravity");
        }
        this.setDrawerLockMode(n2, ((LayoutParams)view.getLayoutParams()).gravity);
    }

    public void setDrawerShadow(int n2, int n3) {
        this.setDrawerShadow(this.getResources().getDrawable(n2), n3);
    }

    public void setDrawerShadow(Drawable drawable2, int n2) {
        if (((n2 = GravityCompat.getAbsoluteGravity(n2, ViewCompat.getLayoutDirection((View)this))) & 3) == 3) {
            this.mShadowLeft = drawable2;
            this.invalidate();
        }
        if ((n2 & 5) == 5) {
            this.mShadowRight = drawable2;
            this.invalidate();
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setDrawerTitle(int n2, CharSequence charSequence) {
        if ((n2 = GravityCompat.getAbsoluteGravity(n2, ViewCompat.getLayoutDirection((View)this))) == 3) {
            this.mTitleLeft = charSequence;
            return;
        } else {
            if (n2 != 5) return;
            {
                this.mTitleRight = charSequence;
                return;
            }
        }
    }

    void setDrawerViewOffset(View view, float f2) {
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        if (f2 == layoutParams.onScreen) {
            return;
        }
        layoutParams.onScreen = f2;
        this.dispatchOnDrawerSlide(view, f2);
    }

    public void setScrimColor(int n2) {
        this.mScrimColor = n2;
        this.invalidate();
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setStatusBarBackground(int n2) {
        Drawable drawable2 = n2 != 0 ? ContextCompat.getDrawable(this.getContext(), n2) : null;
        this.mStatusBarBackground = drawable2;
        this.invalidate();
    }

    public void setStatusBarBackground(Drawable drawable2) {
        this.mStatusBarBackground = drawable2;
        this.invalidate();
    }

    public void setStatusBarBackgroundColor(int n2) {
        this.mStatusBarBackground = new ColorDrawable(n2);
        this.invalidate();
    }

    /*
     * Enabled aggressive block sorting
     */
    void updateDrawerState(int n2, int n3, View view) {
        int n4 = 1;
        int n5 = this.mLeftDragger.getViewDragState();
        int n6 = this.mRightDragger.getViewDragState();
        n2 = n4;
        if (n5 != 1) {
            n2 = n6 == 1 ? n4 : (n5 == 2 || n6 == 2 ? 2 : 0);
        }
        if (view != null && n3 == 0) {
            LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
            if (layoutParams.onScreen == 0.0f) {
                this.dispatchOnDrawerClosed(view);
            } else if (layoutParams.onScreen == 1.0f) {
                this.dispatchOnDrawerOpened(view);
            }
        }
        if (n2 != this.mDrawerState) {
            this.mDrawerState = n2;
            if (this.mListener != null) {
                this.mListener.onDrawerStateChanged(n2);
            }
        }
    }

    class AccessibilityDelegate
    extends AccessibilityDelegateCompat {
        private final Rect mTmpRect;

        AccessibilityDelegate() {
            this.mTmpRect = new Rect();
        }

        private void addChildrenForAccessibility(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, ViewGroup viewGroup) {
            int n2 = viewGroup.getChildCount();
            for (int i2 = 0; i2 < n2; ++i2) {
                View view = viewGroup.getChildAt(i2);
                if (!DrawerLayout.includeChildForAccessibility(view)) continue;
                accessibilityNodeInfoCompat.addChild(view);
            }
        }

        private void copyNodeInfoNoChildren(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2) {
            Rect rect = this.mTmpRect;
            accessibilityNodeInfoCompat2.getBoundsInParent(rect);
            accessibilityNodeInfoCompat.setBoundsInParent(rect);
            accessibilityNodeInfoCompat2.getBoundsInScreen(rect);
            accessibilityNodeInfoCompat.setBoundsInScreen(rect);
            accessibilityNodeInfoCompat.setVisibleToUser(accessibilityNodeInfoCompat2.isVisibleToUser());
            accessibilityNodeInfoCompat.setPackageName(accessibilityNodeInfoCompat2.getPackageName());
            accessibilityNodeInfoCompat.setClassName(accessibilityNodeInfoCompat2.getClassName());
            accessibilityNodeInfoCompat.setContentDescription(accessibilityNodeInfoCompat2.getContentDescription());
            accessibilityNodeInfoCompat.setEnabled(accessibilityNodeInfoCompat2.isEnabled());
            accessibilityNodeInfoCompat.setClickable(accessibilityNodeInfoCompat2.isClickable());
            accessibilityNodeInfoCompat.setFocusable(accessibilityNodeInfoCompat2.isFocusable());
            accessibilityNodeInfoCompat.setFocused(accessibilityNodeInfoCompat2.isFocused());
            accessibilityNodeInfoCompat.setAccessibilityFocused(accessibilityNodeInfoCompat2.isAccessibilityFocused());
            accessibilityNodeInfoCompat.setSelected(accessibilityNodeInfoCompat2.isSelected());
            accessibilityNodeInfoCompat.setLongClickable(accessibilityNodeInfoCompat2.isLongClickable());
            accessibilityNodeInfoCompat.addAction(accessibilityNodeInfoCompat2.getActions());
        }

        @Override
        public boolean dispatchPopulateAccessibilityEvent(View object, AccessibilityEvent object2) {
            if (object2.getEventType() == 32) {
                object = object2.getText();
                object2 = DrawerLayout.this.findVisibleDrawer();
                if (object2 != null) {
                    int n2 = DrawerLayout.this.getDrawerViewAbsoluteGravity((View)object2);
                    object2 = DrawerLayout.this.getDrawerTitle(n2);
                    if (object2 != null) {
                        object.add(object2);
                    }
                }
                return true;
            }
            return super.dispatchPopulateAccessibilityEvent((View)object, (AccessibilityEvent)object2);
        }

        @Override
        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(view, accessibilityEvent);
            accessibilityEvent.setClassName((CharSequence)DrawerLayout.class.getName());
        }

        /*
         * Enabled aggressive block sorting
         */
        @Override
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            if (CAN_HIDE_DESCENDANTS) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            } else {
                AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2 = AccessibilityNodeInfoCompat.obtain(accessibilityNodeInfoCompat);
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat2);
                accessibilityNodeInfoCompat.setSource(view);
                ViewParent viewParent = ViewCompat.getParentForAccessibility(view);
                if (viewParent instanceof View) {
                    accessibilityNodeInfoCompat.setParent((View)viewParent);
                }
                this.copyNodeInfoNoChildren(accessibilityNodeInfoCompat, accessibilityNodeInfoCompat2);
                accessibilityNodeInfoCompat2.recycle();
                this.addChildrenForAccessibility(accessibilityNodeInfoCompat, (ViewGroup)view);
            }
            accessibilityNodeInfoCompat.setClassName(DrawerLayout.class.getName());
            accessibilityNodeInfoCompat.setFocusable(false);
            accessibilityNodeInfoCompat.setFocused(false);
        }

        @Override
        public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            if (CAN_HIDE_DESCENDANTS || DrawerLayout.includeChildForAccessibility(view)) {
                return super.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
            }
            return false;
        }
    }

    final class ChildAccessibilityDelegate
    extends AccessibilityDelegateCompat {
        ChildAccessibilityDelegate() {
        }

        @Override
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            if (!DrawerLayout.includeChildForAccessibility(view)) {
                accessibilityNodeInfoCompat.setParent(null);
            }
        }
    }

    static interface DrawerLayoutCompatImpl {
        public void applyMarginInsets(ViewGroup.MarginLayoutParams var1, Object var2, int var3);

        public void configureApplyInsets(View var1);

        public void dispatchChildInsets(View var1, Object var2, int var3);

        public Drawable getDefaultStatusBarBackground(Context var1);

        public int getTopInset(Object var1);
    }

    static class DrawerLayoutCompatImplApi21
    implements DrawerLayoutCompatImpl {
        DrawerLayoutCompatImplApi21() {
        }

        @Override
        public void applyMarginInsets(ViewGroup.MarginLayoutParams marginLayoutParams, Object object, int n2) {
            DrawerLayoutCompatApi21.applyMarginInsets(marginLayoutParams, object, n2);
        }

        @Override
        public void configureApplyInsets(View view) {
            DrawerLayoutCompatApi21.configureApplyInsets(view);
        }

        @Override
        public void dispatchChildInsets(View view, Object object, int n2) {
            DrawerLayoutCompatApi21.dispatchChildInsets(view, object, n2);
        }

        @Override
        public Drawable getDefaultStatusBarBackground(Context context) {
            return DrawerLayoutCompatApi21.getDefaultStatusBarBackground(context);
        }

        @Override
        public int getTopInset(Object object) {
            return DrawerLayoutCompatApi21.getTopInset(object);
        }
    }

    static class DrawerLayoutCompatImplBase
    implements DrawerLayoutCompatImpl {
        DrawerLayoutCompatImplBase() {
        }

        @Override
        public void applyMarginInsets(ViewGroup.MarginLayoutParams marginLayoutParams, Object object, int n2) {
        }

        @Override
        public void configureApplyInsets(View view) {
        }

        @Override
        public void dispatchChildInsets(View view, Object object, int n2) {
        }

        @Override
        public Drawable getDefaultStatusBarBackground(Context context) {
            return null;
        }

        @Override
        public int getTopInset(Object object) {
            return 0;
        }
    }

    public static interface DrawerListener {
        public void onDrawerClosed(View var1);

        public void onDrawerOpened(View var1);

        public void onDrawerSlide(View var1, float var2);

        public void onDrawerStateChanged(int var1);
    }

    @Retention(value=RetentionPolicy.SOURCE)
    @IntDef(value={3, 5, 8388611, 8388613})
    static @interface EdgeGravity {
    }

    public static class LayoutParams
    extends ViewGroup.MarginLayoutParams {
        public int gravity = 0;
        boolean isPeeking;
        boolean knownOpen;
        float onScreen;

        public LayoutParams(int n2, int n3) {
            super(n2, n3);
        }

        public LayoutParams(int n2, int n3, int n4) {
            this(n2, n3);
            this.gravity = n4;
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            context = context.obtainStyledAttributes(attributeSet, LAYOUT_ATTRS);
            this.gravity = context.getInt(0, 0);
            context.recycle();
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((ViewGroup.MarginLayoutParams)layoutParams);
            this.gravity = layoutParams.gravity;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }
    }

    @Retention(value=RetentionPolicy.SOURCE)
    @IntDef(value={0, 1, 2})
    static @interface LockMode {
    }

    public static class SavedState
    extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new DrawerLayout$SavedState$1();
        int lockModeLeft = 0;
        int lockModeRight = 0;
        int openDrawerGravity = 0;

        public SavedState(Parcel parcel) {
            super(parcel);
            this.openDrawerGravity = parcel.readInt();
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(Parcel parcel, int n2) {
            super.writeToParcel(parcel, n2);
            parcel.writeInt(this.openDrawerGravity);
        }
    }

    public static abstract class SimpleDrawerListener
    implements DrawerListener {
        @Override
        public void onDrawerClosed(View view) {
        }

        @Override
        public void onDrawerOpened(View view) {
        }

        @Override
        public void onDrawerSlide(View view, float f2) {
        }

        @Override
        public void onDrawerStateChanged(int n2) {
        }
    }

    @Retention(value=RetentionPolicy.SOURCE)
    @IntDef(value={0, 1, 2})
    static @interface State {
    }

    class ViewDragCallback
    extends ViewDragHelper.Callback {
        private final int mAbsGravity;
        private ViewDragHelper mDragger;
        private final Runnable mPeekRunnable;

        public ViewDragCallback(int n2) {
            this.mPeekRunnable = new DrawerLayout$ViewDragCallback$1(this);
            this.mAbsGravity = n2;
        }

        static /* synthetic */ void access$000(ViewDragCallback viewDragCallback) {
            viewDragCallback.peekDrawer();
        }

        private void closeOtherDrawer() {
            View view;
            int n2 = 3;
            if (this.mAbsGravity == 3) {
                n2 = 5;
            }
            if ((view = DrawerLayout.this.findDrawerWithGravity(n2)) != null) {
                DrawerLayout.this.closeDrawer(view);
            }
        }

        /*
         * Enabled aggressive block sorting
         */
        private void peekDrawer() {
            View view;
            int n2 = 0;
            int n3 = this.mDragger.getEdgeSize();
            boolean bl2 = this.mAbsGravity == 3;
            if (bl2) {
                view = DrawerLayout.this.findDrawerWithGravity(3);
                if (view != null) {
                    n2 = - view.getWidth();
                }
                n2 += n3;
            } else {
                view = DrawerLayout.this.findDrawerWithGravity(5);
                n2 = DrawerLayout.this.getWidth();
                n2 -= n3;
            }
            if (view != null && (bl2 && view.getLeft() < n2 || !bl2 && view.getLeft() > n2) && DrawerLayout.this.getDrawerLockMode(view) == 0) {
                LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
                this.mDragger.smoothSlideViewTo(view, n2, view.getTop());
                layoutParams.isPeeking = true;
                DrawerLayout.this.invalidate();
                this.closeOtherDrawer();
                DrawerLayout.this.cancelChildViewTouch();
            }
        }

        @Override
        public int clampViewPositionHorizontal(View view, int n2, int n3) {
            if (DrawerLayout.this.checkDrawerViewAbsoluteGravity(view, 3)) {
                return Math.max((int)(- view.getWidth()), (int)Math.min((int)n2, (int)0));
            }
            n3 = DrawerLayout.this.getWidth();
            return Math.max((int)(n3 - view.getWidth()), (int)Math.min((int)n2, (int)n3));
        }

        @Override
        public int clampViewPositionVertical(View view, int n2, int n3) {
            return view.getTop();
        }

        @Override
        public int getViewHorizontalDragRange(View view) {
            if (DrawerLayout.this.isDrawerView(view)) {
                return view.getWidth();
            }
            return 0;
        }

        /*
         * Enabled aggressive block sorting
         */
        @Override
        public void onEdgeDragStarted(int n2, int n3) {
            View view = (n2 & 1) == 1 ? DrawerLayout.this.findDrawerWithGravity(3) : DrawerLayout.this.findDrawerWithGravity(5);
            if (view != null && DrawerLayout.this.getDrawerLockMode(view) == 0) {
                this.mDragger.captureChildView(view, n3);
            }
        }

        @Override
        public boolean onEdgeLock(int n2) {
            return false;
        }

        @Override
        public void onEdgeTouched(int n2, int n3) {
            DrawerLayout.this.postDelayed(this.mPeekRunnable, 160);
        }

        @Override
        public void onViewCaptured(View view, int n2) {
            ((LayoutParams)view.getLayoutParams()).isPeeking = false;
            this.closeOtherDrawer();
        }

        @Override
        public void onViewDragStateChanged(int n2) {
            DrawerLayout.this.updateDrawerState(this.mAbsGravity, n2, this.mDragger.getCapturedView());
        }

        /*
         * Enabled aggressive block sorting
         */
        @Override
        public void onViewPositionChanged(View view, int n2, int n3, int n4, int n5) {
            n3 = view.getWidth();
            float f2 = DrawerLayout.this.checkDrawerViewAbsoluteGravity(view, 3) ? (float)(n3 + n2) / (float)n3 : (float)(DrawerLayout.this.getWidth() - n2) / (float)n3;
            DrawerLayout.this.setDrawerViewOffset(view, f2);
            n2 = f2 == 0.0f ? 4 : 0;
            view.setVisibility(n2);
            DrawerLayout.this.invalidate();
        }

        /*
         * Unable to fully structure code
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        @Override
        public void onViewReleased(View var1_1, float var2_2, float var3_3) {
            var3_3 = DrawerLayout.this.getDrawerViewOffset(var1_1);
            var6_4 = var1_1.getWidth();
            if (!DrawerLayout.this.checkDrawerViewAbsoluteGravity(var1_1, 3)) ** GOTO lbl12
            if (var2_2 > 0.0f || var2_2 == 0.0f && var3_3 > 0.5f) {
                var4_5 = 0;
lbl6: // 5 sources:
                do {
                    this.mDragger.settleCapturedViewAt(var4_5, var1_1.getTop());
                    DrawerLayout.this.invalidate();
                    return;
                    break;
                } while (true);
            }
            var4_5 = - var6_4;
            ** GOTO lbl6
lbl12: // 1 sources:
            var5_6 = DrawerLayout.this.getWidth();
            if (var2_2 < 0.0f) ** GOTO lbl18
            var4_5 = var5_6;
            if (var2_2 != 0.0f) ** GOTO lbl6
            var4_5 = var5_6;
            if (var3_3 <= 0.5f) ** GOTO lbl6
lbl18: // 2 sources:
            var4_5 = var5_6 - var6_4;
            ** while (true)
        }

        public void removeCallbacks() {
            DrawerLayout.this.removeCallbacks(this.mPeekRunnable);
        }

        public void setDragger(ViewDragHelper viewDragHelper) {
            this.mDragger = viewDragHelper;
        }

        @Override
        public boolean tryCaptureView(View view, int n2) {
            if (DrawerLayout.this.isDrawerView(view) && DrawerLayout.this.checkDrawerViewAbsoluteGravity(view, this.mAbsGravity) && DrawerLayout.this.getDrawerLockMode(view) == 0) {
                return true;
            }
            return false;
        }
    }

}

