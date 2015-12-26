/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  java.lang.Object
 *  java.util.LinkedList
 */
import android.content.Context;
import java.lang.ref.WeakReference;
import java.util.LinkedList;

public class akd {
    private static LinkedList<WeakReference<Context>> a;

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static Context a() {
        synchronized (akd.class) {
            if (a == null) return null;
            if (a.size() <= 0) return null;
            int n2 = a.size() - 1;
            while (n2 >= 0) {
                block5 : {
                    Context context = (Context)((WeakReference)a.get(n2)).get();
                    if (context == null) break block5;
                    return context;
                }
                --n2;
            }
            return null;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static final void a(Context context) {
        synchronized (akd.class) {
            akd.b();
            if (akd.c(context) == -1) {
                a.add(new WeakReference<Context>(context));
                anf.b("add tracker : " + (Object)context);
                do {
                    return;
                    break;
                } while (true);
            }
            anf.b("duplicate tracker : " + (Object)context);
            return;
        }
    }

    private static void b() {
        if (a == null) {
            anf.b("init com list");
            a = new LinkedList();
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static final void b(Context context) {
        synchronized (akd.class) {
            int n2 = akd.c(context);
            if (n2 != -1) {
                a.remove(n2);
                anf.b("rm tracker : " + (Object)context);
            } else {
                anf.b("cant find tracker : " + (Object)context);
            }
            akd.c();
            return;
        }
    }

    private static int c(Context context) {
        if (a != null && a.size() > 0) {
            for (int i2 = akd.a.size() - 1; i2 >= 0; --i2) {
                if ((Context)((WeakReference)a.get(i2)).get() != context) continue;
                return i2;
            }
        }
        return -1;
    }

    private static void c() {
        if (a != null) {
            for (int i2 = akd.a.size() - 1; i2 >= 0; --i2) {
                if ((Context)((WeakReference)a.get(i2)).get() != null) continue;
                anf.b("discard no reference list index:" + i2);
                a.remove(i2);
            }
            if (a.size() == 0) {
                anf.b("discard com list");
                a = null;
            }
        }
    }
}

