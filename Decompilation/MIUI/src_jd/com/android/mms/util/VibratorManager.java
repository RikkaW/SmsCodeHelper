package com.android.mms.util;

import android.content.Context;
import android.os.Vibrator;
import miui.util.AudioManagerHelper;

public class VibratorManager
{
  private static final long[] DEFAULT_VIBRATE_PATTERN = { 0L, 250L, 250L, 250L };
  private static Vibrator sVibrator;
  
  public static void cancel()
  {
    if (sVibrator != null) {
      sVibrator.cancel();
    }
  }
  
  public static void vibrate(Context paramContext)
  {
    vibrate(paramContext, 0);
  }
  
  public static void vibrate(Context paramContext, int paramInt)
  {
    if (sVibrator == null) {
      sVibrator = (Vibrator)paramContext.getSystemService("vibrator");
    }
    if ((paramInt == 0) && (AudioManagerHelper.isVibrateEnabled(paramContext))) {
      sVibrator.vibrate(DEFAULT_VIBRATE_PATTERN, -1);
    }
    while (paramInt == 0) {
      return;
    }
    sVibrator.vibrate(paramInt);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.util.VibratorManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */