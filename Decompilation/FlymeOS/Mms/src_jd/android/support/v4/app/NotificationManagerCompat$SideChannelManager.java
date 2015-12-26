package android.support.v4.app;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class NotificationManagerCompat$SideChannelManager
  implements ServiceConnection, Handler.Callback
{
  private static final String KEY_BINDER = "binder";
  private static final int MSG_QUEUE_TASK = 0;
  private static final int MSG_RETRY_LISTENER_QUEUE = 3;
  private static final int MSG_SERVICE_CONNECTED = 1;
  private static final int MSG_SERVICE_DISCONNECTED = 2;
  private Set<String> mCachedEnabledPackages = new HashSet();
  private final Context mContext;
  private final Handler mHandler;
  private final HandlerThread mHandlerThread;
  private final Map<ComponentName, ListenerRecord> mRecordMap = new HashMap();
  
  public NotificationManagerCompat$SideChannelManager(Context paramContext)
  {
    mContext = paramContext;
    mHandlerThread = new HandlerThread("NotificationManagerCompat");
    mHandlerThread.start();
    mHandler = new Handler(mHandlerThread.getLooper(), this);
  }
  
  private boolean ensureServiceBound(ListenerRecord paramListenerRecord)
  {
    if (bound) {
      return true;
    }
    Intent localIntent = new Intent("android.support.BIND_NOTIFICATION_SIDE_CHANNEL").setComponent(componentName);
    bound = mContext.bindService(localIntent, this, NotificationManagerCompat.access$000());
    if (bound) {
      retryCount = 0;
    }
    for (;;)
    {
      return bound;
      Log.w("NotifManCompat", "Unable to bind to listener " + componentName);
      mContext.unbindService(this);
    }
  }
  
  private void ensureServiceUnbound(ListenerRecord paramListenerRecord)
  {
    if (bound)
    {
      mContext.unbindService(this);
      bound = false;
    }
    service = null;
  }
  
  private void handleQueueTask(NotificationManagerCompat.Task paramTask)
  {
    updateListenerMap();
    Iterator localIterator = mRecordMap.values().iterator();
    while (localIterator.hasNext())
    {
      ListenerRecord localListenerRecord = (ListenerRecord)localIterator.next();
      taskQueue.add(paramTask);
      processListenerQueue(localListenerRecord);
    }
  }
  
  private void handleRetryListenerQueue(ComponentName paramComponentName)
  {
    paramComponentName = (ListenerRecord)mRecordMap.get(paramComponentName);
    if (paramComponentName != null) {
      processListenerQueue(paramComponentName);
    }
  }
  
  private void handleServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
  {
    paramComponentName = (ListenerRecord)mRecordMap.get(paramComponentName);
    if (paramComponentName != null)
    {
      service = INotificationSideChannel.Stub.asInterface(paramIBinder);
      retryCount = 0;
      processListenerQueue(paramComponentName);
    }
  }
  
  private void handleServiceDisconnected(ComponentName paramComponentName)
  {
    paramComponentName = (ListenerRecord)mRecordMap.get(paramComponentName);
    if (paramComponentName != null) {
      ensureServiceUnbound(paramComponentName);
    }
  }
  
  private void processListenerQueue(ListenerRecord paramListenerRecord)
  {
    if (Log.isLoggable("NotifManCompat", 3)) {
      Log.d("NotifManCompat", "Processing component " + componentName + ", " + taskQueue.size() + " queued tasks");
    }
    if (taskQueue.isEmpty()) {}
    do
    {
      return;
      if ((!ensureServiceBound(paramListenerRecord)) || (service == null))
      {
        scheduleListenerRetry(paramListenerRecord);
        return;
      }
      try
      {
        Object localObject;
        do
        {
          if (Log.isLoggable("NotifManCompat", 3)) {
            Log.d("NotifManCompat", "Sending task " + localObject);
          }
          ((NotificationManagerCompat.Task)localObject).send(service);
          taskQueue.remove();
          localObject = (NotificationManagerCompat.Task)taskQueue.peek();
        } while (localObject != null);
      }
      catch (DeadObjectException localDeadObjectException)
      {
        for (;;)
        {
          if (Log.isLoggable("NotifManCompat", 3)) {
            Log.d("NotifManCompat", "Remote service has died: " + componentName);
          }
        }
      }
      catch (RemoteException localRemoteException)
      {
        for (;;)
        {
          Log.w("NotifManCompat", "RemoteException communicating with " + componentName, localRemoteException);
        }
      }
    } while (taskQueue.isEmpty());
    scheduleListenerRetry(paramListenerRecord);
  }
  
  private void scheduleListenerRetry(ListenerRecord paramListenerRecord)
  {
    if (mHandler.hasMessages(3, componentName)) {
      return;
    }
    retryCount += 1;
    if (retryCount > 6)
    {
      Log.w("NotifManCompat", "Giving up on delivering " + taskQueue.size() + " tasks to " + componentName + " after " + retryCount + " retries");
      taskQueue.clear();
      return;
    }
    int i = (1 << retryCount - 1) * 1000;
    if (Log.isLoggable("NotifManCompat", 3)) {
      Log.d("NotifManCompat", "Scheduling retry for " + i + " ms");
    }
    paramListenerRecord = mHandler.obtainMessage(3, componentName);
    mHandler.sendMessageDelayed(paramListenerRecord, i);
  }
  
  private void updateListenerMap()
  {
    Object localObject1 = NotificationManagerCompat.getEnabledListenerPackages(mContext);
    if (((Set)localObject1).equals(mCachedEnabledPackages)) {}
    for (;;)
    {
      return;
      mCachedEnabledPackages = ((Set)localObject1);
      Object localObject2 = mContext.getPackageManager().queryIntentServices(new Intent().setAction("android.support.BIND_NOTIFICATION_SIDE_CHANNEL"), 4);
      HashSet localHashSet = new HashSet();
      localObject2 = ((List)localObject2).iterator();
      while (((Iterator)localObject2).hasNext())
      {
        ResolveInfo localResolveInfo = (ResolveInfo)((Iterator)localObject2).next();
        if (((Set)localObject1).contains(serviceInfo.packageName))
        {
          ComponentName localComponentName = new ComponentName(serviceInfo.packageName, serviceInfo.name);
          if (serviceInfo.permission != null) {
            Log.w("NotifManCompat", "Permission present on component " + localComponentName + ", not adding listener record.");
          } else {
            localHashSet.add(localComponentName);
          }
        }
      }
      localObject1 = localHashSet.iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (ComponentName)((Iterator)localObject1).next();
        if (!mRecordMap.containsKey(localObject2))
        {
          if (Log.isLoggable("NotifManCompat", 3)) {
            Log.d("NotifManCompat", "Adding listener record for " + localObject2);
          }
          mRecordMap.put(localObject2, new ListenerRecord((ComponentName)localObject2));
        }
      }
      localObject1 = mRecordMap.entrySet().iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (Map.Entry)((Iterator)localObject1).next();
        if (!localHashSet.contains(((Map.Entry)localObject2).getKey()))
        {
          if (Log.isLoggable("NotifManCompat", 3)) {
            Log.d("NotifManCompat", "Removing listener record for " + ((Map.Entry)localObject2).getKey());
          }
          ensureServiceUnbound((ListenerRecord)((Map.Entry)localObject2).getValue());
          ((Iterator)localObject1).remove();
        }
      }
    }
  }
  
  public boolean handleMessage(Message paramMessage)
  {
    switch (what)
    {
    default: 
      return false;
    case 0: 
      handleQueueTask((NotificationManagerCompat.Task)obj);
      return true;
    case 1: 
      paramMessage = (NotificationManagerCompat.ServiceConnectedEvent)obj;
      handleServiceConnected(componentName, iBinder);
      return true;
    case 2: 
      handleServiceDisconnected((ComponentName)obj);
      return true;
    }
    handleRetryListenerQueue((ComponentName)obj);
    return true;
  }
  
  public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
  {
    if (Log.isLoggable("NotifManCompat", 3)) {
      Log.d("NotifManCompat", "Connected to service " + paramComponentName);
    }
    mHandler.obtainMessage(1, new NotificationManagerCompat.ServiceConnectedEvent(paramComponentName, paramIBinder)).sendToTarget();
  }
  
  public void onServiceDisconnected(ComponentName paramComponentName)
  {
    if (Log.isLoggable("NotifManCompat", 3)) {
      Log.d("NotifManCompat", "Disconnected from service " + paramComponentName);
    }
    mHandler.obtainMessage(2, paramComponentName).sendToTarget();
  }
  
  public void queueTask(NotificationManagerCompat.Task paramTask)
  {
    mHandler.obtainMessage(0, paramTask).sendToTarget();
  }
  
  static class ListenerRecord
  {
    public boolean bound = false;
    public final ComponentName componentName;
    public int retryCount = 0;
    public INotificationSideChannel service;
    public LinkedList<NotificationManagerCompat.Task> taskQueue = new LinkedList();
    
    public ListenerRecord(ComponentName paramComponentName)
    {
      componentName = paramComponentName;
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.NotificationManagerCompat.SideChannelManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */