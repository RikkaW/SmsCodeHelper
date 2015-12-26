/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.BroadcastReceiver
 *  android.content.Context
 *  android.content.Intent
 *  android.content.IntentFilter
 *  android.content.pm.PackageInfo
 *  android.content.pm.PackageManager
 *  android.util.Log
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.util.concurrent.Executors
 */
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import com.ted.android.contacts.common.DataBus;
import com.ted.android.contacts.updatesdk.IParsedDownload;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class apv {
    private static boolean a = false;
    private static Context b;
    private static a c;
    private static IntentFilter d;
    private static long e;
    private static IParsedDownload f;
    private static boolean g;
    private static int h;
    private static apu i;

    static {
        g = true;
    }

    public static void a() {
        if (b != null && c != null) {
            b.unregisterReceiver((BroadcastReceiver)c);
            c = null;
            d = null;
        }
        f = null;
    }

    static /* synthetic */ void a(long l2) {
        e = l2;
    }

    public static void a(Context context) {
        apv.a(context, 0);
    }

    public static void a(Context context, int n2) {
        h = n2;
        if (a) {
            Log.e((String)"update", (String)"Update SDK init()");
        }
        DataBus.APP_VERSION = apv.b(context);
        if (c == null) {
            c = new a();
            d = new IntentFilter();
            d.addAction("android.intent.action.SCREEN_ON");
            d.addAction("android.intent.action.BATTERY_OKAY");
            d.addAction("android.intent.action.ACTION_POWER_CONNECTED");
            d.addAction("android.intent.action.DATE_CHANGED");
            d.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            d.addAction("android.net.wifi.WIFI_STATE_CHANGED");
            d.addAction("android.net.wifi.STATE_CHANGE");
            b = context;
            b.registerReceiver((BroadcastReceiver)c, d);
        }
    }

    static /* synthetic */ void a(apu apu2) {
        i = apu2;
    }

    public static void a(IParsedDownload iParsedDownload) {
        f = iParsedDownload;
    }

    public static void a(boolean bl2) {
        g = bl2;
    }

    private static String b(Context object) {
        try {
            object = object.getPackageManager().getPackageInfo((String)object.getPackageName(), (int)16384).versionName;
            return object;
        }
        catch (Exception var0_1) {
            var0_1.printStackTrace();
            return null;
        }
    }

}

