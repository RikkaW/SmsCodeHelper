import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public enum ai
{
  private static final Set<String> w;
  private static final HashMap<ai, ah> x;
  private int A;
  private String y;
  private int z;
  
  static
  {
    B = new ai[] { a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v };
    w = new HashSet();
    w.add(a.a());
    w.add(c.a());
    w.add(d.a());
    w.add(e.a());
    w.add(f.a());
    w.add(g.a());
    w.add(h.a());
    w.add(k.a());
    w.add(l.a());
    w.add(m.a());
    w.add(n.a());
    w.add(o.a());
    w.add(p.a());
    w.add(q.a());
    w.add(r.a());
    w.add(s.a());
    w.add(v.a());
    w.add(t.a());
    w.add(u.a());
    x = new HashMap();
    x.put(a, new ax());
    x.put(c, new aw());
    x.put(d, new au());
    x.put(e, new am());
    x.put(f, new at());
    x.put(g, new az());
    x.put(h, new as());
    x.put(k, new ar());
    x.put(l, new aq());
    x.put(m, new ap());
    x.put(n, new ao());
    x.put(o, new an());
    x.put(p, new av());
    x.put(q, new aj());
    x.put(r, new ay());
    x.put(s, new ak());
    x.put(v, new bb());
    x.put(t, new ba());
    x.put(u, new al());
  }
  
  private ai(String paramString1, int paramInt2, int paramInt3)
  {
    y = paramString1;
    z = paramInt2;
    A = paramInt3;
  }
  
  public String a()
  {
    return y;
  }
  
  public m a(int paramInt, l[] paramArrayOfl)
  {
    ah localah = (ah)x.get(this);
    if (localah == null) {
      throw new IllegalStateException("系统内部错误：找不到操作符对应的执行定义");
    }
    return localah.a(paramInt, paramArrayOfl);
  }
  
  public m a(m[] paramArrayOfm)
  {
    ah localah = (ah)x.get(this);
    if (localah == null) {
      throw new IllegalStateException("系统内部错误：找不到操作符对应的执行定义");
    }
    return localah.a(paramArrayOfm);
  }
  
  public int b()
  {
    return z;
  }
  
  public int c()
  {
    return A;
  }
}

/* Location:
 * Qualified Name:     ai
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */