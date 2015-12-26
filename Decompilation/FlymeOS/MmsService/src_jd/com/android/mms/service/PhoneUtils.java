package com.android.mms.service;

import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.android.i18n.phonenumbers.NumberParseException;
import com.android.i18n.phonenumbers.PhoneNumberUtil;
import com.android.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat;
import com.android.i18n.phonenumbers.Phonenumber.PhoneNumber;
import java.util.Locale;

public class PhoneUtils
{
  public static String getNationalNumber(TelephonyManager paramTelephonyManager, int paramInt, String paramString)
  {
    Object localObject = getSimOrDefaultLocaleCountry(paramTelephonyManager, paramInt);
    paramTelephonyManager = PhoneNumberUtil.getInstance();
    localObject = getParsedNumber(paramTelephonyManager, paramString, (String)localObject);
    if (localObject == null) {
      return paramString;
    }
    return paramTelephonyManager.format((Phonenumber.PhoneNumber)localObject, PhoneNumberUtil.PhoneNumberFormat.NATIONAL).replaceAll("\\D", "");
  }
  
  private static Phonenumber.PhoneNumber getParsedNumber(PhoneNumberUtil paramPhoneNumberUtil, String paramString1, String paramString2)
  {
    try
    {
      Phonenumber.PhoneNumber localPhoneNumber = paramPhoneNumberUtil.parse(paramString1, paramString2);
      if (paramPhoneNumberUtil.isValidNumber(localPhoneNumber)) {
        return localPhoneNumber;
      }
      Log.e("MmsService", "getParsedNumber: not a valid phone number " + paramString1 + " for country " + paramString2);
      return null;
    }
    catch (NumberParseException paramPhoneNumberUtil)
    {
      Log.e("MmsService", "getParsedNumber: Not able to parse phone number " + paramString1);
    }
    return null;
  }
  
  private static String getSimCountry(TelephonyManager paramTelephonyManager, int paramInt)
  {
    paramTelephonyManager = paramTelephonyManager.getSimCountryIso(paramInt);
    if (TextUtils.isEmpty(paramTelephonyManager)) {
      return null;
    }
    return paramTelephonyManager.toUpperCase();
  }
  
  private static String getSimOrDefaultLocaleCountry(TelephonyManager paramTelephonyManager, int paramInt)
  {
    String str = getSimCountry(paramTelephonyManager, paramInt);
    paramTelephonyManager = str;
    if (TextUtils.isEmpty(str)) {
      paramTelephonyManager = Locale.getDefault().getCountry();
    }
    return paramTelephonyManager;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.service.PhoneUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */