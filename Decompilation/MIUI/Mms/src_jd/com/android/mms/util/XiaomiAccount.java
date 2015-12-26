package com.android.mms.util;

import android.accounts.Account;
import android.content.Context;
import miui.accounts.ExtraAccountManager;

public class XiaomiAccount
{
  public static Account getAccount(Context paramContext)
  {
    return ExtraAccountManager.getXiaomiAccount(paramContext);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.util.XiaomiAccount
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */