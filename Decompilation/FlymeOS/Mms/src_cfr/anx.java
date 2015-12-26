/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.net.Uri
 *  android.text.TextUtils
 *  java.lang.Object
 *  java.lang.String
 *  java.net.URLEncoder
 *  java.util.ArrayList
 *  org.apache.http.client.utils.URIUtils
 */
import android.net.Uri;
import android.text.TextUtils;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.http.client.utils.URIUtils;

public class anx {
    private Uri a;
    private String b;
    private String c;
    private int d;
    private String e;
    private String f;
    private String g;
    private List<String> h;

    public anx(String string2) {
        this.a = Uri.parse((String)string2);
        this.b = this.a.getScheme();
        this.c = this.a.getHost();
        this.d = this.a.getPort();
        this.e = this.a.getPath();
        this.f = this.a.getQuery();
        this.g = this.a.getFragment();
        this.h = new ArrayList();
        if (this.f == null) {
            this.f = "";
        }
        String[] arrstring = this.f.split("&");
        int n2 = arrstring.length;
        int n3 = 0;
        while (n3 < n2) {
            String string3 = arrstring[n3];
            if (!TextUtils.isEmpty((CharSequence)string3)) {
                int n4 = string3.indexOf("=");
                string2 = string3;
                if (n4 > -1) {
                    String string4 = string3.substring(n4 + 1);
                    string2 = string3;
                    if (!TextUtils.isEmpty((CharSequence)string4)) {
                        string2 = string3.replace((CharSequence)string4, (CharSequence)URLEncoder.encode((String)string4));
                    }
                }
                this.h.add(string2);
            }
            ++n3;
        }
        return;
    }

    private boolean b(String string2, String arrstring) {
        boolean bl2 = false;
        arrstring = arrstring.split("=");
        boolean bl3 = bl2;
        if (arrstring.length > 0) {
            bl3 = bl2;
            if (string2.equalsIgnoreCase(arrstring[0])) {
                bl3 = true;
            }
        }
        return bl3;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public String a() {
        try {
            this.f = "";
            Iterator<String> iterator = this.h.iterator();
            do {
                if (!iterator.hasNext()) {
                    return URIUtils.createURI((String)this.b, (String)this.c, (int)this.d, (String)this.e, (String)this.f, (String)this.g).toString();
                }
                String string2 = iterator.next();
                if (this.f.length() == 0) {
                    this.f = String.valueOf((Object)this.f) + string2;
                    continue;
                }
                this.f = String.valueOf((Object)this.f) + "&" + string2;
            } while (true);
        }
        catch (Exception var1_2) {
            var1_2.printStackTrace();
            return null;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void a(String string2, String string3) {
        int n2 = 0;
        string3 = String.valueOf((Object)string2) + "=" + string3;
        int n3 = 0;
        do {
            if (n3 >= this.h.size()) {
                n3 = n2;
                if (n3 != 0) return;
                this.h.add(string3);
                return;
            }
            if (this.b(string2, this.h.get(n3))) {
                this.h.set(n3, string3);
                return;
            }
            ++n3;
        } while (true);
    }

    public boolean a(String string2) {
        if (this.b(string2) != null) {
            return true;
        }
        return false;
    }

    public String b(String string2) {
        String string3;
        Iterator<String> iterator = this.h.iterator();
        do {
            if (iterator.hasNext()) continue;
            return null;
        } while (!this.b(string2, string3 = iterator.next()));
        return string3;
    }

    public boolean c(String string2) {
        if ((string2 = this.b(string2)) != null && string2.contains((CharSequence)"=") && !string2.endsWith("=")) {
            return true;
        }
        return false;
    }
}

