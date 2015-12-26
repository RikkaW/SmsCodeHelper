import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public abstract class l
{
  l.a a;
  Object b;
  private boolean c;
  
  public l(l.a parama, Object paramObject)
  {
    a = parama;
    b = paramObject;
    m();
  }
  
  private boolean b(l paraml)
  {
    if ((l.a.a == a()) || (l.a.a == paraml.a())) {}
    do
    {
      do
      {
        return true;
      } while (a() == paraml.a());
      if ((l.a.d != a()) && (l.a.e != a()) && (l.a.f != a()) && (l.a.g != a())) {
        return false;
      }
    } while ((l.a.d == paraml.a()) || (l.a.e == paraml.a()) || (l.a.f == paraml.a()) || (l.a.g == paraml.a()));
    return false;
  }
  
  public l.a a()
  {
    if (c) {
      return l().a();
    }
    return a;
  }
  
  public l.a a(l paraml)
  {
    if (b(paraml))
    {
      if (l.a.a == a()) {
        return paraml.a();
      }
      if (l.a.a == paraml.a()) {
        return a();
      }
      if (a() == paraml.a()) {
        return a();
      }
      if ((l.a.g == a()) || (l.a.g == paraml.a())) {
        return l.a.g;
      }
      if ((l.a.f == a()) || (l.a.f == paraml.a())) {
        return l.a.f;
      }
      if ((l.a.e == a()) || (l.a.e == paraml.a())) {
        return l.a.e;
      }
      return l.a.d;
    }
    return null;
  }
  
  void a(boolean paramBoolean)
  {
    c = paramBoolean;
  }
  
  public Object b()
  {
    return b;
  }
  
  public String c()
  {
    if (b == null) {
      return null;
    }
    if (l.a.h == a) {
      return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date)b);
    }
    if (l.a.i == a)
    {
      StringBuffer localStringBuffer = new StringBuffer("[");
      Iterator localIterator = ((List)b).iterator();
      for (;;)
      {
        if (!localIterator.hasNext())
        {
          localStringBuffer.append("]");
          if (localStringBuffer.length() > 2) {
            localStringBuffer.delete(localStringBuffer.length() - 3, localStringBuffer.length() - 1);
          }
          return localStringBuffer.toString();
        }
        Object localObject = localIterator.next();
        if (localObject == null) {
          localStringBuffer.append("null, ");
        } else if ((localObject instanceof Date)) {
          localStringBuffer.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date)localObject)).append(", ");
        } else {
          localStringBuffer.append(localObject.toString()).append(", ");
        }
      }
    }
    return b.toString();
  }
  
  public String d()
  {
    return c();
  }
  
  public Boolean e()
  {
    if (l.a.c != a) {
      throw new UnsupportedOperationException("当前常量类型不支持此操作");
    }
    return (Boolean)b;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {}
    do
    {
      do
      {
        return true;
        if (!(paramObject instanceof l)) {
          break label96;
        }
        paramObject = (l)paramObject;
        if ((!p()) || (!c)) {
          break;
        }
      } while (l() == ((l)paramObject).l());
      return false;
      if (a != a) {
        break;
      }
    } while (((b != null) && (b.equals(b))) || ((b == null) && (b == null)));
    return false;
    return false;
    label96:
    return false;
  }
  
  public Integer f()
  {
    if (l.a.d != a) {
      throw new UnsupportedOperationException("当前常量类型不支持此操作");
    }
    return (Integer)b;
  }
  
  public Long g()
  {
    if ((l.a.d != a) && (l.a.e != a)) {
      throw new UnsupportedOperationException("当前常量类型不支持此操作");
    }
    if (b == null) {
      return null;
    }
    return Long.valueOf(b.toString());
  }
  
  public Float h()
  {
    if ((l.a.d != a) && (l.a.f != a) && (l.a.e != a)) {
      throw new UnsupportedOperationException("当前常量类型不支持此操作");
    }
    if (b == null) {
      return null;
    }
    return Float.valueOf(b.toString());
  }
  
  public Double i()
  {
    if ((l.a.d != a) && (l.a.e != a) && (l.a.f != a) && (l.a.g != a)) {
      throw new UnsupportedOperationException("当前常量类型不支持此操作");
    }
    if (b == null) {
      return null;
    }
    return Double.valueOf(b.toString());
  }
  
  public Date j()
  {
    if (l.a.h != a) {
      throw new UnsupportedOperationException("当前常量类型不支持此操作");
    }
    return (Date)b;
  }
  
  public List<Object> k()
  {
    if (l.a.i != a) {
      throw new UnsupportedOperationException("当前常量类型不支持此操作");
    }
    return (List)b;
  }
  
  public n l()
  {
    if (!c) {
      throw new UnsupportedOperationException("当前常量类型不支持此操作");
    }
    return (n)b;
  }
  
  protected void m()
  {
    if ((a != null) && (b != null))
    {
      if ((l.a.a == a) && (b != null)) {
        throw new IllegalArgumentException("数据类型不匹配; 类型：" + a + ",值不为空");
      }
      if (l.a.c != a) {
        break label119;
      }
    }
    label119:
    do
    {
      try
      {
        e();
        return;
      }
      catch (UnsupportedOperationException localUnsupportedOperationException1)
      {
        throw new IllegalArgumentException("数据类型不匹配; 类型：" + a + ",值:" + b);
      }
      if (l.a.h == a) {
        try
        {
          j();
          return;
        }
        catch (UnsupportedOperationException localUnsupportedOperationException2)
        {
          throw new IllegalArgumentException("数据类型不匹配; 类型：" + a + ",值:" + b);
        }
      }
      if (l.a.g == a) {
        try
        {
          i();
          return;
        }
        catch (UnsupportedOperationException localUnsupportedOperationException3)
        {
          throw new IllegalArgumentException("数据类型不匹配; 类型：" + a + ",值:" + b);
        }
      }
      if (l.a.f == a) {
        try
        {
          h();
          return;
        }
        catch (UnsupportedOperationException localUnsupportedOperationException4)
        {
          throw new IllegalArgumentException("数据类型不匹配; 类型：" + a + ",值:" + b);
        }
      }
      if (l.a.d == a) {
        try
        {
          f();
          return;
        }
        catch (UnsupportedOperationException localUnsupportedOperationException5)
        {
          throw new IllegalArgumentException("数据类型不匹配; 类型：" + a + ",值:" + b);
        }
      }
      if (l.a.e == a) {
        try
        {
          g();
          return;
        }
        catch (UnsupportedOperationException localUnsupportedOperationException6)
        {
          throw new IllegalArgumentException("数据类型不匹配; 类型：" + a + ",值:" + b);
        }
      }
      if (l.a.b == a) {
        try
        {
          d();
          return;
        }
        catch (UnsupportedOperationException localUnsupportedOperationException7)
        {
          throw new IllegalArgumentException("数据类型不匹配; 类型：" + a + ",值:" + b);
        }
      }
      if (l.a.i == a) {
        try
        {
          k();
          return;
        }
        catch (UnsupportedOperationException localUnsupportedOperationException8)
        {
          throw new IllegalArgumentException("数据类型不匹配; 类型：" + a + ",值:" + b);
        }
      }
      if (c) {
        try
        {
          l();
          return;
        }
        catch (UnsupportedOperationException localUnsupportedOperationException9)
        {
          throw new IllegalArgumentException("数据类型不匹配; 类型：" + a + ",值:" + b);
        }
      }
    } while (l.a.j != a);
    try
    {
      b();
      return;
    }
    catch (UnsupportedOperationException localUnsupportedOperationException10)
    {
      throw new IllegalArgumentException("数据类型不匹配; 类型：" + a + ",值:" + b);
    }
  }
  
  public Class<?> n()
  {
    if (l.a.c == a()) {
      return Boolean.TYPE;
    }
    if (l.a.h == a()) {
      return Date.class;
    }
    if (l.a.g == a()) {
      return Double.TYPE;
    }
    if (l.a.f == a()) {
      return Float.TYPE;
    }
    if (l.a.d == a()) {
      return Integer.TYPE;
    }
    if (l.a.e == a()) {
      return Long.TYPE;
    }
    if (l.a.b == a()) {
      return String.class;
    }
    if (l.a.i == a()) {
      return List.class;
    }
    if (l.a.j == a()) {
      return Object.class;
    }
    if (l.a.a == a()) {
      return null;
    }
    throw new RuntimeException("映射Java类型失败：无法识别的数据类型");
  }
  
  public Object o()
  {
    if (b == null) {
      return null;
    }
    if (l.a.c == a()) {
      return e();
    }
    if (l.a.h == a()) {
      return j();
    }
    if (l.a.g == a()) {
      return i();
    }
    if (l.a.f == a()) {
      return h();
    }
    if (l.a.d == a()) {
      return f();
    }
    if (l.a.e == a()) {
      return g();
    }
    if (l.a.b == a()) {
      return d();
    }
    if (l.a.i == a()) {
      return k();
    }
    if (l.a.j == a()) {
      return b();
    }
    throw new RuntimeException("映射Java类型失败：无法识别的数据类型");
  }
  
  public boolean p()
  {
    return c;
  }
  
  public static enum a {}
}

/* Location:
 * Qualified Name:     l
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */