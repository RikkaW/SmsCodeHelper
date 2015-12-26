/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.Intent
 *  android.content.pm.PackageManager
 *  android.net.Uri
 *  java.io.File
 *  java.lang.Class
 *  java.lang.Enum
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 */
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.IPackageInstallObserver;
import android.content.pm.PackageManager;
import android.net.Uri;
import com.meizu.update.UpdateInfo;
import java.io.File;

public class amr {
    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    public static a a(Context context, String object) {
        int n2;
        Object object2 = new Object();
        a a2 = a.b;
        try {
            int n3 = (Integer)aji.a("android.content.pm.PackageManager", "INSTALL_REPLACE_EXISTING");
            n2 = (Integer)aji.a("android.content.pm.PackageManager", "INSTALL_SUCCEEDED");
            PackageManager packageManager = context.getPackageManager();
            Class class_ = Integer.TYPE;
            object = Uri.parse((String)("file://" + (String)object));
            ams ams2 = new ams(n2, a2, object2);
            aji.a((Object)packageManager, "installPackage", new Class[]{Uri.class, IPackageInstallObserver.class, class_, String.class}, new Object[]{object, ams2, n3, null});
            // MONITORENTER : object2
        }
        catch (Exception var1_4) {
            anf.a(context, "background install error :" + var1_4.getMessage());
            var1_4.printStackTrace();
            return a.a;
        }
        try {
            object2.wait(120000);
            // MONITOREXIT : object2
        }
        catch (InterruptedException var1_2) {
            var1_2.printStackTrace();
            a a3 = a.c;
            // MONITOREXIT : object2
            return a3;
        }
        if (a2.a() == n2) return a2;
        object = a.c;
        object.a(a2.a());
        return object;
    }

    public static final Intent a(String string2) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(Uri.fromFile((File)new File(string2)), "application/vnd.android.package-archive");
        return intent;
    }

    public static final void a(Context context, String string2, UpdateInfo updateInfo) {
        amy.a(context, updateInfo);
        string2 = amr.a(string2);
        if (!(context instanceof Activity)) {
            string2.addFlags(268435456);
        }
        context.startActivity((Intent)string2);
    }

    public static enum a {
        a,
        b,
        c;
        
        private int d = -10000;

        private a() {
        }

        public int a() {
            return this.d;
        }

        protected void a(int n2) {
            this.d = n2;
        }
    }

}

