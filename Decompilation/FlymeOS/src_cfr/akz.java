/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  java.lang.Object
 *  java.lang.String
 */
import android.content.Context;
import android.content.res.Resources;
import com.meizu.update.UpdateInfo;
import com.meizu.update.service.MzUpdateComponentService;

public class akz
extends aks {
    public akz(Context context, UpdateInfo updateInfo) {
        super(context, updateInfo);
        this.a(true);
    }

    static /* synthetic */ void a(akz akz2) {
        akz2.c();
    }

    private void c() {
        MzUpdateComponentService.c(this.a);
    }

    @Override
    public aks.a a() {
        return new aks.a(this.a.getString(akq.d.mzuc_downloading), null, anc.b(this.b, this.a) + " , " + this.b.mSize, this.a.getResources().getString(akq.d.mzuc_delete), this.a.getResources().getString(akq.d.mzuc_cancel), null, new ala(this));
    }

}

