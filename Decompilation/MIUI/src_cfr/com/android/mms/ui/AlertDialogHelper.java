/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.annotation.SuppressLint
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  android.content.Intent
 *  android.content.res.Resources
 *  android.text.SpannableStringBuilder
 *  android.text.TextPaint
 *  android.text.method.LinkMovementMethod
 *  android.text.method.MovementMethod
 *  android.text.style.ClickableSpan
 *  android.view.View
 *  android.view.Window
 *  android.widget.TextView
 *  java.lang.Object
 *  java.lang.String
 *  miui.R
 *  miui.R$id
 *  miui.app.AlertDialog
 *  miui.app.AlertDialog$Builder
 */
package com.android.mms.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import miui.R;
import miui.app.AlertDialog;

public class AlertDialogHelper {
    private static Intent getPrivacyIntent() {
        Intent intent = new Intent("android.intent.action.VIEW_LICENSE");
        intent.putExtra("android.intent.extra.LICENSE_TYPE", 1);
        return intent;
    }

    private static Intent getUserAgreementIntent() {
        Intent intent = new Intent("android.intent.action.VIEW_LICENSE");
        intent.putExtra("android.intent.extra.LICENSE_TYPE", 2);
        return intent;
    }

    /*
     * Enabled aggressive block sorting
     */
    @SuppressLint(value={"InflateParams"})
    public static AlertDialog showUserNoticeDialog(final Context context, int n, int n2, int n3, int n4, DialogInterface.OnClickListener onClickListener, DialogInterface.OnClickListener onClickListener2) {
        if (n2 < 0) {
            return null;
        }
        Object object = context.getResources();
        String string = object.getString(2131362293);
        String string2 = object.getString(2131362295);
        object = object.getString(n2, new Object[]{string, string2});
        UrlSpan.UrlSpanOnClickListener urlSpanOnClickListener = new UrlSpan.UrlSpanOnClickListener(){

            @Override
            public void onClick() {
                context.startActivity(AlertDialogHelper.getUserAgreementIntent());
            }
        };
        UrlSpan.UrlSpanOnClickListener urlSpanOnClickListener2 = new UrlSpan.UrlSpanOnClickListener(){

            @Override
            public void onClick() {
                context.startActivity(AlertDialogHelper.getPrivacyIntent());
            }
        };
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder((CharSequence)object);
        n2 = object.indexOf(string);
        spannableStringBuilder.setSpan((Object)new UrlSpan(urlSpanOnClickListener), n2, string.length() + n2, 33);
        n2 = object.indexOf(string2);
        spannableStringBuilder.setSpan((Object)new UrlSpan(urlSpanOnClickListener2), n2, string2.length() + n2, 33);
        n2 = object.indexOf(string2);
        spannableStringBuilder.setSpan((Object)new UrlSpan(urlSpanOnClickListener2), n2, string2.length() + n2, 33);
        n2 = n3 == 0 ? 17039370 : n3;
        n3 = n4 == 0 ? 17039360 : n4;
        context = new AlertDialog.Builder(context).setMessage((CharSequence)spannableStringBuilder).setCancelable(false).setNegativeButton(n3, onClickListener2).setPositiveButton(n2, onClickListener);
        if (n > 0) {
            context.setTitle(n);
        }
        context = context.show();
        ((TextView)context.getWindow().findViewById(R.id.message)).setMovementMethod(LinkMovementMethod.getInstance());
        return context;
    }

    public static class UrlSpan
    extends ClickableSpan {
        private UrlSpanOnClickListener mOnClickListener;

        public UrlSpan(UrlSpanOnClickListener urlSpanOnClickListener) {
            this.mOnClickListener = urlSpanOnClickListener;
        }

        public void onClick(View view) {
            if (this.mOnClickListener != null) {
                this.mOnClickListener.onClick();
            }
        }

        public void updateDrawState(TextPaint textPaint) {
            textPaint.setUnderlineText(true);
            textPaint.setColor(-16776961);
        }

        public static interface UrlSpanOnClickListener {
            public void onClick();
        }

    }

}

