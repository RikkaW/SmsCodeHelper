package com.xiaomi.common;

import java.io.PrintStream;
import java.util.logging.Logger;

public class Log
{
  public static boolean localTest = false;
  
  public static void i(String paramString1, String paramString2)
  {
    if (localTest) {
      Logger.getLogger(paramString1).info(paramString2);
    }
  }
  
  public static void println(String paramString)
  {
    if (localTest) {
      System.err.println(paramString);
    }
  }
  
  public static void setLocalTest(boolean paramBoolean)
  {
    localTest = paramBoolean;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.common.Log
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */