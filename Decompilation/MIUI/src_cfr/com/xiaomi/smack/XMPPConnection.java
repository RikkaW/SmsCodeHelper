/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.os.SystemClock
 *  android.text.TextUtils
 *  java.io.BufferedReader
 *  java.io.BufferedWriter
 *  java.io.InputStreamReader
 *  java.io.OutputStream
 *  java.io.OutputStreamWriter
 *  java.io.Reader
 *  java.io.Writer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 *  java.net.InetAddress
 *  java.net.InetSocketAddress
 *  java.net.Socket
 *  java.util.ArrayList
 */
package com.xiaomi.smack;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.network.Network;
import com.xiaomi.network.Fallback;
import com.xiaomi.network.Host;
import com.xiaomi.network.HostManager;
import com.xiaomi.push.service.PushClientsManager;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.smack.Connection;
import com.xiaomi.smack.ConnectionConfiguration;
import com.xiaomi.smack.PacketListener;
import com.xiaomi.smack.PacketReader;
import com.xiaomi.smack.PacketWriter;
import com.xiaomi.smack.XMBinder;
import com.xiaomi.smack.XMPPException;
import com.xiaomi.smack.debugger.SmackDebugger;
import com.xiaomi.smack.filter.PacketFilter;
import com.xiaomi.smack.packet.Packet;
import com.xiaomi.smack.packet.Presence;
import com.xiaomi.smack.util.TaskExecutor;
import com.xiaomi.stats.StatsHelper;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;

public class XMPPConnection
extends Connection {
    private final String PING_PERF = "<pf><p>t:%1$d</p></pf>";
    private String connectedHost;
    String connectionID = null;
    private int curShortConnCount;
    public Exception failedException = null;
    private volatile long lastConnectedTime = 0;
    private volatile long lastPingReceived = 0;
    private volatile long lastPingSent = 0;
    PacketReader packetReader;
    PacketWriter packetWriter;
    private String pingString = "";
    private XMPushService pushService;
    protected Socket socket;
    private String user = null;

    public XMPPConnection(XMPushService xMPushService, ConnectionConfiguration connectionConfiguration) {
        super(xMPushService, connectionConfiguration);
        this.pushService = xMPushService;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void connectDirectly(String var1_1, int var2_2) throws XMPPException {
        var4_3 = 0;
        this.failedException = null;
        var14_4 = new ArrayList<String>();
        var3_5 = MyLog.ps("get bucket for host : " + var1_1);
        var15_6 = this.getFallback(var1_1);
        MyLog.pe(var3_5);
        if (var15_6 != null) {
            var14_4 = var15_6.getHosts(true);
        }
        if (var14_4.isEmpty()) {
            var14_4.add((Object)var1_1);
        }
        this.lastConnectedTime = 0;
        var1_1 = Network.getActiveConnPoint((Context)this.pushService);
        var16_8 = new StringBuilder();
        var17_9 = var14_4.iterator();
        do lbl-1000: // 7 sources:
        {
            var3_5 = var4_3;
            if (!var17_9.hasNext()) break;
            var14_4 = (String)var17_9.next();
            var12_13 = System.currentTimeMillis();
            ++this.connTimes;
            var5_10 = var4_3;
            var6_11 = var4_3;
            var7_12 = var4_3;
            var3_5 = var4_3;
            try {
                MyLog.warn("begin to connect to " + (String)var14_4);
                var5_10 = var4_3;
                var6_11 = var4_3;
                var7_12 = var4_3;
                var3_5 = var4_3;
                this.socket = this.createSocket();
                var5_10 = var4_3;
                var6_11 = var4_3;
                var7_12 = var4_3;
                var3_5 = var4_3;
                this.socket.bind(null);
                var5_10 = var4_3;
                var6_11 = var4_3;
                var7_12 = var4_3;
                var3_5 = var4_3;
                var18_14 = Host.from((String)var14_4, var2_2);
                var5_10 = var4_3;
                var6_11 = var4_3;
                var7_12 = var4_3;
                var3_5 = var4_3;
                this.socket.connect((SocketAddress)var18_14, 5000);
                var5_10 = var4_3;
                var6_11 = var4_3;
                var7_12 = var4_3;
                var3_5 = var4_3;
                this.socket.setTcpNoDelay(true);
                var5_10 = var4_3;
                var6_11 = var4_3;
                var7_12 = var4_3;
                var3_5 = var4_3;
                this.connectedHost = var14_4;
                var5_10 = var4_3;
                var6_11 = var4_3;
                var7_12 = var4_3;
                var3_5 = var4_3;
                this.initConnection();
                var8_18 = 1;
                var9_19 = 1;
                var10_20 = 1;
                var11_21 = 1;
                var4_3 = 1;
                var5_10 = var8_18;
                var6_11 = var9_19;
                var7_12 = var10_20;
                var3_5 = var11_21;
                this.connectTime = System.currentTimeMillis() - var12_13;
                if (var15_6 != null) {
                    var5_10 = var8_18;
                    var6_11 = var9_19;
                    var7_12 = var10_20;
                    var3_5 = var11_21;
                    var15_6.succeedHost((String)var14_4, this.connectTime, 0);
                }
                var5_10 = var8_18;
                var6_11 = var9_19;
                var7_12 = var10_20;
                var3_5 = var11_21;
                this.lastConnectedTime = SystemClock.elapsedRealtime();
                var5_10 = var8_18;
                var6_11 = var9_19;
                var7_12 = var10_20;
                var3_5 = var11_21;
                MyLog.warn("connected to " + (String)var14_4 + " in " + this.connectTime);
                var3_5 = var4_3;
                if (true) break;
            }
            catch (IOException var18_15) {
                if (var15_6 == null) ** GOTO lbl99
                var3_5 = var5_10;
                var15_6.failedHost((String)var14_4, System.currentTimeMillis() - var12_13, 0, var18_15);
lbl99: // 2 sources:
                var3_5 = var5_10;
                this.failedException = var18_15;
                var3_5 = var5_10;
                MyLog.e("SMACK: Could not connect to:" + (String)var14_4);
                var3_5 = var5_10;
                var16_8.append("SMACK: Could not connect to ").append((String)var14_4).append(" port:").append(var2_2).append(" ").append(var18_15.getMessage()).append("\n");
                var4_3 = var5_10;
                if (var5_10 != 0) ** GOTO lbl-1000
                StatsHelper.connectFail((String)var14_4, this.failedException);
                var4_3 = var5_10;
                if (TextUtils.equals((CharSequence)var1_1, (CharSequence)Network.getActiveConnPoint((Context)this.pushService))) ** GOTO lbl-1000
                var3_5 = var5_10;
                break;
                catch (XMPPException var18_16) {
                    if (var15_6 == null) ** GOTO lbl117
                    var3_5 = var6_11;
                    var15_6.failedHost((String)var14_4, System.currentTimeMillis() - var12_13, 0, var18_16);
lbl117: // 2 sources:
                    var3_5 = var6_11;
                    this.failedException = var18_16;
                    var3_5 = var6_11;
                    MyLog.e("SMACK: Could not connect to:" + (String)var14_4);
                    var3_5 = var6_11;
                    var16_8.append("SMACK: Could not connect to ").append((String)var14_4).append(" port:").append(var2_2).append(" ").append(var18_16.getMessage()).append("\n");
                    var4_3 = var6_11;
                    if (var6_11 != 0) ** GOTO lbl-1000
                    StatsHelper.connectFail((String)var14_4, this.failedException);
                    var4_3 = var6_11;
                    if (TextUtils.equals((CharSequence)var1_1, (CharSequence)Network.getActiveConnPoint((Context)this.pushService))) ** GOTO lbl-1000
                    var3_5 = var6_11;
                    break;
                    catch (Throwable var18_17) {
                        var3_5 = var7_12;
                        try {
                            this.failedException = new Exception("abnormal exception", var18_17);
                            var3_5 = var7_12;
                            MyLog.e(var18_17);
                            var4_3 = var7_12;
                            if (var7_12 != 0) ** GOTO lbl-1000
                        }
                        catch (Throwable var15_7) {
                            if (var3_5 == 0) {
                                StatsHelper.connectFail((String)var14_4, this.failedException);
                                if (!TextUtils.equals((CharSequence)var1_1, (CharSequence)Network.getActiveConnPoint((Context)this.pushService))) break;
                            }
                            throw var15_7;
                        }
                        StatsHelper.connectFail((String)var14_4, this.failedException);
                        var4_3 = var7_12;
                        if (TextUtils.equals((CharSequence)var1_1, (CharSequence)Network.getActiveConnPoint((Context)this.pushService))) continue;
                        var3_5 = var7_12;
                        break;
                    }
                }
            }
            StatsHelper.connectFail((String)var14_4, this.failedException);
            var3_5 = var4_3;
            if (TextUtils.equals((CharSequence)var1_1, (CharSequence)Network.getActiveConnPoint((Context)this.pushService))) break;
            break;
            break;
        } while (true);
        HostManager.getInstance().persist();
        if (var3_5 == 0) {
            throw new XMPPException(var16_8.toString());
        }
    }

    private void connectUsingConfiguration(ConnectionConfiguration connectionConfiguration) throws XMPPException, IOException {
        this.connectDirectly(connectionConfiguration.getHost(), connectionConfiguration.getPort());
    }

    private void initConnection() throws XMPPException, IOException {
        synchronized (this) {
            this.initReaderAndWriter();
            this.packetWriter = new PacketWriter(this);
            this.packetReader = new PacketReader(this);
            if (this.config.isDebuggerEnabled()) {
                this.addPacketListener(this.debugger.getReaderListener(), null);
                if (this.debugger.getWriterListener() != null) {
                    this.addPacketSendingListener(this.debugger.getWriterListener(), null);
                }
            }
            this.packetWriter.openStream();
            this.packetReader.startup();
            return;
        }
    }

    private void initReaderAndWriter() throws XMPPException {
        try {
            this.reader = new BufferedReader((Reader)new InputStreamReader(this.socket.getInputStream(), "UTF-8"), 4096);
            this.writer = new BufferedWriter((Writer)new OutputStreamWriter(this.socket.getOutputStream(), "UTF-8"));
            if (this.reader != null && this.writer != null) {
                this.initDebugger();
            }
            return;
        }
        catch (Exception var1_1) {
            throw new XMPPException("Error to init reader and writer", var1_1);
        }
    }

    private void sinkdownHost(Exception exception) {
        if (SystemClock.elapsedRealtime() - this.lastConnectedTime < 300000) {
            if (Network.hasNetwork((Context)this.pushService)) {
                ++this.curShortConnCount;
                if (this.curShortConnCount >= 2) {
                    String string2 = this.getHost();
                    MyLog.warn("max short conn time reached, sink down current host:" + string2);
                    this.sinkdownHost(string2, 0, exception);
                    this.curShortConnCount = 0;
                }
            }
            return;
        }
        this.curShortConnCount = 0;
    }

    private void sinkdownHost(String string2, long l, Exception exception) {
        Object object = ConnectionConfiguration.getXmppServerHost();
        object = HostManager.getInstance().getFallbacksByHost((String)object, false);
        if (object != null) {
            object.failedHost(string2, l, 0, exception);
            HostManager.getInstance().persist();
        }
    }

    @Override
    public void batchSendPacket(Packet[] arrpacket) throws XMPPException {
        int n = arrpacket.length;
        for (int i = 0; i < n; ++i) {
            this.sendPacket(arrpacket[i]);
        }
    }

    @Override
    public void bind(PushClientsManager.ClientLoginInfo clientLoginInfo) throws XMPPException {
        synchronized (this) {
            new XMBinder().doBind(clientLoginInfo, this.getChallenge(), this);
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void connect() throws XMPPException {
        synchronized (this) {
            try {
                if (this.isConnected() || this.isConnecting()) {
                    MyLog.warn("WARNING: current xmpp has connected");
                } else {
                    this.setConnectionStatus(0, 0, null);
                    this.connectUsingConfiguration(this.config);
                }
                return;
            }
            catch (IOException var1_1) {
                throw new XMPPException(var1_1);
            }
        }
    }

    public Socket createSocket() {
        return new Socket();
    }

    @Override
    public void disconnect(Presence presence, int n, Exception exception) {
        this.shutdown(presence, n, exception);
        if (exception != null && this.lastConnectedTime != 0) {
            this.sinkdownHost(exception);
        }
    }

    public String getChallenge() {
        return this.challenge;
    }

    Fallback getFallback(final String string2) {
        Fallback fallback = HostManager.getInstance().getFallbacksByHost(string2, false);
        if (!fallback.isEffective()) {
            TaskExecutor.execute(new Runnable(){

                @Override
                public void run() {
                    HostManager.getInstance().getFallbacksByHost(string2, true);
                }
            });
        }
        this.clientIP = 0;
        try {
            string2 = (String)InetAddress.getByName((String)fallback.ip).getAddress();
            this.clientIP = string2[0] & 255;
            this.clientIP |= string2[1] << 8 & 65280;
            this.clientIP |= string2[2] << 16 & 16711680;
            this.clientIP |= string2[3] << 24 & -16777216;
            return fallback;
        }
        catch (UnknownHostException var1_2) {
            return fallback;
        }
    }

    @Override
    public String getHost() {
        return this.connectedHost;
    }

    /*
     * Enabled aggressive block sorting
     */
    public String getPingString() {
        String string2 = this.lastPingReceived == 0 || this.lastPingSent == 0 ? "" : String.format((String)"<pf><p>t:%1$d</p></pf>", (Object[])new Object[]{this.lastPingReceived - this.lastPingSent});
        String string3 = StatsHelper.retriveStatsAsString();
        string3 = string3 != null ? "<q>" + string3 + "</q>" : "";
        return String.format((String)this.pingString, (Object[])new Object[]{string2, string3});
    }

    public void notifyConnectionError(final int n, final Exception exception) {
        this.pushService.executeJob(new XMPushService.Job(2){

            @Override
            public String getDesc() {
                return "shutdown the connection. " + n + ", " + (Object)((Object)exception);
            }

            @Override
            public void process() {
                XMPPConnection.this.pushService.disconnect(n, exception);
            }
        });
    }

    @Override
    public void sendPacket(Packet packet) throws XMPPException {
        if (this.packetWriter != null) {
            this.packetWriter.sendPacket(packet);
            return;
        }
        throw new XMPPException("the writer is null.");
    }

    @Override
    public void sendPingString() throws XMPPException {
        if (this.packetWriter != null) {
            this.packetWriter.sendPingString();
            final long l = System.currentTimeMillis();
            this.pushService.executeJobDelayed(new XMPushService.Job(13){

                @Override
                public String getDesc() {
                    return "check the ping-pong.";
                }

                @Override
                public void process() {
                    if (XMPPConnection.this.isConnected() && !XMPPConnection.this.isReadAlive(l)) {
                        XMPPConnection.this.pushService.disconnect(22, null);
                    }
                }
            }, 15000);
            return;
        }
        throw new XMPPException("the packetwriter is null.");
    }

    public void setPingString(String string2) {
        this.pingString = string2;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected void shutdown(Presence object, int n, Exception exception) {
        synchronized (this) {
            int n2 = this.getConnectionStatus();
            if (n2 != 2) {
                this.setConnectionStatus(2, n, exception);
                this.challenge = "";
                if (this.packetReader != null) {
                    this.packetReader.shutdown();
                    this.packetReader.cleanup();
                    this.packetReader = null;
                }
                if ((object = this.packetWriter) != null) {
                    try {
                        this.packetWriter.shutdown();
                    }
                    catch (IOException var1_2) {
                        MyLog.e(var1_2);
                    }
                    this.packetWriter.cleanup();
                    this.packetWriter = null;
                }
                try {
                    this.socket.close();
                }
                catch (Throwable var1_5) {}
                if ((object = this.reader) != null) {
                    try {
                        this.reader.close();
                    }
                    catch (Throwable var1_4) {}
                    this.reader = null;
                }
                if ((object = this.writer) != null) {
                    try {
                        this.writer.close();
                    }
                    catch (Throwable var1_3) {}
                    this.writer = null;
                }
                this.lastPingSent = 0;
                this.lastPingReceived = 0;
            }
            return;
        }
    }

    @Override
    public void unbind(String string2, String string3) throws XMPPException {
        synchronized (this) {
            Presence presence = new Presence(Presence.Type.unavailable);
            presence.setChannelId(string2);
            presence.setFrom(string3);
            if (this.packetWriter != null) {
                this.packetWriter.sendPacket(presence);
            }
            return;
        }
    }

    public void updateLastReceived() {
        this.lastPingReceived = SystemClock.uptimeMillis();
    }

    public void updateLastSent() {
        this.lastPingSent = SystemClock.uptimeMillis();
    }

}

