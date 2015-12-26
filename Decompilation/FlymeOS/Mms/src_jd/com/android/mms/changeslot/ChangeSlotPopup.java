package com.android.mms.changeslot;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

public class ChangeSlotPopup
  extends LinearLayout
{
  public static int a;
  public static int b;
  private Context c;
  
  public ChangeSlotPopup(Context paramContext)
  {
    super(paramContext);
    c = paramContext;
  }
  
  public ChangeSlotPopup(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    c = paramContext;
  }
  
  public ChangeSlotPopup(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    c = paramContext;
  }
  
  public ChangeSlotPopup(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    super(paramContext, paramAttributeSet, paramInt1, paramInt2);
    c = paramContext;
  }
  
  public void a(boolean paramBoolean, int paramInt1, int paramInt2, ChangeSlotItem.a parama)
  {
    ChangeSlotItem localChangeSlotItem = (ChangeSlotItem)LayoutInflater.from(c).inflate(2130968608, null);
    localChangeSlotItem.setIsInChoose(paramBoolean);
    localChangeSlotItem.a(paramInt1);
    localChangeSlotItem.a();
    localChangeSlotItem.setChangeSlotItemClickCallback(parama);
    addView(localChangeSlotItem, paramInt2, new ViewGroup.LayoutParams(a, b));
  }
  
  protected void onFinishInflate()
  {
    super.onFinishInflate();
    if (a == 0)
    {
      a = c.getResources().getDimensionPixelSize(2131558655);
      b = c.getResources().getDimensionPixelSize(2131558654);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.changeslot.ChangeSlotPopup
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */