package com.xiaomi.mms.mx.fw.xmppmsg;

import java.io.Serializable;
import org.json.JSONObject;

public class HmsExtensionData$HmsMessageEntry
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
  
  public HmsExtensionData$HmsMessageEntry() {}
  
  public HmsExtensionData$HmsMessageEntry(JSONObject paramJSONObject)
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

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.fw.xmppmsg.HmsExtensionData.HmsMessageEntry
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */