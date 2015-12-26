/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.AssetManager
 *  android.text.TextUtils
 *  android.util.Log
 *  java.io.BufferedReader
 *  java.io.File
 *  java.io.InputStreamReader
 *  java.io.Reader
 *  java.lang.Object
 *  java.lang.String
 *  java.util.HashMap
 */
import android.content.Context;
import android.content.res.AssetManager;
import android.text.TextUtils;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class ask {
    private static final String a = ask.class.getSimpleName();
    private static ask b;
    private static Map<String, String> c;
    private Context d;

    static {
        c = new HashMap();
    }

    private ask(Context context) {
        this.d = context;
        this.a();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static ask a(Context context) {
        if (b == null) {
            synchronized (ask.class) {
                b = new ask(context);
            }
        }
        return b;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void a() {
        try {
            InputStream inputStream = this.d.getAssets().open("md5.txt");
            if (inputStream == null) return;
            {
                inputStream = new BufferedReader((Reader)new InputStreamReader(inputStream));
                do {
                    String[] arrstring;
                    if ((arrstring = inputStream.readLine()) == null) {
                        return;
                    }
                    if ((arrstring = arrstring.split("\t")).length < 2) continue;
                    c.put(arrstring[0], arrstring[1]);
                } while (true);
            }
        }
        catch (IOException var1_2) {
            var1_2.printStackTrace();
            Log.e((String)a, (String)"md5\u6587\u4ef6\u4e0d\u5b58\u5728!");
        }
    }

    public int a(String string2) {
        Object object = new File(string2);
        if (!object.exists()) {
            return 1;
        }
        object = object.getName();
        string2 = anv.a(string2);
        if (TextUtils.isEmpty((CharSequence)(object = c.get(object)))) {
            return 2;
        }
        if (TextUtils.equals((CharSequence)string2, (CharSequence)object)) {
            return 0;
        }
        return 3;
    }
}

