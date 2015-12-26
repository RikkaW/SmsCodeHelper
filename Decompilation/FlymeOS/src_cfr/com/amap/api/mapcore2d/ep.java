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
package com.amap.api.mapcore2d;

import com.amap.api.mapcore2d.eq;
import com.amap.api.mapcore2d.er;
import com.amap.api.mapcore2d.es;
import com.amap.api.mapcore2d.et;
import com.amap.api.mapcore2d.eu;
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

public final class ep
implements Closeable {
    static final Pattern a = Pattern.compile((String)"[a-z0-9_-]{1,120}");
    private static final OutputStream q = new er();
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
    private final LinkedHashMap<String, c> l = new LinkedHashMap(0, 0.75f, true);
    private int m;
    private es n;
    private long o = 0;
    private final Callable<Void> p;

    private ep(File file, int n2, int n3, long l2) {
        this.p = new eq(this);
        this.c = file;
        this.g = n2;
        this.d = new File(file, "journal");
        this.e = new File(file, "journal.tmp");
        this.f = new File(file, "journal.bkp");
        this.i = n3;
        this.h = l2;
    }

    static /* synthetic */ int a(ep ep2, int n2) {
        ep2.m = n2;
        return n2;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private a a(String object, long l2) {
        synchronized (this) {
            a a2;
            this.i();
            this.e((String)object);
            c c2 = this.l.get(object);
            if (l2 != -1) {
                if (c2 == null) return null;
                long l3 = c2.f;
                if (l3 != l2) {
                    return null;
                }
            }
            if (c2 == null) {
                c2 = new c((String)object, null);
                this.l.put(object, (Object)c2);
            } else {
                a2 = c2.e;
                if (a2 != null) {
                    return null;
                }
            }
            a2 = new a(c2, null);
            c2.e = a2;
            this.k.write("DIRTY " + (String)object + '\n');
            this.k.flush();
            return a2;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static ep a(File object, int n2, int n3, long l2) {
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
                ep.a((File)object2, file, false);
            }
        }
        object2 = new ep((File)object, n2, n3, l2);
        if (object2.d.exists()) {
            try {
                object2.e();
                object2.f();
                object2.k = new BufferedWriter((Writer)new OutputStreamWriter((OutputStream)new FileOutputStream(object2.d, true), eu.a));
                return object2;
            }
            catch (IOException var6_6) {
                System.out.println("DiskLruCache " + object + " is corrupt: " + var6_6.getMessage() + ", removing");
                object2.c();
            }
        }
        object.mkdirs();
        object = new ep((File)object, n2, n3, l2);
        object.g();
        return object;
    }

    static /* synthetic */ Writer a(ep ep2) {
        return ep2.k;
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
            if (c.a(var10_4) != var1_1) {
                throw new IllegalStateException();
            }
            var4_5 = var5_3;
            if (var2_2) {
                var4_5 = var5_3;
                if (!c.d(var10_4)) {
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
                        var6_7 = c.b(var10_4)[var4_5];
                        c.b((c)var10_4)[var4_5] = var8_8 = var11_9.length();
                        this.j = this.j - var6_7 + var8_8;
                    }
                } else {
                    ep.a((File)var1_1);
                }
            } else {
                ++this.m;
                c.a(var10_4, null);
                if (c.d(var10_4) | var2_2) {
                    c.a(var10_4, true);
                    this.k.write("CLEAN " + c.c(var10_4) + var10_4.a() + '\n');
                    if (var2_2) {
                        var6_7 = this.o;
                        this.o = 1 + var6_7;
                        c.a(var10_4, var6_7);
                    }
                } else {
                    this.l.remove((Object)c.c(var10_4));
                    this.k.write("REMOVE " + c.c(var10_4) + '\n');
                }
                this.k.flush();
                if (this.j <= this.h && !this.h()) ** continue;
                this.b.submit(this.p);
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
            ep.a(file2);
        }
        if (!file.renameTo(file2)) {
            throw new IOException();
        }
    }

    static /* synthetic */ void b(ep ep2) {
        ep2.j();
    }

    static /* synthetic */ boolean c(ep ep2) {
        return ep2.h();
    }

    static /* synthetic */ void d(ep ep2) {
        ep2.g();
    }

    /*
     * Enabled aggressive block sorting
     */
    private void d(String arrstring) {
        c c2;
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
        c c3 = c2 = this.l.get(string2);
        if (c2 == null) {
            c3 = new c(string2, null);
            this.l.put((Object)string2, (Object)c3);
        }
        if (n4 != -1 && n2 == "CLEAN".length() && arrstring.startsWith("CLEAN")) {
            arrstring = arrstring.substring(n4 + 1).split(" ");
            c3.d = true;
            c3.e = null;
            c3.a(arrstring);
            return;
        }
        if (n4 == -1 && n2 == "DIRTY".length() && arrstring.startsWith("DIRTY")) {
            c3.e = new a(c3, null);
            return;
        }
        if (n4 == -1 && n2 == "READ".length() && arrstring.startsWith("READ")) return;
        {
            throw new IOException("unexpected journal line: " + (String)arrstring);
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private void e() {
        var2_1 = new et((InputStream)new FileInputStream(this.d), eu.a);
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
            eu.a(var2_1);
        }
lbl18: // 1 sources:
        var1_9 = 0;
        do {
            this.d(var2_1.a());
            ++var1_9;
        } while (true);
    }

    private void e(String string2) {
        if (!a.matcher((CharSequence)string2).matches()) {
            throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,120}: \"" + string2 + "\"");
        }
    }

    private void f() {
        ep.a(this.e);
        Iterator<c> iterator = this.l.values().iterator();
        while (iterator.hasNext()) {
            int n2;
            c c2 = iterator.next();
            if (c2.e == null) {
                for (n2 = 0; n2 < this.i; ++n2) {
                    this.j += c2.c[n2];
                }
                continue;
            }
            c2.e = null;
            for (n2 = 0; n2 < this.i; ++n2) {
                ep.a(c2.a(n2));
                ep.a(c2.b(n2));
            }
            iterator.remove();
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void g() {
        synchronized (this) {
            if (this.k != null) {
                this.k.close();
            }
            BufferedWriter bufferedWriter = new BufferedWriter((Writer)new OutputStreamWriter((OutputStream)new FileOutputStream(this.e), eu.a));
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
                for (c c2 : this.l.values()) {
                    if (c2.e != null) {
                        bufferedWriter.write("DIRTY " + c2.b + '\n');
                        continue;
                    }
                    bufferedWriter.write("CLEAN " + c2.b + c2.a() + '\n');
                }
            }
            finally {
                bufferedWriter.close();
            }
            if (this.d.exists()) {
                ep.a(this.d, this.f, true);
            }
            ep.a(this.e, this.d, false);
            this.f.delete();
            this.k = new BufferedWriter((Writer)new OutputStreamWriter((OutputStream)new FileOutputStream(this.d, true), eu.a));
            return;
        }
    }

    private boolean h() {
        if (this.m >= 2000 && this.m >= this.l.size()) {
            return true;
        }
        return false;
    }

    private void i() {
        if (this.k == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    private void j() {
        while (this.j > this.h) {
            String string2 = (String)this.l.entrySet().iterator().next().getKey();
            this.c(string2);
            if (this.n == null) continue;
            this.n.a(string2);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public b a(String string2) {
        b b2 = null;
        synchronized (this) {
            int n2;
            this.i();
            this.e(string2);
            c c2 = this.l.get(string2);
            if (c2 == null) {
                return b2;
            }
            b b3 = b2;
            if (!c2.d) return b3;
            InputStream[] arrinputStream = new InputStream[this.i];
            try {
                for (n2 = 0; n2 < this.i; ++n2) {
                    arrinputStream[n2] = new FileInputStream(c2.a(n2));
                }
            }
            catch (FileNotFoundException var1_2) {
                n2 = 0;
                do {
                    b3 = b2;
                    if (n2 >= this.i) return b3;
                    b3 = b2;
                    if (arrinputStream[n2] == null) return b3;
                    eu.a(arrinputStream[n2]);
                    ++n2;
                } while (true);
            }
            ++this.m;
            this.k.append((CharSequence)("READ " + string2 + '\n'));
            if (!this.h()) return new b(string2, c2.f, arrinputStream, c2.c, null);
            this.b.submit(this.p);
            return new b(string2, c2.f, arrinputStream, c2.c, null);
        }
    }

    public void a(es es2) {
        this.n = es2;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean a() {
        synchronized (this) {
            Writer writer = this.k;
            if (writer != null) return false;
            return true;
        }
    }

    public a b(String string2) {
        return this.a(string2, -1);
    }

    public void b() {
        synchronized (this) {
            this.i();
            this.j();
            this.k.flush();
            return;
        }
    }

    public void c() {
        this.close();
        eu.a(this.c);
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
            this.i();
            this.e(var1_1);
            var4_3 = this.l.get(var1_1);
            if (var4_3 != null && (var5_4 = c.a(var4_3)) == null) ** GOTO lbl13
            var3_5 = false;
            // MONITOREXIT : this
            return var3_5;
lbl-1000: // 1 sources:
            {
                this.j -= c.b(var4_3)[var2_2];
                c.b((c)var4_3)[var2_2] = 0;
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
        if (this.h() == false) return true;
        this.b.submit(this.p);
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
                for (c c2 : new ArrayList(this.l.values())) {
                    if (c2.e == null) continue;
                    c2.e.b();
                }
                this.j();
                this.k.close();
                this.k = null;
            }
            return;
        }
    }

    public final class com.amap.api.mapcore2d.ep$a {
        private final c b;
        private final boolean[] c;
        private boolean d;
        private boolean e;

        /*
         * Enabled aggressive block sorting
         */
        private com.amap.api.mapcore2d.ep$a(c c2) {
            void var1_3;
            void var2_5;
            this.b = var2_5;
            if (((c)var2_5).d) {
                Object var1_2 = null;
            } else {
                boolean[] arrbl = new boolean[ep.this.i];
            }
            this.c = var1_3;
        }

        /* synthetic */ com.amap.api.mapcore2d.ep$a(c c2, eq eq2) {
            this(c2);
        }

        static /* synthetic */ c a(com.amap.api.mapcore2d.ep$a a2) {
            return a2.b;
        }

        static /* synthetic */ boolean[] b(com.amap.api.mapcore2d.ep$a a2) {
            return a2.c;
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        public OutputStream a(int n2) {
            if (n2 < 0) throw new IllegalArgumentException("Expected index " + n2 + " to " + "be greater than 0 and less than the maximum value count " + "of " + ep.this.i);
            if (n2 >= ep.this.i) {
                throw new IllegalArgumentException("Expected index " + n2 + " to " + "be greater than 0 and less than the maximum value count " + "of " + ep.this.i);
            }
            ep ep2 = ep.this;
            synchronized (ep2) {
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
                    ep.this.c.mkdirs();
                    try {
                        object = new FileOutputStream(file);
                    }
                    catch (FileNotFoundException var2_6) {
                        return q;
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
                ep.this.a(this, false);
                ep.this.c(this.b.b);
            } else {
                ep.this.a(this, true);
            }
            this.e = true;
        }

        public void b() {
            ep.this.a(this, false);
        }

        class a
        extends FilterOutputStream {
            private a(OutputStream outputStream) {
                super(outputStream);
            }

            /* synthetic */ a(OutputStream outputStream, eq eq2) {
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

    public final class b
    implements Closeable {
        private final String b;
        private final long c;
        private final InputStream[] d;
        private final long[] e;

        private b(String string2, long l2, InputStream[] arrinputStream, long[] arrl) {
            this.b = string2;
            this.c = l2;
            this.d = arrinputStream;
            this.e = arrl;
        }

        /* synthetic */ b(String string2, long l2, InputStream[] arrinputStream, long[] arrl, eq eq2) {
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
                eu.a(arrinputStream[i2]);
            }
        }
    }

    final class c {
        private final String b;
        private final long[] c;
        private boolean d;
        private a e;
        private long f;

        private c(String string2) {
            this.b = string2;
            this.c = new long[ep.this.i];
        }

        /* synthetic */ c(String string2, eq eq2) {
            this(string2);
        }

        static /* synthetic */ long a(c c2, long l2) {
            c2.f = l2;
            return l2;
        }

        private void a(String[] arrstring) {
            if (arrstring.length != ep.this.i) {
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
            return new File(ep.this.c, this.b + "." + n2);
        }

        public String a() {
            StringBuilder stringBuilder = new StringBuilder();
            for (long l2 : this.c) {
                stringBuilder.append(' ').append(l2);
            }
            return stringBuilder.toString();
        }

        public File b(int n2) {
            return new File(ep.this.c, this.b + "." + n2 + ".tmp");
        }
    }

}

