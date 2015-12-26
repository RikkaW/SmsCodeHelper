/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ComponentName
 *  android.content.Context
 *  android.content.Intent
 *  android.content.ServiceConnection
 *  android.os.Bundle
 *  android.os.RemoteException
 *  java.lang.Float
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  org.json.JSONObject
 */
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.RemoteException;
import com.amap.api.location.core.b;
import com.amap.api.location.core.c;
import com.amap.api.location.core.e;
import org.json.JSONObject;

public class ahi {
    a a = null;
    private String b = null;
    private Context c = null;
    private boolean d = true;
    private aft e = null;
    private ServiceConnection f = null;
    private Intent g = new Intent();
    private String h = "com.autonavi.minimap";
    private String i = "com.amap.api.service.AMapService";
    private String j = "invaid type";
    private String k = "empty appkey";
    private String l = "refused";
    private String m = "failed";

    ahi(Context context) {
        this.c = context;
        try {
            this.b = b.a(e.b(c.a.getBytes("UTF-8"), "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDCEYwdO3V2ANrhApjqyk7X8FH5AEaWly58kP9IDAhMqwtIbmcJrUK9oO9Afh3KZnOlDtjiowy733YqpLRO7WBvdbW/c4Dz/d3dy/m+6HMqxaak+GQQRHw/VPdKciaZ3eIZp4MWOyIQwiFSQvPTAo/Na8hV4SgBZHB3lGFw0yu+BmG+h32eIE6p4Y8EDCn+G+yzekX+taMrWTQIysledrygZSGPv1ukbdFDnH/xZEI0dCr9pZT+AZQl3o9a2aMyuRrHM0oupXKKiYl69Y8fKh1Tyd752rF6LrR5uOb9aOfXt18hb+3YL5P9rQ+ZRYbyHYFaxzBPA2jLq0KUQ+Dmg7YhAgMBAAECggEAL9pj0lF3BUHwtssNKdf42QZJMD0BKuDcdZrLV9ifs0f54EJY5enzKw8j76MpdV8N5QVkNX4/BZR0bs9uJogh31oHFs5EXeWbb7V8P7bRrxpNnSAijGBWwscQsyqymf48YlcL28949ujnjoEz3jQjgWOyYnrCgpVhphrQbCGmB5TcZnTFvHfozt/0tzuMj5na5lRnkD0kYXgr0x/SRZcPoCybSpc3t/B/9MAAboGaV/QQkTotr7VOuJfaPRjvg8rzyPzavo3evxsjXj7vDXbN4w0cbk/Uqn2JtvPQ8HoysmF2HdYvILZibvJmWH1hA58b4sn5s6AqFRjMOL7rHdD+gQKBgQD+IzoofmZK5tTxgO9sWsG71IUeshQP9fe159jKCehk1RfuIqqbRP0UcxJiw4eNjHs4zU0HeRL3iF5XfUs0FQanO/pp6YL1xgVdfQlDdTdk6KFHJ0sUJapnJn1S2k7IKfRKE1+rkofSXMYUTsgHF1fDp+gxy4yUMY+h9O+JlKVKOwKBgQDDfaDIblaSm+B0lyG//wFPynAeGd0Q8wcMZbQQ/LWMJZhMZ7fyUZ+A6eL/jB53a2tgnaw2rXBpMe1qu8uSpym2plU0fkgLAnVugS5+KRhOkUHyorcbpVZbs5azf7GlTydR5dI1PHF3Bncemoa6IsEvumHWgQbVyTTz/O9mlFafUwKBgQCvDebms8KUf5JY1F6XfaCLWGVl8nZdVCmQFKbA7Lg2lI5KS3jHQWsupeEZRORffU/3nXsc1apZ9YY+r6CYvI77rRXd1KqPzxos/o7d96TzjkZhc9CEjTlmmh2jb5rqx/Ns/xFcZq/GGH+cx3ODZvHeZQ9NFY+9GLJ+dfB2DX0ZtwKBgQC+9/lZ8telbpqMqpqwqRaJ8LMn5JIdHZu0E6IcuhFLr+ogMW3zTKMpVtGGXEXi2M/TWRPDchiO2tQX4Q5T2/KW19QCbJ5KCwPWiGF3owN4tNOciDGh0xkSidRc0xAh8bnyejSoBry8zlcNUVztdkgMLOGonvCjZWPSOTNQnPYluwKBgCV+WVftpTk3l+OfAJTaXEPNYdh7+WQjzxZKjUaDzx80Ts7hRo2U+EQT7FBjQQNqmmDnWtujo5p1YmJC0FT3n1CVa7g901pb3b0RcOziYWAoJi0/+kLyeo6XBhuLeZ7h90S70GGh1o0V/j/9N1jb5DCL4xKkvdYePPTSTku0BM+n"));
            return;
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
            return;
        }
    }

    static /* synthetic */ aft a(ahi ahi2, aft aft2) {
        ahi2.e = aft2;
        return aft2;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private ahf a(Bundle object) {
        Object object2;
        block15 : {
            if (object == null) {
                return null;
            }
            if (object.containsKey("key")) {
                object2 = b.a(object.getString("key"));
                try {
                    object2 = e.c((byte[])object2, "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDCEYwdO3V2ANrhApjqyk7X8FH5AEaWly58kP9IDAhMqwtIbmcJrUK9oO9Afh3KZnOlDtjiowy733YqpLRO7WBvdbW/c4Dz/d3dy/m+6HMqxaak+GQQRHw/VPdKciaZ3eIZp4MWOyIQwiFSQvPTAo/Na8hV4SgBZHB3lGFw0yu+BmG+h32eIE6p4Y8EDCn+G+yzekX+taMrWTQIysledrygZSGPv1ukbdFDnH/xZEI0dCr9pZT+AZQl3o9a2aMyuRrHM0oupXKKiYl69Y8fKh1Tyd752rF6LrR5uOb9aOfXt18hb+3YL5P9rQ+ZRYbyHYFaxzBPA2jLq0KUQ+Dmg7YhAgMBAAECggEAL9pj0lF3BUHwtssNKdf42QZJMD0BKuDcdZrLV9ifs0f54EJY5enzKw8j76MpdV8N5QVkNX4/BZR0bs9uJogh31oHFs5EXeWbb7V8P7bRrxpNnSAijGBWwscQsyqymf48YlcL28949ujnjoEz3jQjgWOyYnrCgpVhphrQbCGmB5TcZnTFvHfozt/0tzuMj5na5lRnkD0kYXgr0x/SRZcPoCybSpc3t/B/9MAAboGaV/QQkTotr7VOuJfaPRjvg8rzyPzavo3evxsjXj7vDXbN4w0cbk/Uqn2JtvPQ8HoysmF2HdYvILZibvJmWH1hA58b4sn5s6AqFRjMOL7rHdD+gQKBgQD+IzoofmZK5tTxgO9sWsG71IUeshQP9fe159jKCehk1RfuIqqbRP0UcxJiw4eNjHs4zU0HeRL3iF5XfUs0FQanO/pp6YL1xgVdfQlDdTdk6KFHJ0sUJapnJn1S2k7IKfRKE1+rkofSXMYUTsgHF1fDp+gxy4yUMY+h9O+JlKVKOwKBgQDDfaDIblaSm+B0lyG//wFPynAeGd0Q8wcMZbQQ/LWMJZhMZ7fyUZ+A6eL/jB53a2tgnaw2rXBpMe1qu8uSpym2plU0fkgLAnVugS5+KRhOkUHyorcbpVZbs5azf7GlTydR5dI1PHF3Bncemoa6IsEvumHWgQbVyTTz/O9mlFafUwKBgQCvDebms8KUf5JY1F6XfaCLWGVl8nZdVCmQFKbA7Lg2lI5KS3jHQWsupeEZRORffU/3nXsc1apZ9YY+r6CYvI77rRXd1KqPzxos/o7d96TzjkZhc9CEjTlmmh2jb5rqx/Ns/xFcZq/GGH+cx3ODZvHeZQ9NFY+9GLJ+dfB2DX0ZtwKBgQC+9/lZ8telbpqMqpqwqRaJ8LMn5JIdHZu0E6IcuhFLr+ogMW3zTKMpVtGGXEXi2M/TWRPDchiO2tQX4Q5T2/KW19QCbJ5KCwPWiGF3owN4tNOciDGh0xkSidRc0xAh8bnyejSoBry8zlcNUVztdkgMLOGonvCjZWPSOTNQnPYluwKBgCV+WVftpTk3l+OfAJTaXEPNYdh7+WQjzxZKjUaDzx80Ts7hRo2U+EQT7FBjQQNqmmDnWtujo5p1YmJC0FT3n1CVa7g901pb3b0RcOziYWAoJi0/+kLyeo6XBhuLeZ7h90S70GGh1o0V/j/9N1jb5DCL4xKkvdYePPTSTku0BM+n");
                    break block15;
                }
                catch (Exception var7_4) {
                    var7_4.printStackTrace();
                }
            }
            object2 = null;
        }
        if (!object.containsKey("result")) return null;
        object = b.a(object.getString("result"));
        try {
            if ((object = new String(e.b((byte[])object2, (byte[])object), "utf-8")) == null) return null;
            JSONObject jSONObject = new JSONObject((String)object);
            if (jSONObject.has("error")) {
                boolean bl2;
                object = jSONObject.getString("error");
                if (this.j.equals(object)) {
                    this.d = false;
                }
                if (this.k.equals(object)) {
                    this.d = false;
                }
                if (this.l.equals(object)) {
                    this.d = false;
                }
                if (!(bl2 = this.m.equals(object))) return null;
                return null;
            }
            ahf ahf2 = new ahf();
            if (jSONObject.has("time")) {
                ahf2.a(jSONObject.getLong("time"));
            }
            if (jSONObject.has("acc")) {
                ahf2.a((float)jSONObject.getInt("acc"));
            }
            if (jSONObject.has("dir")) {
                ahf2.b(Float.parseFloat((String)jSONObject.getString("dir")));
            }
            ahf2.f("lbs");
            double d2 = jSONObject.has("lat") ? jSONObject.getDouble("lat") : 0.0;
            double d3 = jSONObject.has("lon") ? jSONObject.getDouble("lon") : 0.0;
            object = jSONObject.has("type") ? jSONObject.getString("type") : null;
            object2 = jSONObject.has("poiname") ? jSONObject.getString("poiname") : null;
            ahf2.q((String)object2);
            object2 = jSONObject.has("desc") ? jSONObject.getString("desc") : null;
            ahf2.j((String)object2);
            object2 = jSONObject.has("street") ? jSONObject.getString("street") : null;
            ahf2.p((String)object2);
            object2 = jSONObject.has("pid") ? jSONObject.getString("pid") : null;
            ahf2.a((String)object2);
            object2 = jSONObject.has("flr") ? jSONObject.getString("flr") : null;
            ahf2.b((String)object2);
            object2 = jSONObject.has("road") ? jSONObject.getString("road") : null;
            ahf2.o((String)object2);
            object2 = jSONObject.has("city") ? jSONObject.getString("city") : null;
            ahf2.n((String)object2);
            object2 = jSONObject.has("country") ? jSONObject.getString("country") : null;
            ahf2.l((String)object2);
            object2 = jSONObject.has("citycode") ? jSONObject.getString("citycode") : null;
            ahf2.i((String)object2);
            object2 = jSONObject.has("province") ? jSONObject.getString("province") : null;
            ahf2.m((String)object2);
            object2 = jSONObject.has("adcode") ? jSONObject.getString("adcode") : null;
            ahf2.k((String)object2);
            object2 = jSONObject.has("district") ? jSONObject.getString("district") : null;
            ahf2.c((String)object2);
            if ("WGS84".equals(object) && c.a(d2, d3)) {
                object = aia.a(d3, d2);
                ahf2.b((double)object[1]);
                ahf2.a((double)object[0]);
                return ahf2;
            }
            ahf2.b(d2);
            ahf2.a(d3);
            return ahf2;
        }
        catch (Exception var1_2) {
            var1_2.printStackTrace();
            return null;
        }
    }

    public void a() {
        this.c();
        this.c = null;
        this.e = null;
        this.f = null;
        if (this.a != null) {
            this.a.a(-1);
        }
        this.d = true;
    }

    public void a(a a2) {
        try {
            this.a = a2;
            if (this.f == null) {
                this.f = new ahj(this, a2);
            }
            return;
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
            return;
        }
    }

    boolean b() {
        try {
            this.g.setComponent(new ComponentName(this.h, this.i));
            this.g.putExtra("appkey", this.b);
            boolean bl2 = this.c.bindService(this.g, this.f, 1);
            return bl2;
        }
        catch (Exception var2_2) {
            return false;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    void c() {
        try {
            this.c.unbindService(this.f);
        }
        catch (IllegalArgumentException var1_1) {
            var1_1.printStackTrace();
        }
        catch (Throwable var1_2) {}
        this.e = null;
    }

    ahf d() {
        block5 : {
            if (this.d) break block5;
            return null;
        }
        try {
            Object object = new Bundle();
            object.putString("type", "corse");
            object.putString("appkey", this.b);
            this.e.a((Bundle)object);
            if (object.size() >= 1) {
                object = this.a((Bundle)object);
                return object;
            }
        }
        catch (Throwable var1_2) {
            return null;
        }
        catch (RemoteException var1_3) {
            // empty catch block
        }
        return null;
    }

    public static interface a {
        public void a(int var1);
    }

}

