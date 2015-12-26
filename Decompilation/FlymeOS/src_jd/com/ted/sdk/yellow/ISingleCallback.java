package com.ted.sdk.yellow;

import com.ted.sdk.yellow.entry.BaseItem;

public abstract interface ISingleCallback
  extends IExceptionCallback
{
  public abstract void onFail();
  
  public abstract void onSuccess(BaseItem paramBaseItem);
}

/* Location:
 * Qualified Name:     com.ted.sdk.yellow.ISingleCallback
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */