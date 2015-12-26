/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.PendingIntent
 *  android.content.Context
 *  android.content.Intent
 *  android.graphics.Bitmap
 *  android.os.Parcelable
 *  android.widget.RemoteViews
 *  java.lang.Object
 *  java.lang.String
 *  org.json.JSONObject
 */
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Parcelable;
import android.widget.RemoteViews;
import cn.com.xy.sms.sdk.ui.notification.MessageItem;
import org.json.JSONObject;

public abstract class cg {
    static int c = 100000;
    static int d = 200000;
    static int e = 300000;
    static int f = 400000;
    protected RemoteViews a = null;
    MessageItem b = null;
    private final int g = 199999;
    private final int h = 299999;
    private final int i = 399999;
    private final int j = 499999;

    protected int a() {
        return br.e.duoqu_drop_notification;
    }

    protected PendingIntent a(Context context, int n2, MessageItem messageItem, String string2) {
        Intent intent = new Intent(string2);
        intent.setClassName(context, "cn.com.xy.sms.sdk.ui.notification.DoActionActivity");
        intent.putExtra("action", string2);
        intent.putExtra("message", (Parcelable)messageItem);
        intent.addFlags(268468224);
        return PendingIntent.getActivity((Context)context, (int)n2, (Intent)intent, (int)134217728);
    }

    public RemoteViews a(Context context) {
        if (context == null) {
            return null;
        }
        int n2 = this.a();
        if (n2 != 0) {
            this.a = new RemoteViews(context.getPackageName(), n2);
        }
        return this.a;
    }

    public void a(Context context, Bitmap bitmap, String string2, String string3, JSONObject jSONObject, MessageItem messageItem) {
        if (this.a == null || messageItem == null || context == null) {
            return;
        }
        this.b = messageItem;
        this.a(bitmap);
        this.a(string2);
        this.b(string3);
        this.a(context, messageItem, jSONObject);
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    protected void a(Context context, MessageItem messageItem, JSONObject jSONObject) {
        if (jSONObject == null) return;
        if (jSONObject.length() < 1) {
            return;
        }
        if (e == 399999) {
            e = 300000;
        }
        if (f == 499999) {
            f = 400000;
        }
        if (c == 199999) {
            c = 100000;
        }
        if (d == 299999) {
            d = 200000;
        }
        RemoteViews remoteViews = this.a;
        int n2 = br.d.duoqu_drop_notify_layout;
        int n3 = e;
        e = n3 + 1;
        remoteViews.setOnClickPendingIntent(n2, this.a(context, n3, messageItem, "com.xy.sms.ui.notification.layoutClickAction"));
        remoteViews = this.a;
        n2 = br.d.duoqu_drop_btn_one;
        n3 = f;
        f = n3 + 1;
        remoteViews.setOnClickPendingIntent(n2, this.b(context, n3, messageItem, "com.xy.sms.ui.notification.hasReadButtonClickAction"));
        if (jSONObject.length() == 3) {
            jSONObject = this.a;
            n2 = br.d.duoqu_drop_btn_two;
            n3 = c;
            c = n3 + 1;
            jSONObject.setOnClickPendingIntent(n2, this.b(context, n3, messageItem, "com.xy.sms.ui.notification.deleteButtonClickAction"));
            jSONObject = this.a;
            n2 = br.d.duoqu_drop_btn_three;
            n3 = d;
            d = n3 + 1;
            jSONObject.setOnClickPendingIntent(n2, this.a(context, n3, messageItem, "com.xy.sms.ui.notification.replyButtonClickAction"));
            return;
        }
        if (jSONObject.length() == 4) {
            jSONObject = this.a;
            n2 = br.d.duoqu_drop_btn_two;
            n3 = c;
            c = n3 + 1;
            jSONObject.setOnClickPendingIntent(n2, this.b(context, n3, messageItem, "com.xy.sms.ui.notification.deleteButtonClickAction"));
            jSONObject = this.a;
            n2 = br.d.duoqu_drop_btn_three;
            n3 = d;
            d = n3 + 1;
            jSONObject.setOnClickPendingIntent(n2, this.a(context, n3, messageItem, "com.xy.sms.ui.notification.fristServiceButtonClickAction"));
            return;
        }
        if (jSONObject.length() != 5) return;
        jSONObject = this.a;
        n2 = br.d.duoqu_drop_btn_two;
        n3 = c;
        c = n3 + 1;
        jSONObject.setOnClickPendingIntent(n2, this.a(context, n3, messageItem, "com.xy.sms.ui.notification.secondServiceButtonClickAction"));
        jSONObject = this.a;
        n2 = br.d.duoqu_drop_btn_three;
        n3 = d;
        d = n3 + 1;
        jSONObject.setOnClickPendingIntent(n2, this.a(context, n3, messageItem, "com.xy.sms.ui.notification.fristServiceButtonClickAction"));
    }

    protected void a(Bitmap bitmap) {
        this.a.setImageViewBitmap(br.d.duoqu_drop_logo_img, bitmap);
    }

    protected void a(String string2) {
        this.a.setTextViewText(br.d.duoqu_drop_content_title, (CharSequence)string2);
    }

    protected PendingIntent b(Context context, int n2, MessageItem messageItem, String string2) {
        string2 = new Intent(string2);
        string2.putExtra("message", (Parcelable)messageItem);
        string2.addFlags(268468224);
        return PendingIntent.getBroadcast((Context)context, (int)n2, (Intent)string2, (int)134217728);
    }

    protected void b(String string2) {
        this.a.setTextViewText(br.d.duoqu_drop_content_text, (CharSequence)string2);
    }
}

