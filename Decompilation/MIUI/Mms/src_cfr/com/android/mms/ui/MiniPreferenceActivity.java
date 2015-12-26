/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.AlertDialog
 *  android.app.AlertDialog$Builder
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  android.content.DialogInterface$OnDismissListener
 *  android.content.res.Resources
 *  android.os.Bundle
 *  java.lang.Object
 *  java.lang.String
 */
package com.android.mms.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import com.android.mms.ui.MessagingPreferenceActivity;

public class MiniPreferenceActivity
extends Activity {
    public static String DISABLE_NOTIFICATIONS_INTENT = "com.android.mms.intent.action.MESSAGING_APP_NOTIFICATIONS";
    private DialogInterface.OnClickListener mDialogButtonListener;

    public MiniPreferenceActivity() {
        this.mDialogButtonListener = new DialogInterface.OnClickListener(){

            /*
             * Enabled aggressive block sorting
             */
            public void onClick(DialogInterface dialogInterface, int n) {
                if (n == -1) {
                    MessagingPreferenceActivity.enableNotifications(false, (Context)MiniPreferenceActivity.this);
                    MiniPreferenceActivity.this.setResult(-1);
                } else {
                    MiniPreferenceActivity.this.setResult(0);
                }
                MiniPreferenceActivity.this.finish();
            }
        };
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (!MessagingPreferenceActivity.getNotificationEnabled((Context)this)) {
            this.setResult(-1);
            this.finish();
        }
        new AlertDialog.Builder((Context)this).setMessage((CharSequence)this.getResources().getString(2131362041)).setCancelable(true).setPositiveButton(2131361891, this.mDialogButtonListener).setNegativeButton(2131361892, this.mDialogButtonListener).show().setOnDismissListener(new DialogInterface.OnDismissListener(){

            public void onDismiss(DialogInterface dialogInterface) {
                if (!MiniPreferenceActivity.this.isFinishing()) {
                    MiniPreferenceActivity.this.finish();
                }
            }
        });
    }

}

