package android.support.v4.app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

class RemoteInput$ImplBase
  implements RemoteInput.Impl
{
  public void addResultsToIntent(RemoteInput[] paramArrayOfRemoteInput, Intent paramIntent, Bundle paramBundle)
  {
    Log.w("RemoteInput", "RemoteInput is only supported from API Level 16");
  }
  
  public Bundle getResultsFromIntent(Intent paramIntent)
  {
    Log.w("RemoteInput", "RemoteInput is only supported from API Level 16");
    return null;
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.RemoteInput.ImplBase
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */