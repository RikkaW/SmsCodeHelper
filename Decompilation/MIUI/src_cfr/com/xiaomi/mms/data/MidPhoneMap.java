/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.util.HashMap
 */
package com.xiaomi.mms.data;

import com.android.mms.data.Contact;
import java.util.HashMap;
import java.util.Map;

public class MidPhoneMap {
    private static final Map<String, String> sMap = new HashMap();

    public static String get(String string2) {
        synchronized (MidPhoneMap.class) {
            string2 = sMap.get(string2);
            return string2;
        }
    }

    public static void put(String string2, String string3) {
        synchronized (MidPhoneMap.class) {
            sMap.put(string2, Contact.normalizePhoneNumber(string3));
            return;
        }
    }
}

