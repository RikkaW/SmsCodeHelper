/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  java.lang.Object
 *  java.lang.String
 *  java.util.HashMap
 *  java.util.LinkedList
 */
package com.xiaomi.push.service;

import android.content.SharedPreferences;
import com.xiaomi.channel.commonutils.string.XMStringUtils;
import com.xiaomi.push.service.XMPushService;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class MiPushMessageDuplicate {
    private static Object lock = new Object();
    private static Map<String, Queue<String>> mCachedMsgIds = new HashMap();

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static boolean isDuplicateMessage(XMPushService arrstring, String string2, String string3) {
        Object object = lock;
        synchronized (object) {
            String[] arrstring2;
            SharedPreferences sharedPreferences = arrstring.getSharedPreferences("push_message_ids", 0);
            arrstring = arrstring2 = mCachedMsgIds.get(string2);
            if (arrstring2 == null) {
                arrstring2 = sharedPreferences.getString(string2, "").split(",");
                arrstring = new LinkedList();
                int n = arrstring2.length;
                for (int i = 0; i < n; ++i) {
                    arrstring.add(arrstring2[i]);
                }
                mCachedMsgIds.put(string2, (Queue<String>)arrstring);
            }
            if (arrstring.contains((Object)string3)) {
                return true;
            }
            arrstring.add(string3);
            if (arrstring.size() > 10) {
                arrstring.poll();
            }
            arrstring = XMStringUtils.join(arrstring, ",");
            string3 = sharedPreferences.edit();
            string3.putString(string2, (String)arrstring);
            string3.commit();
            return false;
        }
    }
}

