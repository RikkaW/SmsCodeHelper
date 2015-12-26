package com.meizu.common.preference;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

class SwitchPreference$Listener
  implements CompoundButton.OnCheckedChangeListener
{
  private SwitchPreference$Listener(SwitchPreference paramSwitchPreference) {}
  
  public void onCheckedChanged(CompoundButton paramCompoundButton, boolean paramBoolean)
  {
    if (!SwitchPreference.access$100(this$0, Boolean.valueOf(paramBoolean)))
    {
      if (!paramBoolean) {}
      for (paramBoolean = true;; paramBoolean = false)
      {
        paramCompoundButton.setChecked(paramBoolean);
        return;
      }
    }
    this$0.setChecked(paramBoolean);
    SwitchPreference.access$200(this$0);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.preference.SwitchPreference.Listener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */