package com.amap.api.mapcore2d;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.NinePatch;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;

class bo
{
  private static int a(byte[] paramArrayOfByte, int paramInt)
  {
    return paramArrayOfByte[(paramInt + 0)] & 0xFF | (paramArrayOfByte[(paramInt + 1)] & 0xFF) << 8 | (paramArrayOfByte[(paramInt + 2)] & 0xFF) << 16 | (paramArrayOfByte[(paramInt + 3)] & 0xFF) << 24;
  }
  
  private static Bitmap a(InputStream paramInputStream)
  {
    Object localObject = BitmapFactory.decodeStream(paramInputStream);
    paramInputStream = a((Bitmap)localObject);
    if (NinePatch.isNinePatchChunk(paramInputStream))
    {
      Bitmap localBitmap = Bitmap.createBitmap((Bitmap)localObject, 1, 1, ((Bitmap)localObject).getWidth() - 2, ((Bitmap)localObject).getHeight() - 2);
      ((Bitmap)localObject).recycle();
      localObject = localBitmap.getClass().getDeclaredField("mNinePatchChunk");
      ((Field)localObject).setAccessible(true);
      ((Field)localObject).set(localBitmap, paramInputStream);
      return localBitmap;
    }
    return (Bitmap)localObject;
  }
  
  public static Drawable a(Context paramContext, String paramString)
  {
    paramString = b(paramContext, paramString);
    if (paramString.getNinePatchChunk() == null) {
      return new BitmapDrawable(paramString);
    }
    Rect localRect = new Rect();
    a(paramString.getNinePatchChunk(), localRect);
    return new NinePatchDrawable(paramContext.getResources(), paramString, paramString.getNinePatchChunk(), localRect, null);
  }
  
  private static void a(Bitmap paramBitmap, byte[] paramArrayOfByte)
  {
    int j = 0;
    int[] arrayOfInt = new int[paramBitmap.getWidth() - 2];
    paramBitmap.getPixels(arrayOfInt, 0, arrayOfInt.length, 1, paramBitmap.getHeight() - 1, arrayOfInt.length, 1);
    int i = 0;
    if (i < arrayOfInt.length)
    {
      if (-16777216 == arrayOfInt[i]) {
        a(paramArrayOfByte, 12, i);
      }
    }
    else
    {
      i = arrayOfInt.length - 1;
      label64:
      if (i >= 0)
      {
        if (-16777216 != arrayOfInt[i]) {
          break label184;
        }
        a(paramArrayOfByte, 16, arrayOfInt.length - i - 2);
      }
      arrayOfInt = new int[paramBitmap.getHeight() - 2];
      paramBitmap.getPixels(arrayOfInt, 0, 1, paramBitmap.getWidth() - 1, 0, 1, arrayOfInt.length);
      i = j;
      label121:
      if (i < arrayOfInt.length)
      {
        if (-16777216 != arrayOfInt[i]) {
          break label191;
        }
        a(paramArrayOfByte, 20, i);
      }
      i = arrayOfInt.length - 1;
    }
    for (;;)
    {
      if (i >= 0)
      {
        if (-16777216 == arrayOfInt[i]) {
          a(paramArrayOfByte, 24, arrayOfInt.length - i - 2);
        }
      }
      else
      {
        return;
        i += 1;
        break;
        label184:
        i -= 1;
        break label64;
        label191:
        i += 1;
        break label121;
      }
      i -= 1;
    }
  }
  
  private static void a(OutputStream paramOutputStream, int paramInt)
  {
    paramOutputStream.write(paramInt >> 0 & 0xFF);
    paramOutputStream.write(paramInt >> 8 & 0xFF);
    paramOutputStream.write(paramInt >> 16 & 0xFF);
    paramOutputStream.write(paramInt >> 24 & 0xFF);
  }
  
