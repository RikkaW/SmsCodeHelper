/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.text.TextUtils
 *  android.util.Base64
 *  java.io.Writer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 *  java.util.Locale
 */
package com.xiaomi.smack;

import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.smack.ConnectionConfiguration;
import com.xiaomi.smack.XMPPConnection;
import com.xiaomi.smack.XMPPException;
import com.xiaomi.smack.packet.Packet;
import com.xiaomi.smack.util.StringUtils;
import com.xiaomi.smack.util.SystemUtils;
import com.xiaomi.smack.util.TrafficUtils;
import java.io.IOException;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

class PacketWriter {
    private XMPPConnection connection;
    private Writer writer;

    protected PacketWriter(XMPPConnection xMPPConnection) {
        this.connection = xMPPConnection;
        this.writer = xMPPConnection.writer;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void writePackets(Packet object) throws XMPPException {
        Writer writer = this.writer;
        synchronized (writer) {
            try {
                String string2 = object.toXML();
                this.writer.write(string2 + "\r\n");
                this.writer.flush();
                object = object.getPackageName();
                if (!TextUtils.isEmpty((CharSequence)object)) {
                    TrafficUtils.distributionTraffic(this.connection.mPushService, (String)object, TrafficUtils.getTrafficFlow(string2), false, System.currentTimeMillis());
                }
                return;
            }
            catch (IOException var1_2) {
                throw new XMPPException(var1_2);
            }
        }
    }

    void cleanup() {
        this.connection.sendListeners.clear();
    }

    void openStream() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<stream:stream");
        stringBuilder.append(" xmlns=\"xm\"");
        stringBuilder.append(" xmlns:stream=\"xm\"");
        stringBuilder.append(" to=\"").append(this.connection.getServiceName()).append("\"");
        stringBuilder.append(" version=\"105\"");
        stringBuilder.append(" model=\"").append(StringUtils.escapeForXML(Build.MODEL)).append("\"");
        stringBuilder.append(" os=\"").append(StringUtils.escapeForXML(Build.VERSION.INCREMENTAL)).append("\"");
        String string2 = SystemUtils.getDeviceUUID();
        if (string2 != null) {
            stringBuilder.append(" uid=\"").append(string2).append("\"");
        }
        stringBuilder.append(" sdk=\"").append(7).append("\"");
        stringBuilder.append(" connpt=\"").append(StringUtils.escapeForXML(this.connection.getConnectionPoint())).append("\"");
        stringBuilder.append(" host=\"").append(this.connection.getHost()).append("\"");
        stringBuilder.append(" locale=\"").append(StringUtils.escapeForXML(Locale.getDefault().toString())).append("\"");
        string2 = (String)this.connection.getConfiguration().getConnectionBlob();
        if (string2 != null) {
            stringBuilder.append(" ps=\"").append(Base64.encodeToString((byte[])string2, (int)10)).append("\"");
        }
        stringBuilder.append(">");
        this.writer.write(stringBuilder.toString());
        this.writer.flush();
    }

    public void sendPacket(Packet packet) throws XMPPException {
        this.writePackets(packet);
        this.connection.firePacketSendingListeners(packet);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void sendPingString() throws XMPPException {
        Writer writer = this.writer;
        synchronized (writer) {
            try {
                this.writer.write(this.connection.getPingString() + "\r\n");
                this.writer.flush();
                this.connection.updateLastSent();
                return;
            }
            catch (IOException var2_2) {
                throw new XMPPException(var2_2);
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void shutdown() throws IOException {
        Writer writer = this.writer;
        synchronized (writer) {
            this.writer.write("</stream:stream>");
            this.writer.flush();
            return;
        }
    }
}

