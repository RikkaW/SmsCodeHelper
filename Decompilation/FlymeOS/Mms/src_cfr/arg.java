/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
import flyme.support.v7.widget.MzRecyclerView;

public class arg
implements Runnable {
    final /* synthetic */ MzRecyclerView a;

    public arg(MzRecyclerView mzRecyclerView) {
        this.a = mzRecyclerView;
    }

    @Override
    public void run() {
        this.a.d();
        MzRecyclerView.a(this.a, null);
    }
}

