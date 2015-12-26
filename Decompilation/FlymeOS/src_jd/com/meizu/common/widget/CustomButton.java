package com.meizu.common.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import android.widget.Button;
import com.meizu.common.R.attr;
import com.meizu.common.R.drawable;
import com.meizu.common.R.styleable;

public class CustomButton
  extends Button
{
  private static final int DEFAULT_TEXT_SIZE = 15;
  private static float MAX_ALPHASIGN = 1.0F;
  private static float MIN_ALPHASIGN = 0.0F;
  private int baseline;
  private Interpolator mAddInterpolator;
  private ValueAnimator mAlphaAnim = null;
  private float mAlphaSign = 0.0F;
  private Rect mBackgroundBound;
  private String mBtnAddText;
  private int mBtnAddTextColor;
  private String mBtnAddedText;
  private int mBtnAddedTextColor;
  private float mBtnTextSize = 15.0F;
  boolean mIsFirst = true;
  boolean mIsPressing = true;
  private Drawable mResDefaultDrawble;
  private Drawable mResPressedDrawble;
  private Paint mTextPaintA;
  private Paint mTextPaintB;
  
  public CustomButton(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public CustomButton(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, R.attr.MeizuCommon_CustomButtonStyle);
  }
  
  public CustomButton(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.CustomButton, paramInt, 0);
    mResDefaultDrawble = paramContext.getDrawable(R.styleable.CustomButton_mcBtnFocus);
    mResPressedDrawble = paramContext.getDrawable(R.styleable.CustomButton_mcBtnNormalPress);
    mBtnTextSize = paramContext.getDimension(R.styleable.CustomButton_mcBtnTextSize, 15.0F);
    mBtnAddedText = paramContext.getString(R.styleable.CustomButton_mcBtnPressedText);
    mBtnAddText = paramContext.getString(R.styleable.CustomButton_mcBtnDefaultText);
    mBtnAddedTextColor = paramContext.getColor(R.styleable.CustomButton_mcBtnPressedTextColor, -16777216);
    mBtnAddTextColor = paramContext.getColor(R.styleable.CustomButton_mcBtnDefaultTextColor, -1);
    if (mResDefaultDrawble == null) {
      mResDefaultDrawble = getResources().getDrawable(R.drawable.mc_btn_list_default_alpha_normal);
    }
    if (mResPressedDrawble == null) {
      mResPressedDrawble = getResources().getDrawable(R.drawable.mc_btn_list_default_pressed);
    }
    paramContext.recycle();
    init();
  }
  
  private float getAlphaSign()
  {
    return mAlphaSign;
  }
  
  private void init()
  {
    Paint localPaint = new Paint();
    localPaint.setTextAlign(Paint.Align.CENTER);
    localPaint.setAntiAlias(true);
    localPaint.setTextSize(mBtnTextSize);
    mTextPaintA = new Paint(localPaint);
    mTextPaintA.setColor(mBtnAddTextColor);
    mTextPaintB = new Paint(localPaint);
    mTextPaintB.setColor(mBtnAddedTextColor);
    if (Build.VERSION.SDK_INT >= 21)
    {
      mAddInterpolator = new PathInterpolator(0.33F, 0.0F, 0.67F, 1.0F);
      return;
    }
    mAddInterpolator = new AccelerateInterpolator();
  }
  
  private void processButtonClick()
  {
    if (mIsPressing)
    {
      mAlphaSign = 0.0F;
      if (mIsPressing) {
        break label57;
      }
    }
    label57:
    for (boolean bool = true;; bool = false)
    {
      mIsPressing = bool;
      if (mAlphaSign <= 0.0F) {
        break label62;
      }
      startAnimator(mAlphaSign, MIN_ALPHASIGN, 80);
      return;
      mAlphaSign = 1.0F;
      break;
    }
    label62:
    startAnimator(mAlphaSign, MAX_ALPHASIGN, 80);
  }
  
  private void setAlphaSign(float paramFloat)
  {
    mAlphaSign = paramFloat;
  }
  
  private void startAnimator(float paramFloat1, float paramFloat2, int paramInt)
  {
    mAlphaAnim = ValueAnimator.ofFloat(new float[] { paramFloat1, paramFloat2 });
    mAlphaAnim.addUpdateListener(new CustomButton.1(this));
    mAlphaAnim.setDuration(paramInt);
    mAlphaAnim.setInterpolator(mAddInterpolator);
    mAlphaAnim.start();
  }
  
  public String getBtnDefaultText()
  {
    return mBtnAddText;
  }
  
  public String getBtnPressedText()
  {
    return mBtnAddedText;
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    if (mIsFirst)
    {
      if (mBackgroundBound == null) {
        mBackgroundBound = new Rect(0, 0, getWidth(), getHeight());
      }
      Paint.FontMetricsInt localFontMetricsInt = mTextPaintA.getFontMetricsInt();
      baseline = (mBackgroundBound.centerY() - (bottom - top) / 2 - top);
      mResPressedDrawble.setBounds(mBackgroundBound);
      mResPressedDrawble.setAlpha((int)((MAX_ALPHASIGN - mAlphaSign) * 255.0F));
      mResPressedDrawble.draw(paramCanvas);
      mResDefaultDrawble.setAlpha((int)(mAlphaSign * 255.0F));
      mResDefaultDrawble.setBounds(mBackgroundBound);
      mResDefaultDrawble.draw(paramCanvas);
      paramCanvas.drawText(mBtnAddText, mBackgroundBound.centerX(), baseline, mTextPaintA);
      mIsFirst = false;
    }
    for (;;)
    {
      super.onDraw(paramCanvas);
      paramCanvas.restore();
      return;
      if (mIsPressing)
      {
        mResPressedDrawble.setBounds(mBackgroundBound);
        mResPressedDrawble.setAlpha((int)(mAlphaSign * 255.0F));
        mResPressedDrawble.draw(paramCanvas);
        mResDefaultDrawble.setAlpha((int)((MAX_ALPHASIGN - mAlphaSign) * 255.0F));
        mResDefaultDrawble.setBounds(mBackgroundBound);
        mResDefaultDrawble.draw(paramCanvas);
        mTextPaintA.setAlpha((int)((MAX_ALPHASIGN - mAlphaSign) * 255.0F));
        mTextPaintB.setAlpha((int)(mAlphaSign * 77.0F));
        paramCanvas.drawText(mBtnAddText, mBackgroundBound.centerX(), baseline, mTextPaintA);
        paramCanvas.drawText(mBtnAddedText, mBackgroundBound.centerX(), baseline, mTextPaintB);
      }
      else
      {
        mResDefaultDrawble.setAlpha((int)((MAX_ALPHASIGN - mAlphaSign) * 255.0F));
        mResDefaultDrawble.setBounds(mBackgroundBound);
        mResDefaultDrawble.draw(paramCanvas);
        mResPressedDrawble.setBounds(mBackgroundBound);
        mResPressedDrawble.setAlpha((int)(mAlphaSign * 255.0F));
        mResPressedDrawble.draw(paramCanvas);
        mTextPaintA.setAlpha((int)((MAX_ALPHASIGN - mAlphaSign) * 255.0F));
        mTextPaintB.setAlpha((int)(mAlphaSign * 77.0F));
        paramCanvas.drawText(mBtnAddedText, mBackgroundBound.centerX(), baseline, mTextPaintB);
        paramCanvas.drawText(mBtnAddText, mBackgroundBound.centerX(), baseline, mTextPaintA);
      }
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    Paint.FontMetrics localFontMetrics = mTextPaintA.getFontMetrics();
    int j = (int)(bottom - top + getPaddingBottom() + getPaddingTop());
    int k = (int)(Math.max(mTextPaintA.measureText(mBtnAddText), mTextPaintB.measureText(mBtnAddedText)) + 20.0F);
    int m = View.MeasureSpec.getMode(paramInt1);
    int n = View.MeasureSpec.getMode(paramInt2);
    int i = View.MeasureSpec.getSize(paramInt1);
    paramInt1 = View.MeasureSpec.getSize(paramInt2);
    if (m == Integer.MIN_VALUE)
    {
      paramInt2 = k;
      if (n != Integer.MIN_VALUE) {
        break label136;
      }
    }
    for (paramInt1 = j;; paramInt1 = 0) {
      label136:
      do
      {
        setMeasuredDimension(paramInt2, paramInt1);
        return;
        if (m == 1073741824)
        {
          paramInt2 = i;
          break;
        }
        paramInt2 = i;
        break;
      } while ((n == 1073741824) || (m == 0));
    }
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((paramMotionEvent.getAction() == 0) && (mAlphaAnim != null)) {
      mAlphaAnim.end();
    }
    return super.onTouchEvent(paramMotionEvent);
  }
  
  public boolean performClick()
  {
    processButtonClick();
    return super.performClick();
  }
  
  public void setBtnDefaultText(String paramString)
  {
    String str = mBtnAddText;
    mBtnAddText = paramString;
    if (mTextPaintB.measureText(str) != mTextPaintB.measureText(mBtnAddText)) {
      requestLayout();
    }
    invalidate();
  }
  
  public void setBtnPressedText(String paramString)
  {
    String str = mBtnAddedText;
    mBtnAddedText = paramString;
    if (mTextPaintB.measureText(str) != mTextPaintB.measureText(mBtnAddedText)) {
      requestLayout();
    }
    invalidate();
  }
  
  public void setmBtnAddTextColor(int paramInt)
  {
    mTextPaintA.setColor(paramInt);
    invalidate();
  }
  
  public void setmBtnAddedTextColor(int paramInt)
  {
    mTextPaintB.setColor(paramInt);
    invalidate();
  }
  
  public void setmBtnTextSize(int paramInt)
  {
    mTextPaintA.setTextSize(paramInt);
    mTextPaintB.setTextSize(paramInt);
    mIsFirst = true;
    requestLayout();
    invalidate();
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.CustomButton
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */