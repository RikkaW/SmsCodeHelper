package android.support.v4.app;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;

class NavUtilsJB
{
  public static Intent getParentActivityIntent(Activity paramActivity)
  {
    return paramActivity.getParentActivityIntent();
  }
  
  public static String getParentActivityName(ActivityInfo paramActivityInfo)
  {
    return parentActivityName;
  }
  
  public static void navigateUpTo(Activity paramActivity, Intent paramIntent)
  {
    paramActivity.navigateUpTo(paramIntent);
  }
  
  public static boolean shouldUpRecreateTask(Activity paramActivity, Intent paramIntent)
  {
    return paramActivity.shouldUpRecreateTask(paramIntent);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.NavUtilsJB
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */