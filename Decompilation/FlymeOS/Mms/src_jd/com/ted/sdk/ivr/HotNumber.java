package com.ted.sdk.ivr;

import java.util.HashMap;

public class HotNumber
{
  private HashMap<Character, DialpadAction> mActionMap = DialpadAction.getInitActionMap();
  private final String mName;
  private final String mNumber;
  
  public HotNumber(String paramString1, String paramString2)
  {
    mNumber = paramString1;
    mName = paramString2;
  }
  
  public HashMap<Character, DialpadAction> getActions()
  {
    return mActionMap;
  }
  
  public String getName()
  {
    return mName;
  }
  
  public String getNumber()
  {
    return mNumber;
  }
}

/* Location:
 * Qualified Name:     com.ted.sdk.ivr.HotNumber
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */