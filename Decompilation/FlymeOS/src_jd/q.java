import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class q
{
  private static Map<String, ai> a = new HashMap();
  private Stack<String> b = new Stack();
  
  static
  {
    a.put(ai.a.a(), ai.a);
    a.put(ai.d.a(), ai.d);
    a.put(ai.e.a(), ai.e);
    a.put(ai.f.a(), ai.f);
    a.put(ai.g.a(), ai.g);
    a.put(ai.h.a(), ai.h);
    a.put(ai.k.a(), ai.k);
    a.put(ai.l.a(), ai.l);
    a.put(ai.m.a(), ai.m);
    a.put(ai.n.a(), ai.n);
    a.put(ai.o.a(), ai.o);
    a.put(ai.p.a(), ai.p);
    a.put(ai.q.a(), ai.q);
    a.put(ai.r.a(), ai.r);
    a.put(ai.s.a(), ai.s);
    a.put(ai.v.a(), ai.v);
    a.put(ai.t.a(), ai.t);
    a.put(ai.u.a(), ai.u);
  }
  
  public ai a(String paramString)
  {
    return (ai)a.get(paramString);
  }
  
  public h a(h paramh, p paramp)
  {
    Object localObject = null;
    if (paramp == null) {
      throw new IllegalArgumentException();
    }
    if (p.a.a == paramp.b()) {
      paramh = h.a(l.a.a, null);
    }
    for (;;)
    {
      paramh.a(paramp.c());
      return paramh;
      if (p.a.b == paramp.b())
      {
        paramh = h.a(l.a.b, paramp.a());
      }
      else if (p.a.c == paramp.b())
      {
        paramh = h.a(l.a.c, Boolean.valueOf(paramp.a()));
      }
      else if (p.a.d == paramp.b())
      {
        paramh = h.a(l.a.d, Integer.valueOf(paramp.a()));
      }
      else if (p.a.e == paramp.b())
      {
        paramh = h.a(l.a.e, Long.valueOf(paramp.a()));
      }
      else if (p.a.f == paramp.b())
      {
        paramh = h.a(l.a.f, Float.valueOf(paramp.a()));
      }
      else if (p.a.g == paramp.b())
      {
        paramh = h.a(l.a.g, Double.valueOf(paramp.a()));
      }
      else if (p.a.h == paramp.b())
      {
        paramh = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        paramh = h.a(l.a.h, paramh.parse(paramp.a()));
      }
      else if (p.a.i == paramp.b())
      {
        paramh = h.a(paramp.a());
      }
      else if (p.a.j == paramp.b())
      {
        if ((paramp.a().equals("-")) && ((paramh == null) || (paramh.a() == h.a.c) || ((paramh.a() == h.a.e) && (!")".equals(paramh.f()))))) {
          paramh = h.a(ai.c);
        } else {
          paramh = h.a(a(paramp.a()));
        }
      }
      else if (p.a.k == paramp.b())
      {
        paramh = h.b(paramp.a());
      }
      else
      {
        paramh = (h)localObject;
        if (p.a.l == paramp.b()) {
          paramh = h.c(paramp.a());
        }
      }
    }
  }
  
  public void a(p paramp)
  {
    if (p.a.l == paramp.b())
    {
      if (!paramp.a().equals("(")) {
        break label33;
      }
      b.push("(");
    }
    label33:
    while (!paramp.a().equals(")")) {
      return;
    }
    if ((b.isEmpty()) || (!((String)b.peek()).equals("("))) {
      throw new s("括号匹配出错");
    }
    b.pop();
  }
  
  public List<h> b(String paramString)
  {
    r localr = new r(paramString);
    ArrayList localArrayList = new ArrayList();
    paramString = null;
    for (;;)
    {
      try
      {
        localp = localr.b();
        if (localp != null) {
          continue;
        }
      }
      catch (IOException paramString)
      {
        p localp;
        paramString.printStackTrace();
        continue;
      }
      catch (ParseException paramString)
      {
        paramString.printStackTrace();
        throw new s("表达式词元格式异常");
      }
      if (b.isEmpty()) {
        break;
      }
      throw new s("括号匹配出错");
      paramString = a(paramString, localp);
      a(localp);
      localArrayList.add(paramString);
    }
    return localArrayList;
  }
}

/* Location:
 * Qualified Name:     q
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */