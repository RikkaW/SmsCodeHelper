package com.ted.sdk.yellow.entry;

import com.ted.android.contacts.netparser.model.NumItem;

public class NumAllInfoItem
  extends BaseItem
{
  private final NumItem mNumItem;
  
  public NumAllInfoItem(String paramString, NumItem paramNumItem)
  {
    super(paramString);
    mNumItem = paramNumItem;
  }
  
  public CallerIdItem getCallerIdItem()
  {
    return BaseItem.parseToCallerIdItem(mNumber, mNumItem);
  }
  
  public ContactItem getContactMenuItem()
  {
    return BaseItem.parseToContactMenuItem(mNumber, mNumItem);
  }
  
  public MessageItem getMessageMenuItem()
  {
    return BaseItem.parseToMessageMenuItem(mNumber, mNumItem);
  }
  
  public NumItem getNumItem()
  {
    return mNumItem;
  }
}

/* Location:
 * Qualified Name:     com.ted.sdk.yellow.entry.NumAllInfoItem
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */