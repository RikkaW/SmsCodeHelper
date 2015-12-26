package com.android.mms.ui;

import android.os.AsyncTask;
import com.android.mms.MmsApp;
import com.android.mms.data.Conversation;

class ServiceProviderConversationListActivity$ConfirmMarkAsReadDialogFragment$1$1
  extends AsyncTask<Void, Void, Void>
{
  ServiceProviderConversationListActivity$ConfirmMarkAsReadDialogFragment$1$1(ServiceProviderConversationListActivity.ConfirmMarkAsReadDialogFragment.1 param1) {}
  
  protected Void doInBackground(Void... paramVarArgs)
  {
    Conversation.markSPAsReadSync(MmsApp.getApp(), ServiceProviderConversationListActivity.ConfirmMarkAsReadDialogFragment.access$400(this$1.this$0));
    return null;
  }
  
  protected void onPostExecute(Void paramVoid)
  {
    ServiceProviderConversationListActivity.access$300();
    ServiceProviderConversationListActivity.access$002(null);
  }
  
  protected void onPreExecute()
  {
    ServiceProviderConversationListActivity.access$200(this$1.this$0.getActivity(), this$1.this$0.getFragmentManager());
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ServiceProviderConversationListActivity.ConfirmMarkAsReadDialogFragment.1.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */