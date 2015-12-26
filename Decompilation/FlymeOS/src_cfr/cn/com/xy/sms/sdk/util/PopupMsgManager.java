/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.ArrayList
 *  java.util.HashSet
 *  java.util.LinkedList
 */
package cn.com.xy.sms.sdk.util;

import android.content.Context;
import cn.com.xy.sms.sdk.smsmessage.BusinessSmsMessage;
import cn.com.xy.sms.sdk.util.StringUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PopupMsgManager {
    public static LinkedList<BusinessSmsMessage> businessSmsList = new LinkedList();
    public static HashSet<String> hasPhoneThird;
    public static boolean hasRemoveData;
    public static List<String> removePhoneNumList;

    static {
        hasRemoveData = false;
        removePhoneNumList = new ArrayList();
        hasPhoneThird = new HashSet();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static int addAllToFirst(LinkedList<BusinessSmsMessage> linkedList) {
        synchronized (PopupMsgManager.class) {
            int n2 = -1;
            int n3 = linkedList.size() - 1;
            int n4 = n2;
            if (n3 >= 0) {
                int n5 = businessSmsList.size();
                new StringBuilder("size: ").append(n3).append(" size2: ").append(n5);
                n4 = n3;
                while (n4 >= 0) {
                    BusinessSmsMessage businessSmsMessage = (BusinessSmsMessage)linkedList.get(n4);
                    Object object = businessSmsMessage.getValue("opensms_enable");
                    String string2 = StringUtils.getPhoneNumberNo86(businessSmsMessage.getOriginatingAddress());
                    if (!(object != null && "false".equalsIgnoreCase(object.toString()) || removePhoneNumList.contains(string2))) {
                        businessSmsList.addFirst((Object)businessSmsMessage);
                    } else {
                        if (n5 == 0 && n3 == 0) {
                            businessSmsList.addFirst((Object)businessSmsMessage);
                        }
                        n2 = 1;
                    }
                    --n4;
                }
                n4 = n2;
            }
            removePhoneNumList.clear();
            linkedList.clear();
            new StringBuilder("next addAllToFirst size: ").append(businessSmsList.size());
            return n4;
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public static boolean addThirdPopupMsgData(Map<String, Object> var0) {
        block6 : {
            var4_1 = StringUtils.getPhoneNumberNo86((String)var0.get("phoneNumber"));
            if (PopupMsgManager.businessSmsList != null && !PopupMsgManager.businessSmsList.isEmpty()) ** GOTO lbl6
            var2_3 = null;
            var1_6 = false;
            ** GOTO lbl25
lbl6: // 1 sources:
            var5_2 = PopupMsgManager.businessSmsList.iterator();
            var2_3 = null;
            do lbl-1000: // 6 sources:
            {
                if (var5_2 == null || !var5_2.hasNext()) {
                    var1_6 = false;
                    break block6;
                }
                var2_3 = var3_4 = (BusinessSmsMessage)var5_2.next();
                if (var3_4 == null) ** GOTO lbl-1000
                var2_3 = var3_4;
                if (var3_4.messageBody != null) ** GOTO lbl-1000
                var6_5 = StringUtils.getPhoneNumberNo86(var3_4.getOriginatingAddress());
                var2_3 = var3_4;
                if (StringUtils.isNull(var4_1)) ** GOTO lbl-1000
                var2_3 = var3_4;
                if (StringUtils.isNull(var6_5)) ** GOTO lbl-1000
                var2_3 = var3_4;
            } while (!var4_1.equals((Object)var6_5));
            var1_6 = true;
            var2_3 = var3_4;
        }
        if (var1_6 && var2_3 != null) {
            var2_3.valueMap = var0;
            var2_3.originatingAddress = var4_1;
            PopupMsgManager.businessSmsList.remove((Object)var2_3);
            var0 = var2_3;
            if (!PopupMsgManager.hasPhoneThird.contains((Object)var4_1)) {
                PopupMsgManager.hasPhoneThird.add((Object)var4_1);
                var0 = var2_3;
            }
        } else {
            var2_3 = new BusinessSmsMessage();
            var2_3.valueMap = var0;
            var2_3.originatingAddress = var4_1;
            var0 = var2_3;
        }
        if (var0 != null) {
            PopupMsgManager.businessSmsList.addLast(var0);
        }
        new StringBuilder("addThirdPopupMsgData phoneNumber").append(var4_1).append(" size :").append(PopupMsgManager.businessSmsList.size());
        return true;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void clearBusinessMessage(List<BusinessSmsMessage> list) {
        synchronized (PopupMsgManager.class) {
            int n2 = 0;
            if (list != null) {
                businessSmsList.removeAll(list);
                n2 = list.size();
            }
            new StringBuilder("\u6e05\u9664\u6240\u6709\u77ed\u4fe1 \u6570: ").append(n2).append(" \u5269\u4f59: ").append(businessSmsList.size());
            return;
        }
    }

    public static void clearPhoneThird() {
        hasPhoneThird.clear();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static int clearUserClickBusinessMessage() {
        synchronized (PopupMsgManager.class) {
            int n2 = businessSmsList.size();
            ArrayList arrayList = new ArrayList();
            int n3 = 0;
            do {
                if (n3 >= n2) {
                    if (arrayList.isEmpty()) return -1;
                    businessSmsList.removeAll((Collection)arrayList);
                    arrayList.clear();
                    return 1;
                }
                Object object = ((BusinessSmsMessage)businessSmsList.get(n3)).getValue("opensms_enable");
                if (object != null && "false".equalsIgnoreCase(object.toString())) {
                    arrayList.add((BusinessSmsMessage)businessSmsList.get(n3));
                }
                ++n3;
            } while (true);
        }
    }

    public static boolean containsPhoneThird(String string2) {
        return hasPhoneThird.contains((Object)string2);
    }

    public static int getBusinessMessageSize() {
        synchronized (PopupMsgManager.class) {
            int n2 = businessSmsList.size();
            return n2;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static BusinessSmsMessage getBussinessMessageByIndex(int n2) {
        synchronized (PopupMsgManager.class) {
            if (n2 < 0) return null;
            try {
                if (n2 >= businessSmsList.size()) return null;
                return (BusinessSmsMessage)businessSmsList.get(n2);
            }
            catch (Throwable var1_2) {
                var1_2.printStackTrace();
            }
            return null;
        }
    }

    public static boolean getHasRemoveData() {
        return hasRemoveData;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean removeBusinessMessage(BusinessSmsMessage businessSmsMessage) {
        synchronized (PopupMsgManager.class) {
            try {
                new StringBuilder("\u6e05\u9664\u77ed\u4fe1: phone ").append(businessSmsMessage.originatingAddress);
                return businessSmsList.remove((Object)businessSmsMessage);
            }
            catch (Throwable var0_1) {
                var0_1.printStackTrace();
                return false;
            }
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static BusinessSmsMessage removeBusinessMessageByIndex(int n2) {
        synchronized (PopupMsgManager.class) {
            try {
                return (BusinessSmsMessage)businessSmsList.remove(n2);
            }
            catch (Throwable var1_2) {
                var1_2.printStackTrace();
                return null;
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void removeBusinessMessageByNum(Context object, String string2, boolean bl2, Map<String, String> object2) {
        try {
            if (StringUtils.isNull(string2)) {
                return;
            }
            if (businessSmsList == null || businessSmsList.isEmpty()) return;
            {
                object = businessSmsList.iterator();
                while (object != null && object.hasNext()) {
                    object2 = (BusinessSmsMessage)object.next();
                    if (object2 == null) continue;
                    String string3 = StringUtils.getPhoneNumberNo86(string2);
                    String string4 = StringUtils.getPhoneNumberNo86(object2.getOriginatingAddress());
                    if (StringUtils.isNull(string3) || StringUtils.isNull(string4) || !string3.equals((Object)string4)) continue;
                    if (!bl2) {
                        if (!removePhoneNumList.contains(string3)) {
                            removePhoneNumList.add(string3);
                        }
                        object.remove();
                        hasRemoveData = true;
                        continue;
                    }
                    if (!bl2 || object2.messageBody != null) continue;
                    object.remove();
                }
                return;
            }
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    public static void removePhoneThird(String string2) {
        hasPhoneThird.remove((Object)string2);
    }

    public static void setHasRemoveData(boolean bl2) {
        hasRemoveData = bl2;
    }
}