  private static void a(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    paramArrayOfByte[(paramInt1 + 0)] = ((byte)(paramInt2 >> 0));
    paramArrayOfByte[(paramInt1 + 1)] = ((byte)(paramInt2 >> 8));
    paramArrayOfByte[(paramInt1 + 2)] = ((byte)(paramInt2 >> 16));
    paramArrayOfByte[(paramInt1 + 3)] = ((byte)(paramInt2 >> 24));
  }
  
  private static void a(byte[] paramArrayOfByte, Rect paramRect)
  {
    left = a(paramArrayOfByte, 12);
    right = a(paramArrayOfByte, 16);
    top = a(paramArrayOfByte, 20);
    bottom = a(paramArrayOfByte, 24);
  }
  
  private static byte[] a(Bitmap paramBitmap)
  {
    int j = paramBitmap.getWidth();
    int i3 = paramBitmap.getHeight();
    Object localObject = new ByteArrayOutputStream();
    int i = 0;
    while (i < 32)
    {
      ((ByteArrayOutputStream)localObject).write(0);
      i += 1;
    }
    int[] arrayOfInt = new int[j - 2];
    paramBitmap.getPixels(arrayOfInt, 0, j, 1, 0, j - 2, 1);
    int m;
    if (arrayOfInt[0] == -16777216)
    {
      m = 1;
      if (arrayOfInt[(arrayOfInt.length - 1)] != -16777216) {
        break label160;
      }
    }
    int i4;
    int i1;
    int i2;
    int n;
    label160:
    for (int k = 1;; k = 0)
    {
      i4 = arrayOfInt.length;
      j = 0;
      i1 = 0;
      for (i = 0; j < i4; i = n)
      {
        i2 = i1;
        n = i;
        if (i1 != arrayOfInt[j])
        {
          n = i + 1;
          a((OutputStream)localObject, j);
          i2 = arrayOfInt[j];
        }
        j += 1;
        i1 = i2;
      }
      m = 0;
      break;
    }
    j = i;
    if (k != 0)
    {
      j = i + 1;
      a((OutputStream)localObject, arrayOfInt.length);
    }
    i = j + 1;
    if (m != 0) {
      i -= 1;
    }
    for (;;)
    {
      if (k != 0) {}
      for (k = i - 1;; k = i)
      {
        arrayOfInt = new int[i3 - 2];
        paramBitmap.getPixels(arrayOfInt, 0, 1, 0, 1, 1, i3 - 2);
        if (arrayOfInt[0] == -16777216)
        {
          n = 1;
          if (arrayOfInt[(arrayOfInt.length - 1)] != -16777216) {
            break label333;
          }
        }
        label333:
        for (m = 1;; m = 0)
        {
          int i5 = arrayOfInt.length;
          i1 = 0;
          i3 = 0;
          for (i = 0; i1 < i5; i = i2)
          {
            i4 = i3;
            i2 = i;
            if (i3 != arrayOfInt[i1])
            {
              i2 = i + 1;
              a((OutputStream)localObject, i1);
              i4 = arrayOfInt[i1];
            }
            i1 += 1;
            i3 = i4;
          }
          n = 0;
          break;
        }
        i1 = i;
        if (m != 0)
        {
          i1 = i + 1;
          a((OutputStream)localObject, arrayOfInt.length);
        }
        i = i1 + 1;
        if (n != 0) {
          i -= 1;
        }
        for (;;)
        {
          n = i;
          if (m != 0) {
            n = i - 1;
          }
          i = 0;
          while (i < k * n)
          {
            a((OutputStream)localObject, 1);
            i += 1;
          }
          localObject = ((ByteArrayOutputStream)localObject).toByteArray();
          localObject[0] = 1;
          localObject[1] = ((byte)j);
          localObject[2] = ((byte)i1);
          localObject[3] = ((byte)(n * k));
          a(paramBitmap, (byte[])localObject);
          return (byte[])localObject;
        }
      }
    }
  }
  
  private static Bitmap b(Context paramContext, String paramString)
  {
    paramContext = paramContext.getAssets().open(paramString);
    paramString = a(paramContext);
    paramContext.close();
    return paramString;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.bo
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */