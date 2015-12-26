/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.TypedArray
 *  android.text.TextUtils
 *  android.text.TextUtils$TruncateAt
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.widget.ImageView
 *  android.widget.RelativeLayout
 *  android.widget.RelativeLayout$LayoutParams
 *  android.widget.TextView
 *  java.lang.Math
 *  java.lang.String
 */
package cn.com.xy.sms.sdk.ui.popu.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.com.xy.sms.sdk.smsmessage.BusinessSmsMessage;
import cn.com.xy.sms.sdk.ui.popu.util.ContentUtil;
import cn.com.xy.sms.sdk.ui.popu.widget.DuoquBaseTable;
import java.util.List;

public class DuoquHorizLFItemTable
extends DuoquBaseTable {
    private int d = 0;
    private int e = 0;
    private int f = 0;
    private int g = 0;
    private int h = 0;
    private int i = 0;
    private int j = 0;
    private String k = null;
    private int l = 0;
    private int m = 0;
    private int n = 0;
    private int o = 0;
    private int p = 0;

    public DuoquHorizLFItemTable(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a(context, attributeSet);
    }

    private RelativeLayout.LayoutParams b(int n2) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        if (n2 > 1001) {
            layoutParams.addRule(3, this.b - 1);
            return layoutParams;
        }
        layoutParams.addRule(10);
        return layoutParams;
    }

    @Override
    protected RelativeLayout.LayoutParams a(int n2) {
        if (n2 % 4 == 1) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.p, this.h);
            layoutParams.addRule(9);
            layoutParams.addRule(10);
            layoutParams.setMargins(0, 0, this.m, 0);
            return layoutParams;
        }
        if (n2 % 4 == 2 || n2 % 4 == 3) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams.addRule(1, n2 - 1);
            layoutParams.addRule(11);
            layoutParams.addRule(10);
            return layoutParams;
        }
        if (n2 % 4 == 3) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams.addRule(1, n2 - 2);
            layoutParams.addRule(11);
            layoutParams.addRule(10);
            return layoutParams;
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, this.h);
        layoutParams.addRule(1, n2 - 3);
        layoutParams.addRule(11);
        layoutParams.addRule(10);
        return layoutParams;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected void a(int n2, BusinessSmsMessage businessSmsMessage, int n3, String string2, boolean bl2) {
        ep ep2 = new ep();
        ep2.f = new RelativeLayout(this.getContext());
        ep2.a = new TextView(this.getContext());
        ep2.b = new TextView(this.getContext());
        ep2.c = new TextView(this.getContext());
        ep2.d = new TextView(this.getContext());
        ep2.e = new ImageView(this.getContext());
        TextView textView = ep2.a;
        this.a = n3 = this.a + 1;
        textView.setId(n3);
        ep2.a.setGravity(16);
        ep2.a.setSingleLine(true);
        ep2.a.setEllipsize(TextUtils.TruncateAt.END);
        textView = this.a(this.a);
        textView.addRule(15, -1);
        ep2.f.addView((View)ep2.a, (ViewGroup.LayoutParams)textView);
        textView = ep2.b;
        this.a = n3 = this.a + 1;
        textView.setId(n3);
        ep2.b.setGravity(21);
        ep2.b.setPadding(0, this.n, 0, this.n);
        textView = this.a(this.a);
        ep2.b.setLineSpacing((float)ContentUtil.HORIZ_TABLE_TITLE_LINE_SPACING, 1.0f);
        ep2.b.setMinHeight(this.h);
        ep2.b.setVisibility(8);
        ep2.f.addView((View)ep2.b, (ViewGroup.LayoutParams)textView);
        textView = ep2.c;
        this.a = n3 = this.a + 1;
        textView.setId(n3);
        ep2.c.setGravity(21);
        ep2.c.setPadding(0, this.n, 0, this.n);
        textView = this.a(this.a);
        ep2.c.setLineSpacing((float)ContentUtil.HORIZ_TABLE_TITLE_LINE_SPACING, 1.0f);
        ep2.c.setMinHeight(this.h);
        ep2.c.setVisibility(8);
        ep2.c.setMaxLines(2);
        ep2.c.setEllipsize(TextUtils.TruncateAt.END);
        ep2.f.addView((View)ep2.c, (ViewGroup.LayoutParams)textView);
        textView = ep2.d;
        this.a = n3 = this.a + 1;
        textView.setId(n3);
        ep2.d.setGravity(21);
        textView = this.a(this.a);
        ep2.d.setSingleLine(true);
        ep2.d.setEllipsize(TextUtils.TruncateAt.END);
        ep2.d.setMinHeight(this.h);
        ep2.d.setVisibility(8);
        ep2.f.addView((View)ep2.d, (ViewGroup.LayoutParams)textView);
        textView = ep2.e;
        this.b = n3 = this.b + 1;
        textView.setId(n3);
        ep2.e.setBackgroundResource(br.c.duoqu_dotted_split);
        textView = new RelativeLayout.LayoutParams(-1, 3);
        textView.setMargins(this.l, 0, this.l, 0);
        if (1001 == this.b) {
            textView.addRule(10);
        } else {
            textView.addRule(3, this.b - 1);
            this.addView((View)ep2.e, (ViewGroup.LayoutParams)textView);
        }
        textView = ep2.f;
        this.b = n3 = this.b + 1;
        textView.setId(n3);
        ep2.f.setPadding(this.l, 0, this.l, 0);
        this.addView((View)ep2.f, (ViewGroup.LayoutParams)this.b(this.b));
        ep2.a.setTextSize(0, (float)this.d);
        ep2.b.setTextSize(0, (float)this.e);
        ep2.a(n2, businessSmsMessage, string2, bl2);
        this.c.add(ep2);
    }

    @Override
    protected void a(Context context, AttributeSet attributeSet) {
        context = context.obtainStyledAttributes(attributeSet, br.h.duoqu_table_attr);
        this.d = Math.round((float)context.getDimension(br.h.duoqu_table_attr_title_textsize, 0.0f));
        this.j = context.getResourceId(br.h.duoqu_table_attr_title_textcolor, Integer.MIN_VALUE);
        this.e = Math.round((float)context.getDimension(br.h.duoqu_table_attr_content_textsize, 0.0f));
        this.f = Math.round((float)context.getDimension(br.h.duoqu_table_attr_title_paddingtop, 0.0f));
        this.g = Math.round((float)context.getDimension(br.h.duoqu_table_attr_content_paddingleft, 0.0f));
        this.h = Math.round((float)context.getDimension(br.h.duoqu_table_attr_line_spacing, 0.0f));
        this.k = context.getString(br.h.duoqu_table_attr_single_line);
        this.i = Math.round((float)context.getDimension(br.h.duoqu_table_attr_margin_top, 0.0f));
        this.l = Math.round((float)context.getDimension(br.h.duoqu_table_attr_line_spacing_left, 0.0f));
        this.m = Math.round((float)context.getDimension(br.h.duoqu_table_attr_line_spacing_right, 0.0f));
        this.n = Math.round((float)context.getDimension(br.h.duoqu_table_attr_line_spacing_top, 0.0f));
        this.o = Math.round((float)context.getDimension(br.h.duoqu_table_attr_line_spacing_top_bigsize, 0.0f));
        this.p = Math.round((float)context.getDimension(br.h.duoqu_table_attr_title_width, 0.0f));
        context.recycle();
        if (this.i != 0) {
            context = new RelativeLayout.LayoutParams(-1, -2);
            context.setMargins(0, this.i, 0, 0);
            this.setLayoutParams((ViewGroup.LayoutParams)context);
        }
    }

    /*
     * Exception decompiling
     */
    @Override
    public void a(BusinessSmsMessage var1_1, int var2_3, String var3_4, boolean var4_5) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 1[TRYBLOCK]
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:394)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:446)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:2859)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:805)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:220)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:165)
        // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:91)
        // org.benf.cfr.reader.entities.Method.analyse(Method.java:354)
        // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:751)
        // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:683)
        // org.benf.cfr.reader.Main.doJar(Main.java:128)
        // org.benf.cfr.reader.Main.main(Main.java:178)
        throw new IllegalStateException("Decompilation failed");
    }
}

