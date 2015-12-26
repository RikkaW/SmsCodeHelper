/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.res.Resources
 *  android.util.Log
 *  java.io.ByteArrayOutputStream
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 *  java.util.Arrays
 *  java.util.HashMap
 */
package com.google.android.mms.pdu;

import android.content.res.Resources;
import android.util.Log;
import com.google.android.mms.InvalidHeaderValueException;
import com.google.android.mms.pdu.AcknowledgeInd;
import com.google.android.mms.pdu.Base64;
import com.google.android.mms.pdu.CharacterSets;
import com.google.android.mms.pdu.DeliveryInd;
import com.google.android.mms.pdu.EncodedStringValue;
import com.google.android.mms.pdu.GenericPdu;
import com.google.android.mms.pdu.NotificationInd;
import com.google.android.mms.pdu.NotifyRespInd;
import com.google.android.mms.pdu.PduBody;
import com.google.android.mms.pdu.PduContentTypes;
import com.google.android.mms.pdu.PduHeaders;
import com.google.android.mms.pdu.PduPart;
import com.google.android.mms.pdu.QuotedPrintable;
import com.google.android.mms.pdu.ReadOrigInd;
import com.google.android.mms.pdu.ReadRecInd;
import com.google.android.mms.pdu.RetrieveConf;
import com.google.android.mms.pdu.SendConf;
import com.google.android.mms.pdu.SendReq;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;

public class MiuiPduParser {
    static final /* synthetic */ boolean $assertionsDisabled;
    private static final boolean DEBUG = false;
    private static final int END_STRING_FLAG = 0;
    private static final int LENGTH_QUOTE = 31;
    private static final boolean LOCAL_LOGV = false;
    private static final String LOG_TAG = "MiuiPduParser";
    private static final int LONG_INTEGER_LENGTH_MAX = 8;
    private static final int QUOTE = 127;
    private static final int QUOTED_STRING_FLAG = 34;
    private static final int SHORT_INTEGER_MAX = 127;
    private static final int SHORT_LENGTH_MAX = 30;
    private static final int TEXT_MAX = 127;
    private static final int TEXT_MIN = 32;
    private static final int THE_FIRST_PART = 0;
    private static final int THE_LAST_PART = 1;
    private static final int TYPE_QUOTED_STRING = 1;
    private static final int TYPE_TEXT_STRING = 0;
    private static final int TYPE_TOKEN_STRING = 2;
    private static byte[] mStartParam;
    private static byte[] mTypeParam;
    private PduBody mBody = null;
    private PduHeaders mHeaders = null;
    private final boolean mParseContentDisposition;
    private ByteArrayInputStream mPduDataStream = null;

    /*
     * Enabled aggressive block sorting
     */
    static {
        boolean bl = !MiuiPduParser.class.desiredAssertionStatus();
        $assertionsDisabled = bl;
        mTypeParam = null;
        mStartParam = null;
    }

    public MiuiPduParser(byte[] arrby) {
        this.mPduDataStream = new ByteArrayInputStream(arrby);
        this.mParseContentDisposition = Resources.getSystem().getBoolean(17891393);
    }

    public MiuiPduParser(byte[] arrby, boolean bl) {
        this.mPduDataStream = new ByteArrayInputStream(arrby);
        this.mParseContentDisposition = bl;
    }

    protected static boolean checkMandatoryHeader(PduHeaders pduHeaders) {
        if (pduHeaders == null) {
            return false;
        }
        int n = pduHeaders.getOctet(140);
        if (pduHeaders.getOctet(141) == 0) {
            return false;
        }
        switch (n) {
            default: {
                return false;
            }
            case 128: {
                if (pduHeaders.getTextString(132) == null) {
                    return false;
                }
                if (pduHeaders.getEncodedStringValue(137) == null) {
                    return false;
                }
                if (pduHeaders.getTextString(152) != null) break;
                return false;
            }
            case 129: {
                if (pduHeaders.getOctet(146) == 0) {
                    return false;
                }
                if (pduHeaders.getTextString(152) != null) break;
                return false;
            }
            case 130: {
                if (pduHeaders.getTextString(131) == null) {
                    return false;
                }
                if (-1 == pduHeaders.getLongInteger(136)) {
                    return false;
                }
                if (pduHeaders.getTextString(138) == null) {
                    return false;
                }
                if (-1 == pduHeaders.getLongInteger(142)) {
                    return false;
                }
                if (pduHeaders.getTextString(152) != null) break;
                return false;
            }
            case 131: {
                if (pduHeaders.getOctet(149) == 0) {
                    return false;
                }
                if (pduHeaders.getTextString(152) != null) break;
                return false;
            }
            case 132: {
                if (pduHeaders.getTextString(132) == null) {
                    return false;
                }
                if (-1 != pduHeaders.getLongInteger(133)) break;
                return false;
            }
            case 134: {
                if (-1 == pduHeaders.getLongInteger(133)) {
                    return false;
                }
                if (pduHeaders.getTextString(139) == null) {
                    return false;
                }
                if (pduHeaders.getOctet(149) == 0) {
                    return false;
                }
                if (pduHeaders.getEncodedStringValues(151) != null) break;
                return false;
            }
            case 133: {
                if (pduHeaders.getTextString(152) != null) break;
                return false;
            }
            case 136: {
                if (-1 == pduHeaders.getLongInteger(133)) {
                    return false;
                }
                if (pduHeaders.getEncodedStringValue(137) == null) {
                    return false;
                }
                if (pduHeaders.getTextString(139) == null) {
                    return false;
                }
                if (pduHeaders.getOctet(155) == 0) {
                    return false;
                }
                if (pduHeaders.getEncodedStringValues(151) != null) break;
                return false;
            }
            case 135: {
                if (pduHeaders.getEncodedStringValue(137) == null) {
                    return false;
                }
                if (pduHeaders.getTextString(139) == null) {
                    return false;
                }
                if (pduHeaders.getOctet(155) == 0) {
                    return false;
                }
                if (pduHeaders.getEncodedStringValues(151) != null) break;
                return false;
            }
        }
        return true;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private static int checkPartPosition(PduPart pduPart) {
        byte[] arrby;
        if (!$assertionsDisabled && pduPart == null) {
            throw new AssertionError();
        }
        if (mTypeParam == null && mStartParam == null) {
            return 1;
        }
        if (mStartParam != null && (arrby = pduPart.getContentId()) != null && Arrays.equals((byte[])mStartParam, (byte[])arrby)) {
            return 0;
        }
        if (mTypeParam == null) return 1;
        byte[] arrby2 = pduPart.getContentType();
        if (arrby2 == null) return 1;
        if (true != Arrays.equals((byte[])mTypeParam, (byte[])arrby2)) return 1;
        return 0;
    }

    protected static int extractByteValue(ByteArrayInputStream byteArrayInputStream) {
        if (!$assertionsDisabled && byteArrayInputStream == null) {
            throw new AssertionError();
        }
        int n = byteArrayInputStream.read();
        if (!$assertionsDisabled && -1 == n) {
            throw new AssertionError();
        }
        return n & 255;
    }

    /*
     * Enabled aggressive block sorting
     */
    protected static byte[] getWapString(ByteArrayInputStream byteArrayInputStream, int n) {
        int n2;
        if (!$assertionsDisabled && byteArrayInputStream == null) {
            throw new AssertionError();
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int n3 = n2 = byteArrayInputStream.read();
        if (!$assertionsDisabled) {
            n3 = n2;
            if (-1 == n2) {
                throw new AssertionError();
            }
        }
        while (-1 != n3 && n3 != 0) {
            if (n == 2) {
                if (MiuiPduParser.isTokenCharacter(n3)) {
                    byteArrayOutputStream.write(n3);
                }
            } else if (MiuiPduParser.isText(n3)) {
                byteArrayOutputStream.write(n3);
            }
            n3 = n2 = byteArrayInputStream.read();
            if ($assertionsDisabled) continue;
            n3 = n2;
            if (-1 == n2) {
                throw new AssertionError();
            }
        }
        if (byteArrayOutputStream.size() > 0) {
            return byteArrayOutputStream.toByteArray();
        }
        return null;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    protected static boolean isText(int n) {
        if (n >= 32 && n <= 126 || n >= 128 && n <= 255) {
            do {
                return true;
                break;
            } while (true);
        }
        switch (n) {
            case 9: 
            case 10: 
            case 13: {
                return true;
            }
        }
        return false;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    protected static boolean isTokenCharacter(int n) {
        if (n < 33 || n > 126) {
            do {
                return false;
                break;
            } while (true);
        }
        switch (n) {
            case 34: 
            case 40: 
            case 41: 
            case 44: 
            case 47: 
            case 58: 
            case 59: 
            case 60: 
            case 61: 
            case 62: 
            case 63: 
            case 64: 
            case 91: 
            case 92: 
            case 93: 
            case 123: 
            case 125: {
                return false;
            }
        }
        return true;
    }

    private static void log(String string) {
    }

    /*
     * Enabled aggressive block sorting
     */
    protected static byte[] parseContentType(ByteArrayInputStream byteArrayInputStream, HashMap<Integer, Object> hashMap) {
        if (!$assertionsDisabled && byteArrayInputStream == null) {
            throw new AssertionError();
        }
        byteArrayInputStream.mark(1);
        int n = byteArrayInputStream.read();
        if (!$assertionsDisabled && -1 == n) {
            throw new AssertionError();
        }
        byteArrayInputStream.reset();
        if ((n &= 255) < 32) {
            byte[] arrby;
            n = MiuiPduParser.parseValueLength(byteArrayInputStream);
            int n2 = byteArrayInputStream.available();
            byteArrayInputStream.mark(1);
            int n3 = byteArrayInputStream.read();
            if (!$assertionsDisabled && -1 == n3) {
                throw new AssertionError();
            }
            byteArrayInputStream.reset();
            if ((n3 &= 255) >= 32 && n3 <= 127) {
                arrby = MiuiPduParser.parseWapString(byteArrayInputStream, 0);
            } else {
                if (n3 <= 127) {
                    Log.e((String)"MiuiPduParser", (String)"Corrupt content-type");
                    return PduContentTypes.contentTypes[0].getBytes();
                }
                n3 = MiuiPduParser.parseShortInteger(byteArrayInputStream);
                if (n3 < PduContentTypes.contentTypes.length) {
                    arrby = PduContentTypes.contentTypes[n3].getBytes();
                } else {
                    byteArrayInputStream.reset();
                    arrby = MiuiPduParser.parseWapString(byteArrayInputStream, 0);
                }
            }
            if ((n -= n2 - byteArrayInputStream.available()) > 0) {
                MiuiPduParser.parseContentTypeParams(byteArrayInputStream, hashMap, n);
            }
            if (n >= 0) return arrby;
            Log.e((String)"MiuiPduParser", (String)"Corrupt MMS message");
            return PduContentTypes.contentTypes[0].getBytes();
        }
        if (n > 127) return PduContentTypes.contentTypes[MiuiPduParser.parseShortInteger(byteArrayInputStream)].getBytes();
        return MiuiPduParser.parseWapString(byteArrayInputStream, 0);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected static void parseContentTypeParams(ByteArrayInputStream byteArrayInputStream, HashMap<Integer, Object> hashMap, Integer n) {
        if (!$assertionsDisabled && byteArrayInputStream == null) {
            throw new AssertionError();
        }
        if (!$assertionsDisabled && n <= 0) {
            throw new AssertionError();
        }
        int n2 = byteArrayInputStream.available();
        int n3 = n;
        block8 : while (n3 > 0) {
            int n4 = byteArrayInputStream.read();
            if (!$assertionsDisabled && -1 == n4) {
                throw new AssertionError();
            }
            --n3;
            switch (n4) {
                byte[] arrby;
                default: {
                    if (-1 != MiuiPduParser.skipWapValue(byteArrayInputStream, n3)) break;
                    Log.e((String)"MiuiPduParser", (String)"Corrupt Content-Type");
                    continue block8;
                }
                case 131: 
                case 137: {
                    byteArrayInputStream.mark(1);
                    n3 = MiuiPduParser.extractByteValue(byteArrayInputStream);
                    byteArrayInputStream.reset();
                    if (n3 > 127) {
                        n3 = MiuiPduParser.parseShortInteger(byteArrayInputStream);
                        if (n3 < PduContentTypes.contentTypes.length) {
                            hashMap.put((Object)131, (Object)PduContentTypes.contentTypes[n3].getBytes());
                        }
                    } else {
                        arrby = MiuiPduParser.parseWapString(byteArrayInputStream, 0);
                        if (arrby != null && hashMap != null) {
                            hashMap.put((Object)131, (Object)arrby);
                        }
                    }
                    n3 = byteArrayInputStream.available();
                    n3 = n - (n2 - n3);
                    continue block8;
                }
                case 138: 
                case 153: {
                    arrby = MiuiPduParser.parseWapString(byteArrayInputStream, 0);
                    if (arrby != null && hashMap != null) {
                        hashMap.put((Object)153, (Object)arrby);
                    }
                    n3 = byteArrayInputStream.available();
                    n3 = n - (n2 - n3);
                    continue block8;
                }
                case 129: {
                    byteArrayInputStream.mark(1);
                    n3 = MiuiPduParser.extractByteValue(byteArrayInputStream);
                    byteArrayInputStream.reset();
                    if (n3 > 32 && n3 < 127 || n3 == 0) {
                        arrby = MiuiPduParser.parseWapString(byteArrayInputStream, 0);
                        try {
                            hashMap.put((Object)129, (Object)CharacterSets.getMibEnumValue(new String(arrby)));
                        }
                        catch (UnsupportedEncodingException var7_7) {
                            Log.e((String)"MiuiPduParser", (String)Arrays.toString((byte[])arrby), (Throwable)var7_7);
                            hashMap.put((Object)129, (Object)0);
                        }
                    } else {
                        n3 = (int)MiuiPduParser.parseIntegerValue(byteArrayInputStream);
                        if (hashMap != null) {
                            hashMap.put((Object)129, (Object)n3);
                        }
                    }
                    n3 = byteArrayInputStream.available();
                    n3 = n - (n2 - n3);
                    continue block8;
                }
                case 133: 
                case 151: {
                    arrby = MiuiPduParser.parseWapString(byteArrayInputStream, 0);
                    if (arrby != null && hashMap != null) {
                        hashMap.put((Object)151, (Object)arrby);
                    }
                    n3 = byteArrayInputStream.available();
                    n3 = n - (n2 - n3);
                    continue block8;
                }
            }
            n3 = 0;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    protected static EncodedStringValue parseEncodedStringValue(ByteArrayInputStream object) {
        if (!$assertionsDisabled && object == null) {
            throw new AssertionError();
        }
        object.mark(1);
        int n = 0;
        int n2 = object.read();
        if (!$assertionsDisabled && -1 == n2) {
            throw new AssertionError();
        }
        if ((n2 &= 255) == 0) {
            return new EncodedStringValue("");
        }
        object.reset();
        if (n2 < 32) {
            MiuiPduParser.parseValueLength((ByteArrayInputStream)object);
            n = MiuiPduParser.parseShortInteger((ByteArrayInputStream)object);
        }
        object = MiuiPduParser.parseWapString((ByteArrayInputStream)object, 0);
        if (n == 0) return new EncodedStringValue((byte[])object);
        try {
            return new EncodedStringValue(n, (byte[])object);
        }
        catch (Exception var0_1) {
            return null;
        }
    }

    protected static long parseIntegerValue(ByteArrayInputStream byteArrayInputStream) {
        if (!$assertionsDisabled && byteArrayInputStream == null) {
            throw new AssertionError();
        }
        byteArrayInputStream.mark(1);
        int n = byteArrayInputStream.read();
        if (!$assertionsDisabled && -1 == n) {
            throw new AssertionError();
        }
        byteArrayInputStream.reset();
        if (n > 127) {
            return MiuiPduParser.parseShortInteger(byteArrayInputStream);
        }
        return MiuiPduParser.parseLongInteger(byteArrayInputStream);
    }

    protected static long parseLongInteger(ByteArrayInputStream byteArrayInputStream) {
        if (!$assertionsDisabled && byteArrayInputStream == null) {
            throw new AssertionError();
        }
        int n = byteArrayInputStream.read();
        if (!$assertionsDisabled && -1 == n) {
            throw new AssertionError();
        }
        int n2 = n & 255;
        if (n2 > 8) {
            throw new RuntimeException("Octet count greater than 8 and I can't represent that!");
        }
        long l = 0;
        for (n = 0; n < n2; ++n) {
            int n3 = byteArrayInputStream.read();
            if (!$assertionsDisabled && -1 == n3) {
                throw new AssertionError();
            }
            l = (l << 8) + (long)(n3 & 255);
        }
        return l;
    }

    protected static int parseShortInteger(ByteArrayInputStream byteArrayInputStream) {
        if (!$assertionsDisabled && byteArrayInputStream == null) {
            throw new AssertionError();
        }
        int n = byteArrayInputStream.read();
        if (!$assertionsDisabled && -1 == n) {
            throw new AssertionError();
        }
        return n & 127;
    }

    protected static int parseUnsignedInt(ByteArrayInputStream byteArrayInputStream) {
        int n;
        if (!$assertionsDisabled && byteArrayInputStream == null) {
            throw new AssertionError();
        }
        int n2 = 0;
        int n3 = n = byteArrayInputStream.read();
        if (n == -1) {
            return n;
        }
        while ((n3 & 128) != 0) {
            n2 = n2 << 7 | n3 & 127;
            n3 = n = byteArrayInputStream.read();
            if (n != -1) continue;
            return n;
        }
        return n2 << 7 | n3 & 127;
    }

    protected static int parseValueLength(ByteArrayInputStream byteArrayInputStream) {
        if (!$assertionsDisabled && byteArrayInputStream == null) {
            throw new AssertionError();
        }
        int n = byteArrayInputStream.read();
        if (!$assertionsDisabled && -1 == n) {
            throw new AssertionError();
        }
        if ((n &= 255) <= 30) {
            return n;
        }
        if (n == 31) {
            return MiuiPduParser.parseUnsignedInt(byteArrayInputStream);
        }
        throw new RuntimeException("Value length > LENGTH_QUOTE!");
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    protected static byte[] parseWapString(ByteArrayInputStream byteArrayInputStream, int n) {
        if (!$assertionsDisabled && byteArrayInputStream == null) {
            throw new AssertionError();
        }
        byteArrayInputStream.mark(1);
        int n2 = byteArrayInputStream.read();
        if (!$assertionsDisabled && -1 == n2) {
            throw new AssertionError();
        }
        if (1 == n && 34 == n2) {
            byteArrayInputStream.mark(1);
            do {
                return MiuiPduParser.getWapString(byteArrayInputStream, n);
                break;
            } while (true);
        }
        if (n == 0 && 127 == n2) {
            byteArrayInputStream.mark(1);
            return MiuiPduParser.getWapString(byteArrayInputStream, n);
        }
        byteArrayInputStream.reset();
        return MiuiPduParser.getWapString(byteArrayInputStream, n);
    }

    protected static int skipWapValue(ByteArrayInputStream byteArrayInputStream, int n) {
        int n2;
        if (!$assertionsDisabled && byteArrayInputStream == null) {
            throw new AssertionError();
        }
        int n3 = n2 = byteArrayInputStream.read(new byte[n], 0, n);
        if (n2 < n) {
            n3 = -1;
        }
        return n3;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public GenericPdu parse() {
        if (this.mPduDataStream == null) {
            return null;
        }
        this.mHeaders = this.parseHeaders(this.mPduDataStream);
        if (this.mHeaders == null) {
            return null;
        }
        int n = this.mHeaders.getOctet(140);
        if (!MiuiPduParser.checkMandatoryHeader(this.mHeaders)) {
            MiuiPduParser.log("check mandatory headers failed!");
            return null;
        }
        if (128 == n || 132 == n) {
            this.mBody = this.parseParts(this.mPduDataStream);
            if (this.mBody == null) {
                return null;
            }
        }
        switch (n) {
            default: {
                MiuiPduParser.log("Parser doesn't support this message type in this version!");
                return null;
            }
            case 128: {
                return new SendReq(this.mHeaders, this.mBody);
            }
            case 129: {
                return new SendConf(this.mHeaders);
            }
            case 130: {
                return new NotificationInd(this.mHeaders);
            }
            case 131: {
                return new NotifyRespInd(this.mHeaders);
            }
            case 132: {
                RetrieveConf retrieveConf = new RetrieveConf(this.mHeaders, this.mBody);
                Object object = retrieveConf.getContentType();
                if (object == null) {
                    return null;
                }
                String string = new String((byte[])object);
                object = retrieveConf;
                if (string.equals((Object)"application/vnd.wap.multipart.mixed")) return object;
                object = retrieveConf;
                if (string.equals((Object)"application/vnd.wap.multipart.related")) return object;
                object = retrieveConf;
                if (string.equals((Object)"application/vnd.wap.multipart.alternative")) return object;
                if (!string.equals((Object)"application/vnd.wap.multipart.alternative")) return null;
                object = this.mBody.getPart(0);
                this.mBody.removeAll();
                this.mBody.addPart(0, (PduPart)object);
                return retrieveConf;
            }
            case 134: {
                return new DeliveryInd(this.mHeaders);
            }
            case 133: {
                return new AcknowledgeInd(this.mHeaders);
            }
            case 136: {
                return new ReadOrigInd(this.mHeaders);
            }
            case 135: 
        }
        return new ReadRecInd(this.mHeaders);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    protected PduHeaders parseHeaders(ByteArrayInputStream var1_1) {
        if (var1_1 == null) {
            return null;
        }
        var2_35 = true;
        var10_36 = new PduHeaders();
        block80 : do {
            var9_27 = var10_36;
            if (var2_35 == false) return var9_27;
            var9_27 = var10_36;
            if (var1_1.available() <= 0) return var9_27;
            var1_1.mark(1);
            var3_37 = MiuiPduParser.extractByteValue(var1_1);
            if (var3_37 >= 32 && var3_37 <= 127) {
                var1_1.reset();
                MiuiPduParser.parseWapString(var1_1, 0);
                continue;
            }
            switch (var3_37) {
                default: {
                    MiuiPduParser.log("Unknown header");
                    continue block80;
                }
                case 140: {
                    var4_38 = MiuiPduParser.extractByteValue(var1_1);
                    switch (var4_38) {
                        default: {
                            try {
                                var10_36.setOctet(var4_38, var3_37);
                                continue block80;
                            }
                            catch (InvalidHeaderValueException var1_2) {
                                MiuiPduParser.log("Set invalid Octet value: " + var4_38 + " into the header filed: " + var3_37);
                                return null;
                            }
                        }
                        case 137: 
                        case 138: 
                        case 139: 
                        case 140: 
                        case 141: 
                        case 142: 
                        case 143: 
                        case 144: 
                        case 145: 
                        case 146: 
                        case 147: 
                        case 148: 
                        case 149: 
                        case 150: 
                        case 151: 
                    }
                    return null;
                    catch (RuntimeException var1_3) {
                        MiuiPduParser.log("" + var3_37 + "is not Octet header field!");
                        return null;
                    }
                }
                case 134: 
                case 143: 
                case 144: 
                case 145: 
                case 146: 
                case 148: 
                case 149: 
                case 153: 
                case 155: 
                case 156: 
                case 162: 
                case 163: 
                case 165: 
                case 167: 
                case 169: 
                case 171: 
                case 177: 
                case 180: 
                case 186: 
                case 187: 
                case 188: 
                case 191: {
                    var4_38 = MiuiPduParser.extractByteValue(var1_1);
                    try {
                        var10_36.setOctet(var4_38, var3_37);
                        continue block80;
                    }
                    catch (InvalidHeaderValueException var1_4) {
                        MiuiPduParser.log("Set invalid Octet value: " + var4_38 + " into the header filed: " + var3_37);
                        return null;
                    }
                    catch (RuntimeException var1_5) {
                        MiuiPduParser.log("" + var3_37 + "is not Octet header field!");
                        return null;
                    }
                }
                case 133: 
                case 142: 
                case 159: {
                    try {
                        var10_36.setLongInteger(MiuiPduParser.parseLongInteger(var1_1), var3_37);
                        continue block80;
                    }
                    catch (RuntimeException var1_6) {
                        MiuiPduParser.log("" + var3_37 + "is not Long-Integer header field!");
                        return null;
                    }
                }
                case 173: 
                case 175: 
                case 179: {
                    try {
                        var10_36.setLongInteger(MiuiPduParser.parseIntegerValue(var1_1), var3_37);
                        continue block80;
                    }
                    catch (RuntimeException var1_7) {
                        MiuiPduParser.log("" + var3_37 + "is not Long-Integer header field!");
                        return null;
                    }
                }
                case 131: 
                case 139: 
                case 152: 
                case 158: 
                case 183: 
                case 184: 
                case 185: 
                case 189: 
                case 190: {
                    var9_27 = MiuiPduParser.parseWapString(var1_1, 0);
                    if (var9_27 == null) continue block80;
                    try {
                        var10_36.setTextString((byte[])var9_27, var3_37);
                        continue block80;
                    }
                    catch (NullPointerException var9_28) {
                        MiuiPduParser.log("null pointer error!");
                        continue block80;
                    }
                    catch (RuntimeException var1_8) {
                        MiuiPduParser.log("" + var3_37 + "is not Text-String header field!");
                        return null;
                    }
                }
                case 147: 
                case 150: 
                case 154: 
                case 166: 
                case 181: 
                case 182: {
                    var9_27 = MiuiPduParser.parseEncodedStringValue(var1_1);
                    if (var9_27 == null) continue block80;
                    try {
                        var10_36.setEncodedStringValue((EncodedStringValue)var9_27, var3_37);
                        continue block80;
                    }
                    catch (NullPointerException var9_29) {
                        MiuiPduParser.log("null pointer error!");
                        continue block80;
                    }
                    catch (RuntimeException var1_9) {
                        MiuiPduParser.log("" + var3_37 + "is not Encoded-String-Value header field!");
                        return null;
                    }
                }
                case 129: 
                case 130: 
                case 151: {
                    var12_43 = MiuiPduParser.parseEncodedStringValue(var1_1);
                    if (var12_43 == null) continue block80;
                    var9_27 = var12_43.getTextString();
                    if (var9_27 != null) {
                        var11_41 = new String((byte[])var9_27);
                        var4_38 = var11_41.indexOf("/");
                        var9_27 = var11_41;
                        if (var4_38 > 0) {
                            var9_27 = var11_41.substring(0, var4_38);
                        }
                        var12_43.setTextString(var9_27.getBytes());
                    }
                    try {
                        var10_36.appendEncodedStringValue((EncodedStringValue)var12_43, var3_37);
                        continue block80;
                    }
                    catch (NullPointerException var9_30) {
                        MiuiPduParser.log("null pointer error!");
                        continue block80;
                    }
                    catch (NullPointerException var1_10) {
                        MiuiPduParser.log("null pointer error!");
                        return null;
                    }
                    catch (RuntimeException var1_11) {
                        MiuiPduParser.log("" + var3_37 + "is not Encoded-String-Value header field!");
                        return null;
                    }
                }
                case 135: 
                case 136: 
                case 157: {
                    MiuiPduParser.parseValueLength(var1_1);
                    var4_38 = MiuiPduParser.extractByteValue(var1_1);
                    try {
                        var7_40 = var5_39 = MiuiPduParser.parseLongInteger(var1_1);
                        if (129 != var4_38) ** GOTO lbl118
                    }
                    catch (RuntimeException var1_13) {
                        MiuiPduParser.log("" + var3_37 + "is not Long-Integer header field!");
                        return null;
                    }
                    var7_40 = var5_39 + System.currentTimeMillis() / 1000;
lbl118: // 2 sources:
                    try {
                        var10_36.setLongInteger(var7_40, var3_37);
                        continue block80;
                    }
                    catch (RuntimeException var1_12) {
                        MiuiPduParser.log("" + var3_37 + "is not Long-Integer header field!");
                        return null;
                    }
                }
                case 137: {
                    MiuiPduParser.parseValueLength(var1_1);
                    if (128 == MiuiPduParser.extractByteValue(var1_1)) {
                        var9_27 = var11_41 = (Object)MiuiPduParser.parseEncodedStringValue(var1_1);
                        if (var11_41 != null) {
                            var12_43 = var11_41.getTextString();
                            var9_27 = var11_41;
                            if (var12_43 != null) {
                                var12_43 = new String((byte[])var12_43);
                                var4_38 = var12_43.indexOf("/");
                                var9_27 = var12_43;
                                if (var4_38 > 0) {
                                    var9_27 = var12_43.substring(0, var4_38);
                                }
                                try {
                                    var11_41.setTextString(var9_27.getBytes());
                                    var9_27 = var11_41;
                                }
                                catch (NullPointerException var1_14) {
                                    MiuiPduParser.log("null pointer error!");
                                    return null;
                                }
                            }
                        }
                    } else {
                        var9_27 = new EncodedStringValue("insert-address-token".getBytes());
                    }
                    try {
                        var10_36.setEncodedStringValue((EncodedStringValue)var9_27, 137);
                        continue block80;
                    }
                    catch (NullPointerException var9_31) {
                        MiuiPduParser.log("null pointer error!");
                        continue block80;
                    }
                    catch (NullPointerException var1_15) {
                        MiuiPduParser.log("" + var3_37 + "is not Encoded-String-Value header field!");
                        return null;
                    }
                    catch (RuntimeException var1_16) {
                        MiuiPduParser.log("" + var3_37 + "is not Encoded-String-Value header field!");
                        return null;
                    }
                }
                case 138: {
                    var1_1.mark(1);
                    var4_38 = MiuiPduParser.extractByteValue(var1_1);
                    if (var4_38 >= 128) {
                        if (128 == var4_38) {
                            var10_36.setTextString("personal".getBytes(), 138);
                            continue block80;
                        }
                        if (129 == var4_38) {
                            var10_36.setTextString("advertisement".getBytes(), 138);
                            continue block80;
                        }
                        if (130 == var4_38) {
                            var10_36.setTextString("informational".getBytes(), 138);
                            continue block80;
                        }
                        if (131 != var4_38) continue block80;
                        try {
                            var10_36.setTextString("auto".getBytes(), 138);
                            continue block80;
                        }
                        catch (NullPointerException var9_32) {
                            MiuiPduParser.log("null pointer error!");
                            continue block80;
                        }
                        catch (RuntimeException var1_17) {
                            MiuiPduParser.log("" + var3_37 + "is not Text-String header field!");
                            return null;
                        }
                    }
                    var1_1.reset();
                    var9_27 = MiuiPduParser.parseWapString(var1_1, 0);
                    if (var9_27 == null) continue block80;
                    try {
                        var10_36.setTextString((byte[])var9_27, 138);
                        continue block80;
                    }
                    catch (NullPointerException var9_33) {
                        MiuiPduParser.log("null pointer error!");
                        continue block80;
                    }
                    catch (RuntimeException var1_18) {
                        MiuiPduParser.log("" + var3_37 + "is not Text-String header field!");
                        return null;
                    }
                }
                case 141: {
                    var4_38 = MiuiPduParser.parseShortInteger(var1_1);
                    try {
                        var10_36.setOctet(var4_38, 141);
                        continue block80;
                    }
                    catch (InvalidHeaderValueException var1_19) {
                        MiuiPduParser.log("Set invalid Octet value: " + var4_38 + " into the header filed: " + var3_37);
                        return null;
                    }
                    catch (RuntimeException var1_20) {
                        MiuiPduParser.log("" + var3_37 + "is not Octet header field!");
                        return null;
                    }
                }
                case 160: {
                    MiuiPduParser.parseValueLength(var1_1);
                    try {
                        MiuiPduParser.parseIntegerValue(var1_1);
                    }
                    catch (RuntimeException var1_21) {
                        MiuiPduParser.log("" + var3_37 + " is not Integer-Value");
                        return null;
                    }
                    var9_27 = MiuiPduParser.parseEncodedStringValue(var1_1);
                    if (var9_27 == null) continue block80;
                    try {
                        var10_36.setEncodedStringValue((EncodedStringValue)var9_27, 160);
                        continue block80;
                    }
                    catch (NullPointerException var9_34) {
                        MiuiPduParser.log("null pointer error!");
                        continue block80;
                    }
                    catch (RuntimeException var1_22) {
                        MiuiPduParser.log("" + var3_37 + "is not Encoded-String-Value header field!");
                        return null;
                    }
                }
                case 161: {
                    MiuiPduParser.parseValueLength(var1_1);
                    try {
                        MiuiPduParser.parseIntegerValue(var1_1);
                    }
                    catch (RuntimeException var1_24) {
                        MiuiPduParser.log("" + var3_37 + " is not Integer-Value");
                        return null;
                    }
                    try {
                        var10_36.setLongInteger(MiuiPduParser.parseLongInteger(var1_1), 161);
                        continue block80;
                    }
                    catch (RuntimeException var1_23) {
                        MiuiPduParser.log("" + var3_37 + "is not Long-Integer header field!");
                        return null;
                    }
                }
                case 164: {
                    MiuiPduParser.parseValueLength(var1_1);
                    MiuiPduParser.extractByteValue(var1_1);
                    MiuiPduParser.parseEncodedStringValue(var1_1);
                    continue block80;
                }
                case 170: 
                case 172: {
                    MiuiPduParser.parseValueLength(var1_1);
                    MiuiPduParser.extractByteValue(var1_1);
                    try {
                        MiuiPduParser.parseIntegerValue(var1_1);
                        continue block80;
                    }
                    catch (RuntimeException var1_25) {
                        MiuiPduParser.log("" + var3_37 + " is not Integer-Value");
                        return null;
                    }
                }
                case 178: {
                    MiuiPduParser.parseContentType(var1_1, null);
                    continue block80;
                }
                case 132: 
            }
            var9_27 = new HashMap();
            var11_41 = MiuiPduParser.parseContentType(var1_1, var9_27);
            if (var11_41 != null) {
                try {
                    var10_36.setTextString((byte[])var11_41, 132);
                }
                catch (NullPointerException var11_42) {
                    MiuiPduParser.log("null pointer error!");
                }
            }
            MiuiPduParser.mStartParam = (byte[])var9_27.get((Object)153);
            MiuiPduParser.mTypeParam = (byte[])var9_27.get((Object)131);
            var2_35 = false;
        } while (true);
        catch (RuntimeException var1_26) {
            MiuiPduParser.log("" + var3_37 + "is not Text-String header field!");
            return null;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    protected boolean parsePartHeaders(ByteArrayInputStream byteArrayInputStream, PduPart pduPart, int n) {
        if (!$assertionsDisabled && byteArrayInputStream == null) {
            throw new AssertionError();
        }
        if (!$assertionsDisabled && pduPart == null) {
            throw new AssertionError();
        }
        if (!$assertionsDisabled && n <= 0) {
            throw new AssertionError();
        }
        int n2 = byteArrayInputStream.available();
        int n3 = n;
        block5 : while (n3 > 0) {
            byte[] arrby;
            int n4 = byteArrayInputStream.read();
            if (!$assertionsDisabled && -1 == n4) {
                throw new AssertionError();
            }
            --n3;
            if (n4 > 127) {
                switch (n4) {
                    default: {
                        if (-1 != MiuiPduParser.skipWapValue(byteArrayInputStream, n3)) break;
                        Log.e((String)"MiuiPduParser", (String)"Corrupt Part headers");
                        return false;
                    }
                    case 142: {
                        arrby = MiuiPduParser.parseWapString(byteArrayInputStream, 0);
                        if (arrby != null) {
                            pduPart.setContentLocation(arrby);
                        }
                        n3 = n - (n2 - byteArrayInputStream.available());
                        continue block5;
                    }
                    case 192: {
                        arrby = MiuiPduParser.parseWapString(byteArrayInputStream, 1);
                        if (arrby != null) {
                            pduPart.setContentId(arrby);
                        }
                        n3 = n - (n2 - byteArrayInputStream.available());
                        continue block5;
                    }
                    case 174: 
                    case 197: {
                        if (!this.mParseContentDisposition) continue block5;
                        n3 = MiuiPduParser.parseValueLength(byteArrayInputStream);
                        byteArrayInputStream.mark(1);
                        n4 = byteArrayInputStream.available();
                        int n5 = byteArrayInputStream.read();
                        if (n5 == 128) {
                            pduPart.setContentDisposition(PduPart.DISPOSITION_FROM_DATA);
                        } else if (n5 == 129) {
                            pduPart.setContentDisposition(PduPart.DISPOSITION_ATTACHMENT);
                        } else if (n5 == 130) {
                            pduPart.setContentDisposition(PduPart.DISPOSITION_INLINE);
                        } else {
                            byteArrayInputStream.reset();
                            pduPart.setContentDisposition(MiuiPduParser.parseWapString(byteArrayInputStream, 0));
                        }
                        if (n4 - byteArrayInputStream.available() < n3) {
                            if (byteArrayInputStream.read() == 152) {
                                pduPart.setFilename(MiuiPduParser.parseWapString(byteArrayInputStream, 0));
                            }
                            if (n4 - (n5 = byteArrayInputStream.available()) < n3) {
                                byteArrayInputStream.read(new byte[n3], 0, n3 -= n4 - n5);
                            }
                        }
                        n3 = n - (n2 - byteArrayInputStream.available());
                        continue block5;
                    }
                }
                n3 = 0;
                continue;
            }
            if (n4 >= 32 && n4 <= 127) {
                arrby = MiuiPduParser.parseWapString(byteArrayInputStream, 0);
                byte[] arrby2 = MiuiPduParser.parseWapString(byteArrayInputStream, 0);
                if ("Content-Transfer-Encoding".equalsIgnoreCase(new String(arrby))) {
                    pduPart.setContentTransferEncoding(arrby2);
                }
                n3 = n - (n2 - byteArrayInputStream.available());
                continue;
            }
            if (-1 == MiuiPduParser.skipWapValue(byteArrayInputStream, n3)) {
                Log.e((String)"MiuiPduParser", (String)"Corrupt Part headers");
                return false;
            }
            n3 = 0;
        }
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    protected PduBody parseParts(ByteArrayInputStream byteArrayInputStream) {
        if (byteArrayInputStream == null) {
            return null;
        }
        int n = MiuiPduParser.parseUnsignedInt(byteArrayInputStream);
        PduBody pduBody = new PduBody();
        int n2 = 0;
        do {
            Object object = pduBody;
            if (n2 >= n) return object;
            int n3 = MiuiPduParser.parseUnsignedInt(byteArrayInputStream);
            int n4 = MiuiPduParser.parseUnsignedInt(byteArrayInputStream);
            PduPart pduPart = new PduPart();
            int n5 = byteArrayInputStream.available();
            if (n5 <= 0) {
                return null;
            }
            object = new HashMap();
            byte[] arrby = MiuiPduParser.parseContentType(byteArrayInputStream, object);
            if (arrby != null) {
                pduPart.setContentType(arrby);
            } else {
                pduPart.setContentType(PduContentTypes.contentTypes[0].getBytes());
            }
            if ((arrby = (byte[])object.get((Object)151)) != null) {
                pduPart.setName(arrby);
            }
            if ((object = (Integer)object.get((Object)129)) != null) {
                pduPart.setCharset(object.intValue());
            }
            if ((n3 -= n5 - byteArrayInputStream.available()) > 0 ? !this.parsePartHeaders(byteArrayInputStream, pduPart, n3) : n3 < 0) {
                return null;
            }
            if (pduPart.getContentLocation() == null && pduPart.getName() == null && pduPart.getFilename() == null && pduPart.getContentId() == null) {
                pduPart.setContentLocation(Long.toOctalString((long)System.currentTimeMillis()).getBytes());
            }
            object = pduPart;
            if (n4 > 0) {
                arrby = new byte[n4];
                object = new String(pduPart.getContentType());
                byteArrayInputStream.read(arrby, 0, n4);
                if (object.equalsIgnoreCase("application/vnd.wap.multipart.alternative")) {
                    object = this.parseParts(new ByteArrayInputStream(arrby)).getPart(0);
                } else {
                    Object object2 = pduPart.getContentTransferEncoding();
                    object = arrby;
                    if (object2 != null) {
                        if ((object2 = (Object)new String((byte[])object2)).equalsIgnoreCase("base64")) {
                            object = Base64.decodeBase64(arrby);
                        } else {
                            object = arrby;
                            if (object2.equalsIgnoreCase("quoted-printable")) {
                                object = QuotedPrintable.decodeQuotedPrintable(arrby);
                            }
                        }
                    }
                    if (object == null) {
                        MiuiPduParser.log("Decode part data error!");
                        return null;
                    }
                    pduPart.setData((byte[])object);
                    object = pduPart;
                }
            }
            if (MiuiPduParser.checkPartPosition((PduPart)object) == 0) {
                pduBody.addPart(0, (PduPart)object);
            } else {
                pduBody.addPart((PduPart)object);
            }
            ++n2;
        } while (true);
    }
}

