/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.util.HashMap
 *  java.util.HashSet
 */
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class aex {
    static String a = "v21_generic";
    public static int b = -1073741824;
    private static final Map<String, Integer> c = new HashMap();
    private static final Set<Integer> d;

    static {
        c.put(a, -1073741824);
        c.put("v30_generic", -1073741823);
        c.put("v21_europe", -1073741820);
        c.put("v30_europe", -1073741819);
        c.put("v21_japanese_utf8", -1073741816);
        c.put("v30_japanese_utf8", -1073741815);
        c.put("v21_japanese_mobile", 402653192);
        c.put("docomo", 939524104);
        d = new HashSet();
        d.add(-1073741816);
        d.add(-1073741815);
        d.add(402653192);
        d.add(939524104);
    }

    public static boolean a(int n2) {
        if ((n2 & 3) == 0) {
            return true;
        }
        return false;
    }

    public static boolean b(int n2) {
        if ((n2 & 3) == 1) {
            return true;
        }
        return false;
    }

    public static boolean c(int n2) {
        if ((n2 & 3) == 2) {
            return true;
        }
        return false;
    }

    public static int d(int n2) {
        return n2 & 12;
    }

    public static boolean e(int n2) {
        return d.contains(n2);
    }

    static boolean f(int n2) {
        if ((33554432 & n2) != 0) {
            return true;
        }
        return false;
    }
}

