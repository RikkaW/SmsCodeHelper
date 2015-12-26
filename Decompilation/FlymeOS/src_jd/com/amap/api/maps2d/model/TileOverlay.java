package com.amap.api.maps2d.model;

import com.amap.api.mapcore2d.at;

public final class TileOverlay
{
  private at a;
  
  public TileOverlay(at paramat)
  {
    a = paramat;
  }
  
  public void clearTileCache()
  {
    a.b();
  }
  
  public boolean equals(Object paramObject)
  {
    return a.a(a);
  }
  
  public String getId()
  {
    return a.c();
  }
  
  public float getZIndex()
  {
    return a.d();
  }
  
  public int hashCode()
  {
    return a.f();
  }
  
  public boolean isVisible()
  {
    return a.e();
  }
  
  public void remove()
  {
    a.a();
  }
  
  public void setVisible(boolean paramBoolean)
  {
    a.a(paramBoolean);
  }
  
  public void setZIndex(float paramFloat)
  {
    a.a(paramFloat);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.maps2d.model.TileOverlay
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */