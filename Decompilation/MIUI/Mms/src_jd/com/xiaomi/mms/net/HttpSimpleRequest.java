package com.xiaomi.mms.net;

import android.text.TextUtils;
import com.xiaomi.mms.utils.IOUtils;
import com.xiaomi.mms.utils.ObjectUtils;
import com.xiaomi.mms.utils.logger.MyLog;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import miui.cloud.CloudManager;

abstract class HttpSimpleRequest
  implements SimpleRequest
{
  private final Map<String, String> cookies = new HashMap();
  private final AtomicBoolean isClosed = new AtomicBoolean(false);
  private final AtomicBoolean isConnected = new AtomicBoolean(false);
  private final AtomicBoolean isConnecting = new AtomicBoolean(false);
  private HttpURLConnection mConn;
  private String url;
  
  protected HttpSimpleRequest(String paramString)
  {
    url = paramString;
    try
    {
      mConn = ((HttpURLConnection)new URL(paramString).openConnection());
      return;
    }
    catch (MalformedURLException localMalformedURLException)
    {
      throw new IllegalArgumentException("malformed url: " + paramString);
    }
    catch (IOException localIOException)
    {
      throw new IllegalArgumentException("failed to init url for " + paramString);
    }
  }
  
  private void checkConnection()
  {
    if (!isConnected.get()) {
      throw new IllegalStateException("connection not established");
    }
  }
  
  private void checkHttpStatus()
    throws IOException
  {
    int i = -1;
    try
    {
      int j = mConn.getResponseCode();
      i = j;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      for (;;)
      {
        MyLog.e("HttpSimpleRequest", "check http status error");
      }
    }
    if ((i != 200) && (i != 302)) {
      throw new IOException("invalid http status: " + i);
    }
  }
  
  public void addCookie(String paramString1, String paramString2)
  {
    cookies.put(paramString1, paramString2);
  }
  
  public void close()
  {
    if (!isClosed.getAndSet(true))
    {
      mConn.disconnect();
      return;
    }
    MyLog.w("HttpSimpleRequest", "connection has been closed");
  }
  
  public void connect()
    throws IOException
  {
    if (!isConnecting.getAndSet(true))
    {
      setupConnection();
      mConn.connect();
      isConnected.set(true);
      return;
    }
    MyLog.w("HttpSimpleRequest", "connect has been called");
  }
  
  protected HttpURLConnection getConnection()
  {
    return mConn;
  }
  
  public abstract String getMethod();
  
  public byte[] getResponseBody()
    throws IOException
  {
    checkConnection();
    checkHttpStatus();
    return IOUtils.toByteArray(mConn.getInputStream());
  }
  
  protected void setupConnection()
    throws ProtocolException
  {
    mConn.setInstanceFollowRedirects(false);
    mConn.setConnectTimeout(20000);
    mConn.setReadTimeout(20000);
    mConn.setUseCaches(false);
    mConn.setDoOutput(true);
    mConn.setDoInput(true);
    mConn.setRequestProperty("User-Agent", CloudManager.getUserAgent());
    String str = ObjectUtils.joinMap(cookies, "; ");
    if (!TextUtils.isEmpty(str)) {
      mConn.setRequestProperty("Cookie", str);
    }
    mConn.setRequestMethod(getMethod());
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.net.HttpSimpleRequest
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */