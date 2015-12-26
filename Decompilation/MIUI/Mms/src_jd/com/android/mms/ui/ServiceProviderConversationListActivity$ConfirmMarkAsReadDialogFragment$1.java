package com.android.mms.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.AsyncTask;
import com.android.mms.MmsApp;
import com.android.mms.data.Conversation;

class ServiceProviderConversationListActivity$ConfirmMarkAsReadDialogFragment$1
  implements DialogInterface.OnClickListener
{
  ServiceProviderConversationListActivity$ConfirmMarkAsReadDialogFragment$1(ServiceProviderConversationListActivity.ConfirmMarkAsReadDialogFragment paramConfirmMarkAsReadDialogFragment) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    ServiceProviderConversationListActivity.access$002(new AsyncTask()
    {
      protected Void doInBackground(Void... paramAnonymousVarArgs)
      {
        Conversation.markSPAsReadSync(MmsApp.getApp(), ServiceProviderConversationListActivity.ConfirmMarkAsReadDialogFragment.access$400(this$0));
        return null;
      }
      
      protected void onPostExecute(Void paramAnonymousVoid)
      {
        ServiceProviderConversationListActivity.access$300();
        ServiceProviderConversationListActivity.access$002(null);
      }
      
      protected void onPreExecute()
      {
        ServiceProviderConversationListActivity.access$200(this$0.getActivity(), this$0.getFragmentManager());
      }
    });
    ServiceProviderConversationListActivity.access$000().execute(new Void[] { (Void)null });
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ServiceProviderConversationListActivity.ConfirmMarkAsReadDialogFragment.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */