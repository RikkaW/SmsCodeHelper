package com.meizu.account.pay;

import android.app.Activity;

public final class l
{
  protected static int a = 0;
  protected static int b = 0;
  
  public static final void a(int paramInt1, int paramInt2)
  {
    a = paramInt1;
    b = paramInt2;
  }
  
  public static final void a(Activity paramActivity, OutTradeOrderInfo paramOutTradeOrderInfo, PayListener paramPayListener)
  {
    new m(paramActivity, paramOutTradeOrderInfo, paramPayListener, null, null).c();
  }
  
  public static final void a(Activity paramActivity, OutTradeOrderInfo paramOutTradeOrderInfo, PayListener paramPayListener, String paramString1, String paramString2)
  {
    new m(paramActivity, paramOutTradeOrderInfo, paramPayListener, paramString1, paramString2).c();
  }
  
  public static final void a(Activity paramActivity, String paramString1, double paramDouble, String paramString2, ICustomBusinessHandler paramICustomBusinessHandler, PayListener paramPayListener)
  {
    new j(paramActivity, paramString1, paramDouble, paramString2, paramICustomBusinessHandler, paramPayListener).c();
  }
}

/* Location:
 * Qualified Name:     com.meizu.account.pay.l
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */