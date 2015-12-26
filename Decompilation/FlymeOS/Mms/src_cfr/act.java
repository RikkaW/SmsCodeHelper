/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.text.TextUtils
 *  android.util.Log
 *  java.lang.Character
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 */
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;

public class act {
    private static act a;
    private Object b;
    private Object c;

    private act() {
        try {
            this.b = aau.a("libcore.icu.Transliterator", String.class, (Object)"Han-Latin/Names; Latin-Ascii; Any-Upper");
            this.c = aau.a("libcore.icu.Transliterator", String.class, (Object)"Latin-Ascii");
            return;
        }
        catch (RuntimeException var1_1) {
            Log.w((String)"HanziToPinyin", (String)"Han-Latin/Names transliterator data is missing, HanziToPinyin is disabled");
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(char c2, a a2) {
        a2.b = Character.toString((char)c2);
        if (c2 < '') {
            a2.a = 1;
            a2.c = a2.b;
            return;
        } else {
            if (c2 < '\u0250' || '\u1e00' <= c2 && c2 < '\u1eff') {
                a2.a = 1;
                String string2 = this.c == null ? a2.b : (String)aau.c(this.c, "transliterate", String.class, a2.b);
                a2.c = string2;
                return;
            }
            a2.a = 2;
            a2.c = (String)aau.c(this.b, "transliterate", String.class, a2.b);
            if (!TextUtils.isEmpty((CharSequence)a2.c) && !TextUtils.equals((CharSequence)a2.b, (CharSequence)a2.c)) return;
            {
                a2.a = 3;
                a2.c = a2.b;
                return;
            }
        }
    }

    private void a(StringBuilder stringBuilder, ArrayList<a> arrayList, int n2) {
        String string2 = stringBuilder.toString();
        arrayList.add((Object)new a(n2, string2, string2));
        stringBuilder.setLength(0);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static act b() {
        synchronized (act.class) {
            if (a != null) return a;
            a = new act();
            return a;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public ArrayList<a> a(String string2) {
        ArrayList arrayList = new ArrayList();
        if (!this.a() || TextUtils.isEmpty((CharSequence)string2)) {
            return arrayList;
        }
        int n2 = string2.length();
        StringBuilder stringBuilder = new StringBuilder();
        a a2 = new a();
        int n3 = 1;
        for (int i2 = 0; i2 < n2; ++i2) {
            a a3;
            int n4;
            char c2 = string2.charAt(i2);
            if (Character.isSpaceChar((char)c2)) {
                a3 = a2;
                n4 = n3;
                if (stringBuilder.length() > 0) {
                    this.a(stringBuilder, arrayList, n3);
                    n4 = n3;
                    a3 = a2;
                }
            } else {
                this.a(c2, a2);
                if (a2.a == 2) {
                    if (stringBuilder.length() > 0) {
                        this.a(stringBuilder, arrayList, n3);
                    }
                    arrayList.add((Object)a2);
                    a2 = new a();
                } else {
                    if (n3 != a2.a && stringBuilder.length() > 0) {
                        this.a(stringBuilder, arrayList, n3);
                    }
                    stringBuilder.append(a2.c);
                }
                n4 = a2.a;
                a3 = a2;
            }
            a2 = a3;
            n3 = n4;
        }
        if (stringBuilder.length() > 0) {
            this.a(stringBuilder, arrayList, n3);
        }
        return arrayList;
    }

    public boolean a() {
        if (this.b != null) {
            return true;
        }
        return false;
    }

    public static class a {
        public int a;
        public String b;
        public String c;

        public a() {
        }

        public a(int n2, String string2, String string3) {
            this.a = n2;
            this.b = string2;
            this.c = string3;
        }
    }

}

