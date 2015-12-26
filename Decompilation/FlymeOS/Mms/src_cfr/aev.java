/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.PendingIntent
 *  android.app.TaskStackBuilder
 *  android.appwidget.AppWidgetManager
 *  android.appwidget.AppWidgetProvider
 *  android.content.ComponentName
 *  android.content.Context
 *  android.content.Intent
 *  android.net.Uri
 *  android.util.Log
 *  android.widget.RemoteViews
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 */
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;
import com.android.mms.ui.ComposeMessageActivity;
import com.android.mms.ui.ConversationList;

public class aev
extends AppWidgetProvider {
    public static void a(Context context) {
        if (Log.isLoggable((String)"Mms:widget", (int)2)) {
            Log.v((String)"MmsWidgetProvider", (String)"notifyDatasetChanged");
        }
    }

    private static void a(Context context, int n2) {
        if (Log.isLoggable((String)"Mms:widget", (int)2)) {
            Log.v((String)"MmsWidgetProvider", (String)("updateWidget appWidgetId: " + n2));
        }
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), 2130968855);
        Intent intent = new Intent(context, (Class)aew.class);
        intent.putExtra("appWidgetId", n2);
        intent.setData(Uri.parse((String)intent.toUri(1)));
        remoteViews.setRemoteAdapter(n2, 2131886782, intent);
        remoteViews.setTextViewText(2131886779, (CharSequence)context.getString(2131492888));
        remoteViews.setOnClickPendingIntent(2131886777, PendingIntent.getActivity((Context)context, (int)0, (Intent)new Intent(context, (Class)ConversationList.class), (int)134217728));
        intent = new Intent(context, (Class)ComposeMessageActivity.class);
        intent.setAction("android.intent.action.SENDTO");
        remoteViews.setOnClickPendingIntent(2131886781, PendingIntent.getActivity((Context)context, (int)0, (Intent)intent, (int)134217728));
        intent = TaskStackBuilder.create((Context)context);
        intent.addParentStack((Class)ComposeMessageActivity.class);
        Intent intent2 = new Intent("android.intent.action.VIEW");
        intent2.setType("vnd.android-dir/mms-sms");
        intent.addNextIntent(intent2);
        remoteViews.setPendingIntentTemplate(2131886782, intent.getPendingIntent(0, 134217728));
        AppWidgetManager.getInstance((Context)context).updateAppWidget(n2, remoteViews);
    }

    public void onReceive(Context context, Intent intent) {
        if (Log.isLoggable((String)"Mms:widget", (int)2)) {
            Log.v((String)"MmsWidgetProvider", (String)("onReceive intent: " + (Object)intent));
        }
        if ("com.android.mms.intent.action.ACTION_NOTIFY_DATASET_CHANGED".equals((Object)intent.getAction())) {
            intent = AppWidgetManager.getInstance((Context)context);
            intent.notifyAppWidgetViewDataChanged(intent.getAppWidgetIds(new ComponentName(context, (Class)aev.class)), 2131886782);
            return;
        }
        super.onReceive(context, intent);
    }

    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] arrn) {
        super.onUpdate(context, appWidgetManager, arrn);
        for (int i2 = 0; i2 < arrn.length; ++i2) {
            aev.a(context, arrn[i2]);
        }
    }
}

