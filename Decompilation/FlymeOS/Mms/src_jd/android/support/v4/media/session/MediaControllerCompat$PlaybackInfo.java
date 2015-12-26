package android.support.v4.media.session;

public final class MediaControllerCompat$PlaybackInfo
{
  public static final int PLAYBACK_TYPE_LOCAL = 1;
  public static final int PLAYBACK_TYPE_REMOTE = 2;
  private final int mAudioStream;
  private final int mCurrentVolume;
  private final int mMaxVolume;
  private final int mPlaybackType;
  private final int mVolumeControl;
  
  MediaControllerCompat$PlaybackInfo(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    mPlaybackType = paramInt1;
    mAudioStream = paramInt2;
    mVolumeControl = paramInt3;
    mMaxVolume = paramInt4;
    mCurrentVolume = paramInt5;
  }
  
  public int getAudioStream()
  {
    return mAudioStream;
  }
  
  public int getCurrentVolume()
  {
    return mCurrentVolume;
  }
  
  public int getMaxVolume()
  {
    return mMaxVolume;
  }
  
  public int getPlaybackType()
  {
    return mPlaybackType;
  }
  
  public int getVolumeControl()
  {
    return mVolumeControl;
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.session.MediaControllerCompat.PlaybackInfo
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */