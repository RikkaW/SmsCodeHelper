package com.ted.sdk.yellow.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SdkSharedPrefs
{
  private static final String DATA_NAME = "txl.bat";
  private static final String LOAD_BUBBLE_DATA = "load_bubble_data";
  private static final String LOAD_LOCAL_DATA = "load_local_data";
  private static final String LOAD_NUM_SEGMENT_DATA = "load_number_segment";
  private static final String LOAD_SMS_CARD_DATA = "load_sms_card_data";
  
  private static boolean getSharedPreBoolean(Context paramContext, String paramString, boolean paramBoolean)
  {
    return paramContext.getSharedPreferences("txl.bat", 0).getBoolean(paramString, paramBoolean);
  }
  
  public static boolean isBubbleDataLoaded(Context paramContext)
  {
    return getSharedPreBoolean(paramContext, "load_bubble_data", false);
  }
  
  public static boolean isLocalDataLoaded(Context paramContext)
  {
    return getSharedPreBoolean(paramContext, "load_local_data", false);
  }
  
  public static boolean isNumSegmentDataLoaded(Context paramContext)
  {
    return getSharedPreBoolean(paramContext, "load_number_segment", false);
  }
  
  public static boolean isSmsCardDataLoaded(Context paramContext)
  {
    return getSharedPreBoolean(paramContext, "load_sms_card_data", false);
  }
  
  public static void setBubbleDataLoaded(Context paramContext, boolean paramBoolean)
  {
    setSharedPreBoolean(paramContext, "load_bubble_data", paramBoolean);
  }
  
  public static void setLocalDataLoaded(Context paramContext, boolean paramBoolean)
  {
    setSharedPreBoolean(paramContext, "load_local_data", paramBoolean);
  }
  
  public static void setNumSegmentDataLoaded(Context paramContext, boolean paramBoolean)
  {
    setSharedPreBoolean(paramContext, "load_number_segment", paramBoolean);
  }
  
  private static void setSharedPreBoolean(Context paramContext, String paramString, boolean paramBoolean)
  {
    paramContext = paramContext.getSharedPreferences("txl.bat", 0).edit();
    paramContext.putBoolean(paramString, paramBoolean);
    paramContext.commit();
  }
  
  public static void setSmsCardDataLoaded(Context paramContext, boolean paramBoolean)
  {
    setSharedPreBoolean(paramContext, "load_sms_card_data", paramBoolean);
  }
}

/* Location:
 * Qualified Name:     com.ted.sdk.yellow.util.SdkSharedPrefs
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */