/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.MenuItem
 *  java.lang.Object
 *  java.util.ArrayList
 */
import android.support.v7.internal.view.menu.MenuBuilder;
import android.view.MenuItem;
import java.util.ArrayList;

class ark
implements Runnable {
    final /* synthetic */ arj.a a;

    ark(arj.a a2) {
        this.a = a2;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    @Override
    public void run() {
        arj.b b2 = arj.a(this.a.arj.this);
        if (b2 == null) return;
        if (arj.a.a(this.a) < 0) return;
        if (arj.b(this.a.arj.this) >= arj.c(this.a.arj.this).size()) return;
        Object object = (ArrayList)arj.c(this.a.arj.this).get(arj.b(this.a.arj.this));
        if (arj.a.a(this.a) >= object.size()) {
            return;
        }
        object = (arj.c)object.get(arj.a.a(this.a));
        if (object.e && arj.b(this.a.arj.this) < arj.c(this.a.arj.this).size() - 1) {
            arj.d(this.a.arj.this);
            this.a.invalidate();
            this.a.arj.this.a();
        } else if (object.d && arj.b(this.a.arj.this) > 0) {
            arj.e(this.a.arj.this);
            this.a.invalidate();
            this.a.arj.this.a();
        } else if (b2.onMenuItemSelected(arj.b.a(b2), object.b)) {
            b2.finish();
        }
        arj.a.a(this.a, -1);
    }
}

