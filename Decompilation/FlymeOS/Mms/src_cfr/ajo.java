/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
import com.meizu.statsapp.UsageStatusLog;

class ajo
implements Runnable {
    final /* synthetic */ ajn a;

    ajo(ajn ajn2) {
        this.a = ajn2;
    }

    @Override
    public void run() {
        ajn.a(this.a).clear();
        UsageStatusLog.d("UsageStatsManagerServer", "cleared packageSessionMap ");
    }
}

