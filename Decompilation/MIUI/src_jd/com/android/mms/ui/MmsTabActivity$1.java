package com.android.mms.ui;

import android.os.Handler;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import com.android.mms.util.MiStatSdkHelper;
import miui.view.SearchActionMode;
import miui.view.SearchActionMode.Callback;

class MmsTabActivity$1
  implements SearchActionMode.Callback
{
  MmsTabActivity$1(MmsTabActivity paramMmsTabActivity) {}
  
  public boolean onActionItemClicked(ActionMode paramActionMode, MenuItem paramMenuItem)
  {
    return true;
  }
  
  public boolean onCreateActionMode(ActionMode paramActionMode, Menu paramMenu)
  {
    MiStatSdkHelper.recordSearch("search_create");
    if (MmsTabActivity.access$000(this$0) == null) {
      MmsTabActivity.access$100(this$0, true, false, true);
    }
    MmsTabActivity.access$200(this$0).forceHideHintView();
    paramActionMode = (SearchActionMode)paramActionMode;
    paramActionMode.setAnchorView(MmsTabActivity.access$300(this$0));
    paramActionMode.setAnimateView(MmsTabActivity.access$200(this$0).getView());
    paramActionMode.setResultView(MmsTabActivity.access$000(this$0).getView());
    paramActionMode.getSearchInput().addTextChangedListener(MmsTabActivity.access$400(this$0));
    MmsTabActivity.access$500(this$0, true);
    return true;
  }
  
  public void onDestroyActionMode(ActionMode paramActionMode)
  {
    ((SearchActionMode)paramActionMode).getSearchInput().removeTextChangedListener(MmsTabActivity.access$400(this$0));
    MmsTabActivity.access$602(this$0, null);
    if (MmsTabActivity.access$000(this$0) != null) {
      MmsTabActivity.access$100(this$0, false, true, false);
    }
    new Handler().post(new Runnable()
    {
      public void run()
      {
        MmsTabActivity.access$002(this$0, null);
      }
    });
    MmsTabActivity.access$200(this$0).restoreHintView();
    this$0.onBackPressed();
  }
  
  public boolean onPrepareActionMode(ActionMode paramActionMode, Menu paramMenu)
  {
    return true;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MmsTabActivity.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */