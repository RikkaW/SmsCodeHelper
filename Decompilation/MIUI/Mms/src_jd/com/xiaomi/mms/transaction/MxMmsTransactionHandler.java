package com.xiaomi.mms.transaction;

import android.net.Uri;

public abstract interface MxMmsTransactionHandler
{
  public abstract Uri handleReceiveMxMms(Uri paramUri);
  
  public abstract Uri handleSendMxMms(Uri paramUri);
  
  public abstract void setSendByMxV2(boolean paramBoolean);
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.transaction.MxMmsTransactionHandler
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */