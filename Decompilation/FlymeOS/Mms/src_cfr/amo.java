/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.net.Uri
 *  android.os.AsyncTask
 *  android.text.TextUtils
 *  java.lang.Object
 *  java.lang.String
 *  java.util.UUID
 */
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.meizu.statsapp.UsageStatsProxy;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class amo {
    final String a;
    private UsageStatsProxy b;
    private Context c;

    public amo(Context context) {
        this.b = UsageStatsProxy.a(context, true);
        this.c = context;
        this.a = UUID.randomUUID().toString();
    }

    static /* synthetic */ void a(amo amo2, Map map) {
        amo2.a(map);
    }

    private void a(String string2, int n2, String string3, int n3, String string4, String string5, String string6) {
        new amp(this, n2, string2, string3, n3, string4, string5, string6).execute((Object[])new Void[0]);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void a(Map<String, String> map) {
        try {
            map.put("uuid", this.a);
            map.put("clientip", amq.a());
            Object object = null;
            if (map.containsKey("redirect_url")) {
                object = map.get("redirect_url");
            } else if (map.containsKey("requrl")) {
                object = map.get("requrl");
            }
            if (!TextUtils.isEmpty((CharSequence)object) && !TextUtils.isEmpty((CharSequence)(object = Uri.parse((String)object).getHost()))) {
                map.put("serverip", amq.a((String)object));
            }
            map.put("product", this.c.getPackageName());
            anf.a("Write usage log:");
            for (String string2 : map.keySet()) {
                anf.a(string2 + "=" + map.get(string2));
            }
        }
        catch (Exception var1_2) {
            var1_2.printStackTrace();
            return;
        }
        this.b.a("dns.download.app", map);
    }

    public void a(String string2, String string3, int n2, String string4, String string5, String string6) {
        this.a(string2, 3, string3, n2, string4, string5, string6);
    }

    public void a(String string2, String string3, String string4, String string5, String string6) {
        this.a(string2, 1, string3, 200, string4, string5, string6);
    }

    public void b(String string2, String string3, int n2, String string4, String string5, String string6) {
        this.a(string2, 2, string3, n2, string4, string5, string6);
    }

    public void b(String string2, String string3, String string4, String string5, String string6) {
        this.a(string2, 4, string3, 100002, string4, string5, string6);
    }
}

