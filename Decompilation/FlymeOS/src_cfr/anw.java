/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.net.ConnectivityManager
 *  android.net.NetworkInfo
 *  android.net.wifi.WifiInfo
 *  android.net.wifi.WifiManager
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.provider.Settings
 *  android.provider.Settings$Secure
 *  android.telephony.TelephonyManager
 *  android.text.TextUtils
 *  java.io.BufferedReader
 *  java.io.File
 *  java.io.FileOutputStream
 *  java.io.InputStreamReader
 *  java.io.RandomAccessFile
 *  java.io.Reader
 *  java.lang.Object
 *  java.lang.Runtime
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.UUID
 */
import android.content.ContentResolver;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.util.UUID;

public class anw {
    private static String a = null;

    public static Object a(Context context, String string2) {
        return context.getApplicationContext().getSystemService(string2);
    }

    private static String a() {
        String string2;
        String string3 = null;
        try {
            string3 = string2 = UUID.randomUUID().toString();
        }
        catch (Exception var1_2) {
            return string3;
        }
        string2 = string2.replaceAll("-", "").replace((CharSequence)":", (CharSequence)"").toLowerCase();
        return string2;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static String a(Context object) {
        synchronized (anw.class) {
            if (a == null) {
                File file = new File(object.getFilesDir(), "UID");
                if (file.exists()) {
                    a = anw.b((Context)object, file, true);
                    if (TextUtils.isEmpty((CharSequence)a)) {
                        anw.a((Context)object, file, true);
                        a = anw.b((Context)object, file, true);
                    }
                } else {
                    anw.a((Context)object, file, true);
                    a = anw.b((Context)object, file, true);
                }
            }
            if (a != null) return a;
            return "";
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static void a(Context context, File file, boolean bl2) {
        String string2;
        String string3;
        block9 : {
            block8 : {
                try {
                    TelephonyManager telephonyManager = (TelephonyManager)anw.a(context, "phone");
                    string3 = string2 = telephonyManager.getDeviceId();
                    if (anw.b(context, string2)) {
                        string3 = null;
                    }
                    string2 = string3;
                    if (string3 == null) {
                        string2 = anw.c();
                        string2 = anw.b(context, string2) ? null : "C" + string2;
                    }
                    string3 = string2;
                    if (string2 == null) {
                        string2 = anw.b();
                        string3 = anw.b(context, string2) ? null : "S" + string2;
                    }
                    string2 = string3;
                    if (string3 == null) {
                        string2 = anw.d(context);
                        string2 = anw.b(context, string2) ? null : "A" + string2;
                    }
                    if (string2 != null) break block8;
                    string2 = telephonyManager.getSubscriberId();
                    string3 = anw.b(context, string2) ? null : "I" + string2;
                    break block9;
                }
                catch (Exception var0_1) {
                    return;
                }
            }
            string3 = string2;
        }
        string2 = string3;
        if (string3 == null) {
            string2 = anw.c(context);
            string2 = anw.b(context, string2) ? null : "M" + string2;
        }
        string3 = string2;
        if (string2 == null) {
            string3 = "U" + anw.a();
        }
        a = string3;
        anw.a(context, a, file, bl2);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private static void a(Context var0, String var1_8, File var2_10, boolean var3_11) {
        if (TextUtils.isEmpty((CharSequence)var1_8)) {
            return;
        }
        var4_12 = null;
        var2_10 = new FileOutputStream(var2_10, false);
        var4_12 = var1_8;
        if (!var3_11) ** GOTO lbl10
        var4_12 = anv.a(var1_8, var0.getPackageName());
lbl10: // 2 sources:
        var2_10.write(var4_12.getBytes());
        if (var2_10 == null) return;
        try {
            var2_10.close();
            return;
        }
        catch (Exception var0_1) {
            return;
        }
        catch (Exception var0_2) {
            return;
            catch (Throwable var0_4) {
                var2_10 = var4_12;
                ** GOTO lbl23
                catch (Throwable var0_6) {}
lbl23: // 2 sources:
                if (var2_10 == null) throw var0_5;
                try {
                    var2_10.close();
                }
                catch (Exception var1_9) {
                    throw var0_5;
                }
                throw var0_5;
            }
            catch (Exception var0_7) {}
            if (var2_10 == null) return;
            try {
                var2_10.close();
                return;
            }
            catch (Exception var0_3) {
                return;
            }
        }
    }

    private static String b() {
        String string2 = null;
        if (Build.VERSION.SDK_INT >= 9) {
            string2 = Build.SERIAL;
        }
        return string2;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive exception aggregation
     */
    private static String b(Context var0, File var1_7, boolean var2_10) {
        block20 : {
            var3_11 = null;
            var1_7 = new RandomAccessFile(var1_7, "r");
            var4_12 = new byte[(int)var1_7.length()];
            var1_7.readFully(var4_12);
            if (!var2_10) break block20;
            var0 = anv.b(new String(var4_12), var0.getPackageName());
lbl9: // 2 sources:
            do {
                var3_11 = var0;
                if (var1_7 != null) {
                    var1_7.close();
                    var3_11 = var0;
                }
                do {
                    return var3_11;
                    break;
                } while (true);
                break;
            } while (true);
        }
        var0 = new String(var4_12);
        ** continue;
        catch (Throwable var0_1) {
            var1_7 = null;
lbl23: // 2 sources:
            do {
                if (var1_7 == null) ** continue;
                try {
                    var1_7.close();
                    return null;
                }
                catch (Exception var0_2) {
                    return null;
                }
                break;
            } while (true);
        }
        catch (Throwable var0_3) {
            var1_7 = null;
lbl32: // 2 sources:
            do {
                if (var1_7 != null) {
                    var1_7.close();
                }
lbl36: // 4 sources:
                do {
                    throw var0_4;
                    break;
                } while (true);
                catch (Exception var1_8) {
                    ** continue;
                }
                break;
            } while (true);
        }
        catch (Exception var1_9) {
            return var0;
        }
        catch (Throwable var0_5) {
            ** continue;
        }
        catch (Throwable var0_6) {
            ** continue;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean b(Context context) {
        context = (ConnectivityManager)anw.a(context, "connectivity");
        try {
            context = context.getNetworkInfo(1);
            if (context == null) return false;
        }
        catch (Exception var0_1) {
            return false;
        }
        return context.isConnected();
    }

    /*
     * Exception decompiling
     */
    private static boolean b(Context var0, String var1_6) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [1[TRYBLOCK]], but top level block is 17[UNCONDITIONALDOLOOP]
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:394)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:446)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:2859)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:805)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:220)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:165)
        // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:91)
        // org.benf.cfr.reader.entities.Method.analyse(Method.java:354)
        // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:751)
        // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:683)
        // org.benf.cfr.reader.Main.doJar(Main.java:128)
        // org.benf.cfr.reader.Main.main(Main.java:178)
        throw new IllegalStateException("Decompilation failed");
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static String c() {
        String string2;
        String string3;
        int n2;
        BufferedReader bufferedReader;
        String string4 = null;
        string3 = null;
        string2 = string4;
        try {
            bufferedReader = new BufferedReader((Reader)new InputStreamReader(Runtime.getRuntime().exec("cat /proc/cpuinfo").getInputStream()));
            n2 = 1;
        }
        catch (IOException var4_2) {}
        do {
            if (n2 >= 100) {
                string2 = string3;
                break;
            }
            string2 = string4;
            String string5 = bufferedReader.readLine();
            string2 = string3;
            if (string5 == null) break;
            string2 = string4;
            string5 = string5.toLowerCase();
            string2 = string4;
            int n3 = string5.indexOf("serial");
            string2 = string4;
            int n4 = string5.indexOf(":");
            if (n3 > -1 && n4 > 0) {
                string2 = string4;
                string2 = string3 = string5.substring(n4 + 1);
                string2 = string3 = string3.trim();
                break;
            }
            ++n2;
            continue;
            break;
        } while (true);
        string3 = string2;
        if (string2 == null) return string3;
        return string2.toLowerCase();
    }

    private static String c(Context object) {
        block5 : {
            Object object2;
            block6 : {
                try {
                    object = ((WifiManager)anw.a((Context)object, "wifi")).getConnectionInfo();
                    if (object == null) break block5;
                }
                catch (Exception var0_1) {
                    return null;
                }
                object2 = object = object.getMacAddress();
                if (object == null) break block6;
                try {
                    object2 = object.replaceAll("-", "").replaceAll(":", "").toLowerCase();
                }
                catch (Exception var1_3) {
                    return object;
                }
            }
            return object2;
        }
        return null;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static String d(Context object) {
        Object object3 = null;
        Object object2 = null;
        if (Build.VERSION.SDK_INT < 8) return object2;
        object2 = object3;
        try {
            object2 = object = Settings.Secure.getString((ContentResolver)object.getContentResolver(), (String)"android_id");
            if (object == null) return object2;
            object2 = object;
        }
        catch (Exception exception) {
            return object2;
        }
        return object = object.toLowerCase();
    }
}

