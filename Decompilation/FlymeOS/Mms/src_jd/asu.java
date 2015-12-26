public class asu
  implements Comparable
{
  public boolean a;
  public boolean b = false;
  private String c;
  private Integer d;
  private String e = "";
  private String f = "0";
  private String g = "";
  
  public String a()
  {
    return c;
  }
  
  public void a(Integer paramInteger)
  {
    d = paramInteger;
  }
  
  public void a(String paramString)
  {
    c = paramString;
  }
  
  public void a(boolean paramBoolean)
  {
    a = paramBoolean;
  }
  
  public String b()
  {
    return g;
  }
  
  public void b(String paramString)
  {
    g = paramString;
  }
  
  public void c(String paramString)
  {
    e = paramString;
  }
  
  public boolean c()
  {
    return a;
  }
  
  public int compareTo(Object paramObject)
  {
    paramObject = (asu)paramObject;
    return a().compareTo(((asu)paramObject).a());
  }
  
  public Integer d()
  {
    return d;
  }
  
  public void d(String paramString)
  {
    f = paramString;
  }
  
  public String e()
  {
    return e;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    boolean bool1;
    if (this == paramObject) {
      bool1 = true;
    }
    do
    {
      do
      {
        do
        {
          return bool1;
          bool1 = bool2;
        } while (paramObject == null);
        bool1 = bool2;
      } while (getClass() != paramObject.getClass());
      paramObject = (asu)paramObject;
      bool1 = bool2;
    } while (a != a);
    return c.equals(c);
  }
  
  public String f()
  {
    return f;
  }
  
  public int hashCode()
  {
    int j = c.hashCode();
    if (a) {}
    for (int i = 1;; i = 0) {
      return i + j * 31;
    }
  }
  
  public String toString()
  {
    return "Reply{content='" + c + '\'' + ", priority=" + d + ", desc='" + e + '\'' + ", phone='" + f + '\'' + ", underline='" + g + '\'' + ", editable=" + a + ", isMulti=" + b + '}';
  }
}

/* Location:
 * Qualified Name:     asu
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */