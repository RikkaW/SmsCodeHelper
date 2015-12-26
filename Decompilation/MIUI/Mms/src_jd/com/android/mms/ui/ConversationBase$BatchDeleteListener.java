package com.android.mms.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.database.DatabaseUtils;
import android.provider.Telephony.Mms;
import android.provider.Telephony.Sms;
import android.text.TextUtils;
import android.view.ActionMode;
import com.android.mms.audio.Mx2DeleteHelper;
import com.android.mms.data.Conversation;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import miui.app.ProgressDialog;

class ConversationBase$BatchDeleteListener
  implements DialogInterface.OnClickListener
{
  public ActionMode mActionMode;
  private boolean mDeleteLockedMessage;
  protected List<MessageItem> mSelectedMsgs;
  
  public ConversationBase$BatchDeleteListener(List<MessageItem> paramList, ActionMode paramActionMode)
  {
    mSelectedMsgs = paramActionMode;
    ActionMode localActionMode;
    mActionMode = localActionMode;
  }
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    Object localObject1 = new HashSet();
    Object localObject2 = new HashSet();
    paramDialogInterface = mSelectedMsgs.iterator();
    Object localObject3;
    while (paramDialogInterface.hasNext())
    {
      localObject3 = (MessageItem)paramDialogInterface.next();
      if ("mms".equals(((MessageItem)localObject3).getType())) {
        ((HashSet)localObject2).add(Long.valueOf(((MessageItem)localObject3).getMsgId()));
      } else if ("sms".equals(((MessageItem)localObject3).getType())) {
        if (this$0.isGroup()) {
          ((HashSet)localObject1).add(Long.valueOf(((MessageItem)localObject3).getDate()));
        } else {
          ((HashSet)localObject1).add(Long.valueOf(((MessageItem)localObject3).getMsgId()));
        }
      }
    }
    if ((mDeleteLockedMessage) || (this$0.isGroup()))
    {
      paramDialogInterface = "";
      this$0.mBatchDeleteTaskCount = 0;
      if (!((HashSet)localObject1).isEmpty()) {
        if (!this$0.isGroup()) {
          break label399;
        }
      }
    }
    label399:
    for (localObject1 = String.format("(%s=%s AND (%s IN (%s)))", new Object[] { "thread_id", Long.valueOf(this$0.mConversation.getThreadId()), "date", TextUtils.join(",", (Iterable)localObject1) });; localObject1 = "_id IN (" + TextUtils.join(",", (Iterable)localObject1) + ")")
    {
      localObject3 = this$0;
      mBatchDeleteTaskCount += 1;
      this$0.mBackgroundQueryHandler.startDelete(9700, null, Telephony.Sms.CONTENT_URI, DatabaseUtils.concatenateWhere(paramDialogInterface, (String)localObject1), null);
      if (!((HashSet)localObject2).isEmpty())
      {
        localObject1 = "_id IN (" + TextUtils.join(",", (Iterable)localObject2) + ")";
        localObject2 = this$0;
        mBatchDeleteTaskCount += 1;
        Mx2DeleteHelper.deleteMms(9700, null, Telephony.Mms.CONTENT_URI, DatabaseUtils.concatenateWhere(paramDialogInterface, (String)localObject1), null, ConversationBase.access$400(this$0), this$0.mMsgListAdapter.getAudioItemCache());
      }
      if (this$0.mBatchDeleteTaskCount > 0) {
        ConversationBase.mBatchDeleteProgressDialog = ProgressDialog.show(this$0, null, this$0.getString(2131362132), true, false);
      }
      mActionMode.finish();
      return;
      paramDialogInterface = "locked = 0";
      break;
    }
  }
  
  public void setDeleteLockedMessage(boolean paramBoolean)
  {
    mDeleteLockedMessage = paramBoolean;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ConversationBase.BatchDeleteListener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */