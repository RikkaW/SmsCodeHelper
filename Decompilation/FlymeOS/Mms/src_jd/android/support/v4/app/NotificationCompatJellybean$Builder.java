package android.support.v4.app;

import android.app.Notification;
import android.app.Notification.Builder;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.SparseArray;
import android.widget.RemoteViews;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class NotificationCompatJellybean$Builder
  implements NotificationBuilderWithActions, NotificationBuilderWithBuilderAccessor
{
  private Notification.Builder b;
  private List<Bundle> mActionExtrasList = new ArrayList();
  private final Bundle mExtras;
  
  public NotificationCompatJellybean$Builder(Context paramContext, Notification paramNotification, CharSequence paramCharSequence1, CharSequence paramCharSequence2, CharSequence paramCharSequence3, RemoteViews paramRemoteViews, int paramInt1, PendingIntent paramPendingIntent1, PendingIntent paramPendingIntent2, Bitmap paramBitmap, int paramInt2, int paramInt3, boolean paramBoolean1, boolean paramBoolean2, int paramInt4, CharSequence paramCharSequence4, boolean paramBoolean3, Bundle paramBundle, String paramString1, boolean paramBoolean4, String paramString2)
  {
    paramContext = new Notification.Builder(paramContext).setWhen(when).setSmallIcon(icon, iconLevel).setContent(contentView).setTicker(tickerText, paramRemoteViews).setSound(sound, audioStreamType).setVibrate(vibrate).setLights(ledARGB, ledOnMS, ledOffMS);
    boolean bool;
    if ((flags & 0x2) != 0)
    {
      bool = true;
      paramContext = paramContext.setOngoing(bool);
      if ((flags & 0x8) == 0) {
        break label337;
      }
      bool = true;
      label123:
      paramContext = paramContext.setOnlyAlertOnce(bool);
      if ((flags & 0x10) == 0) {
        break label343;
      }
      bool = true;
      label143:
      paramContext = paramContext.setAutoCancel(bool).setDefaults(defaults).setContentTitle(paramCharSequence1).setContentText(paramCharSequence2).setSubText(paramCharSequence4).setContentInfo(paramCharSequence3).setContentIntent(paramPendingIntent1).setDeleteIntent(deleteIntent);
      if ((flags & 0x80) == 0) {
        break label349;
      }
      bool = true;
      label202:
      b = paramContext.setFullScreenIntent(paramPendingIntent2, bool).setLargeIcon(paramBitmap).setNumber(paramInt1).setUsesChronometer(paramBoolean2).setPriority(paramInt4).setProgress(paramInt2, paramInt3, paramBoolean1);
      mExtras = new Bundle();
      if (paramBundle != null) {
        mExtras.putAll(paramBundle);
      }
      if (paramBoolean3) {
        mExtras.putBoolean("android.support.localOnly", true);
      }
      if (paramString1 != null)
      {
        mExtras.putString("android.support.groupKey", paramString1);
        if (!paramBoolean4) {
          break label355;
        }
        mExtras.putBoolean("android.support.isGroupSummary", true);
      }
    }
    for (;;)
    {
      if (paramString2 != null) {
        mExtras.putString("android.support.sortKey", paramString2);
      }
      return;
      bool = false;
      break;
      label337:
      bool = false;
      break label123;
      label343:
      bool = false;
      break label143;
      label349:
      bool = false;
      break label202;
      label355:
      mExtras.putBoolean("android.support.useSideChannel", true);
    }
  }
  
  public void addAction(NotificationCompatBase.Action paramAction)
  {
    mActionExtrasList.add(NotificationCompatJellybean.writeActionAndGetExtras(b, paramAction));
  }
  
  public Notification build()
  {
    Notification localNotification = b.build();
    Object localObject = NotificationCompatJellybean.getExtras(localNotification);
    Bundle localBundle = new Bundle(mExtras);
    Iterator localIterator = mExtras.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if (((Bundle)localObject).containsKey(str)) {
        localBundle.remove(str);
      }
    }
    ((Bundle)localObject).putAll(localBundle);
    localObject = NotificationCompatJellybean.buildActionExtrasMap(mActionExtrasList);
    if (localObject != null) {
      NotificationCompatJellybean.getExtras(localNotification).putSparseParcelableArray("android.support.actionExtras", (SparseArray)localObject);
    }
    return localNotification;
  }
  
  public Notification.Builder getBuilder()
  {
    return b;
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.NotificationCompatJellybean.Builder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */