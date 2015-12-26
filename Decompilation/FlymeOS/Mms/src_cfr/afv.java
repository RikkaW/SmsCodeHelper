/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.ByteArrayOutputStream
 *  java.io.DataOutputStream
 *  java.io.OutputStream
 *  java.lang.Boolean
 *  java.lang.Integer
 *  java.lang.Object
 *  java.util.ArrayList
 */
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;

final class afv
implements Serializable {
    protected byte[] a = new byte[16];
    protected byte[] b = new byte[16];
    protected byte[] c = new byte[16];
    protected short d = 0;
    protected short e = 0;
    protected byte f = 0;
    protected byte[] g = new byte[16];
    protected byte[] h = new byte[32];
    protected short i = 0;
    protected ArrayList j = new ArrayList();
    private byte k = 41;
    private short l = 0;

    afv() {
    }

    private Boolean a(DataOutputStream dataOutputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream2 = new DataOutputStream((OutputStream)byteArrayOutputStream);
        dataOutputStream2.flush();
        dataOutputStream2.write(this.a);
        dataOutputStream2.write(this.b);
        dataOutputStream2.write(this.c);
        dataOutputStream2.writeShort((int)this.d);
        dataOutputStream2.writeShort((int)this.e);
        dataOutputStream2.writeByte((int)this.f);
        this.g[15] = 0;
        dataOutputStream2.write(agc.a(this.g, this.g.length));
        this.h[31] = 0;
        dataOutputStream2.write(agc.a(this.h, this.h.length));
        dataOutputStream2.writeShort((int)this.i);
        short s2 = 0;
        do {
            if (s2 >= this.i) break;
            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream3 = new DataOutputStream((OutputStream)byteArrayOutputStream2);
            dataOutputStream3.flush();
            aid aid2 = (aid)this.j.get((int)s2);
            if (aid2.c == null || !aid2.c.a(dataOutputStream3).booleanValue()) {
                // empty if block
            }
            if (aid2.d == null || !aid2.d.a(dataOutputStream3).booleanValue()) {
                // empty if block
            }
            if (aid2.e == null || !aid2.e.a(dataOutputStream3).booleanValue()) {
                // empty if block
            }
            if (aid2.f == null || !aid2.f.a(dataOutputStream3).booleanValue()) {
                // empty if block
            }
            if (aid2.g == null || !aid2.g.a(dataOutputStream3).booleanValue()) {
                // empty if block
            }
            aid2.a = Integer.valueOf((int)(byteArrayOutputStream2.size() + 4)).shortValue();
            dataOutputStream2.writeShort((int)aid2.a);
            dataOutputStream2.writeInt(aid2.b);
            dataOutputStream2.write(byteArrayOutputStream2.toByteArray());
            s2 = (short)(s2 + 1);
        } while (true);
        try {
            this.l = Integer.valueOf((int)byteArrayOutputStream.size()).shortValue();
            dataOutputStream.writeByte((int)this.k);
            dataOutputStream.writeShort((int)this.l);
            dataOutputStream.write(byteArrayOutputStream.toByteArray());
        }
        catch (IOException var1_2) {
            return false;
        }
        return true;
    }

    protected final byte[] a() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        this.a(new DataOutputStream((OutputStream)byteArrayOutputStream));
        return byteArrayOutputStream.toByteArray();
    }
}

