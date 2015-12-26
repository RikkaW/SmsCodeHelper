package com.android.mms.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.net.Uri;
import android.net.Uri.Builder;
import android.view.ActionMode;

class BlockedConversationActivity$DeleteMessageListener
  implements DialogInterface.OnClickListener
{
  private final ActionMode mActionMode;
  private final Uri mDeleteUri;
  
  public BlockedConversationActivity$DeleteMessageListener(BlockedConversationActivity paramBlockedConversationActivity, Uri paramUri, long paramLong, boolean paramBoolean, ActionMode paramActionMode)
  {
    mDeleteUri = paramUri.buildUpon().appendQueryParameter("blocked_flag", "1").build();
    mActionMode = paramActionMode;
  }
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    this$0.mBackgroundQueryHandler.startDelete(9701, null, mDeleteUri, null, null);
    paramDialogInterface.dismiss();
    mActionMode.finish();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.BlockedConversationActivity.DeleteMessageListener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */