import android.text.TextUtils;

public class anu
{
  public static final String[] a = { "17951", "12593", "17910", "17911", "10193", "10131", "96531", "17900", "17901", "17909", "11808" };
  private static final String[] b = { "+86" };
  
  public static String a(String paramString)
  {
    return a(paramString, b);
  }
  
  private static String a(String paramString, String[] paramArrayOfString)
  {
    int j = paramArrayOfString.length;
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        return paramString;
      }
      String str = paramArrayOfString[i];
      if (paramString.startsWith(str)) {
        return paramString.substring(str.length());
      }
      i += 1;
    }
  }
  
  public static String b(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return paramString;
    }
    return e(a(d(paramString)));
  }
  
  public static boolean c(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    int i;
    int j;
    do
    {
      do
      {
        return false;
      } while ((paramString.length() < 7) || (paramString.length() > 11));
      i = paramString.charAt(0);
      j = paramString.charAt(1);
    } while ((i != 49) || ((j != 51) && (j != 52) && (j != 53) && (j != 56) && (j != 55)));
    return true;
  }
  
  public static String d(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return "";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    if (i >= paramString.length()) {
      return localStringBuilder.toString();
    }
    char c = paramString.charAt(i);
    if ((c >= '0') && (c <= '9')) {
      localStringBuilder.append(c);
    }
    for (;;)
    {
      i += 1;
      break;
      if ((i == 0) && (c == '+')) {
        localStringBuilder.append(c);
      }
    }
  }
  
  private static String e(String paramString)
  {
    return a(paramString, a);
  }
}

/* Location:
 * Qualified Name:     anu
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */