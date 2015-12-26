/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.DataOutputStream
 *  java.lang.Boolean
 *  java.lang.Object
 *  java.lang.System
 *  java.util.ArrayList
 */
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

final class afx
implements Serializable {
    protected byte a = 0;
    protected ArrayList b = new ArrayList();
    private byte c = 3;

    afx() {
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected final Boolean a(DataOutputStream dataOutputStream) {
        try {
            dataOutputStream.writeByte((int)this.c);
            dataOutputStream.writeByte((int)this.a);
            int n2 = 0;
            while (n2 < this.b.size()) {
                afy afy2 = (afy)this.b.get(n2);
                dataOutputStream.writeByte((int)afy2.a);
                byte[] arrby = new byte[afy2.a];
                byte[] arrby2 = afy2.b;
                int n3 = afy2.a < afy2.b.length ? afy2.a : afy2.b.length;
                System.arraycopy((Object)arrby2, (int)0, (Object)arrby, (int)0, (int)n3);
                dataOutputStream.write(arrby);
                dataOutputStream.writeDouble(afy2.c);
                dataOutputStream.writeInt(afy2.d);
                dataOutputStream.writeInt(afy2.e);
                dataOutputStream.writeDouble(afy2.f);
                dataOutputStream.writeByte((int)afy2.g);
                dataOutputStream.writeByte((int)afy2.h);
                arrby = new byte[afy2.h];
                arrby2 = afy2.i;
                n3 = afy2.h < afy2.i.length ? afy2.h : afy2.i.length;
                System.arraycopy((Object)arrby2, (int)0, (Object)arrby, (int)0, (int)n3);
                dataOutputStream.write(arrby);
                dataOutputStream.writeByte((int)afy2.j);
                ++n2;
            }
            return true;
        }
        catch (IOException var1_2) {
            return false;
        }
    }
}

