import java.util.HashSet;
import java.util.Set;

public class y
  implements u
{
  private static final Set<String> a = new HashSet();
  
  static
  {
    a.add("+");
    a.add("-");
    a.add(">");
    a.add("<");
    a.add(">=");
    a.add("<=");
    a.add("==");
    a.add("!=");
    a.add("*");
    a.add("/");
    a.add("%");
    a.add("&");
    a.add("|");
    a.add("&&");
    a.add("||");
    a.add("!");
    a.add("~");
    a.add("#");
    a.add("?:");
    a.add("?");
    a.add(":");
  }
  
  public static boolean a(String paramString)
  {
    return a.contains(paramString);
  }
  
  public static boolean b(r paramr)
  {
    paramr.mark(0);
    try
    {
      StringBuffer localStringBuffer = new StringBuffer();
      int i = paramr.read();
      if (i == -1) {
        return false;
      }
      char c = (char)i;
      localStringBuffer.append(c);
      boolean bool = a(localStringBuffer.toString());
      if (bool) {
        return true;
      }
      do
      {
        c = (char)i;
        localStringBuffer.append(c);
        if (a(localStringBuffer.toString())) {
          break;
        }
        i = "+-*/%^<>=&|!?:#$(),[]'\" \r\n\t".indexOf(c);
        if (i >= 0) {
          return false;
        }
        i = paramr.read();
      } while (i != -1);
      return false;
    }
    finally
    {
      paramr.reset();
    }
  }
  
  public p a(r paramr)
  {
    int i = paramr.a();
    StringBuffer localStringBuffer = new StringBuffer();
    int j = paramr.read();
    if (j == -1) {
      throw new s("表达式已结束");
    }
    localStringBuffer.append((char)j);
    if (a(localStringBuffer.toString()))
    {
      if (localStringBuffer.length() == 1)
      {
        paramr.mark(0);
        j = paramr.read();
        if ((j != -1) && (a(localStringBuffer.toString() + (char)j))) {
          return new p(localStringBuffer.toString() + (char)j, i, p.a.j);
        }
        paramr.reset();
      }
      return new p(localStringBuffer.toString(), i, p.a.j);
    }
    do
    {
      char c = (char)j;
      localStringBuffer.append(c);
      if (a(localStringBuffer.toString())) {
        return new p(localStringBuffer.toString(), i, p.a.j);
      }
      if ("+-*/%^<>=&|!?:#$(),[]'\" \r\n\t".indexOf(c) >= 0) {
        throw new s("不是有效的运算符：" + localStringBuffer.toString());
      }
      j = paramr.read();
    } while (j != -1);
    throw new s("不是有效的运算符结束");
  }
}

/* Location:
 * Qualified Name:     y
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */