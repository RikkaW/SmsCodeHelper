package com.amap.api.location;

import java.util.List;

class b$a$1
  extends Thread
{
  b$a$1(b.a parama, b paramb, AMapLocation paramAMapLocation) {}
  
  public void run()
  {
    int i = 0;
    for (;;)
    {
      try
      {
        if (i < a.d.size())
        {
          if (((Integer)a.d.get(i)).intValue() == 1) {
            a.a(b, "base", (AMapLocalWeatherListener)a.e.get(i));
          }
          if (((Integer)a.d.get(i)).intValue() == 2) {
            a.a(b, "all", (AMapLocalWeatherListener)a.e.get(i));
          }
        }
        else
        {
          a.d.clear();
          a.e.clear();
          return;
        }
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
        return;
      }
      i += 1;
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.location.b.a.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */