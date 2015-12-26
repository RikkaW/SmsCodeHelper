public class au
  implements ah
{
  public static final ai a = ai.d;
  
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
    if ((l.a.a == locall.a()) || (l.a.a == paramArrayOfl.a()) || (l.a.c == locall.a()) || (l.a.c == paramArrayOfl.a()) || (l.a.h == locall.a()) || (l.a.h == paramArrayOfl.a()) || (l.a.b == locall.a()) || (l.a.b == paramArrayOfl.a()) || (l.a.i == locall.a()) || (l.a.i == paramArrayOfl.a())) {
      throw new i("操作符\"" + a.a() + "\"参数类型错误", a.a(), paramInt);
    }
    if ((l.a.g == locall.a()) || (l.a.g == paramArrayOfl.a())) {
      return new m(l.a.g, Double.valueOf(0.0D));
    }
    if ((l.a.f == locall.a()) || (l.a.f == paramArrayOfl.a())) {
      return new m(l.a.f, Float.valueOf(0.0F));
    }
    if ((l.a.e == locall.a()) || (l.a.e == paramArrayOfl.a())) {
      return new m(l.a.e, Long.valueOf(0L));
    }
    return new m(l.a.d, Integer.valueOf(0));
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
        if ((l.a.a == paramArrayOfm.a()) || (l.a.a == localm1.a()) || (l.a.c == paramArrayOfm.a()) || (l.a.c == localm1.a()) || (l.a.h == paramArrayOfm.a()) || (l.a.h == localm1.a()) || (l.a.b == paramArrayOfm.a()) || (l.a.b == localm1.a()) || (l.a.i == paramArrayOfm.a()) || (l.a.i == localm1.a())) {
          throw new IllegalArgumentException("操作符\"" + a.a() + "\"参数类型错误");
        }
        if ((l.a.g == paramArrayOfm.a()) || (l.a.g == localm1.a()))
        {
          double d1 = paramArrayOfm.i().doubleValue();
          double d2 = localm1.i().doubleValue();
          return new m(l.a.g, Double.valueOf(d2 * d1));
        }
        if ((l.a.f == paramArrayOfm.a()) || (l.a.f == localm1.a()))
        {
          float f1 = paramArrayOfm.h().floatValue();
          float f2 = localm1.h().floatValue();
          return new m(l.a.f, Float.valueOf(f2 * f1));
        }
        if ((l.a.e == paramArrayOfm.a()) || (l.a.e == localm1.a()))
        {
          long l1 = paramArrayOfm.g().longValue();
          long l2 = localm1.g().longValue();
          return new m(l.a.e, Long.valueOf(l2 * l1));
        }
        int i = paramArrayOfm.f().intValue();
        int j = localm1.f().intValue();
        return new m(l.a.d, Integer.valueOf(j * i));
      }
    }
  }
}

/* Location:
 * Qualified Name:     au
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */