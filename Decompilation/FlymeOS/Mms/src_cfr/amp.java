/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.AsyncTask
 *  java.lang.Object
 *  java.lang.String
 *  java.util.HashMap
 */
import android.os.AsyncTask;
import java.util.HashMap;
import java.util.Map;

class amp
extends AsyncTask<Void, Void, Void> {
    final /* synthetic */ int a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ int d;
    final /* synthetic */ String e;
    final /* synthetic */ String f;
    final /* synthetic */ String g;
    final /* synthetic */ amo h;

    amp(amo amo2, int n2, String string2, String string3, int n3, String string4, String string5, String string6) {
        this.h = amo2;
        this.a = n2;
        this.b = string2;
        this.c = string3;
        this.d = n3;
        this.e = string4;
        this.f = string5;
        this.g = string6;
    }

    protected /* varargs */ Void a(Void ... hashMap) {
        hashMap = new HashMap();
        hashMap.put("result_mark", String.valueOf((int)this.a));
        if (this.b != null) {
            hashMap.put("package_name", this.b);
        }
        if (this.c != null) {
            hashMap.put("requrl", this.c);
        }
        hashMap.put("rescode", String.valueOf((int)this.d));
        if (this.e != null && !this.e.equalsIgnoreCase(this.c)) {
            hashMap.put("redirect_url", this.e);
        }
        if (this.f != null) {
            hashMap.put("msg", this.f);
        }
        if (this.g != null) {
            hashMap.put("version_log", this.g);
        }
        amo.a(this.h, (Map)hashMap);
        return null;
    }

    protected /* synthetic */ Object doInBackground(Object[] arrobject) {
        return this.a((Void[])arrobject);
    }
}

