package com.ted.sdk.location;

class CmSpecailLocation$NumberSegment
{
  private int from;
  private int idx;
  private int to;
  
  public CmSpecailLocation$NumberSegment(String paramString, int paramInt)
  {
    if (paramString.contains("-"))
    {
      paramString = paramString.split("-");
      from = Integer.valueOf(remove0(paramString[0])).intValue();
    }
    for (to = Integer.valueOf(remove0(paramString[1])).intValue();; to = from)
    {
      idx = paramInt;
      return;
      from = Integer.valueOf(remove0(paramString)).intValue();
    }
  }
  
  private static String remove0(String paramString)
  {
    for (;;)
    {
      if ((!paramString.startsWith("0")) || (paramString.length() <= 1)) {
        return paramString;
      }
      paramString = paramString.substring(1);
    }
  }
  
  public int getProvCityIdx()
  {
    return idx;
  }
  
  public boolean isIn(String paramString)
  {
    int i = Integer.valueOf(remove0(paramString)).intValue();
    return (from <= i) && (i <= to);
  }
}

/* Location:
 * Qualified Name:     com.ted.sdk.location.CmSpecailLocation.NumberSegment
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */