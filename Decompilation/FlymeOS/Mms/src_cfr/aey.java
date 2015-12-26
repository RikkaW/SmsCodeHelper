/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.accounts.Account
 *  android.text.TextUtils
 *  android.util.Log
 *  java.lang.Class
 *  java.lang.Enum
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.Arrays
 *  java.util.Collections
 *  java.util.HashMap
 */
import android.accounts.Account;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class aey {
    private static final Map<String, Integer> a = new HashMap();
    private static final List<String> s;
    private final i b = new i();
    private List<m> c;
    private List<d> d;
    private List<o> e;
    private List<l> f;
    private List<h> g;
    private List<n> h;
    private List<r> i;
    private List<p> j;
    private List<j> k;
    private List<k> l;
    private List<a> m;
    private c n;
    private b o;
    private final int p;
    private final Account q;
    private List<aey> r;

    static {
        a.put("X-AIM", 0);
        a.put("X-MSN", 1);
        a.put("X-YAHOO", 2);
        a.put("X-ICQ", 6);
        a.put("X-JABBER", 7);
        a.put("X-SKYPE-USERNAME", 3);
        a.put("X-GOOGLE-TALK", 5);
        a.put("X-GOOGLE TALK", 5);
        s = Collections.unmodifiableList((List)new ArrayList(0));
    }

    public aey() {
        this(-1073741824);
    }

    public aey(int n2) {
        this(n2, null);
    }

    public aey(int n2, Account account) {
        this.p = n2;
        this.q = account;
    }

    private String a(Map<String, Collection<String>> object) {
        if ((object = object.get("SORT-AS")) != null && object.size() != 0) {
            if (object.size() > 1) {
                Log.w((String)"vCard", (String)("Incorrect multiple SORT_AS parameters detected: " + Arrays.toString((Object[])object.toArray())));
            }
            Object object2 = afl.a((String)object.iterator().next(), this.p);
            object = new StringBuilder();
            object2 = object2.iterator();
            while (object2.hasNext()) {
                object.append((String)object2.next());
            }
            return object.toString();
        }
        return null;
    }

    private void a(int n2, String string2, String string3, int n3, boolean bl2) {
        if (this.g == null) {
            this.g = new ArrayList();
        }
        this.g.add(new h(n2, string2, string3, n3, bl2));
    }

    /*
     * Unable to fully structure code
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private void a(int var1_1, String var2_2, String var3_3, boolean var4_4) {
        var8_5 = 0;
        if (this.c == null) {
            this.c = new ArrayList();
        }
        var10_6 = new StringBuilder();
        var2_2 = var2_2.trim();
        if (var1_1 == 6 || aex.f(this.p)) lbl-1000: // 3 sources:
        {
            do {
                var2_2 = new m((String)var2_2, var1_1, var3_3, var4_4);
                this.c.add((m)var2_2);
                return;
                break;
            } while (true);
        }
        var9_7 = var2_2.length();
        block1 : for (var7_8 = 0; var7_8 < var9_7; ++var7_8) {
            var5_9 = var2_2.charAt(var7_8);
            if (var5_9 == 'p' || var5_9 == 'P') {
                var10_6.append(',');
                var6_10 = 1;
lbl17: // 5 sources:
                do {
                    var8_5 = var6_10;
                    continue block1;
                    break;
                } while (true);
            }
            if (var5_9 != 'w' && var5_9 != 'W') ** GOTO lbl24
            var10_6.append(';');
            var6_10 = 1;
            ** GOTO lbl17
lbl24: // 1 sources:
            if ('0' <= var5_9 && var5_9 <= '9') ** GOTO lbl29
            var6_10 = var8_5;
            if (var7_8 != 0) ** GOTO lbl17
            var6_10 = var8_5;
            if (var5_9 != '+') ** GOTO lbl17
lbl29: // 2 sources:
            var10_6.append(var5_9);
            var6_10 = var8_5;
            ** continue;
        }
        if (var8_5 != 0) ** GOTO lbl36
        var6_10 = afl.a(this.p);
        var2_2 = afl.b.a(var10_6.toString(), var6_10);
        ** GOTO lbl-1000
lbl36: // 1 sources:
        var2_2 = var10_6.toString();
        ** while (true)
    }

    private void a(int n2, List<String> list, String string2, boolean bl2) {
        if (this.e == null) {
            this.e = new ArrayList(0);
        }
        this.e.add(o.a(list, n2, string2, bl2, this.p));
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private void a(int var1_1, List<String> var2_2, Map<String, Collection<String>> var3_3, boolean var4_4) {
        var7_5 = this.a((Map<String, Collection<String>>)var3_3);
        var3_3 = var2_2;
        if (var2_2 == null) {
            var3_3 = aey.s;
        }
        var6_6 = var3_3.size();
        switch (var6_6) {
            default: {
                var2_2 = var3_3.get(0);
                var8_7 = new StringBuilder();
                for (var5_8 = 1; var5_8 < var6_6; ++var5_8) {
                    if (var5_8 > 1) {
                        var8_7.append(' ');
                    }
                    var8_7.append((String)var3_3.get(var5_8));
                }
                break;
            }
            case 0: {
                var3_3 = null;
                var2_2 = "";
                ** GOTO lbl25
            }
            case 1: {
                var2_2 = var3_3.get(0);
                var3_3 = null;
                ** GOTO lbl25
            }
        }
        var3_3 = var8_7.toString();
lbl25: // 3 sources:
        if (this.f == null) {
            this.a((String)var2_2, (String)var3_3, null, var7_5, var1_1, var4_4);
            return;
        }
        var8_7 = this.f.iterator();
        do {
            if (var8_7.hasNext()) continue;
            this.a((String)var2_2, (String)var3_3, null, var7_5, var1_1, var4_4);
            return;
        } while (l.a(var9_9 = (l)var8_7.next()) != null || l.b(var9_9) != null);
        l.a(var9_9, (String)var2_2);
        l.b(var9_9, (String)var3_3);
        l.a(var9_9, var4_4);
    }

    private void a(String string2) {
        if (this.k == null) {
            this.k = new ArrayList();
        }
        this.k.add(new j(string2));
    }

    private void a(String string2, int n2, String string3, boolean bl2) {
        if (this.j == null) {
            this.j = new ArrayList();
        }
        this.j.add(new p(string2, n2, string3, bl2));
    }

    private void a(String string2, String string3, String string4, String string5, int n2, boolean bl2) {
        if (this.f == null) {
            this.f = new ArrayList();
        }
        this.f.add(new l(string2, string3, string4, string5, n2, bl2));
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private void a(String object, Collection<String> object2) {
        boolean bl2;
        if (TextUtils.isEmpty((CharSequence)object)) {
            return;
        }
        Object object3 = object;
        if (object.startsWith("sip:")) {
            object3 = object.substring(4);
            if (object3.length() == 0) return;
        }
        int n2 = -1;
        Iterator iterator = null;
        object = null;
        if (object2 == null) {
            bl2 = false;
            n2 = -1;
            object = iterator;
        } else {
            iterator = object2.iterator();
            bl2 = false;
            while (iterator.hasNext()) {
                object2 = (String)iterator.next();
                String string2 = object2.toUpperCase();
                if (string2.equals((Object)"PREF")) {
                    bl2 = true;
                    continue;
                }
                if (string2.equals((Object)"HOME")) {
                    n2 = 1;
                    continue;
                }
                if (string2.equals((Object)"WORK")) {
                    n2 = 2;
                    continue;
                }
                if (n2 >= 0) continue;
                object = object2;
                if (string2.startsWith("X-")) {
                    object = object2.substring(2);
                }
                n2 = 0;
            }
        }
        int n3 = n2;
        if (n2 < 0) {
            n3 = 3;
        }
        this.a((String)object3, n3, (String)object, bl2);
    }

    private void a(String object, byte[] arrby, boolean bl2) {
        if (this.h == null) {
            this.h = new ArrayList(1);
        }
        object = new n((String)object, arrby, bl2);
        this.h.add((n)object);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(List<String> list) {
        int n2;
        if (!(TextUtils.isEmpty((CharSequence)this.b.h) && TextUtils.isEmpty((CharSequence)this.b.j) && TextUtils.isEmpty((CharSequence)this.b.i) && list != null && (n2 = list.size()) >= 1)) {
            return;
        }
        if (n2 > 3) {
            n2 = 3;
        }
        if (list.get(0).length() > 0) {
            int n3;
            block11 : {
                for (n3 = 1; n3 < n2; ++n3) {
                    if (list.get(n3).length() <= 0) continue;
                    n3 = 0;
                    break block11;
                }
                n3 = 1;
            }
            if (n3 != 0) {
                String[] arrstring = list.get(0).split(" ");
                n2 = arrstring.length;
                if (n2 == 3) {
                    this.b.h = arrstring[0];
                    this.b.j = arrstring[1];
                    this.b.i = arrstring[2];
                    return;
                }
                if (n2 == 2) {
                    this.b.h = arrstring[0];
                    this.b.i = arrstring[1];
                    return;
                }
                this.b.i = list.get(0);
                return;
            }
        }
        switch (n2) {
            default: {
                break;
            }
            case 3: {
                this.b.j = list.get(2);
            }
            case 2: {
                this.b.i = list.get(1);
            }
        }
        this.b.h = list.get(0);
    }

    private void a(List<? extends e> object, f f2) {
        if (object != null && object.size() > 0) {
            f2.a(object.get(0).a());
            object = object.iterator();
            while (object.hasNext()) {
                f2.a((e)object.next());
            }
            f2.c();
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(List<String> list, Map<String, Collection<String>> map) {
        int n2;
        int n3 = 5;
        this.b(map);
        if (list == null || (n2 = list.size()) < 1) {
            return;
        }
        if (n2 <= 5) {
            n3 = n2;
        }
        switch (n3) {
            default: {
                break;
            }
            case 5: {
                this.b.f = list.get(4);
            }
            case 4: {
                this.b.e = list.get(3);
            }
            case 3: {
                this.b.d = list.get(2);
            }
            case 2: {
                this.b.c = list.get(1);
            }
        }
        this.b.b = list.get(0);
    }

    private void b(int n2, String string2, String string3, boolean bl2) {
        if (this.d == null) {
            this.d = new ArrayList();
        }
        this.d.add(new d(string2, n2, string3, bl2));
    }

    private void b(String string2) {
        if (this.f == null) {
            this.a(null, null, string2, null, 1, false);
            return;
        }
        for (l l2 : this.f) {
            if (l2.c != null) continue;
            l2.c = string2;
            return;
        }
        this.a(null, null, string2, null, 1, false);
    }

    private void b(List<String> list) {
        if (this.m == null) {
            this.m = new ArrayList();
        }
        this.m.add(a.a(list));
    }

    /*
     * Enabled aggressive block sorting
     */
    private void b(Map<String, Collection<String>> collection) {
        int n2;
        if (aex.b(this.p) && (!TextUtils.isEmpty((CharSequence)this.b.h) || !TextUtils.isEmpty((CharSequence)this.b.j) || !TextUtils.isEmpty((CharSequence)this.b.i)) || (collection = (Collection)collection.get("SORT-AS")) == null || collection.size() == 0) {
            return;
        }
        if (collection.size() > 1) {
            Log.w((String)"vCard", (String)("Incorrect multiple SORT_AS parameters detected: " + Arrays.toString((Object[])collection.toArray())));
        }
        collection = afl.a((String)collection.iterator().next(), this.p);
        int n3 = n2 = collection.size();
        if (n2 > 3) {
            n3 = 3;
        }
        switch (n3) {
            default: {
                break;
            }
            case 3: {
                this.b.j = (String)collection.get(2);
            }
            case 2: {
                this.b.i = (String)collection.get(1);
            }
        }
        this.b.h = (String)collection.get(0);
    }

    private String c(List<String> object) {
        int n2 = object.size();
        if (n2 > 1) {
            StringBuilder stringBuilder = new StringBuilder();
            object = object.iterator();
            while (object.hasNext()) {
                stringBuilder.append((String)object.next());
                if (n2 - 1 >= 0) continue;
                stringBuilder.append(";");
            }
            return stringBuilder.toString();
        }
        if (n2 == 1) {
            return object.get(0);
        }
        return "";
    }

    private void c(String string2) {
        if (this.l == null) {
            this.l = new ArrayList(1);
        }
        this.l.add(new k(string2));
    }

    /*
     * Enabled aggressive block sorting
     */
    private String l() {
        String string2;
        String string3 = null;
        if (!TextUtils.isEmpty((CharSequence)this.b.g)) {
            string2 = this.b.g;
        } else if (!this.b.b()) {
            string2 = afl.a(this.p, this.b.b, this.b.d, this.b.c, this.b.e, this.b.f);
        } else if (!this.b.c()) {
            string2 = afl.b(this.p, this.b.h, this.b.j, this.b.i);
        } else if (this.d != null && this.d.size() > 0) {
            string2 = this.d.get(0).a;
        } else if (this.c != null && this.c.size() > 0) {
            string2 = this.c.get(0).a;
        } else if (this.e != null && this.e.size() > 0) {
            string2 = this.e.get(0).a(this.p);
        } else {
            string2 = string3;
            if (this.f != null) {
                string2 = string3;
                if (this.f.size() > 0) {
                    string2 = this.f.get(0).b();
                }
            }
        }
        string3 = string2;
        if (string2 != null) return string3;
        return "";
    }

    public void a() {
        this.b.a = this.l();
    }

    public final void a(f f2) {
        f2.a();
        f2.a(this.b.a());
        f2.a(this.b);
        f2.c();
        this.a(this.c, f2);
        this.a(this.d, f2);
        this.a(this.e, f2);
        this.a(this.f, f2);
        this.a(this.g, f2);
        this.a(this.h, f2);
        this.a(this.i, f2);
        this.a(this.j, f2);
        this.a(this.k, f2);
        this.a(this.l, f2);
        this.a(this.m, f2);
        if (this.n != null) {
            f2.a(this.n.a());
            f2.a(this.n);
            f2.c();
        }
        if (this.o != null) {
            f2.a(this.o.a());
            f2.a(this.o);
            f2.c();
        }
        f2.b();
    }

    public void a(aey aey2) {
        if (this.r == null) {
            this.r = new ArrayList();
        }
        this.r.add(aey2);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public void a(afj var1_1) {
        var2_2 = -1;
        var17_3 = null;
        var14_4 = null;
        var16_5 = null;
        var13_6 = null;
        var15_7 = null;
        var7_8 = false;
        var8_9 = false;
        var9_10 = false;
        var6_11 = false;
        var10_12 = true;
        var11_13 = true;
        var5_14 = 1;
        var4_15 = 1;
        var21_16 = var1_1.a();
        var20_17 = var1_1.b();
        var18_18 = var1_1.d();
        var19_19 = var1_1.e();
        if ((var18_18 == null || var18_18.size() == 0) && var19_19 == null) {
            return;
        }
        var12_20 = var18_18 != null ? this.c(var18_18).trim() : null;
        if (var21_16.equals((Object)"VERSION") != false) return;
        if (var21_16.equals((Object)"FN")) {
            i.i(this.b, (String)var12_20);
            return;
        }
        if (var21_16.equals((Object)"NAME")) {
            if (TextUtils.isEmpty((CharSequence)i.d(this.b)) == false) return;
            i.i(this.b, (String)var12_20);
            return;
        }
        if (var21_16.equals((Object)"N")) {
            this.a(var18_18, var20_17);
            return;
        }
        if (var21_16.equals((Object)"SORT-STRING")) {
            i.j(this.b, (String)var12_20);
            return;
        }
        if (var21_16.equals((Object)"NICKNAME") || var21_16.equals((Object)"X-NICKNAME")) {
            this.a((String)var12_20);
            return;
        }
        if (var21_16.equals((Object)"SOUND")) {
            var1_1 = var20_17.get("TYPE");
            if (var1_1 == null) return;
            if (var1_1.contains("X-IRMC-N") == false) return;
            this.a(afl.a((String)var12_20, this.p));
            return;
        }
        if (!var21_16.equals((Object)"ADR")) ** GOTO lbl58
        var1_1 = var18_18.iterator();
        do {
            if (var1_1.hasNext() == false) return;
        } while (TextUtils.isEmpty((CharSequence)((String)var1_1.next())));
        var3_21 = false;
        if (var3_21 != false) return;
        var1_1 = var20_17.get("TYPE");
        if (var1_1 == null) ** GOTO lbl169
        var13_6 = var1_1.iterator();
        var6_11 = false;
        var1_1 = null;
        ** GOTO lbl173
lbl58: // 1 sources:
        if (!var21_16.equals((Object)"EMAIL")) ** GOTO lbl65
        var1_1 = var20_17.get("TYPE");
        if (var1_1 == null) ** GOTO lbl165
        var14_4 = var1_1.iterator();
        var6_11 = false;
        var1_1 = var15_7;
        ** GOTO lbl199
lbl65: // 1 sources:
        if (var21_16.equals((Object)"ORG")) {
            var1_1 = var20_17.get("TYPE");
            if (var1_1 != null) {
                var1_1 = var1_1.iterator();
                do {
                    var7_8 = var6_11;
                    if (!var1_1.hasNext()) break;
                    if (!((String)var1_1.next()).equals((Object)"PREF")) continue;
                    var6_11 = true;
                } while (true);
            }
            this.a(1, var18_18, var20_17, var7_8);
            return;
        }
        if (var21_16.equals((Object)"TITLE")) {
            this.b((String)var12_20);
            return;
        }
        if (var21_16.equals((Object)"ROLE") != false) return;
        if (!var21_16.equals((Object)"PHOTO") && !var21_16.equals((Object)"LOGO")) ** GOTO lbl89
        var1_1 = var20_17.get("VALUE");
        if (var1_1 != null) {
            if (var1_1.contains("URL") != false) return;
        }
        if ((var1_1 = var20_17.get("TYPE")) != null) ** GOTO lbl225
        var7_8 = false;
        var12_20 = var16_5;
        ** GOTO lbl239
lbl89: // 1 sources:
        if (var21_16.equals((Object)"TEL")) {
            if (aex.c(this.p)) {
                if (var12_20.startsWith("sip:")) {
                    var2_2 = 1;
                    var1_1 = null;
                } else if (var12_20.startsWith("tel:")) {
                    var1_1 = var12_20.substring(4);
                    var2_2 = 0;
                } else {
                    var2_2 = 0;
                    var1_1 = var12_20;
                }
            } else {
                var2_2 = 0;
                var1_1 = var12_20;
            }
            if (var2_2 != 0) {
                this.a((String)var12_20, var20_17.get("TYPE"));
                return;
            }
            if (var12_20.length() == 0) return;
            var14_4 = var20_17.get("TYPE");
            var12_20 = afl.a(var14_4, (String)var1_1);
            if (var12_20 instanceof Integer) {
                var2_2 = (Integer)var12_20;
                var12_20 = var13_6;
            } else {
                var12_20 = var12_20.toString();
                var2_2 = 0;
            }
            var6_11 = var14_4 != null && var14_4.contains("PREF") != false ? var10_12 : false;
            this.a(var2_2, (String)var1_1, (String)var12_20, var6_11);
            return;
        }
        if (var21_16.equals((Object)"X-SKYPE-PSTNNUMBER")) {
            var1_1 = var20_17.get("TYPE");
            var6_11 = var1_1 != null && var1_1.contains("PREF") != false ? var11_13 : false;
            this.a(7, (String)var12_20, null, var6_11);
            return;
        }
        if (!aey.a.containsKey(var21_16)) ** GOTO lbl131
        var4_15 = aey.a.get(var21_16);
        var1_1 = var20_17.get("TYPE");
        var3_23 = var2_2;
        var7_8 = var9_10;
        if (var1_1 == null) ** GOTO lbl258
        ** GOTO lbl241
lbl131: // 1 sources:
        if (var21_16.equals((Object)"NOTE")) {
            this.c((String)var12_20);
            return;
        }
        if (var21_16.equals((Object)"URL")) {
            if (this.i == null) {
                this.i = new ArrayList(1);
            }
            this.i.add(new r((String)var12_20));
            return;
        }
        if (var21_16.equals((Object)"BDAY")) {
            this.n = new c((String)var12_20);
            return;
        }
        if (var21_16.equals((Object)"ANNIVERSARY")) {
            this.o = new b((String)var12_20);
            return;
        }
        if (var21_16.equals((Object)"X-PHONETIC-FIRST-NAME")) {
            i.b(this.b, var12_20);
            return;
        }
        if (var21_16.equals((Object)"X-PHONETIC-MIDDLE-NAME")) {
            i.a(this.b, var12_20);
            return;
        }
        if (var21_16.equals((Object)"X-PHONETIC-LAST-NAME")) {
            i.c(this.b, var12_20);
            return;
        }
        if (var21_16.equals((Object)"IMPP")) {
            if (var12_20.startsWith("sip:") == false) return;
            this.a((String)var12_20, var20_17.get("TYPE"));
            return;
        }
        if (var21_16.equals((Object)"X-SIP")) {
            if (TextUtils.isEmpty(var12_20) != false) return;
            this.a((String)var12_20, var20_17.get("TYPE"));
            return;
        }
        if (var21_16.equals((Object)"X-ANDROID-CUSTOM") == false) return;
        this.b(afl.a(var12_20, this.p));
        return;
lbl165: // 1 sources:
        var6_11 = false;
        var2_2 = -1;
        var1_1 = var17_3;
        ** GOTO lbl220
lbl169: // 1 sources:
        var6_11 = false;
        var1_1 = null;
        var2_2 = -1;
        ** GOTO lbl195
lbl173: // 7 sources:
        while (var13_6.hasNext()) {
            var12_20 = (String)var13_6.next();
            var14_4 = var12_20.toUpperCase();
            if (var14_4.equals((Object)"PREF")) {
                var6_11 = true;
                continue;
            }
            if (var14_4.equals((Object)"HOME")) {
                var2_2 = 1;
                var1_1 = null;
                continue;
            }
            if (var14_4.equals((Object)"WORK") || var14_4.equalsIgnoreCase("COMPANY")) {
                var2_2 = 2;
                var1_1 = null;
                continue;
            }
            if (var14_4.equals((Object)"PARCEL") || var14_4.equals((Object)"DOM") || var14_4.equals((Object)"INTL") || var2_2 >= 0) continue;
            if (var14_4.startsWith("X-")) {
                var1_1 = var12_20.substring(2);
                var2_2 = 0;
                continue;
            }
            var2_2 = 0;
            var1_1 = var12_20;
        }
lbl195: // 2 sources:
        if (var2_2 < 0) {
            var2_2 = var4_15;
        }
        this.a(var2_2, var18_18, (String)var1_1, var6_11);
        return;
lbl199: // 7 sources:
        while (var14_4.hasNext()) {
            var13_6 = var14_4.next();
            var15_7 = var13_6.toUpperCase();
            if (var15_7.equals((Object)"PREF")) {
                var6_11 = true;
                continue;
            }
            if (var15_7.equals((Object)"HOME")) {
                var2_2 = 1;
                continue;
            }
            if (var15_7.equals((Object)"WORK")) {
                var2_2 = 2;
                continue;
            }
            if (var15_7.equals((Object)"CELL")) {
                var2_2 = 4;
                continue;
            }
            if (var2_2 >= 0) continue;
            var1_1 = var13_6;
            if (var15_7.startsWith("X-")) {
                var1_1 = var13_6.substring(2);
            }
            var2_2 = 0;
        }
lbl220: // 2 sources:
        var3_22 = var2_2;
        if (var2_2 < 0) {
            var3_22 = 3;
        }
        this.b(var3_22, (String)var12_20, (String)var1_1, var6_11);
        return;
lbl225: // 1 sources:
        var13_6 = var1_1.iterator();
        var6_11 = false;
        var1_1 = var14_4;
        do {
            var7_8 = var6_11;
            var12_20 = var1_1;
            if (!var13_6.hasNext()) break;
            var12_20 = var13_6.next();
            if ("PREF".equals(var12_20)) {
                var6_11 = true;
                continue;
            }
            if (var1_1 != null) continue;
            var1_1 = var12_20;
        } while (true);
lbl239: // 2 sources:
        this.a((String)var12_20, var19_19, var7_8);
        return;
lbl241: // 1 sources:
        var1_1 = var1_1.iterator();
        var6_11 = var8_9;
        do {
            var3_23 = var2_2;
            var7_8 = var6_11;
            if (!var1_1.hasNext()) break;
            var13_6 = (String)var1_1.next();
            if (var13_6.equals((Object)"PREF")) {
                var6_11 = true;
                continue;
            }
            if (var2_2 >= 0) continue;
            if (var13_6.equalsIgnoreCase("HOME")) {
                var2_2 = 1;
                continue;
            }
            if (!var13_6.equalsIgnoreCase("WORK")) continue;
            var2_2 = 2;
        } while (true);
lbl258: // 2 sources:
        var2_2 = var3_23 < 0 ? var5_14 : var3_23;
        this.a(var4_15, null, (String)var12_20, var2_2, var7_8);
    }

    public final List<j> b() {
        return this.k;
    }

    public final String c() {
        if (this.n != null) {
            return this.n.a;
        }
        return null;
    }

    public final List<k> d() {
        return this.l;
    }

    public final List<m> e() {
        return this.c;
    }

    public final List<d> f() {
        return this.d;
    }

    public final List<o> g() {
        return this.e;
    }

    public final List<l> h() {
        return this.f;
    }

    public final List<h> i() {
        return this.g;
    }

    public final List<r> j() {
        return this.i;
    }

    public String k() {
        if (this.b.a == null) {
            this.b.a = this.l();
        }
        return this.b.a;
    }

    public String toString() {
        q q2 = new q();
        this.a(q2);
        return q2.toString();
    }

    public static class a
    implements e {
        private final String a;
        private final List<String> b;

        public a(String string2, List<String> list) {
            this.a = string2;
            this.b = list;
        }

        /*
         * Enabled aggressive block sorting
         */
        public static a a(List<String> list) {
            String string2 = null;
            if (list == null) {
                list = null;
                return new a(string2, list);
            }
            if (list.size() < 2) {
                string2 = list.get(0);
                list = null;
                return new a(string2, list);
            }
            int n2 = list.size() < 16 ? list.size() : 16;
            string2 = list.get(0);
            list = list.subList(1, n2);
            return new a(string2, list);
        }

        @Override
        public g a() {
            return g.n;
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public boolean equals(Object object) {
            boolean bl2 = false;
            if (this == object) {
                return true;
            }
            boolean bl3 = bl2;
            if (!(object instanceof a)) return bl3;
            object = (a)object;
            bl3 = bl2;
            if (!TextUtils.equals((CharSequence)this.a, (CharSequence)object.a)) return bl3;
            if (this.b == null) {
                if (object.b != null) return false;
                return true;
            }
            int n2 = this.b.size();
            bl3 = bl2;
            if (n2 != object.b.size()) return bl3;
            int n3 = 0;
            while (n3 < n2) {
                bl3 = bl2;
                if (!TextUtils.equals((CharSequence)this.b.get(n3), (CharSequence)object.b.get(n3))) return bl3;
                ++n3;
            }
            return true;
        }

        /*
         * Enabled aggressive block sorting
         */
        public int hashCode() {
            int n2 = this.a != null ? this.a.hashCode() : 0;
            if (this.b == null) return n2;
            Iterator<String> iterator = this.b.iterator();
            do {
                int n3 = n2;
                if (!iterator.hasNext()) return n3;
                String string2 = iterator.next();
                n3 = string2 != null ? string2.hashCode() : 0;
                n2 = n3 + n2 * 31;
            } while (true);
        }

        /*
         * Enabled aggressive block sorting
         */
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("android-custom: " + this.a + ", data: ");
            String string2 = this.b == null ? "null" : Arrays.toString((Object[])this.b.toArray());
            stringBuilder.append(string2);
            return stringBuilder.toString();
        }
    }

    public static class b
    implements e {
        private final String a;

        public b(String string2) {
            this.a = string2;
        }

        @Override
        public g a() {
            return g.m;
        }

        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (!(object instanceof b)) {
                return false;
            }
            object = (b)object;
            return TextUtils.equals((CharSequence)this.a, (CharSequence)object.a);
        }

        public int hashCode() {
            if (this.a != null) {
                return this.a.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "anniversary: " + this.a;
        }
    }

    public static class c
    implements e {
        private final String a;

        public c(String string2) {
            this.a = string2;
        }

        @Override
        public g a() {
            return g.l;
        }

        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (!(object instanceof c)) {
                return false;
            }
            object = (c)object;
            return TextUtils.equals((CharSequence)this.a, (CharSequence)object.a);
        }

        public int hashCode() {
            if (this.a != null) {
                return this.a.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "birthday: " + this.a;
        }
    }

    public static class d
    implements e {
        private final String a;
        private final int b;
        private final String c;
        private final boolean d;

        public d(String string2, int n2, String string3, boolean bl2) {
            this.b = n2;
            this.a = string2;
            this.c = string3;
            this.d = bl2;
        }

        @Override
        public final g a() {
            return g.c;
        }

        public String b() {
            return this.a;
        }

        /*
         * Enabled aggressive block sorting
         * Lifted jumps to return sites
         */
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (!(object instanceof d)) {
                return false;
            }
            object = (d)object;
            if (this.b != object.b) return false;
            if (!TextUtils.equals((CharSequence)this.a, (CharSequence)object.a)) return false;
            if (!TextUtils.equals((CharSequence)this.c, (CharSequence)object.c)) return false;
            if (this.d == object.d) return true;
            return false;
        }

        /*
         * Enabled aggressive block sorting
         */
        public int hashCode() {
            int n2;
            int n3 = 0;
            int n4 = this.b;
            int n5 = this.a != null ? this.a.hashCode() : 0;
            if (this.c != null) {
                n3 = this.c.hashCode();
            }
            if (this.d) {
                n2 = 1231;
                return n2 + ((n5 + n4 * 31) * 31 + n3) * 31;
            }
            n2 = 1237;
            return n2 + ((n5 + n4 * 31) * 31 + n3) * 31;
        }

        public String toString() {
            return String.format((String)"type: %d, data: %s, label: %s, isPrimary: %s", (Object[])new Object[]{this.b, this.a, this.c, this.d});
        }
    }

    public static interface e {
        public g a();
    }

    public static interface f {
        public void a();

        public void a(g var1);

        public boolean a(e var1);

        public void b();

        public void c();
    }

    public static enum g {
        a,
        b,
        c,
        d,
        e,
        f,
        g,
        h,
        i,
        j,
        k,
        l,
        m,
        n;
        

        private g() {
        }
    }

    public static class h
    implements e {
        private final String a;
        private final int b;
        private final String c;
        private final int d;
        private final boolean e;

        public h(int n2, String string2, String string3, int n3, boolean bl2) {
            this.b = n2;
            this.c = string2;
            this.d = n3;
            this.a = string3;
            this.e = bl2;
        }

        @Override
        public final g a() {
            return g.f;
        }

        public String b() {
            return this.a;
        }

        /*
         * Enabled aggressive block sorting
         * Lifted jumps to return sites
         */
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (!(object instanceof h)) {
                return false;
            }
            object = (h)object;
            if (this.d != object.d) return false;
            if (this.b != object.b) return false;
            if (!TextUtils.equals((CharSequence)this.c, (CharSequence)object.c)) return false;
            if (!TextUtils.equals((CharSequence)this.a, (CharSequence)object.a)) return false;
            if (this.e == object.e) return true;
            return false;
        }

        /*
         * Enabled aggressive block sorting
         */
        public int hashCode() {
            int n2;
            int n3 = 0;
            int n4 = this.d;
            int n5 = this.b;
            int n6 = this.c != null ? this.c.hashCode() : 0;
            if (this.a != null) {
                n3 = this.a.hashCode();
            }
            if (this.e) {
                n2 = 1231;
                return n2 + ((n6 + (n4 * 31 + n5) * 31) * 31 + n3) * 31;
            }
            n2 = 1237;
            return n2 + ((n6 + (n4 * 31 + n5) * 31) * 31 + n3) * 31;
        }

        public String toString() {
            return String.format((String)"type: %d, protocol: %d, custom_protcol: %s, data: %s, isPrimary: %s", (Object[])new Object[]{this.d, this.b, this.c, this.a, this.e});
        }
    }

    public static class i
    implements e {
        public String a;
        private String b;
        private String c;
        private String d;
        private String e;
        private String f;
        private String g;
        private String h;
        private String i;
        private String j;
        private String k;

        static /* synthetic */ String i(i i2, String string2) {
            i2.g = string2;
            return string2;
        }

        static /* synthetic */ String j(i i2, String string2) {
            i2.k = string2;
            return string2;
        }

        @Override
        public final g a() {
            return g.a;
        }

        public boolean b() {
            if (TextUtils.isEmpty((CharSequence)this.b) && TextUtils.isEmpty((CharSequence)this.c) && TextUtils.isEmpty((CharSequence)this.d) && TextUtils.isEmpty((CharSequence)this.e) && TextUtils.isEmpty((CharSequence)this.f)) {
                return true;
            }
            return false;
        }

        public boolean c() {
            if (TextUtils.isEmpty((CharSequence)this.h) && TextUtils.isEmpty((CharSequence)this.i) && TextUtils.isEmpty((CharSequence)this.j)) {
                return true;
            }
            return false;
        }

        /*
         * Enabled aggressive block sorting
         * Lifted jumps to return sites
         */
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (!(object instanceof i)) {
                return false;
            }
            object = (i)object;
            if (!TextUtils.equals((CharSequence)this.b, (CharSequence)object.b)) return false;
            if (!TextUtils.equals((CharSequence)this.d, (CharSequence)object.d)) return false;
            if (!TextUtils.equals((CharSequence)this.c, (CharSequence)object.c)) return false;
            if (!TextUtils.equals((CharSequence)this.e, (CharSequence)object.e)) return false;
            if (!TextUtils.equals((CharSequence)this.f, (CharSequence)object.f)) return false;
            if (!TextUtils.equals((CharSequence)this.g, (CharSequence)object.g)) return false;
            if (!TextUtils.equals((CharSequence)this.h, (CharSequence)object.h)) return false;
            if (!TextUtils.equals((CharSequence)this.j, (CharSequence)object.j)) return false;
            if (!TextUtils.equals((CharSequence)this.i, (CharSequence)object.i)) return false;
            if (TextUtils.equals((CharSequence)this.k, (CharSequence)object.k)) return true;
            return false;
        }

        /*
         * Enabled aggressive block sorting
         */
        public int hashCode() {
            String[] arrstring = new String[]{this.b, this.d, this.c, this.e, this.f, this.g, this.h, this.j, this.i, this.k};
            int n2 = arrstring.length;
            int n3 = 0;
            int n4 = 0;
            while (n3 < n2) {
                String string2 = arrstring[n3];
                int n5 = string2 != null ? string2.hashCode() : 0;
                ++n3;
                n4 = n4 * 31 + n5;
            }
            return n4;
        }

        public String toString() {
            return String.format((String)"family: %s, given: %s, middle: %s, prefix: %s, suffix: %s", (Object[])new Object[]{this.b, this.c, this.d, this.e, this.f});
        }
    }

    public static class j
    implements e {
        private final String a;

        public j(String string2) {
            this.a = string2;
        }

        @Override
        public g a() {
            return g.j;
        }

        public String b() {
            return this.a;
        }

        public boolean equals(Object object) {
            if (!(object instanceof j)) {
                return false;
            }
            object = (j)object;
            return TextUtils.equals((CharSequence)this.a, (CharSequence)object.a);
        }

        public int hashCode() {
            if (this.a != null) {
                return this.a.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "nickname: " + this.a;
        }
    }

    public static class k
    implements e {
        public final String a;

        public k(String string2) {
            this.a = string2;
        }

        @Override
        public g a() {
            return g.k;
        }

        public String b() {
            return this.a;
        }

        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (!(object instanceof k)) {
                return false;
            }
            object = (k)object;
            return TextUtils.equals((CharSequence)this.a, (CharSequence)object.a);
        }

        public int hashCode() {
            if (this.a != null) {
                return this.a.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "note: " + this.a;
        }
    }

    public static class l
    implements e {
        private String a;
        private String b;
        private String c;
        private final String d;
        private final int e;
        private boolean f;

        public l(String string2, String string3, String string4, String string5, int n2, boolean bl2) {
            this.e = n2;
            this.a = string2;
            this.b = string3;
            this.c = string4;
            this.d = string5;
            this.f = bl2;
        }

        static /* synthetic */ String a(l l2) {
            return l2.a;
        }

        static /* synthetic */ String a(l l2, String string2) {
            l2.a = string2;
            return string2;
        }

        static /* synthetic */ boolean a(l l2, boolean bl2) {
            l2.f = bl2;
            return bl2;
        }

        static /* synthetic */ String b(l l2) {
            return l2.b;
        }

        static /* synthetic */ String b(l l2, String string2) {
            l2.b = string2;
            return string2;
        }

        @Override
        public final g a() {
            return g.e;
        }

        public String b() {
            StringBuilder stringBuilder = new StringBuilder();
            if (!TextUtils.isEmpty((CharSequence)this.a)) {
                stringBuilder.append(this.a);
            }
            if (!TextUtils.isEmpty((CharSequence)this.b)) {
                if (stringBuilder.length() > 0) {
                    stringBuilder.append(", ");
                }
                stringBuilder.append(this.b);
            }
            if (!TextUtils.isEmpty((CharSequence)this.c)) {
                if (stringBuilder.length() > 0) {
                    stringBuilder.append(", ");
                }
                stringBuilder.append(this.c);
            }
            return stringBuilder.toString();
        }

        /*
         * Enabled aggressive block sorting
         * Lifted jumps to return sites
         */
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (!(object instanceof l)) {
                return false;
            }
            object = (l)object;
            if (this.e != object.e) return false;
            if (!TextUtils.equals((CharSequence)this.a, (CharSequence)object.a)) return false;
            if (!TextUtils.equals((CharSequence)this.b, (CharSequence)object.b)) return false;
            if (!TextUtils.equals((CharSequence)this.c, (CharSequence)object.c)) return false;
            if (this.f == object.f) return true;
            return false;
        }

        /*
         * Enabled aggressive block sorting
         */
        public int hashCode() {
            int n2;
            int n3 = 0;
            int n4 = this.e;
            int n5 = this.a != null ? this.a.hashCode() : 0;
            int n6 = this.b != null ? this.b.hashCode() : 0;
            if (this.c != null) {
                n3 = this.c.hashCode();
            }
            if (this.f) {
                n2 = 1231;
                return n2 + ((n6 + (n5 + n4 * 31) * 31) * 31 + n3) * 31;
            }
            n2 = 1237;
            return n2 + ((n6 + (n5 + n4 * 31) * 31) * 31 + n3) * 31;
        }

        public String toString() {
            return String.format((String)"type: %d, organization: %s, department: %s, title: %s, isPrimary: %s", (Object[])new Object[]{this.e, this.a, this.b, this.c, this.f});
        }
    }

    public static class m
    implements e {
        private final String a;
        private final int b;
        private final String c;
        private boolean d;

        public m(String string2, int n2, String string3, boolean bl2) {
            this.a = string2;
            this.b = n2;
            this.c = string3;
            this.d = bl2;
        }

        @Override
        public final g a() {
            return g.b;
        }

        public String b() {
            return this.a;
        }

        /*
         * Enabled aggressive block sorting
         * Lifted jumps to return sites
         */
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (!(object instanceof m)) {
                return false;
            }
            object = (m)object;
            if (this.b != object.b) return false;
            if (!TextUtils.equals((CharSequence)this.a, (CharSequence)object.a)) return false;
            if (!TextUtils.equals((CharSequence)this.c, (CharSequence)object.c)) return false;
            if (this.d == object.d) return true;
            return false;
        }

        /*
         * Enabled aggressive block sorting
         */
        public int hashCode() {
            int n2;
            int n3 = 0;
            int n4 = this.b;
            int n5 = this.a != null ? this.a.hashCode() : 0;
            if (this.c != null) {
                n3 = this.c.hashCode();
            }
            if (this.d) {
                n2 = 1231;
                return n2 + ((n5 + n4 * 31) * 31 + n3) * 31;
            }
            n2 = 1237;
            return n2 + ((n5 + n4 * 31) * 31 + n3) * 31;
        }

        public String toString() {
            return String.format((String)"type: %d, data: %s, label: %s, isPrimary: %s", (Object[])new Object[]{this.b, this.a, this.c, this.d});
        }
    }

    public static class n
    implements e {
        private final String a;
        private final boolean b;
        private final byte[] c;
        private Integer d = null;

        public n(String string2, byte[] arrby, boolean bl2) {
            this.a = string2;
            this.c = arrby;
            this.b = bl2;
        }

        @Override
        public final g a() {
            return g.g;
        }

        /*
         * Enabled aggressive block sorting
         * Lifted jumps to return sites
         */
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (!(object instanceof n)) {
                return false;
            }
            object = (n)object;
            if (!TextUtils.equals((CharSequence)this.a, (CharSequence)object.a)) return false;
            if (!Arrays.equals((byte[])this.c, (byte[])object.c)) return false;
            if (this.b == object.b) return true;
            return false;
        }

        /*
         * Enabled aggressive block sorting
         */
        public int hashCode() {
            int n2 = 0;
            if (this.d != null) {
                return this.d;
            }
            int n3 = this.a != null ? this.a.hashCode() : 0;
            int n4 = n3 *= 31;
            if (this.c != null) {
                byte[] arrby = this.c;
                int n5 = arrby.length;
                do {
                    n4 = n3;
                    if (n2 >= n5) break;
                    n3 += arrby[n2];
                    ++n2;
                } while (true);
            }
            n3 = this.b ? 1231 : 1237;
            this.d = n3 += n4 * 31;
            return n3;
        }

        public String toString() {
            return String.format((String)"format: %s: size: %d, isPrimary: %s", (Object[])new Object[]{this.a, this.c.length, this.b});
        }
    }

    public static class o
    implements e {
        private final String a;
        private final String b;
        private final String c;
        private final String d;
        private final String e;
        private final String f;
        private final String g;
        private final int h;
        private final String i;
        private boolean j;
        private int k;

        public o(String string2, String string3, String string4, String string5, String string6, String string7, String string8, int n2, String string9, boolean bl2, int n3) {
            this.h = n2;
            this.a = string2;
            this.b = string3;
            this.c = string4;
            this.d = string5;
            this.e = string6;
            this.f = string7;
            this.g = string8;
            this.i = string9;
            this.j = bl2;
            this.k = n3;
        }

        public static o a(List<String> object, int n2, String string2, boolean bl2, int n3) {
            String[] arrstring = new String[7];
            int n4 = object.size();
            if (n4 > 7) {
                n4 = 7;
            }
            object = object.iterator();
            int n5 = 0;
            while (object.hasNext()) {
                arrstring[n5] = (String)object.next();
                if (++n5 < n4) continue;
            }
            while (n5 < 7) {
                arrstring[n5] = null;
                ++n5;
            }
            return new o(arrstring[0], arrstring[1], arrstring[2], arrstring[3], arrstring[4], arrstring[5], arrstring[6], n2, string2, bl2, n3);
        }

        @Override
        public final g a() {
            return g.d;
        }

        /*
         * Enabled aggressive block sorting
         */
        public String a(int n2) {
            int n3;
            int n4 = 1;
            int n5 = 1;
            StringBuilder stringBuilder = new StringBuilder();
            String[] arrstring = new String[]{this.a, this.b, this.c, this.d, this.e, this.f, this.g};
            if (!aex.e(n2)) {
                n2 = n4;
            } else {
                n2 = n5;
                for (n3 = 6; n3 >= 0; --n3) {
                    String string2 = arrstring[n3];
                    n4 = n2;
                    if (!TextUtils.isEmpty((CharSequence)string2)) {
                        if (n2 == 0) {
                            stringBuilder.append(' ');
                        } else {
                            n2 = 0;
                        }
                        stringBuilder.append(string2);
                        n4 = n2;
                    }
                    n2 = n4;
                }
                return stringBuilder.toString().trim();
            }
            for (n3 = 0; n3 < 7; ++n3) {
                String string3 = arrstring[n3];
                n4 = n2;
                if (!TextUtils.isEmpty((CharSequence)string3)) {
                    if (n2 == 0) {
                        stringBuilder.append(' ');
                    } else {
                        n2 = 0;
                    }
                    stringBuilder.append(string3);
                    n4 = n2;
                }
                n2 = n4;
            }
            return stringBuilder.toString().trim();
        }

        /*
         * Enabled aggressive block sorting
         * Lifted jumps to return sites
         */
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (!(object instanceof o)) {
                return false;
            }
            object = (o)object;
            if (this.h != object.h) return false;
            if (this.h == 0) {
                if (!TextUtils.equals((CharSequence)this.i, (CharSequence)object.i)) return false;
            }
            if (this.j != object.j) return false;
            if (!TextUtils.equals((CharSequence)this.a, (CharSequence)object.a)) return false;
            if (!TextUtils.equals((CharSequence)this.b, (CharSequence)object.b)) return false;
            if (!TextUtils.equals((CharSequence)this.c, (CharSequence)object.c)) return false;
            if (!TextUtils.equals((CharSequence)this.d, (CharSequence)object.d)) return false;
            if (!TextUtils.equals((CharSequence)this.e, (CharSequence)object.e)) return false;
            if (!TextUtils.equals((CharSequence)this.f, (CharSequence)object.f)) return false;
            if (TextUtils.equals((CharSequence)this.g, (CharSequence)object.g)) return true;
            return false;
        }

        /*
         * Enabled aggressive block sorting
         */
        public int hashCode() {
            int n2 = this.h;
            int n3 = this.i != null ? this.i.hashCode() : 0;
            int n4 = this.j ? 1231 : 1237;
            n3 = n4 + (n3 + n2 * 31) * 31;
            String[] arrstring = new String[]{this.a, this.b, this.c, this.d, this.e, this.f, this.g};
            int n5 = arrstring.length;
            n4 = 0;
            while (n4 < n5) {
                String string2 = arrstring[n4];
                n2 = string2 != null ? string2.hashCode() : 0;
                ++n4;
                n3 = n3 * 31 + n2;
            }
            return n3;
        }

        public String toString() {
            return String.format((String)"type: %d, label: %s, isPrimary: %s, pobox: %s, extendedAddress: %s, street: %s, localty: %s, region: %s, postalCode %s, country: %s", (Object[])new Object[]{this.h, this.i, this.j, this.a, this.b, this.c, this.d, this.e, this.f, this.g});
        }
    }

    public static class p
    implements e {
        private final String a;
        private final int b;
        private final String c;
        private final boolean d;

        /*
         * Enabled aggressive block sorting
         */
        public p(String string2, int n2, String string3, boolean bl2) {
            this.a = string2.startsWith("sip:") ? string2.substring(4) : string2;
            this.b = n2;
            this.c = string3;
            this.d = bl2;
        }

        @Override
        public g a() {
            return g.i;
        }

        /*
         * Enabled aggressive block sorting
         * Lifted jumps to return sites
         */
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (!(object instanceof p)) {
                return false;
            }
            object = (p)object;
            if (this.b != object.b) return false;
            if (!TextUtils.equals((CharSequence)this.c, (CharSequence)object.c)) return false;
            if (!TextUtils.equals((CharSequence)this.a, (CharSequence)object.a)) return false;
            if (this.d == object.d) return true;
            return false;
        }

        /*
         * Enabled aggressive block sorting
         */
        public int hashCode() {
            int n2;
            int n3 = 0;
            int n4 = this.b;
            int n5 = this.c != null ? this.c.hashCode() : 0;
            if (this.a != null) {
                n3 = this.a.hashCode();
            }
            if (this.d) {
                n2 = 1231;
                return n2 + ((n5 + n4 * 31) * 31 + n3) * 31;
            }
            n2 = 1237;
            return n2 + ((n5 + n4 * 31) * 31 + n3) * 31;
        }

        public String toString() {
            return "sip: " + this.a;
        }
    }

    class q
    implements f {
        private StringBuilder b;
        private boolean c;

        private q() {
        }

        @Override
        public void a() {
            this.b = new StringBuilder();
            this.b.append("[[hash: " + aey.this.hashCode() + "\n");
        }

        @Override
        public void a(g g2) {
            this.b.append(g2.toString() + ": ");
            this.c = true;
        }

        @Override
        public boolean a(e e2) {
            if (!this.c) {
                this.b.append(", ");
                this.c = false;
            }
            this.b.append("[").append(e2.toString()).append("]");
            return true;
        }

        @Override
        public void b() {
            this.b.append("]]\n");
        }

        @Override
        public void c() {
            this.b.append("\n");
        }

        public String toString() {
            return this.b.toString();
        }
    }

    public static class r
    implements e {
        private final String a;

        public r(String string2) {
            this.a = string2;
        }

        @Override
        public g a() {
            return g.h;
        }

        public String b() {
            return this.a;
        }

        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (!(object instanceof r)) {
                return false;
            }
            object = (r)object;
            return TextUtils.equals((CharSequence)this.a, (CharSequence)object.a);
        }

        public int hashCode() {
            if (this.a != null) {
                return this.a.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "website: " + this.a;
        }
    }

}

