package com.ted.sdk.yellow.entry;

public class MessageItem$SpBuilder
{
  String mSendTo;
  String mSmsContent;
  MessageItem.SpItem.SpType mSpType;
  
  public MessageItem.SpItem build()
  {
    return new MessageItem.SpItem(this);
  }
  
  public SpBuilder setSendTo(String paramString)
  {
    mSendTo = paramString;
    return this;
  }
  
  public SpBuilder setSmsContent(String paramString)
  {
    mSmsContent = paramString;
    return this;
  }
  
  public SpBuilder setSpType(MessageItem.SpItem.SpType paramSpType)
  {
    mSpType = paramSpType;
    return this;
  }
}

/* Location:
 * Qualified Name:     com.ted.sdk.yellow.entry.MessageItem.SpBuilder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */