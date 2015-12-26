/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.net.ConnectivityManager
 *  android.net.ConnectivityManager$NetworkCallback
 *  android.net.Network
 *  android.net.NetworkInfo
 *  android.net.NetworkRequest
 *  android.net.NetworkRequest$Builder
 *  android.os.SystemClock
 *  android.util.Log
 *  com.android.okhttp.ConnectionPool
 *  com.android.okhttp.HostResolver
 *  java.lang.Boolean
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.net.InetAddress
 */
package com.android.mms.service;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.SystemClock;
import android.util.Log;
import com.android.mms.service.MmsHttpClient;
import com.android.mms.service.exception.MmsNetworkException;
import com.android.okhttp.ConnectionPool;
import com.android.okhttp.HostResolver;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.net.SocketFactory;

public class MmsNetworkManager
implements HostResolver {
    private static final InetAddress[] EMPTY_ADDRESS_ARRAY;
    private static final boolean httpKeepAlive;
    private static final long httpKeepAliveDurationMs;
    private static final int httpMaxConnections;
    private ConnectionPool mConnectionPool;
    private volatile ConnectivityManager mConnectivityManager;
    private final Context mContext;
    private boolean mIsNetworkLost;
    private boolean mIsNetworkReleased;
    private MmsHttpClient mMmsHttpClient;
    private int mMmsRequestCount;
    private Network mNetwork;
    private ConnectivityManager.NetworkCallback mNetworkCallback;
    private final NetworkRequest mNetworkRequest;
    private int mPendingMmsRequestCount;
    private final int mSubId;

    /*
     * Enabled aggressive block sorting
     */
    static {
        httpKeepAlive = Boolean.parseBoolean((String)System.getProperty((String)"http.keepAlive", (String)"true"));
        int n = httpKeepAlive ? Integer.parseInt((String)System.getProperty((String)"http.maxConnections", (String)"5")) : 0;
        httpMaxConnections = n;
        httpKeepAliveDurationMs = Long.parseLong((String)System.getProperty((String)"http.keepAliveDuration", (String)"300000"));
        EMPTY_ADDRESS_ARRAY = new InetAddress[0];
    }

    public MmsNetworkManager(Context context, int n) {
        this.mContext = context;
        this.mNetworkCallback = null;
        this.mNetwork = null;
        this.mMmsRequestCount = 0;
        this.mConnectivityManager = null;
        this.mConnectionPool = null;
        this.mMmsHttpClient = null;
        this.mSubId = n;
        this.mIsNetworkLost = false;
        this.mIsNetworkReleased = true;
        this.mNetworkRequest = new NetworkRequest.Builder().addTransportType(0).addCapability(0).setNetworkSpecifier(Integer.toString((int)this.mSubId)).build();
    }

    private ConnectivityManager getConnectivityManager() {
        if (this.mConnectivityManager == null) {
            this.mConnectivityManager = (ConnectivityManager)this.mContext.getSystemService("connectivity");
        }
        return this.mConnectivityManager;
    }

    private ConnectionPool getOrCreateConnectionPoolLocked() {
        if (this.mConnectionPool == null) {
            this.mConnectionPool = new ConnectionPool(httpMaxConnections, httpKeepAliveDurationMs);
        }
        return this.mConnectionPool;
    }

    private void newRequest() {
        ConnectivityManager connectivityManager = this.getConnectivityManager();
        this.mNetworkCallback = new ConnectivityManager.NetworkCallback(){

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             */
            public void onAvailable(Network network) {
                super.onAvailable(network);
                Log.d((String)"MmsService", (String)("NetworkCallbackListener.onAvailable: network=" + (Object)network));
                MmsNetworkManager mmsNetworkManager = MmsNetworkManager.this;
                synchronized (mmsNetworkManager) {
                    MmsNetworkManager.this.mNetwork = network;
                    MmsNetworkManager.this.notifyAll();
                    return;
                }
            }

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             */
            public void onLost(Network object) {
                super.onLost((Network)object);
                Log.d((String)"MmsService", (String)("NetworkCallbackListener.onLost: network=" + object));
                object = MmsNetworkManager.this;
                synchronized (object) {
                    MmsNetworkManager.this.mIsNetworkLost = true;
                    MmsNetworkManager.this.releaseRequestLocked(this);
                    MmsNetworkManager.this.notifyAll();
                    return;
                }
            }

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             */
            public void onUnavailable() {
                super.onUnavailable();
                Log.d((String)"MmsService", (String)"NetworkCallbackListener.onUnavailable");
                MmsNetworkManager mmsNetworkManager = MmsNetworkManager.this;
                synchronized (mmsNetworkManager) {
                    MmsNetworkManager.this.mIsNetworkLost = true;
                    MmsNetworkManager.this.releaseRequestLocked(this);
                    MmsNetworkManager.this.notifyAll();
                    return;
                }
            }
        };
        Log.d((String)"MmsService", (String)("newRequest subid = " + this.mSubId));
        connectivityManager.requestNetwork(this.mNetworkRequest, this.mNetworkCallback, 60000);
    }

    private void releaseRequestLocked(ConnectivityManager.NetworkCallback networkCallback) {
        if (networkCallback != null) {
            Log.d((String)"MmsService", (String)"MmsNetworkManager: releaseRequestLocked");
            this.getConnectivityManager().unregisterNetworkCallback(networkCallback);
        }
        this.resetLocked();
    }

    private void resetLocked() {
        this.mIsNetworkReleased = true;
        this.mNetworkCallback = null;
        this.mNetwork = null;
        this.mMmsRequestCount = 0;
        this.mConnectionPool = null;
        this.mMmsHttpClient = null;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void acquireNetwork() throws MmsNetworkException {
        Log.d((String)"MmsService", (String)"MmsNetworkManager: acquireNetwork start");
        synchronized (this) {
            ++this.mMmsRequestCount;
            this.mIsNetworkLost = false;
            this.mIsNetworkReleased = false;
            if (this.mNetwork != null) {
                Log.d((String)"MmsService", (String)"MmsNetworkManager: already available");
                return;
            }
            Log.d((String)"MmsService", (String)"MmsNetworkManager: start new network request");
            this.newRequest();
            long l = SystemClock.elapsedRealtime();
            long l2 = 65000;
            while (l2 > 0) {
                try {
                    this.wait(l2);
                }
                catch (InterruptedException var5_3) {
                    Log.w((String)"MmsService", (String)"MmsNetworkManager: acquire network wait interrupted");
                }
                if (this.mNetwork != null) {
                    return;
                }
                if (this.mIsNetworkLost) {
                    Log.d((String)"MmsService", (String)"MmsNetworkManager: network already lost!");
                    break;
                }
                l2 = SystemClock.elapsedRealtime();
                l2 = l + 65000 - l2;
            }
            Log.d((String)"MmsService", (String)"MmsNetworkManager: timed out");
            this.releaseRequestLocked(this.mNetworkCallback);
            throw new MmsNetworkException("Acquiring network timed out");
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public InetAddress[] getAllByName(String arrinetAddress) throws UnknownHostException {
        synchronized (this) {
            if (this.mNetwork == null) {
                return EMPTY_ADDRESS_ARRAY;
            }
            Network network = this.mNetwork;
            return network.getAllByName((String)arrinetAddress);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public String getApnName() {
        Network network;
        synchronized (this) {
            if (this.mNetwork == null) {
                Log.d((String)"MmsService", (String)"MmsNetworkManager: getApnName: network not available");
                return null;
            }
            network = this.mNetwork;
        }
        String string = null;
        network = this.getConnectivityManager().getNetworkInfo(network);
        if (network != null) {
            string = network.getExtraInfo();
        }
        Log.d((String)"MmsService", (String)("MmsNetworkManager: getApnName: " + string));
        return string;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public MmsHttpClient getOrCreateHttpClient() {
        synchronized (this) {
            if (this.mMmsHttpClient != null) return this.mMmsHttpClient;
            if (this.mNetwork == null) return this.mMmsHttpClient;
            this.mMmsHttpClient = new MmsHttpClient(this.mContext, this.mNetwork.getSocketFactory(), this, this.getOrCreateConnectionPoolLocked());
            return this.mMmsHttpClient;
        }
    }

    public boolean isNetworkReleased() {
        return this.mIsNetworkReleased;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void releaseNetwork() {
        synchronized (this) {
            if (this.mMmsRequestCount > 0) {
                --this.mMmsRequestCount;
                Log.d((String)"MmsService", (String)("MmsNetworkManager: release, count=" + this.mMmsRequestCount + ", mPendingMmsRequestCount = " + this.mPendingMmsRequestCount));
                if (this.mMmsRequestCount < 1 && this.mPendingMmsRequestCount < 1) {
                    this.releaseRequestLocked(this.mNetworkCallback);
                }
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void releaseNetworkImmediately() {
        synchronized (this) {
            Log.d((String)"MmsService", (String)"MmsNetworkManager: release network immdiately");
            this.releaseRequestLocked(this.mNetworkCallback);
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void updatePendingMmsRequestCount(int n) {
        synchronized (this) {
            Log.d((String)"MmsService", (String)("MmsNetworkManager: update pending count: " + n));
            this.mPendingMmsRequestCount = n;
            return;
        }
    }

}

