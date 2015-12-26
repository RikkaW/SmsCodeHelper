public class x
  implements u
{
  public static void a(StringBuffer paramStringBuffer)
  {
    if (paramStringBuffer.indexOf(".") != paramStringBuffer.lastIndexOf(".")) {
      throw new s("数字最多只能有一个小数点");
    }
  }
  
  public p a(r paramr)
  {
    int i = paramr.a();
    StringBuffer localStringBuffer = new StringBuffer();
    for (;;)
    {
      int j = paramr.read();
      if (j == -1)
      {
        if (localStringBuffer.indexOf(".") < 0) {
          break;
        }
        a(localStringBuffer);
        return new p(localStringBuffer.toString(), i, p.a.g);
      }
      char c = (char)j;
      if ("01234567890.".indexOf(c) == -1)
      {
        if ("lL".indexOf(c) >= 0)
        {
          if (localStringBuffer.indexOf(".") >= 0) {
            throw new s("long类型不能有小数点");
          }
          return new p(localStringBuffer.toString(), i, p.a.e);
        }
        if ("fF".indexOf(c) >= 0)
        {
          a(localStringBuffer);
          return new p(localStringBuffer.toString(), i, p.a.f);
        }
        if ("dD".indexOf(c) >= 0)
        {
          a(localStringBuffer);
          return new p(localStringBuffer.toString(), i, p.a.g);
        }
        paramr.reset();
        if (localStringBuffer.indexOf(".") >= 0)
        {
          a(localStringBuffer);
          return new p(localStringBuffer.toString(), i, p.a.g);
        }
        return new p(localStringBuffer.toString(), i, p.a.d);
      }
      localStringBuffer.append(c);
      paramr.mark(0);
    }
    return new p(localStringBuffer.toString(), i, p.a.d);
  }
}

/* Location:
 * Qualified Name:     x
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */