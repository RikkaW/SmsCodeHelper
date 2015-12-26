/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Class
 *  java.lang.Enum
 *  java.lang.Object
 *  java.lang.String
 */
package com.xiaomi.xmpush.thrift;

public enum ActionType {
    Registration(1),
    UnRegistration(2),
    Subscription(3),
    UnSubscription(4),
    SendMessage(5),
    AckMessage(6),
    SetConfig(7),
    ReportFeedback(8),
    Notification(9),
    Command(10),
    MultiConnectionBroadcast(11),
    MultiConnectionResult(12),
    ConnectionKick(13);
    
    private final int value;

    private ActionType(int n2) {
        this.value = n2;
    }

    public static ActionType findByValue(int n) {
        switch (n) {
            default: {
                return null;
            }
            case 1: {
                return Registration;
            }
            case 2: {
                return UnRegistration;
            }
            case 3: {
                return Subscription;
            }
            case 4: {
                return UnSubscription;
            }
            case 5: {
                return SendMessage;
            }
            case 6: {
                return AckMessage;
            }
            case 7: {
                return SetConfig;
            }
            case 8: {
                return ReportFeedback;
            }
            case 9: {
                return Notification;
            }
            case 10: {
                return Command;
            }
            case 11: {
                return MultiConnectionBroadcast;
            }
            case 12: {
                return MultiConnectionResult;
            }
            case 13: 
        }
        return ConnectionKick;
    }

    public int getValue() {
        return this.value;
    }
}

