/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  android.content.Intent
 *  android.os.Bundle
 *  android.view.KeyEvent
 *  android.view.Window
 *  com.android.internal.app.AlertController
 *  com.android.internal.app.AlertController$AlertParams
 *  java.lang.Class
 *  java.lang.String
 *  miui.app.Activity
 */
package com.android.mms.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import com.android.internal.app.AlertController;
import com.android.mms.ui.MessagingPreferenceActivity;
import miui.app.Activity;

public class WarnOfStorageLimitsActivity
extends Activity
implements DialogInterface,
DialogInterface.OnClickListener {
    protected AlertController mAlert;
    protected AlertController.AlertParams mAlertParams;

    public void cancel() {
        this.finish();
    }

    public void dismiss() {
        if (!this.isFinishing()) {
            this.finish();
        }
    }

    public void onClick(DialogInterface dialogInterface, int n) {
        if (n == -1) {
            this.startActivity(new Intent((Context)this, (Class)MessagingPreferenceActivity.class));
        }
        dialogInterface.dismiss();
        this.finish();
    }

    protected void onCreate(Bundle bundle) {
        this.setTheme(16973939);
        super.onCreate(bundle);
        this.mAlert = new AlertController((Context)this, (DialogInterface)this, this.getWindow());
        bundle = this.mAlertParams = new AlertController.AlertParams((Context)this);
        bundle.mMessage = this.getString(2131362033);
        bundle.mPositiveButtonText = this.getString(2131362034);
        bundle.mNegativeButtonText = this.getString(2131362035);
        bundle.mPositiveButtonListener = this;
        this.setupAlert();
    }

    public boolean onKeyDown(int n, KeyEvent keyEvent) {
        if (this.mAlert.onKeyDown(n, keyEvent)) {
            return true;
        }
        return super.onKeyDown(n, keyEvent);
    }

    public boolean onKeyUp(int n, KeyEvent keyEvent) {
        if (this.mAlert.onKeyUp(n, keyEvent)) {
            return true;
        }
        return super.onKeyUp(n, keyEvent);
    }

    protected void setupAlert() {
        this.mAlertParams.apply(this.mAlert);
        this.mAlert.installContent();
    }
}

