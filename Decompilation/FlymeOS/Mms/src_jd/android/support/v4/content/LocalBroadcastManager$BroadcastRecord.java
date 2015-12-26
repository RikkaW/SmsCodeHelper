package android.support.v4.content;

import android.content.Intent;
import java.util.ArrayList;

class LocalBroadcastManager$BroadcastRecord
{
  final Intent intent;
  final ArrayList<LocalBroadcastManager.ReceiverRecord> receivers;
  
  LocalBroadcastManager$BroadcastRecord(Intent paramIntent, ArrayList<LocalBroadcastManager.ReceiverRecord> paramArrayList)
  {
    intent = paramIntent;
    receivers = paramArrayList;
  }
}

/* Location:
 * Qualified Name:     android.support.v4.content.LocalBroadcastManager.BroadcastRecord
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */