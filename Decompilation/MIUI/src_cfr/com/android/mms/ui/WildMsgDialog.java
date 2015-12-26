/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Application
 *  android.content.ContentResolver
 *  android.content.ContentValues
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  android.content.DialogInterface$OnMultiChoiceClickListener
 *  android.database.Cursor
 *  android.net.Uri
 *  android.os.AsyncTask
 *  android.provider.Settings
 *  android.provider.Settings$System
 *  android.text.TextUtils
 *  android.util.Log
 *  android.widget.ListView
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Thread
 *  java.util.ArrayList
 *  miui.app.AlertDialog
 *  miui.app.AlertDialog$Builder
 *  miui.provider.ExtraTelephony
 *  miui.provider.ExtraTelephony$SimCards
 */
package com.android.mms.ui;

import android.app.Application;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ListView;
import com.android.mms.MmsApp;
import com.android.mms.ui.MessageUtils;
import java.util.ArrayList;
import miui.app.AlertDialog;
import miui.provider.ExtraTelephony;

public class WildMsgDialog {
    private boolean[] mCheckedItems;
    private Context mContext;
    private AlertDialog mDialog;
    private boolean mFromSettings;
    private String[] mItems;
    private DialogInterface.OnMultiChoiceClickListener mOnMultiChoiceClickListener;
    private DialogInterface.OnClickListener mOnNegativeClickListener;
    private DialogInterface.OnClickListener mOnPositiveClickListener;
    private AsyncTask<Void, Void, Void> mTask;

    public WildMsgDialog(Context context, boolean bl) {
        this.mOnMultiChoiceClickListener = new DialogInterface.OnMultiChoiceClickListener(){

            public void onClick(DialogInterface dialogInterface, int n, boolean bl) {
                if (WildMsgDialog.this.mCheckedItems != null && WildMsgDialog.this.mCheckedItems.length > n) {
                    WildMsgDialog.access$000((WildMsgDialog)WildMsgDialog.this)[n] = bl;
                }
            }
        };
        this.mOnPositiveClickListener = new DialogInterface.OnClickListener(){

            /*
             * Enabled aggressive block sorting
             */
            public void onClick(DialogInterface dialogInterface, int n) {
                n = Settings.System.getInt((ContentResolver)WildMsgDialog.this.mContext.getContentResolver(), (String)"mms_sync_wild_msg_state", (int)0);
                if (n != 4 && n != 5) {
                    Settings.System.putInt((ContentResolver)WildMsgDialog.this.mContext.getContentResolver(), (String)"mms_sync_wild_msg_state", (int)2);
                }
                int n2 = 0;
                n = 0;
                dialogInterface = new ArrayList();
                int n3 = n2;
                if (WildMsgDialog.this.mItems != null) {
                    n3 = n2;
                    if (WildMsgDialog.this.mCheckedItems != null) {
                        n2 = 0;
                        do {
                            n3 = n;
                            if (n2 >= WildMsgDialog.this.mCheckedItems.length) break;
                            if (WildMsgDialog.this.mCheckedItems[n2]) {
                                dialogInterface.add((Object)WildMsgDialog.this.mItems[n2]);
                            } else {
                                n = 1;
                            }
                            ++n2;
                        } while (true);
                    }
                }
                Log.v((String)"WildMsgDialog", (String)("update list.size is " + dialogInterface.size()));
                if (dialogInterface.size() > 0) {
                    new Thread(new Runnable("number IN (" + TextUtils.join((CharSequence)",", (Iterable)dialogInterface) + ")"){
                        final /* synthetic */ String val$selection;

                        @Override
                        public void run() {
                            Application application = MmsApp.getApp();
                            ContentValues contentValues = new ContentValues(1);
                            contentValues.put("download_status", Integer.valueOf((int)1));
                            int n = application.getContentResolver().update(ExtraTelephony.SimCards.CONTENT_URI, contentValues, this.val$selection, null);
                            Log.v((String)"WildMsgDialog", (String)("update number to sim cards is " + n));
                            if (n > 0) {
                                Log.v((String)"WildMsgDialog", (String)"force sync");
                                MessageUtils.forceSync((Context)application);
                            }
                        }
                    }, "update_sim_cards_thread").start();
                }
                if (n3 != 0 && !WildMsgDialog.this.mFromSettings) {
                    WildMsgDialog.this.showWildGuideSettingsDialog(WildMsgDialog.this.mContext);
                }
                WildMsgDialog.this.dismissDialog();
            }

        };
        this.mOnNegativeClickListener = new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialogInterface, int n) {
                n = Settings.System.getInt((ContentResolver)WildMsgDialog.this.mContext.getContentResolver(), (String)"mms_sync_wild_msg_state", (int)0);
                if (n != 4 && n != 5) {
                    Settings.System.putInt((ContentResolver)WildMsgDialog.this.mContext.getContentResolver(), (String)"mms_sync_wild_msg_state", (int)2);
                }
                if (!WildMsgDialog.this.mFromSettings) {
                    WildMsgDialog.this.showWildGuideSettingsDialog(WildMsgDialog.this.mContext);
                }
                WildMsgDialog.this.dismissDialog();
            }
        };
        this.mContext = context;
        this.mFromSettings = bl;
    }

    private void clear() {
        this.dismissDialog();
        this.mDialog = null;
        this.mItems = null;
        this.mCheckedItems = null;
    }

    private void dismissDialog() {
        if (this.mDialog != null && this.mDialog.isShowing()) {
            Log.v((String)"WildMsgDialog", (String)"dismiss wild message dialog");
            this.mDialog.dismiss();
        }
        this.mDialog = null;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void load() {
        int n;
        String string2;
        block7 : {
            this.mItems = null;
            this.mCheckedItems = null;
            string2 = this.mFromSettings ? null : "download_status=0";
            string2 = this.mContext.getContentResolver().query(ExtraTelephony.SimCards.CONTENT_URI, new String[]{"number"}, string2, null, null);
            if (string2 != null) {
                int n2 = string2.getCount();
                Log.v((String)"WildMsgDialog", (String)("loadNumberFromSimCards count is " + n2));
                if (n2 <= 0) break block7;
                this.mItems = new String[n2];
                string2.moveToPosition(-1);
                for (n = 0; string2.moveToNext() && n < n2; ++n) {
                    this.mItems[n] = string2.getString(0);
                }
            }
        }
        if (this.mItems == null) {
            return;
        }
        this.mCheckedItems = new boolean[this.mItems.length];
        n = 0;
        while (n < this.mCheckedItems.length) {
            this.mCheckedItems[n] = true;
            ++n;
        }
        return;
        finally {
            string2.close();
        }
    }

    private boolean shouldShowDialog() {
        if (this.mItems == null || this.mItems.length <= 0) {
            Log.v((String)"WildMsgDialog", (String)"shouldShowDialog mItems is empty");
            return false;
        }
        if (this.mCheckedItems == null || this.mCheckedItems.length <= 0) {
            Log.v((String)"WildMsgDialog", (String)"shouldShowDialog mCheckedItems is empty");
            return false;
        }
        Log.v((String)"WildMsgDialog", (String)"shouldShowDialog is true");
        return true;
    }

    private void showDialog() {
        this.dismissDialog();
        AlertDialog.Builder builder = new AlertDialog.Builder(this.mContext);
        builder.setTitle(2131362196);
        builder.setMultiChoiceItems(this.mItems, this.mCheckedItems, this.mOnMultiChoiceClickListener);
        builder.setCancelable(this.mFromSettings);
        builder.setPositiveButton(2131361891, this.mOnPositiveClickListener);
        builder.setNegativeButton(2131361892, this.mOnNegativeClickListener);
        this.mDialog = builder.show();
        builder = this.mDialog.getListView();
        for (int i = 0; i < this.mCheckedItems.length; ++i) {
            builder.setItemChecked(i, this.mCheckedItems[i]);
        }
        Log.v((String)"WildMsgDialog", (String)"show wild message dialog");
    }

    private void showGuideCloudDialog(Context context) {
        context = new AlertDialog.Builder(context);
        context.setMessage(2131362198);
        context.setCancelable(true);
        context.setPositiveButton(2131361891, null);
        context.show();
    }

    private void showWildGuideSettingsDialog(Context context) {
        context = new AlertDialog.Builder(context);
        context.setMessage(2131362197);
        context.setCancelable(true);
        context.setPositiveButton(2131361891, null);
        context.show();
    }

    public void cancel() {
        if (this.mTask != null) {
            this.mTask.cancel(true);
        }
        this.mTask = null;
        this.clear();
    }

    public void show() {
        this.cancel();
        this.mTask = new AsyncTask<Void, Void, Void>(){

            protected /* varargs */ Void doInBackground(Void ... arrvoid) {
                WildMsgDialog.this.load();
                return null;
            }

            protected void onCancelled() {
                WildMsgDialog.this.mTask = null;
                WildMsgDialog.this.clear();
            }

            /*
             * Enabled aggressive block sorting
             */
            protected void onPostExecute(Void void_) {
                WildMsgDialog.this.mTask = null;
                if (WildMsgDialog.this.shouldShowDialog()) {
                    WildMsgDialog.this.showDialog();
                    return;
                } else {
                    if (!WildMsgDialog.this.mFromSettings) return;
                    {
                        WildMsgDialog.this.showGuideCloudDialog(WildMsgDialog.this.mContext);
                        return;
                    }
                }
            }

            protected void onPreExecute() {
                WildMsgDialog.this.clear();
            }
        };
        this.mTask.execute((Object[])new Void[0]);
    }

}

