package com.android.mms;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Build.VERSION;
import android.util.Log;
import java.io.IOException;
import java.util.Locale;
import miui.os.Build;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class MmsConfig
{
  private static boolean mAliasEnabled;
  private static int mAliasRuleMaxChars = 48;
  private static int mAliasRuleMinChars;
  private static boolean mAllowAttachAudio;
  private static int mDefaultMMSMessagesPerThread;
  private static int mDefaultSMSMessagesPerThread;
  private static String mEmailGateway;
  private static boolean mEnableMMSDeliveryReports;
  private static boolean mEnableMMSReadReports;
  private static boolean mEnableMultipartSMS;
  private static boolean mEnableSMSDeliveryReports;
  private static boolean mEnableSlideDuration;
  private static String mHttpParams;
  private static String mHttpParamsLine1Key;
  private static int mHttpSocketTimeout;
  private static String mLogPath;
  private static int mMaxImageHeight;
  private static int mMaxImageWidth;
  private static int mMaxMessageCountPerThread;
  private static int mMaxMessageSize;
  private static int mMaxSizeScaleForPendingMmsAllowed;
  private static int mMaxSubjectLength = 40;
  private static int mMaxTextLength;
  private static int mMinMessageCountPerThread;
  private static int mMinimumSlideElementDuration;
  private static int mMmsEnabled;
  private static boolean mNotifyWapMMSC;
  private static int mRecipientLimit;
  private static int mSmsToMmsTextThreshold;
  private static boolean mTransIdEnabled = false;
  private static String mUaProfTagName;
  private static String mUaProfUrl;
  private static String mUserAgent;
  
  static
  {
    mMmsEnabled = 1;
    mMaxMessageSize = 307200;
    mUserAgent = "Android-Mms/2.0";
    mUaProfTagName = "x-wap-profile";
    mLogPath = "/MIUI/debug_log/Mms/";
    mUaProfUrl = null;
    mHttpParams = null;
    mHttpParamsLine1Key = null;
    mEmailGateway = null;
    mMaxImageHeight = 1920;
    mMaxImageWidth = 1920;
    mRecipientLimit = Integer.MAX_VALUE;
    mDefaultSMSMessagesPerThread = 500;
    mDefaultMMSMessagesPerThread = 50;
    mMinMessageCountPerThread = 10;
    mMaxMessageCountPerThread = 5000;
    mHttpSocketTimeout = 60000;
    mMinimumSlideElementDuration = 7;
    mNotifyWapMMSC = false;
    mAllowAttachAudio = true;
    mSmsToMmsTextThreshold = 9;
    mEnableMultipartSMS = false;
    mEnableSlideDuration = true;
    mEnableMMSReadReports = true;
    mEnableSMSDeliveryReports = true;
    mEnableMMSDeliveryReports = true;
    mMaxTextLength = -1;
    mMaxSizeScaleForPendingMmsAllowed = 4;
    mAliasEnabled = false;
    mAliasRuleMinChars = 2;
  }
  
  public static final void beginDocument(XmlPullParser paramXmlPullParser, String paramString)
    throws XmlPullParserException, IOException
  {
    int i;
    do
    {
      i = paramXmlPullParser.next();
    } while ((i != 2) && (i != 1));
    if (i != 2) {
      throw new XmlPullParserException("No start tag found");
    }
    if (!paramXmlPullParser.getName().equals(paramString)) {
      throw new XmlPullParserException("Unexpected start tag: found " + paramXmlPullParser.getName() + ", expected " + paramString);
    }
  }
  
  public static int getAliasMaxChars()
  {
    return mAliasRuleMaxChars;
  }
  
  public static int getAliasMinChars()
  {
    return mAliasRuleMinChars;
  }
  
  public static boolean getAllowAttachAudio()
  {
    return mAllowAttachAudio;
  }
  
  private static String getDeviceName()
  {
    if ("2014817".equalsIgnoreCase(Build.MODEL)) {
      return " Redmi2";
    }
    return Build.MODEL;
  }
  
  public static String getEmailGateway()
  {
    return mEmailGateway;
  }
  
  public static String getHttpParams()
  {
    return mHttpParams;
  }
  
  public static String getHttpParamsLine1Key()
  {
    return mHttpParamsLine1Key;
  }
  
  public static int getHttpSocketTimeout()
  {
    return mHttpSocketTimeout;
  }
  
  private static String getLocalString()
  {
    Object localObject = Locale.getDefault();
    String str1 = ((Locale)localObject).getLanguage().toLowerCase();
    String str2 = ((Locale)localObject).getCountry().toLowerCase();
    localObject = ((Locale)localObject).getVariant().toLowerCase();
    if ((str1.length() == 0) && (str2.length() == 0)) {
      return "";
    }
    StringBuilder localStringBuilder = new StringBuilder(11);
    localStringBuilder.append(str1);
    if ((str2.length() > 0) || (((String)localObject).length() > 0)) {
      localStringBuilder.append('-');
    }
    localStringBuilder.append(str2);
    if (((String)localObject).length() > 0) {
      localStringBuilder.append('-');
    }
    localStringBuilder.append((String)localObject);
    return localStringBuilder.toString();
  }
  
  public static String getLogPath()
  {
    return mLogPath;
  }
  
  public static int getMaxImageHeight()
  {
    return mMaxImageHeight;
  }
  
  public static int getMaxImageWidth()
  {
    return mMaxImageWidth;
  }
  
  public static int getMaxMessageSize()
  {
    Log.v("MmsConfig", "MmsConfig.getMaxMessageSize(): " + mMaxMessageSize);
    return mMaxMessageSize;
  }
  
  public static int getMaxSizeScaleForPendingMmsAllowed()
  {
    return mMaxSizeScaleForPendingMmsAllowed;
  }
  
  public static int getMaxTextLimit()
  {
    if (mMaxTextLength > -1) {
      return mMaxTextLength;
    }
    return Integer.MAX_VALUE;
  }
  
  public static int getMinimumSlideElementDuration()
  {
    return mMinimumSlideElementDuration;
  }
  
  public static boolean getMmsEnabled()
  {
    return mMmsEnabled == 1;
  }
  
  public static boolean getMultipartSmsEnabled()
  {
    return mEnableMultipartSMS;
  }
  
  public static boolean getNotifyWapMMSC()
  {
    return mNotifyWapMMSC;
  }
  
  public static int getRecipientLimit()
  {
    return mRecipientLimit;
  }
  
  public static boolean getSlideDurationEnabled()
  {
    return mEnableSlideDuration;
  }
  
  public static int getSmsToMmsTextThreshold()
  {
    return mSmsToMmsTextThreshold;
  }
  
  public static boolean getTransIdEnabled()
  {
    return mTransIdEnabled;
  }
  
  public static String getUaProfTagName()
  {
    return mUaProfTagName;
  }
  
  public static String getUaProfUrl()
  {
    return mUaProfUrl;
  }
  
  public static String getUserAgent()
  {
    if (Build.checkRegion("SG")) {}
    for (String str = getDeviceName() + " " + mUserAgent + " (Linux; U; Android " + Build.VERSION.RELEASE + "; " + getLocalString() + "; " + Build.BRAND + " " + getDeviceName() + " Build/" + Build.ID + ")";; str = mUserAgent + " (Linux; U; Android " + Build.VERSION.RELEASE + "; " + getLocalString() + "; " + Build.BRAND + " " + getDeviceName() + " Build/" + Build.ID + ")")
    {
      Log.v("MmsConfig", str);
      return str;
    }
  }
  
  public static void init(Context paramContext)
  {
    Log.v("MmsConfig", "MmsConfig.init()");
    loadMmsSettings(paramContext);
  }
  
  public static boolean isAliasEnabled()
  {
    return mAliasEnabled;
  }
  
  private static void loadMmsSettings(Context paramContext)
  {
    XmlResourceParser localXmlResourceParser = paramContext.getResources().getXml(2131099649);
    for (;;)
    {
      String str1;
      String str3;
      try
      {
        beginDocument(localXmlResourceParser, "mms_config");
        nextElement(localXmlResourceParser);
        str1 = localXmlResourceParser.getName();
        if (str1 == null)
        {
          localXmlResourceParser.close();
          localXmlResourceParser = null;
          paramContext = localXmlResourceParser;
          if (getMmsEnabled())
          {
            paramContext = localXmlResourceParser;
            if (mUaProfUrl == null) {
              paramContext = "uaProfUrl";
            }
          }
          if (paramContext != null) {
            Log.e("MmsConfig", String.format("MmsConfig.loadMmsSettings mms_config.xml missing %s setting", new Object[] { paramContext }));
          }
          return;
        }
        String str2 = localXmlResourceParser.getAttributeName(0);
        str3 = localXmlResourceParser.getAttributeValue(0);
        paramContext = null;
        if (localXmlResourceParser.next() == 4) {
          paramContext = localXmlResourceParser.getText();
        }
        if (!"name".equalsIgnoreCase(str2)) {
          continue;
        }
        if (!"bool".equals(str1)) {
          break label464;
        }
        if ("enabledMMS".equalsIgnoreCase(str3)) {
          if ("true".equalsIgnoreCase(paramContext))
          {
            i = 1;
            mMmsEnabled = i;
            continue;
          }
        }
      }
      catch (XmlPullParserException paramContext)
      {
        Log.e("MmsConfig", "loadMmsSettings caught ", paramContext);
        localXmlResourceParser.close();
        continue;
        int i = 0;
        continue;
        if ("enabledTransID".equalsIgnoreCase(str3))
        {
          mTransIdEnabled = "true".equalsIgnoreCase(paramContext);
          continue;
        }
      }
      catch (NumberFormatException paramContext)
      {
        Log.e("MmsConfig", "loadMmsSettings caught ", paramContext);
        localXmlResourceParser.close();
        continue;
        if ("enabledNotifyWapMMSC".equalsIgnoreCase(str3))
        {
          mNotifyWapMMSC = "true".equalsIgnoreCase(paramContext);
          continue;
        }
      }
      catch (IOException paramContext)
      {
        Log.e("MmsConfig", "loadMmsSettings caught ", paramContext);
        localXmlResourceParser.close();
        continue;
        if ("aliasEnabled".equalsIgnoreCase(str3))
        {
          mAliasEnabled = "true".equalsIgnoreCase(paramContext);
          continue;
        }
      }
      finally
      {
        localXmlResourceParser.close();
      }
      if ("allowAttachAudio".equalsIgnoreCase(str3))
      {
        mAllowAttachAudio = "true".equalsIgnoreCase(paramContext);
      }
      else if ("enableMultipartSMS".equalsIgnoreCase(str3))
      {
        mEnableMultipartSMS = "true".equalsIgnoreCase(paramContext);
      }
      else if ("enableSlideDuration".equalsIgnoreCase(str3))
      {
        mEnableSlideDuration = "true".equalsIgnoreCase(paramContext);
      }
      else if ("enableMMSReadReports".equalsIgnoreCase(str3))
      {
        mEnableMMSReadReports = "true".equalsIgnoreCase(paramContext);
      }
      else if ("enableSMSDeliveryReports".equalsIgnoreCase(str3))
      {
        mEnableSMSDeliveryReports = "true".equalsIgnoreCase(paramContext);
      }
      else if ("enableMMSDeliveryReports".equalsIgnoreCase(str3))
      {
        mEnableMMSDeliveryReports = "true".equalsIgnoreCase(paramContext);
        continue;
        label464:
        if ("int".equals(str1))
        {
          if ("maxMessageSize".equalsIgnoreCase(str3))
          {
            if (Build.IS_CM_CUSTOMIZATION_TEST) {
              mMaxMessageSize = 306688;
            } else {
              mMaxMessageSize = Integer.parseInt(paramContext);
            }
          }
          else if ("maxImageHeight".equalsIgnoreCase(str3))
          {
            mMaxImageHeight = Integer.parseInt(paramContext);
          }
          else if ("maxImageWidth".equalsIgnoreCase(str3))
          {
            mMaxImageWidth = Integer.parseInt(paramContext);
          }
          else if ("defaultSMSMessagesPerThread".equalsIgnoreCase(str3))
          {
            mDefaultSMSMessagesPerThread = Integer.parseInt(paramContext);
          }
          else if ("defaultMMSMessagesPerThread".equalsIgnoreCase(str3))
          {
            mDefaultMMSMessagesPerThread = Integer.parseInt(paramContext);
          }
          else if ("minMessageCountPerThread".equalsIgnoreCase(str3))
          {
            mMinMessageCountPerThread = Integer.parseInt(paramContext);
          }
          else if ("maxMessageCountPerThread".equalsIgnoreCase(str3))
          {
            mMaxMessageCountPerThread = Integer.parseInt(paramContext);
          }
          else if ("recipientLimit".equalsIgnoreCase(str3))
          {
            mRecipientLimit = Integer.parseInt(paramContext);
            if (mRecipientLimit < 0) {
              if (Build.IS_CM_CUSTOMIZATION) {
                mRecipientLimit = 100;
              } else {
                mRecipientLimit = Integer.MAX_VALUE;
              }
            }
          }
          else if ("httpSocketTimeout".equalsIgnoreCase(str3))
          {
            mHttpSocketTimeout = Integer.parseInt(paramContext);
          }
          else if ("minimumSlideElementDuration".equalsIgnoreCase(str3))
          {
            mMinimumSlideElementDuration = Integer.parseInt(paramContext);
          }
          else if ("maxSizeScaleForPendingMmsAllowed".equalsIgnoreCase(str3))
          {
            mMaxSizeScaleForPendingMmsAllowed = Integer.parseInt(paramContext);
          }
          else if ("aliasMinChars".equalsIgnoreCase(str3))
          {
            mAliasRuleMinChars = Integer.parseInt(paramContext);
          }
          else if ("aliasMaxChars".equalsIgnoreCase(str3))
          {
            mAliasRuleMaxChars = Integer.parseInt(paramContext);
          }
          else if ("smsToMmsTextThreshold".equalsIgnoreCase(str3))
          {
            if (Build.IS_CM_CUSTOMIZATION_TEST) {
              mSmsToMmsTextThreshold = 11;
            } else {
              mSmsToMmsTextThreshold = Integer.parseInt(paramContext);
            }
          }
          else if ("maxMessageTextSize".equalsIgnoreCase(str3))
          {
            mMaxTextLength = Integer.parseInt(paramContext);
          }
          else if ("maxSubjectLength".equalsIgnoreCase(str3))
          {
            mMaxSubjectLength = Integer.parseInt(paramContext);
          }
        }
        else if ("string".equals(str1)) {
          if ("userAgent".equalsIgnoreCase(str3)) {
            mUserAgent = paramContext;
          } else if ("uaProfTagName".equalsIgnoreCase(str3)) {
            mUaProfTagName = paramContext;
          } else if ("uaProfUrl".equalsIgnoreCase(str3)) {
            mUaProfUrl = paramContext;
          } else if ("httpParams".equalsIgnoreCase(str3)) {
            mHttpParams = paramContext;
          } else if ("httpParamsLine1Key".equalsIgnoreCase(str3)) {
            mHttpParamsLine1Key = paramContext;
          } else if ("emailGatewayNumber".equalsIgnoreCase(str3)) {
            mEmailGateway = paramContext;
          } else if ("logPath".equalsIgnoreCase(str3)) {
            mLogPath = paramContext;
          }
        }
      }
    }
  }
  
  public static final void nextElement(XmlPullParser paramXmlPullParser)
    throws XmlPullParserException, IOException
  {
    int i;
    do
    {
      i = paramXmlPullParser.next();
    } while ((i != 2) && (i != 1));
  }
}

/* Location:
 * Qualified Name:     com.android.mms.MmsConfig
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */