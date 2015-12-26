/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
import com.android.mms.view.MeizuSearchListItem;

public class ada
implements Runnable {
    final /* synthetic */ MeizuSearchListItem a;

    public ada(MeizuSearchListItem meizuSearchListItem) {
        this.a = meizuSearchListItem;
    }

    @Override
    public void run() {
        MeizuSearchListItem.a(this.a, false);
    }
}

