import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

class aqd
  implements X509TrustManager
{
  aqd(ab.b paramb) {}
  
  public void checkClientTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString) {}
  
  public void checkServerTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString) {}
  
  public X509Certificate[] getAcceptedIssuers()
  {
    return null;
  }
}

/* Location:
 * Qualified Name:     aqd
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */