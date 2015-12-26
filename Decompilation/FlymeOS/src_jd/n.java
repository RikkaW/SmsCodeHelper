public class n
{
  private h a;
  private m[] b;
  private l.a c;
  
  public n(h paramh, m[] paramArrayOfm)
  {
    this(paramh, paramArrayOfm, true);
  }
  
  public n(h paramh, m[] paramArrayOfm, boolean paramBoolean)
  {
    a = paramh;
    b = paramArrayOfm;
    if (h.a.d == paramh.a()) {
      c = ad.a(paramh.e(), paramh.g(), paramArrayOfm).a();
    }
    while (h.a.c != paramh.a()) {
      return;
    }
    if (paramBoolean)
    {
      c = paramh.d().a(paramh.g(), paramArrayOfm).a();
      return;
    }
    c = l.a.j;
  }
  
  public l.a a()
  {
    return c;
  }
  
  public m a(d<m> paramd)
  {
    if (h.a.c == a.a())
    {
      ai localai = a.d();
      m localm2 = b[0];
      m localm1 = null;
      if (b.length > 1)
      {
        localm2 = b[1];
        localm1 = b[0];
      }
      if ((paramd != null) && (paramd.b(localai, localm2, localm1))) {
        return (m)paramd.a(localai, localm2, localm1);
      }
      return localai.a(b);
    }
    if (h.a.d == a.a()) {
      return ad.a(a.e(), a.g(), b);
    }
    throw new i("不支持的Reference执行异常");
  }
  
  public m b()
  {
    return a(null);
  }
}

/* Location:
 * Qualified Name:     n
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */