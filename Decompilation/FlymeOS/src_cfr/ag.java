/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Boolean
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.lang.reflect.Method
 *  java.util.HashMap
 */
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ag
implements d<m> {
    private d a = null;
    private Map<String, Method> b = new HashMap();

    public ag(d<?> d2) {
        this.a = d2;
    }

    /*
     * Enabled aggressive block sorting
     */
    private Method a(ai ai2, Object arrmethod) {
        if (arrmethod == null) {
            return null;
        }
        String string2 = String.valueOf((Object)arrmethod.getClass().getName()) + ai2.a();
        if (this.b.containsKey(string2)) return this.b.get(string2);
        arrmethod = arrmethod.getClass().getMethods();
        int n2 = arrmethod.length;
        int n3 = 0;
        while (n3 < n2) {
            Method method = arrmethod[n3];
            k k2 = (k)method.getAnnotation((Class)k.class);
            if (k2 != null && k2.a().equals((Object)ai2.a())) {
                this.b.put(string2, method);
                return this.b.get(string2);
            }
            ++n3;
        }
        return this.b.get(string2);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public m a(ai object, m object2, m object3) {
        Object object4 = object2;
        if (object2.p()) {
            object4 = ((n)object2.b()).a(this);
        }
        object4 = object4.b();
        object2 = object3;
        if (object3 != null) {
            object2 = object3;
            if (object3.p()) {
                object2 = ((n)object3.b()).a(this);
            }
        }
        object3 = object2 != null ? object2.b() : null;
        if (this.a != null && this.a.b((ai)((Object)object), object4, object3)) {
            object = this.a.a((ai)((Object)object), object4, object3);
            return new m(l.a.j, object);
        }
        if (object4 instanceof c) {
            object2 = (c)object4;
            if (object.equals((Object)ai.q)) {
                object = new Boolean(object2.l(object3));
                return new m(l.a.j, object);
            }
            if (object.equals((Object)ai.i)) {
                object = object2.n(object3);
                return new m(l.a.j, object);
            }
            if (object.equals((Object)ai.b)) {
                object = object2.a();
                return new m(l.a.j, object);
            }
            if (object.equals((Object)ai.j)) {
                object = object2.m(object3);
                return new m(l.a.j, object);
            }
            if (object.equals((Object)ai.e)) {
                object = object2.b(object3);
                return new m(l.a.j, object);
            }
            if (object.equals((Object)ai.o)) {
                object = new Boolean(object2.j(object3));
                return new m(l.a.j, object);
            }
            if (object.equals((Object)ai.n)) {
                object = new Boolean(object2.i(object3));
                return new m(l.a.j, object);
            }
            if (object.equals((Object)ai.m)) {
                object = new Boolean(object2.h(object3));
                return new m(l.a.j, object);
            }
            if (object.equals((Object)ai.l)) {
                object = new Boolean(object2.g(object3));
                return new m(l.a.j, object);
            }
            if (object.equals((Object)ai.k)) {
                object = new Boolean(object2.f(null));
                return new m(l.a.j, object);
            }
            if (object.equals((Object)ai.h)) {
                object = object2.e(object3);
                return new m(l.a.j, object);
            }
            if (object.equals((Object)ai.f)) {
                object = object2.c(object3);
                return new m(l.a.j, object);
            }
            if (object.equals((Object)ai.d)) {
                object = object2.a(object3);
                return new m(l.a.j, object);
            }
            if (object.equals((Object)ai.p)) {
                object = new Boolean(object2.k(object3));
                return new m(l.a.j, object);
            }
            if (object.equals((Object)ai.c)) {
                object = object2.b();
                return new m(l.a.j, object);
            }
            if (object.equals((Object)ai.a)) {
                object = object2.a();
                return new m(l.a.j, object);
            }
            if (object.equals((Object)ai.r)) {
                object = object2.m(object3);
                return new m(l.a.j, object);
            }
            if (!object.equals((Object)ai.g)) throw new i("\u4e0d\u652f\u6301:" + object.a() + "\u64cd\u4f5c, \u5728Evaluable\u5bf9\u8c61\u3002");
            object = object2.d(object3);
            return new m(l.a.j, object);
        }
        Method method = this.a((ai)((Object)object), object4);
        if (method == null) throw new i("\u4e0d\u652f\u6301:" + object.a() + "\u64cd\u4f5c, \u5728\u5bf9\u8c61:" + object4.getClass().getName());
        object = object2 != null ? new Boolean[]{object3} : new Object[]{};
        try {
            object = method.invoke(object4, (Object[])object);
            return new m(l.a.j, object);
        }
        catch (Exception var1_2) {
            throw new i(var1_2.toString(), var1_2.getCause());
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public boolean b(ai ai2, m object, m m2) {
        Object object2 = object;
        if (object.p()) {
            object2 = ((n)object.b()).a(this);
        }
        object2 = object2.b();
        object = m2;
        if (m2 != null) {
            object = m2;
            if (m2.p()) {
                object = ((n)m2.b()).a(this);
            }
        }
        object = object != null ? object.b() : null;
        if (this.a != null && this.a.b(ai2, object2, object)) {
            return true;
        }
        if (object2 instanceof c) {
            return true;
        }
        if (this.a(ai2, object2) != null) {
            return true;
        }
        return false;
    }
}

