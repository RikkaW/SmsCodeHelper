/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Handler
 *  android.os.Looper
 *  android.os.Message
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Thread
 *  java.util.concurrent.LinkedBlockingQueue
 *  java.util.concurrent.TimeUnit
 */
package com.xiaomi.mms.mx.fw.misc;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.xiaomi.mms.mx.utils.Log;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class SerializedAsyncTaskProcessor {
    private volatile SerializedAsyncTask mCurrentTask;
    private final boolean mIsDaemon;
    private Handler mMainThreadHandler = null;
    private ProcessPackageThread mProcessThread;
    private volatile boolean threadQuit = false;

    public SerializedAsyncTaskProcessor() {
        this(false);
    }

    public SerializedAsyncTaskProcessor(boolean bl) {
        this.mMainThreadHandler = new Handler(Looper.getMainLooper()){

            /*
             * Enabled aggressive block sorting
             */
            public void handleMessage(Message message) {
                SerializedAsyncTask serializedAsyncTask = (SerializedAsyncTask)message.obj;
                if (message.what == 0) {
                    serializedAsyncTask.preProcess();
                } else if (message.what == 1) {
                    serializedAsyncTask.postProcess();
                }
                super.handleMessage(message);
            }
        };
        this.mIsDaemon = bl;
    }

    public void addNewTask(SerializedAsyncTask serializedAsyncTask) {
        synchronized (this) {
            if (this.mProcessThread == null) {
                this.mProcessThread = new ProcessPackageThread();
                this.mProcessThread.setDaemon(this.mIsDaemon);
                this.mProcessThread.start();
            }
            this.mProcessThread.insertTask(serializedAsyncTask);
            return;
        }
    }

    public void addNewTaskWithDelayed(final SerializedAsyncTask serializedAsyncTask, long l) {
        this.mMainThreadHandler.postDelayed(new Runnable(){

            @Override
            public void run() {
                SerializedAsyncTaskProcessor.this.addNewTask(serializedAsyncTask);
            }
        }, l);
    }

    private class ProcessPackageThread
    extends Thread {
        private final LinkedBlockingQueue<SerializedAsyncTask> mTasks;

        public ProcessPackageThread() {
            super("PackageProcessor");
            this.mTasks = new LinkedBlockingQueue();
        }

        public void insertTask(SerializedAsyncTask serializedAsyncTask) {
            this.mTasks.add((Object)serializedAsyncTask);
        }

        public void run() {
            while (!SerializedAsyncTaskProcessor.this.threadQuit) {
                try {
                    SerializedAsyncTaskProcessor.this.mCurrentTask = (SerializedAsyncTask)this.mTasks.poll(1, TimeUnit.SECONDS);
                    if (SerializedAsyncTaskProcessor.this.mCurrentTask == null) continue;
                    Message message = SerializedAsyncTaskProcessor.this.mMainThreadHandler.obtainMessage(0, (Object)SerializedAsyncTaskProcessor.this.mCurrentTask);
                    SerializedAsyncTaskProcessor.this.mMainThreadHandler.sendMessage(message);
                    SerializedAsyncTaskProcessor.this.mCurrentTask.process();
                    message = SerializedAsyncTaskProcessor.this.mMainThreadHandler.obtainMessage(1, (Object)SerializedAsyncTaskProcessor.this.mCurrentTask);
                    SerializedAsyncTaskProcessor.this.mMainThreadHandler.sendMessage(message);
                }
                catch (InterruptedException var1_2) {
                    Log.e("SerializedAsyncTaskProcessor", var1_2.toString());
                }
            }
        }
    }

    public static abstract class SerializedAsyncTask {
        public void postProcess() {
        }

        public void preProcess() {
        }

        public abstract void process();
    }

}

