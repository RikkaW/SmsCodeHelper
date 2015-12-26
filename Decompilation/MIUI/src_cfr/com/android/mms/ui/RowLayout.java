/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.TypedArray
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewGroup$MarginLayoutParams
 *  java.lang.Object
 *  java.util.ArrayList
 */
package com.android.mms.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.android.mms.R;
import java.util.ArrayList;

public class RowLayout
extends ViewGroup {
    private int mHorizontalSpacing = 0;
    protected ArrayList<RowInfo> mRows = new ArrayList();
    private int mVerticalSpacing = 0;

    public RowLayout(Context context) {
        super(context);
    }

    public RowLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.initAttributes(attributeSet);
    }

    public RowLayout(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.initAttributes(attributeSet);
    }

    private void initAttributes(AttributeSet attributeSet) {
        attributeSet = this.getContext().obtainStyledAttributes(attributeSet, R.styleable.RowLayout);
        this.setVerticalSpacing(attributeSet.getDimensionPixelSize(0, 0));
        this.setHorizontalSpacing(attributeSet.getDimensionPixelSize(1, 0));
        attributeSet.recycle();
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(this.getContext(), attributeSet);
    }

    public int getRowCount() {
        return this.mRows.size();
    }

    protected void onLayout(boolean bl, int n, int n2, int n3, int n4) {
        int n5 = 0;
        n4 = n2 + this.getPaddingTop();
        for (n2 = 0; n2 < this.mRows.size(); ++n2) {
            int n6 = n + this.getPaddingLeft();
            for (int i = 0; i < ((RowInfo)this.mRows.get((int)n2)).childCount; ++i) {
                View view = this.getChildAt(n5);
                int n7 = n6 + view.getMeasuredWidth();
                if (n5 == this.getChildCount() - 1) {
                    n7 = n3;
                }
                view.layout(n6, n4, n7, view.getMeasuredHeight() + n4);
                n6 = n7 + this.mHorizontalSpacing;
                ++n5;
            }
            n4 += ((RowInfo)this.mRows.get((int)n2)).height + this.mVerticalSpacing;
        }
    }

    /*
     * Exception decompiling
     */
    protected void onMeasure(int var1_1, int var2_2) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Statement already marked as first in another block
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.markFirstStatementInBlock(Op03SimpleStatement.java:412)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.Misc.markWholeBlock(Misc.java:219)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.ConditionalRewriter.considerAsSimpleIf(ConditionalRewriter.java:619)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.ConditionalRewriter.identifyNonjumpingConditionals(ConditionalRewriter.java:45)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:669)
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

    public void setHorizontalSpacing(int n) {
        this.mHorizontalSpacing = n;
        this.requestLayout();
    }

    public void setVerticalSpacing(int n) {
        this.mVerticalSpacing = n;
        this.requestLayout();
    }

    public static class LayoutParams
    extends ViewGroup.MarginLayoutParams {
        public LayoutParams(int n, int n2) {
            super(n, n2);
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }
    }

    public static class RowInfo {
        int childCount;
        int height;
        int width;
    }

}

