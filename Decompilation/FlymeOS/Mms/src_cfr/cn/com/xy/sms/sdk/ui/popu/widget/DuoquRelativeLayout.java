/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.TypedArray
 *  android.util.AttributeSet
 *  android.widget.RelativeLayout
 *  java.lang.Object
 */
package cn.com.xy.sms.sdk.ui.popu.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import cn.com.xy.sms.sdk.ui.popu.util.ViewManger;

public class DuoquRelativeLayout
extends RelativeLayout
implements er {
    public TypedArray a = null;

    public DuoquRelativeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = context.obtainStyledAttributes(attributeSet, br.h.duoqu_attr);
    }

    @Override
    public Object a(byte by2, int n2) {
        return ViewManger.obtainStyledAttributes(this.a, by2, n2);
    }
}

