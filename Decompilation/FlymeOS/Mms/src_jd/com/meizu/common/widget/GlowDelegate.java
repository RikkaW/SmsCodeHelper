package com.meizu.common.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.meizu.common.R.drawable;

public class GlowDelegate
{
  private static final String TAG = "GlowDelegate";
  final float BUTTON_QUIESCENT_ALPHA = 0.7F;
  final float GLOW_MAX_SCALE_FACTOR = 1.0F;
  final float GLOW_MIN_SCALE_FACTOR = 0.72F;
  float mDrawingAlpha = 1.0F;
  float mGlowAlpha = 0.0F;
  Drawable mGlowBG;
  int mGlowHeight;
  float mGlowScale = 1.0F;
  int mGlowWidth;
  private boolean mPressed = false;
  AnimatorSet mPressedAnim;
  RectF mRect = new RectF(0.0F, 0.0F, 0.0F, 0.0F);
  private View mView;
  
  public GlowDelegate(Context paramContext, View paramView)
  {
    mView = paramView;
    mGlowBG = paramContext.getResources().getDrawable(R.drawable.mz_ic_actionbar_highlight);
    if (mGlowBG != null)
    {
      setDrawingAlpha(0.7F);
      mGlowWidth = mGlowBG.getIntrinsicWidth();
      mGlowHeight = mGlowBG.getIntrinsicHeight();
    }
  }
  
  private void invalidate()
  {
    mView.invalidate();
  }
  
  public float getDrawingAlpha()
  {
    if (mGlowBG == null) {
      return 0.0F;
    }
    return mDrawingAlpha;
  }
  
  public float getGlowAlpha()
  {
    if (mGlowBG == null) {
      return 0.0F;
    }
    return mGlowAlpha;
  }
  
  public float getGlowScale()
  {
    if (mGlowBG == null) {
      return 0.0F;
    }
    return mGlowScale;
  }
  
  public void invalidateGlobalRegion(View paramView, RectF paramRectF)
  {
    while ((paramView.getParent() != null) && ((paramView.getParent() instanceof View)))
    {
      paramView = (View)paramView.getParent();
      paramView.getMatrix().mapRect(paramRectF);
      paramView.invalidate((int)Math.floor(left), (int)Math.floor(top), (int)Math.ceil(right), (int)Math.ceil(bottom));
    }
  }
  
  public boolean isPressed()
  {
    return mPressed;
  }
  
  public void jumpToCurrentState()
  {
    if ((mPressedAnim != null) && (mPressedAnim.isRunning()))
    {
      mPressedAnim.cancel();
      setGlowAlpha(0.0F);
      setGlowScale(0.72F);
    }
  }
  
  public void onDraw(Canvas paramCanvas)
  {
    if (mGlowBG != null)
    {
      paramCanvas.save();
      int i = mView.getWidth();
      int j = mView.getHeight();
      int k = mGlowWidth;
      int m = mGlowHeight;
      int n = (k - i) / 2;
      int i1 = (m - j) / 2;
      paramCanvas.scale(mGlowScale, mGlowScale, i * 0.5F, j * 0.5F);
      mGlowBG.setBounds(-n, -i1, k - n, m - i1);
      mGlowBG.setAlpha((int)(mDrawingAlpha * mGlowAlpha * 255.0F));
      mGlowBG.draw(paramCanvas);
      paramCanvas.restore();
      mRect.right = i;
      mRect.bottom = j;
    }
  }
  
  public void setDrawingAlpha(float paramFloat)
  {
    if (mGlowBG == null) {
      return;
    }
    mDrawingAlpha = paramFloat;
  }
  
  public void setGlowAlpha(float paramFloat)
  {
    if (mGlowBG == null) {
      return;
    }
    mGlowAlpha = paramFloat;
    invalidate();
  }
  
  public void setGlowBackground(Drawable paramDrawable)
  {
    mGlowBG = paramDrawable;
    if (mGlowBG != null)
    {
      setDrawingAlpha(0.7F);
      mGlowWidth = mGlowBG.getIntrinsicWidth();
      mGlowHeight = mGlowBG.getIntrinsicHeight();
    }
  }
  
  public void setGlowScale(float paramFloat)
  {
    if (mGlowBG == null) {}
    do
    {
      return;
      mGlowScale = paramFloat;
      float f = mView.getWidth();
      paramFloat = mView.getHeight();
      f = (mGlowWidth * mGlowScale - f) / 2.0F + 1.0F;
      paramFloat = (mGlowHeight * mGlowScale - paramFloat) / 2.0F + 1.0F;
      invalidateGlobalRegion(mView, new RectF(mView.getLeft() - f, mView.getTop() - paramFloat, f + mView.getRight(), paramFloat + mView.getBottom()));
    } while (mView.getParent() == null);
    ((View)mView.getParent()).invalidate();
  }
  
  public void setPressed(boolean paramBoolean)
  {
    AnimatorSet localAnimatorSet;
    if ((mGlowBG != null) && (paramBoolean != isPressed()))
    {
      if ((mPressedAnim != null) && (mPressedAnim.isRunning())) {
        mPressedAnim.cancel();
      }
      localAnimatorSet = new AnimatorSet();
      mPressedAnim = localAnimatorSet;
      if (!paramBoolean) {
        break label115;
      }
      if (mGlowScale < 1.0F) {
        mGlowScale = 1.0F;
      }
      if (mGlowAlpha < 0.7F) {
        mGlowAlpha = 0.7F;
      }
      setDrawingAlpha(1.0F);
      setGlowScale(1.0F);
      setGlowAlpha(1.0F);
    }
    for (;;)
    {
      localAnimatorSet.start();
      mPressed = paramBoolean;
      invalidate();
      return;
      label115:
      setDrawingAlpha(1.0F);
      localAnimatorSet.playTogether(new Animator[] { ObjectAnimator.ofFloat(this, "glowAlpha", new float[] { 0.0F }), ObjectAnimator.ofFloat(this, "glowScale", new float[] { 0.72F }) });
      localAnimatorSet.setDuration(416L);
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.GlowDelegate
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */