/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.net.Uri
 *  android.os.Bundle
 *  android.os.Handler
 *  java.lang.Object
 *  java.lang.String
 */
import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;

public class aai {
    public static final Uri a = null;

    public static void a(Context context, int n2) {
        aai.a(context, "android.intent.action.MMS_BLOCK_LIST", "mms", n2);
    }

    public static void a(Context object, String string2, String string3, int n2) {
        object = new aaj(string2, (Context)object, string3);
        if (n2 > 0) {
            new Handler().postDelayed((Runnable)object, (long)n2);
            return;
        }
        object.run();
    }

    public static class a
    extends b {
        public static final Uri a = Uri.parse((String)"content://blockresult/");

        public static String a(Context object) {
            try {
                object = object.getContentResolver().call(a, "getBlockCalls", null, null).getString("extra_info", null);
                return object;
            }
            catch (Exception var0_1) {
                var0_1.printStackTrace();
                return null;
            }
        }

        public static String b(Context object) {
            try {
                object = object.getContentResolver().call(a, "getBlockMessages", null, null).getString("extra_info", null);
                return object;
            }
            catch (Exception var0_1) {
                var0_1.printStackTrace();
                return null;
            }
        }
    }

    public static class b {
    }

}

