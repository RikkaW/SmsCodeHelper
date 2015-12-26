/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
class apq
implements Runnable {
    final /* synthetic */ app a;
    private final /* synthetic */ boolean b;

    apq(app app2, boolean bl2) {
        this.a = app2;
        this.b = bl2;
    }

    @Override
    public void run() {
        app.a(this.a, this.b);
    }
}

