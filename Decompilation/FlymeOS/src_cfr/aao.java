/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.net.Uri
 *  java.lang.Object
 */
import android.net.Uri;

class aao
implements zz {
    final /* synthetic */ zy a;
    final /* synthetic */ aan b;
    private boolean c;

    aao(aan aan2, zy zy2) {
        this.b = aan2;
        this.a = zy2;
    }

    @Override
    public void a(Uri uri) {
        this.b.a(this.a);
        this.b.a(uri);
    }

    @Override
    public void a(boolean bl2) {
        this.c = bl2;
    }

    @Override
    public boolean a() {
        return this.c;
    }
}

