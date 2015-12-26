/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.Log
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  org.apache.http.Header
 *  org.json.JSONException
 *  org.json.JSONObject
 */
import android.content.Context;
import android.util.Log;
import com.ted.android.contacts.netparser.model.NumItem;
import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

public class aof
extends ath<NumItem> {
    private static final String a = aof.class.getSimpleName();
    private aod b;
    private Context c;
    private String d;
    private aod.a e = aod.a.c;
    private int f = 0;
    private ape g;

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected NumItem a(String object, boolean bl2) {
        Object object2;
        int n2;
        block13 : {
            block12 : {
                object2 = apm.b((String)object);
                Log.i((String)a, (String)(String.valueOf((Object)this.d) + " json: " + (String)object2));
                n2 = 0;
                try {
                    object2 = new JSONObject((String)object2);
                    int n3 = object2.getInt("code");
                    if (n3 != 0) {
                        this.f = n3;
                    }
                    if (!object2.has("data") || (object2 = object2.get("data")) == null || !(object2 instanceof JSONObject)) break block12;
                    if ((object2 = (JSONObject)object2).has("status") && object2.getInt("status") == -1) {
                        n2 = n3 = object2.getInt("status");
                        object2 = null;
                    } else {
                        object2 = apj.a((JSONObject)object2);
                    }
                    break block13;
                }
                catch (JSONException var5_4) {
                    Log.e((String)a, (String)"parser network number json error:", (Throwable)var5_4);
                }
            }
            object2 = null;
        }
        if (object2 != null) {
            this.e = aod.a.a;
            object2.c(this.d);
            aoh.a(this.c).a(this.d, (String)object, object2.f());
        } else {
            this.e = aod.a.c;
            if (n2 == -1) {
                aoh.a(this.c).d(this.d);
            } else {
                aoh.a(this.c).c(this.d);
            }
        }
        object = object2;
        if (this.g != null) {
            object = object2;
            if (object2 == null) {
                object = new NumItem();
                object.c(this.d);
            }
            object.d(this.g.a());
            object.a(this.g.b());
        }
        return object;
    }

    @Override
    public void a(int n2, Header[] arrheader, String string2, NumItem numItem) {
        if (this.b != null) {
            this.b.a(this.e, numItem);
        }
    }

    @Override
    public void a(int n2, Header[] arrheader, Throwable throwable, String string2, NumItem numItem) {
        if (this.b != null) {
            this.b.a(throwable);
        }
    }

    public void a(Context context) {
        this.c = context.getApplicationContext();
    }

    public void a(aod aod2) {
        this.b = aod2;
    }

    public void a(ape ape2) {
        this.g = ape2;
    }

    public void a(String string2) {
        this.d = string2;
    }

    @Override
    protected /* synthetic */ Object b(String string2, boolean bl2) {
        return this.a(string2, bl2);
    }
}

