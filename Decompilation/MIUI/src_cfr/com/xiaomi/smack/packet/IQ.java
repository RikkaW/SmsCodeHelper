/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Bundle
 *  java.lang.Object
 *  java.lang.String
 *  java.util.HashMap
 *  java.util.Map$Entry
 */
package com.xiaomi.smack.packet;

import android.os.Bundle;
import com.xiaomi.smack.packet.Packet;
import com.xiaomi.smack.packet.XMPPError;
import com.xiaomi.smack.util.StringUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class IQ
extends Packet {
    private final Map<String, String> attributes = new HashMap();
    private Type type = Type.GET;

    public IQ() {
    }

    public IQ(Bundle bundle) {
        super(bundle);
        if (bundle.containsKey("ext_iq_type")) {
            this.type = Type.fromString(bundle.getString("ext_iq_type"));
        }
    }

    public String getAttribute(String string2) {
        synchronized (this) {
            string2 = this.attributes.get(string2);
            return string2;
        }
    }

    public String getChildElementXML() {
        return null;
    }

    public Type getType() {
        return this.type;
    }

    public void setAttribute(String string2, String string3) {
        synchronized (this) {
            this.attributes.put(string2, string3);
            return;
        }
    }

    public void setAttributes(Map<String, String> map) {
        synchronized (this) {
            this.attributes.putAll(map);
            return;
        }
    }

    public void setType(Type type) {
        if (type == null) {
            this.type = Type.GET;
            return;
        }
        this.type = type;
    }

    @Override
    public Bundle toBundle() {
        Bundle bundle = super.toBundle();
        if (this.type != null) {
            bundle.putString("ext_iq_type", this.type.toString());
        }
        return bundle;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public String toXML() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<iq ");
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
            stringBuilder.append("chid=\"").append(StringUtils.escapeForXML(this.getChannelId())).append("\" ");
        }
        Object object = this.attributes.entrySet().iterator();
        while (object.hasNext()) {
            Map.Entry<String, String> entry = object.next();
            stringBuilder.append(StringUtils.escapeForXML((String)entry.getKey())).append("=\"");
            stringBuilder.append(StringUtils.escapeForXML((String)entry.getValue())).append("\" ");
        }
        if (this.type == null) {
            stringBuilder.append("type=\"get\">");
        } else {
            stringBuilder.append("type=\"").append(this.getType()).append("\">");
        }
        if ((object = this.getChildElementXML()) != null) {
            stringBuilder.append((String)object);
        }
        stringBuilder.append(this.getExtensionsXML());
        object = this.getError();
        if (object != null) {
            stringBuilder.append(object.toXML());
        }
        stringBuilder.append("</iq>");
        return stringBuilder.toString();
    }

    public static class Type {
        public static final Type COMMAND;
        public static final Type ERROR;
        public static final Type GET;
        public static final Type RESULT;
        public static final Type SET;
        private String value;

        static {
            GET = new Type("get");
            SET = new Type("set");
            RESULT = new Type("result");
            ERROR = new Type("error");
            COMMAND = new Type("command");
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
            if (GET.toString().equals((Object)string2)) {
                return GET;
            }
            if (SET.toString().equals((Object)string2)) {
                return SET;
            }
            if (ERROR.toString().equals((Object)string2)) {
                return ERROR;
            }
            if (RESULT.toString().equals((Object)string2)) {
                return RESULT;
            }
            if (!COMMAND.toString().equals((Object)string2)) return null;
            return COMMAND;
        }

        public String toString() {
            return this.value;
        }
    }

}

