/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.text.TextUtils
 *  android.util.Pair
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 */
import android.content.Context;
import android.text.TextUtils;
import android.util.Pair;
import java.util.ArrayList;
import java.util.List;

public class amg {
    private static amh a;

    public static void a() {
        a = null;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private amh b(Context context) {
        try {
            String string2 = this.c(context);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new Pair((Object)"sim_card_sp", (Object)string2));
            arrayList.add(new Pair((Object)"rule_id", (Object)"15"));
            string2 = this.a("http://servicecut.meizu.com/interface/locate", (List<Pair<String, String>>)arrayList);
            if (!TextUtils.isEmpty((CharSequence)string2)) {
                anf.a("Proxy info: " + string2);
                return new amh(string2, context);
            }
            anf.d("Proxy response is null!");
            do {
                return null;
                break;
            } while (true);
        }
        catch (Exception var1_2) {
            anf.d("Load proxy exception!");
            var1_2.printStackTrace();
            return null;
        }
    }

    private String c(Context context) {
        if (anl.j(context)) {
            return "wifi";
        }
        return anl.k(context);
    }

    public amh a(Context context) {
        if (a != null && !a.a(context)) {
            return a;
        }
        a = new amg().b(context);
        return a;
    }

    protected String a(String string2, List<Pair<String, String>> list) {
        return ank.a(string2, list);
    }
}

