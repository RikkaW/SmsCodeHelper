/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.AlertDialog
 *  android.app.AlertDialog$Builder
 *  android.app.Application
 *  android.app.Dialog
 *  android.app.Fragment
 *  android.content.BroadcastReceiver
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  android.content.Intent
 *  android.content.IntentFilter
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.graphics.Bitmap
 *  android.graphics.Bitmap$Config
 *  android.graphics.Canvas
 *  android.graphics.Paint
 *  android.graphics.PorterDuff
 *  android.graphics.PorterDuff$Mode
 *  android.graphics.PorterDuffXfermode
 *  android.graphics.RectF
 *  android.graphics.Xfermode
 *  android.net.ConnectivityManager
 *  android.net.NetworkInfo
 *  android.os.AsyncTask
 *  android.os.Bundle
 *  android.preference.PreferenceManager
 *  android.util.Log
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.CheckBox
 *  android.widget.LinearLayout
 *  android.widget.Toast
 *  java.lang.Boolean
 *  java.lang.Class
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.util.ArrayList
 */
package com.android.mms.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.app.Dialog;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.android.mms.LogTag;
import com.android.mms.MmsApp;
import com.android.mms.data.FestivalDatabase;
import com.android.mms.ui.FestivalSmsList;
import com.android.mms.ui.PickerActivity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Executor;

public class FestivalFragment
extends Fragment {
    private static UpdateTask sUpdateTask = null;
    private Activity mActivity;
    private View mDownloadingView;
    private boolean mIsForeground = false;
    private LinearLayout mList;
    private AsyncTask<Void, Void, ArrayList<LinearLayout>> mLoadTask;
    private boolean mNeedReload = true;
    private boolean mPickerMode;
    private SharedPreferences mPref;
    private View mRootView;
    private Dialog mShowNetworkingDlg;
    private BroadcastReceiver mUpdateCompleteReceiver;

    private void checkAndShowNetworkingDialog() {
        View view = View.inflate((Context)this.mActivity, (int)2130968609, (ViewGroup)null);
        final CheckBox checkBox = (CheckBox)view.findViewById(2131820639);
        checkBox.setChecked(true);
        this.mShowNetworkingDlg = new AlertDialog.Builder((Context)this.mActivity).setTitle(2131362335).setPositiveButton((CharSequence)this.getString(2131362337), new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialogInterface, int n) {
                if (checkBox.isChecked()) {
                    FestivalFragment.setAllowNetworkingDialog((Context)FestivalFragment.this.mActivity, false);
                }
                FestivalFragment.setAllowNetworking((Context)FestivalFragment.this.mActivity, true);
                FestivalFragment.this.checkForUpdate(false);
            }
        }).setNegativeButton(17039360, new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialogInterface, int n) {
                if (checkBox.isChecked()) {
                    FestivalFragment.setAllowNetworkingDialog((Context)FestivalFragment.this.mActivity, false);
                }
                FestivalFragment.setAllowNetworking((Context)FestivalFragment.this.mActivity, false);
            }
        }).setView(view).setCancelable(false).show();
    }

    public static boolean isAllowNetworking(Context context) {
        return PreferenceManager.getDefaultSharedPreferences((Context)context).getBoolean("pref_key_festival_allow_networking", false);
    }

    public static boolean isAllowNetworkingDialog(Context context) {
        return PreferenceManager.getDefaultSharedPreferences((Context)context).getBoolean("pref_key_festival_networking", true);
    }

    private boolean loadCategories() {
        if (!this.mNeedReload) {
            return false;
        }
        if (this.mLoadTask != null) {
            LogTag.verbose("Previous load is running. Stop it.", new Object[0]);
            this.mLoadTask.cancel(true);
            this.mLoadTask = null;
        }
        if (this.mShowNetworkingDlg != null) {
            LogTag.verbose("Previous dialog is show. Dismiss it", new Object[0]);
            this.mShowNetworkingDlg.dismiss();
            this.mShowNetworkingDlg = null;
        }
        this.mLoadTask = new AsyncTask<Void, Void, ArrayList<LinearLayout>>(){

            /*
             * Exception decompiling
             */
            protected /* varargs */ ArrayList<LinearLayout> doInBackground(Void ... var1_1) {
                // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
                // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 4[WHILELOOP]
                // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:394)
                // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:446)
                // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:2859)
                // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:805)
                // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:220)
                // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:165)
                // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:91)
                // org.benf.cfr.reader.entities.Method.analyse(Method.java:354)
                // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:751)
                // org.benf.cfr.reader.entities.ClassFile.analyseInnerClassesPass1(ClassFile.java:664)
                // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:747)
                // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:683)
                // org.benf.cfr.reader.Main.doJar(Main.java:128)
                // org.benf.cfr.reader.Main.main(Main.java:178)
                throw new IllegalStateException("Decompilation failed");
            }

            /*
             * Enabled aggressive block sorting
             */
            protected void onPostExecute(ArrayList<LinearLayout> object) {
                if (object != null) {
                    FestivalFragment.this.mNeedReload = false;
                    FestivalFragment.this.mList.removeAllViews();
                    object = object.iterator();
                    while (object.hasNext()) {
                        LinearLayout linearLayout = (LinearLayout)object.next();
                        FestivalFragment.this.mList.addView((View)linearLayout);
                    }
                }
                FestivalFragment.this.mLoadTask = null;
                if (FestivalFragment.isAllowNetworkingDialog((Context)FestivalFragment.this.mActivity)) {
                    FestivalFragment.this.checkAndShowNetworkingDialog();
                    return;
                } else {
                    if (!FestivalFragment.isAllowNetworking((Context)FestivalFragment.this.mActivity)) return;
                    {
                        FestivalFragment.this.checkForUpdate(false);
                        return;
                    }
                }
            }

        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Void[0]);
        return true;
    }

    public static Bitmap makeRoundImage(Bitmap bitmap, int n, int n2) {
        if (bitmap == null) {
            return null;
        }
        int n3 = bitmap.getWidth();
        int n4 = bitmap.getHeight();
        Bitmap bitmap2 = Bitmap.createBitmap((int)n3, (int)n4, (Bitmap.Config)Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap2);
        Paint paint = new Paint(1);
        int n5 = Math.min((int)n3, (int)n4) / 3;
        n = Math.min((int)n, (int)n5);
        n2 = Math.min((int)n2, (int)n5);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-12434878);
        canvas.drawRoundRect(new RectF(0.0f, 0.0f, (float)n3, (float)n4), (float)n, (float)n2, paint);
        paint.setXfermode((Xfermode)new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        return bitmap2;
    }

    private void onInvisible() {
        if (this.mLoadTask != null) {
            this.mLoadTask.cancel(true);
            this.mLoadTask = null;
        }
        if (this.mShowNetworkingDlg != null) {
            this.mShowNetworkingDlg.dismiss();
            this.mShowNetworkingDlg = null;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void onVisible() {
        View view = this.mDownloadingView;
        int n = sUpdateTask == null ? 8 : 0;
        view.setVisibility(n);
        if (!this.loadCategories() && FestivalFragment.isAllowNetworkingDialog((Context)this.mActivity)) {
            this.checkAndShowNetworkingDialog();
        }
    }

    public static void setAllowNetworking(Context context, boolean bl) {
        context = PreferenceManager.getDefaultSharedPreferences((Context)context).edit();
        context.putBoolean("pref_key_festival_allow_networking", bl);
        context.commit();
    }

    public static void setAllowNetworkingDialog(Context context, boolean bl) {
        context = PreferenceManager.getDefaultSharedPreferences((Context)context).edit();
        context.putBoolean("pref_key_festival_networking", bl);
        context.commit();
    }

    public void checkForUpdate(boolean bl) {
        if (!bl) {
            long l = this.mPref.getLong("update_timestamp", 0);
            if (System.currentTimeMillis() - l < 86400000) {
                return;
            }
        }
        if (sUpdateTask != null) {
            Log.v((String)"FestivalFragment", (String)"Updating is under way, do not initiate another one.");
            return;
        }
        NetworkInfo networkInfo = ((ConnectivityManager)this.mActivity.getSystemService("connectivity")).getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isConnected()) {
            if (bl) {
                Toast.makeText((Context)this.mActivity, (int)2131362060, (int)0).show();
            }
            Log.w((String)"FestivalFragment", (String)"No network. Don't check for update");
            return;
        }
        sUpdateTask = new UpdateTask();
        sUpdateTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Void[0]);
        this.mDownloadingView.setVisibility(0);
    }

    public void onActivityResult(int n, int n2, Intent intent) {
        switch (n) {
            default: {
                throw new IllegalArgumentException("Invalid requestCode " + n);
            }
            case 1000: 
        }
        if ((n2 == -1 || intent != null) && this.mPickerMode) {
            ((PickerActivity)this.mActivity).returnPickerResult(intent);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mRootView = layoutInflater.inflate(2130968610, null);
        this.mActivity = this.getActivity();
        if (this.mActivity instanceof PickerActivity) {
            this.mPickerMode = true;
        }
        this.mList = (LinearLayout)this.mRootView.findViewById(16908298);
        this.mDownloadingView = this.mRootView.findViewById(2131820642);
        this.mPref = PreferenceManager.getDefaultSharedPreferences((Context)this.mActivity);
        this.mUpdateCompleteReceiver = new BroadcastReceiver(){

            /*
             * Enabled aggressive block sorting
             */
            public void onReceive(Context context, Intent intent) {
                boolean bl = intent.getBooleanExtra("success", false);
                LogTag.verbose("Received broadcast of finishing update. Success=%s", bl);
                FestivalFragment.this.mNeedReload = true;
                if (FestivalFragment.this.getUserVisibleHint() && FestivalFragment.this.mIsForeground) {
                    LogTag.verbose("Activity is visible. Presenting result.", new Object[0]);
                    if (!bl) {
                        Toast.makeText((Context)FestivalFragment.this.mActivity, (int)2131362060, (int)0).show();
                    } else {
                        FestivalFragment.this.loadCategories();
                    }
                    FestivalFragment.this.mDownloadingView.setVisibility(8);
                }
            }
        };
        layoutInflater = new IntentFilter("com.android.mms.UPDATE_COMPLETE");
        this.mActivity.registerReceiver(this.mUpdateCompleteReceiver, (IntentFilter)layoutInflater);
        this.setHasOptionsMenu(true);
        return this.mRootView;
    }

    public void onDestroyView() {
        if (this.mUpdateCompleteReceiver != null) {
            this.mActivity.unregisterReceiver(this.mUpdateCompleteReceiver);
            this.mUpdateCompleteReceiver = null;
        }
        super.onDestroyView();
    }

    public void onPause() {
        this.mIsForeground = false;
        if (this.getUserVisibleHint()) {
            this.onInvisible();
        }
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        this.mIsForeground = true;
        if (this.getUserVisibleHint()) {
            this.onVisible();
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setUserVisibleHint(boolean bl) {
        if (bl) {
            if (this.mIsForeground) {
                this.onVisible();
            }
        } else if (this.mIsForeground) {
            this.onInvisible();
        }
        super.setUserVisibleHint(bl);
    }

    private static final class UpdateTask
    extends AsyncTask<Void, Void, Boolean> {
        private UpdateTask() {
        }

        protected /* varargs */ Boolean doInBackground(Void ... arrvoid) {
            LogTag.verbose("Start updating messages", new Object[0]);
            return FestivalDatabase.getInstance().updateMessages();
        }

        protected void onPostExecute(Boolean bl) {
            SharedPreferences.Editor editor;
            LogTag.verbose("Done updating messages", new Object[0]);
            Application application = MmsApp.getApp();
            if (bl.booleanValue()) {
                LogTag.verbose("Succeeded updating messages", new Object[0]);
                editor = PreferenceManager.getDefaultSharedPreferences((Context)application).edit();
                editor.putLong("update_timestamp", System.currentTimeMillis());
                editor.commit();
            }
            sUpdateTask = null;
            editor = new Intent("com.android.mms.UPDATE_COMPLETE");
            editor.putExtra("success", (Serializable)bl);
            application.sendBroadcast((Intent)editor);
        }
    }

}

