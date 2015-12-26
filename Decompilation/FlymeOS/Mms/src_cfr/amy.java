/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ComponentName
 *  android.content.Context
 *  android.content.Intent
 *  android.content.pm.PackageManager
 *  android.os.Parcelable
 *  java.lang.Object
 *  java.lang.String
 */
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Parcelable;
import com.meizu.update.UpdateInfo;
import com.meizu.update.service.MzUpdateComponentService;

public class amy {
    public static Intent a(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            context = packageManager.getLaunchIntentForPackage(context.getPackageName());
            return context;
        }
        catch (Exception var0_1) {
            return null;
        }
    }

    public static void a(Context context, UpdateInfo updateInfo) {
        try {
            updateInfo = MzUpdateComponentService.g(context, updateInfo);
            Intent intent = new Intent("com.meizu.appupdate.intent.wakeup");
            intent.putExtra("PendingIntent", (Parcelable)updateInfo);
            intent.setPackage("com.meizu.cloud");
            context.startService(intent);
            return;
        }
        catch (Exception var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }
}

