package com.android.ex.editstyledtext;

import android.graphics.Color;
import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.util.Log;

public class EditStyledText$c$c
  extends CharacterStyle
{
  private int a;
  private int b;
  
  public EditStyledText$c$c(int paramInt1, int paramInt2)
  {
    a = paramInt1;
    b(paramInt1);
    b = a(paramInt1, paramInt2);
  }
  
  private int a(int paramInt1, int paramInt2)
  {
    int m = Color.alpha(paramInt2);
    int j = Color.red(paramInt2);
    int k = Color.green(paramInt2);
    int n = Color.blue(paramInt2);
    int i = m;
    if (m == 0) {
      i = 128;
    }
    switch (paramInt1)
    {
    default: 
      Log.e("EditStyledText", "--- getMarqueeColor: got illigal marquee ID.");
      return 16777215;
    case 0: 
      if (j > 128)
      {
        paramInt2 = j / 2;
        paramInt1 = k;
      }
    case 1: 
      for (;;)
      {
        return Color.argb(i, paramInt2, paramInt1, n);
        paramInt2 = (255 - j) / 2;
        paramInt1 = k;
        continue;
        if (k > 128)
        {
          paramInt1 = k / 2;
          paramInt2 = j;
        }
        else
        {
          paramInt1 = (255 - k) / 2;
          paramInt2 = j;
        }
      }
    }
    return 16777215;
  }
  
  private boolean b(int paramInt)
  {
    if ((paramInt == 0) || (paramInt == 1)) {
      return true;
    }
    Log.e("EditStyledTextSpan", "--- Invalid type of MarqueeSpan");
    return false;
  }
  
  public void a(int paramInt)
  {
    b = a(a, paramInt);
  }
  
  public void updateDrawState(TextPaint paramTextPaint)
  {
    bgColor = b;
  }
}

/* Location:
 * Qualified Name:     com.android.ex.editstyledtext.EditStyledText.c.c
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */