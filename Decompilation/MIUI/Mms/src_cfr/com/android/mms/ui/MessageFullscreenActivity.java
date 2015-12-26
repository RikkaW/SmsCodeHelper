/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Intent
 *  android.os.Bundle
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.View$OnTouchListener
 *  android.widget.LinearLayout
 *  android.widget.RelativeLayout
 *  android.widget.TextView
 *  java.lang.Object
 *  java.lang.String
 *  miui.app.Activity
 */
package com.android.mms.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import miui.app.Activity;

public class MessageFullscreenActivity
extends Activity {
    private void finishWithAnimation() {
        this.finish();
        this.overridePendingTransition(0, 2131034117);
    }

    public void onBackPressed() {
        super.onBackPressed();
        this.overridePendingTransition(0, 2131034117);
    }

    protected void onCreate(Bundle object) {
        super.onCreate((Bundle)object);
        this.setContentView(2130968639);
        object = this.getIntent().getStringExtra("body");
        ((TextView)this.findViewById(2131820578)).setText((CharSequence)object);
        ((RelativeLayout)this.findViewById(2131820715)).setOnTouchListener(new View.OnTouchListener(){

            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    default: {
                        MessageFullscreenActivity.this.finishWithAnimation();
                    }
                    case 0: 
                }
                return true;
            }
        });
        ((LinearLayout)this.findViewById(2131820717)).setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                MessageFullscreenActivity.this.finishWithAnimation();
            }
        });
    }

}

