/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.annotation.SuppressLint
 *  android.content.Context
 *  android.content.res.TypedArray
 *  android.text.TextUtils
 *  android.text.TextUtils$TruncateAt
 *  android.util.AttributeSet
 *  android.util.Log
 *  android.view.View
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.widget.ImageView
 *  android.widget.RelativeLayout
 *  android.widget.RelativeLayout$LayoutParams
 *  android.widget.TextView
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 */
package cn.com.xy.sms.sdk.ui.popu.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.com.xy.sms.sdk.smsmessage.BusinessSmsMessage;
import cn.com.xy.sms.sdk.ui.popu.widget.DuoquBaseTable;
import java.util.ArrayList;
import java.util.List;

public class DuoquHorizItemTable
extends DuoquBaseTable {
    private int d = 0;
    private int e = 0;
    private int f = 0;
    private int g = 0;
    private int h = 0;
    private int i = 0;
    private int j = 0;
    private int k = 0;
    private int l = 0;
    private int m = 0;
    private String n = null;

    public DuoquHorizItemTable(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a(context, attributeSet);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public RelativeLayout.LayoutParams a(int n2) {
        RelativeLayout.LayoutParams layoutParams;
        if (n2 % 3 - 2 == 0) {
            layoutParams = new RelativeLayout.LayoutParams(this.m, this.i);
            layoutParams.setMargins(this.j, 0, this.k, 0);
        } else {
            layoutParams = new RelativeLayout.LayoutParams(-1, this.i);
            layoutParams.setMargins(0, 0, this.j, 0);
        }
        if (n2 == 3 || n2 == 2) {
            layoutParams.addRule(10);
        }
        if (n2 % 3 - 2 == 0) {
            layoutParams.addRule(9);
        } else {
            layoutParams.addRule(1, n2 - 1);
            layoutParams.addRule(11);
        }
        if (n2 - 3 > 0) {
            layoutParams.addRule(3, (n2 - 1) / 3 * 3 + 1);
        }
        return layoutParams;
    }

    /*
     * Enabled aggressive block sorting
     */
    @SuppressLint(value={"ResourceAsColor"})
    @Override
    protected void a(int n2, BusinessSmsMessage businessSmsMessage, int n3, String string2, boolean bl2) {
        eq eq2 = new eq();
        eq2.a = new TextView(this.getContext());
        eq2.d = new TextView(this.getContext());
        ImageView imageView = eq2.e = new ImageView(this.getContext());
        this.a = n3 = this.a + 1;
        imageView.setId(n3);
        eq2.e.setBackgroundResource(br.c.duoqu_dotted_split);
        imageView = new RelativeLayout.LayoutParams(-1, 3);
        imageView.setMargins(this.j, 0, this.j, 0);
        if (1 == this.a) {
            imageView.addRule(10);
        } else {
            imageView.addRule(3, this.a - 2);
            this.addView((View)eq2.e, (ViewGroup.LayoutParams)imageView);
        }
        imageView = eq2.a;
        this.a = n3 = this.a + 1;
        imageView.setId(n3);
        eq2.a.setGravity(16);
        imageView = this.a(this.a);
        this.addView((View)eq2.a, (ViewGroup.LayoutParams)imageView);
        imageView = eq2.d;
        this.a = n3 = this.a + 1;
        imageView.setId(n3);
        eq2.d.setGravity(21);
        imageView = this.a(this.a);
        this.addView((View)eq2.d, (ViewGroup.LayoutParams)imageView);
        eq2.a.setTextSize(0, (float)this.d);
        eq2.d.setTextSize(0, (float)this.e);
        if (this.f != Integer.MIN_VALUE) {
            eq2.a.setTextColor(this.f);
        }
        if ("true".equals((Object)this.n)) {
            eq2.d.setSingleLine();
            eq2.d.setEllipsize(TextUtils.TruncateAt.valueOf((String)"END"));
            eq2.a.setSingleLine();
            eq2.a.setEllipsize(TextUtils.TruncateAt.valueOf((String)"END"));
        }
        eq2.a(n2, businessSmsMessage, string2, bl2);
        this.c.add(eq2);
    }

    @Override
    protected void a(Context context, AttributeSet attributeSet) {
        context = context.obtainStyledAttributes(attributeSet, br.h.duoqu_table_attr);
        this.d = Math.round((float)context.getDimension(br.h.duoqu_table_attr_title_textsize, 0.0f));
        this.e = Math.round((float)context.getDimension(br.h.duoqu_table_attr_content_textsize, 0.0f));
        this.f = context.getResourceId(br.h.duoqu_table_attr_title_textcolor, Integer.MIN_VALUE);
        this.g = Math.round((float)context.getDimension(br.h.duoqu_table_attr_title_paddingtop, 0.0f));
        this.h = Math.round((float)context.getDimension(br.h.duoqu_table_attr_content_paddingleft, 0.0f));
        this.i = Math.round((float)context.getDimension(br.h.duoqu_table_attr_line_spacing, 0.0f));
        this.j = Math.round((float)context.getDimension(br.h.duoqu_table_attr_line_spacing_left, 0.0f));
        this.k = Math.round((float)context.getDimension(br.h.duoqu_table_attr_line_spacing_right, 0.0f));
        this.n = context.getString(br.h.duoqu_table_attr_single_line);
        this.l = Math.round((float)context.getDimension(br.h.duoqu_table_attr_margin_top, 0.0f));
        this.m = Math.round((float)context.getDimension(br.h.duoqu_table_attr_title_width, 0.0f));
        context.recycle();
        if (this.l != 0) {
            context = new RelativeLayout.LayoutParams(-1, -2);
            context.setMargins(0, this.l, 0, 0);
            this.setLayoutParams((ViewGroup.LayoutParams)context);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void a(BusinessSmsMessage businessSmsMessage, int n2, String string2, boolean bl2) {
        int n3 = 5;
        int n4 = 0;
        if (n2 == 0) {
            try {
                this.setVisibility(8);
                return;
            }
            catch (Exception var1_2) {
                var1_2.printStackTrace();
                return;
            }
        }
        if (n2 > 5) {
            n2 = n3;
        }
        this.setVisibility(0);
        this.c = new ArrayList();
        this.a = 0;
        Log.i((String)"getHolder", (String)("smsId=" + businessSmsMessage.smsId + "  dataSize=" + n2));
        this.removeAllViews();
        while (n4 < n2) {
            this.a(n4, businessSmsMessage, n2, string2, false);
            ++n4;
        }
    }
}

