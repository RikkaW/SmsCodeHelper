/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.Bitmap
 *  android.widget.RemoteViews
 *  java.lang.Object
 *  java.lang.String
 *  org.json.JSONException
 *  org.json.JSONObject
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.RemoteViews;
import cn.com.xy.sms.sdk.ui.notification.MessageItem;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class ci
extends cg {
    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void a(Context object, Bitmap bitmap, String string2, String string3, JSONObject jSONObject, MessageItem messageItem) {
        super.a((Context)object, bitmap, string2, string3, jSONObject, messageItem);
        if (jSONObject != null && jSONObject.length() > 0) {
            try {
                this.a.setViewVisibility(br.d.duoqu_drop_btn_layout, 0);
                this.a.setTextViewText(br.d.duoqu_drop_btn_one, (CharSequence)((String)jSONObject.get("readBtn")));
                if (jSONObject.length() == 3) {
                    this.a.setViewVisibility(br.d.duoqu_drop_split_line1, 0);
                    this.a.setViewVisibility(br.d.duoqu_drop_btn_two, 0);
                    this.a.setTextViewText(br.d.duoqu_drop_btn_two, (CharSequence)((String)jSONObject.get("deleteBtn")));
                    this.a.setViewVisibility(br.d.duoqu_drop_split_line2, 0);
                    this.a.setViewVisibility(br.d.duoqu_drop_btn_three, 0);
                    this.a.setTextViewText(br.d.duoqu_drop_btn_three, (CharSequence)((String)jSONObject.get("replyBtn")));
                } else {
                    if (jSONObject.length() == 4) {
                        this.a.setViewVisibility(br.d.duoqu_drop_split_line1, 0);
                        this.a.setViewVisibility(br.d.duoqu_drop_btn_two, 0);
                        this.a.setTextViewText(br.d.duoqu_drop_btn_two, (CharSequence)((String)jSONObject.get("deleteBtn")));
                        this.a.setViewVisibility(br.d.duoqu_drop_split_line2, 0);
                        this.a.setViewVisibility(br.d.duoqu_drop_btn_three, 0);
                        this.a.setTextViewText(br.d.duoqu_drop_btn_three, (CharSequence)((String)jSONObject.get("btn1")));
                    }
                    if (jSONObject.length() == 5) {
                        this.a.setViewVisibility(br.d.duoqu_drop_split_line1, 0);
                        this.a.setViewVisibility(br.d.duoqu_drop_btn_two, 0);
                        this.a.setTextViewText(br.d.duoqu_drop_btn_two, (CharSequence)((String)jSONObject.get("btn2")));
                        this.a.setViewVisibility(br.d.duoqu_drop_split_line2, 0);
                        this.a.setViewVisibility(br.d.duoqu_drop_btn_three, 0);
                        this.a.setTextViewText(br.d.duoqu_drop_btn_three, (CharSequence)((String)jSONObject.get("btn1")));
                    }
                }
            }
            catch (JSONException var1_2) {
                var1_2.printStackTrace();
            }
        }
        if (messageItem.d.isEmpty() || !messageItem.d.containsKey("simIndex")) return;
        {
            object = messageItem.d.get("simIndex");
            if ("0".equals(object)) {
                this.a.setViewVisibility(br.d.duoqu_drop_sim_status, 0);
                this.a.setImageViewResource(br.d.duoqu_drop_sim_status, br.c.duoqu_notice_sim1_list);
                return;
            } else {
                if (!"1".equals(object)) return;
                {
                    this.a.setViewVisibility(br.d.duoqu_drop_sim_status, 0);
                    this.a.setImageViewResource(br.d.duoqu_drop_sim_status, br.c.duoqu_notice_sim2_list);
                    return;
                }
            }
        }
    }
}

