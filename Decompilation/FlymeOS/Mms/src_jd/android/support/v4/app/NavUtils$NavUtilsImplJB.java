package android.support.v4.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;

class NavUtils$NavUtilsImplJB
  extends NavUtils.NavUtilsImplBase
{
  public Intent getParentActivityIntent(Activity paramActivity)
  {
    Intent localIntent2 = NavUtilsJB.getParentActivityIntent(paramActivity);
    Intent localIntent1 = localIntent2;
    if (localIntent2 == null) {
      localIntent1 = superGetParentActivityIntent(paramActivity);
    }
    return localIntent1;
  }
  
  public String getParentActivityName(Context paramContext, ActivityInfo paramActivityInfo)
  {
    String str2 = NavUtilsJB.getParentActivityName(paramActivityInfo);
    String str1 = str2;
    if (str2 == null) {
      str1 = super.getParentActivityName(paramContext, paramActivityInfo);
    }
    return str1;
  }
  
  public void navigateUpTo(Activity paramActivity, Intent paramIntent)
  {
    NavUtilsJB.navigateUpTo(paramActivity, paramIntent);
  }
  
  public boolean shouldUpRecreateTask(Activity paramActivity, Intent paramIntent)
  {
    return NavUtilsJB.shouldUpRecreateTask(paramActivity, paramIntent);
  }
  
  Intent superGetParentActivityIntent(Activity paramActivity)
  {
    return super.getParentActivityIntent(paramActivity);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.NavUtils.NavUtilsImplJB
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */