package com.android.ex.editstyledtext;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.style.ImageSpan;
import android.util.Log;
import java.io.InputStream;

public class EditStyledText$c$d
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

/* Location:
 * Qualified Name:     com.android.ex.editstyledtext.EditStyledText.c.d
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */