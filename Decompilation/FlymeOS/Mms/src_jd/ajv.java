import android.text.TextUtils;
import android.util.Log;
import java.io.IOException;
import java.net.Socket;
import java.net.URI;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;

public class ajv
{
  public static String a(String paramString, Map<String, String> paramMap, Scheme paramScheme, HttpParams paramHttpParams, HttpEntity paramHttpEntity)
  {
    if (TextUtils.isEmpty(paramString)) {}
    for (;;)
    {
      return null;
      HttpPost localHttpPost = new HttpPost();
      localHttpPost.addHeader("Accept-Charset", "UTF-8");
      if (paramMap != null)
      {
        Iterator localIterator = paramMap.keySet().iterator();
        while (localIterator.hasNext())
        {
          String str = (String)localIterator.next();
          localHttpPost.addHeader(str, (String)paramMap.get(str));
        }
      }
      if (paramHttpParams != null) {
        localHttpPost.setParams(paramHttpParams);
      }
      localHttpPost.setURI(URI.create(paramString));
      paramMap = new DefaultHttpClient();
      paramMap.getConnectionManager().getSchemeRegistry().register(paramScheme);
      try
      {
        localHttpPost.setEntity(paramHttpEntity);
        paramMap = paramMap.execute(localHttpPost);
        if (paramMap != null) {
          if (200 == paramMap.getStatusLine().getStatusCode())
          {
            paramString = EntityUtils.toString(paramMap.getEntity(), "UTF-8");
          }
          else
          {
            Log.e("HttpHelper", "executeHttpPost, rescode=" + paramMap.getStatusLine().getStatusCode() + ", url=" + paramString);
            paramString = null;
          }
        }
      }
      catch (ClientProtocolException paramString)
      {
        paramString.printStackTrace();
        return null;
      }
      catch (IOException paramString)
      {
        paramString.printStackTrace();
        return null;
      }
    }
    return paramString;
  }
  
  public static String a(String paramString, HttpEntity paramHttpEntity)
  {
    try
    {
      Object localObject1 = KeyStore.getInstance(KeyStore.getDefaultType());
      ((KeyStore)localObject1).load(null, null);
      localObject1 = new ajv.a((KeyStore)localObject1);
      ((org.apache.http.conn.ssl.SSLSocketFactory)localObject1).setHostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
      localObject1 = new Scheme("https", (SocketFactory)localObject1, 443);
      BasicHttpParams localBasicHttpParams = new BasicHttpParams();
      HttpProtocolParams.setVersion(localBasicHttpParams, HttpVersion.HTTP_1_1);
      HttpProtocolParams.setContentCharset(localBasicHttpParams, "UTF-8");
      return a(paramString, null, (Scheme)localObject1, localBasicHttpParams, paramHttpEntity);
    }
    catch (KeyStoreException localKeyStoreException)
    {
      for (;;)
      {
        localKeyStoreException.printStackTrace();
        Object localObject2 = null;
      }
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      for (;;)
      {
        localNoSuchAlgorithmException.printStackTrace();
        Object localObject3 = null;
      }
    }
    catch (CertificateException localCertificateException)
    {
      for (;;)
      {
        localCertificateException.printStackTrace();
        Object localObject4 = null;
      }
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        localIOException.printStackTrace();
        Object localObject5 = null;
      }
    }
    catch (KeyManagementException localKeyManagementException)
    {
      for (;;)
      {
        localKeyManagementException.printStackTrace();
        Object localObject6 = null;
      }
    }
    catch (UnrecoverableKeyException localUnrecoverableKeyException)
    {
      for (;;)
      {
        localUnrecoverableKeyException.printStackTrace();
        Object localObject7 = null;
      }
    }
  }
  
  static class a
    extends org.apache.http.conn.ssl.SSLSocketFactory
  {
    SSLContext a = SSLContext.getInstance("TLS");
    
    public a(KeyStore paramKeyStore)
    {
      super();
      paramKeyStore = new ajw(this);
      a.init(null, new TrustManager[] { paramKeyStore }, null);
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
}

/* Location:
 * Qualified Name:     ajv
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */