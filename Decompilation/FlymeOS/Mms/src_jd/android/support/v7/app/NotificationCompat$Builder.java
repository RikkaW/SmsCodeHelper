package android.support.v7.app;

import android.content.Context;
import android.os.Build.VERSION;
import android.support.v4.app.NotificationCompat.BuilderExtender;

public class NotificationCompat$Builder
  extends android.support.v4.app.NotificationCompat.Builder
{
  public NotificationCompat$Builder(Context paramContext)
  {
    super(paramContext);
  }
  
  protected NotificationCompat.BuilderExtender getExtender()
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return new NotificationCompat.LollipopExtender(null);
    }
    if (Build.VERSION.SDK_INT >= 16) {
      return new NotificationCompat.JellybeanExtender(null);
    }
    if (Build.VERSION.SDK_INT >= 14) {
      return new NotificationCompat.IceCreamSandwichExtender(null);
    }
    return super.getExtender();
  }
}

/* Location:
 * Qualified Name:     android.support.v7.app.NotificationCompat.Builder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */