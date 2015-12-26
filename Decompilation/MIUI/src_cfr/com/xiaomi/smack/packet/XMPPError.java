/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Bundle
 *  android.os.Parcelable
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.Collections
 */
package com.xiaomi.smack.packet;

import android.os.Bundle;
import android.os.Parcelable;
import com.xiaomi.smack.packet.CommonPacketExtension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class XMPPError {
    private List<CommonPacketExtension> applicationExtensions = null;
    private int code;
    private String condition;
    private String message;
    private String reason;
    private String type;

    public XMPPError(int n, String string2, String string3, String string4, String string5, List<CommonPacketExtension> list) {
        this.code = n;
        this.type = string2;
        this.reason = string3;
        this.condition = string4;
        this.message = string5;
        this.applicationExtensions = list;
    }

    public XMPPError(Bundle arrparcelable) {
        this.code = arrparcelable.getInt("ext_err_code");
        if (arrparcelable.containsKey("ext_err_type")) {
            this.type = arrparcelable.getString("ext_err_type");
        }
        this.condition = arrparcelable.getString("ext_err_cond");
        this.reason = arrparcelable.getString("ext_err_reason");
        this.message = arrparcelable.getString("ext_err_msg");
        if ((arrparcelable = arrparcelable.getParcelableArray("ext_exts")) != null) {
            this.applicationExtensions = new ArrayList(arrparcelable.length);
            int n = arrparcelable.length;
            for (int i = 0; i < n; ++i) {
                CommonPacketExtension commonPacketExtension = CommonPacketExtension.parseFromBundle((Bundle)arrparcelable[i]);
                if (commonPacketExtension == null) continue;
                this.applicationExtensions.add(commonPacketExtension);
            }
        }
    }

    public XMPPError(Condition condition) {
        this.init(condition);
        this.message = null;
    }

    private void init(Condition condition) {
        this.condition = condition.value;
    }

    public List<CommonPacketExtension> getExtensions() {
        synchronized (this) {
            List list;
            if (this.applicationExtensions == null) {
                list = Collections.emptyList();
                return list;
            }
            list = Collections.unmodifiableList(this.applicationExtensions);
        }
    }

    public String getReason() {
        return this.reason;
    }

    public String getType() {
        return this.type;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        if (this.type != null) {
            bundle.putString("ext_err_type", this.type);
        }
        bundle.putInt("ext_err_code", this.code);
        if (this.reason != null) {
            bundle.putString("ext_err_reason", this.reason);
        }
        if (this.condition != null) {
            bundle.putString("ext_err_cond", this.condition);
        }
        if (this.message != null) {
            bundle.putString("ext_err_msg", this.message);
        }
        if (this.applicationExtensions != null) {
            Bundle[] arrbundle = new Bundle[this.applicationExtensions.size()];
            int n = 0;
            Iterator<CommonPacketExtension> iterator = this.applicationExtensions.iterator();
            while (iterator.hasNext()) {
                Bundle bundle2 = iterator.next().toBundle();
                if (bundle2 == null) continue;
                arrbundle[n] = bundle2;
                ++n;
            }
            bundle.putParcelableArray("ext_exts", (Parcelable[])arrbundle);
        }
        return bundle;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.condition != null) {
            stringBuilder.append(this.condition);
        }
        stringBuilder.append("(").append(this.code).append(")");
        if (this.message != null) {
            stringBuilder.append(" ").append(this.message);
        }
        return stringBuilder.toString();
    }

    public String toXML() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<error code=\"").append(this.code).append("\"");
        if (this.type != null) {
            stringBuilder.append(" type=\"");
            stringBuilder.append(this.type);
            stringBuilder.append("\"");
        }
        if (this.reason != null) {
            stringBuilder.append(" reason=\"");
            stringBuilder.append(this.reason);
            stringBuilder.append("\"");
        }
        stringBuilder.append(">");
        if (this.condition != null) {
            stringBuilder.append("<").append(this.condition);
            stringBuilder.append(" xmlns=\"urn:ietf:params:xml:ns:xmpp-stanzas\"/>");
        }
        if (this.message != null) {
            stringBuilder.append("<text xml:lang=\"en\" xmlns=\"urn:ietf:params:xml:ns:xmpp-stanzas\">");
            stringBuilder.append(this.message);
            stringBuilder.append("</text>");
        }
        Iterator<CommonPacketExtension> iterator = this.getExtensions().iterator();
        while (iterator.hasNext()) {
            stringBuilder.append(iterator.next().toXML());
        }
        stringBuilder.append("</error>");
        return stringBuilder.toString();
    }

    public static class Condition {
        public static final Condition bad_request;
        public static final Condition conflict;
        public static final Condition feature_not_implemented;
        public static final Condition forbidden;
        public static final Condition gone;
        public static final Condition interna_server_error;
        public static final Condition item_not_found;
        public static final Condition jid_malformed;
        public static final Condition no_acceptable;
        public static final Condition not_allowed;
        public static final Condition not_authorized;
        public static final Condition payment_required;
        public static final Condition recipient_unavailable;
        public static final Condition redirect;
        public static final Condition registration_required;
        public static final Condition remote_server_error;
        public static final Condition remote_server_not_found;
        public static final Condition remote_server_timeout;
        public static final Condition request_timeout;
        public static final Condition resource_constraint;
        public static final Condition service_unavailable;
        public static final Condition subscription_required;
        public static final Condition undefined_condition;
        public static final Condition unexpected_request;
        private String value;

        static {
            interna_server_error = new Condition("internal-server-error");
            forbidden = new Condition("forbidden");
            bad_request = new Condition("bad-request");
            conflict = new Condition("conflict");
            feature_not_implemented = new Condition("feature-not-implemented");
            gone = new Condition("gone");
            item_not_found = new Condition("item-not-found");
            jid_malformed = new Condition("jid-malformed");
            no_acceptable = new Condition("not-acceptable");
            not_allowed = new Condition("not-allowed");
            not_authorized = new Condition("not-authorized");
            payment_required = new Condition("payment-required");
            recipient_unavailable = new Condition("recipient-unavailable");
            redirect = new Condition("redirect");
            registration_required = new Condition("registration-required");
            remote_server_error = new Condition("remote-server-error");
            remote_server_not_found = new Condition("remote-server-not-found");
            remote_server_timeout = new Condition("remote-server-timeout");
            resource_constraint = new Condition("resource-constraint");
            service_unavailable = new Condition("service-unavailable");
            subscription_required = new Condition("subscription-required");
            undefined_condition = new Condition("undefined-condition");
            unexpected_request = new Condition("unexpected-request");
            request_timeout = new Condition("request-timeout");
        }

        public Condition(String string2) {
            this.value = string2;
        }

        public String toString() {
            return this.value;
        }
    }

}

