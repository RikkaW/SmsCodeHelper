package android.support.v4.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;

public class SlidingPaneLayout$LayoutParams
  extends ViewGroup.MarginLayoutParams
{
  private static final int[] ATTRS = { 16843137 };
  Paint dimPaint;
  boolean dimWhenOffset;
  boolean slideable;
  public float weight = 0.0F;
  
  public SlidingPaneLayout$LayoutParams()
  {
    super(-1, -1);
  }
  
  public SlidingPaneLayout$LayoutParams(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
  }
  
  public SlidingPaneLayout$LayoutParams(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, ATTRS);
    weight = paramContext.getFloat(0, 0.0F);
    paramContext.recycle();
  }
  
  public SlidingPaneLayout$LayoutParams(LayoutParams paramLayoutParams)
  {
    super(paramLayoutParams);
    weight = weight;
  }
  
  public SlidingPaneLayout$LayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    super(paramLayoutParams);
  }
  
  public SlidingPaneLayout$LayoutParams(ViewGroup.MarginLayoutParams paramMarginLayoutParams)
  {
    super(paramMarginLayoutParams);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.widget.SlidingPaneLayout.LayoutParams
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */