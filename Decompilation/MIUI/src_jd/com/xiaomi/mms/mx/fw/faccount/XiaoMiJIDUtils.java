package com.xiaomi.mms.mx.fw.faccount;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerFuture;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.xiaomi.accountsdk.account.data.ExtendedAuthToken;
import com.xiaomi.mms.mx.fw.HmsPersister;
import com.xiaomi.mms.mx.utils.Log;
import miui.yellowpage.MiPubUtils;

public class XiaoMiJIDUtils
{
  public static ExtendedAuthToken getAuthtoken(Context paramContext, String paramString)
  {
    Object localObject = null;
    paramString = getAuthtokenString(paramContext, paramString);
    paramContext = (Context)localObject;
    if (!TextUtils.isEmpty(paramString)) {
      paramContext = ExtendedAuthToken.parse(paramString);
    }
    return paramContext;
  }
  
  public static String getAuthtokenString(Context paramContext, String paramString)
  {
    Account localAccount = getXiaomiAccount(paramContext);
    Object localObject = null;
    try
    {
      paramString = AccountManager.get(paramContext).getAuthToken(localAccount, paramString, null, false, null, null);
      paramContext = (Context)localObject;
      if (paramString != null)
      {
        paramString = (Bundle)paramString.getResult();
        paramContext = (Context)localObject;
        if (paramString != null) {
          paramContext = paramString.getString("authtoken");
        }
      }
      return paramContext;
    }
    catch (Exception paramContext)
    {
      Log.d("XiaoMiJID", paramContext.toString());
    }
    return null;
  }
  
  public static String getUserIDWithResId()
  {
    return getUserIDWithResId(HmsPersister.getContext());
  }
  
  public static String getUserIDWithResId(Context paramContext)
  {
    return getUserIDWithResId(paramContext, tryGetFullSmtpName(paramContext));
  }
  
  private static String getUserIDWithResId(Context paramContext, String paramString)
  {
    paramString = JIDUtils.getFullSmtpName(paramString);
    paramContext = MiPubUtils.getDeviceId(paramContext);
    return paramString + "/" + paramContext;
  }
  
  public static Account getXiaomiAccount(Context paramContext)
  {
    Object localObject = null;
    Account[] arrayOfAccount = AccountManager.get(paramContext).getAccountsByType("com.xiaomi");
    paramContext = (Context)localObject;
    if (arrayOfAccount != null)
    {
      paramContext = (Context)localObject;
      if (arrayOfAccount.length > 0) {
        paramContext = arrayOfAccount[0];
      }
    }
    return paramContext;
  }
  
  public static boolean hasAccount(Context paramContext)
  {
    paramContext = getXiaomiAccount(paramContext);
    return (paramContext != null) && (!TextUtils.isEmpty(name));
  }
  
  public static String tryGetFullSmtpName(Context paramContext)
  {
    Object localObject = null;
    Account localAccount = getXiaomiAccount(paramContext);
    paramContext = (Context)localObject;
    if (localAccount != null) {
      paramContext = JIDUtils.getFullSmtpName(name);
    }
    return paramContext;
  }
  
  public static String tryGetPSecurity(Context paramContext, String paramString)
  {
    Object localObject = null;
    paramString = getAuthtoken(paramContext, paramString);
    paramContext = (Context)localObject;
    if (paramString != null) {
      paramContext = security;
    }
    return paramContext;
  }
  
  public static String tryGetServiceToken(Context paramContext, String paramString)
  {
    Object localObject = null;
    paramString = getAuthtoken(paramContext, paramString);
    paramContext = (Context)localObject;
    if (paramString != null) {
      paramContext = authToken;
    }
    return paramContext;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.fw.faccount.XiaoMiJIDUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */