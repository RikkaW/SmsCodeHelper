/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.BufferedWriter
 *  java.io.File
 *  java.io.FileInputStream
 *  java.io.FileNotFoundException
 *  java.io.FileOutputStream
 *  java.io.FilterOutputStream
 *  java.io.OutputStream
 *  java.io.OutputStreamWriter
 *  java.io.PrintStream
 *  java.io.Writer
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.nio.charset.Charset
 *  java.util.ArrayList
 *  java.util.Arrays
 *  java.util.Map$Entry
 *  java.util.concurrent.LinkedBlockingQueue
 *  java.util.concurrent.ThreadPoolExecutor
 *  java.util.concurrent.TimeUnit
 *  java.util.regex.Matcher
 *  java.util.regex.Pattern
 */
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ahl
implements Closeable {
    static final Pattern a = Pattern.compile((String)"[a-z0-9_-]{1,120}");
    private static final OutputStream p = new ahn();
    final ThreadPoolExecutor b = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, (BlockingQueue)new LinkedBlockingQueue());
    private final File c;
    private final File d;
    private final File e;
    private final File f;
    private final int g;
    private long h;
    private final int i;
    private long j = 0;
    private Writer k;
    private final LinkedHashMap<String, b> l = new LinkedHashMap(0, 0.75f, true);
    private int m;
    private long n = 0;
    private final Callable<Void> o;

    private ahl(File file, int n2, int n3, long l2) {
        this.o = new ahm(this);
        this.c = file;
        this.g = n2;
        this.d = new File(file, "journal");
        this.e = new File(file, "journal.tmp");
        this.f = new File(file, "journal.bkp");
        this.i = n3;
        this.h = l2;
    }

    static /* synthetic */ int a(ahl ahl2, int n2) {
        ahl2.m = n2;
        return n2;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    private a a(String object, long l2) {
        a a2;
        // MONITORENTER : this
        Object object2 = this.k;
        if (object2 == null) {
            object = null;
            // MONITOREXIT : this
            return object;
        }
        this.g();
        this.e((String)object);
        object2 = this.l.get(object);
        if (l2 != -1) {
            if (object2 == null) return null;
            if (((b)object2).f != l2) return null;
        }
        if (object2 == null) {
            object2 = new b((String)object, null);
            this.l.put(object, object2);
        } else {
            a2 = ((b)object2).e;
            if (a2 != null) {
                return null;
            }
        }
        a2 = new a((b)object2, null);
        ((b)object2).e = a2;
        this.k.write("DIRTY " + (String)object + '\n');
        this.k.flush();
        return a2;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static ahl a(File object, int n2, int n3, long l2) {
        if (l2 <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        if (n3 <= 0) {
            throw new IllegalArgumentException("valueCount <= 0");
        }
        Object object2 = new File((File)object, "journal.bkp");
        if (object2.exists()) {
            File file = new File((File)object, "journal");
            if (file.exists()) {
                object2.delete();
            } else {
                ahl.a((File)object2, file, false);
            }
        }
        object2 = new ahl((File)object, n2, n3, l2);
        if (object2.d.exists()) {
            try {
                object2.c();
                object2.d();
                object2.k = new BufferedWriter((Writer)new OutputStreamWriter((OutputStream)new FileOutputStream(object2.d, true), ahy.a));
                return object2;
            }
            catch (IOException var6_6) {
                System.out.println("DiskLruCache " + object + " is corrupt: " + var6_6.getMessage() + ", removing");
                object2.a();
            }
        }
        object.mkdirs();
        object = new ahl((File)object, n2, n3, l2);
        object.e();
        return object;
    }

    static /* synthetic */ Writer a(ahl ahl2) {
        return ahl2.k;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    private void a(a var1_1, boolean var2_2) {
        block20 : {
            var5_3 = 0;
            // MONITORENTER : this
            var10_4 = a.a(var1_1);
            if (b.a(var10_4) != var1_1) {
                throw new IllegalStateException();
            }
            var4_5 = var5_3;
            if (var2_2) {
                var4_5 = var5_3;
                if (!b.d(var10_4)) {
                    var3_6 = 0;
                    do {
                        var4_5 = var5_3;
                        if (var3_6 >= this.i) break block20;
                        if (!a.b(var1_1)[var3_6]) {
                            var1_1.b();
                            throw new IllegalStateException("Newly created entry didn't create value for index " + var3_6);
                        }
                        if (!var10_4.b(var3_6).exists()) {
                            var1_1.b();
                            return;
                        }
                        ++var3_6;
                    } while (true);
                    do {
                        // MONITOREXIT : this
                        return;
                        break;
                    } while (true);
                }
            }
        }
        do {
            if (var4_5 < this.i) {
                var1_1 = var10_4.b(var4_5);
                if (var2_2) {
                    if (var1_1.exists()) {
                        var11_9 = var10_4.a(var4_5);
                        var1_1.renameTo(var11_9);
                        var6_7 = b.b(var10_4)[var4_5];
                        b.b((b)var10_4)[var4_5] = var8_8 = var11_9.length();
                        this.j = this.j - var6_7 + var8_8;
                    }
                } else {
                    ahl.a((File)var1_1);
                }
            } else {
                ++this.m;
                b.a(var10_4, null);
                if (b.d(var10_4) | var2_2) {
                    b.a(var10_4, true);
                    this.k.write("CLEAN " + b.c(var10_4) + var10_4.a() + '\n');
                    if (var2_2) {
                        var6_7 = this.n;
                        this.n = 1 + var6_7;
                        b.a(var10_4, var6_7);
                    }
                } else {
                    this.l.remove((Object)b.c(var10_4));
                    this.k.write("REMOVE " + b.c(var10_4) + '\n');
                }
                this.k.flush();
                if (this.j <= this.h && !this.f()) ** continue;
                this.b.submit(this.o);
                return;
            }
            ++var4_5;
        } while (true);
    }

    private static void a(File file) {
        if (file.exists() && !file.delete()) {
            throw new IOException();
        }
    }

    private static void a(File file, File file2, boolean bl2) {
        if (bl2) {
            ahl.a(file2);
        }
        if (!file.renameTo(file2)) {
            throw new IOException();
        }
    }

    static /* synthetic */ void b(ahl ahl2) {
        ahl2.h();
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private void c() {
        var2_1 = new ahw((InputStream)new FileInputStream(this.d), ahy.a);
        var3_2 = var2_1.a();
        var4_5 = var2_1.a();
        var5_6 = var2_1.a();
        var6_7 = var2_1.a();
        var7_8 = var2_1.a();
        if ("libcore.io.DiskLruCache".equals((Object)var3_2) == false) throw new IOException("unexpected journal header: [" + var3_2 + ", " + var4_5 + ", " + var6_7 + ", " + var7_8 + "]");
        if ("1".equals((Object)var4_5) == false) throw new IOException("unexpected journal header: [" + var3_2 + ", " + var4_5 + ", " + var6_7 + ", " + var7_8 + "]");
        if (Integer.toString((int)this.g).equals((Object)var5_6) == false) throw new IOException("unexpected journal header: [" + var3_2 + ", " + var4_5 + ", " + var6_7 + ", " + var7_8 + "]");
        if (Integer.toString((int)this.i).equals((Object)var6_7) == false) throw new IOException("unexpected journal header: [" + var3_2 + ", " + var4_5 + ", " + var6_7 + ", " + var7_8 + "]");
        if (!"".equals((Object)var7_8)) {
            throw new IOException("unexpected journal header: [" + var3_2 + ", " + var4_5 + ", " + var6_7 + ", " + var7_8 + "]");
        }
        ** GOTO lbl18
        finally {
            ahy.a(var2_1);
        }
lbl18: // 1 sources:
        var1_9 = 0;
        do {
            this.d(var2_1.a());
            ++var1_9;
        } while (true);
    }

    static /* synthetic */ boolean c(ahl ahl2) {
        return ahl2.f();
    }

    private void d() {
        ahl.a(this.e);
        Iterator<b> iterator = this.l.values().iterator();
        while (iterator.hasNext()) {
            int n2;
            b b2 = iterator.next();
            if (b2.e == null) {
                for (n2 = 0; n2 < this.i; ++n2) {
                    this.j += b2.c[n2];
                }
                continue;
            }
            b2.e = null;
            for (n2 = 0; n2 < this.i; ++n2) {
                ahl.a(b2.a(n2));
                ahl.a(b2.b(n2));
            }
            iterator.remove();
        }
    }

    static /* synthetic */ void d(ahl ahl2) {
        ahl2.e();
    }

    /*
     * Enabled aggressive block sorting
     */
    private void d(String arrstring) {
        b b2;
        String string2;
        int n2 = arrstring.indexOf(32);
        if (n2 == -1) {
            throw new IOException("unexpected journal line: " + (String)arrstring);
        }
        int n3 = n2 + 1;
        int n4 = arrstring.indexOf(32, n3);
        if (n4 == -1) {
            string2 = arrstring.substring(n3);
            if (n2 == "REMOVE".length() && arrstring.startsWith("REMOVE")) {
                this.l.remove((Object)string2);
                return;
            }
        } else {
            string2 = arrstring.substring(n3, n4);
        }
        b b3 = b2 = this.l.get(string2);
        if (b2 == null) {
            b3 = new b(string2, null);
            this.l.put((Object)string2, (Object)b3);
        }
        if (n4 != -1 && n2 == "CLEAN".length() && arrstring.startsWith("CLEAN")) {
            arrstring = arrstring.substring(n4 + 1).split(" ");
            b3.d = true;
            b3.e = null;
            b3.a(arrstring);
            return;
        }
        if (n4 == -1 && n2 == "DIRTY".length() && arrstring.startsWith("DIRTY")) {
            b3.e = new a(b3, null);
            return;
        }
        if (n4 == -1 && n2 == "READ".length() && arrstring.startsWith("READ")) return;
        {
            throw new IOException("unexpected journal line: " + (String)arrstring);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void e() {
        synchronized (this) {
            if (this.k != null) {
                this.k.close();
            }
            BufferedWriter bufferedWriter = new BufferedWriter((Writer)new OutputStreamWriter((OutputStream)new FileOutputStream(this.e), ahy.a));
            try {
                bufferedWriter.write("libcore.io.DiskLruCache");
                bufferedWriter.write("\n");
                bufferedWriter.write("1");
                bufferedWriter.write("\n");
                bufferedWriter.write(Integer.toString((int)this.g));
                bufferedWriter.write("\n");
                bufferedWriter.write(Integer.toString((int)this.i));
                bufferedWriter.write("\n");
                bufferedWriter.write("\n");
                for (b b2 : this.l.values()) {
                    if (b2.e != null) {
                        bufferedWriter.write("DIRTY " + b2.b + '\n');
                        continue;
                    }
                    bufferedWriter.write("CLEAN " + b2.b + b2.a() + '\n');
                }
            }
            finally {
                bufferedWriter.close();
            }
            if (this.d.exists()) {
                ahl.a(this.d, this.f, true);
            }
            ahl.a(this.e, this.d, false);
            this.f.delete();
            this.k = new BufferedWriter((Writer)new OutputStreamWriter((OutputStream)new FileOutputStream(this.d, true), ahy.a));
            return;
        }
    }

    private void e(String string2) {
        if (!a.matcher((CharSequence)string2).matches()) {
            throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,120}: \"" + string2 + "\"");
        }
    }

    private boolean f() {
        if (this.m >= 2000 && this.m >= this.l.size()) {
            return true;
        }
        return false;
    }

    private void g() {
        if (this.k == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    private void h() {
        while (this.j > this.h) {
            this.c((String)this.l.entrySet().iterator().next().getKey());
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public c a(String string2) {
        Object var4_3 = null;
        synchronized (this) {
            int n2;
            Object object = this.k;
            if (object == null) {
                return var4_3;
            }
            this.g();
            this.e(string2);
            b b2 = this.l.get(string2);
            object = var4_3;
            if (b2 == null) return object;
            object = var4_3;
            if (!b2.d) return object;
            InputStream[] arrinputStream = new InputStream[this.i];
            try {
                for (n2 = 0; n2 < this.i; ++n2) {
                    arrinputStream[n2] = new FileInputStream(b2.a(n2));
                }
            }
            catch (FileNotFoundException var1_2) {
                n2 = 0;
                do {
                    object = var4_3;
                    if (n2 >= this.i) return object;
                    object = var4_3;
                    if (arrinputStream[n2] == null) return object;
                    ahy.a(arrinputStream[n2]);
                    ++n2;
                } while (true);
            }
            ++this.m;
            this.k.append((CharSequence)("READ " + string2 + '\n'));
            if (!this.f()) return new c(string2, b2.f, arrinputStream, b2.c, null);
            this.b.submit(this.o);
            return new c(string2, b2.f, arrinputStream, b2.c, null);
        }
    }

    public void a() {
        this.close();
        ahy.a(this.c);
    }

    public a b(String string2) {
        return this.a(string2, -1);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    public boolean c(String var1_1) {
        block6 : {
            var2_2 = 0;
            // MONITORENTER : this
            this.g();
            this.e(var1_1);
            var4_3 = this.l.get(var1_1);
            if (var4_3 != null && (var5_4 = b.a(var4_3)) == null) ** GOTO lbl13
            var3_5 = false;
            // MONITOREXIT : this
            return var3_5;
lbl-1000: // 1 sources:
            {
                this.j -= b.b(var4_3)[var2_2];
                b.b((b)var4_3)[var2_2] = 0;
                ++var2_2;
lbl13: // 2 sources:
                if (var2_2 >= this.i) break block6;
                ** while (!(var5_4 = var4_3.a((int)var2_2)).exists() || var5_4.delete())
            }
lbl15: // 1 sources:
            throw new IOException("failed to delete " + var5_4);
        }
        ++this.m;
        this.k.append((CharSequence)("REMOVE " + var1_1 + '\n'));
        this.l.remove((Object)var1_1);
        if (this.f() == false) return true;
        this.b.submit(this.o);
        return true;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void close() {
        synchronized (this) {
            Writer writer = this.k;
            if (writer != null) {
                for (b b2 : new ArrayList(this.l.values())) {
                    if (b2.e == null) continue;
                    b2.e.b();
                }
                this.h();
                this.k.close();
                this.k = null;
            }
            return;
        }
    }

    public final class ahl$a {
        private final b b;
        private final boolean[] c;
        private boolean d;
        private boolean e;

        /*
         * Enabled aggressive block sorting
         */
        private ahl$a(b b2) {
            void var1_3;
            void var2_5;
            this.b = var2_5;
            if (((b)var2_5).d) {
                Object var1_2 = null;
            } else {
                boolean[] arrbl = new boolean[ahl.this.i];
            }
            this.c = var1_3;
        }

        /* synthetic */ ahl$a(b b2, ahm ahm2) {
            this(b2);
        }

        static /* synthetic */ b a(ahl$a a2) {
            return a2.b;
        }

        static /* synthetic */ boolean[] b(ahl$a a2) {
            return a2.c;
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        public OutputStream a(int n2) {
            if (n2 < 0) throw new IllegalArgumentException("Expected index " + n2 + " to " + "be greater than 0 and less than the maximum value count " + "of " + ahl.this.i);
            if (n2 >= ahl.this.i) {
                throw new IllegalArgumentException("Expected index " + n2 + " to " + "be greater than 0 and less than the maximum value count " + "of " + ahl.this.i);
            }
            ahl ahl2 = ahl.this;
            synchronized (ahl2) {
                Object object;
                if (this.b.e != this) {
                    throw new IllegalStateException();
                }
                if (!this.b.d) {
                    this.c[n2] = true;
                }
                File file = this.b.b(n2);
                try {
                    object = new FileOutputStream(file);
                    return new a((OutputStream)object, null);
                }
                catch (FileNotFoundException var2_5) {
                    ahl.this.c.mkdirs();
                    try {
                        object = new FileOutputStream(file);
                    }
                    catch (FileNotFoundException var2_6) {
                        return p;
                    }
                    return new a((OutputStream)object, null);
                }
            }
        }

        /*
         * Enabled aggressive block sorting
         */
        public void a() {
            if (this.d) {
                ahl.this.a(this, false);
                ahl.this.c(this.b.b);
            } else {
                ahl.this.a(this, true);
            }
            this.e = true;
        }

        public void b() {
            ahl.this.a(this, false);
        }

        class a
        extends FilterOutputStream {
            private a(OutputStream outputStream) {
                super(outputStream);
            }

            /* synthetic */ a(OutputStream outputStream, ahm ahm2) {
                this(outputStream);
            }

            public void close() {
                try {
                    this.out.close();
                    return;
                }
                catch (IOException var1_1) {
                    a.this.d = true;
                    return;
                }
            }

            public void flush() {
                try {
                    this.out.flush();
                    return;
                }
                catch (IOException var1_1) {
                    a.this.d = true;
                    return;
                }
            }

            public void write(int n2) {
                try {
                    this.out.write(n2);
                    return;
                }
                catch (IOException var2_2) {
                    a.this.d = true;
                    return;
                }
            }

            public void write(byte[] arrby, int n2, int n3) {
                try {
                    this.out.write(arrby, n2, n3);
                    return;
                }
                catch (IOException var1_2) {
                    a.this.d = true;
                    return;
                }
            }
        }

    }

    final class b {
        private final String b;
        private final long[] c;
        private boolean d;
        private a e;
        private long f;

        private b(String string2) {
            this.b = string2;
            this.c = new long[ahl.this.i];
        }

        /* synthetic */ b(String string2, ahm ahm2) {
            this(string2);
        }

        static /* synthetic */ long a(b b2, long l2) {
            b2.f = l2;
            return l2;
        }

        private void a(String[] arrstring) {
            if (arrstring.length != ahl.this.i) {
                throw this.b(arrstring);
            }
            int n2 = 0;
            do {
                try {
                    if (n2 >= arrstring.length) break;
                    this.c[n2] = Long.parseLong((String)arrstring[n2]);
                    ++n2;
                    continue;
                }
                catch (NumberFormatException var3_3) {
                    throw this.b(arrstring);
                }
            } while (true);
        }

        private IOException b(String[] arrstring) {
            throw new IOException("unexpected journal line: " + Arrays.toString((Object[])arrstring));
        }

        public File a(int n2) {
            return new File(ahl.this.c, this.b + "." + n2);
        }

        public String a() {
            StringBuilder stringBuilder = new StringBuilder();
            for (long l2 : this.c) {
                stringBuilder.append(' ').append(l2);
            }
            return stringBuilder.toString();
        }

        public File b(int n2) {
            return new File(ahl.this.c, this.b + "." + n2 + ".tmp");
        }
    }

    public final class c
    implements Closeable {
        private final String b;
        private final long c;
        private final InputStream[] d;
        private final long[] e;

        private c(String string2, long l2, InputStream[] arrinputStream, long[] arrl) {
            this.b = string2;
            this.c = l2;
            this.d = arrinputStream;
            this.e = arrl;
        }

        /* synthetic */ c(String string2, long l2, InputStream[] arrinputStream, long[] arrl, ahm ahm2) {
            this(string2, l2, arrinputStream, arrl);
        }

        public InputStream a(int n2) {
            return this.d[n2];
        }

        @Override
        public void close() {
            InputStream[] arrinputStream = this.d;
            int n2 = arrinputStream.length;
            for (int i2 = 0; i2 < n2; ++i2) {
                ahy.a(arrinputStream[i2]);
            }
        }
    }

}

