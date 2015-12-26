/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
package com.xiaomi.stats;

import com.xiaomi.push.thrift.ChannelStatsType;
import com.xiaomi.smack.ConnectionHelper;
import com.xiaomi.smack.XMPPException;
import java.net.UnknownHostException;

final class StatsAnalyser {
    StatsAnalyser() {
    }

    private static void checkNull(Exception exception) {
        if (exception == null) {
            throw new NullPointerException();
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    static TypeWraper fromBind(Exception object) {
        Object object2;
        StatsAnalyser.checkNull((Exception)((Object)object));
        Object object3 = object2 = object;
        if (object instanceof XMPPException) {
            object3 = object2;
            if (((XMPPException)((Object)object)).getWrappedThrowable() != null) {
                object3 = ((XMPPException)((Object)object)).getWrappedThrowable();
            }
        }
        object2 = new TypeWraper();
        object = object3.getMessage();
        if (object3.getCause() != null) {
            object = object3.getCause().getMessage();
        }
        int n = ConnectionHelper.asErrorCode((Throwable)object3);
        object3 = object3.getClass().getSimpleName() + ":" + (String)object;
        switch (n) {
            default: {
                object2.type = ChannelStatsType.BIND_XMPP_ERR;
                break;
            }
            case 105: {
                object2.type = ChannelStatsType.BIND_TCP_READ_TIMEOUT;
                break;
            }
            case 109: {
                object2.type = ChannelStatsType.BIND_TCP_CONNRESET;
                break;
            }
            case 110: {
                object2.type = ChannelStatsType.BIND_TCP_BROKEN_PIPE;
                break;
            }
            case 199: {
                object2.type = ChannelStatsType.BIND_TCP_ERR;
                break;
            }
            case 499: {
                object2.type = ChannelStatsType.BIND_BOSH_ERR;
                if (!object.startsWith("Terminal binding condition encountered: item-not-found")) break;
                object2.type = ChannelStatsType.BIND_BOSH_ITEM_NOT_FOUND;
            }
        }
        if (object2.type == ChannelStatsType.BIND_TCP_ERR || object2.type == ChannelStatsType.BIND_XMPP_ERR || object2.type == ChannelStatsType.BIND_BOSH_ERR) {
            object2.annotation = object3;
        }
        return object2;
    }

    /*
     * Enabled aggressive block sorting
     */
    static TypeWraper fromConnectionException(Exception object) {
        Object object2;
        StatsAnalyser.checkNull((Exception)((Object)object));
        Object object3 = object2 = object;
        if (object instanceof XMPPException) {
            object3 = object2;
            if (((XMPPException)((Object)object)).getWrappedThrowable() != null) {
                object3 = ((XMPPException)((Object)object)).getWrappedThrowable();
            }
        }
        object2 = new TypeWraper();
        object = object3.getMessage();
        if (object3.getCause() != null) {
            object = object3.getCause().getMessage();
        }
        int n = ConnectionHelper.asErrorCode((Throwable)object3);
        object = object3.getClass().getSimpleName() + ":" + (String)object;
        if (n != 0) {
            object2.type = ChannelStatsType.findByValue(ChannelStatsType.CONN_SUCCESS.getValue() + n);
            if (object2.type == ChannelStatsType.CONN_BOSH_ERR && (object3 = object3.getCause()) != null && object3 instanceof UnknownHostException) {
                object2.type = ChannelStatsType.CONN_BOSH_UNKNOWNHOST;
            }
        } else {
            object2.type = ChannelStatsType.CONN_XMPP_ERR;
        }
        if (object2.type == ChannelStatsType.CONN_TCP_ERR_OTHER || object2.type == ChannelStatsType.CONN_XMPP_ERR || object2.type == ChannelStatsType.CONN_BOSH_ERR) {
            object2.annotation = object;
        }
        return object2;
    }

    /*
     * Enabled aggressive block sorting
     */
    static TypeWraper fromDisconnectEx(Exception object) {
        Object object2;
        StatsAnalyser.checkNull((Exception)((Object)object));
        Object object3 = object2 = object;
        if (object instanceof XMPPException) {
            object3 = object2;
            if (((XMPPException)((Object)object)).getWrappedThrowable() != null) {
                object3 = ((XMPPException)((Object)object)).getWrappedThrowable();
            }
        }
        object = new TypeWraper();
        object2 = object3.getMessage();
        int n = ConnectionHelper.asErrorCode((Throwable)object3);
        object3 = object3.getClass().getSimpleName() + ":" + (String)object2;
        switch (n) {
            default: {
                object.type = ChannelStatsType.CHANNEL_XMPPEXCEPTION;
                break;
            }
            case 105: {
                object.type = ChannelStatsType.CHANNEL_TCP_READTIMEOUT;
                break;
            }
            case 109: {
                object.type = ChannelStatsType.CHANNEL_TCP_CONNRESET;
                break;
            }
            case 110: {
                object.type = ChannelStatsType.CHANNEL_TCP_BROKEN_PIPE;
                break;
            }
            case 199: {
                object.type = ChannelStatsType.CHANNEL_TCP_ERR;
                break;
            }
            case 499: {
                object.type = ChannelStatsType.CHANNEL_BOSH_EXCEPTION;
                if (!object2.startsWith("Terminal binding condition encountered: item-not-found")) break;
                object.type = ChannelStatsType.CHANNEL_BOSH_ITEMNOTFIND;
            }
        }
        if (object.type == ChannelStatsType.CHANNEL_TCP_ERR || object.type == ChannelStatsType.CHANNEL_XMPPEXCEPTION || object.type == ChannelStatsType.CHANNEL_BOSH_EXCEPTION) {
            object.annotation = object3;
        }
        return object;
    }

    static TypeWraper fromGslbException(Exception object) {
        Object object2;
        StatsAnalyser.checkNull((Exception)((Object)object));
        Object object3 = object2 = object;
        if (object instanceof XMPPException) {
            object3 = object2;
            if (((XMPPException)((Object)object)).getWrappedThrowable() != null) {
                object3 = ((XMPPException)((Object)object)).getWrappedThrowable();
            }
        }
        object2 = new TypeWraper();
        object = object3.getMessage();
        if (object3.getCause() != null) {
            object = object3.getCause().getMessage();
        }
        object = object3.getClass().getSimpleName() + ":" + (String)object;
        int n = ConnectionHelper.asErrorCode((Throwable)object3);
        if (n != 0) {
            object2.type = ChannelStatsType.findByValue(ChannelStatsType.GSLB_REQUEST_SUCCESS.getValue() + n);
        }
        if (object2.type == null) {
            object2.type = ChannelStatsType.GSLB_TCP_ERR_OTHER;
        }
        if (object2.type == ChannelStatsType.GSLB_TCP_ERR_OTHER) {
            object2.annotation = object;
        }
        return object2;
    }

    static class TypeWraper {
        String annotation;
        ChannelStatsType type;

        TypeWraper() {
        }
    }

}

