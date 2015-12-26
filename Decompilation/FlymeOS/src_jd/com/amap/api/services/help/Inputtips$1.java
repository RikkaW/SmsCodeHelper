package com.amap.api.services.help;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.d;
import com.amap.api.services.core.h;
import com.amap.api.services.core.i;
import com.amap.api.services.core.p;
import java.util.ArrayList;

class Inputtips$1
  extends Thread
{
  Inputtips$1(Inputtips paramInputtips, String paramString1, String paramString2) {}
  
  public void run()
  {
    Object localObject1 = new h(Inputtips.a(c), new i(a, b));
    Message localMessage = p.a().obtainMessage();
    obj = Inputtips.b(c);
    arg1 = 5;
    try
    {
      localObject1 = (ArrayList)((h)localObject1).g();
      Bundle localBundle = new Bundle();
      localBundle.putParcelableArrayList("result", (ArrayList)localObject1);
      localMessage.setData(localBundle);
      what = 0;
      return;
    }
    catch (AMapException localAMapException)
    {
      d.a(localAMapException, "Inputtips", "requestInputtips");
      what = localAMapException.getErrorCode();
      return;
    }
    finally
    {
      Inputtips.c(c).sendMessage(localMessage);
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.help.Inputtips.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */