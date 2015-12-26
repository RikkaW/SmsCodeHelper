/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ClipData
 *  android.content.ClipboardManager
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  android.content.Intent
 *  android.net.Uri
 *  java.lang.Object
 *  java.lang.String
 */
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import com.ted.android.data.bubbleAction.ActionBase;
import com.ted.android.data.bubbleAction.DateReminderAction;

class acg
implements DialogInterface.OnClickListener {
    final /* synthetic */ ActionBase a;
    final /* synthetic */ DateReminderAction b;
    final /* synthetic */ String c;
    final /* synthetic */ abu d;

    acg(abu abu2, ActionBase actionBase, DateReminderAction dateReminderAction, String string2) {
        this.d = abu2;
        this.a = actionBase;
        this.b = dateReminderAction;
        this.c = string2;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void onClick(DialogInterface dialogInterface, int n2) {
        if (n2 == 0) {
            this.a.doAction(abu.c(this.d));
            return;
        } else {
            if (n2 == 1) {
                dialogInterface = new Intent("android.intent.action.VIEW");
                dialogInterface.putExtra("viewTime", this.b.startTime);
                dialogInterface.setData(Uri.parse((String)("content://com.android.calendar/time/" + this.b.startTime)));
                abu.c(this.d).startActivity((Intent)dialogInterface);
                return;
            }
            if (n2 != 2) return;
            {
                ((ClipboardManager)abu.c(this.d).getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText((CharSequence)null, (CharSequence)this.c));
                return;
            }
        }
    }
}

