import android.text.TextUtils;
import android.text.util.Rfc822Token;
import android.text.util.Rfc822Tokenizer;
import android.util.Patterns;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class nc
{
  public static final Pattern a = Pattern.compile("\\s*(\"[^\"]*\"|[^<>\"]+)\\s*<([^<>]+)>\\s*");
  private static final Pattern b = Pattern.compile("(\\+[0-9]+[\\- \\.]*)?(\\([0-9]+\\)[\\- \\.]*)?([0-9][0-9\\- \\.]*)");
  
  public static String a(String paramString)
  {
    Matcher localMatcher = Pattern.compile("([一-龥]+)").matcher(paramString);
    String str = null;
    if (localMatcher.find()) {
      str = localMatcher.group();
    }
    if (!TextUtils.isEmpty(str)) {
      return str;
    }
    return paramString;
  }
  
  public static boolean a(String paramString, int paramInt)
  {
    boolean bool1 = false;
    if ((paramInt & 0x1) == 1) {
      bool1 = c(paramString);
    }
    boolean bool2 = bool1;
    if (!bool1)
    {
      bool2 = bool1;
      if ((paramInt & 0x2) == 2) {
        bool2 = d(paramString);
      }
    }
    return bool2;
  }
  
  public static String b(String paramString, int paramInt)
  {
    int i = 0;
    if (TextUtils.isEmpty(paramString)) {
      paramString = "";
    }
    Rfc822Token[] arrayOfRfc822Token;
    do
    {
      String str;
      do
      {
        return paramString;
        str = paramString.trim();
        if (((paramInt & 0x1) == 1) && (c(str)))
        {
          int j = str.length();
          paramString = new StringBuilder(j);
          paramInt = i;
          if (paramInt < j)
          {
            char c = str.charAt(paramInt);
            if (c == ' ') {}
            for (;;)
            {
              paramInt += 1;
              break;
              if ((c != '.') && (c != '-') && (c != '(') && (c != ')')) {
                paramString.append(c);
              }
            }
          }
          return paramString.toString();
        }
        paramString = str;
      } while (!d(str));
      arrayOfRfc822Token = Rfc822Tokenizer.tokenize(str);
      paramString = str;
    } while (arrayOfRfc822Token.length <= 0);
    return arrayOfRfc822Token[0].getAddress();
  }
  
  public static boolean b(String paramString)
  {
    if ((TextUtils.isEmpty(paramString)) || (paramString.length() == 1)) {
      return false;
    }
    return paramString.startsWith("@");
  }
  
  public static boolean c(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return false;
    }
    return b.matcher(paramString).matches();
  }
  
  public static boolean d(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return false;
    }
    paramString = e(paramString);
    return Patterns.EMAIL_ADDRESS.matcher(paramString).matches();
  }
  
  public static String e(String paramString)
  {
    Matcher localMatcher = a.matcher(paramString);
    if (localMatcher.matches()) {
      paramString = localMatcher.group(2);
    }
    return paramString;
  }
  
  public static boolean f(String paramString)
  {
    return ("com.android.exchange".equals(paramString)) || ("com.google.android.exchange".equals(paramString));
  }
}

/* Location:
 * Qualified Name:     nc
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */