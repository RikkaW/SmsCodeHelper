/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentUris
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.drawable.AnimationDrawable
 *  android.graphics.drawable.Drawable
 *  android.net.Uri
 *  android.os.AsyncTask
 *  android.os.Handler
 *  android.provider.Telephony
 *  android.provider.Telephony$Mms
 *  android.text.TextUtils
 *  android.util.Log
 *  android.view.View
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewStub
 *  android.widget.ImageView
 *  android.widget.TextView
 *  android.widget.Toast
 *  java.io.File
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 */
package com.android.mms.audio;

import android.content.ContentUris;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.provider.Telephony;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.mms.MmsApp;
import com.android.mms.audio.AudioHelper;
import com.android.mms.audio.AudioItemCache;
import com.android.mms.audio.AudioSensorManager;
import com.android.mms.transaction.DownloadMxV2FileService;
import com.android.mms.ui.MessageItem;
import com.android.mms.ui.SimplePduDoc;
import com.android.mms.ui.SimplePduPart;
import com.android.mms.util.MSimUtils;
import com.xiaomi.mms.mx.audio.player.AudioTalkMediaPlayer;
import com.xiaomi.mms.mx.data.Attachment;
import java.io.File;
import java.util.List;

public class AudioItemController {
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

    static {
        Resources resources = MmsApp.getApp().getResources();
        sAudioBubbleWidthMax = resources.getDimensionPixelSize(2131427473);
        sAudioBubbleWidthMin = resources.getDimensionPixelSize(2131427474);
    }

    public AudioItemController(ViewStub viewStub, ViewStub viewStub2, AudioItemCache audioItemCache) {
        this.mAudioItemStub = viewStub;
        this.mIsReadStub = viewStub2;
        this.mAudioItemCache = audioItemCache;
    }

    static /* synthetic */ Attachment access$600(AudioItemController audioItemController) {
        return audioItemController.mAttachment;
    }

    private void hideReadImg() {
        if (this.mIsReadImg != null) {
            this.mIsReadImg.setVisibility(8);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void initAudioData(MessageItem object) {
        this.mAttachment = object.getMx2Attachments().get(0);
        this.mMsgId = object.getMsgId();
        boolean bl = object.getBoxId() == 1;
        this.mIsMsgIn = bl;
        this.mMsgPreviewType = object.getMmsPreviewType();
        if (this.mAttachment != null) {
            this.mPlayTime = (this.mAttachment.playTime + 500) / 1000;
            if (this.mMsgPreviewType != 3) {
                this.mPath = this.mAttachment.getLocalPath((Context)MmsApp.getApp(), AudioHelper.getAudioDir());
                return;
            }
            object = (object = object.getSimplePduDoc()) != null ? object.getPageAppearancePart(0).getDataUri() : null;
            this.mMsgPartUri = object;
            this.mPath = this.mAudioItemCache.getMmsAudioPath(this.mMsgId);
            if (this.mPath == null && this.mMsgPartUri != null) {
                this.mPath = AudioHelper.getMediaFilePathByUri((Context)MmsApp.getApp(), this.mMsgPartUri);
                this.mAudioItemCache.addMmsAudioPath(this.mMsgId, this.mPath);
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void initAudioItemView() {
        ViewGroup.LayoutParams layoutParams = this.mAudioItemStub.getLayoutParams();
        layoutParams.width = this.mPlayTime < 10 ? (sAudioBubbleWidthMax - sAudioBubbleWidthMin) / 9 * (this.mPlayTime - 1) + sAudioBubbleWidthMin : sAudioBubbleWidthMax;
        this.mAudioItemStub.setLayoutParams(layoutParams);
        this.mAudioItemStub.setVisibility(0);
    }

    private void showReadImg() {
        if (this.mIsReadImg != null && this.mAttachment != null && !this.mAttachment.mIsRead) {
            this.mIsReadImg.setVisibility(0);
        }
    }

    public void endEditMode() {
        this.showReadImg();
        this.mEditMode = false;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void initListItem(MessageItem messageItem, Handler handler) {
        Object object;
        if (this.mAudioItemStub == null) {
            return;
        }
        if (this.mPlayingView == null) {
            object = this.mAudioItemStub.inflate();
            this.mPlayingView = (ImageView)object.findViewById(2131820556);
            this.mAudioText = (TextView)object.findViewById(2131820557);
        }
        this.initAudioData(messageItem);
        this.initAudioItemView();
        object = String.format((String)MmsApp.getApp().getString(2131362356), (Object[])new Object[]{this.mPlayTime});
        this.mAudioText.setText((CharSequence)object);
        this.mMsgUri = messageItem.getMessageUri();
        this.mMsgSimId = messageItem.getSimId();
        this.mHandler = handler;
        if (AudioTalkMediaPlayer.getInstance((Context)MmsApp.getApp()).isPlaying(this.mPath)) {
            this.setPlayingViews();
        } else {
            this.resetPlayingViews();
            if (this.mIsReadStub != null && this.mAttachment != null && !this.mAttachment.mIsRead && !this.mEditMode) {
                if (this.mIsReadImg == null) {
                    this.mIsReadView = this.mIsReadStub.inflate();
                    this.mIsReadImg = (ImageView)this.mIsReadView.findViewById(2131820558);
                }
                this.mIsReadImg.setVisibility(0);
            } else {
                this.hideReadImg();
            }
        }
        this.mAudioItemCache.add(messageItem.getMsgId(), this);
    }

    public boolean isDownloadingFile() {
        boolean bl;
        boolean bl2 = bl = false;
        if (this.mAudioItemStub != null) {
            bl2 = bl;
            if (this.mAttachment != null) {
                bl2 = bl;
                if (DownloadMxV2FileService.isDownloading(this.mAttachment.shareId)) {
                    bl2 = true;
                }
            }
        }
        return bl2;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void markAsRead() {
        if (this.mAudioItemStub == null || this.mAttachment == null || this.mAttachment.mIsRead) {
            return;
        }
        this.hideReadImg();
        AudioHelper.markReadAsync(this.mMsgUri, this.mAttachment);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void resetPlayingViews() {
        if (this.mAudioItemStub == null || this.mAnimationDrawable == null) {
            return;
        }
        this.mAnimationDrawable.stop();
        int n = this.mIsMsgIn ? 2130837574 : 2130837579;
        this.mPlayingView.setImageResource(n);
        this.mAnimationDrawable = null;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setPlayingViews() {
        if (this.mAudioItemStub == null) {
            return;
        }
        int n = this.mIsMsgIn ? 2130837543 : 2130837544;
        this.mPlayingView.setImageResource(n);
        this.mAnimationDrawable = (AnimationDrawable)this.mPlayingView.getDrawable();
        this.mAnimationDrawable.start();
        this.hideReadImg();
    }

    public void startEditMode() {
        this.hideReadImg();
        this.mEditMode = true;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public void startPlayAudio() {
        if (this.mAudioItemStub == null) {
            return;
        }
        AudioTalkMediaPlayer audioTalkMediaPlayer = AudioTalkMediaPlayer.getInstance((Context)MmsApp.getApp());
        audioTalkMediaPlayer.clearPlayList();
        if (audioTalkMediaPlayer.isPlaying(this.mPath)) {
            audioTalkMediaPlayer.stop();
            return;
        }
        if (this.mAnimationDrawable != null) {
            this.resetPlayingViews();
            return;
        }
        if (audioTalkMediaPlayer.isPlaying()) {
            audioTalkMediaPlayer.stop();
        }
        if (this.mPath == null) {
            if (this.mMsgPreviewType != 3) return;
        }
        sPlayingPath = this.mPath;
        if (sPlayAudioAsyncTask != null) {
            sPlayAudioAsyncTask.cancel(true);
        }
        sPlayAudioAsyncTask = new PlayAudioAsyncTask();
        sPlayAudioAsyncTask.execute((Object[])new String[]{this.mPath});
    }

    public void unbind() {
        if (this.mAudioItemStub != null) {
            this.mAudioItemStub.setVisibility(8);
        }
        if (this.mAudioItemCache != null) {
            this.mAudioItemCache.remove(this.mMsgId);
        }
        this.hideReadImg();
        this.mMsgId = -1;
    }

    private class PlayAudioAsyncTask
    extends AsyncTask<String, Void, Integer> {
        private PlayAudioAsyncTask() {
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        protected /* varargs */ Integer doInBackground(String ... object) {
            Log.v((String)"AudioItemController.RICH", (String)("MX2AudioPlay the spath = " + sPlayingPath));
            if (AudioItemController.this.mPath != null && TextUtils.equals((CharSequence)AudioItemController.this.mPath, (CharSequence)sPlayingPath)) {
                AudioHelper.getAudioDir(true);
                Object object2 = object = new File(AudioItemController.this.mPath);
                if (!object.exists()) {
                    object2 = object;
                    if (AudioItemController.this.mMsgPreviewType != 3) {
                        long l;
                        object2 = AudioItemController.this.mPath.split("/");
                        try {
                            l = Long.parseLong((String)object2[object2.length - 1].split("\\.")[0]);
                        }
                        catch (NumberFormatException var1_2) {
                            Log.e((String)"AudioItemController.RICH", (String)("MX2AudioPlay parse path to number error, mPath = " + AudioItemController.this.mPath));
                            l = System.currentTimeMillis();
                            object = AudioHelper.getAudioDir(true);
                            AudioItemController.this.mPath = (String)object + "/" + l + ".amr";
                            sPlayingPath = AudioItemController.this.mPath;
                            object = new File(AudioItemController.this.mPath);
                        }
                        if (System.currentTimeMillis() - l >= 561600000) {
                            return 2;
                        }
                        int n = MSimUtils.getSlotIdBySimInfoId(AudioItemController.this.mMsgSimId);
                        Uri uri = ContentUris.withAppendedId((Uri)Telephony.Mms.CONTENT_URI, (long)AudioItemController.this.mMsgId);
                        object2 = object;
                        if (n != -1) {
                            DownloadMxV2FileService.startDownloadAudio((Context)MmsApp.getApp(), AudioItemController.access$600((AudioItemController)AudioItemController.this).shareId, n, uri, AudioItemController.this.mIsMsgIn, object.getName());
                            return 3;
                        }
                    }
                }
                if (object2.exists()) {
                    return 0;
                }
                return 1;
            }
            Log.v((String)"AudioItemController.RICH", (String)("MX2AudioPlay play failed the audio path is " + AudioItemController.this.mPath + ", and spath = " + sPlayingPath));
            return -1;
        }

        /*
         * Enabled aggressive block sorting
         */
        protected void onPostExecute(Integer n) {
            if (AudioItemController.this.mPath != null && AudioItemController.this.mPath.equals((Object)sPlayingPath)) {
                switch (n) {
                    default: {
                        Toast.makeText((Context)MmsApp.getApp(), (int)2131362357, (int)0).show();
                        break;
                    }
                    case 0: {
                        AudioSensorManager audioSensorManager = new AudioSensorManager(AudioItemController.this.mHandler);
                        AudioHelper.playAudio(AudioItemController.this.mMsgId, AudioItemController.this.mPath, audioSensorManager, AudioItemController.this.mAudioItemCache);
                        break;
                    }
                    case 1: 
                    case 2: {
                        Toast.makeText((Context)MmsApp.getApp(), (int)2131362365, (int)0).show();
                        break;
                    }
                    case 3: {
                        Toast.makeText((Context)MmsApp.getApp(), (int)2131362360, (int)0).show();
                    }
                }
            }
            super.onPostExecute((Object)n);
        }
    }

}

