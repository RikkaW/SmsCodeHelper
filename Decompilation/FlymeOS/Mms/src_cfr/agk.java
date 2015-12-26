/*
 * Decompiled with CFR 0_110.
 */
import java.util.TimerTask;

final class agk
extends TimerTask {
    private /* synthetic */ agj a;

    agk(agj agj2) {
        this.a = agj2;
    }

    @Override
    public final void run() {
        try {
            if (ahb.a && agf.c(this.a.a) != null) {
                agf.c(this.a.a).startScan();
            }
            return;
        }
        catch (Exception var1_1) {
            return;
        }
    }
}

