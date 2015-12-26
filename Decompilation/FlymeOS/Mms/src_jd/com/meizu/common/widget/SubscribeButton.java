package com.meizu.common.widget;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
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

public class SubscribeButton
  extends Button
{
  private static final int DEFAULT_TEXT_SIZE = 15;
  private static float MAX_ALPHASIGN = 1.0F;
  private static float MIN_ALPHASIGN = 0.0F;
  private static final String Tag = "SubButton";
  private ValueAnimator mAlphaAnim = null;
  private float mAlphaSign = 0.0F;
  private int mAnimDuration;
  private Rect mBackgroundBound;
  private int mBaseline;
  private String mBtnBeAddedText;
  private int mBtnBeAddedTextColor;
  private String mBtnNormalText;
  private int mBtnNormalTextColor;
  private float mBtnSubTextSize = 15.0F;
  private Interpolator mInterpolator;
  boolean mIsSelected = false;
  private boolean mManuaStartAnim = false;
  private Drawable mResBeAddedDrawble;
  private Drawable mResNormalDrawble;
  private Paint mTextPaintA;
  private Paint mTextPaintB;
  
  public SubscribeButton(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public SubscribeButton(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, R.attr.MeizuCommon_SubscribeButtonStyle);
  }
  
  public SubscribeButton(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.SubscribeButton, paramInt, 0);
    mResNormalDrawble = paramContext.getDrawable(R.styleable.SubscribeButton_mcBtnNormalBg);
    mResBeAddedDrawble = paramContext.getDrawable(R.styleable.SubscribeButton_mcBtnBeAddedBg);
    mBtnSubTextSize = paramContext.getDimension(R.styleable.SubscribeButton_mcBtnSubTextSize, 15.0F);
    mBtnBeAddedText = paramContext.getString(R.styleable.SubscribeButton_mcBtnBeAddedText);
    mBtnNormalText = paramContext.getString(R.styleable.SubscribeButton_mcBtnNormalText);
    mBtnBeAddedTextColor = paramContext.getColor(R.styleable.SubscribeButton_mcBtnBeAddedTextColor, -16777216);
    mBtnNormalTextColor = paramContext.getColor(R.styleable.SubscribeButton_mcBtnNormalTextColor, -1);
    mAnimDuration = paramContext.getInteger(R.styleable.SubscribeButton_mcBtnAnimDuration, 80);
    if (mResNormalDrawble == null) {
      mResNormalDrawble = getResources().getDrawable(R.drawable.mc_btn_list_default_alpha_normal);
    }
    if (mResBeAddedDrawble == null) {
      mResBeAddedDrawble = getResources().getDrawable(R.drawable.mc_btn_list_default_pressed);
    }
    paramContext.recycle();
    init();
  }
  
  private float getAlphaSign()
  {
    return mAlphaSign;
  }
  
  @TargetApi(21)
  private void init()
  {
    Paint localPaint = new Paint();
    localPaint.setTextAlign(Paint.Align.CENTER);
    localPaint.setAntiAlias(true);
    localPaint.setTextSize(mBtnSubTextSize);
    mTextPaintA = new Paint(localPaint);
    mTextPaintA.setColor(mBtnNormalTextColor);
    mTextPaintB = new Paint(localPaint);
    mTextPaintB.setColor(mBtnBeAddedTextColor);
    if (Build.VERSION.SDK_INT >= 21)
    {
      mInterpolator = new PathInterpolator(0.33F, 0.0F, 0.67F, 1.0F);
      return;
    }
    mInterpolator = new AccelerateInterpolator();
  }
  
  private void setAlphaSign(float paramFloat)
  {
    mAlphaSign = paramFloat;
  }
  
  private void startAnimator(float paramFloat1, float paramFloat2, int paramInt)
  {
    mAlphaAnim = ValueAnimator.ofFloat(new float[] { paramFloat1, paramFloat2 });
    mAlphaAnim.addUpdateListener(new SubscribeButton.1(this));
    mAlphaAnim.setDuration(paramInt);
    mAlphaAnim.setInterpolator(mInterpolator);
    mAlphaAnim.start();
  }
  
  public String getBtnBeAddedText()
  {
    return mBtnBeAddedText;
  }
  
  public String getBtnNormalText()
  {
    return mBtnNormalText;
  }
  
  public boolean getSelectedState()
  {
    return mIsSelected;
  }
  
  public boolean isManuaStartAnim()
  {
    return mManuaStartAnim;
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    float f = MAX_ALPHASIGN - mAlphaSign;
    mTextPaintA.setAlpha((int)(f * 255.0F));
    mTextPaintB.setAlpha((int)(mAlphaSign * 77.0F));
    if (mIsSelected)
    {
      mResBeAddedDrawble.setAlpha((int)(mAlphaSign * 255.0F));
      mResBeAddedDrawble.draw(paramCanvas);
      mResNormalDrawble.setAlpha((int)(f * 255.0F));
      mResNormalDrawble.draw(paramCanvas);
    }
    for (;;)
    {
      paramCanvas.drawText(mBtnNormalText, mBackgroundBound.centerX(), mBaseline, mTextPaintA);
      paramCanvas.drawText(mBtnBeAddedText, mBackgroundBound.centerX(), mBaseline, mTextPaintB);
      super.onDraw(paramCanvas);
      return;
      mResNormalDrawble.setAlpha((int)(f * 255.0F));
      mResNormalDrawble.draw(paramCanvas);
      mResBeAddedDrawble.setAlpha((int)(mAlphaSign * 255.0F));
      mResBeAddedDrawble.draw(paramCanvas);
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    Paint.FontMetrics localFontMetrics = mTextPaintA.getFontMetrics();
    int j = (int)(bottom - top + getPaddingBottom() + getPaddingTop());
    int k = (int)(Math.max(mTextPaintA.measureText(mBtnNormalText), mTextPaintB.measureText(mBtnBeAddedText)) + 20.0F);
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
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    mBackgroundBound = new Rect(0, 0, paramInt1, paramInt2);
    Paint.FontMetricsInt localFontMetricsInt = mTextPaintA.getFontMetricsInt();
    mBaseline = (mBackgroundBound.centerY() - (bottom - top) / 2 - top);
    mResBeAddedDrawble.setBounds(mBackgroundBound);
    mResNormalDrawble.setBounds(mBackgroundBound);
  }
  
  public void onStartAnimation()
  {
    if (!mIsSelected) {}
    for (boolean bool = true;; bool = false)
    {
      mIsSelected = bool;
      if (mAlphaSign <= 0.0F) {
        break;
      }
      startAnimator(mAlphaSign, MIN_ALPHASIGN, mAnimDuration);
      return;
    }
    startAnimator(mAlphaSign, MAX_ALPHASIGN, mAnimDuration);
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
    if (!mManuaStartAnim) {
      onStartAnimation();
    }
    return super.performClick();
  }
  
  public void setAnimDuration(int paramInt)
  {
    mAnimDuration = paramInt;
  }
  
  public void setBtnBeAddedText(String paramString)
  {
    String str = mBtnBeAddedText;
    mBtnBeAddedText = paramString;
    if (mTextPaintB.measureText(str) != mTextPaintB.measureText(mBtnBeAddedText)) {
      requestLayout();
    }
    invalidate();
  }
  
  public void setBtnBeAddedTextColor(int paramInt)
  {
    mTextPaintB.setColor(paramInt);
    invalidate();
  }
  
  public void setBtnNormalText(String paramString)
  {
    String str = mBtnNormalText;
    mBtnNormalText = paramString;
    if (mTextPaintB.measureText(str) != mTextPaintB.measureText(mBtnNormalText)) {
      requestLayout();
    }
    invalidate();
  }
  
  public void setBtnNormalTextColor(int paramInt)
  {
    mTextPaintA.setColor(paramInt);
    invalidate();
  }
  
  public void setBtnSubTextSize(int paramInt)
  {
    mTextPaintA.setTextSize(paramInt);
    mTextPaintB.setTextSize(paramInt);
    if (mBackgroundBound != null)
    {
      Paint.FontMetricsInt localFontMetricsInt = mTextPaintA.getFontMetricsInt();
      mBaseline = (mBackgroundBound.centerY() - (bottom - top) / 2 - top);
    }
    invalidate();
  }
  
  public void setManuaStartAnim(boolean paramBoolean)
  {
    mManuaStartAnim = paramBoolean;
  }
  
  public void setSelectedable(boolean paramBoolean)
  {
    if (mIsSelected != paramBoolean)
    {
      mIsSelected = paramBoolean;
      if (mIsSelected)
      {
        setAlphaSign(1.0F);
        startAnimator(MIN_ALPHASIGN, mAlphaSign, 0);
      }
    }
    else
    {
      return;
    }
    setAlphaSign(0.0F);
    startAnimator(MAX_ALPHASIGN, mAlphaSign, 0);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.SubscribeButton
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */