package com.meizu.i18n.phonenumbers;

 enum PhoneNumberUtil$Leniency$3
{
  PhoneNumberUtil$Leniency$3()
  {
    super(paramString, paramInt, null);
  }
  
  boolean verify(Phonenumber.PhoneNumber paramPhoneNumber, String paramString, PhoneNumberUtil paramPhoneNumberUtil)
  {
    if ((!paramPhoneNumberUtil.isValidNumber(paramPhoneNumber)) || (!PhoneNumberMatcher.containsOnlyValidXChars(paramPhoneNumber, paramString, paramPhoneNumberUtil)) || (PhoneNumberMatcher.containsMoreThanOneSlash(paramString)) || (!PhoneNumberMatcher.isNationalPrefixPresentIfRequired(paramPhoneNumber, paramPhoneNumberUtil))) {
      return false;
    }
    return PhoneNumberMatcher.checkNumberGroupingIsValid(paramPhoneNumber, paramString, paramPhoneNumberUtil, new PhoneNumberUtil.Leniency.3.1(this));
  }
}

/* Location:
 * Qualified Name:     com.meizu.i18n.phonenumbers.PhoneNumberUtil.Leniency.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */