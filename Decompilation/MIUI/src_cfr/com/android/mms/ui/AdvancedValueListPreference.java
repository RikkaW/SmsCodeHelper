/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.text.TextUtils
 *  android.util.AttributeSet
 *  android.view.View
 *  android.widget.ImageView
 *  android.widget.TextView
 *  java.lang.Object
 *  java.lang.String
 */
package com.android.mms.ui;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.mms.ui.GetSimInfo;
import com.android.mms.ui.ValueListPreference;

public class AdvancedValueListPreference
extends ValueListPreference {
    private GetSimInfo mSimInfo;
    private int mSlotId = 0;

    public AdvancedValueListPreference(Context context) {
        super(context);
    }

    public AdvancedValueListPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public int getSlotId() {
        return this.mSlotId;
    }

    public void init(Context context, int n) {
        this.mSimInfo = (GetSimInfo)context;
        this.mSlotId = n;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected void onBindView(View object) {
        super.onBindView((View)object);
        TextView textView = (TextView)object.findViewById(16908310);
        TextView textView2 = (TextView)object.findViewById(16908304);
        object = (ImageView)object.findViewById(16908294);
        if (object != null) {
            object.setMinimumWidth(0);
            int n = this.mSimInfo.getSimDisplayIcon(this.mSlotId);
            if (n != 0) {
                object.setBackgroundResource(n);
                object.setVisibility(0);
            } else {
                object.setVisibility(8);
            }
        }
        textView.setVisibility(0);
        textView.setText((CharSequence)this.mSimInfo.getSimDisplayName(this.mSlotId));
        object = this.mSimInfo.getSimNumber(this.mSlotId);
        if (TextUtils.isEmpty((CharSequence)object)) {
            textView2.setVisibility(8);
            return;
        }
        textView2.setText((CharSequence)object);
        textView2.setVisibility(0);
    }

    public void setNotifyChanged(Context context, int n) {
        this.init(context, n);
        this.notifyChanged();
    }
}

