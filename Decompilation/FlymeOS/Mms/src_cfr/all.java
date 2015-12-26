/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  java.lang.Object
 *  java.lang.String
 */
import android.content.Context;
import com.meizu.update.UpdateInfo;

public class all
extends aks {
    private akr e;

    public all(Context context, UpdateInfo updateInfo, akr akr2, boolean bl2) {
        super(context, updateInfo);
        this.a(bl2);
        this.e = akr2;
    }

    static /* synthetic */ void a(all all2) {
        all2.c();
    }

    static /* synthetic */ void b(all all2) {
        all2.d();
    }

    private void c() {
        if (this.b != null) {
            and.a(this.a).a(and.a.e, this.b.mVersionName, anl.b(this.a, this.a.getPackageName()));
            amx.b(this.a, this.b.mVersionName);
        }
    }

    private void d() {
        if (this.e != null) {
            this.e.a(1, this.b);
        }
    }

    @Override
    public aks.a a() {
        return new aks.a(null, null, this.a.getString(akq.d.mzuc_skip_warn_tip), this.a.getString(akq.d.mzuc_ok), this.a.getString(akq.d.mzuc_cancel), null, new alm(this));
    }

}

