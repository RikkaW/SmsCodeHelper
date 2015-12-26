package com.android.mms.understand;

import java.util.ArrayList;

public class UnderstandMessage
{
  public int mActionID;
  public String mFrameName;
  public ArrayList<Item> mItems;
  
  public static class Item
  {
    public int mEndPosition;
    public int mHas;
    public int mStartPosition;
    public int mType;
    public String mValue;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.understand.UnderstandMessage
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */