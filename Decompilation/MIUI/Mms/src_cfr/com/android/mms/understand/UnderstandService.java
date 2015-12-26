/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Service
 *  android.content.ClipData
 *  android.content.ClipboardManager
 *  android.content.ContentResolver
 *  android.content.ContentValues
 *  android.content.Context
 *  android.content.Intent
 *  android.net.Uri
 *  android.os.IBinder
 *  android.text.TextUtils
 *  android.widget.Toast
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Thread
 */
package com.android.mms.understand;

import android.app.Service;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.text.TextUtils;
import android.widget.Toast;
import com.android.mms.transaction.MessagingNotification;

public class UnderstandService
extends Service {
    private void markMessageAsReadSync(Uri uri) {
        ContentValues contentValues = new ContentValues(2);
        contentValues.put("read", Integer.valueOf((int)1));
        contentValues.put("seen", Integer.valueOf((int)1));
        this.getContentResolver().update(uri, contentValues, null, null);
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
    }

    public int onStartCommand(Intent intent, int n, int n2) {
        if (intent != null) {
            String string2 = intent.getStringExtra("extra_text");
            final Uri uri = intent.getData();
            if (!TextUtils.isEmpty((CharSequence)string2)) {
                ((ClipboardManager)this.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText((CharSequence)null, (CharSequence)string2));
                Toast.makeText((Context)this, (CharSequence)this.getString(2131361821), (int)0).show();
                new Thread(new Runnable(){

                    @Override
                    public void run() {
                        UnderstandService.this.markMessageAsReadSync(uri);
                    }
                }).start();
                MessagingNotification.cancelNotification((Context)this, 123);
                MessagingNotification.cancelFloatNotification((Context)this);
            }
        }
        return super.onStartCommand(intent, n, n2);
    }

}

