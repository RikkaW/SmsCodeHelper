/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Handler
 *  android.os.Looper
 *  android.os.Message
 *  android.util.Log
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Thread
 *  java.lang.Throwable
 *  java.net.URI
 *  org.apache.http.Header
 *  org.apache.http.HttpEntity
 *  org.apache.http.HttpResponse
 *  org.apache.http.StatusLine
 *  org.apache.http.client.HttpResponseException
 *  org.apache.http.util.ByteArrayBuffer
 */
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpResponseException;
import org.apache.http.util.ByteArrayBuffer;

public abstract class atg
implements aut {
    private String a = "UTF-8";
    private Handler b;
    private boolean c;
    private URI d = null;
    private Header[] e = null;
    private Looper f = null;

    public atg() {
        this(null);
    }

    public atg(Looper looper) {
        Looper looper2 = looper;
        if (looper == null) {
            looper2 = Looper.myLooper();
        }
        this.f = looper2;
        this.a(false);
    }

    protected Message a(int n2, Object object) {
        return Message.obtain((Handler)this.b, (int)n2, (Object)object);
    }

    @Override
    public final void a() {
        this.b(this.a(2, (Object)null));
    }

    @Override
    public final void a(int n2) {
        this.b(this.a(5, new Object[]{n2}));
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(int n2, int n3) {
        double d2 = n3 > 0 ? (double)n2 * 1.0 / (double)n3 * 100.0 : -1.0;
        Log.v((String)"AsyncHttpResponseHandler", (String)String.format((String)"Progress %d from %d (%2.0f%%)", (Object[])new Object[]{n2, n3, d2}));
    }

    public abstract void a(int var1, Header[] var2, byte[] var3);

    @Override
    public final void a(int n2, Header[] arrheader, byte[] arrby, Throwable throwable) {
        this.b(this.a(1, new Object[]{n2, arrheader, arrby, throwable}));
    }

    protected void a(Message arrobject) {
        switch (arrobject.what) {
            default: {
                return;
            }
            case 0: {
                arrobject = (Object[])arrobject.obj;
                if (arrobject != null && arrobject.length >= 3) {
                    this.a((Integer)arrobject[0], (Header[])arrobject[1], (byte[])arrobject[2]);
                    return;
                }
                Log.e((String)"AsyncHttpResponseHandler", (String)"SUCCESS_MESSAGE didn't got enough params");
                return;
            }
            case 1: {
                arrobject = (Object[])arrobject.obj;
                if (arrobject != null && arrobject.length >= 4) {
                    this.b((Integer)arrobject[0], (Header[])arrobject[1], (byte[])arrobject[2], (Throwable)arrobject[3]);
                    return;
                }
                Log.e((String)"AsyncHttpResponseHandler", (String)"FAILURE_MESSAGE didn't got enough params");
                return;
            }
            case 2: {
                this.f();
                return;
            }
            case 3: {
                this.g();
                return;
            }
            case 4: {
                arrobject = (Object[])arrobject.obj;
                if (arrobject != null && arrobject.length >= 2) {
                    try {
                        this.a((int)((Integer)arrobject[0]), (Integer)arrobject[1]);
                        return;
                    }
                    catch (Throwable var1_2) {
                        Log.e((String)"AsyncHttpResponseHandler", (String)"custom onProgress contains an error", (Throwable)var1_2);
                        return;
                    }
                }
                Log.e((String)"AsyncHttpResponseHandler", (String)"PROGRESS_MESSAGE didn't got enough params");
                return;
            }
            case 5: {
                arrobject = (Object[])arrobject.obj;
                if (arrobject != null && arrobject.length == 1) {
                    this.b((Integer)arrobject[0]);
                    return;
                }
                Log.e((String)"AsyncHttpResponseHandler", (String)"RETRY_MESSAGE didn't get enough params");
                return;
            }
            case 6: 
        }
        this.h();
    }

    @Override
    public void a(aut aut2, HttpResponse httpResponse) {
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    protected void a(Runnable runnable) {
        if (runnable == null) return;
        if (this.d() || this.b == null) {
            runnable.run();
            return;
        }
        boolean bl2 = this.b != null;
        a.a(bl2, "handler should not be null!");
        this.b.post(runnable);
    }

    @Override
    public void a(URI uRI) {
        this.d = uRI;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public void a(HttpResponse httpResponse) {
        if (Thread.currentThread().isInterrupted()) return;
        StatusLine statusLine = httpResponse.getStatusLine();
        byte[] arrby = this.a(httpResponse.getEntity());
        if (Thread.currentThread().isInterrupted()) return;
        if (statusLine.getStatusCode() >= 300) {
            this.a(statusLine.getStatusCode(), httpResponse.getAllHeaders(), arrby, (Throwable)new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase()));
            return;
        }
        this.b(statusLine.getStatusCode(), httpResponse.getAllHeaders(), arrby);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(boolean bl2) {
        boolean bl3 = bl2;
        if (!bl2) {
            bl3 = bl2;
            if (this.f == null) {
                bl3 = true;
                Log.w((String)"AsyncHttpResponseHandler", (String)"Current thread has not called Looper.prepare(). Forcing synchronous mode.");
            }
        }
        if (!bl3 && this.b == null) {
            this.b = new a(this, this.f);
        } else if (bl3 && this.b != null) {
            this.b = null;
        }
        this.c = bl3;
    }

    @Override
    public void a(Header[] arrheader) {
        this.e = arrheader;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    byte[] a(HttpEntity object) {
        int n2;
        byte[] arrby;
        int n3 = 4096;
        if (object == null) return null;
        InputStream inputStream = object.getContent();
        if (inputStream == null) {
            return null;
        }
        long l2 = object.getContentLength();
        if (l2 > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("HTTP entity too large to be buffered in memory");
        }
        if (l2 > 0) {
            n3 = (int)l2;
        }
        ByteArrayBuffer byteArrayBuffer = new ByteArrayBuffer(n3);
        try {
            arrby = new byte[4096];
            n3 = 0;
            do {
                boolean bl2;
                if ((n2 = inputStream.read(arrby)) != -1 && !(bl2 = Thread.currentThread().isInterrupted())) break block11;
                break;
            } while (true);
        }
        catch (Throwable var10_7) {
            try {
                ab.a(inputStream);
                ab.a((HttpEntity)object);
                throw var10_7;
            }
            catch (OutOfMemoryError var1_2) {
                System.gc();
                throw new IOException("File too large to fit into available memory");
            }
        }
        {
            block11 : {
                ab.a(inputStream);
                ab.a((HttpEntity)object);
                return byteArrayBuffer.toByteArray();
            }
            n3 += n2;
            byteArrayBuffer.append(arrby, 0, n2);
            long l3 = l2 <= 0 ? 1 : l2;
            this.b(n3, (int)l3);
            continue;
        }
    }

    @Override
    public final void b() {
        this.b(this.a(3, (Object)null));
    }

    public void b(int n2) {
        Log.d((String)"AsyncHttpResponseHandler", (String)String.format((String)"Request retry no. %d", (Object[])new Object[]{n2}));
    }

    public final void b(int n2, int n3) {
        this.b(this.a(4, new Object[]{n2, n3}));
    }

    public final void b(int n2, Header[] arrheader, byte[] arrby) {
        this.b(this.a(0, new Object[]{n2, arrheader, arrby}));
    }

    public abstract void b(int var1, Header[] var2, byte[] var3, Throwable var4);

    /*
     * Enabled aggressive block sorting
     */
    protected void b(Message message) {
        boolean bl2;
        if (this.d() || this.b == null) {
            this.a(message);
            return;
        } else {
            if (Thread.currentThread().isInterrupted()) return;
            {
                bl2 = this.b != null;
            }
        }
        a.a(bl2, "handler should not be null!");
        this.b.sendMessage(message);
    }

    @Override
    public void b(aut aut2, HttpResponse httpResponse) {
    }

    public void b(String string2) {
        this.a = string2;
    }

    @Override
    public final void c() {
        this.b(this.a(6, (Object)null));
    }

    @Override
    public boolean d() {
        return this.c;
    }

    public String e() {
        if (this.a == null) {
            return "UTF-8";
        }
        return this.a;
    }

    public void f() {
    }

    public void g() {
    }

    public void h() {
        Log.d((String)"AsyncHttpResponseHandler", (String)"Request got cancelled");
    }

    static class a
    extends Handler {
        private final atg a;

        a(atg atg2, Looper looper) {
            super(looper);
            this.a = atg2;
        }

        public void handleMessage(Message message) {
            this.a.a(message);
        }
    }

}

