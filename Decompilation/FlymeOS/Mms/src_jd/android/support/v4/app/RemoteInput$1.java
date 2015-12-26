package android.support.v4.app;

import android.os.Bundle;

final class RemoteInput$1
  implements RemoteInputCompatBase.RemoteInput.Factory
{
  public RemoteInput build(String paramString, CharSequence paramCharSequence, CharSequence[] paramArrayOfCharSequence, boolean paramBoolean, Bundle paramBundle)
  {
    return new RemoteInput(paramString, paramCharSequence, paramArrayOfCharSequence, paramBoolean, paramBundle);
  }
  
  public RemoteInput[] newArray(int paramInt)
  {
    return new RemoteInput[paramInt];
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.RemoteInput.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */