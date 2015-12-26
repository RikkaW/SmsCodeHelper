/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.pm.ApplicationInfo
 *  android.content.pm.PackageInfo
 *  android.content.pm.PackageManager
 *  android.content.pm.PackageManager$NameNotFoundException
 *  android.graphics.Bitmap
 *  android.graphics.Bitmap$Config
 *  android.graphics.Canvas
 *  android.graphics.drawable.Drawable
 *  android.net.ConnectivityManager
 *  android.net.NetworkInfo
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.Bundle
 *  android.telephony.TelephonyManager
 *  android.text.TextUtils
 *  android.util.Log
 *  java.io.File
 *  java.lang.Boolean
 *  java.lang.Class
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.reflect.Method
 *  java.security.MessageDigest
 */
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.MessageDigest;

public class anl {
    private static String a;
    private static String b;
    private static final char[] c;
    private static Boolean d;

    static {
        c = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        d = null;
    }

    public static final long a(String string2) {
        try {
            string2 = new File(string2);
            if (string2.exists() && string2.isFile()) {
                long l2 = string2.length();
                return l2;
            }
        }
        catch (Exception var0_1) {
            // empty catch block
        }
        return 0;
    }

    /*
     * Enabled aggressive block sorting
     */
    private static Bitmap a(Drawable drawable2) {
        int n2 = drawable2.getIntrinsicWidth();
        int n3 = drawable2.getIntrinsicHeight();
        Bitmap.Config config = drawable2.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;
        config = Bitmap.createBitmap((int)n2, (int)n3, (Bitmap.Config)config);
        Canvas canvas = new Canvas((Bitmap)config);
        drawable2.setBounds(0, 0, n2, n3);
        drawable2.draw(canvas);
        return config;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static final Bitmap a(String string2, Context context) {
        context = context.getPackageManager();
        string2 = context.getPackageInfo((String)string2, (int)0).applicationInfo.loadIcon((PackageManager)context);
        if (string2 == null) return null;
        try {
            return anl.a((Drawable)string2);
        }
        catch (Exception var0_1) {
            var0_1.printStackTrace();
        }
        return null;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static String a() {
        String string2;
        if (!anl.b()) {
            try {
                string2 = (String)aji.a("android.os.BuildExt", "MZ_MODEL");
            }
            catch (Exception var0_1) {
                string2 = null;
            }
        } else {
            string2 = null;
        }
        String string3 = string2;
        if (!TextUtils.isEmpty((CharSequence)string2)) return string3;
        return Build.MODEL;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String a(double d2) {
        int n2;
        if (d2 >= 1024.0) {
            if (d2 >= 1024.0 && d2 < 10240.0) {
                return String.format((String)"%dKB", (Object[])new Object[]{(int)(d2 / 1024.0)});
            }
            if (d2 >= 10240.0 && d2 < 102400.0) {
                return String.format((String)"%dKB", (Object[])new Object[]{(int)(d2 / 1024.0)});
            }
            if (d2 >= 102400.0 && d2 < 1048576.0) {
                return String.format((String)"%dKB", (Object[])new Object[]{(int)(d2 / 1024.0)});
            }
            if (d2 >= 1048576.0 && d2 < 1.048576E8) {
                return String.format((String)"%.2fMB", (Object[])new Object[]{d2 / 1048576.0});
            }
            if (d2 >= 1.048576E8 && d2 < 1.073741824E9) {
                return String.format((String)"%.1fMB", (Object[])new Object[]{d2 / 1048576.0});
            }
            if (d2 >= 1.073741824E9 && d2 < 1.073741824E10) {
                return String.format((String)"%.2fGB", (Object[])new Object[]{d2 / 1.073741824E9});
            }
            if (d2 < 1.073741824E10 || d2 >= 1.073741824E11) return String.format((String)"%dGB", (Object[])new Object[]{(int)(d2 / 1.073741824E9)});
            return String.format((String)"%.1fGB", (Object[])new Object[]{d2 / 1.073741824E9});
        }
        if (d2 > 0.0) {
            n2 = (int)d2;
            do {
                return String.format((String)"%dB", (Object[])new Object[]{n2});
                break;
            } while (true);
        }
        n2 = 0;
        return String.format((String)"%dB", (Object[])new Object[]{n2});
    }

    public static String a(Context context) {
        return anl.a(context, context.getPackageName());
    }

    public static String a(Context context, String string2) {
        String string3;
        string2 = string3 = anl.b(context, string2);
        if (!anl.l(context)) {
            string2 = string3;
            if (anl.c()) {
                string2 = string3 + "_i";
            }
        }
        return string2;
    }

    private static String a(String string2, String string3) {
        try {
            string2 = (String)aji.a("android.os.SystemProperties", "get", new Object[]{string2});
            return string2;
        }
        catch (Exception var0_1) {
            var0_1.printStackTrace();
            return string3;
        }
    }

    public static String a(byte[] arrby) {
        int n2 = 0;
        int n3 = arrby.length;
        char[] arrc = new char[n3 << 1];
        for (int i2 = 0; i2 < n3; ++i2) {
            int n4 = n2 + 1;
            arrc[n2] = c[(arrby[i2] & 240) >>> 4];
            n2 = n4 + 1;
            arrc[n4] = c[arrby[i2] & 15];
        }
        return new String(arrc);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static final String b(Context object) {
        Object object2;
        object = null;
        try {
            object = object2 = anl.a("ro.build.mask.id", "");
        }
        catch (Exception var1_2) {
            var1_2.printStackTrace();
        }
        object2 = object;
        if (!TextUtils.isEmpty((CharSequence)object)) return object2;
        return Build.DISPLAY;
    }

    public static String b(Context object, String string2) {
        try {
            object = object.getPackageManager().getPackageInfo((String)string2, (int)0).versionName;
            return object;
        }
        catch (PackageManager.NameNotFoundException var0_1) {
            return "";
        }
    }

    public static String b(String string2) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance((String)"MD5");
            messageDigest.update(string2.getBytes("utf-8"));
            string2 = anl.a(messageDigest.digest());
            return string2;
        }
        catch (Exception var0_1) {
            var0_1.printStackTrace();
            return null;
        }
    }

    public static boolean b() {
        try {
            boolean bl2 = (Boolean)aji.a("android.os.BuildExt", "isFlymeRom", null);
            return bl2;
        }
        catch (Exception var1_1) {
            return false;
        }
    }

    public static final String c(Context context) {
        return Build.VERSION.RELEASE;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean c() {
        try {
            return (Boolean)Class.forName((String)"android.os.BuildExt").getMethod("isProductInternational", new Class[0]).invoke((Object)null, new Object[0]);
        }
        catch (ClassNotFoundException var1_1) {
            var1_1.printStackTrace();
            do {
                return false;
                break;
            } while (true);
        }
        catch (NoSuchMethodException var1_2) {
            var1_2.printStackTrace();
            return false;
        }
        catch (InvocationTargetException var1_3) {
            var1_3.printStackTrace();
            return false;
        }
        catch (IllegalAccessException var1_4) {
            var1_4.printStackTrace();
            return false;
        }
    }

    public static final boolean c(Context context, String string2) {
        boolean bl2;
        block4 : {
            File file;
            boolean bl3 = false;
            try {
                file = new File(string2);
                bl2 = bl3;
            }
            catch (Exception var0_1) {
                var0_1.printStackTrace();
                return false;
            }
            if (!file.isFile()) break block4;
            bl2 = bl3;
            if (!file.exists()) break block4;
            context = context.getPackageManager().getPackageArchiveInfo(string2, 0);
            bl2 = bl3;
            if (context == null) break block4;
            bl2 = true;
        }
        return bl2;
    }

    public static final PackageInfo d(Context context, String string2) {
        try {
            File file = new File(string2);
            if (file.isFile() && file.exists()) {
                context = context.getPackageManager().getPackageArchiveInfo(string2, 0);
                return context;
            }
        }
        catch (Exception var0_1) {
            var0_1.printStackTrace();
        }
        return null;
    }

    public static final String d(Context context) {
        if (anl.l(context)) {
            return "All";
        }
        return anl.a();
    }

    public static boolean d() {
        try {
            boolean bl2 = (Boolean)aji.a("android.os.BuildExt", "IS_SHOPDEMO");
            return bl2;
        }
        catch (Exception var1_1) {
            return false;
        }
    }

    public static final String e(Context context) {
        return anl.g(context);
    }

    public static boolean e(Context context, String string2) {
        boolean bl2;
        block4 : {
            boolean bl3 = false;
            context = context.getPackageManager();
            try {
                context = context.getApplicationInfo(string2, 0);
                bl2 = bl3;
                if (context == null) break block4;
            }
            catch (PackageManager.NameNotFoundException var0_1) {
                return false;
            }
            if ((context.flags & 1) == 0) {
                int n2 = context.flags;
                bl2 = bl3;
                if ((n2 & 128) == 0) break block4;
            }
            bl2 = true;
        }
        return bl2;
    }

    public static final String f(Context context) {
        if (a == null) {
            a = anl.a("ro.serialno", null);
        }
        return a;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static String g(Context context) {
        if (TextUtils.isEmpty((CharSequence)b)) {
            try {
                b = (String)aji.a("android.telephony.MzTelephonyManager", "getDeviceId", null, null);
            }
            catch (Exception var1_1) {
                var1_1.printStackTrace();
            }
            if (TextUtils.isEmpty((CharSequence)b)) {
                try {
                    b = (String)aji.a("com.meizu.telephony.MzTelephonymanager", "getDeviceId", new Class[]{Context.class, Integer.TYPE}, new Object[]{context, 0});
                }
                catch (Exception var1_2) {}
            }
            if (TextUtils.isEmpty((CharSequence)b)) {
                b = ((TelephonyManager)context.getSystemService("phone")).getDeviceId();
            }
        }
        return b;
    }

    public static final String h(Context context) {
        PackageManager packageManager = context.getPackageManager();
        return context.getApplicationInfo().loadLabel(packageManager).toString();
    }

    public static boolean i(Context context) {
        if ((context = (ConnectivityManager)context.getSystemService("connectivity")) == null) {
            return false;
        }
        if ((context = context.getActiveNetworkInfo()) != null && context.isConnected()) {
            return true;
        }
        return false;
    }

    public static boolean j(Context context) {
        if ((context = (ConnectivityManager)context.getSystemService("connectivity")) != null && (context = context.getActiveNetworkInfo()) != null && context.isAvailable()) {
            if (context.getType() == 1) {
                return true;
            }
            return false;
        }
        return false;
    }

    public static String k(Context context) {
        if ((context = (TelephonyManager)context.getSystemService("phone")).getSimState() == 5) {
            return context.getSimOperator();
        }
        return "";
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static boolean l(Context context) {
        if (d == null) {
            try {
                context = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
                if (context.metaData != null) {
                    d = context.metaData.getBoolean("system_independent", false);
                    Log.d((String)"MzUpdateComponent", (String)("sSystemIndependent : " + (Object)d));
                }
            }
            catch (Exception var0_1) {
                var0_1.printStackTrace();
            }
            if (d == null) {
                d = false;
            }
        }
        return d;
    }
}

