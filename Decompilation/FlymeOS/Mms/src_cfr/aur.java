/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.File
 *  java.lang.String
 *  org.apache.http.client.methods.HttpUriRequest
 */
import java.io.File;
import org.apache.http.client.methods.HttpUriRequest;

public abstract class aur
extends auo {
    private long c;
    private boolean d;

    public void a(HttpUriRequest httpUriRequest) {
        if (this.a.exists() && this.a.canWrite()) {
            this.c = this.a.length();
        }
        if (this.c > 0) {
            this.d = true;
            httpUriRequest.setHeader("Range", "bytes=" + this.c + "-");
        }
    }
}

