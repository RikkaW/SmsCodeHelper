package com.xiaomi.mms.mx.audio.player;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.text.TextUtils;
import android.util.Log;
import java.io.IOException;

public class XMAudioPlayer
  extends Thread
{
  private final String mAudioPath;
  private MediaPlayer mMediaPlayer;
  private Object mMediaPlayerLock = new Object();
  private PlayerObservable mObservable;
  private boolean mStopPlay = false;
  
  public XMAudioPlayer(String paramString, MediaPlayerObserver paramMediaPlayerObserver)
  {
    mAudioPath = paramString;
    mObservable = new PlayerObservable();
    mObservable.addObserver(paramMediaPlayerObserver);
  }
  
  private void doStop()
  {
    mStopPlay = true;
    sendPlayStatusMessage(3);
    releaseMediaPlayer();
    mObservable.deleteObservers();
  }
  
  private void init()
  {
    synchronized (mMediaPlayerLock)
    {
      mMediaPlayer = new MediaPlayer();
      return;
    }
  }
  
  private void play()
  {
    init();
    sendPlayStatusMessage(5);
    try
    {
      synchronized (mMediaPlayerLock)
      {
        mMediaPlayer.setDataSource(mAudioPath);
        mMediaPlayer.prepare();
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
        {
          public void onCompletion(MediaPlayer paramAnonymousMediaPlayer)
          {
            XMAudioPlayer.this.doStop();
          }
        });
        sendPlayStatusMessage(2);
        startMediaPlayer();
        return;
      }
      return;
    }
    catch (IOException localIOException)
    {
      doStop();
      Log.e("XMAudioPlayer", localIOException.toString());
    }
  }
  
  private void releaseMediaPlayer()
  {
    synchronized (mMediaPlayerLock)
    {
      if (mMediaPlayer != null)
      {
        mMediaPlayer.release();
        mMediaPlayer = null;
      }
      return;
    }
  }
  
  private void sendPlayStatusMessage(int paramInt)
  {
    mObservable.notifyObservers(new PlayerStatus(paramInt));
  }
  
  private void startMediaPlayer()
  {
    synchronized (mMediaPlayerLock)
    {
      if (mMediaPlayer != null) {
        mMediaPlayer.start();
      }
      return;
    }
  }
  
  private void stopMediaPlayer()
  {
    synchronized (mMediaPlayerLock)
    {
      if (mMediaPlayer != null) {
        mMediaPlayer.stop();
      }
      return;
    }
  }
  
  public boolean isPlaying(String paramString)
  {
    return (!isStopped()) && (TextUtils.equals(paramString, mAudioPath));
  }
  
  public boolean isStopped()
  {
    return mStopPlay;
  }
  
  public void run()
  {
    sendPlayStatusMessage(1);
    play();
  }
  
  public void stopPlay()
  {
    stopMediaPlayer();
    doStop();
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.audio.player.XMAudioPlayer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */