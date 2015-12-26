package com.meizu.common.widget;

import android.util.Log;
import android.view.View;

class SlidingMenu$2
  implements Runnable
{
  SlidingMenu$2(SlidingMenu paramSlidingMenu, int paramInt) {}
  
  public void run()
  {
    StringBuilder localStringBuilder = new StringBuilder().append("changing layerType. hardware? ");
    if (val$layerType == 2) {}
    for (boolean bool = true;; bool = false)
    {
      Log.v("SlidingMenu", bool);
      this$0.getContent().setLayerType(val$layerType, null);
      this$0.getMenu().setLayerType(val$layerType, null);
      if (this$0.getSecondaryMenu() != null) {
        this$0.getSecondaryMenu().setLayerType(val$layerType, null);
      }
      return;
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.SlidingMenu.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */