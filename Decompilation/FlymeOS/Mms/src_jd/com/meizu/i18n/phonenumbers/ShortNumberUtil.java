package com.meizu.i18n.phonenumbers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShortNumberUtil
{
  private final PhoneNumberUtil phoneUtil;
  
  public ShortNumberUtil()
  {
    phoneUtil = PhoneNumberUtil.getInstance();
  }
  
  ShortNumberUtil(PhoneNumberUtil paramPhoneNumberUtil)
  {
    phoneUtil = paramPhoneNumberUtil;
  }
  
  private boolean matchesEmergencyNumberHelper(String paramString1, String paramString2, boolean paramBoolean)
  {
    paramString1 = PhoneNumberUtil.extractPossibleNumber(paramString1);
    if (PhoneNumberUtil.PLUS_CHARS_PATTERN.matcher(paramString1).lookingAt()) {}
    do
    {
      return false;
      localObject = phoneUtil.getMetadataForRegion(paramString2);
    } while ((localObject == null) || (!((Phonemetadata.PhoneMetadata)localObject).hasEmergency()));
    Object localObject = Pattern.compile(((Phonemetadata.PhoneMetadata)localObject).getEmergency().getNationalNumberPattern());
    paramString1 = PhoneNumberUtil.normalizeDigitsOnly(paramString1);
    if ((!paramBoolean) || (paramString2.equals("BR"))) {
      return ((Pattern)localObject).matcher(paramString1).matches();
    }
    return ((Pattern)localObject).matcher(paramString1).lookingAt();
  }
  
  public boolean connectsToEmergencyNumber(String paramString1, String paramString2)
  {
    return matchesEmergencyNumberHelper(paramString1, paramString2, true);
  }
  
  public boolean isEmergencyNumber(String paramString1, String paramString2)
  {
    return matchesEmergencyNumberHelper(paramString1, paramString2, false);
  }
}

/* Location:
 * Qualified Name:     com.meizu.i18n.phonenumbers.ShortNumberUtil
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */