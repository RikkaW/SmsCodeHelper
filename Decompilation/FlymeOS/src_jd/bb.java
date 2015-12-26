public class bb
  implements ah
{
  public static final ai a = ai.v;
  
  public m a(int paramInt, l[] paramArrayOfl)
  {
    if (paramArrayOfl == null) {
      throw new IllegalArgumentException("运算操作符参数为空");
    }
    if (paramArrayOfl.length != 3) {
      throw new i("操作符\"" + a.a() + "\"参数个数不匹配", a.a(), paramInt);
    }
    l locall1 = paramArrayOfl[2];
    l locall2 = paramArrayOfl[1];
    paramArrayOfl = paramArrayOfl[0];
    if ((locall1 == null) || (locall2 == null) || (paramArrayOfl == null)) {
      throw new NullPointerException("操作符\"" + a.a() + "\"参数为空");
    }
    if (l.a.c != locall1.a()) {
      throw new i("操作符\"" + a.a() + "\"参数类型错误", a.a(), paramInt);
    }
    paramArrayOfl = locall2.a(paramArrayOfl);
    if (paramArrayOfl != null) {
      return new m(paramArrayOfl, null);
    }
    throw new i("操作符\"" + a.a() + "\"二，三参数类型不一致", a.a(), paramInt);
  }
  
  public m a(m[] paramArrayOfm)
  {
    if ((paramArrayOfm == null) || (paramArrayOfm.length != 3)) {
      throw new IllegalArgumentException("操作符\"" + a.a() + "操作缺少参数");
    }
    Object localObject = paramArrayOfm[2];
    if ((localObject == null) || (((m)localObject).b() == null)) {
      throw new NullPointerException("操作符\"" + a.a() + "\"第一参数为空");
    }
    m localm1 = paramArrayOfm[1];
    if ((localm1 == null) || (localm1.b() == null)) {
      throw new NullPointerException("操作符\"" + a.a() + "\"第二参数为空");
    }
    m localm2 = paramArrayOfm[0];
    if ((localm2 == null) || (localm2.b() == null)) {
      throw new NullPointerException("操作符\"" + a.a() + "\"第三参数为空");
    }
    paramArrayOfm = (m[])localObject;
    if (((m)localObject).p()) {
      paramArrayOfm = ((n)((m)localObject).b()).b();
    }
    if (l.a.c == paramArrayOfm.a())
    {
      localObject = localm1.a(localm2);
      if (paramArrayOfm.e().booleanValue()) {
        if (!localm1.p()) {
          break label347;
        }
      }
    }
    label347:
    for (paramArrayOfm = ((n)localm1.b()).b();; paramArrayOfm = localm1)
    {
      return new m((l.a)localObject, paramArrayOfm.b());
      if (localm2.p()) {}
      for (paramArrayOfm = ((n)localm2.b()).b();; paramArrayOfm = localm2)
      {
        return new m((l.a)localObject, paramArrayOfm.b());
        throw new IllegalArgumentException("操作符\"" + a.a() + "\"第一参数类型错误");
      }
    }
  }
}

/* Location:
 * Qualified Name:     bb
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */