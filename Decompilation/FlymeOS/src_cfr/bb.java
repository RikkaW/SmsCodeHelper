/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Boolean
 *  java.lang.Object
 *  java.lang.String
 */
public class bb
implements ah {
    public static final ai a = ai.v;

    @Override
    public m a(int n2, l[] object) {
        if (object == null) {
            throw new IllegalArgumentException("\u8fd0\u7b97\u64cd\u4f5c\u7b26\u53c2\u6570\u4e3a\u7a7a");
        }
        if (object.length != 3) {
            throw new i("\u64cd\u4f5c\u7b26\"" + a.a() + "\"\u53c2\u6570\u4e2a\u6570\u4e0d\u5339\u914d", a.a(), n2);
        }
        l l2 = object[2];
        l l3 = object[1];
        object = object[0];
        if (l2 == null || l3 == null || object == null) {
            throw new NullPointerException("\u64cd\u4f5c\u7b26\"" + a.a() + "\"\u53c2\u6570\u4e3a\u7a7a");
        }
        if (l.a.c != l2.a()) {
            throw new i("\u64cd\u4f5c\u7b26\"" + a.a() + "\"\u53c2\u6570\u7c7b\u578b\u9519\u8bef", a.a(), n2);
        }
        if ((object = l3.a((l)object)) != null) {
            return new m((l.a)((Object)object), null);
        }
        throw new i("\u64cd\u4f5c\u7b26\"" + a.a() + "\"\u4e8c\uff0c\u4e09\u53c2\u6570\u7c7b\u578b\u4e0d\u4e00\u81f4", a.a(), n2);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public m a(m[] object) {
        if (object == null || object.length != 3) {
            throw new IllegalArgumentException("\u64cd\u4f5c\u7b26\"" + a.a() + "\u64cd\u4f5c\u7f3a\u5c11\u53c2\u6570");
        }
        Object object2 = object[2];
        if (object2 == null || object2.b() == null) {
            throw new NullPointerException("\u64cd\u4f5c\u7b26\"" + a.a() + "\"\u7b2c\u4e00\u53c2\u6570\u4e3a\u7a7a");
        }
        Object object3 = object[1];
        if (object3 == null || object3.b() == null) {
            throw new NullPointerException("\u64cd\u4f5c\u7b26\"" + a.a() + "\"\u7b2c\u4e8c\u53c2\u6570\u4e3a\u7a7a");
        }
        Object object4 = object[0];
        if (object4 == null || object4.b() == null) {
            throw new NullPointerException("\u64cd\u4f5c\u7b26\"" + a.a() + "\"\u7b2c\u4e09\u53c2\u6570\u4e3a\u7a7a");
        }
        object = object2;
        if (object2.p()) {
            object = ((n)object2.b()).b();
        }
        if (l.a.c != object.a()) throw new IllegalArgumentException("\u64cd\u4f5c\u7b26\"" + a.a() + "\"\u7b2c\u4e00\u53c2\u6570\u7c7b\u578b\u9519\u8bef");
        object2 = object3.a((l)object4);
        if (!object.e().booleanValue()) {
            if (object4.p()) {
                object = ((n)object4.b()).b();
                do {
                    return new m((l.a)((Object)object2), object.b());
                    break;
                } while (true);
            }
            object = object4;
            return new m((l.a)((Object)object2), object.b());
        }
        if (object3.p()) {
            object = ((n)object3.b()).b();
            do {
                return new m((l.a)((Object)object2), object.b());
                break;
            } while (true);
        }
        object = object3;
        return new m((l.a)((Object)object2), object.b());
    }
}

