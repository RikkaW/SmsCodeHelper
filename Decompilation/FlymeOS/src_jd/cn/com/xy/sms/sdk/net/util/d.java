package cn.com.xy.sms.sdk.net.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public final class d
{
  private static final String a = "-noWait";
  private static final String b = "-wait=";
  private static final String c = "-ids=";
  private static final String d = "-domain=";
  private static final String e = "-sql=";
  private int f;
  private boolean g = false;
  private int h = -1;
  private String i;
  private String j;
  private String[] k;
  private Map<String, String> l;
  private String m;
  
  public d(int paramInt, String paramString)
  {
    f = paramInt;
    m = paramString;
    d();
  }
  
  private void a(int paramInt)
  {
    f = paramInt;
  }
  
  private void a(String paramString)
  {
    i = paramString;
  }
  
  private void a(Map<String, String> paramMap)
  {
    l = paramMap;
  }
  
  private void a(boolean paramBoolean)
  {
    g = paramBoolean;
  }
  
  private void a(String[] paramArrayOfString)
  {
    k = paramArrayOfString;
  }
  
  private void b(int paramInt)
  {
    h = paramInt;
  }
  
  private void b(String paramString)
  {
    j = paramString;
  }
  
  private void c(String paramString)
  {
    m = paramString;
  }
  
  private void d()
  {
    String[] arrayOfString = m.split("\\s(?=-[a-zA-Z]+)");
    int i1 = arrayOfString.length;
    int n = 0;
    if (n >= i1) {
      return;
    }
    Object localObject = arrayOfString[n];
    if (((String)localObject).equals("-noWait")) {
      g = true;
    }
    for (;;)
    {
      n += 1;
      break;
      if (((String)localObject).startsWith("-wait="))
      {
        h = Integer.valueOf(((String)localObject).substring(6)).intValue();
      }
      else if (((String)localObject).startsWith("-ids="))
      {
        k = ((String)localObject).substring(5).split(",");
        g = true;
      }
      else if (((String)localObject).startsWith("-domain="))
      {
        i = ((String)localObject).substring(8);
      }
      else if (((String)localObject).startsWith("-sql="))
      {
        j = ((String)localObject).substring(5);
      }
      else
      {
        if (l == null) {
          l = new HashMap();
        }
        localObject = ((String)localObject).split("=");
        if (localObject.length < 2) {
          l.put(localObject[0], "true");
        } else {
          l.put(localObject[0], localObject[1]);
        }
      }
    }
  }
  
  private boolean e()
  {
    return g;
  }
  
  private int f()
  {
    return h;
  }
  
  private String g()
  {
    return i;
  }
  
  private Map<String, String> h()
  {
    return l;
  }
  
  private String i()
  {
    return m;
  }
  
  public final int a()
  {
    return f;
  }
  
  public final String b()
  {
    return j;
  }
  
  public final String[] c()
  {
    return k;
  }
  
  public final String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer("cmd : ").append(m).append("\n targetTo interface:");
    if (f == 0)
    {
      localObject = "all";
      localStringBuffer = localStringBuffer.append(localObject).append("\n execute right now? ").append(g).append("\n just for this ids:");
      if (k != null) {
        break label171;
      }
      localObject = "all";
      label65:
      localStringBuffer = localStringBuffer.append((String)localObject).append("\n reset Wait Date Period to ");
      if (h != -1) {
        break label182;
      }
      localObject = "no change";
      label87:
      localStringBuffer = localStringBuffer.append(localObject).append("\n reset Domain Url to ");
      if (i != null) {
        break label193;
      }
      localObject = "no change";
      label108:
      localStringBuffer = localStringBuffer.append((String)localObject).append("\n sql:");
      if (j != null) {
        break label201;
      }
    }
    label171:
    label182:
    label193:
    label201:
    for (Object localObject = "nosql to execute";; localObject = j)
    {
      return (String)localObject + new StringBuilder("\n other cmd:").append(l).toString();
      localObject = Integer.valueOf(f);
      break;
      localObject = Arrays.toString(k);
      break label65;
      localObject = Integer.valueOf(h);
      break label87;
      localObject = i;
      break label108;
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.net.util.d
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */