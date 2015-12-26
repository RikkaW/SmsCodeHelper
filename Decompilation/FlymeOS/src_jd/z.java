public class z
  implements u
{
  public p a(r paramr)
  {
    int i = paramr.a();
    int j = paramr.read();
    char c = (char)j;
    if ((j == -1) || ("(),".indexOf(c) == -1)) {
      throw new s("不是有效的分割字符");
    }
    return new p(Character.toString(c), i, p.a.l);
  }
}

/* Location:
 * Qualified Name:     z
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */