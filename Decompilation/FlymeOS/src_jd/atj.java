import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class atj
{
  private static final atk a = new atk("zht2zhs");
  private static final atk b = new atk("zhs2zht");
  
  public static String a(String paramString)
  {
    if (atm.b(paramString)) {
      return paramString;
    }
    return a(new StringBuffer(paramString));
  }
  
  public static String a(StringBuffer paramStringBuffer)
  {
    Object localObject = null;
    if (atm.a(paramStringBuffer))
    {
      if (paramStringBuffer == null) {
        return null;
      }
      return paramStringBuffer.toString();
    }
    try
    {
      b.a(paramStringBuffer);
      b.b();
      String str = b.d();
      return str;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      if (paramStringBuffer == null) {}
      for (paramStringBuffer = (StringBuffer)localObject;; paramStringBuffer = paramStringBuffer.toString()) {
        return paramStringBuffer;
      }
    }
    finally
    {
      b.c();
    }
  }
  
  public static String b(String paramString)
  {
    if (atm.b(paramString)) {
      return paramString;
    }
    return b(new StringBuffer(paramString));
  }
  
  public static String b(StringBuffer paramStringBuffer)
  {
    Object localObject = null;
    if (atm.a(paramStringBuffer))
    {
      if (paramStringBuffer == null) {
        return null;
      }
      return paramStringBuffer.toString();
    }
    try
    {
      a.a(paramStringBuffer);
      a.b();
      String str = a.d();
      return str;
    }
    catch (Exception localException)
    {
      if (paramStringBuffer == null) {}
      for (paramStringBuffer = (StringBuffer)localObject;; paramStringBuffer = paramStringBuffer.toString()) {
        return paramStringBuffer;
      }
    }
    finally
    {
      a.c();
    }
  }
  
  public static String[] c(String paramString)
  {
    int k = 0;
    paramString = paramString.trim();
    Object localObject1 = new ArrayList(5);
    ((ArrayList)localObject1).add(paramString);
    Object localObject2 = Pattern.compile("(.*?)[一-龥](.*?)").matcher(paramString);
    if (((Matcher)localObject2).find())
    {
      int i = ((Matcher)localObject2).end() - 1;
      String str1 = paramString.substring(0, i);
      String str2 = paramString.substring(i + 1);
      if (((Matcher)localObject2).find())
      {
        str1 = a(b(paramString));
        str2 = b(str1);
        if (!str2.equals(paramString)) {
          ((ArrayList)localObject1).add(str2);
        }
        if (!str1.equals(paramString)) {
          ((ArrayList)localObject1).add(str1);
        }
        paramString = (String[])((ArrayList)localObject1).toArray(new String[0]);
        return paramString;
      }
      localObject1 = paramString.substring(i, i + 1);
      localObject2 = b((String)localObject1);
      paramString = new ArrayList();
      paramString.add(localObject1);
      localObject1 = ((String)localObject2).split(" ");
      int j = localObject1.length;
      i = 0;
      for (;;)
      {
        if (i >= j)
        {
          i = 0;
          if (i < paramString.size()) {
            break label307;
          }
          localObject1 = (String[])paramString.toArray(new String[0]);
          j = localObject1.length;
          i = k;
          for (;;)
          {
            paramString = (String)localObject1;
            if (i >= j) {
              break;
            }
            localObject1[i] = (str1 + localObject1[i] + str2);
            i += 1;
          }
        }
        localObject2 = localObject1[i];
        if (!paramString.contains(localObject2)) {
          paramString.add(localObject2);
        }
        i += 1;
      }
      label307:
      localObject1 = a((String)paramString.get(i)).split(" ");
      int m = localObject1.length;
      j = 0;
      for (;;)
      {
        if (j >= m)
        {
          i += 1;
          break;
        }
        localObject2 = localObject1[j];
        if (!paramString.contains(localObject2)) {
          paramString.add(localObject2);
        }
        j += 1;
      }
    }
    return new String[] { paramString };
  }
}

/* Location:
 * Qualified Name:     atj
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */