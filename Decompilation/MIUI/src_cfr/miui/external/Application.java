/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Application
 *  android.content.Context
 *  android.content.res.Configuration
 *  android.util.Log
 *  java.lang.Class
 *  java.lang.ClassLoader
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.lang.reflect.Method
 *  java.util.HashMap
 */
package miui.external;

import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import miui.external.ApplicationDelegate;
import miui.external.SdkConstants;
import miui.external.b;
import miui.external.d;
import miui.external.e;
import miui.external.f;

public class Application
extends android.app.Application
implements SdkConstants {
    private boolean j;
    private boolean k;
    private ApplicationDelegate l;

    /*
     * Enabled aggressive block sorting
     */
    public Application() {
        if (!this.i() || !this.j()) {
            return;
        }
        this.j = true;
    }

    private void a(String string, int n) {
        Log.e((String)"miuisdk", (String)("MIUI SDK encounter errors, please contact miuisdk@xiaomi.com for support. phase: " + string + " code: " + n));
        e.a(SdkConstants.SdkError.GENERIC);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(Throwable throwable) {
        while (throwable != null && throwable.getCause() != null) {
            if (throwable instanceof InvocationTargetException) {
                throwable = throwable.getCause();
                continue;
            }
            if (!(throwable instanceof ExceptionInInitializerError)) break;
            throwable = throwable.getCause();
        }
        Log.e((String)"miuisdk", (String)"MIUI SDK encounter errors, please contact miuisdk@xiaomi.com for support.", (Throwable)throwable);
        e.a(SdkConstants.SdkError.GENERIC);
    }

    private boolean i() {
        try {
            if (!d.isMiuiSystem() && !b.load(d.getApkPath(null, "com.miui.core", "miui"), null, d.getLibPath(null, "com.miui.core"), Application.class.getClassLoader())) {
                e.a(SdkConstants.SdkError.NO_SDK);
                return false;
            }
        }
        catch (Throwable var1_1) {
            this.a(var1_1);
            return false;
        }
        return true;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private boolean j() {
        int n;
        try {
            HashMap hashMap = new HashMap();
            n = (Integer)f.o().getMethod("initialize", new Class[]{android.app.Application.class, Map.class}).invoke((Object)null, new Object[]{this, hashMap});
            if (n == 0) return true;
        }
        catch (Throwable var2_2) {
            this.a(var2_2);
            return false;
        }
        this.a("initialize", n);
        return false;
    }

    private boolean k() {
        int n;
        block5 : {
            try {
                HashMap hashMap = new HashMap();
                n = (Integer)f.o().getMethod("start", new Class[]{Map.class}).invoke((Object)null, new Object[]{hashMap});
                if (n != 1) break block5;
            }
            catch (Throwable var2_2) {
                this.a(var2_2);
                return false;
            }
            e.a(SdkConstants.SdkError.LOW_SDK_VERSION);
            return false;
        }
        if (n != 0) {
            this.a("start", n);
            return false;
        }
        return true;
    }

    final void a(int n) {
        super.onTrimMemory(n);
    }

    final void a(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        if (!this.j || !this.k()) {
            return;
        }
        this.l = this.onCreateApplicationDelegate();
        if (this.l != null) {
            this.l.a(this);
        }
        this.k = true;
    }

    final void l() {
        super.onCreate();
    }

    final void m() {
        super.onTerminate();
    }

    final void n() {
        super.onLowMemory();
    }

    public final void onConfigurationChanged(Configuration configuration) {
        if (this.l != null) {
            this.l.onConfigurationChanged(configuration);
            return;
        }
        this.a(configuration);
    }

    public final void onCreate() {
        if (!this.k) {
            return;
        }
        if (this.l != null) {
            this.l.onCreate();
            return;
        }
        this.l();
    }

    public ApplicationDelegate onCreateApplicationDelegate() {
        return null;
    }

    public final void onLowMemory() {
        if (this.l != null) {
            this.l.onLowMemory();
            return;
        }
        this.n();
    }

    public final void onTerminate() {
        if (this.l != null) {
            this.l.onTerminate();
            return;
        }
        this.m();
    }

    public final void onTrimMemory(int n) {
        if (this.l != null) {
            this.l.onTrimMemory(n);
            return;
        }
        this.a(n);
    }
}

