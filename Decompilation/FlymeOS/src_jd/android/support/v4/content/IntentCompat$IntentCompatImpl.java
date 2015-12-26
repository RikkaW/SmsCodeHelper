package android.support.v4.content;

import android.content.ComponentName;
import android.content.Intent;

abstract interface IntentCompat$IntentCompatImpl
{
  public abstract Intent makeMainActivity(ComponentName paramComponentName);
  
  public abstract Intent makeMainSelectorActivity(String paramString1, String paramString2);
  
  public abstract Intent makeRestartActivityTask(ComponentName paramComponentName);
}

/* Location:
 * Qualified Name:     android.support.v4.content.IntentCompat.IntentCompatImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */