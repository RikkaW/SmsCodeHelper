package cn.com.xy.sms.sdk.net;

import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

public final class b
{
  private SSLSocketFactory a = null;
  
  public static HttpsURLConnection a(String paramString)
  {
    return a(paramString, 1);
  }
  
  public static HttpsURLConnection a(String paramString, int paramInt)
  {
    try
    {
      b localb = new b();
      paramString = (HttpsURLConnection)new URL(paramString).openConnection();
      localThrowable1.printStackTrace();
    }
    catch (Throwable localThrowable1)
    {
      try
      {
        localb.a(paramString, paramInt);
        paramString.connect();
        return paramString;
      }
      catch (Throwable localThrowable2)
      {
        for (;;) {}
      }
      localThrowable1 = localThrowable1;
      paramString = null;
    }
    return paramString;
  }
  
  private void a(URL paramURL)
  {
    paramURL = (HttpURLConnection)paramURL.openConnection();
    if ((paramURL instanceof HttpsURLConnection)) {
      a((HttpsURLConnection)paramURL, 1);
    }
  }
  
  /* Error */
  private void a(HttpsURLConnection paramHttpsURLConnection, int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 12	cn/com/xy/sms/sdk/net/b:a	Ljavax/net/ssl/SSLSocketFactory;
    //   4: ifnonnull +14 -> 18
    //   7: aload_0
    //   8: iload_2
    //   9: invokestatic 48	cn/com/xy/sms/sdk/net/d:a	(I)Lcn/com/xy/sms/sdk/net/d;
    //   12: invokevirtual 51	cn/com/xy/sms/sdk/net/d:a	()Ljavax/net/ssl/SSLSocketFactory;
    //   15: putfield 12	cn/com/xy/sms/sdk/net/b:a	Ljavax/net/ssl/SSLSocketFactory;
    //   18: aload_0
    //   19: getfield 12	cn/com/xy/sms/sdk/net/b:a	Ljavax/net/ssl/SSLSocketFactory;
    //   22: ifnull +11 -> 33
    //   25: aload_1
    //   26: aload_0
    //   27: getfield 12	cn/com/xy/sms/sdk/net/b:a	Ljavax/net/ssl/SSLSocketFactory;
    //   30: invokevirtual 55	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
    //   33: aload_1
    //   34: iload_2
    //   35: invokestatic 48	cn/com/xy/sms/sdk/net/d:a	(I)Lcn/com/xy/sms/sdk/net/d;
    //   38: invokevirtual 59	cn/com/xy/sms/sdk/net/d:b	()Ljavax/net/ssl/HostnameVerifier;
    //   41: invokevirtual 63	javax/net/ssl/HttpsURLConnection:setHostnameVerifier	(Ljavax/net/ssl/HostnameVerifier;)V
    //   44: return
    //   45: astore_1
    //   46: aload_1
    //   47: instanceof 65
    //   50: ifeq +8 -> 58
    //   53: aload_1
    //   54: checkcast 65	java/io/IOException
    //   57: athrow
    //   58: new 65	java/io/IOException
    //   61: dup
    //   62: aload_1
    //   63: invokespecial 68	java/io/IOException:<init>	(Ljava/lang/Throwable;)V
    //   66: athrow
    //   67: astore_1
    //   68: new 65	java/io/IOException
    //   71: dup
    //   72: aload_1
    //   73: invokespecial 68	java/io/IOException:<init>	(Ljava/lang/Throwable;)V
    //   76: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	77	0	this	b
    //   0	77	1	paramHttpsURLConnection	HttpsURLConnection
    //   0	77	2	paramInt	int
    // Exception table:
    //   from	to	target	type
    //   7	18	45	java/lang/Throwable
    //   33	44	67	java/lang/Throwable
  }
  
  /* Error */
  public static HttpsURLConnection b(String paramString)
  {
    // Byte code:
    //   0: new 2	cn/com/xy/sms/sdk/net/b
    //   3: dup
    //   4: invokespecial 20	cn/com/xy/sms/sdk/net/b:<init>	()V
    //   7: astore_1
    //   8: new 22	java/net/URL
    //   11: dup
    //   12: aload_0
    //   13: invokespecial 25	java/net/URL:<init>	(Ljava/lang/String;)V
    //   16: invokevirtual 29	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   19: checkcast 31	javax/net/ssl/HttpsURLConnection
    //   22: astore_0
    //   23: aload_1
    //   24: aload_0
    //   25: iconst_1
    //   26: invokespecial 34	cn/com/xy/sms/sdk/net/b:a	(Ljavax/net/ssl/HttpsURLConnection;I)V
    //   29: aload_0
    //   30: areturn
    //   31: astore_0
    //   32: aconst_null
    //   33: areturn
    //   34: astore_1
    //   35: aload_0
    //   36: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	37	0	paramString	String
    //   7	17	1	localb	b
    //   34	1	1	localThrowable	Throwable
    // Exception table:
    //   from	to	target	type
    //   0	23	31	java/lang/Throwable
    //   23	29	34	java/lang/Throwable
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.net.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */