package com.amap.api.mapcore2d;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import java.io.FileDescriptor;

class cu
  extends cv
{
  private int b;
  private int c;
  
  cu(Context paramContext, int paramInt1, int paramInt2)
  {
    super(paramContext);
    a(paramInt1, paramInt2);
  }
  
  public static int a(BitmapFactory.Options paramOptions, int paramInt1, int paramInt2)
  {
    int k = outHeight;
    int m = outWidth;
    int j = 1;
    if ((k > paramInt2) || (m > paramInt1))
    {
      int i = Math.round(k / paramInt2);
      j = Math.round(m / paramInt1);
      if (i < j) {}
      for (;;)
      {
        float f1 = m * k;
        float f2 = paramInt1 * paramInt2 * 2;
        for (;;)
        {
          j = i;
          if (f1 / (i * i) <= f2) {
            break;
          }
          i += 1;
        }
        i = j;
      }
    }
    return j;
  }
  
  private Bitmap a(int paramInt)
  {
    cw.a("ImageResizer", "processBitmap - " + paramInt, 111);
    return a(a, paramInt, b, c, a());
  }
  
  public static Bitmap a(Resources paramResources, int paramInt1, int paramInt2, int paramInt3, cs paramcs)
  {
    paramcs = new BitmapFactory.Options();
    inJustDecodeBounds = true;
    BitmapFactory.decodeResource(paramResources, paramInt1, paramcs);
    inSampleSize = a(paramcs, paramInt2, paramInt3);
    inJustDecodeBounds = false;
    return BitmapFactory.decodeResource(paramResources, paramInt1, paramcs);
  }
  
  public static Bitmap a(FileDescriptor paramFileDescriptor, int paramInt1, int paramInt2, cs paramcs)
  {
    paramcs = new BitmapFactory.Options();
    inJustDecodeBounds = true;
    BitmapFactory.decodeFileDescriptor(paramFileDescriptor, null, paramcs);
    inSampleSize = a(paramcs, paramInt1, paramInt2);
    inJustDecodeBounds = false;
    return BitmapFactory.decodeFileDescriptor(paramFileDescriptor, null, paramcs);
  }
  
  protected Bitmap a(Object paramObject)
  {
    return a(Integer.parseInt(String.valueOf(paramObject)));
  }
  
  public void a(int paramInt1, int paramInt2)
  {
    b = paramInt1;
    c = paramInt2;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.cu
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */