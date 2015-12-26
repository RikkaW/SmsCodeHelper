/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.DataOutputStream
 *  java.lang.Boolean
 *  java.lang.Object
 */
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;

final class afu
implements Serializable {
    protected int a = 0;
    protected int b = 0;
    protected int c = 0;
    protected int d = 0;
    protected int e = 0;
    protected short f = 0;
    protected byte g = 0;
    protected byte h = 0;
    protected long i = 0;
    protected long j = 0;
    private byte k = 1;

    afu() {
    }

    protected final Boolean a(DataOutputStream dataOutputStream) {
        Boolean bl2 = false;
        if (dataOutputStream == null) {
            return bl2;
        }
        try {
            dataOutputStream.writeByte((int)this.k);
            dataOutputStream.writeInt(this.a);
            dataOutputStream.writeInt(this.b);
            dataOutputStream.writeInt(this.c);
            dataOutputStream.writeInt(this.d);
            dataOutputStream.writeInt(this.e);
            dataOutputStream.writeShort((int)this.f);
            dataOutputStream.writeByte((int)this.g);
            dataOutputStream.writeByte((int)this.h);
            dataOutputStream.writeLong(this.i);
            dataOutputStream.writeLong(this.j);
        }
        catch (IOException var1_2) {
            return bl2;
        }
        return true;
    }
}

