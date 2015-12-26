/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentValues
 *  android.content.Context
 *  android.net.Uri
 *  android.text.TextUtils
 *  java.lang.Object
 *  java.lang.String
 */
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;

public class abl {
    public static final Uri a = Uri.parse((String)"content://mms-sms/canonical-addresses");
    private static boolean b = false;

    public static final a a(String string2, String string3, long l2) {
        a a2 = new a();
        if (zj.a(string2, string3)) {
            a2.b = true;
            a2.c = 1;
        }
        return a2;
    }

    public static final Uri a(Context context, ContentValues contentValues, a a2) {
        zj.a(contentValues);
        return null;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static boolean a(Context object, long l2) {
        if ((object = gr.a((Context)object, l2, true)) == null || object.h().size() != 1) {
            return false;
        }
        return true;
    }

    public static boolean a(Context context, long[] arrl) {
        for (int i2 = 0; i2 < arrl.length; ++i2) {
            if (abl.a(context, arrl[i2])) continue;
            return false;
        }
        return true;
    }

    private static final boolean a(String string2) {
        if (TextUtils.isEmpty((CharSequence)string2)) {
            return false;
        }
        try {
            boolean bl2 = zj.a(string2);
            return bl2;
        }
        catch (Exception var0_1) {
            return false;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static final boolean a(String string2, String string3, Runnable runnable) {
        if (abl.a(string2)) {
            return false;
        }
        try {
            zj.b(string2, string3);
            do {
                return true;
                break;
            } while (true);
        }
        catch (Exception var0_1) {
            var0_1.printStackTrace();
            return true;
        }
    }

    public static final class a {
        public gm a;
        public boolean b;
        public int c;
    }

}

