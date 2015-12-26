/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.graphics.Bitmap
 *  android.graphics.BitmapFactory
 *  android.graphics.BitmapFactory$Options
 *  android.graphics.Matrix
 *  android.graphics.Rect
 *  android.graphics.RectF
 *  android.net.Uri
 *  android.print.PrintAttributes
 *  android.print.PrintAttributes$Builder
 *  android.print.PrintAttributes$MediaSize
 *  android.print.PrintDocumentAdapter
 *  android.print.PrintJob
 *  android.print.PrintManager
 *  android.util.Log
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
package android.support.v4.print;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.support.v4.print.PrintHelperKitkat$1;
import android.support.v4.print.PrintHelperKitkat$2;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;

class PrintHelperKitkat {
    public static final int COLOR_MODE_COLOR = 2;
    public static final int COLOR_MODE_MONOCHROME = 1;
    private static final String LOG_TAG = "PrintHelperKitkat";
    private static final int MAX_PRINT_SIZE = 3500;
    public static final int ORIENTATION_LANDSCAPE = 1;
    public static final int ORIENTATION_PORTRAIT = 2;
    public static final int SCALE_MODE_FILL = 2;
    public static final int SCALE_MODE_FIT = 1;
    int mColorMode = 2;
    final Context mContext;
    BitmapFactory.Options mDecodeOptions = null;
    private final Object mLock = new Object();
    int mOrientation = 1;
    int mScaleMode = 2;

    PrintHelperKitkat(Context context) {
        this.mContext = context;
    }

    static /* synthetic */ Matrix access$000(PrintHelperKitkat printHelperKitkat, int n2, int n3, RectF rectF, int n4) {
        return printHelperKitkat.getMatrix(n2, n3, rectF, n4);
    }

    static /* synthetic */ Bitmap access$200(PrintHelperKitkat printHelperKitkat, Uri uri, int n2) {
        return printHelperKitkat.loadConstrainedBitmap(uri, n2);
    }

    static /* synthetic */ Object access$300(PrintHelperKitkat printHelperKitkat) {
        return printHelperKitkat.mLock;
    }

    /*
     * Enabled aggressive block sorting
     */
    private Matrix getMatrix(int n2, int n3, RectF rectF, int n4) {
        Matrix matrix = new Matrix();
        float f2 = rectF.width() / (float)n2;
        f2 = n4 == 2 ? Math.max((float)f2, (float)(rectF.height() / (float)n3)) : Math.min((float)f2, (float)(rectF.height() / (float)n3));
        matrix.postScale(f2, f2);
        matrix.postTranslate((rectF.width() - (float)n2 * f2) / 2.0f, (rectF.height() - f2 * (float)n3) / 2.0f);
        return matrix;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private Bitmap loadBitmap(Uri object, BitmapFactory.Options options) {
        Object object2 = null;
        if (object == null) throw new IllegalArgumentException("bad argument to loadBitmap");
        if (this.mContext == null) {
            throw new IllegalArgumentException("bad argument to loadBitmap");
        }
        try {
            object2 = object = this.mContext.getContentResolver().openInputStream((Uri)object);
            options = BitmapFactory.decodeStream((InputStream)object, (Rect)null, (BitmapFactory.Options)options);
            if (object == null) return options;
        }
        catch (Throwable var1_3) {
            if (object2 == null) throw var1_3;
            try {
                object2.close();
            }
            catch (IOException iOException) {
                Log.w((String)"PrintHelperKitkat", (String)"close fail ", (Throwable)iOException);
                throw var1_3;
            }
            throw var1_3;
        }
        try {
            object.close();
            return options;
        }
        catch (IOException var1_2) {
            Log.w((String)"PrintHelperKitkat", (String)"close fail ", (Throwable)var1_2);
            return options;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    private Bitmap loadConstrainedBitmap(Uri object, int n2) {
        int n3 = 1;
        if (n2 <= 0) throw new IllegalArgumentException("bad argument to getScaledBitmap");
        if (object == null) throw new IllegalArgumentException("bad argument to getScaledBitmap");
        if (this.mContext == null) {
            throw new IllegalArgumentException("bad argument to getScaledBitmap");
        }
        Object object2 = new BitmapFactory.Options();
        object2.inJustDecodeBounds = true;
        this.loadBitmap((Uri)object, (BitmapFactory.Options)object2);
        int n4 = object2.outWidth;
        int n5 = object2.outHeight;
        if (n4 <= 0) return null;
        if (n5 <= 0) {
            return null;
        }
        for (int i2 = Math.max((int)n4, (int)n5); i2 > n2; i2 >>>= 1, n3 <<= 1) {
        }
        if (n3 <= 0) return null;
        if (Math.min((int)n4, (int)n5) / n3 <= 0) return null;
        object2 = this.mLock;
        // MONITORENTER : object2
        this.mDecodeOptions = new BitmapFactory.Options();
        this.mDecodeOptions.inMutable = true;
        this.mDecodeOptions.inSampleSize = n3;
        BitmapFactory.Options options = this.mDecodeOptions;
        // MONITOREXIT : object2
        try {
            object2 = this.loadBitmap((Uri)object, options);
            return object2;
        }
        finally {
            object = this.mLock;
            // MONITORENTER : object
            this.mDecodeOptions = null;
            // MONITOREXIT : object
        }
    }

    public int getColorMode() {
        return this.mColorMode;
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public int getScaleMode() {
        return this.mScaleMode;
    }

    public void printBitmap(String string2, Bitmap bitmap, OnPrintFinishCallback onPrintFinishCallback) {
        if (bitmap == null) {
            return;
        }
        int n2 = this.mScaleMode;
        PrintManager printManager = (PrintManager)this.mContext.getSystemService("print");
        PrintAttributes.MediaSize mediaSize = PrintAttributes.MediaSize.UNKNOWN_PORTRAIT;
        if (bitmap.getWidth() > bitmap.getHeight()) {
            mediaSize = PrintAttributes.MediaSize.UNKNOWN_LANDSCAPE;
        }
        mediaSize = new PrintAttributes.Builder().setMediaSize(mediaSize).setColorMode(this.mColorMode).build();
        printManager.print(string2, (PrintDocumentAdapter)new PrintHelperKitkat$1(this, string2, bitmap, n2, onPrintFinishCallback), (PrintAttributes)mediaSize);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void printBitmap(String string2, Uri object, OnPrintFinishCallback onPrintFinishCallback) {
        object = new PrintHelperKitkat$2(this, string2, (Uri)object, onPrintFinishCallback, this.mScaleMode);
        onPrintFinishCallback = (PrintManager)this.mContext.getSystemService("print");
        PrintAttributes.Builder builder = new PrintAttributes.Builder();
        builder.setColorMode(this.mColorMode);
        if (this.mOrientation == 1) {
            builder.setMediaSize(PrintAttributes.MediaSize.UNKNOWN_LANDSCAPE);
        } else if (this.mOrientation == 2) {
            builder.setMediaSize(PrintAttributes.MediaSize.UNKNOWN_PORTRAIT);
        }
        onPrintFinishCallback.print(string2, (PrintDocumentAdapter)object, builder.build());
    }

    public void setColorMode(int n2) {
        this.mColorMode = n2;
    }

    public void setOrientation(int n2) {
        this.mOrientation = n2;
    }

    public void setScaleMode(int n2) {
        this.mScaleMode = n2;
    }

    public static interface OnPrintFinishCallback {
        public void onFinish();
    }

}

