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

public class amv {
    final String a;
    private UsageStatsProxy b;
    private Context c;

    public amv(Context context) {
        this.b = UsageStatsProxy.a(context, true);
        this.c = context;
        this.a = UUID.randomUUID().toString();
    }

    static /* synthetic */ Context a(amv amv2) {
        return amv2.c;
    }

    private void a(int n2, int n3, String string2) {
        new amw(this, n2, n3, string2).execute((Object[])new Void[0]);
    }

    static /* synthetic */ void a(amv amv2, Map map, String string2) {
        amv2.a(map, string2);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void a(Map<String, String> map, String object) {
        try {
            map.put("uuid", this.a);
            map.put("clientip", amq.a());
            if (!TextUtils.isEmpty((CharSequence)object) && !TextUtils.isEmpty((CharSequence)(object = Uri.parse((String)object).getHost()))) {
                map.put("serverip", amq.a((String)object));
            }
            map.put("product", this.c.getPackageName());
            anf.a("Write push usage log:");
            for (String string2 : map.keySet()) {
                anf.a(string2 + "=" + map.get(string2));
            }
            this.b.a("update.push.system.app", map);
            return;
        }
        catch (Exception var1_2) {
            var1_2.printStackTrace();
            return;
        }
    }

    public void a(int n2, String string2) {
        this.a(4, n2, string2);
    }

    public void a(String string2) {
        this.a(1, 200, string2);
    }

    public void b(String string2) {
        this.a(2, 200, string2);
    }

    public void c(String string2) {
        this.a(3, 200, string2);
    }
}

