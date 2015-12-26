/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Intent
 *  android.os.Bundle
 *  android.os.Handler
 *  android.view.KeyEvent
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.Button
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 */
package com.android.mms.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

public class ConfirmRateLimitActivity
extends Activity {
    private long mCreateTime;
    private Handler mHandler;
    private Runnable mRunnable;

    private void doAnswer(boolean bl) {
        Intent intent = new Intent("com.android.mms.RATE_LIMIT_CONFIRMED");
        intent.putExtra("answer", bl);
        this.sendBroadcast(intent);
        this.finish();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.requestWindowFeature(1);
        this.setContentView(2130968591);
        ((Button)this.findViewById(2131820576)).setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                ConfirmRateLimitActivity.this.doAnswer(true);
            }
        });
        ((Button)this.findViewById(2131820577)).setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                ConfirmRateLimitActivity.this.doAnswer(false);
            }
        });
        this.mHandler = new Handler();
        this.mRunnable = new Runnable(){

            @Override
            public void run() {
                ConfirmRateLimitActivity.this.doAnswer(false);
            }
        };
        this.mCreateTime = System.currentTimeMillis();
    }

    public boolean onKeyDown(int n, KeyEvent keyEvent) {
        if (n == 4 && keyEvent.getRepeatCount() == 0) {
            this.doAnswer(false);
        }
        return super.onKeyDown(n, keyEvent);
    }

    protected void onPause() {
        super.onPause();
        if (this.mHandler != null) {
            this.mHandler.removeCallbacks(this.mRunnable);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void onResume() {
        super.onResume();
        long l = this.mCreateTime - System.currentTimeMillis() + 19500;
        if (l <= 0) {
            this.doAnswer(false);
            return;
        } else {
            if (this.mHandler == null) return;
            {
                this.mHandler.postDelayed(this.mRunnable, l);
                return;
            }
        }
    }

}

