package com.xiaomi.mms.mx.fw.xmppmsg;

import android.text.TextUtils;
import com.xiaomi.mms.mx.utils.Log;
import java.io.Serializable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HmsExtensionData
  extends ExtensionData
{
  private static final long serialVersionUID = -6972215280115358541L;
  private boolean mIsTemplate;
  private HmsMessageEntry mMessageEntry;
  private String mOwnerID;
  private String mOwnerName;
  private String mRawBody;
  private int mType;
  private String mXMPPMessageString;
  
  private HmsExtensionData(String paramString)
  {
    mExtensionString = paramString;
    try
    {
      parseHmsExtJSON(new JSONObject(paramString));
      return;
    }
    catch (JSONException paramString)
    {
      Log.e("HmsExtensionData", paramString.toString());
    }
  }
  
  public static HmsExtensionData get(String paramString)
  {
    if (!TextUtils.isEmpty(paramString)) {
      return new HmsExtensionData(paramString);
    }
    return null;
  }
  
  private void parseHmsExtJSON(JSONObject paramJSONObject)
    throws JSONException
  {
    mOwnerID = paramJSONObject.getString("owner");
    mType = paramJSONObject.optInt("type");
    mOwnerName = paramJSONObject.optString("name");
    mRawBody = paramJSONObject.optString("body");
    mXMPPMessageString = paramJSONObject.getString("msg");
    mIsTemplate = paramJSONObject.has("template");
    paramJSONObject = paramJSONObject.getJSONArray("msg");
    if ((paramJSONObject != null) && (paramJSONObject.length() > 0)) {
      mMessageEntry = new HmsMessageEntry(paramJSONObject.getJSONObject(0));
    }
  }
  
  public HmsMessageEntry getHmsMessageEntry()
  {
    return mMessageEntry;
  }
  
  public static class HmsMessageEntry
    implements Serializable
  {
    private static final long serialVersionUID = -235423453453411L;
    public String audio;
    public String content;
    public String id;
    public int messageSource;
    public String multiModule;
    public String pic;
    public String source;
    public String title;
    public int type;
    public String url;
    public String video;
    
    public HmsMessageEntry() {}
    
    public HmsMessageEntry(JSONObject paramJSONObject)
    {
      if (paramJSONObject != null)
      {
        id = paramJSONObject.optString("id");
        title = paramJSONObject.optString("title");
        content = paramJSONObject.optString("content");
        source = paramJSONObject.optString("source");
        url = paramJSONObject.optString("url");
        pic = paramJSONObject.optString("pic");
        audio = paramJSONObject.optString("audio");
        video = paramJSONObject.optString("video");
        multiModule = paramJSONObject.optString("module");
        type = paramJSONObject.optInt("type");
        messageSource = paramJSONObject.optInt("sendinitiative");
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.fw.xmppmsg.HmsExtensionData
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */