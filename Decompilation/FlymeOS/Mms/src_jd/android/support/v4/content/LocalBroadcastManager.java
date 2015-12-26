package android.support.v4.content;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class LocalBroadcastManager
{
  private static final boolean DEBUG = false;
  static final int MSG_EXEC_PENDING_BROADCASTS = 1;
  private static final String TAG = "LocalBroadcastManager";
  private static LocalBroadcastManager mInstance;
  private static final Object mLock = new Object();
  private final HashMap<String, ArrayList<ReceiverRecord>> mActions = new HashMap();
  private final Context mAppContext;
  private final Handler mHandler;
  private final ArrayList<BroadcastRecord> mPendingBroadcasts = new ArrayList();
  private final HashMap<BroadcastReceiver, ArrayList<IntentFilter>> mReceivers = new HashMap();
  
  private LocalBroadcastManager(Context paramContext)
  {
    mAppContext = paramContext;
    mHandler = new LocalBroadcastManager.1(this, paramContext.getMainLooper());
  }
  
  private void executePendingBroadcasts()
  {
    for (;;)
    {
      int i;
      synchronized (mReceivers)
      {
        i = mPendingBroadcasts.size();
        if (i <= 0) {
          return;
        }
        BroadcastRecord[] arrayOfBroadcastRecord = new BroadcastRecord[i];
        mPendingBroadcasts.toArray(arrayOfBroadcastRecord);
        mPendingBroadcasts.clear();
        i = 0;
        if (i >= arrayOfBroadcastRecord.length) {
          continue;
        }
        ??? = arrayOfBroadcastRecord[i];
        int j = 0;
        if (j < receivers.size())
        {
          receivers.get(j)).receiver.onReceive(mAppContext, intent);
          j += 1;
        }
      }
      i += 1;
    }
  }
  
  public static LocalBroadcastManager getInstance(Context paramContext)
  {
    synchronized (mLock)
    {
      if (mInstance == null) {
        mInstance = new LocalBroadcastManager(paramContext.getApplicationContext());
      }
      paramContext = mInstance;
      return paramContext;
    }
  }
  
  public void registerReceiver(BroadcastReceiver paramBroadcastReceiver, IntentFilter paramIntentFilter)
  {
    synchronized (mReceivers)
    {
      ReceiverRecord localReceiverRecord = new ReceiverRecord(paramIntentFilter, paramBroadcastReceiver);
      Object localObject2 = (ArrayList)mReceivers.get(paramBroadcastReceiver);
      Object localObject1 = localObject2;
      if (localObject2 == null)
      {
        localObject1 = new ArrayList(1);
        mReceivers.put(paramBroadcastReceiver, localObject1);
      }
      ((ArrayList)localObject1).add(paramIntentFilter);
      int i = 0;
      while (i < paramIntentFilter.countActions())
      {
        localObject2 = paramIntentFilter.getAction(i);
        localObject1 = (ArrayList)mActions.get(localObject2);
        paramBroadcastReceiver = (BroadcastReceiver)localObject1;
        if (localObject1 == null)
        {
          paramBroadcastReceiver = new ArrayList(1);
          mActions.put(localObject2, paramBroadcastReceiver);
        }
        paramBroadcastReceiver.add(localReceiverRecord);
        i += 1;
      }
      return;
    }
  }
  
  public boolean sendBroadcast(Intent paramIntent)
  {
    int i;
    label161:
    int j;
    Object localObject;
    int k;
    ArrayList localArrayList1;
    synchronized (mReceivers)
    {
      String str1 = paramIntent.getAction();
      String str2 = paramIntent.resolveTypeIfNeeded(mAppContext.getContentResolver());
      Uri localUri = paramIntent.getData();
      String str3 = paramIntent.getScheme();
      Set localSet = paramIntent.getCategories();
      if ((paramIntent.getFlags() & 0x8) == 0) {
        break label500;
      }
      i = 1;
      if (i != 0) {
        Log.v("LocalBroadcastManager", "Resolving type " + str2 + " scheme " + str3 + " of intent " + paramIntent);
      }
      ArrayList localArrayList2 = (ArrayList)mActions.get(paramIntent.getAction());
      if (localArrayList2 == null) {
        break label477;
      }
      if (i == 0) {
        break label485;
      }
      Log.v("LocalBroadcastManager", "Action list: " + localArrayList2);
      break label485;
      if (j >= localArrayList2.size()) {
        break label536;
      }
      localObject = (ReceiverRecord)localArrayList2.get(j);
      if (i != 0) {
        Log.v("LocalBroadcastManager", "Matching against filter " + filter);
      }
      if (broadcasting)
      {
        if (i == 0) {
          break label505;
        }
        Log.v("LocalBroadcastManager", "  Filter's target already added");
      }
      else
      {
        k = filter.match(str1, str2, str3, localUri, localSet, "LocalBroadcastManager");
        if (k >= 0)
        {
          if (i != 0) {
            Log.v("LocalBroadcastManager", "  Filter matched!  match=0x" + Integer.toHexString(k));
          }
          if (localArrayList1 != null) {
            break label482;
          }
          localArrayList1 = new ArrayList();
          label311:
          localArrayList1.add(localObject);
          broadcasting = true;
        }
      }
    }
    if (i != 0) {
      switch (k)
      {
      default: 
        localObject = "unknown reason";
        label376:
        Log.v("LocalBroadcastManager", "  Filter did not match: " + (String)localObject);
        break;
      }
    }
    for (;;)
    {
      if (i < localArrayList1.size())
      {
        getbroadcasting = false;
        i += 1;
      }
      else
      {
        mPendingBroadcasts.add(new BroadcastRecord(paramIntent, localArrayList1));
        if (!mHandler.hasMessages(1)) {
          mHandler.sendEmptyMessage(1);
        }
        return true;
        label477:
        label482:
        label485:
        label500:
        label505:
        label536:
        do
        {
          return false;
          break label311;
          localArrayList1 = null;
          j = 0;
          break label161;
          for (;;)
          {
            j += 1;
            break label161;
            i = 0;
            break;
          }
          localObject = "action";
          break label376;
          localObject = "category";
          break label376;
          localObject = "data";
          break label376;
          localObject = "type";
          break label376;
        } while (localArrayList1 == null);
        i = 0;
      }
    }
  }
  
  public void sendBroadcastSync(Intent paramIntent)
  {
    if (sendBroadcast(paramIntent)) {
      executePendingBroadcasts();
    }
  }
  
  public void unregisterReceiver(BroadcastReceiver paramBroadcastReceiver)
  {
    for (;;)
    {
      int k;
      int i;
      synchronized (mReceivers)
      {
        ArrayList localArrayList1 = (ArrayList)mReceivers.remove(paramBroadcastReceiver);
        if (localArrayList1 != null) {
          break label167;
        }
        return;
        if (j < localArrayList1.size())
        {
          IntentFilter localIntentFilter = (IntentFilter)localArrayList1.get(j);
          k = 0;
          if (k >= localIntentFilter.countActions()) {
            break label188;
          }
          String str = localIntentFilter.getAction(k);
          ArrayList localArrayList2 = (ArrayList)mActions.get(str);
          if (localArrayList2 == null) {
            break label179;
          }
          i = 0;
          if (i < localArrayList2.size())
          {
            if (getreceiver == paramBroadcastReceiver)
            {
              localArrayList2.remove(i);
              i -= 1;
              break label172;
            }
          }
          else
          {
            if (localArrayList2.size() > 0) {
              break label179;
            }
            mActions.remove(str);
            break label179;
          }
        }
        else
        {
          return;
        }
      }
      break label172;
      label167:
      int j = 0;
      continue;
      label172:
      i += 1;
      continue;
      label179:
      k += 1;
      continue;
      label188:
      j += 1;
    }
  }
  
  static class BroadcastRecord
  {
    final Intent intent;
    final ArrayList<LocalBroadcastManager.ReceiverRecord> receivers;
    
    BroadcastRecord(Intent paramIntent, ArrayList<LocalBroadcastManager.ReceiverRecord> paramArrayList)
    {
      intent = paramIntent;
      receivers = paramArrayList;
    }
  }
  
  static class ReceiverRecord
  {
    boolean broadcasting;
    final IntentFilter filter;
    final BroadcastReceiver receiver;
    
    ReceiverRecord(IntentFilter paramIntentFilter, BroadcastReceiver paramBroadcastReceiver)
    {
      filter = paramIntentFilter;
      receiver = paramBroadcastReceiver;
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder(128);
      localStringBuilder.append("Receiver{");
      localStringBuilder.append(receiver);
      localStringBuilder.append(" filter=");
      localStringBuilder.append(filter);
      localStringBuilder.append("}");
      return localStringBuilder.toString();
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v4.content.LocalBroadcastManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */