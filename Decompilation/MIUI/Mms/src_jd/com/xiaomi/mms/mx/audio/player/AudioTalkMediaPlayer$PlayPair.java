package com.xiaomi.mms.mx.audio.player;

public class AudioTalkMediaPlayer$PlayPair
{
  public long attId;
  public String localPath;
  public boolean mMarkAsHeard;
  public MediaPlayerObserver mObserver;
  public String mTargetAccountName;
  public long msgId;
  
  public AudioTalkMediaPlayer$PlayPair(long paramLong1, long paramLong2, String paramString1, String paramString2, MediaPlayerObserver paramMediaPlayerObserver, boolean paramBoolean)
  {
    msgId = paramLong1;
    attId = paramLong2;
    localPath = paramString1;
    mTargetAccountName = paramString2;
    mObserver = paramMediaPlayerObserver;
    mMarkAsHeard = paramBoolean;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof PlayPair)) {}
    while (msgId != msgId) {
      return false;
    }
    return true;
  }
  
  public int hashCode()
  {
    return (int)msgId;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.audio.player.AudioTalkMediaPlayer.PlayPair
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */