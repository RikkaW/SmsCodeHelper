/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentProviderOperation
 *  android.content.ContentProviderOperation$Builder
 *  android.content.ContentProviderResult
 *  android.content.ContentResolver
 *  android.content.ContentUris
 *  android.content.ContentValues
 *  android.content.Context
 *  android.content.OperationApplicationException
 *  android.database.Cursor
 *  android.database.sqlite.SQLiteException
 *  android.net.Uri
 *  android.net.Uri$Builder
 *  android.os.RemoteException
 *  android.provider.Telephony
 *  android.provider.Telephony$Mms
 *  android.provider.Telephony$Mms$Draft
 *  android.provider.Telephony$Mms$Inbox
 *  android.provider.Telephony$Mms$Outbox
 *  android.provider.Telephony$Mms$Sent
 *  android.provider.Telephony$MmsSms
 *  android.provider.Telephony$MmsSms$PendingMessages
 *  android.provider.Telephony$Threads
 *  android.util.Log
 *  com.google.android.collect.Lists
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 *  java.util.ArrayList
 *  java.util.HashMap
 *  java.util.HashSet
 *  java.util.Map$Entry
 *  miui.provider.ExtraTelephony
 */
package com.google.android.mms.pdu;

import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.RemoteException;
import android.provider.Telephony;
import android.util.Log;
import com.google.android.collect.Lists;
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
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import miui.provider.ExtraTelephony;

public class MiuiPduPersister {
    static final /* synthetic */ boolean $assertionsDisabled;
    private static final int[] ADDRESS_FIELDS;
    private static final HashMap<Integer, Integer> CHARSET_COLUMN_INDEX_MAP;
    private static final HashMap<Integer, String> CHARSET_COLUMN_NAME_MAP;
    private static final boolean DEBUG = false;
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
    private static final String TAG = "MiuiPduPersister";
    public static final String TEMPORARY_DRM_OBJECT_URI = "content://mms/9223372036854775807/part";
    private static final HashMap<Integer, Integer> TEXT_STRING_COLUMN_INDEX_MAP;
    private static final HashMap<Integer, String> TEXT_STRING_COLUMN_NAME_MAP;
    private static MiuiPduPersister sPersister;
    private final ContentResolver mContentResolver;
    private final Context mContext;

    /*
     * Enabled aggressive block sorting
     */
    static {
        boolean bl = !MiuiPduPersister.class.desiredAssertionStatus();
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
        LONG_COLUMN_NAME_MAP.put((Object)133, (Object)"date_sent");
        LONG_COLUMN_NAME_MAP.put((Object)135, (Object)"d_tm");
        LONG_COLUMN_NAME_MAP.put((Object)136, (Object)"exp");
        LONG_COLUMN_NAME_MAP.put((Object)142, (Object)"m_size");
        PDU_CACHE_INSTANCE = PduCache.getInstance();
    }

    private MiuiPduPersister(Context context) {
        this.mContext = context;
        this.mContentResolver = context.getContentResolver();
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
            return MiuiPduPersister.getBytes(cursor.getString(n));
        }
        return null;
    }

    public static byte[] getBytes(String string) {
        try {
            string = (String)string.getBytes("iso-8859-1");
            return string;
        }
        catch (UnsupportedEncodingException var0_1) {
            Log.e((String)"MiuiPduPersister", (String)"ISO_8859_1 must be supported!", (Throwable)var0_1);
            return new byte[0];
        }
    }

    private Integer getIntegerFromPartColumn(Cursor cursor, int n) {
        if (!cursor.isNull(n)) {
            return cursor.getInt(n);
        }
        return null;
    }

    public static MiuiPduPersister getPduPersister(Context context) {
        if (sPersister == null || !context.equals((Object)MiuiPduPersister.sPersister.mContext)) {
            sPersister = new MiuiPduPersister(context);
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

    private void persistAddress(ArrayList<ContentProviderOperation> arrayList, long l, int n, EncodedStringValue[] arrencodedStringValue) {
        ContentValues contentValues = new ContentValues(3);
        for (EncodedStringValue encodedStringValue : arrencodedStringValue) {
            contentValues.clear();
            contentValues.put("address", MiuiPduPersister.toIsoString(encodedStringValue.getTextString()));
            contentValues.put("charset", Integer.valueOf((int)encodedStringValue.getCharacterSet()));
            contentValues.put("type", Integer.valueOf((int)n));
            arrayList.add((Object)ContentProviderOperation.newInsert((Uri)Uri.parse((String)("content://mms/" + l + "/addr"))).withValues(contentValues).build());
        }
    }

    /*
     * Exception decompiling
     */
    private void persistData(PduPart var1_1, Uri var2_11, String var3_14) throws MmsException {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [5[TRYBLOCK]], but top level block is 45[SIMPLE_IF_TAKEN]
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
            pduHeaders.setEncodedStringValue(new EncodedStringValue(cursor.getInt(((Integer)CHARSET_COLUMN_INDEX_MAP.get((Object)n2)).intValue()), MiuiPduPersister.getBytes(string)), n2);
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
            pduHeaders.setTextString(MiuiPduPersister.getBytes((String)object), n2);
        }
    }

    public static String toIsoString(byte[] object) {
        try {
            object = new String((byte[])object, "iso-8859-1");
            return object;
        }
        catch (UnsupportedEncodingException var0_1) {
            Log.e((String)"MiuiPduPersister", (String)"ISO_8859_1 must be supported!", (Throwable)var0_1);
            return "";
        }
    }

    private void updateAddress(long l, int n, EncodedStringValue[] arrencodedStringValue) {
        ArrayList arrayList = Lists.newArrayList();
        arrayList.add((Object)ContentProviderOperation.newDelete((Uri)Uri.parse((String)("content://mms/" + l + "/addr"))).withSelection("type=" + n, null).build());
        this.persistAddress(arrayList, l, n, arrencodedStringValue);
        try {
            this.mContentResolver.applyBatch("mms", arrayList);
            return;
        }
        catch (OperationApplicationException var4_4) {
            Log.e((String)"MiuiPduPersister", (String)"Error while applying batch", (Throwable)var4_4);
            return;
        }
        catch (RemoteException var4_5) {
            Log.e((String)"MiuiPduPersister", (String)"Error while applying batch", (Throwable)var4_5);
            return;
        }
    }

    private void updatePart(Uri uri, PduPart pduPart) throws MmsException {
        ContentValues contentValues = new ContentValues(7);
        int n = pduPart.getCharset();
        if (n != 0) {
            contentValues.put("chset", Integer.valueOf((int)n));
        }
        if (pduPart.getContentType() != null) {
            String string = MiuiPduPersister.toIsoString(pduPart.getContentType());
            contentValues.put("ct", string);
            if (pduPart.getFilename() != null) {
                contentValues.put("fn", new String(pduPart.getFilename()));
            }
            if (pduPart.getName() != null) {
                contentValues.put("name", new String(pduPart.getName()));
            }
            if (pduPart.getContentDisposition() != null) {
                contentValues.put("cd", MiuiPduPersister.toIsoString(pduPart.getContentDisposition()));
            }
            if (pduPart.getContentId() != null) {
                contentValues.put("cid", MiuiPduPersister.toIsoString(pduPart.getContentId()));
            }
            if (pduPart.getContentLocation() != null) {
                contentValues.put("cl", MiuiPduPersister.toIsoString(pduPart.getContentLocation()));
            }
            SqliteWrapper.update(this.mContext, this.mContentResolver, uri, contentValues, null, null);
            if (pduPart.getData() != null || uri != pduPart.getDataUri()) {
                this.persistData(pduPart, uri, string);
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

    public Uri persist(GenericPdu genericPdu, Uri uri, Uri uri2, long l) throws MmsException {
        return this.persist(genericPdu, uri, uri2, l, 0);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public Uri persist(GenericPdu object, Uri arrencodedStringValue, Uri object2, long l, int n) throws MmsException {
        Map.Entry entry2222;
        int n2;
        Object object3;
        long l2;
        EncodedStringValue[] arrencodedStringValue2;
        block44 : {
            EncodedStringValue encodedStringValue /* !! */ ;
            Object object4;
            long l3;
            EncodedStringValue encodedStringValue2 /* !! */ ;
            if (arrencodedStringValue == null) {
                throw new MmsException("Uri may not be null.");
            }
            l3 = -1;
            try {
                l3 = l2 = ContentUris.parseId((Uri)arrencodedStringValue);
            }
            catch (NumberFormatException var15_12) {}
            n2 = l3 != -1 ? 1 : 0;
            if (n2 == 0 && MESSAGE_BOX_MAP.get((Object)arrencodedStringValue) == null) {
                throw new MmsException("Bad destination, must be one of content://mms/inbox, content://mms/sent, content://mms/drafts, content://mms/outbox, content://mms/temp.");
            }
            arrencodedStringValue2 = PDU_CACHE_INSTANCE;
            synchronized (arrencodedStringValue2) {
                boolean bl = PDU_CACHE_INSTANCE.isUpdating((Uri)arrencodedStringValue);
                if (bl) {
                    try {
                        PDU_CACHE_INSTANCE.wait();
                    }
                    catch (InterruptedException var16_16) {
                        Log.e((String)"MiuiPduPersister", (String)"persist1: ", (Throwable)var16_16);
                    }
                }
            }
            PDU_CACHE_INSTANCE.purge((Uri)arrencodedStringValue);
            Object object5 = object.getPduHeaders();
            ContentValues contentValues = new ContentValues();
            contentValues.put("block_type", Integer.valueOf((int)n));
            for (Map.Entry entry2222 : ENCODED_STRING_COLUMN_NAME_MAP.entrySet()) {
                n = (Integer)entry2222.getKey();
                encodedStringValue /* !! */  = object5.getEncodedStringValue(n);
                if (encodedStringValue /* !! */  == null) continue;
                object4 = (String)CHARSET_COLUMN_NAME_MAP.get((Object)n);
                contentValues.put((String)entry2222.getValue(), MiuiPduPersister.toIsoString(encodedStringValue /* !! */ .getTextString()));
                contentValues.put((String)object4, Integer.valueOf((int)encodedStringValue /* !! */ .getCharacterSet()));
            }
            for (Map.Entry entry2222 : TEXT_STRING_COLUMN_NAME_MAP.entrySet()) {
                encodedStringValue /* !! */  = (EncodedStringValue)object5.getTextString((Integer)entry2222.getKey());
                if (encodedStringValue /* !! */  == null) continue;
                contentValues.put((String)entry2222.getValue(), MiuiPduPersister.toIsoString((byte[])encodedStringValue /* !! */ ));
            }
            for (Map.Entry entry2222 : OCTET_COLUMN_NAME_MAP.entrySet()) {
                n = object5.getOctet((Integer)entry2222.getKey());
                if (n == 0) continue;
                contentValues.put((String)entry2222.getValue(), Integer.valueOf((int)n));
            }
            for (Map.Entry entry2222 : LONG_COLUMN_NAME_MAP.entrySet()) {
                l2 = object5.getLongInteger((Integer)entry2222.getKey());
                if (l2 == -1) continue;
                contentValues.put((String)entry2222.getValue(), Long.valueOf((long)l2));
            }
            contentValues.put("date_full", Long.valueOf((long)System.currentTimeMillis()));
            entry2222 = new HashMap(ADDRESS_FIELDS.length);
            encodedStringValue /* !! */  = (EncodedStringValue)ADDRESS_FIELDS;
            object3 = encodedStringValue /* !! */ .length;
            for (n = 0; n < object3; ++n) {
                encodedStringValue2 /* !! */  = encodedStringValue /* !! */ [n];
                arrencodedStringValue2 = null;
                if (encodedStringValue2 /* !! */  == 137) {
                    object4 = object5.getEncodedStringValue((int)encodedStringValue2 /* !! */ );
                    if (object4 != null) {
                        arrencodedStringValue2 = new EncodedStringValue[]{object4};
                    }
                } else {
                    arrencodedStringValue2 = object5.getEncodedStringValues((int)encodedStringValue2 /* !! */ );
                }
                entry2222.put((Object)((int)encodedStringValue2 /* !! */ ), (Object)arrencodedStringValue2);
            }
            encodedStringValue /* !! */  = new HashSet();
            object3 = object.getMessageType();
            if (l == -1 && object3 == 130 || object3 == 132 || object3 == 128) {
                arrencodedStringValue2 = object5 = null;
                switch (object3) {
                    default: {
                        arrencodedStringValue2 = object5;
                        break;
                    }
                    case 130: 
                    case 132: {
                        arrencodedStringValue2 = (EncodedStringValue[])entry2222.get((Object)137);
                        break;
                    }
                    case 128: {
                        arrencodedStringValue2 = (EncodedStringValue[])entry2222.get((Object)151);
                    }
                    case 129: 
                    case 131: 
                }
                if (arrencodedStringValue2 != null) {
                    encodedStringValue2 /* !! */  = (EncodedStringValue)arrencodedStringValue2.length;
                    for (n = 0; n < encodedStringValue2 /* !! */ ; ++n) {
                        object5 = arrencodedStringValue2[n];
                        if (object5 == null) continue;
                        encodedStringValue /* !! */ .add((Object)object5.getString());
                    }
                }
                if (!encodedStringValue /* !! */ .isEmpty()) {
                    contentValues.put("thread_id", Long.valueOf((long)Telephony.Threads.getOrCreateThreadId((Context)this.mContext, (Set)((Object)encodedStringValue /* !! */ ))));
                    if (encodedStringValue /* !! */ .size() == 1 && (object3 == 130 || object3 == 132)) {
                        if (ExtraTelephony.isServiceNumber((String)((String)encodedStringValue /* !! */ .iterator().next()))) {
                            contentValues.put("advanced_seen", Integer.valueOf((int)1));
                        } else {
                            contentValues.put("advanced_seen", Integer.valueOf((int)0));
                        }
                    }
                }
            }
            l2 = System.currentTimeMillis();
            if (object instanceof MultimediaMessagePdu && (object = ((MultimediaMessagePdu)object).getBody()) != null) {
                object3 = object.getPartsNum();
                for (n = 0; n < object3; ++n) {
                    this.persistPart(object.getPart(n), l2);
                }
            }
            arrencodedStringValue2 = Lists.newArrayList();
            object = null;
            if (n2 != 0) {
                object = arrencodedStringValue;
                arrencodedStringValue2.add((Object)ContentProviderOperation.newUpdate((Uri)arrencodedStringValue).withValues(contentValues).build());
            } else {
                arrencodedStringValue2.add((Object)ContentProviderOperation.newInsert((Uri)arrencodedStringValue).withValues(contentValues).build());
            }
            if (object2 != null) {
                arrencodedStringValue2.add((Object)ContentProviderOperation.newDelete((Uri)object2).build());
            }
            try {
                object5 = this.mContentResolver.applyBatch("mms", (ArrayList)arrencodedStringValue2);
                l = l3;
                object2 = object;
                if (object != null) break block44;
                object2 = object5[0].uri;
                l = ContentUris.parseId((Uri)object2);
            }
            catch (OperationApplicationException var1_2) {
                Log.e((String)"MiuiPduPersister", (String)"Error while applying batch", (Throwable)var1_2);
                throw new MmsException("Error while applying batch.");
            }
            catch (RemoteException var1_3) {
                Log.e((String)"MiuiPduPersister", (String)"Error while applying batch", (Throwable)var1_3);
                throw new MmsException("Error while applying batch.");
            }
        }
        arrencodedStringValue2.clear();
        object = new ContentValues(1);
        object.put("mid", Long.valueOf((long)l));
        arrencodedStringValue2.add((Object)ContentProviderOperation.newUpdate((Uri)Uri.parse((String)("content://mms/" + l2 + "/part"))).withValues((ContentValues)object).build());
        if (n2 == 0) {
            object2 = Uri.parse((String)(arrencodedStringValue + "/" + l));
        }
        object = ADDRESS_FIELDS;
        n2 = object.length;
        for (n = 0; n < n2; ++n) {
            object3 = object[n];
            arrencodedStringValue = (EncodedStringValue[])entry2222.get((Object)((int)object3));
            if (arrencodedStringValue == null) continue;
            this.persistAddress((ArrayList<ContentProviderOperation>)arrencodedStringValue2, l, (int)object3, arrencodedStringValue);
        }
        try {
            this.mContentResolver.applyBatch("mms", (ArrayList)arrencodedStringValue2);
            return object2;
        }
        catch (OperationApplicationException var1_4) {
            Log.e((String)"MiuiPduPersister", (String)"Error while applying batch", (Throwable)var1_4);
            throw new MmsException("Error while applying batch.");
        }
        catch (RemoteException var1_5) {
            Log.e((String)"MiuiPduPersister", (String)"Error while applying batch", (Throwable)var1_5);
            throw new MmsException("Error while applying batch.");
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public Uri persistPart(PduPart var1_1, long var2_2) throws MmsException {
        var8_3 = Uri.parse((String)("content://mms/" + var2_2 + "/part"));
        var9_4 = new ContentValues(8);
        var4_5 = var1_1.getCharset();
        if (var4_5 != 0) {
            var9_4.put("chset", Integer.valueOf((int)var4_5));
        }
        if (var1_1.getContentType() == null) throw new MmsException("MIME type of the part must be set.");
        var6_7 = var5_6 = MiuiPduPersister.toIsoString(var1_1.getContentType());
        if ("text/x-vCard".equalsIgnoreCase(var5_6)) {
            Log.e((String)"MiuiPduPersister", (String)(var5_6 + " -> " + "text/x-vCard"));
            var6_7 = "text/x-vCard";
        }
        var5_6 = var6_7;
        if (var1_1.getContentLocation() == null) ** GOTO lbl31
        if ("text/plain".equalsIgnoreCase(var6_7)) ** GOTO lbl16
        var5_6 = var6_7;
        if (!"application/oct-stream".equalsIgnoreCase(var6_7)) ** GOTO lbl31
lbl16: // 2 sources:
        var10_8 = new String(var1_1.getContentLocation());
        var7_9 = var6_7;
        if (var10_8 == null) ** GOTO lbl24
        if (var10_8.endsWith(".vcf")) ** GOTO lbl-1000
        var7_9 = var6_7;
        if (var10_8.endsWith(".VCF")) lbl-1000: // 2 sources:
        {
            Log.e((String)"MiuiPduPersister", (String)(var6_7 + " -> " + "text/x-vCard"));
            var7_9 = "text/x-vCard";
        }
lbl24: // 4 sources:
        var5_6 = var7_9;
        if (var10_8 == null) ** GOTO lbl31
        if (var10_8.endsWith(".ogg")) ** GOTO lbl-1000
        var5_6 = var7_9;
        if (var10_8.endsWith(".OGG")) lbl-1000: // 2 sources:
        {
            Log.e((String)"MiuiPduPersister", (String)(var7_9 + " -> " + "application/ogg"));
            var5_6 = "application/ogg";
        }
lbl31: // 6 sources:
        if (var1_1.getContentLocation() != null && "text/plain".equalsIgnoreCase(var5_6) && (var6_7 = new String(var1_1.getContentLocation())) != null && (var6_7.endsWith(".txt") || var6_7.endsWith(".TXT")) && var1_1.getCharset() == 0) {
            Log.i((String)"MiuiPduPersister", (String)"Set default text charset to UTF_8");
            var9_4.put("chset", Integer.valueOf((int)106));
        }
        var6_7 = var5_6;
        if ("image/jpg".equals((Object)var5_6)) {
            var6_7 = "image/jpeg";
        }
        var9_4.put("ct", var6_7);
        if ("application/smil".equals((Object)var6_7)) {
            var9_4.put("seq", Integer.valueOf((int)-1));
        }
        if (var1_1.getFilename() != null) {
            var9_4.put("fn", new String(var1_1.getFilename()));
        }
        if (var1_1.getName() != null) {
            var9_4.put("name", new String(var1_1.getName()));
        }
        if (var1_1.getContentDisposition() != null) {
            var9_4.put("cd", MiuiPduPersister.toIsoString(var1_1.getContentDisposition()));
        }
        if (var1_1.getContentId() != null) {
            var9_4.put("cid", MiuiPduPersister.toIsoString(var1_1.getContentId()));
        }
        if (var1_1.getContentLocation() != null) {
            var9_4.put("cl", MiuiPduPersister.toIsoString(var1_1.getContentLocation()));
        }
        if ((var5_6 = SqliteWrapper.insert(this.mContext, this.mContentResolver, var8_3, var9_4)) == null) {
            throw new MmsException("Failed to persist part, return null.");
        }
        this.persistData(var1_1, (Uri)var5_6, var6_7);
        var1_1.setDataUri((Uri)var5_6);
        return var5_6;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public ArrayList<Uri> recoverPersist(ArrayList<GenericPdu> uri, ArrayList<Uri> arrayList) throws MmsException {
        long l;
        EncodedStringValue[] arrencodedStringValue;
        Object object;
        int n = uri.size();
        if (n != arrayList.size()) {
            throw new MmsException("pdus or uris number error");
        }
        ArrayList arrayList2 = Lists.newArrayList();
        ArrayList arrayList3 = Lists.newArrayList();
        ArrayList arrayList4 = Lists.newArrayList();
        ArrayList arrayList5 = Lists.newArrayList();
        ArrayList arrayList6 = Lists.newArrayList();
        long[] arrl = new long[n];
        long[] arrl2 = new long[n];
        int n2 = 0;
        do {
            Object object2;
            int n3;
            EncodedStringValue encodedStringValue /* !! */ ;
            Map.Entry entry222;
            if (n2 >= n) {
                block44 : {
                    try {
                        this.recoverPersistPart(arrayList5, arrayList6);
                        uri = this.mContentResolver.applyBatch("mms", arrayList2);
                        if (uri != null && uri.length == n) {
                            for (n2 = 0; n2 < n; ++n2) {
                                arrl[n2] = ContentUris.parseId((Uri)uri[n2].uri);
                            }
                            break block44;
                        }
                        throw new MmsException("insert pdu error");
                    }
                    catch (OperationApplicationException var1_2) {
                        Log.e((String)"MiuiPduPersister", (String)"Error while applying batch", (Throwable)var1_2);
                        throw new MmsException("Error while applying batch.");
                    }
                    catch (RemoteException var1_3) {
                        Log.e((String)"MiuiPduPersister", (String)"Error while applying batch", (Throwable)var1_3);
                        throw new MmsException("Error while applying batch.");
                    }
                }
                arrayList2.clear();
                break;
            }
            GenericPdu genericPdu = (GenericPdu)uri.get(n2);
            Uri uri2 = (Uri)arrayList.get(n2);
            if (uri2 == null) {
                throw new MmsException("Uri may not be null.");
            }
            if (MESSAGE_BOX_MAP.get((Object)uri2) == null) {
                throw new MmsException("Bad destination, must be one of content://mms/inbox, content://mms/sent, content://mms/drafts, content://mms/outbox, content://mms/temp.");
            }
            object = PDU_CACHE_INSTANCE;
            synchronized (object) {
                boolean bl = PDU_CACHE_INSTANCE.isUpdating(uri2);
                if (bl) {
                    try {
                        PDU_CACHE_INSTANCE.wait();
                    }
                    catch (InterruptedException var14_23) {
                        Log.e((String)"MiuiPduPersister", (String)"persist1: ", (Throwable)var14_23);
                    }
                }
            }
            PDU_CACHE_INSTANCE.purge(uri2);
            arrencodedStringValue = genericPdu.getPduHeaders();
            ContentValues contentValues = new ContentValues();
            contentValues.put("block_type", Integer.valueOf((int)0));
            for (Map.Entry entry222 : ENCODED_STRING_COLUMN_NAME_MAP.entrySet()) {
                n3 = (Integer)entry222.getKey();
                encodedStringValue /* !! */  = arrencodedStringValue.getEncodedStringValue(n3);
                if (encodedStringValue /* !! */  == null) continue;
                object2 = (String)CHARSET_COLUMN_NAME_MAP.get((Object)n3);
                contentValues.put((String)entry222.getValue(), MiuiPduPersister.toIsoString(encodedStringValue /* !! */ .getTextString()));
                contentValues.put((String)object2, Integer.valueOf((int)encodedStringValue /* !! */ .getCharacterSet()));
            }
            for (Map.Entry entry222 : TEXT_STRING_COLUMN_NAME_MAP.entrySet()) {
                encodedStringValue /* !! */  = (EncodedStringValue)arrencodedStringValue.getTextString((Integer)entry222.getKey());
                if (encodedStringValue /* !! */  == null) continue;
                contentValues.put((String)entry222.getValue(), MiuiPduPersister.toIsoString((byte[])encodedStringValue /* !! */ ));
            }
            for (Map.Entry entry222 : OCTET_COLUMN_NAME_MAP.entrySet()) {
                n3 = arrencodedStringValue.getOctet((Integer)entry222.getKey());
                if (n3 == 0) continue;
                contentValues.put((String)entry222.getValue(), Integer.valueOf((int)n3));
            }
            object = LONG_COLUMN_NAME_MAP.entrySet().iterator();
            while (object.hasNext()) {
                entry222 = (Map.Entry)object.next();
                l = arrencodedStringValue.getLongInteger((Integer)entry222.getKey());
                if (l == -1) continue;
                contentValues.put((String)entry222.getValue(), Long.valueOf((long)l));
            }
            contentValues.put("date_full", Long.valueOf((long)System.currentTimeMillis()));
            entry222 = new HashMap(ADDRESS_FIELDS.length);
            for (Iterator iterator2 : (EncodedStringValue)ADDRESS_FIELDS) {
                object = null;
                if (iterator2 == 137) {
                    object2 = arrencodedStringValue.getEncodedStringValue((int)iterator2);
                    if (object2 != null) {
                        object = new EncodedStringValue[]{object2};
                    }
                } else {
                    object = arrencodedStringValue.getEncodedStringValues((int)iterator2);
                }
                entry222.put((Object)((int)iterator2), object);
            }
            arrayList4.add((Object)entry222);
            encodedStringValue /* !! */  = new HashSet();
            int n4 = genericPdu.getMessageType();
            if (n4 == 130 || n4 == 132 || n4 == 128) {
                object = arrencodedStringValue = null;
                switch (n4) {
                    default: {
                        object = arrencodedStringValue;
                        break;
                    }
                    case 130: 
                    case 132: {
                        object = (EncodedStringValue[])entry222.get((Object)137);
                        break;
                    }
                    case 128: {
                        object = (EncodedStringValue[])entry222.get((Object)151);
                    }
                    case 129: 
                    case 131: 
                }
                if (object != null) {
                    Iterator iterator2;
                    iterator2 = (Iterator)object.length;
                    for (n3 = 0; n3 < iterator2; ++n3) {
                        arrencodedStringValue = object[n3];
                        if (arrencodedStringValue == null) continue;
                        encodedStringValue /* !! */ .add((Object)arrencodedStringValue.getString());
                    }
                }
                if (!encodedStringValue /* !! */ .isEmpty()) {
                    contentValues.put("thread_id", Long.valueOf((long)Telephony.Threads.getOrCreateThreadId((Context)this.mContext, (Set)((Object)encodedStringValue /* !! */ ))));
                    if (encodedStringValue /* !! */ .size() == 1 && (n4 == 130 || n4 == 132)) {
                        if (ExtraTelephony.isServiceNumber((String)((String)encodedStringValue /* !! */ .iterator().next()))) {
                            contentValues.put("advanced_seen", Integer.valueOf((int)1));
                        } else {
                            contentValues.put("advanced_seen", Integer.valueOf((int)0));
                        }
                    }
                }
            }
            arrl2[n2] = l = System.currentTimeMillis();
            if (genericPdu instanceof MultimediaMessagePdu && (object = ((MultimediaMessagePdu)genericPdu).getBody()) != null) {
                n4 = object.getPartsNum();
                for (n3 = 0; n3 < n4; ++n3) {
                    arrayList5.add((Object)object.getPart(n3));
                    arrayList6.add((Object)String.valueOf((long)l));
                }
            }
            arrayList2.add((Object)ContentProviderOperation.newInsert((Uri)uri2).withValues(contentValues).build());
            ++n2;
        } while (true);
        for (n2 = 0; n2 < n; ++n2) {
            l = arrl[n2];
            long l2 = arrl2[n2];
            uri = (Uri)arrayList.get(n2);
            object = new ContentValues(1);
            object.put("mid", Long.valueOf((long)l));
            arrayList2.add((Object)ContentProviderOperation.newUpdate((Uri)Uri.parse((String)("content://mms/" + l2 + "/part"))).withValues(object).build());
            arrayList3.add((Object)Uri.parse((String)((Object)uri + "/" + l)));
            uri = (HashMap)arrayList4.get(n2);
            for (Iterator iterator2 : (Object)ADDRESS_FIELDS) {
                arrencodedStringValue = (EncodedStringValue[])uri.get((Object)((int)iterator2));
                if (arrencodedStringValue == null) continue;
                this.persistAddress(arrayList2, l, (int)iterator2, arrencodedStringValue);
            }
        }
        try {
            this.mContentResolver.applyBatch("mms", arrayList2);
            return arrayList3;
        }
        catch (OperationApplicationException var1_4) {
            Log.e((String)"MiuiPduPersister", (String)"Error while applying batch", (Throwable)var1_4);
            throw new MmsException("Error while applying batch.");
        }
        catch (RemoteException var1_5) {
            Log.e((String)"MiuiPduPersister", (String)"Error while applying batch", (Throwable)var1_5);
            throw new MmsException("Error while applying batch.");
        }
    }

    /*
     * Exception decompiling
     */
    public void recoverPersistPart(ArrayList<PduPart> var1_1, ArrayList var2_4) throws MmsException {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // java.lang.IllegalStateException: Backjump on non jumping statement [] lbl80 : TryStatement: try { 1[TRYBLOCK]

        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.Cleaner$1.call(Cleaner.java:44)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.Cleaner$1.call(Cleaner.java:22)
        // org.benf.cfr.reader.util.graph.GraphVisitorDFS.process(GraphVisitorDFS.java:68)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.Cleaner.removeUnreachableCode(Cleaner.java:54)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.RemoveDeterministicJumps.apply(RemoveDeterministicJumps.java:35)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:507)
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
                    Log.e((String)"MiuiPduPersister", (String)"updateHeaders: ", (Throwable)var11_5);
                }
            }
        }
        PDU_CACHE_INSTANCE.purge(uri);
        pduCache = new ContentValues(10);
        Object object = arrencodedStringValue.getContentType();
        if (object != null) {
            pduCache.put("ct_t", MiuiPduPersister.toIsoString((byte[])object));
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
            pduCache.put("m_cls", MiuiPduPersister.toIsoString((byte[])object));
        }
        if ((n = arrencodedStringValue.getPriority()) != 0) {
            pduCache.put("pri", Integer.valueOf((int)n));
        }
        if ((n = arrencodedStringValue.getReadReport()) != 0) {
            pduCache.put("rr", Integer.valueOf((int)n));
        }
        if ((object = arrencodedStringValue.getTransactionId()) != null) {
            pduCache.put("tr_id", MiuiPduPersister.toIsoString((byte[])object));
        }
        if ((object = (Object)arrencodedStringValue.getSubject()) != null) {
            pduCache.put("sub", MiuiPduPersister.toIsoString(object.getTextString()));
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
    public void updateParts(Uri var1_1, PduBody var2_2) throws MmsException {
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

