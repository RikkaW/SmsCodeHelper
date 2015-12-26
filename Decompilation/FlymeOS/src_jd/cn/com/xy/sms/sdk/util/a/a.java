package cn.com.xy.sms.sdk.util.a;

import android.util.Xml;
import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import java.io.InputStream;
import org.xmlpull.v1.XmlPullParser;

public final class a
{
  private static void a(InputStream paramInputStream, String[] paramArrayOfString, XyCallBack paramXyCallBack)
  {
    XmlPullParser localXmlPullParser = Xml.newPullParser();
    localXmlPullParser.setInput(paramInputStream, "UTF-8");
    int i = localXmlPullParser.getEventType();
    if (i == 1) {
      return;
    }
    switch (i)
    {
    }
    for (;;)
    {
      i = localXmlPullParser.next();
      break;
      paramInputStream = localXmlPullParser.getName();
      int j = paramArrayOfString.length;
      i = 0;
      while (i < j)
      {
        if (paramInputStream.equals(paramArrayOfString[i])) {
          paramXyCallBack.execute(new Object[] { paramInputStream, localXmlPullParser.nextText() });
        }
        i += 1;
      }
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.util.a.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */