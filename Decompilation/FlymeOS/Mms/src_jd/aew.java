import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import android.widget.RemoteViewsService.RemoteViewsFactory;
import com.android.mms.MmsApp;
import com.android.mms.ui.ConversationList;
import com.android.mms.view.ConversationListItem;

public class aew
  extends RemoteViewsService
{
  private static final Object a = new Object();
  
  public RemoteViewsService.RemoteViewsFactory onGetViewFactory(Intent paramIntent)
  {
    if (Log.isLoggable("Mms:widget", 2)) {
      Log.v("MmsWidgetService", "onGetViewFactory intent: " + paramIntent);
    }
    return new aew.a(getApplicationContext(), paramIntent);
  }
  
  static class a
    implements RemoteViewsService.RemoteViewsFactory, gm.b
  {
    private static int g;
    private static int h;
    private static int i;
    private static int j;
    private final Context a;
    private final int b;
    private boolean c;
    private Cursor d;
    private int e;
    private final AppWidgetManager f;
    
    public a(Context paramContext, Intent paramIntent)
    {
      a = paramContext;
      b = paramIntent.getIntExtra("appWidgetId", 0);
      f = AppWidgetManager.getInstance(paramContext);
      if (Log.isLoggable("Mms:widget", 2)) {
        Log.v("MmsWidgetService", "MmsFactory intent: " + paramIntent + "widget id: " + b);
      }
      paramContext = paramContext.getResources();
      i = paramContext.getColor(2131820871);
      j = paramContext.getColor(2131820872);
      g = paramContext.getColor(2131820873);
      h = paramContext.getColor(2131820874);
    }
    
    private Cursor a()
    {
      return a.getContentResolver().query(gr.a, gr.b, null, null, null);
    }
    
    private SpannableStringBuilder a(CharSequence paramCharSequence, int paramInt)
    {
      SpannableStringBuilder localSpannableStringBuilder = new SpannableStringBuilder(paramCharSequence);
      if (paramInt != 0) {
        localSpannableStringBuilder.setSpan(new ForegroundColorSpan(paramInt), 0, paramCharSequence.length(), 33);
      }
      return localSpannableStringBuilder;
    }
    
    private int b()
    {
      for (;;)
      {
        try
        {
          Cursor localCursor = a.getContentResolver().query(gr.a, gr.b, "read=0", null, null);
          if (localCursor != null) {}
          int k = 0;
        }
        finally
        {
          try
          {
            k = localCursor.getCount();
            if (localCursor != null) {
              localCursor.close();
            }
            return k;
          }
          finally {}
          localObject1 = finally;
          localCursor = null;
          if (localCursor != null) {
            localCursor.close();
          }
        }
      }
    }
    
    private int c()
    {
      if (Log.isLoggable("Mms:widget", 2)) {
        Log.v("MmsWidgetService", "getConversationCount");
      }
      return Math.min(d.getCount(), 25);
    }
    
    private RemoteViews d()
    {
      if (Log.isLoggable("Mms:widget", 2)) {
        Log.v("MmsWidgetService", "getViewMoreConversationsView");
      }
      RemoteViews localRemoteViews = new RemoteViews(a.getPackageName(), 2130968857);
      localRemoteViews.setTextViewText(2131886790, a.getText(2131493164));
      localRemoteViews.setOnClickPendingIntent(2131886789, PendingIntent.getActivity(a, 0, new Intent(a, ConversationList.class), 134217728));
      return localRemoteViews;
    }
    
    private void e()
    {
      if (Log.isLoggable("Mms:widget", 2)) {
        Log.v("MmsWidgetService", "onLoadComplete");
      }
      RemoteViews localRemoteViews = new RemoteViews(a.getPackageName(), 2130968855);
      if (e > 0) {}
      for (int k = 0;; k = 8)
      {
        localRemoteViews.setViewVisibility(2131886780, k);
        if (e > 0) {
          localRemoteViews.setTextViewText(2131886780, Integer.toString(e));
        }
        f.partiallyUpdateAppWidget(b, localRemoteViews);
        return;
      }
    }
    
    public void a(gm paramgm)
    {
      if (Log.isLoggable("Mms:widget", 2)) {
        Log.v("MmsWidgetService", "onUpdate from Contact: " + paramgm);
      }
      f.notifyAppWidgetViewDataChanged(b, 2131886782);
    }
    
    public int getCount()
    {
      int k = 0;
      if (Log.isLoggable("Mms:widget", 2)) {
        Log.v("MmsWidgetService", "getCount");
      }
      for (;;)
      {
        synchronized (aew.a())
        {
          if (d == null) {
            return 0;
          }
          int m = c();
          if (m < d.getCount())
          {
            bool = true;
            c = bool;
            if (c) {
              k = 1;
            }
            return k + m;
          }
        }
        boolean bool = false;
      }
    }
    
    public long getItemId(int paramInt)
    {
      return paramInt;
    }
    
    public RemoteViews getLoadingView()
    {
      RemoteViews localRemoteViews = new RemoteViews(a.getPackageName(), 2130968857);
      localRemoteViews.setTextViewText(2131886790, a.getText(2131492978));
      return localRemoteViews;
    }
    
    public RemoteViews getViewAt(int paramInt)
    {
      int k = 0;
      if (Log.isLoggable("Mms:widget", 2)) {
        Log.v("MmsWidgetService", "getViewAt position: " + paramInt);
      }
      synchronized (aew.a())
      {
        RemoteViews localRemoteViews1;
        if ((d == null) || ((c) && (paramInt >= c())))
        {
          localRemoteViews1 = d();
          return localRemoteViews1;
        }
        if (!d.moveToPosition(paramInt))
        {
          Log.w("MmsWidgetService", "Failed to move to position: " + paramInt);
          localRemoteViews1 = d();
          return localRemoteViews1;
        }
      }
      gr localgr = gr.a(MmsApp.c(), d);
      RemoteViews localRemoteViews2 = new RemoteViews(a.getPackageName(), 2130968856);
      if (localgr.n())
      {
        localRemoteViews2.setViewVisibility(2131886784, 0);
        localRemoteViews2.setViewVisibility(2131886785, 8);
        if (!localgr.q()) {
          break label554;
        }
      }
      label235:
      label262:
      label540:
      label547:
      label554:
      for (paramInt = k;; paramInt = 8)
      {
        localRemoteViews2.setViewVisibility(2131886788, paramInt);
        Object localObject3 = wd.a(a, localgr.j());
        if (localgr.n())
        {
          paramInt = h;
          localRemoteViews2.setTextViewText(2131886786, a((CharSequence)localObject3, paramInt));
          if (!localgr.n()) {
            break label540;
          }
          paramInt = j;
          localObject3 = a(localgr.h().a(", "), paramInt);
          if (localgr.i())
          {
            ((SpannableStringBuilder)localObject3).append(a.getResources().getString(2131492947));
            k = ((SpannableStringBuilder)localObject3).length();
            ((SpannableStringBuilder)localObject3).append(a.getResources().getString(2131492967));
            ((SpannableStringBuilder)localObject3).setSpan(new TextAppearanceSpan(a, 16973894, paramInt), k, ((SpannableStringBuilder)localObject3).length(), 17);
            ((SpannableStringBuilder)localObject3).setSpan(new ForegroundColorSpan(a.getResources().getColor(2130838705)), k, ((SpannableStringBuilder)localObject3).length(), 17);
          }
          if (localgr.n()) {
            ((SpannableStringBuilder)localObject3).setSpan(ConversationListItem.a, 0, ((SpannableStringBuilder)localObject3).length(), 17);
          }
          localRemoteViews2.setTextViewText(2131886623, (CharSequence)localObject3);
          localObject3 = localgr.l();
          if (!localgr.n()) {
            break label547;
          }
        }
        for (paramInt = h;; paramInt = g)
        {
          localRemoteViews2.setTextViewText(2131886596, a((CharSequence)localObject3, paramInt));
          localObject3 = new Intent("android.intent.action.VIEW");
          ((Intent)localObject3).setType("vnd.android-dir/mms-sms");
          ((Intent)localObject3).putExtra("thread_id", localgr.e());
          localRemoteViews2.setOnClickFillInIntent(2131886783, (Intent)localObject3);
          return localRemoteViews2;
          localRemoteViews2.setViewVisibility(2131886784, 8);
          localRemoteViews2.setViewVisibility(2131886785, 0);
          break;
          paramInt = g;
          break label235;
          paramInt = i;
          break label262;
        }
      }
    }
    
    public int getViewTypeCount()
    {
      return 2;
    }
    
    public boolean hasStableIds()
    {
      return true;
    }
    
    public void onCreate()
    {
      if (Log.isLoggable("Mms:widget", 2)) {
        Log.v("MmsWidgetService", "onCreate");
      }
      gm.a(this);
    }
    
    public void onDataSetChanged()
    {
      if (Log.isLoggable("Mms:widget", 2)) {
        Log.v("MmsWidgetService", "onDataSetChanged");
      }
      synchronized (aew.a())
      {
        if (d != null)
        {
          d.close();
          d = null;
        }
        d = a();
        e = b();
        e();
        return;
      }
    }
    
    public void onDestroy()
    {
      if (Log.isLoggable("Mms:widget", 2)) {
        Log.v("MmsWidgetService", "onDestroy");
      }
      synchronized (aew.a())
      {
        if ((d != null) && (!d.isClosed()))
        {
          d.close();
          d = null;
        }
        gm.b(this);
        return;
      }
    }
  }
}

/* Location:
 * Qualified Name:     aew
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */