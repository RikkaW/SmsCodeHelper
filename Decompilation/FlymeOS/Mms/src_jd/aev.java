import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;
import com.android.mms.ui.ComposeMessageActivity;
import com.android.mms.ui.ConversationList;

public class aev
  extends AppWidgetProvider
{
  public static void a(Context paramContext)
  {
    if (Log.isLoggable("Mms:widget", 2)) {
      Log.v("MmsWidgetProvider", "notifyDatasetChanged");
    }
  }
  
  private static void a(Context paramContext, int paramInt)
  {
    if (Log.isLoggable("Mms:widget", 2)) {
      Log.v("MmsWidgetProvider", "updateWidget appWidgetId: " + paramInt);
    }
    RemoteViews localRemoteViews = new RemoteViews(paramContext.getPackageName(), 2130968855);
    Object localObject = new Intent(paramContext, aew.class);
    ((Intent)localObject).putExtra("appWidgetId", paramInt);
    ((Intent)localObject).setData(Uri.parse(((Intent)localObject).toUri(1)));
    localRemoteViews.setRemoteAdapter(paramInt, 2131886782, (Intent)localObject);
    localRemoteViews.setTextViewText(2131886779, paramContext.getString(2131492888));
    localRemoteViews.setOnClickPendingIntent(2131886777, PendingIntent.getActivity(paramContext, 0, new Intent(paramContext, ConversationList.class), 134217728));
    localObject = new Intent(paramContext, ComposeMessageActivity.class);
    ((Intent)localObject).setAction("android.intent.action.SENDTO");
    localRemoteViews.setOnClickPendingIntent(2131886781, PendingIntent.getActivity(paramContext, 0, (Intent)localObject, 134217728));
    localObject = TaskStackBuilder.create(paramContext);
    ((TaskStackBuilder)localObject).addParentStack(ComposeMessageActivity.class);
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setType("vnd.android-dir/mms-sms");
    ((TaskStackBuilder)localObject).addNextIntent(localIntent);
    localRemoteViews.setPendingIntentTemplate(2131886782, ((TaskStackBuilder)localObject).getPendingIntent(0, 134217728));
    AppWidgetManager.getInstance(paramContext).updateAppWidget(paramInt, localRemoteViews);
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if (Log.isLoggable("Mms:widget", 2)) {
      Log.v("MmsWidgetProvider", "onReceive intent: " + paramIntent);
    }
    if ("com.android.mms.intent.action.ACTION_NOTIFY_DATASET_CHANGED".equals(paramIntent.getAction()))
    {
      paramIntent = AppWidgetManager.getInstance(paramContext);
      paramIntent.notifyAppWidgetViewDataChanged(paramIntent.getAppWidgetIds(new ComponentName(paramContext, aev.class)), 2131886782);
      return;
    }
    super.onReceive(paramContext, paramIntent);
  }
  
  public void onUpdate(Context paramContext, AppWidgetManager paramAppWidgetManager, int[] paramArrayOfInt)
  {
    super.onUpdate(paramContext, paramAppWidgetManager, paramArrayOfInt);
    int i = 0;
    while (i < paramArrayOfInt.length)
    {
      a(paramContext, paramArrayOfInt[i]);
      i += 1;
    }
  }
}

/* Location:
 * Qualified Name:     aev
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */