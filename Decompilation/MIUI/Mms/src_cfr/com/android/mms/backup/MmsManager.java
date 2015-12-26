/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentProviderOperation
 *  android.content.ContentProviderOperation$Builder
 *  android.content.ContentResolver
 *  android.content.ContentValues
 *  android.content.Context
 *  android.database.Cursor
 *  android.net.Uri
 *  android.net.Uri$Builder
 *  android.provider.Telephony
 *  android.provider.Telephony$Mms
 *  com.google.protobuf.ByteString
 *  java.io.File
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.HashMap
 *  java.util.Vector
 */
package com.android.mms.backup;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Telephony;
import com.android.mms.backup.MmsProtos;
import com.google.protobuf.ByteString;
import java.io.File;
import java.util.HashMap;
import java.util.Vector;

public class MmsManager {
    private static final String[] PDU_ADDR_COLUMNS;
    private static final String[] PDU_COLUMNS;
    private static final String[] PDU_PART_COLUMNS;
    private Uri CONTENT_URI = Telephony.Mms.CONTENT_URI;
    private Uri INSERT_URI = this.CONTENT_URI.buildUpon().appendQueryParameter("need_full_insert_uri", "1").appendQueryParameter("check_duplication", "1").build();
    private HashMap<String, Uri> mAttach2Uri;
    private ContentResolver mResolver;

    static {
        PDU_COLUMNS = new String[]{"_id", "date", "date_sent", "msg_box", "read", "m_id", "sub", "sub_cs", "ct_t", "ct_l", "exp", "m_cls", "m_type", "v", "m_size", "pri", "rr", "rpt_a", "resp_st", "st", "tr_id", "retr_st", "retr_txt", "retr_txt_cs", "read_status", "ct_cls", "resp_txt", "d_tm", "d_rpt", "locked", "seen", "date_ms_part", "mx_id", "mx_status"};
        PDU_ADDR_COLUMNS = new String[]{"address", "type", "charset"};
        PDU_PART_COLUMNS = new String[]{"_id", "seq", "ct", "name", "chset", "cd", "fn", "cid", "cl", "ctt_s", "ctt_t", "text"};
    }

    public MmsManager(Context context) {
        this.mResolver = context.getContentResolver();
    }

    private MmsProtos.PduAddr buildPduAddr(Cursor cursor) {
        MmsProtos.PduAddr.Builder builder = MmsProtos.PduAddr.newBuilder();
        if (!cursor.isNull(0)) {
            builder.setAddress(cursor.getString(0));
        }
        if (!cursor.isNull(1)) {
            builder.setType(cursor.getInt(1));
        }
        if (!cursor.isNull(2)) {
            builder.setCharset(cursor.getInt(2));
        }
        return builder.build();
    }

    private MmsProtos.PduPart buildPduPart(Cursor cursor) {
        MmsProtos.PduPart.Builder builder = MmsProtos.PduPart.newBuilder();
        long l = cursor.getLong(0);
        if (!cursor.isNull(1)) {
            builder.setSequence(cursor.getInt(1));
        }
        if (!cursor.isNull(2)) {
            builder.setContentType(cursor.getString(2));
        }
        if (!cursor.isNull(3)) {
            builder.setName(cursor.getString(3));
        }
        if (!cursor.isNull(4)) {
            builder.setCharset(cursor.getInt(4));
        }
        if (!cursor.isNull(5)) {
            builder.setContentDisposition(cursor.getString(5));
        }
        if (!cursor.isNull(6)) {
            builder.setFileName(cursor.getString(6));
        }
        if (!cursor.isNull(7)) {
            builder.setContentId(cursor.getString(7));
        }
        if (!cursor.isNull(8)) {
            builder.setContentLocation(cursor.getString(8));
        }
        if (!cursor.isNull(9)) {
            builder.setContentTypeStart(cursor.getInt(9));
        }
        if (!cursor.isNull(10)) {
            builder.setContentTypeType(cursor.getString(10));
        }
        if (!cursor.isNull(11)) {
            builder.setText(cursor.getString(11));
        }
        cursor = Uri.withAppendedPath((Uri)Telephony.Mms.CONTENT_URI, (String)("part/" + l));
        String string = Uri.encode((String)cursor.toString());
        this.mAttach2Uri.put((Object)string, (Object)cursor);
        builder.setData(ByteString.copyFrom((byte[])string.getBytes()));
        return builder.build();
    }

    /*
     * Enabled aggressive block sorting
     */
    private String getAddresses(MmsProtos.Pdu pdu) {
        int n;
        int n2 = n = 0;
        switch (pdu.getMsgType()) {
            default: {
                n2 = n;
                break;
            }
            case 130: 
            case 132: {
                n2 = 137;
                break;
            }
            case 128: {
                n2 = 151;
            }
            case 129: 
            case 131: 
        }
        StringBuilder stringBuilder = new StringBuilder();
        n = 0;
        while (n < pdu.getAddrsCount()) {
            MmsProtos.PduAddr pduAddr = pdu.getAddrs(n);
            if (pduAddr.hasAddress() && pduAddr.hasType() && pduAddr.getType() == n2) {
                if (stringBuilder.length() > 0) {
                    stringBuilder.append(',');
                }
                stringBuilder.append(pduAddr.getAddress());
            }
            ++n;
        }
        return stringBuilder.toString();
    }

    /*
     * Enabled aggressive block sorting
     */
    private void setBasicPduFields(MmsProtos.Pdu.Builder builder, Cursor cursor) {
        boolean bl;
        boolean bl2 = true;
        if (!cursor.isNull(0)) {
            builder.setLuid(cursor.getString(0));
        }
        if (!cursor.isNull(1)) {
            builder.setDate(cursor.getLong(1));
        }
        if (!cursor.isNull(2)) {
            builder.setServerDate(cursor.getLong(2));
        }
        if (!cursor.isNull(3)) {
            builder.setMsgBox(cursor.getInt(3));
        }
        if (!cursor.isNull(4)) {
            bl = cursor.getInt(4) > 0;
            builder.setRead(bl);
        }
        if (!cursor.isNull(5)) {
            builder.setMId(cursor.getString(5));
        }
        if (!cursor.isNull(6)) {
            builder.setSubject(cursor.getString(6));
        }
        if (!cursor.isNull(7)) {
            builder.setSubjectCharset(cursor.getInt(7));
        }
        if (!cursor.isNull(8)) {
            builder.setContentType(cursor.getString(8));
        }
        if (!cursor.isNull(9)) {
            builder.setContentLocation(cursor.getString(9));
        }
        if (!cursor.isNull(10)) {
            builder.setExpiry(cursor.getLong(10));
        }
        if (!cursor.isNull(11)) {
            builder.setMsgClass(cursor.getString(11));
        }
        if (!cursor.isNull(12)) {
            builder.setMsgType(cursor.getInt(12));
        }
        if (!cursor.isNull(13)) {
            builder.setMmsVersion(cursor.getInt(13));
        }
        if (!cursor.isNull(14)) {
            builder.setMsgSize(cursor.getInt(14));
        }
        if (!cursor.isNull(15)) {
            builder.setPriority(cursor.getInt(15));
        }
        if (!cursor.isNull(16)) {
            builder.setReadReport(cursor.getInt(16));
        }
        if (!cursor.isNull(17)) {
            bl = cursor.getInt(17) > 0;
            builder.setReportAllowed(bl);
        }
        if (!cursor.isNull(18)) {
            builder.setResponseStatus(cursor.getInt(18));
        }
        if (!cursor.isNull(19)) {
            builder.setStatus(cursor.getInt(19));
        }
        if (!cursor.isNull(20)) {
            builder.setTransactionId(cursor.getString(20));
        }
        if (!cursor.isNull(21)) {
            builder.setRetrieveStatus(cursor.getInt(21));
        }
        if (!cursor.isNull(22)) {
            builder.setRetrieveText(cursor.getString(22));
        }
        if (!cursor.isNull(23)) {
            builder.setRetrieveTextCharset(cursor.getInt(23));
        }
        if (!cursor.isNull(24)) {
            builder.setReadStatus(cursor.getInt(24));
        }
        if (!cursor.isNull(25)) {
            builder.setContentClass(cursor.getInt(25));
        }
        if (!cursor.isNull(26)) {
            builder.setResponseText(cursor.getString(26));
        }
        if (!cursor.isNull(27)) {
            builder.setDeliveryTime(cursor.getLong(27));
        }
        if (!cursor.isNull(28)) {
            builder.setDeliveryReport(cursor.getInt(28));
        }
        if (!cursor.isNull(29)) {
            bl = cursor.getInt(29) > 0;
            builder.setLocked(bl);
        }
        if (!cursor.isNull(30)) {
            bl = cursor.getInt(30) > 0 ? bl2 : false;
            builder.setSeen(bl);
        }
        if (!cursor.isNull(31)) {
            builder.setDateMsPart(cursor.getInt(31));
        }
        if (!cursor.isNull(32)) {
            builder.setMxId(cursor.getLong(32));
        }
        if (!cursor.isNull(33)) {
            builder.setMxStatus(cursor.getInt(33));
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public MmsProtos.Pdu backupToProtocolBuffer(long var1_1) {
        block16 : {
            block15 : {
                var5_2 = MmsProtos.Pdu.newBuilder();
                var3_7 = var4_3 = null;
                var6_8 = Uri.withAppendedPath((Uri)Telephony.Mms.CONTENT_URI, (String)String.valueOf((long)var1_1));
                var3_7 = var4_3;
                var4_3 = this.mResolver.query(var6_8, MmsManager.PDU_COLUMNS, null, null, null);
                if (var4_3 != null) {
                    var3_7 = var4_3;
                    if (var4_3.moveToFirst()) {
                        var3_7 = var4_3;
                        this.setBasicPduFields(var5_2, var4_3);
                    }
                }
                if (var4_3 == null) break block15;
                var4_3.close();
            }
            var3_7 = var4_3;
            try {
                var6_8 = Uri.withAppendedPath((Uri)Telephony.Mms.CONTENT_URI, (String)("" + var1_1 + "/addr"));
                var3_7 = var4_3;
                var4_3 = this.mResolver.query(var6_8, MmsManager.PDU_ADDR_COLUMNS, null, null, null);
                if (var4_3 != null) {
                    var3_7 = var4_3;
                    var4_3.moveToPosition(-1);
                    do {
                        var3_7 = var4_3;
                        if (var4_3.moveToNext()) {
                            var3_7 = var4_3;
                            var5_2.addAddrs(this.buildPduAddr(var4_3));
                            continue;
                        }
                        ** GOTO lbl39
                        break;
                    } while (true);
                }
                break block16;
            }
            catch (Throwable var4_4) {
                if (var3_7 == null) throw var4_4;
                var3_7.close();
                throw var4_4;
            }
            catch (Throwable var4_5) {
                if (var3_7 == null) throw var4_5;
                var3_7.close();
                throw var4_5;
            }
        }
        if (var4_3 != null) {
            var4_3.close();
        }
        var3_7 = var4_3;
        try {
            var6_8 = Uri.withAppendedPath((Uri)Telephony.Mms.CONTENT_URI, (String)("" + var1_1 + "/part"));
            var3_7 = var4_3;
            var4_3 = this.mResolver.query(var6_8, MmsManager.PDU_PART_COLUMNS, null, null, null);
            if (var4_3 != null) {
                var3_7 = var4_3;
                var4_3.moveToPosition(-1);
                do {
                    var3_7 = var4_3;
                    if (var4_3.moveToNext()) {
                        var3_7 = var4_3;
                        var5_2.addPduParts(this.buildPduPart(var4_3));
                        continue;
                    }
                    break;
                    break;
                } while (true);
            }
        }
        catch (Throwable var4_6) {
            if (var3_7 == null) throw var4_6;
            var3_7.close();
            throw var4_6;
        }
        if (var4_3 == null) return var5_2.build();
        var4_3.close();
        return var5_2.build();
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public Vector<String> prepareAllMmsIds() {
        block5 : {
            var3_1 = new Vector();
            var2_2 = this.mResolver.query(Telephony.Mms.CONTENT_URI, new String[]{"_id"}, "timed=0 AND mx_status!=1 AND mx_status!=16 AND (msg_box=1 OR msg_box=2)", null, "date ASC");
            if (var2_2 != null) break block5;
            if (var2_2 == null) return var3_1;
            var2_2.close();
            return var3_1;
        }
        try {
            var1_4 = var2_2.getColumnIndexOrThrow("_id");
            var2_2.moveToFirst();
            while (!var2_2.isAfterLast()) {
                var3_1.add((Object)String.valueOf((long)var2_2.getLong(var1_4)));
                var2_2.moveToNext();
            }
            ** GOTO lbl21
        }
        catch (Throwable var4_5) {
            var3_1 = var2_2;
            var2_2 = var4_5;
            ** GOTO lbl26
lbl21: // 1 sources:
            if (var2_2 == null) return var3_1;
            var2_2.close();
            return var3_1;
            catch (Throwable var2_3) {
                var3_1 = null;
            }
lbl26: // 2 sources:
            if (var3_1 == null) throw var2_2;
            var3_1.close();
            throw var2_2;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public ContentProviderOperation restorePdu(MmsProtos.Pdu object) {
        int n;
        int n2 = 1;
        ContentValues contentValues = new ContentValues();
        if (object.hasDate()) {
            contentValues.put("date", Long.valueOf((long)object.getDate()));
        }
        if (object.hasServerDate()) {
            contentValues.put("date_sent", Long.valueOf((long)object.getServerDate()));
        }
        if (object.hasMsgBox()) {
            contentValues.put("msg_box", Integer.valueOf((int)object.getMsgBox()));
        }
        if (object.hasRead()) {
            n = object.getRead() ? 1 : 0;
            contentValues.put("read", Integer.valueOf((int)n));
        }
        if (object.hasMId()) {
            contentValues.put("m_id", object.getMId());
        }
        if (object.hasSubject()) {
            contentValues.put("sub", object.getSubject());
        }
        if (object.hasSubjectCharset()) {
            contentValues.put("sub_cs", Integer.valueOf((int)object.getSubjectCharset()));
        }
        if (object.hasContentType()) {
            contentValues.put("ct_t", object.getContentType());
        }
        if (object.hasContentLocation()) {
            contentValues.put("ct_l", object.getContentLocation());
        }
        if (object.hasExpiry()) {
            contentValues.put("exp", Long.valueOf((long)object.getExpiry()));
        }
        if (object.hasMsgClass()) {
            contentValues.put("m_cls", object.getMsgClass());
        }
        if (object.hasMsgType()) {
            contentValues.put("m_type", Integer.valueOf((int)object.getMsgType()));
        }
        if (object.hasMmsVersion()) {
            contentValues.put("v", Integer.valueOf((int)object.getMmsVersion()));
        }
        if (object.hasMsgSize()) {
            contentValues.put("m_size", Integer.valueOf((int)object.getMsgSize()));
        }
        if (object.hasPriority()) {
            contentValues.put("pri", Integer.valueOf((int)object.getPriority()));
        }
        if (object.hasReadReport()) {
            contentValues.put("rr", Integer.valueOf((int)object.getReadReport()));
        }
        if (object.hasReportAllowed()) {
            n = object.getReportAllowed() ? 1 : 0;
            contentValues.put("rpt_a", Integer.valueOf((int)n));
        }
        if (object.hasResponseStatus()) {
            contentValues.put("resp_st", Integer.valueOf((int)object.getResponseStatus()));
        }
        if (object.hasStatus()) {
            contentValues.put("st", Integer.valueOf((int)object.getStatus()));
        }
        if (object.hasTransactionId()) {
            contentValues.put("tr_id", object.getTransactionId());
        }
        if (object.hasRetrieveStatus()) {
            contentValues.put("retr_st", Integer.valueOf((int)object.getRetrieveStatus()));
        }
        if (object.hasRetrieveText()) {
            contentValues.put("retr_txt", object.getRetrieveText());
        }
        if (object.hasRetrieveTextCharset()) {
            contentValues.put("retr_txt_cs", Integer.valueOf((int)object.getRetrieveTextCharset()));
        }
        if (object.hasReadStatus()) {
            contentValues.put("read_status", Integer.valueOf((int)object.getReadStatus()));
        }
        if (object.hasContentClass()) {
            contentValues.put("ct_cls", Integer.valueOf((int)object.getContentClass()));
        }
        if (object.hasResponseText()) {
            contentValues.put("resp_txt", object.getResponseText());
        }
        if (object.hasDeliveryTime()) {
            contentValues.put("d_tm", Long.valueOf((long)object.getDeliveryTime()));
        }
        if (object.hasDeliveryReport()) {
            contentValues.put("d_rpt", Integer.valueOf((int)object.getDeliveryReport()));
        }
        if (object.hasLocked()) {
            n = object.getLocked() ? 1 : 0;
            contentValues.put("locked", Integer.valueOf((int)n));
        }
        if (object.hasSeen()) {
            n = object.getSeen() ? n2 : 0;
            contentValues.put("seen", Integer.valueOf((int)n));
        }
        if (object.hasDateMsPart()) {
            contentValues.put("date_ms_part", Integer.valueOf((int)object.getDateMsPart()));
        }
        if (object.hasMxId()) {
            contentValues.put("mx_id", Long.valueOf((long)object.getMxId()));
        }
        if (object.hasMxStatus()) {
            contentValues.put("mx_status", Integer.valueOf((int)object.getMxStatus()));
        }
        if ((object = this.getAddresses((MmsProtos.Pdu)object)).length() > 0) {
            contentValues.put("addresses", (String)object);
        }
        return ContentProviderOperation.newInsert((Uri)this.INSERT_URI).withValues(contentValues).build();
    }

    public ContentProviderOperation restorePduPart(long l, MmsProtos.PduPart pduPart) {
        ContentValues contentValues = new ContentValues();
        if (pduPart.hasSequence()) {
            contentValues.put("seq", Integer.valueOf((int)pduPart.getSequence()));
        }
        if (pduPart.hasContentType()) {
            contentValues.put("ct", pduPart.getContentType());
        }
        if (pduPart.hasName()) {
            contentValues.put("name", pduPart.getName());
        }
        if (pduPart.hasCharset()) {
            contentValues.put("chset", Integer.valueOf((int)pduPart.getCharset()));
        }
        if (pduPart.hasContentDisposition()) {
            contentValues.put("cd", pduPart.getContentDisposition());
        }
        if (pduPart.hasFileName()) {
            contentValues.put("fn", pduPart.getFileName());
        }
        if (pduPart.hasContentId()) {
            contentValues.put("cid", pduPart.getContentId());
        }
        if (pduPart.hasContentLocation()) {
            contentValues.put("cl", pduPart.getContentLocation());
        }
        if (pduPart.hasContentTypeStart()) {
            contentValues.put("ctt_s", Integer.valueOf((int)pduPart.getContentTypeStart()));
        }
        if (pduPart.hasContentTypeType()) {
            contentValues.put("ctt_t", pduPart.getContentTypeType());
        }
        if (pduPart.hasText()) {
            contentValues.put("text", pduPart.getText());
        }
        return ContentProviderOperation.newInsert((Uri)Telephony.Mms.CONTENT_URI.buildUpon().appendEncodedPath("" + l + "/part").appendQueryParameter("supress_making_mms_preview", "1").build()).withValues(contentValues).build();
    }

    public void restorePduPartFile(Uri uri, MmsProtos.PduPart object) {
        if (object.hasData()) {
            object = new File(new String(object.getData().toByteArray())).getName();
            this.mAttach2Uri.put(object, (Object)uri);
        }
    }

    public void setMmsAttach(HashMap<String, Uri> hashMap) {
        this.mAttach2Uri = hashMap;
    }
}

