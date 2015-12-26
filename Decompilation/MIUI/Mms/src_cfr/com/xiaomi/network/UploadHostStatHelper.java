/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 *  java.math.BigInteger
 *  java.security.MessageDigest
 *  java.util.ArrayList
 *  java.util.Random
 *  java.util.Timer
 */
package com.xiaomi.network;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.network.Network;
import com.xiaomi.channel.commonutils.string.Base64Coder;
import com.xiaomi.common.logger.thrift.Common;
import com.xiaomi.common.logger.thrift.mfs.HttpApi;
import com.xiaomi.common.logger.thrift.mfs.HttpLog;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;
import org.apache.thrift.TBase;
import org.apache.thrift.TException;
import org.apache.thrift.TSerializer;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocolFactory;

public class UploadHostStatHelper {
    private static UploadHostStatHelper sInstance;
    private List<HttpRecordCallback> callbacks = new ArrayList();
    private final Random mRandomGenerator = new Random();
    private boolean mTaskPending = false;
    private Timer mTimer = new Timer("Upload Http Record Timer");
    private Context sAppContext = null;

    private UploadHostStatHelper(Context context) {
        this.sAppContext = context.getApplicationContext();
    }

    private byte[] getBytes(String string2) {
        try {
            byte[] arrby = string2.getBytes("UTF-8");
            return arrby;
        }
        catch (UnsupportedEncodingException var2_3) {
            return string2.getBytes();
        }
    }

    public static UploadHostStatHelper getInstance() {
        synchronized (UploadHostStatHelper.class) {
            UploadHostStatHelper uploadHostStatHelper = sInstance;
            return uploadHostStatHelper;
        }
    }

    private String getMd5Digest(String string2) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance((String)"MD5");
            messageDigest.update(this.getBytes(string2));
            string2 = String.format((String)"%1$032X", (Object[])new Object[]{new BigInteger(1, messageDigest.digest())});
            return string2;
        }
        catch (NoSuchAlgorithmException var1_2) {
            throw new RuntimeException(var1_2);
        }
    }

    public static void init(Context context) {
        synchronized (UploadHostStatHelper.class) {
            if (sInstance == null) {
                sInstance = new UploadHostStatHelper(context);
            }
            return;
        }
    }

    private void uploadHostStat(String string2, String string3) throws IOException {
        String string4 = String.valueOf((long)System.nanoTime());
        String string5 = String.valueOf((long)System.currentTimeMillis());
        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        treeMap.put("n", string4);
        treeMap.put("d", string3);
        treeMap.put("t", string5);
        treeMap.put("s", this.getMd5Digest(string4 + string3 + string5 + "56C6A520%$C99119A0&^229(!@2746C7"));
        string2 = String.format((String)"http://%1$s/diagnoses/v1/report", (Object[])new Object[]{string2});
        Network.doHttpPost(this.sAppContext, string2, treeMap);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private void uploadHostStat(List<HttpApi> object, double d2) throws TException {
        object = object.iterator();
        while (object.hasNext()) {
            Object object2 = (HttpApi)object.next();
            HttpLog httpLog = new HttpLog();
            httpLog.setCategory("httpapi");
            httpLog.setHttpApi((HttpApi)object2);
            httpLog.setCommon(new Common());
            object2 = new String(Base64Coder.encode(new TSerializer(new TCompactProtocol.Factory()).serialize(httpLog)));
            if ((double)this.mRandomGenerator.nextInt(10000) >= 10000.0 * d2) continue;
            try {
                this.uploadHostStat("f3.mi-stat.gslb.mi-idc.com", (String)object2);
            }
            catch (IOException var4_4) {
            }
            catch (Exception exception) {}
        }
    }

    public void addCallBack(HttpRecordCallback httpRecordCallback) {
        synchronized (this) {
            this.callbacks.add(httpRecordCallback);
            return;
        }
    }

    public void fireHostEvent() {
        if (!this.mTaskPending) {
            this.mTaskPending = true;
            this.mTimer.schedule(new TimerTask(){

                /*
                 * Enabled aggressive block sorting
                 * Enabled unnecessary exception pruning
                 * Enabled aggressive exception aggregation
                 */
                @Override
                public void run() {
                    Object object = new ArrayList();
                    Object object2 = UploadHostStatHelper.this;
                    synchronized (object2) {
                        object.addAll(UploadHostStatHelper.this.callbacks);
                    }
                    object2 = object.iterator();
                    do {
                        if (!object2.hasNext()) {
                            UploadHostStatHelper.this.mTaskPending = false;
                            return;
                        }
                        object = (HttpRecordCallback)object2.next();
                        List<HttpApi> list = object.generateStat();
                        double d2 = object.getPercentage();
                        if (list == null) continue;
                        try {
                            if (list.size() <= 0) continue;
                            UploadHostStatHelper.this.uploadHostStat(list, d2);
                        }
                        catch (TException var4_2) {
                            MyLog.warn("uploadHostStat exception" + var4_2.toString());
                            continue;
                        }
                        break;
                    } while (true);
                }
            }, 60000);
        }
    }

    public void removeCallBack(HttpRecordCallback httpRecordCallback) {
        synchronized (this) {
            this.callbacks.remove(httpRecordCallback);
            return;
        }
    }

    public static interface HttpRecordCallback {
        public List<HttpApi> generateStat();

        public double getPercentage();
    }

}

