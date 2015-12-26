/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Service
 *  android.content.ContentResolver
 *  android.content.ContentValues
 *  android.content.Context
 *  android.content.Intent
 *  android.database.sqlite.SqliteWrapper
 *  android.net.Uri
 *  android.os.Handler
 *  android.os.HandlerThread
 *  android.os.IBinder
 *  android.os.Looper
 *  android.text.TextUtils
 *  android.util.Pair
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.util.HashSet
 *  miui.telephony.PhoneNumberUtils
 *  miui.telephony.PhoneNumberUtils$PhoneNumber
 */
package com.android.mms.transaction;

import android.app.Service;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Pair;
import com.android.mms.data.Contact;
import com.android.mms.util.MSimUtils;
import com.xiaomi.mms.data.MidPhoneMap;
import com.xiaomi.mms.data.MxIdCache;
import com.xiaomi.mms.transaction.MxActivateService;
import com.xiaomi.mms.transaction.MxTaskService;
import com.xiaomi.mms.transaction.PushSession;
import com.xiaomi.mms.utils.B2cMessageUtils;
import java.util.HashSet;
import miui.telephony.PhoneNumberUtils;

public class SendWebMessageService
extends Service {
    private MxIdCache.MxCacheStatusListener mListener;
    private HashSet<Pair<String, Integer>> mPendingAddresses = new HashSet();
    private volatile Handler mServiceHandler;
    private Looper mServiceLooper;

    public SendWebMessageService() {
        this.mListener = new MxIdCache.MxCacheStatusListener(){

            @Override
            public void onMxIdAdded(String string, String string2) {
            }

            @Override
            public void onMxIdOffline(String string, final String string2) {
                SendWebMessageService.this.mServiceHandler.post(new Runnable(){

                    @Override
                    public void run() {
                        SendWebMessageService.this.sendAndRemovePendingAddress(string2, false, 0);
                        SendWebMessageService.this.sendAndRemovePendingAddress(string2, false, 1);
                    }
                });
            }

            @Override
            public void onMxIdOnline(String string, final String string2) {
                SendWebMessageService.this.mServiceHandler.post(new Runnable(){

                    @Override
                    public void run() {
                        SendWebMessageService.this.sendAndRemovePendingAddress(string2, true, 0);
                        SendWebMessageService.this.sendAndRemovePendingAddress(string2, true, 1);
                    }
                });
            }

        };
    }

    private String getCompareKey(String string) {
        string = PhoneNumberUtils.PhoneNumber.parse((CharSequence)string);
        String string2 = string.getPrefix() + string.getEffectiveNumber();
        string.recycle();
        return string2;
    }

    private void send(String string, boolean bl, int n) {
        this.send(string, bl, n, false);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void send(String string, boolean bl, int n, boolean bl2) {
        Uri uri = Uri.parse((String)"content://sms/queued");
        ContentValues contentValues = new ContentValues(1);
        long l = MSimUtils.getSimIdBySlotId(n);
        int n2 = bl ? 1 : 0;
        contentValues.put("mx_status", Integer.valueOf((int)n2));
        if (bl2) {
            contentValues.put("b2c_numbers", string);
        }
        SqliteWrapper.update((Context)this, (ContentResolver)this.getContentResolver(), (Uri)uri, (ContentValues)contentValues, (String)("sim_id=" + l + " AND " + "mx_status" + "=" + 196609 + " AND PHONE_NUMBERS_EQUAL(address, ?, 1)"), (String[])new String[]{string});
        MSimUtils.sendQueuedMessage((Context)this, n);
    }

    private void sendAfterQueryStatus(final String string, final int n) {
        this.mPendingAddresses.add((Object)Pair.create((Object)this.getCompareKey(string), (Object)n));
        MxTaskService.queryStatus((Context)this, string);
        this.mServiceHandler.postDelayed(new Runnable(){

            @Override
            public void run() {
                SendWebMessageService.this.sendAndRemovePendingAddress(string, false, n);
            }
        }, 10000);
    }

    private void sendAndRemovePendingAddress(String string, boolean bl, int n) {
        if (this.mPendingAddresses.remove((Object)Pair.create((Object)this.getCompareKey(string), (Object)n))) {
            this.send(string, bl, n);
        }
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        HandlerThread handlerThread = new HandlerThread("SendWebMessageService");
        handlerThread.start();
        this.mServiceLooper = handlerThread.getLooper();
        this.mServiceHandler = new Handler(this.mServiceLooper);
        MxIdCache.addStatusListener(this.mListener);
    }

    public void onDestroy() {
        MxIdCache.removeStatusListener(this.mListener);
        this.mServiceLooper.quit();
        super.onDestroy();
    }

    public int onStartCommand(Intent intent, int n, int n2) {
        if (intent == null) {
            return super.onStartCommand(intent, n, n2);
        }
        if ("com.android.mms.transaction.ACTION_SEND_WEB_MESSAGE".equals((Object)intent.getAction())) {
            final String string = intent.getStringExtra("extra_address");
            final String string2 = intent.getStringExtra("extra_mid");
            final int n3 = intent.getIntExtra("extra_slotId", 0);
            this.mServiceHandler.post(new Runnable(){

                @Override
                public void run() {
                    if (B2cMessageUtils.isB2cNumber(Contact.get(string))) {
                        SendWebMessageService.this.send(string, true, n3, true);
                        return;
                    }
                    if (!MxActivateService.isMxEnabled((Context)SendWebMessageService.this) || !PushSession.getInstance((Context)SendWebMessageService.this).isConnected(n3)) {
                        SendWebMessageService.this.send(string, false, n3);
                        return;
                    }
                    MxIdCache.MxIdCacheItem mxIdCacheItem = MxIdCache.get(string, true);
                    if (mxIdCacheItem == null) {
                        if (TextUtils.isEmpty((CharSequence)string2)) {
                            SendWebMessageService.this.send(string, false, n3);
                            return;
                        }
                        MidPhoneMap.put(string2, string);
                        MxIdCache.put(string, string2);
                        SendWebMessageService.this.sendAfterQueryStatus(string, n3);
                        return;
                    }
                    if (mxIdCacheItem.isExpired()) {
                        SendWebMessageService.this.sendAfterQueryStatus(string, n3);
                        return;
                    }
                    SendWebMessageService.this.send(string, mxIdCacheItem.allowSms(), n3);
                }
            });
        }
        return super.onStartCommand(intent, n, n2);
    }

}

