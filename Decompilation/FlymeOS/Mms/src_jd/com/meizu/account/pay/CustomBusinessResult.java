package com.meizu.account.pay;

public class CustomBusinessResult
{
  protected final String mErrMsg;
  protected final boolean mSuccess;
  protected final OutTradeOrderInfo mTradeOrderInfo;
  
  private CustomBusinessResult(boolean paramBoolean, OutTradeOrderInfo paramOutTradeOrderInfo, String paramString)
  {
    mSuccess = paramBoolean;
    mTradeOrderInfo = paramOutTradeOrderInfo;
    mErrMsg = paramString;
  }
  
  public static CustomBusinessResult constructError(String paramString)
  {
    return new CustomBusinessResult(false, null, paramString);
  }
  
  public static CustomBusinessResult constructOrder(OutTradeOrderInfo paramOutTradeOrderInfo)
  {
    return new CustomBusinessResult(true, paramOutTradeOrderInfo, null);
  }
  
  public static CustomBusinessResult constructPayEnd()
  {
    return new CustomBusinessResult(true, null, null);
  }
}

/* Location:
 * Qualified Name:     com.meizu.account.pay.CustomBusinessResult
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */