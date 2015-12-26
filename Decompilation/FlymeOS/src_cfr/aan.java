/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.net.Uri
 *  android.os.Handler
 *  android.util.Log
 *  com.google.android.mms.MmsException
 *  com.meizu.android.mms.pdu.MzGenericPdu
 *  com.meizu.android.mms.pdu.MzPduPersister
 *  com.meizu.android.mms.util.MzPduCache
 *  com.meizu.android.mms.util.MzPduCacheEntry
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import com.google.android.mms.MmsException;
import com.meizu.android.mms.pdu.MzGenericPdu;
import com.meizu.android.mms.pdu.MzPduPersister;
import com.meizu.android.mms.util.MzPduCache;
import com.meizu.android.mms.util.MzPduCacheEntry;
import java.util.Set;
import java.util.concurrent.Executor;

public class aan
extends zh {
    private static MzPduCache e;
    private final MzPduPersister f;
    private final aay<Uri, lr> g = new aay(8, 16, 0.75f, true);
    private final Context h;

    public aan(Context context) {
        super(context);
        e = MzPduCache.getInstance();
        this.f = MzPduPersister.getPduPersister((Context)context);
        this.h = context;
    }

    static /* synthetic */ aay c(aan aan2) {
        return aan2.g;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public zz a(Uri object, boolean bl2, zy<a> zy2) {
        MzPduCacheEntry mzPduCacheEntry;
        boolean bl3 = true;
        if (object == null) {
            throw new NullPointerException();
        }
        Object object2 = e;
        synchronized (object2) {
            mzPduCacheEntry = !e.isUpdating((Uri)object) ? (MzPduCacheEntry)e.get(object) : null;
        }
        object2 = bl2 ? this.g.a(object) : null;
        boolean bl4 = !bl2 || object2 != null;
        boolean bl5 = mzPduCacheEntry != null && mzPduCacheEntry.getPdu() != null;
        boolean bl6 = this.a.contains(object);
        boolean bl7 = (!bl5 || !bl4) && !bl6;
        if (zy2 == null) {
            bl3 = false;
        }
        if (bl5) {
            if (bl4) {
                if (bl3) {
                    zy2.a(new a(mzPduCacheEntry.getPdu(), (lr)object2), null);
                }
                return new aam();
            }
            if (bl3) {
                this.a((Uri)object, zy2);
            }
        }
        if (bl7) {
            this.a.add(object);
            object = new b((Uri)object, bl2);
            this.c.execute((Runnable)object);
        }
        return new aao(this, zy2);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void a(Uri uri) {
        if (Log.isLoggable((String)"Mms:PduLoaderManager", (int)3)) {
            Log.d((String)"Mms:PduLoaderManager", (String)("removePdu: " + (Object)uri));
        }
        if (uri != null) {
            MzPduCache mzPduCache = e;
            synchronized (mzPduCache) {
                e.purge(uri);
            }
            this.g.b(uri);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void b() {
        super.b();
        MzPduCache mzPduCache = e;
        synchronized (mzPduCache) {
            e.purgeAll();
        }
        this.g.a();
    }

    @Override
    public String c() {
        return "Mms:PduLoaderManager";
    }

    public static class a {
        public final MzGenericPdu a;
        public final lr b;

        public a(MzGenericPdu mzGenericPdu, lr lr2) {
            this.a = mzGenericPdu;
            this.b = lr2;
        }
    }

    public class b
    implements Runnable {
        private final Uri b;
        private final boolean c;

        public b(Uri uri, boolean bl2) {
            if (uri == null) {
                throw new NullPointerException();
            }
            this.b = uri;
            this.c = bl2;
        }

        static /* synthetic */ Uri a(b b2) {
            return b2.b;
        }

        /*
         * Loose catch block
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         * Lifted jumps to return sites
         */
        @Override
        public void run() {
            Object object;
            MzPduCacheEntry mzPduCacheEntry;
            MzGenericPdu mzGenericPdu;
            block5 : {
                block4 : {
                    mzPduCacheEntry = null;
                    try {
                        object = aan.this.f.load(this.b, true);
                        mzGenericPdu = object.getPdu();
                        if (mzGenericPdu == null) break block4;
                    }
                    catch (MmsException var2_3) {
                        mzGenericPdu = null;
                        Log.e((String)"Mms:PduLoaderManager", (String)("MmsException loading uri: " + (Object)this.b), (Throwable)object);
                        Object var4_6 = null;
                        mzPduCacheEntry = object;
                        object = var4_6;
                        break block5;
                    }
                    if (!this.c) break block4;
                    object = lr.a(aan.this.h, (MzPduCacheEntry)object);
                    break block5;
                    {
                        catch (MmsException mmsException) {}
                    }
                }
                object = null;
            }
            aan.this.d.post((Runnable)new aap(this, mzGenericPdu, (lr)object, (Throwable)mzPduCacheEntry));
        }
    }

}

