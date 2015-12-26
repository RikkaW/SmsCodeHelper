/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.text.TextUtils
 *  java.lang.Character
 *  java.lang.Object
 *  java.lang.String
 *  java.util.Random
 */
package com.xiaomi.smack.util;

import android.text.TextUtils;
import com.xiaomi.channel.commonutils.string.Base64Coder;
import java.util.Random;

public class StringUtils {
    private static final char[] AMP_ENCODE;
    private static final char[] APOS_ENCODE;
    private static final char[] GT_ENCODE;
    private static final char[] LT_ENCODE;
    private static final char[] QUOTE_ENCODE;
    private static char[] numbersAndLetters;
    private static Random randGen;

    static {
        QUOTE_ENCODE = "&quot;".toCharArray();
        APOS_ENCODE = "&apos;".toCharArray();
        AMP_ENCODE = "&amp;".toCharArray();
        LT_ENCODE = "&lt;".toCharArray();
        GT_ENCODE = "&gt;".toCharArray();
        randGen = new Random();
        numbersAndLetters = "0123456789abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    }

    private StringUtils() {
    }

    public static String encodeBase64(byte[] arrby) {
        return String.valueOf((char[])Base64Coder.encode(arrby));
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public static String escapeForXML(String var0) {
        if (var0 == null) {
            return null;
        }
        var2_1 = 0;
        var3_2 = 0;
        var6_3 = var0.toCharArray();
        var4_4 = var6_3.length;
        var7_5 = new StringBuilder((int)((double)var4_4 * 1.3));
        do {
            if (var2_1 >= var4_4) {
                if (var3_2 == 0) return var0;
                if (var2_1 <= var3_2) return var7_5.toString();
                var7_5.append(var6_3, var3_2, var2_1 - var3_2);
                return var7_5.toString();
            }
            var5_7 = var6_3[var2_1];
            if (var5_7 <= '>') ** GOTO lbl18
            var1_6 = var3_2;
            ** GOTO lbl51
lbl18: // 1 sources:
            if (var5_7 != '<') ** GOTO lbl24
            if (var2_1 > var3_2) {
                var7_5.append(var6_3, var3_2, var2_1 - var3_2);
            }
            var1_6 = var2_1 + 1;
            var7_5.append(StringUtils.LT_ENCODE);
            ** GOTO lbl51
lbl24: // 1 sources:
            if (var5_7 != '>') ** GOTO lbl30
            if (var2_1 > var3_2) {
                var7_5.append(var6_3, var3_2, var2_1 - var3_2);
            }
            var1_6 = var2_1 + 1;
            var7_5.append(StringUtils.GT_ENCODE);
            ** GOTO lbl51
lbl30: // 1 sources:
            if (var5_7 != '&') ** GOTO lbl39
            if (var2_1 > var3_2) {
                var7_5.append(var6_3, var3_2, var2_1 - var3_2);
            }
            if (var4_4 <= var2_1 + 5 || var6_3[var2_1 + 1] != '#' || !Character.isDigit((char)var6_3[var2_1 + 2]) || !Character.isDigit((char)var6_3[var2_1 + 3]) || !Character.isDigit((char)var6_3[var2_1 + 4])) ** GOTO lbl-1000
            var1_6 = var3_2;
            if (var6_3[var2_1 + 5] != ';') lbl-1000: // 2 sources:
            {
                var1_6 = var2_1 + 1;
                var7_5.append(StringUtils.AMP_ENCODE);
            }
            ** GOTO lbl51
lbl39: // 1 sources:
            if (var5_7 == '\"') {
                if (var2_1 > var3_2) {
                    var7_5.append(var6_3, var3_2, var2_1 - var3_2);
                }
                var1_6 = var2_1 + 1;
                var7_5.append(StringUtils.QUOTE_ENCODE);
            } else {
                var1_6 = var3_2;
                if (var5_7 == '\'') {
                    if (var2_1 > var3_2) {
                        var7_5.append(var6_3, var3_2, var2_1 - var3_2);
                    }
                    var1_6 = var2_1 + 1;
                    var7_5.append(StringUtils.APOS_ENCODE);
                }
            }
lbl51: // 8 sources:
            ++var2_1;
            var3_2 = var1_6;
        } while (true);
    }

    public static boolean isValidXmlChar(char c2) {
        if (c2 >= ' ' && c2 <= '\ud7ff' || c2 >= '\ue000' && c2 <= '\ufffd' || c2 >= '\u10000' && c2 <= '\u10ffff' || c2 == '\t' || c2 == '\n' || c2 == '\r') {
            return true;
        }
        return false;
    }

    public static String randomString(int n) {
        if (n < 1) {
            return null;
        }
        char[] arrc = new char[n];
        for (n = 0; n < arrc.length; ++n) {
            arrc[n] = numbersAndLetters[randGen.nextInt(71)];
        }
        return new String(arrc);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static final String replace(String string2, String string3, String string4) {
        int n;
        void var6_5;
        if (string2 == null) {
            return var6_5;
        }
        int n2 = string2.indexOf(string3, 0);
        String string5 = string2;
        if (n2 < 0) {
            return var6_5;
        }
        char[] arrc = string2.toCharArray();
        char[] arrc2 = string4.toCharArray();
        int n3 = string3.length();
        StringBuilder stringBuilder = new StringBuilder(arrc.length);
        stringBuilder.append(arrc, 0, n2).append(arrc2);
        n2 = n = n2 + n3;
        do {
            if ((n = string2.indexOf(string3, n)) <= 0) {
                stringBuilder.append(arrc, n2, arrc.length - n2);
                return stringBuilder.toString();
            }
            stringBuilder.append(arrc, n2, n - n2).append(arrc2);
            n2 = n += n3;
        } while (true);
    }

    public static String stripInvalidXMLChars(String string2) {
        if (TextUtils.isEmpty((CharSequence)string2)) {
            return string2;
        }
        StringBuilder stringBuilder = new StringBuilder(string2.length());
        for (int i = 0; i < string2.length(); ++i) {
            char c2 = string2.charAt(i);
            if (!StringUtils.isValidXmlChar(c2)) continue;
            stringBuilder.append(c2);
        }
        return stringBuilder.toString();
    }

    public static final String unescapeFromXML(String string2) {
        return StringUtils.replace(StringUtils.replace(StringUtils.replace(StringUtils.replace(StringUtils.replace(string2, "&lt;", "<"), "&gt;", ">"), "&quot;", "\""), "&apos;", "'"), "&amp;", "&");
    }
}

