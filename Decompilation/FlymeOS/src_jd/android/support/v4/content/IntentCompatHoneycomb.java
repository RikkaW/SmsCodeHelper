package android.support.v4.content;

import android.content.ComponentName;
import android.content.Intent;

class IntentCompatHoneycomb
{
  public static Intent makeMainActivity(ComponentName paramComponentName)
  {
    return Intent.makeMainActivity(paramComponentName);
  }
  
  public static Intent makeRestartActivityTask(ComponentName paramComponentName)
  {
    return Intent.makeRestartActivityTask(paramComponentName);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.content.IntentCompatHoneycomb
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */