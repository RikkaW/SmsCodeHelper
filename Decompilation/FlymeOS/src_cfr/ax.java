/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Boolean
 *  java.lang.Object
 *  java.lang.String
 */
public class ax
implements ah {
    public static final ai a = ai.a;

    @Override
    public m a(int n2, l[] object) {
        if (object == null) {
            throw new IllegalArgumentException("\u8fd0\u7b97\u64cd\u4f5c\u7b26\u53c2\u6570\u4e3a\u7a7a");
        }
        if (object.length != 1) {
            throw new i("\u64cd\u4f5c\u7b26\"" + a.a() + "\"\u53c2\u6570\u4e2a\u6570\u4e0d\u5339\u914d", a.a(), n2);
        }
        if ((object = object[0]) == null) {
            throw new NullPointerException("\u64cd\u4f5c\u7b26\"" + a.a() + "\"\u53c2\u6570\u4e3a\u7a7a");
        }
        if (l.a.c == object.a()) {
            return new m(l.a.c, (Object)Boolean.FALSE);
        }
        throw new i("\u64cd\u4f5c\u7b26\"" + a.a() + "\"\u53c2\u6570\u7c7b\u578b\u9519\u8bef", a.a(), n2);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public m a(m[] object) {
        boolean bl2;
        if (object == null || object.length != 1) {
            throw new IllegalArgumentException("\u64cd\u4f5c\u7b26\"" + a.a() + "\u53c2\u6570\u4e2a\u6570\u4e0d\u5339\u914d");
        }
        Object object2 = object[0];
        if (object2 == null || object2.b() == null) {
            throw new NullPointerException("\u64cd\u4f5c\u7b26\"" + a.a() + "\"\u53c2\u6570\u4e3a\u7a7a");
        }
        object = object2;
        if (object2.p()) {
            object = ((n)object2.b()).b();
        }
        if (l.a.c != object.a()) throw new IllegalArgumentException("\u64cd\u4f5c\u7b26\"" + a.a() + "\"\u53c2\u6570\u7c7b\u578b\u9519\u8bef");
        if (object.e().booleanValue()) {
            bl2 = false;
            do {
                return new m(l.a.c, bl2);
                break;
            } while (true);
        }
        bl2 = true;
        return new m(l.a.c, bl2);
    }
}

