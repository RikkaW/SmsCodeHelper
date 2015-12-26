package com.android.mms.ui;

import android.text.TextUtils;
import android.util.Log;
import com.android.mms.model.LayoutModel;
import com.android.mms.model.RegionModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import miui.os.Build;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

class SimplePduDoc$SlideHandler
  extends DefaultHandler
{
  private SimplePduPage mCurrPage;
  private ArrayList<SimplePduPart> mPartsRecord;
  private boolean mStartPart = false;
  private int mediaCount;
  private int textCount;
  
  public SimplePduDoc$SlideHandler(ArrayList<SimplePduPart> paramArrayList)
  {
    Object localObject;
    mPartsRecord = ((ArrayList)((ArrayList)localObject).clone());
    textCount = 0;
    mediaCount = 0;
  }
  
  private void parseRegion(Attributes paramAttributes)
  {
    String str2 = paramAttributes.getValue("id");
    String str1 = paramAttributes.getValue("top");
    int i = 0;
    if (TextUtils.isEmpty(str1)) {}
    label132:
    label163:
    do
    {
      for (;;)
      {
        return;
        if (str1.endsWith("px")) {
          paramAttributes = str1.substring(0, str1.indexOf("px"));
        }
        for (;;)
        {
          if (TextUtils.isEmpty(paramAttributes)) {
            break label132;
          }
          try
          {
            int j = Integer.parseInt(paramAttributes);
            i = j;
          }
          catch (Exception paramAttributes)
          {
            for (;;)
            {
              Log.e("Mms/SimplePduDoc", "parse number error! " + paramAttributes);
            }
          }
          if (!"Image".equals(str2)) {
            break label163;
          }
          if (SimplePduDoc.access$300(this$0) == null) {
            break;
          }
          SimplePduDoc.access$300(this$0).getImageRegion().setTop(i);
          return;
          paramAttributes = str1;
          if (str1.endsWith("%")) {
            paramAttributes = str1.substring(0, str1.length() - 1);
          }
        }
      }
    } while ((!"Text".equals(str2)) || (SimplePduDoc.access$300(this$0) == null));
    SimplePduDoc.access$300(this$0).getTextRegion().setTop(i);
  }
  
  public void characters(char[] paramArrayOfChar, int paramInt1, int paramInt2) {}
  
  public void endElement(String paramString1, String paramString2, String paramString3)
    throws SAXException
  {
    if ("par".equals(paramString2))
    {
      mStartPart = false;
      if (mCurrPage != null)
      {
        if (((mCurrPage.getPartSize() != 0) && (!mCurrPage.isEmpty())) || (Build.IS_CM_CUSTOMIZATION_TEST)) {
          SimplePduDoc.access$200(this$0).add(mCurrPage);
        }
        mCurrPage = null;
      }
    }
  }
  
  public ArrayList<SimplePduPart> getRestParts()
  {
    return mPartsRecord;
  }
  
  public boolean isSlideshow()
  {
    return (textCount > 1) || (mediaCount > 1) || (SimplePduDoc.access$200(this$0).size() > 1);
  }
  
  public void startElement(String paramString1, String paramString2, String paramString3, Attributes paramAttributes)
    throws SAXException
  {
    if ("par".equals(paramString2))
    {
      mStartPart = true;
      mCurrPage = new SimplePduPage();
    }
    label185:
    label214:
    do
    {
      int j;
      for (;;)
      {
        return;
        if (!mStartPart) {
          break label214;
        }
        paramString1 = paramAttributes.getValue("src");
        if (paramString1 != null)
        {
          if (paramString1.startsWith("cid:")) {
            paramString1 = paramString1.substring("cid:".length());
          }
          for (paramString1 = (SimplePduPart)SimplePduDoc.access$000(this$0).get(paramString1);; paramString1 = (SimplePduPart)SimplePduDoc.access$100(this$0).get(paramString1))
          {
            if (paramString1 == null) {
              break label185;
            }
            j = paramString1.getAttachmentType();
            if (j == -1) {
              break;
            }
            mCurrPage.addPart(paramString1);
            int i = mPartsRecord.size() - 1;
            while (i >= 0)
            {
              if (Arrays.equals(((SimplePduPart)mPartsRecord.get(i)).getContentLocation(), paramString1.getContentLocation())) {
                mPartsRecord.remove(i);
              }
              i -= 1;
            }
          }
        }
      }
      if (j == 0)
      {
        textCount += 1;
        return;
      }
      mediaCount += 1;
      return;
    } while ((!"region".equals(paramString2)) || ((!Build.IS_CM_CUSTOMIZATION) && (!Build.IS_CU_CUSTOMIZATION)));
    parseRegion(paramAttributes);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SimplePduDoc.SlideHandler
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */