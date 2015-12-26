public class v
{
  public static u a(r paramr)
  {
    paramr.mark(0);
    int i = paramr.read();
    paramr.reset();
    if (i != -1)
    {
      i = (char)i;
      if (i == 34) {}
      try
      {
        return (u)aa.class.newInstance();
      }
      catch (Exception paramr)
      {
        throw new s(paramr);
      }
      if (i == 91) {
        return (u)t.class.newInstance();
      }
      if (i == 36) {
        return (u)w.class.newInstance();
      }
      if ("(),".indexOf(i) >= 0) {
        return (u)z.class.newInstance();
      }
      if ("01234567890.".indexOf(i) >= 0) {
        return (u)x.class.newInstance();
      }
      if (y.b(paramr)) {
        return (u)y.class.newInstance();
      }
      paramr = (u)ac.class.newInstance();
      return paramr;
    }
    throw new s("流已结束");
  }
}

/* Location:
 * Qualified Name:     v
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */