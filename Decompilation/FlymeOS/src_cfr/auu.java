/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.SystemClock
 *  java.lang.Boolean
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.HashSet
 *  org.apache.http.NoHttpResponseException
 *  org.apache.http.client.HttpRequestRetryHandler
 *  org.apache.http.client.methods.HttpUriRequest
 *  org.apache.http.protocol.HttpContext
 */
import android.os.SystemClock;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Iterator;
import javax.net.ssl.SSLException;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.protocol.HttpContext;

class auu
implements HttpRequestRetryHandler {
    private static final HashSet<Class<?>> a = new HashSet();
    private static final HashSet<Class<?>> b = new HashSet();
    private final int c;
    private final int d;

    static {
        a.add((Object)NoHttpResponseException.class);
        a.add((Object)UnknownHostException.class);
        a.add((Object)SocketException.class);
        b.add((Object)InterruptedIOException.class);
        b.add((Object)SSLException.class);
    }

    public auu(int n2, int n3) {
        this.c = n2;
        this.d = n3;
    }

    protected boolean a(HashSet<Class<?>> object, Throwable throwable) {
        object = object.iterator();
        do {
            if (object.hasNext()) continue;
            return false;
        } while (!((Class)object.next()).isInstance((Object)throwable));
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean retryRequest(IOException iOException, int n2, HttpContext httpContext) {
        boolean bl2;
        boolean bl3 = true;
        Boolean bl4 = (Boolean)httpContext.getAttribute("http.request_sent");
        boolean bl5 = bl4 != null && bl4 != false;
        if (n2 > this.c) {
            bl2 = false;
        } else {
            bl2 = bl3;
            if (!this.a(a, iOException)) {
                if (this.a(b, iOException)) {
                    bl2 = false;
                } else {
                    bl2 = bl3;
                    if (!bl5) {
                        bl2 = bl3;
                    }
                }
            }
        }
        if (bl2 && (HttpUriRequest)httpContext.getAttribute("http.request") == null) {
            return false;
        }
        if (bl2) {
            SystemClock.sleep((long)this.d);
            return bl2;
        }
        iOException.printStackTrace();
        return bl2;
    }
}

