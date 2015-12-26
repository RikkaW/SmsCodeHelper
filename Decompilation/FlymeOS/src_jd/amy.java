import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import com.meizu.update.UpdateInfo;
import com.meizu.update.service.MzUpdateComponentService;

public class amy
{
  public static Intent a(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      paramContext = localPackageManager.getLaunchIntentForPackage(paramContext.getPackageName());
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public static void a(Context paramContext, UpdateInfo paramUpdateInfo)
  {
    try
    {
      paramUpdateInfo = MzUpdateComponentService.g(paramContext, paramUpdateInfo);
      Intent localIntent = new Intent("com.meizu.appupdate.intent.wakeup");
      localIntent.putExtra("PendingIntent", paramUpdateInfo);
      localIntent.setPackage("com.meizu.cloud");
      paramContext.startService(localIntent);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
}

/* Location:
 * Qualified Name:     amy
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */