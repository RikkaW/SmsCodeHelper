/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.res.Resources
 *  android.content.res.TypedArray
 *  android.graphics.drawable.Drawable
 *  android.graphics.drawable.GradientDrawable
 *  android.util.Log
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewGroup$MarginLayoutParams
 *  android.view.ViewTreeObserver
 *  android.view.ViewTreeObserver$OnGlobalLayoutListener
 *  android.widget.RelativeLayout
 *  android.widget.ScrollView
 *  java.lang.Float
 *  java.lang.Integer
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.HashMap
 */
package cn.com.xy.sms.sdk.ui.popu.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.smsmessage.BusinessSmsMessage;
import cn.com.xy.sms.sdk.ui.popu.util.ResourceCacheUtil;
import cn.com.xy.sms.sdk.ui.popu.util.ViewManger$1;
import cn.com.xy.sms.sdk.ui.popu.util.ViewUtil;
import cn.com.xy.sms.sdk.util.StringUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ViewManger {
    public static final int ONE_SIDE_POPUPVIEW = 1;
    private static final int TYPE_MARGIN_11;
    private static final int TYPE_PADDING_11;
    private static final int TYPE_SPLIT_LR_MARGIN_111;
    private static final int TYPE_SPLIT_LR_MARGIN_112;
    private static final int TYPE_VIEW_HEIGHT_11;
    private static final Integer[] VIEW_PART_ID;

    static {
        TYPE_PADDING_11 = ViewManger.getIntDimen(Constant.getContext(), br.b.duoqu_type_padding_11);
        TYPE_VIEW_HEIGHT_11 = ViewManger.getIntDimen(Constant.getContext(), br.b.duoqu_type_view_height_11);
        TYPE_SPLIT_LR_MARGIN_111 = ViewManger.getIntDimen(Constant.getContext(), br.b.duoqu_type_split_lr_margin_111);
        TYPE_SPLIT_LR_MARGIN_112 = ViewManger.getIntDimen(Constant.getContext(), br.b.duoqu_type_split_lr_margin_112);
        TYPE_MARGIN_11 = ViewManger.getIntDimen(Constant.getContext(), br.b.duoqu_type_margin_11);
        VIEW_PART_ID = new Integer[]{101, 102, 510, 501, 508, 901, 902, 504, 503, 505, 509, 502};
    }

    static boolean checkHasViewPartId(int n2) {
        Integer[] arrinteger = VIEW_PART_ID;
        int n3 = arrinteger.length;
        for (int i2 = 0; i2 < n3; ++i2) {
            if (arrinteger[i2] != n2) continue;
            return true;
        }
        throw new Exception("checkHasViewPartId partId: " + n2 + " not Find.");
    }

    public static View createContextByLayoutId(Context context, int n2, ViewGroup viewGroup) {
        try {
            context = ((LayoutInflater)context.getSystemService("layout_inflater")).inflate(n2, viewGroup);
            return context;
        }
        catch (Exception var0_1) {
            var0_1.printStackTrace();
            return null;
        }
    }

    public static ViewGroup createFrameViewGroup(Context context) {
        return (ViewGroup)ViewManger.createContextByLayoutId(context, br.e.duoqu_frame_view, null);
    }

    public static RelativeLayout createRootView(Context context) {
        context = new RelativeLayout(context);
        context.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        return context;
    }

    public static ScrollView createScrollView(Context context, View view) {
        return (ScrollView)ViewManger.createContextByLayoutId(context, br.e.duoqu_scroll_view, null);
    }

    public static boolean displayMarkImage(BusinessSmsMessage businessSmsMessage) {
        return true;
    }

    public static boolean displayTime(BusinessSmsMessage businessSmsMessage) {
        return false;
    }

    private static dg getBodyUIPartByPartId(Activity activity, BusinessSmsMessage businessSmsMessage, XyCallBack xyCallBack, ViewGroup viewGroup, int n2) {
        switch (n2) {
            default: {
                return null;
            }
            case 501: {
                return new cu(activity, businessSmsMessage, xyCallBack, br.e.duoqu_horz_title_lf_table, viewGroup, n2);
            }
            case 510: {
                return new cu(activity, businessSmsMessage, xyCallBack, br.e.duoqu_horz_title_lf_table, viewGroup, n2);
            }
            case 502: {
                return new cv(activity, businessSmsMessage, xyCallBack, br.e.duoqu_horz_title_table, viewGroup, n2);
            }
            case 503: {
                return new cp(activity, businessSmsMessage, xyCallBack, br.e.duoqu_horz_air_table, viewGroup, n2);
            }
            case 504: {
                return new cw(activity, businessSmsMessage, xyCallBack, br.e.duoqu_horz_tarin_table, viewGroup, n2);
            }
            case 505: 
        }
        return new cm(activity, businessSmsMessage, xyCallBack, br.e.duoqu_bubble_body_callsmessage, viewGroup, n2);
    }

    public static int getBodyViewPadding(Context context, int n2) {
        switch (n2) {
            default: {
                return 0;
            }
            case 11: 
        }
        return TYPE_PADDING_11;
    }

    public static int getDouquAttrDimen(er object, int n2) {
        if ((object = object.a(1, n2)) != null) {
            return Math.round((float)((Float)object).floatValue());
        }
        return 0;
    }

    private static dg getFootUIPartByPartId(Activity object, BusinessSmsMessage businessSmsMessage, XyCallBack xyCallBack, ViewGroup viewGroup, int n2) {
        switch (n2) {
            default: {
                return null;
            }
            case 901: {
                return new co((Activity)object, businessSmsMessage, xyCallBack, br.e.duoqu_bubble_bottom_two, viewGroup, n2);
            }
            case 902: 
        }
        object = new df((Activity)object, businessSmsMessage, xyCallBack, br.e.duoqu_top_split, viewGroup, n2);
        object.a("MLR", 112);
        return object;
    }

    private static dg getHeadUIPartByPartId(Activity activity, BusinessSmsMessage businessSmsMessage, XyCallBack xyCallBack, ViewGroup viewGroup, int n2) {
        switch (n2) {
            default: {
                return null;
            }
            case 101: {
                return new dd(activity, businessSmsMessage, xyCallBack, br.e.duoqu_bubble_title_head, viewGroup, n2);
            }
            case 102: 
        }
        return new de(activity, businessSmsMessage, xyCallBack, br.e.duoqu_bubble_title_split_head, viewGroup, n2);
    }

    public static int getIdentifier(String string2, String string3) {
        return Constant.getContext().getResources().getIdentifier(string2, string3, Constant.getContext().getPackageName());
    }

    public static int getInnerLayoutMargin(Context context, int n2) {
        switch (n2) {
            default: {
                return 0;
            }
            case 111: {
                return TYPE_SPLIT_LR_MARGIN_111;
            }
            case 112: 
        }
        return TYPE_SPLIT_LR_MARGIN_112;
    }

    public static int getIntDimen(Context context, int n2) {
        return Math.round((float)context.getResources().getDimension(n2));
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static dg getUIPartByPartId(Activity activity, BusinessSmsMessage businessSmsMessage, XyCallBack xyCallBack, ViewGroup viewGroup, int n2) {
        dg dg2 = null;
        if (n2 < 500) {
            return ViewManger.getHeadUIPartByPartId(activity, businessSmsMessage, xyCallBack, viewGroup, n2);
        }
        if (n2 < 900) {
            return ViewManger.getBodyUIPartByPartId(activity, businessSmsMessage, xyCallBack, viewGroup, n2);
        }
        if (n2 < 900) return dg2;
        return ViewManger.getFootUIPartByPartId(activity, businessSmsMessage, xyCallBack, viewGroup, n2);
    }

    public static ArrayList<Integer> getViewPartList(String string2) {
        ArrayList arrayList = new ArrayList();
        int n2 = string2.length();
        int n3 = 0;
        while (n3 < n2 && n3 + 3 <= n2) {
            int n4 = Integer.parseInt((String)string2.substring(n3, n3 + 3));
            ViewManger.checkHasViewPartId(n4);
            arrayList.add((Object)n4);
            n3 += 3;
        }
        return arrayList;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static int indexOfChild(View view, ViewGroup viewGroup) {
        if (view == null || viewGroup == null) {
            Log.e((String)"duoqu_xiaoyuan", (String)"indexOfChild view == null || apView == null");
            return -1;
        }
        int n2 = viewGroup.getChildCount();
        int n3 = 0;
        while (n3 < n2) {
            View view2 = viewGroup.getChildAt(n3);
            int n4 = n3;
            if (view2 == view) return n4;
            if ((view2 = view2.findViewById(999999999)) != null && view2 == view) {
                return n3;
            }
            ++n3;
        }
        return -1;
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static boolean isPopupAble(Map<String, Object> map, String object) {
        if (map == null) return false;
        if (StringUtils.isNull(object)) {
            return false;
        }
        if (!map.containsKey("View_viewid")) return false;
        object = (String)map.get("View_viewid");
        if (StringUtils.isNull((String)object)) return false;
        int n2 = Integer.parseInt((String)object);
        {
            catch (Exception exception) {
                exception.printStackTrace();
                return false;
            }
        }
        try {
            object = ViewManger.parseViewPartParam((String)map.get("View_fdes"));
            if (object == null) return false;
            if (object.isEmpty()) return false;
            map.put("viewPartParam", object);
            if (n2 == 1) {
                return true;
            }
            if (n2 != 1) return true;
            return true;
        }
        catch (Exception var0_1) {
            var0_1.printStackTrace();
            return false;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public static Object obtainStyledAttributes(TypedArray typedArray, byte by2, int n2) {
        Float f2 = null;
        Float f3 = null;
        if (typedArray == null) return f2;
        switch (by2) {
            default: {
                f2 = f3;
                break;
            }
            case 1: {
                f2 = Float.valueOf((float)typedArray.getDimension(n2, -1.0f));
            }
        }
        typedArray.recycle();
        return f2;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static Map<String, dk> parseViewPartParam(String string2) {
        if (string2 == null) {
            return null;
        }
        String[] arrstring = string2.split(";");
        HashMap hashMap = new HashMap();
        int n2 = arrstring.length;
        int n3 = 0;
        while (n3 < n2) {
            String string3;
            String string4;
            string2 = arrstring[n3];
            dk dk2 = new dk();
            int n4 = string2.indexOf(",");
            if (n4 > 0) {
                string4 = string2.substring(0, n4);
                string2 = string2.substring(n4 + 1);
            } else {
                string4 = string2;
                string2 = null;
            }
            if ("H".equals((Object)(string3 = string4.substring(0, 1))) || "F".equals((Object)string3) || "B".equals((Object)string3)) {
                dk2.a = ViewManger.getViewPartList(string4.substring(1));
                hashMap.put(string3, dk2);
                ViewManger.setPartViewParamRule(dk2, string2);
            }
            ++n3;
        }
        return hashMap;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static int setBodyLayoutHeight(Context context, ViewGroup.LayoutParams layoutParams, int n2, int n3) {
        switch (n2) {
            default: {
                return -1;
            }
            case 11: {
                n2 = TYPE_VIEW_HEIGHT_11;
            }
        }
        if (n2 == -1) return n2;
        layoutParams.height = n2;
        return n2;
    }

    public static int setBodyViewPadding(Context context, View view, View view2, dk dk2, int n2) {
        if (view == null || dk2 == null) {
            return -1;
        }
        n2 = ViewManger.getBodyViewPadding(context, dk2.f);
        int n3 = ViewManger.getBodyViewPadding(context, dk2.g);
        int n4 = ViewManger.getBodyViewPadding(context, dk2.h);
        int n5 = ViewManger.getBodyViewPadding(context, dk2.i);
        if (n2 != 0 || n3 != 0 || n4 != 0 || n5 != 0) {
            view.setPadding(n2, n3, n4, n5);
        }
        return 1;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static int setLayoutMarginTop(Context context, ViewGroup.LayoutParams layoutParams, int n2) {
        if (layoutParams == null) return -1;
        if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) return -1;
        context = (ViewGroup.MarginLayoutParams)layoutParams;
        switch (n2) {
            default: {
                return -1;
            }
            case 11: {
                n2 = TYPE_MARGIN_11;
            }
        }
        if (n2 == -1) return n2;
        context.setMargins(context.leftMargin, n2, context.rightMargin, context.bottomMargin);
        return n2;
    }

    /*
     * Enabled aggressive block sorting
     */
    private static void setPartViewParamRule(dk dk2, String string2) {
        boolean bl2;
        boolean bl3 = true;
        if (string2 == null) return;
        int n2 = string2.length();
        if (n2 > 0) {
            bl2 = Integer.parseInt((String)string2.substring(0, 1)) == 1;
            dk2.b = bl2;
        }
        if (n2 > 1) {
            bl2 = Integer.parseInt((String)string2.substring(1, 2)) == 1 ? bl3 : false;
            dk2.c = bl2;
        }
        if (n2 > 3) {
            dk2.d = Integer.parseInt((String)string2.substring(2, 4));
        }
        if (n2 > 5) {
            dk2.e = Integer.parseInt((String)string2.substring(4, 6));
        }
        if (n2 > 7) {
            dk2.f = Integer.parseInt((String)string2.substring(6, 8));
        }
        if (n2 > 9) {
            dk2.g = Integer.parseInt((String)string2.substring(8, 10));
        }
        if (n2 > 11) {
            dk2.h = Integer.parseInt((String)string2.substring(10, 12));
        }
        if (n2 > 13) {
            dk2.i = Integer.parseInt((String)string2.substring(12, 14));
        }
        if (n2 > 15) {
            dk2.j = Integer.parseInt((String)string2.substring(14, 16));
        }
    }

    public static void setRippleDrawable(View view) {
    }

    public static void setViewBg(Context context, View view, String string2, int n2, int n3) {
        ViewManger.setViewBg(context, view, string2, n2, n3, false);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void setViewBg(Context context, View view, String string2, int n2, int n3, boolean bl2) {
        LogManager.i("setViewBg", "relativePath=" + string2 + "resId=" + n2);
        try {
            context = ViewUtil.getDrawable(context, string2, false, bl2);
            if (context != null) {
                ViewUtil.setBackground(view, (Drawable)context);
                return;
            }
            if (n2 == -1) return;
            {
                view.setBackgroundResource(n2);
                context = (GradientDrawable)view.getBackground();
                if (StringUtils.isNull(string2)) return;
                {
                    context.setColor(ResourceCacheUtil.parseColor(string2));
                    if (n3 > 0) {
                        context.setStroke(n3, ResourceCacheUtil.parseColor(string2));
                        return;
                    }
                    context.setColor(ResourceCacheUtil.parseColor(string2));
                    return;
                }
            }
        }
        catch (Exception var0_1) {
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
    public static void setViewBg(Context object, View view, String string2, String string3, int n2, int n3) {
        if (object == null) {
            return;
        }
        if (view == null) return;
        if (StringUtils.isNull(string2)) return;
        if (StringUtils.isNull(string3)) return;
        object = string2.trim();
        string2 = string3.trim();
        {
            catch (Exception exception) {
                exception.printStackTrace();
                return;
            }
        }
        try {
            view.setBackgroundResource(n2);
            view = (GradientDrawable)view.getBackground();
            if (!StringUtils.isNull((String)object)) {
                view.setColor(ResourceCacheUtil.parseColor((String)object));
            }
            if (StringUtils.isNull(string2)) return;
            view.setStroke(n3, ResourceCacheUtil.parseColor(string2));
            return;
        }
        catch (Exception var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    public static void setViewTreeObserver(View view, XyCallBack xyCallBack) {
        try {
            view.getViewTreeObserver().addOnGlobalLayoutListener((ViewTreeObserver.OnGlobalLayoutListener)new ViewManger$1(view, xyCallBack));
            return;
        }
        catch (Exception var0_1) {
            return;
        }
    }
}

