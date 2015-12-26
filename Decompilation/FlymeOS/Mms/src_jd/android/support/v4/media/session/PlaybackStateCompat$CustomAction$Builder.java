package android.support.v4.media.session;

import android.os.Bundle;
import android.text.TextUtils;

public final class PlaybackStateCompat$CustomAction$Builder
{
  private final String mAction;
  private Bundle mExtras;
  private final int mIcon;
  private final CharSequence mName;
  
  public PlaybackStateCompat$CustomAction$Builder(String paramString, CharSequence paramCharSequence, int paramInt)
  {
    if (TextUtils.isEmpty(paramString)) {
      throw new IllegalArgumentException("You must specify an action to build a CustomAction.");
    }
    if (TextUtils.isEmpty(paramCharSequence)) {
      throw new IllegalArgumentException("You must specify a name to build a CustomAction.");
    }
    if (paramInt == 0) {
      throw new IllegalArgumentException("You must specify an icon resource id to build a CustomAction.");
    }
    mAction = paramString;
    mName = paramCharSequence;
    mIcon = paramInt;
  }
  
  public PlaybackStateCompat.CustomAction build()
  {
    return new PlaybackStateCompat.CustomAction(mAction, mName, mIcon, mExtras, null);
  }
  
  public Builder setExtras(Bundle paramBundle)
  {
    mExtras = paramBundle;
    return this;
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.session.PlaybackStateCompat.CustomAction.Builder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */