/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.text.TextUtils
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 */
import android.content.Context;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

public class amk
implements amn {
    private final int a;
    private boolean b = false;
    private int c = -1;
    private List<String> d;
    private int e = 0;
    private boolean f = false;
    private int g = 0;

    public amk(int n2) {
        this.a = n2;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public ami a(Context object, String string2) {
        if (!this.b) {
            if ((object = new amg().a((Context)object)) != null) {
                this.g = 0;
                this.b = true;
                ami ami2 = object.a(string2);
                if (ami2 != null) {
                    anf.d("Transform url success: " + ami2.a);
                    return ami2;
                }
                anf.d("Cant transform url: " + string2 + ", proxy: " + object);
                do {
                    return null;
                    break;
                } while (true);
            }
            anf.d("Get relocate ip failed!");
            return null;
        }
        anf.d("Relocate had used before!");
        return null;
    }

    @Override
    public void a() {
        ++this.c;
        if (this.f) {
            int n2;
            this.f = false;
            this.g = n2 = this.g + 1;
            if (n2 <= 10) {
                anf.c("Reduce download time while relocate 302: " + this.g);
                --this.c;
            }
        }
        anf.c("start download time: " + (this.c + 1));
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void a(String string2) {
        if (TextUtils.isEmpty((CharSequence)string2)) {
            this.d = null;
            return;
        }
        if (this.d == null) {
            this.d = new ArrayList(1);
        } else {
            this.d.clear();
        }
        this.d.add(string2);
        this.e = 0;
    }

    @Override
    public ami b(Context context, String string2) {
        return null;
    }

    @Override
    public boolean b() {
        if (this.c < this.a) {
            return true;
        }
        return false;
    }

    @Override
    public String c() {
        if (this.d != null && this.d.size() > this.e) {
            List<String> list = this.d;
            int n2 = this.e;
            this.e = n2 + 1;
            return list.get(n2);
        }
        return null;
    }

    @Override
    public void d() {
        if (this.b) {
            this.b = false;
        }
        amg.a();
    }

    @Override
    public void e() {
        this.f = true;
    }
}

