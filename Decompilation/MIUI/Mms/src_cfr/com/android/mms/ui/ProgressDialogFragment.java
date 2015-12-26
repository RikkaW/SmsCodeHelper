/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.Dialog
 *  android.app.DialogFragment
 *  android.content.Context
 *  android.os.Bundle
 *  java.lang.String
 *  miui.app.ProgressDialog
 */
package com.android.mms.ui;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import miui.app.ProgressDialog;

public class ProgressDialogFragment
extends DialogFragment {
    private String mMessage;

    public ProgressDialogFragment(String string2) {
        this.mMessage = string2;
    }

    public Dialog onCreateDialog(Bundle bundle) {
        bundle = new ProgressDialog((Context)this.getActivity());
        bundle.setMessage((CharSequence)this.mMessage);
        return bundle;
    }
}

