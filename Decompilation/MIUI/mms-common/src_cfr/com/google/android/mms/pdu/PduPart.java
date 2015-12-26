/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.net.Uri
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.util.HashMap
 */
package com.google.android.mms.pdu;

import android.net.Uri;
import java.util.HashMap;
import java.util.Map;

public class PduPart {
    public static final String CONTENT_TRANSFER_ENCODING = "Content-Transfer-Encoding";
    static final byte[] DISPOSITION_ATTACHMENT;
    static final byte[] DISPOSITION_FROM_DATA;
    static final byte[] DISPOSITION_INLINE;
    public static final String P_7BIT = "7bit";
    public static final String P_8BIT = "8bit";
    public static final String P_BASE64 = "base64";
    public static final String P_BINARY = "binary";
    public static final int P_CHARSET = 129;
    public static final int P_COMMENT = 155;
    public static final int P_CONTENT_DISPOSITION = 197;
    public static final int P_CONTENT_ID = 192;
    public static final int P_CONTENT_LOCATION = 142;
    public static final int P_CONTENT_TRANSFER_ENCODING = 200;
    public static final int P_CONTENT_TYPE = 145;
    public static final int P_CREATION_DATE = 147;
    public static final int P_CT_MR_TYPE = 137;
    public static final int P_DEP_COMMENT = 140;
    public static final int P_DEP_CONTENT_DISPOSITION = 174;
    public static final int P_DEP_DOMAIN = 141;
    public static final int P_DEP_FILENAME = 134;
    public static final int P_DEP_NAME = 133;
    public static final int P_DEP_PATH = 143;
    public static final int P_DEP_START = 138;
    public static final int P_DEP_START_INFO = 139;
    public static final int P_DIFFERENCES = 135;
    public static final int P_DISPOSITION_ATTACHMENT = 129;
    public static final int P_DISPOSITION_FROM_DATA = 128;
    public static final int P_DISPOSITION_INLINE = 130;
    public static final int P_DOMAIN = 156;
    public static final int P_FILENAME = 152;
    public static final int P_LEVEL = 130;
    public static final int P_MAC = 146;
    public static final int P_MAX_AGE = 142;
    public static final int P_MODIFICATION_DATE = 148;
    public static final int P_NAME = 151;
    public static final int P_PADDING = 136;
    public static final int P_PATH = 157;
    public static final int P_Q = 128;
    public static final String P_QUOTED_PRINTABLE = "quoted-printable";
    public static final int P_READ_DATE = 149;
    public static final int P_SEC = 145;
    public static final int P_SECURE = 144;
    public static final int P_SIZE = 150;
    public static final int P_START = 153;
    public static final int P_START_INFO = 154;
    public static final int P_TYPE = 131;
    private static final String TAG = "PduPart";
    private byte[] mPartData = null;
    private Map<Integer, Object> mPartHeader = new HashMap();
    private Uri mUri = null;

    static {
        DISPOSITION_FROM_DATA = "from-data".getBytes();
        DISPOSITION_ATTACHMENT = "attachment".getBytes();
        DISPOSITION_INLINE = "inline".getBytes();
    }

    public String generateLocation() {
        byte[] arrby;
        byte[] arrby2 = arrby = (byte[])this.mPartHeader.get(151);
        if (arrby == null) {
            arrby2 = arrby = (byte[])this.mPartHeader.get(152);
            if (arrby == null) {
                arrby2 = (byte[])this.mPartHeader.get(142);
            }
        }
        if (arrby2 == null) {
            arrby2 = (byte[])this.mPartHeader.get(192);
            return "cid:" + new String(arrby2);
        }
        return new String(arrby2);
    }

    public int getCharset() {
        Integer n = (Integer)this.mPartHeader.get(129);
        if (n == null) {
            return 0;
        }
        return n;
    }

    public byte[] getContentDisposition() {
        return (byte[])this.mPartHeader.get(197);
    }

    public byte[] getContentId() {
        return (byte[])this.mPartHeader.get(192);
    }

    public byte[] getContentLocation() {
        return (byte[])this.mPartHeader.get(142);
    }

    public byte[] getContentTransferEncoding() {
        return (byte[])this.mPartHeader.get(200);
    }

    public byte[] getContentType() {
        return (byte[])this.mPartHeader.get(145);
    }

    public byte[] getData() {
        if (this.mPartData == null) {
            return null;
        }
        byte[] arrby = new byte[this.mPartData.length];
        System.arraycopy((Object)this.mPartData, (int)0, (Object)arrby, (int)0, (int)this.mPartData.length);
        return arrby;
    }

    public Uri getDataUri() {
        return this.mUri;
    }

    public byte[] getFilename() {
        return (byte[])this.mPartHeader.get(152);
    }

    public byte[] getName() {
        return (byte[])this.mPartHeader.get(151);
    }

    public void setCharset(int n) {
        this.mPartHeader.put(129, n);
    }

    public void setContentDisposition(byte[] arrby) {
        if (arrby == null) {
            throw new NullPointerException("null content-disposition");
        }
        this.mPartHeader.put(197, arrby);
    }

    public void setContentId(byte[] arrby) {
        if (arrby == null || arrby.length == 0) {
            throw new IllegalArgumentException("Content-Id may not be null or empty.");
        }
        if (arrby.length > 1 && arrby[0] == '<' && arrby[arrby.length - 1] == '>') {
            this.mPartHeader.put(192, arrby);
            return;
        }
        byte[] arrby2 = new byte[arrby.length + 2];
        arrby2[0] = 60;
        arrby2[arrby2.length - 1] = 62;
        System.arraycopy((Object)arrby, (int)0, (Object)arrby2, (int)1, (int)arrby.length);
        this.mPartHeader.put(192, arrby2);
    }

    public void setContentLocation(byte[] arrby) {
        if (arrby == null) {
            throw new NullPointerException("null content-location");
        }
        this.mPartHeader.put(142, arrby);
    }

    public void setContentTransferEncoding(byte[] arrby) {
        if (arrby == null) {
            throw new NullPointerException("null content-transfer-encoding");
        }
        this.mPartHeader.put(200, arrby);
    }

    public void setContentType(byte[] arrby) {
        if (arrby == null) {
            throw new NullPointerException("null content-type");
        }
        this.mPartHeader.put(145, arrby);
    }

    public void setData(byte[] arrby) {
        if (arrby == null) {
            return;
        }
        this.mPartData = new byte[arrby.length];
        System.arraycopy((Object)arrby, (int)0, (Object)this.mPartData, (int)0, (int)arrby.length);
    }

    public void setDataUri(Uri uri) {
        this.mUri = uri;
    }

    public void setFilename(byte[] arrby) {
        if (arrby == null) {
            throw new NullPointerException("null content-id");
        }
        this.mPartHeader.put(152, arrby);
    }

    public void setName(byte[] arrby) {
        if (arrby == null) {
            throw new NullPointerException("null content-id");
        }
        this.mPartHeader.put(151, arrby);
    }
}

