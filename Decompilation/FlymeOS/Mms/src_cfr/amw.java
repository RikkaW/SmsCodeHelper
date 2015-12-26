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

class amw
extends AsyncTask<Void, Void, Void> {
    final /* synthetic */ int a;
    final /* synthetic */ int b;
    final /* synthetic */ String c;
    final /* synthetic */ amv d;

    amw(amv amv2, int n2, int n3, String string2) {
        this.d = amv2;
        this.a = n2;
        this.b = n3;
        this.c = string2;
    }

    protected /* varargs */ Void a(Void ... hashMap) {
        hashMap = new HashMap();
        hashMap.put("result_mark", String.valueOf((int)this.a));
        hashMap.put("rescode", String.valueOf((int)this.b));
        if (this.c != null) {
            hashMap.put("msg", this.c);
        }
        String string2 = anl.d(amv.a(this.d));
        String string3 = anl.c(amv.a(this.d));
        String string4 = anl.b(amv.a(this.d));
        String string5 = anl.a(amv.a(this.d));
        if (string2 != null) {
            hashMap.put("local_model", string2);
        }
        if (string3 != null) {
            hashMap.put("android_version", string3);
        }
        if (string4 != null) {
            hashMap.put("flyme_version", string4);
        }
        if (string5 != null) {
            hashMap.put("app_version", string5);
        }
        amv.a(this.d, (Map)hashMap, "http://u.meizu.com/appupgrade/check");
        return null;
    }

    protected /* synthetic */ Object doInBackground(Object[] arrobject) {
        return this.a((Void[])arrobject);
    }
}

