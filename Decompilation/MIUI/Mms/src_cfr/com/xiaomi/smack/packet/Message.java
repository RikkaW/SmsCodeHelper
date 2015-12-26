/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Bundle
 *  android.text.TextUtils
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 */
package com.xiaomi.smack.packet;

import android.os.Bundle;
import android.text.TextUtils;
import com.xiaomi.smack.packet.Packet;
import com.xiaomi.smack.packet.XMPPError;
import com.xiaomi.smack.util.StringUtils;

public class Message
extends Packet {
    private String fseq = "";
    private String language;
    private String mAppId;
    private String mBody;
    private String mBodyEncoding;
    private boolean mEncrypted = false;
    private String mSubject;
    private boolean mTransient = false;
    private String mseq = "";
    private String seq = "";
    private String status = "";
    private String thread = null;
    private String type = null;

    public Message() {
    }

    public Message(Bundle bundle) {
        super(bundle);
        this.type = bundle.getString("ext_msg_type");
        this.language = bundle.getString("ext_msg_lang");
        this.thread = bundle.getString("ext_msg_thread");
        this.mSubject = bundle.getString("ext_msg_sub");
        this.mBody = bundle.getString("ext_msg_body");
        this.mBodyEncoding = bundle.getString("ext_body_encode");
        this.mAppId = bundle.getString("ext_msg_appid");
        this.mTransient = bundle.getBoolean("ext_msg_trans", false);
        this.mEncrypted = bundle.getBoolean("ext_msg_encrypt", false);
        this.seq = bundle.getString("ext_msg_seq");
        this.mseq = bundle.getString("ext_msg_mseq");
        this.fseq = bundle.getString("ext_msg_fseq");
        this.status = bundle.getString("ext_msg_status");
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public boolean equals(Object object) {
        boolean bl = true;
        boolean bl2 = false;
        if (this == object) {
            return true;
        }
        boolean bl3 = bl2;
        if (object == null) return bl3;
        bl3 = bl2;
        if (this.getClass() != object.getClass()) return bl3;
        object = (Message)object;
        bl3 = bl2;
        if (!super.equals(object)) return bl3;
        if (this.mBody != null) {
            bl3 = bl2;
            if (!this.mBody.equals((Object)object.mBody)) return bl3;
        } else if (object.mBody != null) {
            return false;
        }
        if (this.language != null) {
            bl3 = bl2;
            if (!this.language.equals((Object)object.language)) return bl3;
        } else if (object.language != null) {
            return false;
        }
        if (this.mSubject != null) {
            bl3 = bl2;
            if (!this.mSubject.equals((Object)object.mSubject)) return bl3;
        } else if (object.mSubject != null) {
            return false;
        }
        if (this.thread != null) {
            bl3 = bl2;
            if (!this.thread.equals((Object)object.thread)) return bl3;
        } else if (object.thread != null) {
            return false;
        }
        if (this.type != object.type) return false;
        return bl;
    }

    public String getAppId() {
        return this.mAppId;
    }

    public String getBody() {
        return this.mBody;
    }

    public String getFSeq() {
        return this.fseq;
    }

    public String getLanguage() {
        return this.language;
    }

    public String getMSeq() {
        return this.mseq;
    }

    public String getSeq() {
        return this.seq;
    }

    public String getStatus() {
        return this.status;
    }

    public String getType() {
        return this.type;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public int hashCode() {
        int n = 0;
        int n2 = this.type != null ? this.type.hashCode() : 0;
        int n3 = this.mBody != null ? this.mBody.hashCode() : 0;
        int n4 = this.thread != null ? this.thread.hashCode() : 0;
        int n5 = this.language != null ? this.language.hashCode() : 0;
        if (this.mSubject != null) {
            n = this.mSubject.hashCode();
        }
        return (((n2 * 31 + n3) * 31 + n4) * 31 + n5) * 31 + n;
    }

    public void setAppId(String string2) {
        this.mAppId = string2;
    }

    public void setBody(String string2) {
        this.mBody = string2;
    }

    public void setBody(String string2, String string3) {
        this.mBody = string2;
        this.mBodyEncoding = string3;
    }

    public void setEncrypted(boolean bl) {
        this.mEncrypted = bl;
    }

    public void setFSeq(String string2) {
        this.fseq = string2;
    }

    public void setIsTransient(boolean bl) {
        this.mTransient = bl;
    }

    public void setLanguage(String string2) {
        this.language = string2;
    }

    public void setMSeq(String string2) {
        this.mseq = string2;
    }

    public void setSeq(String string2) {
        this.seq = string2;
    }

    public void setStatus(String string2) {
        this.status = string2;
    }

    public void setSubject(String string2) {
        this.mSubject = string2;
    }

    public void setThread(String string2) {
        this.thread = string2;
    }

    public void setType(String string2) {
        this.type = string2;
    }

    @Override
    public Bundle toBundle() {
        Bundle bundle = super.toBundle();
        if (!TextUtils.isEmpty((CharSequence)this.type)) {
            bundle.putString("ext_msg_type", this.type);
        }
        if (this.language != null) {
            bundle.putString("ext_msg_lang", this.language);
        }
        if (this.mSubject != null) {
            bundle.putString("ext_msg_sub", this.mSubject);
        }
        if (this.mBody != null) {
            bundle.putString("ext_msg_body", this.mBody);
        }
        if (!TextUtils.isEmpty((CharSequence)this.mBodyEncoding)) {
            bundle.putString("ext_body_encode", this.mBodyEncoding);
        }
        if (this.thread != null) {
            bundle.putString("ext_msg_thread", this.thread);
        }
        if (this.mAppId != null) {
            bundle.putString("ext_msg_appid", this.mAppId);
        }
        if (this.mTransient) {
            bundle.putBoolean("ext_msg_trans", true);
        }
        if (!TextUtils.isEmpty((CharSequence)this.seq)) {
            bundle.putString("ext_msg_seq", this.seq);
        }
        if (!TextUtils.isEmpty((CharSequence)this.mseq)) {
            bundle.putString("ext_msg_mseq", this.mseq);
        }
        if (!TextUtils.isEmpty((CharSequence)this.fseq)) {
            bundle.putString("ext_msg_fseq", this.fseq);
        }
        if (this.mEncrypted) {
            bundle.putBoolean("ext_msg_encrypt", true);
        }
        if (!TextUtils.isEmpty((CharSequence)this.status)) {
            bundle.putString("ext_msg_status", this.status);
        }
        return bundle;
    }

    @Override
    public String toXML() {
        XMPPError xMPPError;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<message");
        if (this.getXmlns() != null) {
            stringBuilder.append(" xmlns=\"").append(this.getXmlns()).append("\"");
        }
        if (this.language != null) {
            stringBuilder.append(" xml:lang=\"").append(this.getLanguage()).append("\"");
        }
        if (this.getPacketID() != null) {
            stringBuilder.append(" id=\"").append(this.getPacketID()).append("\"");
        }
        if (this.getTo() != null) {
            stringBuilder.append(" to=\"").append(StringUtils.escapeForXML(this.getTo())).append("\"");
        }
        if (!TextUtils.isEmpty((CharSequence)this.getSeq())) {
            stringBuilder.append(" seq=\"").append(this.getSeq()).append("\"");
        }
        if (!TextUtils.isEmpty((CharSequence)this.getMSeq())) {
            stringBuilder.append(" mseq=\"").append(this.getMSeq()).append("\"");
        }
        if (!TextUtils.isEmpty((CharSequence)this.getFSeq())) {
            stringBuilder.append(" fseq=\"").append(this.getFSeq()).append("\"");
        }
        if (!TextUtils.isEmpty((CharSequence)this.getStatus())) {
            stringBuilder.append(" status=\"").append(this.getStatus()).append("\"");
        }
        if (this.getFrom() != null) {
            stringBuilder.append(" from=\"").append(StringUtils.escapeForXML(this.getFrom())).append("\"");
        }
        if (this.getChannelId() != null) {
            stringBuilder.append(" chid=\"").append(StringUtils.escapeForXML(this.getChannelId())).append("\"");
        }
        if (this.mTransient) {
            stringBuilder.append(" transient=\"true\"");
        }
        if (!TextUtils.isEmpty((CharSequence)this.mAppId)) {
            stringBuilder.append(" appid=\"").append(this.getAppId()).append("\"");
        }
        if (!TextUtils.isEmpty((CharSequence)this.type)) {
            stringBuilder.append(" type=\"").append(this.type).append("\"");
        }
        if (this.mEncrypted) {
            stringBuilder.append(" s=\"1\"");
        }
        stringBuilder.append(">");
        if (this.mSubject != null) {
            stringBuilder.append("<subject>").append(StringUtils.escapeForXML(this.mSubject));
            stringBuilder.append("</subject>");
        }
        if (this.mBody != null) {
            stringBuilder.append("<body");
            if (!TextUtils.isEmpty((CharSequence)this.mBodyEncoding)) {
                stringBuilder.append(" encode=\"").append(this.mBodyEncoding).append("\"");
            }
            stringBuilder.append(">").append(StringUtils.escapeForXML(this.mBody)).append("</body>");
        }
        if (this.thread != null) {
            stringBuilder.append("<thread>").append(this.thread).append("</thread>");
        }
        if ("error".equalsIgnoreCase(this.type) && (xMPPError = this.getError()) != null) {
            stringBuilder.append(xMPPError.toXML());
        }
        stringBuilder.append(this.getExtensionsXML());
        stringBuilder.append("</message>");
        return stringBuilder.toString();
    }
}

