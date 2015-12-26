package com.amap.api.services.geocoder;

public class GeocodeQuery
{
  private String a;
  private String b;
  
  public GeocodeQuery(String paramString1, String paramString2)
  {
    a = paramString1;
    b = paramString2;
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
        paramObject = (GeocodeQuery)paramObject;
        if (b == null)
        {
          if (b != null) {
            return false;
          }
        }
        else if (!b.equals(b)) {
          return false;
        }
        if (a != null) {
          break;
        }
      } while (a == null);
      return false;
    } while (a.equals(a));
    return false;
  }
  
  public String getCity()
  {
    return b;
  }
  
  public String getLocationName()
  {
    return a;
  }
  
  public int hashCode()
  {
    int j = 0;
    int i;
    if (b == null)
    {
      i = 0;
      if (a != null) {
        break label39;
      }
    }
    for (;;)
    {
      return (i + 31) * 31 + j;
      i = b.hashCode();
      break;
      label39:
      j = a.hashCode();
    }
  }
  
  public void setCity(String paramString)
  {
    b = paramString;
  }
  
  public void setLocationName(String paramString)
  {
    a = paramString;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.geocoder.GeocodeQuery
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */