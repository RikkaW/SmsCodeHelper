/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ActivityNotFoundException
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnCancelListener
 *  android.content.DialogInterface$OnClickListener
 *  android.content.Intent
 *  android.content.res.Resources
 *  android.text.SpannableStringBuilder
 *  android.text.TextPaint
 *  android.text.method.LinkMovementMethod
 *  android.text.method.MovementMethod
 *  android.text.style.ClickableSpan
 *  android.view.View
 *  android.widget.TextView
 *  com.xiaomi.accountsdk.activate.ActivateManager
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  miui.app.AlertDialog
 *  miui.app.AlertDialog$Builder
 */
package com.xiaomi.mms.utils;

import android.content.ActivityNotFoundException;
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
import android.widget.TextView;
import com.android.mms.util.MSimUtils;
import com.xiaomi.accountsdk.activate.ActivateManager;
import com.xiaomi.mms.transaction.MxActivateService;
import com.xiaomi.mms.utils.logger.MyLog;
import miui.app.AlertDialog;

public class MxActivateSimpleDialog {
    private ActivationCallBack mActivationCallBack;
    private AlertDialog mAlertDialog;
    private Context mContext;
    private DialogInterface.OnCancelListener mDialogCancelListener;
    private boolean mHasVoip;
    private CharSequence mMessage;
    private DialogInterface.OnClickListener mMxAndVoipEnableOnClickListener;
    private DialogInterface.OnClickListener mMxEnableOnClickListener;
    private DialogInterface.OnClickListener mNegativeButtonCancelListener;
    private DialogInterface.OnClickListener mPhoneActivateOnClickListener;
    private int mSimIndex;
    private String mSource;
    private String mTitle;
    private int mType;
    private DialogInterface.OnClickListener mVoipEnableOnClickListener;

    public MxActivateSimpleDialog(Context context, int n, String string2, int n2) {
        this(context, n, string2, n2, false);
    }

    public MxActivateSimpleDialog(Context context, int n, String string2, int n2, boolean bl) {
        this.mMxEnableOnClickListener = new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialogInterface, int n) {
                MxActivateService.enableAll(MxActivateSimpleDialog.this.mContext, true);
            }
        };
        this.mVoipEnableOnClickListener = new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialogInterface, int n) {
                dialogInterface = new Intent("com.miui.voip.action.TURN_ON_VOIP");
                dialogInterface.putExtra("extra_turn_on_voip_from", 2);
                MxActivateSimpleDialog.this.mContext.sendBroadcast((Intent)dialogInterface);
            }
        };
        this.mMxAndVoipEnableOnClickListener = new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialogInterface, int n) {
                MxActivateService.enableAll(MxActivateSimpleDialog.this.mContext, true);
                dialogInterface = new Intent("com.miui.voip.action.TURN_ON_VOIP");
                dialogInterface.putExtra("extra_turn_on_voip_from", 2);
                MxActivateSimpleDialog.this.mContext.sendBroadcast((Intent)dialogInterface);
            }
        };
        this.mPhoneActivateOnClickListener = new DialogInterface.OnClickListener(){

            /*
             * Enabled aggressive block sorting
             */
            public void onClick(DialogInterface dialogInterface, int n) {
                int n2 = MSimUtils.getMultiSimCount();
                if (MxActivateSimpleDialog.this.mSimIndex != -1) {
                    n = n2 = MxActivateSimpleDialog.this.mSimIndex;
                } else {
                    n = 0;
                    --n2;
                }
                dialogInterface = ActivateManager.get((Context)MxActivateSimpleDialog.this.mContext);
                while (n <= n2) {
                    int n3 = MxActivateSimpleDialog.this.mHasVoip ? 17 : 1;
                    dialogInterface.startActivateSim(n, 2, null, false, null, n3);
                    ++n;
                }
                if (MxActivateSimpleDialog.this.mActivationCallBack != null) {
                    MxActivateSimpleDialog.this.mActivationCallBack.onResult(0);
                }
            }
        };
        this.mNegativeButtonCancelListener = new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialogInterface, int n) {
                if (MxActivateSimpleDialog.this.mActivationCallBack != null) {
                    MxActivateSimpleDialog.this.mActivationCallBack.onResult(1);
                }
            }
        };
        this.mDialogCancelListener = new DialogInterface.OnCancelListener(){

            public void onCancel(DialogInterface dialogInterface) {
                if (MxActivateSimpleDialog.this.mActivationCallBack != null) {
                    MxActivateSimpleDialog.this.mActivationCallBack.onResult(1);
                }
            }
        };
        this.mContext = context;
        this.mType = n;
        this.mSource = string2;
        this.mSimIndex = n2;
        this.mHasVoip = bl;
        this.initRes(n, string2, bl);
    }

    private void create() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.mContext);
        builder.setTitle((CharSequence)this.mTitle).setMessage(this.mMessage).setPositiveButton(2131362206, this.getClickListener()).setNegativeButton(17039360, this.mNegativeButtonCancelListener).setOnCancelListener(this.mDialogCancelListener);
        this.mAlertDialog = builder.create();
    }

    private DialogInterface.OnClickListener getClickListener() {
        if (this.mType == 1) {
            return this.mMxEnableOnClickListener;
        }
        if (this.mType == 2) {
            return this.mVoipEnableOnClickListener;
        }
        if (this.mType == 3) {
            return this.mMxAndVoipEnableOnClickListener;
        }
        return this.mPhoneActivateOnClickListener;
    }

    private SpannableStringBuilder getVoipSpannableStringBuilderMessage(String string2, String string3) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append((CharSequence)string2);
        int n = string2.indexOf(string3);
        spannableStringBuilder.setSpan((Object)new VoipIntentSpan(), n, string3.length() + n, 17);
        return spannableStringBuilder;
    }

    private void initRes(int n, String string2, boolean bl) {
        Resources resources = this.mContext.getResources();
        if (n == 1) {
            this.mTitle = resources.getString(2131362164);
            this.mMessage = resources.getString(2131362205);
            return;
        }
        if (n == 3) {
            this.mTitle = resources.getString(2131362397);
            this.mMessage = this.getVoipSpannableStringBuilderMessage(resources.getString(2131362398), resources.getString(2131362399));
            return;
        }
        if (n == 2) {
            this.mTitle = resources.getString(2131362394);
            this.mMessage = this.getVoipSpannableStringBuilderMessage(resources.getString(2131362395), resources.getString(2131362396));
            return;
        }
        if (bl && "Mms_Reminder".equals((Object)string2)) {
            this.mTitle = resources.getString(2131362347);
            this.mMessage = this.getVoipSpannableStringBuilderMessage(resources.getString(2131362344), resources.getString(2131362345));
            return;
        }
        this.mTitle = resources.getString(2131362346);
        this.mMessage = resources.getString(2131362343);
    }

    public void setActivationCallBack(ActivationCallBack activationCallBack) {
        this.mActivationCallBack = activationCallBack;
    }

    public void show() {
        if (this.mAlertDialog == null) {
            this.create();
        }
        this.mAlertDialog.show();
        this.mAlertDialog.getMessageView().setMovementMethod(LinkMovementMethod.getInstance());
    }

    public static interface ActivationCallBack {
        public void onResult(int var1);
    }

    class VoipIntentSpan
    extends ClickableSpan {
        VoipIntentSpan() {
        }

        public void onClick(View view) {
            try {
                view = new Intent("com.miui.voip.action.VOIP_LICENSE");
                MxActivateSimpleDialog.this.mContext.startActivity((Intent)view);
                return;
            }
            catch (ActivityNotFoundException var1_2) {
                MyLog.e("MxActivateSimpleDialog", (Throwable)var1_2);
                return;
            }
        }

        public void updateDrawState(TextPaint textPaint) {
            textPaint.setUnderlineText(true);
        }
    }

}

