package com.meizu.common.util;

import android.app.ActionBar;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build.VERSION;
import com.meizu.common.R.drawable;

class ActionBarProxy$1
  implements ActionBarProxy.OnBackButtonEnableChangeListener
{
  ActionBarProxy$1(ActionBarProxy paramActionBarProxy) {}
  
  public void onBackButtonEnableChange(boolean paramBoolean)
  {
    boolean bool2 = true;
    if (access$000this$0).getThemedContext().getResources().getConfiguration().orientation == 2) {
      paramBoolean = true;
    }
    Object localObject = ActionBarProxy.access$000(this$0);
    boolean bool1;
    if (!paramBoolean)
    {
      bool1 = true;
      ((ActionBar)localObject).setDisplayHomeAsUpEnabled(bool1);
      localObject = ActionBarProxy.access$000(this$0);
      if (paramBoolean) {
        break label160;
      }
      bool1 = true;
      label63:
      ((ActionBar)localObject).setHomeButtonEnabled(bool1);
      if (paramBoolean) {
        break label165;
      }
      if (Build.VERSION.SDK_INT >= 18) {
        ActionBarProxy.access$100(this$0, R.drawable.mz_ic_ab_back_top);
      }
      label91:
      if ((ActionBarProxy.access$200(this$0) != null) && (ActionBarProxy.access$300(this$0) == paramBoolean))
      {
        localObject = ActionBarProxy.access$200(this$0);
        if (paramBoolean) {
          break label189;
        }
        bool1 = true;
        label127:
        ((ActionBarProxy.OnTopBackButtonEnableChangeListener)localObject).onTopBackButtonEnableChange(bool1);
      }
      localObject = this$0;
      if (paramBoolean) {
        break label194;
      }
    }
    label160:
    label165:
    label189:
    label194:
    for (paramBoolean = bool2;; paramBoolean = false)
    {
      ActionBarProxy.access$302((ActionBarProxy)localObject, paramBoolean);
      return;
      bool1 = false;
      break;
      bool1 = false;
      break label63;
      if (Build.VERSION.SDK_INT < 18) {
        break label91;
      }
      ActionBarProxy.access$000(this$0).setHomeAsUpIndicator(R.drawable.mz_ic_ab_back_transparent);
      break label91;
      bool1 = false;
      break label127;
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.util.ActionBarProxy.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */