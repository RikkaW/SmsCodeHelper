/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Bundle
 *  java.lang.Class
 *  java.lang.Enum
 *  java.lang.Object
 *  java.lang.String
 */
package com.xiaomi.smack.packet;

import android.os.Bundle;
import com.xiaomi.smack.packet.Packet;
import com.xiaomi.smack.packet.XMPPError;
import com.xiaomi.smack.util.StringUtils;

public class Presence
extends Packet {
    private Mode mode = null;
    private int priority = Integer.MIN_VALUE;
    private String status = null;
    private Type type = Type.available;

    public Presence(Bundle bundle) {
        super(bundle);
        if (bundle.containsKey("ext_pres_type")) {
            this.type = Type.valueOf(bundle.getString("ext_pres_type"));
        }
        if (bundle.containsKey("ext_pres_status")) {
            this.status = bundle.getString("ext_pres_status");
        }
        if (bundle.containsKey("ext_pres_prio")) {
            this.priority = bundle.getInt("ext_pres_prio");
        }
        if (bundle.containsKey("ext_pres_mode")) {
            this.mode = Mode.valueOf(bundle.getString("ext_pres_mode"));
        }
    }

    public Presence(Type type) {
        this.setType(type);
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public void setPriority(int n) {
        if (n < -128 || n > 128) {
            throw new IllegalArgumentException("Priority value " + n + " is not valid. Valid range is -128 through 128.");
        }
        this.priority = n;
    }

    public void setStatus(String string2) {
        this.status = string2;
    }

    public void setType(Type type) {
        if (type == null) {
            throw new NullPointerException("Type cannot be null");
        }
        this.type = type;
    }

    @Override
    public Bundle toBundle() {
        Bundle bundle = super.toBundle();
        if (this.type != null) {
            bundle.putString("ext_pres_type", this.type.toString());
        }
        if (this.status != null) {
            bundle.putString("ext_pres_status", this.status);
        }
        if (this.priority != Integer.MIN_VALUE) {
            bundle.putInt("ext_pres_prio", this.priority);
        }
        if (this.mode != null && this.mode != Mode.available) {
            bundle.putString("ext_pres_mode", this.mode.toString());
        }
        return bundle;
    }

    @Override
    public String toXML() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<presence");
        if (this.getXmlns() != null) {
            stringBuilder.append(" xmlns=\"").append(this.getXmlns()).append("\"");
        }
        if (this.getPacketID() != null) {
            stringBuilder.append(" id=\"").append(this.getPacketID()).append("\"");
        }
        if (this.getTo() != null) {
            stringBuilder.append(" to=\"").append(StringUtils.escapeForXML(this.getTo())).append("\"");
        }
        if (this.getFrom() != null) {
            stringBuilder.append(" from=\"").append(StringUtils.escapeForXML(this.getFrom())).append("\"");
        }
        if (this.getChannelId() != null) {
            stringBuilder.append(" chid=\"").append(StringUtils.escapeForXML(this.getChannelId())).append("\"");
        }
        if (this.type != null) {
            stringBuilder.append(" type=\"").append((Object)this.type).append("\"");
        }
        stringBuilder.append(">");
        if (this.status != null) {
            stringBuilder.append("<status>").append(StringUtils.escapeForXML(this.status)).append("</status>");
        }
        if (this.priority != Integer.MIN_VALUE) {
            stringBuilder.append("<priority>").append(this.priority).append("</priority>");
        }
        if (this.mode != null && this.mode != Mode.available) {
            stringBuilder.append("<show>").append((Object)this.mode).append("</show>");
        }
        stringBuilder.append(this.getExtensionsXML());
        XMPPError xMPPError = this.getError();
        if (xMPPError != null) {
            stringBuilder.append(xMPPError.toXML());
        }
        stringBuilder.append("</presence>");
        return stringBuilder.toString();
    }

    public static enum Mode {
        chat,
        available,
        away,
        xa,
        dnd;
        

        private Mode() {
        }
    }

    public static enum Type {
        available,
        unavailable,
        subscribe,
        subscribed,
        unsubscribe,
        unsubscribed,
        error,
        probe;
        

        private Type() {
        }
    }

}

