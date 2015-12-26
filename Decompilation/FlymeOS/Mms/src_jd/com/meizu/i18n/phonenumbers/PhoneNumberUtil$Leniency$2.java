package com.meizu.i18n.phonenumbers;

 enum PhoneNumberUtil$Leniency$2
{
  PhoneNumberUtil$Leniency$2()
  {
    super(paramString, paramInt, null);
  }
  
  boolean verify(Phonenumber.PhoneNumber paramPhoneNumber, String paramString, PhoneNumberUtil paramPhoneNumberUtil)
  {
    if ((!paramPhoneNumberUtil.isValidNumber(paramPhoneNumber)) || (!PhoneNumberMatcher.containsOnlyValidXChars(paramPhoneNumber, paramString, paramPhoneNumberUtil))) {
      return false;
    }
    return PhoneNumberMatcher.isNationalPrefixPresentIfRequired(paramPhoneNumber, paramPhoneNumberUtil);
  }
}

/* Location:
 * Qualified Name:     com.meizu.i18n.phonenumbers.PhoneNumberUtil.Leniency.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */