package com.xiaomi.mms.mx.fw.fdata;

import android.text.TextUtils;
import com.xiaomi.mms.mx.utils.Log;
import org.json.JSONException;
import org.json.JSONObject;

public class HmsConversationInfo
{
  private long mMaxSeq = 0L;
  private boolean mNotifyDisabled = false;
  private long mReadSeq = 0L;
  private String mVerifyInfo = "";
  
  public HmsConversationInfo() {}
  
  public HmsConversationInfo(String paramString)
  {
    updateWithJSON(paramString);
  }
  
  public long getReadSeq()
  {
    return mReadSeq;
  }
  
  public void setReadSeq(long paramLong)
  {
    mReadSeq = paramLong;
  }
  
  public String toJson()
  {
    try
    {
      Object localObject = new JSONObject();
      ((JSONObject)localObject).put("maxSeq", mMaxSeq);
      ((JSONObject)localObject).put("readSeq", mReadSeq);
      ((JSONObject)localObject).put("verifyInfo", mVerifyInfo);
      ((JSONObject)localObject).put("notifyDisabled", mNotifyDisabled);
      localObject = ((JSONObject)localObject).toString();
      return (String)localObject;
    }
    catch (JSONException localJSONException)
    {
      Log.e("HmsDetailInfo", localJSONException.toString());
    }
    return "";
  }
  
  public void updateWithJSON(String paramString)
  {
    if (!TextUtils.isEmpty(paramString)) {
      try
      {
        paramString = new JSONObject(paramString);
        mMaxSeq = paramString.optLong("maxSeq");
        mReadSeq = paramString.optLong("readSeq");
        mVerifyInfo = paramString.optString("verifyInfo");
        mNotifyDisabled = paramString.optBoolean("notifyDisabled");
        return;
      }
      catch (JSONException paramString)
      {
        Log.e("HmsDetailInfo", paramString.toString());
        return;
      }
    }
    mMaxSeq = 0L;
    mReadSeq = 0L;
    mNotifyDisabled = false;
    mVerifyInfo = "";
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.fw.fdata.HmsConversationInfo
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */