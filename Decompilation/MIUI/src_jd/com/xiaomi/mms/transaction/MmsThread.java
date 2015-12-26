package com.xiaomi.mms.transaction;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.android.mms.data.Contact;
import com.android.mms.ui.ComposeMessageRouterActivity;
import com.android.mms.ui.MmsTabActivity;

public class MmsThread
  extends MsgThread
{
  public MmsThread(long paramLong, String paramString)
  {
    super(paramLong, paramString);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if ((paramObject == null) || (getClass() != paramObject.getClass())) {
        return false;
      }
      paramObject = (MmsThread)paramObject;
    } while (mThreadId == mThreadId);
    return false;
  }
  
  public Intent getConversationClickIntent(Context paramContext)
  {
    return ComposeMessageRouterActivity.createIntent(paramContext, mThreadId);
  }
  
  public Intent getCvListClickIntent(Context paramContext)
  {
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.setClass(paramContext, MmsTabActivity.class);
    localIntent.setType("vnd.android-dir/mms-sms");
    return localIntent;
  }
  
  public String getPackageName()
  {
    return "com.android.mms";
  }
  
  public Uri getPeoplePreferenceUri(String paramString)
  {
    paramString = Contact.get(paramString).load(true, true);
    if (paramString != null) {
      return paramString.getPeopleReferenceUri();
    }
    return null;
  }
  
  public int hashCode()
  {
    return ("MmsThread" + mThreadId).hashCode();
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.transaction.MmsThread
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */