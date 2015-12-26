import android.text.TextUtils;
import java.util.Iterator;
import java.util.List;

public class aey$o
  implements aey.e
{
  private final String a;
  private final String b;
  private final String c;
  private final String d;
  private final String e;
  private final String f;
  private final String g;
  private final int h;
  private final String i;
  private boolean j;
  private int k;
  
  public aey$o(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, int paramInt1, String paramString8, boolean paramBoolean, int paramInt2)
  {
    h = paramInt1;
    a = paramString1;
    b = paramString2;
    c = paramString3;
    d = paramString4;
    e = paramString5;
    f = paramString6;
    g = paramString7;
    i = paramString8;
    j = paramBoolean;
    k = paramInt2;
  }
  
  public static o a(List<String> paramList, int paramInt1, String paramString, boolean paramBoolean, int paramInt2)
  {
    String[] arrayOfString = new String[7];
    int n = paramList.size();
    if (n > 7) {
      n = 7;
    }
    for (;;)
    {
      paramList = paramList.iterator();
      int m = 0;
      if (paramList.hasNext())
      {
        arrayOfString[m] = ((String)paramList.next());
        m += 1;
        if (m < n) {}
      }
      for (;;)
      {
        if (m < 7)
        {
          arrayOfString[m] = null;
          m += 1;
          continue;
          break;
        }
        return new o(arrayOfString[0], arrayOfString[1], arrayOfString[2], arrayOfString[3], arrayOfString[4], arrayOfString[5], arrayOfString[6], paramInt1, paramString, paramBoolean, paramInt2);
      }
    }
  }
  
  public final aey.g a()
  {
    return aey.g.d;
  }
  
  public String a(int paramInt)
  {
    int m = 6;
    int n = 1;
    int i1 = 1;
    StringBuilder localStringBuilder = new StringBuilder();
    String[] arrayOfString = new String[7];
    arrayOfString[0] = a;
    arrayOfString[1] = b;
    arrayOfString[2] = c;
    arrayOfString[3] = d;
    arrayOfString[4] = e;
    arrayOfString[5] = f;
    arrayOfString[6] = g;
    String str;
    if (aex.e(paramInt))
    {
      paramInt = i1;
      if (m >= 0)
      {
        str = arrayOfString[m];
        n = paramInt;
        if (!TextUtils.isEmpty(str))
        {
          if (paramInt != 0) {
            break label142;
          }
          localStringBuilder.append(' ');
        }
        for (;;)
        {
          localStringBuilder.append(str);
          n = paramInt;
          m -= 1;
          paramInt = n;
          break;
          label142:
          paramInt = 0;
        }
      }
    }
    else
    {
      m = 0;
      paramInt = n;
      if (m < 7)
      {
        str = arrayOfString[m];
        n = paramInt;
        if (!TextUtils.isEmpty(str))
        {
          if (paramInt != 0) {
            break label204;
          }
          localStringBuilder.append(' ');
        }
        for (;;)
        {
          localStringBuilder.append(str);
          n = paramInt;
          m += 1;
          paramInt = n;
          break;
          label204:
          paramInt = 0;
        }
      }
    }
    return localStringBuilder.toString().trim();
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof o)) {
        return false;
      }
      paramObject = (o)paramObject;
    } while ((h == h) && ((h != 0) || (TextUtils.equals(i, i))) && (j == j) && (TextUtils.equals(a, a)) && (TextUtils.equals(b, b)) && (TextUtils.equals(c, c)) && (TextUtils.equals(d, d)) && (TextUtils.equals(e, e)) && (TextUtils.equals(f, f)) && (TextUtils.equals(g, g)));
    return false;
  }
  
  public int hashCode()
  {
    int i1 = h;
    int m;
    int n;
    label31:
    label114:
    String str;
    if (i != null)
    {
      m = i.hashCode();
      if (!j) {
        break label156;
      }
      n = 1231;
      m = n + (m + i1 * 31) * 31;
      String[] arrayOfString = new String[7];
      arrayOfString[0] = a;
      arrayOfString[1] = b;
      arrayOfString[2] = c;
      arrayOfString[3] = d;
      arrayOfString[4] = e;
      arrayOfString[5] = f;
      arrayOfString[6] = g;
      int i2 = arrayOfString.length;
      n = 0;
      if (n >= i2) {
        return m;
      }
      str = arrayOfString[n];
      if (str == null) {
        break label163;
      }
    }
    label156:
    label163:
    for (i1 = str.hashCode();; i1 = 0)
    {
      n += 1;
      m = m * 31 + i1;
      break label114;
      m = 0;
      break;
      n = 1237;
      break label31;
    }
    return m;
  }
  
  public String toString()
  {
    return String.format("type: %d, label: %s, isPrimary: %s, pobox: %s, extendedAddress: %s, street: %s, localty: %s, region: %s, postalCode %s, country: %s", new Object[] { Integer.valueOf(h), i, Boolean.valueOf(j), a, b, c, d, e, f, g });
  }
}

/* Location:
 * Qualified Name:     aey.o
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */