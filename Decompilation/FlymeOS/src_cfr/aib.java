/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.DataOutputStream
 *  java.lang.Boolean
 *  java.lang.Object
 *  java.util.ArrayList
 */
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

final class aib
implements Serializable {
    protected short a = 0;
    protected int b = 0;
    protected byte c = 0;
    protected byte d = 0;
    protected ArrayList e = new ArrayList();
    private byte f = 2;

    aib() {
    }

    protected final Boolean a(DataOutputStream dataOutputStream) {
        int n2;
        try {
            dataOutputStream.writeByte((int)this.f);
            dataOutputStream.writeShort((int)this.a);
            dataOutputStream.writeInt(this.b);
            dataOutputStream.writeByte((int)this.c);
            dataOutputStream.writeByte((int)this.d);
            n2 = 0;
        }
        catch (IOException var1_2) {
            return false;
        }
        do {
            if (n2 >= this.d) break;
            dataOutputStream.writeShort((int)((age)this.e.get((int)n2)).a);
            dataOutputStream.writeInt(((age)this.e.get((int)n2)).b);
            dataOutputStream.writeByte((int)((age)this.e.get((int)n2)).c);
            ++n2;
            continue;
            break;
        } while (true);
        return true;
    }
}

