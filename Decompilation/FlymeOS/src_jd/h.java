public class h
{
  private h.a a;
  private m b;
  private o c;
  private ai d;
  private String e;
  private int f = -1;
  
  public static h a(ai paramai)
  {
    if (paramai == null) {
      throw new IllegalArgumentException("非法参数：操作符为空");
    }
    h localh = new h();
    d = paramai;
    e = paramai.a();
    a = h.a.c;
    return localh;
  }
  
  public static h a(String paramString)
  {
    h localh = new h();
    c = new o(paramString);
    a = h.a.b;
    e = paramString;
    return localh;
  }
  
  public static h a(l.a parama, Object paramObject)
  {
    h localh = new h();
    b = new m(parama, paramObject);
    a = h.a.a;
    if (paramObject != null) {
      e = b.c();
    }
    return localh;
  }
  
  public static h a(m paramm)
  {
    if (paramm == null) {
      throw new IllegalArgumentException("非法参数异常：常量为null");
    }
    h localh = new h();
    b = paramm;
    a = h.a.a;
    if (paramm.b() != null) {
      e = paramm.c();
    }
    return localh;
  }
  
  public static h a(n paramn)
  {
    h localh = new h();
    b = new m(paramn);
    a = h.a.a;
    if (paramn != null) {
      e = b.c();
    }
    return localh;
  }
  
  public static h b(String paramString)
  {
    if (paramString == null) {
      throw new IllegalArgumentException("非法参数：函数名称为空");
    }
    h localh = new h();
    e = paramString;
    a = h.a.d;
    return localh;
  }
  
  public static h c(String paramString)
  {
    if (paramString == null) {
      throw new IllegalArgumentException("非法参数：分隔符为空");
    }
    h localh = new h();
    e = paramString;
    a = h.a.e;
    return localh;
  }
  
  public h.a a()
  {
    return a;
  }
  
  public void a(int paramInt)
  {
    f = paramInt;
  }
  
  public m b()
  {
    return b;
  }
  
  public o c()
  {
    return c;
  }
  
  public ai d()
  {
    return d;
  }
  
  public String e()
  {
    return e;
  }
  
  public String f()
  {
    return e;
  }
  
  public int g()
  {
    return f;
  }
  
  public String toString()
  {
    return e;
  }
  
  public static enum a {}
}

/* Location:
 * Qualified Name:     h
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */