package com.amap.api.mapcore2d;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup.LayoutParams;
import com.amap.api.maps2d.model.LatLng;

public class bg$a
  extends ViewGroup.LayoutParams
{
  public int a = 1;
  public LatLng b = null;
  public int c = 0;
  public int d = 0;
  public int e = 51;
  
  public bg$a(int paramInt1, int paramInt2, LatLng paramLatLng, int paramInt3, int paramInt4, int paramInt5)
  {
    super(paramInt1, paramInt2);
    a = 0;
    b = paramLatLng;
    c = paramInt3;
    d = paramInt4;
    e = paramInt5;
  }
  
  public bg$a(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public bg$a(ViewGroup.LayoutParams paramLayoutParams)
  {
    super(paramLayoutParams);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.bg.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */