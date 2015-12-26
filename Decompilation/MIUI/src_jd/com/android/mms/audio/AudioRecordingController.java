package com.android.mms.audio;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLayoutChangeListener;
import android.view.View.OnTouchListener;
import android.view.ViewStub;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.mms.MmsApp;
import com.android.mms.data.Conversation;
import com.android.mms.data.WorkingMessage.MessageStatusListener;
import com.android.mms.util.MSimUtils;
import com.android.mms.util.VibratorManager;
import com.xiaomi.mms.mx.audio.player.AudioTalkMediaPlayer;
import com.xiaomi.mms.mx.audio.player.XMAudioRecorder;

public class AudioRecordingController
{
  private Button mAudioBtn;
  private float mAudioBtnDiffX;
  private Button mAudioBtnRemind;
  private float mAudioBtnX;
  private float mAudioBtnY;
  private ImageView mAudioCancel;
  private ViewStub mBottomStub;
  private View mBottomView;
  private ViewStub mBtnStub;
  private AudioBtnTouchRunnable mBtnTouch = new AudioBtnTouchRunnable(this);
  private Context mContext;
  private Conversation mConversation;
  private boolean mIsAudioAvailable;
  private boolean mIsCancelled;
  private boolean mIsSendingMx2 = false;
  private View.OnLayoutChangeListener mLayoutChangeListener = new View.OnLayoutChangeListener()
  {
    public void onLayoutChange(View paramAnonymousView, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3, int paramAnonymousInt4, int paramAnonymousInt5, int paramAnonymousInt6, int paramAnonymousInt7, int paramAnonymousInt8)
    {
      mSendBtn.getLocationOnScreen(mSendBtnLocation);
      if (mAudioBtnRemind.getVisibility() == 0)
      {
        mAudioBtnRemind.setY(mSendBtnLocation[1]);
        return;
      }
      mAudioBtn.setY(mSendBtnLocation[1]);
    }
  };
  private WorkingMessage.MessageStatusListener mListener;
  private View mPanelBtn;
  private View mParent;
  private int mProgressMaxWidth;
  private Handler mRecordHandler = new Handler()
  {
    private boolean mIsWarning;
    
    public void handleMessage(Message paramAnonymousMessage)
    {
      switch (what)
      {
      case 0: 
      default: 
      case 1: 
      case 2: 
        do
        {
          return;
          VibratorManager.vibrate(mContext, 20);
          AudioHelper.gainFocus();
          AudioTalkMediaPlayer.getInstance(MmsApp.getApp()).stop();
          AudioRecordingController.access$1002(AudioRecordingController.this, false);
          mIsWarning = false;
          return;
          mRecordingProgressView.addVolumes(((Integer)obj).intValue());
          if (mRecorder.getDuration() >= 60000)
          {
            AudioRecordingController.access$1002(AudioRecordingController.this, false);
            mRecorder.stop();
            AudioRecordingController.this.endRecord();
            return;
          }
        } while (mRecorder.getDuration() <= 50000);
        if (!mIsWarning)
        {
          VibratorManager.vibrate(mContext, 20);
          mIsWarning = true;
        }
        int i = (60000 - mRecorder.getDuration()) / 1000;
        paramAnonymousMessage = String.format(MmsApp.getApp().getString(2131362355), new Object[] { Integer.valueOf(i) });
        mTextNotify.setText(paramAnonymousMessage);
        mTextNotify.setVisibility(0);
        return;
      case 3: 
        AudioHelper.releaseFocus();
        if (isCancelled())
        {
          mRecorder.clear();
          return;
        }
        if (mRecorder.getDuration() < 1000)
        {
          mRecorder.clear();
          return;
        }
        AudioHelper.sendRecordedAudio(mRecorder.getAudioPath(), mRecorder.getDuration(), mThreadId, mIsAudioAvailable, mListener, mSlotId, mConversation);
        AudioRecordingController.access$1902(AudioRecordingController.this, true);
        return;
      }
      mRecorder.stop();
      mRecorder.clear();
      AudioRecordingController.this.endRecord();
      Toast.makeText(MmsApp.getApp(), 2131362359, 1).show();
      Log.e("AudioRecordingController.RICH", "controller audio failed " + what);
    }
  };
  private XMAudioRecorder mRecorder;
  private RecordingProgressView mRecordingProgressView;
  private int[] mRecordingTextViewLoc = new int[2];
  private Runnable mRemindRunnable = new Runnable()
  {
    public void run()
    {
      AudioRecordingController.this.endRecordAnimation();
    }
  };
  private TextView mRemindText;
  private View.OnTouchListener mRemindTouchListener = new View.OnTouchListener()
  {
    public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
    {
      if (!isRecording()) {
        switch (paramAnonymousMotionEvent.getAction())
        {
        }
      }
      for (;;)
      {
        mAudioBtn.setOnTouchListener(null);
        return false;
        mBtnTouch.setEvent(paramAnonymousMotionEvent);
        mRecordHandler.postDelayed(mBtnTouch, 200L);
        continue;
        if (!mBtnTouch.isRunning())
        {
          mRecordHandler.removeCallbacks(mBtnTouch);
          mRecordHandler.removeCallbacks(mRemindRunnable);
          AudioRecordingController.this.endRecordAnimation();
        }
      }
    }
  };
  private View mSendBtn;
  private int[] mSendBtnLocation = new int[2];
  private boolean mShouldBtnStop;
  private int mSlotId;
  private Runnable mStopRecordRunnable = new Runnable()
  {
    public void run()
    {
      mRecorder.stop();
    }
  };
  private TextView mTextCancel;
  private View mTextEditor;
  private float mTextLocationX;
  private TextView mTextNotify;
  private long mThreadId;
  
  public AudioRecordingController(WorkingMessage.MessageStatusListener paramMessageStatusListener, boolean paramBoolean, View paramView1, ViewStub paramViewStub1, ViewStub paramViewStub2, View paramView2, View paramView3, View paramView4, Context paramContext)
  {
    mListener = paramMessageStatusListener;
    mIsAudioAvailable = paramBoolean;
    mBottomStub = paramViewStub1;
    mBtnStub = paramViewStub2;
    mParent = paramView1;
    mSendBtn = paramView2;
    mPanelBtn = paramView3;
    mTextEditor = paramView4;
    mContext = paramContext;
    mRecorder = new XMAudioRecorder(mRecordHandler, paramContext);
    paramMessageStatusListener = mBtnStub.inflate();
    mAudioBtn = ((Button)paramMessageStatusListener.findViewById(2131820554));
    mAudioBtnRemind = ((Button)paramMessageStatusListener.findViewById(2131820555));
    mAudioBtnRemind.setOnTouchListener(mRemindTouchListener);
    paramMessageStatusListener.addOnLayoutChangeListener(mLayoutChangeListener);
  }
  
  private void endRecord()
  {
    mAudioBtn.setVisibility(4);
    mAudioBtnRemind.setVisibility(4);
    mSendBtn.setVisibility(0);
    mRecordingProgressView.setVisibility(4);
    showTextBottom();
  }
  
  private void endRecordAnimation()
  {
    mAudioBtn.setVisibility(4);
    mAudioBtnRemind.setVisibility(4);
    mSendBtn.setVisibility(0);
    mRecordingProgressView.setVisibility(4);
    showTextBottom();
    AlphaAnimation localAlphaAnimation = new AlphaAnimation(0.0F, 1.0F);
    localAlphaAnimation.setFillAfter(true);
    localAlphaAnimation.setDuration(300L);
    mPanelBtn.setAnimation(localAlphaAnimation);
    mTextEditor.setAnimation(localAlphaAnimation);
    localAlphaAnimation.startNow();
  }
  
  private void hideTextBottom()
  {
    mPanelBtn.setVisibility(8);
    mPanelBtn.clearAnimation();
    mTextEditor.setVisibility(8);
    mTextEditor.clearAnimation();
    mSendBtn.setVisibility(4);
  }
  
  private void initAudioBtn(MotionEvent paramMotionEvent)
  {
    if (mAudioBtnRemind.getVisibility() == 0) {
      mAudioBtnRemind.getLocationOnScreen(mSendBtnLocation);
    }
    for (;;)
    {
      mSendBtn.setVisibility(4);
      mAudioBtnY = mSendBtnLocation[1];
      float f = mSendBtnLocation[0] + mSendBtn.getWidth() / 2 - mAudioBtn.getWidth() / 2;
      mAudioBtnX = f;
      mTextLocationX = f;
      mAudioBtnDiffX = (mSendBtnLocation[0] + mSendBtn.getWidth() / 2 - paramMotionEvent.getRawX());
      mAudioBtn.setX(mAudioBtnX);
      mAudioBtn.setY(mAudioBtnY);
      mAudioBtn.setBackgroundResource(2130837583);
      mAudioBtn.setVisibility(0);
      mAudioBtnRemind.setVisibility(4);
      mTextNotify.setVisibility(4);
      mShouldBtnStop = true;
      return;
      mSendBtn.getLocationOnScreen(mSendBtnLocation);
    }
  }
  
  private void initBottomStub()
  {
    if (mBottomView == null)
    {
      View localView = mParent.findViewById(2131820784);
      mBottomStub.getLayoutParams().height = localView.getHeight();
      mBottomView = mBottomStub.inflate();
      mRecordingProgressView = ((RecordingProgressView)mBottomView.findViewById(2131820678));
      mTextCancel = ((TextView)mBottomView.findViewById(2131820680));
      mTextNotify = ((TextView)mBottomView.findViewById(2131820679));
      mRemindText = ((TextView)mBottomView.findViewById(2131820676));
      mAudioCancel = ((ImageView)mBottomView.findViewById(2131820677));
    }
  }
  
  private boolean isProgressViewVisible()
  {
    return (mRecordingProgressView != null) && (mRecordingProgressView.getVisibility() == 0);
  }
  
  private void showAudioBottom()
  {
    hideTextBottom();
    mBottomStub.setVisibility(0);
    mTextCancel.setVisibility(0);
    mRecordingProgressView.reset();
    mRecordingProgressView.setVisibility(0);
    mAudioCancel.setVisibility(0);
    mRemindText.clearAnimation();
    mRemindText.setVisibility(8);
  }
  
  private void showTextBottom()
  {
    mBottomStub.setVisibility(8);
    mSendBtn.setVisibility(0);
    mPanelBtn.setVisibility(0);
    mPanelBtn.clearAnimation();
    mTextEditor.setVisibility(0);
    mTextEditor.requestFocus();
    mTextEditor.clearAnimation();
  }
  
  private void startRecording()
  {
    AudioHelper.getAudioDir(true);
    String str = AudioHelper.getAudioPath();
    mRecorder.init(str);
    mRecorder.start();
  }
  
  public void cancel()
  {
    mIsCancelled = true;
    mAudioBtn.setBackgroundResource(2130837573);
  }
  
  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent, int paramInt, boolean paramBoolean)
  {
    if (isProgressViewVisible())
    {
      if (paramMotionEvent.getAction() != 2) {
        break label256;
      }
      if (mProgressMaxWidth != 0) {
        break label46;
      }
      mTextCancel.getLocationOnScreen(mRecordingTextViewLoc);
      mProgressMaxWidth = mRecordingProgressView.getWidth();
    }
    label46:
    label256:
    do
    {
      do
      {
        return false;
        if ((mShouldBtnStop) && (Math.abs(paramMotionEvent.getRawX() + mAudioBtnDiffX - mAudioBtn.getWidth() / 2 - mTextLocationX) > AudioHelper.convertdipTopx(33))) {
          mShouldBtnStop = false;
        }
      } while (mShouldBtnStop);
      mAudioBtnX = (paramMotionEvent.getRawX() + mAudioBtnDiffX);
      if (mAudioBtnX < mBottomView.getWidth() / 2 + AudioHelper.convertdipTopx(33))
      {
        mAudioBtnX = (mBottomView.getWidth() / 2);
        if (!isCancelled()) {
          cancel();
        }
      }
      for (;;)
      {
        mAudioBtnX -= mAudioBtn.getWidth() / 2;
        mAudioBtn.setX(mAudioBtnX);
        if (mAudioBtnX >= mBottomView.getWidth() - AudioHelper.convertdipTopx(70)) {
          break;
        }
        mTextCancel.setVisibility(8);
        return false;
        mIsCancelled = false;
        mAudioBtn.setBackgroundResource(2130837583);
      }
      mTextCancel.setVisibility(0);
      return false;
      if (paramMotionEvent.getAction() == 1)
      {
        endRecord();
        if (paramInt < 0)
        {
          mSlotId = MSimUtils.getInsertedSlotId();
          if (mSlotId >= 0) {}
        }
        for (mSlotId = 0;; mSlotId = paramInt)
        {
          mIsAudioAvailable = paramBoolean;
          mRecordHandler.postDelayed(mStopRecordRunnable, 500L);
          return false;
        }
      }
    } while (paramMotionEvent.getPointerCount() <= 1);
    return true;
  }
  
  public boolean isCancelled()
  {
    return mIsCancelled;
  }
  
  public boolean isRecording()
  {
    return mRecorder.isRecording();
  }
  
  public boolean isSendingMx2()
  {
    return mIsSendingMx2;
  }
  
  public void release()
  {
    if (mRecorder.isRecording())
    {
      mIsCancelled = true;
      mRecorder.stop();
      mRecorder.clear();
      endRecord();
      mRecordHandler.removeCallbacks(mStopRecordRunnable);
    }
    VibratorManager.cancel();
  }
  
  public void resetIsSendingMx2()
  {
    mIsSendingMx2 = false;
  }
  
  public void setConversation(Conversation paramConversation)
  {
    mConversation = paramConversation;
  }
  
  public void setThreadId(long paramLong)
  {
    mThreadId = paramLong;
  }
  
  public void showRemind()
  {
    initBottomStub();
    mSendBtn.getLocationOnScreen(mSendBtnLocation);
    hideTextBottom();
    mBottomStub.setVisibility(0);
    mTextCancel.setVisibility(8);
    mRecordingProgressView.setVisibility(8);
    mAudioCancel.setVisibility(8);
    mRemindText.setVisibility(0);
    mAudioBtnRemind.setX(mSendBtnLocation[0]);
    mAudioBtnRemind.setY(mSendBtnLocation[1]);
    mAudioBtnRemind.setVisibility(0);
    AlphaAnimation localAlphaAnimation = new AlphaAnimation(0.0F, 1.0F);
    localAlphaAnimation.setFillAfter(true);
    localAlphaAnimation.setDuration(300L);
    mRemindText.setAnimation(localAlphaAnimation);
    localAlphaAnimation.startNow();
    mRecordHandler.postDelayed(mRemindRunnable, 1500L);
  }
  
  public void startRecord(MotionEvent paramMotionEvent)
  {
    initBottomStub();
    mRecordHandler.removeCallbacks(mRemindRunnable);
    initAudioBtn(paramMotionEvent);
    showAudioBottom();
    startRecording();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.audio.AudioRecordingController
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */