import android.text.TextUtils;

public class aey$i
  implements aey.e
{
  public String a;
  private String b;
  private String c;
  private String d;
  private String e;
  private String f;
  private String g;
  private String h;
  private String i;
  private String j;
  private String k;
  
  public final aey.g a()
  {
    return aey.g.a;
  }
  
  public boolean b()
  {
    return (TextUtils.isEmpty(b)) && (TextUtils.isEmpty(c)) && (TextUtils.isEmpty(d)) && (TextUtils.isEmpty(e)) && (TextUtils.isEmpty(f));
  }
  
  public boolean c()
  {
    return (TextUtils.isEmpty(h)) && (TextUtils.isEmpty(i)) && (TextUtils.isEmpty(j));
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof i)) {
        return false;
      }
      paramObject = (i)paramObject;
    } while ((TextUtils.equals(b, b)) && (TextUtils.equals(d, d)) && (TextUtils.equals(c, c)) && (TextUtils.equals(e, e)) && (TextUtils.equals(f, f)) && (TextUtils.equals(g, g)) && (TextUtils.equals(h, h)) && (TextUtils.equals(j, j)) && (TextUtils.equals(i, i)) && (TextUtils.equals(k, k)));
    return false;
  }
  
  public int hashCode()
  {
    String[] arrayOfString = new String[10];
    arrayOfString[0] = b;
    arrayOfString[1] = d;
    arrayOfString[2] = c;
    arrayOfString[3] = e;
    arrayOfString[4] = f;
    arrayOfString[5] = g;
    arrayOfString[6] = h;
    arrayOfString[7] = j;
    arrayOfString[8] = i;
    arrayOfString[9] = k;
    int i2 = arrayOfString.length;
    int n = 0;
    int m = 0;
    if (n < i2)
    {
      String str = arrayOfString[n];
      if (str != null) {}
      for (int i1 = str.hashCode();; i1 = 0)
      {
        n += 1;
        m = m * 31 + i1;
        break;
      }
    }
    return m;
  }
  
  public String toString()
  {
    return String.format("family: %s, given: %s, middle: %s, prefix: %s, suffix: %s", new Object[] { b, c, d, e, f });
  }
}

/* Location:
 * Qualified Name:     aey.i
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */