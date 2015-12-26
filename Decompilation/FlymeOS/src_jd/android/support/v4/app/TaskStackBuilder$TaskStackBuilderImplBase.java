package android.support.v4.app;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

class TaskStackBuilder$TaskStackBuilderImplBase
  implements TaskStackBuilder.TaskStackBuilderImpl
{
  public PendingIntent getPendingIntent(Context paramContext, Intent[] paramArrayOfIntent, int paramInt1, int paramInt2, Bundle paramBundle)
  {
    paramArrayOfIntent = new Intent(paramArrayOfIntent[(paramArrayOfIntent.length - 1)]);
    paramArrayOfIntent.addFlags(268435456);
    return PendingIntent.getActivity(paramContext, paramInt1, paramArrayOfIntent, paramInt2);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.TaskStackBuilder.TaskStackBuilderImplBase
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */