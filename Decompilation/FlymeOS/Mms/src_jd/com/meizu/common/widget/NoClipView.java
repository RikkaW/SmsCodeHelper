package com.meizu.common.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnDrawListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class NoClipView
  extends View
  implements ViewTreeObserver.OnDrawListener, ViewTreeObserver.OnGlobalLayoutListener
{
  private static final Field sDirtyField = getField();
  private static final Method sGetViewRootImplMethod = ;
  private ViewTreeObserver mViewTreeObserver;
  private Rect mVisibleRect = new Rect();
  
  public NoClipView(Context paramContext)
  {
    super(paramContext);
  }
  
  public NoClipView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public NoClipView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  private static Field getField()
  {
    try
    {
      Field localField = Class.forName("android.view.ViewRootImpl").getDeclaredField("mDirty");
      localField.setAccessible(true);
      return localField;
    }
    catch (Exception localException) {}
    return null;
  }
  
  private static Method getMethod()
  {
    try
    {
      Method localMethod = View.class.getDeclaredMethod("getViewRootImpl", new Class[0]);
      return localMethod;
    }
    catch (NoSuchMethodException localNoSuchMethodException) {}
    return null;
  }
  
  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    if (mViewTreeObserver == null)
    {
      mViewTreeObserver = getViewTreeObserver();
      mViewTreeObserver.addOnDrawListener(this);
      mViewTreeObserver.addOnGlobalLayoutListener(this);
    }
  }
  
  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    if (mViewTreeObserver != null)
    {
      mViewTreeObserver.removeOnDrawListener(this);
      mViewTreeObserver.removeOnGlobalLayoutListener(this);
      mViewTreeObserver = null;
    }
  }
  
  public void onDraw()
  {
    try
    {
      Rect localRect = (Rect)sDirtyField.get(sGetViewRootImplMethod.invoke(this, new Object[0]));
      if ((localRect != null) && (Rect.intersects(mVisibleRect, localRect)) && (!localRect.contains(mVisibleRect))) {
        localRect.union(mVisibleRect);
      }
      return;
    }
    catch (Exception localException) {}
  }
  
  public void onGlobalLayout()
  {
    getGlobalVisibleRect(mVisibleRect);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.NoClipView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */