/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.nio.charset.Charset
 */
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

class ahw
implements Closeable {
    private final InputStream a;
    private final Charset b;
    private byte[] c;
    private int d;
    private int e;

    public ahw(InputStream inputStream, int n2, Charset charset) {
        if (inputStream == null || charset == null) {
            throw new NullPointerException();
        }
        if (n2 < 0) {
            throw new IllegalArgumentException("capacity <= 0");
        }
        if (!charset.equals((Object)ahy.a)) {
            throw new IllegalArgumentException("Unsupported encoding");
        }
        this.a = inputStream;
        this.b = charset;
        this.c = new byte[n2];
    }

    public ahw(InputStream inputStream, Charset charset) {
        this(inputStream, 8192, charset);
    }

    static /* synthetic */ Charset a(ahw ahw2) {
        return ahw2.b;
    }

    private void b() {
        int n2 = this.a.read(this.c, 0, this.c.length);
        if (n2 == -1) {
            throw new EOFException();
        }
        this.d = 0;
        this.e = n2;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public String a() {
        InputStream inputStream = this.a;
        synchronized (inputStream) {
            if (this.c == null) {
                throw new IOException("LineReader is closed");
            }
            if (this.d >= this.e) {
                this.b();
            }
            int n2 = this.d;
            do {
                if (n2 == this.e) break;
                if (this.c[n2] == 10) {
                    int n3 = n2 != this.d && this.c[n2 - 1] == 13 ? n2 - 1 : n2;
                    String string2 = new String(this.c, this.d, n3 - this.d, this.b.name());
                    this.d = n2 + 1;
                    return string2;
                }
                ++n2;
            } while (true);
            ahx ahx2 = new ahx(this, this.e - this.d + 80);
            block5 : do {
                ahx2.write(this.c, this.d, this.e - this.d);
                this.e = -1;
                this.b();
                n2 = this.d;
                do {
                    if (n2 == this.e) continue block5;
                    if (this.c[n2] == 10) {
                        if (n2 != this.d) {
                            ahx2.write(this.c, this.d, n2 - this.d);
                        }
                        this.d = n2 + 1;
                        return ahx2.toString();
                    }
                    ++n2;
                } while (true);
                break;
            } while (true);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void close() {
        InputStream inputStream = this.a;
        synchronized (inputStream) {
            if (this.c != null) {
                this.c = null;
                this.a.close();
            }
            return;
        }
    }
}

