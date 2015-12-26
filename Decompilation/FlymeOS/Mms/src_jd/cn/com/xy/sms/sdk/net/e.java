package cn.com.xy.sms.sdk.net;

import cn.com.xy.sms.sdk.log.LogManager;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

final class e
  implements HostnameVerifier
{
  e(d paramd) {}
  
  public final boolean verify(String paramString, SSLSession paramSSLSession)
  {
    boolean bool = false;
    try
    {
      LogManager.e("Https", "hostName: " + paramString, null);
      if ((d.a(a) != 0) || (paramString == null) || ((paramString.indexOf("duoqu.in") == -1) && (paramString.indexOf("bizport.cn") == -1)))
      {
        HostnameVerifier localHostnameVerifier = HttpsURLConnection.getDefaultHostnameVerifier();
        if (localHostnameVerifier == null) {
          return bool;
        }
        bool = localHostnameVerifier.verify(paramString, paramSSLSession);
        return bool;
      }
    }
    catch (Throwable paramString)
    {
      paramString.printStackTrace();
      return false;
    }
    bool = true;
    return bool;
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.net.e
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */