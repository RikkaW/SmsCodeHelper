/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.AttributeSet
 *  android.view.View
 *  android.widget.LinearLayout
 *  android.widget.TextView
 *  java.lang.String
 */
package com.android.mms.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PhraseListItem
extends LinearLayout {
    private TextView mPhraseBodyView;
    private String mPhraseSmsItem;

    public PhraseListItem(Context context) {
        super(context);
    }

    public PhraseListItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void bind(String string2) {
        this.mPhraseSmsItem = string2;
        this.mPhraseBodyView.setText((CharSequence)string2);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mPhraseBodyView = (TextView)this.findViewById(2131820841);
    }
}

