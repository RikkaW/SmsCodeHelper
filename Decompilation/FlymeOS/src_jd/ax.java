public class ax
  implements ah
{
  public static final ai a = ai.a;
  
  public m a(int paramInt, l[] paramArrayOfl)
  {
    if (paramArrayOfl == null) {
      throw new IllegalArgumentException("运算操作符参数为空");
    }
    if (paramArrayOfl.length != 1) {
      throw new i("操作符\"" + a.a() + "\"参数个数不匹配", a.a(), paramInt);
    }
    paramArrayOfl = paramArrayOfl[0];
    if (paramArrayOfl == null) {
      throw new NullPointerException("操作符\"" + a.a() + "\"参数为空");
    }
    if (l.a.c == paramArrayOfl.a()) {
      return new m(l.a.c, Boolean.FALSE);
    }
    throw new i("操作符\"" + a.a() + "\"参数类型错误", a.a(), paramInt);
  }
  
  public m a(m[] paramArrayOfm)
  {
    if ((paramArrayOfm == null) || (paramArrayOfm.length != 1)) {
      throw new IllegalArgumentException("操作符\"" + a.a() + "参数个数不匹配");
    }
    m localm = paramArrayOfm[0];
    if ((localm == null) || (localm.b() == null)) {
      throw new NullPointerException("操作符\"" + a.a() + "\"参数为空");
    }
    paramArrayOfm = localm;
    if (localm.p()) {
      paramArrayOfm = ((n)localm.b()).b();
    }
    if (l.a.c == paramArrayOfm.a())
    {
      if (paramArrayOfm.e().booleanValue()) {}
      for (boolean bool = false;; bool = true) {
        return new m(l.a.c, Boolean.valueOf(bool));
      }
    }
    throw new IllegalArgumentException("操作符\"" + a.a() + "\"参数类型错误");
  }
}

/* Location:
 * Qualified Name:     ax
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */