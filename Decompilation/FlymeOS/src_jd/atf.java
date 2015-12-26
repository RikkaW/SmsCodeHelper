public class atf
{
  public static boolean a(String paramString)
  {
    boolean bool2 = false;
    int j;
    boolean bool1;
    if (paramString != null)
    {
      j = paramString.length();
      if (j != 0) {}
    }
    else
    {
      bool1 = true;
      return bool1;
    }
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        break label48;
      }
      bool1 = bool2;
      if (!Character.isWhitespace(paramString.charAt(i))) {
        break;
      }
      i += 1;
    }
    label48:
    return true;
  }
  
  public static boolean b(String paramString)
  {
    return !a(paramString);
  }
}

/* Location:
 * Qualified Name:     atf
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */