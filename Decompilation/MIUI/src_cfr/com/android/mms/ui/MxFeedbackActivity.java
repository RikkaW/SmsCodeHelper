/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.os.Bundle
 *  android.text.Editable
 *  android.text.TextUtils
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.Button
 *  android.widget.EditText
 *  android.widget.Toast
 *  java.lang.Object
 *  java.lang.String
 *  miui.app.Activity
 */
package com.android.mms.ui;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.xiaomi.mms.transaction.MxFeedbackService;
import miui.app.Activity;

public class MxFeedbackActivity
extends Activity {
    private EditText mDsptEditView;
    private Button mSubmitBtn;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2130968673);
        this.mDsptEditView = (EditText)this.findViewById(2131820804);
        this.mSubmitBtn = (Button)this.findViewById(2131820805);
        this.mSubmitBtn.setOnClickListener(new View.OnClickListener(){

            public void onClick(View object) {
                object = MxFeedbackActivity.this.mDsptEditView.getText().toString();
                if (TextUtils.isEmpty((CharSequence)object)) {
                    Toast.makeText((Context)MxFeedbackActivity.this, (int)2131362286, (int)0).show();
                    return;
                }
                MxFeedbackService.startMxFeedback((android.app.Activity)MxFeedbackActivity.this, (String)object);
                MxFeedbackActivity.this.finish();
            }
        });
    }

    protected void onResume() {
        super.onResume();
        this.mDsptEditView.requestFocus();
    }

}

