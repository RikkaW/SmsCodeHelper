package com.meizu.account.pay;

import android.os.AsyncTask;
import android.os.RemoteException;
import com.meizu.account.pay.a.j;

final class c
  extends AsyncTask
{
  c(a parama) {}
  
  private Void a()
  {
    com.meizu.account.pay.a.g localg = (com.meizu.account.pay.a.g)a.a(a).a();
    if (localg != null) {}
    for (;;)
    {
      try
      {
        a.a(localg, new d(this));
        return null;
      }
      catch (RemoteException localRemoteException)
      {
        localRemoteException.printStackTrace();
        a.a(a, new g(this));
        continue;
      }
      a.a(a, new h(this));
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.account.pay.c
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */