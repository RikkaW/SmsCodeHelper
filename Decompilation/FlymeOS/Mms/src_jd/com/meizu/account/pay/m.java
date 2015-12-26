package com.meizu.account.pay;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.meizu.account.pay.a.d;
import com.meizu.account.pay.a.g;

public final class m
  extends a
{
  private OutTradeOrderInfo b;
  private String c;
  private String d;
  private PayListener e;
  
  public m(Activity paramActivity, OutTradeOrderInfo paramOutTradeOrderInfo, PayListener paramPayListener, String paramString1, String paramString2)
  {
    super(paramActivity);
    b = paramOutTradeOrderInfo;
    e = paramPayListener;
    c = paramString1;
    d = paramString2;
    if ((b == null) || (e == null)) {
      throw new IllegalArgumentException("Params cant be null!");
    }
    if (((!TextUtils.isEmpty(paramString1)) && (TextUtils.isEmpty(paramString2))) || ((TextUtils.isEmpty(paramString1)) && (!TextUtils.isEmpty(paramString2)))) {
      throw new IllegalArgumentException("account/pwd illegal.");
    }
  }
  
  protected final void a()
  {
    e.onPayResult(0, b, null);
  }
  
  protected final void a(int paramInt, String paramString)
  {
    Log.e("SystemPayController", "service error : " + paramString + " , " + paramInt);
    e.onPayResult(PayResultCode.fixCode(paramInt), b, paramString);
  }
  
  protected final void a(g paramg, d paramd)
  {
    String str = a.getPackageName();
    Bundle localBundle = b.toBundle();
    localBundle.putString("package", str);
    if (!TextUtils.isEmpty(c))
    {
      localBundle.putString("account", c);
      localBundle.putString("pwd", d);
    }
    localBundle.putInt("btn_color_id", l.a);
    localBundle.putInt("edittext_id", l.b);
    paramg.a(localBundle, paramd);
  }
  
  protected final void b()
  {
    Log.e("SystemPayController", "service exception.");
    e.onPayResult(100, b, "pay service exception.");
  }
}

/* Location:
 * Qualified Name:     com.meizu.account.pay.m
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */