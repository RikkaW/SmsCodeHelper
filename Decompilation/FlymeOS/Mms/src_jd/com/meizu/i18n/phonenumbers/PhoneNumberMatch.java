package com.meizu.i18n.phonenumbers;

import java.util.Arrays;

public final class PhoneNumberMatch
{
  private final Phonenumber.PhoneNumber number;
  private final String rawString;
  private final int start;
  
  PhoneNumberMatch(int paramInt, String paramString, Phonenumber.PhoneNumber paramPhoneNumber)
  {
    if (paramInt < 0) {
      throw new IllegalArgumentException("Start index must be >= 0.");
    }
    if ((paramString == null) || (paramPhoneNumber == null)) {
      throw new NullPointerException();
    }
    start = paramInt;
    rawString = paramString;
    number = paramPhoneNumber;
  }
  
  public int end()
  {
    return start + rawString.length();
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof PhoneNumberMatch)) {
        return false;
      }
      paramObject = (PhoneNumberMatch)paramObject;
    } while ((rawString.equals(rawString)) && (start == start) && (number.equals(number)));
    return false;
  }
  
  public int hashCode()
  {
    return Arrays.hashCode(new Object[] { Integer.valueOf(start), rawString, number });
  }
  
  public Phonenumber.PhoneNumber number()
  {
    return number;
  }
  
  public String rawString()
  {
    return rawString;
  }
  
  public int start()
  {
    return start;
  }
  
  public String toString()
  {
    return "PhoneNumberMatch [" + start() + "," + end() + ") " + rawString;
  }
}

/* Location:
 * Qualified Name:     com.meizu.i18n.phonenumbers.PhoneNumberMatch
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */