package android.support.v4.app;

import android.os.Bundle;

public final class RemoteInput$Builder
{
  private boolean mAllowFreeFormInput = true;
  private CharSequence[] mChoices;
  private Bundle mExtras = new Bundle();
  private CharSequence mLabel;
  private final String mResultKey;
  
  public RemoteInput$Builder(String paramString)
  {
    if (paramString == null) {
      throw new IllegalArgumentException("Result key can't be null");
    }
    mResultKey = paramString;
  }
  
  public Builder addExtras(Bundle paramBundle)
  {
    if (paramBundle != null) {
      mExtras.putAll(paramBundle);
    }
    return this;
  }
  
  public RemoteInput build()
  {
    return new RemoteInput(mResultKey, mLabel, mChoices, mAllowFreeFormInput, mExtras);
  }
  
  public Bundle getExtras()
  {
    return mExtras;
  }
  
  public Builder setAllowFreeFormInput(boolean paramBoolean)
  {
    mAllowFreeFormInput = paramBoolean;
    return this;
  }
  
  public Builder setChoices(CharSequence[] paramArrayOfCharSequence)
  {
    mChoices = paramArrayOfCharSequence;
    return this;
  }
  
  public Builder setLabel(CharSequence paramCharSequence)
  {
    mLabel = paramCharSequence;
    return this;
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.RemoteInput.Builder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */