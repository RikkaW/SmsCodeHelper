public class w
  implements u
{
  public p a(r paramr)
  {
    int k = paramr.a();
    StringBuffer localStringBuffer = new StringBuffer();
    int i = paramr.read();
    if ((i == -1) || (i != 36)) {
      throw new s("不是有效的函数开始");
    }
    int j;
    for (i = 1;; i = j)
    {
      j = paramr.read();
      if (j == -1) {
        throw new s("不是有效的函数结束");
      }
      char c = (char)j;
      if (c == '(')
      {
        if (localStringBuffer.length() == 0) {
          throw new s("函数名称不能为空");
        }
        paramr.reset();
        return new p(localStringBuffer.toString(), k, p.a.k);
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
}

/* Location:
 * Qualified Name:     w
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */