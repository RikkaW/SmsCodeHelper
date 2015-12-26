/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.Bitmap
 *  android.graphics.Bitmap$CompressFormat
 *  android.graphics.Bitmap$Config
 *  android.graphics.BitmapFactory
 *  android.graphics.BitmapFactory$Options
 *  android.graphics.Canvas
 *  android.graphics.Paint
 *  android.graphics.Rect
 *  android.media.MediaMetadataRetriever
 *  android.net.Uri
 *  android.os.Handler
 *  android.util.Log
 *  java.io.ByteArrayOutputStream
 *  java.io.FileNotFoundException
 *  java.io.OutputStream
 *  java.lang.Integer
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import com.android.mms.MmsApp;
import com.android.mms.TempFileProvider;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.concurrent.Executor;

public class abm
extends zh {
    public static int e;
    private static Bitmap i;
    private static Bitmap j;
    private static Bitmap k;
    private final aay<Uri, Bitmap> f = new aay(8, 16, 0.75f, true);
    private final Context g;
    private zx h;

    public abm(Context context) {
        super(context);
        this.g = context;
        i = BitmapFactory.decodeResource((Resources)context.getResources(), (int)2130838200);
        j = BitmapFactory.decodeResource((Resources)context.getResources(), (int)2130838190);
        k = BitmapFactory.decodeResource((Resources)context.getResources(), (int)2130837719);
        e = context.getResources().getDimensionPixelSize(2131559301);
    }

    /*
     * Enabled aggressive block sorting
     */
    private zz a(Uri object, boolean bl2, zy<a> zy2, boolean bl3) {
        boolean bl4 = true;
        if (object == null) {
            throw new NullPointerException();
        }
        Bitmap bitmap = this.f.a(object);
        boolean bl5 = bitmap != null;
        boolean bl6 = this.a.contains(object);
        boolean bl7 = !bl5 && !bl6;
        if (zy2 == null) {
            bl4 = false;
        }
        if (Log.isLoggable((String)"Mms:thumbnailcache", (int)3)) {
            Log.v((String)"ThumbnailManager", (String)("getThumbnail mThumbnailCache.get for uri: " + object + " thumbnail: " + (Object)bitmap + " callback: " + zy2 + " thumbnailExists: " + bl5 + " taskExists: " + bl6 + " newTaskRequired: " + bl7 + " callbackRequired: " + bl4));
        }
        if (bl5) {
            if (bl4) {
                zy2.a(new a(bitmap, bl2), null);
            }
            return new aam();
        }
        if (bl4) {
            this.a((Uri)object, zy2);
        }
        if (bl7) {
            this.a.add(object);
            object = new b((Uri)object, bl2, bl3);
            this.c.execute((Runnable)object);
        }
        return new abn(this, zy2);
    }

    static /* synthetic */ void a(abm abm2) {
        abm2.h();
    }

    static /* synthetic */ aay b(abm abm2) {
        return abm2.f;
    }

    static /* synthetic */ Bitmap e() {
        return j;
    }

    static /* synthetic */ Bitmap f() {
        return k;
    }

    static /* synthetic */ Bitmap g() {
        return i;
    }

    private void h() {
        if (i == null) {
            i = BitmapFactory.decodeResource((Resources)MmsApp.c().getResources(), (int)2130838200);
        }
        if (j == null) {
            j = BitmapFactory.decodeResource((Resources)MmsApp.c().getResources(), (int)2130838190);
        }
        if (k == null) {
            k = BitmapFactory.decodeResource((Resources)MmsApp.c().getResources(), (int)2130837719);
        }
    }

    private zx i() {
        synchronized (this) {
            if (this.h == null) {
                this.h = new zx(this.g);
            }
            zx zx2 = this.h;
            return zx2;
        }
    }

    public zz a(Uri uri, zy<a> zy2, boolean bl2) {
        return this.a(uri, false, zy2, bl2);
    }

    @Override
    public void a() {
        super.a();
        if (i != null) {
            i.recycle();
            i = null;
        }
        if (j != null) {
            j.recycle();
            j = null;
        }
        if (k != null) {
            k.recycle();
            k = null;
        }
    }

    public void a(Uri uri) {
        if (Log.isLoggable((String)"ThumbnailManager", (int)3)) {
            Log.d((String)"ThumbnailManager", (String)("removeThumbnail: " + (Object)uri));
        }
        if (uri != null) {
            this.f.b(uri);
        }
    }

    public zz b(Uri uri, zy<a> zy2) {
        return this.a(uri, true, zy2, false);
    }

    @Override
    public void b() {
        synchronized (this) {
            super.b();
            this.f.a();
            this.d();
            return;
        }
    }

    @Override
    public String c() {
        return "ThumbnailManager";
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void d() {
        synchronized (this) {
            if (this.h == null) {
                zk.a(this.g);
            } else {
                this.i().a();
                this.h = null;
            }
            return;
        }
    }

    public static class a {
        public final Bitmap a;
        public final boolean b;

        public a(Bitmap bitmap, boolean bl2) {
            this.a = bitmap;
            this.b = bl2;
        }
    }

    public class b
    implements Runnable {
        private final Uri b;
        private final boolean c;
        private final boolean d;

        public b(Uri uri, boolean bl2, boolean bl3) {
            if (uri == null) {
                throw new NullPointerException();
            }
            this.b = uri;
            this.c = bl2;
            this.d = bl3;
        }

        /*
         * Enabled aggressive block sorting
         */
        private int a(double d2, double d3) {
            int n2;
            if (d3 <= d2) {
                d2 = d3;
            }
            if ((n2 = (int)d2) <= 1) {
                return 1;
            }
            if (n2 <= 8) {
                return this.a(n2);
            }
            return n2 / 8 * 8;
        }

        private int a(int n2) {
            if (n2 <= 0) {
                throw new IllegalArgumentException();
            }
            return Integer.highestOneBit((int)n2);
        }

        /*
         * Unable to fully structure code
         * Enabled aggressive exception aggregation
         */
        private Bitmap a() {
            mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(abm.d(abm.this), this.b);
            bitmap = mediaMetadataRetriever.getFrameAtTime(-1);
            mediaMetadataRetriever.release();
            return bitmap;
            catch (RuntimeException runtimeException) {
                mediaMetadataRetriever.release();
lbl11: // 2 sources:
                do {
                    return null;
                    break;
                } while (true);
            }
            catch (Throwable throwable) {
                mediaMetadataRetriever.release();
lbl16: // 2 sources:
                do {
                    throw throwable;
                    break;
                } while (true);
            }
            catch (RuntimeException var1_2) {
                return bitmap;
            }
            {
                catch (RuntimeException runtimeException2) {
                    ** continue;
                }
            }
            {
                catch (RuntimeException runtimeException3) {
                    ** continue;
                }
            }
        }

        /*
         * Enabled aggressive block sorting
         */
        private Bitmap a(double d2, double d3, Bitmap bitmap, int n2, boolean bl2) {
            int n3 = bitmap.getWidth();
            int n4 = bitmap.getHeight();
            if (d3 > d2) {
                n4 = (int)((double)n2 * 1.0 * (double)n4 / (double)n3);
                n3 = n2;
            } else {
                n3 = (int)((double)n2 * 1.0 * (double)n3 / (double)n4);
                n4 = n2;
            }
            if (n3 == bitmap.getWidth() && n4 == bitmap.getHeight()) {
                return bitmap;
            }
            float f2 = (float)((double)n3 * 1.0 / (double)bitmap.getWidth());
            Bitmap bitmap2 = Bitmap.createBitmap((int)n3, (int)n4, (Bitmap.Config)this.b(bitmap));
            Canvas canvas = new Canvas(bitmap2);
            canvas.scale(f2, f2);
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, new Paint(6));
            if (bl2) {
                bitmap.recycle();
            }
            return bitmap2;
        }

        private Bitmap a(Bitmap bitmap, float f2, boolean bl2) {
            int n2 = Math.round((float)((float)bitmap.getWidth() * f2));
            int n3 = Math.round((float)((float)bitmap.getHeight() * f2));
            if (n2 == bitmap.getWidth() && n3 == bitmap.getHeight()) {
                return bitmap;
            }
            Bitmap bitmap2 = Bitmap.createBitmap((int)n2, (int)n3, (Bitmap.Config)this.b(bitmap));
            Canvas canvas = new Canvas(bitmap2);
            canvas.scale(f2, f2);
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, new Paint(6));
            if (bl2) {
                bitmap.recycle();
            }
            return bitmap2;
        }

        private Bitmap a(Bitmap bitmap, int n2, int n3) {
            Bitmap bitmap2 = Bitmap.createBitmap((Bitmap)bitmap, (int)(bitmap.getWidth() - n2 >> 1), (int)(bitmap.getHeight() - n3 >> 1), (int)n2, (int)n3);
            if (bitmap2 == null) {
                return bitmap;
            }
            bitmap.recycle();
            return bitmap2;
        }

        private Bitmap a(Bitmap bitmap, int n2, boolean bl2) {
            int n3;
            int n4 = bitmap.getWidth();
            float f2 = Math.min((float)((float)n2 / (float)n4), (float)((float)n2 / (float)(n3 = bitmap.getHeight())));
            if (f2 >= 1.0f) {
                return bitmap;
            }
            return this.a(bitmap, f2, bl2);
        }

        private Bitmap a(Uri uri, int n2) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            return this.a(uri, options, abm.e);
        }

        /*
         * Unable to fully structure code
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         * Lifted jumps to return sites
         */
        private Bitmap a(Uri var1_1, BitmapFactory.Options var2_3, int var3_7) {
            var9_8 = var2_3;
            if (var2_3 == null) {
                var9_8 = new BitmapFactory.Options();
            }
            var8_9 = abp.a(abm.d(abm.this), (Uri)var1_1);
            try {
                var2_3 = abm.d(abm.this).getContentResolver().openInputStream((Uri)var1_1);
            }
            catch (FileNotFoundException var2_4) {
                Log.e((String)"ThumbnailManager", (String)("Can't open uri: " + var1_1), (Throwable)var2_4);
                return null;
            }
            var9_8.inJustDecodeBounds = true;
            BitmapFactory.decodeStream((InputStream)var2_3, (Rect)null, (BitmapFactory.Options)var9_8);
            this.a((Closeable)var2_3);
            try {
                var10_10 = abm.d(abm.this).getContentResolver().openInputStream((Uri)var1_1);
            }
            catch (FileNotFoundException var2_5) {
                Log.e((String)"ThumbnailManager", (String)("Can't open uri: " + var1_1), (Throwable)var2_5);
                return null;
            }
            var4_11 = (double)var9_8.outWidth * 1.0 / (double)var3_7;
            var6_12 = (double)var9_8.outHeight * 1.0 / (double)var3_7;
            var9_8.inSampleSize = this.a(var4_11, var6_12);
            var9_8.inJustDecodeBounds = false;
            try {
                var1_1 = var2_3 = BitmapFactory.decodeStream((InputStream)var10_10, (Rect)null, (BitmapFactory.Options)var9_8);
                this.a(var10_10);
                ** GOTO lbl32
            }
            catch (OutOfMemoryError var2_6) {
                Log.e((String)"ThumbnailManager", (String)("requestDecode " + var1_1 + "-- " + (Object)var2_6));
                var1_1 = null;
lbl32: // 2 sources:
                if (var1_1 == null) {
                    return null;
                }
            }
            finally {
                this.a(var10_10);
            }
            var1_1 = var2_3 = this.a(this.a(var4_11, var6_12, (Bitmap)var1_1, var3_7, true), var3_7, var3_7);
            if (var8_9 == 1) return this.c((Bitmap)var1_1);
            var1_1 = var2_3;
            if (var8_9 == 0) return this.c((Bitmap)var1_1);
            var1_1 = wd.a((Bitmap)var2_3, var8_9);
            return this.c((Bitmap)var1_1);
        }

        /*
         * Enabled aggressive block sorting
         */
        private Bitmap a(boolean bl2) {
            zx.a a2 = null;
            zx zx2 = abm.this.i();
            String string2 = new zf(abm.this.g, this.b).c();
            if (string2 == null) return a2;
            boolean bl3 = TempFileProvider.a(string2);
            a2 = !bl3 ? zx2.a(string2, 1) : null;
            if (a2 != null) {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                a2 = options = this.a(a2.a, a2.b, a2.a.length - a2.b, options);
                if (options != null) return a2;
                {
                    Log.w((String)"ThumbnailManager", (String)("decode cached failed " + string2));
                    return options;
                }
            }
            a2 = bl2 ? this.a() : this.a(this.b, 1);
            if (a2 == null) {
                Log.w((String)"ThumbnailManager", (String)("decode orig failed " + string2));
                return null;
            }
            zx.a a3 = bl2 ? this.a((Bitmap)a2, abm.e, true) : a2;
            a2 = a3;
            if (bl3) {
                return a2;
            }
            zx2.a(string2, 1, this.a((Bitmap)a3));
            return a3;
        }

        private Bitmap a(byte[] arrby, int n2, int n3, BitmapFactory.Options options) {
            BitmapFactory.Options options2 = options;
            if (options == null) {
                options2 = new BitmapFactory.Options();
            }
            return this.c(BitmapFactory.decodeByteArray((byte[])arrby, (int)n2, (int)n3, (BitmapFactory.Options)options2));
        }

        static /* synthetic */ Uri a(b b2) {
            return b2.b;
        }

        private void a(Closeable closeable) {
            if (closeable == null) {
                return;
            }
            try {
                closeable.close();
                return;
            }
            catch (Throwable var1_2) {
                Log.w((String)"ThumbnailManager", (String)"close fail", (Throwable)var1_2);
                return;
            }
        }

        private byte[] a(Bitmap bitmap) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, (OutputStream)byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }

        private Bitmap.Config b(Bitmap bitmap) {
            Bitmap.Config config;
            bitmap = config = bitmap.getConfig();
            if (config == null) {
                bitmap = Bitmap.Config.ARGB_8888;
            }
            return bitmap;
        }

        static /* synthetic */ boolean b(b b2) {
            return b2.c;
        }

        private Bitmap c(Bitmap bitmap) {
            if (bitmap == null || bitmap.getConfig() != null) {
                return bitmap;
            }
            Bitmap bitmap2 = bitmap.copy(Bitmap.Config.ARGB_8888, false);
            bitmap.recycle();
            return bitmap2;
        }

        static /* synthetic */ boolean c(b b2) {
            return b2.d;
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        @Override
        public void run() {
            Bitmap bitmap = null;
            try {
                Bitmap bitmap2;
                bitmap = bitmap2 = this.a(this.c);
            }
            catch (IllegalArgumentException var2_3) {
                Log.e((String)"ThumbnailManager", (String)("Couldn't load bitmap for " + (Object)this.b), (Throwable)var2_3);
            }
            catch (OutOfMemoryError var2_4) {
                Log.e((String)"ThumbnailManager", (String)("Couldn't load bitmap for " + (Object)this.b), (Throwable)var2_4);
            }
            abm.this.d.post((Runnable)new abo(this, bitmap));
        }
    }

}

