/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.Throwable
 *  java.net.URI
 *  org.apache.http.Header
 *  org.apache.http.HttpResponse
 */
import java.net.URI;
import org.apache.http.Header;
import org.apache.http.HttpResponse;

public interface aut {
    public void a();

    public void a(int var1);

    public void a(int var1, Header[] var2, byte[] var3, Throwable var4);

    public void a(aut var1, HttpResponse var2);

    public void a(URI var1);

    public void a(HttpResponse var1);

    public void a(Header[] var1);

    public void b();

    public void b(aut var1, HttpResponse var2);

    public void c();

    public boolean d();
}

