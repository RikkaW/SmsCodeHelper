/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  android.content.ContentUris
 *  android.content.ContentValues
 *  android.content.Context
 *  android.database.Cursor
 *  android.database.sqlite.SqliteWrapper
 *  android.net.Uri
 *  android.net.Uri$Builder
 *  android.provider.Telephony
 *  android.provider.Telephony$Mms
 *  android.provider.Telephony$Mms$Inbox
 *  android.provider.Telephony$Threads
 *  android.text.TextUtils
 *  com.google.android.mms.pdu.EncodedStringValue
 *  com.google.android.mms.pdu.MiuiPduPersister
 *  com.google.android.mms.util.SqliteWrapper
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 */
package com.xiaomi.mms.utils;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.provider.Telephony;
import android.text.TextUtils;
import com.android.mms.ui.MessageUtils;
import com.google.android.mms.pdu.EncodedStringValue;
import com.google.android.mms.pdu.MiuiPduPersister;
import com.xiaomi.mms.mx.utils.Mx2ExtentionHelper;
import com.xiaomi.mms.utils.logger.MyLog;

public class MxMessagePduHelper {
    private static final String[] NOTIFICATION_IND_PROJECTION;
    private static final String[] THREAD_ID_PROJECT;

    static {
        THREAD_ID_PROJECT = new String[]{"thread_id"};
        NOTIFICATION_IND_PROJECTION = new String[]{"thread_id", "date", "date_ms_part", "date_sent", "exp", "sub", "sub_cs", "ct_t", "m_size", "mx_id", "sim_id", "block_type", "mx_type"};
    }

    protected static long findThreadIdForMms(ContentResolver contentResolver, long l) {
        if ((contentResolver = contentResolver.query(Telephony.Mms.CONTENT_URI, THREAD_ID_PROJECT, "mx_id=" + l, null, null)) != null) {
            try {
                if (contentResolver.moveToFirst()) {
                    l = contentResolver.getLong(0);
                    return l;
                }
            }
            finally {
                contentResolver.close();
            }
        }
        return -1;
    }

    public static Cursor getExpiredMxMms(Context context) {
        return context.getContentResolver().query(Telephony.Mms.CONTENT_URI, null, "(mx_status=16 OR mx_status=1) AND (out_time>0 AND out_time<" + (System.currentTimeMillis() - 300000) + ") AND (" + "mx_id" + ">0) AND (" + "mx_type" + "=0)", null, "out_time");
    }

    protected static String getIncomingMmsAddress(Context object, Uri uri) {
        block4 : {
            Object var5_3 = null;
            Object var4_4 = null;
            long l = ContentUris.parseId((Uri)uri);
            uri = Uri.parse((String)("content://mms/" + l + "/addr"));
            uri = com.google.android.mms.util.SqliteWrapper.query((Context)object, (ContentResolver)object.getContentResolver(), (Uri)uri, (String[])null, (String)"type=137", (String[])null, (String)null);
            object = var5_3;
            if (uri != null) {
                object = var4_4;
                if (!uri.moveToFirst()) break block4;
                object = uri.getString(uri.getColumnIndexOrThrow("address"));
            }
        }
        return object;
        finally {
            uri.close();
        }
    }

    public static Cursor getIncompleteMxMms(Context context) {
        return context.getContentResolver().query(Telephony.Mms.CONTENT_URI, null, "(mx_status=16 OR mx_status=1) AND (out_time>0) AND (mx_id>0) AND (m_type=128) AND (mx_type=0)", null, "out_time");
    }

    public static int getMessageMx2Type(Context context, long l) {
        int n;
        block4 : {
            n = 0;
            int n2 = 0;
            ContentResolver contentResolver = context.getContentResolver();
            Uri uri = Telephony.Mms.CONTENT_URI;
            String string2 = "_id=" + l;
            if ((context = com.google.android.mms.util.SqliteWrapper.query((Context)context, (ContentResolver)contentResolver, (Uri)uri, (String[])new String[]{"mx_type"}, (String)string2, (String[])null, (String)null)) != null) {
                n = n2;
                if (!context.moveToFirst()) break block4;
                n = context.getInt(0);
            }
        }
        return n;
        finally {
            context.close();
        }
    }

