/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.InputStreamReader
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuffer
 *  java.nio.ByteBuffer
 */
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;

public class ajg {
    private InputStream a;

    public ajg(InputStream inputStream) {
        this.a = inputStream;
    }

    public int a() {
        return this.a.read();
    }

    public byte b() {
        int n2 = this.a.read();
        if (n2 == -1) {
            throw new IOException("Unexpected stream EOF met");
        }
        return (byte)n2;
    }

    public int c() {
        int n2;
        byte by2;
        int n3 = 0;
        do {
            by2 = this.b();
            n3 = n2 = n3 << 7 | by2 & 127;
        } while ((by2 & 128) != 0);
        return n2;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public String d() {
        ByteBuffer byteBuffer;
        byteBuffer = ByteBuffer.allocate((int)1024);
        try {
            byte by2;
            do {
                by2 = this.b();
                byteBuffer.put(by2);
            } while (by2 != 0);
        }
        catch (IOException var4_3) {}
        byteBuffer = new InputStreamReader((InputStream)new ByteArrayInputStream(byteBuffer.array()), "UTF-8");
        StringBuffer stringBuffer = new StringBuffer();
        do {
            int n2;
            if ((n2 = byteBuffer.read()) == -1) {
                throw new IOException("Unexpected stream EOF met");
            }
            if (n2 == 0) {
                return stringBuffer.toString();
            }
            stringBuffer.append((char)n2);
        } while (true);
    }

    public String e() {
        return null;
    }
}

