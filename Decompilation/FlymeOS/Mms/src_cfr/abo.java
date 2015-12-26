/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.graphics.Bitmap
 *  android.util.Log
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.HashMap
 */
import android.graphics.Bitmap;
import android.util.Log;
import java.util.HashMap;
import java.util.Set;

class abo
implements Runnable {
    final /* synthetic */ Bitmap a;
    final /* synthetic */ abm.b b;

    abo(abm.b b2, Bitmap bitmap) {
        this.b = b2;
        this.a = bitmap;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void run() {
        Set set = (Set)this.b.abm.this.b.get((Object)abm.b.a(this.b));
        if (set != null) {
            abm.a(this.b.abm.this);
            Bitmap bitmap = this.a == null ? (abm.b.b(this.b) ? abm.e() : (abm.b.c(this.b) ? abm.f() : abm.g())) : this.a;
            for (zy zy2 : zh.a(set)) {
                if (Log.isLoggable((String)"Mms:thumbnailcache", (int)3)) {
                    Log.d((String)"ThumbnailManager", (String)("Invoking item loaded callback " + zy2));
                }
                zy2.a(new abm.a(bitmap, abm.b.b(this.b)), null);
            }
        } else if (Log.isLoggable((String)"ThumbnailManager", (int)3)) {
            Log.d((String)"ThumbnailManager", (String)"No image callback!");
        }
        if (this.a != null) {
            abm.b(this.b.abm.this).a(abm.b.a(this.b), this.a);
            if (Log.isLoggable((String)"Mms:thumbnailcache", (int)3)) {
                Log.v((String)"ThumbnailManager", (String)("in callback runnable: bitmap uri: " + (Object)abm.b.a(this.b) + " width: " + this.a.getWidth() + " height: " + this.a.getHeight() + " size: " + this.a.getByteCount()));
            }
        }
        this.b.abm.this.b.remove((Object)abm.b.a(this.b));
        this.b.abm.this.a.remove((Object)abm.b.a(this.b));
        if (Log.isLoggable((String)"Mms:thumbnailcache", (int)3)) {
            Log.d((String)"ThumbnailManager", (String)("Image task for " + (Object)abm.b.a(this.b) + "exiting " + this.b.abm.this.a.size() + " remain"));
        }
    }
}

