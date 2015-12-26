/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Boolean
 *  java.lang.Double
 *  java.lang.Object
 *  java.lang.String
 */
public class an
implements ah {
    public static final ai a = ai.o;

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
        if (l.a.a == l2.a() || l.a.a == object.a()) {
            return new m(l.a.c, (Object)Boolean.FALSE);
        }
        if (l.a.c == l2.a() && l.a.c == object.a()) {
            return new m(l.a.c, (Object)Boolean.FALSE);
        }
        if (l.a.h == l2.a() && l.a.h == object.a()) {
            return new m(l.a.c, (Object)Boolean.FALSE);
        }
        if (l.a.b == l2.a() && l.a.b == object.a()) {
            return new m(l.a.c, (Object)Boolean.FALSE);
        }
        if (!(l.a.g != l2.a() && l.a.f != l2.a() && l.a.e != l2.a() && l.a.d != l2.a() || l.a.g != object.a() && l.a.f != object.a() && l.a.e != object.a() && l.a.d != object.a())) {
            return new m(l.a.c, (Object)Boolean.FALSE);
        }
        if (l.a.j == l2.a() && l.a.j == object.a()) {
            return new m(l.a.c, (Object)Boolean.FALSE);
        }
        throw new i("\u64cd\u4f5c\u7b26\"" + a.a() + "\"\u53c2\u6570\u7c7b\u578b\u9519\u8bef", a.a(), n2);
    }

    @Override
    public m a(m[] object) {
        if (object == null || object.length != 2) {
            throw new IllegalArgumentException("\u64cd\u4f5c\u7b26\"" + a.a() + "\u53c2\u6570\u4e2a\u6570\u4e0d\u5339\u914d");
        }
        Object object2 = object[1];
        object = object[0];
        if (object2 == null || object == null) {
            throw new NullPointerException("\u64cd\u4f5c\u7b26\"" + a.a() + "\"\u53c2\u6570\u4e3a\u7a7a");
        }
        if (object2.p()) {
            object2 = ((n)object2.b()).b();
        }
        if (object.p()) {
            object = ((n)object.b()).b();
        }
        if (l.a.i == object2.a() || l.a.i == object.a()) {
            throw new IllegalArgumentException("\u64cd\u4f5c\u7b26\"" + a.a() + "\"\u53c2\u6570\u7c7b\u578b\u9519\u8bef");
        }
        if (l.a.a == object2.a()) {
            if (object.b() == null) {
                return new m(l.a.c, (Object)Boolean.TRUE);
            }
            return new m(l.a.c, (Object)Boolean.FALSE);
        }
        if (l.a.a == object.a()) {
            if (object2.b() == null) {
                return new m(l.a.c, (Object)Boolean.TRUE);
            }
            return new m(l.a.c, (Object)Boolean.FALSE);
        }
        if (l.a.c == object2.a() && l.a.c == object.a()) {
            object2 = object2.e();
            object = object.e();
            if (object2 != null) {
                return new m(l.a.c, object2.equals(object));
            }
            if (object == null) {
                return new m(l.a.c, (Object)Boolean.TRUE);
            }
            return new m(l.a.c, (Object)Boolean.FALSE);
        }
        if (l.a.h == object2.a() && l.a.h == object.a()) {
            object2 = object2.c();
            object = object.c();
            if (object2 != null) {
                return new m(l.a.c, object2.equals(object));
            }
            if (object == null) {
                return new m(l.a.c, (Object)Boolean.TRUE);
            }
            return new m(l.a.c, (Object)Boolean.FALSE);
        }
        if (l.a.b == object2.a() && l.a.b == object.a()) {
            object2 = object2.d();
            object = object.d();
            if (object2 != null) {
                return new m(l.a.c, object2.equals(object));
            }
            if (object == null) {
                return new m(l.a.c, (Object)Boolean.TRUE);
            }
            return new m(l.a.c, (Object)Boolean.FALSE);
        }
        if (!(l.a.g != object2.a() && l.a.f != object2.a() && l.a.e != object2.a() && l.a.d != object2.a() || l.a.g != object.a() && l.a.f != object.a() && l.a.e != object.a() && l.a.d != object.a())) {
            object2 = object2.i();
            object = object.i();
            if (object2 != null && object != null) {
                if (Double.compare((double)object2.doubleValue(), (double)object.doubleValue()) == 0) {
                    return new m(l.a.c, (Object)Boolean.TRUE);
                }
                return new m(l.a.c, (Object)Boolean.FALSE);
            }
            if (object2 == null && object == null) {
                return new m(l.a.c, (Object)Boolean.TRUE);
            }
            return new m(l.a.c, (Object)Boolean.FALSE);
        }
        if (l.a.j == object2.a() && l.a.j == object.a()) {
            object2 = object2.b();
            object = object.b();
            if (object2 != null) {
                return new m(l.a.c, object2.equals(object));
            }
            if (object == null) {
                return new m(l.a.c, (Object)Boolean.TRUE);
            }
            return new m(l.a.c, (Object)Boolean.FALSE);
        }
        throw new IllegalArgumentException("\u64cd\u4f5c\u7b26\"" + a.a() + "\"\u53c2\u6570\u7c7b\u578b\u9519\u8bef");
    }
}

