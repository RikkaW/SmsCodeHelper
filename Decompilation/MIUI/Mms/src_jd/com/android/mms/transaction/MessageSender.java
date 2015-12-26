package com.android.mms.transaction;

import com.google.android.mms.MmsException;

public abstract interface MessageSender
{
  public abstract boolean sendMessage()
    throws MmsException;
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.MessageSender
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */