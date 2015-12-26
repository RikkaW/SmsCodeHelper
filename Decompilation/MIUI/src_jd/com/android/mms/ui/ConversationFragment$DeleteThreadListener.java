package com.android.mms.ui;

import android.content.AsyncQueryHandler;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.android.mms.data.Conversation;
import java.util.Collection;
import miui.app.ProgressDialog;

public class ConversationFragment$DeleteThreadListener
  implements DialogInterface.OnClickListener
{
  private final Context mContext;
  private boolean mDeleteLockedMessages;
  private final AsyncQueryHandler mHandler;
  private final Collection<Long> mThreadIds;
  
  public ConversationFragment$DeleteThreadListener(Collection<Long> paramCollection, AsyncQueryHandler paramAsyncQueryHandler, Context paramContext)
  {
    mThreadIds = paramCollection;
    mHandler = paramAsyncQueryHandler;
    mContext = paramContext;
  }
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    MessageUtils.handleReadReport(mContext, mThreadIds, 129, new Runnable()
    {
      public void run()
      {
        ConversationFragment.access$1302(ProgressDialog.show(mContext, null, mContext.getString(2131362131), true, false));
        if (mThreadIds == null)
        {
          Conversation.startDeleteAll(mHandler, 1801, mDeleteLockedMessages);
          return;
        }
        Conversation.startDelete(mHandler, 1801, mDeleteLockedMessages, mThreadIds);
      }
    });
    paramDialogInterface.dismiss();
  }
  
  public void setDeleteLockedMessage(boolean paramBoolean)
  {
    mDeleteLockedMessages = paramBoolean;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ConversationFragment.DeleteThreadListener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */