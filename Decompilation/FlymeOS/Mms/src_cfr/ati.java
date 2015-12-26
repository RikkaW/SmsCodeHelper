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

class ati
implements Runnable {
    final /* synthetic */ ath a;
    private final /* synthetic */ String b;
    private final /* synthetic */ int c;
    private final /* synthetic */ Header[] d;

    ati(ath ath2, String string2, int n2, Header[] arrheader) {
        this.a = ath2;
        this.b = string2;
        this.c = n2;
        this.d = arrheader;
    }

    static /* synthetic */ ath a(ati ati2) {
        return ati2.a;
    }

    @Override
    public void run() {
        try {
            Object JSON_TYPE = this.a.b(this.b, false);
            this.a.a(new atn(this, this.c, this.d, this.b, JSON_TYPE));
            return;
        }
        catch (Throwable var1_2) {
            Log.d((String)"BaseJsonHttpResponseHandler", (String)"parseResponse thrown an problem", (Throwable)var1_2);
            this.a.a(new ato(this, this.c, this.d, var1_2, this.b));
            return;
        }
    }
}

