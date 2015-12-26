package com.meizu.account.pay;

import android.content.Intent;
import android.os.Bundle;

final class d
  extends com.meizu.account.pay.a.e
{
  d(c paramc) {}
  
  public final void a(int paramInt, String paramString)
  {
    a.a(c.a(a), new f(this, paramInt, paramString));
  }
  
  public final void a(Bundle paramBundle)
  {
    a.a(c.a(a), new e(this, paramBundle));
  }
  
  public final void b(Bundle paramBundle)
  {
    if (paramBundle.containsKey("intent")) {
      a.a(c.a(a), (Intent)paramBundle.getParcelable("intent"));
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.account.pay.d
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */