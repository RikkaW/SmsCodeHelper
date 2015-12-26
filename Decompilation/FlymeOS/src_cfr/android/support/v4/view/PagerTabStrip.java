/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.Canvas
 *  android.graphics.Paint
 *  android.graphics.Rect
 *  android.graphics.drawable.Drawable
 *  android.util.AttributeSet
 *  android.util.DisplayMetrics
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewConfiguration
 *  android.widget.TextView
 *  java.lang.Math
 *  java.lang.String
 */
package android.support.v4.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerTabStrip$1;
import android.support.v4.view.PagerTabStrip$2;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.TextView;

public class PagerTabStrip
extends PagerTitleStrip {
    private static final int FULL_UNDERLINE_HEIGHT = 1;
    private static final int INDICATOR_HEIGHT = 3;
    private static final int MIN_PADDING_BOTTOM = 6;
    private static final int MIN_STRIP_HEIGHT = 32;
    private static final int MIN_TEXT_SPACING = 64;
    private static final int TAB_PADDING = 16;
    private static final int TAB_SPACING = 32;
    private static final String TAG = "PagerTabStrip";
    private boolean mDrawFullUnderline = false;
    private boolean mDrawFullUnderlineSet = false;
    private int mFullUnderlineHeight;
    private boolean mIgnoreTap;
    private int mIndicatorColor;
    private int mIndicatorHeight;
    private float mInitialMotionX;
    private float mInitialMotionY;
    private int mMinPaddingBottom;
    private int mMinStripHeight;
    private int mMinTextSpacing;
    private int mTabAlpha = 255;
    private int mTabPadding;
    private final Paint mTabPaint = new Paint();
    private final Rect mTempRect = new Rect();
    private int mTouchSlop;

    public PagerTabStrip(Context context) {
        this(context, null);
    }

    public PagerTabStrip(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mIndicatorColor = this.mTextColor;
        this.mTabPaint.setColor(this.mIndicatorColor);
        float f2 = context.getResources().getDisplayMetrics().density;
        this.mIndicatorHeight = (int)(3.0f * f2 + 0.5f);
        this.mMinPaddingBottom = (int)(6.0f * f2 + 0.5f);
        this.mMinTextSpacing = (int)(64.0f * f2);
        this.mTabPadding = (int)(16.0f * f2 + 0.5f);
        this.mFullUnderlineHeight = (int)(1.0f * f2 + 0.5f);
        this.mMinStripHeight = (int)(f2 * 32.0f + 0.5f);
        this.mTouchSlop = ViewConfiguration.get((Context)context).getScaledTouchSlop();
        this.setPadding(this.getPaddingLeft(), this.getPaddingTop(), this.getPaddingRight(), this.getPaddingBottom());
        this.setTextSpacing(this.getTextSpacing());
        this.setWillNotDraw(false);
        this.mPrevText.setFocusable(true);
        this.mPrevText.setOnClickListener((View.OnClickListener)new PagerTabStrip$1(this));
        this.mNextText.setFocusable(true);
        this.mNextText.setOnClickListener((View.OnClickListener)new PagerTabStrip$2(this));
        if (this.getBackground() == null) {
            this.mDrawFullUnderline = true;
        }
    }

    public boolean getDrawFullUnderline() {
        return this.mDrawFullUnderline;
    }

    @Override
    int getMinHeight() {
        return Math.max((int)super.getMinHeight(), (int)this.mMinStripHeight);
    }

    public int getTabIndicatorColor() {
        return this.mIndicatorColor;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int n2 = this.getHeight();
        int n3 = this.mCurrText.getLeft();
        int n4 = this.mTabPadding;
        int n5 = this.mCurrText.getRight();
        int n6 = this.mTabPadding;
        int n7 = this.mIndicatorHeight;
        this.mTabPaint.setColor(this.mTabAlpha << 24 | this.mIndicatorColor & 16777215);
        canvas.drawRect((float)(n3 - n4), (float)(n2 - n7), (float)(n5 + n6), (float)n2, this.mTabPaint);
        if (this.mDrawFullUnderline) {
            this.mTabPaint.setColor(-16777216 | this.mIndicatorColor & 16777215);
            canvas.drawRect((float)this.getPaddingLeft(), (float)(n2 - this.mFullUnderlineHeight), (float)(this.getWidth() - this.getPaddingRight()), (float)n2, this.mTabPaint);
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public boolean onTouchEvent(MotionEvent var1_1) {
        var4_2 = var1_1.getAction();
        if (var4_2 != 0 && this.mIgnoreTap) {
            return false;
        }
        var2_3 = var1_1.getX();
        var3_4 = var1_1.getY();
        switch (var4_2) {
            case 0: {
                this.mInitialMotionX = var2_3;
                this.mInitialMotionY = var3_4;
                this.mIgnoreTap = false;
                ** break;
            }
            case 2: {
                if (Math.abs((float)(var2_3 - this.mInitialMotionX)) <= (float)this.mTouchSlop) {
                    if (Math.abs((float)(var3_4 - this.mInitialMotionY)) <= (float)this.mTouchSlop) return true;
                }
                this.mIgnoreTap = true;
            }
lbl16: // 3 sources:
            default: {
                return true;
            }
            case 1: 
        }
        if (var2_3 < (float)(this.mCurrText.getLeft() - this.mTabPadding)) {
            this.mPager.setCurrentItem(this.mPager.getCurrentItem() - 1);
            return true;
        }
        if (var2_3 <= (float)(this.mCurrText.getRight() + this.mTabPadding)) return true;
        this.mPager.setCurrentItem(this.mPager.getCurrentItem() + 1);
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setBackgroundColor(int n2) {
        super.setBackgroundColor(n2);
        if (!this.mDrawFullUnderlineSet) {
            boolean bl2 = (-16777216 & n2) == 0;
            this.mDrawFullUnderline = bl2;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setBackgroundDrawable(Drawable drawable2) {
        super.setBackgroundDrawable(drawable2);
        if (!this.mDrawFullUnderlineSet) {
            boolean bl2 = drawable2 == null;
            this.mDrawFullUnderline = bl2;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setBackgroundResource(int n2) {
        super.setBackgroundResource(n2);
        if (!this.mDrawFullUnderlineSet) {
            boolean bl2 = n2 == 0;
            this.mDrawFullUnderline = bl2;
        }
    }

    public void setDrawFullUnderline(boolean bl2) {
        this.mDrawFullUnderline = bl2;
        this.mDrawFullUnderlineSet = true;
        this.invalidate();
    }

    public void setPadding(int n2, int n3, int n4, int n5) {
        int n6 = n5;
        if (n5 < this.mMinPaddingBottom) {
            n6 = this.mMinPaddingBottom;
        }
        super.setPadding(n2, n3, n4, n6);
    }

    public void setTabIndicatorColor(int n2) {
        this.mIndicatorColor = n2;
        this.mTabPaint.setColor(this.mIndicatorColor);
        this.invalidate();
    }

    public void setTabIndicatorColorResource(int n2) {
        this.setTabIndicatorColor(this.getContext().getResources().getColor(n2));
    }

    @Override
    public void setTextSpacing(int n2) {
        int n3 = n2;
        if (n2 < this.mMinTextSpacing) {
            n3 = this.mMinTextSpacing;
        }
        super.setTextSpacing(n3);
    }

    @Override
    void updateTextPositions(int n2, float f2, boolean bl2) {
        Rect rect = this.mTempRect;
        int n3 = this.getHeight();
        int n4 = this.mCurrText.getLeft();
        int n5 = this.mTabPadding;
        int n6 = this.mCurrText.getRight();
        int n7 = this.mTabPadding;
        int n8 = n3 - this.mIndicatorHeight;
        rect.set(n4 - n5, n8, n6 + n7, n3);
        super.updateTextPositions(n2, f2, bl2);
        this.mTabAlpha = (int)(Math.abs((float)(f2 - 0.5f)) * 2.0f * 255.0f);
        rect.union(this.mCurrText.getLeft() - this.mTabPadding, n8, this.mCurrText.getRight() + this.mTabPadding, n3);
        this.invalidate(rect);
    }
}

