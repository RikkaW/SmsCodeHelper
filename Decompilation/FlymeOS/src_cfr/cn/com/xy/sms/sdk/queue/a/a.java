/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.File
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 */
package cn.com.xy.sms.sdk.queue.a;

import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.entity.SysParamEntityManager;
import cn.com.xy.sms.sdk.dex.DexUtil;
import cn.com.xy.sms.sdk.util.d;
import java.io.File;
import java.util.Iterator;
import java.util.List;

public final class a {
    private static int a = 1;

    public static List<File> a(String string2) {
        return d.e(Constant.getPath("duoqu_temp"), String.valueOf((Object)string2) + "_", ".zip");
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void a(String string2, String string3) {
        List<File> list = a.a(string2);
        if (list == null || list.isEmpty()) return;
        list = list.iterator();
        while (list != null && list.hasNext()) {
            File file = (File)list.next();
            if ((String.valueOf((Object)string2) + "_" + string3 + "_").compareTo(file.getName()) >= 0) {
                file.delete();
                new StringBuilder("deleteFile=").append(file.getAbsolutePath());
                continue;
            }
            list.remove();
            new StringBuilder("\u4e0d\u5220\u9664").append(file.getAbsolutePath());
        }
        return;
    }

    public static boolean a(int n2) {
        int n3;
        block4 : {
            int n4;
            n3 = n4 = SysParamEntityManager.getIntParam(Constant.getContext(), "ONLINE_UPDATE_RES_PERIOD");
            if (n4 > 0) break block4;
            n3 = 2;
        }
        try {
            long l2 = SysParamEntityManager.getLongParam("LastCheckResourseTime_" + n2, 0, Constant.getContext());
            long l3 = System.currentTimeMillis();
            long l4 = DexUtil.getUpdateCycleByType(9, 86400000 * (long)n3);
            if (l3 > l4 + l2) {
                return true;
            }
        }
        catch (Throwable var9_6) {
            // empty catch block
        }
        return false;
    }

    public static void b(int n2) {
        SysParamEntityManager.setParam("LastCheckResourseTime_" + n2, String.valueOf((long)System.currentTimeMillis()));
    }

    public static String c(int n2) {
        if (n2 == 1) {
            return "5YD15P";
        }
        return "3T0CFQ";
    }
}

