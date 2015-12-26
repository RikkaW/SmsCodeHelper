package com.android.mms.audio;

import java.util.HashMap;

public class AudioItemCache
{
  private HashMap<Long, AudioItemController> mItemCache = new HashMap();
  private HashMap<Long, String> mMmsAudioPathCache = new HashMap();
  
  public void add(long paramLong, AudioItemController paramAudioItemController)
  {
    mItemCache.put(Long.valueOf(paramLong), paramAudioItemController);
  }
  
  public void addMmsAudioPath(long paramLong, String paramString)
  {
    mMmsAudioPathCache.put(Long.valueOf(paramLong), paramString);
  }
  
  public AudioItemController getItemController(long paramLong)
  {
    return (AudioItemController)mItemCache.get(Long.valueOf(paramLong));
  }
  
  public String getMmsAudioPath(long paramLong)
  {
    return (String)mMmsAudioPathCache.get(Long.valueOf(paramLong));
  }
  
  public void remove(long paramLong)
  {
    mItemCache.remove(Long.valueOf(paramLong));
  }
  
  public void removeMmsAudioPath(long paramLong)
  {
    mMmsAudioPathCache.remove(Long.valueOf(paramLong));
  }
}

/* Location:
 * Qualified Name:     com.android.mms.audio.AudioItemCache
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */