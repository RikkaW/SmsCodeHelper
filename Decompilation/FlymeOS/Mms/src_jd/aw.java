public class aw
  implements ah
{
  public static final ai a = ai.c;
  
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
    if (l.a.g == paramArrayOfl.a()) {
      return new m(l.a.g, Double.valueOf(0.0D));
    }
    if (l.a.f == paramArrayOfl.a()) {
      return new m(l.a.f, Float.valueOf(0.0F));
    }
    if (l.a.e == paramArrayOfl.a()) {
      return new m(l.a.e, Long.valueOf(0L));
    }
    if (l.a.d == paramArrayOfl.a()) {
      return new m(l.a.d, Integer.valueOf(0));
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
    if (l.a.g == paramArrayOfm.a())
    {
      double d = paramArrayOfm.i().doubleValue();
      return new m(l.a.g, Double.valueOf(0.0D - d));
    }
    if (l.a.f == paramArrayOfm.a())
    {
      float f = paramArrayOfm.h().floatValue();
      return new m(l.a.f, Float.valueOf(0.0F - f));
    }
    if (l.a.e == paramArrayOfm.a())
    {
      long l = paramArrayOfm.g().longValue();
      return new m(l.a.e, Long.valueOf(0L - l));
    }
    if (l.a.d == paramArrayOfm.a())
    {
      int i = paramArrayOfm.f().intValue();
      return new m(l.a.d, Integer.valueOf(0 - i));
    }
    throw new IllegalArgumentException("操作符\"" + a.a() + "\"参数类型错误");
  }
}

/* Location:
 * Qualified Name:     aw
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */