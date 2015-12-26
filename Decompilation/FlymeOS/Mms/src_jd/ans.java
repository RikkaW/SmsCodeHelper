import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ans
{
  private Properties a = new Properties();
  private Map<String, Properties> b = new HashMap();
  
  public void a(InputStream paramInputStream)
  {
    BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(paramInputStream, "utf-8"));
    char[] arrayOfChar = new char['က'];
    int j = localBufferedReader.read(arrayOfChar, 0, 4096);
    Object localObject4 = ans.a.a;
    int m = 0;
    Object localObject5 = null;
    Object localObject6 = null;
    Object localObject2 = new StringBuilder();
    int k;
    for (;;)
    {
      if (j < 0)
      {
        if (((StringBuilder)localObject2).length() > 0)
        {
          paramInputStream = ((StringBuilder)localObject2).toString().trim();
          new StringBuilder();
          if (localObject6 != null)
          {
            if (localObject5 != null) {
              break label716;
            }
            a((String)localObject6, paramInputStream);
          }
        }
        return;
      }
      k = 0;
      if (k < j) {
        break;
      }
      j = localBufferedReader.read(arrayOfChar, 0, 4096);
    }
    char c = arrayOfChar[k];
    paramInputStream = (InputStream)localObject4;
    Object localObject3;
    Object localObject7;
    int i;
    if (localObject4 == ans.a.d)
    {
      if (c != '\r')
      {
        localObject1 = localObject2;
        localObject3 = localObject6;
        localObject7 = localObject5;
        i = m;
        paramInputStream = (InputStream)localObject4;
        if (c != '\n') {}
      }
      else
      {
        paramInputStream = ans.a.a;
      }
    }
    else
    {
      if (paramInputStream != ans.a.b) {
        break label275;
      }
      ((StringBuilder)localObject2).append(c);
      if (c != '\r') {
        break label253;
      }
      paramInputStream = ans.a.c;
      i = m;
      localObject7 = localObject5;
      localObject3 = localObject6;
      localObject1 = localObject2;
    }
    for (;;)
    {
      k += 1;
      localObject2 = localObject1;
      localObject6 = localObject3;
      localObject5 = localObject7;
      m = i;
      localObject4 = paramInputStream;
      break;
      label253:
      paramInputStream = ans.a.a;
      localObject1 = localObject2;
      localObject3 = localObject6;
      localObject7 = localObject5;
      i = m;
      continue;
      switch (c)
      {
      default: 
        ((StringBuilder)localObject2).append(c);
        localObject1 = localObject2;
        localObject3 = localObject6;
        localObject7 = localObject5;
        i = m;
        break;
      case '[': 
        localObject1 = new StringBuilder();
        i = 1;
        localObject3 = localObject6;
        localObject7 = localObject5;
        break;
      case ']': 
        if (m != 0)
        {
          localObject7 = ((StringBuilder)localObject2).toString().trim();
          localObject1 = new StringBuilder();
          b.put(localObject7, new Properties());
          i = 0;
          localObject3 = localObject6;
        }
        else
        {
          ((StringBuilder)localObject2).append(c);
          localObject1 = localObject2;
          localObject3 = localObject6;
          localObject7 = localObject5;
          i = m;
        }
        break;
      case '\\': 
        paramInputStream = ans.a.b;
        localObject1 = localObject2;
        localObject3 = localObject6;
        localObject7 = localObject5;
        i = m;
        break;
      case '#': 
      case ';': 
        paramInputStream = ans.a.d;
        localObject1 = localObject2;
        localObject3 = localObject6;
        localObject7 = localObject5;
        i = m;
        break;
      case ':': 
      case '=': 
        if (localObject6 == null)
        {
          localObject3 = ((StringBuilder)localObject2).toString().trim();
          localObject1 = new StringBuilder();
          localObject7 = localObject5;
          i = m;
        }
        else
        {
          ((StringBuilder)localObject2).append(c);
          localObject1 = localObject2;
          localObject3 = localObject6;
          localObject7 = localObject5;
          i = m;
        }
        break;
      case '\n': 
      case '\r': 
        label275:
        if ((paramInputStream != ans.a.c) || (c != '\n')) {
          break label629;
        }
        ((StringBuilder)localObject2).append(c);
        paramInputStream = ans.a.a;
        localObject1 = localObject2;
        localObject3 = localObject6;
        localObject7 = localObject5;
        i = m;
      }
    }
    label629:
    Object localObject1 = localObject2;
    if (((StringBuilder)localObject2).length() > 0)
    {
      localObject3 = ((StringBuilder)localObject2).toString().trim();
      localObject2 = new StringBuilder();
      localObject1 = localObject2;
      if (localObject6 != null)
      {
        if (localObject5 != null) {
          break label699;
        }
        a((String)localObject6, (String)localObject3);
      }
    }
    for (localObject1 = localObject2;; localObject1 = localObject2)
    {
      localObject3 = null;
      localObject7 = localObject5;
      i = m;
      break;
      label699:
      a((String)localObject5, (String)localObject6, (String)localObject3);
    }
    label716:
    a((String)localObject5, (String)localObject6, paramInputStream);
  }
  
  public void a(String paramString1, String paramString2)
  {
    a.setProperty(paramString1, paramString2);
  }
  
  public void a(String paramString1, String paramString2, String paramString3)
  {
    Properties localProperties2 = (Properties)b.get(paramString1);
    Properties localProperties1 = localProperties2;
    if (localProperties2 == null)
    {
      localProperties1 = new Properties();
      b.put(paramString1, localProperties1);
    }
    localProperties1.setProperty(paramString2, paramString3);
  }
  
  public String b(String paramString1, String paramString2)
  {
    paramString1 = (Properties)b.get(paramString1);
    if (paramString1 == null) {
      return null;
    }
    return paramString1.getProperty(paramString2);
  }
  
  static enum a {}
}

/* Location:
 * Qualified Name:     ans
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */