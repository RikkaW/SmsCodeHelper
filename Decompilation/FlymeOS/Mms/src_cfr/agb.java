/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.ByteArrayOutputStream
 *  java.io.DataOutputStream
 *  java.io.File
 *  java.io.OutputStream
 *  java.lang.Object
 *  java.util.ArrayList
 */
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class agb {
    protected File a;
    protected int[] b;
    private ArrayList c;
    private boolean d = false;

    protected agb(File file, ArrayList arrayList, int[] arrn) {
        this.a = file;
        this.c = arrayList;
        this.b = arrn;
    }

    protected final void a(boolean bl2) {
        this.d = bl2;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public byte[] a() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream((OutputStream)byteArrayOutputStream);
        for (byte[] arrby : this.c) {
            try {
                dataOutputStream.writeInt(arrby.length);
                dataOutputStream.write(arrby);
            }
            catch (IOException var4_6) {}
        }
        try {
            byteArrayOutputStream.close();
            dataOutputStream.close();
        }
        catch (IOException var2_3) {
            return byteArrayOutputStream.toByteArray();
        }
        do {
            return byteArrayOutputStream.toByteArray();
            break;
        } while (true);
    }

    protected final boolean b() {
        return this.d;
    }

    /*
     * Enabled aggressive block sorting
     */
    protected final int c() {
        if (this.c == null) {
            return 0;
        }
        int n2 = 0;
        int n3 = 0;
        while (n2 < this.c.size()) {
            int n4 = this.c.get(n2) != null ? ((byte[])this.c.get(n2)).length : 0;
            n3 += n4;
            ++n2;
        }
        return n3;
    }
}

