/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.Canvas
 *  android.graphics.Paint
 *  android.graphics.drawable.Drawable
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewParent
 */
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v7.appcompat.R;
import android.support.v7.internal.widget.ScrollingTabContainerView;
import android.support.v7.internal.widget.TintImageView;
import android.support.v7.internal.widget.TintTypedArray;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

public class aqy
extends LinearLayoutCompat {
    private boolean a;
    private ScrollingTabContainerView b;
    private a c;
    private Context d;
    private int e;
    private int f;
    private final Paint g;
    private boolean h;
    private View.OnClickListener i;

    public aqy(Context context) {
        super(context, null, R.attr.mzActionBarTabContainerStyle);
        TintTypedArray tintTypedArray = TintTypedArray.obtainStyledAttributes(context, null, R.styleable.MzActionBarTabContainer, R.attr.mzActionBarTabContainerStyle, 0);
        this.a = tintTypedArray.getBoolean(R.styleable.MzActionBarTabContainer_mzAllowCollapse, false);
        tintTypedArray.recycle();
        tintTypedArray = TintTypedArray.obtainStyledAttributes(context, null, R.styleable.MzActionBarTabScrollView, R.attr.mzActionBarTabScrollViewStyle, 0);
        this.f = tintTypedArray.getColor(R.styleable.MzActionBarTabScrollView_mzTopDividerColor, this.getResources().getColor(R.color.mz_action_bar_scrollview_divider_default_color));
        this.e = tintTypedArray.getDimensionPixelSize(R.styleable.MzActionBarTabScrollView_mzTopDividerHeight, this.getResources().getDimensionPixelSize(R.dimen.mz_action_bar_tab_scroll_top_divider_height));
        tintTypedArray.recycle();
        this.d = context;
        this.g = new Paint();
        this.g.setColor(this.f);
        this.setOrientation(0);
    }

    static /* synthetic */ View.OnClickListener a(aqy aqy2) {
        return aqy2.i;
    }

    private void a() {
        int n2 = this.getResources().getDimensionPixelOffset(R.dimen.mz_action_tab_bar_margin_left);
        this.setPadding(n2, 0, n2, 0);
        if (!this.c()) {
            return;
        }
        this.removeView((View)this.c);
    }

    private void b() {
        if (this.c()) {
            return;
        }
        if (this.c == null) {
            this.c = new a(this.d);
        }
        this.addView((View)this.c, new ViewGroup.LayoutParams(-2, -1));
        this.setPadding(this.getResources().getDimensionPixelOffset(R.dimen.mz_action_tab_bar_margin_left), 0, 0, 0);
    }

    private boolean c() {
        if (this.c != null && this.c.getParent() == this) {
            return true;
        }
        return false;
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        int n2 = this.getResources().getDimensionPixelSize(R.dimen.mz_action_tab_bar_margin_left);
        canvas.drawRect((float)n2, 0.0f, (float)(this.getMeasuredWidth() - n2), (float)this.e, this.g);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected void onMeasure(int n2, int n3) {
        super.onMeasure(n2, n3);
        boolean bl2 = false;
        int n4 = this.getResources().getDimensionPixelSize(R.dimen.mz_action_tab_bar_margin_left);
        int n5 = this.getMeasuredWidth();
        boolean bl3 = bl2;
        if (this.a) {
            bl3 = bl2;
            if (this.b != null) {
                bl3 = bl2;
                if (this.b.getVisibility() == 0) {
                    bl3 = bl2;
                    if (n5 < n4 * 2 + this.b.getTabStripWidth()) {
                        bl3 = true;
                    }
                }
            }
        }
        if (bl3 == this.h) return;
        this.h = bl3;
        if (bl3) {
            this.b();
        } else {
            this.a();
        }
        super.onMeasure(n2, n3);
    }

    public void setAllowCollapse(boolean bl2) {
        this.a = bl2;
    }

    public void setCollapseButtonClickListener(View.OnClickListener onClickListener) {
        this.i = onClickListener;
    }

    @Override
    public void setDividerDrawable(Drawable drawable2) {
        super.setDividerDrawable(drawable2);
        this.setWillNotDraw(false);
    }

    public void setTabView(ScrollingTabContainerView scrollingTabContainerView) {
        if (this.b != null) {
            this.removeView((View)this.b);
        }
        if (this.c()) {
            this.removeView((View)this.c);
        }
        this.b = scrollingTabContainerView;
        if (scrollingTabContainerView != null) {
            this.addView((View)scrollingTabContainerView);
            LinearLayoutCompat.LayoutParams layoutParams = (LinearLayoutCompat.LayoutParams)scrollingTabContainerView.getLayoutParams();
            int n2 = this.getResources().getDimensionPixelOffset(R.dimen.mz_action_tab_bar_margin_left);
            this.setPadding(n2, 0, n2, 0);
            layoutParams.width = 0;
            layoutParams.weight = 1.0f;
            layoutParams.height = -2;
            scrollingTabContainerView.setAllowCollapse(false);
        }
    }

    class a
    extends TintImageView {
        public a(Context context) {
            super(context, null, R.attr.mzTabContainerCollapseButtonStyle);
            this.setClickable(true);
            this.setFocusable(true);
            this.setVisibility(0);
            this.setEnabled(true);
            this.setOnClickListener((View.OnClickListener)new aqz(this, aqy.this));
            this.setBackgroundDrawable((Drawable)new aqt((View)this, R.attr.mzActionButtonRippleStyle));
        }

        public boolean performClick() {
            if (super.performClick()) {
                return true;
            }
            this.playSoundEffect(0);
            return true;
        }
    }

}

