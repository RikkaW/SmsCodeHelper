/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  java.lang.Class
 *  java.lang.Enum
 *  java.lang.Object
 *  java.lang.String
 *  java.util.HashMap
 */
import android.content.Context;
import com.meizu.statsapp.UsageStatsProxy;
import java.util.HashMap;
import java.util.Map;

public class and {
    private static and a;
    private UsageStatsProxy b;
    private Context c;
    private final String d;

    public and(Context context) {
        this.c = context.getApplicationContext();
        this.d = context.getPackageName();
        this.b = UsageStatsProxy.a(context, true);
    }

    public static final and a(Context object) {
        synchronized (and.class) {
            if (a == null) {
                a = new and((Context)object);
            }
            object = a;
            return object;
        }
    }

    public void a(a a2, String string2) {
        this.a(a2, string2, null);
    }

    public void a(a a2, String string2, String string3) {
        this.a(a2, string2, string3, false);
    }

    public void a(a a2, String string2, String string3, boolean bl2) {
        HashMap hashMap = new HashMap();
        hashMap.put("update_action", a2.a());
        hashMap.put("package_name", this.d);
        if (string2 != null) {
            hashMap.put("new_version", string2);
        }
        if (string3 != null) {
            hashMap.put("old_version", string3);
        }
        if (bl2) {
            hashMap.put("update_manual", "manual");
        }
        this.b.a("update.component.app", (Map<String, String>)hashMap);
    }

    public static enum a {
        a("PushMessageReceived"),
        b("UpdateDisplay_Alert"),
        c("UpdateDisplay_Notification"),
        d("UpdateAlert_Yes"),
        e("UpdateAlert_Ignore"),
        f("UpdateAlert_No"),
        g("Download_Del"),
        h("Download_Failure"),
        i("Download_Done"),
        j("Install_Yes"),
        k("Install_No"),
        l("Install_Complete"),
        m("Install_Failure");
        
        private String n;

        private a(String string3) {
            this.n = string3;
        }

        public String a() {
            return this.n;
        }
    }

}

