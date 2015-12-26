package cn.com.xy.sms.sdk.net;

import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.net.util.f;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.XyUtil;
import cn.com.xy.sms.sdk.util.d;
import java.io.Closeable;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;

public final class h
  extends a
{
  private int d = -1;
  
  public h(int paramInt, String paramString1, String paramString2, XyCallBack paramXyCallBack, boolean paramBoolean)
  {
    super(paramString1, null, paramString2, false, null, paramXyCallBack, true);
    d = paramInt;
  }
  
  public final void run()
  {
    Object localObject1 = null;
    localObject5 = null;
    for (;;)
    {
      try
      {
        localHttpURLConnection = a();
        localObject5 = localHttpURLConnection;
        localObject1 = localHttpURLConnection;
        localHttpURLConnection.addRequestProperty("sceneType", d);
        localObject5 = localHttpURLConnection;
        localObject1 = localHttpURLConnection;
        localHttpURLConnection.addRequestProperty("reqVersion", "5.1.2");
        localObject5 = localHttpURLConnection;
        localObject1 = localHttpURLConnection;
        localHttpURLConnection.addRequestProperty("clientKey", i.e);
        localObject5 = localHttpURLConnection;
        localObject1 = localHttpURLConnection;
        localHttpURLConnection.addRequestProperty("client_key", "123456");
        localObject5 = localHttpURLConnection;
        localObject1 = localHttpURLConnection;
        localHttpURLConnection.connect();
        localObject5 = localHttpURLConnection;
        localObject1 = localHttpURLConnection;
        localObject9 = localHttpURLConnection.getOutputStream();
        localObject5 = localHttpURLConnection;
        localObject1 = localHttpURLConnection;
        localObject7 = XyUtil.getXyValue(1);
        localObject5 = localHttpURLConnection;
        localObject1 = localHttpURLConnection;
        LogManager.e("xyvalue", "tempKey: " + (String)localObject7, null);
        localObject5 = localHttpURLConnection;
        localObject1 = localHttpURLConnection;
        arrayOfByte = f.a(b, (String)localObject7);
        localObject7 = arrayOfByte;
        localObject5 = localHttpURLConnection;
        localObject1 = localHttpURLConnection;
        if (c)
        {
          localObject5 = localHttpURLConnection;
          localObject1 = localHttpURLConnection;
          localObject7 = StringUtils.compressGZip(arrayOfByte);
        }
        localObject5 = localHttpURLConnection;
        localObject1 = localHttpURLConnection;
        ((OutputStream)localObject9).write((byte[])localObject7);
        localObject5 = localHttpURLConnection;
        localObject1 = localHttpURLConnection;
        ((OutputStream)localObject9).flush();
        localObject5 = localHttpURLConnection;
        localObject1 = localHttpURLConnection;
        d.a((Closeable)localObject9);
        localObject5 = localHttpURLConnection;
        localObject1 = localHttpURLConnection;
        i = localHttpURLConnection.getResponseCode();
        localObject5 = localHttpURLConnection;
        localObject1 = localHttpURLConnection;
        new StringBuilder("########sceneType=").append(d);
        localObject5 = localHttpURLConnection;
        localObject1 = localHttpURLConnection;
        new StringBuilder("url=").append(a);
        localObject5 = localHttpURLConnection;
        localObject1 = localHttpURLConnection;
        new StringBuilder("content=").append(b);
        localObject5 = localHttpURLConnection;
        localObject1 = localHttpURLConnection;
        new StringBuilder(" responseCode: ").append(localHttpURLConnection.getResponseCode());
        if (i != 200) {
          continue;
        }
        localObject5 = localHttpURLConnection;
        localObject1 = localHttpURLConnection;
        localObject9 = localHttpURLConnection.getInputStream();
        localObject5 = localHttpURLConnection;
        localObject1 = localHttpURLConnection;
        arrayOfByte = d.b((InputStream)localObject9);
        localObject5 = localHttpURLConnection;
        localObject1 = localHttpURLConnection;
        i = arrayOfByte.length;
        localObject5 = localHttpURLConnection;
        localObject1 = localHttpURLConnection;
        a(b, i);
        if (i <= 26214400L) {
          continue;
        }
        localObject5 = localHttpURLConnection;
        localObject1 = localHttpURLConnection;
        a(-9, "");
        if (localHttpURLConnection == null) {}
      }
      catch (Throwable localThrowable5)
      {
        HttpURLConnection localHttpURLConnection;
        Object localObject9;
        Object localObject7;
        int i;
        Object localObject2;
        Object localObject3 = localObject5;
        LogManager.e("HTTP", "http error: " + localThrowable5.getMessage(), localThrowable5);
        localObject3 = localObject5;
        if (localThrowable5.getClass() != SocketTimeoutException.class) {
          continue;
        }
        localObject3 = localObject5;
        a(-6, null);
        if (localObject5 == null) {
          continue;
        }
        try
        {
          ((HttpURLConnection)localObject5).disconnect();
          return;
        }
        catch (Throwable localThrowable3)
        {
          localThrowable3.printStackTrace();
          return;
        }
        localObject4 = localObject5;
        a(-7, localThrowable5.getMessage());
        continue;
      }
      finally
      {
        byte[] arrayOfByte;
        Object localObject4;
        if (localObject4 == null) {
          continue;
        }
        try
        {
          ((HttpURLConnection)localObject4).disconnect();
          throw ((Throwable)localObject6);
        }
        catch (Throwable localThrowable4)
        {
          localThrowable4.printStackTrace();
          continue;
        }
        Object localObject8 = arrayOfByte;
        continue;
      }
      try
      {
        localHttpURLConnection.disconnect();
        return;
      }
      catch (Throwable localThrowable1)
      {
        localThrowable1.printStackTrace();
        return;
      }
    }
    localObject2 = localHttpURLConnection;
    for (;;)
    {
      try
      {
        if (!c) {
          break label684;
        }
        localObject2 = localHttpURLConnection;
        localObject7 = StringUtils.uncompressGZip(arrayOfByte);
        if (localObject7 == null) {
          break label684;
        }
        localObject5 = localHttpURLConnection;
        localObject2 = localHttpURLConnection;
        localObject7 = new String((byte[])localObject7, "UTF-8");
        localObject5 = localHttpURLConnection;
        localObject2 = localHttpURLConnection;
        a(0, (String)localObject7);
        localObject5 = localHttpURLConnection;
        localObject2 = localHttpURLConnection;
        new StringBuilder("response=").append((String)localObject7).append(" len : ").append(i);
        localObject5 = localHttpURLConnection;
        localObject2 = localHttpURLConnection;
        d.a((Closeable)localObject9);
        if (localHttpURLConnection == null) {
          break;
        }
        try
        {
          localHttpURLConnection.disconnect();
          return;
        }
        catch (Throwable localThrowable2)
        {
          localThrowable2.printStackTrace();
          return;
        }
        localObject5 = localHttpURLConnection;
      }
      catch (Throwable localThrowable6)
      {
        localObject5 = localHttpURLConnection;
        localObject3 = localHttpURLConnection;
        localThrowable6.printStackTrace();
      }
      localObject3 = localHttpURLConnection;
      a(-8, null);
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.net.h
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */