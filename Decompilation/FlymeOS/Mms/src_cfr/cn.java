/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.os.AsyncTask
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  org.json.JSONObject
 */
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import cn.com.xy.sms.sdk.smsmessage.BusinessSmsMessage;
import cn.com.xy.sms.sdk.util.DuoquUtils;
import cn.com.xy.sms.sdk.util.JsonUtil;
import cn.com.xy.sms.sdk.util.StringUtils;
import org.json.JSONObject;

class cn
extends AsyncTask {
    final /* synthetic */ BusinessSmsMessage a;
    final /* synthetic */ cm b;

    cn(cm cm2, BusinessSmsMessage businessSmsMessage) {
        this.b = cm2;
        this.a = businessSmsMessage;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected /* varargs */ BusinessSmsMessage a(Object ... object) {
        BusinessSmsMessage businessSmsMessage = (BusinessSmsMessage)object[0];
        object = (String)businessSmsMessage.getValue("view_side_phone_num");
        Object object2 = DuoquUtils.getSdkDoAction().getContactObj((Context)this.b.f, (String)object);
        if (object2 == null) {
            businessSmsMessage.putValue("smart_call_data_null", "true");
        } else {
            object = JsonUtil.getValFromJsonObject((JSONObject)object2, "contact_type");
            if (object != null) {
                try {
                    object = (Integer)object;
                }
                catch (Exception var1_2) {
                    object = 1;
                }
                if (object == null || object.intValue() == 0 || 1 != object.intValue()) {
                    // empty if block
                }
            }
            object = (String)JsonUtil.getValFromJsonObject((JSONObject)object2, "contact_name");
        }
        object2 = this.a.getImgNameByKey("v_hd_bg");
        String string2 = this.a.getImgNameByKey("v_hd_bg_fir");
        if (StringUtils.isNull((String)object2)) {
            object2 = string2;
        }
        businessSmsMessage.putValue("smart_call_number_text_color", object2);
        businessSmsMessage.putValue("smart_call_number_text", object);
        businessSmsMessage.putValue("smart_call_data_null", "false");
        return businessSmsMessage;
    }

    protected /* synthetic */ Object doInBackground(Object[] arrobject) {
        return this.a(arrobject);
    }

    protected void onPostExecute(Object object) {
        if (this.b.g == object) {
            cm.a(this.b, (String)this.b.g.getValue("smart_call_number_text"), (String)this.b.g.getValue("smart_call_number_text_color"), (String)this.b.g.getValue("view_side_phone_num"));
        }
    }
}

