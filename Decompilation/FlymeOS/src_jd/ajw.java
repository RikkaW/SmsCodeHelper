import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

class ajw
  implements X509TrustManager
{
  ajw(ajv.a parama) {}
  
  public void checkClientTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString) {}
  
  public void checkServerTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString) {}
  
  public X509Certificate[] getAcceptedIssuers()
  {
    return null;
  }
}

/* Location:
 * Qualified Name:     ajw
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */