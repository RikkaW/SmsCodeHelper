/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.HashMap
 *  org.json.JSONArray
 *  org.json.JSONObject
 */
package cn.com.xy.sms.sdk.smsmessage;

import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.util.JsonUtil;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class BusinessSmsMessage
implements Serializable,
Cloneable {
    private static final long a = 1;
    public static BusinessSmsMessage emptyObj = null;
    private HashMap<String, List<Map<String, String>>> b = null;
    public JSONObject bubbleJsonObj = null;
    private HashMap<String, JSONArray> c = null;
    public HashMap<String, Object> extendParamMap = null;
    public HashMap<String, String> imagePathMap = null;
    public boolean isBgVis;
    public boolean isPopByWeishi = false;
    public String messageBody;
    public long msgTime = 0;
    public String originatingAddress;
    public boolean reBindData = false;
    public int simIndex = -1;
    public String simName = "";
    public long smsId = -1;
    public String titleNo;
    public Map<String, Object> valueMap = null;
    public byte viewType = 0;

    public static BusinessSmsMessage createMsgObj() {
        try {
            if (emptyObj == null) {
                emptyObj = new BusinessSmsMessage();
            }
            BusinessSmsMessage businessSmsMessage = (BusinessSmsMessage)emptyObj.clone();
            return businessSmsMessage;
        }
        catch (CloneNotSupportedException var0_1) {
            LogManager.e("duoqu_xiaoyuan", "BusinessSmsMessage createMsgObj: " + var0_1.getMessage(), var0_1);
            var0_1.printStackTrace();
            return new BusinessSmsMessage();
        }
    }

    public Object clone() {
        return super.clone();
    }

    public JSONArray getActionJsonArray() {
        Object object = this.getValue("ADACTION");
        if (object == null) {
            return null;
        }
        if (object instanceof JSONArray) {
            return (JSONArray)object;
        }
        object = JsonUtil.parseStrToJsonArray((String)object);
        this.putValue("ADACTION", object);
        return object;
    }

    public String getCenterAddress() {
        return "";
    }

    public Object getExtendParamValue(String string2) {
        if (this.extendParamMap != null) {
            return this.extendParamMap.get((Object)string2);
        }
        return null;
    }

    public String getImgNameByKey(String string2) {
        if (this.viewType == 0) {
            if (this.imagePathMap != null) {
                return (String)this.imagePathMap.get((Object)string2);
            }
        } else if (this.bubbleJsonObj != null && this.bubbleJsonObj.has(string2)) {
            try {
                string2 = (String)this.bubbleJsonObj.get(string2);
                return string2;
            }
            catch (Throwable var1_2) {
                var1_2.printStackTrace();
            }
        }
        return null;
    }

    public String getMessageBody() {
        return this.messageBody;
    }

    public String getOriginatingAddress() {
        return this.originatingAddress;
    }

    public long getSmsId() {
        return this.smsId;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public Object getTableData(int n2, String object) {
        if (this.viewType == 0) {
            if (this.b == null) return null;
            if ((object = (List)this.b.get(object)) == null) return null;
            if (n2 < 0) return null;
            try {
                if (object.size() <= n2) return null;
                return object.get(n2);
            }
            catch (Throwable var2_3) {
                var2_3.printStackTrace();
            }
            do {
                return null;
                break;
            } while (true);
        }
        if (this.c == null) return null;
        try {
            if ((object = (JSONArray)this.c.get(object)) == null) return null;
            if (n2 < 0) return null;
        }
        catch (Throwable var2_4) {
            var2_4.printStackTrace();
            return null;
        }
        if (object.length() <= n2) return null;
        return object.get(n2);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public int getTableDataSize(String string2) {
        try {
            int n2;
            JSONArray jSONArray;
            if (this.viewType == 0) {
                List list;
                if (this.b == null) {
                    this.b = new HashMap();
                }
                List list2 = list = (List)this.b.get((Object)string2);
                if (list == null) {
                    Object object = this.getValue(string2);
                    list2 = list;
                    if (object != null) {
                        list2 = list;
                        if (object instanceof List) {
                            list2 = (List)object;
                            this.b.put((Object)string2, (Object)list2);
                        }
                    }
                }
                if (list2 == null) return 0;
                return list2.size();
            }
            if (this.c == null) {
                this.c = new HashMap();
            }
            JSONArray jSONArray2 = jSONArray = (JSONArray)this.c.get((Object)string2);
            if (jSONArray == null) {
                Object object = this.getValue(string2);
                jSONArray2 = jSONArray;
                if (object != null) {
                    jSONArray2 = jSONArray;
                    if (object instanceof JSONArray) {
                        jSONArray2 = (JSONArray)object;
                        this.c.put((Object)string2, (Object)jSONArray2);
                    }
                }
            }
            if (jSONArray2 == null) return 0;
            int n3 = n2 = jSONArray2.length();
            if (n2 <= 5) return n3;
            return 5;
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
        }
        return 0;
    }

    public String getTitleNo() {
        return this.titleNo;
    }

    public Object getValue(String object) {
        if (this.viewType == 0) {
            if (this.valueMap != null) {
                return this.valueMap.get(object);
            }
        } else if (this.bubbleJsonObj != null && this.bubbleJsonObj.has((String)object)) {
            try {
                object = this.bubbleJsonObj.get((String)object);
                return object;
            }
            catch (Throwable var1_2) {
                var1_2.printStackTrace();
            }
        }
        return null;
    }

    public boolean isDataNull(String object) {
        if (this.viewType == 0 ? this.b != null && (object = (List)this.b.get(object)) != null && object.size() > 0 : this.c != null && (object = (JSONArray)this.c.get(object)) != null && object.length() > 0) {
            return false;
        }
        return true;
    }

    public void putValue(String string2, Object object) {
        if (string2 == null || object == null) {
            return;
        }
        if (this.viewType == 0) {
            if (this.valueMap == null) {
                this.valueMap = new HashMap();
            }
            this.valueMap.put(string2, object);
            return;
        }
        if (this.bubbleJsonObj == null) {
            this.bubbleJsonObj = new JSONObject();
        }
        try {
            this.bubbleJsonObj.put(string2, object);
            return;
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
            return;
        }
    }

    public void setMessageBody(String string2) {
        this.messageBody = string2;
    }

    public void setOriginatingAddress(String string2) {
        this.originatingAddress = string2;
    }

    public void setSmsId(long l2) {
        this.smsId = l2;
    }

    public void setTitleNo(String string2) {
        this.titleNo = string2;
    }
}

