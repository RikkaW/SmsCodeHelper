package android.support.v4.app;

import android.app.RemoteInput;
import android.app.RemoteInput.Builder;
import android.content.Intent;
import android.os.Bundle;

class RemoteInputCompatApi20
{
  static void addResultsToIntent(RemoteInputCompatBase.RemoteInput[] paramArrayOfRemoteInput, Intent paramIntent, Bundle paramBundle)
  {
    RemoteInput.addResultsToIntent(fromCompat(paramArrayOfRemoteInput), paramIntent, paramBundle);
  }
  
  static RemoteInput[] fromCompat(RemoteInputCompatBase.RemoteInput[] paramArrayOfRemoteInput)
  {
    if (paramArrayOfRemoteInput == null) {
      return null;
    }
    RemoteInput[] arrayOfRemoteInput = new RemoteInput[paramArrayOfRemoteInput.length];
    int i = 0;
    while (i < paramArrayOfRemoteInput.length)
    {
      RemoteInputCompatBase.RemoteInput localRemoteInput = paramArrayOfRemoteInput[i];
      arrayOfRemoteInput[i] = new RemoteInput.Builder(localRemoteInput.getResultKey()).setLabel(localRemoteInput.getLabel()).setChoices(localRemoteInput.getChoices()).setAllowFreeFormInput(localRemoteInput.getAllowFreeFormInput()).addExtras(localRemoteInput.getExtras()).build();
      i += 1;
    }
    return arrayOfRemoteInput;
  }
  
  static Bundle getResultsFromIntent(Intent paramIntent)
  {
    return RemoteInput.getResultsFromIntent(paramIntent);
  }
  
  static RemoteInputCompatBase.RemoteInput[] toCompat(RemoteInput[] paramArrayOfRemoteInput, RemoteInputCompatBase.RemoteInput.Factory paramFactory)
  {
    if (paramArrayOfRemoteInput == null) {
      return null;
    }
    RemoteInputCompatBase.RemoteInput[] arrayOfRemoteInput = paramFactory.newArray(paramArrayOfRemoteInput.length);
    int i = 0;
    while (i < paramArrayOfRemoteInput.length)
    {
      RemoteInput localRemoteInput = paramArrayOfRemoteInput[i];
      arrayOfRemoteInput[i] = paramFactory.build(localRemoteInput.getResultKey(), localRemoteInput.getLabel(), localRemoteInput.getChoices(), localRemoteInput.getAllowFreeFormInput(), localRemoteInput.getExtras());
      i += 1;
    }
    return arrayOfRemoteInput;
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.RemoteInputCompatApi20
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */