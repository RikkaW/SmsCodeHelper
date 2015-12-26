/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.media.MediaRecorder
 *  android.media.MediaRecorder$OnErrorListener
 *  android.media.MediaRecorder$OnInfoListener
 *  android.os.AsyncTask
 *  android.os.Handler
 *  android.os.Message
 *  android.util.Log
 *  android.widget.Toast
 *  java.io.File
 *  java.io.FileNotFoundException
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
package com.xiaomi.mms.mx.audio.player;

import android.content.Context;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;
import com.xiaomi.common.library.utils.SDCardUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class XMAudioRecorder {
    private Context mContext;
    private int mCount;
    private MediaRecorder.OnErrorListener mErrorListener;
    private Handler mHandler;
    private MediaRecorder.OnInfoListener mInfoListener;
    private boolean mIsRecording;
    private Object mLock = new Object();
    private String mPath;
    private MediaRecorder mRecorder;
    private Runnable mSamplingRunnable;

    public XMAudioRecorder(Handler handler, Context context) {
        this.mInfoListener = new MediaRecorder.OnInfoListener(){

            public void onInfo(MediaRecorder mediaRecorder, int n, int n2) {
                XMAudioRecorder.this.sendMsg(-3);
                Log.v((String)"XMAudioRecorder", (String)("unknown audio failed, errorcode=" + n));
                XMAudioRecorder.this.mIsRecording = false;
            }
        };
        this.mErrorListener = new MediaRecorder.OnErrorListener(){

            public void onError(MediaRecorder mediaRecorder, int n, int n2) {
                XMAudioRecorder.this.sendMsg(-2);
                Log.v((String)"XMAudioRecorder", (String)("unknown audio error failed, errorcode=" + n));
                XMAudioRecorder.this.mIsRecording = false;
            }
        };
        this.mSamplingRunnable = new Runnable(){

            @Override
            public void run() {
                if (XMAudioRecorder.this.mIsRecording) {
                    Message message = XMAudioRecorder.this.mHandler.obtainMessage();
                    message.what = 2;
                    message.obj = XMAudioRecorder.this.mRecorder.getMaxAmplitude();
                    message.sendToTarget();
                    XMAudioRecorder.access$404(XMAudioRecorder.this);
                    XMAudioRecorder.this.mHandler.postDelayed((Runnable)this, 100);
                }
            }
        };
        this.mHandler = handler;
        this.mContext = context;
    }

    static /* synthetic */ int access$404(XMAudioRecorder xMAudioRecorder) {
        int n;
        xMAudioRecorder.mCount = n = xMAudioRecorder.mCount + 1;
        return n;
    }

    private void sendMsg(int n) {
        if (n < 0) {
            this.clear();
        }
        Message message = this.mHandler.obtainMessage();
        message.what = n;
        message.sendToTarget();
    }

    /*
     * Enabled aggressive block sorting
     */
    public void clear() {
        File file;
        if (this.mPath == null || !(file = new File(this.mPath)).exists()) {
            return;
        }
        file.delete();
    }

    public String getAudioPath() {
        return this.mPath;
    }

    public int getDuration() {
        return this.mCount * 100;
    }

    public void init(String string2) {
        this.mPath = string2;
        this.mRecorder = new MediaRecorder();
        this.mRecorder.setAudioSource(1);
        this.mRecorder.setOutputFormat(3);
        this.mRecorder.setAudioEncoder(1);
        this.mRecorder.setOutputFile(this.mPath);
        this.mRecorder.setOnInfoListener(this.mInfoListener);
        this.mRecorder.setOnErrorListener(this.mErrorListener);
    }

    public boolean isRecording() {
        return this.mIsRecording;
    }

    public void start() {
        this.mIsRecording = true;
        new AsyncTask<Void, Void, Void>(){

            /*
             * Loose catch block
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             * Converted monitor instructions to comments
             * Lifted jumps to return sites
             */
            protected /* varargs */ Void doInBackground(Void ... object) {
                object = XMAudioRecorder.this.mLock;
                // MONITORENTER : object
                if (!XMAudioRecorder.this.mIsRecording) {
                    // MONITOREXIT : object
                    return null;
                }
                boolean bl = true;
                XMAudioRecorder.this.mRecorder.prepare();
                if (!bl) return null;
                try {
                    XMAudioRecorder.this.mRecorder.start();
                    XMAudioRecorder.this.sendMsg(1);
                    XMAudioRecorder.this.mIsRecording = true;
                    XMAudioRecorder.this.mCount = 0;
                    XMAudioRecorder.this.mHandler.postDelayed(XMAudioRecorder.this.mSamplingRunnable, 100);
                    // MONITOREXIT : object
                    return null;
                }
                catch (Exception var3_7) {
                    Log.w((String)"XMAudioRecorder", (String)"error occures when staring recorder.", (Throwable)var3_7);
                    return null;
                }
                catch (IllegalStateException illegalStateException) {
                    illegalStateException.printStackTrace();
                    XMAudioRecorder.this.sendMsg(-1);
                    return null;
                }
                catch (IOException iOException) {
                    if (iOException instanceof FileNotFoundException && SDCardUtils.isSDCardFull()) {
                        Toast.makeText((Context)XMAudioRecorder.this.mContext, (CharSequence)"sdcard_has_not_enough_space", (int)1);
                    }
                    iOException.printStackTrace();
                    XMAudioRecorder.this.sendMsg(-1);
                    return null;
                }
                catch (Exception exception) {
                    exception.printStackTrace();
                    XMAudioRecorder.this.sendMsg(-1);
                    return null;
                }
            }
        }.execute((Object[])new Void[0]);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void stop() {
        Object object = this.mLock;
        synchronized (object) {
            this.mIsRecording = false;
            if (this.mRecorder == null) {
                return;
            }
            try {
                this.mRecorder.stop();
                this.sendMsg(3);
            }
            catch (Exception var2_2) {
                var2_2.printStackTrace();
                this.clear();
            }
            this.mRecorder.release();
            return;
        }
    }

}

