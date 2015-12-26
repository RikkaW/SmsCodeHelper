/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.AssetManager
 *  java.lang.Boolean
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.Hashtable
 *  java.util.Properties
 *  java.util.StringTokenizer
 */
package com.android.mms.jwap_port;

import android.content.Context;
import android.content.res.AssetManager;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.StringTokenizer;

public class TransTable {
    private static Hashtable tables = new Hashtable();
    private Hashtable mib2str = new Hashtable();
    private Hashtable str2mib = new Hashtable();

    private TransTable() {
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    public static TransTable getTable(Context var0, String var1_4) throws RuntimeException {
        if (var1_4 == null) {
            return null;
        }
        var3_5 = TransTable.tables.get((Object)var1_4);
        if (var3_5 != null) ** GOTO lbl62
        var6_7 = TransTable.tables;
        // MONITORENTER : var6_7
        var3_5 = var4_8 = TransTable.tables.get((Object)var1_4);
        if (var4_8 != null) ** GOTO lbl61
        var4_8 = new TransTable();
        var3_5 = null;
        try {
            var0 = var0.getAssets().open(var1_4);
        }
        catch (IOException var0_1) {
            var0_1.printStackTrace();
            var0 = var3_5;
        }
        if (var0 == null) {
            TransTable.tables.put((Object)var1_4, (Object)new byte[0]);
            throw new RuntimeException(var1_4 + ": Unable to load translation table");
        }
        var7_9 = new Properties();
        var7_9.load((InputStream)var0);
        ** try [egrp 5[TRYBLOCK] [9 : 136->140)] { 
lbl-1000: // 2 sources:
        {
            catch (IOException var0_3) {
                TransTable.tables.put((Object)var1_4, (Object)new byte[0]);
                throw new RuntimeException(var1_4 + ": Unable to load translation table");
            }
        }
lbl27: // 1 sources:
        ** GOTO lbl32
        catch (Throwable var3_6) {
            var0.close();
            throw var3_6;
        }
lbl32: // 1 sources:
        var0.close();
        TransTable.tables.put((Object)var1_4, var4_8);
        var8_10 = (TransTable)var4_8;
        var2_11 = Boolean.valueOf((String)var7_9.getProperty("transtable.aliases"));
        var9_12 = var7_9.getProperty("transtable.delimiter", ",");
        var10_13 = var7_9.keys();
        block13 : do {
            var3_5 = var4_8;
            if (!var10_13.hasMoreElements()) break;
            var11_15 = (String)var10_13.nextElement();
            if ("transtable.aliases".equals((Object)var11_15) || "transtable.delimiter".equals((Object)var11_15)) continue;
            var5_14 = var7_9.getProperty(var11_15).trim();
            var0 = null;
            var3_5 = var5_14;
            if (var2_11) {
                var0 = new StringTokenizer(var5_14, var9_12);
                var3_5 = var0.nextToken().trim();
            }
            try {
                var5_14 = Integer.decode((String)var11_15);
                var8_10.mib2str.put((Object)var5_14, var3_5);
                var8_10.str2mib.put((Object)var3_5.toLowerCase(), (Object)var5_14);
                if (!var2_11) continue;
                do {
                    if (!var0.hasMoreTokens()) continue block13;
                    var3_5 = var0.nextToken().trim().toLowerCase();
                    var8_10.str2mib.put(var3_5, (Object)var5_14);
                } while (true);
            }
            catch (NumberFormatException var0_2) {
                continue;
            }
            break;
        } while (true);
lbl61: // 2 sources:
        // MONITOREXIT : var6_7
lbl62: // 2 sources:
        if (var3_5 == null) return (TransTable)var3_5;
        if (var3_5 instanceof TransTable != false) return (TransTable)var3_5;
        throw new RuntimeException(var1_4 + ": Unable to load translation table.");
    }

    public String code2str(int n) {
        return (String)this.mib2str.get((Object)new Integer(n));
    }
}

