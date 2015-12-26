/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 *  android.util.Pair
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.HashMap
 */
package com.xiaomi.push.service;

import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.push.service.ClientEventDispatcher;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.smack.XMPPException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MIPushClientManager {
    private static ArrayList<Pair<String, byte[]>> pendingMessages;
    private static final Map<String, byte[]> pendingRegisterationRequests;

    static {
        pendingRegisterationRequests = new HashMap();
        pendingMessages = new ArrayList();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void addPendingMessages(String string2, byte[] arrby) {
        ArrayList<Pair<String, byte[]>> arrayList = pendingMessages;
        synchronized (arrayList) {
            pendingMessages.add((Object)new Pair((Object)string2, (Object)arrby));
            if (pendingMessages.size() > 50) {
                pendingMessages.remove(0);
            }
            return;
        }
    }

    public static void notifyError(Context context, String string2, byte[] arrby, int n, String string3) {
        Intent intent = new Intent("com.xiaomi.mipush.ERROR");
        intent.setPackage(string2);
        intent.putExtra("mipush_payload", arrby);
        intent.putExtra("mipush_error_code", n);
        intent.putExtra("mipush_error_msg", string3);
        context.sendBroadcast(intent, ClientEventDispatcher.getReceiverPermission(string2));
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void notifyRegisterError(Context context, int n, String string2) {
        Map<String, byte[]> map = pendingRegisterationRequests;
        synchronized (map) {
            Iterator<String> iterator = pendingRegisterationRequests.keySet().iterator();
            do {
                if (!iterator.hasNext()) {
                    pendingRegisterationRequests.clear();
                    return;
                }
                String string3 = iterator.next();
                MIPushClientManager.notifyError(context, string3, pendingRegisterationRequests.get(string3), n, string2);
            } while (true);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    public static void processPendingMessages(XMPushService xMPushService) {
        Object object = pendingMessages;
        // MONITORENTER : object
        Pair pair = pendingMessages;
        pendingMessages = new ArrayList();
        // MONITOREXIT : object
        try {
            object = pair.iterator();
            while (object.hasNext()) {
                pair = (Pair)object.next();
                xMPushService.sendMIPushPacket((String)pair.first, (byte[])pair.second);
            }
            return;
        }
        catch (XMPPException var1_2) {
            MyLog.e(var1_2);
            xMPushService.disconnect(10, var1_2);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    public static void processPendingRegistrationRequest(XMPushService xMPushService) {
        Iterator<String> iterator;
        try {
            Map<String, byte[]> map = pendingRegisterationRequests;
            // MONITORENTER : map
            iterator = pendingRegisterationRequests.keySet().iterator();
        }
        catch (XMPPException var1_2) {
            MyLog.e(var1_2);
            xMPushService.disconnect(10, var1_2);
            return;
        }
        do {
            if (!iterator.hasNext()) {
                pendingRegisterationRequests.clear();
                // MONITOREXIT : map
                return;
            }
            String string2 = iterator.next();
            xMPushService.sendMIPushPacket(string2, pendingRegisterationRequests.get(string2));
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void registerApp(String string2, byte[] arrby) {
        Map<String, byte[]> map = pendingRegisterationRequests;
        synchronized (map) {
            pendingRegisterationRequests.put(string2, arrby);
            return;
        }
    }
}

