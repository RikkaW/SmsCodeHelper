/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.ByteArrayOutputStream
 *  java.io.FileInputStream
 *  java.io.OutputStream
 *  java.lang.Byte
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.security.MessageDigest
 *  java.util.ArrayList
 *  java.util.regex.Matcher
 *  java.util.regex.Pattern
 *  java.util.zip.GZIPInputStream
 *  javax.xml.parsers.DocumentBuilder
 *  javax.xml.parsers.DocumentBuilderFactory
 *  org.json.JSONArray
 *  org.json.JSONException
 */
package cn.com.xy.sms.sdk.util;

import cn.com.xy.sms.sdk.util.d;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.w3c.dom.Document;

public class StringUtils {
    public static final String MPLUG86 = "+86";
    private static final String a = "0123456789ABCDEF";
    private static final String b = "UTF-8";
    private static final String c = "+86";
    private static final String d = "\\+86";
    private static final String e = "0086";
    private static final String f = "86";
    private static final String g = "17951";
    private static final String h = "12593";
    public static final String phoneFiled10193 = "10193";
    public static final String phoneFiled12520 = "12520";
    public static final String phoneFiled17908 = "17908";
    public static final String phoneFiled17909 = "17909";
    public static final String phoneFiled17911 = "17911";
    public static final String phoneFiled179110 = "179110";

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static byte[] MD5(byte[] arrby) {
        MessageDigest messageDigest;
        byte[] arrby2 = null;
        try {
            messageDigest = MessageDigest.getInstance((String)"MD5");
            if (messageDigest == null) return arrby2;
        }
        catch (NoSuchAlgorithmException var1_3) {
            var1_3.printStackTrace();
            return arrby2;
        }
        messageDigest.update(arrby);
        return messageDigest.digest();
    }

    public static List<String> arryToList(JSONArray jSONArray) {
        block6 : {
            if (jSONArray != null) {
                int n2;
                ArrayList arrayList;
                try {
                    if (jSONArray.length() <= 0) break block6;
                    arrayList = new ArrayList();
                    n2 = 0;
                }
                catch (JSONException var0_1) {
                    var0_1.printStackTrace();
                }
                do {
                    block7 : {
                        if (n2 < jSONArray.length()) break block7;
                        return arrayList;
                    }
                    arrayList.add(jSONArray.get(n2).toString());
                    ++n2;
                    continue;
                    break;
                } while (true);
            }
        }
        return null;
    }

    public static String bytesToHexString(byte[] arrby) {
        int n2 = 0;
        if (arrby == null) {
            return null;
        }
        char[] arrc = new char[arrby.length * 2];
        int n3 = 0;
        while (n3 < arrby.length) {
            arrc[n2] = "0123456789abcdef".charAt(arrby[n3] >> 4 & 15);
            byte by2 = arrby[n3];
            arrc[++n2] = "0123456789abcdef".charAt(by2 & 15);
            ++n3;
            ++n2;
        }
        return String.valueOf((char[])arrc);
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static byte[] compressGZip(byte[] object) {
        ByteArrayOutputStream byteArrayOutputStream;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream((OutputStream)byteArrayOutputStream);
            gZIPOutputStream.write((byte[])object);
            gZIPOutputStream.finish();
            gZIPOutputStream.close();
            object = byteArrayOutputStream.toByteArray();
        }
        catch (Throwable var1_2) {
            void var1_3;
            object = null;
            var1_3.printStackTrace();
            return object;
        }
        byteArrayOutputStream.close();
        return object;
        {
            catch (Throwable throwable) {}
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive exception aggregation
     */
    public static String decode(String var0) {
        try {
            var3_5 = new ByteArrayOutputStream(var0.length() / 2);
            var1_6 = 0;
        }
        catch (Throwable var4_10) {
            block21 : {
                var0 = null;
lbl24: // 2 sources:
                var2_7 = var0;
                var4_11.printStackTrace();
                if (var0 == null) break block21;
                try {
                    var0.close();
                }
                catch (IOException var0_1) {
                    var0_1.printStackTrace();
                    ** continue;
                }
            }
lbl34: // 2 sources:
            do {
                return null;
                break;
            } while (true);
        }
        catch (Throwable var0_2) {
            var2_7 = null;
lbl38: // 2 sources:
            do {
                if (var2_7 != null) {
                    var2_7.close();
                }
lbl42: // 4 sources:
                do {
                    throw var0_3;
                    break;
                } while (true);
                catch (IOException var2_9) {
                    var2_9.printStackTrace();
                    ** continue;
                }
                break;
            } while (true);
        }
        do {
            var2_7 = var3_5;
            if (var1_6 < var0.length()) ** break block20
            var2_7 = var3_5;
            var0 = new String(var3_5.toByteArray(), "UTF-8");
            var3_5.close();
            return var0;
            break;
        } while (true);
        catch (IOException var2_8) {
            var2_8.printStackTrace();
            return var0;
        }
        {
            
            var2_7 = var3_5;
            var3_5.write("0123456789ABCDEF".indexOf((int)var0.charAt(var1_6)) << 4 | "0123456789ABCDEF".indexOf((int)var0.charAt(var1_6 + 1)));
            var1_6 += 2;
            continue;
        }
        {
            catch (Throwable var0_4) {
                ** continue;
            }
        }
        catch (Throwable var4_12) {
            var0 = var3_5;
            ** GOTO lbl24
        }
    }

    public static String encode(String string2) {
        StringBuilder stringBuilder;
        int n2;
        try {
            string2 = (String)string2.getBytes("UTF-8");
            stringBuilder = new StringBuilder(string2.length << 1);
            n2 = 0;
        }
        catch (UnsupportedEncodingException var0_1) {
            var0_1.printStackTrace();
            return null;
        }
        do {
            if (n2 >= string2.length) {
                return stringBuilder.toString();
            }
            stringBuilder.append("0123456789ABCDEF".charAt((string2[n2] & 240) >> 4));
            stringBuilder.append("0123456789ABCDEF".charAt(string2[n2] & 15));
            ++n2;
            continue;
            break;
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static String getFileMD5(String string2) {
        byte[] arrby = new byte[1024];
        try {
            string2 = new FileInputStream(string2);
            MessageDigest messageDigest = MessageDigest.getInstance((String)"MD5");
            do {
                int n2;
                if ((n2 = string2.read(arrby)) <= 0) {
                    string2.close();
                    return StringUtils.getMD5(messageDigest.digest());
                }
                messageDigest.update(arrby, 0, n2);
            } while (true);
        }
        catch (Throwable var0_1) {
            return null;
        }
    }

    public static long getLongByString(String string2) {
        long l2 = -1;
        try {
            if (!StringUtils.isNull(string2)) {
                l2 = Long.parseLong((String)string2);
            }
            return l2;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return -1;
        }
    }

    public static String getMD5(String string2) {
        return StringUtils.getMD5(string2.getBytes());
    }

    public static String getMD5(byte[] arrby) {
        return StringUtils.bytesToHexString(StringUtils.MD5(arrby));
    }

    public static String getNoNullString(String string2) {
        if (string2 == null) {
            return "";
        }
        return string2.trim();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String getPhoneNumberNo86(String string2) {
        String string3 = string2;
        if (StringUtils.isNull(string2)) return string3;
        if ((string2 = string2.replace((CharSequence)" ", (CharSequence)"").replace((CharSequence)"-", (CharSequence)"").replace((CharSequence)"(", (CharSequence)"").replace((CharSequence)")", (CharSequence)"")).startsWith("+86")) {
            return string2.replaceFirst("\\+86", "");
        }
        if (string2.startsWith("0086")) {
            return string2.replaceFirst("0086", "");
        }
        if (string2.startsWith("86")) {
            return string2.replaceFirst("86", "");
        }
        if (string2.startsWith("17951") && string2.length() > 10) {
            return string2.replaceFirst("17951", "");
        }
        if (string2.startsWith("12593") && string2.length() > 10) {
            return string2.replaceFirst("12593", "");
        }
        string3 = string2;
        if (!string2.startsWith("12520")) return string3;
        string3 = string2;
        if (string2.length() <= 10) return string3;
        return string2.replaceFirst("12520", "");
    }

    public static String getSubString(String string2) {
        String string3 = string2;
        if (!StringUtils.isNull(string2)) {
            string3 = string2;
            if (string2.length() >= 7) {
                string3 = string2.substring(0, 7);
            }
        }
        return string3;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String getTwoDigitType(String string2) {
        if (StringUtils.isNull(string2)) {
            return "";
        }
        if (string2.length() < 2) {
            return "0" + string2;
        }
        String string3 = string2;
        if (string2.length() <= 2) return string3;
        return "99";
    }

    public static String getValueByKey(Map<String, String> map, String string2) {
        if (map != null && !map.isEmpty() && !StringUtils.isNull(string2)) {
            return map.get(string2);
        }
        return "";
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static String handlerAssemble2(String string2) {
        int n2 = 0;
        try {
            byte[] arrby = string2.getBytes("UTF-8");
            int n3 = arrby.length;
            int n4 = 0;
            do {
                if (n2 >= n3) {
                    return new String(arrby);
                }
                arrby[n4] = Byte.valueOf((String)String.valueOf((int)(arrby[n2] - (n4 + 1) % 3))).byteValue();
                ++n4;
                ++n2;
            } while (true);
        }
        catch (Throwable var0_2) {
            var0_2.printStackTrace();
            return "";
        }
    }

    public static boolean isNull(String string2) {
        if (string2 == null || string2.trim().length() == 0 || string2.trim().equalsIgnoreCase("null")) {
            return true;
        }
        return false;
    }

    public static boolean isNull2(String string2) {
        if (string2 == null || string2.trim().length() == 0) {
            return true;
        }
        return false;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean isNumber(String string2) {
        if (StringUtils.isNull(string2)) return false;
        string2 = StringUtils.getPhoneNumberNo86(string2);
        if (Pattern.compile((String)"[0-9]*").matcher((CharSequence)string2).matches()) return true;
        return false;
    }

    public static boolean isPhoneNumber(String string2) {
        if (StringUtils.isNull(string2)) {
            return false;
        }
        return StringUtils.sj(StringUtils.getPhoneNumberNo86(string2));
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static String[] jsonArryToArray(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() <= 0) {
            return null;
        }
        String[] arrstring = new String[jSONArray.length()];
        int n2 = 0;
        while (n2 < jSONArray.length()) {
            try {
                arrstring[n2] = jSONArray.getString(n2);
            }
            catch (JSONException var3_3) {
                var3_3.printStackTrace();
            }
            ++n2;
        }
        return arrstring;
    }

    public static String replaceBlank(String string2) {
        if (StringUtils.isNull(string2)) {
            return null;
        }
        return string2.replaceAll("\\s", "");
    }

    /*
     * Enabled aggressive block sorting
     */
    public static boolean sj(String string2) {
        if (string2 == null || string2.length() != 11 || "13800138000".equals((Object)string2) || !string2.startsWith("13") && !string2.startsWith("14") && !string2.startsWith("15") && !string2.startsWith("18")) {
            return false;
        }
        return true;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive exception aggregation
     */
    public static Document stringConvertXML(String var0, String var1_2) {
        if (StringUtils.isNull((String)var0)) {
            return null;
        }
        var2_4 = var0;
        if (var0.indexOf("?>") != -1) {
            var2_4 = var0.substring(var0.indexOf("?>") + 2);
        }
        var0 = new StringBuilder((String)var1_2);
        var0.append((String)var2_4);
        var2_4 = DocumentBuilderFactory.newInstance();
        var0 = var1_2 = new ByteArrayInputStream(var0.toString().getBytes("utf-8"));
        try {
            var2_4 = var2_4.newDocumentBuilder().parse((InputStream)var1_2);
        }
        catch (Throwable var2_6) {
            ** GOTO lbl17
        }
        d.a((Closeable)var1_2);
        return var2_4;
        catch (Throwable var2_5) {
            var1_2 = null;
lbl17: // 2 sources:
            var0 = var1_2;
            var2_4.printStackTrace();
            d.a((Closeable)var1_2);
            return null;
        }
        catch (Throwable var0_1) {
            var2_4 = null;
            var1_2 = var0_1;
lbl25: // 2 sources:
            do {
                d.a((Closeable)var2_4);
                throw var1_2;
                break;
            } while (true);
        }
        {
            catch (Throwable var1_3) {
                var2_4 = var0;
                ** continue;
            }
        }
    }

    public static byte[] uncompressGZip(byte[] object) {
        object = new ByteArrayInputStream((byte[])object);
        GZIPInputStream gZIPInputStream = new GZIPInputStream((InputStream)object);
        byte[] arrby = new byte[1024];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        do {
            int n2;
            if ((n2 = gZIPInputStream.read(arrby, 0, 1024)) == -1) {
                arrby = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.flush();
                byteArrayOutputStream.close();
                gZIPInputStream.close();
                object.close();
                return arrby;
            }
            byteArrayOutputStream.write(arrby, 0, n2);
        } while (true);
    }
}

