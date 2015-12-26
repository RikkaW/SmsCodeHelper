package com.meizu.update.service;

import and;
import and.a;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import anj;
import anl;
import com.meizu.update.UpdateInfo;
import com.meizu.update.iresponse.MzUpdateResponse;

final class MzUpdateComponentService$a
  extends Handler
{
  public MzUpdateComponentService$a(MzUpdateComponentService paramMzUpdateComponentService, Looper paramLooper)
  {
    super(paramLooper);
  }
  
  public void handleMessage(Message paramMessage)
  {
    Object localObject1 = null;
    Object localObject2 = null;
    Bundle localBundle = ((Intent)obj).getExtras();
    switch (what)
    {
    }
    for (;;)
    {
      a.stopSelf(arg1);
      return;
      anj.a(true);
      localObject1 = localObject2;
      if (localBundle.containsKey("response")) {
        localObject1 = (MzUpdateResponse)localBundle.getParcelable("response");
      }
      localObject2 = (UpdateInfo)localBundle.getParcelable("update_info");
      if (localBundle.containsKey("from_notification")) {
        and.a(a).a(and.a.d, mVersionName, anl.b(a, a.getPackageName()));
      }
      MzUpdateComponentService.a(a, (UpdateInfo)localObject2, (MzUpdateResponse)localObject1);
      anj.a(false);
      continue;
      anj.a(true);
      localObject2 = localBundle.getString("apk_path");
      if (localBundle.containsKey("response")) {
        localObject1 = (MzUpdateResponse)localBundle.getParcelable("response");
      }
      MzUpdateComponentService.a(a, (UpdateInfo)localBundle.getParcelable("update_info"), (String)localObject2, (MzUpdateResponse)localObject1);
      anj.a(false);
      continue;
      MzUpdateComponentService.a(a);
      continue;
      MzUpdateComponentService.b(a);
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.update.service.MzUpdateComponentService.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */