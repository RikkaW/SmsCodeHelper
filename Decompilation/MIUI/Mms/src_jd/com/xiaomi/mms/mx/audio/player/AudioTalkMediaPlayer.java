package com.xiaomi.mms.mx.audio.player;

import android.content.Context;
import android.media.AudioManager;
import android.os.Handler;
import java.util.LinkedList;
import java.util.Observable;

public class AudioTalkMediaPlayer
{
  private static AudioTalkMediaPlayer instance;
  private XMAudioPlayer mAudioPlayer;
  private Context mContext;
  private LinkedList<PlayPair> mPlayList = new LinkedList();
  
  private AudioTalkMediaPlayer(Context paramContext)
  {
    mContext = paramContext;
  }
  
  /* Error */
  public static AudioTalkMediaPlayer getInstance(Context paramContext)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 35	com/xiaomi/mms/mx/audio/player/AudioTalkMediaPlayer:instance	Lcom/xiaomi/mms/mx/audio/player/AudioTalkMediaPlayer;
    //   6: ifnull +12 -> 18
    //   9: getstatic 35	com/xiaomi/mms/mx/audio/player/AudioTalkMediaPlayer:instance	Lcom/xiaomi/mms/mx/audio/player/AudioTalkMediaPlayer;
    //   12: astore_0
    //   13: ldc 2
    //   15: monitorexit
    //   16: aload_0
    //   17: areturn
    //   18: new 2	com/xiaomi/mms/mx/audio/player/AudioTalkMediaPlayer
    //   21: dup
    //   22: aload_0
    //   23: invokespecial 37	com/xiaomi/mms/mx/audio/player/AudioTalkMediaPlayer:<init>	(Landroid/content/Context;)V
    //   26: putstatic 35	com/xiaomi/mms/mx/audio/player/AudioTalkMediaPlayer:instance	Lcom/xiaomi/mms/mx/audio/player/AudioTalkMediaPlayer;
    //   29: getstatic 35	com/xiaomi/mms/mx/audio/player/AudioTalkMediaPlayer:instance	Lcom/xiaomi/mms/mx/audio/player/AudioTalkMediaPlayer;
    //   32: astore_0
    //   33: goto -20 -> 13
    //   36: astore_0
    //   37: ldc 2
    //   39: monitorexit
    //   40: aload_0
    //   41: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	42	0	paramContext	Context
    // Exception table:
    //   from	to	target	type
    //   3	13	36	finally
    //   18	33	36	finally
  }
  
  /* Error */
  public void addToPlayList(long paramLong1, long paramLong2, int paramInt, String paramString1, String paramString2, MediaPlayerObserver paramMediaPlayerObserver, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 28	com/xiaomi/mms/mx/audio/player/AudioTalkMediaPlayer:mPlayList	Ljava/util/LinkedList;
    //   6: invokevirtual 43	java/util/LinkedList:iterator	()Ljava/util/Iterator;
    //   9: astore 10
    //   11: aload 10
    //   13: invokeinterface 49 1 0
    //   18: ifeq +35 -> 53
    //   21: aload 10
    //   23: invokeinterface 53 1 0
    //   28: checkcast 8	com/xiaomi/mms/mx/audio/player/AudioTalkMediaPlayer$PlayPair
    //   31: astore 11
    //   33: aload 11
    //   35: getfield 57	com/xiaomi/mms/mx/audio/player/AudioTalkMediaPlayer$PlayPair:msgId	J
    //   38: lload_1
    //   39: lcmp
    //   40: ifne -29 -> 11
    //   43: aload 11
    //   45: aload 6
    //   47: putfield 61	com/xiaomi/mms/mx/audio/player/AudioTalkMediaPlayer$PlayPair:localPath	Ljava/lang/String;
    //   50: aload_0
    //   51: monitorexit
    //   52: return
    //   53: aload_0
    //   54: getfield 28	com/xiaomi/mms/mx/audio/player/AudioTalkMediaPlayer:mPlayList	Ljava/util/LinkedList;
    //   57: new 8	com/xiaomi/mms/mx/audio/player/AudioTalkMediaPlayer$PlayPair
    //   60: dup
    //   61: lload_1
    //   62: lload_3
    //   63: aload 6
    //   65: aload 7
    //   67: aload 8
    //   69: iload 9
    //   71: invokespecial 64	com/xiaomi/mms/mx/audio/player/AudioTalkMediaPlayer$PlayPair:<init>	(JJLjava/lang/String;Ljava/lang/String;Lcom/xiaomi/mms/mx/audio/player/MediaPlayerObserver;Z)V
    //   74: invokevirtual 68	java/util/LinkedList:add	(Ljava/lang/Object;)Z
    //   77: pop
    //   78: goto -28 -> 50
    //   81: astore 6
    //   83: aload_0
    //   84: monitorexit
    //   85: aload 6
    //   87: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	88	0	this	AudioTalkMediaPlayer
    //   0	88	1	paramLong1	long
    //   0	88	3	paramLong2	long
    //   0	88	5	paramInt	int
    //   0	88	6	paramString1	String
    //   0	88	7	paramString2	String
    //   0	88	8	paramMediaPlayerObserver	MediaPlayerObserver
    //   0	88	9	paramBoolean	boolean
    //   9	13	10	localIterator	java.util.Iterator
    //   31	13	11	localPlayPair	PlayPair
    // Exception table:
    //   from	to	target	type
    //   2	11	81	finally
    //   11	50	81	finally
    //   53	78	81	finally
  }
  
  public void clearPlayList()
  {
    try
    {
      mPlayList.clear();
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /* Error */
  public boolean hasNext()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 28	com/xiaomi/mms/mx/audio/player/AudioTalkMediaPlayer:mPlayList	Ljava/util/LinkedList;
    //   6: invokevirtual 75	java/util/LinkedList:isEmpty	()Z
    //   9: istore_1
    //   10: iload_1
    //   11: ifne +9 -> 20
    //   14: iconst_1
    //   15: istore_1
    //   16: aload_0
    //   17: monitorexit
    //   18: iload_1
    //   19: ireturn
    //   20: iconst_0
    //   21: istore_1
    //   22: goto -6 -> 16
    //   25: astore_2
    //   26: aload_0
    //   27: monitorexit
    //   28: aload_2
    //   29: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	30	0	this	AudioTalkMediaPlayer
    //   9	13	1	bool	boolean
    //   25	4	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	10	25	finally
  }
  
  public boolean isPlaying()
  {
    return (mAudioPlayer != null) && (!mAudioPlayer.isStopped());
  }
  
  public boolean isPlaying(String paramString)
  {
    return (mAudioPlayer != null) && (mAudioPlayer.isPlaying(paramString));
  }
  
  /* Error */
  public void playNext()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 28	com/xiaomi/mms/mx/audio/player/AudioTalkMediaPlayer:mPlayList	Ljava/util/LinkedList;
    //   6: invokevirtual 90	java/util/LinkedList:peek	()Ljava/lang/Object;
    //   9: checkcast 8	com/xiaomi/mms/mx/audio/player/AudioTalkMediaPlayer$PlayPair
    //   12: getfield 61	com/xiaomi/mms/mx/audio/player/AudioTalkMediaPlayer$PlayPair:localPath	Ljava/lang/String;
    //   15: astore_1
    //   16: aload_1
    //   17: ifnonnull +6 -> 23
    //   20: aload_0
    //   21: monitorexit
    //   22: return
    //   23: aload_0
    //   24: getfield 28	com/xiaomi/mms/mx/audio/player/AudioTalkMediaPlayer:mPlayList	Ljava/util/LinkedList;
    //   27: invokevirtual 93	java/util/LinkedList:poll	()Ljava/lang/Object;
    //   30: checkcast 8	com/xiaomi/mms/mx/audio/player/AudioTalkMediaPlayer$PlayPair
    //   33: astore_1
    //   34: new 6	com/xiaomi/mms/mx/audio/player/AudioTalkMediaPlayer$1
    //   37: dup
    //   38: aload_0
    //   39: aconst_null
    //   40: aload_1
    //   41: invokespecial 96	com/xiaomi/mms/mx/audio/player/AudioTalkMediaPlayer$1:<init>	(Lcom/xiaomi/mms/mx/audio/player/AudioTalkMediaPlayer;Landroid/os/Handler;Lcom/xiaomi/mms/mx/audio/player/AudioTalkMediaPlayer$PlayPair;)V
    //   44: astore_2
    //   45: aload_0
    //   46: new 80	com/xiaomi/mms/mx/audio/player/XMAudioPlayer
    //   49: dup
    //   50: aload_1
    //   51: getfield 61	com/xiaomi/mms/mx/audio/player/AudioTalkMediaPlayer$PlayPair:localPath	Ljava/lang/String;
    //   54: aload_2
    //   55: invokespecial 99	com/xiaomi/mms/mx/audio/player/XMAudioPlayer:<init>	(Ljava/lang/String;Lcom/xiaomi/mms/mx/audio/player/MediaPlayerObserver;)V
    //   58: putfield 78	com/xiaomi/mms/mx/audio/player/AudioTalkMediaPlayer:mAudioPlayer	Lcom/xiaomi/mms/mx/audio/player/XMAudioPlayer;
    //   61: aload_0
    //   62: getfield 78	com/xiaomi/mms/mx/audio/player/AudioTalkMediaPlayer:mAudioPlayer	Lcom/xiaomi/mms/mx/audio/player/XMAudioPlayer;
    //   65: invokevirtual 102	com/xiaomi/mms/mx/audio/player/XMAudioPlayer:start	()V
    //   68: goto -48 -> 20
    //   71: astore_1
    //   72: aload_0
    //   73: monitorexit
    //   74: aload_1
    //   75: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	76	0	this	AudioTalkMediaPlayer
    //   15	36	1	localObject1	Object
    //   71	4	1	localObject2	Object
    //   44	11	2	local1	1
    // Exception table:
    //   from	to	target	type
    //   2	16	71	finally
    //   23	68	71	finally
  }
  
  public void setEarphoneStatus(boolean paramBoolean)
  {
    setEarphoneStatus(paramBoolean, false);
  }
  
  public void setEarphoneStatus(boolean paramBoolean1, boolean paramBoolean2)
  {
    AudioManager localAudioManager;
    if (((mAudioPlayer != null) && (!mAudioPlayer.isStopped())) || (paramBoolean2))
    {
      localAudioManager = (AudioManager)mContext.getSystemService("audio");
      if (paramBoolean1)
      {
        localAudioManager.setMode(2);
        localAudioManager.setSpeakerphoneOn(false);
      }
    }
    else
    {
      return;
    }
    localAudioManager.setMode(0);
    localAudioManager.setSpeakerphoneOn(true);
  }
  
  public void stop()
  {
    if ((mAudioPlayer != null) && (!mAudioPlayer.isStopped()))
    {
      mAudioPlayer.stopPlay();
      mAudioPlayer = null;
    }
  }
  
  public static class PlayPair
  {
    public long attId;
    public String localPath;
    public boolean mMarkAsHeard;
    public MediaPlayerObserver mObserver;
    public String mTargetAccountName;
    public long msgId;
    
    public PlayPair(long paramLong1, long paramLong2, String paramString1, String paramString2, MediaPlayerObserver paramMediaPlayerObserver, boolean paramBoolean)
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
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.audio.player.AudioTalkMediaPlayer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */