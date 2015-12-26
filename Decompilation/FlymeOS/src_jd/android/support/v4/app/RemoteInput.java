package android.support.v4.app;

import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.Log;

public class RemoteInput
  extends RemoteInputCompatBase.RemoteInput
{
  public static final String EXTRA_RESULTS_DATA = "android.remoteinput.resultsData";
  public static final RemoteInputCompatBase.RemoteInput.Factory FACTORY;
  private static final Impl IMPL;
  public static final String RESULTS_CLIP_LABEL = "android.remoteinput.results";
  private static final String TAG = "RemoteInput";
  private final boolean mAllowFreeFormInput;
  private final CharSequence[] mChoices;
  private final Bundle mExtras;
  private final CharSequence mLabel;
  private final String mResultKey;
  
  static
  {
    if (Build.VERSION.SDK_INT >= 20) {
      IMPL = new ImplApi20();
    }
    for (;;)
    {
      FACTORY = new RemoteInput.1();
      return;
      if (Build.VERSION.SDK_INT >= 16) {
        IMPL = new ImplJellybean();
      } else {
        IMPL = new ImplBase();
      }
    }
  }
  
  RemoteInput(String paramString, CharSequence paramCharSequence, CharSequence[] paramArrayOfCharSequence, boolean paramBoolean, Bundle paramBundle)
  {
    mResultKey = paramString;
    mLabel = paramCharSequence;
    mChoices = paramArrayOfCharSequence;
    mAllowFreeFormInput = paramBoolean;
    mExtras = paramBundle;
  }
  
  public static void addResultsToIntent(RemoteInput[] paramArrayOfRemoteInput, Intent paramIntent, Bundle paramBundle)
  {
    IMPL.addResultsToIntent(paramArrayOfRemoteInput, paramIntent, paramBundle);
  }
  
  public static Bundle getResultsFromIntent(Intent paramIntent)
  {
    return IMPL.getResultsFromIntent(paramIntent);
  }
  
  public boolean getAllowFreeFormInput()
  {
    return mAllowFreeFormInput;
  }
  
  public CharSequence[] getChoices()
  {
    return mChoices;
  }
  
  public Bundle getExtras()
  {
    return mExtras;
  }
  
  public CharSequence getLabel()
  {
    return mLabel;
  }
  
  public String getResultKey()
  {
    return mResultKey;
  }
  
  public static final class Builder
  {
    private boolean mAllowFreeFormInput = true;
    private CharSequence[] mChoices;
    private Bundle mExtras = new Bundle();
    private CharSequence mLabel;
    private final String mResultKey;
    
    public Builder(String paramString)
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
  
  static abstract interface Impl
  {
    public abstract void addResultsToIntent(RemoteInput[] paramArrayOfRemoteInput, Intent paramIntent, Bundle paramBundle);
    
    public abstract Bundle getResultsFromIntent(Intent paramIntent);
  }
  
  static class ImplApi20
    implements RemoteInput.Impl
  {
    public void addResultsToIntent(RemoteInput[] paramArrayOfRemoteInput, Intent paramIntent, Bundle paramBundle)
    {
      RemoteInputCompatApi20.addResultsToIntent(paramArrayOfRemoteInput, paramIntent, paramBundle);
    }
    
    public Bundle getResultsFromIntent(Intent paramIntent)
    {
      return RemoteInputCompatApi20.getResultsFromIntent(paramIntent);
    }
  }
  
  static class ImplBase
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
  
  static class ImplJellybean
    implements RemoteInput.Impl
  {
    public void addResultsToIntent(RemoteInput[] paramArrayOfRemoteInput, Intent paramIntent, Bundle paramBundle)
    {
      RemoteInputCompatJellybean.addResultsToIntent(paramArrayOfRemoteInput, paramIntent, paramBundle);
    }
    
    public Bundle getResultsFromIntent(Intent paramIntent)
    {
      return RemoteInputCompatJellybean.getResultsFromIntent(paramIntent);
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.RemoteInput
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */