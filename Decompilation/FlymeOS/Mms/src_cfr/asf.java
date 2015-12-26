/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.text.TextUtils
 *  java.io.BufferedReader
 *  java.io.FileNotFoundException
 *  java.io.InputStreamReader
 *  java.io.Reader
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Thread
 *  java.util.ArrayList
 *  java.util.Collections
 *  java.util.HashMap
 *  org.json.JSONArray
 *  org.json.JSONException
 *  org.json.JSONObject
 */
import android.content.Context;
import android.text.TextUtils;
import com.ted.android.contacts.common.util.NovoFileUtil;
import com.ted.android.utils.TedSDKLog;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class asf {
    public static asf a;
    private static final String b;
    private Map<String, Integer> c = Collections.synchronizedMap((Map)new HashMap());
    private Context d;
    private int e;
    private boolean f = false;
    private boolean g;
    private boolean h = false;
    private String i;

    static {
        b = asf.class.getSimpleName();
    }

    private asf() {
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static asf a() {
        if (a == null) {
            synchronized (asf.class) {
                a = new asf();
            }
        }
        return a;
    }

    static /* synthetic */ void a(asf asf2) {
        asf2.c();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void b(String iterator) {
        TedSDKLog.begin(b);
        try {
            iterator = new JSONObject(iterator);
            Object object = iterator.getString("service");
            this.e = iterator.getInt("version");
            if (iterator.has("recharge") && "oppo".equals((Object)iterator.getString("recharge"))) {
                this.f = true;
            }
            if ((iterator = a.b((String)object)) != null && iterator.size() > 0) {
                this.c.clear();
                iterator = iterator.iterator();
                while (iterator.hasNext()) {
                    object = (a)iterator.next();
                    this.c.put(object.a, object.b);
                }
            }
        }
        catch (JSONException var1_2) {
            var1_2.printStackTrace();
            return;
        }
        TedSDKLog.d(b, "parseConfig: " + this.c);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void c() {
        block7 : {
            try {
                InputStream inputStream = NovoFileUtil.openLatestInputFile(this.d, "sms.cfg");
                if (inputStream == null) break block7;
                inputStream = new BufferedReader((Reader)new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                do {
                    String string2;
                    if ((string2 = inputStream.readLine()) == null) {
                        this.g = true;
                        this.i = stringBuilder.toString();
                        inputStream.close();
                        break;
                    }
                    stringBuilder.append(string2);
                } while (true);
            }
            catch (FileNotFoundException var1_2) {
                this.g = false;
            }
            catch (IOException var1_3) {
                var1_3.printStackTrace();
                this.g = false;
            }
        }
        if (!TextUtils.isEmpty((CharSequence)this.i)) {
            this.b(this.i);
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public int a(String string2) {
        if (!this.h) {
            throw new IllegalStateException("initialise first");
        }
        if (this.c.containsKey(string2)) {
            return this.c.get(string2);
        }
        int n2 = Integer.parseInt((String)string2);
        if (n2 <= 100000) return 0;
        try {
            n2 = n2 / 100000 * 100000;
            if (!this.c.containsKey(String.valueOf((int)n2))) return 0;
            return this.c.get(String.valueOf((int)n2));
        }
        catch (NumberFormatException var3_3) {
            var3_3.printStackTrace();
            TedSDKLog.e(b, String.valueOf((Object)string2) + " is not a number");
        }
        return 0;
    }

    public void a(Context context) {
        this.d = context.getApplicationContext();
        new Thread((Runnable)new asg(this)).start();
        this.h = true;
    }

    public boolean b() {
        return this.g;
    }

    static class a {
        String a;
        int b;

        a() {
        }

        public static a a(String string2) {
            a a2;
            block3 : {
                Object object;
                try {
                    string2 = new JSONObject(string2);
                    a2 = new a();
                    object = string2.keys();
                    if (object == null) break block3;
                }
                catch (JSONException var0_1) {
                    var0_1.printStackTrace();
                    return null;
                }
                if (!object.hasNext()) break block3;
                object = (String)object.next();
                int n2 = string2.getInt((String)object);
                a2.a = object;
                a2.b = n2;
            }
            return a2;
        }

        public static List<a> b(String string2) {
            ArrayList arrayList;
            block7 : {
                int n2;
                arrayList = new ArrayList();
                string2 = new JSONArray(string2);
                if (string2 == null) break block7;
                try {
                    if (string2.length() <= 1) break block7;
                    n2 = 0;
                }
                catch (JSONException var0_1) {
                    var0_1.printStackTrace();
                }
                do {
                    block9 : {
                        block8 : {
                            if (n2 < string2.length()) break block8;
                            return arrayList;
                        }
                        a a2 = a.a(string2.getString(n2));
                        if (a2 == null) break block9;
                        arrayList.add(a2);
                    }
                    ++n2;
                } while (true);
            }
            return arrayList;
        }
    }

}

