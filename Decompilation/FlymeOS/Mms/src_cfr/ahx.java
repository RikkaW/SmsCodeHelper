/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.ByteArrayOutputStream
 *  java.lang.Object
 *  java.lang.String
 */
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

class ahx
extends ByteArrayOutputStream {
    final /* synthetic */ ahw a;

    ahx(ahw ahw2, int n2) {
        this.a = ahw2;
        super(n2);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public String toString() {
        int n2 = this.count > 0 && this.buf[this.count - 1] == 13 ? this.count - 1 : this.count;
        try {
            return new String(this.buf, 0, n2, ahw.a(this.a).name());
        }
        catch (UnsupportedEncodingException var2_3) {
            throw new AssertionError((Object)var2_3);
        }
    }
}

