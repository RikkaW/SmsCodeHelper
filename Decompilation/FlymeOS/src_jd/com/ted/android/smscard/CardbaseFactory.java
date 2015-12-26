package com.ted.android.smscard;

public class CardbaseFactory
{
  public static CardBase creator(String paramString)
  {
    int i = CardBaseUtils.convertTypeToInt(paramString);
    paramString = new CardBase();
    switch (i)
    {
    default: 
      return paramString;
    }
    return new CardPlaneTicket();
  }
}

/* Location:
 * Qualified Name:     com.ted.android.smscard.CardbaseFactory
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */