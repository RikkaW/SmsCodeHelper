/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.BroadcastReceiver
 *  android.content.Context
 *  android.content.Intent
 *  android.os.Parcelable
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 */
package cn.com.xy.sms.sdk.ui.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import cn.com.xy.sms.sdk.ui.notification.DoActionActivity;
import cn.com.xy.sms.sdk.ui.notification.MessageItem;

public class DoActionBroadcastReceiver
extends BroadcastReceiver {
    public void onReceive(Context context, Intent object) {
        String string2 = object.getAction();
        object = (MessageItem)object.getParcelableExtra("message");
        Intent intent = new Intent(context, (Class)DoActionActivity.class);
        intent.addFlags(268468224);
        intent.putExtra("message", (Parcelable)object);
        intent.putExtra("action", string2);
        context.startActivity(intent);
    }
}

