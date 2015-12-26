/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.Bitmap
 *  android.graphics.BitmapShader
 *  android.graphics.Paint
 *  android.graphics.RectF
 *  android.graphics.Shader
 *  android.graphics.Shader$TileMode
 *  android.graphics.drawable.AnimationDrawable
 *  android.graphics.drawable.BitmapDrawable
 *  android.graphics.drawable.ClipDrawable
 *  android.graphics.drawable.Drawable
 *  android.graphics.drawable.LayerDrawable
 *  android.graphics.drawable.ShapeDrawable
 *  android.graphics.drawable.shapes.RoundRectShape
 *  android.graphics.drawable.shapes.Shape
 *  android.util.AttributeSet
 *  android.widget.RatingBar
 *  java.lang.Object
 */
package android.support.v7.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.support.v4.graphics.drawable.DrawableWrapper;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R;
import android.support.v7.internal.widget.TintManager;
import android.support.v7.internal.widget.TintTypedArray;
import android.util.AttributeSet;
import android.widget.RatingBar;

public class AppCompatRatingBar
extends RatingBar {
    private static final int[] TINT_ATTRS = new int[]{16843067, 16843068};
    private Bitmap mSampleTile;

    public AppCompatRatingBar(Context context) {
        this(context, null);
    }

    public AppCompatRatingBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.ratingBarStyle);
    }

    public AppCompatRatingBar(Context object, AttributeSet attributeSet, int n2) {
        super((Context)object, attributeSet, n2);
        if (TintManager.SHOULD_BE_USED) {
            object = TintTypedArray.obtainStyledAttributes(this.getContext(), attributeSet, TINT_ATTRS, n2, 0);
            attributeSet = object.getDrawableIfKnown(0);
            if (attributeSet != null) {
                this.setIndeterminateDrawable(this.tileifyIndeterminate((Drawable)attributeSet));
            }
            if ((attributeSet = object.getDrawableIfKnown(1)) != null) {
                this.setProgressDrawable(this.tileify((Drawable)attributeSet, false));
            }
            object.recycle();
        }
    }

    private Shape getDrawableShape() {
        return new RoundRectShape(new float[]{5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f}, null, null);
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private Drawable tileify(Drawable shapeDrawable, boolean bl2) {
        int n2;
        LayerDrawable layerDrawable;
        LayerDrawable layerDrawable2;
        int n3;
        int n4 = 0;
        if (shapeDrawable instanceof DrawableWrapper) {
            Drawable drawable2 = ((DrawableWrapper)shapeDrawable).getWrappedDrawable();
            if (drawable2 == null) return shapeDrawable;
            drawable2 = this.tileify(drawable2, bl2);
            ((DrawableWrapper)shapeDrawable).setWrappedDrawable(drawable2);
            return shapeDrawable;
        }
        if (shapeDrawable instanceof LayerDrawable) {
            layerDrawable = (LayerDrawable)shapeDrawable;
            n3 = layerDrawable.getNumberOfLayers();
            shapeDrawable = new Drawable[n3];
        } else {
            if (!(shapeDrawable instanceof BitmapDrawable)) return shapeDrawable;
            Bitmap bitmap = ((BitmapDrawable)shapeDrawable).getBitmap();
            if (this.mSampleTile == null) {
                this.mSampleTile = bitmap;
            }
            shapeDrawable = new ShapeDrawable(this.getDrawableShape());
            bitmap = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP);
            shapeDrawable.getPaint().setShader((Shader)bitmap);
            if (!bl2) return shapeDrawable;
            return new ClipDrawable((Drawable)shapeDrawable, 3, 1);
        }
        for (n2 = 0; n2 < n3; ++n2) {
            int n5 = layerDrawable.getId(n2);
            layerDrawable2 = layerDrawable.getDrawable(n2);
            bl2 = n5 == 16908301 || n5 == 16908303;
            shapeDrawable[n2] = this.tileify((Drawable)layerDrawable2, bl2);
        }
        layerDrawable2 = new LayerDrawable((Drawable[])shapeDrawable);
        n2 = n4;
        do {
            shapeDrawable = layerDrawable2;
            if (n2 >= n3) return shapeDrawable;
            layerDrawable2.setId(n2, layerDrawable.getId(n2));
            ++n2;
        } while (true);
    }

    private Drawable tileifyIndeterminate(Drawable drawable2) {
        Drawable drawable3 = drawable2;
        if (drawable2 instanceof AnimationDrawable) {
            drawable2 = (AnimationDrawable)drawable2;
            int n2 = drawable2.getNumberOfFrames();
            drawable3 = new AnimationDrawable();
            drawable3.setOneShot(drawable2.isOneShot());
            for (int i2 = 0; i2 < n2; ++i2) {
                Drawable drawable4 = this.tileify(drawable2.getFrame(i2), true);
                drawable4.setLevel(10000);
                drawable3.addFrame(drawable4, drawable2.getDuration(i2));
            }
            drawable3.setLevel(10000);
        }
        return drawable3;
    }

    protected void onMeasure(int n2, int n3) {
        synchronized (this) {
            super.onMeasure(n2, n3);
            if (this.mSampleTile != null) {
                this.setMeasuredDimension(ViewCompat.resolveSizeAndState(this.mSampleTile.getWidth() * this.getNumStars(), n2, 0), this.getMeasuredHeight());
            }
            return;
        }
    }
}

