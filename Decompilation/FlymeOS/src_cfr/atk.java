/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.ClassLoader
 *  java.lang.Object
 *  java.lang.StackTraceElement
 *  java.lang.String
 *  java.lang.StringBuffer
 */
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class atk {
    private boolean a;
    private Map<String, String> b;
    private Map<String, String> c;
    private StringBuffer d;
    private String e;

    public atk(String string2) {
        this.e = string2;
        this.e();
    }

    private static void a(StringBuffer stringBuffer, Map<String, String> map) {
        Iterator<String> iterator = map.keySet().iterator();
        block0 : while (iterator.hasNext()) {
            String string2 = iterator.next();
            int n2 = 0;
            do {
                if ((n2 = stringBuffer.indexOf(string2, n2)) <= -1) continue block0;
                String string3 = map.get(string2).split(" ")[0];
                int n3 = string3.length();
                stringBuffer.replace(n2, n2 + n3, string3);
                n2 += n3;
            } while (true);
            break;
        }
        return;
    }

    private static void b(StringBuffer stringBuffer, Map<String, String> map) {
        int n2;
        String string2;
        Iterator<String> iterator = map.keySet().iterator();
        do {
            if (iterator.hasNext()) continue;
            return;
        } while ((n2 = stringBuffer.indexOf(string2 = iterator.next())) <= -1);
        stringBuffer.replace(n2, n2 + 1, map.get(string2));
    }

    private void e() {
        this.b = new LinkedHashMap<String, String>();
        this.c = new LinkedHashMap<String, String>();
    }

    public void a(StringBuffer stringBuffer) {
        this.d = stringBuffer;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public boolean a() {
        String[] arrstring;
        if (this.a) {
            return true;
        }
        String[] arrstring2 = null;
        try {
            arrstring2 = arrstring = atk.class.getClassLoader().getResourceAsStream(String.valueOf((Object)this.e) + ".properties");
            arrstring = atm.a((InputStream)arrstring, new String[]{"phrase", "character"});
        }
        catch (Exception var2_4) {
            atm.a("initDict Exception: " + var2_4.getStackTrace());
            if (arrstring2 == null) return false;
            try {
                arrstring2.close();
                return false;
            }
            catch (IOException iOException) {
                atm.a("initDict close IOException: " + iOException.getStackTrace());
                return false;
            }
        }
        if (atm.a(arrstring)) {
            atm.a("cannot get config: " + this.e);
            return false;
        }
        this.b = atl.a(arrstring[0]);
        this.c = atl.a(arrstring[1]);
        if (!atm.a(this.b) && !atm.a(this.c)) {
            this.a = true;
            return this.a;
        }
        atm.a("cannot get dict");
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void b() {
        if (atm.a(this.d)) {
            atm.a("missing src");
            return;
        }
        this.a();
        if (this.d.length() == 1) {
            atk.b(this.d, this.c);
            return;
        }
        if (!atm.a(this.b)) {
            atk.a(this.d, this.b);
        } else {
            atm.a("missing dictPhrase");
        }
        if (!atm.a(this.c)) {
            atk.a(this.d, this.c);
            return;
        }
        atm.a("missing dictChar");
    }

    public void c() {
        this.d = null;
    }

    public String d() {
        return this.d.toString();
    }
}

