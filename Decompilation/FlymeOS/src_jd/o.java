import java.util.Date;
import java.util.List;

public class o
  extends l
{
  String c;
  
  public o(String paramString)
  {
    this(paramString, null, null);
  }
  
  public o(String paramString, l.a parama, Object paramObject)
  {
    super(parama, paramObject);
    if (paramString == null) {
      throw new IllegalArgumentException("非法参数：变量名为空");
    }
    c = paramString;
  }
  
  public static o a(String paramString, Object paramObject)
  {
    if ((paramObject instanceof Boolean)) {
      return new o(paramString, l.a.c, paramObject);
    }
    if ((paramObject instanceof Date)) {
      return new o(paramString, l.a.h, paramObject);
    }
    if ((paramObject instanceof Double)) {
      return new o(paramString, l.a.g, paramObject);
    }
    if ((paramObject instanceof Float)) {
      return new o(paramString, l.a.f, paramObject);
    }
    if ((paramObject instanceof Integer)) {
      return new o(paramString, l.a.d, paramObject);
    }
    if ((paramObject instanceof Long)) {
      return new o(paramString, l.a.e, paramObject);
    }
    if ((paramObject instanceof String)) {
      return new o(paramString, l.a.b, paramObject);
    }
    if ((paramObject instanceof List)) {
      return new o(paramString, l.a.i, paramObject);
    }
    if ((paramObject instanceof Object)) {
      return new o(paramString, l.a.j, paramObject);
    }
    if (paramObject == null) {
      return new o(paramString, l.a.a, paramObject);
    }
    throw new IllegalArgumentException("非法参数：无法识别的变量类型");
  }
  
  public void a(l.a parama)
  {
    a = parama;
    m();
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {}
    do
    {
      return true;
      if ((!(paramObject instanceof o)) || (!super.equals(paramObject))) {
        break;
      }
      paramObject = (o)paramObject;
    } while ((c != null) && (c.equals(c)));
    return false;
    return false;
  }
  
  public String q()
  {
    return c;
  }
}

/* Location:
 * Qualified Name:     o
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */