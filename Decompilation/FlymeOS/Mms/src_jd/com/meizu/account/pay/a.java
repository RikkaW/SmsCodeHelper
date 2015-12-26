package com.meizu.account.pay;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Handler;
import com.meizu.account.pay.a.d;
import com.meizu.account.pay.a.g;
import com.meizu.account.pay.a.j;

public abstract class a
{
  protected Activity a;
  private j b;
  private Handler c;
  
  public a(Activity paramActivity)
  {
    a = paramActivity;
    c = new Handler(a.getMainLooper());
    if (a == null) {
      throw new IllegalArgumentException("activity cant be null!");
    }
    paramActivity = new b();
    b = new j(a, paramActivity, "com.meizu.account.pay.SystemPayService", "com.meizu.account");
  }
  
  private void a(Runnable paramRunnable)
  {
    c.post(paramRunnable);
  }
  
  protected abstract void a();
  
  protected abstract void a(int paramInt, String paramString);
  
  protected abstract void a(g paramg, d paramd);
  
  protected abstract void b();
  
  public final void c()
  {
    new c(this).execute(new Void[0]);
  }
}

/* Location:
 * Qualified Name:     com.meizu.account.pay.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */