/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 */
package com.android.mms.transaction;

import android.content.Context;
import android.content.Intent;
import com.android.mms.transaction.PushReceiver;

public class HighPushReceiver
extends PushReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        this.processReceive(context, intent);
    }
}

