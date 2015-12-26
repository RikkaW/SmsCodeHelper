/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Bundle
 *  java.io.PrintWriter
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.app;

import android.os.Bundle;
import android.support.v4.app.LoaderManagerImpl;
import android.support.v4.content.Loader;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public abstract class LoaderManager {
    public static void enableDebugLogging(boolean bl2) {
        LoaderManagerImpl.DEBUG = bl2;
    }

    public abstract void destroyLoader(int var1);

    public abstract void dump(String var1, FileDescriptor var2, PrintWriter var3, String[] var4);

    public abstract <D> Loader<D> getLoader(int var1);

    public boolean hasRunningLoaders() {
        return false;
    }

    public abstract <D> Loader<D> initLoader(int var1, Bundle var2, LoaderCallbacks<D> var3);

    public abstract <D> Loader<D> restartLoader(int var1, Bundle var2, LoaderCallbacks<D> var3);

    public static interface LoaderCallbacks<D> {
        public Loader<D> onCreateLoader(int var1, Bundle var2);

        public void onLoadFinished(Loader<D> var1, D var2);

        public void onLoaderReset(Loader<D> var1);
    }

}

