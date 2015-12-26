/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  android.content.ContentUris
 *  android.content.ContentValues
 *  android.content.Context
 *  android.database.Cursor
 *  android.database.sqlite.SQLiteException
 *  android.drm.DrmManagerClient
 *  android.net.Uri
 *  android.net.Uri$Builder
 *  android.provider.Telephony
 *  android.provider.Telephony$Mms
 *  android.provider.Telephony$Mms$Draft
 *  android.provider.Telephony$Mms$Inbox
 *  android.provider.Telephony$Mms$Outbox
 *  android.provider.Telephony$Mms$Sent
 *  android.provider.Telephony$MmsSms
 *  android.provider.Telephony$MmsSms$PendingMessages
 *  android.provider.Telephony$Threads
 *  android.telephony.PhoneNumberUtils
 *  android.telephony.TelephonyManager
 *  android.util.Log
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 *  java.util.HashMap
 *  java.util.HashSet
 *  java.util.Map$Entry
 */
package com.google.android.mms.pdu;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.drm.DrmManagerClient;
import android.net.Uri;
import android.provider.Telephony;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.google.android.mms.InvalidHeaderValueException;
import com.google.android.mms.MmsException;
import com.google.android.mms.pdu.EncodedStringValue;
import com.google.android.mms.pdu.GenericPdu;
import com.google.android.mms.pdu.MultimediaMessagePdu;
import com.google.android.mms.pdu.PduBody;
import com.google.android.mms.pdu.PduHeaders;
import com.google.android.mms.pdu.PduPart;
import com.google.android.mms.pdu.SendReq;
import com.google.android.mms.util.PduCache;
import com.google.android.mms.util.PduCacheEntry;
import com.google.android.mms.util.SqliteWrapper;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PduPersister {
    static final /* synthetic */ boolean $assertionsDisabled;
    private static final int[] ADDRESS_FIELDS;
    private static final HashMap<Integer, Integer> CHARSET_COLUMN_INDEX_MAP;
    private static final HashMap<Integer, String> CHARSET_COLUMN_NAME_MAP;
    private static final boolean DEBUG = false;
    private static final int DEFAULT_SUBSCRIPTION = 0;
    private static final long DUMMY_THREAD_ID = Long.MAX_VALUE;
    private static final HashMap<Integer, Integer> ENCODED_STRING_COLUMN_INDEX_MAP;
    private static final HashMap<Integer, String> ENCODED_STRING_COLUMN_NAME_MAP;
    private static final boolean LOCAL_LOGV = false;
    private static final HashMap<Integer, Integer> LONG_COLUMN_INDEX_MAP;
    private static final HashMap<Integer, String> LONG_COLUMN_NAME_MAP;
    private static final HashMap<Uri, Integer> MESSAGE_BOX_MAP;
    private static final HashMap<Integer, Integer> OCTET_COLUMN_INDEX_MAP;
    private static final HashMap<Integer, String> OCTET_COLUMN_NAME_MAP;
    private static final int PART_COLUMN_CHARSET = 1;
    private static final int PART_COLUMN_CONTENT_DISPOSITION = 2;
    private static final int PART_COLUMN_CONTENT_ID = 3;
    private static final int PART_COLUMN_CONTENT_LOCATION = 4;
    private static final int PART_COLUMN_CONTENT_TYPE = 5;
    private static final int PART_COLUMN_FILENAME = 6;
    private static final int PART_COLUMN_ID = 0;
    private static final int PART_COLUMN_NAME = 7;
    private static final int PART_COLUMN_TEXT = 8;
    private static final String[] PART_PROJECTION;
    private static final PduCache PDU_CACHE_INSTANCE;
    private static final int PDU_COLUMN_CONTENT_CLASS = 11;
    private static final int PDU_COLUMN_CONTENT_LOCATION = 5;
    private static final int PDU_COLUMN_CONTENT_TYPE = 6;
    private static final int PDU_COLUMN_DATE = 21;
    private static final int PDU_COLUMN_DELIVERY_REPORT = 12;
    private static final int PDU_COLUMN_DELIVERY_TIME = 22;
    private static final int PDU_COLUMN_EXPIRY = 23;
    private static final int PDU_COLUMN_ID = 0;
    private static final int PDU_COLUMN_MESSAGE_BOX = 1;
    private static final int PDU_COLUMN_MESSAGE_CLASS = 7;
    private static final int PDU_COLUMN_MESSAGE_ID = 8;
    private static final int PDU_COLUMN_MESSAGE_SIZE = 24;
    private static final int PDU_COLUMN_MESSAGE_TYPE = 13;
    private static final int PDU_COLUMN_MMS_VERSION = 14;
    private static final int PDU_COLUMN_PRIORITY = 15;
    private static final int PDU_COLUMN_READ_REPORT = 16;
    private static final int PDU_COLUMN_READ_STATUS = 17;
    private static final int PDU_COLUMN_REPORT_ALLOWED = 18;
    private static final int PDU_COLUMN_RESPONSE_TEXT = 9;
    private static final int PDU_COLUMN_RETRIEVE_STATUS = 19;
    private static final int PDU_COLUMN_RETRIEVE_TEXT = 3;
    private static final int PDU_COLUMN_RETRIEVE_TEXT_CHARSET = 26;
    private static final int PDU_COLUMN_STATUS = 20;
    private static final int PDU_COLUMN_SUBJECT = 4;
    private static final int PDU_COLUMN_SUBJECT_CHARSET = 25;
    private static final int PDU_COLUMN_THREAD_ID = 2;
    private static final int PDU_COLUMN_TRANSACTION_ID = 10;
    private static final String[] PDU_PROJECTION;
    public static final int PROC_STATUS_COMPLETED = 3;
    public static final int PROC_STATUS_PERMANENTLY_FAILURE = 2;
    public static final int PROC_STATUS_TRANSIENT_FAILURE = 1;
    private static final String TAG = "PduPersister";
    public static final String TEMPORARY_DRM_OBJECT_URI = "content://mms/9223372036854775807/part";
    private static final HashMap<Integer, Integer> TEXT_STRING_COLUMN_INDEX_MAP;
    private static final HashMap<Integer, String> TEXT_STRING_COLUMN_NAME_MAP;
    private static PduPersister sPersister;
    private final ContentResolver mContentResolver;
    private final Context mContext;
    private final DrmManagerClient mDrmManagerClient;
    private final TelephonyManager mTelephonyManager;

    /*
     * Enabled aggressive block sorting
     */
    static {
        boolean bl = !PduPersister.class.desiredAssertionStatus();
        $assertionsDisabled = bl;
        ADDRESS_FIELDS = new int[]{129, 130, 137, 151};
        PDU_PROJECTION = new String[]{"_id", "msg_box", "thread_id", "retr_txt", "sub", "ct_l", "ct_t", "m_cls", "m_id", "resp_txt", "tr_id", "ct_cls", "d_rpt", "m_type", "v", "pri", "rr", "read_status", "rpt_a", "retr_st", "st", "date", "d_tm", "exp", "m_size", "sub_cs", "retr_txt_cs"};
        PART_PROJECTION = new String[]{"_id", "chset", "cd", "cid", "cl", "ct", "fn", "name", "text"};
        MESSAGE_BOX_MAP = new HashMap();
        MESSAGE_BOX_MAP.put((Object)Telephony.Mms.Inbox.CONTENT_URI, (Object)1);
        MESSAGE_BOX_MAP.put((Object)Telephony.Mms.Sent.CONTENT_URI, (Object)2);
        MESSAGE_BOX_MAP.put((Object)Telephony.Mms.Draft.CONTENT_URI, (Object)3);
        MESSAGE_BOX_MAP.put((Object)Telephony.Mms.Outbox.CONTENT_URI, (Object)4);
        CHARSET_COLUMN_INDEX_MAP = new HashMap();
        CHARSET_COLUMN_INDEX_MAP.put((Object)150, (Object)25);
        CHARSET_COLUMN_INDEX_MAP.put((Object)154, (Object)26);
        CHARSET_COLUMN_NAME_MAP = new HashMap();
        CHARSET_COLUMN_NAME_MAP.put((Object)150, (Object)"sub_cs");
        CHARSET_COLUMN_NAME_MAP.put((Object)154, (Object)"retr_txt_cs");
        ENCODED_STRING_COLUMN_INDEX_MAP = new HashMap();
        ENCODED_STRING_COLUMN_INDEX_MAP.put((Object)154, (Object)3);
        ENCODED_STRING_COLUMN_INDEX_MAP.put((Object)150, (Object)4);
        ENCODED_STRING_COLUMN_NAME_MAP = new HashMap();
        ENCODED_STRING_COLUMN_NAME_MAP.put((Object)154, (Object)"retr_txt");
        ENCODED_STRING_COLUMN_NAME_MAP.put((Object)150, (Object)"sub");
        TEXT_STRING_COLUMN_INDEX_MAP = new HashMap();
        TEXT_STRING_COLUMN_INDEX_MAP.put((Object)131, (Object)5);
        TEXT_STRING_COLUMN_INDEX_MAP.put((Object)132, (Object)6);
        TEXT_STRING_COLUMN_INDEX_MAP.put((Object)138, (Object)7);
        TEXT_STRING_COLUMN_INDEX_MAP.put((Object)139, (Object)8);
        TEXT_STRING_COLUMN_INDEX_MAP.put((Object)147, (Object)9);
        TEXT_STRING_COLUMN_INDEX_MAP.put((Object)152, (Object)10);
        TEXT_STRING_COLUMN_NAME_MAP = new HashMap();
        TEXT_STRING_COLUMN_NAME_MAP.put((Object)131, (Object)"ct_l");
        TEXT_STRING_COLUMN_NAME_MAP.put((Object)132, (Object)"ct_t");
        TEXT_STRING_COLUMN_NAME_MAP.put((Object)138, (Object)"m_cls");
        TEXT_STRING_COLUMN_NAME_MAP.put((Object)139, (Object)"m_id");
        TEXT_STRING_COLUMN_NAME_MAP.put((Object)147, (Object)"resp_txt");
        TEXT_STRING_COLUMN_NAME_MAP.put((Object)152, (Object)"tr_id");
        OCTET_COLUMN_INDEX_MAP = new HashMap();
        OCTET_COLUMN_INDEX_MAP.put((Object)186, (Object)11);
        OCTET_COLUMN_INDEX_MAP.put((Object)134, (Object)12);
        OCTET_COLUMN_INDEX_MAP.put((Object)140, (Object)13);
        OCTET_COLUMN_INDEX_MAP.put((Object)141, (Object)14);
        OCTET_COLUMN_INDEX_MAP.put((Object)143, (Object)15);
        OCTET_COLUMN_INDEX_MAP.put((Object)144, (Object)16);
        OCTET_COLUMN_INDEX_MAP.put((Object)155, (Object)17);
        OCTET_COLUMN_INDEX_MAP.put((Object)145, (Object)18);
        OCTET_COLUMN_INDEX_MAP.put((Object)153, (Object)19);
        OCTET_COLUMN_INDEX_MAP.put((Object)149, (Object)20);
        OCTET_COLUMN_NAME_MAP = new HashMap();
        OCTET_COLUMN_NAME_MAP.put((Object)186, (Object)"ct_cls");
        OCTET_COLUMN_NAME_MAP.put((Object)134, (Object)"d_rpt");
        OCTET_COLUMN_NAME_MAP.put((Object)140, (Object)"m_type");
        OCTET_COLUMN_NAME_MAP.put((Object)141, (Object)"v");
        OCTET_COLUMN_NAME_MAP.put((Object)143, (Object)"pri");
        OCTET_COLUMN_NAME_MAP.put((Object)144, (Object)"rr");
        OCTET_COLUMN_NAME_MAP.put((Object)155, (Object)"read_status");
        OCTET_COLUMN_NAME_MAP.put((Object)145, (Object)"rpt_a");
        OCTET_COLUMN_NAME_MAP.put((Object)153, (Object)"retr_st");
        OCTET_COLUMN_NAME_MAP.put((Object)149, (Object)"st");
        LONG_COLUMN_INDEX_MAP = new HashMap();
        LONG_COLUMN_INDEX_MAP.put((Object)133, (Object)21);
        LONG_COLUMN_INDEX_MAP.put((Object)135, (Object)22);
        LONG_COLUMN_INDEX_MAP.put((Object)136, (Object)23);
        LONG_COLUMN_INDEX_MAP.put((Object)142, (Object)24);
        LONG_COLUMN_NAME_MAP = new HashMap();
        LONG_COLUMN_NAME_MAP.put((Object)133, (Object)"date");
        LONG_COLUMN_NAME_MAP.put((Object)135, (Object)"d_tm");
        LONG_COLUMN_NAME_MAP.put((Object)136, (Object)"exp");
        LONG_COLUMN_NAME_MAP.put((Object)142, (Object)"m_size");
        PDU_CACHE_INSTANCE = PduCache.getInstance();
    }

    private PduPersister(Context context) {
        this.mContext = context;
        this.mContentResolver = context.getContentResolver();
        this.mDrmManagerClient = new DrmManagerClient(context);
        this.mTelephonyManager = (TelephonyManager)context.getSystemService("phone");
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static String convertUriToPath(Context var0, Uri var1_3) {
        var2_4 = null;
        if (var1_3 == null) return var2_4;
        var2_4 = var1_3.getScheme();
        if (var2_4 == null) return var1_3.getPath();
        if (var2_4.equals((Object)"") != false) return var1_3.getPath();
        if (var2_4.equals((Object)"file")) {
            return var1_3.getPath();
        }
        if (var2_4.equals((Object)"http")) {
            return var1_3.toString();
        }
        if (var2_4.equals((Object)"content") == false) throw new IllegalArgumentException("Given Uri scheme is not supported");
        var3_5 = null;
        var2_4 = null;
        try {
            try {
                var0 = var0.getContentResolver().query((Uri)var1_3, new String[]{"_data"}, null, null, null);
                if (var0 == null) ** GOTO lbl-1000
                var2_4 = var0;
                var3_5 = var0;
                if (var0.getCount() == 0) ** GOTO lbl-1000
                var2_4 = var0;
                var3_5 = var0;
                if (!var0.moveToFirst()) lbl-1000: // 3 sources:
                {
                    var2_4 = var0;
                    var3_5 = var0;
                    throw new IllegalArgumentException("Given Uri could not be found in media store");
                }
                var2_4 = var0;
                var3_5 = var0;
                var2_4 = var1_3 = var0.getString(var0.getColumnIndexOrThrow("_data"));
                if (var0 == null) return var2_4;
            }
            catch (SQLiteException var0_1) {
                var3_5 = var2_4;
                throw new IllegalArgumentException("Given Uri is not formatted in a way so that it can be found in media store.");
            }
        }
        catch (Throwable var0_2) {
            if (var3_5 == null) throw var0_2;
            var3_5.close();
            throw var0_2;
        }
        var0.close();
        return var1_3;
    }

    private byte[] getByteArrayFromPartColumn(Cursor cursor, int n) {
        if (!cursor.isNull(n)) {
            return PduPersister.getBytes(cursor.getString(n));
        }
        return null;
    }

    public static byte[] getBytes(String string) {
        try {
            string = (String)string.getBytes("iso-8859-1");
            return string;
        }
        catch (UnsupportedEncodingException var0_1) {
            Log.e((String)"PduPersister", (String)"ISO_8859_1 must be supported!", (Throwable)var0_1);
            return new byte[0];
        }
    }

    private Integer getIntegerFromPartColumn(Cursor cursor, int n) {
        if (!cursor.isNull(n)) {
            return cursor.getInt(n);
        }
        return null;
    }

    private static String getPartContentType(PduPart pduPart) {
        if (pduPart.getContentType() == null) {
            return null;
        }
        return PduPersister.toIsoString(pduPart.getContentType());
    }

    public static PduPersister getPduPersister(Context context) {
        if (sPersister == null || !context.equals((Object)PduPersister.sPersister.mContext)) {
            sPersister = new PduPersister(context);
        }
        return sPersister;
    }

    /*
     * Exception decompiling
     */
    private void loadAddress(long var1_1, PduHeaders var3_2) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: First case is not immediately after switch.
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.SwitchReplacer.examineSwitchContiguity(SwitchReplacer.java:366)
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

    /*
     * Exception decompiling
     */
    private PduPart[] loadParts(long var1_1) throws MmsException {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [1[TRYBLOCK]], but top level block is 13[UNCONDITIONALDOLOOP]
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:394)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:446)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:2859)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:805)
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

    /*
     * Enabled aggressive block sorting
     */
    private void loadRecipients(int n, HashSet<String> hashSet, HashMap<Integer, EncodedStringValue[]> object, boolean bl) {
        EncodedStringValue[] arrencodedStringValue = (EncodedStringValue[])object.get((Object)n);
        if (arrencodedStringValue == null || bl && arrencodedStringValue.length == 1) {
            return;
        }
        object = bl ? this.mTelephonyManager.getLine1Number() : null;
        int n2 = arrencodedStringValue.length;
        n = 0;
        while (n < n2) {
            Object object2 = arrencodedStringValue[n];
            if (object2 != null) {
                object2 = object2.getString();
                if (!(object != null && PhoneNumberUtils.compare((String)object2, (String)object) || hashSet.contains(object2))) {
                    hashSet.add(object2);
                }
            }
            ++n;
        }
    }

    private void persistAddress(long l, int n, EncodedStringValue[] arrencodedStringValue) {
        ContentValues contentValues = new ContentValues(3);
        for (EncodedStringValue encodedStringValue : arrencodedStringValue) {
            contentValues.clear();
            contentValues.put("address", PduPersister.toIsoString(encodedStringValue.getTextString()));
            contentValues.put("charset", Integer.valueOf((int)encodedStringValue.getCharacterSet()));
            contentValues.put("type", Integer.valueOf((int)n));
            encodedStringValue = Uri.parse((String)("content://mms/" + l + "/addr"));
            SqliteWrapper.insert(this.mContext, this.mContentResolver, (Uri)encodedStringValue, contentValues);
        }
    }

    /*
     * Exception decompiling
     */
    private void persistData(PduPart var1_1, Uri var2_11, String var3_14, HashMap<Uri, InputStream> var4_15) throws MmsException {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [5[TRYBLOCK]], but top level block is 47[SIMPLE_IF_TAKEN]
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:394)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:446)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:2859)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:805)
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

    private void setEncodedStringValueToHeaders(Cursor cursor, int n, PduHeaders pduHeaders, int n2) {
        String string = cursor.getString(n);
        if (string != null && string.length() > 0) {
            pduHeaders.setEncodedStringValue(new EncodedStringValue(cursor.getInt(((Integer)CHARSET_COLUMN_INDEX_MAP.get((Object)n2)).intValue()), PduPersister.getBytes(string)), n2);
        }
    }

    private void setLongToHeaders(Cursor cursor, int n, PduHeaders pduHeaders, int n2) {
        if (!cursor.isNull(n)) {
            pduHeaders.setLongInteger(cursor.getLong(n), n2);
        }
    }

    private void setOctetToHeaders(Cursor cursor, int n, PduHeaders pduHeaders, int n2) throws InvalidHeaderValueException {
        if (!cursor.isNull(n)) {
            pduHeaders.setOctet(cursor.getInt(n), n2);
        }
    }

    private void setTextStringToHeaders(Cursor object, int n, PduHeaders pduHeaders, int n2) {
        if ((object = object.getString(n)) != null) {
            pduHeaders.setTextString(PduPersister.getBytes((String)object), n2);
        }
    }

    public static String toIsoString(byte[] object) {
        try {
            object = new String((byte[])object, "iso-8859-1");
            return object;
        }
        catch (UnsupportedEncodingException var0_1) {
            Log.e((String)"PduPersister", (String)"ISO_8859_1 must be supported!", (Throwable)var0_1);
            return "";
        }
    }

    private void updateAddress(long l, int n, EncodedStringValue[] arrencodedStringValue) {
        SqliteWrapper.delete(this.mContext, this.mContentResolver, Uri.parse((String)("content://mms/" + l + "/addr")), "type=" + n, null);
        this.persistAddress(l, n, arrencodedStringValue);
    }

    private void updatePart(Uri uri, PduPart pduPart, HashMap<Uri, InputStream> hashMap) throws MmsException {
        ContentValues contentValues = new ContentValues(7);
        int n = pduPart.getCharset();
        if (n != 0) {
            contentValues.put("chset", Integer.valueOf((int)n));
        }
        if (pduPart.getContentType() != null) {
            String string = PduPersister.toIsoString(pduPart.getContentType());
            contentValues.put("ct", string);
            if (pduPart.getFilename() != null) {
                contentValues.put("fn", new String(pduPart.getFilename()));
            }
            if (pduPart.getName() != null) {
                contentValues.put("name", new String(pduPart.getName()));
            }
            if (pduPart.getContentDisposition() != null) {
                contentValues.put("cd", PduPersister.toIsoString(pduPart.getContentDisposition()));
            }
            if (pduPart.getContentId() != null) {
                contentValues.put("cid", PduPersister.toIsoString(pduPart.getContentId()));
            }
            if (pduPart.getContentLocation() != null) {
                contentValues.put("cl", PduPersister.toIsoString(pduPart.getContentLocation()));
            }
            SqliteWrapper.update(this.mContext, this.mContentResolver, uri, contentValues, null, null);
            if (pduPart.getData() != null || uri != pduPart.getDataUri()) {
                this.persistData(pduPart, uri, string, hashMap);
            }
            return;
        }
        throw new MmsException("MIME type of the part must be set.");
    }

    public Cursor getPendingMessages(long l) {
        Uri.Builder builder = Telephony.MmsSms.PendingMessages.CONTENT_URI.buildUpon();
        builder.appendQueryParameter("protocol", "mms");
        return SqliteWrapper.query(this.mContext, this.mContentResolver, builder.build(), null, "err_type < ? AND due_time <= ?", new String[]{String.valueOf((int)10), String.valueOf((long)l)}, "due_time");
    }

    /*
     * Exception decompiling
     */
    public GenericPdu load(Uri var1_1) throws MmsException {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [1[TRYBLOCK]], but top level block is 41[CATCHBLOCK]
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:394)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:446)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:2859)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:805)
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

    public Uri move(Uri uri, Uri uri2) throws MmsException {
        long l = ContentUris.parseId((Uri)uri);
        if (l == -1) {
            throw new MmsException("Error! ID of the message: -1.");
        }
        Integer n = (Integer)MESSAGE_BOX_MAP.get((Object)uri2);
        if (n == null) {
            throw new MmsException("Bad destination, must be one of content://mms/inbox, content://mms/sent, content://mms/drafts, content://mms/outbox, content://mms/temp.");
        }
        ContentValues contentValues = new ContentValues(1);
        contentValues.put("msg_box", n);
        SqliteWrapper.update(this.mContext, this.mContentResolver, uri, contentValues, null, null);
        return ContentUris.withAppendedId((Uri)uri2, (long)l);
    }

    public Uri persist(GenericPdu genericPdu, Uri uri, boolean bl, boolean bl2, HashMap<Uri, InputStream> hashMap) throws MmsException {
        return this.persist(genericPdu, uri, bl, bl2, hashMap, 0);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public Uri persist(GenericPdu object, Uri object2, boolean bl, boolean bl2, HashMap<Uri, InputStream> arrencodedStringValue, int n) throws MmsException {
        Object object3;
        Map.Entry entry2222;
        EncodedStringValue encodedStringValue;
        long l;
        long l2;
        Object object4;
        Object object52;
        if (object2 == null) {
            throw new MmsException("Uri may not be null.");
        }
        l2 = -1;
        try {
            l2 = l = ContentUris.parseId((Uri)object2);
        }
        catch (NumberFormatException var19_9) {}
        Object object6 = l2 != -1;
        if (object6 == false && MESSAGE_BOX_MAP.get(object2) == null) {
            throw new MmsException("Bad destination, must be one of content://mms/inbox, content://mms/sent, content://mms/drafts, content://mms/outbox, content://mms/temp.");
        }
        Object object7 = PDU_CACHE_INSTANCE;
        synchronized (object7) {
            boolean bl3 = PDU_CACHE_INSTANCE.isUpdating((Uri)object2);
            if (bl3) {
                try {
                    PDU_CACHE_INSTANCE.wait();
                }
                catch (InterruptedException var20_13) {
                    Log.e((String)"PduPersister", (String)"persist1: ", (Throwable)var20_13);
                }
            }
        }
        PDU_CACHE_INSTANCE.purge((Uri)object2);
        PduHeaders pduHeaders = object.getPduHeaders();
        ContentValues contentValues = new ContentValues();
        for (Map.Entry entry2222 : ENCODED_STRING_COLUMN_NAME_MAP.entrySet()) {
            object4 = (Integer)entry2222.getKey();
            encodedStringValue = pduHeaders.getEncodedStringValue((int)object4);
            if (encodedStringValue == null) continue;
            object3 = (String)CHARSET_COLUMN_NAME_MAP.get((Object)((int)object4));
            contentValues.put((String)entry2222.getValue(), PduPersister.toIsoString(encodedStringValue.getTextString()));
            contentValues.put((String)object3, Integer.valueOf((int)encodedStringValue.getCharacterSet()));
        }
        for (Map.Entry entry2222 : TEXT_STRING_COLUMN_NAME_MAP.entrySet()) {
            encodedStringValue = (EncodedStringValue)pduHeaders.getTextString((Integer)entry2222.getKey());
            if (encodedStringValue == null) continue;
            contentValues.put((String)entry2222.getValue(), PduPersister.toIsoString((byte[])encodedStringValue));
        }
        for (Map.Entry entry2222 : OCTET_COLUMN_NAME_MAP.entrySet()) {
            object4 = pduHeaders.getOctet((Integer)entry2222.getKey());
            if (object4 == 0) continue;
            contentValues.put((String)entry2222.getValue(), Integer.valueOf((int)object4));
        }
        for (Map.Entry entry2222 : LONG_COLUMN_NAME_MAP.entrySet()) {
            l = pduHeaders.getLongInteger((Integer)entry2222.getKey());
            if (l == -1) continue;
            contentValues.put((String)entry2222.getValue(), Long.valueOf((long)l));
        }
        entry2222 = new HashMap(ADDRESS_FIELDS.length);
        for (Object object52 : (EncodedStringValue)ADDRESS_FIELDS) {
            object7 = null;
            if (object52 == 137) {
                object3 = pduHeaders.getEncodedStringValue((int)object52);
                if (object3 != null) {
                    object7 = new EncodedStringValue[]{object3};
                }
            } else {
                object7 = pduHeaders.getEncodedStringValues((int)object52);
            }
            entry2222.put((Object)((int)object52), object7);
        }
        object7 = new EncodedStringValue[]();
        object4 = object.getMessageType();
        if (object4 == 130 || object4 == 132 || object4 == 128) {
            long l3;
            switch (object4) {
                case 130: 
                case 132: {
                    this.loadRecipients(137, (HashSet<String>)object7, (HashMap<Integer, EncodedStringValue[]>)entry2222, false);
                    if (!bl2) break;
                    this.loadRecipients(151, (HashSet<String>)object7, (HashMap<Integer, EncodedStringValue[]>)entry2222, true);
                    break;
                }
                case 128: {
                    this.loadRecipients(151, (HashSet<String>)object7, (HashMap<Integer, EncodedStringValue[]>)entry2222, false);
                }
            }
            l = l3 = 0;
            if (bl) {
                l = l3;
                if (!object7.isEmpty()) {
                    l = Telephony.Threads.getOrCreateThreadId((Context)this.mContext, (Set)object7);
                }
            }
            contentValues.put("thread_id", Long.valueOf((long)l));
        }
        l = System.currentTimeMillis();
        int n2 = 1;
        object4 = 1;
        object52 = n2;
        if (object instanceof MultimediaMessagePdu) {
            object = ((MultimediaMessagePdu)object).getBody();
            object52 = n2;
            if (object != null) {
                int n3 = object.getPartsNum();
                if (n3 > 2) {
                    object4 = 0;
                }
                n2 = 0;
                do {
                    object52 = object4;
                    if (n2 >= n3) break;
                    object7 = object.getPart(n2);
                    this.persistPart((PduPart)object7, l, (HashMap<Uri, InputStream>)arrencodedStringValue);
                    object7 = PduPersister.getPartContentType((PduPart)object7);
                    object52 = object4;
                    if (object7 != null) {
                        object52 = object4;
                        if (!"application/smil".equals(object7)) {
                            object52 = object4;
                            if (!"text/plain".equals(object7)) {
                                object52 = false;
                            }
                        }
                    }
                    ++n2;
                    object4 = object52;
                } while (true);
            }
        }
        object4 = object52 != false ? 1 : 0;
        contentValues.put("text_only", Integer.valueOf((int)object4));
        contentValues.put("sub_id", Integer.valueOf((int)n));
        if (object6 != false) {
            object = object2;
            SqliteWrapper.update(this.mContext, this.mContentResolver, (Uri)object, contentValues, null, null);
        } else {
            object = SqliteWrapper.insert(this.mContext, this.mContentResolver, (Uri)object2, contentValues);
            if (object == null) {
                throw new MmsException("persist() failed: return null.");
            }
            l2 = ContentUris.parseId((Uri)object);
        }
        arrencodedStringValue = new EncodedStringValue[](1);
        arrencodedStringValue.put("mid", Long.valueOf((long)l2));
        SqliteWrapper.update(this.mContext, this.mContentResolver, Uri.parse((String)("content://mms/" + l + "/part")), (ContentValues)arrencodedStringValue, null, null);
        if (object6 == false) {
            object = Uri.parse((String)(object2 + "/" + l2));
        }
        object2 = ADDRESS_FIELDS;
        object4 = object2.length;
        n = 0;
        while (n < object4) {
            object6 = object2[n];
            arrencodedStringValue = (EncodedStringValue[])entry2222.get((Object)((int)object6));
            if (arrencodedStringValue != null) {
                this.persistAddress(l2, (int)object6, arrencodedStringValue);
            }
            ++n;
        }
        return object;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public Uri persistPart(PduPart var1_1, long var2_2, HashMap<Uri, InputStream> var4_3) throws MmsException {
        var8_4 = Uri.parse((String)("content://mms/" + var2_2 + "/part"));
        var9_5 = new ContentValues(8);
        var5_6 = var1_1.getCharset();
        if (var5_6 != 0) {
            var9_5.put("chset", Integer.valueOf((int)var5_6));
        }
        if ((var6_7 = PduPersister.getPartContentType(var1_1)) == null) throw new MmsException("MIME type of the part must be set.");
        var7_8 = var6_7;
        if ("text/x-vCard".equalsIgnoreCase(var6_7)) {
            Log.d((String)"PduPersister", (String)(var6_7 + " -> " + "text/x-vCard"));
            var7_8 = "text/x-vCard";
        }
        var6_7 = var7_8;
        if (var1_1.getContentLocation() == null) ** GOTO lbl39
        if ("text/plain".equalsIgnoreCase(var7_8)) ** GOTO lbl16
        var6_7 = var7_8;
        if (!"application/oct-stream".equalsIgnoreCase(var7_8)) ** GOTO lbl39
lbl16: // 2 sources:
        var10_9 = new String(var1_1.getContentLocation());
        Log.d((String)"PduPersister", (String)("cl = " + var10_9));
        var6_7 = var7_8;
        if (var10_9 == null) ** GOTO lbl25
        if (var10_9.endsWith(".vcf")) ** GOTO lbl-1000
        var6_7 = var7_8;
        if (var10_9.endsWith(".VCF")) lbl-1000: // 2 sources:
        {
            Log.e((String)"PduPersister", (String)(var7_8 + " -> " + "text/x-vCard"));
            var6_7 = "text/x-vCard";
        }
lbl25: // 4 sources:
        var7_8 = var6_7;
        if (var10_9 == null) ** GOTO lbl32
        if (var10_9.endsWith(".ogg")) ** GOTO lbl-1000
        var7_8 = var6_7;
        if (var10_9.endsWith(".OGG")) lbl-1000: // 2 sources:
        {
            Log.d((String)"PduPersister", (String)(var6_7 + " -> " + "application/ogg"));
            var7_8 = "application/ogg";
        }
lbl32: // 4 sources:
        var6_7 = var7_8;
        if (var10_9 == null) ** GOTO lbl39
        if (var10_9.endsWith(".mp3")) ** GOTO lbl-1000
        var6_7 = var7_8;
        if (var10_9.endsWith(".MP3")) lbl-1000: // 2 sources:
        {
            Log.d((String)"PduPersister", (String)(var7_8 + " -> " + "application/ogg"));
            var6_7 = "audio/mp3";
        }
lbl39: // 6 sources:
        if (var1_1.getContentLocation() != null && "text/plain".equalsIgnoreCase(var6_7) && (var7_8 = new String(var1_1.getContentLocation())) != null && (var7_8.endsWith(".txt") || var7_8.endsWith(".TXT")) && var1_1.getCharset() == 0) {
            Log.i((String)"PduPersister", (String)"Set default text charset to UTF_8");
            var9_5.put("chset", Integer.valueOf((int)106));
        }
        var7_8 = var6_7;
        if ("image/jpg".equals((Object)var6_7)) {
            var7_8 = "image/jpeg";
        }
        var9_5.put("ct", var7_8);
        if ("application/smil".equals((Object)var7_8)) {
            var9_5.put("seq", Integer.valueOf((int)-1));
        }
        if (var1_1.getFilename() != null) {
            var9_5.put("fn", new String(var1_1.getFilename()));
        }
        if (var1_1.getName() != null) {
            var9_5.put("name", new String(var1_1.getName()));
        }
        if (var1_1.getContentDisposition() != null) {
            var9_5.put("cd", PduPersister.toIsoString(var1_1.getContentDisposition()));
        }
        if (var1_1.getContentId() != null) {
            var9_5.put("cid", PduPersister.toIsoString(var1_1.getContentId()));
        }
        if (var1_1.getContentLocation() != null) {
            var9_5.put("cl", PduPersister.toIsoString(var1_1.getContentLocation()));
        }
        if ((var6_7 = SqliteWrapper.insert(this.mContext, this.mContentResolver, var8_4, var9_5)) == null) {
            throw new MmsException("Failed to persist part, return null.");
        }
        this.persistData(var1_1, (Uri)var6_7, var7_8, var4_3);
        var1_1.setDataUri((Uri)var6_7);
        return var6_7;
    }

    public void release() {
        Uri uri = Uri.parse((String)"content://mms/9223372036854775807/part");
        SqliteWrapper.delete(this.mContext, this.mContentResolver, uri, null, null);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void updateHeaders(Uri uri, SendReq arrencodedStringValue) {
        int n;
        long l;
        PduCache pduCache = PDU_CACHE_INSTANCE;
        synchronized (pduCache) {
            boolean bl = PDU_CACHE_INSTANCE.isUpdating(uri);
            if (bl) {
                try {
                    PDU_CACHE_INSTANCE.wait();
                }
                catch (InterruptedException var11_5) {
                    Log.e((String)"PduPersister", (String)"updateHeaders: ", (Throwable)var11_5);
                }
            }
        }
        PDU_CACHE_INSTANCE.purge(uri);
        pduCache = new ContentValues(10);
        Object object = arrencodedStringValue.getContentType();
        if (object != null) {
            pduCache.put("ct_t", PduPersister.toIsoString((byte[])object));
        }
        if ((l = arrencodedStringValue.getDate()) != -1) {
            pduCache.put("date", Long.valueOf((long)l));
        }
        if ((n = arrencodedStringValue.getDeliveryReport()) != 0) {
            pduCache.put("d_rpt", Integer.valueOf((int)n));
        }
        if ((l = arrencodedStringValue.getExpiry()) != -1) {
            pduCache.put("exp", Long.valueOf((long)l));
        }
        if ((object = arrencodedStringValue.getMessageClass()) != null) {
            pduCache.put("m_cls", PduPersister.toIsoString((byte[])object));
        }
        if ((n = arrencodedStringValue.getPriority()) != 0) {
            pduCache.put("pri", Integer.valueOf((int)n));
        }
        if ((n = arrencodedStringValue.getReadReport()) != 0) {
            pduCache.put("rr", Integer.valueOf((int)n));
        }
        if ((object = arrencodedStringValue.getTransactionId()) != null) {
            pduCache.put("tr_id", PduPersister.toIsoString((byte[])object));
        }
        if ((object = (Object)arrencodedStringValue.getSubject()) != null) {
            pduCache.put("sub", PduPersister.toIsoString(object.getTextString()));
            pduCache.put("sub_cs", Integer.valueOf((int)object.getCharacterSet()));
        } else {
            pduCache.put("sub", "");
        }
        if ((l = arrencodedStringValue.getMessageSize()) > 0) {
            pduCache.put("m_size", Long.valueOf((long)l));
        }
        object = arrencodedStringValue.getPduHeaders();
        HashSet hashSet = new HashSet();
        for (int n2 : ADDRESS_FIELDS) {
            arrencodedStringValue = null;
            if (n2 == 137) {
                EncodedStringValue encodedStringValue = object.getEncodedStringValue(n2);
                if (encodedStringValue != null) {
                    arrencodedStringValue = new EncodedStringValue[]{encodedStringValue};
                }
            } else {
                arrencodedStringValue = object.getEncodedStringValues(n2);
            }
            if (arrencodedStringValue == null) continue;
            this.updateAddress(ContentUris.parseId((Uri)uri), n2, arrencodedStringValue);
            if (n2 != 151) continue;
            for (EncodedStringValue encodedStringValue : arrencodedStringValue) {
                if (encodedStringValue == null) continue;
                hashSet.add((Object)encodedStringValue.getString());
            }
        }
        if (!hashSet.isEmpty()) {
            pduCache.put("thread_id", Long.valueOf((long)Telephony.Threads.getOrCreateThreadId((Context)this.mContext, (Set)hashSet)));
        }
        SqliteWrapper.update(this.mContext, this.mContentResolver, uri, (ContentValues)pduCache, null, null);
    }

    /*
     * Exception decompiling
     */
    public void updateParts(Uri var1_1, PduBody var2_2, HashMap<Uri, InputStream> var3_3) throws MmsException {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [3[TRYBLOCK]], but top level block is 16[FORLOOP]
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:394)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:446)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:2859)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:805)
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
}

