package com.android.mms.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.text.TextUtils;
import android.view.ActionMode;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import miui.app.ProgressDialog;

class BlockedConversationActivity$BatchDeleteListener
  implements DialogInterface.OnClickListener
{
  private ActionMode mActionMode;
  private List<MessageItem> mSelectedMsgs;
  
  public BlockedConversationActivity$BatchDeleteListener(List<MessageItem> paramList, ActionMode paramActionMode)
  {
    mSelectedMsgs = paramActionMode;
    ActionMode localActionMode;
    mActionMode = localActionMode;
  }
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    Object localObject1 = new HashSet();
    paramDialogInterface = new HashSet();
    Object localObject2 = mSelectedMsgs.iterator();
    while (((Iterator)localObject2).hasNext())
    {
      MessageItem localMessageItem = (MessageItem)((Iterator)localObject2).next();
      if (localMessageItem.getType().equals("mms")) {
        paramDialogInterface.add(Long.valueOf(localMessageItem.getMsgId()));
      } else if (localMessageItem.getType().equals("sms")) {
        ((HashSet)localObject1).add(Long.valueOf(localMessageItem.getMsgId()));
      }
    }
    this$0.mBatchDeleteTaskCount = 0;
    if (!((HashSet)localObject1).isEmpty())
    {
      localObject1 = "_id IN (" + TextUtils.join(",", (Iterable)localObject1) + ")";
      localObject2 = this$0;
      mBatchDeleteTaskCount += 1;
      this$0.mBackgroundQueryHandler.startDelete(9701, null, BlockedConversationActivity.access$800(), (String)localObject1, null);
    }
    if (!paramDialogInterface.isEmpty())
    {
      paramDialogInterface = "_id IN (" + TextUtils.join(",", paramDialogInterface) + ")";
      localObject1 = this$0;
      mBatchDeleteTaskCount += 1;
      this$0.mBackgroundQueryHandler.startDelete(9701, null, BlockedConversationActivity.access$900(), paramDialogInterface, null);
    }
    if (this$0.mBatchDeleteTaskCount > 0) {
      ConversationBase.mBatchDeleteProgressDialog = ProgressDialog.show(this$0, null, this$0.getString(2131362132), true, false);
    }
    mActionMode.finish();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.BlockedConversationActivity.BatchDeleteListener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */