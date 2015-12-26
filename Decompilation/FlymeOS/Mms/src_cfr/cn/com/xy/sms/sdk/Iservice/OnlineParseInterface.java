/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  java.lang.Object
 *  java.lang.String
 */
package cn.com.xy.sms.sdk.Iservice;

import android.content.Context;
import java.util.Map;

public interface OnlineParseInterface {
    public int getActionCode(String var1);

    public String getCorp(String var1);

    public String getReqVersion(String var1);

    public String getSceneVersion(String var1);

    public int getSmsTypeByMap(Map<String, Object> var1, int var2);

    public boolean isAppChannel(String var1);

    public boolean isEnterpriseSms(Context var1, String var2, String var3, Map<String, String> var4);

    public int isServiceChoose(String var1, String var2);

    public Map<String, Object> parseMessage(String var1, String var2, Map<String, String> var3);

    public String[] parseMsgToNewContacts(String var1, String var2, String var3, String[] var4);
}

