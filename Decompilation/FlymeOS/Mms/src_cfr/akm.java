/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
import com.meizu.update.UpdateInfo;

class akm
implements Runnable {
    final /* synthetic */ boolean a;
    final /* synthetic */ akl b;

    akm(akl akl2, boolean bl2) {
        this.b = akl2;
        this.a = bl2;
    }

    @Override
    public void run() {
        UpdateInfo updateInfo = akl.a(this.b).a(this.a);
        if (updateInfo != null) {
            akl.a(this.b).a(updateInfo);
            return;
        }
        akl.a(this.b).a();
    }
}

