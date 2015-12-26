package com.meizu.i18n.phonenumbers;

 enum PhoneNumberUtil$Leniency$1
{
  PhoneNumberUtil$Leniency$1()
  {
    super(paramString, paramInt, null);
  }
  
  boolean verify(Phonenumber.PhoneNumber paramPhoneNumber, String paramString, PhoneNumberUtil paramPhoneNumberUtil)
  {
    return paramPhoneNumberUtil.isPossibleNumber(paramPhoneNumber);
  }
}

/* Location:
 * Qualified Name:     com.meizu.i18n.phonenumbers.PhoneNumberUtil.Leniency.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */