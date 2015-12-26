package android.support.v4.media.session;

import android.os.Bundle;
import android.os.SystemClock;
import java.util.ArrayList;
import java.util.List;

public final class PlaybackStateCompat$Builder
{
  private long mActions;
  private long mActiveItemId = -1L;
  private long mBufferedPosition;
  private final List<PlaybackStateCompat.CustomAction> mCustomActions = new ArrayList();
  private CharSequence mErrorMessage;
  private Bundle mExtras;
  private long mPosition;
  private float mRate;
  private int mState;
  private long mUpdateTime;
  
  public PlaybackStateCompat$Builder() {}
  
  public PlaybackStateCompat$Builder(PlaybackStateCompat paramPlaybackStateCompat)
  {
    mState = PlaybackStateCompat.access$300(paramPlaybackStateCompat);
    mPosition = PlaybackStateCompat.access$400(paramPlaybackStateCompat);
    mRate = PlaybackStateCompat.access$500(paramPlaybackStateCompat);
    mUpdateTime = PlaybackStateCompat.access$600(paramPlaybackStateCompat);
    mBufferedPosition = PlaybackStateCompat.access$700(paramPlaybackStateCompat);
    mActions = PlaybackStateCompat.access$800(paramPlaybackStateCompat);
    mErrorMessage = PlaybackStateCompat.access$900(paramPlaybackStateCompat);
    if (PlaybackStateCompat.access$1000(paramPlaybackStateCompat) != null) {
      mCustomActions.addAll(PlaybackStateCompat.access$1000(paramPlaybackStateCompat));
    }
    mActiveItemId = PlaybackStateCompat.access$1100(paramPlaybackStateCompat);
    mExtras = PlaybackStateCompat.access$1200(paramPlaybackStateCompat);
  }
  
  public Builder addCustomAction(PlaybackStateCompat.CustomAction paramCustomAction)
  {
    if (paramCustomAction == null) {
      throw new IllegalArgumentException("You may not add a null CustomAction to PlaybackStateCompat.");
    }
    mCustomActions.add(paramCustomAction);
    return this;
  }
  
  public Builder addCustomAction(String paramString1, String paramString2, int paramInt)
  {
    return addCustomAction(new PlaybackStateCompat.CustomAction(paramString1, paramString2, paramInt, null, null));
  }
  
  public PlaybackStateCompat build()
  {
    return new PlaybackStateCompat(mState, mPosition, mBufferedPosition, mRate, mActions, mErrorMessage, mUpdateTime, mCustomActions, mActiveItemId, mExtras, null);
  }
  
  public Builder setActions(long paramLong)
  {
    mActions = paramLong;
    return this;
  }
  
  public Builder setActiveQueueItemId(long paramLong)
  {
    mActiveItemId = paramLong;
    return this;
  }
  
  public Builder setBufferedPosition(long paramLong)
  {
    mBufferedPosition = paramLong;
    return this;
  }
  
  public Builder setErrorMessage(CharSequence paramCharSequence)
  {
    mErrorMessage = paramCharSequence;
    return this;
  }
  
  public Builder setExtras(Bundle paramBundle)
  {
    mExtras = paramBundle;
    return this;
  }
  
  public Builder setState(int paramInt, long paramLong, float paramFloat)
  {
    return setState(paramInt, paramLong, paramFloat, SystemClock.elapsedRealtime());
  }
  
  public Builder setState(int paramInt, long paramLong1, float paramFloat, long paramLong2)
  {
    mState = paramInt;
    mPosition = paramLong1;
    mUpdateTime = paramLong2;
    mRate = paramFloat;
    return this;
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.session.PlaybackStateCompat.Builder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */