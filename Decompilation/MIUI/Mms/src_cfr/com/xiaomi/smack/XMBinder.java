/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.text.TextUtils
 *  java.lang.Object
 *  java.lang.String
 *  java.util.HashMap
 */
package com.xiaomi.smack;

import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.string.CloudCoder;
import com.xiaomi.push.service.PushClientsManager;
import com.xiaomi.smack.Connection;
import com.xiaomi.smack.XMPPException;
import com.xiaomi.smack.packet.CommonPacketExtension;
import com.xiaomi.smack.packet.Packet;
import com.xiaomi.smack.packet.XMPPError;
import com.xiaomi.smack.util.StringUtils;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class XMBinder {
    public void doBind(PushClientsManager.ClientLoginInfo object, String string2, Connection connection) throws XMPPException {
        object = new Bind((PushClientsManager.ClientLoginInfo)object, string2, connection);
        connection.sendPacket((Packet)object);
        MyLog.warn("SMACK: bind id=" + object.getPacketID());
    }

    public class Bind
    extends Packet {
        /*
         * Enabled aggressive block sorting
         */
        public Bind(PushClientsManager.ClientLoginInfo object2, String object3, Connection object4) {
            Object object5 = new HashMap();
            int n = object4.getConnTryTimes();
            object5.put("challenge", object3);
            object5.put("token", object2.token);
            object5.put("chid", object2.chid);
            object5.put("from", object2.userId);
            object5.put("id", this.getPacketID());
            object5.put("to", "xiaomi.com");
            if (object2.kick) {
                object5.put("kick", "1");
            } else {
                object5.put("kick", "0");
            }
            Object.this = null;
            if (object4.getConnectTime() > 0) {
                Object.this = String.format((String)"conn:%1$d,t:%2$d", (Object[])new Object[]{n, object4.getConnectTime()});
                object5.put("pf", Object.this);
                object4.resetConnTryTimes();
                object4.resetConnectTime();
            }
            if (!TextUtils.isEmpty((CharSequence)object2.clientExtra)) {
                object5.put("client_attrs", object2.clientExtra);
            } else {
                object5.put("client_attrs", "");
            }
            if (!TextUtils.isEmpty((CharSequence)object2.cloudExtra)) {
                object5.put("cloud_attrs", object2.cloudExtra);
            } else {
                object5.put("cloud_attrs", "");
            }
            object4 = null;
            if (object2.authMethod.equals((Object)"XIAOMI-PASS") || object2.authMethod.equals((Object)"XMPUSH-PASS")) {
                object3 = CloudCoder.generateSignature(object2.authMethod, null, object5, object2.security);
            } else {
                object3 = object4;
                if (object2.authMethod.equals((Object)"XIAOMI-SASL")) {
                    object3 = object4;
                }
            }
            this.setChannelId(object2.chid);
            this.setFrom(object2.userId);
            this.setTo("xiaomi.com");
            this.setPackageName(object2.pkgName);
            object4 = new CommonPacketExtension("token", null, (String[])null, (String[])null);
            object4.setText(object2.token);
            this.addExtension((CommonPacketExtension)object4);
            object5 = new CommonPacketExtension("kick", null, (String[])null, (String[])null);
            object4 = object2.kick ? "1" : "0";
            object5.setText((String)object4);
            this.addExtension((CommonPacketExtension)object5);
            object4 = new CommonPacketExtension("sig", null, (String[])null, (String[])null);
            object4.setText((String)object3);
            this.addExtension((CommonPacketExtension)object4);
            object3 = new CommonPacketExtension("method", null, (String[])null, (String[])null);
            if (!TextUtils.isEmpty((CharSequence)object2.authMethod)) {
                object3.setText(object2.authMethod);
            } else {
                object3.setText("XIAOMI-SASL");
            }
            this.addExtension((CommonPacketExtension)object3);
            object4 = new CommonPacketExtension("client_attrs", null, (String[])null, (String[])null);
            object3 = object2.clientExtra == null ? "" : StringUtils.escapeForXML(object2.clientExtra);
            object4.setText((String)object3);
            this.addExtension((CommonPacketExtension)object4);
            object3 = new CommonPacketExtension("cloud_attrs", null, (String[])null, (String[])null);
            object2 = object2.cloudExtra == null ? "" : StringUtils.escapeForXML(object2.cloudExtra);
            object3.setText((String)object2);
            this.addExtension((CommonPacketExtension)object3);
            if (!TextUtils.isEmpty((CharSequence)Object.this)) {
                object2 = new CommonPacketExtension("pf", null, (String[])null, (String[])null);
                object2.setText((String)Object.this);
                this.addExtension((CommonPacketExtension)object2);
            }
        }

        @Override
        public String toXML() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("<bind ");
            if (this.getPacketID() != null) {
                stringBuilder.append("id=\"" + this.getPacketID() + "\" ");
            }
            if (this.getTo() != null) {
                stringBuilder.append("to=\"").append(StringUtils.escapeForXML(this.getTo())).append("\" ");
            }
            if (this.getFrom() != null) {
                stringBuilder.append("from=\"").append(StringUtils.escapeForXML(this.getFrom())).append("\" ");
            }
            if (this.getChannelId() != null) {
                stringBuilder.append("chid=\"").append(StringUtils.escapeForXML(this.getChannelId())).append("\">");
            }
            if (this.getExtensions() != null) {
                Iterator<CommonPacketExtension> iterator = this.getExtensions().iterator();
                while (iterator.hasNext()) {
                    stringBuilder.append(iterator.next().toXML());
                }
            }
            stringBuilder.append("</bind>");
            return stringBuilder.toString();
        }
    }

    public static class BindResult
    extends Packet {
        private Type type;

        public Type getType() {
            return this.type;
        }

        public void setType(Type type) {
            if (type == null) {
                this.type = Type.RESULT;
                return;
            }
            this.type = type;
        }

        /*
         * Enabled aggressive block sorting
         */
        @Override
        public String toXML() {
            Object object;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("<bind ");
            if (this.getPacketID() != null) {
                stringBuilder.append("id=\"" + this.getPacketID() + "\" ");
            }
            if (this.getTo() != null) {
                stringBuilder.append("to=\"").append(StringUtils.escapeForXML(this.getTo())).append("\" ");
            }
            if (this.getFrom() != null) {
                stringBuilder.append("from=\"").append(StringUtils.escapeForXML(this.getFrom())).append("\" ");
            }
            if (this.getChannelId() != null) {
                stringBuilder.append(" chid=\"").append(StringUtils.escapeForXML(this.getChannelId())).append("\" ");
            }
            if (this.type == null) {
                stringBuilder.append("type=\"result\">");
            } else {
                stringBuilder.append("type=\"").append(this.getType()).append("\">");
            }
            if (this.getExtensions() != null) {
                object = this.getExtensions().iterator();
                while (object.hasNext()) {
                    stringBuilder.append(((CommonPacketExtension)object.next()).toXML());
                }
            }
            if ((object = this.getError()) != null) {
                stringBuilder.append(object.toXML());
            }
            stringBuilder.append("</bind>");
            return stringBuilder.toString();
        }

        public static class Type {
            public static final Type ERROR;
            public static final Type RESULT;
            private String value;

            static {
                RESULT = new Type("result");
                ERROR = new Type("error");
            }

            private Type(String string2) {
                this.value = string2;
            }

            /*
             * Enabled aggressive block sorting
             * Lifted jumps to return sites
             */
            public static Type fromString(String string2) {
                if (string2 == null) {
                    return null;
                }
                string2 = string2.toLowerCase();
                if (ERROR.toString().equals((Object)string2)) {
                    return ERROR;
                }
                if (!RESULT.toString().equals((Object)string2)) return null;
                return RESULT;
            }

            public String toString() {
                return this.value;
            }
        }

    }

}

