/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.content.res.Resources$NotFoundException
 *  android.view.View
 *  android.widget.TextView
 *  java.lang.Character
 *  java.lang.Object
 *  java.lang.String
 *  java.util.Locale
 *  org.json.JSONObject
 */
package cn.com.xy.sms.sdk.ui.popu.util;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.TextView;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.ui.popu.util.ResourceCacheUtil;
import cn.com.xy.sms.sdk.util.JsonUtil;
import cn.com.xy.sms.sdk.util.StringUtils;
import java.util.Locale;
import org.json.JSONObject;

public class ContentUtil {
    public static final String CHINESE = "[\u4e00-\u9fa5]";
    public static final String COR_BLACK = "#000000";
    public static final String COR_GRAY = "#7F000000";
    public static final String COR_LIGHT_BLUE = "#D4EEFB";
    public static final String COR_RED = "#FF1F00";
    public static final String COR_WHITE = "#ffffff";
    public static final String DUOQU_BUTTON_NAME_DELETE_EN;
    public static final String DUOQU_BUTTON_NAME_DELETE_TW;
    public static final String DUOQU_BUTTON_NAME_DELETE_ZH;
    public static final String DUOQU_BUTTON_NAME_HAS_READ_EN;
    public static final String DUOQU_BUTTON_NAME_HAS_READ_TW;
    public static final String DUOQU_BUTTON_NAME_HAS_READ_ZH;
    public static final String DUOQU_BUTTON_NAME_REPLY_EN;
    public static final String DUOQU_BUTTON_NAME_REPLY_TW;
    public static final String DUOQU_BUTTON_NAME_REPLY_ZH;
    public static final int HORIZ_LF_TABLE_TITLE_WIDTH;
    public static final int HORIZ_TABLE_MIN_TITLE_WIDTH;
    public static final int HORIZ_TABLE_TITLE_LINE_SPACING;
    public static final int HORIZ_TABLE_TITLE_PADDING;
    public static final String NO_DATA;
    public static final String NO_DATA_EN;

    static {
        NO_DATA = ContentUtil.getResourceString(Constant.getContext(), br.f.duoqu_double_line);
        NO_DATA_EN = ContentUtil.getResourceString(Constant.getContext(), br.f.duoqu_double_line_en);
        HORIZ_TABLE_TITLE_PADDING = ContentUtil.getDimension(br.b.duoqu_title_padding);
        HORIZ_TABLE_TITLE_LINE_SPACING = ContentUtil.getDimension(br.b.duoqu_title_Line_spacing);
        HORIZ_TABLE_MIN_TITLE_WIDTH = ContentUtil.getDimension(br.b.duoqu_title_min_width);
        HORIZ_LF_TABLE_TITLE_WIDTH = ContentUtil.getDimension(br.b.duoqu_title_width);
        DUOQU_BUTTON_NAME_HAS_READ_ZH = ContentUtil.getResourceString(Constant.getContext(), br.f.duoqu_has_read_zh);
        DUOQU_BUTTON_NAME_HAS_READ_EN = ContentUtil.getResourceString(Constant.getContext(), br.f.duoqu_has_read_en);
        DUOQU_BUTTON_NAME_HAS_READ_TW = ContentUtil.getResourceString(Constant.getContext(), br.f.duoqu_has_read_tw);
        DUOQU_BUTTON_NAME_DELETE_ZH = ContentUtil.getResourceString(Constant.getContext(), br.f.duoqu_delete_zh);
        DUOQU_BUTTON_NAME_DELETE_EN = ContentUtil.getResourceString(Constant.getContext(), br.f.duoqu_delete_en);
        DUOQU_BUTTON_NAME_DELETE_TW = ContentUtil.getResourceString(Constant.getContext(), br.f.duoqu_delete_tw);
        DUOQU_BUTTON_NAME_REPLY_ZH = ContentUtil.getResourceString(Constant.getContext(), br.f.duoqu_reply_zh);
        DUOQU_BUTTON_NAME_REPLY_EN = ContentUtil.getResourceString(Constant.getContext(), br.f.duoqu_reply_en);
        DUOQU_BUTTON_NAME_REPLY_TW = ContentUtil.getResourceString(Constant.getContext(), br.f.duoqu_reply_tw);
    }

    public static int getBackgroundResId(String string2) {
        return br.a.duoqu_all_transparent;
    }

    public static String getBtnName(JSONObject jSONObject) {
        if ("zh-cn".equalsIgnoreCase(ContentUtil.getLanguage())) {
            return (String)JsonUtil.getValueFromJsonObject(jSONObject, "btn_name");
        }
        if ("zh-tw".equalsIgnoreCase(ContentUtil.getLanguage())) {
            return (String)JsonUtil.getValueFromJsonObject(jSONObject, "ftName");
        }
        return (String)JsonUtil.getValueFromJsonObject(jSONObject, "egName");
    }

    public static int getDimension(int n2) {
        return (int)Constant.getContext().getResources().getDimension(n2);
    }

    public static String getLanguage() {
        String string2 = Locale.getDefault().getLanguage();
        String string3 = Locale.getDefault().getCountry();
        if ("zh".equals((Object)string2)) {
            if ("HK".equalsIgnoreCase(string3) || "TW".equalsIgnoreCase(string3)) {
                return "zh-tw";
            }
            return "zh-cn";
        }
        return "en";
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String getResourceString(Context context, int n2) {
        String string2 = null;
        if (context == null) return string2;
        try {
            return context.getResources().getString(n2);
        }
        catch (Resources.NotFoundException notFoundException) {
            return null;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public static int getStringLength(String string2) {
        int n2 = 0;
        if (string2 == null) {
            return 0;
        }
        int n3 = 0;
        while (n2 < string2.length()) {
            n3 = Character.valueOf((char)string2.charAt(n2)).toString().matches("[\u4e00-\u9fa5]") ? (n3 += 2) : ++n3;
            ++n2;
        }
        return n3;
    }

    public static void isTextSetColor(TextView textView, String string2, int n2) {
        if (textView == null) {
            return;
        }
        int n3 = ResourceCacheUtil.parseColor(string2);
        if (n3 != -1) {
            textView.setTextColor(n3);
            return;
        }
        textView.setTextColor(n2);
    }

    public static void setText(TextView textView, String string2, String string3) {
        if (textView == null) {
            return;
        }
        if (StringUtils.isNull(string2)) {
            textView.setText((CharSequence)string3);
            return;
        }
        textView.setText((CharSequence)string2.trim());
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void setTextColor(TextView textView, String string2) {
        if (textView == null) return;
        try {
            if (StringUtils.isNull(string2)) return;
            textView.setTextColor(ResourceCacheUtil.parseColor(string2));
            return;
        }
        catch (Exception var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    public static void setViewVisibility(View view, int n2) {
        if (view != null) {
            view.setVisibility(n2);
        }
    }

    public static void textSetColor(TextView textView, String string2) {
        int n2 = ResourceCacheUtil.parseColor(string2);
        if (n2 != -1 && textView != null) {
            textView.setTextColor(n2);
        }
    }
}

