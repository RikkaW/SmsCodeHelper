/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
package com.xiaomi.mipush.sdk;

import com.xiaomi.mipush.sdk.PushMessageHandler;
import java.util.List;

public class MiPushCommandMessage
implements PushMessageHandler.PushMessageInterface {
    private static final long serialVersionUID = 1;
    private String category;
    private String command;
    private List<String> commandArguments;
    private String reason;
    private long resultCode;

    public String getCategory() {
        return this.category;
    }

    public String getCommand() {
        return this.command;
    }

    public List<String> getCommandArguments() {
        return this.commandArguments;
    }

    public String getReason() {
        return this.reason;
    }

    public long getResultCode() {
        return this.resultCode;
    }

    public void setCategory(String string2) {
        this.category = string2;
    }

    public void setCommand(String string2) {
        this.command = string2;
    }

    public void setCommandArguments(List<String> list) {
        this.commandArguments = list;
    }

    public void setReason(String string2) {
        this.reason = string2;
    }

    public void setResultCode(long l) {
        this.resultCode = l;
    }

    public String toString() {
        return "command={" + this.command + "}, resultCode={" + this.resultCode + "}, reason={" + this.reason + "}, category={" + this.category + "}, commandArguments={" + this.commandArguments + "}";
    }
}

