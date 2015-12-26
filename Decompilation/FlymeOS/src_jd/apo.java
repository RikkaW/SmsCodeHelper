import android.text.TextUtils;
import com.ted.android.contacts.common.DataBus;

public class apo
{
  private static final String[] a = { "+", "0", "400", "800" };
  
  public static String a(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return "";
    }
    return d(anu.b(paramString));
  }
  
  private static boolean b(String paramString)
  {
    String[] arrayOfString = a;
    int j = arrayOfString.length;
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        return false;
      }
      if (paramString.startsWith(arrayOfString[i])) {
        return true;
      }
      i += 1;
    }
  }
  
  private static boolean c(String paramString)
  {
    int i = paramString.length();
    if ((i <= 8) && (i >= 5) && (paramString.startsWith("96"))) {}
    while ((i <= 8) && (i >= 7) && (!paramString.startsWith("1")) && (!paramString.startsWith("9"))) {
      return true;
    }
    return false;
  }
  
  private static String d(String paramString)
  {
    String str = paramString;
    if (!b(paramString))
    {
      str = paramString;
      if (c(paramString))
      {
        str = paramString;
        if (!TextUtils.isEmpty(DataBus.USER_CITY_CODE)) {
          str = DataBus.USER_CITY_CODE + paramString;
        }
      }
    }
    return str;
  }
}

/* Location:
 * Qualified Name:     apo
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */