package com.android.mms.view;

import android.content.Context;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;
import android.widget.TextView.BufferType;

public class MmsEllipTextView
  extends TextView
{
  float a = 0.0F;
  private String b = "‥";
  private boolean c = false;
  private String d;
  
  public MmsEllipTextView(Context paramContext)
  {
    super(paramContext);
  }
  
  public MmsEllipTextView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public MmsEllipTextView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  public void a(String paramString1, String paramString2)
  {
    b = ("‥" + paramString2);
    d = paramString1;
    requestLayout();
  }
  
  protected void onFinishInflate()
  {
    super.onFinishInflate();
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    float f2 = getWidth() - getPaddingLeft() - getPaddingRight();
    if ((a == f2) || (f2 <= 0.0F))
    {
      super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
      return;
    }
    a = f2;
    Object localObject2 = d.toLowerCase();
    Object localObject1 = b.toLowerCase();
    TextPaint localTextPaint = getPaint();
    float f1 = localTextPaint.measureText((String)localObject2);
    if (f1 < f2)
    {
      super.setText(d, TextView.BufferType.SPANNABLE);
      super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
      return;
    }
    f2 -= localTextPaint.measureText((String)localObject1);
    int i;
    if (f1 >= 2.0F * f2)
    {
      localObject2 = new StringBuffer();
      int k = d.length();
      int j = Math.min(5, k);
      i = j;
      if (j > 0)
      {
        ((StringBuffer)localObject2).append(d, 0, j);
        i = j;
      }
      if (i < k)
      {
        ((StringBuffer)localObject2).append(d.charAt(i));
        if (localTextPaint.measureText((((StringBuffer)localObject2).toString() + (String)localObject1).toLowerCase()) >= f2) {
          ((StringBuffer)localObject2).deleteCharAt(i);
        }
      }
      else
      {
        ((StringBuffer)localObject2).append(b);
        super.setText((CharSequence)localObject2, TextView.BufferType.SPANNABLE);
      }
    }
    for (;;)
    {
      super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
      return;
      i += 1;
      break;
      localObject1 = new StringBuffer(d);
      i = ((StringBuffer)localObject1).length();
      ((StringBuffer)localObject1).append(b);
      do
      {
        i -= 1;
        ((StringBuffer)localObject1).delete(i, i + 1);
      } while (localTextPaint.measureText(((StringBuffer)localObject1).toString().toLowerCase()) >= f2);
      super.setText((CharSequence)localObject1, TextView.BufferType.SPANNABLE);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.view.MmsEllipTextView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */