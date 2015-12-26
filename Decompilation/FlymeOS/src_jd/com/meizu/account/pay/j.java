package com.meizu.account.pay;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import com.meizu.account.pay.a.d;
import com.meizu.account.pay.a.g;

public final class j
  extends a
{
  private String b;
  private double c;
  private String d;
  private ICustomBusinessHandler e;
  private PayListener f;
  private OutTradeOrderInfo g;
  private com.meizu.account.pay.a.a h = new k(this);
  
  public j(Activity paramActivity, String paramString1, double paramDouble, String paramString2, ICustomBusinessHandler paramICustomBusinessHandler, PayListener paramPayListener)
  {
    super(paramActivity);
    b = paramString1;
    c = paramDouble;
    d = paramString2;
    e = paramICustomBusinessHandler;
    f = paramPayListener;
    if ((b == null) || (c <= 0.0D) || (e == null) || (f == null)) {
      throw new IllegalArgumentException("Params cant be null!");
    }
  }
  
  protected final void a()
  {
    f.onPayResult(0, g, null);
  }
  
  protected final void a(int paramInt, String paramString)
  {
    Log.e("AccountInputController", "service error : " + paramString + " , " + paramInt);
    f.onPayResult(PayResultCode.fixCode(paramInt), g, paramString);
  }
  
  protected final void a(g paramg, d paramd)
  {
    String str = a.getPackageName();
    Bundle localBundle = new Bundle();
    localBundle.putString("package", str);
    localBundle.putString("show_order_title", b);
    localBundle.putDouble("show_order_amount", c);
    localBundle.putString("token_scope", d);
    localBundle.putInt("btn_color_id", l.a);
    localBundle.putInt("edittext_id", l.b);
    paramg.a(localBundle, paramd, h);
  }
  
  protected final void b()
  {
    Log.e("AccountInputController", "service exception.");
    f.onPayResult(100, g, "pay service exception.");
  }
}

/* Location:
 * Qualified Name:     com.meizu.account.pay.j
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */