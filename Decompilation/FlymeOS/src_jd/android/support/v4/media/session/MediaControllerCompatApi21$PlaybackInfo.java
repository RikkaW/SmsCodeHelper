package android.support.v4.media.session;

import android.media.AudioAttributes;
import android.media.session.MediaController.PlaybackInfo;

public class MediaControllerCompatApi21$PlaybackInfo
{
  private static final int FLAG_SCO = 4;
  private static final int STREAM_BLUETOOTH_SCO = 6;
  private static final int STREAM_SYSTEM_ENFORCED = 7;
  
  public static AudioAttributes getAudioAttributes(Object paramObject)
  {
    return ((MediaController.PlaybackInfo)paramObject).getAudioAttributes();
  }
  
  public static int getCurrentVolume(Object paramObject)
  {
    return ((MediaController.PlaybackInfo)paramObject).getCurrentVolume();
  }
  
  public static int getLegacyAudioStream(Object paramObject)
  {
    return toLegacyStreamType(getAudioAttributes(paramObject));
  }
  
  public static int getMaxVolume(Object paramObject)
  {
    return ((MediaController.PlaybackInfo)paramObject).getMaxVolume();
  }
  
  public static int getPlaybackType(Object paramObject)
  {
    return ((MediaController.PlaybackInfo)paramObject).getPlaybackType();
  }
  
  public static int getVolumeControl(Object paramObject)
  {
    return ((MediaController.PlaybackInfo)paramObject).getVolumeControl();
  }
  
  private static int toLegacyStreamType(AudioAttributes paramAudioAttributes)
  {
    int i = 3;
    if ((paramAudioAttributes.getFlags() & 0x1) == 1)
    {
      i = 7;
      return i;
    }
    if ((paramAudioAttributes.getFlags() & 0x4) == 4) {
      return 6;
    }
    switch (paramAudioAttributes.getUsage())
    {
    case 1: 
    case 11: 
    case 12: 
    case 14: 
    default: 
      return 3;
    case 2: 
      return 0;
    case 13: 
      return 1;
    case 3: 
      return 8;
    case 4: 
      return 4;
    case 6: 
      return 2;
    }
    return 5;
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.session.MediaControllerCompatApi21.PlaybackInfo
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */