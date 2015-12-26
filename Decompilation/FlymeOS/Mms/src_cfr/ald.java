/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.ProgressDialog
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnCancelListener
 *  android.os.Handler
 *  android.os.Looper
 *  android.text.TextUtils
 *  java.lang.Object
 *  java.lang.String
 */
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.meizu.update.UpdateInfo;
import com.meizu.update.iresponse.MzUpdateResponse;
import com.meizu.update.service.MzUpdateComponentService;

public class ald
extends aks {
    private String e;
    private Handler f;
    private akr g;
    private ProgressDialog h;
    private amt i;

    public ald(Context context, akr akr2, UpdateInfo updateInfo, String string2) {
        super(context, updateInfo);
        this.i = new ale(this);
        if (TextUtils.isEmpty((CharSequence)string2)) {
            throw new IllegalArgumentException("params cant be null!");
        }
        this.g = akr2;
        this.e = string2;
        if (this.g != null) {
            this.f = new Handler(context.getMainLooper());
            this.h = anm.a(context);
            this.h.setMessage((CharSequence)context.getString(akq.d.mzuc_installing));
            this.h.setCancelable(false);
            this.h.setOnCancelListener((DialogInterface.OnCancelListener)new alg(this));
        }
    }

    private void a(int n2) {
        this.g();
        switch (n2) {
            default: {
                return;
            }
            case 1: {
                this.d();
                return;
            }
            case 2: {
                this.e();
                return;
            }
            case 3: 
        }
        this.h();
    }

    static /* synthetic */ void a(ald ald2) {
        ald2.d();
    }

    static /* synthetic */ void a(ald ald2, int n2) {
        ald2.a(n2);
    }

    static /* synthetic */ void a(ald ald2, Runnable runnable) {
        ald2.a(runnable);
    }

    private void a(Runnable runnable) {
        this.f.post(runnable);
    }

    private void d() {
        if (this.g != null) {
            this.g.a(1, this.b);
        }
    }

    private void e() {
        if (this.g != null) {
            this.g.a(3, this.b);
        }
    }

    private void f() {
        try {
            if (this.h != null) {
                this.h.show();
            }
            return;
        }
        catch (Exception var1_1) {
            return;
        }
    }

    private void g() {
        try {
            if (this.h != null && this.h.isShowing()) {
                this.h.dismiss();
            }
            return;
        }
        catch (Exception var1_1) {
            return;
        }
    }

    private void h() {
        amr.a(this.a, this.e, this.b);
        this.f.postDelayed((Runnable)new ali(this), 1000);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public aks.a a() {
        String string2 = anl.h(this.a);
        String string3 = String.format((String)this.a.getString(akq.d.mzuc_download_finish_s), (Object[])new Object[]{string2, this.b.mVersionName});
        String string4 = this.a.getString(akq.d.mzuc_install_immediately);
        string2 = !this.b.mNeedUpdate ? this.a.getString(akq.d.mzuc_install_later) : null;
        and.a(this.a).a(and.a.i, this.b.mVersionName);
        return new aks.a(null, null, string3, string4, string2, null, new alh(this));
    }

    protected void c() {
        this.f();
        MzUpdateResponse mzUpdateResponse = null;
        if (this.g != null) {
            mzUpdateResponse = new MzUpdateResponse(this.i);
        }
        MzUpdateComponentService.a(this.a, this.b, this.e, mzUpdateResponse);
    }

}

