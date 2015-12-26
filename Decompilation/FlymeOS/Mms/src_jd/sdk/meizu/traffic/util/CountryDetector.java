package sdk.meizu.traffic.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Geocoder;
import android.location.LocationManager;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import java.util.Locale;

public class CountryDetector
{
  private static final long DISTANCE_BETWEEN_UPDATES_METERS = 5000L;
  public static final String KEY_PREFERENCE_CURRENT_COUNTRY = "preference_current_country";
  public static final String KEY_PREFERENCE_TIME_UPDATED = "preference_time_updated";
  private static final String TAG = "CountryDetector";
  private static final long TIME_BETWEEN_UPDATES_MS = 43200000L;
  private static CountryDetector sInstance;
  private final String DEFAULT_COUNTRY_ISO = "US";
  private final Context mContext;
  private final LocaleProvider mLocaleProvider;
  private final LocationManager mLocationManager;
  private final TelephonyManager mTelephonyManager;
  
  private CountryDetector(Context paramContext)
  {
    this(paramContext, (TelephonyManager)paramContext.getSystemService("phone"), (LocationManager)paramContext.getSystemService("location"), new LocaleProvider());
  }
  
  private CountryDetector(Context paramContext, TelephonyManager paramTelephonyManager, LocationManager paramLocationManager, LocaleProvider paramLocaleProvider)
  {
    mTelephonyManager = paramTelephonyManager;
    mLocationManager = paramLocationManager;
    mLocaleProvider = paramLocaleProvider;
    mContext = paramContext;
  }
  
  public static CountryDetector getInstance(Context paramContext)
  {
    try
    {
      if (sInstance == null) {
        sInstance = new CountryDetector(paramContext.getApplicationContext());
      }
      paramContext = sInstance;
      return paramContext;
    }
    finally {}
  }
  
  private String getLocaleBasedCountryIso()
  {
    Locale localLocale = mLocaleProvider.getDefaultLocale();
    if (localLocale != null) {
      return localLocale.getCountry();
    }
    return null;
  }
  
  private String getLocationBasedCountryIso()
  {
    if (!Geocoder.isPresent()) {
      return null;
    }
    return PreferenceManager.getDefaultSharedPreferences(mContext).getString("preference_current_country", null);
  }
  
  private String getNetworkBasedCountryIso()
  {
    return mTelephonyManager.getNetworkCountryIso();
  }
  
  private String getSimBasedCountryIso()
  {
    return mTelephonyManager.getSimCountryIso();
  }
  
  private boolean isNetworkCountryCodeAvailable()
  {
    return mTelephonyManager.getPhoneType() == 1;
  }
  
  public String getCurrentCountryIso()
  {
    Object localObject2 = null;
    if (isNetworkCountryCodeAvailable()) {
      localObject2 = getNetworkBasedCountryIso();
    }
    Object localObject1 = localObject2;
    if (TextUtils.isEmpty((CharSequence)localObject2)) {
      localObject1 = getLocationBasedCountryIso();
    }
    localObject2 = localObject1;
    if (TextUtils.isEmpty((CharSequence)localObject1)) {
      localObject2 = getSimBasedCountryIso();
    }
    localObject1 = localObject2;
    if (TextUtils.isEmpty((CharSequence)localObject2)) {
      localObject1 = getLocaleBasedCountryIso();
    }
    localObject2 = localObject1;
    if (TextUtils.isEmpty((CharSequence)localObject1)) {
      localObject2 = "US";
    }
    return ((String)localObject2).toUpperCase(Locale.US);
  }
  
  public static class LocaleProvider
  {
    public Locale getDefaultLocale()
    {
      return Locale.getDefault();
    }
  }
}

/* Location:
 * Qualified Name:     sdk.meizu.traffic.util.CountryDetector
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */