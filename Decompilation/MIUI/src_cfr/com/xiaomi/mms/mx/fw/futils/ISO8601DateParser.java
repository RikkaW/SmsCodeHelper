/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.text.SimpleDateFormat
 *  java.util.Date
 */
package com.xiaomi.mms.mx.fw.futils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ISO8601DateParser {
    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static Date parse(String string2) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz");
        if (string2.endsWith("Z")) {
            string2 = string2.substring(0, string2.length() - 1) + "GMT-00:00";
            do {
                return simpleDateFormat.parse(string2);
                break;
            } while (true);
        }
        String string3 = string2.substring(0, string2.length() - 6);
        string2 = string2.substring(string2.length() - 6, string2.length());
        string2 = string3 + "GMT" + string2;
        return simpleDateFormat.parse(string2);
    }
}

