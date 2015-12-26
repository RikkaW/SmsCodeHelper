package com.ted.sdk.yellow.entry;

import com.ted.android.contacts.netparser.model.MenuItem.MenuType;

public class ActionType
{
  public static final int ACTIONTYPE_ADDRESS_APP = 10;
  public static final int ACTIONTYPE_APP = 6;
  public static final int ACTIONTYPE_BUSINESS_CARD = 11;
  public static final int ACTIONTYPE_CHONGZHI = 4;
  public static final int ACTIONTYPE_CONTACT_DETAIL = 14;
  public static final int ACTIONTYPE_COPY_CODE = 16;
  public static final int ACTIONTYPE_COPY_ZOOM_IN = 17;
  public static final int ACTIONTYPE_COURIER_SEARCH = 12;
  public static final int ACTIONTYPE_EMAIL = 9;
  public static final int ACTIONTYPE_GOTOLINK = 3;
  public static final int ACTIONTYPE_GOTOLINK_ARG = 15;
  public static final int ACTIONTYPE_INVALID = -1;
  public static final int ACTIONTYPE_NULL = 0;
  public static final int ACTIONTYPE_PHONE = 8;
  public static final int ACTIONTYPE_PHONE_NUMBER = 22;
  public static final int ACTIONTYPE_QUICK_REPLY_GROUP = 20;
  public static final int ACTIONTYPE_REDIRECT_114 = 13;
  public static final int ACTIONTYPE_REMINDER_DATE = 21;
  public static final int ACTIONTYPE_REMIND_EVENT = 7;
  public static final int ACTIONTYPE_REPAYMENT = 5;
  public static final int ACTIONTYPE_SENDSMS = 2;
  public static final int ACTIONTYPE_SHOWSUBMENU = 1;
  
  public static int changToCmdType(MenuItem.MenuType paramMenuType)
  {
    if (paramMenuType == null) {
      return -1;
    }
    switch (paramMenuType)
    {
    default: 
      return -1;
    case e: 
      return 1;
    case i: 
      return 2;
    case d: 
      return 8;
    case g: 
      return 3;
    case a: 
      return 6;
    case c: 
      return 0;
    case h: 
      return 9;
    case b: 
      return 5;
    }
    return 14;
  }
}

/* Location:
 * Qualified Name:     com.ted.sdk.yellow.entry.ActionType
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */