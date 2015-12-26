/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.Bitmap
 *  android.graphics.Bitmap$Config
 *  android.graphics.Canvas
 *  android.graphics.Matrix
 *  android.graphics.Rect
 *  android.graphics.RectF
 *  android.graphics.drawable.BitmapDrawable
 *  android.graphics.drawable.Drawable
 *  android.os.Bundle
 *  android.os.Parcelable
 *  android.view.View
 *  android.widget.ImageView
 *  android.widget.ImageView$ScaleType
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.app;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import java.util.List;
import java.util.Map;

public abstract class SharedElementCallback {
    private static final String BUNDLE_SNAPSHOT_BITMAP = "sharedElement:snapshot:bitmap";
    private static final String BUNDLE_SNAPSHOT_IMAGE_MATRIX = "sharedElement:snapshot:imageMatrix";
    private static final String BUNDLE_SNAPSHOT_IMAGE_SCALETYPE = "sharedElement:snapshot:imageScaleType";
    private static int MAX_IMAGE_SIZE = 1048576;
    private Matrix mTempMatrix;

    private static Bitmap createDrawableBitmap(Drawable drawable2) {
        int n2 = drawable2.getIntrinsicWidth();
        int n3 = drawable2.getIntrinsicHeight();
        if (n2 <= 0 || n3 <= 0) {
            return null;
        }
        float f2 = Math.min((float)1.0f, (float)((float)MAX_IMAGE_SIZE / (float)(n2 * n3)));
        if (drawable2 instanceof BitmapDrawable && f2 == 1.0f) {
            return ((BitmapDrawable)drawable2).getBitmap();
        }
        n2 = (int)((float)n2 * f2);
        n3 = (int)((float)n3 * f2);
        Bitmap bitmap = Bitmap.createBitmap((int)n2, (int)n3, (Bitmap.Config)Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Rect rect = drawable2.getBounds();
        int n4 = rect.left;
        int n5 = rect.top;
        int n6 = rect.right;
        int n7 = rect.bottom;
        drawable2.setBounds(0, 0, n2, n3);
        drawable2.draw(canvas);
        drawable2.setBounds(n4, n5, n6, n7);
        return bitmap;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public Parcelable onCaptureSharedElementSnapshot(View view, Matrix object, RectF rectF) {
        ImageView imageView;
        Drawable drawable2;
        if (view instanceof ImageView) {
            imageView = (ImageView)view;
            drawable2 = imageView.getDrawable();
            Drawable drawable3 = imageView.getBackground();
            if (drawable2 != null && drawable3 == null && (drawable3 = SharedElementCallback.createDrawableBitmap(drawable2)) != null) {
                drawable2 = new Bundle();
                drawable2.putParcelable("sharedElement:snapshot:bitmap", (Parcelable)drawable3);
                drawable2.putString("sharedElement:snapshot:imageScaleType", imageView.getScaleType().toString());
                if (imageView.getScaleType() != ImageView.ScaleType.MATRIX) return drawable2;
                view = imageView.getImageMatrix();
                object = new float[9];
                view.getValues((float[])object);
                drawable2.putFloatArray("sharedElement:snapshot:imageMatrix", (float[])object);
                return drawable2;
            }
        }
        int n2 = Math.round((float)rectF.width());
        int n3 = Math.round((float)rectF.height());
        drawable2 = imageView = null;
        if (n2 <= 0) return drawable2;
        drawable2 = imageView;
        if (n3 <= 0) return drawable2;
        float f2 = Math.min((float)1.0f, (float)((float)MAX_IMAGE_SIZE / (float)(n2 * n3)));
        n2 = (int)((float)n2 * f2);
        n3 = (int)((float)n3 * f2);
        if (this.mTempMatrix == null) {
            this.mTempMatrix = new Matrix();
        }
        this.mTempMatrix.set((Matrix)object);
        this.mTempMatrix.postTranslate(- rectF.left, - rectF.top);
        this.mTempMatrix.postScale(f2, f2);
        object = Bitmap.createBitmap((int)n2, (int)n3, (Bitmap.Config)Bitmap.Config.ARGB_8888);
        rectF = new Canvas((Bitmap)object);
        rectF.concat(this.mTempMatrix);
        view.draw((Canvas)rectF);
        return object;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public View onCreateSnapshotView(Context context, Parcelable object) {
        if (object instanceof Bundle) {
            Bitmap bitmap = (Bitmap)(object = (Bundle)object).getParcelable("sharedElement:snapshot:bitmap");
            if (bitmap == null) {
                return null;
            }
            context = new ImageView(context);
            context.setImageBitmap(bitmap);
            context.setScaleType(ImageView.ScaleType.valueOf((String)object.getString("sharedElement:snapshot:imageScaleType")));
            if (context.getScaleType() != ImageView.ScaleType.MATRIX) return context;
            object = object.getFloatArray("sharedElement:snapshot:imageMatrix");
            bitmap = new Matrix();
            bitmap.setValues((float[])object);
            context.setImageMatrix((Matrix)bitmap);
            do {
                return context;
                break;
            } while (true);
        }
        if (!(object instanceof Bitmap)) return null;
        object = (Bitmap)object;
        context = new ImageView(context);
        context.setImageBitmap((Bitmap)object);
        return context;
    }

    public void onMapSharedElements(List<String> list, Map<String, View> map) {
    }

    public void onRejectSharedElements(List<View> list) {
    }

    public void onSharedElementEnd(List<String> list, List<View> list2, List<View> list3) {
    }

    public void onSharedElementStart(List<String> list, List<View> list2, List<View> list3) {
    }
}

