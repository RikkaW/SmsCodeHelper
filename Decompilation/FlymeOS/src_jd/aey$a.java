import android.text.TextUtils;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class aey$a
  implements aey.e
{
  private final String a;
  private final List<String> b;
  
  public aey$a(String paramString, List<String> paramList)
  {
    a = paramString;
    b = paramList;
  }
  
  public static a a(List<String> paramList)
  {
    String str = null;
    if (paramList == null) {}
    for (paramList = null;; paramList = null)
    {
      return new a(str, paramList);
      if (paramList.size() >= 2) {
        break;
      }
      str = (String)paramList.get(0);
    }
    if (paramList.size() < 16) {}
    for (int i = paramList.size();; i = 16)
    {
      str = (String)paramList.get(0);
      paramList = paramList.subList(1, i);
      break;
    }
  }
  
  public aey.g a()
  {
    return aey.g.n;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    boolean bool1;
    if (this == paramObject) {
      bool1 = true;
    }
    int j;
    do
    {
      do
      {
        do
        {
          return bool1;
          bool1 = bool2;
        } while (!(paramObject instanceof a));
        paramObject = (a)paramObject;
        bool1 = bool2;
      } while (!TextUtils.equals(a, a));
      if (b == null)
      {
        if (b == null) {}
        for (bool1 = true;; bool1 = false) {
          return bool1;
        }
      }
      j = b.size();
      bool1 = bool2;
    } while (j != b.size());
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        break label151;
      }
      bool1 = bool2;
      if (!TextUtils.equals((CharSequence)b.get(i), (CharSequence)b.get(i))) {
        break;
      }
      i += 1;
    }
    label151:
    return true;
  }
  
  public int hashCode()
  {
    int i;
    label32:
    String str;
    if (a != null)
    {
      i = a.hashCode();
      if (b == null) {
        break label85;
      }
      Iterator localIterator = b.iterator();
      j = i;
      if (!localIterator.hasNext()) {
        return j;
      }
      str = (String)localIterator.next();
      if (str == null) {
        break label80;
      }
    }
    label80:
    for (int j = str.hashCode();; j = 0)
    {
      i = j + i * 31;
      break label32;
      i = 0;
      break;
    }
    label85:
    j = i;
    return j;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("android-custom: " + a + ", data: ");
    if (b == null) {}
    for (String str = "null";; str = Arrays.toString(b.toArray()))
    {
      localStringBuilder.append(str);
      return localStringBuilder.toString();
    }
  }
}

/* Location:
 * Qualified Name:     aey.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */