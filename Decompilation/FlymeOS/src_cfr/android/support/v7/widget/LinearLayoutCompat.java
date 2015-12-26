/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.TypedArray
 *  android.graphics.Canvas
 *  android.graphics.drawable.Drawable
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.View$MeasureSpec
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewGroup$MarginLayoutParams
 *  android.view.accessibility.AccessibilityEvent
 *  android.view.accessibility.AccessibilityNodeInfo
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.IntDef;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R;
import android.support.v7.internal.widget.TintTypedArray;
import android.support.v7.internal.widget.ViewUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class LinearLayoutCompat
extends ViewGroup {
    public static final int HORIZONTAL = 0;
    private static final int INDEX_BOTTOM = 2;
    private static final int INDEX_CENTER_VERTICAL = 0;
    private static final int INDEX_FILL = 3;
    private static final int INDEX_TOP = 1;
    public static final int SHOW_DIVIDER_BEGINNING = 1;
    public static final int SHOW_DIVIDER_END = 4;
    public static final int SHOW_DIVIDER_MIDDLE = 2;
    public static final int SHOW_DIVIDER_NONE = 0;
    public static final int VERTICAL = 1;
    private static final int VERTICAL_GRAVITY_COUNT = 4;
    private boolean mBaselineAligned = true;
    private int mBaselineAlignedChildIndex = -1;
    private int mBaselineChildTop = 0;
    private Drawable mDivider;
    private int mDividerHeight;
    private int mDividerPadding;
    private int mDividerWidth;
    private int mGravity = 8388659;
    private int[] mMaxAscent;
    private int[] mMaxDescent;
    private int mOrientation;
    private int mShowDividers;
    private int mTotalLength;
    private boolean mUseLargestChild;
    private float mWeightSum;

    public LinearLayoutCompat(Context context) {
        this(context, null);
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LinearLayoutCompat(Context object, AttributeSet attributeSet, int n2) {
        boolean bl2;
        super((Context)object, attributeSet, n2);
        object = TintTypedArray.obtainStyledAttributes((Context)object, attributeSet, R.styleable.LinearLayoutCompat, n2, 0);
        n2 = object.getInt(R.styleable.LinearLayoutCompat_android_orientation, -1);
        if (n2 >= 0) {
            this.setOrientation(n2);
        }
        if ((n2 = object.getInt(R.styleable.LinearLayoutCompat_android_gravity, -1)) >= 0) {
            this.setGravity(n2);
        }
        if (!(bl2 = object.getBoolean(R.styleable.LinearLayoutCompat_android_baselineAligned, true))) {
            this.setBaselineAligned(bl2);
        }
        this.mWeightSum = object.getFloat(R.styleable.LinearLayoutCompat_android_weightSum, -1.0f);
        this.mBaselineAlignedChildIndex = object.getInt(R.styleable.LinearLayoutCompat_android_baselineAlignedChildIndex, -1);
        this.mUseLargestChild = object.getBoolean(R.styleable.LinearLayoutCompat_measureWithLargestChild, false);
        this.setDividerDrawable(object.getDrawable(R.styleable.LinearLayoutCompat_divider));
        this.mShowDividers = object.getInt(R.styleable.LinearLayoutCompat_showDividers, 0);
        this.mDividerPadding = object.getDimensionPixelSize(R.styleable.LinearLayoutCompat_dividerPadding, 0);
        object.recycle();
    }

    private void forceUniformHeight(int n2, int n3) {
        int n4 = View.MeasureSpec.makeMeasureSpec((int)this.getMeasuredHeight(), (int)1073741824);
        for (int i2 = 0; i2 < n2; ++i2) {
            View view = this.getVirtualChildAt(i2);
            if (view.getVisibility() == 8) continue;
            LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
            if (layoutParams.height != -1) continue;
            int n5 = layoutParams.width;
            layoutParams.width = view.getMeasuredWidth();
            this.measureChildWithMargins(view, n3, 0, n4, 0);
            layoutParams.width = n5;
        }
    }

    private void forceUniformWidth(int n2, int n3) {
        int n4 = View.MeasureSpec.makeMeasureSpec((int)this.getMeasuredWidth(), (int)1073741824);
        for (int i2 = 0; i2 < n2; ++i2) {
            View view = this.getVirtualChildAt(i2);
            if (view.getVisibility() == 8) continue;
            LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
            if (layoutParams.width != -1) continue;
            int n5 = layoutParams.height;
            layoutParams.height = view.getMeasuredHeight();
            this.measureChildWithMargins(view, n4, 0, n3, 0);
            layoutParams.height = n5;
        }
    }

    private void setChildFrame(View view, int n2, int n3, int n4, int n5) {
        view.layout(n2, n3, n2 + n4, n3 + n5);
    }

    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    /*
     * Enabled aggressive block sorting
     */
    void drawDividersHorizontal(Canvas canvas) {
        View view;
        int n2;
        LayoutParams layoutParams;
        int n3 = this.getVirtualChildCount();
        boolean bl2 = ViewUtils.isLayoutRtl((View)this);
        for (n2 = 0; n2 < n3; ++n2) {
            int n4;
            view = this.getVirtualChildAt(n2);
            if (view == null || view.getVisibility() == 8 || !this.hasDividerBeforeChildAt(n2)) continue;
            layoutParams = (LayoutParams)view.getLayoutParams();
            if (bl2) {
                n4 = view.getRight();
                n4 = layoutParams.rightMargin + n4;
            } else {
                n4 = view.getLeft() - layoutParams.leftMargin - this.mDividerWidth;
            }
            this.drawVerticalDivider(canvas, n4);
        }
        if (this.hasDividerBeforeChildAt(n3)) {
            view = this.getVirtualChildAt(n3 - 1);
            if (view == null) {
                n2 = bl2 ? this.getPaddingLeft() : this.getWidth() - this.getPaddingRight() - this.mDividerWidth;
            } else {
                layoutParams = (LayoutParams)view.getLayoutParams();
                if (bl2) {
                    n2 = view.getLeft() - layoutParams.leftMargin - this.mDividerWidth;
                } else {
                    n2 = view.getRight();
                    n2 = layoutParams.rightMargin + n2;
                }
            }
            this.drawVerticalDivider(canvas, n2);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    void drawDividersVertical(Canvas canvas) {
        int n2;
        View view;
        LayoutParams layoutParams;
        int n3 = this.getVirtualChildCount();
        for (n2 = 0; n2 < n3; ++n2) {
            view = this.getVirtualChildAt(n2);
            if (view == null || view.getVisibility() == 8 || !this.hasDividerBeforeChildAt(n2)) continue;
            layoutParams = (LayoutParams)view.getLayoutParams();
            this.drawHorizontalDivider(canvas, view.getTop() - layoutParams.topMargin - this.mDividerHeight);
        }
        if (this.hasDividerBeforeChildAt(n3)) {
            view = this.getVirtualChildAt(n3 - 1);
            if (view == null) {
                n2 = this.getHeight() - this.getPaddingBottom() - this.mDividerHeight;
            } else {
                layoutParams = (LayoutParams)view.getLayoutParams();
                n2 = view.getBottom();
                n2 = layoutParams.bottomMargin + n2;
            }
            this.drawHorizontalDivider(canvas, n2);
        }
    }

    void drawHorizontalDivider(Canvas canvas, int n2) {
        this.mDivider.setBounds(this.getPaddingLeft() + this.mDividerPadding, n2, this.getWidth() - this.getPaddingRight() - this.mDividerPadding, this.mDividerHeight + n2);
        this.mDivider.draw(canvas);
    }

    void drawVerticalDivider(Canvas canvas, int n2) {
        this.mDivider.setBounds(n2, this.getPaddingTop() + this.mDividerPadding, this.mDividerWidth + n2, this.getHeight() - this.getPaddingBottom() - this.mDividerPadding);
        this.mDivider.draw(canvas);
    }

    protected LayoutParams generateDefaultLayoutParams() {
        if (this.mOrientation == 0) {
            return new LayoutParams(-2, -2);
        }
        if (this.mOrientation == 1) {
            return new LayoutParams(-1, -2);
        }
        return null;
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(this.getContext(), attributeSet);
    }

    protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    /*
     * Enabled aggressive block sorting
     */
    public int getBaseline() {
        int n2 = -1;
        if (this.mBaselineAlignedChildIndex < 0) {
            return super.getBaseline();
        }
        if (this.getChildCount() <= this.mBaselineAlignedChildIndex) {
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
        }
        View view = this.getChildAt(this.mBaselineAlignedChildIndex);
        int n3 = view.getBaseline();
        if (n3 == -1) {
            if (this.mBaselineAlignedChildIndex == 0) return n2;
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
        }
        n2 = this.mBaselineChildTop;
        if (this.mOrientation != 1) return ((LayoutParams)view.getLayoutParams()).topMargin + n2 + n3;
        int n4 = this.mGravity & 112;
        if (n4 == 48) return ((LayoutParams)view.getLayoutParams()).topMargin + n2 + n3;
        switch (n4) {
            case 80: {
                n2 = this.getBottom() - this.getTop() - this.getPaddingBottom() - this.mTotalLength;
            }
            default: {
                return ((LayoutParams)view.getLayoutParams()).topMargin + n2 + n3;
            }
            case 16: 
        }
        return ((LayoutParams)view.getLayoutParams()).topMargin + (n2 += (this.getBottom() - this.getTop() - this.getPaddingTop() - this.getPaddingBottom() - this.mTotalLength) / 2) + n3;
    }

    public int getBaselineAlignedChildIndex() {
        return this.mBaselineAlignedChildIndex;
    }

    int getChildrenSkipCount(View view, int n2) {
        return 0;
    }

    public Drawable getDividerDrawable() {
        return this.mDivider;
    }

    public int getDividerPadding() {
        return this.mDividerPadding;
    }

    public int getDividerWidth() {
        return this.mDividerWidth;
    }

    int getLocationOffset(View view) {
        return 0;
    }

    int getNextLocationOffset(View view) {
        return 0;
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public int getShowDividers() {
        return this.mShowDividers;
    }

    View getVirtualChildAt(int n2) {
        return this.getChildAt(n2);
    }

    int getVirtualChildCount() {
        return this.getChildCount();
    }

    public float getWeightSum() {
        return this.mWeightSum;
    }

    /*
     * Enabled aggressive block sorting
     */
    protected boolean hasDividerBeforeChildAt(int n2) {
        if (n2 == 0) {
            if ((this.mShowDividers & 1) != 0) return true;
            return false;
        }
        if (n2 == this.getChildCount()) {
            if ((this.mShowDividers & 4) != 0) return true;
            return false;
        }
        if ((this.mShowDividers & 2) == 0) {
            return false;
        }
        --n2;
        while (n2 >= 0) {
            if (this.getChildAt(n2).getVisibility() != 8) {
                return true;
            }
            --n2;
        }
        return false;
    }

    public boolean isBaselineAligned() {
        return this.mBaselineAligned;
    }

    public boolean isMeasureWithLargestChildEnabled() {
        return this.mUseLargestChild;
    }

    /*
     * Enabled aggressive block sorting
     */
    void layoutHorizontal(int n2, int n3, int n4, int n5) {
        int n6;
        boolean bl2 = ViewUtils.isLayoutRtl((View)this);
        int n7 = this.getPaddingTop();
        int n8 = n5 - n3;
        int n9 = this.getPaddingBottom();
        int n10 = this.getPaddingBottom();
        int n11 = this.getVirtualChildCount();
        n3 = this.mGravity;
        int n12 = this.mGravity;
        boolean bl3 = this.mBaselineAligned;
        int[] arrn = this.mMaxAscent;
        int[] arrn2 = this.mMaxDescent;
        switch (GravityCompat.getAbsoluteGravity(n3 & 8388615, ViewCompat.getLayoutDirection((View)this))) {
            default: {
                n2 = this.getPaddingLeft();
                break;
            }
            case 5: {
                n2 = this.getPaddingLeft() + n4 - n2 - this.mTotalLength;
                break;
            }
            case 1: {
                n2 = this.getPaddingLeft() + (n4 - n2 - this.mTotalLength) / 2;
            }
        }
        if (bl2) {
            n5 = -1;
            n6 = n11 - 1;
        } else {
            n5 = 1;
            n6 = 0;
        }
        n3 = 0;
        n4 = n2;
        while (n3 < n11) {
            int n13 = n6 + n5 * n3;
            View view = this.getVirtualChildAt(n13);
            if (view == null) {
                n4 += this.measureNullChild(n13);
                n2 = n3;
            } else if (view.getVisibility() != 8) {
                int n14;
                int n15 = view.getMeasuredWidth();
                int n16 = view.getMeasuredHeight();
                LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
                int n17 = bl3 && layoutParams.height != -1 ? view.getBaseline() : -1;
                n2 = n14 = layoutParams.gravity;
                if (n14 < 0) {
                    n2 = n12 & 112;
                }
                switch (n2 & 112) {
                    default: {
                        n2 = n7;
                        break;
                    }
                    case 48: {
                        n2 = n14 = n7 + layoutParams.topMargin;
                        if (n17 == -1) break;
                        n2 = n14 + (arrn[1] - n17);
                        break;
                    }
                    case 16: {
                        n2 = (n8 - n7 - n10 - n16) / 2 + n7 + layoutParams.topMargin - layoutParams.bottomMargin;
                        break;
                    }
                    case 80: {
                        n2 = n14 = n8 - n9 - n16 - layoutParams.bottomMargin;
                        if (n17 == -1) break;
                        n2 = view.getMeasuredHeight();
                        n2 = n14 - (arrn2[2] - (n2 - n17));
                    }
                }
                if (this.hasDividerBeforeChildAt(n13)) {
                    n4 = this.mDividerWidth + n4;
                }
                this.setChildFrame(view, (n4 += layoutParams.leftMargin) + this.getLocationOffset(view), n2, n15, n16);
                n4 += layoutParams.rightMargin + n15 + this.getNextLocationOffset(view);
                n2 = this.getChildrenSkipCount(view, n13) + n3;
            } else {
                n2 = n3;
            }
            n3 = n2 + 1;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    void layoutVertical(int n2, int n3, int n4, int n5) {
        int n6 = this.getPaddingLeft();
        int n7 = n4 - n2;
        int n8 = this.getPaddingRight();
        int n9 = this.getPaddingRight();
        int n10 = this.getVirtualChildCount();
        n2 = this.mGravity;
        int n11 = this.mGravity;
        switch (n2 & 112) {
            default: {
                n2 = this.getPaddingTop();
                break;
            }
            case 80: {
                n2 = this.getPaddingTop() + n5 - n3 - this.mTotalLength;
                break;
            }
            case 16: {
                n2 = this.getPaddingTop() + (n5 - n3 - this.mTotalLength) / 2;
            }
        }
        n4 = 0;
        n3 = n2;
        for (n2 = n4; n2 < n10; ++n2) {
            View view = this.getVirtualChildAt(n2);
            if (view == null) {
                n3 += this.measureNullChild(n2);
                continue;
            }
            if (view.getVisibility() == 8) continue;
            int n12 = view.getMeasuredWidth();
            int n13 = view.getMeasuredHeight();
            LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
            n4 = n5 = layoutParams.gravity;
            if (n5 < 0) {
                n4 = n11 & 8388615;
            }
            switch (GravityCompat.getAbsoluteGravity(n4, ViewCompat.getLayoutDirection((View)this)) & 7) {
                default: {
                    n4 = n6 + layoutParams.leftMargin;
                    break;
                }
                case 1: {
                    n4 = (n7 - n6 - n9 - n12) / 2 + n6 + layoutParams.leftMargin - layoutParams.rightMargin;
                    break;
                }
                case 5: {
                    n4 = n7 - n8 - n12 - layoutParams.rightMargin;
                }
            }
            if (this.hasDividerBeforeChildAt(n2)) {
                n3 = this.mDividerHeight + n3;
            }
            this.setChildFrame(view, n4, (n3 += layoutParams.topMargin) + this.getLocationOffset(view), n12, n13);
            n3 += layoutParams.bottomMargin + n13 + this.getNextLocationOffset(view);
            n2 = this.getChildrenSkipCount(view, n2) + n2;
        }
    }

    void measureChildBeforeLayout(View view, int n2, int n3, int n4, int n5, int n6) {
        this.measureChildWithMargins(view, n3, n4, n5, n6);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    void measureHorizontal(int var1_1, int var2_2) {
        this.mTotalLength = 0;
        var7_3 = 0;
        var6_4 = 0;
        var10_5 = 0;
        var12_6 = 0;
        var5_7 = 1;
        var3_8 = 0.0f;
        var20_9 = this.getVirtualChildCount();
        var22_10 = View.MeasureSpec.getMode((int)var1_1);
        var21_11 = View.MeasureSpec.getMode((int)var2_2);
        var11_12 = 0;
        var9_13 = 0;
        if (this.mMaxAscent == null || this.mMaxDescent == null) {
            this.mMaxAscent = new int[4];
            this.mMaxDescent = new int[4];
        }
        var25_14 = this.mMaxAscent;
        var26_15 = this.mMaxDescent;
        var25_14[3] = -1;
        var25_14[2] = -1;
        var25_14[1] = -1;
        var25_14[0] = -1;
        var26_15[3] = -1;
        var26_15[2] = -1;
        var26_15[1] = -1;
        var26_15[0] = -1;
        var23_16 = this.mBaselineAligned;
        var24_17 = this.mUseLargestChild;
        var16_18 = var22_10 == 1073741824;
        var8_19 = Integer.MIN_VALUE;
        for (var13_20 = 0; var13_20 < var20_9; ++var13_20) {
            var27_26 = this.getVirtualChildAt(var13_20);
            if (var27_26 == null) {
                this.mTotalLength += this.measureNullChild(var13_20);
                var14_21 = var8_19;
                var15_22 = var9_13;
                var9_13 = var7_3;
                var8_19 = var6_4;
                var7_3 = var5_7;
                var6_4 = var15_22;
                var5_7 = var14_21;
            } else if (var27_26.getVisibility() == 8) {
                var17_23 = var13_20 + this.getChildrenSkipCount(var27_26, var13_20);
                var13_20 = var8_19;
                var15_22 = var9_13;
                var8_19 = var5_7;
                var9_13 = var6_4;
                var14_21 = var7_3;
                var5_7 = var13_20;
                var6_4 = var15_22;
                var13_20 = var17_23;
                var7_3 = var8_19;
                var8_19 = var9_13;
                var9_13 = var14_21;
            } else {
                if (this.hasDividerBeforeChildAt(var13_20)) {
                    this.mTotalLength += this.mDividerWidth;
                }
                var28_27 = (LayoutParams)var27_26.getLayoutParams();
                var3_8 += var28_27.weight;
                if (var22_10 == 1073741824 && var28_27.width == 0 && var28_27.weight > 0.0f) {
                    if (var16_18) {
                        this.mTotalLength += var28_27.leftMargin + var28_27.rightMargin;
                    } else {
                        var14_21 = this.mTotalLength;
                        this.mTotalLength = Math.max((int)var14_21, (int)(var28_27.leftMargin + var14_21 + var28_27.rightMargin));
                    }
                    if (var23_16) {
                        var14_21 = View.MeasureSpec.makeMeasureSpec((int)0, (int)0);
                        var27_26.measure(var14_21, var14_21);
                        var15_22 = var9_13;
                        var14_21 = var8_19;
                    } else {
                        var15_22 = 1;
                        var14_21 = var8_19;
                    }
                } else {
                    var14_21 = var15_22 = Integer.MIN_VALUE;
                    if (var28_27.width == 0) {
                        var14_21 = var15_22;
                        if (var28_27.weight > 0.0f) {
                            var14_21 = 0;
                            var28_27.width = -2;
                        }
                    }
                    var15_22 = var3_8 == 0.0f ? this.mTotalLength : 0;
                    this.measureChildBeforeLayout(var27_26, var13_20, var1_1, var15_22, var2_2, 0);
                    if (var14_21 != Integer.MIN_VALUE) {
                        var28_27.width = var14_21;
                    }
                    var17_23 = var27_26.getMeasuredWidth();
                    if (var16_18) {
                        this.mTotalLength += var28_27.leftMargin + var17_23 + var28_27.rightMargin + this.getNextLocationOffset(var27_26);
                    } else {
                        var14_21 = this.mTotalLength;
                        this.mTotalLength = Math.max((int)var14_21, (int)(var14_21 + var17_23 + var28_27.leftMargin + var28_27.rightMargin + this.getNextLocationOffset(var27_26)));
                    }
                    var14_21 = var8_19;
                    var15_22 = var9_13;
                    if (var24_17) {
                        var14_21 = Math.max((int)var17_23, (int)var8_19);
                        var15_22 = var9_13;
                    }
                }
                var9_13 = 0;
                if (var21_11 != 1073741824 && var28_27.height == -1) {
                    var8_19 = 1;
                    var9_13 = 1;
                } else {
                    var8_19 = var11_12;
                }
                var11_12 = var28_27.topMargin;
                var11_12 = var28_27.bottomMargin + var11_12;
                var17_23 = var27_26.getMeasuredHeight() + var11_12;
                var18_24 = ViewUtils.combineMeasuredStates(var6_4, ViewCompat.getMeasuredState(var27_26));
                if (var23_16 && (var19_25 = var27_26.getBaseline()) != -1) {
                    var6_4 = var28_27.gravity < 0 ? this.mGravity : var28_27.gravity;
                    var6_4 = ((var6_4 & 112) >> 4 & -2) >> 1;
                    var25_14[var6_4] = Math.max((int)var25_14[var6_4], (int)var19_25);
                    var26_15[var6_4] = Math.max((int)var26_15[var6_4], (int)(var17_23 - var19_25));
                }
                var19_25 = Math.max((int)var7_3, (int)var17_23);
                var7_3 = var5_7 != 0 && var28_27.height == -1 ? 1 : 0;
                if (var28_27.weight > 0.0f) {
                    if (var9_13 == 0) {
                        var11_12 = var17_23;
                    }
                    var6_4 = Math.max((int)var12_6, (int)var11_12);
                    var5_7 = var10_5;
                } else {
                    if (var9_13 == 0) {
                        var11_12 = var17_23;
                    }
                    var5_7 = Math.max((int)var10_5, (int)var11_12);
                    var6_4 = var12_6;
                }
                var13_20 += this.getChildrenSkipCount(var27_26, var13_20);
                var12_6 = var6_4;
                var10_5 = var5_7;
                var9_13 = var19_25;
                var5_7 = var14_21;
                var14_21 = var18_24;
                var11_12 = var8_19;
                var6_4 = var15_22;
                var8_19 = var14_21;
            }
            var14_21 = var8_19;
            var15_22 = var9_13;
            var8_19 = var5_7;
            var9_13 = var6_4;
            var5_7 = var7_3;
            var6_4 = var14_21;
            var7_3 = var15_22;
        }
        if (this.mTotalLength > 0 && this.hasDividerBeforeChildAt(var20_9)) {
            this.mTotalLength += this.mDividerWidth;
        }
        var13_20 = var25_14[1] != -1 || var25_14[0] != -1 || var25_14[2] != -1 || var25_14[3] != -1 ? Math.max((int)var7_3, (int)(Math.max((int)var25_14[3], (int)Math.max((int)var25_14[0], (int)Math.max((int)var25_14[1], (int)var25_14[2]))) + Math.max((int)var26_15[3], (int)Math.max((int)var26_15[0], (int)Math.max((int)var26_15[1], (int)var26_15[2]))))) : var7_3;
        if (var24_17 && (var22_10 == Integer.MIN_VALUE || var22_10 == 0)) {
            this.mTotalLength = 0;
            for (var7_3 = 0; var7_3 < var20_9; ++var7_3) {
                var27_26 = this.getVirtualChildAt(var7_3);
                if (var27_26 == null) {
                    this.mTotalLength += this.measureNullChild(var7_3);
                    continue;
                }
                if (var27_26.getVisibility() == 8) {
                    var7_3 = this.getChildrenSkipCount(var27_26, var7_3) + var7_3;
                    continue;
                }
                var28_27 = (LayoutParams)var27_26.getLayoutParams();
                if (var16_18) {
                    var14_21 = this.mTotalLength;
                    var15_22 = var28_27.leftMargin;
                    this.mTotalLength = var28_27.rightMargin + (var15_22 + var8_19) + this.getNextLocationOffset(var27_26) + var14_21;
                    continue;
                }
                var14_21 = this.mTotalLength;
                var15_22 = var28_27.leftMargin;
                this.mTotalLength = Math.max((int)var14_21, (int)(var28_27.rightMargin + (var14_21 + var8_19 + var15_22) + this.getNextLocationOffset(var27_26)));
            }
        }
        this.mTotalLength += this.getPaddingLeft() + this.getPaddingRight();
        var17_23 = ViewCompat.resolveSizeAndState(Math.max((int)this.mTotalLength, (int)this.getSuggestedMinimumWidth()), var1_1, 0);
        var7_3 = (16777215 & var17_23) - this.mTotalLength;
        if (var9_13 == 0 && (var7_3 == 0 || var3_8 <= 0.0f)) ** GOTO lbl170
        if (this.mWeightSum > 0.0f) {
            var3_8 = this.mWeightSum;
        }
        ** GOTO lbl173
lbl170: // 1 sources:
        var9_13 = Math.max((int)var10_5, (int)var12_6);
        if (!var24_17 || var22_10 == 1073741824) ** GOTO lbl274
        ** GOTO lbl269
lbl173: // 1 sources:
        var25_14[3] = -1;
        var25_14[2] = -1;
        var25_14[1] = -1;
        var25_14[0] = -1;
        var26_15[3] = -1;
        var26_15[2] = -1;
        var26_15[1] = -1;
        var26_15[0] = -1;
        this.mTotalLength = 0;
        var13_20 = 0;
        var12_6 = var10_5;
        var9_13 = var6_4;
        var8_19 = var7_3;
        var7_3 = -1;
        var10_5 = var13_20;
        var6_4 = var12_6;
        do {
            block48 : {
                if (var10_5 >= var20_9) ** GOTO lbl238
                var27_26 = this.getVirtualChildAt(var10_5);
                if (var27_26 == null) ** GOTO lbl249
                if (var27_26.getVisibility() == 8) {
                    var12_6 = var8_19;
                    var8_19 = var7_3;
                    var7_3 = var6_4;
                    var6_4 = var5_7;
                    var5_7 = var12_6;
                } else {
                    var28_27 = (LayoutParams)var27_26.getLayoutParams();
                    var4_28 = var28_27.weight;
                    if (var4_28 > 0.0f) {
                        var13_20 = (int)((float)var8_19 * var4_28 / var3_8);
                        var12_6 = var8_19 - var13_20;
                        var14_21 = LinearLayoutCompat.getChildMeasureSpec((int)var2_2, (int)(this.getPaddingTop() + this.getPaddingBottom() + var28_27.topMargin + var28_27.bottomMargin), (int)var28_27.height);
                        if (var28_27.width != 0 || var22_10 != 1073741824) {
                            var8_19 = var13_20 += var27_26.getMeasuredWidth();
                            if (var13_20 < 0) {
                                var8_19 = 0;
                            }
                            var27_26.measure(View.MeasureSpec.makeMeasureSpec((int)var8_19, (int)1073741824), var14_21);
                        } else {
                            var8_19 = var13_20 > 0 ? var13_20 : 0;
                            var27_26.measure(View.MeasureSpec.makeMeasureSpec((int)var8_19, (int)1073741824), var14_21);
                        }
                        var9_13 = ViewUtils.combineMeasuredStates(var9_13, ViewCompat.getMeasuredState(var27_26) & -16777216);
                        var3_8 -= var4_28;
                        var8_19 = var12_6;
                    }
                    if (var16_18) {
                        this.mTotalLength += var27_26.getMeasuredWidth() + var28_27.leftMargin + var28_27.rightMargin + this.getNextLocationOffset(var27_26);
                    } else {
                        var12_6 = this.mTotalLength;
                        this.mTotalLength = Math.max((int)var12_6, (int)(var27_26.getMeasuredWidth() + var12_6 + var28_27.leftMargin + var28_27.rightMargin + this.getNextLocationOffset(var27_26)));
                    }
                    var12_6 = var21_11 != 1073741824 && var28_27.height == -1 ? 1 : 0;
                    var15_22 = var28_27.topMargin + var28_27.bottomMargin;
                    var14_21 = var27_26.getMeasuredHeight() + var15_22;
                    var13_20 = Math.max((int)var7_3, (int)var14_21);
                    var7_3 = var12_6 != 0 ? var15_22 : var14_21;
                    var7_3 = Math.max((int)var6_4, (int)var7_3);
                    var5_7 = var5_7 != 0 && var28_27.height == -1 ? 1 : 0;
                    if (var23_16 && (var12_6 = var27_26.getBaseline()) != -1) {
                        var6_4 = var28_27.gravity < 0 ? this.mGravity : var28_27.gravity;
                        var6_4 = ((var6_4 & 112) >> 4 & -2) >> 1;
                        var25_14[var6_4] = Math.max((int)var25_14[var6_4], (int)var12_6);
                        var26_15[var6_4] = Math.max((int)var26_15[var6_4], (int)(var14_21 - var12_6));
                    }
                    var6_4 = var5_7;
                    var5_7 = var8_19;
                    var8_19 = var13_20;
                }
                ** GOTO lbl254
lbl238: // 1 sources:
                this.mTotalLength += this.getPaddingLeft() + this.getPaddingRight();
                if (var25_14[1] == -1 && var25_14[0] == -1 && var25_14[2] == -1) {
                    var8_19 = var7_3;
                    if (var25_14[3] == -1) break block48;
                }
                var8_19 = Math.max((int)var7_3, (int)(Math.max((int)var25_14[3], (int)Math.max((int)var25_14[0], (int)Math.max((int)var25_14[1], (int)var25_14[2]))) + Math.max((int)var26_15[3], (int)Math.max((int)var26_15[0], (int)Math.max((int)var26_15[1], (int)var26_15[2])))));
            }
            var7_3 = var5_7;
            var5_7 = var9_13;
            var9_13 = var7_3;
            var7_3 = var6_4;
            break;
lbl249: // 1 sources:
            var12_6 = var8_19;
            var8_19 = var7_3;
            var7_3 = var6_4;
            var6_4 = var5_7;
            var5_7 = var12_6;
lbl254: // 3 sources:
            var12_6 = var10_5 + 1;
            var10_5 = var8_19;
            var8_19 = var5_7;
            var5_7 = var6_4;
            var6_4 = var7_3;
            var7_3 = var10_5;
            var10_5 = var12_6;
        } while (true);
lbl262: // 2 sources:
        do {
            if (var9_13 != 0 || var21_11 == 1073741824) {
                var7_3 = var8_19;
            }
            this.setMeasuredDimension(-16777216 & var5_7 | var17_23, ViewCompat.resolveSizeAndState(Math.max((int)(var7_3 + (this.getPaddingTop() + this.getPaddingBottom())), (int)this.getSuggestedMinimumHeight()), var2_2, var5_7 << 16));
            if (var11_12 == 0) return;
            this.forceUniformHeight(var20_9, var1_1);
            return;
            break;
        } while (true);
lbl269: // 3 sources:
        for (var7_3 = 0; var7_3 < var20_9; ++var7_3) {
            var25_14 = (Object)this.getVirtualChildAt(var7_3);
            if (var25_14 == null || var25_14.getVisibility() == 8 || ((LayoutParams)var25_14.getLayoutParams()).weight <= 0.0f) continue;
            var25_14.measure(View.MeasureSpec.makeMeasureSpec((int)var8_19, (int)1073741824), View.MeasureSpec.makeMeasureSpec((int)var25_14.getMeasuredHeight(), (int)1073741824));
        }
lbl274: // 2 sources:
        var7_3 = var9_13;
        var8_19 = var13_20;
        var9_13 = var5_7;
        var5_7 = var6_4;
        ** while (true)
    }

    int measureNullChild(int n2) {
        return 0;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    void measureVertical(int var1_1, int var2_2) {
        this.mTotalLength = 0;
        var6_3 = 0;
        var5_4 = 0;
        var10_5 = 0;
        var13_6 = 0;
        var7_7 = 1;
        var3_8 = 0.0f;
        var19_9 = this.getVirtualChildCount();
        var20_10 = View.MeasureSpec.getMode((int)var1_1);
        var21_11 = View.MeasureSpec.getMode((int)var2_2);
        var12_12 = 0;
        var9_13 = 0;
        var22_14 = this.mBaselineAlignedChildIndex;
        var23_15 = this.mUseLargestChild;
        var8_16 = Integer.MIN_VALUE;
        for (var11_17 = 0; var11_17 < var19_9; ++var11_17) {
            var24_23 = this.getVirtualChildAt(var11_17);
            if (var24_23 == null) {
                this.mTotalLength += this.measureNullChild(var11_17);
                var14_18 = var8_16;
                var15_19 = var9_13;
                var9_13 = var6_3;
                var8_16 = var5_4;
                var6_3 = var15_19;
                var5_4 = var14_18;
            } else if (var24_23.getVisibility() == 8) {
                var15_19 = var11_17 + this.getChildrenSkipCount(var24_23, var11_17);
                var11_17 = var8_16;
                var14_18 = var9_13;
                var8_16 = var5_4;
                var9_13 = var6_3;
                var5_4 = var11_17;
                var6_3 = var14_18;
                var11_17 = var15_19;
            } else {
                if (this.hasDividerBeforeChildAt(var11_17)) {
                    this.mTotalLength += this.mDividerHeight;
                }
                var25_24 = (LayoutParams)var24_23.getLayoutParams();
                var3_8 += var25_24.weight;
                if (var21_11 == 1073741824 && var25_24.height == 0 && var25_24.weight > 0.0f) {
                    var9_13 = this.mTotalLength;
                    this.mTotalLength = Math.max((int)var9_13, (int)(var25_24.topMargin + var9_13 + var25_24.bottomMargin));
                    var15_19 = 1;
                    var14_18 = var8_16;
                } else {
                    var14_18 = var15_19 = Integer.MIN_VALUE;
                    if (var25_24.height == 0) {
                        var14_18 = var15_19;
                        if (var25_24.weight > 0.0f) {
                            var14_18 = 0;
                            var25_24.height = -2;
                        }
                    }
                    var15_19 = var3_8 == 0.0f ? this.mTotalLength : 0;
                    this.measureChildBeforeLayout(var24_23, var11_17, var1_1, 0, var2_2, var15_19);
                    if (var14_18 != Integer.MIN_VALUE) {
                        var25_24.height = var14_18;
                    }
                    var16_20 = var24_23.getMeasuredHeight();
                    var14_18 = this.mTotalLength;
                    this.mTotalLength = Math.max((int)var14_18, (int)(var14_18 + var16_20 + var25_24.topMargin + var25_24.bottomMargin + this.getNextLocationOffset(var24_23)));
                    var14_18 = var8_16;
                    var15_19 = var9_13;
                    if (var23_15) {
                        var14_18 = Math.max((int)var16_20, (int)var8_16);
                        var15_19 = var9_13;
                    }
                }
                if (var22_14 >= 0 && var22_14 == var11_17 + 1) {
                    this.mBaselineChildTop = this.mTotalLength;
                }
                if (var11_17 < var22_14 && var25_24.weight > 0.0f) {
                    throw new RuntimeException("A child of LinearLayout with index less than mBaselineAlignedChildIndex has weight > 0, which won't work.  Either remove the weight, or don't set mBaselineAlignedChildIndex.");
                }
                var9_13 = 0;
                if (var20_10 != 1073741824 && var25_24.width == -1) {
                    var8_16 = 1;
                    var9_13 = 1;
                } else {
                    var8_16 = var12_12;
                }
                var12_12 = var25_24.leftMargin;
                var12_12 = var25_24.rightMargin + var12_12;
                var16_20 = var24_23.getMeasuredWidth() + var12_12;
                var18_22 = Math.max((int)var6_3, (int)var16_20);
                var17_21 = ViewUtils.combineMeasuredStates(var5_4, ViewCompat.getMeasuredState(var24_23));
                var7_7 = var7_7 != 0 && var25_24.width == -1 ? 1 : 0;
                if (var25_24.weight > 0.0f) {
                    if (var9_13 == 0) {
                        var12_12 = var16_20;
                    }
                    var6_3 = Math.max((int)var13_6, (int)var12_12);
                    var5_4 = var10_5;
                } else {
                    if (var9_13 == 0) {
                        var12_12 = var16_20;
                    }
                    var5_4 = Math.max((int)var10_5, (int)var12_12);
                    var6_3 = var13_6;
                }
                var11_17 += this.getChildrenSkipCount(var24_23, var11_17);
                var13_6 = var6_3;
                var10_5 = var5_4;
                var9_13 = var18_22;
                var5_4 = var14_18;
                var14_18 = var17_21;
                var12_12 = var8_16;
                var6_3 = var15_19;
                var8_16 = var14_18;
            }
            var14_18 = var8_16;
            var15_19 = var9_13;
            var8_16 = var5_4;
            var9_13 = var6_3;
            var5_4 = var14_18;
            var6_3 = var15_19;
        }
        if (this.mTotalLength > 0 && this.hasDividerBeforeChildAt(var19_9)) {
            this.mTotalLength += this.mDividerHeight;
        }
        if (var23_15 && (var21_11 == Integer.MIN_VALUE || var21_11 == 0)) {
            this.mTotalLength = 0;
            for (var11_17 = 0; var11_17 < var19_9; ++var11_17) {
                var24_23 = this.getVirtualChildAt(var11_17);
                if (var24_23 == null) {
                    this.mTotalLength += this.measureNullChild(var11_17);
                    continue;
                }
                if (var24_23.getVisibility() == 8) {
                    var11_17 = this.getChildrenSkipCount(var24_23, var11_17) + var11_17;
                    continue;
                }
                var25_24 = (LayoutParams)var24_23.getLayoutParams();
                var14_18 = this.mTotalLength;
                var15_19 = var25_24.topMargin;
                this.mTotalLength = Math.max((int)var14_18, (int)(var25_24.bottomMargin + (var14_18 + var8_16 + var15_19) + this.getNextLocationOffset(var24_23)));
            }
        }
        this.mTotalLength += this.getPaddingTop() + this.getPaddingBottom();
        var15_19 = ViewCompat.resolveSizeAndState(Math.max((int)this.mTotalLength, (int)this.getSuggestedMinimumHeight()), var2_2, 0);
        var11_17 = (16777215 & var15_19) - this.mTotalLength;
        if (var9_13 == 0 && (var11_17 == 0 || var3_8 <= 0.0f)) ** GOTO lbl131
        if (this.mWeightSum > 0.0f) {
            var3_8 = this.mWeightSum;
        }
        ** GOTO lbl134
lbl131: // 1 sources:
        var10_5 = Math.max((int)var10_5, (int)var13_6);
        if (!var23_15 || var21_11 == 1073741824) ** GOTO lbl210
        ** GOTO lbl205
lbl134: // 1 sources:
        this.mTotalLength = 0;
        var8_16 = 0;
        var9_13 = var7_7;
        var7_7 = var10_5;
        var10_5 = var11_17;
        var11_17 = var8_16;
        var8_16 = var6_3;
        var6_3 = var9_13;
        var9_13 = var10_5;
        do {
            if (var11_17 >= var19_9) ** GOTO lbl170
            var24_23 = this.getVirtualChildAt(var11_17);
            if (var24_23.getVisibility() != 8) ** GOTO lbl151
            var10_5 = var7_7;
            var7_7 = var8_16;
            var8_16 = var10_5;
            ** GOTO lbl193
lbl151: // 1 sources:
            var25_24 = (LayoutParams)var24_23.getLayoutParams();
            var4_25 = var25_24.weight;
            if (var4_25 <= 0.0f) ** GOTO lbl175
            var13_6 = (int)((float)var9_13 * var4_25 / var3_8);
            var16_20 = LinearLayoutCompat.getChildMeasureSpec((int)var1_1, (int)(this.getPaddingLeft() + this.getPaddingRight() + var25_24.leftMargin + var25_24.rightMargin), (int)var25_24.width);
            if (var25_24.height != 0 || var21_11 != 1073741824) {
                var10_5 = var14_18 = var13_6 + var24_23.getMeasuredHeight();
                if (var14_18 < 0) {
                    var10_5 = 0;
                }
                var24_23.measure(var16_20, View.MeasureSpec.makeMeasureSpec((int)var10_5, (int)1073741824));
            } else {
                var10_5 = var13_6 > 0 ? var13_6 : 0;
                var24_23.measure(var16_20, View.MeasureSpec.makeMeasureSpec((int)var10_5, (int)1073741824));
            }
            var5_4 = ViewUtils.combineMeasuredStates(var5_4, ViewCompat.getMeasuredState(var24_23) & -256);
            var10_5 = var9_13 - var13_6;
            var9_13 = var5_4;
            var3_8 -= var4_25;
            var5_4 = var10_5;
            ** GOTO lbl178
lbl170: // 1 sources:
            this.mTotalLength += this.getPaddingTop() + this.getPaddingBottom();
            var9_13 = var6_3;
            var6_3 = var7_7;
            var7_7 = var9_13;
            break;
lbl175: // 1 sources:
            var10_5 = var5_4;
            var5_4 = var9_13;
            var9_13 = var10_5;
lbl178: // 2 sources:
            var13_6 = var25_24.leftMargin + var25_24.rightMargin;
            var14_18 = var24_23.getMeasuredWidth() + var13_6;
            var10_5 = Math.max((int)var8_16, (int)var14_18);
            var8_16 = var20_10 != 1073741824 && var25_24.width == -1 ? 1 : 0;
            var8_16 = var8_16 != 0 ? var13_6 : var14_18;
            var8_16 = Math.max((int)var7_7, (int)var8_16);
            var6_3 = var6_3 != 0 && var25_24.width == -1 ? 1 : 0;
            var7_7 = this.mTotalLength;
            var13_6 = var24_23.getMeasuredHeight();
            var14_18 = var25_24.topMargin;
            this.mTotalLength = Math.max((int)var7_7, (int)(var25_24.bottomMargin + (var13_6 + var7_7 + var14_18) + this.getNextLocationOffset(var24_23)));
            var7_7 = var10_5;
            var10_5 = var5_4;
            var5_4 = var9_13;
            var9_13 = var10_5;
lbl193: // 2 sources:
            ++var11_17;
            var10_5 = var7_7;
            var7_7 = var8_16;
            var8_16 = var10_5;
        } while (true);
lbl198: // 2 sources:
        do {
            if (var7_7 != 0 || var20_10 == 1073741824) {
                var6_3 = var8_16;
            }
            this.setMeasuredDimension(ViewCompat.resolveSizeAndState(Math.max((int)(var6_3 + (this.getPaddingLeft() + this.getPaddingRight())), (int)this.getSuggestedMinimumWidth()), var1_1, var5_4), var15_19);
            if (var12_12 == 0) return;
            this.forceUniformWidth(var19_9, var2_2);
            return;
            break;
        } while (true);
lbl205: // 3 sources:
        for (var9_13 = 0; var9_13 < var19_9; ++var9_13) {
            var24_23 = this.getVirtualChildAt(var9_13);
            if (var24_23 == null || var24_23.getVisibility() == 8 || ((LayoutParams)var24_23.getLayoutParams()).weight <= 0.0f) continue;
            var24_23.measure(View.MeasureSpec.makeMeasureSpec((int)var24_23.getMeasuredWidth(), (int)1073741824), View.MeasureSpec.makeMeasureSpec((int)var8_16, (int)1073741824));
        }
lbl210: // 2 sources:
        var8_16 = var10_5;
        var9_13 = var6_3;
        var6_3 = var8_16;
        var8_16 = var9_13;
        ** while (true)
    }

    public void onDraw(Canvas canvas) {
        if (this.mDivider == null) {
            return;
        }
        if (this.mOrientation == 1) {
            this.drawDividersVertical(canvas);
            return;
        }
        this.drawDividersHorizontal(canvas);
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (Build.VERSION.SDK_INT >= 14) {
            super.onInitializeAccessibilityEvent(accessibilityEvent);
            accessibilityEvent.setClassName((CharSequence)LinearLayoutCompat.class.getName());
        }
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        if (Build.VERSION.SDK_INT >= 14) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            accessibilityNodeInfo.setClassName((CharSequence)LinearLayoutCompat.class.getName());
        }
    }

    public void onLayout(boolean bl2, int n2, int n3, int n4, int n5) {
        if (this.mOrientation == 1) {
            this.layoutVertical(n2, n3, n4, n5);
            return;
        }
        this.layoutHorizontal(n2, n3, n4, n5);
    }

    public void onMeasure(int n2, int n3) {
        if (this.mOrientation == 1) {
            this.measureVertical(n2, n3);
            return;
        }
        this.measureHorizontal(n2, n3);
    }

    public void setBaselineAligned(boolean bl2) {
        this.mBaselineAligned = bl2;
    }

    public void setBaselineAlignedChildIndex(int n2) {
        if (n2 < 0 || n2 >= this.getChildCount()) {
            throw new IllegalArgumentException("base aligned child index out of range (0, " + this.getChildCount() + ")");
        }
        this.mBaselineAlignedChildIndex = n2;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setDividerDrawable(Drawable drawable2) {
        boolean bl2 = false;
        if (drawable2 == this.mDivider) {
            return;
        }
        this.mDivider = drawable2;
        if (drawable2 != null) {
            this.mDividerWidth = drawable2.getIntrinsicWidth();
            this.mDividerHeight = drawable2.getIntrinsicHeight();
        } else {
            this.mDividerWidth = 0;
            this.mDividerHeight = 0;
        }
        if (drawable2 == null) {
            bl2 = true;
        }
        this.setWillNotDraw(bl2);
        this.requestLayout();
    }

    public void setDividerPadding(int n2) {
        this.mDividerPadding = n2;
    }

    public void setGravity(int n2) {
        if (this.mGravity != n2) {
            if ((8388615 & n2) == 0) {
                n2 = 8388611 | n2;
            }
            int n3 = n2;
            if ((n2 & 112) == 0) {
                n3 = n2 | 48;
            }
            this.mGravity = n3;
            this.requestLayout();
        }
    }

    public void setHorizontalGravity(int n2) {
        if ((this.mGravity & 8388615) != (n2 &= 8388615)) {
            this.mGravity = n2 | this.mGravity & -8388616;
            this.requestLayout();
        }
    }

    public void setMeasureWithLargestChildEnabled(boolean bl2) {
        this.mUseLargestChild = bl2;
    }

    public void setOrientation(int n2) {
        if (this.mOrientation != n2) {
            this.mOrientation = n2;
            this.requestLayout();
        }
    }

    public void setShowDividers(int n2) {
        if (n2 != this.mShowDividers) {
            this.requestLayout();
        }
        this.mShowDividers = n2;
    }

    public void setVerticalGravity(int n2) {
        if ((this.mGravity & 112) != (n2 &= 112)) {
            this.mGravity = n2 | this.mGravity & -113;
            this.requestLayout();
        }
    }

    public void setWeightSum(float f2) {
        this.mWeightSum = Math.max((float)0.0f, (float)f2);
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }

    @Retention(value=RetentionPolicy.SOURCE)
    @IntDef(flag=1, value={0, 1, 2, 4})
    public static @interface DividerMode {
    }

    public static class LayoutParams
    extends ViewGroup.MarginLayoutParams {
        public int gravity = -1;
        public float weight;

        public LayoutParams(int n2, int n3) {
            super(n2, n3);
            this.weight = 0.0f;
        }

        public LayoutParams(int n2, int n3, float f2) {
            super(n2, n3);
            this.weight = f2;
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            context = context.obtainStyledAttributes(attributeSet, R.styleable.LinearLayoutCompat_Layout);
            this.weight = context.getFloat(R.styleable.LinearLayoutCompat_Layout_android_layout_weight, 0.0f);
            this.gravity = context.getInt(R.styleable.LinearLayoutCompat_Layout_android_layout_gravity, -1);
            context.recycle();
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((ViewGroup.MarginLayoutParams)layoutParams);
            this.weight = layoutParams.weight;
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
    @IntDef(value={0, 1})
    public static @interface OrientationMode {
    }

}

