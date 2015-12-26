package com.xiaomi.smack.packet;

import android.os.Bundle;
import android.text.TextUtils;
import com.xiaomi.smack.util.StringUtils;

public class Message
  extends Packet
{
  private String fseq = "";
  private String language;
  private String mAppId;
  private String mBody;
  private String mBodyEncoding;
  private boolean mEncrypted = false;
  private String mSubject;
  private boolean mTransient = false;
  private String mseq = "";
  private String seq = "";
  private String status = "";
  private String thread = null;
  private String type = null;
  
  public Message() {}
  
  public Message(Bundle paramBundle)
  {
    super(paramBundle);
    type = paramBundle.getString("ext_msg_type");
    language = paramBundle.getString("ext_msg_lang");
    thread = paramBundle.getString("ext_msg_thread");
    mSubject = paramBundle.getString("ext_msg_sub");
    mBody = paramBundle.getString("ext_msg_body");
    mBodyEncoding = paramBundle.getString("ext_body_encode");
    mAppId = paramBundle.getString("ext_msg_appid");
    mTransient = paramBundle.getBoolean("ext_msg_trans", false);
    mEncrypted = paramBundle.getBoolean("ext_msg_encrypt", false);
    seq = paramBundle.getString("ext_msg_seq");
    mseq = paramBundle.getString("ext_msg_mseq");
    fseq = paramBundle.getString("ext_msg_fseq");
    status = paramBundle.getString("ext_msg_status");
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = true;
    boolean bool3 = false;
    if (this == paramObject) {
      bool1 = true;
    }
    label99:
    label123:
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                do
                {
                  return bool1;
                  bool1 = bool3;
                } while (paramObject == null);
                bool1 = bool3;
              } while (getClass() != paramObject.getClass());
              paramObject = (Message)paramObject;
              bool1 = bool3;
            } while (!super.equals(paramObject));
            if (mBody == null) {
              break;
            }
            bool1 = bool3;
          } while (!mBody.equals(mBody));
          if (language == null) {
            break label171;
          }
          bool1 = bool3;
        } while (!language.equals(language));
        if (mSubject == null) {
          break label180;
        }
        bool1 = bool3;
      } while (!mSubject.equals(mSubject));
      if (thread == null) {
        break label189;
      }
      bool1 = bool3;
    } while (!thread.equals(thread));
    label147:
    if (type == type) {}
    for (boolean bool1 = bool2;; bool1 = false)
    {
      return bool1;
      if (mBody == null) {
        break;
      }
      return false;
      label171:
      if (language == null) {
        break label99;
      }
      return false;
      label180:
      if (mSubject == null) {
        break label123;
      }
      return false;
      label189:
      if (thread == null) {
        break label147;
      }
      return false;
    }
  }
  
  public String getAppId()
  {
    return mAppId;
  }
  
  public String getBody()
  {
    return mBody;
  }
  
  public String getFSeq()
  {
    return fseq;
  }
  
  public String getLanguage()
  {
    return language;
  }
  
  public String getMSeq()
  {
    return mseq;
  }
  
  public String getSeq()
  {
    return seq;
  }
  
  public String getStatus()
  {
    return status;
  }
  
  public String getType()
  {
    return type;
  }
  
  public int hashCode()
  {
    int n = 0;
    int i;
    int j;
    label33:
    int k;
    if (type != null)
    {
      i = type.hashCode();
      if (mBody == null) {
        break label109;
      }
      j = mBody.hashCode();
      if (thread == null) {
        break label114;
      }
      k = thread.hashCode();
      label48:
      if (language == null) {
        break label119;
      }
    }
    label109:
    label114:
    label119:
    for (int m = language.hashCode();; m = 0)
    {
      if (mSubject != null) {
        n = mSubject.hashCode();
      }
      return (((i * 31 + j) * 31 + k) * 31 + m) * 31 + n;
      i = 0;
      break;
      j = 0;
      break label33;
      k = 0;
      break label48;
    }
  }
  
  public void setAppId(String paramString)
  {
    mAppId = paramString;
  }
  
  public void setBody(String paramString)
  {
    mBody = paramString;
  }
  
  public void setBody(String paramString1, String paramString2)
  {
    mBody = paramString1;
    mBodyEncoding = paramString2;
  }
  
  public void setEncrypted(boolean paramBoolean)
  {
    mEncrypted = paramBoolean;
  }
  
  public void setFSeq(String paramString)
  {
    fseq = paramString;
  }
  
  public void setIsTransient(boolean paramBoolean)
  {
    mTransient = paramBoolean;
  }
  
  public void setLanguage(String paramString)
  {
    language = paramString;
  }
  
  public void setMSeq(String paramString)
  {
    mseq = paramString;
  }
  
  public void setSeq(String paramString)
  {
    seq = paramString;
  }
  
  public void setStatus(String paramString)
  {
    status = paramString;
  }
  
  public void setSubject(String paramString)
  {
    mSubject = paramString;
  }
  
  public void setThread(String paramString)
  {
    thread = paramString;
  }
  
  public void setType(String paramString)
  {
    type = paramString;
  }
  
  public Bundle toBundle()
  {
    Bundle localBundle = super.toBundle();
    if (!TextUtils.isEmpty(type)) {
      localBundle.putString("ext_msg_type", type);
    }
    if (language != null) {
      localBundle.putString("ext_msg_lang", language);
    }
    if (mSubject != null) {
      localBundle.putString("ext_msg_sub", mSubject);
    }
    if (mBody != null) {
      localBundle.putString("ext_msg_body", mBody);
    }
    if (!TextUtils.isEmpty(mBodyEncoding)) {
      localBundle.putString("ext_body_encode", mBodyEncoding);
    }
    if (thread != null) {
      localBundle.putString("ext_msg_thread", thread);
    }
    if (mAppId != null) {
      localBundle.putString("ext_msg_appid", mAppId);
    }
    if (mTransient) {
      localBundle.putBoolean("ext_msg_trans", true);
    }
    if (!TextUtils.isEmpty(seq)) {
      localBundle.putString("ext_msg_seq", seq);
    }
    if (!TextUtils.isEmpty(mseq)) {
      localBundle.putString("ext_msg_mseq", mseq);
    }
    if (!TextUtils.isEmpty(fseq)) {
      localBundle.putString("ext_msg_fseq", fseq);
    }
    if (mEncrypted) {
      localBundle.putBoolean("ext_msg_encrypt", true);
    }
    if (!TextUtils.isEmpty(status)) {
      localBundle.putString("ext_msg_status", status);
    }
    return localBundle;
  }
  
  public String toXML()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("<message");
    if (getXmlns() != null) {
      localStringBuilder.append(" xmlns=\"").append(getXmlns()).append("\"");
    }
    if (language != null) {
      localStringBuilder.append(" xml:lang=\"").append(getLanguage()).append("\"");
    }
    if (getPacketID() != null) {
      localStringBuilder.append(" id=\"").append(getPacketID()).append("\"");
    }
    if (getTo() != null) {
      localStringBuilder.append(" to=\"").append(StringUtils.escapeForXML(getTo())).append("\"");
    }
    if (!TextUtils.isEmpty(getSeq())) {
      localStringBuilder.append(" seq=\"").append(getSeq()).append("\"");
    }
    if (!TextUtils.isEmpty(getMSeq())) {
      localStringBuilder.append(" mseq=\"").append(getMSeq()).append("\"");
    }
    if (!TextUtils.isEmpty(getFSeq())) {
      localStringBuilder.append(" fseq=\"").append(getFSeq()).append("\"");
    }
    if (!TextUtils.isEmpty(getStatus())) {
      localStringBuilder.append(" status=\"").append(getStatus()).append("\"");
    }
    if (getFrom() != null) {
      localStringBuilder.append(" from=\"").append(StringUtils.escapeForXML(getFrom())).append("\"");
    }
    if (getChannelId() != null) {
      localStringBuilder.append(" chid=\"").append(StringUtils.escapeForXML(getChannelId())).append("\"");
    }
    if (mTransient) {
      localStringBuilder.append(" transient=\"true\"");
    }
    if (!TextUtils.isEmpty(mAppId)) {
      localStringBuilder.append(" appid=\"").append(getAppId()).append("\"");
    }
    if (!TextUtils.isEmpty(type)) {
      localStringBuilder.append(" type=\"").append(type).append("\"");
    }
    if (mEncrypted) {
      localStringBuilder.append(" s=\"1\"");
    }
    localStringBuilder.append(">");
    if (mSubject != null)
    {
      localStringBuilder.append("<subject>").append(StringUtils.escapeForXML(mSubject));
      localStringBuilder.append("</subject>");
    }
    if (mBody != null)
    {
      localStringBuilder.append("<body");
      if (!TextUtils.isEmpty(mBodyEncoding)) {
        localStringBuilder.append(" encode=\"").append(mBodyEncoding).append("\"");
      }
      localStringBuilder.append(">").append(StringUtils.escapeForXML(mBody)).append("</body>");
    }
    if (thread != null) {
      localStringBuilder.append("<thread>").append(thread).append("</thread>");
    }
    if ("error".equalsIgnoreCase(type))
    {
      XMPPError localXMPPError = getError();
      if (localXMPPError != null) {
        localStringBuilder.append(localXMPPError.toXML());
      }
    }
    localStringBuilder.append(getExtensionsXML());
    localStringBuilder.append("</message>");
    return localStringBuilder.toString();
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.smack.packet.Message
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */