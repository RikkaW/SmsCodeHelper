/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.os.Handler
 *  android.os.Message
 *  android.util.Log
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.View$OnLayoutChangeListener
 *  android.view.View$OnTouchListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewStub
 *  android.view.animation.AlphaAnimation
 *  android.view.animation.Animation
 *  android.widget.Button
 *  android.widget.ImageView
 *  android.widget.TextView
 *  android.widget.Toast
 *  java.lang.Integer
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 */
package com.android.mms.audio;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.mms.MmsApp;
import com.android.mms.audio.AudioBtnTouchRunnable;
import com.android.mms.audio.AudioHelper;
import com.android.mms.audio.RecordingProgressView;
import com.android.mms.data.Conversation;
import com.android.mms.data.WorkingMessage;
import com.android.mms.util.MSimUtils;
import com.android.mms.util.VibratorManager;
import com.xiaomi.mms.mx.audio.player.AudioTalkMediaPlayer;
import com.xiaomi.mms.mx.audio.player.XMAudioRecorder;

public class AudioRecordingController {
    private Button mAudioBtn;
    private float mAudioBtnDiffX;
    private Button mAudioBtnRemind;
    private float mAudioBtnX;
    private float mAudioBtnY;
    private ImageView mAudioCancel;
    private ViewStub mBottomStub;
    private View mBottomView;
    private ViewStub mBtnStub;
    private AudioBtnTouchRunnable mBtnTouch;
    private Context mContext;
    private Conversation mConversation;
    private boolean mIsAudioAvailable;
    private boolean mIsCancelled;
    private boolean mIsSendingMx2;
    private View.OnLayoutChangeListener mLayoutChangeListener;
    private WorkingMessage.MessageStatusListener mListener;
    private View mPanelBtn;
    private View mParent;
    private int mProgressMaxWidth;
    private Handler mRecordHandler;
    private XMAudioRecorder mRecorder;
    private RecordingProgressView mRecordingProgressView;
    private int[] mRecordingTextViewLoc = new int[2];
    private Runnable mRemindRunnable;
    private TextView mRemindText;
    private View.OnTouchListener mRemindTouchListener;
    private View mSendBtn;
    private int[] mSendBtnLocation = new int[2];
    private boolean mShouldBtnStop;
    private int mSlotId;
    private Runnable mStopRecordRunnable;
    private TextView mTextCancel;
    private View mTextEditor;
    private float mTextLocationX;
    private TextView mTextNotify;
    private long mThreadId;

    public AudioRecordingController(WorkingMessage.MessageStatusListener messageStatusListener, boolean bl, View view, ViewStub viewStub, ViewStub viewStub2, View view2, View view3, View view4, Context context) {
        this.mBtnTouch = new AudioBtnTouchRunnable(this);
        this.mIsSendingMx2 = false;
        this.mStopRecordRunnable = new Runnable(){

            @Override
            public void run() {
                AudioRecordingController.this.mRecorder.stop();
            }
        };
        this.mRemindRunnable = new Runnable(){

            @Override
            public void run() {
                AudioRecordingController.this.endRecordAnimation();
            }
        };
        this.mLayoutChangeListener = new View.OnLayoutChangeListener(){

            public void onLayoutChange(View view, int n, int n2, int n3, int n4, int n5, int n6, int n7, int n8) {
                AudioRecordingController.this.mSendBtn.getLocationOnScreen(AudioRecordingController.this.mSendBtnLocation);
                if (AudioRecordingController.this.mAudioBtnRemind.getVisibility() == 0) {
                    AudioRecordingController.this.mAudioBtnRemind.setY((float)AudioRecordingController.this.mSendBtnLocation[1]);
                    return;
                }
                AudioRecordingController.this.mAudioBtn.setY((float)AudioRecordingController.this.mSendBtnLocation[1]);
            }
        };
        this.mRemindTouchListener = new View.OnTouchListener(){

            /*
             * Enabled aggressive block sorting
             */
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (!AudioRecordingController.this.isRecording()) {
                    switch (motionEvent.getAction()) {
                        case 0: {
                            AudioRecordingController.this.mBtnTouch.setEvent(motionEvent);
                            AudioRecordingController.this.mRecordHandler.postDelayed((Runnable)AudioRecordingController.this.mBtnTouch, 200);
                        }
                        default: {
                            break;
                        }
                        case 1: {
                            if (AudioRecordingController.this.mBtnTouch.isRunning()) break;
                            AudioRecordingController.this.mRecordHandler.removeCallbacks((Runnable)AudioRecordingController.this.mBtnTouch);
                            AudioRecordingController.this.mRecordHandler.removeCallbacks(AudioRecordingController.this.mRemindRunnable);
                            AudioRecordingController.this.endRecordAnimation();
                        }
                    }
                    AudioRecordingController.this.mAudioBtn.setOnTouchListener(null);
                }
                return false;
            }
        };
        this.mRecordHandler = new Handler(){
            private boolean mIsWarning;

            /*
             * Enabled aggressive block sorting
             */
            public void handleMessage(Message object) {
                switch (object.what) {
                    case 1: {
                        VibratorManager.vibrate(AudioRecordingController.this.mContext, 20);
                        AudioHelper.gainFocus();
                        AudioTalkMediaPlayer.getInstance((Context)MmsApp.getApp()).stop();
                        AudioRecordingController.this.mIsCancelled = false;
                        this.mIsWarning = false;
                        return;
                    }
                    case 2: {
                        AudioRecordingController.this.mRecordingProgressView.addVolumes(((Integer)object.obj).intValue());
                        if (AudioRecordingController.this.mRecorder.getDuration() >= 60000) {
                            AudioRecordingController.this.mIsCancelled = false;
                            AudioRecordingController.this.mRecorder.stop();
                            AudioRecordingController.this.endRecord();
                            return;
                        }
                        if (AudioRecordingController.this.mRecorder.getDuration() > 50000) {
                            if (!this.mIsWarning) {
                                VibratorManager.vibrate(AudioRecordingController.this.mContext, 20);
                                this.mIsWarning = true;
                            }
                            int n = (60000 - AudioRecordingController.this.mRecorder.getDuration()) / 1000;
                            object = String.format((String)MmsApp.getApp().getString(2131362355), (Object[])new Object[]{n});
                            AudioRecordingController.this.mTextNotify.setText((CharSequence)object);
                            AudioRecordingController.this.mTextNotify.setVisibility(0);
                            return;
                        }
                    }
                    default: {
                        return;
                    }
                    case 3: {
                        AudioHelper.releaseFocus();
                        if (AudioRecordingController.this.isCancelled()) {
                            AudioRecordingController.this.mRecorder.clear();
                            return;
                        }
                        if (AudioRecordingController.this.mRecorder.getDuration() < 1000) {
                            AudioRecordingController.this.mRecorder.clear();
                            return;
                        }
                        AudioHelper.sendRecordedAudio(AudioRecordingController.this.mRecorder.getAudioPath(), AudioRecordingController.this.mRecorder.getDuration(), AudioRecordingController.this.mThreadId, AudioRecordingController.this.mIsAudioAvailable, AudioRecordingController.this.mListener, AudioRecordingController.this.mSlotId, AudioRecordingController.this.mConversation);
                        AudioRecordingController.this.mIsSendingMx2 = true;
                        return;
                    }
                    case -3: 
                    case -2: 
                    case -1: 
                }
                AudioRecordingController.this.mRecorder.stop();
                AudioRecordingController.this.mRecorder.clear();
                AudioRecordingController.this.endRecord();
                Toast.makeText((Context)MmsApp.getApp(), (int)2131362359, (int)1).show();
                Log.e((String)"AudioRecordingController.RICH", (String)("controller audio failed " + object.what));
            }
        };
        this.mListener = messageStatusListener;
        this.mIsAudioAvailable = bl;
        this.mBottomStub = viewStub;
        this.mBtnStub = viewStub2;
        this.mParent = view;
        this.mSendBtn = view2;
        this.mPanelBtn = view3;
        this.mTextEditor = view4;
        this.mContext = context;
        this.mRecorder = new XMAudioRecorder(this.mRecordHandler, context);
        messageStatusListener = this.mBtnStub.inflate();
        this.mAudioBtn = (Button)messageStatusListener.findViewById(2131820554);
        this.mAudioBtnRemind = (Button)messageStatusListener.findViewById(2131820555);
        this.mAudioBtnRemind.setOnTouchListener(this.mRemindTouchListener);
        messageStatusListener.addOnLayoutChangeListener(this.mLayoutChangeListener);
    }

    private void endRecord() {
        this.mAudioBtn.setVisibility(4);
        this.mAudioBtnRemind.setVisibility(4);
        this.mSendBtn.setVisibility(0);
        this.mRecordingProgressView.setVisibility(4);
        this.showTextBottom();
    }

    private void endRecordAnimation() {
        this.mAudioBtn.setVisibility(4);
        this.mAudioBtnRemind.setVisibility(4);
        this.mSendBtn.setVisibility(0);
        this.mRecordingProgressView.setVisibility(4);
        this.showTextBottom();
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setDuration(300);
        this.mPanelBtn.setAnimation((Animation)alphaAnimation);
        this.mTextEditor.setAnimation((Animation)alphaAnimation);
        alphaAnimation.startNow();
    }

    private void hideTextBottom() {
        this.mPanelBtn.setVisibility(8);
        this.mPanelBtn.clearAnimation();
        this.mTextEditor.setVisibility(8);
        this.mTextEditor.clearAnimation();
        this.mSendBtn.setVisibility(4);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void initAudioBtn(MotionEvent motionEvent) {
        float f2;
        if (this.mAudioBtnRemind.getVisibility() == 0) {
            this.mAudioBtnRemind.getLocationOnScreen(this.mSendBtnLocation);
        } else {
            this.mSendBtn.getLocationOnScreen(this.mSendBtnLocation);
        }
        this.mSendBtn.setVisibility(4);
        this.mAudioBtnY = this.mSendBtnLocation[1];
        this.mAudioBtnX = f2 = (float)(this.mSendBtnLocation[0] + this.mSendBtn.getWidth() / 2 - this.mAudioBtn.getWidth() / 2);
        this.mTextLocationX = f2;
        this.mAudioBtnDiffX = (float)(this.mSendBtnLocation[0] + this.mSendBtn.getWidth() / 2) - motionEvent.getRawX();
        this.mAudioBtn.setX(this.mAudioBtnX);
        this.mAudioBtn.setY(this.mAudioBtnY);
        this.mAudioBtn.setBackgroundResource(2130837583);
        this.mAudioBtn.setVisibility(0);
        this.mAudioBtnRemind.setVisibility(4);
        this.mTextNotify.setVisibility(4);
        this.mShouldBtnStop = true;
    }

    private void initBottomStub() {
        if (this.mBottomView == null) {
            View view = this.mParent.findViewById(2131820784);
            this.mBottomStub.getLayoutParams().height = view.getHeight();
            this.mBottomView = this.mBottomStub.inflate();
            this.mRecordingProgressView = (RecordingProgressView)this.mBottomView.findViewById(2131820678);
            this.mTextCancel = (TextView)this.mBottomView.findViewById(2131820680);
            this.mTextNotify = (TextView)this.mBottomView.findViewById(2131820679);
            this.mRemindText = (TextView)this.mBottomView.findViewById(2131820676);
            this.mAudioCancel = (ImageView)this.mBottomView.findViewById(2131820677);
        }
    }

    private boolean isProgressViewVisible() {
        if (this.mRecordingProgressView != null && this.mRecordingProgressView.getVisibility() == 0) {
            return true;
        }
        return false;
    }

    private void showAudioBottom() {
        this.hideTextBottom();
        this.mBottomStub.setVisibility(0);
        this.mTextCancel.setVisibility(0);
        this.mRecordingProgressView.reset();
        this.mRecordingProgressView.setVisibility(0);
        this.mAudioCancel.setVisibility(0);
        this.mRemindText.clearAnimation();
        this.mRemindText.setVisibility(8);
    }

    private void showTextBottom() {
        this.mBottomStub.setVisibility(8);
        this.mSendBtn.setVisibility(0);
        this.mPanelBtn.setVisibility(0);
        this.mPanelBtn.clearAnimation();
        this.mTextEditor.setVisibility(0);
        this.mTextEditor.requestFocus();
        this.mTextEditor.clearAnimation();
    }

    private void startRecording() {
        AudioHelper.getAudioDir(true);
        String string = AudioHelper.getAudioPath();
        this.mRecorder.init(string);
        this.mRecorder.start();
    }

    public void cancel() {
        this.mIsCancelled = true;
        this.mAudioBtn.setBackgroundResource(2130837573);
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean dispatchTouchEvent(MotionEvent motionEvent, int n, boolean bl) {
        if (!this.isProgressViewVisible()) return false;
        {
            if (motionEvent.getAction() == 2) {
                if (this.mProgressMaxWidth == 0) {
                    this.mTextCancel.getLocationOnScreen(this.mRecordingTextViewLoc);
                    this.mProgressMaxWidth = this.mRecordingProgressView.getWidth();
                    return false;
                } else {
                    if (this.mShouldBtnStop && Math.abs((float)(motionEvent.getRawX() + this.mAudioBtnDiffX - (float)(this.mAudioBtn.getWidth() / 2) - this.mTextLocationX)) > (float)AudioHelper.convertdipTopx(33)) {
                        this.mShouldBtnStop = false;
                    }
                    if (this.mShouldBtnStop) return false;
                    {
                        this.mAudioBtnX = motionEvent.getRawX() + this.mAudioBtnDiffX;
                        if (this.mAudioBtnX < (float)(this.mBottomView.getWidth() / 2 + AudioHelper.convertdipTopx(33))) {
                            this.mAudioBtnX = this.mBottomView.getWidth() / 2;
                            if (!this.isCancelled()) {
                                this.cancel();
                            }
                        } else {
                            this.mIsCancelled = false;
                            this.mAudioBtn.setBackgroundResource(2130837583);
                        }
                        this.mAudioBtnX -= (float)(this.mAudioBtn.getWidth() / 2);
                        this.mAudioBtn.setX(this.mAudioBtnX);
                        if (this.mAudioBtnX < (float)(this.mBottomView.getWidth() - AudioHelper.convertdipTopx(70))) {
                            this.mTextCancel.setVisibility(8);
                            return false;
                        }
                        this.mTextCancel.setVisibility(0);
                        return false;
                    }
                }
            } else {
                if (motionEvent.getAction() == 1) {
                    this.endRecord();
                    if (n < 0) {
                        this.mSlotId = MSimUtils.getInsertedSlotId();
                        if (this.mSlotId < 0) {
                            this.mSlotId = 0;
                        }
                    } else {
                        this.mSlotId = n;
                    }
                    this.mIsAudioAvailable = bl;
                    this.mRecordHandler.postDelayed(this.mStopRecordRunnable, 500);
                    return false;
                }
                if (motionEvent.getPointerCount() <= 1) return false;
                return true;
            }
        }
    }

    public boolean isCancelled() {
        return this.mIsCancelled;
    }

    public boolean isRecording() {
        return this.mRecorder.isRecording();
    }

    public boolean isSendingMx2() {
        return this.mIsSendingMx2;
    }

    public void release() {
        if (this.mRecorder.isRecording()) {
            this.mIsCancelled = true;
            this.mRecorder.stop();
            this.mRecorder.clear();
            this.endRecord();
            this.mRecordHandler.removeCallbacks(this.mStopRecordRunnable);
        }
        VibratorManager.cancel();
    }

    public void resetIsSendingMx2() {
        this.mIsSendingMx2 = false;
    }

    public void setConversation(Conversation conversation) {
        this.mConversation = conversation;
    }

    public void setThreadId(long l) {
        this.mThreadId = l;
    }

    public void showRemind() {
        this.initBottomStub();
        this.mSendBtn.getLocationOnScreen(this.mSendBtnLocation);
        this.hideTextBottom();
        this.mBottomStub.setVisibility(0);
        this.mTextCancel.setVisibility(8);
        this.mRecordingProgressView.setVisibility(8);
        this.mAudioCancel.setVisibility(8);
        this.mRemindText.setVisibility(0);
        this.mAudioBtnRemind.setX((float)this.mSendBtnLocation[0]);
        this.mAudioBtnRemind.setY((float)this.mSendBtnLocation[1]);
        this.mAudioBtnRemind.setVisibility(0);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setDuration(300);
        this.mRemindText.setAnimation((Animation)alphaAnimation);
        alphaAnimation.startNow();
        this.mRecordHandler.postDelayed(this.mRemindRunnable, 1500);
    }

    public void startRecord(MotionEvent motionEvent) {
        this.initBottomStub();
        this.mRecordHandler.removeCallbacks(this.mRemindRunnable);
        this.initAudioBtn(motionEvent);
        this.showAudioBottom();
        this.startRecording();
    }

}

