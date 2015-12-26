/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.content.res.Resources
 *  android.database.Cursor
 *  android.media.AudioManager
 *  android.media.AudioManager$OnAudioFocusChangeListener
 *  android.net.Uri
 *  android.os.AsyncTask
 *  android.os.Environment
 *  android.os.Handler
 *  android.text.TextUtils
 *  android.util.DisplayMetrics
 *  android.util.Log
 *  java.io.File
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.util.ArrayList
 */
package com.android.mms.audio;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.media.AudioManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import com.android.mms.MmsApp;
import com.android.mms.audio.AudioItemCache;
import com.android.mms.audio.AudioPlayingHandler;
import com.android.mms.audio.AudioSensorManager;
import com.android.mms.data.Conversation;
import com.android.mms.data.WorkingMessage;
import com.android.mms.util.MSimUtils;
import com.xiaomi.mms.data.Mx2MessageModel;
import com.xiaomi.mms.mx.audio.player.AudioTalkMediaPlayer;
import com.xiaomi.mms.mx.audio.player.MediaPlayerObserver;
import com.xiaomi.mms.mx.data.Attachment;
import com.xiaomi.mms.mx.utils.Mx2ExtentionHelper;
import com.xiaomi.mms.transaction.Mx2MmsTransactionService;
import com.xiaomi.mms.transaction.MxMmsTransactionService;
import com.xiaomi.mms.utils.Mx2PduPersister;
import com.xiaomi.mms.utils.MxMessagePduHelper;
import com.xiaomi.mms.utils.MxMessageUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AudioHelper {
    private static final String MMS_PRIORITY_CACHE_DIR;
    private static String sCachePath;
    private static AudioManager.OnAudioFocusChangeListener sOnAudioFocusChangeListener;
    private static final float sScaleConstant;

    static {
        sCachePath = null;
        MMS_PRIORITY_CACHE_DIR = (Object)Environment.getExternalStorageDirectory() + "/Mms/cache";
        sScaleConstant = MmsApp.getApp().getResources().getDisplayMetrics().density;
        sOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener(){

            public void onAudioFocusChange(int n) {
                AudioTalkMediaPlayer audioTalkMediaPlayer;
                if (n == -1 && (audioTalkMediaPlayer = AudioTalkMediaPlayer.getInstance((Context)MmsApp.getApp())).isPlaying()) {
                    audioTalkMediaPlayer.stop();
                }
            }
        };
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static int convertdipTopx(int n) {
        float f2 = n;
        float f3 = sScaleConstant;
        if (n >= 0) {
            n = 1;
            do {
                return (int)((float)n * 0.5f + f3 * f2);
                break;
            } while (true);
        }
        n = -1;
        return (int)((float)n * 0.5f + f3 * f2);
    }

    public static boolean gainFocus() {
        if (((AudioManager)MmsApp.getApp().getSystemService("audio")).requestAudioFocus(sOnAudioFocusChangeListener, 3, 2) == 1) {
            return true;
        }
        return false;
    }

    public static String getAudioDir() {
        return AudioHelper.getAudioDir(false);
    }

    public static String getAudioDir(boolean bl) {
        Object object;
        if (TextUtils.isEmpty((CharSequence)sCachePath)) {
            object = MxMessageUtils.getMmsExternalFileDir() + "/cache/audio";
            bl = true;
            sCachePath = object;
        }
        if (bl && !(object = new File(sCachePath)).exists()) {
            object.mkdirs();
        }
        return sCachePath;
    }

    public static String getAudioPath() {
        return AudioHelper.getAudioDir() + "/" + System.currentTimeMillis() + ".amr";
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String getMediaFilePathByUri(Context arrstring, Uri object) {
        block7 : {
            Object var5_5 = null;
            Object var4_6 = null;
            object = arrstring.getContentResolver().query((Uri)object, new String[]{"_data"}, null, null, null);
            arrstring = var5_5;
            if (object != null) {
                arrstring = var4_6;
                if (!object.moveToFirst()) break block7;
                arrstring = object.getString(0);
            }
        }
        object = arrstring;
        if (!MSimUtils.isAndroid50()) return object;
        try {
            arrstring = arrstring.split("/");
            arrstring = arrstring[arrstring.length - 1].split("\\.")[0].split("_");
            long l = Long.parseLong((String)arrstring[arrstring.length - 1]);
            return AudioHelper.getAudioDir(true) + "/" + l + ".amr";
        }
        catch (NumberFormatException numberFormatException) {
            Log.e((String)"AudioHelper.RICH", (String)"getMediaFilePathByUri android5.0 failed..");
            return null;
        }
        catch (Exception exception) {
            Log.e((String)"AudioHelper.RICH", (String)"getMediaFilePathByUri path is error..");
            return null;
        }
        finally {
            object.close();
        }
    }

    public static void markReadAsync(final Uri uri, final Attachment attachment) {
        new AsyncTask<Void, Void, Void>(){

            protected /* varargs */ Void doInBackground(Void ... arrayList) {
                attachment.mIsRead = true;
                arrayList = new ArrayList();
                arrayList.add(attachment);
                Mx2PduPersister.updateExtension((Context)MmsApp.getApp(), uri, arrayList);
                return null;
            }
        }.execute((Object[])new Void[0]);
    }

    public static void playAudio(long l, String string, AudioSensorManager audioSensorManager, AudioItemCache audioItemCache) {
        AudioTalkMediaPlayer audioTalkMediaPlayer = AudioTalkMediaPlayer.getInstance((Context)MmsApp.getApp());
        audioTalkMediaPlayer.addToPlayList(l, 0, 0, string, "0", new MediaPlayerObserver(new AudioPlayingHandler(l, audioSensorManager, audioItemCache)), false);
        audioTalkMediaPlayer.playNext();
    }

    public static void releaseFocus() {
        ((AudioManager)MmsApp.getApp().getSystemService("audio")).abandonAudioFocus(sOnAudioFocusChangeListener);
    }

    public static void sendRecordedAudio(final String string, final int n, final long l, final boolean bl, final WorkingMessage.MessageStatusListener messageStatusListener, final int n2, final Conversation conversation) {
        Log.i((String)"AudioHelper.RICH", (String)("AudioHelper   sendRecordedAudio   isMx2 = " + bl + "     audioPath =" + string + "     audioDuration=" + n + "       threadId=" + l + "     slotId=" + n2));
        new AsyncTask<Void, Void, Void>(){

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            protected /* varargs */ Void doInBackground(Void ... file) {
                file = new File(string);
                Mx2MessageModel mx2MessageModel = new Mx2MessageModel();
                mx2MessageModel.mConversation = conversation;
                mx2MessageModel.setThreadId(l);
                mx2MessageModel.setDate(System.currentTimeMillis() / 1000);
                mx2MessageModel.setDateSent(System.currentTimeMillis() / 1000);
                mx2MessageModel.setType(128);
                mx2MessageModel.setMxType(String.valueOf((int)3));
                Attachment attachment = new Attachment();
                attachment.playTime = n;
                attachment.mimeType = "audio/amr";
                String[] arrstring = string.split("/");
                attachment.filename = arrstring[arrstring.length - 1];
                attachment.datasize = file.length();
                mx2MessageModel.addAttachment(attachment);
                mx2MessageModel.setMxExtension(Mx2ExtentionHelper.generateAttachmentsExtentionString(mx2MessageModel.getAttachments(), true));
                mx2MessageModel.setBoxId(3);
                mx2MessageModel.setSimId(MSimUtils.getSimIdBySlotId(n2));
                file = Mx2PduPersister.insertMxMessage((Context)MmsApp.getApp(), mx2MessageModel);
                if (file != null) {
                    MxMessagePduHelper.markMmsSendAsMx((Context)MmsApp.getApp(), (Uri)file, true);
                    if (bl) {
                        Mx2MmsTransactionService.startSendMx2((Context)MmsApp.getApp(), (Uri)file);
                        do {
                            return null;
                            break;
                        } while (true);
                    }
                    MxMessageUtils.convertMx2toMms((Context)MmsApp.getApp(), messageStatusListener, (Uri)file, false);
                    MxMmsTransactionService.startSendMms((Context)MmsApp.getApp(), (Uri)file);
                    return null;
                }
                Log.e((String)"AudioHelper.RICH", (String)("insert MxMessage failed, AudioHelper sendRecordedAudio    isMx2 = " + bl + "     audioPath = " + string + "     audioDuration = " + n + "       threadId = " + l + "     slotId = " + n2));
                return null;
            }

            protected void onPostExecute(Void void_) {
                messageStatusListener.onMessageSent();
            }
        }.execute((Object[])new Void[0]);
    }

}

