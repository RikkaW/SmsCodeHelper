package com.android.mms.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class SwitchImageButton
  extends ImageButton
  implements View.OnClickListener
{
  private int a = 0;
  private int[] b;
  private a[] c;
  private boolean[] d;
  
  public SwitchImageButton(Context paramContext)
  {
    super(paramContext);
  }
  
  public SwitchImageButton(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public SwitchImageButton(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  public SwitchImageButton(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    super(paramContext, paramAttributeSet, paramInt1, paramInt2);
  }
  
  public void a(int paramInt)
  {
    a = paramInt;
    setImageResource(b[paramInt]);
    setEnabled(d[paramInt]);
  }
  
  public void a(int paramInt1, int paramInt2)
  {
    if (a == paramInt1)
    {
      setImageResource(paramInt2);
      setEnabled(d[paramInt1]);
      return;
    }
    b[paramInt1] = paramInt2;
  }
  
  public void a(boolean paramBoolean, int paramInt)
  {
    d[paramInt] = paramBoolean;
    if (paramInt == a) {
      setEnabled(paramBoolean);
    }
  }
  
  public void a(int[] paramArrayOfInt, boolean[] paramArrayOfBoolean)
  {
    b = paramArrayOfInt;
    d = paramArrayOfBoolean;
  }
  
  public void onClick(View paramView)
  {
    c[a].a();
  }
  
  public void setOnClickListeners(a[] paramArrayOfa)
  {
    c = paramArrayOfa;
    setOnClickListener(this);
  }
  
  public static abstract interface a
  {
    public abstract void a();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.view.SwitchImageButton
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */