/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.OutputStream
 *  java.lang.String
 *  org.apache.http.util.EncodingUtils
 */
import java.io.OutputStream;
import org.apache.http.util.EncodingUtils;

public class akb
extends aka {
    private byte[] i;
    private String j;

    public akb(String string2, String string3) {
        this(string2, string3, null);
    }

    public akb(String string2, String string3, String string4) {
        String string5 = string4;
        if (string4 == null) {
            string5 = "US-ASCII";
        }
        super(string2, "text/plain", string5, "8bit");
        string2 = string3;
        if (string3 == null) {
            string2 = "";
        }
        if (string2.indexOf(0) != -1) {
            throw new IllegalArgumentException("NULs may not be present in string parts");
        }
        this.j = string2;
    }

    private byte[] i() {
        if (this.i == null) {
            this.i = EncodingUtils.getBytes((String)this.j, (String)this.d());
        }
        return this.i;
    }

    @Override
    protected long a() {
        return this.i().length;
    }

    @Override
    protected void b(OutputStream outputStream) {
        outputStream.write(this.i());
    }
}

