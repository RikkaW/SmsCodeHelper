/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 *  android.net.Uri
 *  java.lang.String
 *  java.lang.Throwable
 */
package cn.com.xy.sms.sdk.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import cn.com.xy.sms.sdk.action.AbsSdkDoAction;
import java.util.Map;

final class c
extends AbsSdkDoAction {
    c() {
    }

    @Override
    public final void deleteMsgForDatabase(Context context, String string2) {
    }

    @Override
    public final String getContactName(Context context, String string2) {
        return null;
    }

    @Override
    public final void markAsReadForDatabase(Context context, String string2) {
    }

    @Override
    public final void openSms(Context context, String string2, Map<String, String> map) {
        context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse((String)("sms:" + string2))));
    }

    @Override
    public final void openSmsDetail(Context context, String string2, Map map) {
    }

    @Override
    public final void sendSms(Context context, String string2, String string3, int n2, Map<String, String> map) {
        try {
            string2 = new Intent("android.intent.action.SENDTO", Uri.parse((String)("smsto:" + string2)));
            string2.putExtra("sms_body", string3);
            context.startActivity((Intent)string2);
            return;
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
            return;
        }
    }
}

