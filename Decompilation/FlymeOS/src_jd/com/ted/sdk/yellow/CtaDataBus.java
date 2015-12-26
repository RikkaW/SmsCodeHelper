package com.ted.sdk.yellow;

import com.ted.sdk.yellow.update.Updater;

public class CtaDataBus
{
  public static boolean a = false;
  
  public static void setNetworkAccessible(boolean paramBoolean)
  {
    a = paramBoolean;
    Updater.enable(paramBoolean);
  }
}

/* Location:
 * Qualified Name:     com.ted.sdk.yellow.CtaDataBus
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */