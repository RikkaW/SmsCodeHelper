package android.support.v4.view;

import android.view.ViewConfiguration;

class ViewConfigurationCompat$BaseViewConfigurationVersionImpl
  implements ViewConfigurationCompat.ViewConfigurationVersionImpl
{
  public int getScaledPagingTouchSlop(ViewConfiguration paramViewConfiguration)
  {
    return paramViewConfiguration.getScaledTouchSlop();
  }
  
  public boolean hasPermanentMenuKey(ViewConfiguration paramViewConfiguration)
  {
    return true;
  }
}

/* Location:
 * Qualified Name:     android.support.v4.view.ViewConfigurationCompat.BaseViewConfigurationVersionImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */