/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.Reader
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Thread
 *  org.xmlpull.v1.XmlPullParser
 *  org.xmlpull.v1.XmlPullParserException
 *  org.xmlpull.v1.XmlPullParserFactory
 */
package com.xiaomi.smack;

import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.smack.Connection;
import com.xiaomi.smack.ConnectionConfiguration;
import com.xiaomi.smack.XMPPConnection;
import com.xiaomi.smack.XMPPException;
import com.xiaomi.smack.packet.IQ;
import com.xiaomi.smack.packet.Packet;
import com.xiaomi.smack.packet.StreamError;
import com.xiaomi.smack.util.PacketParserUtils;
import java.io.Reader;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

class PacketReader {
    private XMPPConnection connection;
    private boolean done;
    private XmlPullParser parser;
    private Thread readerThread;

    protected PacketReader(XMPPConnection xMPPConnection) {
        this.connection = xMPPConnection;
        this.init();
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void parsePackets() {
        block23 : {
            block25 : {
                block22 : {
                    block24 : {
                        try {
                            this.setParser();
                            var1_1 = this.parser.getEventType();
                            var4_2 = "";
lbl5: // 2 sources:
                            this.connection.setReadAlive();
                            if (var1_1 != 2) break block22;
                            var5_6 = this.parser.getName();
                            if (this.parser.getName().equals((Object)"message")) {
                                this.processPacket(PacketParserUtils.parseMessage(this.parser));
                                var3_4 = var5_6;
                                break block23;
                            }
                            if (!this.parser.getName().equals((Object)"iq")) break block24;
                            this.processPacket(PacketParserUtils.parseIQ(this.parser, this.connection));
                            var3_4 = var5_6;
                            break block23;
                        }
                        catch (Exception var3_5) {
                            MyLog.e(var3_5);
                            if (this.done) break block25;
                            this.notifyConnectionError(9, var3_5);
                            return;
                        }
                    }
                    if (!this.parser.getName().equals((Object)"presence")) ** GOTO lbl26
                    this.processPacket(PacketParserUtils.parsePresence(this.parser));
                    var3_4 = var5_6;
                    ** GOTO lbl52
lbl26: // 1 sources:
                    if (this.parser.getName().equals((Object)"stream")) ** GOTO lbl61
                    if (this.parser.getName().equals((Object)"error")) {
                        throw new XMPPException(PacketParserUtils.parseStreamError(this.parser));
                    }
                    if (this.parser.getName().equals((Object)"warning")) {
                        this.parser.next();
                        var3_4 = var5_6;
                        if (this.parser.getName().equals((Object)"multi-login")) {
                            this.notifyConnectionError(6, null);
                            var3_4 = var5_6;
                        }
                    } else {
                        var3_4 = var5_6;
                        if (this.parser.getName().equals((Object)"bind")) {
                            this.processPacket(PacketParserUtils.parseBindResult(this.parser));
                            var3_4 = var5_6;
                        }
                    }
                    ** GOTO lbl52
                }
                var3_4 = var4_2;
                if (var1_1 == 3) {
                    var3_4 = var4_2;
                    if (this.parser.getName().equals((Object)"stream")) {
                        this.notifyConnectionError(13, null);
                        var3_4 = var4_2;
                    }
                }
                ** GOTO lbl52
            }
            MyLog.v("reader is shutdown, ignore the exception.");
            return;
        }
lbl53: // 2 sources:
        do {
            var2_3 = this.parser.next();
            if (this.done) ** GOTO lbl59
            var1_1 = var2_3;
            var4_2 = var3_4;
            if (var2_3 != 1) ** GOTO lbl5
lbl59: // 2 sources:
            if (var2_3 != 1) return;
            {
                throw new Exception("SMACK: server close the connection or timeout happened, last element name=" + var3_4 + " host=" + this.connection.getHost());
            }
            break;
        } while (true);
lbl61: // 1 sources:
        var3_4 = "";
        var1_1 = 0;
        do {
            if (var1_1 < this.parser.getAttributeCount()) {
                if (this.parser.getAttributeName(var1_1).equals((Object)"from")) {
                    this.connection.config.setServiceName(this.parser.getAttributeValue(var1_1));
                    var4_2 = var3_4;
                } else if (this.parser.getAttributeName(var1_1).equals((Object)"challenge")) {
                    var4_2 = this.parser.getAttributeValue(var1_1);
                } else {
                    var4_2 = var3_4;
                    if ("ps".equals((Object)this.parser.getAttributeName(var1_1))) {
                        var4_2 = this.parser.getAttributeValue(var1_1);
                        var6_7 = new IQ();
                        var6_7.setChannelId("0");
                        var6_7.setPacketID("0");
                        var6_7.setAttribute("ps", var4_2);
                        var6_7.setType(IQ.Type.SET);
                        this.processPacket(var6_7);
                        var4_2 = var3_4;
                    }
                }
            } else {
                this.connection.setChallenge(var3_4);
                var3_4 = var5_6;
                ** continue;
            }
            ++var1_1;
            var3_4 = var4_2;
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void processPacket(Packet packet) {
        if (packet != null) {
            Iterator iterator = this.connection.recvListeners.values().iterator();
            while (iterator.hasNext()) {
                ((Connection.ListenerWrapper)iterator.next()).notifyListener(packet);
            }
        }
    }

    private void setParser() throws XmlPullParserException {
        this.parser = XmlPullParserFactory.newInstance().newPullParser();
        this.parser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
        this.parser.setInput(this.connection.reader);
    }

    void cleanup() {
        this.connection.recvListeners.clear();
    }

    protected void init() {
        this.done = false;
        this.readerThread = new Thread("Smack Packet Reader (" + this.connection.connectionCounterValue + ")"){

            public void run() {
                PacketReader.this.parsePackets();
            }
        };
    }

    void notifyConnectionError(int n, Exception exception) {
        this.done = true;
        this.connection.notifyConnectionError(n, exception);
    }

    public void shutdown() {
        this.done = true;
    }

    public void startup() throws XMPPException {
        this.readerThread.start();
    }

}

