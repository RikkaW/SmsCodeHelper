package com.meizu.common.widget;

import android.accounts.Account;
import android.text.TextUtils;

public final class MzContactsContract$MzAccounts
{
  public static final Account DEVICES_ONLY_ACCOUNT = new Account("DeviceOnly", "DeviceOnly");
  private static final String DEVICES_ONLY_ACCOUNT_NAME = "DeviceOnly";
  private static final String DEVICES_ONLY_ACCOUNT_TYPE = "DeviceOnly";
  public static final String FLYME_ACCOUNT_TYPE = "com.meizu.account";
  public static final String SINA_ACCOUNT_TYPE = "com.meizu.sns.sina";
  public static final Account VENDER_ACCOUNT = new Account("account.vender", "account.vender");
  private static final String VENDER_ACCOUNT_NAME = "account.vender";
  public static final String VENDER_ACCOUNT_TYPE = "account.vender";
  
  public static Account[] addDeviceOnlyAccount(Account[] paramArrayOfAccount)
  {
    int j = 0;
    Account[] arrayOfAccount = new Account[paramArrayOfAccount.length + 1];
    arrayOfAccount[0] = DEVICES_ONLY_ACCOUNT;
    int i = 1;
    int k = paramArrayOfAccount.length;
    while (j < k)
    {
      arrayOfAccount[i] = paramArrayOfAccount[j];
      j += 1;
      i += 1;
    }
    return arrayOfAccount;
  }
  
  public static boolean isFlymeAccount(Account paramAccount)
  {
    return (paramAccount != null) && (TextUtils.equals(type, "com.meizu.account"));
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.MzContactsContract.MzAccounts
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */