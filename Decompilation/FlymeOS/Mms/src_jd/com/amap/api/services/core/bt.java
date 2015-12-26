package com.amap.api.services.core;

import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;

public abstract class bt
{
  int e = 20000;
  int f = 20000;
  HttpHost g = null;
  
  public final void a(HttpHost paramHttpHost)
  {
    g = paramHttpHost;
  }
  
  public abstract String b();
  
  public final void c(int paramInt)
  {
    e = paramInt;
  }
  
  public abstract Map<String, String> c_();
  
  public final void d(int paramInt)
  {
    f = paramInt;
  }
  
  public abstract Map<String, String> d_();
  
  public abstract HttpEntity e();
  
  public byte[] e_()
  {
    return null;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.core.bt
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */