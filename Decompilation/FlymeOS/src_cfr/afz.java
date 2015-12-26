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

final class afz
implements Serializable {
    protected byte a = 0;
    protected ArrayList b = new ArrayList();
    private byte c = 8;

    afz() {
    }

    protected final Boolean a(DataOutputStream dataOutputStream) {
        int n2;
        try {
            dataOutputStream.writeByte((int)this.c);
            dataOutputStream.writeByte((int)this.a);
            n2 = 0;
        }
        catch (IOException var1_2) {
            return false;
        }
        do {
            if (n2 >= this.a) break;
            aga aga2 = (aga)this.b.get(n2);
            dataOutputStream.write(aga2.a);
            dataOutputStream.writeShort((int)aga2.b);
            dataOutputStream.write(agc.a(aga2.c, aga2.c.length));
            ++n2;
            continue;
            break;
        } while (true);
        return true;
    }
}

