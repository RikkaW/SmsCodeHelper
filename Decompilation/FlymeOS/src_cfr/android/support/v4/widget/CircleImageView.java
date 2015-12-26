/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.Canvas
 *  android.graphics.Paint
 *  android.graphics.RadialGradient
 *  android.graphics.Shader
 *  android.graphics.Shader$TileMode
 *  android.graphics.drawable.Drawable
 *  android.graphics.drawable.ShapeDrawable
 *  android.graphics.drawable.shapes.OvalShape
 *  android.graphics.drawable.shapes.Shape
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.util.DisplayMetrics
 *  android.view.View
 *  android.view.animation.Animation
 *  android.view.animation.Animation$AnimationListener
 *  android.widget.ImageView
 */
package android.support.v4.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.Shape;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;

class CircleImageView
extends ImageView {
    private static final int FILL_SHADOW_COLOR = 1023410176;
    private static final int KEY_SHADOW_COLOR = 503316480;
    private static final int SHADOW_ELEVATION = 4;
    private static final float SHADOW_RADIUS = 3.5f;
    private static final float X_OFFSET = 0.0f;
    private static final float Y_OFFSET = 1.75f;
    private Animation.AnimationListener mListener;
    private int mShadowRadius;

    /*
     * Enabled aggressive block sorting
     */
    public CircleImageView(Context context, int n2, float f2) {
        super(context);
        float f3 = this.getContext().getResources().getDisplayMetrics().density;
        int n3 = (int)(f2 * f3 * 2.0f);
        int n4 = (int)(1.75f * f3);
        int n5 = (int)(0.0f * f3);
        this.mShadowRadius = (int)(3.5f * f3);
        if (this.elevationSupported()) {
            context = new ShapeDrawable((Shape)new OvalShape());
            ViewCompat.setElevation((View)this, f3 * 4.0f);
        } else {
            context = new ShapeDrawable((Shape)new OvalShadow(this.mShadowRadius, n3));
            ViewCompat.setLayerType((View)this, 1, context.getPaint());
            context.getPaint().setShadowLayer((float)this.mShadowRadius, (float)n5, (float)n4, 503316480);
            n3 = this.mShadowRadius;
            this.setPadding(n3, n3, n3, n3);
        }
        context.getPaint().setColor(n2);
        this.setBackgroundDrawable((Drawable)context);
    }

    private boolean elevationSupported() {
        if (Build.VERSION.SDK_INT >= 21) {
            return true;
        }
        return false;
    }

    public void onAnimationEnd() {
        super.onAnimationEnd();
        if (this.mListener != null) {
            this.mListener.onAnimationEnd(this.getAnimation());
        }
    }

    public void onAnimationStart() {
        super.onAnimationStart();
        if (this.mListener != null) {
            this.mListener.onAnimationStart(this.getAnimation());
        }
    }

    protected void onMeasure(int n2, int n3) {
        super.onMeasure(n2, n3);
        if (!this.elevationSupported()) {
            this.setMeasuredDimension(this.getMeasuredWidth() + this.mShadowRadius * 2, this.getMeasuredHeight() + this.mShadowRadius * 2);
        }
    }

    public void setAnimationListener(Animation.AnimationListener animationListener) {
        this.mListener = animationListener;
    }

    public void setBackgroundColor(int n2) {
        if (this.getBackground() instanceof ShapeDrawable) {
            ((ShapeDrawable)this.getBackground()).getPaint().setColor(n2);
        }
    }

    public void setBackgroundColorRes(int n2) {
        this.setBackgroundColor(this.getContext().getResources().getColor(n2));
    }

    class OvalShadow
    extends OvalShape {
        private int mCircleDiameter;
        private RadialGradient mRadialGradient;
        private Paint mShadowPaint;

        public OvalShadow(int n2, int n3) {
            this.mShadowPaint = new Paint();
            CircleImageView.this.mShadowRadius = n2;
            this.mCircleDiameter = n3;
            float f2 = this.mCircleDiameter / 2;
            float f3 = this.mCircleDiameter / 2;
            float f4 = CircleImageView.this.mShadowRadius;
            CircleImageView.this = Shader.TileMode.CLAMP;
            this.mRadialGradient = new RadialGradient(f2, f3, f4, new int[]{1023410176, 0}, null, (Shader.TileMode)CircleImageView.this);
            this.mShadowPaint.setShader((Shader)this.mRadialGradient);
        }

        public void draw(Canvas canvas, Paint paint) {
            int n2 = CircleImageView.this.getWidth();
            int n3 = CircleImageView.this.getHeight();
            canvas.drawCircle((float)(n2 / 2), (float)(n3 / 2), (float)(this.mCircleDiameter / 2 + CircleImageView.this.mShadowRadius), this.mShadowPaint);
            canvas.drawCircle((float)(n2 / 2), (float)(n3 / 2), (float)(this.mCircleDiameter / 2), paint);
        }
    }

}

