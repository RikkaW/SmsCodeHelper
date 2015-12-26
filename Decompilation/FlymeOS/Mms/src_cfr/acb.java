/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
class acb
implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ aca b;

    acb(aca aca2, String string2) {
        this.b = aca2;
        this.a = string2;
    }

    @Override
    public void run() {
        abu.d(this.b.b).setTitle(this.b.a + " - " + this.a);
    }
}

