package com.amap.api.mapcore2d;

import android.graphics.Bitmap;
import java.lang.ref.WeakReference;
import java.util.HashMap;

class cs$1
  extends cx<String, Bitmap>
{
  cs$1(cs paramcs, int paramInt)
  {
    super(paramInt);
  }
  
  protected int a(String paramString, Bitmap paramBitmap)
  {
    int j = cs.a(paramBitmap) / 1024;
    int i = j;
    if (j == 0) {
      i = 1;
    }
    return i;
  }
  
  protected void a(boolean paramBoolean, String paramString, Bitmap paramBitmap1, Bitmap paramBitmap2)
  {
    if ((cy.c()) && (cs.a(a) != null) && (paramBitmap1 != null) && (!paramBitmap1.isRecycled())) {
      cs.a(a).put(paramString, new WeakReference(paramBitmap1));
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.cs.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */