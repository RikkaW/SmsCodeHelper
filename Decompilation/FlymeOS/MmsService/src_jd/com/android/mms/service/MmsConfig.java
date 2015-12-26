package com.android.mms.service;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class MmsConfig
{
  private static final Map<String, Object> DEFAULTS = new ConcurrentHashMap();
  private final Map<String, Object> mKeyValues = new ConcurrentHashMap();
  private final int mSubId;
  private String mUaProfUrl = null;
  private String mUserAgent = null;
  
  static
  {
    DEFAULTS.put("enabledMMS", Boolean.valueOf(true));
    DEFAULTS.put("enabledTransID", Boolean.valueOf(false));
    DEFAULTS.put("enabledNotifyWapMMSC", Boolean.valueOf(false));
    DEFAULTS.put("aliasEnabled", Boolean.valueOf(false));
    DEFAULTS.put("allowAttachAudio", Boolean.valueOf(true));
    DEFAULTS.put("enableMultipartSMS", Boolean.valueOf(true));
    DEFAULTS.put("enableSMSDeliveryReports", Boolean.valueOf(true));
    DEFAULTS.put("enableGroupMms", Boolean.valueOf(true));
    DEFAULTS.put("supportMmsContentDisposition", Boolean.valueOf(true));
    DEFAULTS.put("config_cellBroadcastAppLinks", Boolean.valueOf(true));
    DEFAULTS.put("sendMultipartSmsAsSeparateMessages", Boolean.valueOf(false));
    DEFAULTS.put("enableMMSReadReports", Boolean.valueOf(false));
    DEFAULTS.put("enableMMSDeliveryReports", Boolean.valueOf(false));
    DEFAULTS.put("supportHttpCharsetHeader", Boolean.valueOf(false));
    DEFAULTS.put("maxMessageSize", Integer.valueOf(307200));
    DEFAULTS.put("maxImageHeight", Integer.valueOf(480));
    DEFAULTS.put("maxImageWidth", Integer.valueOf(640));
    DEFAULTS.put("recipientLimit", Integer.valueOf(Integer.MAX_VALUE));
    DEFAULTS.put("httpSocketTimeout", Integer.valueOf(60000));
    DEFAULTS.put("aliasMinChars", Integer.valueOf(2));
    DEFAULTS.put("aliasMaxChars", Integer.valueOf(48));
    DEFAULTS.put("smsToMmsTextThreshold", Integer.valueOf(-1));
    DEFAULTS.put("smsToMmsTextLengthThreshold", Integer.valueOf(-1));
    DEFAULTS.put("maxMessageTextSize", Integer.valueOf(-1));
    DEFAULTS.put("maxSubjectLength", Integer.valueOf(40));
    DEFAULTS.put("uaProfTagName", "x-wap-profile");
    DEFAULTS.put("userAgent", "");
    DEFAULTS.put("uaProfUrl", "");
    DEFAULTS.put("httpParams", "");
    DEFAULTS.put("emailGatewayNumber", "");
    DEFAULTS.put("naiSuffix", "");
  }
  
  public MmsConfig(Context paramContext, int paramInt)
  {
    mSubId = paramInt;
    mKeyValues.clear();
    mKeyValues.putAll(DEFAULTS);
    loadDeviceUaSettings(paramContext);
    Log.v("MmsService", "MmsConfig: mUserAgent=" + mUserAgent + ", mUaProfUrl=" + mUaProfUrl);
    loadFromResources(paramContext);
    Log.v("MmsService", "MmsConfig: all settings -- " + mKeyValues);
  }
  
  private String getNullableStringValue(String paramString)
  {
    paramString = mKeyValues.get(paramString);
    if (paramString != null) {
      return (String)paramString;
    }
    return null;
  }
  
  public static boolean isValidKey(String paramString1, String paramString2)
  {
    if ((!TextUtils.isEmpty(paramString1)) && (DEFAULTS.containsKey(paramString1)))
    {
      paramString1 = DEFAULTS.get(paramString1);
      if (paramString1 != null)
      {
        paramString1 = paramString1.getClass();
        if (!"int".equals(paramString2)) {
          break label63;
        }
        if (paramString1 != Integer.class) {
          break label61;
        }
      }
      label61:
      label63:
      label80:
      do
      {
        do
        {
          return true;
          paramString1 = String.class;
          break;
          return false;
          if (!"bool".equals(paramString2)) {
            break label80;
          }
        } while (paramString1 == Boolean.class);
        return false;
        if (!"string".equals(paramString2)) {
          break label97;
        }
      } while (paramString1 == String.class);
      return false;
    }
    label97:
    return false;
  }
  
  private void loadDeviceUaSettings(Context paramContext)
  {
    paramContext = (TelephonyManager)paramContext.getSystemService("phone");
    mUserAgent = paramContext.getMmsUserAgent();
    mUaProfUrl = paramContext.getMmsUAProfUrl();
  }
  
  private void loadFromResources(Context paramContext)
  {
    Log.d("MmsService", "MmsConfig.loadFromResources");
    paramContext = paramContext.getResources().getXml(2130837504);
    MmsConfigXmlProcessor localMmsConfigXmlProcessor = MmsConfigXmlProcessor.get(paramContext);
    localMmsConfigXmlProcessor.setMmsConfigHandler(new MmsConfigXmlProcessor.MmsConfigHandler()
    {
      public void process(String paramAnonymousString1, String paramAnonymousString2, String paramAnonymousString3)
      {
        MmsConfig.this.update(paramAnonymousString1, paramAnonymousString2, paramAnonymousString3);
      }
    });
    try
    {
      localMmsConfigXmlProcessor.process();
      return;
    }
    finally
    {
      paramContext.close();
    }
  }
  
  private void update(String paramString1, String paramString2, String paramString3)
  {
    try
    {
      if ("int".equals(paramString3))
      {
        mKeyValues.put(paramString1, Integer.valueOf(Integer.parseInt(paramString2)));
        return;
      }
      if ("bool".equals(paramString3))
      {
        mKeyValues.put(paramString1, Boolean.valueOf(Boolean.parseBoolean(paramString2)));
        return;
      }
    }
    catch (NumberFormatException localNumberFormatException)
    {
      Log.e("MmsService", "MmsConfig.update: invalid " + paramString1 + "," + paramString2 + "," + paramString3);
      return;
    }
    if ("string".equals(paramString3)) {
      mKeyValues.put(paramString1, paramString2);
    }
  }
  
  public Bundle getCarrierConfigValues()
  {
    Bundle localBundle = new Bundle();
    Iterator localIterator = mKeyValues.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Object localObject1 = (Map.Entry)localIterator.next();
      String str = (String)((Map.Entry)localObject1).getKey();
      Object localObject2 = ((Map.Entry)localObject1).getValue();
      if (localObject2 != null) {}
      for (localObject1 = localObject2.getClass();; localObject1 = String.class)
      {
        if (localObject1 != Integer.class) {
          break label101;
        }
        localBundle.putInt(str, ((Integer)localObject2).intValue());
        break;
      }
      label101:
      if (localObject1 == Boolean.class) {
        localBundle.putBoolean(str, ((Boolean)localObject2).booleanValue());
      } else if (localObject1 == String.class) {
        localBundle.putString(str, (String)localObject2);
      }
    }
    return localBundle;
  }
  
  public int getSubId()
  {
    return mSubId;
  }
  
  public static class Overridden
  {
    private final MmsConfig mBase;
    private final Bundle mOverrides;
    
    public Overridden(MmsConfig paramMmsConfig, Bundle paramBundle)
    {
      mBase = paramMmsConfig;
      mOverrides = paramBundle;
    }
    
    private boolean getBoolean(String paramString)
    {
      Boolean localBoolean = (Boolean)mBase.mKeyValues.get(paramString);
      if (mOverrides != null) {
        return mOverrides.getBoolean(paramString, localBoolean.booleanValue());
      }
      return localBoolean.booleanValue();
    }
    
    private int getInt(String paramString)
    {
      Integer localInteger = (Integer)mBase.mKeyValues.get(paramString);
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
      return mBase.getNullableStringValue(paramString);
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
      if (!TextUtils.isEmpty(mBase.mUaProfUrl)) {
        return mBase.mUaProfUrl;
      }
      return mBase.getNullableStringValue("uaProfUrl");
    }
    
    public String getUserAgent()
    {
      if ((mOverrides != null) && (mOverrides.containsKey("userAgent"))) {
        return mOverrides.getString("userAgent");
      }
      if (!TextUtils.isEmpty(mBase.mUserAgent)) {
        return mBase.mUserAgent;
      }
      return mBase.getNullableStringValue("userAgent");
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.service.MmsConfig
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */