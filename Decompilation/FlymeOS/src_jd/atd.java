public class atd
{
  private static volatile atd a = null;
  
  public static atd a()
  {
    if (a == null) {}
    try
    {
      if (a == null) {
        a = new atd();
      }
      return a;
    }
    finally {}
  }
  
  public String a(String paramString)
  {
    String str = paramString;
    if (atf.b(paramString)) {
      str = ate.b(ate.a(paramString.trim()).toLowerCase());
    }
    return str;
  }
}

/* Location:
 * Qualified Name:     atd
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */