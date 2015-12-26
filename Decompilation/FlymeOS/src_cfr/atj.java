/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuffer
 *  java.util.ArrayList
 *  java.util.regex.Matcher
 *  java.util.regex.Pattern
 */
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class atj {
    private static final atk a = new atk("zht2zhs");
    private static final atk b = new atk("zhs2zht");

    public static String a(String string2) {
        if (atm.b(string2)) {
            return string2;
        }
        return atj.a(new StringBuffer(string2));
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String a(StringBuffer object) {
        String string2;
        Object var1_2 = null;
        if (atm.a((StringBuffer)object)) {
            if (object != null) return object.toString();
            return null;
        }
        try {
            b.a((StringBuffer)object);
            b.b();
            string2 = b.d();
        }
        catch (Exception var2_4) {
            block8 : {
                var2_4.printStackTrace();
                if (object != null) break block8;
                return var1_2;
            }
            try {
                object = object.toString();
                return object;
            }
            finally {
                b.c();
            }
        }
        b.c();
        return string2;
    }

    public static String b(String string2) {
        if (atm.b(string2)) {
            return string2;
        }
        return atj.b(new StringBuffer(string2));
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String b(StringBuffer object) {
        Object var1_2 = null;
        if (atm.a((StringBuffer)object)) {
            if (object != null) return object.toString();
            return null;
        }
        try {
            a.a((StringBuffer)object);
            a.b();
            String string2 = a.d();
            return string2;
        }
        catch (Exception var2_4) {
            if (object == null) {
                object = var1_2;
                do {
                    return object;
                    break;
                } while (true);
            }
            object = object.toString();
            return object;
        }
        finally {
            a.c();
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public static String[] c(String arrstring) {
        int n2 = 0;
        arrstring = arrstring.trim();
        String[] arrstring2 = new ArrayList(5);
        arrstring2.add((Object)arrstring);
        Object object = Pattern.compile((String)"(.*?)[\u4e00-\u9fa5](.*?)").matcher((CharSequence)arrstring);
        if (!object.find()) {
            return new String[]{arrstring};
        }
        int n3 = object.end() - 1;
        String string2 = arrstring.substring(0, n3);
        String string3 = arrstring.substring(n3 + 1);
        if (object.find()) {
            string2 = atj.a(atj.b((String)arrstring));
            string3 = atj.b(string2);
            if (!string3.equals((Object)arrstring)) {
                arrstring2.add((Object)string3);
            }
            if (string2.equals((Object)arrstring)) return (String[])arrstring2.toArray((Object[])new String[0]);
            arrstring2.add((Object)string2);
            return (String[])arrstring2.toArray((Object[])new String[0]);
        }
        arrstring2 = arrstring.substring(n3, n3 + 1);
        object = atj.b((String)arrstring2);
        arrstring = new ArrayList();
        arrstring.add(arrstring2);
        arrstring2 = object.split(" ");
        int n4 = arrstring2.length;
        n3 = 0;
        do {
            if (n3 >= n4) break;
            object = arrstring2[n3];
            if (!arrstring.contains(object)) {
                arrstring.add(object);
            }
            ++n3;
        } while (true);
        n3 = 0;
        block1 : do {
            if (n3 >= arrstring.size()) {
                arrstring2 = arrstring.toArray(new String[0]);
                n4 = arrstring2.length;
                n3 = n2;
                do {
                    arrstring = arrstring2;
                    if (n3 >= n4) return arrstring;
                    arrstring2[n3] = String.valueOf((Object)string2) + arrstring2[n3] + string3;
                    ++n3;
                } while (true);
            }
            arrstring2 = atj.a((String)arrstring.get(n3)).split(" ");
            int n5 = arrstring2.length;
            n4 = 0;
            do {
                if (n4 >= n5) {
                    ++n3;
                    continue block1;
                }
                object = arrstring2[n4];
                if (!arrstring.contains(object)) {
                    arrstring.add(object);
                }
                ++n4;
            } while (true);
            break;
        } while (true);
    }
}

