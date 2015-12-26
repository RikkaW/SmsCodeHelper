/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ClipData
 *  android.content.ClipboardManager
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  android.content.Intent
 *  android.net.Uri
 *  java.lang.Object
 *  java.lang.String
 */
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;

class abz
implements DialogInterface.OnClickListener {
    final /* synthetic */ String a;
    final /* synthetic */ abu b;

    abz(abu abu2, String string2) {
        this.b = abu2;
        this.a = string2;
    }

    public void onClick(DialogInterface dialogInterface, int n2) {
        if (n2 == 0) {
            dialogInterface = new Intent("android.intent.action.VIEW", Uri.parse((String)("tel:" + this.a)));
            abu.c(this.b).startActivity((Intent)dialogInterface);
            return;
        }
        if (n2 == 1) {
            dialogInterface = new Intent("android.intent.action.SENDTO", Uri.fromParts((String)"smsto", (String)this.a, (String)null));
            abu.c(this.b).startActivity((Intent)dialogInterface);
            return;
        }
        if (n2 == 2) {
            ((ClipboardManager)abu.c(this.b).getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText((CharSequence)null, (CharSequence)this.a));
            return;
        }
        dialogInterface = new Intent("android.intent.action.INSERT_OR_EDIT");
        dialogInterface.setType("vnd.android.cursor.item/contact");
        dialogInterface.putExtra("phone", this.a);
        dialogInterface.putExtra("com.android.contacts.extra.SHOW_CREATE_NEW_CONTACT_BUTTON", false);
        abu.c(this.b).startActivity((Intent)dialogInterface);
    }
}

