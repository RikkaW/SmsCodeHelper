/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Class
 *  java.lang.Enum
 *  java.lang.Object
 *  java.lang.String
 */
package com.xiaomi.push.thrift;

public enum ChannelStatsType {
    TCP_CONN_FAIL(1),
    TCP_CONN_TIME(2),
    PING_RTT(3),
    CHANNEL_CON_FAIL(4),
    CHANNEL_CON_OK(5),
    ICMP_PING_FAIL(6),
    ICMP_PING_OK(7),
    GSLB_REQUEST_SUCCESS(10000),
    GSLB_TCP_NOACCESS(10101),
    GSLB_TCP_NETUNREACH(10102),
    GSLB_TCP_CONNREFUSED(10103),
    GSLB_TCP_NOROUTETOHOST(10104),
    GSLB_TCP_TIMEOUT(10105),
    GSLB_TCP_INVALARG(10106),
    GSLB_TCP_UKNOWNHOST(10107),
    GSLB_TCP_ERR_OTHER(10199),
    GSLB_ERR(10999),
    CONN_SUCCESS(20000),
    CONN_TCP_NOACCESS(20101),
    CONN_TCP_NETUNREACH(20102),
    CONN_TCP_CONNREFUSED(20103),
    CONN_TCP_NOROUTETOHOST(20104),
    CONN_TCP_TIMEOUT(20105),
    CONN_TCP_INVALARG(20106),
    CONN_TCP_UKNOWNHOST(20107),
    CONN_TCP_ERR_OTHER(20199),
    CONN_XMPP_ERR(20399),
    CONN_BOSH_UNKNOWNHOST(20407),
    CONN_BOSH_ERR(20499),
    BIND_SUCCESS(30000),
    BIND_TCP_READ_TIMEOUT_DEPRECTED(30101),
    BIND_TCP_CONNRESET_DEPRECTED(30102),
    BIND_TCP_BROKEN_PIPE_DEPRECTED(30103),
    BIND_TCP_READ_TIMEOUT(30108),
    BIND_TCP_CONNRESET(30109),
    BIND_TCP_BROKEN_PIPE(30110),
    BIND_TCP_ERR(30199),
    BIND_XMPP_ERR(30399),
    BIND_BOSH_ITEM_NOT_FOUND(30401),
    BIND_BOSH_ERR(30499),
    BIND_TIMEOUT(30501),
    BIND_INVALID_SIG(30502),
    CHANNEL_TCP_READTIMEOUT_DEPRECTED(40101),
    CHANNEL_TCP_CONNRESET_DEPRECTED(40102),
    CHANNEL_TCP_BROKEN_PIPE_DEPRECTED(40103),
    CHANNEL_TCP_READTIMEOUT(40108),
    CHANNEL_TCP_CONNRESET(40109),
    CHANNEL_TCP_BROKEN_PIPE(40110),
    CHANNEL_TCP_ERR(40199),
    CHANNEL_XMPPEXCEPTION(40399),
    CHANNEL_BOSH_ITEMNOTFIND(40401),
    CHANNEL_BOSH_EXCEPTION(40499),
    CHANNEL_TIMER_DELAYED(50001),
    CHANNEL_STATS_COUNTER(8000);
    
    private final int value;

    private ChannelStatsType(int n2) {
        this.value = n2;
    }

    public static ChannelStatsType findByValue(int n) {
        switch (n) {
            default: {
                return null;
            }
            case 1: {
                return TCP_CONN_FAIL;
            }
            case 2: {
                return TCP_CONN_TIME;
            }
            case 3: {
                return PING_RTT;
            }
            case 4: {
                return CHANNEL_CON_FAIL;
            }
            case 5: {
                return CHANNEL_CON_OK;
            }
            case 6: {
                return ICMP_PING_FAIL;
            }
            case 7: {
                return ICMP_PING_OK;
            }
            case 10000: {
                return GSLB_REQUEST_SUCCESS;
            }
            case 10101: {
                return GSLB_TCP_NOACCESS;
            }
            case 10102: {
                return GSLB_TCP_NETUNREACH;
            }
            case 10103: {
                return GSLB_TCP_CONNREFUSED;
            }
            case 10104: {
                return GSLB_TCP_NOROUTETOHOST;
            }
            case 10105: {
                return GSLB_TCP_TIMEOUT;
            }
            case 10106: {
                return GSLB_TCP_INVALARG;
            }
            case 10107: {
                return GSLB_TCP_UKNOWNHOST;
            }
            case 10199: {
                return GSLB_TCP_ERR_OTHER;
            }
            case 10999: {
                return GSLB_ERR;
            }
            case 20000: {
                return CONN_SUCCESS;
            }
            case 20101: {
                return CONN_TCP_NOACCESS;
            }
            case 20102: {
                return CONN_TCP_NETUNREACH;
            }
            case 20103: {
                return CONN_TCP_CONNREFUSED;
            }
            case 20104: {
                return CONN_TCP_NOROUTETOHOST;
            }
            case 20105: {
                return CONN_TCP_TIMEOUT;
            }
            case 20106: {
                return CONN_TCP_INVALARG;
            }
            case 20107: {
                return CONN_TCP_UKNOWNHOST;
            }
            case 20199: {
                return CONN_TCP_ERR_OTHER;
            }
            case 20399: {
                return CONN_XMPP_ERR;
            }
            case 20407: {
                return CONN_BOSH_UNKNOWNHOST;
            }
            case 20499: {
                return CONN_BOSH_ERR;
            }
            case 30000: {
                return BIND_SUCCESS;
            }
            case 30101: {
                return BIND_TCP_READ_TIMEOUT_DEPRECTED;
            }
            case 30102: {
                return BIND_TCP_CONNRESET_DEPRECTED;
            }
            case 30103: {
                return BIND_TCP_BROKEN_PIPE_DEPRECTED;
            }
            case 30108: {
                return BIND_TCP_READ_TIMEOUT;
            }
            case 30109: {
                return BIND_TCP_CONNRESET;
            }
            case 30110: {
                return BIND_TCP_BROKEN_PIPE;
            }
            case 30199: {
                return BIND_TCP_ERR;
            }
            case 30399: {
                return BIND_XMPP_ERR;
            }
            case 30401: {
                return BIND_BOSH_ITEM_NOT_FOUND;
            }
            case 30499: {
                return BIND_BOSH_ERR;
            }
            case 30501: {
                return BIND_TIMEOUT;
            }
            case 30502: {
                return BIND_INVALID_SIG;
            }
            case 40101: {
                return CHANNEL_TCP_READTIMEOUT_DEPRECTED;
            }
            case 40102: {
                return CHANNEL_TCP_CONNRESET_DEPRECTED;
            }
            case 40103: {
                return CHANNEL_TCP_BROKEN_PIPE_DEPRECTED;
            }
            case 40108: {
                return CHANNEL_TCP_READTIMEOUT;
            }
            case 40109: {
                return CHANNEL_TCP_CONNRESET;
            }
            case 40110: {
                return CHANNEL_TCP_BROKEN_PIPE;
            }
            case 40199: {
                return CHANNEL_TCP_ERR;
            }
            case 40399: {
                return CHANNEL_XMPPEXCEPTION;
            }
            case 40401: {
                return CHANNEL_BOSH_ITEMNOTFIND;
            }
            case 40499: {
                return CHANNEL_BOSH_EXCEPTION;
            }
            case 50001: {
                return CHANNEL_TIMER_DELAYED;
            }
            case 8000: 
        }
        return CHANNEL_STATS_COUNTER;
    }

    public int getValue() {
        return this.value;
    }
}

