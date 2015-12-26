/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.AlertDialog
 *  android.app.AlertDialog$Builder
 *  android.app.ProgressDialog
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnCancelListener
 *  android.content.DialogInterface$OnClickListener
 *  android.content.res.Resources
 *  java.lang.Object
 *  java.lang.String
 */
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;

public class anm {
    public static AlertDialog a(Context context, String string2, DialogInterface.OnClickListener onClickListener, DialogInterface.OnCancelListener onCancelListener) {
        return anm.a(context, null, string2, onClickListener, onCancelListener, -1);
    }

    public static AlertDialog a(Context context, String string2, String string3, DialogInterface.OnClickListener onClickListener, DialogInterface.OnCancelListener onCancelListener, int n2) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (string2 != null && string2.length() > 0) {
            builder.setTitle((CharSequence)string2);
        }
        if (string3 != null) {
            builder.setMessage((CharSequence)string3);
        }
        if (n2 > 0) {
            builder.setIcon(n2);
        }
        builder.setPositiveButton((CharSequence)context.getResources().getString(17039370), onClickListener);
        builder.setCancelable(false);
        if (onCancelListener != null) {
            builder.setOnCancelListener(onCancelListener);
        }
        return builder.show();
    }

    public static ProgressDialog a(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(0);
        progressDialog.setMessage((CharSequence)context.getResources().getString(akq.d.mzuc_wait_tip));
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }
}

