package com.android.mms.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.android.mms.util.MiStatSdkHelper;

class ConversationFragment$21
  implements DialogInterface.OnClickListener
{
  ConversationFragment$21(ConversationFragment paramConversationFragment) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    MessageUtils.setYpRecommendEnabled(this$0.mActivity, true);
    MessageUtils.setNetworkRecommendDate(this$0.mActivity, Long.valueOf(System.currentTimeMillis()));
    MiStatSdkHelper.recordNetworkAllowRecommend("network_recommend_close");
    ConversationFragment.access$800(this$0);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ConversationFragment.21
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */