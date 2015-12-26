/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.text.TextUtils
 *  java.io.File
 *  java.lang.Object
 *  java.lang.String
 *  org.json.JSONException
 *  org.json.JSONObject
 */
import android.text.TextUtils;
import java.io.File;
import org.json.JSONException;
import org.json.JSONObject;

public class be {
    private String a;
    private String b;
    private String c;
    private String d;
    private int e;
    private int f;
    private String g;
    private String h;
    private String i;
    private long j;

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static be a(JSONObject jSONObject) {
        be be2;
        be2 = new be();
        if (!jSONObject.isNull("url")) {
            try {
                be2.a(jSONObject.getString("url"));
            }
            catch (JSONException var2_10) {}
        }
        if (!jSONObject.isNull("path")) {
            try {
                be2.b(jSONObject.getString("path"));
            }
            catch (JSONException var2_9) {}
        }
        if (!jSONObject.isNull("md5")) {
            try {
                be2.c(jSONObject.getString("md5"));
            }
            catch (JSONException var2_8) {}
        }
        if (!jSONObject.isNull("prompt")) {
            try {
                be2.a(jSONObject.getInt("prompt"));
            }
            catch (JSONException var2_7) {}
        }
        if (!jSONObject.isNull("force")) {
            try {
                be2.b(jSONObject.getInt("force"));
            }
            catch (JSONException var2_6) {}
        }
        if (!jSONObject.isNull("desc")) {
            try {
                be2.d(jSONObject.getString("desc"));
            }
            catch (JSONException var2_5) {}
        }
        if (!jSONObject.isNull("version")) {
            try {
                be2.e(jSONObject.getString("version"));
            }
            catch (JSONException var2_4) {}
        }
        if (!jSONObject.isNull("exp")) {
            try {
                be2.f(jSONObject.getString("exp"));
            }
            catch (JSONException var2_3) {}
        }
        if (jSONObject.isNull("size")) return be2;
        try {
            be2.a(jSONObject.getLong("size"));
            return be2;
        }
        catch (JSONException jSONException) {
            return be2;
        }
    }

    public String a() {
        return this.b;
    }

    public void a(int n2) {
        this.e = n2;
    }

    public void a(long l2) {
        this.j = l2;
    }

    public void a(String string2) {
        this.b = string2;
    }

    public String b() {
        return this.c;
    }

    public void b(int n2) {
        this.f = n2;
    }

    public void b(String string2) {
        String string3 = this.c = string2;
        if (string2.indexOf(File.separator) >= 0) {
            string3 = string2.substring(string2.lastIndexOf(File.separator) + 1);
        }
        this.a = string3;
    }

    public String c() {
        return this.d;
    }

    public void c(String string2) {
        this.d = string2;
    }

    public String d() {
        return this.i;
    }

    public void d(String string2) {
        this.g = string2;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public JSONObject e() {
        JSONObject jSONObject;
        jSONObject = new JSONObject();
        if (!TextUtils.isEmpty((CharSequence)this.a())) {
            try {
                jSONObject.put("url", (Object)this.a());
            }
            catch (JSONException var2_10) {}
        }
        if (!TextUtils.isEmpty((CharSequence)this.b())) {
            try {
                jSONObject.put("path", (Object)this.c);
            }
            catch (JSONException var2_9) {}
        }
        if (!TextUtils.isEmpty((CharSequence)this.c())) {
            try {
                jSONObject.put("md5", (Object)this.c());
            }
            catch (JSONException var2_8) {}
        }
        try {
            jSONObject.put("prompt", this.e);
        }
        catch (JSONException var2_7) {}
        try {
            jSONObject.put("force", this.f);
        }
        catch (JSONException var2_6) {}
        if (!TextUtils.isEmpty((CharSequence)this.g)) {
            try {
                jSONObject.put("desc", (Object)this.g);
            }
            catch (JSONException var2_5) {}
        }
        if (!TextUtils.isEmpty((CharSequence)this.h)) {
            try {
                jSONObject.put("version", (Object)this.h);
            }
            catch (JSONException var2_4) {}
        }
        if (!TextUtils.isEmpty((CharSequence)this.i)) {
            try {
                jSONObject.put("exp", (Object)this.i);
            }
            catch (JSONException var2_3) {}
        }
        if (this.j == 0) return jSONObject;
        try {
            jSONObject.put("size", this.j);
            return jSONObject;
        }
        catch (JSONException jSONException) {
            return jSONObject;
        }
    }

    public void e(String string2) {
        this.h = string2;
    }

    public String f() {
        return this.a;
    }

    public void f(String string2) {
        this.i = string2;
    }
}

