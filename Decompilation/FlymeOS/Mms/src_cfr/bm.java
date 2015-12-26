/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  java.io.BufferedReader
 *  java.io.File
 *  java.io.FileInputStream
 *  java.io.FileOutputStream
 *  java.io.InputStreamReader
 *  java.io.OutputStream
 *  java.io.Reader
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.regex.Pattern
 */
import android.content.Context;
import com.ted.android.contacts.common.ComManager;
import com.ted.android.contacts.common.DataBus;
import com.ted.android.contacts.common.util.NovoFileUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class bm {
    private static ArrayList<String> a = new ArrayList();
    private static boolean b = false;

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static void a(Context var0) {
        try {
            ComManager.a((Context)var0);
            var3_2 = NovoFileUtil.openLatestInputFile((Context)var0, "w.dat");
            if (var3_2 == null) ** GOTO lbl27
            var0 = String.valueOf((Object)var0.getCacheDir().getAbsolutePath()) + "/" + "w.dat";
            var5_3 = new File((String)var0);
            var4_4 = new FileOutputStream(var5_3);
            anv.a((InputStream)var3_2, (OutputStream)var4_4, DataBus.FILE_MASK);
            var4_4.close();
            var3_2.close();
            var6_5 = new FileInputStream((String)var0);
            var7_6 = new InputStreamReader((InputStream)var6_5, "utf-8");
            var8_7 = new BufferedReader((Reader)var7_6);
            var3_2 = var8_7.readLine();
            block4 : do {
                if (var3_2 != null) {
                    var0 = "^";
                    if (var3_2.length() > 0 && var3_2.charAt(0) == '+') {
                        var0 = "+^";
                        var2_9 = 1;
                    } else {
                        var2_9 = 0;
                    }
                } else {
                    var7_6.close();
                    var6_5.close();
                    var5_3.delete();
lbl27: // 3 sources:
                    do {
                        bm.b = true;
                        return;
                        break;
                    } while (true);
                }
                do {
                    block14 : {
                        if (var2_9 >= var3_2.length()) {
                            var0 = String.valueOf((Object)var0) + "$";
                            bm.a.add(var0);
                            var3_2 = var8_7.readLine();
                            continue block4;
                        }
                        var1_8 = var3_2.charAt(var2_9);
                        if (var1_8 != '*') {
                            var4_4 = var0;
                            if (var1_8 != '?') break block14;
                        }
                        var4_4 = String.valueOf((Object)var0) + ".";
                    }
                    var0 = String.valueOf((Object)var4_4) + var1_8;
                    ++var2_9;
                } while (true);
                break;
            } while (true);
        }
        catch (Exception var0_1) {
            var0_1.printStackTrace();
            ** continue;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean a(Context context, String string2) {
        if (string2 == null || string2.length() == 0) {
            return false;
        }
        if (!b) {
            bm.a(context);
        }
        int n2 = 0;
        while (n2 < a.size()) {
            if (bm.a((String)a.get(n2), string2)) {
                return true;
            }
            ++n2;
        }
        return false;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static boolean a(String string2, String string3) {
        if (string2 == null) return false;
        if (string2.length() == 0) {
            return false;
        }
        String string4 = string2;
        String string5 = string3;
        if (string2.charAt(0) == '+') {
            if (string3.charAt(0) != '+') return false;
            string4 = string2.substring(1);
            string5 = string3.substring(1);
        }
        try {
            return Pattern.matches((String)string4, (CharSequence)string5);
        }
        catch (Exception var0_1) {
            var0_1.printStackTrace();
            return false;
        }
    }
}

