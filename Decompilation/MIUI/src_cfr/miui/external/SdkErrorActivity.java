/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.AlertDialog
 *  android.app.AlertDialog$Builder
 *  android.app.Dialog
 *  android.app.DialogFragment
 *  android.app.FragmentManager
 *  android.app.ProgressDialog
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  android.content.Intent
 *  android.os.Bundle
 *  java.lang.Boolean
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.reflect.Method
 *  java.util.HashMap
 *  java.util.Locale
 */
package miui.external;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import miui.external.SdkConstants;
import miui.external.c;
import miui.external.f;

public class SdkErrorActivity
extends Activity
implements SdkConstants {
    private String f;
    private DialogInterface.OnClickListener g;
    private DialogInterface.OnClickListener h;

    public SdkErrorActivity() {
        this.g = new miui.external.a(this);
        this.h = new c(this);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private Dialog a() {
        String string2;
        String string3;
        if (Locale.CHINESE.getLanguage().equals((Object)this.f)) {
            string2 = "MIUI SDK\u53d1\u751f\u9519\u8bef";
            string3 = "\u8bf7\u91cd\u65b0\u5b89\u88c5MIUI SDK\u518d\u8fd0\u884c\u672c\u7a0b\u5e8f\u3002";
            do {
                return this.a(string2, string3, this.g);
                break;
            } while (true);
        }
        string2 = "MIUI SDK encounter errors";
        string3 = "Please re-install MIUI SDK and then re-run this application.";
        return this.a(string2, string3, this.g);
    }

    private Dialog a(String string2, String string3, DialogInterface.OnClickListener onClickListener) {
        return new AlertDialog.Builder((Context)this).setTitle((CharSequence)string2).setMessage((CharSequence)string3).setPositiveButton(17039370, onClickListener).setIcon(17301543).setCancelable(false).create();
    }

    private Dialog a(String string2, String string3, DialogInterface.OnClickListener onClickListener, DialogInterface.OnClickListener onClickListener2) {
        return new AlertDialog.Builder((Context)this).setTitle((CharSequence)string2).setMessage((CharSequence)string3).setPositiveButton(17039370, onClickListener).setNegativeButton(17039360, onClickListener2).setIcon(17301543).setCancelable(false).create();
    }

    static /* synthetic */ Dialog a(SdkErrorActivity sdkErrorActivity) {
        return sdkErrorActivity.d();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private Dialog b() {
        String string2;
        String string3;
        if (Locale.CHINESE.getLanguage().equals((Object)this.f)) {
            string2 = "\u6ca1\u6709\u627e\u5230MIUI SDK";
            string3 = "\u8bf7\u5148\u5b89\u88c5MIUI SDK\u518d\u8fd0\u884c\u672c\u7a0b\u5e8f\u3002";
            do {
                return this.a(string2, string3, this.g);
                break;
            } while (true);
        }
        string2 = "MIUI SDK not found";
        string3 = "Please install MIUI SDK and then re-run this application.";
        return this.a(string2, string3, this.g);
    }

    static /* synthetic */ boolean b(SdkErrorActivity sdkErrorActivity) {
        return sdkErrorActivity.h();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private Dialog c() {
        String string2;
        String string3;
        if (!this.g()) {
            String string4;
            String string5;
            if (Locale.CHINESE.getLanguage().equals((Object)this.f)) {
                string4 = "MIUI SDK\u7248\u672c\u8fc7\u4f4e";
                string5 = "\u8bf7\u5148\u5347\u7ea7MIUI SDK\u518d\u8fd0\u884c\u672c\u7a0b\u5e8f\u3002";
                do {
                    return this.a(string4, string5, this.g);
                    break;
                } while (true);
            }
            string4 = "MIUI SDK too old";
            string5 = "Please upgrade MIUI SDK and then re-run this application.";
            return this.a(string4, string5, this.g);
        }
        if (Locale.CHINESE.getLanguage().equals((Object)this.f)) {
            string2 = "MIUI SDK\u7248\u672c\u8fc7\u4f4e";
            string3 = "\u8bf7\u5148\u5347\u7ea7MIUI SDK\u518d\u8fd0\u884c\u672c\u7a0b\u5e8f\u3002\u662f\u5426\u73b0\u5728\u5347\u7ea7\uff1f";
            do {
                return this.a(string2, string3, this.h, this.g);
                break;
            } while (true);
        }
        string2 = "MIUI SDK too old";
        string3 = "Please upgrade MIUI SDK and then re-run this application. Upgrade now?";
        return this.a(string2, string3, this.h, this.g);
    }

    static /* synthetic */ Dialog c(SdkErrorActivity sdkErrorActivity) {
        return sdkErrorActivity.e();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private Dialog d() {
        String string2;
        String string3;
        if (Locale.CHINESE.getLanguage().equals((Object)this.f)) {
            string2 = "MIUI SDK\u6b63\u5728\u66f4\u65b0";
            string3 = "\u8bf7\u7a0d\u5019...";
            do {
                return ProgressDialog.show((Context)this, (CharSequence)string2, (CharSequence)string3, (boolean)true, (boolean)false);
                break;
            } while (true);
        }
        string2 = "MIUI SDK updating";
        string3 = "Please wait...";
        return ProgressDialog.show((Context)this, (CharSequence)string2, (CharSequence)string3, (boolean)true, (boolean)false);
    }

    static /* synthetic */ Dialog d(SdkErrorActivity sdkErrorActivity) {
        return sdkErrorActivity.f();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private Dialog e() {
        String string2;
        String string3;
        if (Locale.CHINESE.getLanguage().equals((Object)this.f)) {
            string2 = "MIUI SDK\u66f4\u65b0\u5b8c\u6210";
            string3 = "\u8bf7\u91cd\u65b0\u8fd0\u884c\u672c\u7a0b\u5e8f\u3002";
            do {
                return this.a(string2, string3, this.g);
                break;
            } while (true);
        }
        string2 = "MIUI SDK updated";
        string3 = "Please re-run this application.";
        return this.a(string2, string3, this.g);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private Dialog f() {
        String string2;
        String string3;
        if (Locale.CHINESE.getLanguage().equals((Object)this.f)) {
            string2 = "MIUI SDK\u66f4\u65b0\u5931\u8d25";
            string3 = "\u8bf7\u7a0d\u540e\u91cd\u8bd5\u3002";
            do {
                return this.a(string2, string3, this.g);
                break;
            } while (true);
        }
        string2 = "MIUI SDK update failed";
        string3 = "Please try it later.";
        return this.a(string2, string3, this.g);
    }

    private boolean g() {
        try {
            boolean bl = (Boolean)f.o().getMethod("supportUpdate", new Class[]{Map.class}).invoke((Object)null, new Object[]{null});
            return bl;
        }
        catch (Exception var2_2) {
            var2_2.printStackTrace();
            return false;
        }
    }

    private boolean h() {
        try {
            HashMap hashMap = new HashMap();
            boolean bl = (Boolean)f.o().getMethod("update", new Class[]{Map.class}).invoke((Object)null, new Object[]{hashMap});
            return bl;
        }
        catch (Exception var2_2) {
            var2_2.printStackTrace();
            return false;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void onCreate(Bundle object) {
        this.setTheme(16973909);
        super.onCreate((Bundle)object);
        this.f = Locale.getDefault().getLanguage();
        object = null;
        Object object2 = this.getIntent();
        if (object2 != null) {
            object = (SdkConstants.SdkError)((Object)object2.getSerializableExtra("com.miui.sdk.error"));
        }
        object2 = object;
        if (object == null) {
            object2 = SdkConstants.SdkError.GENERIC;
        }
        switch (.i[object2.ordinal()]) {
            default: {
                object = this.a();
                break;
            }
            case 1: {
                object = this.b();
                break;
            }
            case 2: {
                object = this.c();
            }
        }
        new a((Dialog)object).show(this.getFragmentManager(), "SdkErrorPromptDialog");
    }

    class a
    extends DialogFragment {
        private Dialog r;

        public a(Dialog dialog) {
            this.r = dialog;
        }

        public Dialog onCreateDialog(Bundle bundle) {
            return this.r;
        }
    }

}

