/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Long
 *  java.lang.Object
 *  java.util.HashMap
 */
import java.util.HashMap;

public class aav {
    private static final HashMap<Object, Long> a = new HashMap();

    public static void a(Object object) {
        synchronized (aav.class) {
            a.remove(object);
            return;
        }
    }

    public static void a(Object object, long l2) {
        synchronized (aav.class) {
            a.put(object, (Object)l2);
            return;
        }
    }
}

