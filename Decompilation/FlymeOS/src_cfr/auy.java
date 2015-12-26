/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  java.io.File
 *  java.lang.Object
 *  java.lang.String
 */
import android.content.Context;
import com.ted.android.contacts.common.util.FileUtil;
import java.io.File;
import java.util.List;

public abstract class auy {
    private static final boolean a = aux.a;
    private Context b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;
    private String m;

    public auy(Context context) {
        this.b = context.getApplicationContext();
    }

    public abstract void a(be var1);

    public void a(String string2) {
        this.l = string2;
    }

    public abstract void a(List<be> var1);

    public abstract boolean a();

    public abstract boolean b();

    public void c() {
        this.c = this.b.getFilesDir().getAbsolutePath();
        this.d = this.c.concat("/u1");
        this.f = this.d.concat("/files");
        this.e = this.d.concat("/temp");
        this.k = this.d.concat("/upload");
        FileUtil.ensurePathExists(this.d);
        FileUtil.ensurePathExists(this.f);
        FileUtil.ensurePathExists(this.e);
        FileUtil.ensurePathExists(this.k);
        this.g = this.c.concat("/u.md5");
        this.h = this.f.concat("/u.md5");
        this.i = this.c.concat("/u.json");
        this.j = this.f.concat("/u.json");
        this.m = this.f.concat("/w.json");
        af.a = new b(this.b);
        if (this.l == null || this.l.length() == 0) {
            throw new RuntimeException("\u66f4\u65b0\u7684url\u4e0d\u80fd\u4e3a\u7a7a\uff01\u5b9e\u73b0\u7c7b\u5fc5\u987b\u8bbe\u7f6e\u4e0b\u8f7durl");
        }
    }

    public Context d() {
        return this.b;
    }

    public String e() {
        return this.f;
    }

    public String f() {
        return this.g;
    }

    protected void finalize() {
        super.finalize();
    }

    public String g() {
        return this.i;
    }

    public String h() {
        return this.j;
    }

    public String i() {
        return this.h;
    }

    public String j() {
        return this.l;
    }

    public String k() {
        return this.k;
    }

    public String l() {
        return this.m;
    }
}

