import com.amap.api.location.core.AMapLocException;
import org.json.JSONException;
import org.json.JSONObject;

public class ahf
{
  private String a = "";
  private double b = 0.0D;
  private double c = 0.0D;
  private float d = 0.0F;
  private float e = 0.0F;
  private float f = 0.0F;
  private long g = 0L;
  private AMapLocException h = new AMapLocException();
  private String i = "new";
  private String j = "";
  private String k = "";
  private String l = "";
  private String m = "";
  private String n = "";
  private String o = "";
  private String p = "";
  private String q = "";
  private String r = "";
  private String s = "";
  private String t = "";
  private String u = "";
  private String v = "";
  private String w = "";
  private String x = "";
  private JSONObject y = null;
  
  public ahf() {}
  
  public ahf(JSONObject paramJSONObject)
  {
    if (paramJSONObject != null) {}
    try
    {
      a = paramJSONObject.getString("provider");
      b = paramJSONObject.getDouble("lon");
      c = paramJSONObject.getDouble("lat");
      d = ((float)paramJSONObject.getLong("accuracy"));
      e = ((float)paramJSONObject.getLong("speed"));
      f = ((float)paramJSONObject.getLong("bearing"));
      g = paramJSONObject.getLong("time");
      i = paramJSONObject.getString("type");
      j = paramJSONObject.getString("retype");
      k = paramJSONObject.getString("citycode");
      l = paramJSONObject.getString("desc");
      m = paramJSONObject.getString("adcode");
      n = paramJSONObject.getString("country");
      o = paramJSONObject.getString("province");
      p = paramJSONObject.getString("city");
      q = paramJSONObject.getString("road");
      r = paramJSONObject.getString("street");
      s = paramJSONObject.getString("poiname");
      u = paramJSONObject.getString("floor");
      t = paramJSONObject.getString("poiid");
      v = paramJSONObject.getString("coord");
      w = paramJSONObject.getString("mcell");
      x = paramJSONObject.getString("district");
      return;
    }
    catch (Throwable paramJSONObject)
    {
      paramJSONObject.printStackTrace();
    }
  }
  
  public AMapLocException a()
  {
    return h;
  }
  
  public void a(double paramDouble)
  {
    b = paramDouble;
  }
  
  public void a(float paramFloat)
  {
    d = paramFloat;
  }
  
  public void a(long paramLong)
  {
    g = paramLong;
  }
  
  public void a(AMapLocException paramAMapLocException)
  {
    h = paramAMapLocException;
  }
  
  public void a(String paramString)
  {
    t = paramString;
  }
  
  public void a(JSONObject paramJSONObject)
  {
    y = paramJSONObject;
  }
  
  public String b()
  {
    return t;
  }
  
  public void b(double paramDouble)
  {
    c = paramDouble;
  }
  
  public void b(float paramFloat)
  {
    f = paramFloat;
  }
  
  public void b(String paramString)
  {
    u = paramString;
  }
  
  public String c()
  {
    return u;
  }
  
  public void c(String paramString)
  {
    x = paramString;
  }
  
  public String d()
  {
    return x;
  }
  
  public void d(String paramString)
  {
    v = paramString;
  }
  
  public double e()
  {
    return b;
  }
  
  public void e(String paramString)
  {
    w = paramString;
  }
  
  public double f()
  {
    return c;
  }
  
  public void f(String paramString)
  {
    a = paramString;
  }
  
  public float g()
  {
    return d;
  }
  
  public void g(String paramString)
  {
    i = paramString;
  }
  
  public long h()
  {
    return g;
  }
  
  public void h(String paramString)
  {
    j = paramString;
  }
  
  public String i()
  {
    return i;
  }
  
  public void i(String paramString)
  {
    k = paramString;
  }
  
  public String j()
  {
    return j;
  }
  
  public void j(String paramString)
  {
    l = paramString;
  }
  
  public String k()
  {
    return k;
  }
  
  public void k(String paramString)
  {
    m = paramString;
  }
  
  public String l()
  {
    return l;
  }
  
  public void l(String paramString)
  {
    n = paramString;
  }
  
  public String m()
  {
    return m;
  }
  
  public void m(String paramString)
  {
    o = paramString;
  }
  
  public String n()
  {
    return n;
  }
  
  public void n(String paramString)
  {
    p = paramString;
  }
  
  public String o()
  {
    return o;
  }
  
  public void o(String paramString)
  {
    q = paramString;
  }
  
  public String p()
  {
    return p;
  }
  
  public void p(String paramString)
  {
    r = paramString;
  }
  
  public String q()
  {
    return q;
  }
  
  public void q(String paramString)
  {
    s = paramString;
  }
  
  public String r()
  {
    return r;
  }
  
  public String s()
  {
    return s;
  }
  
  public JSONObject t()
  {
    return y;
  }
  
  public String u()
  {
    try
    {
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("provider", a);
      localJSONObject.put("lon", b);
      localJSONObject.put("lat", c);
      localJSONObject.put("accuracy", d);
      localJSONObject.put("speed", e);
      localJSONObject.put("bearing", f);
      localJSONObject.put("time", g);
      localJSONObject.put("type", i);
      localJSONObject.put("retype", j);
      localJSONObject.put("citycode", k);
      localJSONObject.put("desc", l);
      localJSONObject.put("adcode", m);
      localJSONObject.put("country", n);
      localJSONObject.put("province", o);
      localJSONObject.put("city", p);
      localJSONObject.put("road", q);
      localJSONObject.put("street", r);
      localJSONObject.put("poiname", s);
      localJSONObject.put("poiid", t);
      localJSONObject.put("floor", u);
      localJSONObject.put("coord", v);
      localJSONObject.put("mcell", w);
      localJSONObject.put("district", x);
      if (localJSONObject == null) {
        return null;
      }
    }
    catch (JSONException localJSONException)
    {
      Object localObject;
      for (;;)
      {
        ahz.a(localJSONException);
        localObject = null;
      }
      return ((JSONObject)localObject).toString();
    }
  }
}

/* Location:
 * Qualified Name:     ahf
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */