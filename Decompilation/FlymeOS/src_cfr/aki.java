/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.os.Environment
 *  android.text.TextUtils
 *  android.util.Log
 *  java.io.File
 *  java.lang.Object
 *  java.lang.String
 */
import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;

public class aki {
    private static final String a(String string2) {
        return "update_cache_" + string2 + ".temp";
    }

    public static void a(Context context) {
        aki.a(context, null);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void a(Context object, String object2) {
        File[] arrfile = null;
        String string2 = aki.c(object.getPackageName());
        if (!TextUtils.isEmpty((CharSequence)object2)) {
            object = aki.a((String)object2);
            arrfile = aki.b((String)object2);
            object2 = object;
            object = arrfile;
        } else {
            object2 = null;
            object = arrfile;
        }
        if (!(arrfile = new File(string2)).exists()) return;
        {
            if (!arrfile.isDirectory()) {
                arrfile.delete();
                return;
            } else {
                if ((arrfile = arrfile.listFiles()) == null || arrfile.length <= 0) return;
                {
                    int n2 = arrfile.length;
                    int n3 = 0;
                    while (n3 < n2) {
                        string2 = arrfile[n3];
                        if (!(!string2.isFile() || object2 != null && string2.getName().equals(object2) || object != null && string2.getName().equals(object))) {
                            aki.d("delete cache file : " + string2.getName());
                            string2.delete();
                        }
                        ++n3;
                    }
                    return;
                }
            }
        }
    }

    public static final boolean a(String string2, String string3) {
        try {
            boolean bl2 = new File(string2).renameTo(new File(string3));
            return bl2;
        }
        catch (Exception var0_1) {
            var0_1.printStackTrace();
            return false;
        }
    }

    public static final String b(Context object, String string2) {
        string2 = aki.a(string2);
        object = aki.c(object.getPackageName());
        return (String)object + string2;
    }

    private static final String b(String string2) {
        return "update_cache_" + string2 + ".apk";
    }

    public static void b(Context object) {
        String string2 = object.getPackageName();
        String string3 = aki.c(string2);
        object = aki.b(anl.b((Context)object, string2));
        object = new File(string3 + (String)object);
        if (object.exists()) {
            aki.d("delete cur cache : " + object.getName());
            object.delete();
        }
    }

    public static final String c(Context object, String string2) {
        string2 = aki.b(string2);
        object = aki.c(object.getPackageName());
        return (String)object + string2;
    }

    private static final String c(String string2) {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/data/" + string2 + "/InstallCache/";
    }

    private static void d(String string2) {
        Log.d((String)"FileCacheManager", (String)string2);
    }
}

