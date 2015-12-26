import java.util.Date;

public class ap
  implements ah
{
  public static final ai a = ai.m;
  
  public m a(int paramInt, l[] paramArrayOfl)
  {
    if (paramArrayOfl == null) {
      throw new IllegalArgumentException("运算操作符参数为空");
    }
    if (paramArrayOfl.length != 2) {
      throw new i("操作符\"" + a.a() + "\"参数个数不匹配", a.a(), paramInt);
    }
    l locall = paramArrayOfl[1];
    paramArrayOfl = paramArrayOfl[0];
    if ((locall == null) || (paramArrayOfl == null)) {
      throw new NullPointerException("操作符\"" + a.a() + "\"参数为空");
    }
    if ((l.a.h == locall.a()) && (l.a.h == paramArrayOfl.a())) {
      return new m(l.a.c, Boolean.FALSE);
    }
    if ((l.a.b == locall.a()) && (l.a.b == paramArrayOfl.a())) {
      return new m(l.a.c, Boolean.FALSE);
    }
    if (((l.a.g == locall.a()) || (l.a.f == locall.a()) || (l.a.e == locall.a()) || (l.a.d == locall.a())) && ((l.a.g == paramArrayOfl.a()) || (l.a.f == paramArrayOfl.a()) || (l.a.e == paramArrayOfl.a()) || (l.a.d == paramArrayOfl.a()))) {
      return new m(l.a.c, Boolean.FALSE);
    }
    throw new i("操作符\"" + a.a() + "\"参数类型错误");
  }
  
  public m a(m[] paramArrayOfm)
  {
    if ((paramArrayOfm == null) || (paramArrayOfm.length != 2)) {
      throw new IllegalArgumentException("操作符\"" + a.a() + "参数个数不匹配");
    }
    m localm2 = paramArrayOfm[1];
    if ((localm2 == null) || (localm2.b() == null)) {
      throw new NullPointerException("操作符\"" + a.a() + "\"参数为空");
    }
    m localm1 = paramArrayOfm[0];
    if ((localm1 == null) || (localm1.b() == null)) {
      throw new NullPointerException("操作符\"" + a.a() + "\"参数为空");
    }
    if (localm2.p()) {}
    for (paramArrayOfm = ((n)localm2.b()).b();; paramArrayOfm = localm2)
    {
      if (localm1.p()) {
        localm1 = ((n)localm1.b()).b();
      }
      for (;;)
      {
        if ((l.a.h == paramArrayOfm.a()) && (l.a.h == localm1.a()))
        {
          if (paramArrayOfm.j().compareTo(localm1.j()) > 0) {
            return new m(l.a.c, Boolean.TRUE);
          }
          return new m(l.a.c, Boolean.FALSE);
        }
        if ((l.a.b == paramArrayOfm.a()) && (l.a.b == localm1.a()))
        {
          if (paramArrayOfm.d().compareTo(localm1.d()) > 0) {
            return new m(l.a.c, Boolean.TRUE);
          }
          return new m(l.a.c, Boolean.FALSE);
        }
        if (((l.a.g == paramArrayOfm.a()) || (l.a.f == paramArrayOfm.a()) || (l.a.e == paramArrayOfm.a()) || (l.a.d == paramArrayOfm.a())) && ((l.a.g == localm1.a()) || (l.a.f == localm1.a()) || (l.a.e == localm1.a()) || (l.a.d == localm1.a())))
        {
          if (Double.compare(paramArrayOfm.i().doubleValue(), localm1.i().doubleValue()) > 0) {
            return new m(l.a.c, Boolean.TRUE);
          }
          return new m(l.a.c, Boolean.FALSE);
        }
        throw new IllegalArgumentException("操作符\"" + a.a() + "\"参数类型错误");
      }
    }
  }
}

/* Location:
 * Qualified Name:     ap
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */