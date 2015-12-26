package com.meizu.i18n.phonenumbers;

import java.util.Iterator;

class PhoneNumberUtil$1
  implements Iterable<PhoneNumberMatch>
{
  PhoneNumberUtil$1(PhoneNumberUtil paramPhoneNumberUtil, CharSequence paramCharSequence, String paramString, PhoneNumberUtil.Leniency paramLeniency, long paramLong) {}
  
  public Iterator<PhoneNumberMatch> iterator()
  {
    return new PhoneNumberMatcher(this$0, val$text, val$defaultRegion, val$leniency, val$maxTries);
  }
}

/* Location:
 * Qualified Name:     com.meizu.i18n.phonenumbers.PhoneNumberUtil.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */