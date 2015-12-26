/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
class aqs
implements Runnable {
    final /* synthetic */ aqr a;

    aqs(aqr aqr2) {
        this.a = aqr2;
    }

    @Override
    public void run() {
        if (aqr.a(this.a).getLineCount() > 1) {
            aqr.a(this.a).setGravity(8388611);
        }
    }
}

