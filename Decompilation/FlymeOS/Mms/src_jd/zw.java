import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.text.TextUtils;
import com.android.mms.MmsApp;
import com.meizu.i18n.phonenumbers.NumberParseException;
import com.meizu.i18n.phonenumbers.PhoneNumberUtil;
import com.meizu.i18n.phonenumbers.Phonenumber.PhoneNumber;
import com.meizu.i18n.phonenumbers.geocoding.PhoneNumberOfflineGeocoder;
import java.util.Locale;

public class zw
{
  private static final String[] a = { "12593", "17951", "17911", "17910", "17909", "10131", "10193", "96531", "193", "12589", "12598", "12520" };
  
  public static String a(Context paramContext, String paramString)
  {
    PhoneNumberOfflineGeocoder localPhoneNumberOfflineGeocoder = PhoneNumberOfflineGeocoder.getInstance();
    Object localObject = PhoneNumberUtil.getInstance();
    String str = MmsApp.c().h();
    try
    {
      localObject = ((PhoneNumberUtil)localObject).parse(paramString, str);
      paramContext = getResourcesgetConfigurationlocale;
      if (Locale.CHINA.getCountry().equals(str))
      {
        if ((paramString.startsWith("0")) || (paramString.startsWith("+")) || (paramString.startsWith("1")))
        {
          if ((!a(paramString)) && (paramString.startsWith("10"))) {
            return null;
          }
          return localPhoneNumberOfflineGeocoder.getDescriptionForValidNumber((Phonenumber.PhoneNumber)localObject, paramContext, Locale.CHINA.getCountry(), true);
        }
      }
      else
      {
        paramContext = localPhoneNumberOfflineGeocoder.getDescriptionForNumber((Phonenumber.PhoneNumber)localObject, paramContext);
        return paramContext;
      }
    }
    catch (NumberParseException paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static boolean a(String paramString)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    int i;
    if (!TextUtils.isEmpty(paramString)) {
      i = 0;
    }
    for (;;)
    {
      bool1 = bool2;
      if (i < a.length)
      {
        if (paramString.startsWith(a[i])) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i += 1;
    }
  }
}

/* Location:
 * Qualified Name:     zw
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */