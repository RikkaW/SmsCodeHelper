/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.OutputStream
 *  java.lang.String
 *  org.apache.http.util.EncodingUtils
 */
import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import org.apache.http.util.EncodingUtils;

public class ajx
extends aka {
    private static final byte[] i = EncodingUtils.getAsciiBytes((String)"; filename=");
    private byte[] j;
    private String k;

    public ajx(String string2, String string3, byte[] arrby, String string4, String string5) {
        String string6 = string4;
        if (string4 == null) {
            string6 = "application/octet-stream";
        }
        string4 = string5;
        if (string5 == null) {
            string4 = "ISO-8859-1";
        }
        super(string2, string6, string4, "binary");
        if (arrby == null) {
            throw new IllegalArgumentException("Source may not be null");
        }
        this.k = string3;
        this.j = arrby;
    }

    @Override
    protected long a() {
        return this.j.length;
    }

    @Override
    protected void a(OutputStream outputStream) {
        super.a(outputStream);
        if (this.k != null) {
            outputStream.write(i);
            outputStream.write(c);
            outputStream.write(EncodingUtils.getAsciiBytes((String)this.k));
            outputStream.write(c);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    protected void b(OutputStream outputStream) {
        if (this.a() == 0) {
            return;
        }
        byte[] arrby = new byte[4096];
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.j);
        try {
            int n2;
            while ((n2 = byteArrayInputStream.read(arrby)) >= 0) {
                outputStream.write(arrby, 0, n2);
            }
            return;
        }
        finally {
            byteArrayInputStream.close();
        }
    }
}

