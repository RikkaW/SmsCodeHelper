package com.android.mms.ui;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.database.Cursor;
import android.provider.MiuiSettings.AntiSpam;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import com.android.mms.data.Conversation;
import miui.widget.ScrollableViewDrawer;

class ConversationFragment$11
  implements AdapterView.OnItemClickListener
{
  ConversationFragment$11(ConversationFragment paramConversationFragment) {}
  
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    if (paramInt == 1)
    {
      if ((this$0.mRootView instanceof ScrollableViewDrawer)) {
        ((ScrollableViewDrawer)this$0.mRootView).resetState();
      }
      ((MmsTabActivity)this$0.mActivity).startSearchMode(this$0.mSearchView, this$0.mRootView);
    }
    do
    {
      return;
      if ((paramInt == 2) && (this$0.mBookmarkVisible))
      {
        paramAdapterView = new Intent(this$0.mActivity, BookmarkActivity.class);
        this$0.startActivity(paramAdapterView);
        return;
      }
    } while (paramInt == this$0.mListView.getAdapter().getCount() - 1);
    paramAdapterView = (Cursor)this$0.mListView.getItemAtPosition(paramInt);
    paramAdapterView = Conversation.from(this$0.mActivity, paramAdapterView);
    paramLong = paramAdapterView.getThreadId();
    if (paramLong == -100L)
    {
      paramAdapterView = new Intent(MiuiSettings.AntiSpam.ACTION_SOURCE_SMS);
      paramAdapterView.setComponent(new ComponentName("com.miui.antispam", "com.miui.antispam.ui.activity.MainActivity"));
      this$0.mActivity.startActivity(paramAdapterView);
      return;
    }
    if (paramLong == -101L)
    {
      paramAdapterView = new Intent();
      paramAdapterView.setAction("com.miui.mipub.action_open_threadlist");
      paramAdapterView.setPackage("com.miui.mipub");
      this$0.startActivity(paramAdapterView);
      return;
    }
    Log.d("ConversationFragment", "onListItemClick: pos=" + paramInt + ", view=" + paramView + ", tid=" + paramLong);
    if ((this$0.mIsCompositeMode) && (paramAdapterView.isServiceProvider()) && (!paramAdapterView.isSticky()))
    {
      paramAdapterView = new Intent(this$0.mActivity, ServiceProviderConversationListActivity.class);
      this$0.startActivity(paramAdapterView);
      return;
    }
    paramAdapterView = ComposeMessageRouterActivity.createIntent(this$0.mActivity, paramLong);
    this$0.startActivity(paramAdapterView);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ConversationFragment.11
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */