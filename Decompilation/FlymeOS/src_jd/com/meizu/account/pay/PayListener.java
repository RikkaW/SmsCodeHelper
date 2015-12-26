package com.meizu.account.pay;

public abstract interface PayListener
{
  public abstract void onPayResult(int paramInt, OutTradeOrderInfo paramOutTradeOrderInfo, String paramString);
}

/* Location:
 * Qualified Name:     com.meizu.account.pay.PayListener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */