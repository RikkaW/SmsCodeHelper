package com.xiaomi.mms.mx.data;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import com.xiaomi.mms.mx.utils.AttachmentUtils;
import com.xiaomi.mms.mx.utils.Log;
import java.io.File;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public class Attachment
  implements Serializable
{
  public long attId;
  public long datasize;
  public long expireAt;
  public String filename;
  public int height = 0;
  public String link;
  public boolean mIsRead = false;
  public String mimeType;
  public int playTime;
  public String shareId;
  public int width = 0;
  
  public Attachment() {}
  
  public Attachment(JSONObject paramJSONObject)
    throws JSONException
  {
    if (paramJSONObject != null)
    {
      attId = paramJSONObject.optLong("attId");
      mimeType = paramJSONObject.optString("mime_type", null);
      filename = paramJSONObject.optString("filename", null);
      if ((TextUtils.isEmpty(mimeType)) && (!TextUtils.isEmpty(filename))) {
        mimeType = getMimeType(filename);
      }
      datasize = paramJSONObject.optLong("filesize", 0L);
      playTime = paramJSONObject.optInt("play_time");
      width = paramJSONObject.optInt("width", 0);
      height = paramJSONObject.optInt("height", 0);
      shareId = paramJSONObject.optString("share_id", null);
      expireAt = paramJSONObject.optLong("expires", 0L);
      link = paramJSONObject.optString("link", null);
      if (paramJSONObject.optInt("is_read", 0) != 0) {
        break label180;
      }
    }
    for (;;)
    {
      mIsRead = bool;
      return;
      label180:
      bool = true;
    }
  }
  
  private File getFilebyKey(Context paramContext, String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
    {
      paramContext = AttachmentUtils.getCachedImagePath(paramContext, paramString);
      if (!TextUtils.isEmpty(paramContext))
      {
        paramContext = new File(paramContext);
        if (paramContext.exists()) {
          return paramContext;
        }
      }
    }
    return null;
  }
  
  public static String getMimeType(String paramString)
  {
    String str = "";
    int i = paramString.lastIndexOf('.');
    if (i > 0)
    {
      paramString = paramString.substring(i + 1);
      str = MimeTypeMap.getSingleton().getMimeTypeFromExtension(paramString);
    }
    return str;
  }
  
  public JSONObject generateJSONObject(boolean paramBoolean)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      if (!TextUtils.isEmpty(mimeType)) {
        localJSONObject.put("mime_type", mimeType);
      }
      if (!paramBoolean)
      {
        localJSONObject.put("filename", filename);
        localJSONObject.put("attId", attId);
      }
      localJSONObject.put("filesize", datasize);
      if (!TextUtils.isEmpty(shareId)) {
        localJSONObject.put("share_id", shareId);
      }
      if (!TextUtils.isEmpty(link)) {
        localJSONObject.put("link", link);
      }
      if (playTime > 0) {
        localJSONObject.put("play_time", playTime);
      }
      if (width > 0) {
        localJSONObject.put("width", width);
      }
      if (height > 0) {
        localJSONObject.put("height", height);
      }
      if (mIsRead) {}
      for (int i = 1;; i = 0)
      {
        localJSONObject.put("is_read", i);
        return localJSONObject;
      }
      return null;
    }
    catch (JSONException localJSONException)
    {
      Log.e("Attachment", "generateJSONObject exception :" + localJSONException);
    }
  }
  
  public JSONObject generateLocalJSONObject()
  {
    return generateJSONObject(false);
  }
  
  public JSONObject generateServerJSONObject()
  {
    return generateJSONObject(true);
  }
  
  public File getFile(Context paramContext, String paramString)
  {
    File localFile = null;
    switch (AttachmentUtils.getMessageTypeFromMimeType(mimeType))
    {
    default: 
      paramString = localFile;
    case 2: 
      do
      {
        return paramString;
        localFile = getFilebyKey(paramContext, filename);
        paramString = localFile;
      } while (localFile != null);
      return getFilebyKey(paramContext, shareId);
    }
    return new File(paramString + "/" + filename);
  }
  
  public String getLocalPath(Context paramContext, String paramString)
  {
    switch (AttachmentUtils.getMessageTypeFromMimeType(mimeType))
    {
    default: 
      return null;
    case 2: 
      return AttachmentUtils.getCachedImagePath(paramContext, filename);
    }
    return paramString + "/" + filename;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.data.Attachment
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */