import java.net.Socket;
import java.security.KeyStore;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

public class aup
  extends org.apache.http.conn.ssl.SSLSocketFactory
{
  SSLContext a = SSLContext.getInstance("TLS");
  
  public aup(KeyStore paramKeyStore)
  {
    super(paramKeyStore);
    paramKeyStore = new auq(this);
    a.init(null, new TrustManager[] { paramKeyStore }, null);
  }
  
  public static KeyStore a()
  {
    try
    {
      localKeyStore = KeyStore.getInstance(KeyStore.getDefaultType());
      localThrowable1.printStackTrace();
    }
    catch (Throwable localThrowable1)
    {
      try
      {
        localKeyStore.load(null, null);
        return localKeyStore;
      }
      catch (Throwable localThrowable2)
      {
        KeyStore localKeyStore;
        for (;;) {}
      }
      localThrowable1 = localThrowable1;
      localKeyStore = null;
    }
    return localKeyStore;
  }
  
  public static org.apache.http.conn.ssl.SSLSocketFactory b()
  {
    try
    {
      aup localaup = new aup(a());
      localaup.setHostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
      return localaup;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return org.apache.http.conn.ssl.SSLSocketFactory.getSocketFactory();
  }
  
  public Socket createSocket()
  {
    return a.getSocketFactory().createSocket();
  }
  
  public Socket createSocket(Socket paramSocket, String paramString, int paramInt, boolean paramBoolean)
  {
    return a.getSocketFactory().createSocket(paramSocket, paramString, paramInt, paramBoolean);
  }
}

/* Location:
 * Qualified Name:     aup
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */