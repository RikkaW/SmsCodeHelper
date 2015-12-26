/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Class
 *  java.lang.Enum
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.util.HashMap
 *  java.util.HashSet
 */
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public enum ai {
    a("!", 80, 1),
    b("~", 80, 1),
    c("-", 80, 1),
    d("*", 70, 2),
    e("/", 70, 2),
    f("%", 70, 2),
    g("+", 60, 2),
    h("-", 60, 2),
    i("&", 55, 2),
    j("|", 55, 2),
    k("<", 50, 2),
    l("<=", 50, 2),
    m(">", 50, 2),
    n(">=", 50, 2),
    o("==", 40, 2),
    p("!=", 40, 2),
    q("&&", 30, 2),
    r("||", 20, 2),
    s("#", 10, 2),
    t("?", 0, 0),
    u(":", 0, 0),
    v("?:", 0, 3);
    
    private static final Set<String> w;
    private static final HashMap<ai, ah> x;
    private int A;
    private String y;
    private int z;

    static {
        w = new HashSet();
        w.add(a.a());
        w.add(c.a());
        w.add(d.a());
        w.add(e.a());
        w.add(f.a());
        w.add(g.a());
        w.add(h.a());
        w.add(k.a());
        w.add(l.a());
        w.add(m.a());
        w.add(n.a());
        w.add(o.a());
        w.add(p.a());
        w.add(q.a());
        w.add(r.a());
        w.add(s.a());
        w.add(v.a());
        w.add(t.a());
        w.add(u.a());
        x = new HashMap();
        x.put((Object)a, (Object)new ax());
        x.put((Object)c, (Object)new aw());
        x.put((Object)d, (Object)new au());
        x.put((Object)e, (Object)new am());
        x.put((Object)f, (Object)new at());
        x.put((Object)g, (Object)new az());
        x.put((Object)h, (Object)new as());
        x.put((Object)k, (Object)new ar());
        x.put((Object)l, (Object)new aq());
        x.put((Object)m, (Object)new ap());
        x.put((Object)n, (Object)new ao());
        x.put((Object)o, (Object)new an());
        x.put((Object)p, (Object)new av());
        x.put((Object)q, (Object)new aj());
        x.put((Object)r, (Object)new ay());
        x.put((Object)s, (Object)new ak());
        x.put((Object)v, (Object)new bb());
        x.put((Object)t, (Object)new ba());
        x.put((Object)u, (Object)new al());
    }

    private ai(String string3, int n3, String string4, int n4, int n5) {
        this.y = string3;
        this.z = n3;
        this.A = string4;
    }

    public String a() {
        return this.y;
    }

    public m a(int n2, l[] arrl) {
        ah ah2 = (ah)x.get((Object)this);
        if (ah2 == null) {
            throw new IllegalStateException("\u7cfb\u7edf\u5185\u90e8\u9519\u8bef\uff1a\u627e\u4e0d\u5230\u64cd\u4f5c\u7b26\u5bf9\u5e94\u7684\u6267\u884c\u5b9a\u4e49");
        }
        return ah2.a(n2, arrl);
    }

    public m a(m[] arrm) {
        ah ah2 = (ah)x.get((Object)this);
        if (ah2 == null) {
            throw new IllegalStateException("\u7cfb\u7edf\u5185\u90e8\u9519\u8bef\uff1a\u627e\u4e0d\u5230\u64cd\u4f5c\u7b26\u5bf9\u5e94\u7684\u6267\u884c\u5b9a\u4e49");
        }
        return ah2.a(arrm);
    }

    public int b() {
        return this.z;
    }

    public int c() {
        return this.A;
    }
}

