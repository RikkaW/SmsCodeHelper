/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  org.apache.http.Header
 */
import org.apache.http.Header;

class atn
implements Runnable {
    final /* synthetic */ ati a;
    private final /* synthetic */ int b;
    private final /* synthetic */ Header[] c;
    private final /* synthetic */ String d;
    private final /* synthetic */ Object e;

    atn(ati ati2, int n2, Header[] arrheader, String string2, Object object) {
        this.a = ati2;
        this.b = n2;
        this.c = arrheader;
        this.d = string2;
        this.e = object;
    }

    @Override
    public void run() {
        ati.a(this.a).a(this.b, this.c, this.d, this.e);
    }
}

