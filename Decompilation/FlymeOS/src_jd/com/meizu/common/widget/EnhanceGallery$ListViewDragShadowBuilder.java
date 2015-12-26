package com.meizu.common.widget;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.DragShadowBuilder;

class EnhanceGallery$ListViewDragShadowBuilder
  extends View.DragShadowBuilder
{
  private static final int STATE_ENTER_NORMAL = 0;
  private static final int STATE_ENTER_WARNING = 1;
  private static final int STATE_IDLE = -1;
  private Drawable mBackground;
  private Rect mBackgroundPadding;
  private int mHeight;
  private Drawable mHightLightNormal;
  private Drawable mHightLightWarning;
  private boolean mNeedBackground = true;
  private Point mShowPoint = null;
  private int mState = -1;
  private int mWidth;
  
  public EnhanceGallery$ListViewDragShadowBuilder(EnhanceGallery paramEnhanceGallery, View paramView)
  {
    this(paramEnhanceGallery, paramView, true, null);
  }
  
  public EnhanceGallery$ListViewDragShadowBuilder(EnhanceGallery paramEnhanceGallery, View paramView, boolean paramBoolean, Point paramPoint)
  {
    super(paramView);
    mNeedBackground = paramBoolean;
    mShowPoint = paramPoint;
    if (paramView == null) {
      return;
    }
    if (paramBoolean)
    {
      mBackground = paramEnhanceGallery.getResources().getDrawable(EnhanceGallery.access$1400(paramEnhanceGallery));
      mBackgroundPadding = new Rect();
      mBackground.getPadding(mBackgroundPadding);
      paramPoint = mBackgroundPadding;
      int j = paramView.getWidth();
      int i = paramView.getHeight();
      mWidth = (j + left + right);
      j = top;
      mHeight = (bottom + (j + i));
      mBackground.setBounds(0, 0, mWidth, mHeight);
      mHightLightNormal = paramEnhanceGallery.getResources().getDrawable(EnhanceGallery.access$1500(paramEnhanceGallery));
      mHightLightNormal.setBounds(0, 0, mWidth, mHeight);
      mHightLightWarning = paramEnhanceGallery.getResources().getDrawable(EnhanceGallery.access$1600(paramEnhanceGallery));
      mHightLightWarning.setBounds(0, 0, mWidth, mHeight);
    }
    for (;;)
    {
      EnhanceGallery.access$1702(paramEnhanceGallery, 0);
      if (mHeight <= paramEnhanceGallery.getHeight()) {
        break;
      }
      paramPoint = new int[2];
      paramEnhanceGallery.getLocationOnScreen(paramPoint);
      int[] arrayOfInt = new int[2];
      paramView.getLocationOnScreen(arrayOfInt);
      if (arrayOfInt[1] < paramPoint[1])
      {
        EnhanceGallery.access$1702(paramEnhanceGallery, paramPoint[1] - arrayOfInt[1]);
        EnhanceGallery.access$1702(paramEnhanceGallery, Math.min(mHeight - paramEnhanceGallery.getHeight(), EnhanceGallery.access$1700(paramEnhanceGallery)));
      }
      mHeight = paramEnhanceGallery.getHeight();
      return;
      mWidth = paramView.getWidth();
      mHeight = paramView.getHeight();
    }
  }
  
  public int getDragingState()
  {
    return mState;
  }
  
  public void onDrawShadow(Canvas paramCanvas)
  {
    if (mNeedBackground)
    {
      if (mState == 0) {
        mHightLightNormal.draw(paramCanvas);
      }
      for (;;)
      {
        paramCanvas.save();
        paramCanvas.translate(mBackgroundPadding.left, mBackgroundPadding.top - EnhanceGallery.access$1700(this$0));
        super.onDrawShadow(paramCanvas);
        paramCanvas.restore();
        return;
        if (mState == 1) {
          mHightLightWarning.draw(paramCanvas);
        } else {
          mBackground.draw(paramCanvas);
        }
      }
    }
    if (EnhanceGallery.access$1700(this$0) != 0)
    {
      paramCanvas.save();
      paramCanvas.translate(0.0F, -EnhanceGallery.access$1700(this$0));
      super.onDrawShadow(paramCanvas);
      paramCanvas.restore();
      return;
    }
    super.onDrawShadow(paramCanvas);
  }
  
  public void onProvideShadowMetrics(Point paramPoint1, Point paramPoint2)
  {
    super.onProvideShadowMetrics(paramPoint1, paramPoint2);
    paramPoint1.set(mWidth, mHeight);
    if (mNeedBackground)
    {
      paramPoint2.set(EnhanceGallery.access$1800(this$0) + mBackgroundPadding.left, EnhanceGallery.access$1900(this$0) + mBackgroundPadding.top - EnhanceGallery.access$1700(this$0));
      return;
    }
    paramPoint2.set(EnhanceGallery.access$1800(this$0), EnhanceGallery.access$1900(this$0) - EnhanceGallery.access$1700(this$0));
  }
  
  public void setDragingState(int paramInt)
  {
    mState = paramInt;
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.EnhanceGallery.ListViewDragShadowBuilder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */