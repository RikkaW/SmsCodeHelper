/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  org.apache.http.Header
 */
import org.apache.http.Header;

class atr
implements Runnable {
    final /* synthetic */ atp a;
    private final /* synthetic */ int b;
    private final /* synthetic */ Header[] c;
    private final /* synthetic */ Throwable d;
    private final /* synthetic */ String e;

    atr(atp atp2, int n2, Header[] arrheader, Throwable throwable, String string2) {
        this.a = atp2;
        this.b = n2;
        this.c = arrheader;
        this.d = throwable;
        this.e = string2;
    }

    @Override
    public void run() {
        atp.a(this.a).a(this.b, this.c, this.d, this.e, null);
    }
}

