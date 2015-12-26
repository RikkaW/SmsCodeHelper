import android.util.Log;
import java.util.Set;

class afe
  extends afd
{
  private String g;
  private boolean h = false;
  
  public afe() {}
  
  public afe(int paramInt)
  {
    super(paramInt);
  }
  
  public static String b(char paramChar)
  {
    if ((paramChar == 'n') || (paramChar == 'N')) {
      return "\n";
    }
    return String.valueOf(paramChar);
  }
  
  private void b(afj paramafj, String paramString1, String paramString2)
  {
    int k = paramString2.length();
    int j = 0;
    int i = 0;
    Object localObject1 = null;
    if (j < k)
    {
      char c = paramString2.charAt(j);
      if (c == '"') {
        if (i != 0)
        {
          paramafj.a(paramString1, e(((StringBuilder)localObject1).toString()));
          i = 0;
          localObject1 = null;
        }
      }
      for (;;)
      {
        j += 1;
        break;
        if (localObject1 != null)
        {
          if (((StringBuilder)localObject1).length() <= 0) {
            break label98;
          }
          Log.w("vCard", "Unexpected Dquote inside property.");
        }
        for (;;)
        {
          i = 1;
          break;
          label98:
          paramafj.a(paramString1, e(((StringBuilder)localObject1).toString()));
        }
        if ((c == ',') && (i == 0))
        {
          if (localObject1 == null)
          {
            Log.w("vCard", "Comma is used before actual string comes. (" + paramString2 + ")");
          }
          else
          {
            paramafj.a(paramString1, e(((StringBuilder)localObject1).toString()));
            localObject1 = null;
          }
        }
        else
        {
          Object localObject2 = localObject1;
          if (localObject1 == null) {
            localObject2 = new StringBuilder();
          }
          ((StringBuilder)localObject2).append(c);
          localObject1 = localObject2;
        }
      }
    }
    if (i != 0) {
      Log.d("vCard", "Dangling Dquote.");
    }
    if (localObject1 != null)
    {
      if (((StringBuilder)localObject1).length() == 0) {
        Log.w("vCard", "Unintended behavior. We must not see empty StringBuilder at the end of parameter value parsing.");
      }
    }
    else {
      return;
    }
    paramafj.a(paramString1, e(((StringBuilder)localObject1).toString()));
  }
  
  public static String f(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int j = paramString.length();
    int i = 0;
    if (i < j)
    {
      char c = paramString.charAt(i);
      if ((c == '\\') && (i < j - 1))
      {
        i += 1;
        c = paramString.charAt(i);
        if ((c == 'n') || (c == 'N')) {
          localStringBuilder.append("\n");
        }
      }
      for (;;)
      {
        i += 1;
        break;
        localStringBuilder.append(c);
        continue;
        localStringBuilder.append(c);
      }
    }
    return localStringBuilder.toString();
  }
  
  protected String a()
  {
    if (g != null)
    {
      String str = g;
      g = null;
      return str;
    }
    return d.readLine();
  }
  
  protected void a(afj paramafj)
  {
    if (!h)
    {
      Log.w("vCard", "AGENT in vCard 3.0 is not supported yet. Ignore it");
      h = true;
    }
  }
  
  protected void a(afj paramafj, String paramString)
  {
    try
    {
      super.a(paramafj, paramString);
      return;
    }
    catch (afn localafn)
    {
      String[] arrayOfString = paramString.split("=", 2);
      if (arrayOfString.length == 2)
      {
        a(paramafj, arrayOfString[0], arrayOfString[1]);
        return;
      }
      throw new afn("Unknown params value: " + paramString);
    }
  }
  
  protected void a(afj paramafj, String paramString1, String paramString2)
  {
    b(paramafj, paramString1, paramString2);
  }
  
  protected boolean a(boolean paramBoolean)
  {
    return super.a(paramBoolean);
  }
  
  protected void b(afj paramafj, String paramString)
  {
    c(paramafj, paramString);
  }
  
  protected String c()
  {
    Object localObject3 = null;
    Object localObject1 = null;
    String str;
    for (;;)
    {
      str = d.readLine();
      if (str == null) {
        break label110;
      }
      if (str.length() != 0)
      {
        if ((str.charAt(0) != ' ') && (str.charAt(0) != '\t')) {
          break;
        }
        Object localObject2 = localObject1;
        if (localObject1 == null) {
          localObject2 = new StringBuilder();
        }
        if (g != null)
        {
          ((StringBuilder)localObject2).append(g);
          g = null;
        }
        ((StringBuilder)localObject2).append(str.substring(1));
        localObject1 = localObject2;
      }
    }
    if ((localObject1 != null) || (g != null))
    {
      label110:
      if (localObject1 == null) {
        break label148;
      }
      localObject1 = ((StringBuilder)localObject1).toString();
    }
    for (;;)
    {
      g = str;
      if (localObject1 != null) {
        return localObject1;
      }
      throw new afn("Reached end of buffer.");
      g = str;
      break;
      label148:
      localObject1 = localObject3;
      if (g != null) {
        localObject1 = g;
      }
    }
    return (String)localObject1;
  }
  
  protected String c(String paramString)
  {
    return paramString;
  }
  
  protected void c(afj paramafj, String paramString)
  {
    b(paramafj, "TYPE", paramString);
  }
  
  protected String d(String paramString)
  {
    return f(paramString);
  }
  
  protected String e(String paramString)
  {
    return afl.a(paramString, "ISO-8859-1", "UTF-8");
  }
  
  protected int f()
  {
    return 1;
  }
  
  protected String g()
  {
    return "3.0";
  }
  
  protected Set<String> h()
  {
    return afh.a;
  }
}

/* Location:
 * Qualified Name:     afe
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */