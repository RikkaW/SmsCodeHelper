/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.util.Log
 *  java.io.ByteArrayOutputStream
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 *  java.util.ArrayList
 */
package com.google.android.mms.pdu;

import android.util.Log;
import com.google.android.mms.pdu.CharacterSets;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class EncodedStringValue
implements Cloneable {
    private static final boolean DEBUG = false;
    private static final boolean LOCAL_LOGV = false;
    private static final String TAG = "EncodedStringValue";
    private int mCharacterSet;
    private byte[] mData;

    public EncodedStringValue(int n, byte[] arrby) {
        if (arrby == null) {
            throw new NullPointerException("EncodedStringValue: Text-string is null.");
        }
        this.mCharacterSet = n;
        this.mData = new byte[arrby.length];
        System.arraycopy((Object)arrby, (int)0, (Object)this.mData, (int)0, (int)arrby.length);
    }

    public EncodedStringValue(String string) {
        try {
            this.mData = string.getBytes("utf-8");
            this.mCharacterSet = 106;
            return;
        }
        catch (UnsupportedEncodingException var1_2) {
            Log.e((String)"EncodedStringValue", (String)"Default encoding must be supported.", (Throwable)var1_2);
            return;
        }
    }

    public EncodedStringValue(byte[] arrby) {
        this(106, arrby);
    }

    public static String concat(EncodedStringValue[] arrencodedStringValue) {
        StringBuilder stringBuilder = new StringBuilder();
        int n = arrencodedStringValue.length - 1;
        for (int i = 0; i <= n; ++i) {
            stringBuilder.append(arrencodedStringValue[i].getString());
            if (i >= n) continue;
            stringBuilder.append(";");
        }
        return stringBuilder.toString();
    }

    public static EncodedStringValue copy(EncodedStringValue encodedStringValue) {
        if (encodedStringValue == null) {
            return null;
        }
        return new EncodedStringValue(encodedStringValue.mCharacterSet, encodedStringValue.mData);
    }

    public static EncodedStringValue[] encodeStrings(String[] arrstring) {
        EncodedStringValue[] arrencodedStringValue;
        int n = arrstring.length;
        if (n > 0) {
            EncodedStringValue[] arrencodedStringValue2 = new EncodedStringValue[n];
            int n2 = 0;
            do {
                arrencodedStringValue = arrencodedStringValue2;
                if (n2 < n) {
                    arrencodedStringValue2[n2] = new EncodedStringValue(arrstring[n2]);
                    ++n2;
                    continue;
                }
                break;
                break;
            } while (true);
        } else {
            arrencodedStringValue = null;
        }
        return arrencodedStringValue;
    }

    public static EncodedStringValue[] extract(String arrstring) {
        int n;
        arrstring = arrstring.split(";");
        ArrayList arrayList = new ArrayList();
        for (n = 0; n < arrstring.length; ++n) {
            if (arrstring[n].length() <= 0) continue;
            arrayList.add((Object)new EncodedStringValue(arrstring[n]));
        }
        n = arrayList.size();
        if (n > 0) {
            return (EncodedStringValue[])arrayList.toArray((Object[])new EncodedStringValue[n]);
        }
        return null;
    }

    public void appendTextString(byte[] arrby) {
        if (arrby == null) {
            throw new NullPointerException("Text-string is null.");
        }
        if (this.mData == null) {
            this.mData = new byte[arrby.length];
            System.arraycopy((Object)arrby, (int)0, (Object)this.mData, (int)0, (int)arrby.length);
            return;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write(this.mData);
            byteArrayOutputStream.write(arrby);
        }
        catch (IOException var1_2) {
            var1_2.printStackTrace();
            throw new NullPointerException("appendTextString: failed when write a new Text-string");
        }
        this.mData = byteArrayOutputStream.toByteArray();
    }

    public Object clone() throws CloneNotSupportedException {
        super.clone();
        int n = this.mData.length;
        Object object = new byte[n];
        System.arraycopy((Object)this.mData, (int)0, (Object)object, (int)0, (int)n);
        try {
            object = new EncodedStringValue(this.mCharacterSet, (byte[])object);
            return object;
        }
        catch (Exception var2_3) {
            Log.e((String)"EncodedStringValue", (String)("failed to clone an EncodedStringValue: " + this));
            var2_3.printStackTrace();
            throw new CloneNotSupportedException(var2_3.getMessage());
        }
    }

    public int getCharacterSet() {
        return this.mCharacterSet;
    }

    public String getString() {
        if (this.mCharacterSet == 0) {
            return new String(this.mData);
        }
        try {
            String string = CharacterSets.getMimeName(this.mCharacterSet);
            string = new String(this.mData, string);
            return string;
        }
        catch (UnsupportedEncodingException var1_2) {
            try {
                String string = new String(this.mData, "iso-8859-1");
                return string;
            }
            catch (UnsupportedEncodingException var1_4) {
                return new String(this.mData);
            }
        }
    }

    public byte[] getTextString() {
        byte[] arrby = new byte[this.mData.length];
        System.arraycopy((Object)this.mData, (int)0, (Object)arrby, (int)0, (int)this.mData.length);
        return arrby;
    }

    public void setCharacterSet(int n) {
        this.mCharacterSet = n;
    }

    public void setTextString(byte[] arrby) {
        if (arrby == null) {
            throw new NullPointerException("EncodedStringValue: Text-string is null.");
        }
        this.mData = new byte[arrby.length];
        System.arraycopy((Object)arrby, (int)0, (Object)this.mData, (int)0, (int)arrby.length);
    }

    public EncodedStringValue[] split(String arrencodedStringValue) {
        String[] arrstring = this.getString().split((String)arrencodedStringValue);
        EncodedStringValue[] arrencodedStringValue2 = new EncodedStringValue[arrstring.length];
        int n = 0;
        do {
            arrencodedStringValue = arrencodedStringValue2;
            if (n >= arrencodedStringValue2.length) break;
            try {
                arrencodedStringValue2[n] = new EncodedStringValue(this.mCharacterSet, arrstring[n].getBytes());
                ++n;
                continue;
            }
            catch (NullPointerException var1_2) {
                arrencodedStringValue = null;
                break;
            }
        } while (true);
        return arrencodedStringValue;
    }
}

