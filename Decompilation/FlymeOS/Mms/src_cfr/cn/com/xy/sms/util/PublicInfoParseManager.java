/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.drawable.BitmapDrawable
 *  java.io.File
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 *  java.util.HashMap
 *  java.util.concurrent.Executors
 *  org.json.JSONObject
 */
package cn.com.xy.sms.util;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.entity.a.c;
import cn.com.xy.sms.sdk.dex.DexUtil;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.net.NetUtil;
import cn.com.xy.sms.sdk.net.util.l;
import cn.com.xy.sms.sdk.queue.g;
import cn.com.xy.sms.sdk.queue.i;
import cn.com.xy.sms.sdk.ui.popu.util.ViewUtil;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.XyUtil;
import cn.com.xy.sms.sdk.util.d;
import cn.com.xy.sms.util.SdkCallBack;
import cn.com.xy.sms.util.s;
import cn.com.xy.sms.util.t;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONObject;

public class PublicInfoParseManager {
    private static HashMap<String, Long> a = new HashMap();
    private static ExecutorService b = Executors.newFixedThreadPool((int)1);
    public static long mins = 1;

    static /* synthetic */ BitmapDrawable a(Context context, String string2, String string3, String string4, String string5, int n2, int n3, SdkCallBack sdkCallBack) {
        return PublicInfoParseManager.b(context, string2, string3, string4, string5, n2, n3, sdkCallBack);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static BitmapDrawable b(Context object, String string2, String string3, String string4, String string5, int n2, int n3, SdkCallBack sdkCallBack) {
        Object var9_10;
        String string6;
        String string7;
        block11 : {
            block12 : {
                block10 : {
                    string7 = null;
                    var9_10 = null;
                    string6 = string7;
                    try {
                        if (NetUtil.checkAccessNetWork(2)) break block10;
                        object = var9_10;
                        if (sdkCallBack == null) return object;
                        string6 = string7;
                    }
                    catch (Throwable var0_1) {
                        return string6;
                    }
                    sdkCallBack.execute(null);
                    return null;
                }
                string6 = string7;
                a.put((Object)string5, (Object)System.currentTimeMillis());
                string6 = string7;
                if (d.g(string2, string3, string4) != 0) break block11;
                string6 = string7;
                a.remove((Object)string5);
                string6 = string7;
                string2 = ViewUtil.createBitmapByPath2((Context)object, string5, n2, n3);
                if (sdkCallBack == null) break block12;
                string6 = string2;
                sdkCallBack.execute(new Object[]{string2});
            }
            object = string2;
            if (string2 == null) return object;
            string6 = string2;
            a.remove((Object)string5);
            return string2;
        }
        object = var9_10;
        if (sdkCallBack == null) return object;
        string6 = string7;
        sdkCallBack.execute(null);
        return var9_10;
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static BitmapDrawable findBitmapLogoByLogoName(Context context, String string2, int n2, int n3, Map<String, String> object, SdkCallBack sdkCallBack) {
        try {
            String string3;
            Object object2;
            block14 : {
                block15 : {
                    try {
                        string3 = Constant.getPath("duoqu_publiclogo");
                        object = String.valueOf((Object)string3) + string2;
                        object2 = new File((String)object);
                        if (!object2.exists()) break block14;
                        new StringBuilder("logoPath=").append((String)object).append("\u6587\u4ef6\u5b58\u5728\uff0cdecode");
                        context = ViewUtil.createBitmapByPath2(context, (File)object2, n2, n3);
                        if (context == null) break block15;
                    }
                    catch (Throwable var0_1) {
                        context = null;
                        return context;
                    }
                    XyUtil.doXycallBackResult(sdkCallBack, new Object[]{context});
                    string2 = (Long)a.get((Object)"runResourseQueue");
                    if (string2 != null) {
                        if (System.currentTimeMillis() <= string2.longValue() + DexUtil.getUpdateCycleByType(20, 3600000)) return context;
                    }
                    g.a(new i(7, new String[0]));
                    a.put((Object)"runResourseQueue", (Object)System.currentTimeMillis());
                    return context;
                }
                a.put(object, (Object)System.currentTimeMillis());
                XyUtil.doXycallBackResult(sdkCallBack, null);
                context = (Long)a.get((Object)"runResourseQueue");
                if (context != null) {
                    if (System.currentTimeMillis() <= context.longValue() + DexUtil.getUpdateCycleByType(20, 3600000)) return null;
                }
                g.a(new i(7, new String[0]));
                a.put((Object)"runResourseQueue", (Object)System.currentTimeMillis());
                return null;
            }
            object2 = (Long)a.get(object);
            if (object2 != null && System.currentTimeMillis() < object2.longValue() + DexUtil.getUpdateCycleByType(19, 60 * mins * 1000)) {
                new StringBuilder("logoPath=").append((String)object).append(" \u8fd8\u6ca1\u5230\u65f6\u95f4");
                XyUtil.doXycallBackResult(sdkCallBack, null);
                return null;
            }
            object2 = "http://down2.bizport.cn/publicnum/upload/" + string2;
            b.execute((Runnable)new s(context, (String)object2, string3, string2, (String)object, n2, n3, sdkCallBack));
            return null;
        }
        catch (Throwable var0_2) {
            throw var0_2;
        }
        finally {
            context = (Long)a.get((Object)"runResourseQueue");
            if (context == null || System.currentTimeMillis() > context.longValue() + DexUtil.getUpdateCycleByType(20, 3600000)) {
                g.a(new i(7, new String[0]));
                a.put((Object)"runResourseQueue", (Object)System.currentTimeMillis());
            }
        }
        catch (Throwable throwable) {
            return context;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String getLogoNameByNum(Context object, String string2, int n2, int n3, String string3, Map<String, String> map, SdkCallBack sdkCallBack) {
        block4 : {
            if (l.a(5)) break block4;
            return null;
        }
        object = bx.a(string2, n2, string3, map, (XyCallBack)new t(n3, sdkCallBack));
        if (StringUtils.isNull((String)object)) return "";
        object = new JSONObject((String)object);
        if (n3 != 1) return object.optString("logoc");
        try {
            return object.optString("logo");
        }
        catch (Throwable var0_1) {
            LogManager.e("XIAOYUAN", "findBitmapLogoByLogoName" + var0_1.getLocalizedMessage(), var0_1);
        }
        return "";
    }

    public static String queryLocalSmsSignByNum(String object) {
        block3 : {
            try {
                object = c.a((String)object);
                boolean bl2 = StringUtils.isNull((String)object);
                if (!bl2) break block3;
                return "";
            }
            catch (Throwable var0_1) {
                var0_1.printStackTrace();
                return "";
            }
        }
        object = object.split(";");
        object = object[object.length - 1];
        return object;
    }
}