    public static Uri getMmsMessageUriFromMxId(ContentResolver contentResolver, long l, int n) {
        String string2;
        block4 : {
            Uri uri = Telephony.Mms.CONTENT_URI;
            Object var5_5 = null;
            Object var4_6 = null;
            string2 = "mx_id=" + l + " AND " + "msg_box" + "=" + n;
            string2 = contentResolver.query(uri, new String[]{"_id"}, string2, null, null);
            contentResolver = var5_5;
            if (string2 != null) {
                contentResolver = var4_6;
                if (!string2.moveToFirst()) break block4;
                contentResolver = ContentUris.withAppendedId((Uri)uri, (long)string2.getLong(0));
            }
        }
        return contentResolver;
        finally {
            string2.close();
        }
    }

    protected static String getOutgoingMmsAddress(Context object, Uri uri) {
        block4 : {
            Object var5_3 = null;
            Object var4_4 = null;
            long l = ContentUris.parseId((Uri)uri);
            uri = Uri.parse((String)("content://mms/" + l + "/addr"));
            uri = com.google.android.mms.util.SqliteWrapper.query((Context)object, (ContentResolver)object.getContentResolver(), (Uri)uri, (String[])null, (String)"type=151", (String[])null, (String)null);
            object = var5_3;
            if (uri != null) {
                object = var4_4;
                if (!uri.moveToFirst()) break block4;
                object = uri.getString(uri.getColumnIndexOrThrow("address"));
            }
        }
        return object;
        finally {
            uri.close();
        }
    }

    public static Cursor getPendingMxMessages(Context context) {
        return com.google.android.mms.util.SqliteWrapper.query((Context)context, (ContentResolver)context.getContentResolver(), (Uri)Telephony.Mms.CONTENT_URI, (String[])null, (String)"(mx_status=1 AND msg_box=2 AND out_time=0)", (String[])null, (String)null);
    }

    public static void handleMxMmsFailed(Context context, long l) {
        MxMessagePduHelper.handleMxMmsFailed(context, l, true, false);
    }

    public static void handleMxMmsFailed(Context context, long l, boolean bl) {
        MxMessagePduHelper.handleMxMmsFailed(context, l, bl, false);
    }

