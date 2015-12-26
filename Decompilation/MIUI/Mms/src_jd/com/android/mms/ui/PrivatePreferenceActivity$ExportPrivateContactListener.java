package com.android.mms.ui;

import android.content.AsyncQueryHandler;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.android.mms.data.Contact;
import miui.app.ProgressDialog;

class PrivatePreferenceActivity$ExportPrivateContactListener
  implements DialogInterface.OnClickListener
{
  private final Contact mContact;
  private final Context mContext;
  private final AsyncQueryHandler mHandler;
  private final long mItemId;
  
  public PrivatePreferenceActivity$ExportPrivateContactListener(PrivatePreferenceActivity paramPrivatePreferenceActivity, long paramLong, Contact paramContact, AsyncQueryHandler paramAsyncQueryHandler, Context paramContext)
  {
    mItemId = paramLong;
    mContact = paramContact;
    mContext = paramContext;
    mHandler = paramAsyncQueryHandler;
  }
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    PrivatePreferenceActivity.access$802(this$0, ProgressDialog.show(mContext, null, mContext.getString(2131362226), true, false));
    PrivatePreferenceActivity.access$900(this$0, mHandler, mItemId, mContact.getNumber());
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.PrivatePreferenceActivity.ExportPrivateContactListener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */