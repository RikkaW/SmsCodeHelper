package com.ted.sdk.ivr;

import java.util.HashMap;

public class DialpadAction
{
  public static final char EIGHT = '8';
  public static final char FIVE = '5';
  public static final char FOUR = '4';
  public static final int KEY_TYPE_DIAL = 0;
  public static final int KEY_TYPE_HOME = 3;
  public static final int KEY_TYPE_NEXT = 2;
  public static final int KEY_TYPE_NONE = 4;
  public static final int KEY_TYPE_PREVIOUS = 1;
  public static final char NINE = '9';
  public static final char ONE = '1';
  public static final char POUND = '#';
  public static final char SEVEN = '7';
  public static final char SIX = '6';
  public static final char STAR = '*';
  public static final char THREE = '3';
  public static final char TWO = '2';
  public static final char ZERO = '0';
  private String mDescription;
  private char mKey;
  private int mKeyType;
  private HashMap<Character, DialpadAction> mSubAction;
  
  DialpadAction(char paramChar, int paramInt)
  {
    this(paramChar, paramInt, null);
  }
  
  public DialpadAction(char paramChar, int paramInt, String paramString)
  {
    mKey = paramChar;
    mKeyType = paramInt;
    mDescription = paramString;
  }
  
  static HashMap<Character, DialpadAction> getInitActionMap()
  {
    return new DialpadAction.1();
  }
  
  public String getDescription()
  {
    return mDescription;
  }
  
  public char getKey()
  {
    return mKey;
  }
  
  public int getKeyType()
  {
    return mKeyType;
  }
  
  public HashMap<Character, DialpadAction> getSubAction()
  {
    if (mSubAction == null) {
      mSubAction = getInitActionMap();
    }
    return mSubAction;
  }
  
  void refresh(DialpadAction paramDialpadAction)
  {
    mKey = paramDialpadAction.getKey();
    mKeyType = paramDialpadAction.getKeyType();
    mDescription = paramDialpadAction.getDescription();
    mSubAction = paramDialpadAction.getSubAction();
  }
  
  void setKeyType(int paramInt)
  {
    mKeyType = paramInt;
  }
}

/* Location:
 * Qualified Name:     com.ted.sdk.ivr.DialpadAction
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */