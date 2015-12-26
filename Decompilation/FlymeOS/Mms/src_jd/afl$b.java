import android.telephony.PhoneNumberUtils;
import android.text.SpannableStringBuilder;

public class afl$b
{
  public static String a(String paramString, int paramInt)
  {
    paramString = new SpannableStringBuilder(paramString);
    PhoneNumberUtils.formatNumber(paramString, paramInt);
    return paramString.toString();
  }
}

/* Location:
 * Qualified Name:     afl.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */