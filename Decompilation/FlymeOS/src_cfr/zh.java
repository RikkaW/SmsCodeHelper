/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.net.Uri
 *  android.os.Handler
 *  android.util.Log
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Thread
 *  java.util.ArrayList
 *  java.util.HashMap
 *  java.util.HashSet
 *  java.util.concurrent.LinkedBlockingQueue
 *  java.util.concurrent.ThreadPoolExecutor
 *  java.util.concurrent.TimeUnit
 *  java.util.concurrent.atomic.AtomicInteger
 */
import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

abstract class zh {
    protected final Set<Uri> a = new HashSet();
    protected final HashMap<Uri, Set<zy>> b = new HashMap();
    protected final Executor c;
    protected final Handler d;

    zh(Context context) {
        context = new LinkedBlockingQueue();
        this.c = new ThreadPoolExecutor(2, 2, 5, TimeUnit.SECONDS, (BlockingQueue)context, (ThreadFactory)new a(this.c()));
        this.d = new Handler();
    }

    protected static <T> ArrayList<T> a(Set<T> set) {
        return new ArrayList(set);
    }

    public void a() {
        this.b();
    }

    public void a(zy zy2) {
        if (Log.isLoggable((String)"BackgroundLoaderManager", (int)3)) {
            Log.d((String)"BackgroundLoaderManager", (String)("Cancelling image callback " + zy2));
        }
        for (Uri uri : this.b.keySet()) {
            ((Set)this.b.get((Object)uri)).remove(zy2);
        }
    }

    public boolean a(Uri uri, zy zy2) {
        Set set;
        if (Log.isLoggable((String)"BackgroundLoaderManager", (int)3)) {
            Log.d((String)"BackgroundLoaderManager", (String)("Adding image callback " + zy2));
        }
        if (uri == null) {
            throw new NullPointerException("uri is null");
        }
        if (zy2 == null) {
            throw new NullPointerException("callback is null");
        }
        Set set2 = set = (Set)this.b.get((Object)uri);
        if (set == null) {
            set2 = new HashSet(4);
            this.b.put((Object)uri, (Object)set2);
        }
        set2.add(zy2);
        return true;
    }

    public void b() {
    }

    public abstract String c();

    static class a
    implements ThreadFactory {
        private final AtomicInteger a = new AtomicInteger(1);
        private final String b;

        public a(String string2) {
            this.b = string2;
        }

        @Override
        public Thread newThread(Runnable runnable) {
            if ((runnable = new Thread(runnable, this.b + "-" + this.a.getAndIncrement())).getPriority() != 1) {
                runnable.setPriority(1);
            }
            return runnable;
        }
    }

}

