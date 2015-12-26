/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.BroadcastReceiver
 *  android.content.Context
 *  android.content.Intent
 *  android.util.Log
 *  java.lang.Object
 *  java.lang.String
 */
package com.android.mms.update;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.android.mms.update.TemplateRequest;
import com.android.mms.update.UpdateManager;

public class TimedUpdateReceiver
extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if ("com.android.mms.update.UPDATE_TEMPLATE".equals((Object)intent.getAction())) {
            Log.v((String)"TimedUpdateReceiver", (String)"try update template() ");
            TemplateRequest.tryUpdateTemplate();
            UpdateManager.onUpdated();
        }
    }
}

