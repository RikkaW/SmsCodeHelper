/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.text.TextUtils
 *  android.util.Pair
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 */
import android.text.TextUtils;
import android.util.Pair;
import java.util.ArrayList;
import java.util.List;

public class ami {
    public String a;
    public List<Pair<String, String>> b;

    public ami(String string2, List<Pair<String, String>> list) {
        this.a = string2;
        this.b = list;
    }

    public void a(amm amm2) {
        if (amm2 != null) {
            String string2 = amm2.a();
            String string3 = amm2.b();
            long l2 = amm2.c();
            if (!TextUtils.isEmpty((CharSequence)string2) || !TextUtils.isEmpty((CharSequence)string3) || l2 > 0) {
                if (this.b == null) {
                    this.b = new ArrayList(2);
                }
                if (!TextUtils.isEmpty((CharSequence)string2)) {
                    this.b.add((Pair)new Pair((Object)"Mz_md5", (Object)string2));
                }
                if (!TextUtils.isEmpty((CharSequence)string3)) {
                    this.b.add((Pair)new Pair((Object)"Mz_partial_md5", (Object)string3));
                }
                if (l2 > 0) {
                    this.b.add((Pair)new Pair((Object)"Mz_size", (Object)String.valueOf((long)l2)));
                }
            }
        }
    }
}

