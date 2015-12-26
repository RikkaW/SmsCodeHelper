/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.ProgressDialog
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnCancelListener
 *  android.content.res.Resources
 *  android.os.Bundle
 *  android.os.Handler
 *  android.os.Looper
 *  java.lang.Object
 *  java.lang.String
 */
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.meizu.update.UpdateInfo;
import com.meizu.update.iresponse.MzUpdateResponse;
import com.meizu.update.service.MzUpdateComponentService;

public class alo
extends aks {
    private akr e;
    private Handler f;
    private ProgressDialog g;
    private boolean h;
    private boolean i;
    private boolean j;
    private amt k;

    public alo(Context context, akr akr2, UpdateInfo updateInfo, boolean bl2, boolean bl3) {
        super(context, updateInfo);
        this.k = new alp(this);
        this.a(bl2);
        this.e = akr2;
        this.h = bl3;
        if (this.e != null) {
            this.f = new Handler(context.getMainLooper());
            this.g = anm.a(context);
            this.g.setMessage((CharSequence)context.getString(akq.d.mzuc_downloading));
            this.g.setOnCancelListener((DialogInterface.OnCancelListener)new alr(this));
        }
    }

    private void a(int n2, Bundle object) {
        this.h();
        if (this.i) {
            this.c();
            return;
        }
        switch (n2) {
            default: {
                return;
            }
            case 0: {
                object = object.getString("apk_path");
                new ald(this.a, this.e, this.b, (String)object).c();
                return;
            }
            case 2: {
                this.d();
                return;
            }
            case 1: 
        }
        this.c();
    }

    static /* synthetic */ void a(alo alo2) {
        alo2.f();
    }

    static /* synthetic */ void a(alo alo2, int n2, Bundle bundle) {
        alo2.a(n2, bundle);
    }

    static /* synthetic */ void a(alo alo2, Runnable runnable) {
        alo2.a(runnable);
    }

    private void a(Runnable runnable) {
        this.f.post(runnable);
    }

    static /* synthetic */ boolean a(alo alo2, boolean bl2) {
        alo2.i = bl2;
        return bl2;
    }

    static /* synthetic */ boolean b(alo alo2) {
        return alo2.j;
    }

    private void c() {
        if (this.e != null) {
            this.e.a(1, this.b);
        }
    }

    static /* synthetic */ void c(alo alo2) {
        alo2.e();
    }

    private void d() {
        if (this.e != null) {
            this.e.a(2, this.b);
        }
    }

    static /* synthetic */ void d(alo alo2) {
        alo2.c();
    }

    private void e() {
        MzUpdateResponse mzUpdateResponse = null;
        if (this.e != null) {
            mzUpdateResponse = new MzUpdateResponse(this.k);
        }
        this.g();
        MzUpdateComponentService.a(this.a, this.b, mzUpdateResponse);
    }

    private void f() {
        MzUpdateComponentService.c(this.a);
    }

    private void g() {
        if (this.g != null) {
            this.g.show();
        }
    }

    private void h() {
        try {
            if (this.g != null && this.g.isShowing()) {
                this.g.dismiss();
            }
            return;
        }
        catch (Exception var1_1) {
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public aks.a a() {
        String string2;
        String string3 = null;
        String string4 = String.format((String)this.a.getString(akq.d.mzuc_found_update_s), (Object[])new Object[]{this.b.mVersionName});
        String string5 = String.format((String)this.a.getString(akq.d.mzuc_file_size_s), (Object[])new Object[]{this.b.mSize});
        String string6 = this.b.mVersionDesc;
        String string7 = this.a.getResources().getString(akq.d.mzuc_update_immediately);
        if (!this.b.mNeedUpdate) {
            string2 = string3;
            if (this.h) {
                string2 = string3;
                if (!this.j) {
                    string2 = this.a.getResources().getString(akq.d.mzuc_skip_version);
                }
            }
            string3 = this.j ? this.a.getString(akq.d.mzuc_cancel) : this.a.getString(akq.d.mzuc_warn_later);
        } else {
            string3 = null;
            string2 = null;
        }
        and.a(this.a).a(and.a.b, this.b.mVersionName, anl.b(this.a, this.a.getPackageName()), this.j);
        return new aks.a(string4, string5, string6, string7, string2, string3, new als(this));
    }

    public void b(boolean bl2) {
        this.j = bl2;
    }

}