    /*
     * Exception decompiling
     */
    public static void handleMxMmsFailed(Context var0, long var1_3, boolean var3_4, boolean var4_5) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.CannotPerformDecode: reachable test BLOCK was exited and re-entered.
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.Misc.getFarthestReachableInRange(Misc.java:143)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.SwitchReplacer.examineSwitchContiguity(SwitchReplacer.java:385)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.SwitchReplacer.replaceRawSwitches(SwitchReplacer.java:65)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:422)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:220)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:165)
        // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:91)
        // org.benf.cfr.reader.entities.Method.analyse(Method.java:354)
        // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:751)
        // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:683)
        // org.benf.cfr.reader.Main.doJar(Main.java:128)
        // org.benf.cfr.reader.Main.main(Main.java:178)
        throw new IllegalStateException("Decompilation failed");
    }

    public static int markFailedMessageAsPending(Context context, Uri uri) {
        ContentValues contentValues = new ContentValues(1);
        contentValues.put("mx_status", Integer.valueOf((int)1));
        return com.google.android.mms.util.SqliteWrapper.update((Context)context, (ContentResolver)context.getContentResolver(), (Uri)uri, (ContentValues)contentValues, (String)"mx_status=131073", (String[])null);
    }

    public static void markMmsAsCommon(Context context, Uri uri) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("msg_box", Integer.valueOf((int)4));
        contentValues.put("mx_status", Integer.valueOf((int)0));
        contentValues.put("pri", Integer.valueOf((int)129));
        contentValues.put("v", Integer.valueOf((int)18));
        contentValues.put("d_rpt", Integer.valueOf((int)128));
        contentValues.putNull("st");
        context.getContentResolver().update(uri, contentValues, null, null);
    }

    public static void markMmsMxStatus(Context context, Uri uri, int n) {
        ContentValues contentValues = new ContentValues(1);
        contentValues.put("mx_status", Integer.valueOf((int)n));
        SqliteWrapper.update((Context)context, (ContentResolver)context.getContentResolver(), (Uri)uri, (ContentValues)contentValues, (String)null, (String[])null);
    }

    public static void markMmsPermanentFailed(Context context, Uri uri) {
        ContentValues contentValues = new ContentValues(3);
        contentValues.put("read", Integer.valueOf((int)0));
        contentValues.put("st", Integer.valueOf((int)135));
        contentValues.put("mx_status", Integer.valueOf((int)131073));
        com.google.android.mms.util.SqliteWrapper.update((Context)context, (ContentResolver)context.getContentResolver(), (Uri)uri, (ContentValues)contentValues, (String)null, (String[])null);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void markMmsSendAsMx(Context context, Uri uri, boolean bl) {
        ContentValues contentValues = new ContentValues(3);
        int n = bl ? 2 : 4;
        contentValues.put("msg_box", Integer.valueOf((int)n));
        n = bl ? 1 : 0;
        contentValues.put("mx_status", Integer.valueOf((int)n));
        contentValues.put("out_time", Integer.valueOf((int)0));
        context.getContentResolver().update(uri, contentValues, null, null);
    }

    public static void markMmsStatus(Context context, Uri uri, int n) {
        ContentValues contentValues = new ContentValues(1);
        contentValues.put("st", Integer.valueOf((int)n));
        SqliteWrapper.update((Context)context, (ContentResolver)context.getContentResolver(), (Uri)uri, (ContentValues)contentValues, (String)null, (String[])null);
    }

    public static void markMmsTransientFailed(Context context, Uri uri) {
        ContentValues contentValues = new ContentValues(3);
        contentValues.put("read", Integer.valueOf((int)0));
        contentValues.put("st", Integer.valueOf((int)130));
        contentValues.put("mx_status", Integer.valueOf((int)1));
        com.google.android.mms.util.SqliteWrapper.update((Context)context, (ContentResolver)context.getContentResolver(), (Uri)uri, (ContentValues)contentValues, (String)null, (String[])null);
    }

    public static void moveMxMmsToOutbox(Context context, Uri uri, long l, long l2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("out_time", Long.valueOf((long)l));
        contentValues.put("mx_id", Long.valueOf((long)l2));
        contentValues.put("m_id", String.valueOf((long)l2));
        contentValues.put("resp_st", Integer.valueOf((int)128));
        com.google.android.mms.util.SqliteWrapper.update((Context)context, (ContentResolver)context.getContentResolver(), (Uri)uri, (ContentValues)contentValues, (String)null, (String[])null);
    }

    public static int moveUnsentMessageToPending(Context context) {
        ContentValues contentValues = new ContentValues(1);
        contentValues.put("out_time", Integer.valueOf((int)0));
        return com.google.android.mms.util.SqliteWrapper.update((Context)context, (ContentResolver)context.getContentResolver(), (Uri)Telephony.Mms.CONTENT_URI, (ContentValues)contentValues, (String)"(mx_status=1 AND msg_box=2 AND out_time>0)", (String[])null);
    }

    protected static void persistAddressTo(Context context, long l, String string2, int n) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("address", string2);
        contentValues.put("charset", Integer.valueOf((int)106));
        contentValues.put("type", Integer.valueOf((int)n));
        string2 = Uri.parse((String)("content://mms/" + l + "/addr"));
        SqliteWrapper.insert((Context)context, (ContentResolver)context.getContentResolver(), (Uri)string2, (ContentValues)contentValues);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static Uri persistNotification(Context context, String string2, long l, long l2, long l3, String string3, String string4, long l4, String string5, long l5, long l6, int n, int n2, String string6) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("m_type", Integer.valueOf((int)130));
        contentValues.put("thread_id", Long.valueOf((long)Telephony.Threads.getOrCreateThreadId((Context)context, (String)string2)));
        contentValues.put("date", Long.valueOf((long)(l / 1000)));
        contentValues.put("date_ms_part", Long.valueOf((long)(l % 1000)));
        contentValues.put("date_sent", Long.valueOf((long)(l2 / 1000)));
        contentValues.put("msg_box", Integer.valueOf((int)1));
        contentValues.put("st", Integer.valueOf((int)132));
        contentValues.put("m_cls", "personal");
        contentValues.put("exp", Long.valueOf((long)(l3 / 1000)));
        contentValues.put("tr_id", string5);
        contentValues.put("ct_l", string5);
        string3 = new EncodedStringValue(string3);
        contentValues.put("sub", MiuiPduPersister.toIsoString((byte[])string3.getTextString()));
        contentValues.put("sub_cs", Integer.valueOf((int)string3.getCharacterSet()));
        contentValues.put("ct_t", string4);
        contentValues.put("m_size", Long.valueOf((long)l4));
        contentValues.put("mx_id", Long.valueOf((long)l5));
        contentValues.put("mx_status", Integer.valueOf((int)1));
        contentValues.put("sim_id", Long.valueOf((long)l6));
        contentValues.put("block_type", Integer.valueOf((int)n));
        contentValues.put("mx_type", Integer.valueOf((int)n2));
        if (!TextUtils.isEmpty((CharSequence)string6)) {
            contentValues.put("mx_extension", Mx2ExtentionHelper.convertServerExtensionToLocal(string6));
        }
        if (MessageUtils.isServiceNumber(context, string2)) {
            contentValues.put("advanced_seen", Integer.valueOf((int)1));
        } else {
            contentValues.put("advanced_seen", Integer.valueOf((int)0));
        }
        if (n2 > 0) {
            contentValues.put("st", Integer.valueOf((int)129));
            contentValues.put("m_type", Integer.valueOf((int)132));
        }
        string3 = com.google.android.mms.util.SqliteWrapper.insert((Context)context, (ContentResolver)context.getContentResolver(), (Uri)Telephony.Mms.Inbox.CONTENT_URI, (ContentValues)contentValues);
        MxMessagePduHelper.persistAddressTo(context, ContentUris.parseId((Uri)string3), string2, 137);
        return string3;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static Uri persistRetrieveConf(Context context, Uri uri) {
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = com.google.android.mms.util.SqliteWrapper.query((Context)context, (ContentResolver)contentResolver, (Uri)uri, (String[])NOTIFICATION_IND_PROJECTION, (String)null, (String[])null, (String)null);
        if (cursor == null) return null;
        if (!cursor.moveToFirst()) return null;
        long l = cursor.getLong(0);
        long l2 = cursor.getLong(1);
        long l3 = cursor.getLong(2);
        long l4 = cursor.getLong(3);
        long l5 = cursor.getLong(4);
        String string2 = cursor.getString(5);
        int n = cursor.getInt(6);
        String string3 = cursor.getString(7);
        long l6 = cursor.getLong(8);
        long l7 = cursor.getLong(9);
        int n2 = cursor.getInt(10);
        int n3 = cursor.getInt(11);
        cursor.getInt(12);
        Object object = new ContentValues();
        object.put("m_type", Integer.valueOf((int)132));
        object.put("thread_id", Long.valueOf((long)l));
        object.put("date", Long.valueOf((long)l2));
        object.put("date_sent", Long.valueOf((long)l4));
        object.put("date_ms_part", Long.valueOf((long)l3));
        object.put("exp", Long.valueOf((long)l5));
        object.put("sub", string2);
        object.put("sub_cs", Integer.valueOf((int)n));
        object.put("ct_t", string3);
        object.put("m_size", Long.valueOf((long)l6));
        object.put("resp_st", Integer.valueOf((int)128));
        object.put("mx_id", Long.valueOf((long)l7));
        object.put("sim_id", Integer.valueOf((int)n2));
        object.put("mx_status", Integer.valueOf((int)65537));
        object.put("block_type", Integer.valueOf((int)n3));
        cursor = uri;
        if (n3 > 1) {
            l = Long.valueOf((String)uri.getLastPathSegment());
            cursor = ContentUris.withAppendedId((Uri)Telephony.Mms.Inbox.CONTENT_URI, (long)l).buildUpon().appendQueryParameter("blocked_flag", "1").build();
        }
        uri = com.google.android.mms.util.SqliteWrapper.insert((Context)context, (ContentResolver)contentResolver, (Uri)Telephony.Mms.CONTENT_URI, (ContentValues)object);
        object = MxMessagePduHelper.getIncomingMmsAddress(context, (Uri)cursor);
        if (!TextUtils.isEmpty((CharSequence)object)) {
            MxMessagePduHelper.persistAddressTo(context, ContentUris.parseId((Uri)uri), (String)object, 137);
        }
        com.google.android.mms.util.SqliteWrapper.delete((Context)context, (ContentResolver)contentResolver, (Uri)cursor, (String)null, (String[])null);
        return uri;
        finally {
            cursor.close();
        }
    }

    public static void setResponseStatus(Context context, Uri uri, int n) {
        ContentValues contentValues = new ContentValues(1);
        contentValues.put("resp_st", Integer.valueOf((int)n));
        com.google.android.mms.util.SqliteWrapper.update((Context)context, (ContentResolver)context.getContentResolver(), (Uri)uri, (ContentValues)contentValues, (String)null, (String[])null);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static Uri updateMmsToDelivered(Context context, long l) {
        long l2;
        Uri uri;
        long l3;
        Object object;
        ContentResolver contentResolver;
        contentResolver = context.getContentResolver();
        l2 = MxMessagePduHelper.findThreadIdForMms(contentResolver, l);
        uri = MxMessagePduHelper.getMmsMessageUriFromMxId(contentResolver, l, 2);
        if (l2 <= 0) {
            return null;
        }
        if (uri == null) {
            return null;
        }
        l3 = System.currentTimeMillis();
        object = com.google.android.mms.util.SqliteWrapper.query((Context)context, (ContentResolver)contentResolver, (Uri)Telephony.Mms.CONTENT_URI, (String[])null, (String)("(mx_id=" + l + ") AND (" + "msg_box" + "=" + 2 + ") AND (" + "mx_status" + "!=" + 1 + " AND " + "mx_status" + "!=" + 16 + " AND " + "mx_status" + "!=" + 131073 + ")"), (String[])null, (String)null);
        if (object != null) {
            try {
                if (object.getCount() > 0) {
                    MyLog.w("MxMessagePduHelper", "mms status incorrect: " + l);
                    return null;
                }
            }
            finally {
                object.close();
            }
        }
        MxMessagePduHelper.getMessageMx2Type(context, ContentUris.parseId((Uri)uri));
        object = new ContentValues();
        object.put("mx_status", Integer.valueOf((int)17));
        object.put("st", Integer.valueOf((int)129));
        com.google.android.mms.util.SqliteWrapper.update((Context)context, (ContentResolver)contentResolver, (Uri)uri, (ContentValues)object, (String)null, (String[])null);
        object.clear();
        object.put("thread_id", Long.valueOf((long)l2));
        object.put("date", Long.valueOf((long)(l3 / 1000)));
        object.put("m_id", Long.valueOf((long)l));
        object.put("m_type", Integer.valueOf((int)134));
        object.put("st", Integer.valueOf((int)129));
        object.put("mx_id", Long.valueOf((long)l));
        object.put("mx_status", Integer.valueOf((int)17));
        contentResolver = com.google.android.mms.util.SqliteWrapper.insert((Context)context, (ContentResolver)contentResolver, (Uri)Telephony.Mms.Inbox.CONTENT_URI, (ContentValues)object);
        object = MxMessagePduHelper.getOutgoingMmsAddress(context, uri);
        uri = contentResolver;
        if (TextUtils.isEmpty((CharSequence)object)) return uri;
        MxMessagePduHelper.persistAddressTo(context, ContentUris.parseId((Uri)contentResolver), (String)object, 151);
        return contentResolver;
    }

    public static int updateMmsToSent(Context context, long l) {
        Uri uri = MxMessagePduHelper.getMmsMessageUriFromMxId(context.getContentResolver(), l, 2);
        if (uri == null) {
            return 0;
        }
        ContentValues contentValues = new ContentValues(2);
        contentValues.put("mx_status", Integer.valueOf((int)16));
        contentValues.put("msg_box", Integer.valueOf((int)2));
        return com.google.android.mms.util.SqliteWrapper.update((Context)context, (ContentResolver)context.getContentResolver(), (Uri)uri, (ContentValues)contentValues, (String)"mx_status=1", (String[])null);
    }
}

