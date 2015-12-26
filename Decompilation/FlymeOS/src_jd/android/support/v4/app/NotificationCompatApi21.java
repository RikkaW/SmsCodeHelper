package android.support.v4.app;

import android.app.Notification;
import android.app.Notification.Builder;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.app.RemoteInput.Builder;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.RemoteViews;
import java.util.ArrayList;
import java.util.Iterator;

class NotificationCompatApi21
{
  public static final String CATEGORY_ALARM = "alarm";
  public static final String CATEGORY_CALL = "call";
  public static final String CATEGORY_EMAIL = "email";
  public static final String CATEGORY_ERROR = "err";
  public static final String CATEGORY_EVENT = "event";
  public static final String CATEGORY_MESSAGE = "msg";
  public static final String CATEGORY_PROGRESS = "progress";
  public static final String CATEGORY_PROMO = "promo";
  public static final String CATEGORY_RECOMMENDATION = "recommendation";
  public static final String CATEGORY_SERVICE = "service";
  public static final String CATEGORY_SOCIAL = "social";
  public static final String CATEGORY_STATUS = "status";
  public static final String CATEGORY_SYSTEM = "sys";
  public static final String CATEGORY_TRANSPORT = "transport";
  private static final String KEY_AUTHOR = "author";
  private static final String KEY_MESSAGES = "messages";
  private static final String KEY_ON_READ = "on_read";
  private static final String KEY_ON_REPLY = "on_reply";
  private static final String KEY_PARTICIPANTS = "participants";
  private static final String KEY_REMOTE_INPUT = "remote_input";
  private static final String KEY_TEXT = "text";
  private static final String KEY_TIMESTAMP = "timestamp";
  
  private static RemoteInput fromCompatRemoteInput(RemoteInputCompatBase.RemoteInput paramRemoteInput)
  {
    return new RemoteInput.Builder(paramRemoteInput.getResultKey()).setLabel(paramRemoteInput.getLabel()).setChoices(paramRemoteInput.getChoices()).setAllowFreeFormInput(paramRemoteInput.getAllowFreeFormInput()).addExtras(paramRemoteInput.getExtras()).build();
  }
  
  static Bundle getBundleForUnreadConversation(NotificationCompatBase.UnreadConversation paramUnreadConversation)
  {
    Parcelable[] arrayOfParcelable = null;
    int i = 0;
    if (paramUnreadConversation == null) {
      return null;
    }
    Bundle localBundle1 = new Bundle();
    Object localObject = arrayOfParcelable;
    if (paramUnreadConversation.getParticipants() != null)
    {
      localObject = arrayOfParcelable;
      if (paramUnreadConversation.getParticipants().length > 1) {
        localObject = paramUnreadConversation.getParticipants()[0];
      }
    }
    arrayOfParcelable = new Parcelable[paramUnreadConversation.getMessages().length];
    while (i < arrayOfParcelable.length)
    {
      Bundle localBundle2 = new Bundle();
      localBundle2.putString("text", paramUnreadConversation.getMessages()[i]);
      localBundle2.putString("author", (String)localObject);
      arrayOfParcelable[i] = localBundle2;
      i += 1;
    }
    localBundle1.putParcelableArray("messages", arrayOfParcelable);
    localObject = paramUnreadConversation.getRemoteInput();
    if (localObject != null) {
      localBundle1.putParcelable("remote_input", fromCompatRemoteInput((RemoteInputCompatBase.RemoteInput)localObject));
    }
    localBundle1.putParcelable("on_reply", paramUnreadConversation.getReplyPendingIntent());
    localBundle1.putParcelable("on_read", paramUnreadConversation.getReadPendingIntent());
    localBundle1.putStringArray("participants", paramUnreadConversation.getParticipants());
    localBundle1.putLong("timestamp", paramUnreadConversation.getLatestTimestamp());
    return localBundle1;
  }
  
  public static String getCategory(Notification paramNotification)
  {
    return category;
  }
  
  static NotificationCompatBase.UnreadConversation getUnreadConversationFromBundle(Bundle paramBundle, NotificationCompatBase.UnreadConversation.Factory paramFactory, RemoteInputCompatBase.RemoteInput.Factory paramFactory1)
  {
    int k = 0;
    if (paramBundle == null) {
      return null;
    }
    Object localObject = paramBundle.getParcelableArray("messages");
    String[] arrayOfString1;
    int i;
    int j;
    if (localObject != null)
    {
      arrayOfString1 = new String[localObject.length];
      i = 0;
      if (i < arrayOfString1.length) {
        if (!(localObject[i] instanceof Bundle))
        {
          j = k;
          label53:
          if (j == 0) {
            break label188;
          }
        }
      }
    }
    for (;;)
    {
      localObject = (PendingIntent)paramBundle.getParcelable("on_read");
      PendingIntent localPendingIntent = (PendingIntent)paramBundle.getParcelable("on_reply");
      RemoteInput localRemoteInput = (RemoteInput)paramBundle.getParcelable("remote_input");
      String[] arrayOfString2 = paramBundle.getStringArray("participants");
      if ((arrayOfString2 == null) || (arrayOfString2.length != 1)) {
        break;
      }
      if (localRemoteInput != null) {}
      for (paramFactory1 = toCompatRemoteInput(localRemoteInput, paramFactory1);; paramFactory1 = null)
      {
        return paramFactory.build(arrayOfString1, paramFactory1, localPendingIntent, (PendingIntent)localObject, arrayOfString2, paramBundle.getLong("timestamp"));
        arrayOfString1[i] = ((Bundle)localObject[i]).getString("text");
        j = k;
        if (arrayOfString1[i] == null) {
          break label53;
        }
        i += 1;
        break;
      }
      j = 1;
      break label53;
      label188:
      break;
      arrayOfString1 = null;
    }
  }
  
  private static RemoteInputCompatBase.RemoteInput toCompatRemoteInput(RemoteInput paramRemoteInput, RemoteInputCompatBase.RemoteInput.Factory paramFactory)
  {
    return paramFactory.build(paramRemoteInput.getResultKey(), paramRemoteInput.getLabel(), paramRemoteInput.getChoices(), paramRemoteInput.getAllowFreeFormInput(), paramRemoteInput.getExtras());
  }
  
  public static class Builder
    implements NotificationBuilderWithActions, NotificationBuilderWithBuilderAccessor
  {
    private Notification.Builder b;
    
    public Builder(Context paramContext, Notification paramNotification1, CharSequence paramCharSequence1, CharSequence paramCharSequence2, CharSequence paramCharSequence3, RemoteViews paramRemoteViews, int paramInt1, PendingIntent paramPendingIntent1, PendingIntent paramPendingIntent2, Bitmap paramBitmap, int paramInt2, int paramInt3, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, int paramInt4, CharSequence paramCharSequence4, boolean paramBoolean4, String paramString1, ArrayList<String> paramArrayList, Bundle paramBundle, int paramInt5, int paramInt6, Notification paramNotification2, String paramString2, boolean paramBoolean5, String paramString3)
    {
      paramContext = new Notification.Builder(paramContext).setWhen(when).setShowWhen(paramBoolean2).setSmallIcon(icon, iconLevel).setContent(contentView).setTicker(tickerText, paramRemoteViews).setSound(sound, audioStreamType).setVibrate(vibrate).setLights(ledARGB, ledOnMS, ledOffMS);
      if ((flags & 0x2) != 0)
      {
        paramBoolean2 = true;
        paramContext = paramContext.setOngoing(paramBoolean2);
        if ((flags & 0x8) == 0) {
          break label325;
        }
        paramBoolean2 = true;
        label117:
        paramContext = paramContext.setOnlyAlertOnce(paramBoolean2);
        if ((flags & 0x10) == 0) {
          break label331;
        }
        paramBoolean2 = true;
        label137:
        paramContext = paramContext.setAutoCancel(paramBoolean2).setDefaults(defaults).setContentTitle(paramCharSequence1).setContentText(paramCharSequence2).setSubText(paramCharSequence4).setContentInfo(paramCharSequence3).setContentIntent(paramPendingIntent1).setDeleteIntent(deleteIntent);
        if ((flags & 0x80) == 0) {
          break label337;
        }
      }
      label325:
      label331:
      label337:
      for (paramBoolean2 = true;; paramBoolean2 = false)
      {
        b = paramContext.setFullScreenIntent(paramPendingIntent2, paramBoolean2).setLargeIcon(paramBitmap).setNumber(paramInt1).setUsesChronometer(paramBoolean3).setPriority(paramInt4).setProgress(paramInt2, paramInt3, paramBoolean1).setLocalOnly(paramBoolean4).setExtras(paramBundle).setGroup(paramString2).setGroupSummary(paramBoolean5).setSortKey(paramString3).setCategory(paramString1).setColor(paramInt5).setVisibility(paramInt6).setPublicVersion(paramNotification2);
        paramContext = paramArrayList.iterator();
        while (paramContext.hasNext())
        {
          paramNotification1 = (String)paramContext.next();
          b.addPerson(paramNotification1);
        }
        paramBoolean2 = false;
        break;
        paramBoolean2 = false;
        break label117;
        paramBoolean2 = false;
        break label137;
      }
    }
    
    public void addAction(NotificationCompatBase.Action paramAction)
    {
      NotificationCompatApi20.addAction(b, paramAction);
    }
    
    public Notification build()
    {
      return b.build();
    }
    
    public Notification.Builder getBuilder()
    {
      return b;
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.NotificationCompatApi21
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */