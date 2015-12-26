/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.System
 *  java.lang.Thread
 */
public final class agq
extends Thread {
    private /* synthetic */ aie a;

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public final void run() {
        do {
            if (!aie.c(this.a)) return;
            aie.a(this.a, aie.g(this.a), 1, System.currentTimeMillis());
            try {
                Thread.sleep((long)aie.h(this.a));
            }
            catch (Exception var1_1) {}
        } while (true);
        catch (Exception exception) {
            // empty catch block
        }
    }
}

