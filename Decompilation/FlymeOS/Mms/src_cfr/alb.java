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

public class alb
extends aks {
    private boolean e;

    public alb(Context context, UpdateInfo updateInfo, boolean bl2) {
        super(context, updateInfo);
        this.e = bl2;
        this.a(true);
    }

    static /* synthetic */ void a(alb alb2) {
        alb2.d();
    }

    static /* synthetic */ void b(alb alb2) {
        alb2.c();
    }

    private void c() {
    }

    private void d() {
        MzUpdateComponentService.a(this.a, this.b, null);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public aks.a a() {
        String string2;
        String string3;
        String string4 = anc.b(this.b, this.a);
        if (this.e) {
            string2 = this.a.getString(akq.d.mzuc_download_fail);
            string3 = this.a.getResources().getString(akq.d.mzuc_cancel_download);
            do {
                return new aks.a(string4, null, string2, this.a.getResources().getString(akq.d.mzuc_retry), string3, null, new alc(this));
                break;
            } while (true);
        }
        string2 = this.a.getString(akq.d.mzuc_install_fail);
        string3 = this.a.getResources().getString(akq.d.mzuc_cancel_install);
        return new aks.a(string4, null, string2, this.a.getResources().getString(akq.d.mzuc_retry), string3, null, new alc(this));
    }

}

