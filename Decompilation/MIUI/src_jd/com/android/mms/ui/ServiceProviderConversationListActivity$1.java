package com.android.mms.ui;

import android.view.View;
import android.view.View.OnClickListener;

class ServiceProviderConversationListActivity$1
  implements View.OnClickListener
{
  ServiceProviderConversationListActivity$1(ServiceProviderConversationListActivity paramServiceProviderConversationListActivity) {}
  
  public void onClick(View paramView)
  {
    if ((ServiceProviderConversationListActivity.access$000() == null) && (ServiceProviderConversationListActivity.access$100(this$0) != null)) {
      new ServiceProviderConversationListActivity.ConfirmMarkAsReadDialogFragment(access$100this$0).mSelectedCategory).show(this$0.getFragmentManager(), "markAsReadDialog");
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ServiceProviderConversationListActivity.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */