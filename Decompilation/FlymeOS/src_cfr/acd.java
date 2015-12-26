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
 *  java.lang.Object
 *  java.lang.String
 */
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import com.ted.android.data.bubbleAction.ActionBase;

class acd
implements DialogInterface.OnClickListener {
    final /* synthetic */ ActionBase a;
    final /* synthetic */ String b;
    final /* synthetic */ abu c;

    acd(abu abu2, ActionBase actionBase, String string2) {
        this.c = abu2;
        this.a = actionBase;
        this.b = string2;
    }

    public void onClick(DialogInterface dialogInterface, int n2) {
        if (n2 == 0) {
            this.a.doAction(abu.c(this.c));
            return;
        }
        if (n2 == 1) {
            ((ClipboardManager)abu.c(this.c).getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText((CharSequence)null, (CharSequence)this.b));
            return;
        }
        dialogInterface = new Intent("android.intent.action.INSERT_OR_EDIT");
        dialogInterface.setType("vnd.android.cursor.item/contact");
        dialogInterface.putExtra("email", this.b);
        dialogInterface.putExtra("com.android.contacts.extra.SHOW_CREATE_NEW_CONTACT_BUTTON", false);
        abu.c(this.c).startActivity((Intent)dialogInterface);
    }
}

