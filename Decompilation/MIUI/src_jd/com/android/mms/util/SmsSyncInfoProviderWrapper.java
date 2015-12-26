package com.android.mms.util;

import android.content.Context;
import android.content.res.Resources;
import miui.cloud.sync.providers.SmsSyncInfoProvider;
import miui.widget.MiCloudStateView.ISyncInfoProvider;

public class SmsSyncInfoProviderWrapper
  implements MiCloudStateView.ISyncInfoProvider
{
  SmsSyncInfoProvider mSyncInfoProvider = new SmsSyncInfoProvider();
  
  public String getAuthority()
  {
    return "sms";
  }
  
  public int[] getUnsyncedCount(Context paramContext)
  {
    return new int[] { mSyncInfoProvider.getUnsyncedCount(paramContext) };
  }
  
  public String getUnsyncedCountText(Context paramContext, int[] paramArrayOfInt)
  {
    return paramContext.getResources().getQuantityString(2131623942, paramArrayOfInt[0], new Object[] { Integer.valueOf(paramArrayOfInt[0]) });
  }
}

/* Location:
 * Qualified Name:     com.android.mms.util.SmsSyncInfoProviderWrapper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */