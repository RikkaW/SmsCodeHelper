package com.android.ex.editstyledtext;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.net.Uri;
import android.text.Spannable;
import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.util.Log;
import java.io.InputStream;

public class EditStyledText$c
{
  public static class a
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
  
  public static class b
    extends DynamicDrawableSpan
  {
    EditStyledText.c.a a;
    
    public void a(int paramInt)
    {
      a.a(paramInt);
    }
    
    public Drawable getDrawable()
    {
      return a;
    }
  }
  
  public static class c
    extends CharacterStyle
  {
    private int a;
    private int b;
    
    public c(int paramInt1, int paramInt2)
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
  
  public static class d
    extends ImageSpan
  {
    Uri a;
    public int b;
    public int c;
    private Drawable d;
    private Context e;
    private final int f;
    
    private void a(Drawable paramDrawable)
    {
      Log.d("EditStyledTextSpan", "--- rescaleBigImage:");
      if (f < 0) {
        return;
      }
      int m = paramDrawable.getIntrinsicWidth();
      int k = paramDrawable.getIntrinsicHeight();
      Log.d("EditStyledTextSpan", "--- rescaleBigImage:" + m + "," + k + "," + f);
      int j = k;
      int i = m;
      if (m > f)
      {
        i = f;
        j = k * f / i;
      }
      paramDrawable.setBounds(0, 0, i, j);
    }
    
    public Drawable getDrawable()
    {
      if (d != null) {
        return d;
      }
      if (a != null) {
        System.gc();
      }
      for (;;)
      {
        try
        {
          InputStream localInputStream = e.getContentResolver().openInputStream(a);
          Object localObject = new BitmapFactory.Options();
          inJustDecodeBounds = true;
          BitmapFactory.decodeStream(localInputStream, null, (BitmapFactory.Options)localObject);
          localInputStream.close();
          localInputStream = e.getContentResolver().openInputStream(a);
          int j = outWidth;
          int i = outHeight;
          b = j;
          c = i;
          if (outWidth > f)
          {
            j = f;
            i = i * f / outWidth;
            localObject = BitmapFactory.decodeStream(localInputStream, new Rect(0, 0, j, i), null);
            d = new BitmapDrawable(e.getResources(), (Bitmap)localObject);
            d.setBounds(0, 0, j, i);
            localInputStream.close();
            return d;
          }
          localObject = BitmapFactory.decodeStream(localInputStream);
          continue;
          d = super.getDrawable();
        }
        catch (Exception localException)
        {
          Log.e("EditStyledTextSpan", "Failed to loaded content " + a, localException);
          return null;
        }
        catch (OutOfMemoryError localOutOfMemoryError)
        {
          Log.e("EditStyledTextSpan", "OutOfMemoryError");
          return null;
        }
        a(d);
        b = d.getIntrinsicWidth();
        c = d.getIntrinsicHeight();
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.ex.editstyledtext.EditStyledText.c
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */