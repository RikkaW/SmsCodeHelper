package com.ted.sdk.yellow;

import com.ted.sdk.yellow.entry.BaseItem;
import java.util.HashMap;

public abstract interface IListCallback
  extends IExceptionCallback
{
  public abstract void onFail();
  
  public abstract void onPartSuccess(HashMap<String, ? extends BaseItem> paramHashMap);
  
  public abstract void onSuccess(HashMap<String, ? extends BaseItem> paramHashMap);
}

/* Location:
 * Qualified Name:     com.ted.sdk.yellow.IListCallback
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */