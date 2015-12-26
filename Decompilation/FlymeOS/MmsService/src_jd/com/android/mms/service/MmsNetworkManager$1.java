package com.android.mms.service;

import android.net.ConnectivityManager.NetworkCallback;
import android.net.Network;
import android.util.Log;

class MmsNetworkManager$1
  extends ConnectivityManager.NetworkCallback
{
  MmsNetworkManager$1(MmsNetworkManager paramMmsNetworkManager) {}
  
  public void onAvailable(Network paramNetwork)
  {
    super.onAvailable(paramNetwork);
    Log.d("MmsService", "NetworkCallbackListener.onAvailable: network=" + paramNetwork);
    synchronized (this$0)
    {
      MmsNetworkManager.access$002(this$0, paramNetwork);
      this$0.notifyAll();
      return;
    }
  }
  
  public void onLost(Network arg1)
  {
    super.onLost(???);
    Log.d("MmsService", "NetworkCallbackListener.onLost: network=" + ???);
    synchronized (this$0)
    {
      MmsNetworkManager.access$102(this$0, true);
      MmsNetworkManager.access$200(this$0, this);
      this$0.notifyAll();
      return;
    }
  }
  
  public void onUnavailable()
  {
    super.onUnavailable();
    Log.d("MmsService", "NetworkCallbackListener.onUnavailable");
    synchronized (this$0)
    {
      MmsNetworkManager.access$102(this$0, true);
      MmsNetworkManager.access$200(this$0, this);
      this$0.notifyAll();
      return;
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.service.MmsNetworkManager.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */