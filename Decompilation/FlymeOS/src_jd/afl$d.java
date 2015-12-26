public class afl$d
{
  public static boolean a(char paramChar)
  {
    return ((' ' <= paramChar) && (paramChar <= '~')) || (paramChar == '\r') || (paramChar == '\n');
  }
  
  public static boolean a(CharSequence paramCharSequence)
  {
    int j = paramCharSequence.length();
    int i = 0;
    while (i < j)
    {
      if (!a(paramCharSequence.charAt(i))) {
        return false;
      }
      i += 1;
    }
    return true;
  }
}

/* Location:
 * Qualified Name:     afl.d
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */