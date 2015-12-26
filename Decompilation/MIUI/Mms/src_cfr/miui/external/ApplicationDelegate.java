/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ComponentCallbacks
 *  android.content.ComponentCallbacks2
 *  android.content.Context
 *  android.content.ContextWrapper
 *  android.content.res.Configuration
 */
package miui.external;

import android.content.ComponentCallbacks;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import miui.external.Application;

public abstract class ApplicationDelegate
extends ContextWrapper
implements ComponentCallbacks2 {
    private Application o;

    public ApplicationDelegate() {
        super(null);
    }

    void a(Application application) {
        this.o = application;
        this.attachBaseContext((Context)application);
    }

    public Application getApplication() {
        return this.o;
    }

    public void onConfigurationChanged(Configuration configuration) {
        this.o.a(configuration);
    }

    public void onCreate() {
        this.o.l();
    }

    public void onLowMemory() {
        this.o.n();
    }

    public void onTerminate() {
        this.o.m();
    }

    public void onTrimMemory(int n) {
        this.o.a(n);
    }

    public void registerComponentCallbacks(ComponentCallbacks componentCallbacks) {
        this.o.registerComponentCallbacks(componentCallbacks);
    }

    public void unregisterComponentCallbacks(ComponentCallbacks componentCallbacks) {
        this.o.unregisterComponentCallbacks(componentCallbacks);
    }
}

