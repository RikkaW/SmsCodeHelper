/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.database.ContentObserver
 *  android.net.Uri
 *  android.os.Handler
 *  java.lang.Object
 *  java.lang.String
 */
import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import com.ted.sdk.yellow.provider.YellowPageProvider;
import com.ted.sdk.yellow.util.SoftRefArray;

public class asl {
    private static final String a = asl.class.getSimpleName();
    private static final Uri b = Uri.withAppendedPath((Uri)YellowPageProvider.a, (String)"marker");
    private static final Uri c = Uri.withAppendedPath((Uri)YellowPageProvider.a, (String)"corrector");
    private static asl g = null;
    private static Context h;
    private SoftRefArray<b> d = new SoftRefArray();
    private c e;
    private a f;
    private Handler i = new Handler();

    public asl(Context context) {
        h = context;
        this.e = new c(this.i);
        this.f = new a(this.i);
        h.getContentResolver().registerContentObserver(b, true, (ContentObserver)this.e);
        h.getContentResolver().registerContentObserver(c, true, (ContentObserver)this.f);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static asl a(Context context) {
        if (g == null && context != null) {
            synchronized (asl.class) {
                if (g == null) {
                    g = new asl(context.getApplicationContext());
                }
            }
        }
        return g;
    }

    public static void b() {
        if (g != null) {
            g.c();
            g = null;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void c() {
        synchronized (asl.class) {
            try {
                h.getContentResolver().unregisterContentObserver((ContentObserver)this.e);
                h.getContentResolver().unregisterContentObserver((ContentObserver)this.f);
            }
            catch (Exception var1_1) {
                var1_1.getMessage();
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected void a() {
        asr.a().b();
        int n2 = this.d.size();
        int n3 = 0;
        while (n3 < n2) {
            b b2 = this.d.get(n3);
            if (b2 != null) {
                try {
                    b2.a();
                }
                catch (Exception var3_4) {
                    var3_4.printStackTrace();
                }
            }
            ++n3;
        }
        return;
    }

    class a
    extends ContentObserver {
        public a(Handler handler) {
            super(handler);
        }

        public void onChange(boolean bl2) {
            asl.this.a();
        }
    }

    public static interface b {
        public void a();
    }

    class c
    extends ContentObserver {
        public c(Handler handler) {
            super(handler);
        }

        public void onChange(boolean bl2) {
            asl.this.a();
        }
    }

}

