/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.AlertDialog
 *  android.app.AlertDialog$Builder
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  android.preference.ListPreference
 *  android.text.TextUtils
 *  android.util.AttributeSet
 *  android.view.View
 *  android.widget.ImageView
 *  android.widget.TextView
 *  java.lang.String
 *  miui.R
 *  miui.R$id
 *  miui.R$layout
 */
package com.android.mms.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.preference.ListPreference;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import miui.R;

public class ValueListPreference
extends ListPreference {
    private int mListTitleId;
    private String mRightString;

    public ValueListPreference(Context context) {
        this(context, null);
    }

    public ValueListPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.setLayoutResource(R.layout.preference_value);
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void onBindView(View view) {
        super.onBindView(view);
        TextView textView = (TextView)view.findViewById(R.id.value_right);
        if (textView != null) {
            if (!TextUtils.isEmpty((CharSequence)this.mRightString)) {
                textView.setText((CharSequence)this.mRightString);
                textView.setVisibility(0);
            } else {
                textView.setVisibility(8);
            }
        }
        textView = (TextView)view.findViewById(16908304);
        CharSequence charSequence = this.getSummary();
        if (textView != null) {
            if (!TextUtils.isEmpty((CharSequence)charSequence)) {
                textView.setVisibility(0);
            } else {
                textView.setVisibility(8);
            }
        }
        if ((textView = (ImageView)view.findViewById(R.id.arrow_right)) != null) {
            textView.setVisibility(0);
        }
        if ((view = (TextView)view.findViewById(16908310)) != null) {
            view.setSingleLine(false);
            view.setMaxLines(2);
        }
    }

    protected void onPrepareDialogBuilder(AlertDialog.Builder builder) {
        super.onPrepareDialogBuilder(builder);
        if (this.mListTitleId > 0) {
            builder.setTitle(this.mListTitleId);
        }
        builder.setNegativeButton(null, null);
    }

    public void setListTitleId(int n) {
        this.mListTitleId = n;
    }

    public void setRightValue(String string) {
        this.mRightString = string;
    }
}

