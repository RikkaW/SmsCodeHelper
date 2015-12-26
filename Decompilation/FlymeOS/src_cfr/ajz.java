/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.ByteArrayOutputStream
 *  java.io.OutputStream
 *  java.lang.Object
 *  java.lang.String
 *  org.apache.http.util.EncodingUtils
 */
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import org.apache.http.util.EncodingUtils;

public abstract class ajz {
    protected static final byte[] a;
    protected static final byte[] b;
    protected static final byte[] c;
    protected static final byte[] d;
    protected static final byte[] e;
    protected static final byte[] f;
    protected static final byte[] g;
    protected static final byte[] h;
    private static final byte[] i;
    private byte[] j;

    static {
        i = ajz.a = EncodingUtils.getAsciiBytes((String)"----------------314159265358979323846");
        b = EncodingUtils.getAsciiBytes((String)"\r\n");
        c = EncodingUtils.getAsciiBytes((String)"\"");
        d = EncodingUtils.getAsciiBytes((String)"--");
        e = EncodingUtils.getAsciiBytes((String)"Content-Disposition: form-data; name=");
        f = EncodingUtils.getAsciiBytes((String)"Content-Type: ");
        g = EncodingUtils.getAsciiBytes((String)"; charset=");
        h = EncodingUtils.getAsciiBytes((String)"Content-Transfer-Encoding: ");
    }

    public static long a(ajz[] arrajz, byte[] arrby) {
        if (arrajz == null) {
            throw new IllegalArgumentException("Parts may not be null");
        }
        long l2 = 0;
        for (int i2 = 0; i2 < arrajz.length; ++i2) {
            arrajz[i2].a(arrby);
            long l3 = arrajz[i2].h();
            if (l3 < 0) {
                return -1;
            }
            l2 += l3;
        }
        return (long)d.length + l2 + (long)arrby.length + (long)d.length + (long)b.length;
    }

    public static void a(OutputStream outputStream, ajz[] arrajz, byte[] arrby) {
        if (arrajz == null) {
            throw new IllegalArgumentException("Parts may not be null");
        }
        if (arrby == null || arrby.length == 0) {
            throw new IllegalArgumentException("partBoundary may not be empty");
        }
        for (int i2 = 0; i2 < arrajz.length; ++i2) {
            arrajz[i2].a(arrby);
            arrajz[i2].h(outputStream);
        }
        outputStream.write(d);
        outputStream.write(arrby);
        outputStream.write(d);
        outputStream.write(b);
    }

    protected abstract long a();

    protected void a(OutputStream outputStream) {
        outputStream.write(e);
        outputStream.write(c);
        outputStream.write(EncodingUtils.getAsciiBytes((String)this.b()));
        outputStream.write(c);
    }

    void a(byte[] arrby) {
        this.j = arrby;
    }

    public abstract String b();

    protected abstract void b(OutputStream var1);

    public abstract String c();

    protected void c(OutputStream outputStream) {
        outputStream.write(d);
        outputStream.write(this.f());
        outputStream.write(b);
    }

    public abstract String d();

    protected void d(OutputStream outputStream) {
        String string2 = this.c();
        if (string2 != null) {
            outputStream.write(b);
            outputStream.write(f);
            outputStream.write(EncodingUtils.getAsciiBytes((String)string2));
            string2 = this.d();
            if (string2 != null) {
                outputStream.write(g);
                outputStream.write(EncodingUtils.getAsciiBytes((String)string2));
            }
        }
    }

    public abstract String e();

    protected void e(OutputStream outputStream) {
        String string2 = this.e();
        if (string2 != null) {
            outputStream.write(b);
            outputStream.write(h);
            outputStream.write(EncodingUtils.getAsciiBytes((String)string2));
        }
    }

    protected void f(OutputStream outputStream) {
        outputStream.write(b);
        outputStream.write(b);
    }

    protected byte[] f() {
        if (this.j == null) {
            return i;
        }
        return this.j;
    }

    protected void g(OutputStream outputStream) {
        outputStream.write(b);
    }

    public boolean g() {
        return true;
    }

    public long h() {
        if (this.a() < 0) {
            return -1;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        this.c((OutputStream)byteArrayOutputStream);
        this.a((OutputStream)byteArrayOutputStream);
        this.d((OutputStream)byteArrayOutputStream);
        this.e((OutputStream)byteArrayOutputStream);
        this.f((OutputStream)byteArrayOutputStream);
        this.g((OutputStream)byteArrayOutputStream);
        return (long)byteArrayOutputStream.size() + this.a();
    }

    public void h(OutputStream outputStream) {
        this.c(outputStream);
        this.a(outputStream);
        this.d(outputStream);
        this.e(outputStream);
        this.f(outputStream);
        this.b(outputStream);
        this.g(outputStream);
    }

    public String toString() {
        return this.b();
    }
}

