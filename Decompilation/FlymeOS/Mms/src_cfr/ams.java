/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
import android.content.pm.IPackageInstallObserver;

final class ams
extends IPackageInstallObserver.Stub {
    final /* synthetic */ int a;
    final /* synthetic */ amr.a b;
    final /* synthetic */ Object c;

    ams(int n2, amr.a a2, Object object) {
        this.a = n2;
        this.b = a2;
        this.c = object;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void packageInstalled(String object, int n2) {
        if (n2 != this.a) {
            anf.c("install return code : " + n2);
        }
        this.b.a(n2);
        object = this.c;
        synchronized (object) {
            this.c.notify();
            return;
        }
    }
}

