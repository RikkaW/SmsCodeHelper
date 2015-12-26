/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Double
 *  java.lang.Float
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 */
public class az
implements ah {
    public static final ai a = ai.g;

    @Override
    public m a(int n2, l[] object) {
        if (object == null) {
            throw new IllegalArgumentException("\u8fd0\u7b97\u64cd\u4f5c\u7b26\u53c2\u6570\u4e3a\u7a7a");
        }
        if (object.length != 2) {
            throw new i("\u64cd\u4f5c\u7b26\"" + a.a() + "\"\u53c2\u6570\u4e2a\u6570\u4e0d\u5339\u914d", a.a(), n2);
        }
        l l2 = object[1];
        object = object[0];
        if (l2 == null || object == null) {
            throw new NullPointerException("\u64cd\u4f5c\u7b26\"" + a.a() + "\"\u53c2\u6570\u4e3a\u7a7a");
        }
        if (l.a.i == l2.a() || l.a.i == object.a()) {
            throw new i("\u64cd\u4f5c\u7b26\"" + a.a() + "\"\u53c2\u6570\u7c7b\u578b\u9519\u8bef", a.a(), n2);
        }
        if (l.a.b == l2.a() || l.a.b == object.a() || l.a.a == l2.a() || l.a.a == object.a() || l.a.c == l2.a() || l.a.c == object.a() || l.a.h == l2.a() || l.a.h == object.a()) {
            return new m(l.a.b, null);
        }
        if (l.a.g == l2.a() || l.a.g == object.a()) {
            return new m(l.a.g, 0.0);
        }
        if (l.a.f == l2.a() || l.a.f == object.a()) {
            return new m(l.a.f, (Object)Float.valueOf((float)0.0f));
        }
        if (l.a.e == l2.a() || l.a.e == object.a()) {
            return new m(l.a.e, 0);
        }
        return new m(l.a.d, 0);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public m a(m[] object) {
        if (object == null || object.length != 2) {
            throw new IllegalArgumentException("\u64cd\u4f5c\u7b26\"" + a.a() + "\u53c2\u6570\u4e2a\u6570\u4e0d\u5339\u914d");
        }
        Object object2 = object[1];
        Object object3 = object[0];
        if (object2 == null || object3 == null) {
            throw new NullPointerException("\u64cd\u4f5c\u7b26\"" + a.a() + "\"\u53c2\u6570\u4e3a\u7a7a");
        }
        object = object2.p() ? ((n)object2.b()).b() : object2;
        if (object3.p()) {
            object3 = ((n)object3.b()).b();
        }
        if (l.a.i == object.a() || l.a.i == object3.a()) {
            throw new IllegalArgumentException("\u64cd\u4f5c\u7b26\"" + a.a() + "\"\u53c2\u6570\u7c7b\u578b\u9519\u8bef");
        }
        if (l.a.b == object.a() || l.a.b == object3.a() || l.a.a == object.a() || l.a.a == object3.a() || l.a.c == object.a() || l.a.c == object3.a() || l.a.h == object.a() || l.a.h == object3.a()) {
            object = object.d() != null ? object.d() : "";
            object3 = object3.d() != null ? object3.d() : "";
            object = String.valueOf((Object)object) + (String)object3;
            return new m(l.a.b, object);
        }
        if (object.b() == null || object3.b() == null) {
            throw new NullPointerException("\u64cd\u4f5c\u7b26\"" + a.a() + "\"\u53c2\u6570\u4e3a\u7a7a");
        }
        if (l.a.g == object.a() || l.a.g == object3.a()) {
            double d2 = object.i();
            double d3 = object3.i();
            return new m(l.a.g, d3 + d2);
        }
        if (l.a.f == object.a() || l.a.f == object3.a()) {
            float f2 = object.h().floatValue();
            float f3 = object3.h().floatValue();
            return new m(l.a.f, (Object)Float.valueOf((float)(f3 + f2)));
        }
        if (l.a.e != object.a() && l.a.e != object3.a()) {
            int n2 = object.f();
            int n3 = object3.f();
            return new m(l.a.d, n3 + n2);
        }
        long l2 = object.g();
        long l3 = object3.g();
        return new m(l.a.e, l3 + l2);
    }
}

