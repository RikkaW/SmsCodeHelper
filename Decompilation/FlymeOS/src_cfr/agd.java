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

final class agd
implements Serializable {
    protected int a = 0;
    protected int b = 0;
    protected short c = 0;
    protected short d = 0;
    protected int e = 0;
    protected byte f = 0;
    private byte g = 4;

    agd() {
    }

    protected final Boolean a(DataOutputStream dataOutputStream) {
        try {
            dataOutputStream.writeByte((int)this.g);
            dataOutputStream.writeInt(this.a);
            dataOutputStream.writeInt(this.b);
            dataOutputStream.writeShort((int)this.c);
            dataOutputStream.writeShort((int)this.d);
            dataOutputStream.writeInt(this.e);
            dataOutputStream.writeByte((int)this.f);
        }
        catch (IOException var1_2) {
            return false;
        }
        return true;
    }
}

