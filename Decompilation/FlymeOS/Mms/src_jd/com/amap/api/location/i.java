package com.amap.api.location;

public class i
{
  long a;
  public AMapLocationListener b;
  Boolean c;
  
  public i(long paramLong, float paramFloat, AMapLocationListener paramAMapLocationListener, String paramString, boolean paramBoolean)
  {
    a = paramLong;
    b = paramAMapLocationListener;
    c = Boolean.valueOf(paramBoolean);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      do
      {
        return true;
        if (paramObject == null) {
          return false;
        }
        if (getClass() != paramObject.getClass()) {
          return false;
        }
        paramObject = (i)paramObject;
        if (b != null) {
          break;
        }
      } while (b == null);
      return false;
    } while (b.equals(b));
    return false;
  }
  
  public int hashCode()
  {
    if (b == null) {}
    for (int i = 0;; i = b.hashCode()) {
      return i + 31;
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.location.i
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */