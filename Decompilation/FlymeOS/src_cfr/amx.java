/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.text.TextUtils
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  org.json.JSONException
 *  org.json.JSONObject
 */
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.meizu.cloud.pushsdk.PushManager;
import com.meizu.update.service.MzUpdateComponentService;
import org.json.JSONException;
import org.json.JSONObject;

public class amx {
    public static void a(Context context) {
        if (!TextUtils.isEmpty((CharSequence)PushManager.getPushId(context)) && !amx.b(context)) {
            MzUpdateComponentService.b(context);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public static final void a(Context context, boolean bl2) {
        SharedPreferences.Editor editor = amx.c(context).edit();
        if (bl2) {
            editor.putString("push_version", anl.a(context));
        } else {
            editor.putString("push_version", "");
        }
        editor.apply();
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static boolean a(Context context, String string2) {
        try {
            String string3 = context.getPackageName();
            Object object = new JSONObject(string2);
            boolean bl2 = object.has(string3);
            if (!bl2) return false;
            object = object.getJSONObject(string3).getString("version");
            and.a(context).a(and.a.a, (String)object, anl.b(context, string3));
            return true;
        }
        catch (JSONException var0_1) {
            var0_1.printStackTrace();
            anf.c("unknown server push : " + string2);
        }
        return false;
        catch (Exception exception) {
            return true;
        }
    }

    public static final void b(Context context, String string2) {
        context = amx.c(context).edit();
        context.putString("skip_version", string2);
        context.apply();
    }

    public static final boolean b(Context context) {
        String string2 = amx.c(context).getString("push_version", null);
        if (!TextUtils.isEmpty((CharSequence)string2)) {
            return string2.equals((Object)anl.a(context));
        }
        return false;
    }

    public static final SharedPreferences c(Context context) {
        return context.getSharedPreferences("mz_update_component_history", 0);
    }

    public static final boolean c(Context object, String string2) {
        if (!TextUtils.isEmpty((CharSequence)(object = amx.c((Context)object).getString("skip_version", null))) && object.equals((Object)string2)) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static final void d(Context context) {
        if (!amx.e(context)) {
            anf.c("cloud server not enable, skip register");
            return;
        } else {
            if (TextUtils.isEmpty((CharSequence)PushManager.getPushId(context))) {
                anf.b(context, "Request sip register");
                PushManager.register(context);
                return;
            }
            if (amx.b(context)) return;
            {
                MzUpdateComponentService.b(context);
                return;
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static final boolean e(Context arrstring) {
        if (TextUtils.isEmpty((CharSequence)(arrstring = anl.b((Context)arrstring, "com.meizu.cloud")))) return false;
        if ((arrstring = arrstring.split("\\.")).length < 2) return false;
        try {
            int n2 = Integer.parseInt((String)arrstring[0]);
            int n3 = Integer.parseInt((String)arrstring[1]);
            if (n2 > 4) {
                return true;
            }
            if (n2 != 4) return false;
            if (n3 > 5) return true;
            return false;
        }
        catch (NumberFormatException numberFormatException) {
            return true;
        }
    }
}

