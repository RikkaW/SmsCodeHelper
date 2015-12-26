/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.util.Log
 *  java.io.File
 *  java.io.FileOutputStream
 *  java.io.OutputStream
 *  java.io.PrintStream
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 *  java.text.SimpleDateFormat
 */
package cn.com.xy.sms.sdk.log;

import android.util.Log;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.util.DuoquUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;

public class LogManager {
    private static SimpleDateFormat a = null;
    public static boolean debug = false;
    public static boolean writeFileLog = false;

    private static SimpleDateFormat a() {
        synchronized (LogManager.class) {
            if (a == null) {
                a = new SimpleDateFormat("yyyy.MM.dd");
            }
            SimpleDateFormat simpleDateFormat = a;
            return simpleDateFormat;
        }
    }

    public static void d(String string2, String string3) {
        if (debug) {
            Log.d((String)string2, (String)string3);
        }
        LogManager.writeLogToFile(string2, string3);
    }

    public static void d(String string2, String string3, Throwable throwable) {
        if (debug) {
            Log.d((String)string2, (String)string3, (Throwable)throwable);
        }
        LogManager.writeLogToFile(string2, string3);
    }

    public static void e(String string2, String string3) {
        if (debug) {
            Log.e((String)string2, (String)string3);
        }
        LogManager.writeLogToFile(string2, string3);
    }

    public static void e(String string2, String string3, Throwable throwable) {
        if (debug) {
            Log.e((String)string2, (String)string3, (Throwable)throwable);
        }
        LogManager.writeLogToFile(string2, string3);
        try {
            DuoquUtils.getSdkDoAction().logError(string2, string3, throwable);
            return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    public static void i(String string2, String string3) {
        if (debug) {
            Log.i((String)string2, (String)string3);
        }
        LogManager.writeLogToFile(string2, string3);
    }

    public static void i(String string2, String string3, Throwable throwable) {
        if (debug) {
            Log.i((String)string2, (String)string3, (Throwable)throwable);
        }
        LogManager.writeLogToFile(string2, string3);
    }

    public static void ll(String string2, String string3) {
        if (debug) {
            Log.d((String)string2, (String)string3);
        }
        LogManager.writeLogToFile(string2, string3);
    }

    public static void w(String string2, String string3) {
        if (debug) {
            Log.w((String)string2, (String)string3);
        }
        LogManager.writeLogToFile(string2, string3);
    }

    public static void w(String string2, String string3, Throwable throwable) {
        if (debug) {
            Log.w((String)string2, (String)string3, (Throwable)throwable);
        }
        LogManager.writeLogToFile(string2, string3);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    public static void writeLogToFile(String string2, String string3) {
        void var1_2;
        SimpleDateFormat simpleDateFormat;
        if (!writeFileLog) {
            return;
        }
        try {
            simpleDateFormat = LogManager.a();
            // MONITORENTER : simpleDateFormat
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
        String string4 = simpleDateFormat.format((Object)System.currentTimeMillis());
        String string5 = String.valueOf((Object)Constant.getFilePath()) + File.separator + "logs";
        File file = new File(string5);
        if (!file.exists()) {
            file.mkdirs();
        }
        string4 = new PrintStream((OutputStream)new FileOutputStream(String.valueOf((Object)string5) + File.separator + string4 + ".ll.log", true));
        string4.println(String.valueOf((Object)string2) + "  " + (String)var1_2 + " time=" + System.currentTimeMillis());
        string4.close();
        // MONITOREXIT : simpleDateFormat
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    public static void writeLogToFile(String string2, String string3, String string4) {
        SimpleDateFormat simpleDateFormat;
        PrintStream printStream;
        void var1_2;
        if (!writeFileLog) {
            return;
        }
        try {
            simpleDateFormat = LogManager.a();
            // MONITORENTER : simpleDateFormat
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
        String string5 = simpleDateFormat.format((Object)System.currentTimeMillis());
        String string6 = String.valueOf((Object)Constant.getFilePath()) + File.separator + "logs";
        File file = new File(string6);
        if (!file.exists()) {
            file.mkdirs();
        }
        printStream = new PrintStream((OutputStream)new FileOutputStream(String.valueOf((Object)string6) + File.separator + string5 + (String)printStream, true));
        printStream.println(String.valueOf((Object)string2) + "  " + (String)var1_2 + " time=" + System.currentTimeMillis());
        printStream.close();
        // MONITOREXIT : simpleDateFormat
    }
}

