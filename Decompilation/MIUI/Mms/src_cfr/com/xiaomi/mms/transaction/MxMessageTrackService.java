/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.AlarmManager
 *  android.app.IntentService
 *  android.app.PendingIntent
 *  android.content.ComponentName
 *  android.content.ContentResolver
 *  android.content.ContentUris
 *  android.content.Context
 *  android.content.Intent
 *  android.database.sqlite.SqliteWrapper
 *  android.net.Uri
 *  android.net.Uri$Builder
 *  android.provider.Telephony
 *  android.provider.Telephony$Mms
 *  android.text.TextUtils
 *  java.lang.Object
 *  java.lang.String
 */
package com.xiaomi.mms.transaction;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.provider.Telephony;
import android.text.TextUtils;
import com.android.mms.data.Contact;
import com.android.mms.ui.MessageUtils;
import com.android.mms.util.MSimUtils;
import com.xiaomi.mms.data.MxIdCache;
import com.xiaomi.mms.utils.MxMessagePduHelper;

public class MxMessageTrackService
extends IntentService {
    public MxMessageTrackService() {
        super("MxMessageTrackService");
    }

    private String getAddrForMsg(long l) {
        Uri uri;
        String string2;
        block4 : {
            string2 = null;
            String string3 = null;
            uri = Telephony.Mms.CONTENT_URI.buildUpon().appendPath(String.valueOf((long)l)).appendPath("addr").build();
            uri = SqliteWrapper.query((Context)this, (ContentResolver)this.getContentResolver(), (Uri)uri, (String[])null, (String)"type=151", (String[])null, (String)null);
            if (uri != null) {
                string2 = string3;
                if (!uri.moveToFirst()) break block4;
                string2 = uri.getString(uri.getColumnIndexOrThrow("address"));
            }
        }
        return string2;
        finally {
            uri.close();
        }
    }

    private void scheduleNext(long l) {
        Intent intent = new Intent("com.xiaomi.mms.mx.ACTION_START_TRACK");
        intent.setPackage(this.getPackageName());
        intent = PendingIntent.getService((Context)this, (int)0, (Intent)intent, (int)268435456);
        ((AlarmManager)this.getSystemService("alarm")).set(0, l, (PendingIntent)intent);
    }

    public static void startTrack(Context context) {
        Intent intent = new Intent("com.xiaomi.mms.mx.ACTION_START_TRACK");
        intent.setPackage(context.getPackageName());
        context.startService(intent);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    protected void onHandleIntent(Intent intent) {
        long l;
        block24 : {
            long l2;
            int n;
            int n2;
            block23 : {
                block22 : {
                    boolean bl;
                    String string2;
                    block21 : {
                        if (intent == null) {
                            return;
                        }
                        if (!"com.xiaomi.mms.mx.ACTION_START_TRACK".equals((Object)intent.getAction())) return;
                        intent = MessageUtils.getExpiredMxSms((Context)this);
                        if (intent != null) {
                            if (!intent.moveToFirst()) break block21;
                            do {
                                n2 = intent.getColumnIndexOrThrow("mx_id");
                                int n3 = intent.getColumnIndexOrThrow("address");
                                int n4 = intent.getColumnIndexOrThrow("sim_id");
                                n = intent.getColumnIndexOrThrow("b2c_numbers");
                                l2 = intent.getLong(n2);
                                String string3 = intent.getString(n3);
                                n2 = MSimUtils.getSlotIdBySimInfoId(intent.getLong(n4));
                                string2 = intent.getString(n);
                                if (!TextUtils.isEmpty((CharSequence)string3) && (string3 = Contact.get(string3).getMxPhoneNumber()) != null) {
                                    MxIdCache.offline(string3);
                                }
                                bl = !TextUtils.isEmpty((CharSequence)string2);
                                MessageUtils.handleMxSmsFailed((Context)this, l2, n2, bl);
                            } while (bl = intent.moveToNext());
                        }
                    }
                    if ((intent = MxMessagePduHelper.getExpiredMxMms((Context)this)) != null) {
                        if (!intent.moveToFirst()) break block22;
                        do {
                            n = intent.getColumnIndexOrThrow("_id");
                            n2 = intent.getColumnIndexOrThrow("mx_id");
                            l2 = intent.getLong(n);
                            intent.getLong(n2);
                            string2 = this.getAddrForMsg(l2);
                            if (!TextUtils.isEmpty((CharSequence)string2) && (string2 = Contact.get(string2).getMxPhoneNumber()) != null) {
                                MxIdCache.offline(string2);
                            }
                            MxMessagePduHelper.setResponseStatus((Context)this, ContentUris.withAppendedId((Uri)Telephony.Mms.CONTENT_URI, (long)l2), 224);
                            MxMessagePduHelper.handleMxMmsFailed((Context)this, l2);
                        } while (bl = intent.moveToNext());
                    }
                }
                intent = MessageUtils.getUncompletedMxSms((Context)this);
                l2 = l = Long.MAX_VALUE;
                if (intent != null) {
                    l2 = l;
                    if (!intent.moveToFirst()) break block23;
                    n = intent.getColumnIndexOrThrow("mx_id");
                    n2 = intent.getColumnIndexOrThrow("out_time");
                    intent.getLong(n);
                    l2 = intent.getLong(n2);
                    l2 += 300000;
                }
            }
            intent = MxMessagePduHelper.getIncompleteMxMms((Context)this);
            l = l2;
            if (intent != null) {
                l = l2;
                if (!intent.moveToFirst()) break block24;
                n = intent.getColumnIndexOrThrow("mx_id");
                n2 = intent.getColumnIndexOrThrow("out_time");
                intent.getLong(n);
                l = intent.getLong(n2);
                long l3 = l + 300000;
                l = l2;
                if (l3 >= l2) break block24;
                l = l3;
            }
        }
        if (l >= Long.MAX_VALUE) return;
        this.scheduleNext(l);
        return;
        finally {
            intent.close();
        }
        finally {
            intent.close();
        }
        finally {
            intent.close();
        }
        finally {
            intent.close();
        }
    }
}

