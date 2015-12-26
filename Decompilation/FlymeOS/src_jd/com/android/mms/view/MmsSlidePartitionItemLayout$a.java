package com.android.mms.view;

import android.util.Log;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.Menu;
import android.view.MenuItem;
import com.android.mms.ui.MeizuSlideshowActivity;
import vp.a;
import wd;

class MmsSlidePartitionItemLayout$a
  implements ActionMode.Callback
{
  private vp.a b;
  
  public MmsSlidePartitionItemLayout$a(MmsSlidePartitionItemLayout paramMmsSlidePartitionItemLayout, vp.a parama)
  {
    b = parama;
  }
  
  public boolean onActionItemClicked(ActionMode paramActionMode, MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    default: 
      return true;
    case 2: 
      if (b.e)
      {
        wd.a(MmsSlidePartitionItemLayout.c(a), b.c, MmsSlidePartitionItemLayout.d(a));
        return true;
      }
      wd.a(2131493296, MmsSlidePartitionItemLayout.c(a), 0, 1, true, 0);
      return true;
    }
    if (b.e)
    {
      ((MeizuSlideshowActivity)MmsSlidePartitionItemLayout.c(a)).a(b.c);
      return true;
    }
    wd.a(2131493296, MmsSlidePartitionItemLayout.c(a), 0, 1, true, 0);
    return true;
  }
  
  public boolean onCreateActionMode(ActionMode paramActionMode, Menu paramMenu)
  {
    if (b == null)
    {
      Log.e("MmsSlidePartitionItemLayout", "onCreateActionMode(), mPressData is null...");
      return true;
    }
    if (("img".equals(b.a)) || ("video".equals(b.a)))
    {
      paramMenu.add(2, 2, 0, 2131493360);
      paramMenu.add(3, 3, 0, 2131493361);
      return true;
    }
    paramMenu.add(3, 3, 0, 2131493361);
    return true;
  }
  
  public void onDestroyActionMode(ActionMode paramActionMode) {}
  
  public boolean onPrepareActionMode(ActionMode paramActionMode, Menu paramMenu)
  {
    return true;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.view.MmsSlidePartitionItemLayout.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */