package com.android.mms.service;

import android.content.Context;
import android.os.Bundle;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import java.io.UnsupportedEncodingException;
import java.util.Map;

public class MmsConfig$Overridden
{
  private final MmsConfig mBase;
  private final Bundle mOverrides;
  
  public MmsConfig$Overridden(MmsConfig paramMmsConfig, Bundle paramBundle)
  {
    mBase = paramMmsConfig;
    mOverrides = paramBundle;
  }
  
  private boolean getBoolean(String paramString)
  {
    Boolean localBoolean = (Boolean)MmsConfig.access$100(mBase).get(paramString);
    if (mOverrides != null) {
      return mOverrides.getBoolean(paramString, localBoolean.booleanValue());
    }
    return localBoolean.booleanValue();
  }
  
  private int getInt(String paramString)
  {
    Integer localInteger = (Integer)MmsConfig.access$100(mBase).get(paramString);
    if (mOverrides != null) {
      return mOverrides.getInt(paramString, localInteger.intValue());
    }
    return localInteger.intValue();
  }
  
  private static String getLine1(Context paramContext, int paramInt)
  {
    return ((TelephonyManager)paramContext.getSystemService("phone")).getLine1NumberForSubscriber(paramInt);
  }
  
  private static String getLine1NoCountryCode(Context paramContext, int paramInt)
  {
    paramContext = (TelephonyManager)paramContext.getSystemService("phone");
    return PhoneUtils.getNationalNumber(paramContext, paramInt, paramContext.getLine1NumberForSubscriber(paramInt));
  }
  
  private String getNai(Context paramContext, int paramInt)
  {
    Object localObject = ((TelephonyManager)paramContext.getSystemService("phone")).getNai(SubscriptionManager.getSlotId(paramInt));
    if (Log.isLoggable("MmsService", 2)) {
      Log.v("MmsService", "MmsConfig.getNai: nai=" + (String)localObject);
    }
    paramContext = (Context)localObject;
    if (!TextUtils.isEmpty((CharSequence)localObject))
    {
      String str = getNaiSuffix();
      paramContext = (Context)localObject;
      if (!TextUtils.isEmpty(str)) {
        paramContext = (String)localObject + str;
      }
    }
    try
    {
      localObject = Base64.encode(paramContext.getBytes("UTF-8"), 2);
      paramContext = (Context)localObject;
      return new String(paramContext);
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException1)
    {
      for (;;)
      {
        try
        {
          localObject = new String(paramContext, "UTF-8");
          paramContext = (Context)localObject;
          return paramContext;
        }
        catch (UnsupportedEncodingException localUnsupportedEncodingException2) {}
        localUnsupportedEncodingException1 = localUnsupportedEncodingException1;
        paramContext = Base64.encode(paramContext.getBytes(), 2);
      }
    }
  }
  
  private String getString(String paramString)
  {
    if ((mOverrides != null) && (mOverrides.containsKey(paramString))) {
      return mOverrides.getString(paramString);
    }
    return MmsConfig.access$200(mBase, paramString);
  }
  
  public String getHttpParamMacro(Context paramContext, String paramString)
  {
    if ("LINE1".equals(paramString)) {
      return getLine1(paramContext, mBase.getSubId());
    }
    if ("LINE1NOCOUNTRYCODE".equals(paramString)) {
      return getLine1NoCountryCode(paramContext, mBase.getSubId());
    }
    if ("NAI".equals(paramString)) {
      return getNai(paramContext, mBase.getSubId());
    }
    return null;
  }
  
  public String getHttpParams()
  {
    return getString("httpParams");
  }
  
  public int getHttpSocketTimeout()
  {
    return getInt("httpSocketTimeout");
  }
  
  public int getMaxMessageSize()
  {
    return getInt("maxMessageSize");
  }
  
  public String getNaiSuffix()
  {
    return getString("naiSuffix");
  }
  
  public boolean getSupportHttpCharsetHeader()
  {
    return getBoolean("supportHttpCharsetHeader");
  }
  
  public String getUaProfTagName()
  {
    return getString("uaProfTagName");
  }
  
  public String getUaProfUrl()
  {
    if ((mOverrides != null) && (mOverrides.containsKey("uaProfUrl"))) {
      return mOverrides.getString("uaProfUrl");
    }
    if (!TextUtils.isEmpty(MmsConfig.access$400(mBase))) {
      return MmsConfig.access$400(mBase);
    }
    return MmsConfig.access$200(mBase, "uaProfUrl");
  }
  
  public String getUserAgent()
  {
    if ((mOverrides != null) && (mOverrides.containsKey("userAgent"))) {
      return mOverrides.getString("userAgent");
    }
    if (!TextUtils.isEmpty(MmsConfig.access$300(mBase))) {
      return MmsConfig.access$300(mBase);
    }
    return MmsConfig.access$200(mBase, "userAgent");
  }
}

/* Location:
 * Qualified Name:     com.android.mms.service.MmsConfig.Overridden
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */