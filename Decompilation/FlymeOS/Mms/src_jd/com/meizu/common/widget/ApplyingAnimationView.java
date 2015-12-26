package com.meizu.common.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.PathInterpolator;
import com.meizu.common.R.styleable;

public class ApplyingAnimationView
  extends View
{
  private final String[] alphaProperty = { "rAlpha", "bAlpha", "gAlpha", "oAlpha" };
  private AnimatorSet animator;
  private int bAlpha = 255;
  private boolean bDraw = false;
  private float baseX;
  private float baseY;
  private float cbPosition;
  private float cbRadius;
  private float cgPosition;
  private float cgRadius;
  private float coPosition;
  private float coRadius;
  private float crPosition;
  private float crRadius;
  private int gAlpha = 255;
  private boolean gDraw = false;
  private float halfMaxRadius;
  private float mAnimScale = 1.0F;
  private Paint mBluePaint;
  private Paint mGreenPaint;
  private boolean mIsAnimRun = false;
  private Paint mOrangePaint;
  private Paint mRedPaint;
  private boolean mStopFromUser = false;
  private float maxRadius;
  private int oAlpha = 255;
  private boolean oDraw = false;
  private float po1;
  private float po2;
  private float po3;
  private float po4;
  private final String[] positionProperty = { "crPosition", "cbPosition", "cgPosition", "coPosition" };
  private int rAlpha = 255;
  private boolean rDraw = false;
  private final String[] radiusProperty = { "crRadius", "cbRadius", "cgRadius", "coRadius" };
  
  public ApplyingAnimationView(Context paramContext)
  {
    this(paramContext, null, 0);
  }
  
  public ApplyingAnimationView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public ApplyingAnimationView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    paramAttributeSet = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.ApplyingAnimationView);
    mAnimScale = paramAttributeSet.getFloat(R.styleable.ApplyingAnimationView_mcApplyingAnimationScale, mAnimScale);
    paramAttributeSet.recycle();
    init(paramContext);
  }
  
  private Animator createAlphaAnimator(int paramInt)
  {
    ObjectAnimator[] arrayOfObjectAnimator = new ObjectAnimator[4];
    arrayOfObjectAnimator[0] = ObjectAnimator.ofInt(this, alphaProperty[paramInt], new int[] { 255, 255 });
    arrayOfObjectAnimator[0].setDuration(720L);
    arrayOfObjectAnimator[1] = ObjectAnimator.ofInt(this, alphaProperty[paramInt], new int[] { 255, 255 });
    arrayOfObjectAnimator[1].setDuration(704L);
    arrayOfObjectAnimator[2] = ObjectAnimator.ofInt(this, alphaProperty[paramInt], new int[] { 255, 0, 0, 0 });
    arrayOfObjectAnimator[2].setInterpolator(new PathInterpolator(0.33F, 0.0F, 0.4F, 1.0F));
    arrayOfObjectAnimator[2].setDuration(704L);
    arrayOfObjectAnimator[3] = ObjectAnimator.ofInt(this, alphaProperty[paramInt], new int[] { 0, 255, 255 });
    arrayOfObjectAnimator[3].setInterpolator(new PathInterpolator(0.33F, 0.0F, 0.4F, 1.0F));
    arrayOfObjectAnimator[3].setDuration(688L);
    AnimatorSet localAnimatorSet = new AnimatorSet();
    localAnimatorSet.playSequentially(new Animator[] { arrayOfObjectAnimator[(paramInt % 4)], arrayOfObjectAnimator[((paramInt + 1) % 4)], arrayOfObjectAnimator[((paramInt + 2) % 4)], arrayOfObjectAnimator[((paramInt + 3) % 4)] });
    return localAnimatorSet;
  }
  
  private Paint createCommonPaint()
  {
    Paint localPaint = new Paint();
    localPaint.setAntiAlias(true);
    localPaint.setStyle(Paint.Style.FILL);
    return localPaint;
  }
  
  private Animator createPositionAnimator(int paramInt)
  {
    ObjectAnimator[] arrayOfObjectAnimator = new ObjectAnimator[4];
    arrayOfObjectAnimator[0] = ObjectAnimator.ofFloat(this, positionProperty[paramInt], new float[] { 0.0F, po2 });
    arrayOfObjectAnimator[0].setDuration(704L);
    arrayOfObjectAnimator[0].setInterpolator(new PathInterpolator(0.21F, 0.0F, 0.35F, 0.471F));
    arrayOfObjectAnimator[1] = ObjectAnimator.ofFloat(this, positionProperty[paramInt], new float[] { po2, po3 });
    arrayOfObjectAnimator[1].setDuration(704L);
    arrayOfObjectAnimator[1].setInterpolator(new PathInterpolator(0.24F, 0.341F, 0.41F, 1.0F));
    arrayOfObjectAnimator[2] = ObjectAnimator.ofFloat(this, positionProperty[paramInt], new float[] { po3, po4 });
    arrayOfObjectAnimator[2].setDuration(672L);
    arrayOfObjectAnimator[2].setInterpolator(new PathInterpolator(0.26F, 0.0F, 0.87F, 0.758F));
    arrayOfObjectAnimator[3] = ObjectAnimator.ofFloat(this, positionProperty[paramInt], new float[] { po4, po1 });
    arrayOfObjectAnimator[3].setDuration(736L);
    arrayOfObjectAnimator[3].setInterpolator(new PathInterpolator(0.18F, 0.434F, 0.59F, 1.0F));
    AnimatorSet localAnimatorSet = new AnimatorSet();
    localAnimatorSet.playSequentially(new Animator[] { arrayOfObjectAnimator[(paramInt % 4)], arrayOfObjectAnimator[((paramInt + 1) % 4)], arrayOfObjectAnimator[((paramInt + 2) % 4)], arrayOfObjectAnimator[((paramInt + 3) % 4)] });
    return localAnimatorSet;
  }
  
  private Animator createRadiusAnimator(int paramInt)
  {
    ObjectAnimator[] arrayOfObjectAnimator = new ObjectAnimator[4];
    arrayOfObjectAnimator[0] = ObjectAnimator.ofFloat(this, radiusProperty[paramInt], new float[] { halfMaxRadius, maxRadius });
    arrayOfObjectAnimator[0].setInterpolator(new PathInterpolator(0.24F, 0.209F, 0.25F, 1.0F));
    arrayOfObjectAnimator[0].setDuration(720L);
    arrayOfObjectAnimator[1] = ObjectAnimator.ofFloat(this, radiusProperty[paramInt], new float[] { maxRadius, halfMaxRadius });
    arrayOfObjectAnimator[1].setInterpolator(new PathInterpolator(0.29F, 0.0F, 0.32F, 0.631F));
    arrayOfObjectAnimator[1].setDuration(704L);
    arrayOfObjectAnimator[2] = ObjectAnimator.ofFloat(this, radiusProperty[paramInt], new float[] { halfMaxRadius, maxRadius * 0.25F });
    arrayOfObjectAnimator[2].setInterpolator(new PathInterpolator(0.2F, 0.337F, 0.17F, 1.0F));
    arrayOfObjectAnimator[2].setDuration(704L);
    arrayOfObjectAnimator[3] = ObjectAnimator.ofFloat(this, radiusProperty[paramInt], new float[] { maxRadius * 0.25F, halfMaxRadius });
    arrayOfObjectAnimator[3].setInterpolator(new PathInterpolator(0.19F, 0.0F, 0.37F, 0.31F));
    arrayOfObjectAnimator[3].setDuration(688L);
    AnimatorSet localAnimatorSet = new AnimatorSet();
    localAnimatorSet.playSequentially(new Animator[] { arrayOfObjectAnimator[(paramInt % 4)], arrayOfObjectAnimator[((paramInt + 1) % 4)], arrayOfObjectAnimator[((paramInt + 2) % 4)], arrayOfObjectAnimator[((paramInt + 3) % 4)] });
    return localAnimatorSet;
  }
  
  private void init(Context paramContext)
  {
    initData(paramContext);
    mRedPaint = createCommonPaint();
    mRedPaint.setColor(-1357238);
    mBluePaint = createCommonPaint();
    mBluePaint.setColor(-16737828);
    mGreenPaint = createCommonPaint();
    mGreenPaint.setColor(110475);
    mOrangePaint = createCommonPaint();
    mOrangePaint.setColor(-620493);
  }
  
  private void initData(Context paramContext)
  {
    float f = getDensity(paramContext) * mAnimScale;
    maxRadius = (6.0F * f);
    halfMaxRadius = (maxRadius * 0.5F);
    po1 = 0.0F;
    po2 = (12.3F * f);
    po3 = (24.0F * f);
    po4 = (f * 11.0F);
    baseX = (getX() + halfMaxRadius + mAnimScale * 2.0F);
    baseY = getY();
  }
  
  private void setBAlpha(int paramInt)
  {
    bAlpha = Math.round(paramInt);
    mBluePaint.setAlpha(bAlpha);
  }
  
  private void setCbPosition(float paramFloat)
  {
    cbPosition = paramFloat;
  }
  
  private void setCbRadius(float paramFloat)
  {
    cbRadius = paramFloat;
  }
  
  private void setCgPosition(float paramFloat)
  {
    cgPosition = paramFloat;
  }
  
  private void setCgRadius(float paramFloat)
  {
    cgRadius = paramFloat;
  }
  
  private void setCoPosition(float paramFloat)
  {
    coPosition = paramFloat;
    invalidate();
  }
  
  private void setCoRadius(float paramFloat)
  {
    coRadius = paramFloat;
  }
  
  private void setCrPosition(float paramFloat)
  {
    crPosition = paramFloat;
  }
  
  private void setCrRadius(float paramFloat)
  {
    crRadius = paramFloat;
  }
  
  private void setGAlpha(int paramInt)
  {
    gAlpha = Math.round(paramInt);
    mGreenPaint.setAlpha(gAlpha);
  }
  
  private void setOAlpha(int paramInt)
  {
    oAlpha = Math.round(paramInt);
    mOrangePaint.setAlpha(oAlpha);
  }
  
  private void setRAlpha(int paramInt)
  {
    rAlpha = Math.round(paramInt);
    mRedPaint.setAlpha(rAlpha);
  }
  
  private void startAnimator()
  {
    if (mIsAnimRun) {
      return;
    }
    Object localObject = createPositionAnimator(0);
    Animator localAnimator1 = createPositionAnimator(1);
    Animator localAnimator2 = createPositionAnimator(2);
    Animator localAnimator3 = createPositionAnimator(3);
    AnimatorSet localAnimatorSet1 = new AnimatorSet();
    localAnimatorSet1.playTogether(new Animator[] { localObject, localAnimator1, localAnimator2, localAnimator3 });
    localAnimator1 = createRadiusAnimator(0);
    localAnimator2 = createRadiusAnimator(1);
    localAnimator3 = createRadiusAnimator(2);
    Animator localAnimator4 = createRadiusAnimator(3);
    localObject = new AnimatorSet();
    ((AnimatorSet)localObject).playTogether(new Animator[] { localAnimator1, localAnimator2, localAnimator3, localAnimator4 });
    localAnimator1 = createAlphaAnimator(0);
    localAnimator2 = createAlphaAnimator(1);
    localAnimator3 = createAlphaAnimator(2);
    localAnimator4 = createAlphaAnimator(3);
    AnimatorSet localAnimatorSet2 = new AnimatorSet();
    localAnimatorSet2.playTogether(new Animator[] { localAnimator1, localAnimator2, localAnimator3, localAnimator4 });
    animator = new AnimatorSet();
    animator.playTogether(new Animator[] { localAnimatorSet1, localObject, localAnimatorSet2 });
    animator.addListener(new ApplyingAnimationView.1(this));
    mIsAnimRun = true;
    animator.start();
  }
  
  private void stopRunAnimator()
  {
    if (animator != null)
    {
      animator.cancel();
      mStopFromUser = true;
      mIsAnimRun = false;
      animator = null;
    }
  }
  
  public float getDensity(Context paramContext)
  {
    return getResourcesgetDisplayMetricsdensity;
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    if (crRadius < halfMaxRadius)
    {
      paramCanvas.drawCircle(baseX + crPosition, baseY + maxRadius, crRadius, mRedPaint);
      rDraw = true;
    }
    if (cbRadius < halfMaxRadius)
    {
      paramCanvas.drawCircle(baseX + cbPosition, baseY + maxRadius, cbRadius, mBluePaint);
      bDraw = true;
    }
    if (cgRadius < halfMaxRadius)
    {
      paramCanvas.drawCircle(baseX + cgPosition, baseY + maxRadius, cgRadius, mGreenPaint);
      gDraw = true;
    }
    if (coRadius < halfMaxRadius)
    {
      paramCanvas.drawCircle(baseX + coPosition, baseY + maxRadius, coRadius, mOrangePaint);
      oDraw = true;
    }
    if (!rDraw) {
      paramCanvas.drawCircle(baseX + crPosition, baseY + maxRadius, crRadius, mRedPaint);
    }
    if (!bDraw) {
      paramCanvas.drawCircle(baseX + cbPosition, baseY + maxRadius, cbRadius, mBluePaint);
    }
    if (!gDraw) {
      paramCanvas.drawCircle(baseX + cgPosition, baseY + maxRadius, cgRadius, mGreenPaint);
    }
    if (!oDraw) {
      paramCanvas.drawCircle(baseX + coPosition, baseY + maxRadius, coRadius, mOrangePaint);
    }
    rDraw = false;
    bDraw = false;
    gDraw = false;
    oDraw = false;
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i = Math.round(po3 - po1 + maxRadius + mAnimScale * 4.0F + 0.5F);
    int j = Math.round(maxRadius * 2.0F + 0.5F);
    int k = getPaddingLeft();
    int m = getPaddingRight();
    int n = getPaddingTop();
    int i1 = getPaddingBottom();
    setMeasuredDimension(resolveSizeAndState(i + (k + m), paramInt1, 0), resolveSizeAndState(j + (n + i1), paramInt2, 0));
  }
  
  protected void onVisibilityChanged(View paramView, int paramInt)
  {
    super.onVisibilityChanged(paramView, paramInt);
    if (paramInt != 0) {
      stopRunAnimator();
    }
    while (!isShown()) {
      return;
    }
    startAnimator();
    mStopFromUser = false;
  }
  
  protected void onWindowVisibilityChanged(int paramInt)
  {
    super.onWindowVisibilityChanged(paramInt);
    if (paramInt != 0) {
      stopRunAnimator();
    }
    while (!isShown()) {
      return;
    }
    startAnimator();
    mStopFromUser = false;
  }
  
  public void setVisibility(int paramInt)
  {
    super.setVisibility(paramInt);
    if (paramInt == 0)
    {
      startAnimator();
      mStopFromUser = false;
    }
    while ((paramInt != 4) && (paramInt != 8)) {
      return;
    }
    stopRunAnimator();
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.ApplyingAnimationView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */