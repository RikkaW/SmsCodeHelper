package android.support.v4.app;

import android.os.Bundle;

public abstract class RemoteInputCompatBase$RemoteInput
{
  protected abstract boolean getAllowFreeFormInput();
  
  protected abstract CharSequence[] getChoices();
  
  protected abstract Bundle getExtras();
  
  protected abstract CharSequence getLabel();
  
  protected abstract String getResultKey();
  
  public static abstract interface Factory
  {
    public abstract RemoteInputCompatBase.RemoteInput build(String paramString, CharSequence paramCharSequence, CharSequence[] paramArrayOfCharSequence, boolean paramBoolean, Bundle paramBundle);
    
    public abstract RemoteInputCompatBase.RemoteInput[] newArray(int paramInt);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.RemoteInputCompatBase.RemoteInput
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */