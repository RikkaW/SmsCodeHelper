import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ak
  implements ah
{
  public static final ai a = ai.s;
  
  private m a(m paramm1, m paramm2)
  {
    if ((paramm1 == null) || (paramm2 == null)) {
      throw new IllegalArgumentException("操作符\"" + a.a() + "\"参数丢失");
    }
    ArrayList localArrayList = new ArrayList();
    if (l.a.i == paramm1.a())
    {
      if (paramm1.k() != null) {
        localArrayList.addAll(paramm1.k());
      }
      if (l.a.i != paramm2.a()) {
        break label140;
      }
      if (paramm2.k() != null) {
        localArrayList.addAll(paramm2.k());
      }
    }
    for (;;)
    {
      return new m(l.a.i, localArrayList);
      try
      {
        localArrayList.add(paramm1.o());
      }
      catch (ParseException paramm1)
      {
        paramm1.printStackTrace();
      }
      break;
      try
      {
        label140:
        localArrayList.add(paramm2.o());
      }
      catch (ParseException paramm1)
      {
        paramm1.printStackTrace();
      }
    }
  }
  
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
    return new m(l.a.i, null);
  }
  
  public m a(m[] paramArrayOfm)
  {
    if ((paramArrayOfm == null) || (paramArrayOfm.length != 2)) {
      throw new IllegalArgumentException("操作符\"" + a.a() + "参数个数不匹配");
    }
    m localm2 = paramArrayOfm[1];
    m localm1 = paramArrayOfm[0];
    if ((localm2 == null) || (localm1 == null)) {
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
        return a(paramArrayOfm, localm1);
      }
    }
  }
}

/* Location:
 * Qualified Name:     ak
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */