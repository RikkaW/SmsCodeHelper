package com.android.mms;

import android.app.Activity;
import android.util.Log;

public class LogTag
{
  public static void debug(String paramString, Object... paramVarArgs)
  {
    Log.d("Mms", logFormat(paramString, paramVarArgs));
  }
  
  public static void error(String paramString, Object... paramVarArgs)
  {
    Log.e("Mms", logFormat(paramString, paramVarArgs));
  }
  
  public static String logFormat(String paramString, Object... paramVarArgs)
  {
    int j = 0;
    int i = 0;
    while (i < paramVarArgs.length)
    {
      if ((paramVarArgs[i] instanceof String[])) {
        paramVarArgs[i] = prettyArray((String[])(String[])paramVarArgs[i]);
      }
      i += 1;
    }
    paramVarArgs = String.format(paramString, paramVarArgs);
    Thread localThread = Thread.currentThread();
    StackTraceElement[] arrayOfStackTraceElement = localThread.getStackTrace();
    i = j;
    for (;;)
    {
      j = i;
      if (i >= arrayOfStackTraceElement.length) {
        break;
      }
      j = i;
      if (arrayOfStackTraceElement[i].getClassName().equals(LogTag.class.getName())) {
        break;
      }
      i += 1;
    }
    while ((j < arrayOfStackTraceElement.length) && (arrayOfStackTraceElement[j].getClassName().equals(LogTag.class.getName()))) {
      j += 1;
    }
    paramString = paramVarArgs;
    if (j < arrayOfStackTraceElement.length) {
      paramString = arrayOfStackTraceElement[j].getFileName() + "(" + arrayOfStackTraceElement[j].getLineNumber() + "): " + paramVarArgs;
    }
    return "[" + localThread.getId() + "] " + paramString;
  }
  
  private static String prettyArray(String[] paramArrayOfString)
  {
    if (paramArrayOfString.length == 0) {
      return "[]";
    }
    StringBuilder localStringBuilder = new StringBuilder("[");
    int j = paramArrayOfString.length - 1;
    int i = 0;
    while (i < j)
    {
      localStringBuilder.append(paramArrayOfString[i]);
      localStringBuilder.append(", ");
      i += 1;
    }
    localStringBuilder.append(paramArrayOfString[j]);
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
  
  public static void verbose(String paramString, Object... paramVarArgs)
  {
    Log.v("Mms", logFormat(paramString, paramVarArgs));
  }
  
  public static void warn(String paramString, Object... paramVarArgs)
  {
    Log.w("Mms", logFormat(paramString, paramVarArgs));
  }
  
  public static void warnPossibleRecipientMismatch(String paramString, Activity paramActivity)
  {
    Log.e("Mms", "WARNING!!!! " + paramString, new RuntimeException());
  }
}

/* Location:
 * Qualified Name:     com.android.mms.LogTag
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */