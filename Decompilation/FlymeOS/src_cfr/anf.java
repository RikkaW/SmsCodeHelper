/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.os.Environment
 *  android.util.Log
 *  java.io.File
 *  java.io.FileOutputStream
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuffer
 *  java.lang.Thread
 *  java.lang.Throwable
 *  java.util.Calendar
 */
import android.content.Context;
import android.os.Environment;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class anf {
    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static void a(Context object, String string2) {
        boolean bl2 = true;
        anf.c(string2);
        object = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/data/" + object.getPackageName() + "/";
        File file = new File((String)object);
        if (!file.exists() || !file.isDirectory()) {
            file.mkdir();
        }
        if (!(object = new File((String)object + "update_component_log")).exists()) {
            if (!object.createNewFile()) {
                return;
            }
        } else if (object.length() > 16384) {
            bl2 = false;
        }
        file = Calendar.getInstance();
        int n2 = file.get(1);
        int n3 = file.get(2);
        int n4 = file.get(5);
        int n5 = file.get(11);
        int n6 = file.get(12);
        int n7 = file.get(13);
        file = new StringBuffer();
        file.append("[");
        file.append(String.format((String)"%04d-%02d-%02d %02d:%02d:%02d", (Object[])new Object[]{n2, n3 + 1, n4, n5, n6, n7}));
        file.append("]");
        file.append(string2);
        file.append("\n");
        try {
            string2 = new FileOutputStream((File)object, bl2);
        }
        catch (Throwable var0_2) {
            string2 = null;
            if (string2 == null) throw object;
            try {
                string2.close();
                throw object;
            }
            catch (IOException iOException) {
                iOException.printStackTrace();
                return;
            }
            catch (Exception exception) {
                exception.printStackTrace();
                return;
            }
        }
        string2.write(file.toString().getBytes());
        if (string2 == null) return;
        {
            catch (Throwable throwable) {}
        }
        string2.close();
    }

    public static final void a(String string2) {
    }

    public static void b(Context context, String string2) {
        new Thread((Runnable)new ang(context, string2)).start();
    }

    public static final void b(String string2) {
        Log.d((String)"MzUpdateComponent", (String)string2);
    }

    public static final void c(String string2) {
        Log.w((String)"MzUpdateComponent", (String)string2);
    }

    public static final void d(String string2) {
        Log.e((String)"MzUpdateComponent", (String)string2);
    }
}

