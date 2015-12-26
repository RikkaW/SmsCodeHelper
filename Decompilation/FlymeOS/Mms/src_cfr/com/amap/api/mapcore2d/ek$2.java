/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  java.lang.Object
 *  java.lang.Throwable
 */
package com.amap.api.mapcore2d;

import android.content.Context;
import com.amap.api.mapcore2d.ed;
import com.amap.api.mapcore2d.el;
import java.util.concurrent.RejectedExecutionException;

final class ek$2
implements Runnable {
    final /* synthetic */ Context a;

    ek$2(Context context) {
        this.a = context;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive exception aggregation
     */
    @Override
    public void run() {
        block30 : {
            var4_1 = null;
            var3_2 = null;
            var1_4 = el.a(this.a, 0);
            var2_9 = el.a(this.a, 1);
            try {
                var4_1 = var3_2 = el.a(this.a, 2);
                var1_4.b(this.a);
                var4_1 = var3_2;
                var2_9.b(this.a);
                var4_1 = var3_2;
                var3_2.b(this.a);
                if (var1_4 == null) break block30;
            }
            catch (RejectedExecutionException var3_3) {
                var3_2 = var4_1;
                ** continue;
            }
            var1_4.c();
        }
        if (var2_9 != null) {
            var2_9.c();
        }
        if (var3_2 == null) ** GOTO lbl24
lbl22: // 2 sources:
        do {
            var3_2.c();
lbl24: // 4 sources:
            do {
                return;
                break;
            } while (true);
            break;
        } while (true);
        catch (Throwable var1_5) {
            block31 : {
                var3_2 = null;
                var4_1 = null;
                var2_9 = null;
lbl31: // 5 sources:
                ed.a((Throwable)var1_4, "Log", "processLog");
                var1_4.printStackTrace();
                if (var4_1 == null) break block31;
                var4_1.c();
            }
            if (var3_2 != null) {
                var3_2.c();
            }
            if (var2_9 == null) ** GOTO lbl24
            var2_9.c();
            return;
        }
        catch (Throwable var1_6) {
            var3_2 = null;
            var4_1 = null;
            var2_9 = null;
lbl45: // 5 sources:
            do {
                if (var4_1 != null) {
                    var4_1.c();
                }
                if (var3_2 != null) {
                    var3_2.c();
                }
                if (var2_9 != null) {
                    var2_9.c();
                }
                throw var1_4;
                break;
            } while (true);
        }
        catch (RejectedExecutionException var1_7) {
            var2_9 = null;
            var1_4 = null;
lbl56: // 3 sources:
            do {
                if (var1_4 != null) {
                    var1_4.c();
                }
                if (var2_9 != null) {
                    var2_9.c();
                }
                if (var3_2 == null) ** continue;
                ** continue;
                break;
            } while (true);
        }
        catch (Throwable var2_10) {
            var4_1 = var1_4;
            var3_2 = null;
            var1_4 = var2_10;
            var2_9 = null;
            ** GOTO lbl45
        }
        {
            catch (Throwable var5_13) {
                var4_1 = var1_4;
                var3_2 = var2_9;
                var2_9 = null;
                var1_4 = var5_13;
                ** GOTO lbl45
            }
            catch (Throwable var6_17) {
                var4_1 = var1_4;
                var5_14 = var2_9;
                var2_9 = var3_2;
                var1_4 = var6_17;
                var3_2 = var5_14;
                ** GOTO lbl45
            }
        }
        {
            catch (Throwable var1_8) {
                ** continue;
            }
        }
        catch (Throwable var2_11) {
            var4_1 = var1_4;
            var3_2 = null;
            var1_4 = var2_11;
            var2_9 = null;
            ** GOTO lbl31
        }
        {
            catch (Throwable var5_15) {
                var4_1 = var1_4;
                var3_2 = var2_9;
                var2_9 = null;
                var1_4 = var5_15;
                ** GOTO lbl31
            }
            catch (Throwable var6_18) {
                var4_1 = var1_4;
                var5_16 = var2_9;
                var2_9 = var3_2;
                var1_4 = var6_18;
                var3_2 = var5_16;
                ** GOTO lbl31
            }
        }
        catch (RejectedExecutionException var2_12) {
            var2_9 = null;
            ** GOTO lbl56
        }
    }
}

