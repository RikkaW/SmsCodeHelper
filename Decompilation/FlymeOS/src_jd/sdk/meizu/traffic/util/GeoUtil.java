package sdk.meizu.traffic.util;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import com.meizu.i18n.phonenumbers.NumberParseException;
import com.meizu.i18n.phonenumbers.PhoneNumberUtil;
import com.meizu.i18n.phonenumbers.geocoding.PhoneNumberOfflineGeocoder;

public class GeoUtil
{
  public static String getCurrentCountryIso(Context paramContext)
  {
    return CountryDetector.getInstance(paramContext).getCurrentCountryIso();
  }
  
  public static String getGeocodedLocationFor(Context paramContext, String paramString)
  {
    PhoneNumberOfflineGeocoder localPhoneNumberOfflineGeocoder = PhoneNumberOfflineGeocoder.getInstance();
    PhoneNumberUtil localPhoneNumberUtil = PhoneNumberUtil.getInstance();
    try
    {
      paramContext = localPhoneNumberOfflineGeocoder.getDescriptionForNumber(localPhoneNumberUtil.parse(paramString, getCurrentCountryIso(paramContext)), getResourcesgetConfigurationlocale);
      return paramContext;
    }
    catch (NumberParseException paramContext) {}
    return null;
  }
}

/* Location:
 * Qualified Name:     sdk.meizu.traffic.util.GeoUtil
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */