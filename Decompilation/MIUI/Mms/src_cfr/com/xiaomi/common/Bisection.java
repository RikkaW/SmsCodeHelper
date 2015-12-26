/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package com.xiaomi.common;

import com.xiaomi.common.IntInt;
import com.xiaomi.common.Log;

public class Bisection {
    public static int thr = 150;

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public static int bisectionSearch(int var0, int[] var1_1) {
        var4_2 = 0;
        var3_3 = var1_1.length - 1;
        var2_4 = 0;
        if (var0 == var1_1[var3_3]) {
            return var3_3;
        }
        if (var0 != var1_1[0]) ** GOTO lbl13
        return 0;
lbl-1000: // 1 sources:
        {
            if (var1_1[var5_5] < var0) {
                var4_2 = var5_5;
            } else {
                var3_3 = var5_5;
            }
            ++var2_4;
lbl13: // 2 sources:
            if (2 <= var3_3 - var4_2) continue;
            return -1;
            ** while (var1_1[var5_5 = var4_2 + (var3_3 - var4_2) / 2] != var0)
        }
lbl16: // 1 sources:
        return var5_5;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public static int bisectionSearch(int var0, IntInt[] var1_1) {
        var5_2 = 0;
        var4_3 = var1_1.length - 1;
        var2_4 = 0;
        var3_5 = 0;
        if (var0 != var1_1[var4_3].getFirst()) ** GOTO lbl9
        var0 = var4_3;
        var2_4 = var3_5;
        ** GOTO lbl12
lbl9: // 1 sources:
        if (var0 != var1_1[0].getFirst()) ** GOTO lbl20
        var0 = 0;
        var2_4 = var3_5;
lbl12: // 4 sources:
        do {
            Log.println("loops = " + var2_4);
            return var0;
            break;
        } while (true);
lbl-1000: // 1 sources:
        {
            if (var1_1[var3_5].getFirst() < var0) {
                var5_2 = var3_5;
            } else {
                var4_3 = var3_5;
            }
            ++var2_4;
lbl20: // 2 sources:
            if (2 <= var4_3 - var5_2) continue;
            var0 = -1;
            ** GOTO lbl12
            ** while (var1_1[var3_5 = var5_2 + (var4_3 - var5_2) / 2].getFirst() != var0)
        }
lbl24: // 1 sources:
        var0 = var3_5;
        ** while (true)
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static int orderSearch(int n, int[] arrn) {
        int n2 = 0;
        while (n2 < arrn.length) {
            int n3 = n2;
            if (arrn[n2] == n) return n3;
            ++n2;
        }
        return -1;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static int orderSearch(int n, IntInt[] arrintInt) {
        int n2 = 0;
        while (n2 < arrintInt.length) {
            int n3 = n2;
            if (arrintInt[n2].getFirst() == n) return n3;
            ++n2;
        }
        return -1;
    }

    public static int search(int n, int[] arrn) {
        if (arrn.length > thr) {
            return Bisection.bisectionSearch(n, arrn);
        }
        return Bisection.orderSearch(n, arrn);
    }

    public static int search(int n, IntInt[] arrintInt) {
        if (arrintInt.length > thr) {
            return Bisection.bisectionSearch(n, arrintInt);
        }
        return Bisection.orderSearch(n, arrintInt);
    }
}

