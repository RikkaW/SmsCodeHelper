package com.amap.api.services.district;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.d;
import com.amap.api.services.core.p;

class DistrictSearch$1
  extends Thread
{
  DistrictSearch$1(DistrictSearch paramDistrictSearch) {}
  
  public void run()
  {
    Message localMessage = p.a().obtainMessage();
    Object localObject1 = new DistrictResult();
    ((DistrictResult)localObject1).setQuery(DistrictSearch.a(a));
    Object localObject2 = localObject1;
    Object localObject4 = localObject1;
    try
    {
      DistrictResult localDistrictResult = DistrictSearch.b(a);
      if (localDistrictResult != null)
      {
        localObject2 = localDistrictResult;
        localObject4 = localDistrictResult;
        localObject1 = localDistrictResult;
        localDistrictResult.setAMapException(new AMapException());
      }
      arg1 = 4;
      obj = DistrictSearch.c(a);
      localObject1 = new Bundle();
      ((Bundle)localObject1).putParcelable("result", localDistrictResult);
      localMessage.setData((Bundle)localObject1);
      if (DistrictSearch.d(a) != null) {
        DistrictSearch.d(a).sendMessage(localMessage);
      }
      return;
    }
    catch (AMapException localAMapException)
    {
      do
      {
        localObject1 = localObject2;
        d.a(localAMapException, "DistrictSearch", "searchDistrictAnsy");
        localObject1 = localObject2;
        ((DistrictResult)localObject2).setAMapException(localAMapException);
        arg1 = 4;
        obj = DistrictSearch.c(a);
        localObject1 = new Bundle();
        ((Bundle)localObject1).putParcelable("result", (Parcelable)localObject2);
        localMessage.setData((Bundle)localObject1);
      } while (DistrictSearch.d(a) == null);
      DistrictSearch.d(a).sendMessage(localMessage);
      return;
    }
    catch (Throwable localThrowable)
    {
      do
      {
        localObject1 = localAMapException;
        d.a(localThrowable, "DistrictSearch", "searchDistrictAnsyThrowable");
        arg1 = 4;
        obj = DistrictSearch.c(a);
        localObject1 = new Bundle();
        ((Bundle)localObject1).putParcelable("result", localAMapException);
        localMessage.setData((Bundle)localObject1);
      } while (DistrictSearch.d(a) == null);
      DistrictSearch.d(a).sendMessage(localMessage);
      return;
    }
    finally
    {
      arg1 = 4;
      obj = DistrictSearch.c(a);
      Bundle localBundle = new Bundle();
      localBundle.putParcelable("result", (Parcelable)localObject1);
      localMessage.setData(localBundle);
      if (DistrictSearch.d(a) != null) {
        DistrictSearch.d(a).sendMessage(localMessage);
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.district.DistrictSearch.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */