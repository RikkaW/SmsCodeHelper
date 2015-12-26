/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.AlertDialog
 *  android.app.AlertDialog$Builder
 *  android.app.Dialog
 *  android.app.KeyguardManager
 *  android.content.BroadcastReceiver
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnCancelListener
 *  android.content.DialogInterface$OnClickListener
 *  android.content.DialogInterface$OnDismissListener
 *  android.content.Intent
 *  android.content.IntentFilter
 *  android.content.res.Resources
 *  android.text.SpannableString
 *  android.text.TextUtils
 *  android.text.style.AbsoluteSizeSpan
 *  android.text.style.ForegroundColorSpan
 *  android.view.View
 *  android.view.Window
 *  android.widget.Button
 *  android.widget.TextView
 *  java.lang.Class
 *  java.lang.Enum
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 */
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import com.meizu.update.UpdateInfo;
import com.meizu.update.display.KeyguardHelperActivity;

public abstract class aks {
    protected Context a;
    protected UpdateInfo b;
    protected boolean c;
    protected Dialog d;
    private BroadcastReceiver e;

    protected aks(Context context, UpdateInfo updateInfo) {
        this.e = new aky(this);
        if (context == null || updateInfo == null) {
            throw new IllegalArgumentException("params cant be null!");
        }
        this.a = context;
        this.b = updateInfo;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private TextView a(AlertDialog alertDialog) {
        Object object = aji.a("com.android.internal.R$id", "alertTitle");
        if (object == null) return null;
        try {
            if (!(object instanceof Integer)) return null;
            return (TextView)alertDialog.findViewById(((Integer)object).intValue());
        }
        catch (Exception var1_2) {
            // empty catch block
        }
        return null;
    }

    static /* synthetic */ void a(aks aks2) {
        aks2.e();
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private akg c() {
        var3_1 = this.a();
        var1_2 = ani.b() != false ? 5 : 3;
        var4_3 = new AlertDialog.Builder(this.a, var1_2);
        if (TextUtils.isEmpty((CharSequence)var3_1.a)) ** GOTO lbl13
        if (!TextUtils.isEmpty((CharSequence)var3_1.b)) {
            var1_2 = 1;
            var5_4 = new SpannableString((CharSequence)(var3_1.a + "\n" + var3_1.b));
            var5_4.setSpan((Object)new AbsoluteSizeSpan(this.a.getResources().getDimensionPixelSize(akq.b.mzuc_dialog_sub_title_text_size)), var3_1.a.length(), var5_4.length(), 33);
            var5_4.setSpan((Object)new ForegroundColorSpan(this.a.getResources().getColor(akq.a.mzuc_dialog_sub_title_text_color)), var3_1.a.length(), var5_4.length(), 33);
            var4_3.setTitle((CharSequence)var5_4);
        } else {
            var4_3.setTitle((CharSequence)var3_1.a);
lbl13: // 2 sources:
            var1_2 = 0;
        }
        var4_3.setMessage((CharSequence)var3_1.c);
        var4_3.setPositiveButton((CharSequence)var3_1.d, (DialogInterface.OnClickListener)new akt(this, var3_1));
        if (!TextUtils.isEmpty((CharSequence)var3_1.e)) {
            var4_3.setNegativeButton((CharSequence)var3_1.e, (DialogInterface.OnClickListener)new aku(this, var3_1));
        } else {
            var4_3.setCancelable(false);
        }
        if (!TextUtils.isEmpty((CharSequence)var3_1.f)) {
            var4_3.setNeutralButton((CharSequence)var3_1.f, (DialogInterface.OnClickListener)new akv(this, var3_1));
        }
        var4_3.setOnCancelListener((DialogInterface.OnCancelListener)new akw(this, var3_1));
        this.d = var4_3 = var4_3.create();
        if (this.c) {
            var4_3.getWindow().setType(2003);
            this.g();
        }
        var4_3.setCanceledOnTouchOutside(false);
        var4_3.setOnDismissListener((DialogInterface.OnDismissListener)new akx(this));
        this.f();
        this.d();
        var4_3.show();
        var5_4 = var4_3.getButton(-1);
        var6_5 = var4_3.getButton(-2);
        var7_6 = var4_3.getButton(-3);
        if (var5_4 != null && var6_5 != null && var7_6 != null && !TextUtils.isEmpty((CharSequence)var3_1.f) && !TextUtils.isEmpty((CharSequence)var3_1.e)) {
            var2_7 = this.a.getResources().getDimensionPixelSize(akq.b.mzuc_dialog_btn_text_size_small);
            var5_4.setTextSize(0, (float)var2_7);
            var6_5.setTextSize(0, (float)var2_7);
            var7_6.setTextSize(0, (float)var2_7);
        }
        if (var1_2 == 0) return new aln((AlertDialog)var4_3, this.b.mNeedUpdate, this.c);
        var3_1 = this.a((AlertDialog)var4_3);
        if (var3_1 != null) {
            var3_1.setLineSpacing((float)this.a.getResources().getDimensionPixelSize(akq.b.mzuc_dialog_title_line_spacing), 1.0f);
        }
        if ((var3_1 = (TextView)var4_3.findViewById(16908299)) == null) return new aln((AlertDialog)var4_3, this.b.mNeedUpdate, this.c);
        var3_1.setTextSize(0, (float)this.a.getResources().getDimensionPixelSize(akq.b.mzuc_dialog_msg_text_size));
        var3_1.setTextColor(this.a.getResources().getColor(akq.a.mzuc_dialog_msg_text_color));
        var3_1.setLineSpacing((float)this.a.getResources().getDimensionPixelSize(akq.b.mzuc_dialog_msg_line_spacing), 1.0f);
        var3_1.setPadding(var3_1.getPaddingLeft() + 8, var3_1.getPaddingTop(), 8 + var3_1.getPaddingRight(), var3_1.getPaddingBottom());
        return new aln((AlertDialog)var4_3, this.b.mNeedUpdate, this.c);
    }

    private void d() {
        anf.b("register broadcast:" + (Object)this.d);
        IntentFilter intentFilter = new IntentFilter("com.meizu.update.component.dialog_show");
        this.a.getApplicationContext().registerReceiver(this.e, intentFilter);
    }

    private void e() {
        try {
            anf.b("unregister broadcast:" + (Object)this.d);
            this.a.getApplicationContext().unregisterReceiver(this.e);
            return;
        }
        catch (Exception var1_1) {
            var1_1.printStackTrace();
            return;
        }
    }

    private void f() {
        Intent intent = new Intent("com.meizu.update.component.dialog_show");
        intent.setPackage(this.a.getPackageName());
        this.a.sendBroadcast(intent);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void g() {
        try {
            anf.b("check keyguard state");
            if (!ani.d()) return;
            KeyguardManager keyguardManager = (KeyguardManager)this.a.getSystemService("keyguard");
            if (keyguardManager.isKeyguardLocked() && !keyguardManager.isKeyguardSecure()) {
                anf.d("need unlock keyguard");
                boolean bl2 = true;
                if (!bl2) return;
                {
                    keyguardManager = new Intent(this.a, (Class)KeyguardHelperActivity.class);
                    keyguardManager.addFlags(268435456);
                    this.a.startActivity((Intent)keyguardManager);
                    return;
                }
            }
            anf.d("need not unlock keyguard");
            return;
        }
        catch (Exception var2_2) {
            anf.d("unlock keyguard exception");
            var2_2.printStackTrace();
        }
    }

    public abstract a a();

    public void a(boolean bl2) {
        this.c = bl2;
    }

    public akg b() {
        try {
            akg akg2 = this.c();
            return akg2;
        }
        catch (Exception var1_2) {
            anf.d("display dialog exception!");
            var1_2.printStackTrace();
            return null;
        }
    }

    public static class aks$a {
        String a;
        String b;
        String c;
        String d;
        String e;
        String f;
        a g;

        public aks$a(String string2, String string3, String string4, String string5, String string6, String string7, a a2) {
            this.a = string2;
            this.b = string3;
            this.c = string4;
            this.d = string5;
            this.e = string6;
            this.f = string7;
            this.g = a2;
        }

        public static interface aks$a$a {
            public void a(a var1);

            public static enum a {
                a,
                b,
                c,
                d;
                

                private a() {
                }
            }

        }

    }

}

