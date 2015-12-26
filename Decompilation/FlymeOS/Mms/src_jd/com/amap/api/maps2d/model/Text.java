package com.amap.api.maps2d.model;

import android.graphics.Typeface;
import com.amap.api.mapcore2d.as;

public final class Text
{
  public static final int ALIGN_BOTTOM = 5;
  public static final int ALIGN_CENTER_HORIZONTAL = 3;
  public static final int ALIGN_CENTER_VERTICAL = 6;
  public static final int ALIGN_LEFT = 1;
  public static final int ALIGN_RIGHT = 2;
  public static final int ALIGN_TOP = 4;
  private as a;
  
  public Text(as paramas)
  {
    a = paramas;
  }
  
  public int getAlignX()
  {
    return a.g();
  }
  
  public int getAlignY()
  {
    return a.h();
  }
  
  public int getBackgroundColor()
  {
    return a.e();
  }
  
  public int getFontColor()
  {
    return a.c();
  }
  
  public int getFontSize()
  {
    return a.b();
  }
  
  public Object getObject()
  {
    return a.u();
  }
  
  public LatLng getPosition()
  {
    return a.t();
  }
  
  public float getRotate()
  {
    return a.d();
  }
  
  public String getText()
  {
    return a.a();
  }
  
  public Typeface getTypeface()
  {
    return a.f();
  }
  
  public float getZIndex()
  {
    return a.r();
  }
  
  public void remove()
  {
    a.i();
  }
  
  public void setAlign(int paramInt1, int paramInt2)
  {
    a.a(paramInt1, paramInt2);
  }
  
  public void setBackgroundColor(int paramInt)
  {
    a.d(paramInt);
  }
  
  public void setFontColor(int paramInt)
  {
    a.c(paramInt);
  }
  
  public void setFontSize(int paramInt)
  {
    a.a(paramInt);
  }
  
  public void setObject(Object paramObject)
  {
    a.a(paramObject);
  }
  
  public void setPosition(LatLng paramLatLng)
  {
    a.b(paramLatLng);
  }
  
  public void setRotate(float paramFloat)
  {
    a.a(paramFloat);
  }
  
  public void setText(String paramString)
  {
    a.a(paramString);
  }
  
  public void setTypeface(Typeface paramTypeface)
  {
    a.a(paramTypeface);
  }
  
  public void setZIndex(float paramFloat)
  {
    a.b(paramFloat);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.maps2d.model.Text
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */