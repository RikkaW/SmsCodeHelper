/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  org.json.JSONException
 *  org.json.JSONObject
 */
import com.amap.api.location.core.AMapLocException;
import org.json.JSONException;
import org.json.JSONObject;

public class ahf {
    private String a = "";
    private double b = 0.0;
    private double c = 0.0;
    private float d = 0.0f;
    private float e = 0.0f;
    private float f = 0.0f;
    private long g = 0;
    private AMapLocException h = new AMapLocException();
    private String i = "new";
    private String j = "";
    private String k = "";
    private String l = "";
    private String m = "";
    private String n = "";
    private String o = "";
    private String p = "";
    private String q = "";
    private String r = "";
    private String s = "";
    private String t = "";
    private String u = "";
    private String v = "";
    private String w = "";
    private String x = "";
    private JSONObject y = null;

    public ahf() {
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public ahf(JSONObject jSONObject) {
        if (jSONObject == null) return;
        try {
            this.a = jSONObject.getString("provider");
            this.b = jSONObject.getDouble("lon");
            this.c = jSONObject.getDouble("lat");
            this.d = jSONObject.getLong("accuracy");
            this.e = jSONObject.getLong("speed");
            this.f = jSONObject.getLong("bearing");
            this.g = jSONObject.getLong("time");
            this.i = jSONObject.getString("type");
            this.j = jSONObject.getString("retype");
            this.k = jSONObject.getString("citycode");
            this.l = jSONObject.getString("desc");
            this.m = jSONObject.getString("adcode");
            this.n = jSONObject.getString("country");
            this.o = jSONObject.getString("province");
            this.p = jSONObject.getString("city");
            this.q = jSONObject.getString("road");
            this.r = jSONObject.getString("street");
            this.s = jSONObject.getString("poiname");
            this.u = jSONObject.getString("floor");
            this.t = jSONObject.getString("poiid");
            this.v = jSONObject.getString("coord");
            this.w = jSONObject.getString("mcell");
            this.x = jSONObject.getString("district");
            return;
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
            return;
        }
    }

    public AMapLocException a() {
        return this.h;
    }

    public void a(double d2) {
        this.b = d2;
    }

    public void a(float f2) {
        this.d = f2;
    }

    public void a(long l2) {
        this.g = l2;
    }

    public void a(AMapLocException aMapLocException) {
        this.h = aMapLocException;
    }

    public void a(String string2) {
        this.t = string2;
    }

    public void a(JSONObject jSONObject) {
        this.y = jSONObject;
    }

    public String b() {
        return this.t;
    }

    public void b(double d2) {
        this.c = d2;
    }

    public void b(float f2) {
        this.f = f2;
    }

    public void b(String string2) {
        this.u = string2;
    }

    public String c() {
        return this.u;
    }

    public void c(String string2) {
        this.x = string2;
    }

    public String d() {
        return this.x;
    }

    public void d(String string2) {
        this.v = string2;
    }

    public double e() {
        return this.b;
    }

    public void e(String string2) {
        this.w = string2;
    }

    public double f() {
        return this.c;
    }

    public void f(String string2) {
        this.a = string2;
    }

    public float g() {
        return this.d;
    }

    public void g(String string2) {
        this.i = string2;
    }

    public long h() {
        return this.g;
    }

    public void h(String string2) {
        this.j = string2;
    }

    public String i() {
        return this.i;
    }

    public void i(String string2) {
        this.k = string2;
    }

    public String j() {
        return this.j;
    }

    public void j(String string2) {
        this.l = string2;
    }

    public String k() {
        return this.k;
    }

    public void k(String string2) {
        this.m = string2;
    }

    public String l() {
        return this.l;
    }

    public void l(String string2) {
        this.n = string2;
    }

    public String m() {
        return this.m;
    }

    public void m(String string2) {
        this.o = string2;
    }

    public String n() {
        return this.n;
    }

    public void n(String string2) {
        this.p = string2;
    }

    public String o() {
        return this.o;
    }

    public void o(String string2) {
        this.q = string2;
    }

    public String p() {
        return this.p;
    }

    public void p(String string2) {
        this.r = string2;
    }

    public String q() {
        return this.q;
    }

    public void q(String string2) {
        this.s = string2;
    }

    public String r() {
        return this.r;
    }

    public String s() {
        return this.s;
    }

    public JSONObject t() {
        return this.y;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public String u() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("provider", (Object)this.a);
            jSONObject.put("lon", this.b);
            jSONObject.put("lat", this.c);
            jSONObject.put("accuracy", (double)this.d);
            jSONObject.put("speed", (double)this.e);
            jSONObject.put("bearing", (double)this.f);
            jSONObject.put("time", this.g);
            jSONObject.put("type", (Object)this.i);
            jSONObject.put("retype", (Object)this.j);
            jSONObject.put("citycode", (Object)this.k);
            jSONObject.put("desc", (Object)this.l);
            jSONObject.put("adcode", (Object)this.m);
            jSONObject.put("country", (Object)this.n);
            jSONObject.put("province", (Object)this.o);
            jSONObject.put("city", (Object)this.p);
            jSONObject.put("road", (Object)this.q);
            jSONObject.put("street", (Object)this.r);
            jSONObject.put("poiname", (Object)this.s);
            jSONObject.put("poiid", (Object)this.t);
            jSONObject.put("floor", (Object)this.u);
            jSONObject.put("coord", (Object)this.v);
            jSONObject.put("mcell", (Object)this.w);
            jSONObject.put("district", (Object)this.x);
            if (jSONObject != null) return jSONObject.toString();
            return null;
        }
        catch (JSONException var1_2) {
            ahz.a((Throwable)var1_2);
            return null;
        }
    }
}

