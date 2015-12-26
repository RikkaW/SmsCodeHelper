/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Service
 *  android.content.Context
 *  java.lang.Object
 *  java.lang.String
 */
import android.app.Service;
import android.content.Context;
import com.meizu.update.UpdateInfo;
import com.meizu.update.service.MzUpdateComponentService;

public class anb
implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ String b;
    final /* synthetic */ UpdateInfo c;
    final /* synthetic */ MzUpdateComponentService d;

    public anb(MzUpdateComponentService mzUpdateComponentService, Context context, String string2, UpdateInfo updateInfo) {
        this.d = mzUpdateComponentService;
        this.a = context;
        this.b = string2;
        this.c = updateInfo;
    }

    @Override
    public void run() {
        try {
            amr.a(this.a, this.b, this.c);
            return;
        }
        catch (Exception var1_1) {
            var1_1.printStackTrace();
            and.a((Context)this.d).a(and.a.m, this.c.mVersionName, anl.b((Context)this.d, this.d.getPackageName()));
            new anc(this.d, this.c).d();
            return;
        }
    }
}

