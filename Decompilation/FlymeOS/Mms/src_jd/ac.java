public class ac
  implements u
{
  private String b(r paramr)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int j;
    for (int i = 1;; i = j)
    {
      j = paramr.read();
      if (j == -1) {
        return localStringBuffer.toString();
      }
      char c = (char)j;
      if (("+-*/%^<>=&|!?:#$(),[]'\" \r\n\t".indexOf(c) >= 0) && (i == 0))
      {
        paramr.reset();
        return localStringBuffer.toString();
      }
      if (!Character.isJavaIdentifierPart(c)) {
        throw new s("名称不能为非法字符：" + c);
      }
      j = i;
      if (i != 0)
      {
        if (!Character.isJavaIdentifierStart(c)) {
          throw new s("名称开头不能为字符：" + c);
        }
        j = 0;
      }
      localStringBuffer.append(c);
      paramr.mark(0);
    }
  }
  
  public p a(r paramr)
  {
    int i = paramr.a();
    paramr = b(paramr);
    if (("true".equals(paramr)) || ("false".equals(paramr))) {
      return new p(paramr, i, p.a.c);
    }
    if ("null".equals(paramr)) {
      return new p(paramr, i, p.a.a);
    }
    return new p(paramr, i, p.a.i);
  }
}

/* Location:
 * Qualified Name:     ac
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */