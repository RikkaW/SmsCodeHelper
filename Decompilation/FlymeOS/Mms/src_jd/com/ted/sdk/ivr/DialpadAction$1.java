package com.ted.sdk.ivr;

import java.util.HashMap;

class DialpadAction$1
  extends HashMap<Character, DialpadAction>
{
  private static final long serialVersionUID = 1L;
  
  DialpadAction$1()
  {
    put(Character.valueOf('1'), new DialpadAction('1', 4));
    put(Character.valueOf('2'), new DialpadAction('2', 4));
    put(Character.valueOf('3'), new DialpadAction('3', 4));
    put(Character.valueOf('4'), new DialpadAction('4', 4));
    put(Character.valueOf('5'), new DialpadAction('5', 4));
    put(Character.valueOf('6'), new DialpadAction('6', 4));
    put(Character.valueOf('7'), new DialpadAction('7', 4));
    put(Character.valueOf('8'), new DialpadAction('8', 4));
    put(Character.valueOf('9'), new DialpadAction('9', 4));
    put(Character.valueOf('0'), new DialpadAction('0', 4));
    put(Character.valueOf('*'), new DialpadAction('*', 4));
    put(Character.valueOf('#'), new DialpadAction('#', 4));
  }
}

/* Location:
 * Qualified Name:     com.ted.sdk.ivr.DialpadAction.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */