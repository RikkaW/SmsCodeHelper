/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
class apw
implements Runnable {
    final /* synthetic */ apv.a a;
    private final /* synthetic */ String b;

    apw(apv.a a2, String string2) {
        this.a = a2;
        this.b = string2;
    }

    @Override
    public void run() {
        apv.a.a(this.a, this.b);
    }
}

