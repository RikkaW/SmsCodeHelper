package com.xiaomi.mms.mx.utils;

import android.text.TextUtils;
import com.xiaomi.mms.mx.data.Attachment;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Mx2ExtentionHelper
{
  public static String convertServerExtensionToLocal(String paramString)
  {
    String str = null;
    if (!TextUtils.isEmpty(paramString)) {}
    try
    {
      paramString = new JSONArray(paramString);
      int i = 0;
      while (i < paramString.length())
      {
        paramString.getJSONObject(i).put("attId", AttachmentUtils.generateAttachmentId());
        i += 1;
      }
      str = paramString.toString();
      return str;
    }
    catch (JSONException paramString)
    {
      Log.v("Mx2ExtentionHelper", "convertReceivedExtensionStringToLocal Exception " + paramString);
    }
    return null;
  }
  
  public static String generateAttachmentsExtentionString(List<Attachment> paramList, boolean paramBoolean)
  {
    Object localObject = null;
    if (paramList != null)
    {
      localObject = new JSONArray();
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        Attachment localAttachment = (Attachment)paramList.next();
        if (paramBoolean) {
          ((JSONArray)localObject).put(localAttachment.generateServerJSONObject());
        } else {
          ((JSONArray)localObject).put(localAttachment.generateLocalJSONObject());
        }
      }
      localObject = ((JSONArray)localObject).toString();
    }
    return (String)localObject;
  }
  
  public static ArrayList<Attachment> praseAttachmentsExtentionString(String paramString)
  {
    localObject = null;
    if (!TextUtils.isEmpty(paramString))
    {
      ArrayList localArrayList = new ArrayList();
      try
      {
        paramString = new JSONArray(paramString);
        int i = 0;
        for (;;)
        {
          localObject = localArrayList;
          if (i >= paramString.length()) {
            break;
          }
          localArrayList.add(new Attachment(paramString.getJSONObject(i)));
          i += 1;
        }
        return (ArrayList<Attachment>)localObject;
      }
      catch (JSONException paramString)
      {
        Log.v("Mx2ExtentionHelper", "praseAttachmentsExtentionString Exception " + paramString);
        localObject = localArrayList;
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.utils.Mx2ExtentionHelper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */