/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
import java.lang.ref.WeakReference;

public class aus {
    private final WeakReference<asi> a;

    public aus(asi asi2) {
        this.a = new WeakReference<asi>(asi2);
    }

    public boolean a() {
        asi asi2 = this.a.get();
        if (asi2 != null && !asi2.b()) {
            return false;
        }
        return true;
    }

    public boolean b() {
        asi asi2 = this.a.get();
        if (asi2 != null && !asi2.a()) {
            return false;
        }
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean c() {
        if (!this.b() && !this.a()) {
            return false;
        }
        boolean bl2 = true;
        if (!bl2) return bl2;
        this.a.clear();
        return bl2;
    }
}

