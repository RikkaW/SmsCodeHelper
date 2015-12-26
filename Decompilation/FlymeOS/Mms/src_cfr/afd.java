/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.util.Log
 *  java.io.BufferedReader
 *  java.io.InputStreamReader
 *  java.io.Reader
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.util.ArrayList
 *  java.util.HashSet
 */
import android.util.Log;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

class afd {
    protected final String a = "ISO-8859-1";
    protected String b;
    protected String c;
    protected a d;
    protected final Set<String> e = new HashSet();
    protected final Set<String> f = new HashSet();
    private final List<afb> g = new ArrayList();
    private boolean h;

    public afd() {
        this(aex.b);
    }

    public afd(int n2) {
    }

    static String a(char c2) {
        if (c2 == '\\' || c2 == ';' || c2 == ':' || c2 == ',') {
            return String.valueOf((char)c2);
        }
        return null;
    }

    private void a(afj afj2, String iterator, String string2, String string3) {
        ArrayList arrayList = new ArrayList();
        if (this.b.equals((Object)"QUOTED-PRINTABLE")) {
            iterator = this.e((String)((Object)iterator));
            afj2.c((String)((Object)iterator));
            iterator = afl.a((String)((Object)iterator), this.f()).iterator();
            while (iterator.hasNext()) {
                arrayList.add(afl.a(iterator.next(), false, string2, string3));
            }
        } else {
            iterator = afl.a(this.f((String)((Object)iterator)), this.f()).iterator();
            while (iterator.hasNext()) {
                arrayList.add(afl.a(iterator.next(), string2, string3));
            }
        }
        afj2.a((List<String>)arrayList);
        iterator = this.g.iterator();
        while (iterator.hasNext()) {
            ((afb)iterator.next()).a(afj2);
        }
    }

    private boolean b(char c2) {
        if (c2 >= 'a' && c2 <= 'z' || c2 >= 'A' && c2 <= 'Z') {
            return true;
        }
        return false;
    }

    private String e(String string2) {
        CharSequence charSequence = string2;
        if (string2.trim().endsWith("=")) {
            int n2 = string2.length() - 1;
            while (string2.charAt(n2) != '=') {
            }
            charSequence = new StringBuilder();
            charSequence.append(string2.substring(0, n2 + 1));
            charSequence.append("\r\n");
            do {
                if ((string2 = this.a()) == null) {
                    throw new afn("File ended during parsing a Quoted-Printable String");
                }
                if (!string2.trim().endsWith("=")) break;
                n2 = string2.length() - 1;
                while (string2.charAt(n2) != '=') {
                }
                charSequence.append(string2.substring(0, n2 + 1));
                charSequence.append("\r\n");
            } while (true);
            charSequence.append(string2);
            charSequence = charSequence.toString();
        }
        return charSequence;
    }

    /*
     * Enabled aggressive block sorting
     */
    private String f(String string2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(string2);
        while ((string2 = this.b()) != null && string2.length() != 0 && this.g(string2) == null) {
            this.a();
            stringBuilder.append(" ").append(string2);
        }
        return stringBuilder.toString();
    }

    /*
     * Enabled aggressive block sorting
     */
    private String g(String string2) {
        int n2 = string2.indexOf(":");
        if (n2 <= -1) return null;
        int n3 = string2.indexOf(";");
        if (n2 == -1) {
            n2 = n3;
            return string2.substring(0, n2).toUpperCase();
        }
        if (n3 == -1) {
            return string2.substring(0, n2).toUpperCase();
        }
        n2 = Math.min((int)n2, (int)n3);
        return string2.substring(0, n2).toUpperCase();
    }

    private void i(afj afj2, String string2) {
        String string3 = afj2.c();
        if (string2.equals((Object)"AGENT")) {
            this.a(afj2);
            return;
        }
        if (this.a(string2)) {
            if (string2.equals((Object)"VERSION") && !string3.equals((Object)this.g())) {
                throw new afs("Incompatible version: " + string3 + " != " + this.g());
            }
            this.h(afj2, string2);
            return;
        }
        throw new afn("Unknown property name: \"" + string2 + "\"");
    }

    private boolean l() {
        this.b = "8BIT";
        this.c = "UTF-8";
        if (!this.a(false)) {
            return false;
        }
        Iterator<afb> iterator = this.g.iterator();
        while (iterator.hasNext()) {
            iterator.next().c();
        }
        this.d();
        iterator = this.g.iterator();
        while (iterator.hasNext()) {
            iterator.next().d();
        }
        return true;
    }

    private void m() {
        Iterator<afb> iterator = this.g.iterator();
        while (iterator.hasNext()) {
            iterator.next().c();
        }
        this.d();
        iterator = this.g.iterator();
        while (iterator.hasNext()) {
            iterator.next().d();
        }
    }

    protected String a() {
        return this.d.readLine();
    }

    public void a(afb afb2) {
        this.g.add(afb2);
    }

    protected void a(afj afj2) {
        if (!afj2.c().toUpperCase().contains((CharSequence)"BEGIN:VCARD")) {
            Iterator<afb> iterator = this.g.iterator();
            while (iterator.hasNext()) {
                iterator.next().a(afj2);
            }
        } else {
            throw new afm("AGENT Property is not supported now.");
        }
    }

    protected void a(afj afj2, String string2) {
        String[] arrstring = string2.split("=", 2);
        if (arrstring.length == 2) {
            string2 = arrstring[0].trim().toUpperCase();
            arrstring = arrstring[1].trim();
            if (string2.equals((Object)"TYPE")) {
                this.c(afj2, (String)arrstring);
                return;
            }
            if (string2.equals((Object)"VALUE")) {
                this.d(afj2, (String)arrstring);
                return;
            }
            if (string2.equals((Object)"ENCODING")) {
                this.e(afj2, arrstring.toUpperCase());
                return;
            }
            if (string2.equals((Object)"CHARSET")) {
                this.f(afj2, (String)arrstring);
                return;
            }
            if (string2.equals((Object)"LANGUAGE")) {
                this.g(afj2, (String)arrstring);
                return;
            }
            if (string2.startsWith("X-")) {
                this.a(afj2, string2, (String)arrstring);
                return;
            }
            throw new afn("Unknown type \"" + string2 + "\"");
        }
        this.b(afj2, arrstring[0]);
    }

    protected void a(afj afj2, String string2, String string3) {
        afj2.a(string2, string3);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void a(InputStream iterator) {
        if (iterator == null) {
            throw new NullPointerException("InputStream must not be null.");
        }
        this.d = new a((Reader)new InputStreamReader((InputStream)((Object)iterator), this.a));
        System.currentTimeMillis();
        iterator = this.g.iterator();
        while (iterator.hasNext()) {
            ((afb)iterator.next()).a();
        }
        do {
            synchronized (this) {
                if (this.h) {
                    Log.i((String)"vCard", (String)"Cancel request has come. exitting parse operation.");
                    break;
                }
            }
        } while (this.l());
        iterator = this.g.iterator();
        while (iterator.hasNext()) {
            iterator.next().b();
        }
        return;
    }

    protected boolean a(String string2) {
        if (!(this.h().contains(string2.toUpperCase()) || string2.startsWith("X-") || this.e.contains(string2))) {
            this.e.add(string2);
            Log.w((String)"vCard", (String)("Property name unsupported by vCard 2.1: " + string2));
        }
        return true;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    protected boolean a(boolean var1_1) {
        do lbl-1000: // 3 sources:
        {
            if ((var2_2 = this.a()) == null) {
                return false;
            }
            if (var2_2.trim().length() <= 0) ** GOTO lbl-1000
            var3_3 = var2_2.split(":", 2);
            if (var3_3.length == 2 && var3_3[0].trim().equalsIgnoreCase("BEGIN") && var3_3[1].trim().equalsIgnoreCase("VCARD")) {
                return true;
            }
            if (var1_1) continue;
            throw new afn("Expected String \"BEGIN:VCARD\" did not come (Instead, \"" + var2_2 + "\" came)");
        } while (var1_1);
        throw new afn("Reached where must not be reached.");
    }

    /*
     * Enabled aggressive block sorting
     */
    protected afj b(String string2) {
        int n2 = 0;
        afj afj2 = new afj();
        int n3 = string2.length();
        if (n3 > 0 && string2.charAt(0) == '#') {
            throw new afo();
        }
        int n4 = 0;
        int n5 = 0;
        do {
            int n6;
            if (n4 >= n3) {
                throw new afp("Invalid line: \"" + string2 + "\"");
            }
            char c2 = string2.charAt(n4);
            switch (n5) {
                default: {
                    n6 = n2;
                    break;
                }
                case 0: {
                    if (c2 == ':') {
                        afj2.a(string2.substring(n2, n4));
                        string2 = n4 < n3 - 1 ? string2.substring(n4 + 1) : "";
                        afj2.c(string2);
                        return afj2;
                    }
                    if (c2 == '.') {
                        String string3 = string2.substring(n2, n4);
                        if (string3.length() == 0) {
                            Log.w((String)"vCard", (String)"Empty group found. Ignoring.");
                        } else {
                            afj2.b(string3);
                        }
                        n6 = n4 + 1;
                        break;
                    }
                    n6 = n2;
                    if (c2 != ';') break;
                    afj2.a(string2.substring(n2, n4));
                    n6 = n4 + 1;
                    n5 = 1;
                    break;
                }
                case 1: {
                    if (c2 == '\"') {
                        if ("2.1".equalsIgnoreCase(this.g())) {
                            Log.w((String)"vCard", (String)"Double-quoted params found in vCard 2.1. Silently allow it");
                        }
                        n5 = 2;
                        n6 = n2;
                        break;
                    }
                    if (c2 == ';') {
                        this.a(afj2, string2.substring(n2, n4));
                        n6 = n4 + 1;
                        break;
                    }
                    n6 = n2;
                    if (c2 != ':') break;
                    this.a(afj2, string2.substring(n2, n4));
                    string2 = n4 < n3 - 1 ? string2.substring(n4 + 1) : "";
                    afj2.c(string2);
                    return afj2;
                }
                case 2: {
                    n6 = n2;
                    if (c2 != '\"') break;
                    if ("2.1".equalsIgnoreCase(this.g())) {
                        Log.w((String)"vCard", (String)"Double-quoted params found in vCard 2.1. Silently allow it");
                    }
                    n5 = 1;
                    n6 = n2;
                }
            }
            ++n4;
            n2 = n6;
        } while (true);
    }

    protected String b() {
        return this.d.a();
    }

    protected void b(afj afj2, String string2) {
        this.c(afj2, string2);
    }

    protected String c() {
        String string2;
        do {
            if ((string2 = this.a()) != null) continue;
            throw new afn("Reached end of buffer.");
        } while (string2.trim().length() <= 0);
        return string2;
    }

    /*
     * Enabled aggressive block sorting
     */
    protected String c(String string2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(string2);
        do {
            if ((string2 = this.b()) == null) {
                throw new afn("File ended during parsing BASE64 binary");
            }
            String string3 = this.g(string2);
            if (this.h().contains(string3) || "X-ANDROID-CUSTOM".equals((Object)string3)) {
                Log.w((String)"vCard", (String)"Found a next property during parsing a BASE64 string, which must not contain semi-colon or colon. Treat the line as next property.");
                Log.w((String)"vCard", (String)("Problematic line: " + string2.trim()));
                return stringBuilder.toString();
            }
            this.a();
            if (string2.length() == 0) return stringBuilder.toString();
            stringBuilder.append(string2.trim());
        } while (true);
    }

    protected void c(afj afj2, String string2) {
        if (!(this.i().contains(string2.toUpperCase()) || string2.startsWith("X-") || this.e.contains(string2))) {
            this.e.add(string2);
            Log.w((String)"vCard", (String)String.format((String)"TYPE unsupported by %s: ", (Object[])new Object[]{this.f(), string2}));
        }
        afj2.a("TYPE", string2);
    }

    protected String d(String string2) {
        return string2;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected void d() {
        boolean bl2;
        boolean bl3 = false;
        try {
            bl3 = bl2 = this.e();
        }
        catch (afo var3_4) {
            Log.e((String)"vCard", (String)"Invalid line which looks like some comment was found. Ignored.");
        }
        while (!bl3) {
            try {
                bl3 = bl2 = this.e();
            }
            catch (afo var3_5) {
                Log.e((String)"vCard", (String)"Invalid line which looks like some comment was found. Ignored.");
                continue;
            }
            break;
        }
        return;
    }

    protected void d(afj afj2, String string2) {
        if (!(this.j().contains(string2.toUpperCase()) || string2.startsWith("X-") || this.f.contains(string2))) {
            this.f.add(string2);
            Log.w((String)"vCard", (String)String.format((String)"The value unsupported by TYPE of %s: ", (Object[])new Object[]{this.f(), string2}));
        }
        afj2.a("VALUE", string2);
    }

    protected void e(afj afj2, String string2) {
        if (this.k().contains(string2) || string2.startsWith("X-")) {
            afj2.a("ENCODING", string2);
            this.b = string2.toUpperCase();
            return;
        }
        throw new afn("Unknown encoding \"" + string2 + "\"");
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    protected boolean e() {
        this.b = "8BIT";
        afj afj2 = this.b(this.c());
        String string2 = afj2.a().toUpperCase();
        String string3 = afj2.c();
        if (string2.equals((Object)"BEGIN")) {
            if (!string3.equalsIgnoreCase("VCARD")) throw new afn("Unknown BEGIN type: " + string3);
            this.m();
            do {
                return false;
                break;
            } while (true);
        }
        if (string2.equals((Object)"END")) {
            if (!string3.equalsIgnoreCase("VCARD")) throw new afn("Unknown END type: " + string3);
            return true;
        }
        this.i(afj2, string2);
        return false;
    }

    protected int f() {
        return 0;
    }

    protected void f(afj afj2, String string2) {
        this.c = string2;
        afj2.a("CHARSET", string2);
    }

    protected String g() {
        return "2.1";
    }

    protected void g(afj afj2, String string2) {
        int n2;
        int n3 = 0;
        String[] arrstring = string2.split("-");
        if (arrstring.length != 2) {
            throw new afn("Invalid Language: \"" + string2 + "\"");
        }
        String string3 = arrstring[0];
        int n4 = string3.length();
        for (n2 = 0; n2 < n4; ++n2) {
            if (this.b(string3.charAt(n2))) continue;
            throw new afn("Invalid Language: \"" + string2 + "\"");
        }
        arrstring = arrstring[1];
        n4 = arrstring.length();
        for (n2 = n3; n2 < n4; ++n2) {
            if (this.b(arrstring.charAt(n2))) continue;
            throw new afn("Invalid Language: \"" + string2 + "\"");
        }
        afj2.a("LANGUAGE", string2);
    }

    protected Set<String> h() {
        return afg.a;
    }

    /*
     * Exception decompiling
     */
    protected void h(afj var1_1, String var2_2) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 4[CATCHBLOCK]
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:394)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:446)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:2859)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:805)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:220)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:165)
        // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:91)
        // org.benf.cfr.reader.entities.Method.analyse(Method.java:354)
        // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:751)
        // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:683)
        // org.benf.cfr.reader.Main.doJar(Main.java:128)
        // org.benf.cfr.reader.Main.main(Main.java:178)
        throw new IllegalStateException("Decompilation failed");
    }

    protected Set<String> i() {
        return afg.b;
    }

    protected Set<String> j() {
        return afg.c;
    }

    protected Set<String> k() {
        return afg.d;
    }

    public static final class a
    extends BufferedReader {
        private long a;
        private boolean b;
        private String c;

        public a(Reader reader) {
            super(reader);
        }

        public String a() {
            if (!this.b) {
                long l2 = System.currentTimeMillis();
                String string2 = super.readLine();
                this.a = System.currentTimeMillis() - l2 + this.a;
                this.c = string2;
                this.b = true;
            }
            return this.c;
        }

        public String readLine() {
            if (this.b) {
                String string2 = this.c;
                this.c = null;
                this.b = false;
                return string2;
            }
            long l2 = System.currentTimeMillis();
            String string3 = super.readLine();
            this.a = System.currentTimeMillis() - l2 + this.a;
            return string3;
        }
    }

}

