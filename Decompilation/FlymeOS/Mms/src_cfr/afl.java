/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.telephony.PhoneNumberUtils
 *  android.text.Editable
 *  android.text.SpannableStringBuilder
 *  android.text.TextUtils
 *  android.util.Log
 *  java.lang.Character
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.nio.ByteBuffer
 *  java.nio.charset.Charset
 *  java.util.ArrayList
 *  java.util.Arrays
 *  java.util.HashMap
 *  java.util.HashSet
 */
import android.telephony.PhoneNumberUtils;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.Log;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class afl {
    private static final Map<Integer, String> a = new HashMap();
    private static final Set<String> b;
    private static final Map<String, Integer> c;
    private static final Map<Integer, String> d;
    private static final Set<String> e;
    private static final Set<Character> f;
    private static final int[] g;
    private static final int[] h;

    static {
        c = new HashMap();
        a.put(9, "CAR");
        c.put("CAR", 9);
        a.put(6, "PAGER");
        c.put("PAGER", 6);
        a.put(11, "ISDN");
        c.put("ISDN", 11);
        c.put("HOME", 1);
        c.put("WORK", 3);
        c.put("CELL", 2);
        c.put("OTHER", 7);
        c.put("CALLBACK", 8);
        c.put("COMPANY-MAIN", 10);
        c.put("RADIO", 14);
        c.put("TTY-TDD", 16);
        c.put("ASSISTANT", 19);
        c.put("VOICE", 7);
        b = new HashSet();
        b.add("MODEM");
        b.add("MSG");
        b.add("BBS");
        b.add("VIDEO");
        d = new HashMap();
        d.put(0, "X-AIM");
        d.put(1, "X-MSN");
        d.put(2, "X-YAHOO");
        d.put(3, "X-SKYPE-USERNAME");
        d.put(5, "X-GOOGLE-TALK");
        d.put(6, "X-ICQ");
        d.put(7, "X-JABBER");
        d.put(4, "X-QQ");
        d.put(8, "X-NETMEETING");
        e = new HashSet((Collection)Arrays.asList((Object[])new String[]{"MOBILE", "\u643a\u5e2f\u96fb\u8a71", "\u643a\u5e2f", "\u30b1\u30a4\u30bf\u30a4", "\uff79\uff72\uff80\uff72"}));
        f = new HashSet((Collection)Arrays.asList((Object[])new Character[]{Character.valueOf((char)'['), Character.valueOf((char)']'), Character.valueOf((char)'='), Character.valueOf((char)':'), Character.valueOf((char)'.'), Character.valueOf((char)','), Character.valueOf((char)' ')}));
        g = new int[]{58, 59, 44, 32};
        h = new int[]{59, 58};
    }

    public static int a(int n2) {
        if (aex.e(n2)) {
            return 2;
        }
        return 1;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public static Object a(Collection<String> var0, String var1_1) {
        var5_2 = 0;
        var7_3 = var1_1;
        if (var1_1 == null) {
            var7_3 = "";
        }
        var3_4 = -1;
        var1_1 = null;
        if (var0 != null) ** GOTO lbl12
        var4_6 = 0;
        var0 = null;
        var3_4 = -1;
        ** GOTO lbl57
lbl12: // 1 sources:
        var8_5 = var0.iterator();
        var4_6 = 0;
        var2_7 = 0;
        var0 = var1_1;
        do {
            if (!var8_5.hasNext()) ** GOTO lbl55
            var1_1 = (String)var8_5.next();
            if (var1_1 == null) continue;
            var9_9 = var1_1.toUpperCase();
            if (!var9_9.equals((Object)"PREF")) ** GOTO lbl27
            var5_2 = 1;
            var4_6 = var3_4;
            var3_4 = var2_7;
            var2_7 = var5_2;
            ** GOTO lbl76
lbl27: // 1 sources:
            if (!var9_9.equals((Object)"FAX")) ** GOTO lbl33
            var2_7 = var4_6;
            var5_2 = 1;
            var4_6 = var3_4;
            var3_4 = var5_2;
            ** GOTO lbl76
lbl33: // 1 sources:
            if (var9_9.startsWith("X-") && var3_4 < 0) {
                var1_1 = var1_1.substring(2);
            }
            if (var1_1.length() == 0) continue;
            var9_9 = afl.c.get(var1_1.toUpperCase());
            if (var9_9 == null) ** GOTO lbl48
            var5_2 = var9_9.intValue();
            var6_8 = var7_3.indexOf("@");
            if (var5_2 == 6 && var6_8 > 0 && var6_8 < var7_3.length() - 1 || var3_4 < 0 || var3_4 == 0 || var3_4 == 7) {
                var3_4 = var9_9.intValue();
            }
            var5_2 = var2_7;
            var6_8 = var3_4;
            var2_7 = var4_6;
            var3_4 = var5_2;
            var4_6 = var6_8;
            ** GOTO lbl76
lbl48: // 1 sources:
            if (var3_4 >= 0) ** GOTO lbl71
            var5_2 = 0;
            var3_4 = var2_7;
            var0 = var1_1;
            var2_7 = var4_6;
            var4_6 = var5_2;
            ** GOTO lbl76
lbl55: // 1 sources:
            var5_2 = var4_6;
            var4_6 = var2_7;
lbl57: // 2 sources:
            var2_7 = var3_4;
            if (var3_4 < 0) {
                var2_7 = var5_2 != 0 ? 12 : 1;
            }
            if (var4_6 != 0) {
                if (var2_7 == 1) {
                    var2_7 = 5;
                } else if (var2_7 == 3) {
                    var2_7 = 4;
                } else if (var2_7 == 7) {
                    var2_7 = 13;
                }
            }
            if (var2_7 != 0) return var2_7;
            return var0;
lbl71: // 1 sources:
            var5_2 = var2_7;
            var6_8 = var3_4;
            var2_7 = var4_6;
            var3_4 = var5_2;
            var4_6 = var6_8;
lbl76: // 5 sources:
            var5_2 = var4_6;
            var4_6 = var2_7;
            var2_7 = var3_4;
            var3_4 = var5_2;
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static String a(int n2, String arrstring, String string22, String string3, String string4, String string5) {
        StringBuilder stringBuilder = new StringBuilder();
        arrstring = afl.a(n2, (String)arrstring, string22, string3);
        n2 = 1;
        if (!TextUtils.isEmpty((CharSequence)string4)) {
            stringBuilder.append(string4);
            n2 = 0;
        }
        for (String string22 : arrstring) {
            int n3 = n2;
            if (!TextUtils.isEmpty((CharSequence)string22)) {
                if (n2 != 0) {
                    n2 = 0;
                } else {
                    stringBuilder.append(' ');
                }
                stringBuilder.append(string22);
                n3 = n2;
            }
            n2 = n3;
        }
        if (!TextUtils.isEmpty((CharSequence)string5)) {
            if (n2 == 0) {
                stringBuilder.append(' ');
            }
            stringBuilder.append(string5);
        }
        return stringBuilder.toString();
    }

    public static final String a(String string2, String string3, String string4) {
        if (string3.equalsIgnoreCase(string4)) {
            return string2;
        }
        string2 = Charset.forName((String)string3).encode(string2);
        string3 = (String)new byte[string2.remaining()];
        string2.get((byte[])string3);
        try {
            string2 = new String((byte[])string3, string4);
            return string2;
        }
        catch (UnsupportedEncodingException var0_1) {
            Log.e((String)"vCard", (String)("Failed to encode: charset=" + string4));
            return null;
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static String a(String var0, boolean var1_2, String var2_3, String var3_6) {
        var9_10 = new StringBuilder();
        var7_11 = var0.length();
        for (var6_12 = 0; var6_12 < var7_11; ++var6_12) {
            var4_13 = var0.charAt(var6_12);
            if (var4_13 == '=' && var6_12 < var7_11 - 1 && ((var5_14 = var0.charAt(var6_12 + 1)) == ' ' || var5_14 == '\t')) {
                var9_10.append(var5_14);
                ++var6_12;
                continue;
            }
            var9_10.append(var4_13);
        }
        var11_15 = var9_10.toString();
        if (var1_2) ** GOTO lbl18
        var0 = new StringBuilder();
        var8_17 = var11_15.length();
        var10_18 = new ArrayList();
        var6_12 = 0;
        ** GOTO lbl48
lbl18: // 1 sources:
        var0 = var11_15.split("\r\n");
lbl19: // 2 sources:
        do {
            var11_16 = new StringBuilder();
            var7_11 = var0.length;
            for (var6_12 = 0; var6_12 < var7_11; ++var6_12) {
                var9_10 = var10_18 = var0[var6_12];
                if (var10_18.endsWith("=")) {
                    var9_10 = var10_18.substring(0, var10_18.length() - 1);
                }
                var11_16.append((String)var9_10);
            }
            var9_10 = var11_16.toString();
            if (TextUtils.isEmpty((CharSequence)var9_10)) {
                Log.w((String)"vCard", (String)"Given raw string is empty.");
            }
            try {
                var0 = var9_10.getBytes(var2_3 /* !! */ );
            }
            catch (UnsupportedEncodingException var0_1) {
                Log.w((String)"vCard", (String)("Failed to decode: " + var2_3 /* !! */ ));
                var0 = var9_10.getBytes();
            }
            try {
                var2_4 = c.a((byte[])var0);
            }
            catch (a var2_7) {
                Log.e((String)"vCard", (String)"DecoderException is thrown.");
            }
            var0 = var2_4;
            try {
                return new String((byte[])var0, (String)var3_9);
            }
            catch (UnsupportedEncodingException var2_8) {
                Log.e((String)"vCard", (String)("Failed to encode: charset=" + (String)var3_9));
                return new String((byte[])var0);
            }
            break;
        } while (true);
lbl48: // 2 sources:
        while (var6_12 < var8_17) {
            var4_13 = var11_15.charAt(var6_12);
            if (var4_13 == '\n') {
                var10_18.add((Object)var0.toString());
                var0 = new StringBuilder();
                var7_11 = var6_12;
            } else if (var4_13 == '\r') {
                var10_18.add((Object)var0.toString());
                var9_10 = new StringBuilder();
                var7_11 = var6_12;
                var0 = var9_10;
                if (var6_12 < var8_17 - 1) {
                    var7_11 = var6_12;
                    var0 = var9_10;
                    if (var11_15.charAt(var6_12 + 1) == '\n') {
                        var7_11 = var6_12 + 1;
                        var0 = var9_10;
                    }
                }
            } else {
                var0.append(var4_13);
                var7_11 = var6_12;
            }
            var6_12 = var7_11 + 1;
        }
        if ((var0 = var0.toString()).length() > 0) {
            var10_18.add(var0);
        }
        var0 = (String[])var10_18.toArray((Object[])new String[0]);
        ** while (true)
    }

    /*
     * Enabled aggressive block sorting
     */
    public static List<String> a(String string2, int n2) {
        ArrayList arrayList = new ArrayList();
        StringBuilder stringBuilder = new StringBuilder();
        int n3 = string2.length();
        int n4 = 0;
        do {
            if (n4 >= n3) {
                arrayList.add(stringBuilder.toString());
                return arrayList;
            }
            char c2 = string2.charAt(n4);
            if (c2 == '\\' && n4 < n3 - 1) {
                String string3;
                char c3 = string2.charAt(n4 + 1);
                if (aex.c(n2)) {
                    string3 = aff.c(c3);
                } else if (aex.b(n2)) {
                    string3 = afe.b(c3);
                } else {
                    if (!aex.a(n2)) {
                        Log.w((String)"vCard", (String)"Unknown vCard type");
                    }
                    string3 = afd.a(c3);
                }
                if (string3 != null) {
                    stringBuilder.append(string3);
                    ++n4;
                } else {
                    stringBuilder.append(c2);
                }
            } else if (c2 == ';') {
                arrayList.add(stringBuilder.toString());
                stringBuilder = new StringBuilder();
            } else {
                stringBuilder.append(c2);
            }
            ++n4;
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public static boolean a(String string2) {
        int n2 = string2.length() % 3;
        if (string2.length() < 2) return false;
        if (n2 != 1 && n2 != 0) {
            return false;
        }
        n2 = 0;
        while (n2 < string2.length()) {
            if (string2.charAt(n2) != '=') return false;
            n2 += 3;
        }
        return true;
    }

    public static boolean a(Collection<String> object) {
        if (object == null) {
            return true;
        }
        object = object.iterator();
        while (object.hasNext()) {
            String string2 = (String)object.next();
            if (TextUtils.isEmpty((CharSequence)string2) || d.a(string2)) continue;
            return false;
        }
        return true;
    }

    public static /* varargs */ boolean a(String ... arrstring) {
        if (arrstring == null) {
            return true;
        }
        return afl.a(Arrays.asList((Object[])arrstring));
    }

    public static String[] a(int n2, String string2, String string3, String string4) {
        String[] arrstring = new String[3];
        switch (aex.d(n2)) {
            default: {
                arrstring[0] = string4;
                arrstring[1] = string3;
                arrstring[2] = string2;
                return arrstring;
            }
            case 8: {
                if (afl.a(new String[]{string2}) && afl.a(new String[]{string4})) {
                    arrstring[0] = string4;
                    arrstring[1] = string3;
                    arrstring[2] = string2;
                    return arrstring;
                }
                arrstring[0] = string2;
                arrstring[1] = string3;
                arrstring[2] = string4;
                return arrstring;
            }
            case 4: 
        }
        arrstring[0] = string3;
        arrstring[1] = string4;
        arrstring[2] = string2;
        return arrstring;
    }

    public static String b(int n2, String string2, String string3, String string4) {
        return afl.a(n2, string2, string3, string4, null, null);
    }

    static class a
    extends Exception {
        public a(String string2) {
            super(string2);
        }
    }

    public static class b {
        public static String a(String string2, int n2) {
            string2 = new SpannableStringBuilder((CharSequence)string2);
            PhoneNumberUtils.formatNumber((Editable)string2, (int)n2);
            return string2.toString();
        }
    }

    static class c {
        private static byte a = 61;

        /*
         * Exception decompiling
         */
        public static final byte[] a(byte[] var0) {
            // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
            // java.lang.IllegalStateException: Backjump on non jumping statement [] lbl19 : TryStatement: try { 2[TRYBLOCK]

            // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.Cleaner$1.call(Cleaner.java:44)
            // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.Cleaner$1.call(Cleaner.java:22)
            // org.benf.cfr.reader.util.graph.GraphVisitorDFS.process(GraphVisitorDFS.java:68)
            // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.Cleaner.removeUnreachableCode(Cleaner.java:54)
            // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.RemoveDeterministicJumps.apply(RemoveDeterministicJumps.java:35)
            // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:507)
            // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:220)
            // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:165)
            // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:91)
            // org.benf.cfr.reader.entities.Method.analyse(Method.java:354)
            // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:751)
            // org.benf.cfr.reader.entities.ClassFile.analyseInnerClassesPass1(ClassFile.java:664)
            // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:747)
            // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:683)
            // org.benf.cfr.reader.Main.doJar(Main.java:128)
            // org.benf.cfr.reader.Main.main(Main.java:178)
            throw new IllegalStateException("Decompilation failed");
        }
    }

    public static class d {
        public static boolean a(char c2) {
            if (' ' <= c2 && c2 <= '~' || c2 == '\r' || c2 == '\n') {
                return true;
            }
            return false;
        }

        public static boolean a(CharSequence charSequence) {
            int n2 = charSequence.length();
            for (int i2 = 0; i2 < n2; ++i2) {
                if (d.a(charSequence.charAt(i2))) continue;
                return false;
            }
            return true;
        }
    }

}

