/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.res.Resources
 *  android.content.res.Resources$NotFoundException
 *  android.graphics.drawable.Drawable
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.TextView
 *  java.lang.Object
 *  java.lang.String
 *  java.util.HashMap
 *  java.util.Map$Entry
 *  org.json.JSONObject
 */
package cn.com.xy.sms.sdk.ui.popu.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.ui.popu.util.ContentUtil;
import cn.com.xy.sms.sdk.ui.popu.util.SimpleButtonUtil$1;
import cn.com.xy.sms.sdk.ui.popu.util.ViewUtil;
import cn.com.xy.sms.sdk.util.DuoquUtils;
import cn.com.xy.sms.sdk.util.JsonUtil;
import cn.com.xy.sms.sdk.util.StringUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

public class SimpleButtonUtil {
    private static final int DRAWABLE_BOUNDS_RIGHT = (int)ViewUtil.getDimension(br.b.duoqu_drawable_bounds_right);
    static int mBottom;
    static int mRight;
    private static int mTop;

    static {
        mTop = 0;
        mRight = 0;
        mBottom = 0;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static int bindButtonData(TextView textView, String string2, boolean bl2, boolean bl3) {
        if (StringUtils.isNull(string2)) return -1;
        {
            if (string2.equalsIgnoreCase("url")) {
                if (!bl2) return -1;
                {
                    textView.setText(br.f.duoqu_open_net);
                    return -1;
                }
            } else if (string2.equalsIgnoreCase("reply_sms") || string2.equalsIgnoreCase("send_sms")) {
                if (!bl2) return -1;
                {
                    textView.setText(br.f.duoqu_reply_sms);
                    return -1;
                }
            } else if (string2.equalsIgnoreCase("reply_sms_fwd")) {
                if (!bl2) return -1;
                {
                    textView.setText(br.f.duoqu_forword_sms);
                    return -1;
                }
            } else if (string2.equalsIgnoreCase("call_phone") || string2.equalsIgnoreCase("call")) {
                if (!bl2) return -1;
                {
                    textView.setText(br.f.duoqu_call_phone);
                    return -1;
                }
            } else if (string2.equalsIgnoreCase("reply_sms_open")) {
                if (!bl2) return -1;
                {
                    textView.setText(br.f.duoqu_open_text);
                    return -1;
                }
            } else if (string2.equalsIgnoreCase("access_url")) {
                if (!bl2) return -1;
                {
                    textView.setText(br.f.duoqu_open_net);
                    return -1;
                }
            } else if (string2.equalsIgnoreCase("down_url")) {
                if (!bl2) return -1;
                {
                    textView.setText(br.f.duoqu_open_net);
                    return -1;
                }
            } else if (string2.equalsIgnoreCase("send_email")) {
                if (!bl2) return -1;
                {
                    textView.setText(br.f.duoqu_send_email);
                    return -1;
                }
            } else if (string2.equalsIgnoreCase("map_site") || string2.equalsIgnoreCase("open_map_list")) {
                if (!bl2) return -1;
                {
                    textView.setText(br.f.duoqu_open_map);
                    return -1;
                }
            } else if (string2.equalsIgnoreCase("chong_zhi") || string2.equalsIgnoreCase("recharge")) {
                if (!bl2) return -1;
                {
                    textView.setText(br.f.duoqu_chonzhi);
                    return -1;
                }
            } else {
                if (string2.equalsIgnoreCase("WEB_QUERY_EXPRESS_FLOW") || string2.equalsIgnoreCase("WEB_QUERY_FLIGHT_TREND") || string2.equalsIgnoreCase("WEB_TRAFFIC_ORDER") || string2.equalsIgnoreCase("WEB_INSTALMENT_PLAN") || string2.equalsIgnoreCase("copy_code") || string2.equalsIgnoreCase("zfb_repayment") || string2.equalsIgnoreCase("repayment") || string2.equalsIgnoreCase("sdk_time_remind") || string2.equalsIgnoreCase("time_remind") || string2.equalsIgnoreCase("web_train_station") || string2.equalsIgnoreCase("open_map") || !string2.equalsIgnoreCase("pay_water_gas")) return -1;
                return -1;
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void doAction(Activity activity, JSONObject jSONObject, HashMap<String, Object> object) {
        try {
            HashMap hashMap = new HashMap();
            if (object != null && !object.isEmpty()) {
                for (Map.Entry entry : object.entrySet()) {
                    if (!(entry.getValue() instanceof String)) continue;
                    hashMap.put(entry.getKey(), (Object)((String)entry.getValue()));
                }
            }
            DuoquUtils.doAction(activity, (String)JsonUtil.getValueFromJsonObject(jSONObject, "action_data"), hashMap);
            return;
        }
        catch (Exception var0_1) {
            if (LogManager.debug) {
                var0_1.printStackTrace();
            }
            return;
        }
    }

    public static int getBottom() {
        if (mBottom == 0) {
            mBottom = SimpleButtonUtil.getRight();
        }
        return mBottom;
    }

    public static int getRight() {
        if (mRight == 0) {
            mRight = DRAWABLE_BOUNDS_RIGHT;
        }
        return mRight;
    }

    public static int getTop() {
        return mTop;
    }

    public static void setBotton(Activity activity, View view, TextView textView, JSONObject jSONObject, boolean bl2, HashMap<String, Object> hashMap) {
        if (jSONObject != null) {
            String string2 = (String)JsonUtil.getValueFromJsonObject(jSONObject, "action");
            SimpleButtonUtil.setButtonValue(activity, textView, jSONObject, bl2, string2, true);
            if (!StringUtils.isNull(string2)) {
                view.setTag((Object)jSONObject);
                view.setOnClickListener((View.OnClickListener)new SimpleButtonUtil$1(activity, jSONObject, hashMap));
            }
        }
    }

    public static void setBottonValue(Activity activity, TextView textView, JSONObject jSONObject, boolean bl2, boolean bl3) {
        if (jSONObject != null) {
            SimpleButtonUtil.setButtonValue(activity, textView, jSONObject, bl2, (String)JsonUtil.getValueFromJsonObject(jSONObject, "action"), bl3);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void setButtonTextAndImg(Context context, TextView textView, String string2, boolean bl2, boolean bl3) {
        if (textView == null) {
            return;
        }
        try {
            int n2 = SimpleButtonUtil.bindButtonData(textView, string2, StringUtils.isNull(textView.getText().toString()), bl3);
            if (bl2 && n2 != -1) {
                context = Constant.getContext().getResources().getDrawable(n2);
                context.setBounds(0, SimpleButtonUtil.getTop(), SimpleButtonUtil.getRight(), SimpleButtonUtil.getBottom());
                textView.setCompoundDrawables((Drawable)context, null, null, null);
                return;
            }
            textView.setCompoundDrawables(null, null, null, null);
            return;
        }
        catch (Resources.NotFoundException var0_1) {
            var0_1.printStackTrace();
            return;
        }
        catch (Exception var0_2) {
            var0_2.printStackTrace();
            return;
        }
    }

    private static void setButtonValue(Activity object, TextView textView, JSONObject jSONObject, boolean bl2, String string2, boolean bl3) {
        object = ContentUtil.getBtnName(jSONObject);
        if (!StringUtils.isNull((String)object)) {
            if (bl3) {
                // empty if block
            }
            textView.setText((CharSequence)object);
        }
    }
}

