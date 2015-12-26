/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Handler
 *  android.util.Log
 *  android.view.View
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.reflect.Method
 */
package android.support.v7.internal.widget;

import android.os.Handler;
import android.support.v7.internal.widget.ViewUtils;
import android.support.v7.internal.widget.ViewUtils$1$1;
import android.util.Log;
import android.view.View;
import java.lang.reflect.Method;

final class ViewUtils$1
implements Runnable {
    final /* synthetic */ Handler val$handler;
    final /* synthetic */ View val$view;

    ViewUtils$1(View view, Handler handler) {
        this.val$view = view;
        this.val$handler = handler;
    }

    @Override
    public void run() {
        try {
            Method method = this.val$view.getClass().getMethod("makeOptionalFitsSystemWindows", new Class[0]);
            if (!method.isAccessible()) {
                method.setAccessible(true);
            }
            ViewUtils.access$102(method);
            this.val$handler.postAtFrontOfQueue((Runnable)new ViewUtils$1$1(this));
            return;
        }
        catch (NoSuchMethodException var1_2) {
            Log.d((String)"ViewUtils", (String)"Could not find method makeOptionalFitsSystemWindows. Oh well...");
            return;
        }
    }
}

