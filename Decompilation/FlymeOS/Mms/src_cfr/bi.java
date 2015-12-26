/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.PushbackInputStream
 *  java.lang.Long
 *  java.lang.Math
 *  java.lang.String
 *  java.util.zip.Inflater
 */
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.zip.CRC32;
import java.util.zip.Inflater;
import java.util.zip.ZipException;

public class bi
extends bg {
    private bh f;
    private CRC32 g = new CRC32();
    private long h;
    private byte[] i = new byte[512];
    private boolean j = false;
    private boolean k = false;
    private byte[] l = new byte[256];

    public bi(InputStream inputStream, String string2) {
        super((InputStream)new PushbackInputStream(inputStream, 512), new Inflater(true), 512);
        this.e = true;
        if (inputStream == null) {
            throw new NullPointerException("in is null");
        }
        this.c = string2;
    }

    private static final int a(byte[] arrby, int n2) {
        return arrby[n2] & 255 | (arrby[n2 + 1] & 255) << 8;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static String a(byte[] arrby, int n2, int n3) {
        int n4;
        int n5;
        int n6 = n2 + n3;
        int n7 = n2;
        n3 = 0;
        block10 : do {
            if (n7 >= n6) {
                if (n7 == n6) break;
                throw new IllegalArgumentException();
            }
            n5 = n7 + 1;
            switch ((arrby[n7] & 255) >> 4) {
                default: {
                    throw new IllegalArgumentException();
                }
                case 0: 
                case 1: 
                case 2: 
                case 3: 
                case 4: 
                case 5: 
                case 6: 
                case 7: {
                    ++n3;
                    n7 = n5;
                    continue block10;
                }
                case 12: 
                case 13: {
                    n7 = n5 + 1;
                    if ((arrby[n5] & 192) != 128) {
                        throw new IllegalArgumentException();
                    }
                    ++n3;
                    continue block10;
                }
                case 14: 
            }
            n4 = n5 + 1;
            if ((arrby[n5] & 192) != 128) throw new IllegalArgumentException();
            n7 = n4 + 1;
            if ((arrby[n4] & 192) != 128) {
                throw new IllegalArgumentException();
            }
            ++n3;
        } while (true);
        char[] arrc = new char[n3];
        n5 = 0;
        n7 = n2;
        n2 = n5;
        block11 : while (n7 < n6) {
            n5 = n7 + 1;
            n4 = arrby[n7] & 255;
            switch (n4 >> 4) {
                default: {
                    throw new IllegalArgumentException();
                }
                case 0: 
                case 1: 
                case 2: 
                case 3: 
                case 4: 
                case 5: 
                case 6: 
                case 7: {
                    arrc[n2] = (char)n4;
                    ++n2;
                    n7 = n5;
                    continue block11;
                }
                case 12: 
                case 13: {
                    n7 = n5 + 1;
                    arrc[n2] = (char)(arrby[n5] & 63 | (n4 & 31) << 6);
                    ++n2;
                    continue block11;
                }
                case 14: 
            }
            int n8 = n5 + 1;
            n5 = arrby[n5];
            n7 = n8 + 1;
            arrc[n2] = (char)((n5 & 63) << 6 | (n4 & 15) << 12 | arrby[n8] & 63);
            ++n2;
        }
        return new String(arrc, 0, n3);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(bh bh2) {
        int n2 = this.a.getRemaining();
        if (n2 > 0) {
            ((PushbackInputStream)this.in).unread(this.b, this.d - n2, n2);
        }
        if ((bh2.h & 8) == 8) {
            this.b(this.i, 0, 16);
            long l2 = bi.b(this.i, 0);
            if (l2 != 134695760) {
                bh2.c = l2;
                bh2.e = bi.b(this.i, 4);
                bh2.d = bi.b(this.i, 8);
                ((PushbackInputStream)this.in).unread(this.i, 11, 4);
            } else {
                bh2.c = bi.b(this.i, 4);
                bh2.e = bi.b(this.i, 8);
                if (bh2.h == 9) {
                    bh2.e -= 12;
                }
                bh2.d = bi.b(this.i, 12);
            }
        }
        if (bh2.d != this.a.getBytesWritten()) {
            throw new ZipException("invalid entry size (expected " + bh2.d + " but got " + this.a.getBytesWritten() + " bytes)");
        }
        if (bh2.e != this.a.getBytesRead()) {
            throw new ZipException("invalid entry compressed size (expected " + bh2.e + " but got " + this.a.getBytesRead() + " bytes)");
        }
        if (bh2.c != this.g.getValue()) {
            throw new ZipException("invalid entry CRC (expected 0x" + Long.toHexString((long)bh2.c) + " but got 0x" + Long.toHexString((long)this.g.getValue()) + ")");
        }
    }

    private static final long b(byte[] arrby, int n2) {
        return (long)bi.a(arrby, n2) | (long)bi.a(arrby, n2 + 2) << 16;
    }

    private void b(byte[] arrby, int n2, int n3) {
        while (n3 > 0) {
            int n4 = this.in.read(arrby, n2, n3);
            if (n4 == -1) {
                throw new EOFException();
            }
            n2 += n4;
            n3 -= n4;
        }
        return;
    }

    private void d() {
        if (this.j) {
            throw new IOException("Stream closed");
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private bh e() {
        void var4_2;
        java.lang.Object var4_1 = null;
        try {
            this.b(this.i, 0, 30);
        }
        catch (EOFException var4_9) {
            return null;
        }
        if (bi.b(this.i, 0) != 67324752) {
            return var4_2;
        }
        int n2 = bi.a(this.i, 26);
        if (n2 == 0) {
            throw new ZipException("missing entry name");
        }
        int n3 = this.l.length;
        if (n2 > n3) {
            int n4;
            do {
                n3 = n4 = n3 * 2;
            } while (n2 > n4);
            this.l = new byte[n4];
        }
        this.b(this.l, 0, n2);
        bh bh2 = this.a(bi.a(this.l, 0, n2));
        bh2.i = bi.a(this.i, 4);
        n3 = bh2.h = bi.a(this.i, 6);
        bh2.f = bi.a(this.i, 8);
        bh2.b = bi.b(this.i, 10);
        if ((bh2.h & 8) == 8) {
            if (bh2.f != 8) {
                throw new ZipException("only DEFLATED entries can have EXT descriptor");
            }
        } else {
            bh2.c = bi.b(this.i, 14);
            bh2.e = bi.b(this.i, 18);
            bh2.d = bi.b(this.i, 22);
        }
        if ((n3 = bi.a(this.i, 28)) > 0) {
            byte[] arrby = new byte[n3];
            this.b(arrby, 0, n3);
            bh2.g = arrby;
        }
        bh bh3 = bh2;
        if (this.c == null) return var4_2;
        byte[] arrby = new byte[12];
        this.b(arrby, 0, 12);
        bk.a(this.c);
        byte[] arrby2 = bk.a(arrby, 12);
        bh bh4 = bh2;
        if (arrby2[11] == (byte)(bh2.c >> 24 & 255)) return var4_2;
        if ((bh2.h & 8) != 8) {
            throw new ZipException("The password did not match.");
        }
        bh bh5 = bh2;
        if (arrby2[11] == (byte)(bh2.b >> 8 & 255)) return var4_2;
        throw new ZipException("The password did not match.");
    }

    protected bh a(String string2) {
        return new bh(string2);
    }

    @Override
    public int available() {
        this.d();
        if (this.k) {
            return 0;
        }
        return 1;
    }

    public bh b() {
        bh bh2;
        this.d();
        if (this.f != null) {
            this.c();
        }
        this.g.reset();
        this.a.reset();
        this.f = bh2 = this.e();
        if (bh2 == null) {
            return null;
        }
        if (this.f.f == 0) {
            this.h = this.f.d;
        }
        this.k = false;
        return this.f;
    }

    public void c() {
        this.d();
        while (this.read(this.i, 0, this.i.length) != -1) {
        }
        this.k = true;
    }

    @Override
    public void close() {
        if (!this.j) {
            super.close();
            this.j = true;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public int read(byte[] arrby, int n2, int n3) {
        int n4 = -1;
        this.d();
        if (n2 < 0) throw new IndexOutOfBoundsException();
        if (n3 < 0) throw new IndexOutOfBoundsException();
        if (n2 > arrby.length - n3) {
            throw new IndexOutOfBoundsException();
        }
        if (n3 == 0) {
            return 0;
        }
        if (this.f == null) return n4;
        switch (this.f.f) {
            default: {
                throw new InternalError("invalid compression method");
            }
            case 8: {
                n3 = super.read(arrby, n2, n3);
                if (n3 == -1) {
                    this.a(this.f);
                    this.k = true;
                    this.f = null;
                    do {
                        return n3;
                        break;
                    } while (true);
                }
                this.g.update(arrby, n2, n3);
                return n3;
            }
            case 0: 
        }
        if (this.h <= 0) {
            this.k = true;
            this.f = null;
            return -1;
        }
        n4 = n3;
        if ((long)n3 > this.h) {
            n4 = (int)this.h;
        }
        if ((n3 = this.in.read(arrby, n2, n4)) == -1) {
            throw new ZipException("unexpected EOF");
        }
        this.g.update(arrby, n2, n3);
        this.h -= (long)n3;
        return n3;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public long skip(long l2) {
        if (l2 < 0) {
            throw new IllegalArgumentException("negative skip length");
        }
        this.d();
        int n2 = (int)Math.min((long)l2, (long)Integer.MAX_VALUE);
        int n3 = 0;
        do {
            int n4;
            if (n3 >= n2) {
                do {
                    return n3;
                    break;
                } while (true);
            }
            int n5 = n4 = n2 - n3;
            if (n4 > this.i.length) {
                n5 = this.i.length;
            }
            if ((n5 = this.read(this.i, 0, n5)) == -1) {
                this.k = true;
                return n3;
            }
            n3 = n5 + n3;
        } while (true);
    }
}

