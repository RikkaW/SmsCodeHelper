/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.AssetManager
 *  android.database.sqlite.SQLiteDatabase
 *  android.util.Log
 *  java.io.BufferedReader
 *  java.io.InputStreamReader
 *  java.io.Reader
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class aok {
    private static final String a = aok.class.getSimpleName();

    /*
     * Unable to fully structure code
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void a(SQLiteDatabase var0, Context var1_2, String var2_7) {
        block23 : {
            var7_8 = new StringBuilder();
            var6_9 = null;
            var5_10 = null;
            var5_10 = var1_2 = var1_2.getAssets().open(var2_7);
            var6_9 = var1_2;
            try {
                var2_7 = new BufferedReader((Reader)new InputStreamReader((InputStream)var1_2));
            }
            catch (Exception var1_3) {
                var6_9 = var5_10;
                try {
                    Log.e((String)aok.a, (String)"open sql file error:", (Throwable)var1_3);
                    if (var5_10 == null) ** GOTO lbl19
                }
                catch (Throwable var0_1) {
                    if (var6_9 != null) {
                        var6_9.close();
                    }
                    do {
                        throw var0_1;
                        break;
                    } while (true);
                    catch (IOException var1_5) {
                        Log.e((String)aok.a, (String)"close sql file error", (Throwable)var1_5);
                        throw var0_1;
                    }
                }
                try {
                    var5_10.close();
                }
                catch (IOException var1_4) {
                    Log.e((String)aok.a, (String)"close sql file error", (Throwable)var1_4);
                }
                ** GOTO lbl19
            }
            do {
                block21 : {
                    block22 : {
                        var5_10 = var1_2;
                        var6_9 = var1_2;
                        var8_11 = var2_7.readLine();
                        if (var8_11 != null) break block21;
                        if (var1_2 == null) break block22;
                        var1_2.close();
                    }
lbl19: // 5 sources:
                    do {
                        var1_2 = var7_8.toString().replaceAll("--.*\n", " ").replaceAll("\n", " ").split(";");
                        var4_12 = var1_2.length;
                        var3_13 = 0;
lbl23: // 2 sources:
                        if (var3_13 >= var4_12) {
                            return;
                        }
                        break block23;
                        break;
                    } while (true);
                }
                var5_10 = var1_2;
                var6_9 = var1_2;
                var7_8.append(var8_11);
                var5_10 = var1_2;
                var6_9 = var1_2;
                var7_8.append("\n");
                continue;
                break;
            } while (true);
            catch (IOException var1_6) {
                Log.e((String)aok.a, (String)"close sql file error", (Throwable)var1_6);
                ** continue;
            }
        }
        var2_7 = var1_2[var3_13];
        if (var2_7.trim().length() > 0) {
            var0.execSQL(var2_7);
        }
        ++var3_13;
        ** GOTO lbl23
    }
}

