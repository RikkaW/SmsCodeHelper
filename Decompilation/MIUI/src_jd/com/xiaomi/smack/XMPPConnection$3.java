package com.xiaomi.smack;

import com.xiaomi.network.HostManager;

class XMPPConnection$3
  implements Runnable
{
  XMPPConnection$3(XMPPConnection paramXMPPConnection, String paramString) {}
  
  public void run()
  {
    HostManager.getInstance().getFallbacksByHost(val$host, true);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.smack.XMPPConnection.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */