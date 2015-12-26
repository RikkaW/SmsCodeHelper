/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.Intent
 *  android.content.res.Configuration
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.Bundle
 *  android.os.Handler
 *  android.os.Parcelable
 *  android.util.AttributeSet
 *  android.util.Log
 *  android.view.KeyEvent
 *  android.view.LayoutInflater
 *  android.view.LayoutInflater$Factory
 *  android.view.Menu
 *  android.view.MenuInflater
 *  android.view.MenuItem
 *  android.view.View
 *  android.view.ViewGroup
 *  android.view.Window
 *  java.io.PrintWriter
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.util.ArrayList
 */
package android.support.v4.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityCompatHoneycomb;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity$1;
import android.support.v4.app.FragmentActivity$2;
import android.support.v4.app.FragmentContainer;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManagerImpl;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.LoaderManagerImpl;
import android.support.v4.app.SharedElementCallback;
import android.support.v4.util.SimpleArrayMap;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FragmentActivity
extends Activity {
    static final String FRAGMENTS_TAG = "android:support:fragments";
    private static final int HONEYCOMB = 11;
    static final int MSG_REALLY_STOPPED = 1;
    static final int MSG_RESUME_PENDING = 2;
    private static final String TAG = "FragmentActivity";
    SimpleArrayMap<String, LoaderManagerImpl> mAllLoaderManagers;
    boolean mCheckedForLoaderManager;
    final FragmentContainer mContainer;
    boolean mCreated;
    final FragmentManagerImpl mFragments;
    final Handler mHandler;
    LoaderManagerImpl mLoaderManager;
    boolean mLoadersStarted;
    boolean mOptionsMenuInvalidated;
    boolean mReallyStopped;
    boolean mResumed;
    boolean mRetaining;
    boolean mStopped;

    public FragmentActivity() {
        this.mHandler = new FragmentActivity$1(this);
        this.mFragments = new FragmentManagerImpl();
        this.mContainer = new FragmentActivity$2(this);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void dumpViewHierarchy(String string2, PrintWriter printWriter, View view) {
        printWriter.print(string2);
        if (view == null) {
            printWriter.println("null");
            return;
        } else {
            int n2;
            printWriter.println(FragmentActivity.viewToString(view));
            if (!(view instanceof ViewGroup) || (n2 = (view = (ViewGroup)view).getChildCount()) <= 0) return;
            {
                string2 = string2 + "  ";
                int n3 = 0;
                while (n3 < n2) {
                    this.dumpViewHierarchy(string2, printWriter, view.getChildAt(n3));
                    ++n3;
                }
                return;
            }
        }
    }

    /*
     * Exception decompiling
     */
    private static String viewToString(View var0) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [7[CASE]], but top level block is 0[TRYBLOCK]
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:394)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:446)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:2859)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:805)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:220)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:165)
        // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:91)
        // org.benf.cfr.reader.entities.Method.analyse(Method.java:354)
        // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:751)
        // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:683)
        // org.benf.cfr.reader.Main.doJar(Main.java:128)
        // org.benf.cfr.reader.Main.main(Main.java:178)
        throw new IllegalStateException("Decompilation failed");
    }

    void doReallyStop(boolean bl2) {
        if (!this.mReallyStopped) {
            this.mReallyStopped = true;
            this.mRetaining = bl2;
            this.mHandler.removeMessages(1);
            this.onReallyStop();
        }
    }

    public void dump(String string2, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] arrstring) {
        if (Build.VERSION.SDK_INT >= 11) {
            // empty if block
        }
        printWriter.print(string2);
        printWriter.print("Local FragmentActivity ");
        printWriter.print(Integer.toHexString((int)System.identityHashCode((Object)((Object)this))));
        printWriter.println(" State:");
        String string3 = string2 + "  ";
        printWriter.print(string3);
        printWriter.print("mCreated=");
        printWriter.print(this.mCreated);
        printWriter.print("mResumed=");
        printWriter.print(this.mResumed);
        printWriter.print(" mStopped=");
        printWriter.print(this.mStopped);
        printWriter.print(" mReallyStopped=");
        printWriter.println(this.mReallyStopped);
        printWriter.print(string3);
        printWriter.print("mLoadersStarted=");
        printWriter.println(this.mLoadersStarted);
        if (this.mLoaderManager != null) {
            printWriter.print(string2);
            printWriter.print("Loader Manager ");
            printWriter.print(Integer.toHexString((int)System.identityHashCode((Object)this.mLoaderManager)));
            printWriter.println(":");
            this.mLoaderManager.dump(string2 + "  ", fileDescriptor, printWriter, arrstring);
        }
        this.mFragments.dump(string2, fileDescriptor, printWriter, arrstring);
        printWriter.print(string2);
        printWriter.println("View Hierarchy:");
        this.dumpViewHierarchy(string2 + "  ", printWriter, this.getWindow().getDecorView());
    }

    public Object getLastCustomNonConfigurationInstance() {
        NonConfigurationInstances nonConfigurationInstances = (NonConfigurationInstances)this.getLastNonConfigurationInstance();
        if (nonConfigurationInstances != null) {
            return nonConfigurationInstances.custom;
        }
        return null;
    }

    LoaderManagerImpl getLoaderManager(String string2, boolean bl2, boolean bl3) {
        LoaderManagerImpl loaderManagerImpl;
        if (this.mAllLoaderManagers == null) {
            this.mAllLoaderManagers = new SimpleArrayMap();
        }
        if ((loaderManagerImpl = this.mAllLoaderManagers.get(string2)) == null) {
            if (bl3) {
                loaderManagerImpl = new LoaderManagerImpl(string2, this, bl2);
                this.mAllLoaderManagers.put(string2, loaderManagerImpl);
            }
            return loaderManagerImpl;
        }
        loaderManagerImpl.updateActivity(this);
        return loaderManagerImpl;
    }

    public FragmentManager getSupportFragmentManager() {
        return this.mFragments;
    }

    public LoaderManager getSupportLoaderManager() {
        if (this.mLoaderManager != null) {
            return this.mLoaderManager;
        }
        this.mCheckedForLoaderManager = true;
        this.mLoaderManager = this.getLoaderManager("(root)", this.mLoadersStarted, true);
        return this.mLoaderManager;
    }

    void invalidateSupportFragment(String string2) {
        LoaderManagerImpl loaderManagerImpl;
        if (this.mAllLoaderManagers != null && (loaderManagerImpl = this.mAllLoaderManagers.get(string2)) != null && !loaderManagerImpl.mRetaining) {
            loaderManagerImpl.doDestroy();
            this.mAllLoaderManagers.remove(string2);
        }
    }

    public void onActivityResult(int n2, int n3, Intent intent) {
        this.mFragments.noteStateNotSaved();
        int n4 = n2 >> 16;
        if (n4 != 0) {
            if (this.mFragments.mActive == null || n4 < 0 || --n4 >= this.mFragments.mActive.size()) {
                Log.w((String)"FragmentActivity", (String)("Activity result fragment index out of range: 0x" + Integer.toHexString((int)n2)));
                return;
            }
            Fragment fragment = (Fragment)this.mFragments.mActive.get(n4);
            if (fragment == null) {
                Log.w((String)"FragmentActivity", (String)("Activity result no fragment exists for index: 0x" + Integer.toHexString((int)n2)));
                return;
            }
            fragment.onActivityResult(65535 & n2, n3, intent);
            return;
        }
        super.onActivityResult(n2, n3, intent);
    }

    public void onAttachFragment(Fragment fragment) {
    }

    public void onBackPressed() {
        if (!this.mFragments.popBackStackImmediate()) {
            this.supportFinishAfterTransition();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mFragments.dispatchConfigurationChanged(configuration);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void onCreate(@Nullable Bundle arrayList) {
        this.mFragments.attachActivity(this, this.mContainer, null);
        if (this.getLayoutInflater().getFactory() == null) {
            this.getLayoutInflater().setFactory((LayoutInflater.Factory)this);
        }
        super.onCreate(arrayList);
        NonConfigurationInstances nonConfigurationInstances = (NonConfigurationInstances)this.getLastNonConfigurationInstance();
        if (nonConfigurationInstances != null) {
            this.mAllLoaderManagers = nonConfigurationInstances.loaders;
        }
        if (arrayList != null) {
            Parcelable parcelable = arrayList.getParcelable("android:support:fragments");
            FragmentManagerImpl fragmentManagerImpl = this.mFragments;
            arrayList = nonConfigurationInstances != null ? nonConfigurationInstances.fragments : null;
            fragmentManagerImpl.restoreAllState(parcelable, arrayList);
        }
        this.mFragments.dispatchCreate();
    }

    public boolean onCreatePanelMenu(int n2, Menu menu) {
        if (n2 == 0) {
            boolean bl2 = super.onCreatePanelMenu(n2, menu);
            boolean bl3 = this.mFragments.dispatchCreateOptionsMenu(menu, this.getMenuInflater());
            if (Build.VERSION.SDK_INT >= 11) {
                return bl2 | bl3;
            }
            return true;
        }
        return super.onCreatePanelMenu(n2, menu);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Nullable
    public View onCreateView(String string2, @NonNull Context context, @NonNull AttributeSet attributeSet) {
        View view;
        if (!"fragment".equals((Object)string2)) {
            return super.onCreateView(string2, context, attributeSet);
        }
        View view2 = view = this.mFragments.onCreateView(null, string2, context, attributeSet);
        if (view != null) return view2;
        return super.onCreateView(string2, context, attributeSet);
    }

    public void onDestroy() {
        super.onDestroy();
        this.doReallyStop(false);
        this.mFragments.dispatchDestroy();
        if (this.mLoaderManager != null) {
            this.mLoaderManager.doDestroy();
        }
    }

    public boolean onKeyDown(int n2, KeyEvent keyEvent) {
        if (Build.VERSION.SDK_INT < 5 && n2 == 4 && keyEvent.getRepeatCount() == 0) {
            this.onBackPressed();
            return true;
        }
        return super.onKeyDown(n2, keyEvent);
    }

    public void onLowMemory() {
        super.onLowMemory();
        this.mFragments.dispatchLowMemory();
    }

    public boolean onMenuItemSelected(int n2, MenuItem menuItem) {
        if (super.onMenuItemSelected(n2, menuItem)) {
            return true;
        }
        switch (n2) {
            default: {
                return false;
            }
            case 0: {
                return this.mFragments.dispatchOptionsItemSelected(menuItem);
            }
            case 6: 
        }
        return this.mFragments.dispatchContextItemSelected(menuItem);
    }

    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.mFragments.noteStateNotSaved();
    }

    /*
     * Enabled aggressive block sorting
     */
    public void onPanelClosed(int n2, Menu menu) {
        switch (n2) {
            default: {
                break;
            }
            case 0: {
                this.mFragments.dispatchOptionsMenuClosed(menu);
            }
        }
        super.onPanelClosed(n2, menu);
    }

    public void onPause() {
        super.onPause();
        this.mResumed = false;
        if (this.mHandler.hasMessages(2)) {
            this.mHandler.removeMessages(2);
            this.onResumeFragments();
        }
        this.mFragments.dispatchPause();
    }

    public void onPostResume() {
        super.onPostResume();
        this.mHandler.removeMessages(2);
        this.onResumeFragments();
        this.mFragments.execPendingActions();
    }

    protected boolean onPrepareOptionsPanel(View view, Menu menu) {
        return super.onPreparePanel(0, view, menu);
    }

    public boolean onPreparePanel(int n2, View view, Menu menu) {
        if (n2 == 0 && menu != null) {
            if (this.mOptionsMenuInvalidated) {
                this.mOptionsMenuInvalidated = false;
                menu.clear();
                this.onCreatePanelMenu(n2, menu);
            }
            return this.onPrepareOptionsPanel(view, menu) | this.mFragments.dispatchPrepareOptionsMenu(menu);
        }
        return super.onPreparePanel(n2, view, menu);
    }

    /*
     * Enabled aggressive block sorting
     */
    void onReallyStop() {
        if (this.mLoadersStarted) {
            this.mLoadersStarted = false;
            if (this.mLoaderManager != null) {
                if (!this.mRetaining) {
                    this.mLoaderManager.doStop();
                } else {
                    this.mLoaderManager.doRetain();
                }
            }
        }
        this.mFragments.dispatchReallyStop();
    }

    public void onResume() {
        super.onResume();
        this.mHandler.sendEmptyMessage(2);
        this.mResumed = true;
        this.mFragments.execPendingActions();
    }

    protected void onResumeFragments() {
        this.mFragments.dispatchResume();
    }

    public Object onRetainCustomNonConfigurationInstance() {
        return null;
    }

    /*
     * Enabled aggressive block sorting
     */
    public final Object onRetainNonConfigurationInstance() {
        Object object;
        int n2;
        int n3 = 0;
        if (this.mStopped) {
            this.doReallyStop(true);
        }
        Object object2 = this.onRetainCustomNonConfigurationInstance();
        ArrayList<Fragment> arrayList = this.mFragments.retainNonConfig();
        if (this.mAllLoaderManagers == null) {
            n2 = 0;
        } else {
            int n4;
            int n5 = this.mAllLoaderManagers.size();
            object = new LoaderManagerImpl[n5];
            for (n4 = n5 - 1; n4 >= 0; --n4) {
                object[n4] = this.mAllLoaderManagers.valueAt(n4);
            }
            n4 = 0;
            do {
                n2 = n4;
                if (n3 >= n5) break;
                Object object3 = object[n3];
                if (object3.mRetaining) {
                    n4 = 1;
                } else {
                    object3.doDestroy();
                    this.mAllLoaderManagers.remove(object3.mWho);
                }
                ++n3;
            } while (true);
        }
        if (arrayList == null && n2 == 0 && object2 == null) {
            return null;
        }
        object = new NonConfigurationInstances();
        object.activity = null;
        object.custom = object2;
        object.children = null;
        object.fragments = arrayList;
        object.loaders = this.mAllLoaderManagers;
        return object;
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        Parcelable parcelable = this.mFragments.saveAllState();
        if (parcelable != null) {
            bundle.putParcelable("android:support:fragments", parcelable);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void onStart() {
        int n2;
        super.onStart();
        this.mStopped = false;
        this.mReallyStopped = false;
        this.mHandler.removeMessages(1);
        if (!this.mCreated) {
            this.mCreated = true;
            this.mFragments.dispatchActivityCreated();
        }
        this.mFragments.noteStateNotSaved();
        this.mFragments.execPendingActions();
        if (!this.mLoadersStarted) {
            this.mLoadersStarted = true;
            if (this.mLoaderManager != null) {
                this.mLoaderManager.doStart();
            } else if (!this.mCheckedForLoaderManager) {
                this.mLoaderManager = this.getLoaderManager("(root)", this.mLoadersStarted, false);
                if (this.mLoaderManager != null && !this.mLoaderManager.mStarted) {
                    this.mLoaderManager.doStart();
                }
            }
            this.mCheckedForLoaderManager = true;
        }
        this.mFragments.dispatchStart();
        if (this.mAllLoaderManagers == null) {
            return;
        }
        int n3 = this.mAllLoaderManagers.size();
        LoaderManagerImpl[] arrloaderManagerImpl = new LoaderManagerImpl[n3];
        for (n2 = n3 - 1; n2 >= 0; --n2) {
            arrloaderManagerImpl[n2] = this.mAllLoaderManagers.valueAt(n2);
        }
        n2 = 0;
        while (n2 < n3) {
            LoaderManagerImpl loaderManagerImpl = arrloaderManagerImpl[n2];
            loaderManagerImpl.finishRetain();
            loaderManagerImpl.doReportStart();
            ++n2;
        }
    }

    public void onStop() {
        super.onStop();
        this.mStopped = true;
        this.mHandler.sendEmptyMessage(1);
        this.mFragments.dispatchStop();
    }

    public void setEnterSharedElementCallback(SharedElementCallback sharedElementCallback) {
        ActivityCompat.setEnterSharedElementCallback(this, sharedElementCallback);
    }

    public void setExitSharedElementCallback(SharedElementCallback sharedElementCallback) {
        ActivityCompat.setExitSharedElementCallback(this, sharedElementCallback);
    }

    public void startActivityForResult(Intent intent, int n2) {
        if (n2 != -1 && (-65536 & n2) != 0) {
            throw new IllegalArgumentException("Can only use lower 16 bits for requestCode");
        }
        super.startActivityForResult(intent, n2);
    }

    public void startActivityFromFragment(Fragment fragment, Intent intent, int n2) {
        if (n2 == -1) {
            super.startActivityForResult(intent, -1);
            return;
        }
        if ((-65536 & n2) != 0) {
            throw new IllegalArgumentException("Can only use lower 16 bits for requestCode");
        }
        super.startActivityForResult(intent, (fragment.mIndex + 1 << 16) + (65535 & n2));
    }

    public void supportFinishAfterTransition() {
        ActivityCompat.finishAfterTransition(this);
    }

    public void supportInvalidateOptionsMenu() {
        if (Build.VERSION.SDK_INT >= 11) {
            ActivityCompatHoneycomb.invalidateOptionsMenu(this);
            return;
        }
        this.mOptionsMenuInvalidated = true;
    }

    public void supportPostponeEnterTransition() {
        ActivityCompat.postponeEnterTransition(this);
    }

    public void supportStartPostponedEnterTransition() {
        ActivityCompat.startPostponedEnterTransition(this);
    }

    static final class NonConfigurationInstances {
        Object activity;
        SimpleArrayMap<String, Object> children;
        Object custom;
        ArrayList<Fragment> fragments;
        SimpleArrayMap<String, LoaderManagerImpl> loaders;

        NonConfigurationInstances() {
        }
    }

}

