import java.util.Set;

class aff
  extends afe
{
  public static String c(char paramChar)
  {
    if ((paramChar == 'n') || (paramChar == 'N')) {
      return "\n";
    }
    return String.valueOf(paramChar);
  }
  
  public static String g(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int j = paramString.length();
    int i = 0;
    if (i < j)
    {
      char c = paramString.charAt(i);
      if ((c == '\\') && (i < j - 1))
      {
        i += 1;
        c = paramString.charAt(i);
        if ((c == 'n') || (c == 'N')) {
          localStringBuilder.append("\n");
        }
      }
      for (;;)
      {
        i += 1;
        break;
        localStringBuilder.append(c);
        continue;
        localStringBuilder.append(c);
      }
    }
    return localStringBuilder.toString();
  }
  
  protected String d(String paramString)
  {
    return g(paramString);
  }
  
  protected int f()
  {
    return 2;
  }
  
  protected String g()
  {
    return "4.0";
  }
  
  protected Set<String> h()
  {
    return afi.a;
  }
}

/* Location:
 * Qualified Name:     aff
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */