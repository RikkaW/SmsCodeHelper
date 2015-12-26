package android.support.v7.widget;

import android.content.Context;
import android.support.v7.app.ActionBar.LayoutParams;
import android.util.AttributeSet;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;

public class Toolbar$LayoutParams
  extends ActionBar.LayoutParams
{
  static final int CUSTOM = 0;
  static final int EXPANDED = 2;
  static final int SYSTEM = 1;
  int mViewType = 0;
  
  public Toolbar$LayoutParams(int paramInt)
  {
    this(-2, -1, paramInt);
  }
  
  public Toolbar$LayoutParams(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
    gravity = 8388627;
  }
  
  public Toolbar$LayoutParams(int paramInt1, int paramInt2, int paramInt3)
  {
    super(paramInt1, paramInt2);
    gravity = paramInt3;
  }
  
  public Toolbar$LayoutParams(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public Toolbar$LayoutParams(ActionBar.LayoutParams paramLayoutParams)
  {
    super(paramLayoutParams);
  }
  
  public Toolbar$LayoutParams(LayoutParams paramLayoutParams)
  {
    super(paramLayoutParams);
    mViewType = mViewType;
  }
  
  public Toolbar$LayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    super(paramLayoutParams);
  }
  
  public Toolbar$LayoutParams(ViewGroup.MarginLayoutParams paramMarginLayoutParams)
  {
    super(paramMarginLayoutParams);
    copyMarginsFromCompat(paramMarginLayoutParams);
  }
  
  void copyMarginsFromCompat(ViewGroup.MarginLayoutParams paramMarginLayoutParams)
  {
    leftMargin = leftMargin;
    topMargin = topMargin;
    rightMargin = rightMargin;
    bottomMargin = bottomMargin;
  }
}

/* Location:
 * Qualified Name:     android.support.v7.widget.Toolbar.LayoutParams
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */