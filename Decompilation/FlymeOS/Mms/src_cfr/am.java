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
public class am
implements ah {
    public static final ai a = ai.e;

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
        if (l.a.a == l2.a() || l.a.a == object.a() || l.a.c == l2.a() || l.a.c == object.a() || l.a.h == l2.a() || l.a.h == object.a() || l.a.b == l2.a() || l.a.b == object.a() || l.a.i == l2.a() || l.a.i == object.a()) {
            throw new i("\u64cd\u4f5c\u7b26\"" + a.a() + "\"\u53c2\u6570\u7c7b\u578b\u9519\u8bef", a.a(), n2);
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
        if (object2 == null || object2.b() == null) {
            throw new NullPointerException("\u64cd\u4f5c\u7b26\"" + a.a() + "\"\u53c2\u6570\u4e3a\u7a7a");
        }
        Object object3 = object[0];
        if (object3 == null || object3.b() == null) {
            throw new NullPointerException("\u64cd\u4f5c\u7b26\"" + a.a() + "\"\u53c2\u6570\u4e3a\u7a7a");
        }
        object = object2.p() ? ((n)object2.b()).b() : object2;
        if (object3.p()) {
            object3 = ((n)object3.b()).b();
        }
        if (l.a.a == object.a() || l.a.a == object3.a() || l.a.c == object.a() || l.a.c == object3.a() || l.a.h == object.a() || l.a.h == object3.a() || l.a.b == object.a() || l.a.b == object3.a() || l.a.i == object.a() || l.a.i == object3.a()) {
            throw new IllegalArgumentException("\u64cd\u4f5c\u7b26\"" + a.a() + "\"\u53c2\u6570\u7c7b\u578b\u9519\u8bef");
        }
        if (Double.compare((double)object3.i(), (double)0.0) == 0) {
            throw new IllegalArgumentException("\u64cd\u4f5c\u7b26\"" + a.a() + "\"\u9664\u6570\u4e3a\u96f6");
        }
        if (l.a.g == object.a() || l.a.g == object3.a()) {
            double d2 = object.i() / object3.i();
            return new m(l.a.g, d2);
        }
        if (l.a.f == object.a() || l.a.f == object3.a()) {
            float f2 = object.h().floatValue() / object3.h().floatValue();
            return new m(l.a.f, (Object)Float.valueOf((float)f2));
        }
        if (l.a.e != object.a() && l.a.e != object3.a()) {
            int n2 = object.f() / object3.f();
            return new m(l.a.d, n2);
        }
        long l2 = object.g() / object3.g();
        return new m(l.a.e, l2);
    }
}

