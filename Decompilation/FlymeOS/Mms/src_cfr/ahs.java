/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Class
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.reflect.Method
 */
import java.lang.reflect.Method;

public class ahs {
    public static /* varargs */ Object a(Object object, String string2, Object ... arrobject) {
        Class class_ = object.getClass();
        Class[] arrclass = new Class[arrobject.length];
        int n2 = arrobject.length;
        for (int i2 = 0; i2 < n2; ++i2) {
            arrclass[i2] = arrobject[i2].getClass();
        }
        return class_.getMethod(string2, arrclass).invoke(object, arrobject);
    }

    public static /* varargs */ int b(Object object, String string2, Object ... arrobject) {
        Class class_ = object.getClass();
        Class[] arrclass = new Class[arrobject.length];
        int n2 = arrobject.length;
        for (int i2 = 0; i2 < n2; ++i2) {
            arrclass[i2] = arrobject[i2].getClass();
        }
        return (Integer)class_.getMethod(string2, arrclass).invoke(object, arrobject);
    }
}

