import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class afj
{
  private String a;
  private List<String> b;
  private Map<String, Collection<String>> c = new HashMap();
  private String d;
  private List<String> e;
  private byte[] f;
  
  public String a()
  {
    return a;
  }
  
  public void a(String paramString)
  {
    if (a != null) {
      Log.w("vCard", String.format("Property name is re-defined (existing: %s, requested: %s", new Object[] { a, paramString }));
    }
    a = paramString;
  }
  
  public void a(String paramString1, String paramString2)
  {
    Object localObject;
    if (!c.containsKey(paramString1)) {
      if (paramString1.equals("TYPE"))
      {
        localObject = new HashSet();
        c.put(paramString1, localObject);
      }
    }
    for (;;)
    {
      ((Collection)localObject).add(paramString2);
      return;
      localObject = new ArrayList();
      break;
      localObject = (Collection)c.get(paramString1);
    }
  }
  
  public void a(List<String> paramList)
  {
    e = paramList;
  }
  
  public void a(byte[] paramArrayOfByte)
  {
    f = paramArrayOfByte;
  }
  
  public void a(String... paramVarArgs)
  {
    e = Arrays.asList(paramVarArgs);
  }
  
  public Map<String, Collection<String>> b()
  {
    return c;
  }
  
  public void b(String paramString)
  {
    if (b == null) {
      b = new ArrayList();
    }
    b.add(paramString);
  }
  
  public String c()
  {
    return d;
  }
  
  public void c(String paramString)
  {
    d = paramString;
  }
  
  public Collection<String> d(String paramString)
  {
    return (Collection)c.get(paramString);
  }
  
  public List<String> d()
  {
    return e;
  }
  
  public byte[] e()
  {
    return f;
  }
}

/* Location:
 * Qualified Name:     afj
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */