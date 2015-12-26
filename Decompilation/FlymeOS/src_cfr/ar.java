/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Boolean
 *  java.lang.Double
 *  java.lang.Object
 *  java.lang.String
 *  java.util.Date
 */
import java.util.Date;

public class ar
implements ah {
    public static final ai a = ai.k;

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
        if (l.a.h == l2.a() && l.a.h == object.a()) {
            return new m(l.a.c, (Object)Boolean.FALSE);
        }
        if (l.a.b == l2.a() && l.a.b == object.a()) {
            return new m(l.a.c, (Object)Boolean.FALSE);
        }
        if (!(l.a.g != l2.a() && l.a.f != l2.a() && l.a.e != l2.a() && l.a.d != l2.a() || l.a.g != object.a() && l.a.f != object.a() && l.a.e != object.a() && l.a.d != object.a())) {
            return new m(l.a.c, (Object)Boolean.FALSE);
        }
        throw new i("\u64cd\u4f5c\u7b26\"" + a.a() + "\"\u53c2\u6570\u7c7b\u578b\u9519\u8bef");
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
        if (l.a.h == object.a() && l.a.h == object3.a()) {
            if (object.j().compareTo(object3.j()) < 0) {
                return new m(l.a.c, (Object)Boolean.TRUE);
            }
            return new m(l.a.c, (Object)Boolean.FALSE);
        }
        if (l.a.b == object.a() && l.a.b == object3.a()) {
            if (object.d().compareTo(object3.d()) < 0) {
                return new m(l.a.c, (Object)Boolean.TRUE);
            }
            return new m(l.a.c, (Object)Boolean.FALSE);
        }
        if (!(l.a.g != object.a() && l.a.f != object.a() && l.a.e != object.a() && l.a.d != object.a() || l.a.g != object3.a() && l.a.f != object3.a() && l.a.e != object3.a() && l.a.d != object3.a())) {
            if (Double.compare((double)object.i(), (double)object3.i()) < 0) {
                return new m(l.a.c, (Object)Boolean.TRUE);
            }
            return new m(l.a.c, (Object)Boolean.FALSE);
        }
        throw new IllegalArgumentException("\u64cd\u4f5c\u7b26\"" + a.a() + "\"\u53c2\u6570\u7c7b\u578b\u9519\u8bef");
    }
}

