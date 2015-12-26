package com.android.mms.dom.smil;

import org.w3c.dom.smil.Time;

public class TimeImpl
  implements Time
{
  boolean mResolved;
  double mResolvedOffset;
  short mTimeType;
  
  TimeImpl(String paramString, int paramInt)
  {
    if ((paramString.equals("indefinite")) && ((paramInt & 0x1) != 0))
    {
      mTimeType = 0;
      return;
    }
    if ((paramInt & 0x2) != 0)
    {
      paramInt = 1;
      String str;
      if (paramString.startsWith("+")) {
        str = paramString.substring(1);
      }
      for (;;)
      {
        mResolvedOffset = (paramInt * parseClockValue(str) / 1000.0D);
        mResolved = true;
        mTimeType = 1;
        return;
        str = paramString;
        if (paramString.startsWith("-"))
        {
          str = paramString.substring(1);
          paramInt = -1;
        }
      }
    }
    throw new IllegalArgumentException("Unsupported time value");
  }
  
  public static float parseClockValue(String paramString)
  {
    float f1 = 0.0F;
    try
    {
      paramString = paramString.trim();
      if (paramString.endsWith("ms"))
      {
        f1 = parseFloat(paramString, 2, true);
        return f1;
      }
      if (paramString.endsWith("s"))
      {
        f1 = 1000.0F * parseFloat(paramString, 1, true);
        return f1;
      }
      if (paramString.endsWith("min"))
      {
        f1 = 60000.0F * parseFloat(paramString, 3, true);
        return f1;
      }
      if (paramString.endsWith("h"))
      {
        f1 = parseFloat(paramString, 1, true);
        f1 = 3600000.0F * f1;
        return f1;
      }
      try
      {
        f2 = parseFloat(paramString, 0, true);
        return f2 * 1000.0F;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        float f2;
        paramString = paramString.split(":");
        if (paramString.length == 2) {}
        for (int i = 0;; i = 1)
        {
          int j = (int)parseFloat(paramString[i], 0, false);
          if ((j < 0) || (j > 59)) {
            break label235;
          }
          f2 = 60000 * j;
          float f3 = parseFloat(paramString[(i + 1)], 0, true);
          if ((f3 < 0.0F) || (f3 >= 60.0F)) {
            break label243;
          }
          f1 = f1 + f2 + 60000.0F * f3;
          return f1;
          if (paramString.length != 3) {
            break;
          }
          f1 = 3600000 * (int)parseFloat(paramString[0], 0, false);
        }
        throw new IllegalArgumentException();
      }
      throw new IllegalArgumentException();
    }
    catch (NumberFormatException paramString)
    {
      throw new IllegalArgumentException();
    }
    label235:
    label243:
    throw new IllegalArgumentException();
    return f1;
  }
  
  private static float parseFloat(String paramString, int paramInt, boolean paramBoolean)
  {
    paramString = paramString.substring(0, paramString.length() - paramInt);
    paramInt = paramString.indexOf('.');
    if (paramInt != -1)
    {
      if (!paramBoolean) {
        throw new IllegalArgumentException("int value contains decimal");
      }
      paramString = paramString + "000";
      return Float.parseFloat(paramString.substring(0, paramInt)) + Float.parseFloat(paramString.substring(paramInt + 1, paramInt + 4)) / 1000.0F;
    }
    return Integer.parseInt(paramString);
  }
  
  public double getOffset()
  {
    return 0.0D;
  }
  
  public boolean getResolved()
  {
    return mResolved;
  }
  
  public double getResolvedOffset()
  {
    return mResolvedOffset;
  }
  
  public short getTimeType()
  {
    return mTimeType;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.dom.smil.TimeImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */