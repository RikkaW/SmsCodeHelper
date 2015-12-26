import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class atk
{
  private boolean a;
  private Map<String, String> b;
  private Map<String, String> c;
  private StringBuffer d;
  private String e;
  
  public atk(String paramString)
  {
    e = paramString;
    e();
  }
  
  private static void a(StringBuffer paramStringBuffer, Map<String, String> paramMap)
  {
    Iterator localIterator = paramMap.keySet().iterator();
    if (!localIterator.hasNext()) {
      return;
    }
    String str1 = (String)localIterator.next();
    int i = 0;
    for (;;)
    {
      i = paramStringBuffer.indexOf(str1, i);
      if (i <= -1) {
        break;
      }
      String str2 = ((String)paramMap.get(str1)).split(" ")[0];
      int j = str2.length();
      paramStringBuffer.replace(i, i + j, str2);
      i += j;
    }
  }
  
  private static void b(StringBuffer paramStringBuffer, Map<String, String> paramMap)
  {
    Iterator localIterator = paramMap.keySet().iterator();
    String str;
    int i;
    do
    {
      if (!localIterator.hasNext()) {
        return;
      }
      str = (String)localIterator.next();
      i = paramStringBuffer.indexOf(str);
    } while (i <= -1);
    paramStringBuffer.replace(i, i + 1, (String)paramMap.get(str));
  }
  
  private void e()
  {
    b = new LinkedHashMap();
    c = new LinkedHashMap();
  }
  
  public void a(StringBuffer paramStringBuffer)
  {
    d = paramStringBuffer;
  }
  
  public boolean a()
  {
    if (a) {
      return true;
    }
    Object localObject1 = null;
    try
    {
      Object localObject2 = atk.class.getClassLoader().getResourceAsStream(e + ".properties");
      localObject1 = localObject2;
      localObject2 = atm.a((InputStream)localObject2, new String[] { "phrase", "character" });
      if (atm.a((String[])localObject2))
      {
        atm.a("cannot get config: " + e);
        return false;
      }
    }
    catch (Exception localException)
    {
      atm.a("initDict Exception: " + localException.getStackTrace());
      if (localObject1 != null) {}
      try
      {
        ((InputStream)localObject1).close();
        return false;
      }
      catch (IOException localIOException)
      {
        for (;;)
        {
          atm.a("initDict close IOException: " + localIOException.getStackTrace());
        }
      }
      b = atl.a(localException[0]);
      c = atl.a(localException[1]);
      if ((atm.a(b)) || (atm.a(c)))
      {
        atm.a("cannot get dict");
        return false;
      }
      a = true;
    }
    return a;
  }
  
  public void b()
  {
    if (atm.a(d))
    {
      atm.a("missing src");
      return;
    }
    a();
    if (d.length() == 1)
    {
      b(d, c);
      return;
    }
    if (!atm.a(b)) {
      a(d, b);
    }
    while (!atm.a(c))
    {
      a(d, c);
      return;
      atm.a("missing dictPhrase");
    }
    atm.a("missing dictChar");
  }
  
  public void c()
  {
    d = null;
  }
  
  public String d()
  {
    return d.toString();
  }
}

/* Location:
 * Qualified Name:     atk
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */