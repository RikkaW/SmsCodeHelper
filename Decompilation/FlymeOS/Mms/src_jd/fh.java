import android.content.Context;
import android.content.Intent;

public class fh
{
  private static boolean a = false;
  
  public static void a(Context paramContext)
  {
    if (a)
    {
      Intent localIntent = new Intent("com.android.common.speech.LOG_EVENT");
      localIntent.putExtra("app_name", "voiceime");
      localIntent.putExtra("extra_event", 21);
      localIntent.putExtra("", paramContext.getPackageName());
      localIntent.putExtra("timestamp", System.currentTimeMillis());
      paramContext.sendBroadcast(localIntent);
      a(false);
    }
  }
  
  public static void a(boolean paramBoolean)
  {
    a = paramBoolean;
  }
}

/* Location:
 * Qualified Name:     fh
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */