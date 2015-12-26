/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.content.res.Resources$NotFoundException
 *  android.content.res.TypedArray
 *  android.database.DataSetObserver
 *  android.graphics.Canvas
 *  android.graphics.Rect
 *  android.graphics.drawable.Drawable
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.Bundle
 *  android.os.IBinder
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  android.os.SystemClock
 *  android.util.AttributeSet
 *  android.util.DisplayMetrics
 *  android.util.Log
 *  android.view.FocusFinder
 *  android.view.KeyEvent
 *  android.view.MotionEvent
 *  android.view.SoundEffectConstants
 *  android.view.VelocityTracker
 *  android.view.View
 *  android.view.View$BaseSavedState
 *  android.view.View$MeasureSpec
 *  android.view.ViewConfiguration
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewParent
 *  android.view.accessibility.AccessibilityEvent
 *  android.view.animation.Interpolator
 *  android.widget.Scroller
 *  java.lang.Boolean
 *  java.lang.Class
 *  java.lang.ClassLoader
 *  java.lang.Enum
 *  java.lang.Integer
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 *  java.lang.reflect.Method
 *  java.util.ArrayList
 *  java.util.Collections
 *  java.util.Comparator
 *  java.util.LinkedList
 */
package android.support.v4.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.v4.os.ParcelableCompat;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.KeyEventCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.VelocityTrackerCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewConfigurationCompat;
import android.support.v4.view.ViewPager$1;
import android.support.v4.view.ViewPager$2;
import android.support.v4.view.ViewPager$3;
import android.support.v4.view.ViewPager$4;
import android.support.v4.view.ViewPager$SavedState$1;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityRecordCompat;
import android.support.v4.view.animation.PathInterpolatorCompat;
import android.support.v4.widget.EdgeEffectCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Interpolator;
import android.widget.Scroller;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class ViewPager
extends ViewGroup {
    private static final int CLOSE_ENOUGH = 2;
    private static final Comparator<ItemInfo> COMPARATOR;
    private static final int COVER_ALPHA_BASE = 102;
    private static final boolean DEBUG = false;
    private static final int DEFAULT_GUTTER_SIZE = 16;
    private static final int DEFAULT_OFFSCREEN_PAGES = 1;
    private static final int DRAW_ORDER_DEFAULT = 0;
    private static final int DRAW_ORDER_FORWARD = 1;
    private static final int DRAW_ORDER_REVERSE = 2;
    private static final int INVALID_POINTER = -1;
    private static final int[] LAYOUT_ATTRS;
    private static final int MAX_SETTLE_DURATION = 600;
    private static final int MAX_SWITCH_DURATION = 5000;
    private static final int MIN_DISTANCE_FOR_FLING = 25;
    private static final int MIN_FLING_VELOCITY = 400;
    private static final float[] OVERLAY_MODE_INTERPOLATIONS;
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SETTLING = 2;
    private static final String TAG = "ViewPager";
    private static final boolean USE_CACHE = false;
    private static final Interpolator overlayModeInterpolator;
    private static final Interpolator sAutoScrollInterpolator;
    private static final Interpolator sInterpolator;
    private static final ViewPositionComparator sPositionComparator;
    private static final ViewReversePositionComparator sReversePositionComparator;
    private int mActivePointerId = -1;
    private PagerAdapter mAdapter;
    private OnAdapterChangeListener mAdapterChangeListener;
    private int mBottomPageBounds;
    private boolean mCalledSuper;
    private int mChildHeightMeasureSpec;
    private int mChildWidthMeasureSpec;
    private int mCloseEnough;
    private Context mContext;
    private int mCoverAlpha;
    private Drawable mCoverDrawable;
    private int mCurIndex;
    private Interpolator mCurInterpolator;
    private int mCurItem;
    private int mDecorChildCount;
    private int mDefaultGutterSize;
    private int mDrawingOrder;
    private LinkedList<View> mDrawingOrderedChildren;
    private final Runnable mEndScrollRunnable;
    private int mExpectedAdapterCount;
    private long mFakeDragBeginTime;
    private boolean mFakeDragging;
    private boolean mFirstLayout = true;
    private float mFirstOffset = -3.4028235E38f;
    private int mFlingDistance;
    private volatile FlipMode mFlipMode = FlipMode.FLIP_MODE_DEFAULT;
    private int mGutterSize;
    private boolean mIgnoreGutter;
    private boolean mInLayout;
    private float mInitialMotionX;
    private float mInitialMotionY;
    private OnPageChangeListener mInternalPageChangeListener;
    private boolean mIsBeingDragged;
    private boolean mIsUnableToDrag;
    private final ArrayList<ItemInfo> mItems = new ArrayList();
    private Interpolator mLastInterpolator;
    private float mLastMotionX;
    private float mLastMotionY;
    private float mLastOffset = Float.MAX_VALUE;
    private EdgeEffectCompat mLeftEdge;
    private Drawable mMarginDrawable;
    private int mMaximumVelocity;
    private int mMinimumVelocity;
    private boolean mNeedCalculatePageOffsets = false;
    private volatile boolean mNeedDrawShadow;
    private volatile boolean mNeedInitShadow;
    private PagerObserver mObserver;
    private int mOffscreenPageLimit = 1;
    private OnPageChangeListener mOnPageChangeListener;
    private List<OnPageChangeListener> mOnPageChangeListeners;
    private int mPageMargin;
    private PageTransformer mPageTransformer;
    private boolean mPopulatePending;
    private Parcelable mRestoredAdapterState = null;
    private ClassLoader mRestoredClassLoader = null;
    private int mRestoredCurItem = -1;
    private EdgeEffectCompat mRightEdge;
    private int mScrollState;
    private Scroller mScroller;
    private boolean mScrollingCacheEnabled;
    private Method mSetChildrenDrawingOrderEnabled;
    private Drawable mShadowDrawable;
    private int mShdH;
    private int mShdL;
    private int mShdT;
    private Rect mSpecRect;
    private int mSpecTab;
    private final ItemInfo mTempItem = new ItemInfo();
    private final Rect mTempRect = new Rect();
    private int mTopIndex;
    private int mTopPageBounds;
    private int mTouchSlop;
    private int mTouchSlopAdjust = -1;
    private VelocityTracker mVelocityTracker;

    static {
        LAYOUT_ATTRS = new int[]{16842931};
        COMPARATOR = new ViewPager$1();
        sInterpolator = new ViewPager$2();
        sPositionComparator = new ViewPositionComparator();
        sReversePositionComparator = new ViewReversePositionComparator();
        OVERLAY_MODE_INTERPOLATIONS = new float[]{0.0f, 0.0f, 0.003365234f, 0.01357806f, 0.030720964f, 0.05475371f, 0.08548926f, 0.12255032f, 0.16538717f, 0.21324258f, 0.2652047f, 0.32024413f, 0.37725833f, 0.4351431f, 0.49284747f, 0.5494277f, 0.6040792f, 0.6561299f, 0.7050707f, 0.7505254f, 0.7922336f, 0.8300537f, 0.86390066f, 0.8937803f, 0.91972214f, 0.94181687f, 0.9601534f, 0.974861f, 0.98606336f, 0.99389625f, 0.99851006f, 1.0f};
        overlayModeInterpolator = new ViewPager$4();
        sAutoScrollInterpolator = PathInterpolatorCompat.create(0.33f, 0.0f, 0.2f, 1.0f);
    }

    public ViewPager(Context context) {
        super(context);
        this.mEndScrollRunnable = new ViewPager$3(this);
        this.mScrollState = 0;
        this.mContext = context;
        this.initViewPager();
    }

    public ViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mEndScrollRunnable = new ViewPager$3(this);
        this.mScrollState = 0;
        this.mContext = context;
        this.initViewPager();
    }

    static /* synthetic */ void access$000(ViewPager viewPager, int n2) {
        viewPager.setScrollState(n2);
    }

    static /* synthetic */ float[] access$500() {
        return OVERLAY_MODE_INTERPOLATIONS;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private void calculatePageOffsets(ItemInfo var1_1, int var2_2, ItemInfo var3_3) {
        var10_4 = this.mAdapter.getCount();
        var7_5 = this.getClientWidth();
        var5_6 = var7_5 > 0 ? (float)this.mPageMargin / (float)var7_5 : 0.0f;
        if (var3_3 == null) ** GOTO lbl54
        var7_5 = var3_3.position;
        if (var7_5 >= var1_1.position) ** GOTO lbl11
        var4_7 = var3_3.offset + var3_3.widthFactor + var5_6;
        ++var7_5;
        var8_8 = 0;
        ** GOTO lbl16
lbl11: // 1 sources:
        if (var7_5 <= var1_1.position) ** GOTO lbl54
        var8_8 = this.mItems.size() - 1;
        var4_7 = var3_3.offset;
        --var7_5;
        ** GOTO lbl36
lbl16: // 2 sources:
        while (var7_5 <= var1_1.position && var8_8 < this.mItems.size()) {
            var3_3 = (ItemInfo)this.mItems.get(var8_8);
            do {
                var9_10 = var7_5;
                var6_9 = var4_7;
                if (var7_5 <= var3_3.position) break;
                var9_10 = var7_5;
                var6_9 = var4_7;
                if (var8_8 >= this.mItems.size() - 1) break;
                var3_3 = (ItemInfo)this.mItems.get(++var8_8);
            } while (true);
            while (var9_10 < var3_3.position) {
                var6_9 += this.mAdapter.getPageWidth(var9_10) + var5_6;
                ++var9_10;
            }
            var3_3.offset = var6_9;
            var4_7 = var6_9 + (var3_3.widthFactor + var5_6);
            var7_5 = var9_10 + 1;
        }
        ** GOTO lbl54
lbl36: // 2 sources:
        while (var7_5 >= var1_1.position && var8_8 >= 0) {
            var3_3 = (ItemInfo)this.mItems.get(var8_8);
            do {
                var9_10 = var7_5;
                var6_9 = var4_7;
                if (var7_5 >= var3_3.position) break;
                var9_10 = var7_5;
                var6_9 = var4_7;
                if (var8_8 <= 0) break;
                var3_3 = (ItemInfo)this.mItems.get(--var8_8);
            } while (true);
            while (var9_10 > var3_3.position) {
                var6_9 -= this.mAdapter.getPageWidth(var9_10) + var5_6;
                --var9_10;
            }
            var3_3.offset = var4_7 = var6_9 - (var3_3.widthFactor + var5_6);
            var7_5 = var9_10 - 1;
        }
lbl54: // 4 sources:
        var9_10 = this.mItems.size();
        var6_9 = var1_1.offset;
        var7_5 = var1_1.position - 1;
        var4_7 = var1_1.position == 0 ? var1_1.offset : -3.4028235E38f;
        this.mFirstOffset = var4_7;
        var4_7 = var1_1.position == var10_4 - 1 ? var1_1.offset + var1_1.widthFactor - 1.0f : Float.MAX_VALUE;
        this.mLastOffset = var4_7;
        var4_7 = var6_9;
        for (var8_8 = var2_2 - 1; var8_8 >= 0; --var7_5, --var8_8) {
            var3_3 = (ItemInfo)this.mItems.get(var8_8);
            while (var7_5 > var3_3.position) {
                var4_7 -= this.mAdapter.getPageWidth(var7_5) + var5_6;
                --var7_5;
            }
            var3_3.offset = var4_7 -= var3_3.widthFactor + var5_6;
            if (var3_3.position != 0) continue;
            this.mFirstOffset = var4_7;
        }
        var4_7 = var1_1.offset + var1_1.widthFactor + var5_6;
        var8_8 = var1_1.position + 1;
        var7_5 = var2_2 + 1;
        var2_2 = var8_8;
        do {
            if (var7_5 >= var9_10) {
                this.mNeedCalculatePageOffsets = false;
                return;
            }
            var1_1 = (ItemInfo)this.mItems.get(var7_5);
            while (var2_2 < var1_1.position) {
                var4_7 = this.mAdapter.getPageWidth(var2_2) + var5_6 + var4_7;
                ++var2_2;
            }
            if (var1_1.position == var10_4 - 1) {
                this.mLastOffset = var1_1.widthFactor + var4_7 - 1.0f;
            }
            var1_1.offset = var4_7;
            var4_7 += var1_1.widthFactor + var5_6;
            ++var2_2;
            ++var7_5;
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void completeScroll(boolean bl2) {
        int n2;
        int n3;
        int n4 = this.mScrollState == 2 ? 1 : 0;
        if (n4 != 0) {
            this.setScrollingCacheEnabled(false);
            this.mScroller.abortAnimation();
            n2 = this.getScrollX();
            n3 = this.getScrollY();
            int n5 = this.mScroller.getCurrX();
            int n6 = this.mScroller.getCurrY();
            if (n2 != n5 || n3 != n6) {
                this.scrollTo(n5, n6);
                if (n5 != n2) {
                    this.pageScrolled(n5);
                }
            }
        }
        this.mPopulatePending = false;
        n3 = 0;
        n2 = n4;
        for (n4 = n3; n4 < this.mItems.size(); ++n4) {
            ItemInfo itemInfo = (ItemInfo)this.mItems.get(n4);
            if (!itemInfo.scrolling) continue;
            itemInfo.scrolling = false;
            n2 = 1;
        }
        if (n2 != 0) {
            if (!bl2) {
                this.mEndScrollRunnable.run();
                return;
            }
            ViewCompat.postOnAnimation((View)this, this.mEndScrollRunnable);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private int determineTargetPage(int n2, float f2, int n3, int n4) {
        if (Math.abs((int)n4) > this.mFlingDistance && Math.abs((int)n3) > this.mMinimumVelocity) {
            if (n3 <= 0) {
                ++n2;
            }
        } else {
            float f3 = n2 >= this.mCurItem ? 0.4f : 0.6f;
            n2 = (int)(f3 + ((float)n2 + f2));
        }
        n3 = n2;
        if (this.mItems.size() <= 0) return n3;
        ItemInfo itemInfo = (ItemInfo)this.mItems.get(0);
        ItemInfo itemInfo2 = (ItemInfo)this.mItems.get(this.mItems.size() - 1);
        return Math.max((int)itemInfo.position, (int)Math.min((int)n2, (int)itemInfo2.position));
    }

    private void dispatchOnPageScrolled(int n2, float f2, int n3) {
        if (this.mOnPageChangeListener != null) {
            this.mOnPageChangeListener.onPageScrolled(n2, f2, n3);
        }
        if (this.mOnPageChangeListeners != null) {
            int n4 = this.mOnPageChangeListeners.size();
            for (int i2 = 0; i2 < n4; ++i2) {
                OnPageChangeListener onPageChangeListener = this.mOnPageChangeListeners.get(i2);
                if (onPageChangeListener == null) continue;
                onPageChangeListener.onPageScrolled(n2, f2, n3);
            }
        }
        if (this.mInternalPageChangeListener != null) {
            this.mInternalPageChangeListener.onPageScrolled(n2, f2, n3);
        }
    }

    private void dispatchOnPageSelected(int n2) {
        if (this.mOnPageChangeListener != null) {
            this.mOnPageChangeListener.onPageSelected(n2);
        }
        if (this.mOnPageChangeListeners != null) {
            int n3 = this.mOnPageChangeListeners.size();
            for (int i2 = 0; i2 < n3; ++i2) {
                OnPageChangeListener onPageChangeListener = this.mOnPageChangeListeners.get(i2);
                if (onPageChangeListener == null) continue;
                onPageChangeListener.onPageSelected(n2);
            }
        }
        if (this.mInternalPageChangeListener != null) {
            this.mInternalPageChangeListener.onPageSelected(n2);
        }
    }

    private void dispatchOnScrollStateChanged(int n2) {
        if (this.mOnPageChangeListener != null) {
            this.mOnPageChangeListener.onPageScrollStateChanged(n2);
        }
        if (this.mOnPageChangeListeners != null) {
            int n3 = this.mOnPageChangeListeners.size();
            for (int i2 = 0; i2 < n3; ++i2) {
                OnPageChangeListener onPageChangeListener = this.mOnPageChangeListeners.get(i2);
                if (onPageChangeListener == null) continue;
                onPageChangeListener.onPageScrollStateChanged(n2);
            }
        }
        if (this.mInternalPageChangeListener != null) {
            this.mInternalPageChangeListener.onPageScrollStateChanged(n2);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void enableLayers(boolean bl2) {
        int n2 = this.getChildCount();
        int n3 = 0;
        while (n3 < n2) {
            int n4 = bl2 ? 2 : 0;
            ViewCompat.setLayerType(this.getChildAt(n3), n4, null);
            ++n3;
        }
    }

    private void endDrag() {
        this.mIsBeingDragged = false;
        this.mIsUnableToDrag = false;
        if (this.mVelocityTracker != null) {
            this.mVelocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    private Rect getChildRectInPagerCoordinates(Rect rect, View view) {
        if (rect == null) {
            rect = new Rect();
        }
        if (view == null) {
            rect.set(0, 0, 0, 0);
            return rect;
        }
        rect.left = view.getLeft();
        rect.right = view.getRight();
        rect.top = view.getTop();
        rect.bottom = view.getBottom();
        for (view = view.getParent(); view instanceof ViewGroup && view != this; view = view.getParent()) {
            view = (ViewGroup)view;
            rect.left += view.getLeft();
            rect.right += view.getRight();
            rect.top += view.getTop();
            rect.bottom += view.getBottom();
        }
        return rect;
    }

    private int getClientWidth() {
        return this.getMeasuredWidth() - this.getPaddingLeft() - this.getPaddingRight();
    }

    /*
     * Enabled aggressive block sorting
     */
    private ItemInfo infoForCurrentScrollPosition() {
        int n2 = this.getClientWidth();
        float f2 = n2 > 0 ? (float)this.getScrollX() / (float)n2 : 0.0f;
        float f3 = n2 > 0 ? (float)this.mPageMargin / (float)n2 : 0.0f;
        float f4 = 0.0f;
        float f5 = 0.0f;
        int n3 = -1;
        n2 = 0;
        boolean bl2 = true;
        ItemInfo itemInfo = null;
        do {
            ItemInfo itemInfo2 = itemInfo;
            if (n2 >= this.mItems.size()) return itemInfo2;
            ItemInfo itemInfo3 = (ItemInfo)this.mItems.get(n2);
            if (!bl2 && itemInfo3.position != n3 + 1) {
                itemInfo3 = this.mTempItem;
                itemInfo3.offset = f4 + f5 + f3;
                itemInfo3.position = n3 + 1;
                itemInfo3.widthFactor = this.mAdapter.getPageWidth(itemInfo3.position);
                --n2;
            }
            f5 = itemInfo3.offset;
            f4 = itemInfo3.widthFactor;
            if (!bl2) {
                itemInfo2 = itemInfo;
                if (f2 < f5) return itemInfo2;
            }
            if (f2 < f4 + f5 + f3) return itemInfo3;
            if (n2 == this.mItems.size() - 1) {
                return itemInfo3;
            }
            n3 = itemInfo3.position;
            f4 = itemInfo3.widthFactor;
            bl2 = false;
            ++n2;
            itemInfo = itemInfo3;
        } while (true);
    }

    private void initItemIndex() {
        int n2 = this.getClientWidth();
        int n3 = this.getPaddingLeft();
        int n4 = this.getScrollX();
        for (int i2 = 0; i2 < this.mItems.size(); ++i2) {
            ItemInfo itemInfo = (ItemInfo)this.mItems.get(i2);
            itemInfo.index = i2;
            if ((int)((float)n2 * itemInfo.offset) + n3 == n4) {
                this.mTopIndex = i2;
            }
            if (itemInfo.position != this.mCurItem) continue;
            this.mCurIndex = i2;
        }
    }

    private boolean isGutterDrag(float f2, float f3) {
        if (f2 < (float)this.mGutterSize && f3 > 0.0f || f2 > (float)(this.getWidth() - this.mGutterSize) && f3 < 0.0f) {
            return true;
        }
        return false;
    }

    private boolean isShadowPrepared() {
        return this.setupShadow();
    }

    /*
     * Enabled aggressive block sorting
     */
    private void onSecondaryPointerUp(MotionEvent motionEvent) {
        int n2 = MotionEventCompat.getActionIndex(motionEvent);
        if (MotionEventCompat.getPointerId(motionEvent, n2) == this.mActivePointerId) {
            n2 = n2 == 0 ? 1 : 0;
            this.mLastMotionX = MotionEventCompat.getX(motionEvent, n2);
            this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, n2);
            if (this.mVelocityTracker != null) {
                this.mVelocityTracker.clear();
            }
        }
    }

    private boolean pageScrolled(int n2) {
        boolean bl2 = false;
        if (this.mItems.size() == 0) {
            this.mCalledSuper = false;
            this.onPageScrolled(0, 0.0f, 0);
            if (!this.mCalledSuper) {
                throw new IllegalStateException("onPageScrolled did not call superclass implementation");
            }
        } else {
            ItemInfo itemInfo = this.infoForCurrentScrollPosition();
            int n3 = this.getClientWidth();
            int n4 = this.mPageMargin;
            float f2 = (float)this.mPageMargin / (float)n3;
            int n5 = itemInfo.position;
            f2 = ((float)n2 / (float)n3 - itemInfo.offset) / (itemInfo.widthFactor + f2);
            n2 = (int)((float)(n4 + n3) * f2);
            this.mCalledSuper = false;
            this.onPageScrolled(n5, f2, n2);
            if (!this.mCalledSuper) {
                throw new IllegalStateException("onPageScrolled did not call superclass implementation");
            }
            bl2 = true;
        }
        return bl2;
    }

    /*
     * Enabled aggressive block sorting
     */
    private boolean performDrag(float f2) {
        boolean bl2;
        boolean bl3 = true;
        boolean bl4 = false;
        boolean bl5 = false;
        float f3 = this.mLastMotionX;
        this.mLastMotionX = f2;
        float f4 = (float)this.getScrollX() + (f3 - f2);
        int n2 = this.getClientWidth();
        f2 = (float)n2 * this.mFirstOffset;
        f3 = n2;
        float f5 = this.mLastOffset;
        ItemInfo itemInfo = (ItemInfo)this.mItems.get(0);
        ItemInfo itemInfo2 = (ItemInfo)this.mItems.get(this.mItems.size() - 1);
        if (itemInfo.position != 0) {
            f2 = itemInfo.offset * (float)n2;
            bl2 = false;
        } else {
            bl2 = true;
        }
        if (itemInfo2.position != this.mAdapter.getCount() - 1) {
            f3 = itemInfo2.offset * (float)n2;
            bl3 = false;
        } else {
            f3 *= f5;
        }
        if (f4 < f2) {
            f3 = f2;
            if (bl2) {
                bl5 = this.mLeftEdge.onPull(Math.abs((float)(f2 - f4)) / (float)n2);
                f3 = f2;
            }
        } else if (f4 > f3) {
            bl5 = bl4;
            if (bl3) {
                bl5 = this.mRightEdge.onPull(Math.abs((float)(f4 - f3)) / (float)n2);
            }
        } else {
            f3 = f4;
        }
        this.mLastMotionX += f3 - (float)((int)f3);
        if (FlipMode.FLIP_MODE_OVERLAY == this.mFlipMode) {
            this.scrollSidePage((int)f3);
        }
        this.scrollTo((int)f3, this.getScrollY());
        this.pageScrolled((int)f3);
        return bl5;
    }

    private boolean pointInRect(MotionEvent motionEvent, Rect rect) {
        boolean bl2 = false;
        float f2 = motionEvent.getRawX();
        float f3 = motionEvent.getRawY();
        Log.d((String)"ViewPager", (String)("pointInRect x = " + f2 + ", y = " + f3 + ", rect = " + (Object)rect));
        boolean bl3 = bl2;
        if (rect != null) {
            bl3 = bl2;
            if (this.mCurItem == this.mSpecTab) {
                bl3 = bl2;
                if (f2 >= (float)rect.left) {
                    bl3 = bl2;
                    if (f2 <= (float)rect.right) {
                        bl3 = bl2;
                        if (f3 >= (float)rect.top) {
                            bl3 = bl2;
                            if (f3 <= (float)rect.bottom) {
                                bl3 = true;
                            }
                        }
                    }
                }
            }
        }
        return bl3;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void recomputeScrollPosition(int n2, int n3, int n4, int n5) {
        if (n3 > 0 && !this.mItems.isEmpty()) {
            int n6 = this.getPaddingLeft();
            int n7 = this.getPaddingRight();
            int n8 = this.getPaddingLeft();
            int n9 = this.getPaddingRight();
            float f2 = (float)this.getScrollX() / (float)(n3 - n8 - n9 + n5);
            n3 = (int)((float)(n2 - n6 - n7 + n4) * f2);
            this.scrollTo(n3, this.getScrollY());
            n4 = this.mScroller.getDuration();
            n5 = this.mScroller.timePassed();
            ItemInfo itemInfo = this.infoForPosition(this.mCurItem);
            this.mScroller.startScroll(n3, 0, (int)(itemInfo.offset * (float)n2 - (float)n3), 0, n4 - n5);
            return;
        }
        ItemInfo itemInfo = this.infoForPosition(this.mCurItem);
        float f3 = itemInfo != null ? Math.min((float)itemInfo.offset, (float)this.mLastOffset) : 0.0f;
        if ((n2 = (int)(f3 * (float)(n2 - this.getPaddingLeft() - this.getPaddingRight()))) == this.getScrollX()) return;
        this.completeScroll(false);
        this.scrollTo(n2, this.getScrollY());
    }

    private void removeNonDecorViews() {
        int n2 = 0;
        while (n2 < this.getChildCount()) {
            int n3 = n2;
            if (!((LayoutParams)this.getChildAt((int)n2).getLayoutParams()).isDecor) {
                this.removeViewAt(n2);
                n3 = n2 - 1;
            }
            n2 = n3 + 1;
        }
    }

    private void requestParentDisallowInterceptTouchEvent(boolean bl2) {
        ViewParent viewParent = this.getParent();
        if (viewParent != null) {
            viewParent.requestDisallowInterceptTouchEvent(bl2);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private void scrollSidePage(int n2) {
        int n3;
        int n4;
        Object object;
        int n5;
        if (this.mItems.size() == 0) {
            return;
        }
        int n6 = this.getPaddingLeft();
        int n7 = this.getClientWidth();
        int n8 = this.mTopIndex >= this.mItems.size() ? this.mItems.size() - 1 : (this.mTopIndex < 0 ? 0 : this.mTopIndex);
        int n9 = (int)(((ItemInfo)this.mItems.get((int)n8)).offset * (float)n7) + n6;
        View view = ((ItemInfo)this.mItems.get((int)n8)).view;
        ItemInfo itemInfo = n8 - 1 >= 0 ? (ItemInfo)this.mItems.get(n8 - 1) : null;
        Object object2 = n8 + 1 < this.mItems.size() ? (ItemInfo)this.mItems.get(n8 + 1) : null;
        View view2 = itemInfo != null ? itemInfo.view : null;
        Object object3 = object2 != null ? object2.view : null;
        int n10 = n2 - n9;
        if (n10 >= this.mPageMargin / 2 + n7) {
            ++n8;
        } else if (n10 <= - this.mPageMargin / 2 + n7) {
            --n8;
        }
        if (n8 < 0) {
            n8 = 0;
        } else if (n8 >= this.mItems.size()) {
            n8 = this.mItems.size() - 1;
        }
        if (this.mTopIndex != n8) {
            this.mTopIndex = n8;
            n9 = (int)(((ItemInfo)this.mItems.get((int)n8)).offset * (float)n7) + n6;
            view = ((ItemInfo)this.mItems.get((int)n8)).view;
            if (view != null) {
                view.offsetLeftAndRight(n9 - view.getLeft());
            }
            if (n8 + 2 < this.mItems.size() && object3 != null) {
                object3.offsetLeftAndRight((int)((float)n7 * object2.offset) + n6 - object3.getLeft());
            }
            if (n8 - 2 >= 0 && view2 != null) {
                view2.offsetLeftAndRight((int)((float)n7 * itemInfo.offset) + n6 - view2.getLeft());
            }
            itemInfo = n8 - 1 >= 0 ? (ItemInfo)this.mItems.get(n8 - 1) : null;
            object2 = n8 + 1 < this.mItems.size() ? (ItemInfo)this.mItems.get(n8 + 1) : null;
            object3 = itemInfo != null ? itemInfo.view : null;
            view2 = object2 != null ? object2.view : null;
            n8 = n2 - n9;
            object = itemInfo;
            Object object4 = object3;
            itemInfo = object2;
            object3 = object;
            object2 = object4;
        } else {
            object = view2;
            ItemInfo itemInfo2 = itemInfo;
            itemInfo = object2;
            n8 = n10;
            view2 = object3;
            object2 = object;
            object3 = itemInfo2;
        }
        if (view == null) return;
        this.mShdH = view.getMeasuredHeight();
        this.mShdT = view.getTop();
        object = this.mCurIndex >= 0 && this.mCurIndex < this.mItems.size() ? (ItemInfo)this.mItems.get(this.mCurIndex) : null;
        n10 = object != null ? (int)((float)n7 * object.offset) + n6 : 0;
        if (itemInfo != null && view2 != null) {
            n3 = (int)((float)n7 * itemInfo.offset) + n6;
            n5 = n3 - n7 / 2 - this.mPageMargin / 2 + n8 / 2;
        } else {
            n5 = 0;
            n3 = 0;
        }
        int n11 = n4 = 0;
        if (object3 != null) {
            n11 = n4;
            if (object2 != null) {
                n11 = (int)((float)n7 * object3.offset) + n6;
            }
        }
        if (object != null && n2 == n10 || n2 == n9) {
            this.mNeedDrawShadow = false;
            if (view2 != null) {
                view2.offsetLeftAndRight(n3 - view2.getLeft());
            }
            if (object2 != null) {
                object2.offsetLeftAndRight(n11 - object2.getLeft());
            }
            view.offsetLeftAndRight(n9 - view.getLeft());
            return;
        }
        if (n8 >= 0 && view2 != null) {
            this.mShdL = view.getRight();
            this.mCoverAlpha = (int)(102.0 - (double)n8 * 1.0 / (double)n7 * 102.0);
            this.mNeedDrawShadow = true;
            view2.offsetLeftAndRight(n5 - view2.getLeft());
            return;
        }
        if (n8 >= 0) return;
        if (object2 == null) return;
        this.mShdL = object2.getRight();
        this.mCoverAlpha = (int)((double)(- n8) * 1.0 / (double)n7 * 102.0);
        this.mNeedDrawShadow = true;
        view.offsetLeftAndRight(n8 / 2 + n9 - view.getLeft());
    }

    /*
     * Enabled aggressive block sorting
     */
    private void scrollToItem(int n2, boolean bl2, int n3, boolean bl3) {
        int n4;
        ItemInfo itemInfo = this.infoForPosition(n2);
        if (itemInfo != null) {
            float f2 = this.getClientWidth();
            n4 = (int)(Math.max((float)this.mFirstOffset, (float)Math.min((float)itemInfo.offset, (float)this.mLastOffset)) * f2);
        } else {
            n4 = 0;
        }
        if (bl2) {
            if (bl3) {
                this.dispatchOnPageSelected(n2);
            }
            this.smoothScrollTo(n4, 0, n3);
            return;
        }
        if (bl3) {
            this.dispatchOnPageSelected(n2);
        }
        this.completeScroll(false);
        this.scrollTo(n4, 0);
        this.pageScrolled(n4);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void setScrollState(int n2) {
        if (this.mScrollState == n2) {
            return;
        }
        this.mScrollState = n2;
        if (this.mPageTransformer != null) {
            boolean bl2 = n2 != 0;
            this.enableLayers(bl2);
        }
        this.dispatchOnScrollStateChanged(n2);
    }

    private void setScrollingCacheEnabled(boolean bl2) {
        if (this.mScrollingCacheEnabled != bl2) {
            this.mScrollingCacheEnabled = bl2;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private boolean setupShadow() {
        if (!this.mNeedInitShadow) {
            if (this.mShadowDrawable != null && this.mCoverDrawable != null) return true;
            return false;
        }
        this.mNeedInitShadow = false;
        if (this.mShadowDrawable != null) {
            this.mShadowDrawable.setBounds(0, 0, this.mShadowDrawable.getIntrinsicWidth(), this.mShdH);
        }
        if (this.mCoverDrawable != null) {
            this.mCoverDrawable.setBounds(0, 0, this.getClientWidth(), this.mShdH);
        }
        if (this.mShadowDrawable == null || this.mCoverDrawable == null) return false;
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void sortChildDrawingOrder() {
        int n2 = 0;
        int n3 = 0;
        if (FlipMode.FLIP_MODE_OVERLAY == this.mFlipMode) {
            if (this.mDrawingOrderedChildren == null) {
                this.mDrawingOrderedChildren = new LinkedList();
            } else {
                this.mDrawingOrderedChildren.clear();
            }
            n2 = this.getChildCount();
            while (n3 < n2) {
                View view = this.getChildAt(n3);
                this.mDrawingOrderedChildren.add((Object)view);
                ++n3;
            }
            Collections.sort(this.mDrawingOrderedChildren, (Comparator)sReversePositionComparator);
            return;
        }
        if (this.mDrawingOrder == 0) return;
        if (this.mDrawingOrderedChildren == null) {
            this.mDrawingOrderedChildren = new LinkedList();
        } else {
            this.mDrawingOrderedChildren.clear();
        }
        int n4 = this.getChildCount();
        n3 = n2;
        do {
            if (n3 >= n4) {
                Collections.sort(this.mDrawingOrderedChildren, (Comparator)sPositionComparator);
                return;
            }
            View view = this.getChildAt(n3);
            this.mDrawingOrderedChildren.add((Object)view);
            ++n3;
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void addFocusables(ArrayList<View> arrayList, int n2, int n3) {
        int n4 = arrayList.size();
        int n5 = this.getDescendantFocusability();
        if (n5 != 393216) {
            for (int i2 = 0; i2 < this.getChildCount(); ++i2) {
                ItemInfo itemInfo;
                View view = this.getChildAt(i2);
                if (view.getVisibility() != 0 || (itemInfo = this.infoForChild(view)) == null || itemInfo.position != this.mCurItem) continue;
                view.addFocusables(arrayList, n2, n3);
            }
        }
        if (n5 == 262144 && n4 != arrayList.size() || !this.isFocusable() || (n3 & 1) == 1 && this.isInTouchMode() && !this.isFocusableInTouchMode() || arrayList == null) {
            return;
        }
        arrayList.add((Object)this);
    }

    ItemInfo addNewItem(int n2, int n3) {
        ItemInfo itemInfo = new ItemInfo();
        itemInfo.position = n2;
        itemInfo.object = this.mAdapter.instantiateItem(this, n2);
        itemInfo.widthFactor = this.mAdapter.getPageWidth(n2);
        if (n3 < 0 || n3 >= this.mItems.size()) {
            itemInfo.index = this.mItems.size();
            this.mItems.add((Object)itemInfo);
            return itemInfo;
        }
        this.mItems.add(n3, (Object)itemInfo);
        itemInfo.index = n3;
        return itemInfo;
    }

    public void addOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        if (this.mOnPageChangeListeners == null) {
            this.mOnPageChangeListeners = new ArrayList();
        }
        this.mOnPageChangeListeners.add(onPageChangeListener);
    }

    public void addTouchables(ArrayList<View> arrayList) {
        for (int i2 = 0; i2 < this.getChildCount(); ++i2) {
            ItemInfo itemInfo;
            View view = this.getChildAt(i2);
            if (view.getVisibility() != 0 || (itemInfo = this.infoForChild(view)) == null || itemInfo.position != this.mCurItem) continue;
            view.addTouchables(arrayList);
        }
    }

    public void addView(View view, int n2, ViewGroup.LayoutParams layoutParams) {
        if (!this.checkLayoutParams(layoutParams)) {
            layoutParams = this.generateLayoutParams(layoutParams);
        }
        LayoutParams layoutParams2 = (LayoutParams)layoutParams;
        layoutParams2.isDecor |= view instanceof Decor;
        if (this.mInLayout) {
            if (layoutParams2 != null && layoutParams2.isDecor) {
                throw new IllegalStateException("Cannot add pager decor view during layout");
            }
            layoutParams2.needsMeasure = true;
            this.addViewInLayout(view, n2, layoutParams);
            return;
        }
        super.addView(view, n2, layoutParams);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public boolean arrowScroll(int var1_1) {
        block8 : {
            var6_2 = this.findFocus();
            if (var6_2 != this) ** GOTO lbl5
            var5_3 = null;
            ** GOTO lbl8
lbl5: // 1 sources:
            if (var6_2 == null) ** GOTO lbl50
            var5_3 = var6_2.getParent();
            ** GOTO lbl31
lbl8: // 3 sources:
            do {
                var6_2 = FocusFinder.getInstance().findNextFocus((ViewGroup)this, var5_3, var1_1);
                if (var6_2 == null || var6_2 == var5_3) ** GOTO lbl21
                if (var1_1 != 17) ** GOTO lbl16
                var2_4 = this.getChildRectInPagerCoordinates((Rect)this.mTempRect, (View)var6_2).left;
                var3_5 = this.getChildRectInPagerCoordinates((Rect)this.mTempRect, (View)var5_3).left;
                var4_7 = var5_3 != null && var2_4 >= var3_5 ? this.pageLeft() : var6_2.requestFocus();
                ** GOTO lbl28
lbl16: // 1 sources:
                if (var1_1 != 66) ** GOTO lbl-1000
                var2_4 = this.getChildRectInPagerCoordinates((Rect)this.mTempRect, (View)var6_2).left;
                var3_6 = this.getChildRectInPagerCoordinates((Rect)this.mTempRect, (View)var5_3).left;
                var4_7 = var5_3 != null && var2_4 <= var3_6 ? this.pageRight() : var6_2.requestFocus();
                ** GOTO lbl28
lbl21: // 1 sources:
                if (var1_1 == 17 || var1_1 == 1) {
                    var4_7 = this.pageLeft();
                } else if (var1_1 == 66 || var1_1 == 2) {
                    var4_7 = this.pageRight();
                } else lbl-1000: // 2 sources:
                {
                    var4_7 = false;
                }
lbl28: // 5 sources:
                if (var4_7 == false) return var4_7;
                this.playSoundEffect(SoundEffectConstants.getContantForFocusDirection((int)var1_1));
                return var4_7;
                break;
            } while (true);
lbl31: // 2 sources:
            while (var5_3 instanceof ViewGroup) {
                if (var5_3 == this) {
                    var2_4 = 1;
                    break block8;
                }
                var5_3 = var5_3.getParent();
            }
            var2_4 = 0;
        }
        if (var2_4 != 0) ** GOTO lbl50
        var7_8 = new StringBuilder();
        var7_8.append(var6_2.getClass().getSimpleName());
        var5_3 = var6_2.getParent();
        while (var5_3 instanceof ViewGroup) {
            var7_8.append(" => ").append(var5_3.getClass().getSimpleName());
            var5_3 = var5_3.getParent();
        }
        Log.e((String)"ViewPager", (String)("arrowScroll tried to find focus based on non-child current focused view " + var7_8.toString()));
        var5_3 = null;
        ** GOTO lbl8
lbl50: // 2 sources:
        var5_3 = var6_2;
        ** while (true)
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean beginFakeDrag() {
        if (this.mIsBeingDragged) {
            return false;
        }
        this.mFakeDragging = true;
        this.setScrollState(1);
        this.mLastMotionX = 0.0f;
        this.mInitialMotionX = 0.0f;
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        } else {
            this.mVelocityTracker.clear();
        }
        long l2 = SystemClock.uptimeMillis();
        MotionEvent motionEvent = MotionEvent.obtain((long)l2, (long)l2, (int)0, (float)0.0f, (float)0.0f, (int)0);
        this.mVelocityTracker.addMovement(motionEvent);
        motionEvent.recycle();
        this.mFakeDragBeginTime = l2;
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    protected boolean canScroll(View view, boolean bl2, int n2, int n3, int n4) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup)view;
            int n5 = view.getScrollX();
            int n6 = view.getScrollY();
            for (int i2 = viewGroup.getChildCount() - 1; i2 >= 0; --i2) {
                View view2 = viewGroup.getChildAt(i2);
                if (n3 + n5 >= view2.getLeft() && n3 + n5 < view2.getRight() && n4 + n6 >= view2.getTop() && n4 + n6 < view2.getBottom() && this.canScroll(view2, true, n2, n3 + n5 - view2.getLeft(), n4 + n6 - view2.getTop())) return true;
                {
                    continue;
                }
            }
        }
        if (!bl2 || !ViewCompat.canScrollHorizontally(view, - n2)) return false;
        return true;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean canScrollHorizontally(int n2) {
        boolean bl2 = true;
        boolean bl3 = true;
        if (this.mAdapter == null) {
            return false;
        }
        int n3 = this.getClientWidth();
        int n4 = this.getScrollX();
        if (n2 < 0) {
            if (n4 <= (int)((float)n3 * this.mFirstOffset)) return false;
            return bl3;
        }
        if (n2 <= 0) return false;
        if (n4 >= (int)((float)n3 * this.mLastOffset)) return false;
        return bl2;
    }

    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof LayoutParams && super.checkLayoutParams(layoutParams)) {
            return true;
        }
        return false;
    }

    public void clearOnPageChangeListeners() {
        if (this.mOnPageChangeListeners != null) {
            this.mOnPageChangeListeners.clear();
        }
    }

    public void computeScroll() {
        if (!this.mScroller.isFinished() && this.mScroller.computeScrollOffset()) {
            int n2 = this.getScrollX();
            int n3 = this.getScrollY();
            int n4 = this.mScroller.getCurrX();
            int n5 = this.mScroller.getCurrY();
            if (n2 != n4 || n3 != n5) {
                if (FlipMode.FLIP_MODE_OVERLAY == this.mFlipMode) {
                    this.scrollSidePage(n4);
                }
                this.scrollTo(n4, n5);
                if (!this.pageScrolled(n4)) {
                    this.mScroller.abortAnimation();
                    this.scrollTo(0, n5);
                }
            }
            ViewCompat.postInvalidateOnAnimation((View)this);
            return;
        }
        this.completeScroll(true);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    void dataSetChanged() {
        this.mExpectedAdapterCount = var7_1 = this.mAdapter.getCount();
        var1_2 = this.mItems.size() < this.mOffscreenPageLimit * 2 + 1 && this.mItems.size() < var7_1 ? 1 : 0;
        var2_3 = this.mCurItem;
        var3_4 = 0;
        var5_5 = 0;
        var4_6 = var1_2;
        var1_2 = var2_3;
        var2_3 = var3_4;
        var3_4 = var5_5;
        do {
            if (var3_4 >= this.mItems.size()) ** GOTO lbl46
            var8_8 = (ItemInfo)this.mItems.get(var3_4);
            var5_5 = this.mAdapter.getItemPosition(var8_8.object);
            if (var5_5 != -1) ** GOTO lbl22
            var5_5 = var3_4;
            var6_7 = var2_3;
            var3_4 = var4_6;
            var2_3 = var1_2;
            var1_2 = var6_7;
            var4_6 = var5_5;
            ** GOTO lbl75
lbl22: // 1 sources:
            if (var5_5 != -2) ** GOTO lbl35
            this.mItems.remove(var3_4);
            var4_6 = var3_4 - 1;
            var3_4 = var2_3;
            if (var2_3 == 0) {
                this.mAdapter.startUpdate(this);
                var3_4 = 1;
            }
            this.mAdapter.destroyItem(this, var8_8.position, var8_8.object);
            if (this.mCurItem != var8_8.position) ** GOTO lbl71
            var2_3 = Math.max((int)0, (int)Math.min((int)this.mCurItem, (int)(var7_1 - 1)));
            var1_2 = var3_4;
            var3_4 = 1;
            ** GOTO lbl75
lbl35: // 1 sources:
            if (var8_8.position == var5_5) ** GOTO lbl64
            if (var8_8.position == this.mCurItem) {
                var1_2 = var5_5;
            }
            var8_8.position = var5_5;
            var5_5 = var1_2;
            var6_7 = 1;
            var4_6 = var3_4;
            var1_2 = var2_3;
            var2_3 = var5_5;
            var3_4 = var6_7;
            ** GOTO lbl75
lbl46: // 1 sources:
            if (var2_3 != 0) {
                this.mAdapter.finishUpdate(this);
            }
            Collections.sort(this.mItems, ViewPager.COMPARATOR);
            if (FlipMode.FLIP_MODE_OVERLAY == this.mFlipMode) {
                this.initItemIndex();
            }
            if (var4_6 == 0) return;
            var3_4 = this.getChildCount();
            var2_3 = 0;
            do {
                if (var2_3 >= var3_4) {
                    this.setCurrentItemInternal(var1_2, false, true);
                    this.requestLayout();
                    return;
                }
                var8_8 = (LayoutParams)this.getChildAt(var2_3).getLayoutParams();
                if (!var8_8.isDecor) {
                    var8_8.widthFactor = 0.0f;
                }
                ++var2_3;
            } while (true);
lbl64: // 1 sources:
            var5_5 = var1_2;
            var6_7 = var4_6;
            var4_6 = var3_4;
            var1_2 = var2_3;
            var2_3 = var5_5;
            var3_4 = var6_7;
            ** GOTO lbl75
lbl71: // 1 sources:
            var2_3 = var1_2;
            var5_5 = 1;
            var1_2 = var3_4;
            var3_4 = var5_5;
lbl75: // 5 sources:
            var5_5 = var3_4;
            var6_7 = var2_3;
            var3_4 = var4_6 + 1;
            var2_3 = var1_2;
            var1_2 = var6_7;
            var4_6 = var5_5;
        } while (true);
    }

    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (FlipMode.FLIP_MODE_OVERLAY == this.mFlipMode && this.mNeedDrawShadow && this.isShadowPrepared()) {
            canvas.save();
            canvas.translate((float)this.mShdL, (float)this.mShdT);
            this.mShadowDrawable.draw(canvas);
            this.mCoverDrawable.setAlpha(this.mCoverAlpha);
            this.mCoverDrawable.draw(canvas);
            canvas.restore();
        }
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (super.dispatchKeyEvent(keyEvent) || this.executeKeyEvent(keyEvent)) {
            return true;
        }
        return false;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        boolean bl2 = false;
        if (accessibilityEvent.getEventType() == 4096) {
            return super.dispatchPopulateAccessibilityEvent(accessibilityEvent);
        }
        int n2 = this.getChildCount();
        int n3 = 0;
        do {
            ItemInfo itemInfo;
            boolean bl3 = bl2;
            if (n3 >= n2) return bl3;
            View view = this.getChildAt(n3);
            if (view.getVisibility() == 0 && (itemInfo = this.infoForChild(view)) != null && itemInfo.position == this.mCurItem && view.dispatchPopulateAccessibilityEvent(accessibilityEvent)) {
                return true;
            }
            ++n3;
        } while (true);
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public boolean dispatchStatusBarTap() {
        int n2 = this.getChildCount();
        int n3 = 0;
        while (n3 < n2) {
            View view = this.getChildAt(n3);
            ItemInfo itemInfo = this.infoForChild(view);
            if (itemInfo != null && itemInfo.position == this.mCurItem) {
                if (view == null) return false;
                itemInfo = View.class.getDeclaredMethod("dispatchStatusBarTap", new Class[0]);
                itemInfo.setAccessible(true);
                return (Boolean)itemInfo.invoke((Object)view, new Object[0]);
            }
            ++n3;
        }
        return false;
        catch (Exception exception) {
            return false;
        }
    }

    float distanceInfluenceForSnapDuration(float f2) {
        return (float)Math.sin((double)((float)((double)(f2 - 0.5f) * 0.4712389167638204)));
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable2 = this.mMarginDrawable;
        if (drawable2 != null && drawable2.isStateful()) {
            drawable2.setState(this.getDrawableState());
        }
    }

    public void endFakeDrag() {
        if (!this.mFakeDragging) {
            throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
        }
        Object object = this.mVelocityTracker;
        object.computeCurrentVelocity(1000, (float)this.mMaximumVelocity);
        int n2 = (int)VelocityTrackerCompat.getXVelocity((VelocityTracker)object, this.mActivePointerId);
        this.mPopulatePending = true;
        int n3 = this.getClientWidth();
        int n4 = this.getScrollX();
        object = this.infoForCurrentScrollPosition();
        this.setCurrentItemInternal(this.determineTargetPage(object.position, ((float)n4 / (float)n3 - object.offset) / object.widthFactor, n2, (int)(this.mLastMotionX - this.mInitialMotionX)), true, true, n2);
        this.endDrag();
        this.mFakeDragging = false;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean executeKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getAction() != 0) return false;
        {
            switch (keyEvent.getKeyCode()) {
                default: {
                    return false;
                }
                case 21: {
                    return this.arrowScroll(17);
                }
                case 22: {
                    return this.arrowScroll(66);
                }
                case 61: {
                    if (Build.VERSION.SDK_INT < 11) return false;
                    if (KeyEventCompat.hasNoModifiers(keyEvent)) {
                        return this.arrowScroll(2);
                    }
                    if (!KeyEventCompat.hasModifiers(keyEvent, 1)) return false;
                    return this.arrowScroll(1);
                }
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void fakeDragBy(float f2) {
        if (!this.mFakeDragging) {
            throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
        }
        this.mLastMotionX += f2;
        float f3 = (float)this.getScrollX() - f2;
        int n2 = this.getClientWidth();
        f2 = n2;
        float f4 = this.mFirstOffset;
        float f5 = n2;
        float f6 = this.mLastOffset;
        ItemInfo itemInfo = (ItemInfo)this.mItems.get(0);
        ItemInfo itemInfo2 = (ItemInfo)this.mItems.get(this.mItems.size() - 1);
        f2 = itemInfo.position != 0 ? itemInfo.offset * (float)n2 : (f2 *= f4);
        f5 = itemInfo2.position != this.mAdapter.getCount() - 1 ? itemInfo2.offset * (float)n2 : (f5 *= f6);
        if (f3 >= f2) {
            f2 = f3 > f5 ? f5 : f3;
        }
        this.mLastMotionX += f2 - (float)((int)f2);
        this.scrollTo((int)f2, this.getScrollY());
        this.pageScrolled((int)f2);
        long l2 = SystemClock.uptimeMillis();
        itemInfo = MotionEvent.obtain((long)this.mFakeDragBeginTime, (long)l2, (int)2, (float)this.mLastMotionX, (float)0.0f, (int)0);
        this.mVelocityTracker.addMovement((MotionEvent)itemInfo);
        itemInfo.recycle();
    }

    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(this.getContext(), attributeSet);
    }

    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return this.generateDefaultLayoutParams();
    }

    public PagerAdapter getAdapter() {
        return this.mAdapter;
    }

    protected int getChildDrawingOrder(int n2, int n3) {
        int n4 = n3;
        if (this.mDrawingOrder == 2) {
            n4 = n2 - 1 - n3;
        }
        return ((LayoutParams)((View)this.mDrawingOrderedChildren.get((int)n4)).getLayoutParams()).childIndex;
    }

    public int getCurrentItem() {
        return this.mCurItem;
    }

    public FlipMode getFlipMode() {
        return this.mFlipMode;
    }

    public int getOffscreenPageLimit() {
        return this.mOffscreenPageLimit;
    }

    public int getPageMargin() {
        return this.mPageMargin;
    }

    ItemInfo infoForAnyChild(View view) {
        ViewParent viewParent;
        while ((viewParent = view.getParent()) != this) {
            if (viewParent == null || !(viewParent instanceof View)) {
                return null;
            }
            view = (View)viewParent;
        }
        return this.infoForChild(view);
    }

    ItemInfo infoForChild(View view) {
        for (int i2 = 0; i2 < this.mItems.size(); ++i2) {
            ItemInfo itemInfo = (ItemInfo)this.mItems.get(i2);
            if (!this.mAdapter.isViewFromObject(view, itemInfo.object)) continue;
            return itemInfo;
        }
        return null;
    }

    ItemInfo infoForPosition(int n2) {
        for (int i2 = 0; i2 < this.mItems.size(); ++i2) {
            ItemInfo itemInfo = (ItemInfo)this.mItems.get(i2);
            if (itemInfo.position != n2) continue;
            return itemInfo;
        }
        return null;
    }

    void initViewPager() {
        this.setWillNotDraw(false);
        this.setDescendantFocusability(262144);
        this.setFocusable(true);
        Context context = this.getContext();
        this.mScroller = new Scroller(context, sInterpolator);
        this.mCurInterpolator = sInterpolator;
        ViewConfiguration viewConfiguration = ViewConfiguration.get((Context)context);
        float f2 = context.getResources().getDisplayMetrics().density;
        this.mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(viewConfiguration);
        this.mMinimumVelocity = (int)(400.0f * f2);
        this.mMaximumVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        this.mLeftEdge = new EdgeEffectCompat(context);
        this.mRightEdge = new EdgeEffectCompat(context);
        this.mFlingDistance = (int)(25.0f * f2);
        this.mCloseEnough = (int)(2.0f * f2);
        this.mDefaultGutterSize = (int)(16.0f * f2);
        ViewCompat.setAccessibilityDelegate((View)this, new MyAccessibilityDelegate());
        if (ViewCompat.getImportantForAccessibility((View)this) == 0) {
            ViewCompat.setImportantForAccessibility((View)this, 1);
        }
        if (FlipMode.FLIP_MODE_OVERLAY == this.mFlipMode) {
            this.setChildrenDrawingOrderEnabledCompat(true);
        }
    }

    public boolean isFakeDragging() {
        return this.mFakeDragging;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mFirstLayout = true;
    }

    protected void onDetachedFromWindow() {
        this.removeCallbacks(this.mEndScrollRunnable);
        super.onDetachedFromWindow();
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mPageMargin <= 0) return;
        if (this.mMarginDrawable == null) return;
        if (this.mItems.size() <= 0) return;
        if (this.mAdapter == null) return;
        int n2 = this.getScrollX();
        int n3 = this.getWidth();
        float f2 = (float)this.mPageMargin / (float)n3;
        Object object = (ArrayList<ItemInfo>)this.mItems.get(0);
        float f3 = object.offset;
        int n4 = this.mItems.size();
        int n5 = object.position;
        int n6 = ((ItemInfo)this.mItems.get((int)(n4 - 1))).position;
        int n7 = 0;
        while (n5 < n6) {
            float f4;
            while (n5 > object.position && n7 < n4) {
                object = this.mItems;
                object = (ItemInfo)object.get(++n7);
            }
            if (n5 == object.position) {
                f4 = (object.offset + object.widthFactor) * (float)n3;
                f3 = object.offset + object.widthFactor + f2;
            } else {
                float f5 = this.mAdapter.getPageWidth(n5);
                f4 = (f3 + f5) * (float)n3;
                f3 += f5 + f2;
            }
            if ((float)this.mPageMargin + f4 > (float)n2) {
                this.mMarginDrawable.setBounds((int)f4, this.mTopPageBounds, (int)((float)this.mPageMargin + f4 + 0.5f), this.mBottomPageBounds);
                this.mMarginDrawable.draw(canvas);
            }
            if (f4 > (float)(n2 + n3)) {
                return;
            }
            ++n5;
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public boolean onInterceptTouchEvent(MotionEvent var1_1) {
        var7_2 = var1_1.getAction() & 255;
        if (var7_2 == 3 || var7_2 == 1) {
            this.mIsBeingDragged = false;
            this.mIsUnableToDrag = false;
            this.mActivePointerId = -1;
            if (this.mVelocityTracker == null) return false;
            this.mVelocityTracker.recycle();
            this.mVelocityTracker = null;
            return false;
        }
        if (var7_2 != 0) {
            if (this.mIsBeingDragged) {
                return true;
            }
            if (this.mIsUnableToDrag != false) return false;
        }
        switch (var7_2) {
            case 2: {
                var7_2 = this.mActivePointerId;
                if (var7_2 != -1) {
                    var8_3 = MotionEventCompat.findPointerIndex(var1_1, var7_2);
                    if (var8_3 == -1) {
                        Log.e((String)"ViewPager", (String)("Invalid pointerId=" + var7_2 + " in onInterceptTouchEvent ACTION_MOVE"));
                        ** break;
                    }
                    var3_4 = MotionEventCompat.getX(var1_1, var8_3);
                    var2_5 = var3_4 - this.mLastMotionX;
                    var5_7 = Math.abs((float)var2_5);
                    var4_8 = MotionEventCompat.getY(var1_1, var8_3);
                    var6_9 = Math.abs((float)(var4_8 - this.mInitialMotionY));
                    if (var2_5 != 0.0f && !this.isGutterDrag(this.mLastMotionX, var2_5) && this.canScroll((View)this, false, (int)var2_5, (int)var3_4, (int)var4_8)) {
                        this.mLastMotionX = var3_4;
                        this.mLastMotionY = var4_8;
                        this.mIsUnableToDrag = true;
                        return false;
                    }
                    if (var5_7 < (float)this.mTouchSlopAdjust && var6_9 < (float)this.mTouchSlopAdjust && this.pointInRect(var1_1, this.mSpecRect)) {
                        Log.d((String)"ViewPager", (String)("xDiff = " + var5_7 + ", yDiff = " + var6_9 + ", mTouchSlopAdj = " + this.mTouchSlopAdjust));
                        return false;
                    }
                    if (var5_7 > (float)this.mTouchSlop && var5_7 > var6_9) {
                        this.mIsBeingDragged = true;
                        this.requestParentDisallowInterceptTouchEvent(true);
                        this.setScrollState(1);
                        var2_5 = var2_5 > 0.0f ? this.mInitialMotionX + (float)this.mTouchSlop : this.mInitialMotionX - (float)this.mTouchSlop;
                        this.mLastMotionX = var2_5;
                        this.mLastMotionY = var4_8;
                        this.setScrollingCacheEnabled(true);
                    } else if (var6_9 > (float)this.mTouchSlop) {
                        this.mIsUnableToDrag = true;
                    }
                    if (this.mIsBeingDragged && this.performDrag(var3_4)) {
                        ViewCompat.postInvalidateOnAnimation((View)this);
                        ** break;
                    }
                }
                ** GOTO lbl68
            }
            case 0: {
                this.mInitialMotionX = var2_6 = var1_1.getX();
                this.mLastMotionX = var2_6;
                this.mInitialMotionY = var2_6 = var1_1.getY();
                this.mLastMotionY = var2_6;
                this.mActivePointerId = MotionEventCompat.getPointerId(var1_1, 0);
                this.mIsUnableToDrag = false;
                this.mScroller.computeScrollOffset();
                if (this.mScrollState == 2 && Math.abs((int)(this.mScroller.getFinalX() - this.mScroller.getCurrX())) > this.mCloseEnough) {
                    this.mScroller.abortAnimation();
                    this.mPopulatePending = false;
                    this.populate();
                    this.mIsBeingDragged = true;
                    this.requestParentDisallowInterceptTouchEvent(true);
                    this.setScrollState(1);
                    ** break;
                }
                this.completeScroll(false);
                this.mIsBeingDragged = false;
            }
lbl68: // 6 sources:
            default: {
                ** GOTO lbl72
            }
            case 6: 
        }
        this.onSecondaryPointerUp(var1_1);
lbl72: // 2 sources:
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(var1_1);
        return this.mIsBeingDragged;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public boolean onInterceptTouchEventExt(MotionEvent var1_1) {
        var7_2 = var1_1.getAction() & 255;
        if (var7_2 == 3 || var7_2 == 1) {
            this.mIsBeingDragged = false;
            this.mIsUnableToDrag = false;
            this.mActivePointerId = -1;
            if (this.mVelocityTracker == null) return false;
            this.mVelocityTracker.recycle();
            this.mVelocityTracker = null;
            return false;
        }
        if (var7_2 != 0) {
            if (this.mIsBeingDragged) {
                return true;
            }
            if (this.mIsUnableToDrag != false) return false;
        }
        switch (var7_2) {
            case 2: {
                var7_2 = this.mActivePointerId;
                if (var7_2 != -1) {
                    var7_2 = MotionEventCompat.findPointerIndex(var1_1, var7_2);
                    var3_3 = MotionEventCompat.getX(var1_1, var7_2);
                    var2_4 = var3_3 - this.mLastMotionX;
                    var5_6 = Math.abs((float)var2_4);
                    var4_7 = MotionEventCompat.getY(var1_1, var7_2);
                    var6_8 = Math.abs((float)(var4_7 - this.mInitialMotionY));
                    if (var5_6 > (float)this.mTouchSlop && (double)var5_6 * 1.6 > (double)var6_8) {
                        this.mIsBeingDragged = true;
                        this.requestParentDisallowInterceptTouchEvent(true);
                        this.setScrollState(1);
                        var2_4 = var2_4 > 0.0f ? this.mInitialMotionX + (float)this.mTouchSlop : this.mInitialMotionX - (float)this.mTouchSlop;
                        this.mLastMotionX = var2_4;
                        this.mLastMotionY = var4_7;
                        this.setScrollingCacheEnabled(true);
                    } else if (var6_8 > (float)this.mTouchSlop) {
                        this.mIsUnableToDrag = true;
                    }
                    if (this.mIsBeingDragged && this.performDrag(var3_3)) {
                        ViewCompat.postInvalidateOnAnimation((View)this);
                        ** break;
                    }
                }
                ** GOTO lbl57
            }
            case 0: {
                this.mInitialMotionX = var2_5 = var1_1.getX();
                this.mLastMotionX = var2_5;
                this.mInitialMotionY = var2_5 = var1_1.getY();
                this.mLastMotionY = var2_5;
                this.mActivePointerId = MotionEventCompat.getPointerId(var1_1, 0);
                this.mIsUnableToDrag = false;
                this.mScroller.computeScrollOffset();
                if (this.mScrollState == 2 && Math.abs((int)(this.mScroller.getFinalX() - this.mScroller.getCurrX())) > this.mCloseEnough) {
                    this.mScroller.abortAnimation();
                    this.mPopulatePending = false;
                    this.populate();
                    this.mIsBeingDragged = true;
                    this.requestParentDisallowInterceptTouchEvent(true);
                    this.setScrollState(1);
                    ** break;
                }
                this.completeScroll(false);
                this.mIsBeingDragged = false;
            }
lbl57: // 5 sources:
            default: {
                ** GOTO lbl61
            }
            case 6: 
        }
        this.onSecondaryPointerUp(var1_1);
lbl61: // 2 sources:
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(var1_1);
        return this.mIsBeingDragged;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    protected void onLayout(boolean var1_1, int var2_2, int var3_3, int var4_4, int var5_5) {
        var12_6 = this.getChildCount();
        var15_7 = var4_4 - var2_2;
        var13_8 = var5_5 - var3_3;
        var3_3 = this.getPaddingLeft();
        var2_2 = this.getPaddingTop();
        var7_9 = this.getPaddingRight();
        var4_4 = this.getPaddingBottom();
        var14_10 = this.getScrollX();
        var8_11 = 0;
        var10_12 = 0;
        do {
            if (var10_12 >= var12_6) ** GOTO lbl72
            var17_16 = this.getChildAt(var10_12);
            if (var17_16.getVisibility() == 8) ** GOTO lbl97
            var18_17 = (LayoutParams)var17_16.getLayoutParams();
            if (!var18_17.isDecor) ** GOTO lbl97
            var5_5 = var18_17.gravity;
            var16_15 = var18_17.gravity;
            switch (var5_5 & 7) {
                default: {
                    var5_5 = var3_3;
                    var9_13 = var3_3;
                    break;
                }
                case 3: {
                    var9_13 = var17_16.getMeasuredWidth();
                    var5_5 = var3_3;
                    var9_13 += var3_3;
                    break;
                }
                case 1: {
                    var5_5 = Math.max((int)((var15_7 - var17_16.getMeasuredWidth()) / 2), (int)var3_3);
                    var9_13 = var3_3;
                    break;
                }
                case 5: {
                    var9_13 = var17_16.getMeasuredWidth();
                    var5_5 = var7_9 + var17_16.getMeasuredWidth();
                    var11_14 = var15_7 - var7_9 - var9_13;
                    var7_9 = var5_5;
                    var9_13 = var3_3;
                    var5_5 = var11_14;
                }
            }
            switch (var16_15 & 112) {
                default: {
                    var11_14 = var2_2;
                    var3_3 = var2_2;
                    var2_2 = var4_4;
                    var4_4 = var11_14;
                    break;
                }
                case 48: {
                    var11_14 = var17_16.getMeasuredHeight();
                    var3_3 = var4_4;
                    var4_4 = var2_2;
                    var2_2 = var3_3;
                    var3_3 = var11_14 += var2_2;
                    break;
                }
                case 16: {
                    var11_14 = Math.max((int)((var13_8 - var17_16.getMeasuredHeight()) / 2), (int)var2_2);
                    var3_3 = var2_2;
                    var2_2 = var4_4;
                    var4_4 = var11_14;
                    break;
                }
                case 80: {
                    var11_14 = var13_8 - var4_4 - var17_16.getMeasuredHeight();
                    var16_15 = var17_16.getMeasuredHeight();
                    var3_3 = var2_2;
                    var2_2 = var4_4 + var16_15;
                    var4_4 = var11_14;
                }
            }
            var17_16.layout(var5_5, var4_4, var17_16.getMeasuredWidth() + (var5_5 += var14_10), var17_16.getMeasuredHeight() + var4_4);
            var5_5 = var7_9;
            var4_4 = var9_13;
            var7_9 = var2_2;
            var2_2 = ++var8_11;
            ** GOTO lbl105
lbl72: // 1 sources:
            var7_9 = var15_7 - var3_3 - var7_9;
            for (var5_5 = 0; var5_5 < var12_6; ++var5_5) {
                var17_16 = this.getChildAt(var5_5);
                if (var17_16.getVisibility() == 8) continue;
                var18_17 = (LayoutParams)var17_16.getLayoutParams();
                if (var18_17.isDecor || (var19_19 = this.infoForChild(var17_16)) == null) continue;
                var9_13 = (int)((float)var7_9 * var19_19.offset) + var3_3;
                if (var18_17.needsMeasure) {
                    var18_17.needsMeasure = false;
                    var6_18 = var7_9;
                    var17_16.measure(View.MeasureSpec.makeMeasureSpec((int)((int)(var18_17.widthFactor * var6_18)), (int)1073741824), View.MeasureSpec.makeMeasureSpec((int)(var13_8 - var2_2 - var4_4), (int)1073741824));
                }
                var19_19.view = var17_16;
                var17_16.layout(var9_13, var2_2, var17_16.getMeasuredWidth() + var9_13, var17_16.getMeasuredHeight() + var2_2);
            }
            this.mTopPageBounds = var2_2;
            this.mBottomPageBounds = var13_8 - var4_4;
            this.mDecorChildCount = var8_11;
            if (FlipMode.FLIP_MODE_OVERLAY == this.mFlipMode) {
                this.mNeedInitShadow = true;
                this.initItemIndex();
                this.scrollSidePage(var14_10);
            }
            if (this.mFirstLayout) {
                this.scrollToItem(this.mCurItem, false, 0, false);
            }
            this.mFirstLayout = false;
            return;
lbl97: // 2 sources:
            var5_5 = var8_11;
            var8_11 = var2_2;
            var9_13 = var3_3;
            var2_2 = var5_5;
            var5_5 = var7_9;
            var7_9 = var4_4;
            var3_3 = var8_11;
            var4_4 = var9_13;
lbl105: // 2 sources:
            ++var10_12;
            var9_13 = var4_4;
            var8_11 = var2_2;
            var2_2 = var3_3;
            var4_4 = var7_9;
            var7_9 = var5_5;
            var3_3 = var9_13;
        } while (true);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    protected void onMeasure(int var1_1, int var2_2) {
        this.setMeasuredDimension(ViewPager.getDefaultSize((int)0, (int)var1_1), ViewPager.getDefaultSize((int)0, (int)var2_2));
        var1_1 = this.getMeasuredWidth();
        this.mGutterSize = Math.min((int)(var1_1 / 10), (int)this.mDefaultGutterSize);
        var1_1 = var1_1 - this.getPaddingLeft() - this.getPaddingRight();
        var2_2 = this.getMeasuredHeight() - this.getPaddingTop() - this.getPaddingBottom();
        var12_3 = this.getChildCount();
        var6_4 = 0;
        do {
            if (var6_4 >= var12_3) ** GOTO lbl38
            var13_12 = this.getChildAt(var6_4);
            var4_5 = var1_1;
            var5_6 = var2_2;
            if (var13_12.getVisibility() == 8) ** GOTO lbl73
            var14_13 = (LayoutParams)var13_12.getLayoutParams();
            var4_5 = var1_1;
            var5_6 = var2_2;
            if (var14_13 == null) ** GOTO lbl73
            var4_5 = var1_1;
            var5_6 = var2_2;
            if (!var14_13.isDecor) ** GOTO lbl73
            var4_5 = var14_13.gravity & 7;
            var7_7 = var14_13.gravity & 112;
            var9_9 = Integer.MIN_VALUE;
            var5_6 = Integer.MIN_VALUE;
            var7_7 = var7_7 == 48 || var7_7 == 80 ? 1 : 0;
            var8_8 = var4_5 == 3 || var4_5 == 5;
            if (var7_7 != 0) {
                var4_5 = 1073741824;
            } else {
                var4_5 = var9_9;
                if (var8_8) {
                    var5_6 = 1073741824;
                    var4_5 = var9_9;
                }
            }
            if (var14_13.width == -2) ** GOTO lbl53
            var9_9 = 1073741824;
            var4_5 = var14_13.width != -1 ? var14_13.width : var1_1;
            ** GOTO lbl55
lbl38: // 1 sources:
            this.mChildWidthMeasureSpec = View.MeasureSpec.makeMeasureSpec((int)var1_1, (int)1073741824);
            this.mChildHeightMeasureSpec = View.MeasureSpec.makeMeasureSpec((int)var2_2, (int)1073741824);
            this.mInLayout = true;
            this.populate();
            this.mInLayout = false;
            var4_5 = this.getChildCount();
            var2_2 = 0;
            while (var2_2 < var4_5) {
                var13_12 = this.getChildAt(var2_2);
                if (!(var13_12.getVisibility() == 8 || (var14_13 = (LayoutParams)var13_12.getLayoutParams()) != null && var14_13.isDecor)) {
                    var3_14 = var1_1;
                    var13_12.measure(View.MeasureSpec.makeMeasureSpec((int)((int)(var14_13.widthFactor * var3_14)), (int)1073741824), this.mChildHeightMeasureSpec);
                }
                ++var2_2;
            }
            return;
lbl53: // 1 sources:
            var9_9 = var4_5;
            var4_5 = var1_1;
lbl55: // 2 sources:
            if (var14_13.height == -2) ** GOTO lbl-1000
            var5_6 = var10_10 = 1073741824;
            if (var14_13.height != -1) {
                var11_11 = var14_13.height;
                var5_6 = var10_10;
                var10_10 = var11_11;
            } else lbl-1000: // 2 sources:
            {
                var10_10 = var2_2;
            }
            var13_12.measure(View.MeasureSpec.makeMeasureSpec((int)var4_5, (int)var9_9), View.MeasureSpec.makeMeasureSpec((int)var10_10, (int)var5_6));
            if (var7_7 != 0) {
                var5_6 = var2_2 - var13_12.getMeasuredHeight();
                var4_5 = var1_1;
            } else {
                var4_5 = var1_1;
                var5_6 = var2_2;
                if (var8_8) {
                    var4_5 = var1_1 - var13_12.getMeasuredWidth();
                    var5_6 = var2_2;
                }
            }
lbl73: // 7 sources:
            ++var6_4;
            var1_1 = var4_5;
            var2_2 = var5_6;
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void onPageScrolled(int n2, float f2, int n3) {
        View view;
        int n4;
        if (this.mDecorChildCount > 0) {
            int n5 = this.getScrollX();
            n4 = this.getPaddingLeft();
            int n6 = this.getPaddingRight();
            int n7 = this.getWidth();
            int n8 = this.getChildCount();
            for (int i2 = 0; i2 < n8; ++i2) {
                int n9;
                int n10;
                view = this.getChildAt(i2);
                LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
                if (!layoutParams.isDecor) {
                    n9 = n4;
                    n10 = n6;
                } else {
                    int n11;
                    switch (layoutParams.gravity & 7) {
                        default: {
                            n10 = n4;
                            n9 = n6;
                            n6 = n4;
                            n4 = n9;
                            break;
                        }
                        case 3: {
                            n10 = view.getWidth();
                            n9 = n10 + n4;
                            n10 = n4;
                            n4 = n6;
                            n6 = n9;
                            break;
                        }
                        case 1: {
                            n10 = Math.max((int)((n7 - view.getMeasuredWidth()) / 2), (int)n4);
                            n9 = n4;
                            n4 = n6;
                            n6 = n9;
                            break;
                        }
                        case 5: {
                            n10 = n7 - n6 - view.getMeasuredWidth();
                            n11 = view.getMeasuredWidth();
                            n9 = n4;
                            n4 = n6 + n11;
                            n6 = n9;
                        }
                    }
                    n11 = n10 + n5 - view.getLeft();
                    n10 = n4;
                    n9 = n6;
                    if (n11 != 0) {
                        view.offsetLeftAndRight(n11);
                        n10 = n4;
                        n9 = n6;
                    }
                }
                n4 = n9;
                n6 = n10;
            }
        }
        this.dispatchOnPageScrolled(n2, f2, n3);
        if (this.mPageTransformer != null) {
            n3 = this.getScrollX();
            n4 = this.getChildCount();
            for (n2 = 0; n2 < n4; ++n2) {
                view = this.getChildAt(n2);
                if (((LayoutParams)view.getLayoutParams()).isDecor) continue;
                f2 = (float)(view.getLeft() - n3) / (float)this.getClientWidth();
                this.mPageTransformer.transformPage(view, f2);
            }
        }
        this.mCalledSuper = true;
    }

    /*
     * Enabled aggressive block sorting
     */
    protected boolean onRequestFocusInDescendants(int n2, Rect rect) {
        int n3;
        int n4 = -1;
        int n5 = this.getChildCount();
        if ((n2 & 2) != 0) {
            n4 = 1;
            n3 = 0;
        } else {
            n3 = n5 - 1;
            n5 = -1;
        }
        while (n3 != n5) {
            ItemInfo itemInfo;
            View view = this.getChildAt(n3);
            if (view.getVisibility() == 0 && (itemInfo = this.infoForChild(view)) != null && itemInfo.position == this.mCurItem && view.requestFocus(n2, rect)) {
                return true;
            }
            n3 += n4;
        }
        return false;
    }

    public void onRestoreInstanceState(Parcelable object) {
        if (!(object instanceof SavedState)) {
            super.onRestoreInstanceState((Parcelable)object);
            return;
        }
        object = (SavedState)((Object)object);
        super.onRestoreInstanceState(object.getSuperState());
        if (this.mAdapter != null) {
            this.mAdapter.restoreState(object.adapterState, object.loader);
            this.setCurrentItemInternal(object.position, false, true);
            return;
        }
        this.mRestoredCurItem = object.position;
        this.mRestoredAdapterState = object.adapterState;
        this.mRestoredClassLoader = object.loader;
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.position = this.mCurItem;
        if (this.mAdapter != null) {
            savedState.adapterState = this.mAdapter.saveState();
        }
        return savedState;
    }

    protected void onSizeChanged(int n2, int n3, int n4, int n5) {
        super.onSizeChanged(n2, n3, n4, n5);
        if (n2 != n4) {
            this.recomputeScrollPosition(n2, n4, this.mPageMargin, this.mPageMargin);
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public boolean onTouchEvent(MotionEvent var1_1) {
        var7_2 = 0;
        if (this.mFakeDragging) {
            return true;
        }
        if (var1_1.getAction() == 0 && var1_1.getEdgeFlags() != 0) {
            return false;
        }
        if (this.mAdapter == null) return false;
        if (this.mAdapter.getCount() == 0) {
            return false;
        }
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(var1_1);
        var6_3 = var7_2;
        switch (var1_1.getAction() & 255) {
            default: {
                var6_3 = var7_2;
                break;
            }
            case 0: {
                this.mScroller.abortAnimation();
                this.mPopulatePending = false;
                this.populate();
                this.mInitialMotionX = var2_4 = var1_1.getX();
                this.mLastMotionX = var2_4;
                this.mInitialMotionY = var2_4 = var1_1.getY();
                this.mLastMotionY = var2_4;
                this.mActivePointerId = MotionEventCompat.getPointerId(var1_1, 0);
                var6_3 = var7_2;
                break;
            }
            case 2: {
                if (this.mCurInterpolator != ViewPager.sInterpolator) {
                    this.mLastInterpolator = this.mCurInterpolator;
                    this.setInterpolator(ViewPager.sInterpolator);
                }
                if (!this.mIsBeingDragged) {
                    var6_3 = MotionEventCompat.findPointerIndex(var1_1, this.mActivePointerId);
                    if (var6_3 == -1) {
                        Log.e((String)"ViewPager", (String)("Invalid pointerId=" + this.mActivePointerId + " in onTouchEvent ACTION_MOVE"));
                        var6_3 = var7_2;
                        break;
                    }
                    var2_5 = MotionEventCompat.getX(var1_1, var6_3);
                    var4_7 = Math.abs((float)(var2_5 - this.mLastMotionX));
                    var3_8 = MotionEventCompat.getY(var1_1, var6_3);
                    var5_9 = Math.abs((float)(var3_8 - this.mLastMotionY));
                    if (var4_7 > (float)this.mTouchSlop && var4_7 > var5_9) {
                        this.mIsBeingDragged = true;
                        this.requestParentDisallowInterceptTouchEvent(true);
                        var2_5 = var2_5 - this.mInitialMotionX > 0.0f ? this.mInitialMotionX + (float)this.mTouchSlop : this.mInitialMotionX - (float)this.mTouchSlop;
                        this.mLastMotionX = var2_5;
                        this.mLastMotionY = var3_8;
                        this.setScrollState(1);
                        this.setScrollingCacheEnabled(true);
                        var12_10 = this.getParent();
                        if (var12_10 != null) {
                            var12_10.requestDisallowInterceptTouchEvent(true);
                        }
                    }
                }
                var6_3 = var7_2;
                if (this.mIsBeingDragged) {
                    var6_3 = false | this.performDrag(MotionEventCompat.getX(var1_1, MotionEventCompat.findPointerIndex(var1_1, this.mActivePointerId)));
                    break;
                }
                ** GOTO lbl107
            }
            case 1: {
                var6_3 = var7_2;
                if (this.mIsBeingDragged) {
                    var12_11 = this.mVelocityTracker;
                    var12_11.computeCurrentVelocity(1000, (float)this.mMaximumVelocity);
                    var6_3 = (int)VelocityTrackerCompat.getXVelocity((VelocityTracker)var12_11, this.mActivePointerId);
                    this.mPopulatePending = true;
                    var9_12 = this.getClientWidth();
                    var10_13 = this.getScrollX();
                    var12_11 = this.infoForCurrentScrollPosition();
                    var8_14 = var12_11.position;
                    var2_6 = ((float)var10_13 / (float)var9_12 - var12_11.offset) / var12_11.widthFactor;
                    var9_12 = MotionEventCompat.findPointerIndex(var1_1, this.mActivePointerId);
                    if (var9_12 == -1) {
                        Log.e((String)"ViewPager", (String)("Invalid pointerId=" + this.mActivePointerId + " in onTouchEvent ACTION_UP"));
                        var6_3 = var7_2;
                        break;
                    }
                    var7_2 = MotionEventCompat.getPointerCount(var1_1);
                    if (var9_12 < 0 || var9_12 >= var7_2) {
                        this.setCurrentItemInternal(var8_14, true, true);
                        this.mActivePointerId = -1;
                        this.endDrag();
                        var11_15 = this.mLeftEdge.onRelease();
                        var6_3 = this.mRightEdge.onRelease() | var11_15;
                        break;
                    }
                    if (FlipMode.FLIP_MODE_OVERLAY == this.mFlipMode) {
                        this.setInterpolator(ViewPager.sInterpolator);
                    }
                    this.setCurrentItemInternal(this.determineTargetPage(var8_14, var2_6, var6_3, (int)(MotionEventCompat.getX(var1_1, var9_12) - this.mInitialMotionX)), true, true, var6_3);
                    this.mActivePointerId = -1;
                    this.endDrag();
                    var11_16 = this.mLeftEdge.onRelease();
                    var6_3 = this.mRightEdge.onRelease() | var11_16;
                    break;
                }
                ** GOTO lbl107
            }
            case 3: {
                var6_3 = var7_2;
                if (this.mIsBeingDragged) {
                    this.scrollToItem(this.mCurItem, true, 0, false);
                    this.mActivePointerId = -1;
                    this.endDrag();
                    var11_17 = this.mLeftEdge.onRelease();
                    var6_3 = this.mRightEdge.onRelease() | var11_17;
                    break;
                }
                ** GOTO lbl107
            }
            case 5: {
                var6_3 = MotionEventCompat.getActionIndex(var1_1);
                this.mLastMotionX = MotionEventCompat.getX(var1_1, var6_3);
                this.mActivePointerId = MotionEventCompat.getPointerId(var1_1, var6_3);
                var6_3 = var7_2;
            }
lbl107: // 5 sources:
            case 4: {
                break;
            }
            case 6: {
                this.onSecondaryPointerUp(var1_1);
                this.mLastMotionX = MotionEventCompat.getX(var1_1, MotionEventCompat.findPointerIndex(var1_1, this.mActivePointerId));
                var6_3 = var7_2;
            }
        }
        if (var6_3 == 0) return true;
        ViewCompat.postInvalidateOnAnimation((View)this);
        return true;
    }

    boolean pageLeft() {
        if (this.mCurItem > 0) {
            this.setCurrentItem(this.mCurItem - 1, true);
            return true;
        }
        return false;
    }

    boolean pageRight() {
        if (this.mAdapter != null && this.mCurItem < this.mAdapter.getCount() - 1) {
            this.setCurrentItem(this.mCurItem + 1, true);
            return true;
        }
        return false;
    }

    void populate() {
        this.populate(this.mCurItem);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    void populate(int var1_1) {
        block34 : {
            block33 : {
                if (this.mCurItem != var1_1) {
                    var5_2 = this.mCurItem < var1_1 ? 66 : 17;
                    var15_3 = this.infoForPosition(this.mCurItem);
                    this.mCurItem = var1_1;
                    if ((this.mAdapter == null || this.mPopulatePending || this.getWindowToken() == null) && FlipMode.FLIP_MODE_OVERLAY == this.mFlipMode) {
                        this.initItemIndex();
                    }
                    var6_4 = var5_2;
                } else {
                    var15_3 = null;
                    var6_4 = 2;
                }
                if (this.mAdapter == null) {
                    this.sortChildDrawingOrder();
                    return;
                }
                if (this.mPopulatePending) {
                    this.sortChildDrawingOrder();
                    return;
                }
                if (this.getWindowToken() == null) return;
                this.mAdapter.startUpdate(this);
                var1_1 = this.mOffscreenPageLimit;
                var12_5 = Math.max((int)0, (int)(this.mCurItem - var1_1));
                var10_6 = this.mAdapter.getCount();
                var11_7 = Math.min((int)(var10_6 - 1), (int)(var1_1 + this.mCurItem));
                if (var10_6 != this.mExpectedAdapterCount) {
                    try {
                        var14_8 = this.getResources().getResourceName(this.getId());
                    }
                    catch (Resources.NotFoundException var14_9) {
                        var14_8 = Integer.toHexString((int)this.getId());
                        throw new IllegalStateException("The application's PagerAdapter changed the adapter's contents without calling PagerAdapter#notifyDataSetChanged! Expected adapter item count: " + this.mExpectedAdapterCount + ", found: " + var10_6 + " Pager id: " + var14_8 + " Pager class: " + (Object)this.getClass() + " Problematic adapter: " + (Object)this.mAdapter.getClass());
                    }
                    throw new IllegalStateException("The application's PagerAdapter changed the adapter's contents without calling PagerAdapter#notifyDataSetChanged! Expected adapter item count: " + this.mExpectedAdapterCount + ", found: " + var10_6 + " Pager id: " + var14_8 + " Pager class: " + (Object)this.getClass() + " Problematic adapter: " + (Object)this.mAdapter.getClass());
                }
                for (var1_1 = 0; var1_1 < this.mItems.size(); ++var1_1) {
                    var14_10 = (ItemInfo)this.mItems.get(var1_1);
                    if (var14_10.position < this.mCurItem) continue;
                    if (var14_10.position != this.mCurItem) break;
                    break block33;
                }
                var14_10 = null;
            }
            var16_11 = var14_10 == null && var10_6 > 0 ? this.addNewItem(this.mCurItem, var1_1) : var14_10;
            if (var16_11 == null) ** GOTO lbl128
            var9_12 = var1_1 - 1;
            var14_10 = var9_12 >= 0 ? (ItemInfo)this.mItems.get(var9_12) : null;
            var13_13 = this.getClientWidth();
            var3_14 = var13_13 <= 0 ? 0.0f : 2.0f - var16_11.widthFactor + (float)this.getPaddingLeft() / (float)var13_13;
            var5_2 = this.mCurItem;
            var4_15 = 0.0f;
            var8_16 = var5_2 - 1;
            var7_17 = var1_1;
            var17_18 = var14_10;
            do {
                if (var8_16 < 0) ** GOTO lbl-1000
                if (var4_15 >= var3_14 && var8_16 < var12_5) {
                    ** if (var17_18 != null) goto lbl62
                }
                ** GOTO lbl83
lbl-1000: // 2 sources:
                {
                    var2_19 = var16_11.widthFactor;
                    var1_1 = var7_17 + 1;
                    if (var2_19 < 2.0f) {
                        var14_10 = var1_1 < this.mItems.size() ? (ItemInfo)this.mItems.get(var1_1) : null;
                        var3_14 = var13_13 <= 0 ? 0.0f : (float)this.getPaddingRight() / (float)var13_13 + 2.0f;
                        var5_2 = this.mCurItem;
                        ++var5_2;
                        break;
                    }
                    break block34;
                }
lbl62: // 1 sources:
                var14_10 = var17_18;
                var1_1 = var9_12;
                var2_19 = var4_15;
                var5_2 = var7_17;
                if (var8_16 == var17_18.position) {
                    var14_10 = var17_18;
                    var1_1 = var9_12;
                    var2_19 = var4_15;
                    var5_2 = var7_17;
                    if (!var17_18.scrolling) {
                        this.mItems.remove(var9_12);
                        this.mAdapter.destroyItem(this, var8_16, var17_18.object);
                        var1_1 = var9_12 - 1;
                        var5_2 = var7_17 - 1;
                        if (var1_1 >= 0) {
                            var14_10 = (ItemInfo)this.mItems.get(var1_1);
                            var2_19 = var4_15;
                        } else {
                            var14_10 = null;
                            var2_19 = var4_15;
                        }
                    }
                }
                ** GOTO lbl101
lbl83: // 1 sources:
                if (var17_18 != null && var8_16 == var17_18.position) {
                    var2_19 = var4_15 + var17_18.widthFactor;
                    var1_1 = var9_12 - 1;
                    if (var1_1 >= 0) {
                        var14_10 = (ItemInfo)this.mItems.get(var1_1);
                        var5_2 = var7_17;
                    } else {
                        var14_10 = null;
                        var5_2 = var7_17;
                    }
                } else {
                    var2_19 = var4_15 + this.addNewItem((int)var8_16, (int)(var9_12 + 1)).widthFactor;
                    var5_2 = var7_17 + 1;
                    if (var9_12 >= 0) {
                        var14_10 = (ItemInfo)this.mItems.get(var9_12);
                        var1_1 = var9_12;
                    } else {
                        var14_10 = null;
                        var1_1 = var9_12;
                    }
                }
lbl101: // 6 sources:
                --var8_16;
                var17_18 = var14_10;
                var9_12 = var1_1;
                var4_15 = var2_19;
                var7_17 = var5_2;
            } while (true);
            while (var5_2 < var10_6) {
                if (var2_19 >= var3_14 && var5_2 > var11_7) {
                    if (var14_10 == null) break;
                    if (var5_2 == var14_10.position && !var14_10.scrolling) {
                        this.mItems.remove(var1_1);
                        this.mAdapter.destroyItem(this, var5_2, var14_10.object);
                        var14_10 = var1_1 < this.mItems.size() ? (ItemInfo)this.mItems.get(var1_1) : null;
                    }
                } else if (var14_10 != null && var5_2 == var14_10.position) {
                    var4_15 = var14_10.widthFactor;
                    var14_10 = ++var1_1 < this.mItems.size() ? (ItemInfo)this.mItems.get(var1_1) : null;
                    var2_19 += var4_15;
                } else {
                    var14_10 = this.addNewItem(var5_2, var1_1);
                    var4_15 = var14_10.widthFactor;
                    var14_10 = ++var1_1 < this.mItems.size() ? (ItemInfo)this.mItems.get(var1_1) : null;
                    var2_19 += var4_15;
                }
                ++var5_2;
            }
        }
        this.calculatePageOffsets(var16_11, var7_17, (ItemInfo)var15_3);
lbl128: // 2 sources:
        var15_3 = this.mAdapter;
        var1_1 = this.mCurItem;
        var14_10 = var16_11 != null ? var16_11.object : null;
        var15_3.setPrimaryItem(this, var1_1, var14_10);
        this.mAdapter.finishUpdate(this);
        var5_2 = this.getChildCount();
        for (var1_1 = 0; var1_1 < var5_2; ++var1_1) {
            var15_3 = this.getChildAt(var1_1);
            var14_10 = (LayoutParams)var15_3.getLayoutParams();
            var14_10.childIndex = var1_1;
            if (var14_10.isDecor || var14_10.widthFactor != 0.0f || (var15_3 = this.infoForChild((View)var15_3)) == null) continue;
            var14_10.widthFactor = var15_3.widthFactor;
            var14_10.position = var15_3.position;
        }
        this.sortChildDrawingOrder();
        if (this.hasFocus()) {
            var14_10 = this.findFocus();
            var14_10 = var14_10 != null ? this.infoForAnyChild((View)var14_10) : null;
            if (var14_10 == null || var14_10.position != this.mCurItem) {
                for (var1_1 = 0; !(var1_1 >= this.getChildCount() || (var15_3 = this.infoForChild((View)(var14_10 = this.getChildAt(var1_1)))) != null && var15_3.position == this.mCurItem && var14_10.requestFocus(var6_4)); ++var1_1) {
                }
            }
        }
        if (FlipMode.FLIP_MODE_OVERLAY != this.mFlipMode) return;
        this.initItemIndex();
    }

    public void removeOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        if (this.mOnPageChangeListeners != null) {
            this.mOnPageChangeListeners.remove(onPageChangeListener);
        }
    }

    public void removeView(View view) {
        if (this.mInLayout) {
            this.removeViewInLayout(view);
            return;
        }
        super.removeView(view);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setAdapter(PagerAdapter pagerAdapter) {
        Object object;
        if (this.mAdapter != null) {
            this.mAdapter.unregisterDataSetObserver(this.mObserver);
            this.mAdapter.startUpdate(this);
            for (int i2 = 0; i2 < this.mItems.size(); ++i2) {
                object = (ItemInfo)this.mItems.get(i2);
                this.mAdapter.destroyItem(this, object.position, object.object);
            }
            this.mAdapter.finishUpdate(this);
            this.mItems.clear();
            this.removeNonDecorViews();
            this.mCurItem = 0;
            this.scrollTo(0, 0);
        }
        object = this.mAdapter;
        this.mAdapter = pagerAdapter;
        this.mExpectedAdapterCount = 0;
        if (this.mAdapter != null) {
            if (this.mObserver == null) {
                this.mObserver = new PagerObserver(null);
            }
            this.mAdapter.registerDataSetObserver(this.mObserver);
            this.mPopulatePending = false;
            boolean bl2 = this.mFirstLayout;
            this.mFirstLayout = true;
            this.mExpectedAdapterCount = this.mAdapter.getCount();
            if (this.mRestoredCurItem >= 0) {
                this.mAdapter.restoreState(this.mRestoredAdapterState, this.mRestoredClassLoader);
                this.setCurrentItemInternal(this.mRestoredCurItem, false, true);
                this.mRestoredCurItem = -1;
                this.mRestoredAdapterState = null;
                this.mRestoredClassLoader = null;
            } else if (!bl2) {
                this.populate();
            } else {
                this.requestLayout();
            }
        }
        if (this.mAdapterChangeListener != null && object != pagerAdapter) {
            this.mAdapterChangeListener.onAdapterChanged((PagerAdapter)object, pagerAdapter);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    void setChildrenDrawingOrderEnabledCompat(boolean bl2) {
        if (Build.VERSION.SDK_INT < 7) return;
        if (this.mSetChildrenDrawingOrderEnabled == null) {
            try {
                this.mSetChildrenDrawingOrderEnabled = ViewGroup.class.getDeclaredMethod("setChildrenDrawingOrderEnabled", new Class[]{Boolean.TYPE});
            }
            catch (NoSuchMethodException var2_2) {
                Log.e((String)"ViewPager", (String)"Can't find setChildrenDrawingOrderEnabled", (Throwable)var2_2);
            }
        }
        try {
            this.mSetChildrenDrawingOrderEnabled.setAccessible(true);
            this.mSetChildrenDrawingOrderEnabled.invoke((Object)this, new Object[]{bl2});
            return;
        }
        catch (Exception var2_3) {
            Log.e((String)"ViewPager", (String)"Error changing children drawing order", (Throwable)var2_3);
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setCurrentItem(int n2) {
        this.mPopulatePending = false;
        boolean bl2 = !this.mFirstLayout;
        this.setCurrentItemInternal(n2, bl2, false);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setCurrentItem(int n2, int n3) {
        if (this.mLastInterpolator != null) {
            this.setInterpolator(this.mLastInterpolator);
            this.mLastInterpolator = null;
        }
        if (this.mCurInterpolator == sInterpolator) {
            this.setInterpolator(sAutoScrollInterpolator);
        }
        this.mPopulatePending = false;
        if (this.mFirstLayout || n3 <= 0) {
            this.setCurrentItemInternal(n2, false, false);
        }
        if (this.mAdapter == null || this.mAdapter.getCount() <= 0) {
            this.setScrollingCacheEnabled(false);
            return;
        } else {
            int n4;
            int n5;
            if (this.mCurItem == n2 && this.mItems.size() != 0) {
                this.setScrollingCacheEnabled(false);
                return;
            }
            if (n2 < 0) {
                n5 = 0;
            } else {
                n5 = n2;
                if (n2 >= this.mAdapter.getCount()) {
                    n5 = this.mAdapter.getCount() - 1;
                }
            }
            if (n5 > this.mCurItem + (n2 = this.mOffscreenPageLimit) || n5 < this.mCurItem - n2) {
                for (n2 = 0; n2 < this.mItems.size(); ++n2) {
                    ((ItemInfo)this.mItems.get((int)n2)).scrolling = true;
                }
            }
            n2 = this.mCurItem != n5 ? 1 : 0;
            this.populate(n5);
            ItemInfo itemInfo = this.infoForPosition(n5);
            if (itemInfo != null) {
                float f2 = this.getClientWidth();
                n4 = (int)(Math.max((float)this.mFirstOffset, (float)Math.min((float)itemInfo.offset, (float)this.mLastOffset)) * f2);
            } else {
                n4 = 0;
            }
            if (this.getChildCount() == 0) {
                this.setScrollingCacheEnabled(false);
                return;
            }
            int n6 = this.getScrollX();
            int n7 = this.getScrollY();
            int n8 = 0 - n7;
            if ((n4 -= n6) == 0 && n8 == 0) {
                this.completeScroll(false);
                this.populate();
                this.setScrollState(0);
                return;
            }
            this.setScrollingCacheEnabled(true);
            this.setScrollState(2);
            n3 = Math.max((int)1, (int)Math.min((int)n3, (int)5000));
            if (FlipMode.FLIP_MODE_OVERLAY == this.mFlipMode) {
                this.setInterpolator(overlayModeInterpolator);
            }
            this.mScroller.startScroll(n6, n7, n4, n8, n3);
            ViewCompat.postInvalidateOnAnimation((View)this);
            if (n2 != 0 && this.mOnPageChangeListener != null) {
                this.mOnPageChangeListener.onPageSelected(n5);
            }
            if (n2 == 0 || this.mInternalPageChangeListener == null) return;
            {
                this.mInternalPageChangeListener.onPageSelected(n5);
                return;
            }
        }
    }

    public void setCurrentItem(int n2, boolean bl2) {
        this.mPopulatePending = false;
        this.setCurrentItemInternal(n2, bl2, false);
    }

    void setCurrentItemInternal(int n2, boolean bl2, boolean bl3) {
        this.setCurrentItemInternal(n2, bl2, bl3, 0);
    }

    /*
     * Enabled aggressive block sorting
     */
    void setCurrentItemInternal(int n2, boolean bl2, boolean bl3, int n3) {
        int n4;
        boolean bl4 = false;
        if (this.mAdapter == null || this.mAdapter.getCount() <= 0) {
            this.setScrollingCacheEnabled(false);
            return;
        }
        if (!bl3 && this.mCurItem == n2 && this.mItems.size() != 0) {
            this.setScrollingCacheEnabled(false);
            return;
        }
        if (n2 < 0) {
            n4 = 0;
        } else {
            n4 = n2;
            if (n2 >= this.mAdapter.getCount()) {
                n4 = this.mAdapter.getCount() - 1;
            }
        }
        if (n4 > this.mCurItem + (n2 = this.mOffscreenPageLimit) || n4 < this.mCurItem - n2) {
            for (n2 = 0; n2 < this.mItems.size(); ++n2) {
                ((ItemInfo)this.mItems.get((int)n2)).scrolling = true;
            }
        }
        bl3 = bl4;
        if (this.mCurItem != n4) {
            bl3 = true;
        }
        if (!this.mFirstLayout) {
            this.populate(n4);
            this.scrollToItem(n4, bl2, n3, bl3);
            return;
        }
        this.mCurItem = n4;
        if (bl3) {
            this.dispatchOnPageSelected(n4);
        }
        this.requestLayout();
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setFlipMode(FlipMode flipMode) {
        if (this.mFlipMode != flipMode) {
            this.mFlipMode = flipMode;
            if (FlipMode.FLIP_MODE_OVERLAY == this.mFlipMode) {
                this.mPageMargin = 0;
                this.initItemIndex();
                this.setChildrenDrawingOrderEnabledCompat(true);
            } else {
                this.setChildrenDrawingOrderEnabledCompat(false);
            }
            this.sortChildDrawingOrder();
            this.requestLayout();
        }
    }

    OnPageChangeListener setInternalPageChangeListener(OnPageChangeListener onPageChangeListener) {
        OnPageChangeListener onPageChangeListener2 = this.mInternalPageChangeListener;
        this.mInternalPageChangeListener = onPageChangeListener;
        return onPageChangeListener2;
    }

    public void setInterpolator(Interpolator interpolator2) {
        if (this.mScroller != null && !this.mScroller.isFinished()) {
            this.mScroller.forceFinished(true);
        }
        if (interpolator2 != null && this.mCurInterpolator != interpolator2) {
            this.mCurInterpolator = interpolator2;
            this.mScroller = new Scroller(this.mContext, interpolator2);
        }
    }

    public void setOffscreenPageLimit(int n2) {
        int n3 = n2;
        if (n2 < 1) {
            Log.w((String)"ViewPager", (String)("Requested offscreen page limit " + n2 + " too small; defaulting to " + 1));
            n3 = 1;
        }
        if (n3 != this.mOffscreenPageLimit) {
            this.mOffscreenPageLimit = n3;
            this.populate();
        }
    }

    void setOnAdapterChangeListener(OnAdapterChangeListener onAdapterChangeListener) {
        this.mAdapterChangeListener = onAdapterChangeListener;
    }

    @Deprecated
    public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        this.mOnPageChangeListener = onPageChangeListener;
    }

    public void setPageMargin(int n2) {
        if (FlipMode.FLIP_MODE_OVERLAY == this.mFlipMode) {
            return;
        }
        int n3 = this.mPageMargin;
        this.mPageMargin = n2;
        int n4 = this.getWidth();
        this.recomputeScrollPosition(n4, n4, n2, n3);
        this.requestLayout();
    }

    public void setPageMarginDrawable(int n2) {
        this.setPageMarginDrawable(this.getContext().getResources().getDrawable(n2));
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setPageMarginDrawable(Drawable drawable2) {
        this.mMarginDrawable = drawable2;
        if (drawable2 != null) {
            this.refreshDrawableState();
        }
        boolean bl2 = drawable2 == null;
        this.setWillNotDraw(bl2);
        this.invalidate();
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setPageTransformer(boolean bl2, PageTransformer pageTransformer) {
        int n2 = 1;
        if (Build.VERSION.SDK_INT < 11) return;
        boolean bl3 = pageTransformer != null;
        boolean bl4 = this.mPageTransformer != null;
        boolean bl5 = bl3 != bl4;
        this.mPageTransformer = pageTransformer;
        this.setChildrenDrawingOrderEnabledCompat(bl3);
        if (bl3) {
            if (bl2) {
                n2 = 2;
            }
            this.mDrawingOrder = n2;
        } else {
            this.mDrawingOrder = 0;
        }
        if (bl5) {
            this.populate();
        }
    }

    public void setRectSlopScaleInTab(int n2, int n3, int n4, int n5, float f2, int n6) {
        this.mSpecRect = new Rect();
        this.mSpecRect.left = n2;
        this.mSpecRect.top = n3;
        this.mSpecRect.right = n4;
        this.mSpecRect.bottom = n5;
        this.mTouchSlopAdjust = (int)((float)this.mTouchSlop * f2);
        this.mSpecTab = n6;
        Log.d((String)"ViewPager", (String)("setRectSlopScaleInTab mSpecRect = " + (Object)this.mSpecRect + ", coef = " + f2 + ", specTab = " + this.mSpecTab));
    }

    public void setShadow(Drawable drawable2, Drawable drawable3) {
        if (drawable2 != this.mShadowDrawable) {
            this.mShadowDrawable = drawable2;
            this.mNeedInitShadow = true;
        }
        if (drawable3 != this.mCoverDrawable) {
            this.mCoverDrawable = drawable3;
            this.mNeedInitShadow = true;
        }
    }

    public void setShadowResource(int n2, int n3) {
        if (n2 != 0) {
            this.mShadowDrawable = this.getContext().getResources().getDrawable(n2);
            this.mNeedInitShadow = true;
        }
        if (n3 != 0) {
            this.mCoverDrawable = this.getContext().getResources().getDrawable(n3);
            this.mNeedInitShadow = true;
        }
    }

    void smoothScrollTo(int n2, int n3) {
        this.smoothScrollTo(n2, n3, 0);
    }

    /*
     * Enabled aggressive block sorting
     */
    void smoothScrollTo(int n2, int n3, int n4) {
        if (this.getChildCount() == 0) {
            this.setScrollingCacheEnabled(false);
            return;
        }
        int n5 = this.getScrollX();
        int n6 = this.getScrollY();
        int n7 = n2 - n5;
        if (n7 == 0 && (n3 -= n6) == 0) {
            this.completeScroll(false);
            this.populate();
            this.setScrollState(0);
            return;
        }
        this.setScrollingCacheEnabled(true);
        this.setScrollState(2);
        n2 = this.getClientWidth();
        int n8 = n2 / 2;
        float f2 = Math.min((float)1.0f, (float)((float)Math.abs((int)n7) * 1.0f / (float)n2));
        float f3 = n8;
        float f4 = n8;
        f2 = this.distanceInfluenceForSnapDuration(f2);
        n4 = Math.abs((int)n4);
        if (n4 > 0) {
            n2 = Math.round((float)(1000.0f * Math.abs((float)((f4 * f2 + f3) / (float)n4)))) * 4;
        } else {
            f3 = n2;
            f4 = this.mAdapter.getPageWidth(this.mCurItem);
            f3 = (float)Math.abs((int)n7) / (f3 * f4 + (float)this.mPageMargin);
            n2 = FlipMode.FLIP_MODE_OVERLAY == this.mFlipMode && f3 >= 1.0f ? (int)((f3 + 4.0f) * 100.0f) : (int)((f3 + 1.0f) * 100.0f);
        }
        n2 = Math.min((int)n2, (int)600);
        this.mScroller.startScroll(n5, n6, n7, n3, n2);
        ViewCompat.postInvalidateOnAnimation((View)this);
    }

    protected boolean verifyDrawable(Drawable drawable2) {
        if (super.verifyDrawable(drawable2) || drawable2 == this.mMarginDrawable) {
            return true;
        }
        return false;
    }

    static interface Decor {
    }

    public static enum FlipMode {
        FLIP_MODE_DEFAULT,
        FLIP_MODE_OVERLAY;
        

        private FlipMode() {
        }
    }

    static class ItemInfo {
        int index;
        Object object;
        float offset;
        int position;
        boolean scrolling;
        View view;
        float widthFactor;

        ItemInfo() {
        }
    }

    public static class LayoutParams
    extends ViewGroup.LayoutParams {
        int childIndex;
        public int gravity;
        public boolean isDecor;
        boolean needsMeasure;
        int position;
        float widthFactor = 0.0f;

        public LayoutParams() {
            super(-1, -1);
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            context = context.obtainStyledAttributes(attributeSet, LAYOUT_ATTRS);
            this.gravity = context.getInteger(0, 48);
            context.recycle();
        }
    }

    class MyAccessibilityDelegate
    extends AccessibilityDelegateCompat {
        MyAccessibilityDelegate() {
        }

        private boolean canScroll() {
            if (ViewPager.this.mAdapter != null && ViewPager.this.mAdapter.getCount() > 1) {
                return true;
            }
            return false;
        }

        @Override
        public void onInitializeAccessibilityEvent(View object, AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent((View)object, accessibilityEvent);
            accessibilityEvent.setClassName((CharSequence)ViewPager.class.getName());
            object = AccessibilityRecordCompat.obtain();
            object.setScrollable(this.canScroll());
            if (accessibilityEvent.getEventType() == 4096 && ViewPager.this.mAdapter != null) {
                object.setItemCount(ViewPager.this.mAdapter.getCount());
                object.setFromIndex(ViewPager.this.mCurItem);
                object.setToIndex(ViewPager.this.mCurItem);
            }
        }

        @Override
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            accessibilityNodeInfoCompat.setClassName(ViewPager.class.getName());
            accessibilityNodeInfoCompat.setScrollable(this.canScroll());
            if (ViewPager.this.canScrollHorizontally(1)) {
                accessibilityNodeInfoCompat.addAction(4096);
            }
            if (ViewPager.this.canScrollHorizontally(-1)) {
                accessibilityNodeInfoCompat.addAction(8192);
            }
        }

        @Override
        public boolean performAccessibilityAction(View view, int n2, Bundle bundle) {
            if (super.performAccessibilityAction(view, n2, bundle)) {
                return true;
            }
            switch (n2) {
                default: {
                    return false;
                }
                case 4096: {
                    if (ViewPager.this.canScrollHorizontally(1)) {
                        ViewPager.this.setCurrentItem(ViewPager.this.mCurItem + 1);
                        return true;
                    }
                    return false;
                }
                case 8192: 
            }
            if (ViewPager.this.canScrollHorizontally(-1)) {
                ViewPager.this.setCurrentItem(ViewPager.this.mCurItem - 1);
                return true;
            }
            return false;
        }
    }

    static interface OnAdapterChangeListener {
        public void onAdapterChanged(PagerAdapter var1, PagerAdapter var2);
    }

    public static interface OnPageChangeListener {
        public void onPageScrollStateChanged(int var1);

        public void onPageScrolled(int var1, float var2, int var3);

        public void onPageSelected(int var1);
    }

    public static interface PageTransformer {
        public void transformPage(View var1, float var2);
    }

    class PagerObserver
    extends DataSetObserver {
        private PagerObserver() {
        }

        /* synthetic */ PagerObserver(ViewPager$1 viewPager$1) {
            this();
        }

        public void onChanged() {
            ViewPager.this.dataSetChanged();
        }

        public void onInvalidated() {
            ViewPager.this.dataSetChanged();
        }
    }

    public static class SavedState
    extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = ParcelableCompat.newCreator(new ViewPager$SavedState$1());
        Parcelable adapterState;
        ClassLoader loader;
        int position;

        SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel);
            ClassLoader classLoader2 = classLoader;
            if (classLoader == null) {
                classLoader2 = this.getClass().getClassLoader();
            }
            this.position = parcel.readInt();
            this.adapterState = parcel.readParcelable(classLoader2);
            this.loader = classLoader2;
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "FragmentPager.SavedState{" + Integer.toHexString((int)System.identityHashCode((Object)((Object)this))) + " position=" + this.position + "}";
        }

        public void writeToParcel(Parcel parcel, int n2) {
            super.writeToParcel(parcel, n2);
            parcel.writeInt(this.position);
            parcel.writeParcelable(this.adapterState, n2);
        }
    }

    public static class SimpleOnPageChangeListener
    implements OnPageChangeListener {
        @Override
        public void onPageScrollStateChanged(int n2) {
        }

        @Override
        public void onPageScrolled(int n2, float f2, int n3) {
        }

        @Override
        public void onPageSelected(int n2) {
        }
    }

    static class ViewPositionComparator
    implements Comparator<View> {
        ViewPositionComparator() {
        }

        public int compare(View object, View object2) {
            object = (LayoutParams)object.getLayoutParams();
            object2 = (LayoutParams)object2.getLayoutParams();
            if (object.isDecor != object2.isDecor) {
                if (object.isDecor) {
                    return 1;
                }
                return -1;
            }
            return object.position - object2.position;
        }
    }

    static class ViewReversePositionComparator
    implements Comparator<View> {
        ViewReversePositionComparator() {
        }

        public int compare(View object, View object2) {
            object = (LayoutParams)object.getLayoutParams();
            object2 = (LayoutParams)object2.getLayoutParams();
            if (object.isDecor != object2.isDecor) {
                if (object.isDecor) {
                    return 1;
                }
                return -1;
            }
            return object2.position - object.position;
        }
    }

}

