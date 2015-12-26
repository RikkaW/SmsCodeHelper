package com.meizu.cloud.pushsdk.notification;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import java.util.List;

public class BroadCastManager
{
  public static String findReceiver(Context paramContext, String paramString1, String paramString2)
  {
    if ((TextUtils.isEmpty(paramString1)) || (TextUtils.isEmpty(paramString2))) {}
    do
    {
      return null;
      paramString1 = new Intent(paramString1);
      paramString1.setPackage(paramString2);
      paramContext = paramContext.getPackageManager().queryBroadcastReceivers(paramString1, 0);
    } while ((paramContext == null) || (paramContext.size() <= 0));
    return get0activityInfo.name;
  }
}

/* Location:
 * Qualified Name:     com.meizu.cloud.pushsdk.notification.BroadCastManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */