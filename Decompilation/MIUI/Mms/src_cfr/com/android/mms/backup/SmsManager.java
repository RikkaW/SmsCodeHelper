/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentProviderOperation
 *  android.content.ContentProviderOperation$Builder
 *  android.content.ContentProviderResult
 *  android.content.ContentResolver
 *  android.content.ContentValues
 *  android.content.Context
 *  android.content.OperationApplicationException
 *  android.database.Cursor
 *  android.net.Uri
 *  android.net.Uri$Builder
 *  android.os.RemoteException
 *  android.provider.Telephony
 *  android.provider.Telephony$Sms
 *  android.text.TextUtils
 *  android.util.Log
 *  java.lang.Boolean
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.ArrayList
 *  java.util.HashSet
 *  java.util.Vector
 */
package com.android.mms.backup;

import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.provider.Telephony;
import android.text.TextUtils;
import android.util.Log;
import com.android.mms.backup.SmsProtos;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Vector;

public class SmsManager {
    private String COLUMN_ADDRESS = "address";
    private String COLUMN_DATE = "date";
    private String COLUMN_ID = "_id";
    private Uri CONTENT_URI = Telephony.Sms.CONTENT_URI;
    private Uri INSERT_URI = this.CONTENT_URI.buildUpon().appendQueryParameter("need_full_insert_uri", "1").appendQueryParameter("check_duplication", "1").build();
    private ArrayList<ContentProviderOperation> mBatchOpertions = new ArrayList();
    private HashSet<String> mExistingMessages;
    private ContentResolver mResolver;

    public SmsManager(Context context) {
        this.mResolver = context.getContentResolver();
        this.mExistingMessages = new HashSet();
    }

    private String makeKey(long l, String string) {
        int n;
        if (string == null) {
            return "" + l + "$";
        }
        int n2 = n = string.length() - 7;
        if (n < 0) {
            n2 = 0;
        }
        string = string.substring(n2, string.length());
        return "" + l + "$" + string;
    }

    /*
     * Enabled aggressive block sorting
     */
    private ContentValues prepareContentValues(SmsProtos.Sms sms) {
        int n;
        ContentValues contentValues;
        block6 : {
            contentValues = new ContentValues();
            int n2 = sms.getType();
            if (n2 != 4) {
                n = n2;
                if (n2 != 6) break block6;
            }
            n = 5;
        }
        contentValues.put("type", Integer.valueOf((int)n));
        String string = sms.getAddress();
        if (!TextUtils.isEmpty((CharSequence)string)) {
            contentValues.put("address", string);
        }
        if (sms.getSubject() != null) {
            contentValues.put("subject", sms.getSubject());
        }
        if (sms.getBody() != null) {
            contentValues.put("body", sms.getBody());
        }
        contentValues.put("date", Long.valueOf((long)sms.getDate()));
        n = sms.getRead() ? 1 : 0;
        contentValues.put("read", Integer.valueOf((int)n));
        contentValues.put("seen", Boolean.valueOf((boolean)sms.getSeen()));
        contentValues.put("status", Integer.valueOf((int)sms.getStatus()));
        contentValues.put("date_sent", Long.valueOf((long)sms.getServerDate()));
        if (sms.getServiceCenter() != null) {
            contentValues.put("service_center", sms.getServiceCenter());
        }
        contentValues.put("protocol", Integer.valueOf((int)sms.getProtocol()));
        contentValues.put("reply_path_present", Boolean.valueOf((boolean)sms.getReplyPathPresent()));
        contentValues.put("locked", Boolean.valueOf((boolean)sms.getLocked()));
        contentValues.put("mx_id", Long.valueOf((long)sms.getMxId()));
        contentValues.put("mx_status", Integer.valueOf((int)sms.getMxStatus()));
        return contentValues;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void setSmsField(SmsProtos.Sms.Builder builder, Cursor cursor, int n) {
        boolean bl = false;
        boolean bl2 = false;
        boolean bl3 = false;
        boolean bl4 = false;
        String string = cursor.getColumnName(n);
        if (string.equals((Object)"_id")) {
            builder.setLuid(String.valueOf((long)cursor.getLong(n)));
            return;
        } else {
            if (string.equals((Object)"type")) {
                block20 : {
                    int n2 = cursor.getInt(n);
                    if (n2 != 4) {
                        n = n2;
                        if (n2 != 6) break block20;
                    }
                    n = 5;
                }
                builder.setType(n);
                return;
            }
            if (string.equals((Object)"address") && cursor.getString(n) != null) {
                builder.setAddress(cursor.getString(n));
                return;
            }
            if (string.equals((Object)"subject") && cursor.getString(n) != null) {
                builder.setSubject(cursor.getString(n));
                return;
            }
            if (string.equals((Object)"body") && cursor.getString(n) != null) {
                builder.setBody(cursor.getString(n));
                return;
            }
            if (string.equals((Object)"date")) {
                builder.setDate(cursor.getLong(n));
                return;
            }
            if (string.equals((Object)"read")) {
                if (cursor.getInt(n) != 0) {
                    bl4 = true;
                }
                builder.setRead(bl4);
                return;
            }
            if (string.equals((Object)"seen")) {
                bl4 = cursor.getInt(n) == 0 ? bl : true;
                builder.setSeen(bl4);
                return;
            }
            if (string.equals((Object)"status")) {
                builder.setStatus(cursor.getInt(n));
                return;
            }
            if (string.equals((Object)"date_sent")) {
                builder.setServerDate(cursor.getLong(n));
                return;
            }
            if (string.equals((Object)"service_center") && cursor.getString(n) != null) {
                builder.setServiceCenter(cursor.getString(n));
                return;
            }
            if (string.equals((Object)"protocol") && cursor.getString(n) != null) {
                builder.setProtocol(cursor.getInt(n));
                return;
            }
            if (string.equals((Object)"reply_path_present") && cursor.getString(n) != null) {
                bl4 = cursor.getInt(n) == 0 ? bl2 : true;
                builder.setReplyPathPresent(bl4);
                return;
            }
            if (string.equals((Object)"locked") && cursor.getString(n) != null) {
                bl4 = cursor.getInt(n) == 0 ? bl3 : true;
                builder.setLocked(bl4);
                return;
            }
            if (string.equals((Object)"mx_id") && cursor.getString(n) != null) {
                builder.setMxId(cursor.getLong(n));
                return;
            }
            if (!string.equals((Object)"mx_status") || cursor.getString(n) == null) return;
            {
                builder.setMxStatus(cursor.getInt(n));
                return;
            }
        }
    }

    public boolean add(SmsProtos.Sms sms) {
        long l = sms.getDate();
        String string = sms.getAddress();
        sms = this.prepareContentValues(sms);
        if (this.exists(l, string)) {
            return false;
        }
        sms = ContentProviderOperation.newInsert((Uri)this.INSERT_URI).withValues((ContentValues)sms).build();
        this.mBatchOpertions.add((Object)sms);
        this.mExistingMessages.add((Object)this.makeKey(l, string));
        return true;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public ContentProviderResult[] apply() {
        ContentProviderResult[] arrcontentProviderResult = null;
        try {
            ContentProviderResult[] arrcontentProviderResult2;
            arrcontentProviderResult = arrcontentProviderResult2 = this.mResolver.applyBatch("sms", this.mBatchOpertions);
        }
        catch (RemoteException var2_3) {
            Log.e((String)"SmsManager", (String)"RemoteException", (Throwable)var2_3);
        }
        catch (OperationApplicationException var2_4) {
            Log.e((String)"SmsManager", (String)"OperationApplicationException", (Throwable)var2_4);
        }
        this.mBatchOpertions.clear();
        return arrcontentProviderResult;
    }

    public boolean exists(long l, String string) {
        return this.mExistingMessages.contains((Object)this.makeKey(l, string));
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public SmsProtos.Sms load(long l) {
        SmsProtos.Sms.Builder builder;
        block6 : {
            Cursor cursor;
            builder = SmsProtos.Sms.newBuilder();
            Cursor cursor2 = null;
            try {
                cursor = this.mResolver.query(this.CONTENT_URI, null, this.COLUMN_ID + "=" + l, null, "date DESC LIMIT 1");
                if (cursor != null) {
                    cursor2 = cursor;
                    if (cursor.moveToFirst()) {
                        cursor2 = cursor;
                        for (int i = cursor.getColumnCount() - 1; i >= 0; --i) {
                            cursor2 = cursor;
                            this.setSmsField(builder, cursor, i);
                        }
                    }
                }
                if (cursor == null) break block6;
            }
            catch (Throwable var5_5) {
                if (cursor2 != null) {
                    cursor2.close();
                }
                throw var5_5;
            }
            cursor.close();
        }
        return builder.build();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public Vector<String> prepareAllSmsIds() {
        Cursor cursor;
        Vector vector;
        Cursor cursor2;
        block8 : {
            vector = new Vector();
            cursor2 = null;
            cursor = this.mResolver.query(Telephony.Sms.CONTENT_URI, new String[]{"_id"}, "timed=0 AND mx_status!=1 AND mx_status!=16 AND (type=1 OR type=5 OR type=2)", null, "date ASC");
            if (cursor != null) break block8;
            if (cursor == null) return vector;
            {
                cursor.close();
                return vector;
            }
        }
        cursor2 = cursor;
        try {
            int n = cursor.getColumnIndexOrThrow("_id");
            cursor2 = cursor;
            cursor.moveToFirst();
            do {
                cursor2 = cursor;
                if (!cursor.isAfterLast()) {
                    cursor2 = cursor;
                    vector.add((Object)String.valueOf((long)cursor.getLong(n)));
                    cursor2 = cursor;
                    cursor.moveToNext();
                    continue;
                }
                break;
            } while (true);
        }
        catch (Throwable var3_4) {
            if (cursor2 == null) throw var3_4;
            {
                cursor2.close();
            }
            throw var3_4;
        }
        if (cursor == null) return vector;
        {
            cursor.close();
            return vector;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public void prepareSms() {
        this.mExistingMessages.clear();
        String string = this.COLUMN_DATE;
        String string2 = this.COLUMN_ADDRESS;
        string = this.mResolver.query(this.CONTENT_URI, new String[]{string, string2}, null, null, null);
        if (string == null) {
            return;
        }
        int n = string.getColumnIndexOrThrow(this.COLUMN_DATE);
        int n2 = string.getColumnIndexOrThrow(this.COLUMN_ADDRESS);
        string.moveToFirst();
        do {
            if (string.isAfterLast()) {
                if (string == null) return;
                string.close();
                return;
            }
            long l = string.getLong(n);
            string2 = string.getString(n2);
            this.mExistingMessages.add((Object)this.makeKey(l, string2));
            string.moveToNext();
        } while (true);
    }
}

