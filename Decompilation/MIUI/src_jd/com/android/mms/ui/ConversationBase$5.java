package com.android.mms.ui;

import android.database.Cursor;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.android.mms.data.Contact;
import com.android.mms.data.ContactList;
import com.android.mms.util.MSimUtils;
import com.xiaomi.mms.transaction.PushSession;
import com.xiaomi.mms.utils.SmsReportUtil;

class ConversationBase$5
  extends AsyncTask<Void, Void, Boolean>
{
  String mMsg = null;
  String mNumber = "";
  int mSimIndex = 0;
  
  ConversationBase$5(ConversationBase paramConversationBase) {}
  
  protected Boolean doInBackground(Void... paramVarArgs)
  {
    if (TextUtils.isEmpty(mMsg))
    {
      mMsg = "null";
      mSimIndex = 0;
    }
    try
    {
      Object localObject2 = PushSession.getInstance(ConversationBase.access$200(this$0));
      Object localObject1 = ((PushSession)localObject2).getMyFullMid(mSimIndex);
      paramVarArgs = (Void[])localObject1;
      if (TextUtils.isEmpty((CharSequence)localObject1)) {
        paramVarArgs = "0";
      }
      localObject1 = paramVarArgs.substring(paramVarArgs.lastIndexOf('/') + 1);
      paramVarArgs = (Void[])localObject1;
      if (TextUtils.isEmpty((CharSequence)localObject1)) {
        paramVarArgs = "";
      }
      localObject2 = ((PushSession)localObject2).getMyMid(mSimIndex);
      localObject1 = localObject2;
      if (TextUtils.isEmpty((CharSequence)localObject2)) {
        localObject1 = "0";
      }
      paramVarArgs = new Boolean(SmsReportUtil.instance().postSms(ConversationBase.access$200(this$0), (String)localObject1, "", paramVarArgs, mNumber, mMsg));
      return paramVarArgs;
    }
    catch (Exception paramVarArgs)
    {
      Log.w("ConversationBase", "Failed to post sms." + paramVarArgs);
    }
    return new Boolean(false);
  }
  
  protected void onPostExecute(Boolean paramBoolean)
  {
    if (paramBoolean.booleanValue())
    {
      Toast.makeText(ConversationBase.access$200(this$0), 2131362386, 0).show();
      return;
    }
    Toast.makeText(ConversationBase.access$200(this$0), 2131362385, 0).show();
  }
  
  protected void onPreExecute()
  {
    Object localObject = this$0.getRecipients();
    if ((localObject != null) && (((ContactList)localObject).size() > 0)) {
      mNumber = ((Contact)((ContactList)localObject).get(0)).getNumber();
    }
    mMsg = null;
    mSimIndex = 0;
    localObject = this$0.mMsgListAdapter.getCursor();
    String str;
    int i;
    if ((localObject != null) && (((Cursor)localObject).getCount() > 0) && (((Cursor)localObject).moveToLast()))
    {
      str = ((Cursor)localObject).getString(0);
      i = -1;
      if (!"sms".equals(str)) {
        break label145;
      }
      i = 4;
    }
    for (;;)
    {
      if ((i != -1) && (36 != -1))
      {
        mMsg = ((Cursor)localObject).getString(i);
        i = MSimUtils.getSlotIdBySimInfoId(((Cursor)localObject).getLong(36));
        if (MSimUtils.isMSimSlotIdValid(i)) {
          mSimIndex = i;
        }
      }
      return;
      label145:
      if ("mms".equals(str)) {
        i = 33;
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ConversationBase.5
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */