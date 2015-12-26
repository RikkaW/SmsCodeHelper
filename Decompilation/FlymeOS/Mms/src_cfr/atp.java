/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.util.Log
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  org.apache.http.Header
 */
import android.util.Log;
import org.apache.http.Header;

class atp
implements Runnable {
    final /* synthetic */ ath a;
    private final /* synthetic */ String b;
    private final /* synthetic */ int c;
    private final /* synthetic */ Header[] d;
    private final /* synthetic */ Throwable e;

    atp(ath ath2, String string2, int n2, Header[] arrheader, Throwable throwable) {
        this.a = ath2;
        this.b = string2;
        this.c = n2;
        this.d = arrheader;
        this.e = throwable;
    }

    static /* synthetic */ ath a(atp atp2) {
        return atp2.a;
    }

    @Override
    public void run() {
        try {
            Object JSON_TYPE = this.a.b(this.b, true);
            this.a.a(new atq(this, this.c, this.d, this.e, this.b, JSON_TYPE));
            return;
        }
        catch (Throwable var1_2) {
            Log.d((String)"BaseJsonHttpResponseHandler", (String)"parseResponse thrown an problem", (Throwable)var1_2);
            this.a.a(new atr(this, this.c, this.d, this.e, this.b));
            return;
        }
    }
}

