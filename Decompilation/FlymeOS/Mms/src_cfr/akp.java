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

public class akp {
    public static void a(Activity activity, UpdateInfo updateInfo) {
        ake.a(activity, null, updateInfo);
    }

    public static void a(Context context, akn akn2) {
        ake.a(context, akn2, -1, false);
    }

    public static final boolean a(Context context, String string2) {
        return ake.a(context, string2);
    }
}

