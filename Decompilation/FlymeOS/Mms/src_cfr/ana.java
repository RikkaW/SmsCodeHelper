/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  java.lang.Object
 *  java.lang.String
 */
import android.app.Activity;
import android.content.Context;
import com.meizu.update.UpdateInfo;
import com.meizu.update.service.MzUpdateComponentService;

public class ana
implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ UpdateInfo b;
    final /* synthetic */ String c;
    final /* synthetic */ MzUpdateComponentService d;

    public ana(MzUpdateComponentService mzUpdateComponentService, Context context, UpdateInfo updateInfo, String string2) {
        this.d = mzUpdateComponentService;
        this.a = context;
        this.b = updateInfo;
        this.c = string2;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void run() {
        ald ald2 = new ald(this.a, null, this.b, this.c);
        boolean bl2 = !(this.a instanceof Activity);
        ald2.a(bl2);
        ald2.b();
    }
}

