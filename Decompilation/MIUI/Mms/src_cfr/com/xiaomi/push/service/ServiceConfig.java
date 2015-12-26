/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.util.Base64
 *  java.io.BufferedInputStream
 *  java.io.BufferedOutputStream
 *  java.io.FileInputStream
 *  java.io.FileOutputStream
 *  java.io.OutputStream
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.ArrayList
 */
package com.xiaomi.push.service;

import android.util.Base64;
import com.google.protobuf.micro.CodedInputStreamMicro;
import com.google.protobuf.micro.CodedOutputStreamMicro;
import com.xiaomi.channel.commonutils.file.IOUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.misc.SerializedAsyncTaskProcessor;
import com.xiaomi.network.HttpUtils;
import com.xiaomi.push.protobuf.ChannelConfig;
import com.xiaomi.push.protobuf.ChannelMessage;
import com.xiaomi.smack.util.SystemUtils;
import com.xiaomi.smack.util.TaskExecutor;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ServiceConfig {
    private static ServiceConfig sInstance = new ServiceConfig();
    private ChannelConfig.PushServiceConfig mConfig;
    private List<Listener> mListener = new ArrayList();
    private SerializedAsyncTaskProcessor.SerializedAsyncTask mPendingFetchTask;

    static {
        sInstance.load();
    }

    private ServiceConfig() {
    }

    private void fetchConfig() {
        if (this.mPendingFetchTask != null) {
            return;
        }
        this.mPendingFetchTask = new SerializedAsyncTaskProcessor.SerializedAsyncTask(){
            boolean success;

            @Override
            public void postProcess() {
                ServiceConfig.this.mPendingFetchTask = null;
                if (this.success) {
                    Iterator iterator = ServiceConfig.this.mListener.iterator();
                    while (iterator.hasNext()) {
                        ((Listener)iterator.next()).onConfigChange(ServiceConfig.this.mConfig);
                    }
                }
            }

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            @Override
            public void process() {
                ChannelConfig.PushServiceConfig pushServiceConfig;
                try {
                    pushServiceConfig = ChannelConfig.PushServiceConfig.parseFrom(Base64.decode((String)HttpUtils.get(SystemUtils.getContext(), "http://resolver.msg.xiaomi.net/psc/?t=a", null), (int)10));
                    if (pushServiceConfig == null) return;
                }
                catch (Exception var1_2) {
                    MyLog.warn("fetch config failure: " + var1_2.getMessage());
                    return;
                }
                ServiceConfig.this.mConfig = pushServiceConfig;
                this.success = true;
                ServiceConfig.this.save();
            }
        };
        TaskExecutor.execute(this.mPendingFetchTask);
    }

    public static ServiceConfig getInstance() {
        return sInstance;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private void load() {
        block7 : {
            var4_1 = null;
            var3_2 = null;
            var2_7 = null;
            var1_9 = var3_2;
            if (this.mConfig == null) break block7;
            var1_9 = var3_2;
            var2_7 = new BufferedInputStream((InputStream)SystemUtils.getContext().openFileInput("XMCloudCfg"));
            this.mConfig = ChannelConfig.PushServiceConfig.parseFrom(CodedInputStreamMicro.newInstance((InputStream)var2_7));
            var2_7.close();
        }
        IOUtils.closeQuietly((InputStream)var2_7);
        return;
        catch (Exception var3_3) {
            var2_7 = var4_1;
            ** GOTO lbl23
            catch (Throwable var3_5) {
                var1_9 = var2_7;
                var2_7 = var3_5;
                ** GOTO lbl-1000
            }
            catch (Exception var3_6) {}
lbl23: // 2 sources:
            var1_9 = var2_7;
            try {
                MyLog.warn("save config failure: " + var3_4.getMessage());
            }
            catch (Throwable var2_8) lbl-1000: // 2 sources:
            {
                IOUtils.closeQuietly((InputStream)var1_9);
                throw var2_7;
            }
            IOUtils.closeQuietly((InputStream)var2_7);
            return;
        }
    }

    private void save() {
        try {
            if (this.mConfig != null) {
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream((OutputStream)SystemUtils.getContext().openFileOutput("XMCloudCfg", 0));
                CodedOutputStreamMicro codedOutputStreamMicro = CodedOutputStreamMicro.newInstance((OutputStream)bufferedOutputStream);
                this.mConfig.writeTo(codedOutputStreamMicro);
                codedOutputStreamMicro.flush();
                bufferedOutputStream.close();
            }
            return;
        }
        catch (Exception var1_2) {
            MyLog.warn("save config failure: " + var1_2.getMessage());
            return;
        }
    }

    public void addListener(Listener listener) {
        this.mListener.add(listener);
    }

    void clear() {
        this.mListener.clear();
    }

    public ChannelConfig.PushServiceConfig getConfig() {
        return this.mConfig;
    }

    int getConfigVersion() {
        if (this.mConfig != null) {
            return this.mConfig.getConfigVersion();
        }
        return 0;
    }

    void handle(ChannelMessage.PushServiceConfigMsg pushServiceConfigMsg) {
        if (pushServiceConfigMsg.hasCloudVersion() && pushServiceConfigMsg.getCloudVersion() > this.getConfigVersion()) {
            this.fetchConfig();
        }
        Iterator<Listener> iterator = this.mListener.iterator();
        while (iterator.hasNext()) {
            iterator.next().onConfigMsgReceive(pushServiceConfigMsg);
        }
    }

    public static abstract class Listener {
        public void onConfigChange(ChannelConfig.PushServiceConfig pushServiceConfig) {
        }

        public void onConfigMsgReceive(ChannelMessage.PushServiceConfigMsg pushServiceConfigMsg) {
        }
    }

}

