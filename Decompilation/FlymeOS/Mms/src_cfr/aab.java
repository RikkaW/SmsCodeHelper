/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.text.TextUtils
 *  android.util.Log
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 *  java.util.HashMap
 */
import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.meizu.statsapp.UsageStatsProxy;
import java.util.HashMap;
import java.util.Map;

public class aab {
    public static boolean a = false;
    public static boolean b = false;

    public static void a(Activity activity) {
        aab.a((Context)activity, aab.c(activity));
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void a(Context context) {
        Log.i((String)"MessageUsageStatsUtils", (String)("monitorSendMessageSituation OPEN_FROM_NEW_MESSAGE = " + a + ", OPEN_FROM_LIST_ITEM_CLICK = " + b));
        if (a) {
            aab.a(context, "send_message_under_new_conversation", "ComposeMessageActivity");
            a = false;
            return;
        } else {
            if (!b) return;
            {
                aab.a(context, "send_message_under_conversation", "ComposeMessageActivity");
                b = false;
                return;
            }
        }
    }

    public static void a(Context context, String string2) {
        if (context != null && !TextUtils.isEmpty((CharSequence)string2)) {
            aab.a("onPageStart pageName = " + string2);
            aab.b(context).a(string2);
            return;
        }
        Log.e((String)"MessageUsageStatsUtils", (String)("onPageStart context = " + (Object)context + ", pageName = " + string2));
    }

    public static void a(Context context, String string2, String string3) {
        aab.a(context, string2, string3, "");
    }

    public static void a(Context context, String string2, String string3, String string4) {
        if (context != null && !TextUtils.isEmpty((CharSequence)string2)) {
            aab.a("onEvent eventName = " + string2 + ", pageName = " + string3 + ", property = " + string4);
            aab.b(context).a(string2, string3, string4);
            return;
        }
        Log.e((String)"MessageUsageStatsUtils", (String)("onEvent context = " + (Object)context + ", eventName = " + string2));
    }

    public static void a(Context context, String string2, String string3, String string4, String string5) {
        if (context != null && !TextUtils.isEmpty((CharSequence)string2)) {
            aab.a("onEvent eventName = " + string2 + ", pageName = " + string3 + ", key = " + string4 + ", property = " + string5);
            HashMap hashMap = new HashMap();
            hashMap.put(string4, string5);
            aab.b(context).a(string2, string3, (Map<String, String>)hashMap);
            return;
        }
        Log.e((String)"MessageUsageStatsUtils", (String)("onEvent context = " + (Object)context + ", eventName = " + string2));
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void a(Context context, String string2, String string3, boolean bl2) {
        String string4 = bl2 ? "1" : "0";
        aab.a(context, string2, string3, string4);
    }

    private static void a(String string2) {
        Log.v((String)"MessageUsageStatsUtils", (String)("logV:" + string2));
    }

    private static UsageStatsProxy b(Context context) {
        return UsageStatsProxy.a(context);
    }

    public static void b(Activity activity) {
        aab.b((Context)activity, aab.c(activity));
    }

    public static void b(Context context, String string2) {
        if (context != null && !TextUtils.isEmpty((CharSequence)string2)) {
            aab.a("onPageStop pageName = " + string2);
            aab.b(context).b(string2);
            return;
        }
        Log.e((String)"MessageUsageStatsUtils", (String)("onPageStop context = " + (Object)context + ", pageName = " + string2));
    }

    public static String c(Activity activity) {
        if (activity != null) {
            return activity.getClass().getSimpleName();
        }
        return null;
    }
}

