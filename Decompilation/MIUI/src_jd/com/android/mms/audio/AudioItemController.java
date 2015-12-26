package com.android.mms.audio;

import android.app.Application;
import android.content.ContentUris;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.provider.Telephony.Mms;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.mms.MmsApp;
import com.android.mms.transaction.DownloadMxV2FileService;
import com.android.mms.ui.MessageItem;
import com.android.mms.ui.SimplePduDoc;
import com.android.mms.ui.SimplePduPart;
import com.android.mms.util.MSimUtils;
import com.xiaomi.mms.mx.audio.player.AudioTalkMediaPlayer;
import com.xiaomi.mms.mx.data.Attachment;
import java.io.File;
import java.util.List;

public class AudioItemController
{
  private static int sAudioBubbleWidthMax;
  private static int sAudioBubbleWidthMin;
  private static PlayAudioAsyncTask sPlayAudioAsyncTask;
  private static String sPlayingPath;
  private AnimationDrawable mAnimationDrawable;
  private Attachment mAttachment;
  private AudioItemCache mAudioItemCache;
  private ViewStub mAudioItemStub;
  private TextView mAudioText;
  private boolean mEditMode = false;
  private Handler mHandler;
  private boolean mIsMsgIn;
  private ImageView mIsReadImg;
  private ViewStub mIsReadStub;
  private View mIsReadView;
  private long mMsgId;
  private Uri mMsgPartUri;
  private int mMsgPreviewType;
  private long mMsgSimId;
  private Uri mMsgUri;
  private String mPath;
  private int mPlayTime;
  private ImageView mPlayingView;
  
  static
  {
    Resources localResources = MmsApp.getApp().getResources();
    sAudioBubbleWidthMax = localResources.getDimensionPixelSize(2131427473);
    sAudioBubbleWidthMin = localResources.getDimensionPixelSize(2131427474);
  }
  
  public AudioItemController(ViewStub paramViewStub1, ViewStub paramViewStub2, AudioItemCache paramAudioItemCache)
  {
    mAudioItemStub = paramViewStub1;
    mIsReadStub = paramViewStub2;
    mAudioItemCache = paramAudioItemCache;
  }
  
  private void hideReadImg()
  {
    if (mIsReadImg != null) {
      mIsReadImg.setVisibility(8);
    }
  }
  
  private void initAudioData(MessageItem paramMessageItem)
  {
    mAttachment = ((Attachment)paramMessageItem.getMx2Attachments().get(0));
    mMsgId = paramMessageItem.getMsgId();
    boolean bool;
    if (paramMessageItem.getBoxId() == 1)
    {
      bool = true;
      mIsMsgIn = bool;
      mMsgPreviewType = paramMessageItem.getMmsPreviewType();
      if (mAttachment != null)
      {
        mPlayTime = ((mAttachment.playTime + 500) / 1000);
        if (mMsgPreviewType != 3) {
          break label174;
        }
        paramMessageItem = paramMessageItem.getSimplePduDoc();
        if (paramMessageItem == null) {
          break label169;
        }
      }
    }
    label169:
    for (paramMessageItem = paramMessageItem.getPageAppearancePart(0).getDataUri();; paramMessageItem = null)
    {
      mMsgPartUri = paramMessageItem;
      mPath = mAudioItemCache.getMmsAudioPath(mMsgId);
      if ((mPath == null) && (mMsgPartUri != null))
      {
        mPath = AudioHelper.getMediaFilePathByUri(MmsApp.getApp(), mMsgPartUri);
        mAudioItemCache.addMmsAudioPath(mMsgId, mPath);
      }
      return;
      bool = false;
      break;
    }
    label174:
    mPath = mAttachment.getLocalPath(MmsApp.getApp(), AudioHelper.getAudioDir());
  }
  
  private void initAudioItemView()
  {
    ViewGroup.LayoutParams localLayoutParams = mAudioItemStub.getLayoutParams();
    if (mPlayTime < 10) {}
    for (width = ((sAudioBubbleWidthMax - sAudioBubbleWidthMin) / 9 * (mPlayTime - 1) + sAudioBubbleWidthMin);; width = sAudioBubbleWidthMax)
    {
      mAudioItemStub.setLayoutParams(localLayoutParams);
      mAudioItemStub.setVisibility(0);
      return;
    }
  }
  
  private void showReadImg()
  {
    if ((mIsReadImg != null) && (mAttachment != null) && (!mAttachment.mIsRead)) {
      mIsReadImg.setVisibility(0);
    }
  }
  
  public void endEditMode()
  {
    showReadImg();
    mEditMode = false;
  }
  
  public void initListItem(MessageItem paramMessageItem, Handler paramHandler)
  {
    if (mAudioItemStub == null) {
      return;
    }
    if (mPlayingView == null)
    {
      localObject = mAudioItemStub.inflate();
      mPlayingView = ((ImageView)((View)localObject).findViewById(2131820556));
      mAudioText = ((TextView)((View)localObject).findViewById(2131820557));
    }
    initAudioData(paramMessageItem);
    initAudioItemView();
    Object localObject = String.format(MmsApp.getApp().getString(2131362356), new Object[] { Integer.valueOf(mPlayTime) });
    mAudioText.setText((CharSequence)localObject);
    mMsgUri = paramMessageItem.getMessageUri();
    mMsgSimId = paramMessageItem.getSimId();
    mHandler = paramHandler;
    if (AudioTalkMediaPlayer.getInstance(MmsApp.getApp()).isPlaying(mPath)) {
      setPlayingViews();
    }
    for (;;)
    {
      mAudioItemCache.add(paramMessageItem.getMsgId(), this);
      return;
      resetPlayingViews();
      if ((mIsReadStub != null) && (mAttachment != null) && (!mAttachment.mIsRead) && (!mEditMode))
      {
        if (mIsReadImg == null)
        {
          mIsReadView = mIsReadStub.inflate();
          mIsReadImg = ((ImageView)mIsReadView.findViewById(2131820558));
        }
        mIsReadImg.setVisibility(0);
      }
      else
      {
        hideReadImg();
      }
    }
  }
  
  public boolean isDownloadingFile()
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (mAudioItemStub != null)
    {
      bool1 = bool2;
      if (mAttachment != null)
      {
        bool1 = bool2;
        if (DownloadMxV2FileService.isDownloading(mAttachment.shareId)) {
          bool1 = true;
        }
      }
    }
    return bool1;
  }
  
  public void markAsRead()
  {
    if (mAudioItemStub == null) {}
    while ((mAttachment == null) || (mAttachment.mIsRead)) {
      return;
    }
    hideReadImg();
    AudioHelper.markReadAsync(mMsgUri, mAttachment);
  }
  
  public void resetPlayingViews()
  {
    if (mAudioItemStub == null) {}
    while (mAnimationDrawable == null) {
      return;
    }
    mAnimationDrawable.stop();
    if (mIsMsgIn) {}
    for (int i = 2130837574;; i = 2130837579)
    {
      mPlayingView.setImageResource(i);
      mAnimationDrawable = null;
      return;
    }
  }
  
  public void setPlayingViews()
  {
    if (mAudioItemStub == null) {
      return;
    }
    if (mIsMsgIn) {}
    for (int i = 2130837543;; i = 2130837544)
    {
      mPlayingView.setImageResource(i);
      mAnimationDrawable = ((AnimationDrawable)mPlayingView.getDrawable());
      mAnimationDrawable.start();
      hideReadImg();
      return;
    }
  }
  
  public void startEditMode()
  {
    hideReadImg();
    mEditMode = true;
  }
  
  public void startPlayAudio()
  {
    if (mAudioItemStub == null) {}
    do
    {
      return;
      AudioTalkMediaPlayer localAudioTalkMediaPlayer = AudioTalkMediaPlayer.getInstance(MmsApp.getApp());
      localAudioTalkMediaPlayer.clearPlayList();
      if (localAudioTalkMediaPlayer.isPlaying(mPath))
      {
        localAudioTalkMediaPlayer.stop();
        return;
      }
      if (mAnimationDrawable != null)
      {
        resetPlayingViews();
        return;
      }
      if (localAudioTalkMediaPlayer.isPlaying()) {
        localAudioTalkMediaPlayer.stop();
      }
    } while ((mPath == null) && (mMsgPreviewType != 3));
    sPlayingPath = mPath;
    if (sPlayAudioAsyncTask != null) {
      sPlayAudioAsyncTask.cancel(true);
    }
    sPlayAudioAsyncTask = new PlayAudioAsyncTask(null);
    sPlayAudioAsyncTask.execute(new String[] { mPath });
  }
  
  public void unbind()
  {
    if (mAudioItemStub != null) {
      mAudioItemStub.setVisibility(8);
    }
    if (mAudioItemCache != null) {
      mAudioItemCache.remove(mMsgId);
    }
    hideReadImg();
    mMsgId = -1L;
  }
  
  private class PlayAudioAsyncTask
    extends AsyncTask<String, Void, Integer>
  {
    private PlayAudioAsyncTask() {}
    
    protected Integer doInBackground(String... paramVarArgs)
    {
      Log.v("AudioItemController.RICH", "MX2AudioPlay the spath = " + AudioItemController.sPlayingPath);
      if ((mPath != null) && (TextUtils.equals(mPath, AudioItemController.sPlayingPath)))
      {
        AudioHelper.getAudioDir(true);
        paramVarArgs = new File(mPath);
        String[] arrayOfString = paramVarArgs;
        if (!paramVarArgs.exists())
        {
          arrayOfString = paramVarArgs;
          if (mMsgPreviewType != 3)
          {
            arrayOfString = mPath.split("/");
            try
            {
              l = Long.parseLong(arrayOfString[(arrayOfString.length - 1)].split("\\.")[0]);
              if (System.currentTimeMillis() - l < 561600000L)
              {
                int i = MSimUtils.getSlotIdBySimInfoId(mMsgSimId);
                Uri localUri = ContentUris.withAppendedId(Telephony.Mms.CONTENT_URI, mMsgId);
                arrayOfString = paramVarArgs;
                if (i == -1) {
                  break label322;
                }
                DownloadMxV2FileService.startDownloadAudio(MmsApp.getApp(), mAttachment.shareId, i, localUri, mIsMsgIn, paramVarArgs.getName());
                return Integer.valueOf(3);
              }
            }
            catch (NumberFormatException paramVarArgs)
            {
              for (;;)
              {
                Log.e("AudioItemController.RICH", "MX2AudioPlay parse path to number error, mPath = " + mPath);
                long l = System.currentTimeMillis();
                paramVarArgs = AudioHelper.getAudioDir(true);
                AudioItemController.access$202(AudioItemController.this, paramVarArgs + "/" + l + ".amr");
                AudioItemController.access$102(mPath);
                paramVarArgs = new File(mPath);
              }
            }
            return Integer.valueOf(2);
          }
        }
        label322:
        if (arrayOfString.exists()) {
          return Integer.valueOf(0);
        }
        return Integer.valueOf(1);
      }
      Log.v("AudioItemController.RICH", "MX2AudioPlay play failed the audio path is " + mPath + ", and spath = " + AudioItemController.sPlayingPath);
      return Integer.valueOf(-1);
    }
    
    protected void onPostExecute(Integer paramInteger)
    {
      if ((mPath != null) && (mPath.equals(AudioItemController.sPlayingPath))) {
        switch (paramInteger.intValue())
        {
        default: 
          Toast.makeText(MmsApp.getApp(), 2131362357, 0).show();
        }
      }
      for (;;)
      {
        super.onPostExecute(paramInteger);
        return;
        AudioSensorManager localAudioSensorManager = new AudioSensorManager(mHandler);
        AudioHelper.playAudio(mMsgId, mPath, localAudioSensorManager, mAudioItemCache);
        continue;
        Toast.makeText(MmsApp.getApp(), 2131362365, 0).show();
        continue;
        Toast.makeText(MmsApp.getApp(), 2131362360, 0).show();
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.audio.AudioItemController
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */