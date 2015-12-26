package com.amap.api.services.poisearch;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.d;
import com.amap.api.services.core.p.e;

class PoiSearch$1
  extends Thread
{
  PoiSearch$1(PoiSearch paramPoiSearch) {}
  
  public void run()
  {
    Message localMessage = PoiSearch.a(a).obtainMessage();
    arg1 = 6;
    what = 60;
    Bundle localBundle = new Bundle();
    Object localObject3 = null;
    Object localObject1 = null;
    try
    {
      PoiResult localPoiResult = a.searchPOI();
      localObject1 = localPoiResult;
      localObject3 = localPoiResult;
      localBundle.putInt("errorCode", 0);
      localObject1 = new p.e();
      b = PoiSearch.b(a);
      a = localPoiResult;
      obj = localObject1;
      localMessage.setData(localBundle);
      PoiSearch.a(a).sendMessage(localMessage);
      return;
    }
    catch (AMapException localAMapException)
    {
      localObject3 = localObject1;
      d.a(localAMapException, "PoiSearch", "searchPOIAsyn");
      localObject3 = localObject1;
      localBundle.putInt("errorCode", localAMapException.getErrorCode());
      localObject3 = new p.e();
      b = PoiSearch.b(a);
      a = ((PoiResult)localObject1);
      obj = localObject3;
      localMessage.setData(localBundle);
      PoiSearch.a(a).sendMessage(localMessage);
      return;
    }
    finally
    {
      p.e locale = new p.e();
      b = PoiSearch.b(a);
      a = ((PoiResult)localObject3);
      obj = locale;
      localMessage.setData(localBundle);
      PoiSearch.a(a).sendMessage(localMessage);
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.poisearch.PoiSearch.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */