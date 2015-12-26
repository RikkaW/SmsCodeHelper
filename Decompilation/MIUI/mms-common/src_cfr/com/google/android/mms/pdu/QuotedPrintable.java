/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.ByteArrayOutputStream
 *  java.lang.Character
 *  java.lang.Object
 */
package com.google.android.mms.pdu;

import java.io.ByteArrayOutputStream;

public class QuotedPrintable {
    private static byte ESCAPE_CHAR = 61;

    /*
     * Unable to fully structure code
     * Enabled aggressive exception aggregation
     */
    public static final byte[] decodeQuotedPrintable(byte[] var0) {
        if (var0 == null) {
            do {
                return null;
                break;
            } while (true);
        }
        var4_2 = new ByteArrayOutputStream();
        var1_3 = 0;
        do {
            if (var1_3 < var0.length) {
                var2_4 = var0[var1_3];
                if (var2_4 == QuotedPrintable.ESCAPE_CHAR) {
                    if ('\r' == var0[var1_3 + 1] && '\n' == var0[var1_3 + 2]) {
                        var1_3 += 2;
                    } else {
                        ++var1_3;
                        try {
                            var2_4 = Character.digit((char)var0[var1_3], (int)16);
                            ++var1_3;
                        }
                        catch (ArrayIndexOutOfBoundsException var0_1) {
                            return null;
                        }
                        var3_5 = Character.digit((char)var0[var1_3], (int)16);
                        if (var2_4 == -1 || var3_5 == -1) ** continue;
                        var4_2.write((int)((char)((var2_4 << 4) + var3_5)));
                    }
                } else {
                    var4_2.write(var2_4);
                }
            } else {
                return var4_2.toByteArray();
            }
            ++var1_3;
        } while (true);
    }
}

