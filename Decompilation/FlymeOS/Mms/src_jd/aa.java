public class aa
  implements u
{
  private static char a(char paramChar)
  {
    if ((paramChar == '\\') || (paramChar == '"')) {
      return paramChar;
    }
    if (paramChar == 'n') {
      return '\n';
    }
    if (paramChar == 'r') {
      return '\r';
    }
    if (paramChar == 't') {
      return '\t';
    }
    throw new s("字符转义出错");
  }
  
  public p a(r paramr)
  {
    int i = paramr.a();
    StringBuffer localStringBuffer = new StringBuffer();
    int j = paramr.read();
    char c2;
    char c1;
    if ((j == -1) || (j != 34))
    {
      throw new s("不是有效的字符窜开始");
      c2 = (char)j;
      if (c2 != '\\') {
        break label92;
      }
      c1 = a((char)paramr.read());
    }
    label92:
    do
    {
      localStringBuffer.append(c1);
      j = paramr.read();
      if (j != -1) {
        break;
      }
      throw new s("不是有效的字符窜结束");
      c1 = c2;
    } while (c2 != '"');
    return new p(localStringBuffer.toString(), i, p.a.b);
  }
}

/* Location:
 * Qualified Name:     aa
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */