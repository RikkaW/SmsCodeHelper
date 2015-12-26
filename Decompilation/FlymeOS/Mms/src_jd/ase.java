import android.text.TextUtils;

public class ase
{
  public static boolean a(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return false;
    }
    int j = paramString.length();
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        return true;
      }
      char c = paramString.charAt(i);
      if ((!Character.isDigit(c)) && (c != '+')) {
        break;
      }
      i += 1;
    }
  }
}

/* Location:
 * Qualified Name:     ase
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */