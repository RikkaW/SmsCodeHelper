import java.util.ArrayList;

public class m
  extends l
{
  public m(l.a parama, Object paramObject)
  {
    super(parama, paramObject);
    if (parama == null) {
      throw new IllegalArgumentException("非法参数：数据类型为空");
    }
    if ((l.a.i == parama) && (b == null)) {
      b = new ArrayList(0);
    }
  }
  
  public m(n paramn)
  {
    super(null, paramn);
    a(true);
  }
}

/* Location:
 * Qualified Name:     m
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */