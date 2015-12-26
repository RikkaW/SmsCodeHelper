/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Intent
 *  android.content.res.Resources
 *  android.os.Bundle
 *  android.text.TextUtils
 *  android.view.View
 *  android.widget.TextView
 *  java.lang.Object
 *  java.lang.String
 *  miui.app.Activity
 */
package com.android.mms.ui;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import miui.app.Activity;

public class MessageSelectCopyTextActivity
extends Activity {
    /*
     * Enabled aggressive block sorting
     */
    protected void onCreate(Bundle object) {
        super.onCreate((Bundle)object);
        this.setContentView(2130968664);
        object = this.getIntent();
        Object object2 = (TextView)this.findViewById(2131820780);
        String string2 = object.getStringExtra("extra_contact");
        if (!TextUtils.isEmpty((CharSequence)string2)) {
            object2.setText((CharSequence)(this.getResources().getString(2131362372) + string2));
        } else {
            object2.setVisibility(8);
        }
        object2 = object.getStringExtra("extra_number");
        string2 = (TextView)this.findViewById(2131820781);
        if (!TextUtils.isEmpty((CharSequence)object2)) {
            string2.setText((CharSequence)(this.getResources().getString(2131362373) + (String)object2));
        } else {
            string2.setVisibility(8);
        }
        object = object.getStringExtra("extra_body");
        ((TextView)this.findViewById(2131820783)).setText((CharSequence)object);
    }
}

