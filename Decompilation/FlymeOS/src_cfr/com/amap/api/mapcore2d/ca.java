/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.util.ArrayList
 *  java.util.LinkedList
 */
package com.amap.api.mapcore2d;

import com.amap.api.mapcore2d.bx;
import com.amap.api.mapcore2d.n;
import java.util.ArrayList;
import java.util.LinkedList;

class ca
extends bx<n.a> {
    ca() {
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    @Override
    protected ArrayList<n.a> b(int var1_1, boolean var2_2) {
        var4_3 = 0;
        // MONITORENTER : this
        var8_4 = this.a;
        if (var8_4 == null) {
            var8_4 = null;
            // MONITOREXIT : this
            return var8_4;
        }
        var3_5 = this.a.size();
        var5_6 = var1_1;
        if (var1_1 > var3_5) {
            var5_6 = var3_5;
        }
        var8_4 = new ArrayList(var5_6);
        var6_7 = 0;
        var1_1 = var3_5;
        var3_5 = var6_7;
        while (var4_3 < var1_1) {
            if (this.a == null) {
                return null;
            }
            var9_9 = (n.a)this.a.get(var4_3);
            if (var9_9 != null) ** GOTO lbl26
            var7_8 = var1_1;
            var6_7 = var4_3;
            var1_1 = var3_5;
            var4_3 = var7_8;
            ** GOTO lbl50
lbl26: // 1 sources:
            var6_7 = var9_9.a;
            if (!var2_2) ** GOTO lbl36
            if (var6_7 != 0) ** GOTO lbl-1000
            var8_4.add((Object)var9_9);
            this.a.remove(var4_3);
            ++var3_5;
            var6_7 = var1_1 - 1;
            var1_1 = --var4_3;
            var4_3 = var6_7;
            ** GOTO lbl47
lbl36: // 1 sources:
            if (var6_7 < 0) {
                var8_4.add((Object)var9_9);
                this.a.remove(var4_3);
                ++var3_5;
                var6_7 = var1_1 - 1;
                var1_1 = --var4_3;
                var4_3 = var6_7;
            } else lbl-1000: // 2 sources:
            {
                var6_7 = var1_1;
                var1_1 = var4_3;
                var4_3 = var6_7;
            }
lbl47: // 3 sources:
            var6_7 = var1_1;
            var1_1 = var3_5;
            if (var3_5 >= var5_6) break;
lbl50: // 2 sources:
            var7_8 = var4_3;
            var4_3 = var6_7 + 1;
            var3_5 = var1_1;
            var1_1 = var7_8;
        }
        this.b();
        return var8_4;
    }
}

