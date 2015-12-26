package com.android.mms.transaction;

import gr;

public final class MessagePopupService$b
{
  public gr a = null;
  public long b = 0L;
  public boolean c = true;
  public long d = 0L;
  public String e = "";
  public String f = "";
  public long g = 0L;
  public int h;
  public int i = 0;
  public boolean j;
  public String k = null;
  public boolean l = false;
  public boolean m;
  public volatile boolean n;
  public int o = -1;
  public String p = "";
  public boolean q = true;
  
  public boolean a()
  {
    return k != null;
  }
  
  public String toString()
  {
    return String.format("[mThreadId = %d] [mMsgId %d] [mMsgAddress = %s] [mMessagePort = %d] [mProtocol = %d] [mIsSms = %b]", new Object[] { Long.valueOf(b), Long.valueOf(d), e, Integer.valueOf(h), Integer.valueOf(i), Boolean.valueOf(c) });
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.MessagePopupService.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */