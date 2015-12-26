package android.support.v4.app;

import android.content.Intent;
import android.os.Bundle;

abstract interface RemoteInput$Impl
{
  public abstract void addResultsToIntent(RemoteInput[] paramArrayOfRemoteInput, Intent paramIntent, Bundle paramBundle);
  
  public abstract Bundle getResultsFromIntent(Intent paramIntent);
}

/* Location:
 * Qualified Name:     android.support.v4.app.RemoteInput.Impl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */