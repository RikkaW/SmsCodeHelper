/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.graphics.Bitmap
 *  android.graphics.drawable.BitmapDrawable
 *  java.lang.Object
 *  java.lang.Runtime
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.Collections
 *  java.util.Map$Entry
 */
package cn.com.xy.sms.sdk.util;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public final class m {
    private static final String a = "MemoryCache";
    private Map<String, BitmapDrawable> b = Collections.synchronizedMap(new LinkedHashMap(10, 1.5f, true));
    private long c = 0;
    private long d = Runtime.getRuntime().maxMemory() / 10;

    public m() {
        new StringBuilder("MemoryCache will use up to ").append((double)this.d / 1024.0 / 1024.0).append("MB");
    }

    /*
     * Enabled aggressive block sorting
     */
    private static long a(BitmapDrawable bitmapDrawable) {
        if (bitmapDrawable == null || (bitmapDrawable = bitmapDrawable.getBitmap()) == null) {
            return 0;
        }
        return bitmapDrawable.getRowBytes() * bitmapDrawable.getHeight();
    }

    private BitmapDrawable a(String string2) {
        block3 : {
            try {
                if (this.b.containsKey(string2)) break block3;
                return null;
            }
            catch (NullPointerException var1_2) {
                return null;
            }
        }
        string2 = this.b.get(string2);
        return string2;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a() {
        new StringBuilder("cache size=").append(this.c).append(" length=").append(this.b.size());
        if (this.c > this.d) {
            Iterator<Map.Entry<String, BitmapDrawable>> iterator = this.b.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, BitmapDrawable> entry = iterator.next();
                this.c -= m.a((BitmapDrawable)entry.getValue());
                iterator.remove();
                if (this.c > this.d) continue;
            }
            new StringBuilder("Clean cache. New size ").append(this.b.size());
        }
    }

    private void a(long l2) {
        this.d = l2;
        new StringBuilder("MemoryCache will use up to ").append((double)this.d / 1024.0 / 1024.0).append("MB");
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void a(String iterator, BitmapDrawable entry) {
        try {
            if (this.b.containsKey(iterator)) {
                this.c -= m.a(this.b.get(iterator));
            }
            this.b.put((String)((Object)iterator), (BitmapDrawable)entry);
            this.c += m.a(entry);
            new StringBuilder("cache size=").append(this.c).append(" length=").append(this.b.size());
            if (this.c <= this.d) return;
            {
                iterator = this.b.entrySet().iterator();
                while (iterator.hasNext()) {
                    entry = iterator.next();
                    this.c -= m.a((BitmapDrawable)entry.getValue());
                    iterator.remove();
                    long l2 = this.c;
                    long l3 = this.d;
                    if (l2 > l3) continue;
                }
                new StringBuilder("Clean cache. New size ").append(this.b.size());
                return;
            }
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
        }
    }

    private void b() {
        this.b.clear();
    }
}

