package com.android.mms.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.android.mms.util.MiStatSdkHelper;

class ConversationFragment$19
  implements View.OnClickListener
{
  ConversationFragment$19(ConversationFragment paramConversationFragment) {}
  
  public void onClick(View paramView)
  {
    ConversationFragment.access$3000(this$0);
    MiStatSdkHelper.recordNetworkAllowRecommend("yellowpage_recommend_close");
    MessageUtils.setNetworkRecommendDate(this$0.mActivity, Long.valueOf(System.currentTimeMillis()));
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ConversationFragment.19
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */