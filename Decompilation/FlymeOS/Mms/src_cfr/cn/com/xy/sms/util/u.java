/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 *  java.lang.reflect.Method
 *  java.util.UUID
 */
package cn.com.xy.sms.util;

import java.lang.reflect.Method;
import java.util.UUID;

public final class u {
    private static String a = null;
    private static char[] b = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private static short c = 20;

    private static String a() {
        String string2;
        if (a != null) {
            return a;
        }
        a = string2 = u.a("ro.aliyun.clouduuid", null);
        if (string2 == null || a.trim().length() == 0) {
            a = u.a("ro.sys.aliyun.clouduuid", null);
        }
        return a;
    }

    private static String a(String string2, String string3) {
        try {
            string2 = (String)Class.forName((String)"android.os.SystemProperties").getMethod("get", new Class[]{String.class, String.class}).invoke((Object)null, new Object[]{string2, null});
            return string2;
        }
        catch (Throwable var0_1) {
            return null;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private static String b() {
        char[] arrc = new char[c];
        long l2 = System.currentTimeMillis() - 936748800000L >> 1;
        int n2 = 7;
        do {
            if (n2 <= 0) {
                long l3;
                arrc[0] = b[(int)(l2 % 26) + 10];
                UUID uUID = UUID.randomUUID();
                l2 = uUID.getLeastSignificantBits();
                l2 = l3 = uUID.getMostSignificantBits() ^ l2;
                if (l3 < 0) {
                    l2 = - l3;
                    break;
                }
                break;
            }
            arrc[n2] = b[(int)(l2 % 36)];
            l2 /= 36;
            --n2;
        } while (true);
        n2 = 8;
        while (n2 < c) {
            arrc[n2] = b[(int)(l2 % 36)];
            l2 /= 36;
            ++n2;
        }
        return new String(arrc);
    }
}

