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
public class aw
implements ah {
    public static final ai a = ai.c;

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
        if (l.a.g == object.a()) {
            return new m(l.a.g, 0.0);
        }
        if (l.a.f == object.a()) {
            return new m(l.a.f, (Object)Float.valueOf((float)0.0f));
        }
        if (l.a.e == object.a()) {
            return new m(l.a.e, 0);
        }
        if (l.a.d == object.a()) {
            return new m(l.a.d, 0);
        }
        throw new i("\u64cd\u4f5c\u7b26\"" + a.a() + "\"\u53c2\u6570\u7c7b\u578b\u9519\u8bef", a.a(), n2);
    }

    @Override
    public m a(m[] object) {
        if (object == null || object.length != 1) {
            throw new IllegalArgumentException("\u64cd\u4f5c\u7b26\"" + a.a() + "\u53c2\u6570\u4e2a\u6570\u4e0d\u5339\u914d");
        }
        m m2 = object[0];
        if (m2 == null || m2.b() == null) {
            throw new NullPointerException("\u64cd\u4f5c\u7b26\"" + a.a() + "\"\u53c2\u6570\u4e3a\u7a7a");
        }
        object = m2;
        if (m2.p()) {
            object = ((n)m2.b()).b();
        }
        if (l.a.g == object.a()) {
            double d2 = object.i();
            return new m(l.a.g, 0.0 - d2);
        }
        if (l.a.f == object.a()) {
            float f2 = object.h().floatValue();
            return new m(l.a.f, (Object)Float.valueOf((float)(0.0f - f2)));
        }
        if (l.a.e == object.a()) {
            long l2 = object.g();
            return new m(l.a.e, 0 - l2);
        }
        if (l.a.d == object.a()) {
            int n2 = object.f();
            return new m(l.a.d, 0 - n2);
        }
        throw new IllegalArgumentException("\u64cd\u4f5c\u7b26\"" + a.a() + "\"\u53c2\u6570\u7c7b\u578b\u9519\u8bef");
    }
}

