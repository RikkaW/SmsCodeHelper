/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.PendingIntent
 *  android.content.ActivityNotFoundException
 *  android.content.BroadcastReceiver
 *  android.content.Context
 *  android.content.Intent
 *  android.content.IntentFilter
 *  android.content.ServiceConnection
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$OnSharedPreferenceChangeListener
 *  android.content.res.Resources
 *  android.os.Handler
 *  android.preference.PreferenceManager
 *  android.util.Log
 *  android.util.SparseIntArray
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 */
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.SparseIntArray;

public class abh {
    private static abh c;
    private Context a;
    private Handler b;
    private Object d = new Object();
    private aiv e = null;
    private ServiceConnection f;

    private abh(Context context) {
        this.f = new abi(this);
        this.a = context;
        this.b = new Handler();
        this.b();
    }

    public static int a(Context context, boolean bl2) {
        if (!wd.c(context) && !wd.e(context)) {
            return 1;
        }
        if (!pj.a()) {
            if (bl2) {
                abh.b(context);
            }
            return 2;
        }
        return 0;
    }

    public static abh a() {
        if (c == null) {
            throw new IllegalStateException("Uninitialized.");
        }
        return c;
    }

    static /* synthetic */ aiv a(abh abh2) {
        return abh2.e;
    }

    static /* synthetic */ aiv a(abh abh2, aiv aiv2) {
        abh2.e = aiv2;
        return aiv2;
    }

    public static void a(Context context) {
        if (c != null) {
            Log.w((String)"SnsMessageManager", (String)"Already initialized.");
        }
        c = new abh(context);
        b.a(context);
    }

    static /* synthetic */ Object b(abh abh2) {
        return abh2.d;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private void b() {
        if (this.e != null) return;
        try {
            Intent intent = new Intent();
            intent.setAction("com.meizu.service.IMzSnsService");
            intent.setPackage("com.meizu.weiboshare");
            this.a.bindService(intent, this.f, 1);
            return;
        }
        catch (Exception var1_2) {
            var1_2.printStackTrace();
            return;
        }
    }

    public static final void b(Context context) {
        Intent intent = new Intent("com.meizu.weibo.action.LOGIN");
        intent.setFlags(524288);
        try {
            context.startActivity(intent);
            return;
        }
        catch (ActivityNotFoundException var0_1) {
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    private aiv c() {
        if (this.e != null) {
            return this.e;
        }
        Log.e((String)"SnsMessageManager", (String)"mSnsSmsService is null begin bindSnsSmsService, wait max time is 3000");
        this.b();
        Object object = this.d;
        // MONITORENTER : object
        try {
            if (this.e == null) {
                this.d.wait(5000);
            }
            // MONITOREXIT : object
        }
        catch (InterruptedException var2_2) {}
        if (this.e != null) return this.e;
        Log.e((String)"SnsMessageManager", (String)"after wait 3000 the mSnsSmsService is null, so bind service is no success...");
        return this.e;
    }

    public String a(String string2, String string3, String string4, byte[] arrby, String string5, boolean bl2, String string6) {
        Log.v((String)"SnsMessageManager", (String)("sendSnsMMS -> destAddress : " + string2 + " , userId : " + string3 + " , uuid : " + string4 + " , attachFilePath : " + string5 + " , deliveryReport : " + bl2 + " , mimeType : " + string6));
        long l2 = System.currentTimeMillis();
        try {
            if (this.c() != null) {
                string2 = this.c().a(string2, string3, string4, arrby, string5, bl2, string6);
                wd.a("SnsMessageManager", "sendSnsMMS", l2);
                return string2;
            }
        }
        catch (Exception var1_2) {
            Log.e((String)"SnsMessageManager", (String)"sendSnsMMS -> error", (Throwable)var1_2);
        }
        return null;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void a(String string2, String string3) {
        Log.e((String)"SnsMessageManager", (String)("cancelTransactionSnsAttachment(), uuid is " + string2 + ", tovid is " + string3));
        long l2 = System.currentTimeMillis();
        try {
            if (this.c() != null) {
                this.c().a(string2, string3);
            }
        }
        catch (Exception var1_2) {
            Log.e((String)"SnsMessageManager", (String)("cancelTransactionSnsAttachment error" + var1_2.getMessage()));
        }
        wd.a("SnsMessageManager", "cancelTransactionSnsAttachment", l2);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void a(String string2, String string3, PendingIntent pendingIntent, String string4) {
        Log.e((String)"SnsMessageManager", (String)"sendSnsTextMessage");
        long l2 = System.currentTimeMillis();
        try {
            if (this.c() != null) {
                this.c().a(string2, "", string3, "", pendingIntent, string4);
            }
        }
        catch (Exception var1_2) {
            Log.e((String)"SnsMessageManager", (String)("sendSnsTextMessage error" + var1_2.getMessage()));
        }
        wd.a("SnsMessageManager", "sendSnsTextMessage", l2);
    }

    public byte[] a(String string2, byte[] arrby, String string3, String string4, long l2) {
        Log.v((String)"SnsMessageManager", (String)("getSnsMMS -> uuid : " + string2 + " , msgBody : " + string3 + " , attachFilePath : " + string4 + " , mmsSize : " + l2));
        long l3 = System.currentTimeMillis();
        try {
            if (this.c() != null) {
                string2 = (String)this.c().a(string2, arrby, string3, string4, l2);
                wd.a("SnsMessageManager", "getSnsMMS", l3);
                return string2;
            }
        }
        catch (Exception var1_2) {
            Log.e((String)"SnsMessageManager", (String)"getSnsMMS -> error", (Throwable)var1_2);
        }
        return null;
    }

    public static final class a {
        public static final String[] a = new String[]{"_id", "address", "subject", "uuid"};
        private static a h;
        private final Context b;
        private final Handler c;
        private final SharedPreferences d;
        private boolean e;
        private final SharedPreferences.OnSharedPreferenceChangeListener f;
        private final BroadcastReceiver g;

        private a(Context context) {
            this.f = new abj(this);
            this.g = new abk(this);
            this.b = context;
            this.c = new Handler();
            this.d = PreferenceManager.getDefaultSharedPreferences((Context)context);
            this.d.registerOnSharedPreferenceChangeListener(this.f);
            context.registerReceiver(this.g, new IntentFilter("android.intent.action.SERVICE_STATE"));
            this.e = a.a(this.d);
        }

        static /* synthetic */ SharedPreferences a(a a2) {
            return a2.d;
        }

        public static void a(Context context) {
            if (h != null) {
                Log.w((String)"SnsMessageManager.SnsDownloadManager", (String)"Already initialized.");
            }
            h = new a(context);
        }

        static boolean a() {
            return "true".equals((Object)aau.a("PROPERTY_OPERATOR_ISROAMING", (Object)null));
        }

        static /* synthetic */ boolean a(a a2, boolean bl2) {
            a2.e = bl2;
            return bl2;
        }

        static boolean a(SharedPreferences sharedPreferences) {
            return a.a(sharedPreferences, a.a());
        }

        static boolean a(SharedPreferences sharedPreferences, boolean bl2) {
            block2 : {
                boolean bl3 = false;
                boolean bl4 = sharedPreferences.getBoolean("pref_key_mms_retrieval_during_roaming", false);
                if (bl2) {
                    bl2 = bl3;
                    if (!bl4) break block2;
                }
                bl2 = true;
            }
            return bl2;
        }

        static /* synthetic */ a b() {
            return h;
        }
    }

    public static final class b {
        public static final SparseIntArray a = new SparseIntArray();
        public static final SparseIntArray b = new SparseIntArray();

        static {
            a.put(1, 2131493583);
            a.put(2, 2131493585);
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public static final String a(Context context, int n2, int n3) {
            String string2 = null;
            String[] arrstring = context.getResources();
            Integer n4 = a.get(n2);
            if ((n2 == 1 || n2 == 2) && n4 != null) {
                return arrstring.getString(n4.intValue());
            }
            if ((n2 -= 2) < 0) {
                if (n3 == -1) return string2;
                return context.getResources().getString(n3);
            }
            if ((arrstring = arrstring.getStringArray(2131361798)).length > n2) return arrstring[n2];
            if (n3 == -1) return string2;
            return context.getResources().getString(n3);
        }

        public static final void a(Context object) {
            object = object.getResources().getIntArray(2131361814);
            for (int i2 = 0; i2 < object.length; ++i2) {
                b.put((int)object[i2], i2);
            }
        }
    }

}

