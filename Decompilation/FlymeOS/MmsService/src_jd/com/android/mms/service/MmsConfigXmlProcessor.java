package com.android.mms.service;

import android.content.ContentValues;
import android.util.Log;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class MmsConfigXmlProcessor
{
  private final XmlPullParser mInputParser;
  private final StringBuilder mLogStringBuilder = new StringBuilder();
  private MmsConfigHandler mMmsConfigHandler;
  
  private MmsConfigXmlProcessor(XmlPullParser paramXmlPullParser)
  {
    mInputParser = paramXmlPullParser;
    mMmsConfigHandler = null;
  }
  
  private int advanceToNextEvent(int paramInt)
    throws XmlPullParserException, IOException
  {
    int i;
    do
    {
      i = mInputParser.next();
    } while ((i != paramInt) && (i != 1));
    return i;
  }
  
  public static MmsConfigXmlProcessor get(XmlPullParser paramXmlPullParser)
  {
    return new MmsConfigXmlProcessor(paramXmlPullParser);
  }
  
  private void processMmsConfig()
    throws IOException, XmlPullParserException
  {
    int i;
    for (;;)
    {
      i = mInputParser.next();
      if (i != 4)
      {
        if (i != 2) {
          break;
        }
        processMmsConfigKeyValue();
      }
    }
    if (i == 3) {
      return;
    }
    throw new XmlPullParserException("MmsConfig: expecting start or end tag @" + xmlParserDebugContext());
  }
  
  private void processMmsConfigKeyValue()
    throws IOException, XmlPullParserException
  {
    String str2 = mInputParser.getAttributeValue(null, "name");
    String str3 = mInputParser.getName();
    int j = mInputParser.next();
    String str1 = null;
    int i = j;
    if (j == 4)
    {
      str1 = mInputParser.getText();
      i = mInputParser.next();
    }
    if (i != 3) {
      throw new XmlPullParserException("MmsConfigXmlProcessor: expecting end tag @" + xmlParserDebugContext());
    }
    if (MmsConfig.isValidKey(str2, str3))
    {
      if (mMmsConfigHandler != null) {
        mMmsConfigHandler.process(str2, str1, str3);
      }
      return;
    }
    Log.w("MmsService", "MmsConfig: invalid key=" + str2 + " or type=" + str3);
  }
  
  private String xmlParserDebugContext()
  {
    mLogStringBuilder.setLength(0);
    if (mInputParser != null) {
      try
      {
        int i = mInputParser.getEventType();
        mLogStringBuilder.append(xmlParserEventString(i));
        if ((i == 2) || (i == 3) || (i == 4))
        {
          mLogStringBuilder.append('<').append(mInputParser.getName());
          i = 0;
          while (i < mInputParser.getAttributeCount())
          {
            mLogStringBuilder.append(' ').append(mInputParser.getAttributeName(i)).append('=').append(mInputParser.getAttributeValue(i));
            i += 1;
          }
          mLogStringBuilder.append("/>");
        }
        String str = mLogStringBuilder.toString();
        return str;
      }
      catch (XmlPullParserException localXmlPullParserException)
      {
        Log.e("MmsService", "xmlParserDebugContext: " + localXmlPullParserException, localXmlPullParserException);
      }
    }
    return "Unknown";
  }
  
  private static String xmlParserEventString(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return Integer.toString(paramInt);
    case 0: 
      return "START_DOCUMENT";
    case 1: 
      return "END_DOCUMENT";
    case 2: 
      return "START_TAG";
    case 3: 
      return "END_TAG";
    }
    return "TEXT";
  }
  
  public void process()
  {
    try
    {
      if (advanceToNextEvent(2) != 2) {
        throw new XmlPullParserException("MmsConfigXmlProcessor: expecting start tag @" + xmlParserDebugContext());
      }
    }
    catch (IOException localIOException)
    {
      Log.e("MmsService", "MmsConfigXmlProcessor: I/O failure " + localIOException, localIOException);
      do
      {
        return;
        new ContentValues();
      } while (!"mms_config".equals(mInputParser.getName()));
      processMmsConfig();
      return;
    }
    catch (XmlPullParserException localXmlPullParserException)
    {
      Log.e("MmsService", "MmsConfigXmlProcessor: parsing failure " + localXmlPullParserException, localXmlPullParserException);
    }
  }
  
  public MmsConfigXmlProcessor setMmsConfigHandler(MmsConfigHandler paramMmsConfigHandler)
  {
    mMmsConfigHandler = paramMmsConfigHandler;
    return this;
  }
  
  public static abstract interface MmsConfigHandler
  {
    public abstract void process(String paramString1, String paramString2, String paramString3);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.service.MmsConfigXmlProcessor
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */