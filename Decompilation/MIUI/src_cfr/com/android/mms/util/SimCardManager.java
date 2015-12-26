/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  android.content.ContentValues
 *  android.content.Context
 *  android.database.sqlite.SqliteWrapper
 *  android.net.Uri
 *  android.provider.Telephony
 *  android.provider.Telephony$MmsSms
 *  android.telephony.PhoneNumberUtils
 *  android.util.Log
 *  com.android.internal.telephony.EncodeException
 *  com.android.internal.telephony.GsmAlphabet
 *  com.android.internal.telephony.GsmAlphabet$TextEncodingDetails
 *  com.android.internal.telephony.SmsHeader
 *  com.android.internal.telephony.SmsHeader$ConcatRef
 *  com.android.internal.telephony.SmsMessageBase
 *  com.android.internal.telephony.SmsMessageBase$SubmitPduBase
 *  com.android.internal.telephony.cdma.SmsMessage
 *  com.android.internal.telephony.cdma.sms.BearerData
 *  com.android.internal.telephony.cdma.sms.BearerData$TimeStamp
 *  com.android.internal.telephony.cdma.sms.CdmaSmsAddress
 *  com.android.internal.telephony.cdma.sms.SmsEnvelope
 *  com.android.internal.telephony.cdma.sms.UserData
 *  com.android.internal.telephony.gsm.SmsMessage
 *  java.io.ByteArrayOutputStream
 *  java.io.DataOutputStream
 *  java.io.OutputStream
 *  java.lang.Boolean
 *  java.lang.Class
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 *  java.lang.reflect.Method
 *  java.util.ArrayList
 *  java.util.Arrays
 *  java.util.Calendar
 *  java.util.Random
 *  java.util.TimeZone
 *  miui.telephony.SmsManager
 */
package com.android.mms.util;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.provider.Telephony;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import com.android.internal.telephony.EncodeException;
import com.android.internal.telephony.GsmAlphabet;
import com.android.internal.telephony.SmsHeader;
import com.android.internal.telephony.SmsMessageBase;
import com.android.internal.telephony.cdma.SmsMessage;
import com.android.internal.telephony.cdma.sms.BearerData;
import com.android.internal.telephony.cdma.sms.CdmaSmsAddress;
import com.android.internal.telephony.cdma.sms.SmsEnvelope;
import com.android.internal.telephony.cdma.sms.UserData;
import com.android.mms.util.MSimUtils;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Random;
import java.util.TimeZone;
import miui.telephony.SmsManager;

public class SimCardManager {
    private static int sConcatenatedRef;
    private static SimCardManager sInstance;
    private Context mContext;

    static {
        sInstance = null;
        sConcatenatedRef = new Random().nextInt(256);
    }

    public SimCardManager(Context context) {
        this.mContext = context;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private GsmAlphabet.TextEncodingDetails calculateLength(CharSequence charSequence, boolean bl, int n) {
        if (n == 1) {
            charSequence = com.android.internal.telephony.gsm.SmsMessage.calculateLength((CharSequence)charSequence, (boolean)bl);
        } else {
            try {
                Class class_ = Class.forName((String)SmsMessage.class.getName());
                charSequence = MSimUtils.isAndroidM() ? (GsmAlphabet.TextEncodingDetails)class_.getMethod("calculateLength", new Class[]{CharSequence.class, Boolean.TYPE, Boolean.TYPE}).invoke((Object)null, new Object[]{charSequence, bl, true}) : (GsmAlphabet.TextEncodingDetails)class_.getMethod("calculateLength", new Class[]{CharSequence.class, Boolean.TYPE}).invoke((Object)null, new Object[]{charSequence, bl});
            }
            catch (Exception var1_2) {
                Log.e((String)"SimCardManager", (String)"calculateLength cdma type ", (Throwable)var1_2);
                charSequence = null;
            }
        }
        if (charSequence == null) {
            Log.e((String)"SimCardManager", (String)"calculateLength details is null");
        }
        return charSequence;
    }

    public static SimCardManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new SimCardManager(context);
        }
        return sInstance;
    }

    private static int getNextConcatenatedRef() {
        return ++sConcatenatedRef;
    }

    private SmsMessageBase.SubmitPduBase getSubmitPdu(String string2, String string3, String string4, boolean bl, SmsHeader smsHeader) {
        Object object = null;
        if (smsHeader != null) {
            object = SmsHeader.toByteArray((SmsHeader)smsHeader);
        }
        return com.android.internal.telephony.gsm.SmsMessage.getSubmitPdu((String)string2, (String)string3, (String)string4, (boolean)bl, (byte[])object);
    }

    private int saveToIcc(SmsManager smsManager, String object, String string2, long l, int n, SmsHeader smsHeader, int n2, int n3) {
        if (n != 1 && n3 == 1) {
            if ((object = this.getSubmitPdu(null, (String)object, string2, true, smsHeader)) == null) {
                Log.e((String)"SimCardManager", (String)"saveToIcc getSubmitPdu is null");
                return 1001;
            }
            if (smsManager.copyMessageToIcc(object.encodedScAddress, object.encodedMessage, n)) {
                return 1000;
            }
            return 1001;
        }
        if (n3 == 1) {
            if ((object = new GSMDeliveryPduGenerator().getDeliveryPdu(null, (String)object, string2, l, smsHeader)) == null) {
                Log.e((String)"SimCardManager", (String)"saveToIcc GSM getDeliveryPdu is null");
                return 1001;
            }
            if (smsManager.copyMessageToIcc(object.encodedScAddress, object.encodedMessage, n)) {
                return 1000;
            }
            return 1001;
        }
        if ((object = new CDMADeliveryPduGenerator().getDeliveryPdu((String)object, string2, false, l, smsHeader)) == null) {
            Log.e((String)"SimCardManager", (String)"saveToIcc CDMA getDeliveryPdu is null");
            return 1001;
        }
        if (smsManager.copyMessageToIcc(object.encodedScAddress, object.encodedMessage, n)) {
            return 1000;
        }
        return 1001;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public int saveMessageToIcc(String var1_1, String var2_2, long var3_3, int var5_4, int var6_5) {
        var14_6 = MSimUtils.getSmsManager(var6_5);
        if (var14_6 == null) {
            Log.e((String)"SimCardManager", (String)"saveMessageToIcc get SmsManager is failed");
            return 1001;
        }
        var11_8 = MSimUtils.getPhoneType(var6_5);
        var15_9 = var14_6.divideMessage((String)var2_2);
        if (var15_9 == null) {
            return this.saveToIcc(var14_6, var1_1, (String)var2_2, var3_3, var5_4, null, var6_5, var11_8);
        }
        var10_10 = 1000;
        var12_11 = var15_9.size();
        if (var12_11 == 1) {
            var7_7 = var5_4 = this.saveToIcc(var14_6, var1_1, (String)var15_9.get(0), var3_3, var5_4, null, var6_5, var11_8);
            if (var5_4 == 1000) return var7_7;
            Log.e((String)"SimCardManager", (String)"saveMessageToIcc saveToIcc is failed");
            return var5_4;
        }
        var13_12 = SimCardManager.getNextConcatenatedRef();
        var8_13 = 0;
        var2_2 = new GsmAlphabet.TextEncodingDetails[var12_11];
        for (var7_7 = 0; var7_7 < var12_11; ++var7_7) {
            var16_15 = this.calculateLength((CharSequence)var15_9.get(var7_7), false, var11_8);
            var9_14 = var8_13;
            if (var8_13 == var16_15.codeUnitSize) ** GOTO lbl27
            if (var8_13 == 0) ** GOTO lbl-1000
            var9_14 = var8_13;
            if (var8_13 == 1) lbl-1000: // 2 sources:
            {
                var9_14 = var16_15.codeUnitSize;
            }
lbl27: // 4 sources:
            var2_2[var7_7] = var16_15;
            var8_13 = var9_14;
        }
        var9_14 = 0;
        var7_7 = var10_10;
        while (var9_14 < var12_11) {
            var16_15 = (String)var15_9.get(var9_14);
            var17_16 = new SmsHeader.ConcatRef();
            var17_16.refNumber = var13_12 & 255;
            var17_16.seqNumber = var9_14 + 1;
            var17_16.msgCount = var12_11;
            var17_16.isEightBits = true;
            var18_17 = new SmsHeader();
            var18_17.concatRef = var17_16;
            if (var8_13 == 1) {
                var18_17.languageTable = var2_2[var9_14].languageTable;
                var18_17.languageShiftTable = var2_2[var9_14].languageShiftTable;
            }
            if ((var7_7 = this.saveToIcc(var14_6, var1_1, (String)var16_15, var3_3, var5_4, var18_17, var6_5, var11_8)) != 1000) {
                Log.e((String)"SimCardManager", (String)"saveMessageToIcc saveToIcc is failed");
                return var7_7;
            }
            ++var9_14;
        }
        return var7_7;
    }

    class CDMADeliveryPduGenerator {
        CDMADeliveryPduGenerator() {
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        private int getNextMessageId() {
            synchronized (this) {
                int n = 1;
                Uri uri = Uri.parse((String)((Object)Telephony.MmsSms.CONTENT_URI + "/getCdmaMsgId"));
                uri = SqliteWrapper.insert((Context)SimCardManager.this.mContext, (ContentResolver)SimCardManager.this.mContext.getContentResolver(), (Uri)uri, (ContentValues)new ContentValues(1));
                if (uri != null) {
                    n = Integer.parseInt((String)uri.getLastPathSegment());
                }
                Log.v((String)"SimCardManager", (String)("getNextMessageId is " + n));
                return n;
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        private DeliveryPdu privateGetDeliveryPdu(String object, boolean bl, UserData object2, long l) {
            if (object == null) {
                return null;
            }
            if ((object = PhoneNumberUtils.cdmaCheckAndProcessPlusCode((String)object)) == null) {
                Log.e((String)"CDMA DeliveryPduGenerator", (String)"privateGetDeliveryPdu addr is error");
                return null;
            }
            if ((object = CdmaSmsAddress.parse((String)object)) == null) {
                Log.e((String)"CDMA DeliveryPduGenerator", (String)"privateGetDeliveryPdu origin addr is error");
                return null;
            }
            BearerData bearerData = new BearerData();
            bearerData.messageType = 1;
            bearerData.messageId = this.getNextMessageId();
            bearerData.deliveryAckReq = bl;
            bearerData.userAckReq = false;
            bearerData.readAckReq = false;
            bearerData.reportReq = false;
            bearerData.userData = object2;
            Object object3 = BearerData.encode((BearerData)bearerData);
            if (object3 == null) {
                return null;
            }
            BearerData.TimeStamp timeStamp = new BearerData.TimeStamp();
            timeStamp.set(l);
            object2 = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream((OutputStream)object2);
            try {
                dataOutputStream.write((byte[])object3, 0, object3.length);
                dataOutputStream.writeByte(3);
                dataOutputStream.writeByte(6);
                object3 = this.timeStampToBCDBytes(timeStamp);
                dataOutputStream.write((byte[])object3, 0, object3.length);
            }
            catch (IOException var8_10) {
                var8_10.printStackTrace();
            }
            object2 = object2.toByteArray();
            int n = bearerData.hasUserDataHeader ? 4101 : 4098;
            dataOutputStream = new SmsEnvelope();
            dataOutputStream.messageType = 0;
            dataOutputStream.teleService = n;
            dataOutputStream.origAddress = object;
            dataOutputStream.bearerReply = 1;
            dataOutputStream.bearerData = object2;
            try {
                bearerData = new ByteArrayOutputStream(500);
                object3 = (Object)new DataOutputStream((OutputStream)bearerData);
                if (MSimUtils.isAndroid50()) {
                    object3.writeInt(dataOutputStream.teleService);
                    object3.writeInt(0);
                    object3.writeInt(0);
                    object3.write(object.digitMode);
                    object3.write(object.numberMode);
                    object3.write(object.ton);
                    object3.write(object.numberPlan);
                    object3.write(object.numberOfDigits);
                    object3.write(object.origBytes, 0, object.origBytes.length);
                    object3.write(0);
                    object3.write(0);
                    object3.write(0);
                    object3.write(object2.length);
                    object3.write((byte[])object2, 0, object2.length);
                } else {
                    object3.writeInt(dataOutputStream.teleService);
                    object3.write(0);
                    object3.writeInt(0);
                    object3.writeInt(object.digitMode);
                    object3.writeInt(object.numberMode);
                    object3.writeInt(object.ton);
                    object3.writeInt(object.numberPlan);
                    object3.write(object.numberOfDigits);
                    object3.write(object.origBytes, 0, object.origBytes.length);
                    object3.writeInt(0);
                    object3.write(0);
                    object3.write(0);
                    object3.writeInt(object2.length);
                    object3.write((byte[])object2, 0, object2.length);
                }
                object3.close();
                object = new DeliveryPdu();
                object.encodedMessage = bearerData.toByteArray();
                object.encodedScAddress = null;
                return object;
            }
            catch (IOException var1_2) {
                Log.e((String)"CDMA DeliveryPduGenerator", (String)("creating Delivery failed: " + (Object)((Object)var1_2)));
                return null;
            }
        }

        public byte createCDMABCD(int n) {
            int n2 = (n & 255) / 10;
            return (byte)(n2 << 4 | (n & 255) - n2 * 10);
        }

        public DeliveryPdu getDeliveryPdu(String string2, String string3, boolean bl, long l, SmsHeader smsHeader) {
            if (string3 == null || string2 == null) {
                return null;
            }
            UserData userData = new UserData();
            userData.payloadStr = string3;
            userData.userDataHeader = smsHeader;
            return this.privateGetDeliveryPdu(string2, bl, userData, l);
        }

        /*
         * Enabled aggressive block sorting
         */
        public byte[] timeStampToBCDBytes(BearerData.TimeStamp timeStamp) {
            int n = timeStamp.year;
            if (n > 2095 || n < 1996) {
                return null;
            }
            n = n >= 2000 ? (n -= 2000) : (n -= 1900);
            byte by = this.createCDMABCD(n);
            n = timeStamp.month + 1;
            if (n < 1 || n > 12) {
                return null;
            }
            byte by2 = this.createCDMABCD(n);
            n = timeStamp.monthDay;
            if (n < 1 || n > 31) {
                return null;
            }
            byte by3 = this.createCDMABCD(n);
            n = timeStamp.hour;
            if (n < 0 || n > 23) {
                return null;
            }
            byte by4 = this.createCDMABCD(n);
            n = timeStamp.minute;
            if (n < 0 || n > 59) {
                return null;
            }
            byte by5 = this.createCDMABCD(n);
            n = timeStamp.second;
            if (n >= 0 && n <= 59) {
                return new byte[]{by, by2, by3, by4, by5, this.createCDMABCD(n)};
            }
            return null;
        }
    }

    private static class DeliveryPdu {
        public byte[] encodedMessage;
        public byte[] encodedScAddress;

        private DeliveryPdu() {
        }

        public String toString() {
            return "DeliveryPdu: encodedScAddress = " + Arrays.toString((byte[])this.encodedScAddress) + ", encodedMessage = " + Arrays.toString((byte[])this.encodedMessage);
        }
    }

    class GSMDeliveryPduGenerator {
        GSMDeliveryPduGenerator() {
        }

        private byte[] encodeUCS2(String string2, byte[] arrby) throws UnsupportedEncodingException {
            string2 = (String)string2.getBytes("utf-16be");
            if (arrby != null) {
                byte[] arrby2 = new byte[arrby.length + string2.length + 1];
                arrby2[0] = (byte)arrby.length;
                System.arraycopy((Object)arrby, (int)0, (Object)arrby2, (int)1, (int)arrby.length);
                System.arraycopy((Object)string2, (int)0, (Object)arrby2, (int)(arrby.length + 1), (int)string2.length);
                string2 = (String)arrby2;
            }
            arrby = new byte[string2.length + 1];
            arrby[0] = (byte)(string2.length & 255);
            System.arraycopy((Object)string2, (int)0, (Object)arrby, (int)1, (int)string2.length);
            return arrby;
        }

        /*
         * Enabled aggressive block sorting
         */
        private ByteArrayOutputStream getDeliveryPduHead(String string2, String string3, byte by, DeliveryPdu deliveryPdu) {
            int n;
            void var2_3;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(180);
            var4_5.encodedScAddress = string2 == null ? (Object)null : PhoneNumberUtils.networkPortionToCalledPartyBCDWithLength((String)string2);
            byteArrayOutputStream.write(n);
            byte[] arrby = PhoneNumberUtils.networkPortionToCalledPartyBCD((String)var2_3);
            if (arrby == null) {
                Log.e((String)"GSM DeliveryPduGenerator", (String)"originator address is error");
                return null;
            }
            int n2 = arrby.length;
            n = (arrby[arrby.length - 1] & 240) == 240 ? 1 : 0;
            byteArrayOutputStream.write((n2 - 1) * 2 - n);
            byteArrayOutputStream.write(arrby, 0, arrby.length);
            byteArrayOutputStream.write(0);
            return byteArrayOutputStream;
        }

        private void writeTimeStampStringForDate(long l, ByteArrayOutputStream byteArrayOutputStream) {
            int n;
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(l);
            int n2 = calendar.get(1);
            int n3 = calendar.get(2);
            int n4 = calendar.get(5);
            int n5 = calendar.get(11);
            int n6 = calendar.get(12);
            int n7 = calendar.get(13);
            int n8 = n = calendar.getTimeZone().getOffset(l) / 60000 / 15;
            if (n < 0) {
                n8 = 128 - n;
            }
            byteArrayOutputStream.write(this.createSwappedBCD(n2 - 2000));
            byteArrayOutputStream.write(this.createSwappedBCD(n3 + 1));
            byteArrayOutputStream.write(this.createSwappedBCD(n4));
            byteArrayOutputStream.write(this.createSwappedBCD(n5));
            byteArrayOutputStream.write(this.createSwappedBCD(n6));
            byteArrayOutputStream.write(this.createSwappedBCD(n7));
            byteArrayOutputStream.write(this.createSwappedBCD(n8));
        }

        public int createSwappedBCD(int n) {
            int n2 = (n & 255) / 10;
            return (n & 255) - n2 * 10 << 4 | n2;
        }

        /*
         * Unable to fully structure code
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         * Lifted jumps to return sites
         */
        public DeliveryPdu getDeliveryPdu(String var1_1, String var2_4, String var3_5, long var4_6, SmsHeader var6_7) {
            block8 : {
                if (var3_5 == null) return null;
                if (var2_4 == null) {
                    return null;
                }
                var8_8 = new DeliveryPdu();
                var7_9 = var6_7 != null ? 64 : 0;
                if ((var2_4 = this.getDeliveryPduHead(var1_1 /* !! */ , var2_4, (byte)(var7_9 | 0), var8_8)) == null) {
                    Log.e((String)"GSM DeliveryPduGenerator", (String)"getDeliveryPduHead is null");
                    return null;
                }
                var7_9 = 1;
                if (var6_7 == null) ** GOTO lbl14
                try {
                    var1_1 /* !! */  = (String)SmsHeader.toByteArray((SmsHeader)var6_7);
                    ** GOTO lbl15
lbl14: // 1 sources:
                    var1_1 /* !! */  = null;
lbl15: // 2 sources:
                    var1_1 /* !! */  = (String)GsmAlphabet.stringToGsm7BitPackedWithHeader((String)var3_5, (byte[])var1_1 /* !! */ );
                    ** GOTO lbl25
                }
                catch (EncodeException var1_2) {
                    if (var6_7 == null) ** GOTO lbl22
                    try {
                        var1_1 /* !! */  = (String)SmsHeader.toByteArray((SmsHeader)var6_7);
                        ** GOTO lbl23
lbl22: // 1 sources:
                        var1_1 /* !! */  = null;
lbl23: // 2 sources:
                        var1_1 /* !! */  = (String)this.encodeUCS2(var3_5, (byte[])var1_1 /* !! */ );
                        var7_9 = 3;
                    }
                    catch (UnsupportedEncodingException var1_3) {
                        Log.e((String)"GSM DeliveryPduGenerator", (String)"Implausible UnsupportedEncodingException ", (Throwable)var1_3);
                        return null;
                    }
lbl25: // 2 sources:
                    if (var7_9 != 1) break block8;
                    if ((var1_1 /* !! */ [0] & 255) > 160) {
                        return null;
                    }
                }
                var2_4.write(18);
                ** GOTO lbl38
            }
            if ((var1_1 /* !! */ [0] & 255) > 140) {
                return null;
            }
            var2_4.write(8);
lbl38: // 2 sources:
            this.writeTimeStampStringForDate(var4_6, (ByteArrayOutputStream)var2_4);
            var2_4.write((byte[])var1_1 /* !! */ , 0, var1_1 /* !! */ .length);
            var8_8.encodedMessage = var2_4.toByteArray();
            return var8_8;
        }
    }

}

