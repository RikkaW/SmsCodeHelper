/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.LruCache
 *  dalvik.system.DexClassLoader
 *  java.io.File
 *  java.lang.Class
 *  java.lang.ClassLoader
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 *  java.util.HashMap
 *  org.json.JSONObject
 */
package cn.com.xy.sms.sdk.dex;

import android.content.Context;
import android.util.LruCache;
import cn.com.xy.sms.sdk.Iservice.CorpSignInterface;
import cn.com.xy.sms.sdk.Iservice.OnlineParseInterface;
import cn.com.xy.sms.sdk.Iservice.OnlineUpdateCycleConfigInterface;
import cn.com.xy.sms.sdk.Iservice.OnlineViewInterface;
import cn.com.xy.sms.sdk.Iservice.ParseBubbleInterface;
import cn.com.xy.sms.sdk.Iservice.ParseCardInterface;
import cn.com.xy.sms.sdk.Iservice.ParseDateInterface;
import cn.com.xy.sms.sdk.Iservice.ParseNotificationInterface;
import cn.com.xy.sms.sdk.Iservice.ParsePayInterface;
import cn.com.xy.sms.sdk.Iservice.ParseRemindInterface;
import cn.com.xy.sms.sdk.Iservice.ParseVerifyCodeInterface;
import cn.com.xy.sms.sdk.Iservice.ParseWatchInterface;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.entity.SysParamEntityManager;
import cn.com.xy.sms.sdk.db.entity.e;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.net.util.l;
import cn.com.xy.sms.sdk.util.PopupUtil;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.XyUtil;
import cn.com.xy.sms.sdk.util.b;
import cn.com.xy.sms.sdk.util.d;
import cn.com.xy.sms.sdk.util.i;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class DexUtil {
    private static ClassLoader a;
    private static OnlineParseInterface b;
    private static OnlineUpdateCycleConfigInterface c;
    private static HashMap<String, ClassLoader> d;
    private static HashMap<String, Class> e;

    static {
        d = new HashMap();
        e = new HashMap();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static OnlineUpdateCycleConfigInterface a() {
        block4 : {
            Class class_;
            try {
                class_ = DexUtil.getClassBymap(null, "cn.com.xy.sms.sdk.Iservice.OnlineUpdateCycleConfig");
                if (class_ == null) break block4;
            }
            catch (Throwable var0_1) {
                LogManager.e("xiaoyuan", "DexUtil OnlineUpdateCycleConfigInterface\uff1a" + var0_1.getMessage(), var0_1);
                return c;
            }
            c = (OnlineUpdateCycleConfigInterface)class_.newInstance();
        }
        do {
            return c;
            break;
        } while (true);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static int getActionCode(String string2) {
        OnlineParseInterface onlineParseInterface;
        try {
            onlineParseInterface = DexUtil.getOnlineParseImpl(false);
            if (onlineParseInterface == null) return PopupUtil.getActionCode(string2);
        }
        catch (Throwable var2_2) {
            var2_2.printStackTrace();
            return PopupUtil.getActionCode(string2);
        }
        return onlineParseInterface.getActionCode(string2);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String getBubbleViewVersion(Map<String, Object> object) {
        Object object2 = DexUtil.getClassBymap(null, "cn.com.xy.sms.sdk.Iservice.ParseUtilBubble");
        if (object2 == null) return "";
        object2 = (ParseBubbleInterface)object2.newInstance();
        if (object2 == null) return "";
        try {
            return object2.getBubbleViewVersion((Map<String, Object>)object);
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
        }
        return "";
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static Class getClassBymap(Map<String, String> classLoader, String string2) {
        String string3;
        block5 : {
            string3 = string2.substring(string2.lastIndexOf(".") + 1);
            classLoader = DexUtil.getClassLoaderBymap(classLoader, string3);
            if (classLoader == null) return null;
            Class class_ = (Class)e.get((Object)(String.valueOf((Object)string3) + "_Class"));
            if (class_ == null) break block5;
            return class_;
        }
        classLoader = classLoader.loadClass(string2);
        if (classLoader == null) return null;
        try {
            e.put((Object)(String.valueOf((Object)string3) + "_Class"), (Object)classLoader);
            return classLoader;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
        }
        return null;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive exception aggregation
     */
    public static ClassLoader getClassLoaderBymap(Map<String, String> var0, String var1_6) {
        block33 : {
            block31 : {
                block32 : {
                    block29 : {
                        block30 : {
                            var2_10 = 0;
                            var4_11 = 600000;
                            var0 = (ClassLoader)DexUtil.d.get((Object)(String.valueOf((Object)var1_6) + "_ClassLoader"));
                            if (var0 == null) break block29;
                            if (var1_6 == null) break block30;
                            if (var1_6.length() <= 7) ** GOTO lbl-1000
                            var6_12 = (Long)Constant.checkJarMap.get((Object)var1_6);
                            if (var6_12 != null) {
                                var2_10 = var6_12;
                            }
                            if (DexUtil.c != null) {
                                var4_11 = DexUtil.getUpdateCycleByType(6, 600000);
                            }
                            if (System.currentTimeMillis() >= var4_11 + var2_10) {
                                e.a(var1_6, "-1", 1);
                                Constant.checkJarMap.put((Object)var1_6, (Object)System.currentTimeMillis());
                                i.a();
                            }
                            ** GOTO lbl22
                        }
lbl-1000: // 1 sources:
                        {
lbl22: // 1 sources:
                        }
                    }
                    var0 = d.d(Constant.getPARSE_PATH(), String.valueOf((Object)var1_6) + "_", ".jar");
                    new StringBuilder("subname=").append(var1_6).append("jarPath=").append((String)var0);
                    var6_13 = new File((String)var0);
                    if (!var6_13.exists() || !l.a((String)var0).booleanValue()) break block31;
                    var7_15 = Constant.getContext().getDir("outdex", 0);
                    var0 = new DexClassLoader(var6_13.getCanonicalPath(), var7_15.getCanonicalPath(), null, DexUtil.getDexClassLoader());
                    XyUtil.chmod("640", String.valueOf((Object)var7_15.getCanonicalPath()) + File.separator + var6_13.getName().substring(0, var6_13.getName().length() - 4) + ".dex");
                    DexUtil.d.put((Object)(String.valueOf((Object)var1_6) + "_ClassLoader"), var0);
                    if (var1_6 == null) break block32;
                    if (var1_6.length() <= 7) break block32;
                    var6_13 = (Long)Constant.checkJarMap.get((Object)var1_6);
                    if (var6_13 != null) {
                        var2_10 = var6_13.longValue();
                    }
                    if (DexUtil.c != null) {
                        var4_11 = DexUtil.getUpdateCycleByType(6, 600000);
                    }
                    if (System.currentTimeMillis() < var4_11 + var2_10) break block32;
                    e.a(var1_6, "-1", 1);
                    Constant.checkJarMap.put((Object)var1_6, (Object)System.currentTimeMillis());
                    i.a();
                }
lbl46: // 2 sources:
                do {
                    return var0;
                    break;
                } while (true);
                catch (Throwable var0_1) {
                    var0_1.printStackTrace();
lbl51: // 5 sources:
                    do {
                        return null;
                        break;
                    } while (true);
                }
            }
            if (var1_6 == null) ** GOTO lbl51
            try {
                if (var1_6.length() <= 7) ** GOTO lbl51
                var0 = (Long)Constant.checkJarMap.get((Object)var1_6);
                if (var0 != null) {
                    var2_10 = var0.longValue();
                }
                if (DexUtil.c == null) break block33;
                var4_11 = DexUtil.getUpdateCycleByType(6, 600000);
lbl64: // 2 sources:
                if (System.currentTimeMillis() < var4_11 + var2_10) ** GOTO lbl51
                e.a(var1_6, "-1", 1);
                Constant.checkJarMap.put((Object)var1_6, (Object)System.currentTimeMillis());
                i.a();
            }
            catch (Throwable var0_4) {}
            ** while (true)
            {
                finally {
                    block34 : {
                        if (var1_6 != null) {
                            if (var1_6.length() <= 7) break block34;
                            var0_2 = (Long)Constant.checkJarMap.get((Object)var1_6);
                            if (var0_2 != null) {
                                var2_10 = var0_2;
                            }
                            if (DexUtil.c != null) {
                                var4_11 = DexUtil.getUpdateCycleByType(6, 600000);
                            }
                            if (System.currentTimeMillis() < var4_11 + var2_10) break block34;
                            e.a(var1_6, "-1", 1);
                            Constant.checkJarMap.put((Object)var1_6, (Object)System.currentTimeMillis());
                            i.a();
                        }
                    }
                }
            }
            catch (Throwable var1_8) {
                ** continue;
            }
            finally {
                return var0;
            }
        }
        var4_11 = 600000;
        ** GOTO lbl64
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String getCorp(String string2) {
        CorpSignInterface corpSignInterface;
        try {
            corpSignInterface = DexUtil.getCorpSignImpl(false);
            if (corpSignInterface == null) return b.a(string2);
        }
        catch (Throwable var1_3) {
            try {
                return b.a(string2);
            }
            catch (Throwable var0_1) {
                return "";
            }
        }
        return corpSignInterface.getCorp(string2);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static CorpSignInterface getCorpSignImpl(boolean bl2) {
        Class class_ = DexUtil.getClassBymap(null, "cn.com.xy.sms.sdk.Iservice.CorpSignImpl");
        if (class_ == null) return null;
        try {
            return (CorpSignInterface)class_.newInstance();
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
        }
        return null;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static ClassLoader getDexClassLoader() {
        try {
            File file;
            if (a == null && (file = new File(Constant.getJarPath())).exists() && l.a(Constant.getJarPath()).booleanValue()) {
                File file2 = Constant.getContext().getDir("outdex", 0);
                a = new DexClassLoader(file.getCanonicalPath(), file2.getCanonicalPath(), null, Constant.getContext().getClassLoader());
                XyUtil.chmod("640", String.valueOf((Object)file2.getCanonicalPath()) + File.separator + file.getName().substring(0, file.getName().length() - 4) + ".dex");
            }
            do {
                return a;
                break;
            } while (true);
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return a;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static ClassLoader getDexClassLoader(boolean bl2) {
        block4 : {
            try {
                if (a != null && !bl2) break block4;
            }
            catch (Throwable var1_2) {
                var1_2.printStackTrace();
                return a;
            }
            File file = new File(Constant.getJarPath());
            if (!file.exists() || !l.a(Constant.getJarPath()).booleanValue()) break block4;
            File file2 = Constant.getContext().getDir("outdex", 0);
            a = new DexClassLoader(file.getCanonicalPath(), file2.getCanonicalPath(), null, Constant.getContext().getClassLoader());
        }
        do {
            return a;
            break;
        } while (true);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static OnlineParseInterface getOnlineParseImpl(boolean bl2) {
        block6 : {
            try {
                if (b != null && !bl2) break block6;
            }
            catch (Throwable var1_2) {
                var1_2.printStackTrace();
                return b;
            }
            ClassLoader classLoader = DexUtil.getDexClassLoader();
            if (classLoader == null) break block6;
            classLoader = classLoader.loadClass("cn.com.xy.sms.sdk.Iservice.OnlineParseImpl");
            if (classLoader == null) break block6;
            b = (OnlineParseInterface)classLoader.newInstance();
        }
        do {
            return b;
            break;
        } while (true);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static OnlineViewInterface getOnlineViewImpl(boolean bl2) {
        Class class_ = DexUtil.getClassBymap(null, "cn.com.xy.sms.sdk.Iservice.OnlineViewImpl");
        if (class_ == null) return null;
        try {
            return (OnlineViewInterface)class_.newInstance();
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
        }
        return null;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static ParseCardInterface getParseCardImpl(boolean bl2) {
        Class class_ = DexUtil.getClassBymap(null, "cn.com.xy.sms.sdk.Iservice.ParseUtilCard");
        if (class_ == null) return null;
        try {
            return (ParseCardInterface)class_.newInstance();
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
        }
        return null;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static ParseDateInterface getParseDateImpl(boolean bl2) {
        Class class_ = DexUtil.getClassBymap(null, "cn.com.xy.sms.sdk.Iservice.ParseDate");
        if (class_ == null) return null;
        try {
            return (ParseDateInterface)class_.newInstance();
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
        }
        return null;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static ParseNotificationInterface getParseNotificationImpl(boolean bl2) {
        Class class_ = DexUtil.getClassBymap(null, "cn.com.xy.sms.sdk.Iservice.ParseNotification");
        if (class_ == null) return null;
        try {
            return (ParseNotificationInterface)class_.newInstance();
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
        }
        return null;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static ParsePayInterface getParsePayImpl(boolean bl2) {
        Class class_ = DexUtil.getClassBymap(null, "cn.com.xy.sms.sdk.Iservice.ParseUtilPay");
        if (class_ == null) return null;
        try {
            return (ParsePayInterface)class_.newInstance();
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
        }
        return null;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static ParseRemindInterface getParseRemindImpl(boolean bl2) {
        Class class_ = DexUtil.getClassBymap(null, "cn.com.xy.sms.sdk.Iservice.ParseRemind");
        if (class_ == null) return null;
        try {
            return (ParseRemindInterface)class_.newInstance();
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
        }
        return null;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static ParseWatchInterface getParseWatchImpl(boolean bl2) {
        Class class_ = DexUtil.getClassBymap(null, "cn.com.xy.sms.sdk.Iservice.ParseWatch");
        if (class_ == null) return null;
        try {
            return (ParseWatchInterface)class_.newInstance();
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
        }
        return null;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static String getSceneVersion() {
        String string2 = null;
        try {
            String string3;
            string2 = string3 = SysParamEntityManager.getStringParam(Constant.getContext(), "CHANNEL");
            Object object = DexUtil.getOnlineParseImpl(false);
            string2 = string3;
            if (object != null) {
                string2 = string3;
                object = object.getSceneVersion(string3);
                string2 = string3;
                boolean bl2 = StringUtils.isNull((String)object);
                string2 = string3;
                if (!bl2) {
                    return object;
                }
            }
        }
        catch (Throwable var2_2) {
            LogManager.e("DexUtil getSceneVersion", "\u83b7\u53d6\u7b97\u6cd5\u5305\u5185\u7684\u8d44\u6e90\u7248\u672c\u53f7\u51fa\u73b0\u5f02\u5e38");
        }
        if ("VMhlWdEwVNEW_LENOVO".equals((Object)string2)) {
            return "20150619";
        }
        return "20140815";
    }

    public static int getSmsTypeByMap(Map<String, Object> map, int n2) {
        int n3;
        block3 : {
            OnlineParseInterface onlineParseInterface;
            n3 = -1;
            try {
                onlineParseInterface = DexUtil.getOnlineParseImpl(false);
                new StringBuilder("lib=").append(onlineParseInterface);
                if (onlineParseInterface == null) break block3;
            }
            catch (Throwable var0_1) {
                return -1;
            }
            n3 = onlineParseInterface.getSmsTypeByMap(map, n2);
        }
        return n3;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static String getSuanfaVersion() {
        String string2 = null;
        try {
            String string3;
            string2 = string3 = SysParamEntityManager.getStringParam(Constant.getContext(), "CHANNEL");
            Object object = DexUtil.getOnlineParseImpl(false);
            string2 = string3;
            if (object != null) {
                string2 = string3;
                object = object.getReqVersion(string3);
                string2 = string3;
                boolean bl2 = StringUtils.isNull((String)object);
                string2 = string3;
                if (!bl2) {
                    return object;
                }
            }
        }
        catch (Throwable var2_2) {
            LogManager.e("DexUtil getSuanfaVersion", "\u83b7\u53d6\u7b97\u6cd5\u5305\u5185\u7684\u7248\u672c\u53f7\u51fa\u73b0\u5f02\u5e38");
        }
        if ("VMhlWdEwVNEW_LENOVO".equals((Object)string2)) {
            return "20150619";
        }
        return "20150202";
    }

    public static long getUpdateCycleByType(int n2, long l2) {
        long l3;
        try {
            if (c == null) {
                c = DexUtil.a();
            }
            l3 = l2;
        }
        catch (Throwable var5_3) {
            LogManager.e("xiaoyuan", "DexUtil getUpdateCycleByType\uff1a" + var5_3.getMessage(), var5_3);
            return l2;
        }
        if (c != null) {
            l3 = c.getUpdateCycle(n2, l2);
        }
        return l3;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static Map<String, Object> handerBubbleValueMap(Map<String, Object> map) {
        Object object = DexUtil.getClassBymap(null, "cn.com.xy.sms.sdk.Iservice.ParseUtilBubble");
        if (object == null) return null;
        object = (ParseBubbleInterface)object.newInstance();
        if (object == null) return null;
        try {
            return object.handerValueMap(map);
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
        }
        return null;
    }

    public static Map<String, Object> handerDateValueMap(Map<String, Object> map) {
        Map<String, Object> map2;
        block3 : {
            ParseDateInterface parseDateInterface;
            map2 = null;
            try {
                parseDateInterface = DexUtil.getParseDateImpl(false);
                if (parseDateInterface == null) break block3;
            }
            catch (Throwable var0_1) {
                var0_1.printStackTrace();
                return null;
            }
            map2 = parseDateInterface.handerValueMap(map);
        }
        return map2;
    }

    public static Map<String, Object> handerNotificationValueMap(Map<String, Object> map) {
        Map<String, Object> map2;
        block3 : {
            ParseNotificationInterface parseNotificationInterface;
            map2 = null;
            try {
                parseNotificationInterface = DexUtil.getParseNotificationImpl(false);
                if (parseNotificationInterface == null) break block3;
            }
            catch (Throwable var0_1) {
                var0_1.printStackTrace();
                return null;
            }
            map2 = parseNotificationInterface.handerValueMap(map);
        }
        return map2;
    }

    public static Map<String, Object> handerPayValueMap(Map<String, Object> map) {
        Map<String, Object> map2;
        block3 : {
            ParsePayInterface parsePayInterface;
            map2 = null;
            try {
                parsePayInterface = DexUtil.getParsePayImpl(false);
                if (parsePayInterface == null) break block3;
            }
            catch (Throwable var0_1) {
                var0_1.printStackTrace();
                return null;
            }
            map2 = parsePayInterface.handerValueMap(map);
        }
        return map2;
    }

    public static Map<String, Object> handerRemindValueMap(Map<String, Object> map) {
        Map<String, Object> map2;
        block3 : {
            ParseRemindInterface parseRemindInterface;
            map2 = null;
            try {
                parseRemindInterface = DexUtil.getParseRemindImpl(false);
                if (parseRemindInterface == null) break block3;
            }
            catch (Throwable var0_1) {
                var0_1.printStackTrace();
                return null;
            }
            map2 = parseRemindInterface.handerValueMap(map);
        }
        return map2;
    }

    public static Map<String, Object> handerValueMap(Map<String, Object> map) {
        Map<String, Object> map2;
        block3 : {
            ParseCardInterface parseCardInterface;
            map2 = null;
            try {
                parseCardInterface = DexUtil.getParseCardImpl(false);
                if (parseCardInterface == null) break block3;
            }
            catch (Throwable var0_1) {
                var0_1.printStackTrace();
                return null;
            }
            map2 = parseCardInterface.handerValueMap(map);
        }
        return map2;
    }

    public static Map<String, Object> handerValueMap(Map<String, Object> map, String string2) {
        Map<String, Object> map2;
        block3 : {
            OnlineViewInterface onlineViewInterface;
            map2 = null;
            try {
                onlineViewInterface = DexUtil.getOnlineViewImpl(false);
                if (onlineViewInterface == null) break block3;
            }
            catch (Throwable var0_1) {
                var0_1.printStackTrace();
                return null;
            }
            map2 = onlineViewInterface.handerValueMap(map, string2);
        }
        return map2;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static Map<String, Object> handerValueMapByType(int n2, Map<String, Object> map) {
        Object object = DexUtil.getClassBymap(null, "cn.com.xy.sms.sdk.Iservice.ParseVerifyCode");
        if (object == null) return null;
        object = (ParseVerifyCodeInterface)object.newInstance();
        if (object == null) return null;
        try {
            return object.handerValueMapByType(n2, map);
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
        }
        return null;
    }

    public static JSONObject handerWatchValueMap(Map<String, Object> map) {
        JSONObject jSONObject;
        block3 : {
            ParseWatchInterface parseWatchInterface;
            jSONObject = null;
            try {
                parseWatchInterface = DexUtil.getParseWatchImpl(false);
                if (parseWatchInterface == null) break block3;
            }
            catch (Throwable var0_1) {
                var0_1.printStackTrace();
                return null;
            }
            jSONObject = parseWatchInterface.handerValueMap(map);
        }
        return jSONObject;
    }

    public static void init() {
        try {
            if (new File(Constant.getJarPath()).exists()) {
                if (l.a(Constant.getJarPath()).booleanValue()) {
                    a = DexUtil.getDexClassLoader(true);
                    b = DexUtil.getOnlineParseImpl(true);
                    if (LogManager.debug) {
                        return;
                    }
                } else {
                    boolean bl2 = LogManager.debug;
                    if (bl2) {
                        return;
                    }
                }
            }
        }
        catch (Throwable var1_1) {
            var1_1.printStackTrace();
        }
    }

    public static void initOnlineUpdateCycleConfig() {
        c = DexUtil.a();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean isEnterpriseSms(Context context, String string2, String string3, Map<String, String> map) {
        OnlineParseInterface onlineParseInterface;
        try {
            onlineParseInterface = DexUtil.getOnlineParseImpl(false);
            new StringBuilder("lib=").append(onlineParseInterface);
            if (onlineParseInterface == null) return PopupUtil.isEnterpriseSms(context, string2, string3, map);
        }
        catch (Throwable var5_5) {
            return PopupUtil.isEnterpriseSms(context, string2, string3, map);
        }
        return onlineParseInterface.isEnterpriseSms(context, string2, string3, map);
    }

    public static int isServiceChoose(String string2, String string3) {
        int n2;
        block3 : {
            OnlineParseInterface onlineParseInterface;
            n2 = -1;
            try {
                onlineParseInterface = DexUtil.getOnlineParseImpl(false);
                new StringBuilder("lib=").append(onlineParseInterface);
                if (onlineParseInterface == null) break block3;
            }
            catch (Throwable var0_1) {
                new StringBuilder("result=-1");
                return -2;
            }
            n2 = onlineParseInterface.isServiceChoose(string2, string3);
        }
        return n2;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static Map<String, Object> parseMsgToMap(String var0, String var1_1, Map<String, String> var2_5) {
        block5 : {
            var3_6 = DexUtil.getOnlineParseImpl(false);
            if (var3_6 == null) break block5;
            if ((var3_6 = var3_6.parseMessage((String)var0, var1_1, var2_5)) != null) return var3_6;
            LogManager.e("duoqu_test", "&&&&&result is null phoneNumber:" + (String)var0 + " smsContent: " + var1_1 + " msgID: " + var2_5.get("msgId"), null);
            return var3_6;
        }
        try {
            LogManager.e("duoqu_test", "lib = DexUtil.getOnlineParseImpl is null phoneNumber:" + (String)var0 + " smsContent: " + var1_1 + " msgID: " + var2_5.get("msgId"), null);
            return null;
        }
        catch (Throwable var1_2) {
            var0 = null;
            ** GOTO lbl17
            catch (Throwable var1_4) {
                var0 = var3_6;
            }
lbl17: // 2 sources:
            var1_3.printStackTrace();
            return var0;
        }
    }

    public static String[] parseMsgToNewContacts(String string2, String string3, String string4, String[] arrstring) {
        String[] arrstring2;
        block3 : {
            OnlineParseInterface onlineParseInterface;
            arrstring2 = null;
            try {
                onlineParseInterface = DexUtil.getOnlineParseImpl(false);
                new StringBuilder("lib=").append(onlineParseInterface);
                if (onlineParseInterface == null) break block3;
            }
            catch (Throwable var0_1) {
                return null;
            }
            arrstring2 = onlineParseInterface.parseMsgToNewContacts(string2, string3, string4, arrstring);
        }
        return arrstring2;
    }

    public static void removeClassLoaderBySubname(String string2) {
        try {
            d.remove((Object)(String.valueOf((Object)string2) + "_ClassLoader"));
            e.remove((Object)(String.valueOf((Object)string2) + "_Class"));
            return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }
}

