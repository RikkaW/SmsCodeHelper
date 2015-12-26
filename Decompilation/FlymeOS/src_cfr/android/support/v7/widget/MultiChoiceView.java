/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.drawable.Drawable
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.text.TextUtils
 *  android.util.AttributeSet
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.View$MeasureSpec
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewGroup$MarginLayoutParams
 *  android.view.ViewParent
 *  android.widget.TextView
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.appcompat.R;
import android.support.v7.internal.widget.TintTypedArray;
import android.support.v7.internal.widget.ViewUtils;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;

public class MultiChoiceView
extends LinearLayoutCompat {
    public static final int ITEM_CLOSE = 0;
    public static final int ITEM_SELECT_ALL = 1;
    private View mClose;
    private int mCloseItemLayout;
    private View mSelectAll;
    private int mSelectAllItemLayout;
    private CharSequence mSubTitle;
    private int mSubtitleStyleRes;
    private final TextView mSubtitleView;
    private CharSequence mTitle;
    private int mTitleGravity = 17;
    private View mTitleLayout;
    private int mTitleStyleRes;
    private final TextView mTitleView;

    public MultiChoiceView(Context context) {
        this(context, null);
    }

    public MultiChoiceView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.mzMultiChoiceViewStyle);
    }

    public MultiChoiceView(Context context, AttributeSet object, int n2) {
        super(context, (AttributeSet)object, n2);
        object = TintTypedArray.obtainStyledAttributes(context, (AttributeSet)object, R.styleable.MzMultiChoiceView, n2, 0);
        this.mTitleStyleRes = object.getResourceId(R.styleable.MzMultiChoiceView_titleTextStyle, 0);
        this.mSubtitleStyleRes = object.getResourceId(R.styleable.MzMultiChoiceView_subtitleTextStyle, 0);
        object.recycle();
        context = LayoutInflater.from((Context)context);
        this.mCloseItemLayout = R.layout.mz_action_multi_choice_mode_close_item;
        this.mSelectAllItemLayout = R.layout.mz_action_multi_choice_mode_select_all_item;
        this.mClose = context.inflate(this.mCloseItemLayout, (ViewGroup)this, false);
        this.addView(this.mClose);
        this.mSelectAll = context.inflate(this.mSelectAllItemLayout, (ViewGroup)this, false);
        this.addView(this.mSelectAll);
        if (Build.VERSION.SDK_INT < 21) {
            object = new ItemRippleDrawable(this.mClose);
            this.mClose.setBackgroundDrawable((Drawable)object);
            object = new ItemRippleDrawable(this.mSelectAll);
            this.mSelectAll.setBackgroundDrawable((Drawable)object);
            this.setClipChildren(false);
        }
        this.mTitleLayout = context.inflate(R.layout.mz_action_bar_title_item, (ViewGroup)this, false);
        this.addView(this.mTitleLayout);
        this.mTitleView = (TextView)this.mTitleLayout.findViewById(R.id.action_bar_title);
        this.mSubtitleView = (TextView)this.mTitleLayout.findViewById(R.id.action_bar_subtitle);
        if (this.mTitleStyleRes != 0) {
            this.mTitleView.setTextAppearance(this.getContext(), this.mTitleStyleRes);
        }
        if (this.mSubtitleStyleRes != 0) {
            this.mSubtitleView.setTextAppearance(this.getContext(), this.mSubtitleStyleRes);
        }
        this.setTitle(this.getResources().getString(R.string.mz_action_bar_multi_choice_title, new Object[]{0}));
    }

    /*
     * Enabled aggressive block sorting
     */
    private int computeAvailableWidth(int n2, int n3) {
        ViewGroup.MarginLayoutParams marginLayoutParams;
        int n4 = 0;
        int n5 = n3 - (this.getPaddingLeft() + this.getPaddingRight());
        if ((this.mTitleGravity & 7) != 1) return n2;
        if (this.mClose != null && this.mClose.getParent() == this) {
            n2 = this.mClose.getMeasuredWidth();
            marginLayoutParams = (ViewGroup.MarginLayoutParams)this.mClose.getLayoutParams();
            n3 = marginLayoutParams.leftMargin;
            n2 = marginLayoutParams.rightMargin + n3 + n2;
        } else {
            n2 = 0;
        }
        n3 = n4;
        if (this.mSelectAll != null) {
            n3 = n4;
            if (this.mSelectAll.getParent() == this) {
                n3 = this.mSelectAll.getMeasuredWidth();
                marginLayoutParams = (ViewGroup.MarginLayoutParams)this.mSelectAll.getLayoutParams();
                n4 = marginLayoutParams.leftMargin;
                n3 += marginLayoutParams.rightMargin + n4;
            }
        }
        if (n2 >= n3) return n5 - n2 * 2;
        return n5 - n3 * 2;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void initTitle() {
        TextView textView;
        int n2;
        block3 : {
            int n3 = 8;
            boolean bl2 = true;
            this.mTitleView.setText(this.mTitle);
            this.mSubtitleView.setText(this.mSubTitle);
            n2 = !TextUtils.isEmpty((CharSequence)this.mTitle) ? 1 : 0;
            if (TextUtils.isEmpty((CharSequence)this.mSubTitle)) {
                bl2 = false;
            }
            textView = this.mSubtitleView;
            int n4 = bl2 ? 0 : 8;
            textView.setVisibility(n4);
            textView = this.mTitleLayout;
            if (n2 == 0) {
                n2 = n3;
                if (!bl2) break block3;
            }
            n2 = 0;
        }
        textView.setVisibility(n2);
    }

    protected static int next(int n2, int n3, boolean bl2) {
        if (bl2) {
            return n2 - n3;
        }
        return n2 + n3;
    }

    private void setBackgroundHotspotBounds(View view) {
        Drawable drawable2 = view.getBackground();
        if (drawable2 != null) {
            int n2 = view.getPaddingLeft();
            int n3 = view.getPaddingRight();
            int n4 = view.getWidth();
            int n5 = (n2 - n3 + n4) / 2;
            n2 = (n4 - n2 - n3) / 2;
            DrawableCompat.setHotspotBounds(drawable2, n5 - n2, 0, n2 + n5, view.getHeight());
        }
    }

    public View getCloseItemView() {
        return this.mClose;
    }

    public View getSelectAllView() {
        return this.mSelectAll;
    }

    public CharSequence getSubTitle() {
        return this.mSubTitle;
    }

    public CharSequence getTitle() {
        return this.mTitle;
    }

    protected int measureChildView(View view, int n2, int n3, int n4) {
        view.measure(View.MeasureSpec.makeMeasureSpec((int)n2, (int)Integer.MIN_VALUE), n3);
        return Math.max((int)0, (int)(n2 - view.getMeasuredWidth() - n4));
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected void onLayout(boolean bl2, int n2, int n3, int n4, int n5) {
        ViewGroup.MarginLayoutParams marginLayoutParams;
        boolean bl3 = ViewUtils.isLayoutRtl((View)this);
        int n6 = bl3 ? n4 - n2 - this.getPaddingRight() : this.getPaddingLeft();
        int n7 = this.getPaddingTop();
        int n8 = n5 - n3 - this.getPaddingTop() - this.getPaddingBottom();
        n3 = n6;
        if (this.mClose != null) {
            n3 = n6;
            if (this.mClose.getVisibility() != 8) {
                marginLayoutParams = (ViewGroup.MarginLayoutParams)this.mClose.getLayoutParams();
                n3 = bl3 ? marginLayoutParams.rightMargin : marginLayoutParams.leftMargin;
                n5 = bl3 ? marginLayoutParams.leftMargin : marginLayoutParams.rightMargin;
                n3 = MultiChoiceView.next(n6, n3, bl3);
                n3 = MultiChoiceView.next(this.positionChild(this.mClose, n3, n7, n8, bl3) + n3, n5, bl3);
                this.setBackgroundHotspotBounds(this.mClose);
            }
        }
        if (this.mTitleLayout != null && this.mTitleLayout.getVisibility() != 8) {
            if ((this.mTitleGravity & 7) == 1) {
                n3 = (n4 - n2 - this.mTitleLayout.getMeasuredWidth()) / 2;
            }
            this.positionChild(this.mTitleLayout, n3, n7, n8, bl3);
        }
        n2 = bl3 ? this.getPaddingLeft() : n4 - n2 - this.getPaddingRight();
        if (this.mSelectAll != null && this.mSelectAll.getVisibility() != 8) {
            marginLayoutParams = (ViewGroup.MarginLayoutParams)this.mSelectAll.getLayoutParams();
            n3 = bl3 ? marginLayoutParams.leftMargin : marginLayoutParams.rightMargin;
            bl2 = !bl3;
            n2 = MultiChoiceView.next(n2, n3, bl2);
            marginLayoutParams = this.mSelectAll;
            bl2 = !bl3;
            this.positionChild((View)marginLayoutParams, n2, n7, n8, bl2);
            this.setBackgroundHotspotBounds(this.mSelectAll);
        }
    }

    @Override
    protected void onMeasure(int n2, int n3) {
        ViewGroup.MarginLayoutParams marginLayoutParams;
        int n4 = 0;
        int n5 = View.MeasureSpec.getSize((int)n2);
        int n6 = this.getPaddingTop() + this.getPaddingBottom();
        int n7 = n5 - this.getPaddingLeft() - this.getPaddingRight();
        int n8 = View.MeasureSpec.makeMeasureSpec((int)(View.MeasureSpec.getSize((int)n3) - n6), (int)Integer.MIN_VALUE);
        n2 = n7;
        if (this.mClose != null) {
            n2 = this.measureChildView(this.mClose, n7, n8, 0);
            marginLayoutParams = (ViewGroup.MarginLayoutParams)this.mClose.getLayoutParams();
            n3 = marginLayoutParams.leftMargin;
            n2 -= marginLayoutParams.rightMargin + n3;
        }
        n3 = n2;
        if (this.mSelectAll != null) {
            n2 = this.measureChildView(this.mSelectAll, n2, n8, 0);
            marginLayoutParams = (ViewGroup.MarginLayoutParams)this.mSelectAll.getLayoutParams();
            n3 = marginLayoutParams.leftMargin;
            n3 = n2 - (marginLayoutParams.rightMargin + n3);
        }
        n2 = this.computeAvailableWidth(n3, n5);
        if (this.mTitleLayout != null) {
            this.measureChildView(this.mTitleLayout, n2, n8, 0);
        }
        n7 = this.getChildCount();
        n2 = n4;
        for (n3 = 0; n3 < n7; ++n3) {
            n4 = this.getChildAt(n3).getMeasuredHeight() + n6;
            if (n4 <= n2) continue;
            n2 = n4;
        }
        this.setMeasuredDimension(n5, n2);
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

    public void setOnCloseItemClickListener(View.OnClickListener onClickListener) {
        if (this.mClose != null) {
            this.mClose.setOnClickListener(onClickListener);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setOnItemClickListener(int n2, View.OnClickListener onClickListener) {
        switch (n2) {
            default: {
                throw new IllegalArgumentException("Item does not exist");
            }
            case 0: {
                if (this.mClose == null) return;
                this.mClose.setOnClickListener(onClickListener);
                return;
            }
            case 1: {
                if (this.mSelectAll == null) return;
                this.mSelectAll.setOnClickListener(onClickListener);
                return;
            }
        }
    }

    public void setOnSelectAllItemClickListener(View.OnClickListener onClickListener) {
        if (this.mSelectAll != null) {
            this.mSelectAll.setOnClickListener(onClickListener);
        }
    }

    public void setSubTitle(CharSequence charSequence) {
        this.mSubTitle = charSequence;
        this.initTitle();
    }

    public void setTitle(CharSequence charSequence) {
        this.mTitle = charSequence;
        this.initTitle();
    }

    class ItemRippleDrawable
    extends aqt {
        public ItemRippleDrawable(View view) {
            super(view, R.attr.mzActionButtonRippleSplitStyle);
        }
    }

}

