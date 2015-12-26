package android.support.v7.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewDebug.ExportedProperty;
import android.view.ViewGroup.LayoutParams;

public class ActionMenuView$LayoutParams
  extends LinearLayoutCompat.LayoutParams
{
  @ViewDebug.ExportedProperty
  public int cellsUsed;
  @ViewDebug.ExportedProperty
  public boolean expandable;
  boolean expanded;
  @ViewDebug.ExportedProperty
  public int extraPixels;
  @ViewDebug.ExportedProperty
  public boolean isOverflowButton;
  @ViewDebug.ExportedProperty
  public boolean preventEdgeOffset;
  
  public ActionMenuView$LayoutParams(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
    isOverflowButton = false;
  }
  
  ActionMenuView$LayoutParams(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    super(paramInt1, paramInt2);
    isOverflowButton = paramBoolean;
  }
  
  public ActionMenuView$LayoutParams(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public ActionMenuView$LayoutParams(LayoutParams paramLayoutParams)
  {
    super(paramLayoutParams);
    isOverflowButton = isOverflowButton;
  }
  
  public ActionMenuView$LayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    super(paramLayoutParams);
  }
}

/* Location:
 * Qualified Name:     android.support.v7.widget.ActionMenuView.LayoutParams
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */