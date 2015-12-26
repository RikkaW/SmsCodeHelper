/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.ByteArrayOutputStream
 *  java.io.OutputStream
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuffer
 *  java.util.Random
 *  org.apache.http.Header
 *  org.apache.http.entity.AbstractHttpEntity
 *  org.apache.http.message.BasicHeader
 *  org.apache.http.params.HttpParams
 *  org.apache.http.util.EncodingUtils
 */
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;
import org.apache.http.Header;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EncodingUtils;

public class ajy
extends AbstractHttpEntity {
    private static byte[] b = EncodingUtils.getAsciiBytes((String)"-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
    protected ajz[] a;
    private byte[] c;
    private HttpParams d;
    private boolean e = false;

    public ajy(ajz[] arrajz) {
        this.setContentType("multipart/form-data");
        if (arrajz == null) {
            throw new IllegalArgumentException("parts cannot be null");
        }
        this.a = arrajz;
        this.d = null;
    }

    private static byte[] b() {
        Random random = new Random();
        byte[] arrby = new byte[random.nextInt(11) + 30];
        for (int i2 = 0; i2 < arrby.length; ++i2) {
            arrby[i2] = b[random.nextInt(b.length)];
        }
        return arrby;
    }

    /*
     * Enabled aggressive block sorting
     */
    protected byte[] a() {
        if (this.c != null) return this.c;
        String string2 = null;
        if (this.d != null && (string2 = (String)this.d.getParameter("http.method.multipart.boundary")) != null) {
            this.c = EncodingUtils.getAsciiBytes((String)string2);
            return this.c;
        }
        this.c = ajy.b();
        return this.c;
    }

    public InputStream getContent() {
        if (!this.isRepeatable() && this.e) {
            throw new IllegalStateException("Content has been consumed");
        }
        this.e = true;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ajz.a((OutputStream)byteArrayOutputStream, this.a, this.c);
        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }

    public long getContentLength() {
        try {
            long l2 = ajz.a(this.a, this.a());
            return l2;
        }
        catch (Exception var3_2) {
            return 0;
        }
    }

    public Header getContentType() {
        StringBuffer stringBuffer = new StringBuffer("multipart/form-data");
        stringBuffer.append("; boundary=");
        stringBuffer.append(EncodingUtils.getAsciiString((byte[])this.a()));
        return new BasicHeader("Content-Type", stringBuffer.toString());
    }

    public boolean isRepeatable() {
        for (int i2 = 0; i2 < this.a.length; ++i2) {
            if (this.a[i2].g()) continue;
            return false;
        }
        return true;
    }

    public boolean isStreaming() {
        return false;
    }

    public void writeTo(OutputStream outputStream) {
        ajz.a(outputStream, this.a, this.a());
    }
}

