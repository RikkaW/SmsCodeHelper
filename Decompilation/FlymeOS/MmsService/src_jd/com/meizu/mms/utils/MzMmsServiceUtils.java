package com.meizu.mms.utils;

import android.os.BuildExt;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;

public class MzMmsServiceUtils
{
  public static boolean checkIsFailedRespStatus(int paramInt)
  {
    return (paramInt == 9527) || (paramInt == 132) || (paramInt == 135) || (paramInt == 130);
  }
  
  public static int getSlotIdBySubId(int paramInt)
  {
    return SubscriptionManager.getSlotId(paramInt);
  }
  
  public static String getSubscriberIdBySubId(int paramInt)
  {
    return TelephonyManager.getDefault().getSubscriberId(paramInt);
  }
  
  public static boolean isCTVersion()
  {
    return BuildExt.CUSTOMIZE_CHINATELECOM.booleanValue();
  }
}

/* Location:
 * Qualified Name:     com.meizu.mms.utils.MzMmsServiceUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */