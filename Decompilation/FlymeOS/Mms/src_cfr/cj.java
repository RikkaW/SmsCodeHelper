/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.NotificationManager
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.Bitmap
 *  android.graphics.drawable.BitmapDrawable
 *  android.graphics.drawable.Drawable
 *  android.widget.RemoteViews
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.util.HashMap
 *  org.json.JSONArray
 *  org.json.JSONObject
 */
import android.app.NotificationManager;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.RemoteViews;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.ui.notification.MessageItem;
import cn.com.xy.sms.sdk.ui.popu.util.ContentUtil;
import cn.com.xy.sms.sdk.util.DuoquUtils;
import cn.com.xy.sms.sdk.util.JsonUtil;
import cn.com.xy.sms.sdk.util.StringUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class cj {
    private static NotificationManager a = null;

    public static int a(Long l2) {
        if (l2 == null) {
            return 0;
        }
        return l2.intValue();
    }

    private static NotificationManager a(Context context) {
        if (a == null) {
            a = (NotificationManager)context.getSystemService("notification");
        }
        return a;
    }

    public static Drawable a(Context context, String string2) {
        if (!StringUtils.isNull(string2) && (string2 = DuoquUtils.getSdkDoAction().getDrawableByNumber(context, string2, null)) != null) {
            return string2;
        }
        return context.getResources().getDrawable(br.c.duoqu_default_logo);
    }

    public static RemoteViews a(Context context, long l2, String string2, String string3, long l3, Map<String, Object> map, Map<String, String> map2) {
        return cj.a(context, l2, string2, string3, l3, map, map2, true);
    }

    private static RemoteViews a(Context context, long l2, String string2, String object, long l3, Map<String, Object> map, Map<String, String> object2, boolean bl2) {
        if (bl2 && !cj.d(context, l2, string2, (String)object, l3, map, object2)) {
            return null;
        }
        String string3 = (String)map.get("view_content_title");
        String string4 = (String)map.get("view_content_text");
        if (StringUtils.isNull(string3)) {
            return null;
        }
        if (StringUtils.isNull(string4)) {
            string4 = object.trim();
        }
        LogManager.i("getPopupNotificationView", "mTitle---" + string3 + ", mText---" + string4);
        object = cj.a(cj.a(l2), string2, (String)object, l3, map, object2);
        object2 = new cl();
        RemoteViews remoteViews = object2.a(context);
        object2.a(context, ((BitmapDrawable)cj.a(context, string2)).getBitmap(), string3, string4, cj.a(map), (MessageItem)object);
        return remoteViews;
    }

    private static MessageItem a(int n2, String string2, String string3, long l2, Map<String, Object> map, Map<String, String> map2) {
        MessageItem messageItem = new MessageItem();
        messageItem.a = n2;
        messageItem.b = string2;
        messageItem.c = string3;
        messageItem.d = map2;
        messageItem.e = map;
        return messageItem;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static JSONObject a(Map<String, Object> object) {
        JSONObject jSONObject;
        String string2;
        block11 : {
            block10 : {
                object = (String)object.get("ADACTION");
                try {
                    jSONObject = new JSONObject();
                    if ("zh-cn".equalsIgnoreCase(ContentUtil.getLanguage())) {
                        jSONObject.put("readBtn", (Object)ContentUtil.DUOQU_BUTTON_NAME_HAS_READ_ZH);
                        jSONObject.put("deleteBtn", (Object)ContentUtil.DUOQU_BUTTON_NAME_DELETE_ZH);
                        jSONObject.put("replyBtn", (Object)ContentUtil.DUOQU_BUTTON_NAME_REPLY_ZH);
                    } else if ("zh-tw".equalsIgnoreCase(ContentUtil.getLanguage())) {
                        jSONObject.put("readBtn", (Object)ContentUtil.DUOQU_BUTTON_NAME_HAS_READ_TW);
                        jSONObject.put("deleteBtn", (Object)ContentUtil.DUOQU_BUTTON_NAME_DELETE_TW);
                        jSONObject.put("replyBtn", (Object)ContentUtil.DUOQU_BUTTON_NAME_REPLY_TW);
                    } else {
                        jSONObject.put("readBtn", (Object)ContentUtil.DUOQU_BUTTON_NAME_HAS_READ_EN);
                        jSONObject.put("deleteBtn", (Object)ContentUtil.DUOQU_BUTTON_NAME_DELETE_EN);
                        jSONObject.put("replyBtn", (Object)ContentUtil.DUOQU_BUTTON_NAME_REPLY_EN);
                    }
                    if (StringUtils.isNull((String)object)) return jSONObject;
                    if ((object = new JSONArray((String)object)).length() != 1) break block10;
                    string2 = ContentUtil.getBtnName(object.getJSONObject(0));
                    object = null;
                    break block11;
                }
                catch (Exception var0_1) {
                    LogManager.i("getButtonName", "error---" + var0_1.getMessage());
                    return null;
                }
            }
            if (object.length() > 1) {
                string2 = ContentUtil.getBtnName(object.getJSONObject(0));
                object = ContentUtil.getBtnName(object.getJSONObject(1));
            } else {
                object = null;
                string2 = null;
            }
        }
        if (!StringUtils.isNull(string2)) {
            jSONObject.put("btn1", (Object)string2);
        }
        if (StringUtils.isNull((String)object)) return jSONObject;
        jSONObject.put("btn2", object);
        return jSONObject;
    }

    public static void a() {
        a = null;
    }

    public static void a(Context context, int n2) {
        if (n2 != 0) {
            LogManager.i("cancelId", "cancel_id---" + n2);
            cj.a(context).cancel(n2);
        }
    }

    public static void a(Context context, MessageItem messageItem) {
        DuoquUtils.getSdkDoAction().openSms(context, messageItem.b, messageItem.d);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void a(Context context, MessageItem object, int n2) {
        HashMap hashMap = new HashMap();
        hashMap.put((Object)"phoneNum", (Object)object.b);
        hashMap.put((Object)"content", (Object)object.c);
        hashMap.put((Object)"msgId", (Object)String.valueOf((int)object.a));
        LogManager.i("MyNotificationManager", "valueMap---" + hashMap.toString());
        try {
            object = (String)object.e.get("ADACTION");
            object = !StringUtils.isNull((String)object) ? new JSONArray((String)object).getJSONObject(n2) : null;
            DuoquUtils.doActionContext(context, (String)JsonUtil.getValueFromJsonObject((JSONObject)object, "action_data"), hashMap);
            return;
        }
        catch (Exception var0_1) {
            LogManager.e("MyNotificationManager", "error---" + var0_1.getMessage());
            return;
        }
    }

    public static RemoteViews b(Context context, long l2, String string2, String string3, long l3, Map<String, Object> map, Map<String, String> map2) {
        return cj.b(context, l2, string2, string3, l3, map, map2, true);
    }

    private static RemoteViews b(Context context, long l2, String string2, String object, long l3, Map<String, Object> object2, Map<String, String> remoteViews, boolean bl2) {
        if (bl2 && !cj.d(context, l2, string2, (String)object, l3, object2, remoteViews)) {
            return null;
        }
        String string3 = (String)object2.get("view_content_title");
        String string4 = (String)object2.get("view_content_text");
        if (StringUtils.isNull(string3)) {
            return null;
        }
        if (StringUtils.isNull(string4)) {
            string4 = object.trim();
        }
        LogManager.i("getDropNotificationView", "mTitle---" + string3 + ", mText---" + string4);
        object = cj.a(cj.a(l2), string2, (String)object, l3, object2, remoteViews);
        object2 = new ci();
        remoteViews = object2.a(context);
        object2.a(context, ((BitmapDrawable)cj.a(context, string2)).getBitmap(), string3, string4, null, (MessageItem)object);
        return remoteViews;
    }

    public static RemoteViews c(Context context, long l2, String string2, String string3, long l3, Map<String, Object> map, Map<String, String> map2) {
        return cj.c(context, l2, string2, string3, l3, map, map2, true);
    }

    private static RemoteViews c(Context context, long l2, String string2, String string3, long l3, Map<String, Object> map, Map<String, String> map2, boolean bl2) {
        if (bl2 && !cj.d(context, l2, string2, string3, l3, map, map2)) {
            return null;
        }
        return cj.e(context, l2, string2, string3, l3, map, map2);
    }

    /*
     * Enabled aggressive block sorting
     */
    private static boolean d(Context context, long l2, String string2, String string3, long l3, Map<String, Object> map, Map<String, String> map2) {
        if (context == null || l2 == 0 || StringUtils.isNull(string2) || StringUtils.isNull(string3) || map == null || map.size() == 0) {
            return false;
        }
        return true;
    }

    private static RemoteViews e(Context context, long l2, String string2, String object, long l3, Map<String, Object> map, Map<String, String> object2) {
        String string3 = (String)map.get("view_content_title");
        String string4 = (String)map.get("view_content_text");
        if (StringUtils.isNull(string3)) {
            return null;
        }
        if (StringUtils.isNull(string4)) {
            string4 = object.trim();
        }
        LogManager.i("getDropNotificationView", "mTitle---" + string3 + ", mText---" + string4);
        object = cj.a(cj.a(l2), string2, (String)object, l3, map, object2);
        object2 = new ci();
        RemoteViews remoteViews = object2.a(context);
        object2.a(context, ((BitmapDrawable)cj.a(context, string2)).getBitmap(), string3, string4, cj.a(map), (MessageItem)object);
        return remoteViews;
    }
}

