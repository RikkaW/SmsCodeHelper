/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.annotation.SuppressLint
 *  android.view.View
 *  android.view.ViewTreeObserver
 *  android.view.ViewTreeObserver$OnGlobalLayoutListener
 *  java.lang.Object
 */
package cn.com.xy.sms.sdk.ui.popu.util;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewTreeObserver;
import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.log.LogManager;

final class ViewManger$1
implements ViewTreeObserver.OnGlobalLayoutListener {
    final /* synthetic */ XyCallBack val$callBack;
    final /* synthetic */ View val$view;

    ViewManger$1(View view, XyCallBack xyCallBack) {
        this.val$view = view;
        this.val$callBack = xyCallBack;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @SuppressLint(value={"NewApi"})
    public void onGlobalLayout() {
        block8 : {
            try {
                this.val$view.getViewTreeObserver().removeOnGlobalLayoutListener((ViewTreeObserver.OnGlobalLayoutListener)this);
            }
            catch (NoSuchMethodError var1_1) {
                try {
                    this.val$view.getViewTreeObserver().removeGlobalOnLayoutListener((ViewTreeObserver.OnGlobalLayoutListener)this);
                }
                catch (NoSuchMethodError var1_2) {
                    if (LogManager.debug) {
                        var1_2.printStackTrace();
                    }
                }
                catch (Exception var1_3) {
                    if (LogManager.debug) {
                        var1_3.printStackTrace();
                    }
                }
            }
            catch (Exception var1_4) {
                if (!LogManager.debug) break block8;
                var1_4.printStackTrace();
            }
        }
        this.val$callBack.execute(new Object[0]);
    }
}

