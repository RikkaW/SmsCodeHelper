package com.meizu.i18n.phonenumbers;

class PhoneNumberUtil$Leniency$3$1
  implements PhoneNumberMatcher.NumberGroupingChecker
{
  PhoneNumberUtil$Leniency$3$1(PhoneNumberUtil.Leniency.3 param3) {}
  
  public boolean checkGroups(PhoneNumberUtil paramPhoneNumberUtil, Phonenumber.PhoneNumber paramPhoneNumber, StringBuilder paramStringBuilder, String[] paramArrayOfString)
  {
    return PhoneNumberMatcher.allNumberGroupsRemainGrouped(paramPhoneNumberUtil, paramPhoneNumber, paramStringBuilder, paramArrayOfString);
  }
}

/* Location:
 * Qualified Name:     com.meizu.i18n.phonenumbers.PhoneNumberUtil.Leniency.3.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */