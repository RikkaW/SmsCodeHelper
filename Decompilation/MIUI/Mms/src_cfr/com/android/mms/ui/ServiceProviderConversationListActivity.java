/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.AlertDialog
 *  android.app.AlertDialog$Builder
 *  android.app.Dialog
 *  android.app.DialogFragment
 *  android.app.Fragment
 *  android.app.FragmentManager
 *  android.app.FragmentTransaction
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  android.os.AsyncTask
 *  android.os.Bundle
 *  android.view.KeyEvent
 *  android.view.View
 *  android.view.View$OnClickListener
 *  java.lang.Object
 *  java.lang.String
 *  miui.app.ActionBar
 *  miui.app.Activity
 */
package com.android.mms.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import com.android.mms.MmsApp;
import com.android.mms.data.Conversation;
import com.android.mms.ui.MessageUtils;
import com.android.mms.ui.ProgressDialogFragment;
import com.android.mms.ui.ServiceProviderConversationFragment;
import miui.app.ActionBar;
import miui.app.Activity;

public class ServiceProviderConversationListActivity
extends Activity {
    private static DialogFragment sMarkAsReadDialog;
    private static AsyncTask<Void, Void, Void> sMarkAsReadTask;
    private ServiceProviderConversationFragment mFragment;

    private static void dismissMarkAsReadProgress() {
        if (sMarkAsReadDialog != null) {
            sMarkAsReadDialog.dismissAllowingStateLoss();
        }
        sMarkAsReadDialog = null;
    }

    private static void showMarkAsReadProgress(Context context, FragmentManager fragmentManager) {
        if (sMarkAsReadDialog == null && (ServiceProviderConversationListActivity.sMarkAsReadDialog = (DialogFragment)fragmentManager.findFragmentByTag("markAsReadProgress")) == null) {
            sMarkAsReadDialog = new ProgressDialogFragment(context.getString(2131362278));
        }
        sMarkAsReadDialog.setCancelable(false);
        sMarkAsReadDialog.show(fragmentManager, "markAsReadProgress");
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2130968692);
        bundle = this.getFragmentManager();
        FragmentTransaction fragmentTransaction = bundle.beginTransaction();
        this.mFragment = (ServiceProviderConversationFragment)bundle.findFragmentByTag("ServiceProviderConversationFragment");
        if (this.mFragment == null) {
            this.mFragment = new ServiceProviderConversationFragment();
            fragmentTransaction.add(2131820849, (Fragment)this.mFragment, "ServiceProviderConversationFragment");
        }
        ActionBar actionBar = this.getActionBar();
        actionBar.setTitle(2131362267);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setCustomView(2130968630);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.getCustomView().findViewById(2131820672).setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                if (sMarkAsReadTask == null && ServiceProviderConversationListActivity.this.mFragment != null) {
                    new ConfirmMarkAsReadDialogFragment(ServiceProviderConversationListActivity.access$100((ServiceProviderConversationListActivity)ServiceProviderConversationListActivity.this).mSelectedCategory).show(ServiceProviderConversationListActivity.this.getFragmentManager(), "markAsReadDialog");
                }
            }
        });
        fragmentTransaction.commitAllowingStateLoss();
        bundle.executePendingTransactions();
        if (sMarkAsReadTask != null) {
            ServiceProviderConversationListActivity.showMarkAsReadProgress((Context)this, (FragmentManager)bundle);
        }
    }

    public boolean onKeyDown(int n, KeyEvent keyEvent) {
        switch (n) {
            default: {
                return super.onKeyDown(n, keyEvent);
            }
            case 82: 
        }
        MessageUtils.launchMessagePreference((Context)this);
        return true;
    }

    public static class ConfirmMarkAsReadDialogFragment
    extends DialogFragment {
        private int mSelectedCategory = 1;

        public ConfirmMarkAsReadDialogFragment() {
        }

        public ConfirmMarkAsReadDialogFragment(int n) {
            this.mSelectedCategory = n;
        }

        public Dialog onCreateDialog(Bundle bundle) {
            bundle = new AlertDialog.Builder((Context)this.getActivity());
            bundle.setTitle(2131362276).setIconAttribute(16843605).setPositiveButton(17039370, new DialogInterface.OnClickListener(){

                public void onClick(DialogInterface dialogInterface, int n) {
                    sMarkAsReadTask = (AsyncTask)new AsyncTask<Void, Void, Void>(){

                        protected /* varargs */ Void doInBackground(Void ... arrvoid) {
                            Conversation.markSPAsReadSync((Context)MmsApp.getApp(), ConfirmMarkAsReadDialogFragment.this.mSelectedCategory);
                            return null;
                        }

                        protected void onPostExecute(Void void_) {
                            ServiceProviderConversationListActivity.dismissMarkAsReadProgress();
                            sMarkAsReadTask = null;
                        }

                        protected void onPreExecute() {
                            ServiceProviderConversationListActivity.showMarkAsReadProgress((Context)ConfirmMarkAsReadDialogFragment.this.getActivity(), ConfirmMarkAsReadDialogFragment.this.getFragmentManager());
                        }
                    };
                    sMarkAsReadTask.execute((Object[])new Void[]{null});
                }

            }).setNegativeButton(17039360, null);
            return bundle.create();
        }

    }

}

