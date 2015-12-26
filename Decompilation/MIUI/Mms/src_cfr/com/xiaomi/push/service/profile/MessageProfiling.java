/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.util.Pair
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.util.Vector
 *  java.util.concurrent.ConcurrentHashMap
 */
package com.xiaomi.push.service.profile;

import android.util.Pair;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

public class MessageProfiling {
    private static ConcurrentHashMap<String, Long> sSendingMessages;
    private static Vector<Pair<String, Long>> sSentPerfDatas;

    static {
        sSentPerfDatas = new Vector();
        sSendingMessages = new ConcurrentHashMap();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static String getPrefString() {
        StringBuilder stringBuilder = new StringBuilder();
        Vector<Pair<String, Long>> vector = sSentPerfDatas;
        synchronized (vector) {
            int n = 0;
            do {
                if (n >= sSentPerfDatas.size()) {
                    sSentPerfDatas.clear();
                    return stringBuilder.toString();
                }
                Pair pair = (Pair)sSentPerfDatas.elementAt(n);
                stringBuilder.append((String)pair.first).append(":").append(pair.second);
                if (n < sSentPerfDatas.size() - 1) {
                    stringBuilder.append(";");
                }
                ++n;
            } while (true);
        }
    }
}

