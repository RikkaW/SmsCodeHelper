/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.util.HashMap
 */
package com.xiaomi.mipush.sdk;

import com.xiaomi.mipush.sdk.PushMessageHandler;
import java.util.HashMap;
import java.util.Map;

public class MiPushMessage
implements PushMessageHandler.PushMessageInterface {
    private static final long serialVersionUID = 1;
    private String alias;
    private boolean arrived = false;
    private String category;
    private String content;
    private String description;
    private HashMap<String, String> extra = new HashMap();
    private boolean isNotified;
    private String messageId;
    private int messageType;
    private int notifyId;
    private int notifyType;
    private int passThrough;
    private String title;
    private String topic;
    private String userAccount;

    public String getAlias() {
        return this.alias;
    }

    public String getCategory() {
        return this.category;
    }

    public String getContent() {
        return this.content;
    }

    public Map<String, String> getExtra() {
        return this.extra;
    }

    public String getMessageId() {
        return this.messageId;
    }

    public int getPassThrough() {
        return this.passThrough;
    }

    public String getTopic() {
        return this.topic;
    }

    public boolean isArrivedMessage() {
        return this.arrived;
    }

    public boolean isNotified() {
        return this.isNotified;
    }

    public void setAlias(String string2) {
        this.alias = string2;
    }

    public void setArrivedMessage(boolean bl) {
        this.arrived = bl;
    }

    public void setCategory(String string2) {
        this.category = string2;
    }

    public void setContent(String string2) {
        this.content = string2;
    }

    public void setDescription(String string2) {
        this.description = string2;
    }

    public void setExtra(Map<String, String> map) {
        this.extra.clear();
        if (map != null) {
            this.extra.putAll(map);
        }
    }

    public void setMessageId(String string2) {
        this.messageId = string2;
    }

    public void setMessageType(int n) {
        this.messageType = n;
    }

    public void setNotified(boolean bl) {
        this.isNotified = bl;
    }

    public void setNotifyId(int n) {
        this.notifyId = n;
    }

    public void setNotifyType(int n) {
        this.notifyType = n;
    }

    public void setPassThrough(int n) {
        this.passThrough = n;
    }

    public void setTitle(String string2) {
        this.title = string2;
    }

    public void setTopic(String string2) {
        this.topic = string2;
    }

    public void setUserAccount(String string2) {
        this.userAccount = string2;
    }

    public String toString() {
        return "messageId={" + this.messageId + "},passThrough={" + this.passThrough + "},alias={" + this.alias + "},topic={" + this.topic + "},userAccount={" + this.userAccount + "},content={" + this.content + "},description={" + this.description + "},title={" + this.title + "},isNotified={" + this.isNotified + "},notifyId={" + this.notifyId + "},notifyType={" + this.notifyType + "}, category={" + this.category + "}, extra={" + this.extra + "}";
    }
}

