import android.text.TextUtils;
import java.io.File;
import org.json.JSONException;
import org.json.JSONObject;

public class be
{
  private String a;
  private String b;
  private String c;
  private String d;
  private int e;
  private int f;
  private String g;
  private String h;
  private String i;
  private long j;
  
  public static be a(JSONObject paramJSONObject)
  {
    be localbe = new be();
    if (!paramJSONObject.isNull("url")) {}
    try
    {
      localbe.a(paramJSONObject.getString("url"));
      if (!paramJSONObject.isNull("path")) {}
      try
      {
        localbe.b(paramJSONObject.getString("path"));
        if (!paramJSONObject.isNull("md5")) {}
        try
        {
          localbe.c(paramJSONObject.getString("md5"));
          if (!paramJSONObject.isNull("prompt")) {}
          try
          {
            localbe.a(paramJSONObject.getInt("prompt"));
            if (!paramJSONObject.isNull("force")) {}
            try
            {
              localbe.b(paramJSONObject.getInt("force"));
              if (!paramJSONObject.isNull("desc")) {}
              try
              {
                localbe.d(paramJSONObject.getString("desc"));
                if (!paramJSONObject.isNull("version")) {}
                try
                {
                  localbe.e(paramJSONObject.getString("version"));
                  if (!paramJSONObject.isNull("exp")) {}
                  try
                  {
                    localbe.f(paramJSONObject.getString("exp"));
                    if (!paramJSONObject.isNull("size")) {}
                    try
                    {
                      localbe.a(paramJSONObject.getLong("size"));
                      return localbe;
                    }
                    catch (JSONException paramJSONObject)
                    {
                      return localbe;
                    }
                  }
                  catch (JSONException localJSONException1)
                  {
                    for (;;) {}
                  }
                }
                catch (JSONException localJSONException2)
                {
                  for (;;) {}
                }
              }
              catch (JSONException localJSONException3)
              {
                for (;;) {}
              }
            }
            catch (JSONException localJSONException4)
            {
              for (;;) {}
            }
          }
          catch (JSONException localJSONException5)
          {
            for (;;) {}
          }
        }
        catch (JSONException localJSONException6)
        {
          for (;;) {}
        }
      }
      catch (JSONException localJSONException7)
      {
        for (;;) {}
      }
    }
    catch (JSONException localJSONException8)
    {
      for (;;) {}
    }
  }
  
  public String a()
  {
    return b;
  }
  
  public void a(int paramInt)
  {
    e = paramInt;
  }
  
  public void a(long paramLong)
  {
    j = paramLong;
  }
  
  public void a(String paramString)
  {
    b = paramString;
  }
  
  public String b()
  {
    return c;
  }
  
  public void b(int paramInt)
  {
    f = paramInt;
  }
  
  public void b(String paramString)
  {
    c = paramString;
    String str = c;
    if (paramString.indexOf(File.separator) >= 0) {
      str = paramString.substring(paramString.lastIndexOf(File.separator) + 1);
    }
    a = str;
  }
  
  public String c()
  {
    return d;
  }
  
  public void c(String paramString)
  {
    d = paramString;
  }
  
  public String d()
  {
    return i;
  }
  
  public void d(String paramString)
  {
    g = paramString;
  }
  
  public JSONObject e()
  {
    JSONObject localJSONObject = new JSONObject();
    if (!TextUtils.isEmpty(a())) {}
    try
    {
      localJSONObject.put("url", a());
      if (!TextUtils.isEmpty(b())) {}
      try
      {
        localJSONObject.put("path", c);
        if (!TextUtils.isEmpty(c())) {}
        try
        {
          localJSONObject.put("md5", c());
          try
          {
            localJSONObject.put("prompt", e);
            try
            {
              localJSONObject.put("force", f);
              if (!TextUtils.isEmpty(g)) {}
              try
              {
                localJSONObject.put("desc", g);
                if (!TextUtils.isEmpty(h)) {}
                try
                {
                  localJSONObject.put("version", h);
                  if (!TextUtils.isEmpty(i)) {}
                  try
                  {
                    localJSONObject.put("exp", i);
                    if (j != 0L) {}
                    try
                    {
                      localJSONObject.put("size", j);
                      return localJSONObject;
                    }
                    catch (JSONException localJSONException1)
                    {
                      return localJSONObject;
                    }
                  }
                  catch (JSONException localJSONException2)
                  {
                    for (;;) {}
                  }
                }
                catch (JSONException localJSONException3)
                {
                  for (;;) {}
                }
              }
              catch (JSONException localJSONException4)
              {
                for (;;) {}
              }
            }
            catch (JSONException localJSONException5)
            {
              for (;;) {}
            }
          }
          catch (JSONException localJSONException6)
          {
            for (;;) {}
          }
        }
        catch (JSONException localJSONException7)
        {
          for (;;) {}
        }
      }
      catch (JSONException localJSONException8)
      {
        for (;;) {}
      }
    }
    catch (JSONException localJSONException9)
    {
      for (;;) {}
    }
  }
  
  public void e(String paramString)
  {
    h = paramString;
  }
  
  public String f()
  {
    return a;
  }
  
  public void f(String paramString)
  {
    i = paramString;
  }
}

/* Location:
 * Qualified Name:     be
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */