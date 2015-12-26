/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuffer
 *  java.lang.Throwable
 *  org.json.JSONObject
 */
package cn.com.xy.sms.sdk.util;

import android.content.Context;
import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.entity.SysParamEntityManager;
import cn.com.xy.sms.sdk.db.entity.i;
import cn.com.xy.sms.sdk.db.entity.j;
import cn.com.xy.sms.sdk.db.entity.k;
import cn.com.xy.sms.sdk.iccid.IccidLocationUtil;
import cn.com.xy.sms.sdk.net.NetUtil;
import cn.com.xy.sms.sdk.util.DateUtils;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.p;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

public final class o {
    private static String a(Context context) {
        return SysParamEntityManager.getStringParam(context, "LastSceneCountActionUpdate");
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static String a(String iterator) {
        StringBuffer stringBuffer = new StringBuffer();
        iterator = j.a(iterator);
        stringBuffer.append("<SceneStat>");
        if (iterator.size() <= 0) {
            return null;
        }
        iterator = iterator.iterator();
        block2 : do {
            if (!iterator.hasNext()) {
                stringBuffer.append("</SceneStat>");
                return stringBuffer.toString();
            }
            Object object = (i)iterator.next();
            stringBuffer.append("t1;");
            object = object.b;
            stringBuffer.append(String.valueOf((Object)object) + ";");
            stringBuffer.append(String.valueOf((Object)StringUtils.getMD5(IccidLocationUtil.getICCID(Constant.getContext()))) + ";");
            List<i> list = j.b((String)object);
            int n2 = 0;
            do {
                if (n2 >= list.size()) {
                    stringBuffer.append("\n");
                    continue block2;
                }
                i i2 = list.get(n2);
                if (n2 != 0) {
                    stringBuffer.append("&amp;");
                }
                String string2 = i2.a;
                stringBuffer.append(String.valueOf((Object)string2) + ",");
                stringBuffer.append(String.valueOf((int)i2.c) + ",");
                stringBuffer.append(String.valueOf((int)i2.d) + ",");
                i2 = k.a(string2, (String)object);
                if (i2 != null) {
                    try {
                        for (int i3 = 0; i3 < i2.length(); ++i3) {
                            string2 = i2.getJSONObject(i3);
                            if (i3 != 0) {
                                stringBuffer.append("#");
                            }
                            stringBuffer.append(String.valueOf((Object)string2.getString("action_type")) + "=");
                            stringBuffer.append(string2.getString("times"));
                        }
                    }
                    catch (Throwable var6_7) {
                        var6_7.printStackTrace();
                    }
                }
                ++n2;
            } while (true);
            break;
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static void a() {
        boolean bl2 = true;
        Object object = DateUtils.getCurrentTimeString("yyyyMMdd");
        String string2 = SysParamEntityManager.getStringParam(Constant.getContext(), "LastSceneCountActionUpdate");
        if (string2 != null) {
            bl2 = DateUtils.compareDateString((String)object, DateUtils.addDays(string2, "yyyyMMdd", 1), "yyyyMMdd");
        }
        if (!bl2) return;
        try {
            string2 = o.a((String)object);
            if (StringUtils.isNull(string2)) return;
            object = new p((String)object);
            if (!NetUtil.isEnhance()) return;
            NetUtil.executeLoginBeforeHttpRequest(string2, "990005", (XyCallBack)object, NetUtil.STATSERVICE_URL, true);
            return;
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
            return;
        }
    }

    private static void b(String string2) {
        SysParamEntityManager.setParam("LastSceneCountActionUpdate", string2);
    }
}

