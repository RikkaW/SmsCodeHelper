/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.Intent
 *  android.os.Bundle
 *  android.os.Parcelable
 *  java.lang.Object
 *  java.lang.String
 */
package cn.com.xy.sms.sdk.ui.notification;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.ui.notification.MessageItem;
import cn.com.xy.sms.sdk.util.DuoquUtils;
import java.util.Map;

public class DoActionActivity
extends Activity {
    /*
     * Enabled aggressive block sorting
     */
    private void a(Context context) {
        Object object = this.getIntent();
        MessageItem messageItem = (MessageItem)object.getParcelableExtra("message");
        if ("com.xy.sms.ui.notification.fristServiceButtonClickAction".equals(object = object.getStringExtra("action"))) {
            cj.a(context, messageItem, 0);
        } else if ("com.xy.sms.ui.notification.secondServiceButtonClickAction".equals(object)) {
            cj.a(context, messageItem, 1);
        } else if ("com.xy.sms.ui.notification.layoutClickAction".equals(object)) {
            cj.a(context, messageItem);
        } else if ("com.xy.sms.ui.notification.hasReadButtonClickAction".equals(object)) {
            DuoquUtils.getSdkDoAction().markAsReadForDatabase(context, String.valueOf((int)messageItem.a));
        } else if ("com.xy.sms.ui.notification.deleteButtonClickAction".equals(object)) {
            DuoquUtils.getSdkDoAction().deleteMsgForDatabase(context, String.valueOf((int)messageItem.a));
        } else if ("com.xy.sms.ui.notification.replyButtonClickAction".equals(object)) {
            DuoquUtils.getSdkDoAction().replySms(context, String.valueOf((int)messageItem.a), messageItem.b, messageItem.c, messageItem.d);
        }
        if (messageItem.a != 0 && !"com.xy.sms.ui.notification.deleteButtonClickAction".equals(object)) {
            cj.a(context, 123);
            this.finish();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(br.e.duoqu_activity_do_action);
        LogManager.i("DoActionActivity", "DoActionActivity is onCreated...");
        this.a((Context)this);
    }

    protected void onDestroy() {
        cj.a();
        super.onDestroy();
    }
}

