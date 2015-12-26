package cn.com.xy.sms.sdk.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

public final class v
{
  public static final Map<String, JSONObject> f = new HashMap();
  public static final Map<String, JSONObject> g = new HashMap();
  public static final HashSet<String> h = new HashSet();
  public static final HashSet<String> i = new HashSet();
  private static HashMap<String, v> j = new HashMap();
  public Map<String, JSONObject> a = new HashMap();
  public Map<String, JSONObject> b = new HashMap();
  public Map<String, JSONArray> c = new HashMap();
  public HashSet<String> d = new HashSet();
  public HashSet<String> e = new HashSet();
  
  public static v a(String paramString)
  {
    return (v)j.get(paramString);
  }
  
  public static void a()
  {
    try
    {
      f.clear();
      g.clear();
      h.clear();
      i.clear();
      return;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
  }
  
  public static void a(String paramString, Map<String, JSONObject> paramMap)
  {
    if (paramMap == null) {
      return;
    }
    ba.putAll(paramMap);
  }
  
  public static v b(String paramString)
  {
    v localv2 = a(paramString);
    v localv1 = localv2;
    if (localv2 == null)
    {
      localv1 = new v();
      j.put(paramString, localv1);
    }
    return localv1;
  }
  
  public static void b(String paramString, Map<String, JSONObject> paramMap)
  {
    if (paramMap == null) {
      return;
    }
    b(paramString);
    f.putAll(paramMap);
  }
  
  public static void c(String paramString)
  {
    paramString = a(paramString);
    if (paramString != null)
    {
      paramString.b();
      a();
    }
  }
  
  public static void e(String paramString)
  {
    try
    {
      Iterator localIterator = j.entrySet().iterator();
      for (;;)
      {
        if (!localIterator.hasNext()) {
          return;
        }
        ((v)((Map.Entry)localIterator.next()).getValue()).d(paramString);
      }
      return;
    }
    catch (Throwable paramString) {}
  }
  
  public final void b()
  {
    try
    {
      a.clear();
      b.clear();
      c.clear();
      d.clear();
      e.clear();
      return;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
  }
  
  public final void d(String paramString)
  {
    try
    {
      f.remove(paramString);
      g.remove(paramString);
      h.remove(paramString);
      i.remove(paramString);
      a.remove(paramString);
      c.remove(paramString);
      e.remove(paramString);
      i.remove(paramString);
      b.remove(paramString);
      return;
    }
    catch (Throwable paramString) {}
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.util.v
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */