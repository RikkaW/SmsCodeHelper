import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v7.appcompat.R.attr;
import android.support.v7.appcompat.R.styleable;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import java.lang.ref.WeakReference;

public class aqt
  extends Drawable
{
  private static final Interpolator RIPPLE_IN_INTERPOLATOR = ;
  private static final Interpolator RIPPLE_OUT_INTERPOLATOR = createOutInterpolator();
  private int mAlpha;
  private int mAlphaBg = 75;
  private ValueAnimator mAnimation;
  private int mColor = -16777216;
  private int mColorBg = -16777216;
  private Drawable mDrawable;
  private int mHeight;
  private int mInDuration = 0;
  private boolean mIsDown = false;
  private boolean mIsHaveBg;
  private boolean mIsRipple;
  private boolean mIsRippleFade;
  private boolean mIsShrink;
  private boolean mIsUseFadeOut;
  private int mMaxRadius = 0;
  private int mOutDuration = 0;
  private Paint mPaint;
  private Paint mPaintBg;
  private int mRadius;
  private int mStartRadius;
  private WeakReference<View> mView;
  private int mWidth;
  
  public aqt(View paramView)
  {
    this(paramView, R.attr.mzRippleDefaultStyle);
  }
  
  public aqt(View paramView, int paramInt)
  {
    if (paramView == null) {
      throw new IllegalArgumentException("you must use a view to create a RippleDrawableComp");
    }
    TypedArray localTypedArray = paramView.getContext().obtainStyledAttributes(null, R.styleable.MzRippleDrawableComp, paramInt, 0);
    mColor = localTypedArray.getColor(R.styleable.MzRippleDrawableComp_mzRippleColor, -16777216);
    mAlpha = Color.alpha(mColor);
    mStartRadius = localTypedArray.getDimensionPixelSize(R.styleable.MzRippleDrawableComp_mzStartRadius, -1);
    mMaxRadius = localTypedArray.getDimensionPixelSize(R.styleable.MzRippleDrawableComp_mzMaxRadius, 0);
    mIsUseFadeOut = localTypedArray.getBoolean(R.styleable.MzRippleDrawableComp_mzUseFadeOut, false);
    mIsHaveBg = localTypedArray.getBoolean(R.styleable.MzRippleDrawableComp_mzAutoLightBackground, true);
    mIsRippleFade = localTypedArray.getBoolean(R.styleable.MzRippleDrawableComp_mzRippleFade, true);
    mInDuration = localTypedArray.getInt(R.styleable.MzRippleDrawableComp_mzInDuration, 160);
    mOutDuration = localTypedArray.getInt(R.styleable.MzRippleDrawableComp_mzOutDuration, 320);
    mIsShrink = localTypedArray.getBoolean(R.styleable.MzRippleDrawableComp_mzShrink, true);
    localTypedArray.recycle();
    mPaint = new Paint();
    mPaint.setColor(mColor);
    mPaint.setAlpha(mAlpha);
    mPaint.setAntiAlias(true);
    mPaintBg = new Paint();
    mPaintBg.setColor(mColor);
    mPaintBg.setAlpha(mAlpha / 2);
    mPaintBg.setAntiAlias(true);
    mView = new WeakReference(paramView);
    mRadius = mStartRadius;
    init();
  }
  
  private void cancelAnimation()
  {
    if ((mAnimation != null) && ((mAnimation.isStarted()) || (mAnimation.isRunning()))) {
      mAnimation.cancel();
    }
  }
  
  private static Interpolator createInInterpolator()
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return new PathInterpolator(0.0F, 0.0F, 0.1F, 1.0F);
    }
    return new DecelerateInterpolator();
  }
  
  private static Interpolator createOutInterpolator()
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return new PathInterpolator(0.66F, 0.0F, 0.67F, 1.0F);
    }
    return new DecelerateInterpolator();
  }
  
  private void initValue()
  {
    View localView = (View)mView.get();
    if (localView == null) {}
    do
    {
      return;
      mWidth = localView.getWidth();
      mHeight = localView.getHeight();
      setBounds(new Rect(0, 0, mWidth, mHeight));
      if (mMaxRadius <= 0) {
        mMaxRadius = ((int)Math.hypot(mWidth, mHeight) / 2);
      }
    } while (mStartRadius >= 0);
    mStartRadius = ((int)(mMaxRadius * 0.825F));
  }
  
  public void draw(Canvas paramCanvas)
  {
    if (mDrawable != null) {
      mDrawable.draw(paramCanvas);
    }
    if ((mIsRipple) || (mIsDown))
    {
      if (mIsHaveBg) {
        paramCanvas.drawCircle(mWidth / 2, mHeight / 2, mMaxRadius, mPaintBg);
      }
      paramCanvas.drawCircle(mWidth / 2, mHeight / 2, mRadius, mPaint);
    }
  }
  
  public int getOpacity()
  {
    return mAlpha;
  }
  
  public void init()
  {
    if (mView == null) {
      return;
    }
    ((View)mView.get()).setClickable(true);
    ((View)mView.get()).addOnLayoutChangeListener(new aqu(this));
    ((View)mView.get()).post(new aqv(this));
  }
  
  public boolean isStateful()
  {
    return true;
  }
  
  protected boolean onStateChange(int[] paramArrayOfInt)
  {
    boolean bool = super.onStateChange(paramArrayOfInt);
    int m = paramArrayOfInt.length;
    int i = 0;
    int j = 0;
    int k = 0;
    while (i < m)
    {
      int n = paramArrayOfInt[i];
      if (n == 16842910) {
        k = 1;
      }
      if (n == 16842919) {
        j = 1;
      }
      i += 1;
    }
    if ((k != 0) && (j != 0))
    {
      mIsDown = true;
      ripple();
    }
    do
    {
      do
      {
        return bool;
      } while (mIsDown != true);
      mIsDown = false;
      invalidateSelf();
    } while ((mIsRipple) || (!mIsRippleFade));
    rippleFade();
    return bool;
  }
  
  public void ripple()
  {
    cancelAnimation();
    mPaint.setAlpha(mAlpha);
    mPaintBg.setAlpha(mAlpha / 2);
    mIsRipple = true;
    mRadius = mStartRadius;
    mAnimation = ValueAnimator.ofInt(new int[] { mRadius, mMaxRadius });
    mAnimation.addUpdateListener(new aqw(this));
    mAnimation.setDuration(mInDuration);
    mAnimation.setInterpolator(RIPPLE_IN_INTERPOLATOR);
    mAnimation.start();
  }
  
  public void rippleFade()
  {
    cancelAnimation();
    mPaint.setAlpha(mAlpha);
    mIsRipple = true;
    mRadius = mMaxRadius;
    float f = mStartRadius / mMaxRadius;
    mAnimation = ValueAnimator.ofInt(new int[] { mMaxRadius, mStartRadius });
    mAnimation.addUpdateListener(new aqx(this, f));
    mAnimation.setDuration(mOutDuration);
    mAnimation.setInterpolator(RIPPLE_OUT_INTERPOLATOR);
    mAnimation.start();
  }
  
  public void setAlpha(int paramInt)
  {
    mAlpha = paramInt;
    mPaint.setAlpha(mAlpha);
  }
  
  public void setAlphaBg(int paramInt)
  {
    mAlphaBg = paramInt;
    mPaintBg.setAlpha(mAlphaBg);
  }
  
  public void setBounds(Rect paramRect)
  {
    if (mDrawable != null) {
      mDrawable.setBounds(paramRect);
    }
  }
  
  public void setColor(int paramInt)
  {
    mColor = paramInt;
    mPaint.setColor(mColor);
  }
  
  public void setColorBg(int paramInt)
  {
    mColorBg = paramInt;
    mPaintBg.setColor(mColorBg);
  }
  
  public void setColorFilter(ColorFilter paramColorFilter)
  {
    mPaint.setColorFilter(paramColorFilter);
  }
  
  public void setDrawable(Drawable paramDrawable)
  {
    mDrawable = paramDrawable;
  }
  
  public void setInDuration(int paramInt)
  {
    mInDuration = paramInt;
  }
  
  public void setIsHaveBg(boolean paramBoolean)
  {
    mIsHaveBg = paramBoolean;
  }
  
  public void setIsRippleFade(boolean paramBoolean)
  {
    mIsRippleFade = paramBoolean;
  }
  
  public void setIsShrink(boolean paramBoolean)
  {
    mIsShrink = paramBoolean;
  }
  
  public void setIsUseFadeOut(boolean paramBoolean)
  {
    mIsUseFadeOut = paramBoolean;
  }
  
  public void setMaxRadius(int paramInt)
  {
    mMaxRadius = paramInt;
  }
  
  public void setOutDuration(int paramInt)
  {
    mOutDuration = paramInt;
  }
  
  public void setStartRadius(int paramInt)
  {
    mStartRadius = paramInt;
  }
}

/* Location:
 * Qualified Name:     aqt
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */