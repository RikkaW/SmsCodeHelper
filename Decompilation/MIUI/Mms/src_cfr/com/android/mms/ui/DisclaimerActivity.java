/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.ActivityManager
 *  android.app.AlertDialog
 *  android.app.AlertDialog$Builder
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  android.content.DialogInterface$OnDismissListener
 *  android.content.Intent
 *  android.os.Bundle
 *  android.provider.Settings
 *  android.provider.Settings$System
 *  android.text.SpannableStringBuilder
 *  android.text.TextPaint
 *  android.text.TextUtils
 *  android.text.method.LinkMovementMethod
 *  android.text.method.MovementMethod
 *  android.text.style.ClickableSpan
 *  android.view.View
 *  android.view.Window
 *  android.widget.TextView
 *  java.lang.Object
 *  java.lang.String
 */
package com.android.mms.ui;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class DisclaimerActivity
extends Activity
implements DialogInterface.OnClickListener {
    public String mStopPackageName;

    private void forceStopPackage() {
        if (!TextUtils.isEmpty((CharSequence)this.mStopPackageName)) {
            ((ActivityManager)this.getSystemService("activity")).forceStopPackage(this.mStopPackageName);
        }
    }

    public void onBackPressed() {
        this.setResult(0);
        this.forceStopPackage();
        this.finish();
        super.onBackPressed();
    }

    /*
     * Enabled aggressive block sorting
     */
    public void onClick(DialogInterface dialogInterface, int n) {
        if (n == -1) {
            Settings.System.putInt((ContentResolver)this.getContentResolver(), (String)"mobile_policy", (int)1);
            this.setResult(1);
            return;
        } else {
            if (n != -2) return;
            {
                this.forceStopPackage();
                this.setResult(0);
                return;
            }
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mStopPackageName = this.getIntent().getStringExtra("stop_package_name");
        String string2 = this.getString(2131362292);
        String string3 = this.getString(2131362293);
        String string4 = this.getString(2131362294);
        String string5 = this.getString(2131362295);
        String string6 = this.getString(2131362296);
        bundle = new SpannableStringBuilder();
        bundle.append((CharSequence)(string2 + string3 + string4 + string5 + string6));
        bundle.setSpan((Object)new IntentSpan(2), string2.length(), string2.length() + string3.length(), 33);
        bundle.setSpan((Object)new IntentSpan(1), string2.length() + string3.length() + string4.length(), string2.length() + string3.length() + string4.length() + string5.length(), 33);
        string2 = new AlertDialog.Builder((Context)this);
        string2.setPositiveButton(17039370, (DialogInterface.OnClickListener)this);
        string2.setNeutralButton(17039360, (DialogInterface.OnClickListener)this);
        string2.setMessage((CharSequence)bundle);
        bundle = string2.show();
        bundle.setOnDismissListener(new DialogInterface.OnDismissListener(){

            public void onDismiss(DialogInterface dialogInterface) {
                DisclaimerActivity.this.finish();
            }
        });
        ((TextView)bundle.getWindow().findViewById(16908299)).setMovementMethod(LinkMovementMethod.getInstance());
    }

    class IntentSpan
    extends ClickableSpan {
        private int mLicenseType;

        public IntentSpan(int n) {
            this.mLicenseType = n;
        }

        public void onClick(View view) {
            view = new Intent("android.intent.action.VIEW_LICENSE");
            view.putExtra("android.intent.extra.LICENSE_TYPE", this.mLicenseType);
            DisclaimerActivity.this.startActivity((Intent)view);
        }

        public void updateDrawState(TextPaint textPaint) {
            textPaint.setUnderlineText(true);
            textPaint.setColor(-16776961);
        }
    }

}

