/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.Pair
 *  java.io.Reader
 *  java.io.Writer
 *  java.lang.Boolean
 *  java.lang.Class
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 *  java.lang.reflect.Constructor
 *  java.util.LinkedList
 *  java.util.concurrent.ConcurrentHashMap
 *  java.util.concurrent.CopyOnWriteArrayList
 *  java.util.concurrent.atomic.AtomicInteger
 */
package com.xiaomi.smack;

import android.content.Context;
import android.util.Pair;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.network.Network;
import com.xiaomi.channel.commonutils.string.MD5;
import com.xiaomi.measite.smack.AndroidDebugger;
import com.xiaomi.push.service.PushClientsManager;
import com.xiaomi.push.service.PushConstants;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.smack.ConnectionConfiguration;
import com.xiaomi.smack.ConnectionListener;
import com.xiaomi.smack.PacketListener;
import com.xiaomi.smack.SmackConfiguration;
import com.xiaomi.smack.XMPPException;
import com.xiaomi.smack.debugger.SmackDebugger;
import com.xiaomi.smack.filter.PacketFilter;
import com.xiaomi.smack.packet.Packet;
import com.xiaomi.smack.packet.Presence;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Connection {
    public static boolean DEBUG_ENABLED;
    private static final AtomicInteger connectionCounter;
    protected String challenge = "";
    protected int clientIP;
    protected ConnectionConfiguration config;
    protected int connTimes = 0;
    private int connectStatus = 2;
    protected long connectTime = -1;
    protected final int connectionCounterValue = connectionCounter.getAndIncrement();
    private final Collection<ConnectionListener> connectionListeners = new CopyOnWriteArrayList();
    protected SmackDebugger debugger = null;
    private LinkedList<Pair<Integer, Long>> mCachedStatus = new LinkedList();
    protected XMPushService mPushService;
    private long readAlive = 0;
    protected Reader reader;
    protected final Map<PacketListener, ListenerWrapper> recvListeners = new ConcurrentHashMap();
    protected final Map<PacketListener, ListenerWrapper> sendListeners = new ConcurrentHashMap();
    protected Writer writer;

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    static {
        connectionCounter = new AtomicInteger(0);
        DEBUG_ENABLED = false;
        try {
            DEBUG_ENABLED = Boolean.getBoolean((String)"smack.debugEnabled");
        }
        catch (Exception var0) {}
        SmackConfiguration.getVersion();
    }

    protected Connection(XMPushService xMPushService, ConnectionConfiguration connectionConfiguration) {
        this.config = connectionConfiguration;
        this.mPushService = xMPushService;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void addStatus(int n) {
        LinkedList<Pair<Integer, Long>> linkedList = this.mCachedStatus;
        synchronized (linkedList) {
            if (n == 1) {
                this.mCachedStatus.clear();
            } else {
                this.mCachedStatus.add((Object)new Pair((Object)n, (Object)System.currentTimeMillis()));
                if (this.mCachedStatus.size() > 6) {
                    this.mCachedStatus.remove(0);
                }
            }
            return;
        }
    }

    private String getDesc(int n) {
        if (n == 1) {
            return "connected";
        }
        if (n == 0) {
            return "connecting";
        }
        if (n == 2) {
            return "disconnected";
        }
        return "unknown";
    }

    /*
     * Enabled aggressive block sorting
     */
    public void addConnectionListener(ConnectionListener connectionListener) {
        if (connectionListener == null || this.connectionListeners.contains(connectionListener)) {
            return;
        }
        this.connectionListeners.add(connectionListener);
    }

    public void addPacketListener(PacketListener packetListener, PacketFilter object) {
        if (packetListener == null) {
            throw new NullPointerException("Packet listener is null.");
        }
        object = new ListenerWrapper(packetListener, (PacketFilter)object);
        this.recvListeners.put(packetListener, (ListenerWrapper)object);
    }

    public void addPacketSendingListener(PacketListener packetListener, PacketFilter object) {
        if (packetListener == null) {
            throw new NullPointerException("Packet listener is null.");
        }
        object = new ListenerWrapper(packetListener, (PacketFilter)object);
        this.sendListeners.put(packetListener, (ListenerWrapper)object);
    }

    public abstract void batchSendPacket(Packet[] var1) throws XMPPException;

    public abstract void bind(PushClientsManager.ClientLoginInfo var1) throws XMPPException;

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void clearCachedStatus() {
        LinkedList<Pair<Integer, Long>> linkedList = this.mCachedStatus;
        synchronized (linkedList) {
            this.mCachedStatus.clear();
            return;
        }
    }

    public abstract void disconnect(Presence var1, int var2, Exception var3);

    protected void firePacketSendingListeners(Packet packet) {
        Iterator<ListenerWrapper> iterator = this.sendListeners.values().iterator();
        while (iterator.hasNext()) {
            iterator.next().notifyListener(packet);
        }
    }

    public int getClientIP() {
        return this.clientIP;
    }

    public ConnectionConfiguration getConfiguration() {
        return this.config;
    }

    public int getConnTryTimes() {
        return this.connTimes;
    }

    public long getConnectTime() {
        return this.connectTime;
    }

    public String getConnectionPoint() {
        return this.config.getConnectionPoint();
    }

    public int getConnectionStatus() {
        return this.connectStatus;
    }

    public String getHost() {
        return this.config.getHost();
    }

    public String getServiceName() {
        return this.config.getServiceName();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected void initDebugger() {
        String string2;
        Class class_ = null;
        if (this.reader == null) return;
        if (this.writer == null || !this.config.isDebuggerEnabled()) {
            return;
        }
        if (this.debugger != null) {
            this.reader = this.debugger.newConnectionReader(this.reader);
            this.writer = this.debugger.newConnectionWriter(this.writer);
            return;
        }
        try {
            string2 = System.getProperty((String)"smack.debuggerClass");
        }
        catch (Throwable var1_3) {
            string2 = null;
        }
        Class class_2 = class_;
        if (string2 != null) {
            try {
                class_2 = Class.forName((String)string2);
            }
            catch (Exception var1_4) {
                var1_4.printStackTrace();
                class_2 = class_;
            }
        }
        if (class_2 == null) {
            this.debugger = new AndroidDebugger(this, this.writer, this.reader);
            this.reader = this.debugger.getReader();
            this.writer = this.debugger.getWriter();
            return;
        }
        try {
            this.debugger = (SmackDebugger)class_2.getConstructor(new Class[]{Connection.class, Writer.class, Reader.class}).newInstance(new Object[]{this, this.writer, this.reader});
            this.reader = this.debugger.getReader();
            this.writer = this.debugger.getWriter();
            return;
        }
        catch (Exception var1_5) {
            throw new IllegalArgumentException("Can't initialize the configured debugger!", var1_5);
        }
    }

    public boolean isConnected() {
        if (this.connectStatus == 1) {
            return true;
        }
        return false;
    }

    public boolean isConnecting() {
        if (this.connectStatus == 0) {
            return true;
        }
        return false;
    }

    public boolean isReadAlive() {
        if (System.currentTimeMillis() - this.readAlive < (long)SmackConfiguration.getCheckAliveInterval()) {
            return true;
        }
        return false;
    }

    public boolean isReadAlive(long l) {
        if (this.readAlive >= l) {
            return true;
        }
        return false;
    }

    public void removeConnectionListener(ConnectionListener connectionListener) {
        this.connectionListeners.remove(connectionListener);
    }

    public void resetConnTryTimes() {
        this.connTimes = 0;
    }

    public void resetConnectTime() {
        this.connectTime = -1;
    }

    public abstract void sendPacket(Packet var1) throws XMPPException;

    public abstract void sendPingString() throws XMPPException;

    public void setChallenge(String string2) {
        MyLog.warn("setChallenge hash = " + MD5.MD5_32(string2).substring(0, 8));
        this.challenge = string2;
        this.setConnectionStatus(1, 0, null);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setConnectionStatus(int n, int n2, Exception object) {
        if (n != this.connectStatus) {
            MyLog.warn(String.format((String)"update the connection status. %1$s -> %2$s : %3$s ", (Object[])new Object[]{this.getDesc(this.connectStatus), this.getDesc(n), PushConstants.getErrorDesc(n2)}));
        }
        if (Network.hasNetwork((Context)this.mPushService)) {
            this.addStatus(n);
        }
        if (n == 1) {
            this.mPushService.removeJobs(10);
            if (this.connectStatus != 0) {
                MyLog.warn("try set connected while not connecting.");
            }
            this.connectStatus = n;
            object = this.connectionListeners.iterator();
            while (object.hasNext()) {
                object.next().reconnectionSuccessful(this);
            }
            return;
        }
        if (n == 0) {
            this.mPushService.setConnectingTimeout();
            if (this.connectStatus != 2) {
                MyLog.warn("try set connecting while not disconnected.");
            }
            this.connectStatus = n;
            object = this.connectionListeners.iterator();
            while (object.hasNext()) {
                object.next().connectionStarted(this);
            }
            return;
        }
        if (n != 2) return;
        this.mPushService.removeJobs(10);
        if (this.connectStatus != 0) {
            if (this.connectStatus == 1) {
                Iterator<ConnectionListener> iterator = this.connectionListeners.iterator();
                while (iterator.hasNext()) {
                    iterator.next().connectionClosed(this, n2, (Exception)((Object)object));
                }
            }
        } else {
            for (ConnectionListener connectionListener : this.connectionListeners) {
                CancellationException cancellationException = object == null ? new CancellationException("disconnect while connecting") : object;
                connectionListener.reconnectionFailed(this, cancellationException);
            }
        }
        this.connectStatus = n;
    }

    public void setReadAlive() {
        this.readAlive = System.currentTimeMillis();
    }

    public abstract void unbind(String var1, String var2) throws XMPPException;

    protected static class ListenerWrapper {
        private PacketFilter packetFilter;
        private PacketListener packetListener;

        public ListenerWrapper(PacketListener packetListener, PacketFilter packetFilter) {
            this.packetListener = packetListener;
            this.packetFilter = packetFilter;
        }

        public void notifyListener(Packet packet) {
            if (this.packetFilter == null || this.packetFilter.accept(packet)) {
                this.packetListener.processPacket(packet);
            }
        }
    }

}

