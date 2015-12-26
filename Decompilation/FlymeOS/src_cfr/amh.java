/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.net.ConnectivityManager
 *  android.net.NetworkInfo
 *  android.net.Uri
 *  android.net.wifi.WifiInfo
 *  android.net.wifi.WifiManager
 *  android.os.SystemClock
 *  android.text.TextUtils
 *  android.util.Base64
 *  android.util.Pair
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  org.json.JSONArray
 *  org.json.JSONObject
 */
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Pair;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class amh {
    private a[] a;
    private a[] b;
    private final long c;
    private final long d;
    private final b e;

    /*
     * Enabled aggressive block sorting
     */
    protected amh(String string2, Context context) {
        int n2;
        int n3;
        JSONArray jSONArray;
        String string3;
        int n4 = 0;
        JSONObject jSONObject = new JSONObject(string2);
        if (jSONObject.has("targets") && (n3 = (jSONArray = jSONObject.getJSONArray("targets")).length()) > 0) {
            this.a = new a[n3];
            for (n2 = 0; n2 < n3; ++n2) {
                string2 = jSONArray.getJSONObject(n2);
                string3 = string2.getString("ip");
                string2 = string2.has("authKey") ? string2.getString("authKey") : null;
                this.a[n2] = new a(string3, string2);
            }
        }
        if (jSONObject.has("baks") && (n3 = (jSONArray = jSONObject.getJSONArray("baks")).length()) > 0) {
            this.b = new a[n3];
            for (n2 = n4; n2 < n3; ++n2) {
                string2 = jSONArray.getJSONObject(n2);
                string3 = string2.getString("ip");
                string2 = string2.has("authKey") ? string2.getString("authKey") : null;
                this.b[n2] = new a(string3, string2);
            }
        }
        this.c = jSONObject.has("expire") ? jSONObject.getLong("expire") : 5;
        this.d = SystemClock.elapsedRealtime();
        this.e = b.b(context);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public ami a(String string2) {
        try {
            Object object;
            String string3 = Uri.parse((String)string2).getAuthority();
            if (TextUtils.isEmpty((CharSequence)string3)) return null;
            if (this.a != null && this.a.length > 0) {
                object = this.a[0];
            } else {
                if (this.b == null) return null;
                if (this.b.length <= 0) return null;
                object = this.b[0];
            }
            if (object == null) return null;
            String string4 = object.a;
            object = object.b;
            int n2 = string2.indexOf(string3);
            if (n2 != -1) {
                string2 = string4 + string2.substring(n2 + string3.length());
                string4 = new ArrayList(2);
                string4.add(new Pair((Object)"Mz_Host", (Object)string3));
                if (TextUtils.isEmpty((CharSequence)object)) return new ami(string2, (List<Pair<String, String>>)((Object)string4));
                string4.add(new Pair((Object)"Authorization", (Object)("Basic " + Base64.encodeToString((byte[])object.getBytes(), (int)2))));
                return new ami(string2, (List<Pair<String, String>>)((Object)string4));
            }
            anf.d("cant re construct url:" + string2);
            return null;
        }
        catch (Exception var1_2) {
            var1_2.printStackTrace();
            return null;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean a(Context context) {
        boolean bl2 = SystemClock.elapsedRealtime() - this.d > this.c * 60000;
        if (!bl2) {
            if (this.e.a(context)) return false;
            return true;
        }
        anf.c("Proxy info time expire!");
        return bl2;
    }

    static class a {
        public final String a;
        public final String b;

        public a(String string2, String string3) {
            this.a = string2;
            this.b = string3;
        }
    }

    static class b {
        private final int a;
        private final String b;

        private b(int n2, String string2) {
            this.a = n2;
            this.b = string2;
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        public static b b(Context object) {
            int n2;
            ConnectivityManager connectivityManager;
            block6 : {
                block5 : {
                    block4 : {
                        try {
                            connectivityManager = (ConnectivityManager)object.getSystemService("connectivity");
                            if (connectivityManager == null || (connectivityManager = connectivityManager.getActiveNetworkInfo()) == null || !connectivityManager.isAvailable()) break block4;
                            n2 = connectivityManager.getType();
                            if (n2 == 1) {
                                if ((object = ((WifiManager)object.getSystemService("wifi")).getConnectionInfo()) == null) break block5;
                                object = object.getSSID();
                                break block6;
                            }
                            if (n2 != 0) break block5;
                            object = anl.k((Context)object);
                            break block6;
                        }
                        catch (Exception var0_1) {
                            anf.c("InstanceCurrent exception!");
                            var0_1.printStackTrace();
                        }
                    }
                    anf.c("InstanceCurrent no network!");
                    return new b(-1, null);
                }
                object = null;
            }
            if (TextUtils.isEmpty((CharSequence)object)) {
                object = connectivityManager.getTypeName();
            }
            anf.c("Current network type:" + n2 + "," + (String)object);
            return new b(n2, (String)object);
        }

        public boolean a(Context object) {
            object = b.b((Context)object);
            if (object.a == -1) {
                anf.c("Check network match while no network");
                return true;
            }
            return this.equals(object);
        }

        /*
         * Enabled aggressive block sorting
         */
        public boolean equals(Object object) {
            boolean bl2 = true;
            boolean bl3 = true;
            if (this == object) {
                return bl3;
            }
            if (object != null && object instanceof b) {
                object = (b)object;
                if (object.a != this.a) {
                    anf.c("Network type change:" + this.a + "->" + object.a);
                    return false;
                }
                if (object.b == null) {
                    if (this.b != null) {
                        bl2 = false;
                    }
                } else {
                    bl2 = object.b.equals((Object)this.b);
                }
                bl3 = bl2;
                if (bl2) return bl3;
                anf.c("Network key change:" + this.b + "->" + object.b);
                return bl2;
            }
            anf.c("Check network match while object is illegal:" + object);
            return false;
        }
    }

}

