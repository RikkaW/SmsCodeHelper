/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
public class atd {
    private static volatile atd a = null;

    private atd() {
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static atd a() {
        if (a == null) {
            synchronized (atd.class) {
                if (a == null) {
                    a = new atd();
                }
            }
        }
        return a;
    }

    public String a(String string2) {
        String string3 = string2;
        if (atf.b(string2)) {
            string3 = ate.b(ate.a(string2.trim()).toLowerCase());
        }
        return string3;
    }
}

