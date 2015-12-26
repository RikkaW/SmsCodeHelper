package com.android.mms.ui;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.PopupWindow;
import java.util.ArrayList;
import miui.yellowpage.ModuleIntent;

class SingleRecipientConversationActivity$10
  implements AdapterView.OnItemClickListener
{
  SingleRecipientConversationActivity$10(SingleRecipientConversationActivity paramSingleRecipientConversationActivity, ArrayList paramArrayList) {}
  
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    paramAdapterView = (ModuleIntent)val$subMenus.get(paramInt);
    this$0.mPopupMenu.dismiss();
    SingleRecipientConversationActivity.access$400(this$0, paramAdapterView);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SingleRecipientConversationActivity.10
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */