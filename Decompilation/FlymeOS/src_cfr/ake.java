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

public class ake {
    public static final akg a(Activity activity, akr akr2, UpdateInfo updateInfo) {
        return ake.a((Context)activity, akr2, updateInfo, false, false);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static final akg a(Context object, akr akr2, UpdateInfo updateInfo, boolean bl2, boolean bl3) {
        if (updateInfo == null || !updateInfo.mExistsUpdate) {
            anf.d("request display while no update!");
            return null;
        }
        akk.b((Context)object);
        if (anj.a()) {
            anf.d("request display while update in process, skip!");
            return null;
        }
        String string2 = aki.c((Context)object, updateInfo.mVersionName);
        if (anl.c((Context)object, string2)) {
            object = new ald((Context)object, null, updateInfo, string2);
        } else {
            object = new alo((Context)object, akr2, updateInfo, false, true);
            object.b(bl3);
        }
        object.a(bl2);
        return object.b();
    }

    public static final void a(Context context) {
        amx.a(context);
    }

    public static final void a(Context context, akn akn2, long l2, boolean bl2) {
        new akl(context, akn2, l2).a(bl2);
        ake.b(context);
    }

    public static final boolean a(Context context, String string2) {
        if (amx.a(context, string2)) {
            MzUpdateComponentService.a(context);
            return true;
        }
        return false;
    }

    public static final void b(Context context) {
        amx.d(context);
    }
}

