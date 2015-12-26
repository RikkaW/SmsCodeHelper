package android.support.v4.app;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;

public class TaskStackBuilder
  implements Iterable<Intent>
{
  private static final TaskStackBuilderImpl IMPL = new TaskStackBuilderImplBase();
  private static final String TAG = "TaskStackBuilder";
  private final ArrayList<Intent> mIntents = new ArrayList();
  private final Context mSourceContext;
  
  static
  {
    if (Build.VERSION.SDK_INT >= 11)
    {
      IMPL = new TaskStackBuilderImplHoneycomb();
      return;
    }
  }
  
  private TaskStackBuilder(Context paramContext)
  {
    mSourceContext = paramContext;
  }
  
  public static TaskStackBuilder create(Context paramContext)
  {
    return new TaskStackBuilder(paramContext);
  }
  
  public static TaskStackBuilder from(Context paramContext)
  {
    return create(paramContext);
  }
  
  public TaskStackBuilder addNextIntent(Intent paramIntent)
  {
    mIntents.add(paramIntent);
    return this;
  }
  
  public TaskStackBuilder addNextIntentWithParentStack(Intent paramIntent)
  {
    ComponentName localComponentName2 = paramIntent.getComponent();
    ComponentName localComponentName1 = localComponentName2;
    if (localComponentName2 == null) {
      localComponentName1 = paramIntent.resolveActivity(mSourceContext.getPackageManager());
    }
    if (localComponentName1 != null) {
      addParentStack(localComponentName1);
    }
    addNextIntent(paramIntent);
    return this;
  }
  
  public TaskStackBuilder addParentStack(Activity paramActivity)
  {
    Object localObject = null;
    if ((paramActivity instanceof SupportParentable)) {
      localObject = ((SupportParentable)paramActivity).getSupportParentActivityIntent();
    }
    if (localObject == null) {}
    for (paramActivity = NavUtils.getParentActivityIntent(paramActivity);; paramActivity = (Activity)localObject)
    {
      if (paramActivity != null)
      {
        ComponentName localComponentName = paramActivity.getComponent();
        localObject = localComponentName;
        if (localComponentName == null) {
          localObject = paramActivity.resolveActivity(mSourceContext.getPackageManager());
        }
        addParentStack((ComponentName)localObject);
        addNextIntent(paramActivity);
      }
      return this;
    }
  }
  
  public TaskStackBuilder addParentStack(ComponentName paramComponentName)
  {
    int i = mIntents.size();
    try
    {
      for (paramComponentName = NavUtils.getParentActivityIntent(mSourceContext, paramComponentName); paramComponentName != null; paramComponentName = NavUtils.getParentActivityIntent(mSourceContext, paramComponentName.getComponent())) {
        mIntents.add(i, paramComponentName);
      }
      return this;
    }
    catch (PackageManager.NameNotFoundException paramComponentName)
    {
      Log.e("TaskStackBuilder", "Bad ComponentName while traversing activity parent metadata");
      throw new IllegalArgumentException(paramComponentName);
    }
  }
  
  public TaskStackBuilder addParentStack(Class<?> paramClass)
  {
    return addParentStack(new ComponentName(mSourceContext, paramClass));
  }
  
  public Intent editIntentAt(int paramInt)
  {
    return (Intent)mIntents.get(paramInt);
  }
  
  public Intent getIntent(int paramInt)
  {
    return editIntentAt(paramInt);
  }
  
  public int getIntentCount()
  {
    return mIntents.size();
  }
  
  public Intent[] getIntents()
  {
    Intent[] arrayOfIntent = new Intent[mIntents.size()];
    if (arrayOfIntent.length == 0) {
      return arrayOfIntent;
    }
    arrayOfIntent[0] = new Intent((Intent)mIntents.get(0)).addFlags(268484608);
    int i = 1;
    while (i < arrayOfIntent.length)
    {
      arrayOfIntent[i] = new Intent((Intent)mIntents.get(i));
      i += 1;
    }
    return arrayOfIntent;
  }
  
  public PendingIntent getPendingIntent(int paramInt1, int paramInt2)
  {
    return getPendingIntent(paramInt1, paramInt2, null);
  }
  
  public PendingIntent getPendingIntent(int paramInt1, int paramInt2, Bundle paramBundle)
  {
    if (mIntents.isEmpty()) {
      throw new IllegalStateException("No intents added to TaskStackBuilder; cannot getPendingIntent");
    }
    Intent[] arrayOfIntent = (Intent[])mIntents.toArray(new Intent[mIntents.size()]);
    arrayOfIntent[0] = new Intent(arrayOfIntent[0]).addFlags(268484608);
    return IMPL.getPendingIntent(mSourceContext, arrayOfIntent, paramInt1, paramInt2, paramBundle);
  }
  
  public Iterator<Intent> iterator()
  {
    return mIntents.iterator();
  }
  
  public void startActivities()
  {
    startActivities(null);
  }
  
  public void startActivities(Bundle paramBundle)
  {
    if (mIntents.isEmpty()) {
      throw new IllegalStateException("No intents added to TaskStackBuilder; cannot startActivities");
    }
    Intent[] arrayOfIntent = (Intent[])mIntents.toArray(new Intent[mIntents.size()]);
    arrayOfIntent[0] = new Intent(arrayOfIntent[0]).addFlags(268484608);
    if (!ContextCompat.startActivities(mSourceContext, arrayOfIntent, paramBundle))
    {
      paramBundle = new Intent(arrayOfIntent[(arrayOfIntent.length - 1)]);
      paramBundle.addFlags(268435456);
      mSourceContext.startActivity(paramBundle);
    }
  }
  
  public static abstract interface SupportParentable
  {
    public abstract Intent getSupportParentActivityIntent();
  }
  
  static abstract interface TaskStackBuilderImpl
  {
    public abstract PendingIntent getPendingIntent(Context paramContext, Intent[] paramArrayOfIntent, int paramInt1, int paramInt2, Bundle paramBundle);
  }
  
  static class TaskStackBuilderImplBase
    implements TaskStackBuilder.TaskStackBuilderImpl
  {
    public PendingIntent getPendingIntent(Context paramContext, Intent[] paramArrayOfIntent, int paramInt1, int paramInt2, Bundle paramBundle)
    {
      paramArrayOfIntent = new Intent(paramArrayOfIntent[(paramArrayOfIntent.length - 1)]);
      paramArrayOfIntent.addFlags(268435456);
      return PendingIntent.getActivity(paramContext, paramInt1, paramArrayOfIntent, paramInt2);
    }
  }
  
  static class TaskStackBuilderImplHoneycomb
    implements TaskStackBuilder.TaskStackBuilderImpl
  {
    public PendingIntent getPendingIntent(Context paramContext, Intent[] paramArrayOfIntent, int paramInt1, int paramInt2, Bundle paramBundle)
    {
      paramArrayOfIntent[0] = new Intent(paramArrayOfIntent[0]).addFlags(268484608);
      return TaskStackBuilderHoneycomb.getActivitiesPendingIntent(paramContext, paramInt1, paramArrayOfIntent, paramInt2);
    }
  }
  
  static class TaskStackBuilderImplJellybean
    implements TaskStackBuilder.TaskStackBuilderImpl
  {
    public PendingIntent getPendingIntent(Context paramContext, Intent[] paramArrayOfIntent, int paramInt1, int paramInt2, Bundle paramBundle)
    {
      paramArrayOfIntent[0] = new Intent(paramArrayOfIntent[0]).addFlags(268484608);
      return TaskStackBuilderJellybean.getActivitiesPendingIntent(paramContext, paramInt1, paramArrayOfIntent, paramInt2, paramBundle);
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.TaskStackBuilder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */