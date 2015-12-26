/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentValues
 *  android.content.Context
 *  android.util.Log
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 *  java.util.ArrayList
 *  org.apache.http.Header
 *  org.json.JSONArray
 *  org.json.JSONException
 *  org.json.JSONObject
 */
import android.content.ContentValues;
import android.content.Context;
import android.util.Log;
import com.ted.android.contacts.netparser.model.NumItem;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class aog
extends ath<List<NumItem>> {
    private static final String a = aog.class.getSimpleName();
    private Context b;
    private List<String> c;
    private int d = 0;
    private aod.a e = aod.a.c;
    private aod f;
    private List<ape> g;

    private boolean a(String string2, List<NumItem> object) {
        object = object.iterator();
        do {
            if (object.hasNext()) continue;
            return false;
        } while (!string2.equals((Object)((NumItem)object.next()).a()));
        return true;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private void c(List<NumItem> list) {
        if (this.g == null) {
            return;
        }
        Iterator<ape> iterator = this.g.iterator();
        block0 : while (iterator.hasNext()) {
            ape ape2 = iterator.next();
            Object object = list.iterator();
            boolean bl2 = false;
            do {
                if (!object.hasNext()) {
                    if (bl2) continue block0;
                    object = new NumItem();
                    object.c(ape2.c());
                    object.d(ape2.a());
                    object.a(ape2.b());
                    list.add((NumItem)object);
                    continue block0;
                }
                NumItem numItem = object.next();
                if (ape2.c() == null || !ape2.c().equals((Object)numItem.a())) continue;
                numItem.d(ape2.a());
                numItem.a(ape2.b());
                bl2 = true;
            } while (true);
            break;
        }
        return;
    }

    private void d(List<NumItem> list) {
        Iterator<String> iterator = this.c.iterator();
        while (iterator.hasNext()) {
            String string2 = iterator.next();
            if (this.a(string2, list)) continue;
            Log.i((String)a, (String)("not recognized: " + string2));
            aoh.a(this.b).c(string2);
        }
        return;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    protected List<NumItem> a(String var1_1, boolean var2_3) {
        block13 : {
            var5_4 = null;
            var3_6 = 0;
            if (var2_3) {
                return null;
            }
            var1_1 = apm.b((String)var1_1);
            var6_7 = new ArrayList();
            Log.i((String)aog.a, (String)("NumListHttpResponseHandler parseResponse, size: " + this.c.size()));
            var1_1 = new JSONObject((String)var1_1);
            var4_8 = var1_1.getInt("code");
            if (var4_8 != 0) {
                this.d = var4_8;
            }
            if (!var1_1.has("data") || !((var1_1 = var1_1.get("data")) instanceof JSONArray)) break block13;
            var7_9 = (JSONArray)var1_1;
            var1_1 = var5_4;
            ** while (var3_6 < (var4_8 = var7_9.length()))
lbl-1000: // 1 sources:
            {
                try {
                    var1_1 = var5_4 = var7_9.getJSONObject(var3_6);
                    ** GOTO lbl23
                }
                catch (JSONException var5_5) {
                    try {
                        Log.e((String)aog.a, (String)("JSONObject Convert Error:" + var5_5.getMessage()));
lbl23: // 2 sources:
                        if (var1_1 != null && (var5_4 = apj.a((JSONObject)var1_1)) != null) {
                            if (var3_6 <= this.c.size() && var5_4.a() != null && var5_4.a().equals((Object)apo.a(this.c.get(var3_6)))) {
                                var5_4.c(this.c.get(var3_6));
                            }
                            var6_7.add(var5_4);
                            var8_10 = new JSONObject().put("data", var1_1).toString();
                            var9_11 = new ContentValues();
                            var9_11.put("json", apm.a(var8_10));
                            var9_11.put("m_t", Integer.valueOf((int)var5_4.f()));
                            var9_11.put("c_t", Integer.valueOf((int)((int)(System.currentTimeMillis() / 1000))));
                            var10_12 = var5_4.a();
                            aoh.a(this.b).a(var9_11, "phone = ?", new String[]{var10_12});
                            Log.i((String)aog.a, (String)("recognized: " + var5_4.a() + "json: " + var8_10));
                        }
                        ++var3_6;
                        continue;
                    }
                    catch (JSONException var1_2) {
                        Log.e((String)aog.a, (String)"parser network number json error:", (Throwable)var1_2);
                        break;
                    }
                }
            }
        }
        this.c((List<NumItem>)var6_7);
        if (var6_7.size() > 0) {
            this.e = aod.a.a;
            if (this.f != null) {
                this.f.a(this.e, (List<NumItem>)var6_7);
            }
        } else {
            this.e = aod.a.c;
        }
        this.d((List<NumItem>)var6_7);
        return var6_7;
    }

    @Override
    public void a(int n2, Header[] arrheader, String string2, List<NumItem> list) {
    }

    @Override
    public void a(int n2, Header[] arrheader, Throwable throwable, String string2, List<NumItem> list) {
    }

    public void a(Context context) {
        this.b = context;
    }

    public void a(aod aod2) {
        this.f = aod2;
    }

    public void a(List<String> list) {
        Log.i((String)a, (String)("setReqNumberList: " + list.size()));
        this.c = list;
    }

    @Override
    protected /* synthetic */ Object b(String string2, boolean bl2) {
        return this.a(string2, bl2);
    }

    public void b(List<ape> list) {
        this.g = list;
    }
}

