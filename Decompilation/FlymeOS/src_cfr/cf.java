/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.annotation.SuppressLint
 *  android.app.Activity
 *  android.content.Context
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.AdapterView
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.util.HashMap
 *  java.util.LinkedList
 *  org.json.JSONObject
 */
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.smsmessage.BusinessSmsMessage;
import cn.com.xy.sms.sdk.ui.popu.util.ViewManger;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.XyUtil;
import cn.com.xy.sms.util.ParseRichBubbleManager;
import cn.com.xy.sms.util.SdkCallBack;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import org.json.JSONObject;

@SuppressLint(value={"NewApi"})
public class cf {
    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static View a(Activity object, BusinessSmsMessage businessSmsMessage, String string2, View object2, ViewGroup object3, ce ce2) {
        Object object4;
        LinkedList<dj> linkedList;
        if (!StringUtils.isNull(string2)) {
            linkedList = ce2.c(string2);
            if (linkedList != null) {
                int n2;
                int n3;
                int n4 = linkedList.size();
                int n5 = 0;
                do {
                    object4 = cf.a(linkedList);
                    n2 = ViewManger.indexOfChild((View)object4, object3);
                    n3 = n5 + 1;
                    if (n2 == -1) break;
                    n5 = n3;
                } while (n3 < n4);
                if (n2 != -1) {
                    object4 = null;
                }
                object2 = object4;
                object3 = linkedList;
                if (object4 != null) {
                    try {
                        object4.b((Activity)object, businessSmsMessage);
                        object3 = linkedList;
                        object2 = object4;
                    }
                    catch (Exception var3_4) {
                        var3_4.printStackTrace();
                        object2 = null;
                        object3 = linkedList;
                    }
                }
            } else {
                object2 = null;
                object3 = linkedList;
            }
        } else {
            object3 = null;
            object2 = null;
        }
        object4 = object2;
        if (object2 == null) {
            object4 = new dj((Context)object);
            object2 = linkedList = (Map)ce2.c.get((Object)string2);
            if (linkedList == null) {
                object2 = ViewManger.parseViewPartParam(string2);
                ce2.c.put((Object)string2, object2);
            }
            businessSmsMessage.putValue("viewPartParam", object2);
            object4.a((Activity)object, businessSmsMessage, null);
            object4.setId(999999999);
            if (object3 == null) {
                object = new LinkedList();
                ce2.a(string2, (LinkedList<dj>)object);
            } else {
                object = object3;
            }
            cf.a((dj)((Object)object4), object);
        }
        return object4;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static View a(Activity activity, JSONObject object, String string2, String string3, String string4, View view, AdapterView adapterView, HashMap<String, Object> hashMap) {
        activity3 = null;
        string5 = null;
        activity2 = activity3;
        System.currentTimeMillis();
        activity2 = activity3;
        ce2 = ce.a(string4);
        activity2 = activity3;
        businessSmsMessage = ce2.b(string2);
        if (businessSmsMessage == null) ** GOTO lbl25
        activity2 = activity3;
        object = (String)businessSmsMessage.getValue("View_fdes");
        {
            catch (Exception exception) {
                exception.printStackTrace();
                return activity2;
            }
        }
        try {
            businessSmsMessage.extendParamMap = hashMap;
            activity = cf.a(activity, businessSmsMessage, (String)object, view, (ViewGroup)adapterView, ce2);
            return activity;
        }
        catch (Exception var0_1) {
            activity2 = activity3;
            var0_1.printStackTrace();
            return null;
lbl25: // 1 sources:
            businessSmsMessage = string5;
            if (object == null) return businessSmsMessage;
            businessSmsMessage = string5;
            activity2 = activity3;
            if (object.has("View_fdes") == false) return businessSmsMessage;
            activity2 = activity3;
            string5 = object.getString("View_fdes");
            activity2 = activity3;
            businessSmsMessage = BusinessSmsMessage.createMsgObj();
            activity2 = activity3;
            businessSmsMessage.smsId = Long.parseLong((String)string2);
            activity2 = activity3;
            businessSmsMessage.viewType = 1;
            activity2 = activity3;
            businessSmsMessage.bubbleJsonObj = object;
            activity2 = activity3;
            businessSmsMessage.messageBody = string3;
            activity2 = activity3;
            businessSmsMessage.originatingAddress = string4;
            activity2 = activity3;
            businessSmsMessage.titleNo = object.optString("title_num");
            activity2 = activity3;
            businessSmsMessage.extendParamMap = hashMap;
            activity2 = activity3;
            businessSmsMessage.simIndex = XyUtil.getSimIndex(hashMap);
            activity2 = activity3;
            activity2 = activity = cf.a(activity, businessSmsMessage, string5, view, (ViewGroup)adapterView, ce2);
            ce2.a(string2, businessSmsMessage);
            return activity;
        }
    }

    @SuppressLint(value={"NewApi"})
    private static dj a(LinkedList<dj> linkedList) {
        if (linkedList != null) {
            dj dj2 = (dj)((Object)linkedList.pollFirst());
            if (dj2 != null) {
                cf.a(dj2, linkedList);
            }
            return dj2;
        }
        return null;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static JSONObject a(Activity activity, String string2, String string3, String string4, String string5, long l2, byte by2, View view, ViewGroup viewGroup, ViewGroup viewGroup2, AdapterView adapterView, HashMap<String, Object> hashMap, SdkCallBack sdkCallBack, boolean bl2) {
        if (StringUtils.isNull(string2)) {
            XyUtil.doXycallBackResult(sdkCallBack, -1, null, string2);
            return null;
        }
        try {
            ParseRichBubbleManager.queryDataByMsgItem(string2, string3, string5, l2, string4, 2, sdkCallBack, bl2, hashMap);
            do {
                return null;
                break;
            } while (true);
        }
        catch (Exception var0_1) {
            XyUtil.doXycallBackResult(sdkCallBack, -1, null, string2);
            var0_1.printStackTrace();
            return null;
        }
    }

    @SuppressLint(value={"NewApi"})
    private static void a(dj dj2, LinkedList<dj> linkedList) {
        linkedList.offerLast((Object)dj2);
    }
}

