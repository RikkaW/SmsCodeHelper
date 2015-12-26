package cn.com.xy.sms.sdk.net;

import java.io.ByteArrayInputStream;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

public class d
{
  private static d d = null;
  private static d e = null;
  private SSLContext a;
  private SSLSocketFactory b;
  private HostnameVerifier c;
  private int f = 1;
  
  private d(int paramInt)
  {
    f = paramInt;
    c = new e(this);
  }
  
  public static d a(int paramInt)
  {
    if (paramInt == 0) {}
    try
    {
      if (e == null) {
        e = new d(paramInt);
      }
      locald = e;
      return locald;
    }
    finally {}
    if (d == null) {
      d = new d(paramInt);
    }
    d locald = d;
    return locald;
  }
  
  private static Certificate a(String paramString1, String paramString2)
  {
    return CertificateFactory.getInstance(paramString2).generateCertificate(new ByteArrayInputStream(paramString1.getBytes()));
  }
  
  private static X509Certificate a(String paramString)
  {
    return (X509Certificate)CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(paramString.getBytes()));
  }
  
  private void a(HostnameVerifier paramHostnameVerifier)
  {
    c = paramHostnameVerifier;
  }
  
  private SSLContext c()
  {
    try
    {
      if (a == null)
      {
        localObject1 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        ((TrustManagerFactory)localObject1).init(null);
        SSLContext localSSLContext = SSLContext.getInstance("TLS");
        localSSLContext.init(null, ((TrustManagerFactory)localObject1).getTrustManagers(), new SecureRandom());
        a = localSSLContext;
      }
      Object localObject1 = a;
      return (SSLContext)localObject1;
    }
    finally {}
  }
  
  public final SSLSocketFactory a()
  {
    try
    {
      if (b == null) {
        b = c().getSocketFactory();
      }
      SSLSocketFactory localSSLSocketFactory = b;
      return localSSLSocketFactory;
    }
    finally {}
  }
  
  public final HostnameVerifier b()
  {
    return c;
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.net.d
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */