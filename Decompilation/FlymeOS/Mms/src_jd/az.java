public class az
  implements ah
{
  public static final ai a = ai.g;
  
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
    if ((l.a.i == locall.a()) || (l.a.i == paramArrayOfl.a())) {
      throw new i("操作符\"" + a.a() + "\"参数类型错误", a.a(), paramInt);
    }
    if ((l.a.b == locall.a()) || (l.a.b == paramArrayOfl.a()) || (l.a.a == locall.a()) || (l.a.a == paramArrayOfl.a()) || (l.a.c == locall.a()) || (l.a.c == paramArrayOfl.a()) || (l.a.h == locall.a()) || (l.a.h == paramArrayOfl.a())) {
      return new m(l.a.b, null);
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
    m localm = paramArrayOfm[1];
    Object localObject = paramArrayOfm[0];
    if ((localm == null) || (localObject == null)) {
      throw new NullPointerException("操作符\"" + a.a() + "\"参数为空");
    }
    if (localm.p()) {}
    for (paramArrayOfm = ((n)localm.b()).b();; paramArrayOfm = localm)
    {
      if (((m)localObject).p()) {
        localObject = ((n)((m)localObject).b()).b();
      }
      for (;;)
      {
        if ((l.a.i == paramArrayOfm.a()) || (l.a.i == ((m)localObject).a())) {
          throw new IllegalArgumentException("操作符\"" + a.a() + "\"参数类型错误");
        }
        if ((l.a.b == paramArrayOfm.a()) || (l.a.b == ((m)localObject).a()) || (l.a.a == paramArrayOfm.a()) || (l.a.a == ((m)localObject).a()) || (l.a.c == paramArrayOfm.a()) || (l.a.c == ((m)localObject).a()) || (l.a.h == paramArrayOfm.a()) || (l.a.h == ((m)localObject).a())) {
          if (paramArrayOfm.d() == null) {
            break label606;
          }
        }
        label606:
        for (paramArrayOfm = paramArrayOfm.d();; paramArrayOfm = "")
        {
          if (((m)localObject).d() != null) {}
          for (localObject = ((m)localObject).d();; localObject = "")
          {
            paramArrayOfm = paramArrayOfm + (String)localObject;
            return new m(l.a.b, paramArrayOfm);
            if ((paramArrayOfm.b() == null) || (((m)localObject).b() == null)) {
              throw new NullPointerException("操作符\"" + a.a() + "\"参数为空");
            }
            if ((l.a.g == paramArrayOfm.a()) || (l.a.g == ((m)localObject).a()))
            {
              double d1 = paramArrayOfm.i().doubleValue();
              double d2 = ((m)localObject).i().doubleValue();
              return new m(l.a.g, Double.valueOf(d2 + d1));
            }
            if ((l.a.f == paramArrayOfm.a()) || (l.a.f == ((m)localObject).a()))
            {
              float f1 = paramArrayOfm.h().floatValue();
              float f2 = ((m)localObject).h().floatValue();
              return new m(l.a.f, Float.valueOf(f2 + f1));
            }
            if ((l.a.e == paramArrayOfm.a()) || (l.a.e == ((m)localObject).a()))
            {
              long l1 = paramArrayOfm.g().longValue();
              long l2 = ((m)localObject).g().longValue();
              return new m(l.a.e, Long.valueOf(l2 + l1));
            }
            int i = paramArrayOfm.f().intValue();
            int j = ((m)localObject).f().intValue();
            return new m(l.a.d, Integer.valueOf(j + i));
          }
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     az
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */