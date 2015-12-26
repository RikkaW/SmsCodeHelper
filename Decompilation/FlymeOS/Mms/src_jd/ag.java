import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ag
  implements d<m>
{
  private d a = null;
  private Map<String, Method> b = new HashMap();
  
  public ag(d<?> paramd)
  {
    a = paramd;
  }
  
  private Method a(ai paramai, Object paramObject)
  {
    if (paramObject == null) {
      return null;
    }
    String str = paramObject.getClass().getName() + paramai.a();
    int j;
    int i;
    if (!b.containsKey(str))
    {
      paramObject = paramObject.getClass().getMethods();
      j = paramObject.length;
      i = 0;
    }
    for (;;)
    {
      if (i >= j) {}
      for (;;)
      {
        return (Method)b.get(str);
        Object localObject = paramObject[i];
        k localk = (k)((Method)localObject).getAnnotation(k.class);
        if ((localk == null) || (!localk.a().equals(paramai.a()))) {
          break;
        }
        b.put(str, localObject);
      }
      i += 1;
    }
  }
  
  public m a(ai paramai, m paramm1, m paramm2)
  {
    Object localObject = paramm1;
    if (paramm1.p()) {
      localObject = ((n)paramm1.b()).a(this);
    }
    localObject = ((m)localObject).b();
    paramm1 = paramm2;
    if (paramm2 != null)
    {
      paramm1 = paramm2;
      if (paramm2.p()) {
        paramm1 = ((n)paramm2.b()).a(this);
      }
    }
    if (paramm1 != null) {}
    for (paramm2 = paramm1.b();; paramm2 = null)
    {
      if ((a != null) && (a.b(paramai, localObject, paramm2))) {
        paramai = a.a(paramai, localObject, paramm2);
      }
      for (;;)
      {
        return new m(l.a.j, paramai);
        if (!(localObject instanceof c)) {
          break label585;
        }
        paramm1 = (c)localObject;
        if (paramai.equals(ai.q))
        {
          paramai = new Boolean(paramm1.l(paramm2));
        }
        else if (paramai.equals(ai.i))
        {
          paramai = paramm1.n(paramm2);
        }
        else if (paramai.equals(ai.b))
        {
          paramai = paramm1.a();
        }
        else if (paramai.equals(ai.j))
        {
          paramai = paramm1.m(paramm2);
        }
        else if (paramai.equals(ai.e))
        {
          paramai = paramm1.b(paramm2);
        }
        else if (paramai.equals(ai.o))
        {
          paramai = new Boolean(paramm1.j(paramm2));
        }
        else if (paramai.equals(ai.n))
        {
          paramai = new Boolean(paramm1.i(paramm2));
        }
        else if (paramai.equals(ai.m))
        {
          paramai = new Boolean(paramm1.h(paramm2));
        }
        else if (paramai.equals(ai.l))
        {
          paramai = new Boolean(paramm1.g(paramm2));
        }
        else if (paramai.equals(ai.k))
        {
          paramai = new Boolean(paramm1.f(null));
        }
        else if (paramai.equals(ai.h))
        {
          paramai = paramm1.e(paramm2);
        }
        else if (paramai.equals(ai.f))
        {
          paramai = paramm1.c(paramm2);
        }
        else if (paramai.equals(ai.d))
        {
          paramai = paramm1.a(paramm2);
        }
        else if (paramai.equals(ai.p))
        {
          paramai = new Boolean(paramm1.k(paramm2));
        }
        else if (paramai.equals(ai.c))
        {
          paramai = paramm1.b();
        }
        else if (paramai.equals(ai.a))
        {
          paramai = paramm1.a();
        }
        else if (paramai.equals(ai.r))
        {
          paramai = paramm1.m(paramm2);
        }
        else
        {
          if (!paramai.equals(ai.g)) {
            break;
          }
          paramai = paramm1.d(paramm2);
        }
      }
      throw new i("不支持:" + paramai.a() + "操作, 在Evaluable对象。");
      label585:
      Method localMethod = a(paramai, localObject);
      if (localMethod != null)
      {
        if (paramm1 != null)
        {
          paramai = new Object[1];
          paramai[0] = paramm2;
        }
        for (;;)
        {
          try
          {
            paramai = localMethod.invoke(localObject, paramai);
          }
          catch (Exception paramai)
          {
            throw new i(paramai.toString(), paramai.getCause());
          }
          paramai = new Object[0];
        }
      }
      throw new i("不支持:" + paramai.a() + "操作, 在对象:" + localObject.getClass().getName());
    }
  }
  
  public boolean b(ai paramai, m paramm1, m paramm2)
  {
    Object localObject = paramm1;
    if (paramm1.p()) {
      localObject = ((n)paramm1.b()).a(this);
    }
    localObject = ((m)localObject).b();
    paramm1 = paramm2;
    if (paramm2 != null)
    {
      paramm1 = paramm2;
      if (paramm2.p()) {
        paramm1 = ((n)paramm2.b()).a(this);
      }
    }
    if (paramm1 != null) {}
    for (paramm1 = paramm1.b();; paramm1 = null)
    {
      if ((a != null) && (a.b(paramai, localObject, paramm1))) {
        return true;
      }
      if ((localObject instanceof c)) {
        return true;
      }
      return a(paramai, localObject) != null;
    }
  }
}

/* Location:
 * Qualified Name:     ag
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */