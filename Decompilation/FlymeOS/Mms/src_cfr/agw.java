/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Throwable
 */
import java.util.TimerTask;

class agw
extends TimerTask {
    final /* synthetic */ int a;
    final /* synthetic */ agu b;

    agw(agu agu2, int n2) {
        this.b = agu2;
        this.a = n2;
    }

    @Override
    public void run() {
        block4 : {
            try {
                if (agu.j(this.b)) {
                    agu.c(this.b, this.a);
                    if (!this.b.e()) {
                        agu.k(this.b);
                        return;
                    }
                    break block4;
                }
                agu.k(this.b);
                return;
            }
            catch (Throwable var1_1) {
                var1_1.printStackTrace();
            }
        }
    }
}

