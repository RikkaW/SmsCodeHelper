/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.preference.Preference
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
import android.preference.Preference;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.mms.ui.GetSimInfo;

public class AdvancedEditorPreference
extends Preference {
    private GetSimInfo mSimInfo;
    private int mSlotId = 0;

    public AdvancedEditorPreference(Context context) {
        this(context, null);
    }

    public AdvancedEditorPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.setLayoutResource(2130968577);
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
    protected void onBindView(View object) {
        super.onBindView((View)object);
        TextView textView = (TextView)object.findViewById(2131820545);
        TextView textView2 = (TextView)object.findViewById(2131820546);
        object = (ImageView)object.findViewById(2131820544);
        if (object != null) {
            int n = this.mSimInfo.getSimDisplayIcon(this.mSlotId);
            if (n != 0) {
                object.setBackgroundResource(n);
                object.setVisibility(0);
            } else {
                object.setVisibility(8);
            }
        }
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

