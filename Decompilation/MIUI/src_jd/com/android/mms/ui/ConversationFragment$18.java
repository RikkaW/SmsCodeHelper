package com.android.mms.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.android.mms.util.MiStatSdkHelper;

class ConversationFragment$18
  implements View.OnClickListener
{
  ConversationFragment$18(ConversationFragment paramConversationFragment) {}
  
  public void onClick(View paramView)
  {
    ConversationFragment.access$3000(this$0);
    MiStatSdkHelper.recordNetworkAllowRecommend("yellowpage_recommend_goto");
    ConversationFragment.access$700(this$0);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ConversationFragment.18
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */