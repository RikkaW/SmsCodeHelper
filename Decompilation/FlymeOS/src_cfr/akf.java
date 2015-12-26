/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.text.TextUtils
 *  android.util.Pair
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuffer
 *  java.util.ArrayList
 *  org.json.JSONArray
 *  org.json.JSONObject
 */
import android.content.Context;
import android.text.TextUtils;
import android.util.Pair;
import com.meizu.update.UpdateInfo;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class akf {
    public static final UpdateInfo a(Context context) {
        return akf.a(context, context.getPackageName());
    }

    public static final UpdateInfo a(Context object, String string2) {
        try {
            object = akf.b((Context)object, string2);
            return object;
        }
        catch (ane var0_1) {
            var0_1.printStackTrace();
            return null;
        }
    }

    private static String a(String string2, String string3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Pair((Object)"apps", (Object)string2));
        arrayList.add(new Pair((Object)"sign", (Object)string3));
        return ank.b("http://u.meizu.com/appupgrade/check", arrayList);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static boolean a(Context context, String string2, String string3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Pair((Object)"subservices", (Object)string2));
        arrayList.add(new Pair((Object)"sign", (Object)string3));
        string2 = ank.a("http://u.meizu.com/subscription/registerWithSign", arrayList);
        if (string2 != null) {
            if (new JSONObject(string2).getJSONObject("reply").getInt("code") == 200) {
                anf.a(context, "register push success");
                return true;
            }
            anf.a(context, "register push failed: " + string2);
            do {
                return false;
                break;
            } while (true);
        }
        anf.a(context, "register push response null");
        return false;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static final UpdateInfo b(Context object, String object2) {
        try {
            String string2 = anl.e((Context)object);
            String string3 = anl.d((Context)object);
            String string4 = anl.f((Context)object);
            String string5 = anl.c((Context)object);
            String string6 = anl.b((Context)object);
            String string7 = anl.a((Context)object, (String)object2);
            object = new JSONObject();
            object.put("serviceName", object2);
            object.put("version", (Object)string7);
            string7 = new JSONArray();
            string7.put(object);
            object = new JSONObject();
            object.put("deviceType", (Object)string3);
            object.put("firmware", (Object)string5);
            object.put("sysVer", (Object)string6);
            object.put("imei", (Object)string2);
            object.put("sn", (Object)string4);
            object.put("services", (Object)string7);
            object = object.toString();
            string2 = new StringBuffer();
            string2.append((String)object).append("2635881a7ab0593849fe89e685fc56cd");
            object = akf.a((String)object, anl.b(string2.toString()));
            if (!TextUtils.isEmpty((CharSequence)object)) {
                if ((object2 = akf.b((String)object, (String)object2)) != null) {
                    if (!object2.mNeedUpdate && !object2.mExistsUpdate) {
                        anf.b("no update");
                        return object2;
                    }
                    anf.b("new version : " + object2.mVersionName);
                    return object2;
                }
                anf.c("check update parse failed.");
                throw new ane("Cant parse server response:" + (String)object);
            }
            anf.c("check update response null.");
            throw new ane("Check update response null.");
        }
        catch (ane var0_1) {
            throw var0_1;
        }
        catch (Exception var0_2) {
            var0_2.printStackTrace();
            throw new ane(var0_2.getMessage());
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static UpdateInfo b(String string2, String object) {
        int n2 = (string2 = new JSONObject(string2).getJSONObject("reply")).getInt("code");
        if (n2 == 200) {
            anf.a("updateinfo: " + string2.toString());
            string2 = string2.getJSONArray("value");
            n2 = string2.length();
            if (n2 == 1) {
                String string3 = (string2 = string2.getJSONObject(0)).getString("serviceName");
                if (object.equals((Object)string3)) {
                    object = new UpdateInfo();
                    object.mExistsUpdate = string2.getBoolean("existsUpdate");
                    object.mNeedUpdate = string2.getBoolean("needUpdate");
                    if (!object.mExistsUpdate && !object.mNeedUpdate) return object;
                    object.mUpdateUrl = string2.getString("updateUrl");
                    object.mSize = string2.getString("fileSize");
                    object.mVersionDate = string2.getString("releaseDate");
                    object.mVersionDesc = string2.getString("releaseNote");
                    object.mVersionName = string2.getString("latestVersion");
                    if (string2.has("digest")) {
                        object.mDigest = string2.getString("digest");
                    }
                    if (string2.has("verifyMode")) {
                        object.mVerifyMode = string2.getInt("verifyMode");
                    }
                    if (string2.has("size")) {
                        object.mSizeByte = string2.getLong("size");
                    }
                    if (string2.has("updateUrl2")) {
                        object.mUpdateUrl2 = string2.getString("updateUrl2");
                    }
                    if (anl.c() && !TextUtils.isEmpty((CharSequence)object.mVersionName) && object.mVersionName.endsWith("_i")) {
                        object.mVersionName = object.mVersionName.substring(0, object.mVersionName.length() - "_i".length());
                    }
                    if (!string2.has("noteNetwork")) return object;
                    object.mNoteNetWork = string2.getBoolean("noteNetwork");
                    return object;
                }
                anf.d("server return package : " + string3);
                do {
                    return null;
                    break;
                } while (true);
            }
            anf.d("server return size : " + n2);
            return null;
        }
        anf.c("unknown server code : " + n2);
        return null;
    }

    public static boolean c(Context context, String string2) {
        try {
            anf.a(context, "start register push to server");
            String string3 = anl.e(context);
            String string4 = anl.d(context);
            String string5 = anl.f(context);
            String string6 = anl.c(context);
            String string7 = anl.b(context);
            String string8 = context.getPackageName();
            String string9 = anl.a(context);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("serviceName", (Object)string8);
            jSONObject.put("subStatus", 1);
            jSONObject.put("version", (Object)string9);
            jSONObject.put("serviceToken", (Object)string2);
            string2 = new JSONArray();
            string2.put((Object)jSONObject);
            jSONObject = new JSONObject();
            jSONObject.put("deviceType", (Object)string4);
            jSONObject.put("firmware", (Object)string6);
            jSONObject.put("sysVer", (Object)string7);
            jSONObject.put("imei", (Object)string3);
            jSONObject.put("sn", (Object)string5);
            jSONObject.put("services", (Object)string2);
            string2 = jSONObject.toString();
            string3 = new StringBuffer();
            string3.append(string2).append("2635881a7ab0593849fe89e685fc56cd");
            boolean bl2 = akf.a(context, string2, anl.b(string3.toString()));
            return bl2;
        }
        catch (Exception var1_2) {
            anf.a(context, "register push to server exception:" + var1_2.getMessage());
            var1_2.printStackTrace();
            return false;
        }
    }
}

