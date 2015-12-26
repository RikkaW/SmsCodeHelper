package android.support.v7.internal.app;

import android.app.Notification;
import android.app.Notification.Builder;
import android.app.PendingIntent;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.support.v4.app.NotificationBuilderWithBuilderAccessor;
import android.support.v4.app.NotificationCompatBase.Action;
import android.support.v7.appcompat.R.dimen;
import android.support.v7.appcompat.R.id;
import android.support.v7.appcompat.R.integer;
import android.support.v7.appcompat.R.layout;
import android.support.v7.appcompat.R.string;
import android.widget.RemoteViews;
import java.text.NumberFormat;
import java.util.List;

public class NotificationCompatImplBase
{
  static final int MAX_MEDIA_BUTTONS = 5;
  static final int MAX_MEDIA_BUTTONS_IN_COMPACT = 3;
  
  private static RemoteViews applyStandardTemplate(Context paramContext, CharSequence paramCharSequence1, CharSequence paramCharSequence2, CharSequence paramCharSequence3, int paramInt1, Bitmap paramBitmap, CharSequence paramCharSequence4, boolean paramBoolean1, long paramLong, int paramInt2, boolean paramBoolean2)
  {
    RemoteViews localRemoteViews = new RemoteViews(paramContext.getPackageName(), paramInt2);
    paramInt2 = 0;
    int i = 0;
    if ((paramBitmap != null) && (Build.VERSION.SDK_INT >= 16))
    {
      localRemoteViews.setImageViewBitmap(R.id.icon, paramBitmap);
      if (paramCharSequence1 != null) {
        localRemoteViews.setTextViewText(R.id.title, paramCharSequence1);
      }
      if (paramCharSequence2 != null)
      {
        localRemoteViews.setTextViewText(R.id.text, paramCharSequence2);
        paramInt2 = 1;
      }
      if (paramCharSequence3 == null) {
        break label298;
      }
      localRemoteViews.setTextViewText(R.id.info, paramCharSequence3);
      localRemoteViews.setViewVisibility(R.id.info, 0);
      paramInt1 = 1;
      label98:
      paramInt2 = i;
      if (paramCharSequence4 != null)
      {
        paramInt2 = i;
        if (Build.VERSION.SDK_INT >= 16)
        {
          localRemoteViews.setTextViewText(R.id.text, paramCharSequence4);
          if (paramCharSequence2 == null) {
            break label390;
          }
          localRemoteViews.setTextViewText(R.id.text2, paramCharSequence2);
          localRemoteViews.setViewVisibility(R.id.text2, 0);
          paramInt2 = 1;
        }
      }
      label154:
      if ((paramInt2 != 0) && (Build.VERSION.SDK_INT >= 16))
      {
        if (paramBoolean2)
        {
          float f = paramContext.getResources().getDimensionPixelSize(R.dimen.notification_subtext_size);
          localRemoteViews.setTextViewTextSize(R.id.text, 0, f);
        }
        localRemoteViews.setViewPadding(R.id.line1, 0, 0, 0, 0);
      }
      if (paramLong != 0L)
      {
        if (!paramBoolean1) {
          break label407;
        }
        localRemoteViews.setViewVisibility(R.id.chronometer, 0);
        localRemoteViews.setLong(R.id.chronometer, "setBase", SystemClock.elapsedRealtime() - System.currentTimeMillis() + paramLong);
        localRemoteViews.setBoolean(R.id.chronometer, "setStarted", true);
      }
      label260:
      paramInt2 = R.id.line3;
      if (paramInt1 == 0) {
        break label431;
      }
    }
    label298:
    label390:
    label407:
    label431:
    for (paramInt1 = 0;; paramInt1 = 8)
    {
      localRemoteViews.setViewVisibility(paramInt2, paramInt1);
      return localRemoteViews;
      localRemoteViews.setViewVisibility(R.id.icon, 8);
      break;
      if (paramInt1 > 0)
      {
        if (paramInt1 > paramContext.getResources().getInteger(R.integer.status_bar_notification_info_maxnum)) {
          localRemoteViews.setTextViewText(R.id.info, paramContext.getResources().getString(R.string.status_bar_notification_info_overflow));
        }
        for (;;)
        {
          localRemoteViews.setViewVisibility(R.id.info, 0);
          paramInt1 = 1;
          break;
          paramCharSequence1 = NumberFormat.getIntegerInstance();
          localRemoteViews.setTextViewText(R.id.info, paramCharSequence1.format(paramInt1));
        }
      }
      localRemoteViews.setViewVisibility(R.id.info, 8);
      paramInt1 = paramInt2;
      break label98;
      localRemoteViews.setViewVisibility(R.id.text2, 8);
      paramInt2 = i;
      break label154;
      localRemoteViews.setViewVisibility(R.id.time, 0);
      localRemoteViews.setLong(R.id.time, "setTime", paramLong);
      break label260;
    }
  }
  
  private static <T extends NotificationCompatBase.Action> RemoteViews generateBigContentView(Context paramContext, CharSequence paramCharSequence1, CharSequence paramCharSequence2, CharSequence paramCharSequence3, int paramInt, Bitmap paramBitmap, CharSequence paramCharSequence4, boolean paramBoolean1, long paramLong, List<T> paramList, boolean paramBoolean2, PendingIntent paramPendingIntent)
  {
    int i = Math.min(paramList.size(), 5);
    paramCharSequence1 = applyStandardTemplate(paramContext, paramCharSequence1, paramCharSequence2, paramCharSequence3, paramInt, paramBitmap, paramCharSequence4, paramBoolean1, paramLong, getBigLayoutResource(i), false);
    paramCharSequence1.removeAllViews(R.id.media_actions);
    if (i > 0)
    {
      paramInt = 0;
      while (paramInt < i)
      {
        paramCharSequence2 = generateMediaActionButton(paramContext, (NotificationCompatBase.Action)paramList.get(paramInt));
        paramCharSequence1.addView(R.id.media_actions, paramCharSequence2);
        paramInt += 1;
      }
    }
    if (paramBoolean2)
    {
      paramCharSequence1.setViewVisibility(R.id.cancel_action, 0);
      paramCharSequence1.setInt(R.id.cancel_action, "setAlpha", paramContext.getResources().getInteger(R.integer.cancel_button_image_alpha));
      paramCharSequence1.setOnClickPendingIntent(R.id.cancel_action, paramPendingIntent);
      return paramCharSequence1;
    }
    paramCharSequence1.setViewVisibility(R.id.cancel_action, 8);
    return paramCharSequence1;
  }
  
  private static <T extends NotificationCompatBase.Action> RemoteViews generateContentView(Context paramContext, CharSequence paramCharSequence1, CharSequence paramCharSequence2, CharSequence paramCharSequence3, int paramInt, Bitmap paramBitmap, CharSequence paramCharSequence4, boolean paramBoolean1, long paramLong, List<T> paramList, int[] paramArrayOfInt, boolean paramBoolean2, PendingIntent paramPendingIntent)
  {
    paramCharSequence1 = applyStandardTemplate(paramContext, paramCharSequence1, paramCharSequence2, paramCharSequence3, paramInt, paramBitmap, paramCharSequence4, paramBoolean1, paramLong, R.layout.notification_template_media, true);
    int j = paramList.size();
    int i;
    if (paramArrayOfInt == null)
    {
      paramInt = 0;
      paramCharSequence1.removeAllViews(R.id.media_actions);
      if (paramInt > 0) {
        i = 0;
      }
    }
    else
    {
      for (;;)
      {
        if (i >= paramInt) {
          break label152;
        }
        if (i >= j)
        {
          throw new IllegalArgumentException(String.format("setShowActionsInCompactView: action %d out of bounds (max %d)", new Object[] { Integer.valueOf(i), Integer.valueOf(j - 1) }));
          paramInt = Math.min(paramArrayOfInt.length, 3);
          break;
        }
        paramCharSequence2 = generateMediaActionButton(paramContext, (NotificationCompatBase.Action)paramList.get(paramArrayOfInt[i]));
        paramCharSequence1.addView(R.id.media_actions, paramCharSequence2);
        i += 1;
      }
    }
    label152:
    if (paramBoolean2)
    {
      paramCharSequence1.setViewVisibility(R.id.end_padder, 8);
      paramCharSequence1.setViewVisibility(R.id.cancel_action, 0);
      paramCharSequence1.setOnClickPendingIntent(R.id.cancel_action, paramPendingIntent);
      paramCharSequence1.setInt(R.id.cancel_action, "setAlpha", paramContext.getResources().getInteger(R.integer.cancel_button_image_alpha));
      return paramCharSequence1;
    }
    paramCharSequence1.setViewVisibility(R.id.end_padder, 0);
    paramCharSequence1.setViewVisibility(R.id.cancel_action, 8);
    return paramCharSequence1;
  }
  
  private static RemoteViews generateMediaActionButton(Context paramContext, NotificationCompatBase.Action paramAction)
  {
    if (paramAction.getActionIntent() == null) {}
    for (int i = 1;; i = 0)
    {
      paramContext = new RemoteViews(paramContext.getPackageName(), R.layout.notification_media_action);
      paramContext.setImageViewResource(R.id.action0, paramAction.getIcon());
      if (i == 0) {
        paramContext.setOnClickPendingIntent(R.id.action0, paramAction.getActionIntent());
      }
      paramContext.setContentDescription(R.id.action0, paramAction.getTitle());
      return paramContext;
    }
  }
  
  private static int getBigLayoutResource(int paramInt)
  {
    if (paramInt <= 3) {
      return R.layout.notification_template_big_media_narrow;
    }
    return R.layout.notification_template_big_media;
  }
  
  public static <T extends NotificationCompatBase.Action> void overrideBigContentView(Notification paramNotification, Context paramContext, CharSequence paramCharSequence1, CharSequence paramCharSequence2, CharSequence paramCharSequence3, int paramInt, Bitmap paramBitmap, CharSequence paramCharSequence4, boolean paramBoolean1, long paramLong, List<T> paramList, boolean paramBoolean2, PendingIntent paramPendingIntent)
  {
    bigContentView = generateBigContentView(paramContext, paramCharSequence1, paramCharSequence2, paramCharSequence3, paramInt, paramBitmap, paramCharSequence4, paramBoolean1, paramLong, paramList, paramBoolean2, paramPendingIntent);
    if (paramBoolean2) {
      flags |= 0x2;
    }
  }
  
  public static <T extends NotificationCompatBase.Action> void overrideContentView(NotificationBuilderWithBuilderAccessor paramNotificationBuilderWithBuilderAccessor, Context paramContext, CharSequence paramCharSequence1, CharSequence paramCharSequence2, CharSequence paramCharSequence3, int paramInt, Bitmap paramBitmap, CharSequence paramCharSequence4, boolean paramBoolean1, long paramLong, List<T> paramList, int[] paramArrayOfInt, boolean paramBoolean2, PendingIntent paramPendingIntent)
  {
    paramContext = generateContentView(paramContext, paramCharSequence1, paramCharSequence2, paramCharSequence3, paramInt, paramBitmap, paramCharSequence4, paramBoolean1, paramLong, paramList, paramArrayOfInt, paramBoolean2, paramPendingIntent);
    paramNotificationBuilderWithBuilderAccessor.getBuilder().setContent(paramContext);
    if (paramBoolean2) {
      paramNotificationBuilderWithBuilderAccessor.getBuilder().setOngoing(true);
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.app.NotificationCompatImplBase
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */