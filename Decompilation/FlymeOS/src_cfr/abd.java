/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  org.json.JSONObject
 */
import org.json.JSONObject;

public class abd {
    private String a;
    private int b;
    private boolean c;

    public abd(JSONObject jSONObject) {
        this.a = jSONObject.getString("addr");
        this.b = jSONObject.getInt("ability");
        this.c = jSONObject.getString("st").equals((Object)"ONLINE");
    }

    public String a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public boolean c() {
        return this.c;
    }
}

