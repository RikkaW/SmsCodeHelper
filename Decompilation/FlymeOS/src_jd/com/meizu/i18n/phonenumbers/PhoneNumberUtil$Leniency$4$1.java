package com.meizu.i18n.phonenumbers;

class PhoneNumberUtil$Leniency$4$1
  implements PhoneNumberMatcher.NumberGroupingChecker
{
  PhoneNumberUtil$Leniency$4$1(PhoneNumberUtil.Leniency.4 param4) {}
  
  public boolean checkGroups(PhoneNumberUtil paramPhoneNumberUtil, Phonenumber.PhoneNumber paramPhoneNumber, StringBuilder paramStringBuilder, String[] paramArrayOfString)
  {
    return PhoneNumberMatcher.allNumberGroupsAreExactlyPresent(paramPhoneNumberUtil, paramPhoneNumber, paramStringBuilder, paramArrayOfString);
  }
}

/* Location:
 * Qualified Name:     com.meizu.i18n.phonenumbers.PhoneNumberUtil.Leniency.4.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */