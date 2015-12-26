/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
class acf
implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ ace b;

    acf(ace ace2, String string2) {
        this.b = ace2;
        this.a = string2;
    }

    @Override
    public void run() {
        abu.d(this.b.b).setTitle(this.b.a + " - " + this.a);
    }
}

