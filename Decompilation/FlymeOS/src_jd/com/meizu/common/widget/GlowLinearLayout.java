package com.meizu.common.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class GlowLinearLayout
  extends LinearLayout
{
  private GlowDelegate mDelegate;
  
  public GlowLinearLayout(Context paramContext)
  {
    super(paramContext);
    initView(paramContext);
  }
  
  public GlowLinearLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initView(paramContext);
  }
  
  public GlowLinearLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    initView(paramContext);
  }
  
  private void initView(Context paramContext)
  {
    mDelegate = new GlowDelegate(paramContext, this);
    mDelegate.setGlowBackground(getBackground());
    setBackground(null);
  }
  
  protected void dispatchDraw(Canvas paramCanvas)
  {
    super.dispatchDraw(paramCanvas);
    mDelegate.onDraw(paramCanvas);
  }
  
  public float getDrawingAlpha()
  {
    return mDelegate.getGlowAlpha();
  }
  
  public float getGlowAlpha()
  {
    return mDelegate.getGlowAlpha();
  }
  
  public float getGlowScale()
  {
    return mDelegate.getGlowScale();
  }
  
  public void setBackground(Drawable paramDrawable)
  {
    super.setBackground(paramDrawable);
    if ((mDelegate != null) && (getBackground() != null))
    {
      mDelegate.setGlowBackground(paramDrawable);
      super.setBackground(null);
    }
  }
  
  public void setDrawingAlpha(float paramFloat)
  {
    mDelegate.setDrawingAlpha(paramFloat);
  }
  
  public void setGlowAlpha(float paramFloat)
  {
    mDelegate.setGlowAlpha(paramFloat);
  }
  
  public void setGlowScale(float paramFloat)
  {
    mDelegate.setGlowScale(paramFloat);
  }
  
  public void setPressed(boolean paramBoolean)
  {
    mDelegate.setPressed(paramBoolean);
    super.setPressed(paramBoolean);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.GlowLinearLayout
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */