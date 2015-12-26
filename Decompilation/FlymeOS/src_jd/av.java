public class av
  implements ah
{
  public static final ai a = ai.p;
  
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
    if ((l.a.a == locall.a()) || (l.a.a == paramArrayOfl.a())) {
      return new m(l.a.c, Boolean.FALSE);
    }
    if ((l.a.c == locall.a()) && (l.a.c == paramArrayOfl.a())) {
      return new m(l.a.c, Boolean.FALSE);
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
    if ((l.a.j == locall.a()) && (l.a.j == paramArrayOfl.a())) {
      return new m(l.a.c, Boolean.FALSE);
    }
    throw new i("操作符\"" + a.a() + "\"参数类型错误", a.a(), paramInt);
  }
  
  public m a(m[] paramArrayOfm)
  {
    boolean bool2 = false;
    boolean bool3 = false;
    boolean bool1 = false;
    if ((paramArrayOfm == null) || (paramArrayOfm.length != 2)) {
      throw new IllegalArgumentException("操作符\"" + a.a() + "参数个数不匹配");
    }
    Object localObject = paramArrayOfm[1];
    paramArrayOfm = paramArrayOfm[0];
    if ((localObject == null) || (paramArrayOfm == null)) {
      throw new NullPointerException("操作符\"" + a.a() + "\"参数为空");
    }
    if (((m)localObject).p()) {
      localObject = ((n)((m)localObject).b()).b();
    }
    for (;;)
    {
      if (paramArrayOfm.p()) {
        paramArrayOfm = ((n)paramArrayOfm.b()).b();
      }
      for (;;)
      {
        if ((l.a.i == ((m)localObject).a()) || (l.a.i == paramArrayOfm.a())) {
          throw new IllegalArgumentException("操作符\"" + a.a() + "\"参数类型错误");
        }
        if (l.a.a == ((m)localObject).a())
        {
          if (paramArrayOfm.b() != null) {
            return new m(l.a.c, Boolean.TRUE);
          }
          return new m(l.a.c, Boolean.FALSE);
        }
        if (l.a.a == paramArrayOfm.a())
        {
          if (((m)localObject).b() != null) {
            return new m(l.a.c, Boolean.TRUE);
          }
          return new m(l.a.c, Boolean.FALSE);
        }
        l.a locala;
        if ((l.a.c == ((m)localObject).a()) && (l.a.c == paramArrayOfm.a()))
        {
          localObject = ((m)localObject).e();
          paramArrayOfm = paramArrayOfm.e();
          if (localObject != null)
          {
            locala = l.a.c;
            if (((Boolean)localObject).equals(paramArrayOfm)) {}
            for (bool1 = false;; bool1 = true) {
              return new m(locala, Boolean.valueOf(bool1));
            }
          }
          if (paramArrayOfm == null) {
            return new m(l.a.c, Boolean.FALSE);
          }
          return new m(l.a.c, Boolean.TRUE);
        }
        if ((l.a.h == ((m)localObject).a()) && (l.a.h == paramArrayOfm.a()))
        {
          localObject = ((m)localObject).c();
          paramArrayOfm = paramArrayOfm.c();
          if (localObject != null)
          {
            locala = l.a.c;
            if (((String)localObject).equals(paramArrayOfm)) {}
            for (;;)
            {
              return new m(locala, Boolean.valueOf(bool1));
              bool1 = true;
            }
          }
          if (paramArrayOfm == null) {
            return new m(l.a.c, Boolean.FALSE);
          }
          return new m(l.a.c, Boolean.TRUE);
        }
        if ((l.a.b == ((m)localObject).a()) && (l.a.b == paramArrayOfm.a()))
        {
          localObject = ((m)localObject).d();
          paramArrayOfm = paramArrayOfm.d();
          if (localObject != null)
          {
            locala = l.a.c;
            if (((String)localObject).equals(paramArrayOfm)) {}
            for (bool1 = bool2;; bool1 = true) {
              return new m(locala, Boolean.valueOf(bool1));
            }
          }
          if (paramArrayOfm == null) {
            return new m(l.a.c, Boolean.FALSE);
          }
          return new m(l.a.c, Boolean.TRUE);
        }
        if (((l.a.g == ((m)localObject).a()) || (l.a.f == ((m)localObject).a()) || (l.a.e == ((m)localObject).a()) || (l.a.d == ((m)localObject).a())) && ((l.a.g == paramArrayOfm.a()) || (l.a.f == paramArrayOfm.a()) || (l.a.e == paramArrayOfm.a()) || (l.a.d == paramArrayOfm.a())))
        {
          localObject = ((m)localObject).i();
          paramArrayOfm = paramArrayOfm.i();
          if ((localObject != null) && (paramArrayOfm != null))
          {
            if (Double.compare(((Double)localObject).doubleValue(), paramArrayOfm.doubleValue()) != 0) {
              return new m(l.a.c, Boolean.TRUE);
            }
            return new m(l.a.c, Boolean.FALSE);
          }
          if ((localObject == null) && (paramArrayOfm == null)) {
            return new m(l.a.c, Boolean.FALSE);
          }
          return new m(l.a.c, Boolean.TRUE);
        }
        if ((l.a.j == ((m)localObject).a()) && (l.a.j == paramArrayOfm.a()))
        {
          localObject = ((m)localObject).b();
          paramArrayOfm = paramArrayOfm.b();
          if (localObject != null)
          {
            locala = l.a.c;
            if (localObject.equals(paramArrayOfm)) {}
            for (bool1 = bool3;; bool1 = true) {
              return new m(locala, Boolean.valueOf(bool1));
            }
          }
          if (paramArrayOfm == null) {
            return new m(l.a.c, Boolean.FALSE);
          }
          return new m(l.a.c, Boolean.TRUE);
        }
        throw new IllegalArgumentException("操作符\"" + a.a() + "\"参数类型错误");
      }
    }
  }
}

/* Location:
 * Qualified Name:     av
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */