package com.android.mms.ui;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.text.TextUtils;
import android.view.ActionMode;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

class BlockedConversationActivity$RestoreListener
  implements DialogInterface.OnClickListener
{
  private ActionMode mActionMode;
  private boolean mChecked;
  private List<MessageItem> mSelectedMsgs;
  
  public BlockedConversationActivity$RestoreListener(List<MessageItem> paramList, ActionMode paramActionMode)
  {
    mSelectedMsgs = paramActionMode;
    ActionMode localActionMode;
    mActionMode = localActionMode;
    mChecked = false;
  }
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    if (mChecked) {
      BlockedConversationActivity.access$200(this$0, BlockedConversationActivity.access$100(this$0));
    }
    for (;;)
    {
      mActionMode.finish();
      return;
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
      localObject2 = new ContentValues();
      ((ContentValues)localObject2).put("block_type", Integer.valueOf(0));
      if (!((HashSet)localObject1).isEmpty())
      {
        localObject1 = "_id IN (" + TextUtils.join(",", (Iterable)localObject1) + ")";
        this$0.mBackgroundQueryHandler.startUpdate(9702, null, BlockedConversationActivity.access$800(), (ContentValues)localObject2, (String)localObject1, null);
      }
      if (!paramDialogInterface.isEmpty())
      {
        paramDialogInterface = "_id IN (" + TextUtils.join(",", paramDialogInterface) + ")";
        this$0.mBackgroundQueryHandler.startUpdate(9702, null, BlockedConversationActivity.access$900(), (ContentValues)localObject2, paramDialogInterface, null);
      }
    }
  }
  
  public void setUnblockChecked(boolean paramBoolean)
  {
    mChecked = paramBoolean;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.BlockedConversationActivity.RestoreListener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */