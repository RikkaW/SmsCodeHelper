/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.reflect.Field
 *  java.lang.reflect.Method
 */
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class akc {
    private static Object a(Class<?> method, Object object, String string2, Object[] arrobject) {
        if (arrobject == null || arrobject.length == 0) {
            method = method.getMethod(string2, new Class[0]);
            method.setAccessible(true);
            return method.invoke(object, new Object[0]);
        }
        method = method.getMethod(string2, akc.a(arrobject));
        method.setAccessible(true);
        return method.invoke(object, arrobject);
    }

    public static Object a(Object object, Class<?> field, String string2) {
        if (object == null || field == null || string2 == null) {
            throw new IllegalArgumentException("parameter can not be null!");
        }
        try {
            field = field.getDeclaredField(string2);
            field.setAccessible(true);
            object = field.get(object);
            return object;
        }
        catch (Exception var0_1) {
            throw new NoSuchFieldException(string2);
        }
    }

    public static Object a(String string2, String string3) {
        if (string2 == null || string3 == null) {
            throw new IllegalArgumentException("parameter can not be null!");
        }
        try {
            string2 = Class.forName((String)string2);
        }
        catch (ClassNotFoundException var0_1) {
            throw new IllegalArgumentException("className not found");
        }
        return akc.b(string2, string2, string3);
    }

    public static Object a(String string2, String string3, Object[] arrobject) {
        string2 = Class.forName((String)string2);
        return akc.a(string2, string2, string3, arrobject);
    }

    private static Class<?>[] a(Object[] arrobject) {
        Class[] arrclass = new Class[arrobject.length];
        for (int i2 = 0; i2 < arrclass.length; ++i2) {
            arrclass[i2] = arrobject[i2].getClass();
        }
        return arrclass;
    }

    private static Object b(Object object, Class<?> class_, String string2) {
        while (class_ != null) {
            try {
                Object object2 = akc.a(object, class_, string2);
                return object2;
            }
            catch (NoSuchFieldException var3_4) {
                try {
                    class_ = class_.getSuperclass();
                }
                catch (Exception var1_2) {
                    class_ = null;
                }
                continue;
            }
        }
        throw new NoSuchFieldException(string2);
    }
}

