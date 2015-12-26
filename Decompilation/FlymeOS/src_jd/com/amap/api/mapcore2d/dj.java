package com.amap.api.mapcore2d;

import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpEntity;

class dj
  extends ey
{
  private Map<String, String> a = new HashMap();
  private String e;
  private Map<String, String> f = new HashMap();
  
  void a(String paramString)
  {
    e = paramString;
  }
  
  void a(Map<String, String> paramMap)
  {
    a.clear();
    a.putAll(paramMap);
  }
  
  public Map<String, String> b()
  {
    return a;
  }
  
  void b(Map<String, String> paramMap)
  {
    f.clear();
    f.putAll(paramMap);
  }
  
  public Map<String, String> c()
  {
    return f;
  }
  
  public String d()
  {
    return e;
  }
  
  public HttpEntity e()
  {
    return null;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.dj
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */