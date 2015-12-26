package com.android.ex.editstyledtext;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.util.Log;

public class EditStyledText$c$a
  extends ShapeDrawable
{
  private static boolean c = false;
  private Spannable a;
  private int b;
  
  private void a()
  {
    Object localObject = b();
    Spannable localSpannable = a;
    localObject = (ForegroundColorSpan[])localSpannable.getSpans(localSpannable.getSpanStart(localObject), localSpannable.getSpanEnd(localObject), ForegroundColorSpan.class);
    if (c) {
      Log.d("EditStyledTextSpan", "--- renewColor:" + localObject.length);
    }
    if (localObject.length > 0) {
      b(localObject[(localObject.length - 1)].getForegroundColor());
    }
  }
  
  private EditStyledText.c.b b()
  {
    Object localObject = a;
    localObject = (EditStyledText.c.b[])((Spannable)localObject).getSpans(0, ((Spannable)localObject).length(), EditStyledText.c.b.class);
    if (localObject.length > 0)
    {
      int j = localObject.length;
      int i = 0;
      while (i < j)
      {
        EditStyledText.c.b localb = localObject[i];
        if (localb.getDrawable() == this) {
          return localb;
        }
        i += 1;
      }
    }
    Log.e("EditStyledTextSpan", "---renewBounds: Couldn't find");
    return null;
  }
  
  private void b(int paramInt)
  {
    if (c) {
      Log.d("EditStyledTextSpan", "--- renewColor:" + paramInt);
    }
    getPaint().setColor(paramInt);
  }
  
  public void a(int paramInt)
  {
    if (c) {
      Log.d("EditStyledTextSpan", "--- renewBounds:" + paramInt);
    }
    int i = paramInt;
    if (paramInt > 20) {
      i = paramInt - 20;
    }
    b = i;
    setBounds(0, 0, i, 20);
  }
  
  public void draw(Canvas paramCanvas)
  {
    a();
    paramCanvas.drawRect(new Rect(0, 9, b, 11), getPaint());
  }
}

/* Location:
 * Qualified Name:     com.android.ex.editstyledtext.EditStyledText.c.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */