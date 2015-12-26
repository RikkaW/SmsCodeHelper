public class aj
  implements ah
{
  public static final ai a = ai.q;
  
  public m a(int paramInt, l[] paramArrayOfl)
  {
    if (paramArrayOfl == null) {
      throw new IllegalArgumentException("运算操作符参数为空");
    }
    if (paramArrayOfl.length != 2) {
      throw new i("操作符\"" + a.a() + "\"参数丢失", a.a(), paramInt);
    }
    l locall = paramArrayOfl[1];
    paramArrayOfl = paramArrayOfl[0];
    if ((locall == null) || (paramArrayOfl == null)) {
      throw new NullPointerException("操作符\"" + a.a() + "\"参数为空");
    }
    if ((l.a.c == locall.a()) && (l.a.c == paramArrayOfl.a())) {
      return new m(l.a.c, Boolean.FALSE);
    }
    throw new i("操作符\"" + a.a() + "\"参数类型错误", a.a(), paramInt);
  }
  
  public m a(m[] paramArrayOfm)
  {
    if ((paramArrayOfm == null) || (paramArrayOfm.length != 2)) {
      throw new IllegalArgumentException("操作符\"" + a.a() + "操作缺少参数");
    }
    Object localObject = paramArrayOfm[1];
    if ((localObject == null) || (((m)localObject).b() == null)) {
      throw new NullPointerException("操作符\"" + a.a() + "\"参数为空");
    }
    m localm = paramArrayOfm[0];
    if ((localm == null) || (localm.b() == null)) {
      throw new NullPointerException("操作符\"" + a.a() + "\"参数为空");
    }
    paramArrayOfm = (m[])localObject;
    if (((m)localObject).p()) {
      paramArrayOfm = ((n)((m)localObject).b()).b();
    }
    if (l.a.c == paramArrayOfm.a())
    {
      localObject = paramArrayOfm;
      if (paramArrayOfm.e().booleanValue()) {
        if (!localm.p()) {
          break label284;
        }
      }
    }
    label284:
    for (paramArrayOfm = ((n)localm.b()).b();; paramArrayOfm = localm)
    {
      if (l.a.c == paramArrayOfm.a())
      {
        localObject = paramArrayOfm;
        return (m)localObject;
      }
      throw new IllegalArgumentException("操作符\"" + a.a() + "\"第二参数类型错误");
      throw new IllegalArgumentException("操作符\"" + a.a() + "\"第一参数类型错误");
    }
  }
}

/* Location:
 * Qualified Name:     aj
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */