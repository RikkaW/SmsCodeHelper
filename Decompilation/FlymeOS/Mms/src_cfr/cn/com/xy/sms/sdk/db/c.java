/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentValues
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 */
package cn.com.xy.sms.sdk.db;

import android.content.ContentValues;
import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.ParseItemManager;
import cn.com.xy.sms.sdk.db.XyCursor;
import cn.com.xy.sms.sdk.db.d;
import cn.com.xy.sms.sdk.db.entity.SysParamEntityManager;
import cn.com.xy.sms.sdk.db.entity.f;
import cn.com.xy.sms.sdk.dex.DexUtil;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.net.NetUtil;
import cn.com.xy.sms.sdk.net.util.i;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.XyUtil;
import cn.com.xy.sms.sdk.util.x;
import java.util.Map;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public final class c {
    private static int a = 0;
    private static int b = 1;
    private static String c = "id";
    private static String d = "name";
    private static String e = "version";
    private static String f = "url";
    private static String g = "status";
    private static String h = "last_load_time";
    private static String i = "update_time";
    private static String j = "delaystart";
    private static String k = "delayend";
    private static String l = "count";
    private static String m = "tb_menu_list";
    private static String n = " DROP TABLE IF EXISTS tb_menu_list";
    private static String o = "create table  if not exists tb_menu_list (id INTEGER PRIMARY KEY,name TEXT,version TEXT,url TEXT,status INTEGER DEFAULT '0',update_time INTEGER DEFAULT '0',delaystart INTEGER DEFAULT '0',delayend INTEGER DEFAULT '0',count INTEGER DEFAULT '0',last_load_time INTEGER DEFAULT '0' )";

    /*
     * Enabled aggressive block sorting
     */
    public static void a() {
        f f2 = c.c();
        if (f2 == null) return;
        {
            if (System.currentTimeMillis() > f2.e + DexUtil.getUpdateCycleByType(5, 172800000)) {
                c.a(f2, null, true, null);
                return;
            } else {
                if (SysParamEntityManager.getIntParam(Constant.getContext(), "AUTO_UPDATE_DATA") != 0 || !NetUtil.checkAccessNetWork(1)) return;
                {
                    c.b(f2);
                    return;
                }
            }
        }
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static void a(f object) {
        new StringBuilder("\u8d85\u8fc7\u5468\u671f\uff0c\u542f\u52a8\u4e0b\u8f7d name=").append(object.b).append(" url=").append(object.d);
        if (StringUtils.isNull(object.d)) return;
        cn.com.xy.sms.sdk.util.d.f(object.d, Constant.getFilePath(), "duoqu_nqsql.zip");
        XyUtil.upZipFile(String.valueOf((Object)Constant.getFilePath()) + "duoqu_nqsql.zip", Constant.getINITSQL_PATH());
        new StringBuilder("\u6210\u529f\u4e0b\u8f7d").append("duoqu_nqsql.zip").append("\uff0c\u6210\u529f\u89e3\u538b").append("duoqu_nqsql.zip");
        if (cn.com.xy.sms.sdk.util.d.a(String.valueOf((Object)Constant.getINITSQL_PATH()) + "menu.sql")) {
            c.b();
        }
        cn.com.xy.sms.sdk.util.d.c(String.valueOf((Object)Constant.getFilePath()) + "duoqu_nqsql.zip");
        new StringBuilder("updateLastLoadTime").append(object.b);
        object = object.b;
        {
            catch (Throwable throwable) {
                return;
            }
        }
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("last_load_time", String.valueOf((long)System.currentTimeMillis()));
            contentValues.put("status", "1");
            DBManager.update("tb_menu_list", contentValues, "name = ? ", new String[]{object});
            return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static void a(f f2, XyCallBack object, boolean bl2, Map<String, String> map) {
        object = new d(bl2, map, f2, (XyCallBack)object);
        String string2 = f2.c;
        String string3 = f2.b;
        string2 = i.a(string2, f2.f, f2.j);
        if (!NetUtil.checkAccessNetWork(map)) return;
        NetUtil.executeHttpPublicRequest("", string2, (XyCallBack)object, String.valueOf((Object)NetUtil.getPubNumServiceUrl()) + "updatepublic", map);
        object = f2.b;
        int n2 = f2.j;
        {
            catch (Throwable throwable) {
                throwable.printStackTrace();
                return;
            }
        }
        try {
            f2 = new ContentValues();
            f2.put("count", Integer.valueOf((int)(n2 + 1)));
            DBManager.update("tb_menu_list", (ContentValues)f2, "name = ? ", new String[]{object});
            return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    public static void a(String string2) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("update_time", String.valueOf((long)System.currentTimeMillis()));
            DBManager.update("tb_menu_list", contentValues, "name = ? ", new String[]{string2});
            return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    private static void a(String string2, int n2) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("last_load_time", String.valueOf((long)System.currentTimeMillis()));
            contentValues.put("status", "1");
            DBManager.update("tb_menu_list", contentValues, "name = ? ", new String[]{string2});
            return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    public static void a(String string2, long l2, long l3) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("update_time", String.valueOf((long)System.currentTimeMillis()));
            contentValues.put("delaystart", String.valueOf((long)l2));
            contentValues.put("delayend", String.valueOf((long)l3));
            DBManager.update("tb_menu_list", contentValues, "name = ? ", new String[]{string2});
            return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    public static void a(String string2, String string3, String string4, long l2, int n2, long l3, long l4) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("version", string3);
            contentValues.put("url", string4);
            contentValues.put("status", Integer.valueOf((int)n2));
            contentValues.put("update_time", String.valueOf((long)l2));
            contentValues.put("delaystart", String.valueOf((long)l3));
            contentValues.put("delayend", String.valueOf((long)l4));
            DBManager.update("tb_menu_list", contentValues, "name = ? ", new String[]{string2});
            return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void a(Map<String, String> map, XyCallBack xyCallBack) {
        if (!NetUtil.checkAccessNetWork(map)) {
            XyUtil.doXycallBack(xyCallBack, "-1");
            return;
        }
        f f2 = c.c();
        if (f2 == null) return;
        {
            if (!StringUtils.isNull(f2.d) && f2.f == 0) {
                XyUtil.doXycallBack(xyCallBack, "1");
                return;
            }
        }
        c.a(f2, xyCallBack, false, map);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static f b(String var0) {
        try {
            var0 = StringUtils.stringConvertXML((String)var0, "");
            if (var0 == null) {
                return null;
            }
            var7_4 = new f();
            var6_5 = "";
            var8_6 = var0.getDocumentElement();
            var9_7 = var8_6.getElementsByTagName("PublicInfoVersion");
            var0 = var6_5;
            if (var9_7 != null) {
                var0 = var6_5;
                if (var9_7.getLength() > 0) {
                    var0 = var6_5 = x.a(var9_7.item(0));
                    if (StringUtils.isNull(var6_5)) {
                        var0 = "";
                    }
                }
            }
            var7_4.c = var0;
            var6_5 = "";
            var9_7 = var8_6.getElementsByTagName("downLoadUrl");
            var0 = var6_5;
            if (var9_7 != null) {
                var0 = var6_5;
                if (var9_7.getLength() > 0) {
                    var0 = var6_5 = x.a(var9_7.item(0));
                    if (StringUtils.isNull(var6_5)) {
                        var0 = "";
                    }
                }
            }
            var7_4.d = var0;
            var0 = var8_6.getElementsByTagName("delaystart");
            if (var0 == null || var0.getLength() <= 0 || (var1_8 = StringUtils.isNull((String)(var0 = x.a(var0.item(0)))))) ** GOTO lbl37
        }
lbl29: // 3 sources:
        catch (Throwable var0_3) {
            var0_3.printStackTrace();
            return null;
        }
        var2_9 = Long.parseLong((String)var0);
        ** GOTO lbl39
        {
            block17 : {
                block16 : {
                    catch (Throwable var0_1) {
                        var0_1.printStackTrace();
                    }
lbl37: // 2 sources:
                    var2_9 = 0;
lbl39: // 3 sources:
                    var7_4.h = var2_9;
                    var0 = var8_6.getElementsByTagName("delayend");
                    if (var0 == null || var0.getLength() <= 0 || (var1_8 = StringUtils.isNull((String)(var0 = x.a(var0.item(0)))))) break block16;
                    try {
                        var2_9 = Long.parseLong((String)var0);
                        break block17;
                    }
                    catch (Throwable var0_2) {
                        var0_2.printStackTrace();
                    }
                }
                var2_9 = 0;
            }
            var4_10 = var2_9;
            if (var2_9 <= 0) {
                var4_10 = 86400000;
            }
            ** try [egrp 4[TRYBLOCK] [15 : 286->309)] { 
lbl54: // 1 sources:
            var7_4.i = var4_10;
            return var7_4;
        }
    }

    public static void b() {
        try {
            c.d("pubInfo");
            c.d("pubNum");
            c.d("pubMenu");
            ParseItemManager.updateNeiQianSql(Constant.getContext());
            return;
        }
        catch (Throwable var0) {
            LogManager.e("xiaoyuan", "insertMenuData error\uff1a " + var0.getMessage(), var0);
            return;
        }
    }

    public static void b(f f2) {
        try {
            long l2 = System.currentTimeMillis();
            new StringBuilder("\u68c0\u67e5\u4e0b\u8f7d\u65f6\u95f4menuInfo.delaystart=").append(f2.h).append("menuInfo.delayend=").append(f2.i).append("nowTime=").append(l2).append(" menuInfo.status=").append(f2.f);
            if (f2.f == 0 && f2.h <= l2 && f2.i >= l2) {
                c.a(f2);
            }
            return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    private static void b(String string2, int n2) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("count", Integer.valueOf((int)(n2 + 1)));
            DBManager.update("tb_menu_list", contentValues, "name = ? ", new String[]{string2});
            return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    /*
     * Exception decompiling
     */
    public static f c() {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [1[TRYBLOCK]], but top level block is 8[SIMPLE_IF_TAKEN]
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:394)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:446)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:2859)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:805)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:220)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:165)
        // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:91)
        // org.benf.cfr.reader.entities.Method.analyse(Method.java:354)
        // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:751)
        // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:683)
        // org.benf.cfr.reader.Main.doJar(Main.java:128)
        // org.benf.cfr.reader.Main.main(Main.java:178)
        throw new IllegalStateException("Decompilation failed");
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    public static void c(String string2) {
        XyCursor xyCursor;
        block9 : {
            // MONITORENTER : cn.com.xy.sms.sdk.db.c.class
            XyCursor xyCursor2 = null;
            XyCursor xyCursor3 = null;
            try {
                xyCursor = DBManager.query("tb_menu_list", new String[]{"url", "version"}, "name = ? ", new String[]{string2});
                if (xyCursor != null) {
                    xyCursor3 = xyCursor;
                    xyCursor2 = xyCursor;
                    if (xyCursor.getCount() > 0) break block9;
                }
                xyCursor3 = xyCursor;
                xyCursor2 = xyCursor;
                ContentValues contentValues = new ContentValues();
                xyCursor3 = xyCursor;
                xyCursor2 = xyCursor;
                contentValues.put("name", string2);
                xyCursor3 = xyCursor;
                xyCursor2 = xyCursor;
                contentValues.put("version", "-1");
                xyCursor3 = xyCursor;
                xyCursor2 = xyCursor;
                DBManager.insert("tb_menu_list", contentValues);
            }
            catch (Throwable var0_1) {
                xyCursor2 = xyCursor3;
                try {
                    var0_1.printStackTrace();
                }
                catch (Throwable var0_2) {
                    XyCursor.closeCursor(xyCursor2, true);
                    throw var0_2;
                }
                XyCursor.closeCursor(xyCursor3, true);
                return;
            }
        }
        XyCursor.closeCursor(xyCursor, true);
        // MONITOREXIT : cn.com.xy.sms.sdk.db.c.class
        return;
    }

    public static boolean c(f f2) {
        if (f2 != null) {
            try {
                int n2;
                if (!StringUtils.isNull(f2.d) && (n2 = f2.f) == 0) {
                    return true;
                }
            }
            catch (Throwable var0_1) {
                // empty catch block
            }
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    private static void d(f f2) {
        if (System.currentTimeMillis() > f2.e + DexUtil.getUpdateCycleByType(5, 172800000)) {
            c.a(f2, null, true, null);
            return;
        } else {
            if (SysParamEntityManager.getIntParam(Constant.getContext(), "AUTO_UPDATE_DATA") != 0 || !NetUtil.checkAccessNetWork(1)) return;
            {
                c.b(f2);
                return;
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static void d(String string2) {
        String string3 = "";
        if (StringUtils.isNull(string2)) return;
        if (string2.equalsIgnoreCase("pubInfo")) {
            string3 = "tb_public_info";
        } else if (string2.equalsIgnoreCase("pubNum")) {
            string3 = "tb_public_num_info";
        } else if (string2.equalsIgnoreCase("pubMenu")) {
            string3 = "tb_public_menu_info";
        }
        if (StringUtils.isNull(string3)) return;
        try {
            DBManager.delete(string3, null, null);
            return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }
}

