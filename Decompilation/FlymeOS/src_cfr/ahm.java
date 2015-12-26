/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
import java.util.concurrent.Callable;

class ahm
implements Callable<Void> {
    final /* synthetic */ ahl a;

    ahm(ahl ahl2) {
        this.a = ahl2;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public Void a() {
        ahl ahl2 = this.a;
        synchronized (ahl2) {
            if (ahl.a(this.a) == null) {
                return null;
            }
            ahl.b(this.a);
            if (ahl.c(this.a)) {
                ahl.d(this.a);
                ahl.a(this.a, 0);
            }
            return null;
        }
    }

    @Override
    public /* synthetic */ Object call() {
        return this.a();
    }
}

