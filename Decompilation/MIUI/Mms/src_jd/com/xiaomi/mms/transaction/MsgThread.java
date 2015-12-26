package com.xiaomi.mms.transaction;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.android.mms.data.Contact;

public abstract class MsgThread
{
  public String mAddress;
  public long mThreadId;
  
  public MsgThread(long paramLong, String paramString)
  {
    mThreadId = paramLong;
    mAddress = paramString;
  }
  
  public abstract Intent getConversationClickIntent(Context paramContext);
  
  public abstract Intent getCvListClickIntent(Context paramContext);
  
  public abstract String getPackageName();
  
  public abstract Uri getPeoplePreferenceUri(String paramString);
  
  public String getSenderName(Context paramContext, String paramString)
  {
    paramContext = Contact.get(paramString).load(true, true).getName();
    if (paramContext == null) {
      return "";
    }
    return paramContext.replace('\n', ' ').replace('\r', ' ');
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.transaction.MsgThread
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */