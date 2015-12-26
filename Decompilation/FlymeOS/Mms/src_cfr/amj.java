/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.pm.PackageInfo
 *  android.text.TextUtils
 *  java.lang.Object
 *  java.lang.String
 */
import android.content.Context;
import android.content.pm.PackageInfo;
import android.text.TextUtils;

public class amj
implements amm {
    private Context a;
    private int b;
    private String c;
    private long d;
    private String e;
    private int f;
    private boolean g = true;
    private boolean h = false;

    public amj(Context context, int n2, String string2, long l2, String string3, int n3) {
        this.a = context;
        this.b = n2;
        this.c = string2;
        this.d = l2;
        this.e = string3;
        this.f = n3;
        this.b("Checker limit:" + this.toString());
    }

    private boolean a(int n2) {
        if ((this.b & n2) > 0) {
            return true;
        }
        return false;
    }

    private void b(String string2) {
        anf.d(string2);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public aml a(long l2, long l3) {
        boolean bl2 = true;
        if (!this.g) return aml.a();
        boolean bl3 = l3 <= 0;
        this.h = bl3;
        if (this.d > 0 && !this.h && this.a(1)) {
            if (l2 + l3 != this.d) {
                bl2 = false;
            }
            if (!bl2) {
                String string2 = "File length not match(" + this.d + "->" + (l2 + l3) + ")";
                this.b(string2);
                return aml.a(string2);
            }
        }
        return aml.a();
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public aml a(String string2) {
        boolean bl2;
        Object object;
        boolean bl3 = true;
        if (!this.g) return aml.a();
        if (!TextUtils.isEmpty((CharSequence)this.c) && this.a(8)) {
            object = anl.d(this.a, string2);
            if (object == null) {
                string2 = "File cant parse to package info(" + this.c + "->" + this.f + ")";
                this.b(string2);
                return aml.a(string2);
            }
            if (!this.c.equalsIgnoreCase(object.packageName)) {
                string2 = "Package name not match(" + this.c + "->" + object.packageName + ")";
                this.b(string2);
                return aml.a(string2);
            }
            if (this.f > 0 && this.a(16)) {
                bl2 = this.f == object.versionCode;
                if (!bl2) {
                    string2 = "Package version code not match(" + this.f + "->" + object.versionCode + ")";
                    this.b(string2);
                    return aml.a(string2);
                }
            }
            bl2 = true;
        } else {
            bl2 = false;
        }
        boolean bl4 = bl2;
        if (TextUtils.isEmpty((CharSequence)this.e)) return aml.a();
        if (this.a(2)) {
            object = anh.a(string2);
            if (this.e.equalsIgnoreCase((String)object)) return aml.a();
            {
                string2 = "Whole md5 not match(" + this.e + "->" + (String)object + ")";
                this.b(string2);
                return aml.a(string2);
            }
        }
        bl4 = bl2;
        if (this.a(4)) {
            object = anh.a(string2, 1048576);
            if (this.e.equalsIgnoreCase((String)object)) return aml.a();
            {
                string2 = "HeadTail md5 not match(" + this.e + "->" + (String)object + ")";
                this.b(string2);
                return aml.a(string2);
            }
        } else {
            if (bl4 || this.d <= 0 || !this.h || !this.a(1)) return aml.a();
            {
                this.h = false;
                long l2 = anl.a(string2);
                if (l2 <= 0) return aml.a();
                {
                    bl2 = l2 == this.d ? bl3 : false;
                    if (bl2) return aml.a();
                    {
                        string2 = "Download File length not match(" + this.d + "->" + l2 + ")";
                        this.b(string2);
                        return aml.a(string2);
                    }
                }
            }
        }
    }

    @Override
    public String a() {
        if (!TextUtils.isEmpty((CharSequence)this.e) && this.a(2)) {
            return this.e;
        }
        return null;
    }

    @Override
    public void a(boolean bl2) {
        this.g = bl2;
    }

    @Override
    public String b() {
        if (!TextUtils.isEmpty((CharSequence)this.e) && this.a(4)) {
            return this.e;
        }
        return null;
    }

    @Override
    public long c() {
        long l2;
        long l3 = l2 = 0;
        if (this.d > 0) {
            l3 = l2;
            if (this.a(1)) {
                l3 = this.d;
            }
        }
        return l3;
    }

    @Override
    public String d() {
        if (this.f > 0) {
            return String.valueOf((int)this.f);
        }
        return null;
    }

    public String toString() {
        String string2 = "";
        if (this.a(1)) {
            string2 = "" + "size ";
        }
        String string3 = string2;
        if (this.a(4)) {
            string3 = string2 + "1mmd5 ";
        }
        string2 = string3;
        if (this.a(8)) {
            string2 = string3 + "pkg ";
        }
        string3 = string2;
        if (this.a(16)) {
            string3 = string2 + "vcode ";
        }
        string2 = string3;
        if (this.a(2)) {
            string2 = string3 + "md5 ";
        }
        string3 = string2;
        if (TextUtils.isEmpty((CharSequence)string2)) {
            string3 = "null";
        }
        return "verify_mode=" + string3 + ",pk=" + this.c + ",size=" + this.d + ",md5=" + this.e + ",v_code=" + this.f;
    }
}

