package com.amap.api.services.poisearch;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.d;
import com.amap.api.services.core.p;
import com.amap.api.services.core.p.d;

class PoiSearch$2
  extends Thread
{
  PoiSearch$2(PoiSearch paramPoiSearch, String paramString) {}
  
  public void run()
  {
    Message localMessage = p.a().obtainMessage();
    arg1 = 6;
    what = 61;
    Bundle localBundle = new Bundle();
    Object localObject3 = null;
    Object localObject1 = null;
    try
    {
      PoiItemDetail localPoiItemDetail = b.searchPOIDetail(a);
      localObject1 = localPoiItemDetail;
      localObject3 = localPoiItemDetail;
      localBundle.putInt("errorCode", 0);
      localObject1 = new p.d();
      b = PoiSearch.b(b);
      a = localPoiItemDetail;
      obj = localObject1;
      localMessage.setData(localBundle);
      PoiSearch.a(b).sendMessage(localMessage);
      return;
    }
    catch (AMapException localAMapException)
    {
      localObject3 = localObject1;
      d.a(localAMapException, "PoiSearch", "searchPOIDetailAsyn");
      localObject3 = localObject1;
      localBundle.putInt("errorCode", localAMapException.getErrorCode());
      localObject3 = new p.d();
      b = PoiSearch.b(b);
      a = ((PoiItemDetail)localObject1);
      obj = localObject3;
      localMessage.setData(localBundle);
      PoiSearch.a(b).sendMessage(localMessage);
      return;
    }
    finally
    {
      p.d locald = new p.d();
      b = PoiSearch.b(b);
      a = ((PoiItemDetail)localObject3);
      obj = locald;
      localMessage.setData(localBundle);
      PoiSearch.a(b).sendMessage(localMessage);
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.poisearch.PoiSearch.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */