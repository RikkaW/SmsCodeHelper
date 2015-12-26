/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.BroadcastReceiver
 *  android.content.ComponentName
 *  android.content.Context
 *  android.content.Intent
 *  java.lang.Class
 *  java.lang.String
 */
package com.xiaomi.mipush.sdk;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.xiaomi.mipush.sdk.PushMessageHandler;

public class PushServiceReceiver
extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        Intent intent2 = new Intent(context, (Class)PushMessageHandler.class);
        intent2.putExtras(intent);
        intent2.setAction(intent.getAction());
        try {
            context.startService(intent2);
            return;
        }
        catch (Exception var1_2) {
            return;
        }
    }
}

