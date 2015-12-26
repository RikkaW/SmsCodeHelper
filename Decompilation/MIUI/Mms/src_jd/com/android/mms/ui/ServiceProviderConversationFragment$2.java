package com.android.mms.ui;

import android.widget.ImageView;
import miui.R.drawable;
import miui.widget.DropDownSingleChoiceMenu;
import miui.widget.DropDownSingleChoiceMenu.OnMenuListener;

class ServiceProviderConversationFragment$2
  implements DropDownSingleChoiceMenu.OnMenuListener
{
  ServiceProviderConversationFragment$2(ServiceProviderConversationFragment paramServiceProviderConversationFragment) {}
  
  public void onDismiss()
  {
    ServiceProviderConversationFragment.access$200(this$0).setImageResource(R.drawable.expander_open_light);
  }
  
  public void onItemSelected(DropDownSingleChoiceMenu paramDropDownSingleChoiceMenu, int paramInt)
  {
    ServiceProviderConversationFragment.access$300(this$0, paramInt);
  }
  
  public void onShow() {}
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ServiceProviderConversationFragment.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */