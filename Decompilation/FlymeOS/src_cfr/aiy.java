/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
import java.io.IOException;
import java.io.InputStream;

public class aiy {
    public int a = 0;
    private InputStream b;

    public aiy(InputStream inputStream) {
        this.b = inputStream;
    }

    public int a() {
        int n2 = this.b.read();
        ++this.a;
        if (n2 == -1) {
            throw new IOException("Unexpected stream EOF met");
        }
        return n2;
    }

    public int b() {
        int n2;
        int n3;
        int n4 = 0;
        do {
            n3 = this.a();
            n4 = n2 = n4 << 7 | n3 & 127;
        } while ((n3 & 128) != 0);
        return n2;
    }
}

