package com.amap.api.location;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.amap.api.location.core.AMapLocException;
import java.lang.ref.WeakReference;
import java.util.List;

class b$a
  extends Handler
{
  private WeakReference<b> a;
  
  b$a(b paramb, Looper paramLooper)
  {
    super(paramLooper);
    try
    {
      a = new WeakReference(paramb);
      return;
    }
    catch (Throwable paramb)
    {
      paramb.printStackTrace();
    }
  }
  
  public void handleMessage(Message paramMessage)
  {
    b localb;
    try
    {
      super.handleMessage(paramMessage);
      localb = (b)a.get();
      switch (what)
      {
      case 1: 
        if (b == null) {
          return;
        }
        b.onWeatherLiveSearched((AMapLocalWeatherLive)obj);
        return;
      }
    }
    catch (Throwable paramMessage)
    {
      paramMessage.printStackTrace();
      return;
    }
    if (b != null)
    {
      b.onWeatherForecaseSearched((AMapLocalWeatherForecast)obj);
      return;
      try
      {
        new b.a.1(this, localb, (AMapLocation)obj).start();
        return;
      }
      catch (Throwable paramMessage)
      {
        paramMessage.printStackTrace();
        return;
      }
      for (;;)
      {
        try
        {
          paramMessage = (AMapLocException)obj;
          int i = 0;
          try
          {
            int j = d.size();
            if (i >= j) {
              break;
            }
          }
          catch (Throwable paramMessage)
          {
            Object localObject;
            paramMessage.printStackTrace();
            return;
          }
        }
        catch (Throwable paramMessage)
        {
          paramMessage.printStackTrace();
          return;
        }
        try
        {
          if (((Integer)d.get(i)).intValue() == 1)
          {
            localObject = new AMapLocalWeatherLive();
            ((AMapLocalWeatherLive)localObject).a(paramMessage);
            ((AMapLocalWeatherListener)e.get(i)).onWeatherLiveSearched((AMapLocalWeatherLive)localObject);
          }
          if (((Integer)d.get(i)).intValue() == 2)
          {
            localObject = new AMapLocalWeatherForecast();
            ((AMapLocalWeatherForecast)localObject).a(paramMessage);
            ((AMapLocalWeatherListener)e.get(i)).onWeatherForecaseSearched((AMapLocalWeatherForecast)localObject);
          }
        }
        catch (Throwable localThrowable)
        {
          continue;
        }
        i += 1;
      }
      d.clear();
      e.clear();
      return;
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.location.b.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */