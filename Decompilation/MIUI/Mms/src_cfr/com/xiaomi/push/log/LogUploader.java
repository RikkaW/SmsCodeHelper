/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  java.io.File
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.util.Date
 *  java.util.HashMap
 *  java.util.concurrent.ConcurrentLinkedQueue
 *  org.json.JSONException
 *  org.json.JSONObject
 */
package com.xiaomi.push.log;

import android.content.Context;
import android.content.SharedPreferences;
import com.xiaomi.channel.commonutils.file.SDCardUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.misc.SerializedAsyncTaskProcessor;
import com.xiaomi.channel.commonutils.network.Network;
import com.xiaomi.push.log.LogFilter;
import com.xiaomi.smack.util.SystemUtils;
import com.xiaomi.smack.util.TaskExecutor;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.json.JSONException;
import org.json.JSONObject;

public class LogUploader {
    private static LogUploader sInstance = null;
    private Context mContext;
    private final ConcurrentLinkedQueue<Task> mTasks = new ConcurrentLinkedQueue();

    private LogUploader(Context context) {
        this.mContext = context;
        this.mTasks.add((Object)new CleanUpTask());
        this.executeTask(0);
    }

    private void cleanExpiredTask() {
        while (!this.mTasks.isEmpty() && (((Task)this.mTasks.peek()).isExpired() || this.mTasks.size() > 6)) {
            MyLog.v("remove Expired task");
            this.mTasks.remove();
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private void cleanUp() {
        if (SDCardUtils.isSDCardBusy()) return;
        if (SDCardUtils.isSDCardUnavailable()) {
            return;
        }
        try {
            File[] arrfile = new File[]((Object)this.mContext.getExternalFilesDir(null) + "/.logcache");
            if (!arrfile.exists()) return;
            if (!arrfile.isDirectory()) return;
            arrfile = arrfile.listFiles();
            int n = arrfile.length;
            int n2 = 0;
            while (n2 < n) {
                arrfile[n2].delete();
                ++n2;
            }
            return;
        }
        catch (NullPointerException var3_2) {
            return;
        }
    }

    private void executeTask(long l) {
        if (!this.mTasks.isEmpty()) {
            TaskExecutor.execute(new SerializedAsyncTaskProcessor.SerializedAsyncTask(){
                SerializedAsyncTaskProcessor.SerializedAsyncTask current;

                @Override
                public void postProcess() {
                    if (this.current != null) {
                        this.current.postProcess();
                    }
                }

                @Override
                public void process() {
                    Task task = (Task)LogUploader.this.mTasks.peek();
                    if (task != null && task.canExcuteNow()) {
                        this.current = (SerializedAsyncTaskProcessor.SerializedAsyncTask)LogUploader.this.mTasks.remove();
                        this.current.process();
                    }
                }
            }, l);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static LogUploader getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LogUploader.class) {
                if (sInstance == null) {
                    sInstance = new LogUploader(context);
                }
            }
        }
        LogUploader.sInstance.mContext = context;
        return sInstance;
    }

    private void uploadIfNeed(long l) {
        Task task = (Task)this.mTasks.peek();
        if (task != null && task.canExcuteNow()) {
            this.executeTask(l);
        }
    }

    public void checkUpload() {
        this.cleanExpiredTask();
        this.uploadIfNeed(0);
    }

    public void upload(final String string2, final String string3, final Date date, final Date date2, final int n, final boolean bl) {
        this.mTasks.add((Object)new Task(){
            File file;

            @Override
            public void postProcess() {
                if (this.file != null && this.file.exists()) {
                    LogUploader.this.mTasks.add((Object)new UploadTask(string2, string3, this.file, bl));
                }
                LogUploader.this.uploadIfNeed(0);
            }

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             * Lifted jumps to return sites
             */
            @Override
            public void process() {
                if (!SDCardUtils.isSDCardUseful()) {
                    return;
                }
                try {
                    File file = new File((Object)LogUploader.this.mContext.getExternalFilesDir(null) + "/.logcache");
                    file.mkdirs();
                    if (!file.isDirectory()) return;
                    LogFilter logFilter = new LogFilter();
                    logFilter.setMaxLen(n);
                    this.file = logFilter.filter(LogUploader.this.mContext, date, date2, file);
                    return;
                }
                catch (NullPointerException var1_2) {
                    return;
                }
            }
        });
        this.executeTask(0);
    }

    class CleanUpTask
    extends Task {
        CleanUpTask() {
            super();
        }

        @Override
        public void process() {
            LogUploader.this.cleanUp();
        }
    }

    class Task
    extends SerializedAsyncTaskProcessor.SerializedAsyncTask {
        long timestamp;

        Task() {
            this.timestamp = System.currentTimeMillis();
        }

        public boolean canExcuteNow() {
            return true;
        }

        final boolean isExpired() {
            if (System.currentTimeMillis() - this.timestamp > 172800000) {
                return true;
            }
            return false;
        }

        @Override
        public void process() {
        }
    }

    class UploadTask
    extends Task {
        File file;
        boolean force;
        int retryNum;
        String token;
        boolean uploaded;
        String url;

        UploadTask(String string2, String string3, File file, boolean bl) {
            this.url = string2;
            this.token = string3;
            this.file = file;
            this.force = bl;
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        private boolean checkLimit() {
            String string2;
            long l;
            int n;
            SharedPreferences sharedPreferences;
            int n2;
            sharedPreferences = LogUploader.this.mContext.getSharedPreferences("log.timestamp", 0);
            string2 = sharedPreferences.getString("log.requst", "");
            long l2 = System.currentTimeMillis();
            n2 = 0;
            l = l2;
            try {
                string2 = new JSONObject(string2);
                l = l2;
                l = l2 = string2.getLong("time");
                n2 = n = string2.getInt("times");
                l = l2;
            }
            catch (JSONException var8_4) {}
            if (System.currentTimeMillis() - l < 86400000) {
                n = n2;
                if (n2 > 10) {
                    return false;
                }
            } else {
                l = System.currentTimeMillis();
                n = 0;
            }
            string2 = new JSONObject();
            try {
                string2.put("time", l);
                string2.put("times", n + 1);
                sharedPreferences.edit().putString("log.requst", string2.toString()).commit();
                return true;
            }
            catch (JSONException var7_2) {
                MyLog.v("JSONException on put " + var7_2.getMessage());
                return true;
            }
        }

        @Override
        public boolean canExcuteNow() {
            if (Network.isWIFIConnected(LogUploader.this.mContext) || this.force && Network.hasNetwork(LogUploader.this.mContext)) {
                return true;
            }
            return false;
        }

        @Override
        public void postProcess() {
            if (!this.uploaded) {
                ++this.retryNum;
                if (this.retryNum < 3) {
                    LogUploader.this.mTasks.add((Object)this);
                }
            }
            if (this.uploaded || this.retryNum >= 3) {
                this.file.delete();
            }
            LogUploader.this.uploadIfNeed((1 << this.retryNum) * 1000);
        }

        @Override
        public void process() {
            try {
                if (this.checkLimit()) {
                    HashMap hashMap = new HashMap();
                    hashMap.put((Object)"uid", (Object)SystemUtils.getDeviceUUID());
                    hashMap.put((Object)"token", (Object)this.token);
                    hashMap.put((Object)"net", (Object)Network.getActiveConnPoint(LogUploader.this.mContext));
                    Network.uploadFile(this.url, hashMap, this.file, "file");
                }
                this.uploaded = true;
                return;
            }
            catch (IOException var1_2) {
                return;
            }
        }
    }

}

