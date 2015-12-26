/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.PendingIntent
 *  android.appwidget.AppWidgetManager
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.content.Intent
 *  android.content.res.Resources
 *  android.database.Cursor
 *  android.net.Uri
 *  android.text.SpannableStringBuilder
 *  android.text.style.ForegroundColorSpan
 *  android.text.style.StyleSpan
 *  android.text.style.TextAppearanceSpan
 *  android.util.Log
 *  android.widget.RemoteViews
 *  android.widget.RemoteViewsService
 *  android.widget.RemoteViewsService$RemoteViewsFactory
 *  java.lang.Class
 *  java.lang.Integer
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import com.android.mms.MmsApp;
import com.android.mms.ui.ConversationList;
import com.android.mms.view.ConversationListItem;

public class aew
extends RemoteViewsService {
    private static final Object a = new Object();

    public RemoteViewsService.RemoteViewsFactory onGetViewFactory(Intent intent) {
        if (Log.isLoggable((String)"Mms:widget", (int)2)) {
            Log.v((String)"MmsWidgetService", (String)("onGetViewFactory intent: " + (Object)intent));
        }
        return new a(this.getApplicationContext(), intent);
    }

    static class a
    implements RemoteViewsService.RemoteViewsFactory,
    gm.b {
        private static int g;
        private static int h;
        private static int i;
        private static int j;
        private final Context a;
        private final int b;
        private boolean c;
        private Cursor d;
        private int e;
        private final AppWidgetManager f;

        public a(Context context, Intent intent) {
            this.a = context;
            this.b = intent.getIntExtra("appWidgetId", 0);
            this.f = AppWidgetManager.getInstance((Context)context);
            if (Log.isLoggable((String)"Mms:widget", (int)2)) {
                Log.v((String)"MmsWidgetService", (String)("MmsFactory intent: " + (Object)intent + "widget id: " + this.b));
            }
            context = context.getResources();
            i = context.getColor(2131820871);
            j = context.getColor(2131820872);
            g = context.getColor(2131820873);
            h = context.getColor(2131820874);
        }

        private Cursor a() {
            return this.a.getContentResolver().query(gr.a, gr.b, null, null, null);
        }

        private SpannableStringBuilder a(CharSequence charSequence, int n2) {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(charSequence);
            if (n2 != 0) {
                spannableStringBuilder.setSpan((Object)new ForegroundColorSpan(n2), 0, charSequence.length(), 33);
            }
            return spannableStringBuilder;
        }

        /*
         * Loose catch block
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         * Lifted jumps to return sites
         */
        private int b() {
            Cursor cursor;
            int n2;
            block5 : {
                block4 : {
                    try {
                        cursor = this.a.getContentResolver().query(gr.a, gr.b, "read=0", null, null);
                        if (cursor == null) break block4;
                    }
                    catch (Throwable var3_3) {
                        void var3_4;
                        cursor = null;
                        if (cursor == null) throw var3_4;
                        cursor.close();
                        throw var3_4;
                    }
                    n2 = cursor.getCount();
                    break block5;
                    {
                        catch (Throwable throwable) {}
                    }
                }
                n2 = 0;
            }
            if (cursor == null) return n2;
            cursor.close();
            return n2;
        }

        private int c() {
            if (Log.isLoggable((String)"Mms:widget", (int)2)) {
                Log.v((String)"MmsWidgetService", (String)"getConversationCount");
            }
            return Math.min((int)this.d.getCount(), (int)25);
        }

        private RemoteViews d() {
            if (Log.isLoggable((String)"Mms:widget", (int)2)) {
                Log.v((String)"MmsWidgetService", (String)"getViewMoreConversationsView");
            }
            RemoteViews remoteViews = new RemoteViews(this.a.getPackageName(), 2130968857);
            remoteViews.setTextViewText(2131886790, this.a.getText(2131493164));
            remoteViews.setOnClickPendingIntent(2131886789, PendingIntent.getActivity((Context)this.a, (int)0, (Intent)new Intent(this.a, (Class)ConversationList.class), (int)134217728));
            return remoteViews;
        }

        /*
         * Enabled aggressive block sorting
         */
        private void e() {
            if (Log.isLoggable((String)"Mms:widget", (int)2)) {
                Log.v((String)"MmsWidgetService", (String)"onLoadComplete");
            }
            RemoteViews remoteViews = new RemoteViews(this.a.getPackageName(), 2130968855);
            int n2 = this.e > 0 ? 0 : 8;
            remoteViews.setViewVisibility(2131886780, n2);
            if (this.e > 0) {
                remoteViews.setTextViewText(2131886780, (CharSequence)Integer.toString((int)this.e));
            }
            this.f.partiallyUpdateAppWidget(this.b, remoteViews);
        }

        @Override
        public void a(gm gm2) {
            if (Log.isLoggable((String)"Mms:widget", (int)2)) {
                Log.v((String)"MmsWidgetService", (String)("onUpdate from Contact: " + gm2));
            }
            this.f.notifyAppWidgetViewDataChanged(this.b, 2131886782);
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        public int getCount() {
            int n2 = 0;
            if (Log.isLoggable((String)"Mms:widget", (int)2)) {
                Log.v((String)"MmsWidgetService", (String)"getCount");
            }
            Object object = a;
            synchronized (object) {
                if (this.d == null) {
                    return 0;
                }
                int n3 = this.c();
                boolean bl2 = n3 < this.d.getCount();
                this.c = bl2;
                if (this.c) {
                    n2 = 1;
                }
                return n2 + n3;
            }
        }

        public long getItemId(int n2) {
            return n2;
        }

        public RemoteViews getLoadingView() {
            RemoteViews remoteViews = new RemoteViews(this.a.getPackageName(), 2130968857);
            remoteViews.setTextViewText(2131886790, this.a.getText(2131492978));
            return remoteViews;
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        public RemoteViews getViewAt(int n2) {
            int n3 = 0;
            if (Log.isLoggable((String)"Mms:widget", (int)2)) {
                Log.v((String)"MmsWidgetService", (String)("getViewAt position: " + n2));
            }
            Object object = a;
            synchronized (object) {
                if (this.d == null) return this.d();
                if (this.c && n2 >= this.c()) {
                    return this.d();
                }
                if (!this.d.moveToPosition(n2)) {
                    Log.w((String)"MmsWidgetService", (String)("Failed to move to position: " + n2));
                    return this.d();
                }
                gr gr2 = gr.a((Context)MmsApp.c(), this.d);
                RemoteViews remoteViews = new RemoteViews(this.a.getPackageName(), 2130968856);
                if (gr2.n()) {
                    remoteViews.setViewVisibility(2131886784, 0);
                    remoteViews.setViewVisibility(2131886785, 8);
                } else {
                    remoteViews.setViewVisibility(2131886784, 8);
                    remoteViews.setViewVisibility(2131886785, 0);
                }
                n2 = gr2.q() ? n3 : 8;
                remoteViews.setViewVisibility(2131886788, n2);
                String string2 = wd.a(this.a, gr2.j());
                n2 = gr2.n() ? h : g;
                remoteViews.setTextViewText(2131886786, (CharSequence)this.a(string2, n2));
                n2 = gr2.n() ? j : i;
                string2 = this.a(gr2.h().a(", "), n2);
                if (gr2.i()) {
                    string2.append((CharSequence)this.a.getResources().getString(2131492947));
                    n3 = string2.length();
                    string2.append((CharSequence)this.a.getResources().getString(2131492967));
                    string2.setSpan((Object)new TextAppearanceSpan(this.a, 16973894, n2), n3, string2.length(), 17);
                    string2.setSpan((Object)new ForegroundColorSpan(this.a.getResources().getColor(2130838705)), n3, string2.length(), 17);
                }
                if (gr2.n()) {
                    string2.setSpan((Object)ConversationListItem.a, 0, string2.length(), 17);
                }
                remoteViews.setTextViewText(2131886623, (CharSequence)string2);
                string2 = gr2.l();
                n2 = gr2.n() ? h : g;
                remoteViews.setTextViewText(2131886596, (CharSequence)this.a(string2, n2));
                string2 = new Intent("android.intent.action.VIEW");
                string2.setType("vnd.android-dir/mms-sms");
                string2.putExtra("thread_id", gr2.e());
                remoteViews.setOnClickFillInIntent(2131886783, (Intent)string2);
                return remoteViews;
            }
        }

        public int getViewTypeCount() {
            return 2;
        }

        public boolean hasStableIds() {
            return true;
        }

        public void onCreate() {
            if (Log.isLoggable((String)"Mms:widget", (int)2)) {
                Log.v((String)"MmsWidgetService", (String)"onCreate");
            }
            gm.a(this);
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        public void onDataSetChanged() {
            if (Log.isLoggable((String)"Mms:widget", (int)2)) {
                Log.v((String)"MmsWidgetService", (String)"onDataSetChanged");
            }
            Object object = a;
            synchronized (object) {
                if (this.d != null) {
                    this.d.close();
                    this.d = null;
                }
                this.d = this.a();
                this.e = this.b();
                this.e();
                return;
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        public void onDestroy() {
            if (Log.isLoggable((String)"Mms:widget", (int)2)) {
                Log.v((String)"MmsWidgetService", (String)"onDestroy");
            }
            Object object = a;
            synchronized (object) {
                if (this.d != null && !this.d.isClosed()) {
                    this.d.close();
                    this.d = null;
                }
                gm.b(this);
                return;
            }
        }
    }

}

