package com.meizu.account.pay;

import android.os.Bundle;
import android.text.TextUtils;
import com.meizu.account.pay.a.b;

final class k
  extends b
{
  k(j paramj) {}
  
  public final Bundle a(String paramString)
  {
    paramString = j.a(a).handleAccountChange(paramString);
    if (!TextUtils.isEmpty(paramString))
    {
      Bundle localBundle = new Bundle();
      localBundle.putString("custom_handle_msg", paramString);
      return localBundle;
    }
    return null;
  }
  
  public final Bundle a(String paramString1, String paramString2)
  {
    paramString1 = j.a(a).handleCustomBusiness(paramString1, paramString2);
    if (mSuccess)
    {
      if (mTradeOrderInfo != null)
      {
        j.a(a, mTradeOrderInfo);
        paramString1 = mTradeOrderInfo.toBundle();
        paramString1.putInt("custom_handle_result", 1);
        return paramString1;
      }
      paramString1 = new Bundle();
      paramString1.putInt("custom_handle_result", 2);
      return paramString1;
    }
    paramString2 = new Bundle();
    paramString2.putInt("custom_handle_result", -1);
    paramString2.putString("custom_handle_msg", mErrMsg);
    return paramString2;
  }
}

/* Location:
 * Qualified Name:     com.meizu.account.pay.k
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */