/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.BroadcastReceiver
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.content.Intent
 *  android.content.IntentFilter
 *  android.content.pm.ApplicationInfo
 *  android.content.pm.PackageManager
 *  android.content.pm.PackageManager$NameNotFoundException
 *  android.database.ContentObserver
 *  android.net.Uri
 *  android.os.Bundle
 *  android.os.Handler
 *  android.os.HandlerThread
 *  android.os.Looper
 *  android.os.Message
 *  android.provider.Settings
 *  android.provider.Settings$System
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.HashMap
 *  java.util.LinkedList
 *  java.util.UUID
 */
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import com.meizu.statsapp.UsageStatsProxy;
import com.meizu.statsapp.UsageStatusLog;
import com.meizu.statsapp.util.Utils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ajn
extends ajj.a {
    private static Object a = new Object();
    private static volatile ajn b;
    private Context c;
    private boolean d;
    private boolean e;
    private ajq f;
    private ajt g;
    private HandlerThread h = new HandlerThread("RecordEventThread");
    private c i;
    private Map<String, String> j = new HashMap();
    private d k;
    private volatile boolean l = true;
    private LinkedList<b> m = new LinkedList();
    private long n;
    private List<UsageStatsProxy.Event> o = new ArrayList();
    private int p = 0;
    private String q = null;
    private String r = null;
    private String s = null;
    private Runnable t;

    private ajn(Context context, boolean bl2, boolean bl3) {
        this.t = new ajo(this);
        this.c = context;
        this.d = bl2;
        this.e = bl3;
        this.h.start();
        this.i = new c(this.h.getLooper());
        this.a();
    }

    static /* synthetic */ long a(ajn ajn2, long l2) {
        ajn2.n = l2;
        return l2;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    static ajn a(Context context, boolean bl2, boolean bl3) {
        if (b == null) {
            Object object = a;
            synchronized (object) {
                if (b == null) {
                    b = new ajn(context, bl2, bl3);
                }
            }
        }
        return b;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void a() {
        Object object;
        IntentFilter intentFilter = null;
        try {
            object = akc.a("com.android.internal.telephony.TelephonyIntents", "ACTION_SERVICE_STATE_CHANGED");
        }
        catch (NoSuchFieldException var2_3) {
            var2_3.printStackTrace();
            object = null;
        }
        object = object == null ? intentFilter : object.toString();
        this.s = object;
        UsageStatusLog.initLog();
        this.a(this.c);
        this.q = Utils.getNetworkType(this.c);
        object = new a();
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        intentFilter.addAction(this.s);
        try {
            this.c.unregisterReceiver((BroadcastReceiver)object);
            this.c.registerReceiver((BroadcastReceiver)object, intentFilter);
        }
        catch (Exception var2_4) {
            var2_4.printStackTrace();
        }
        this.f = ajq.a(this.c, this.d);
        if (this.d) {
            this.g = ajt.a(this.c, this.d, this.e);
        }
        if (!this.d) {
            boolean bl2 = Settings.System.getInt((ContentResolver)this.c.getContentResolver(), (String)"meizu_data_collection", (int)0) != 0;
            this.l = bl2;
            this.k = new d(this.i);
            this.c.getContentResolver().registerContentObserver(Settings.System.getUriFor((String)"meizu_data_collection"), true, (ContentObserver)this.k);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void a(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) return;
        {
            try {
                context = packageManager.getApplicationInfo(context.getPackageName(), 128);
                if (context == null || context.metaData == null) return;
                {
                    int n2 = context.metaData.get("uxip_channel_num") == null ? 0 : (Integer)context.metaData.get("uxip_channel_num");
                    this.p = n2;
                    return;
                }
            }
            catch (PackageManager.NameNotFoundException var1_2) {
                var1_2.printStackTrace();
            }
        }
    }

    static /* synthetic */ int c(ajn ajn2) {
        return ajn2.p;
    }

    static /* synthetic */ String d(ajn ajn2) {
        return ajn2.q;
    }

    static /* synthetic */ LinkedList f(ajn ajn2) {
        return ajn2.m;
    }

    static /* synthetic */ Runnable g(ajn ajn2) {
        return ajn2.t;
    }

    @Override
    public void a(UsageStatsProxy.Event event) {
        event = this.i.obtainMessage(1, (Object)event);
        this.i.sendMessage((Message)event);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void a(String object, boolean bl2, String string2, long l2) {
        if (Utils.isEmpty(string2)) {
            return;
        }
        object = new b((String)object, string2, l2);
        object = bl2 ? this.i.obtainMessage(3, object) : this.i.obtainMessage(4, object);
        this.i.sendMessage((Message)object);
    }

    @Override
    public void a(boolean bl2) {
        if (this.d && this.e != bl2) {
            this.e = bl2;
            this.g.b(this.e);
        }
    }

    @Override
    public void b(UsageStatsProxy.Event event) {
        event = this.i.obtainMessage(2, (Object)event);
        this.i.sendMessage((Message)event);
    }

    public class a
    extends BroadcastReceiver {
        /*
         * Enabled aggressive block sorting
         */
        public void onReceive(Context context, Intent intent) {
            if ("android.net.conn.CONNECTIVITY_CHANGE".equals((Object)intent.getAction())) {
                ajn.this.q = Utils.getNetworkType(ajn.this.c);
                return;
            } else {
                if (!ajn.this.s.equals((Object)intent.getAction())) return;
                {
                    ajn.this.q = Utils.getNetworkType(ajn.this.c);
                    return;
                }
            }
        }
    }

    static class b {
        private String a;
        private String b;
        private long c;

        public b(String string2, String string3, long l2) {
            this.a = string2;
            this.b = string3;
            this.c = l2;
        }

        static /* synthetic */ long a(b b2) {
            return b2.c;
        }

        public String a() {
            return this.a;
        }

        public String b() {
            return this.b;
        }

        public long c() {
            return this.c;
        }

        public String toString() {
            return "{" + this.b + ", " + this.c + ", " + this.a + "}";
        }
    }

    class c
    extends Handler {
        public c(Looper looper) {
            super(looper);
        }

        private void a(UsageStatsProxy.Event event) {
            String string2;
            String string3;
            try {
                string3 = string2 = (String)ajn.this.j.get(event.e());
            }
            catch (Exception var1_2) {
                var1_2.printStackTrace();
                return;
            }
            if (Utils.isEmpty(string2)) {
                string3 = UUID.randomUUID().toString();
                ajn.this.j.put(event.e(), string3);
            }
            event.b(string3);
            if (3 == event.b()) {
                event.c("com.meizu.uxip.log");
            }
        }

        private void b(UsageStatsProxy.Event event) {
            UsageStatusLog.d("UsageStatsManagerServer", "insert Event " + event.toString());
            try {
                ajn.this.f.a(event);
                if (ajn.this.d) {
                    ajn.this.g.a();
                }
                return;
            }
            catch (Exception var1_2) {
                var1_2.printStackTrace();
                return;
            }
        }

        /*
         * Exception decompiling
         */
        public void handleMessage(Message var1_1) {
            // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
            // java.lang.IllegalStateException: Backjump on non jumping statement [5, 7] lbl16 : TryStatement: try { 1[TRYBLOCK]

            // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.Cleaner$1.call(Cleaner.java:44)
            // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.Cleaner$1.call(Cleaner.java:22)
            // org.benf.cfr.reader.util.graph.GraphVisitorDFS.process(GraphVisitorDFS.java:68)
            // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.Cleaner.removeUnreachableCode(Cleaner.java:54)
            // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.RemoveDeterministicJumps.apply(RemoveDeterministicJumps.java:35)
            // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:507)
            // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:220)
            // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:165)
            // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:91)
            // org.benf.cfr.reader.entities.Method.analyse(Method.java:354)
            // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:751)
            // org.benf.cfr.reader.entities.ClassFile.analyseInnerClassesPass1(ClassFile.java:664)
            // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:747)
            // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:683)
            // org.benf.cfr.reader.Main.doJar(Main.java:128)
            // org.benf.cfr.reader.Main.main(Main.java:178)
            throw new IllegalStateException("Decompilation failed");
        }
    }

    class d
    extends ContentObserver {
        public d(Handler handler) {
            super(handler);
        }

        public void onChange(boolean bl2, Uri uri) {
            bl2 = false;
            if (Settings.System.getInt((ContentResolver)ajn.this.c.getContentResolver(), (String)"meizu_data_collection", (int)0) != 0) {
                bl2 = true;
            }
            UsageStatusLog.d("UsageStatsManagerServer", "usage stats switch changed : " + bl2);
            if (ajn.this.l != bl2) {
                ajn.this.l = bl2;
                if (!ajn.this.l) {
                    ajn.this.i.sendEmptyMessage(5);
                }
            }
        }
    }

}

